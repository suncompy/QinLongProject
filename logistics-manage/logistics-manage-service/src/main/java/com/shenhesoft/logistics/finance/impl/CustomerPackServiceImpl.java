package com.shenhesoft.logistics.finance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shenhesoft.logistics.business.bussinessCount.BussinessCountService;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderMapper;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.common.util.StringUtils;
import com.shenhesoft.logistics.finance.CustomerCheckingConf;
import com.shenhesoft.logistics.finance.CustomerCheckingConfService;
import com.shenhesoft.logistics.finance.CustomerPack;
import com.shenhesoft.logistics.finance.CustomerPackOrder;
import com.shenhesoft.logistics.finance.CustomerPackService;
import com.shenhesoft.logistics.finance.FinanceAccountDetail;
import com.shenhesoft.logistics.finance.InvoiceService;
import com.shenhesoft.logistics.finance.mapper.CustomerCheckingConfMapper;
import com.shenhesoft.logistics.finance.mapper.CustomerPackInvoiceMapper;
import com.shenhesoft.logistics.finance.mapper.CustomerPackMapper;
import com.shenhesoft.logistics.finance.mapper.CustomerPackOrderMapper;
import com.shenhesoft.logistics.finance.mapper.FinanceAccountDetailMapper;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.CodeService;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 客户对账打包信息表-业务实现.
 * <p>
 * <a href="CustomerPackServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CustomerPackServiceImpl implements CustomerPackService {

  @Autowired
  private CustomerPackMapper customerPackMapper;
  @Autowired
  private CodeService codeService;
  @Autowired
  private FinanceAccountDetailMapper financeAccountDetailMapper;
  @Autowired
  private CustomerPackInvoiceMapper customerPackInvoiceMapper;
  @Autowired
  private InvoiceService invoiceService;
  @Autowired
  private CustomerCheckingConfMapper customerCheckingConfMapper;
  /**
   * 客户打包-运单中间表-Dao.
   */
  @Autowired
  private CustomerPackOrderMapper customerPackOrderMapper;
  @Autowired
  private BussinessCountService bussinessCountService;
  @Autowired
  private CustomerCheckingConfService customerCheckingConfService;
  /**
   * 短驳运单 mapper
   */
  @Autowired
  private TbOrderMapper tbOrderMapper;
  /**
   * 火运运单 mapper
   */
  @Autowired
  private TbTrainOrderMapper trainOrderMapper;

  /**
   * 新增客户对账打包信息表.
   * 
   * @param customerPack 客户对账打包信息表实体
   * @return 页面表单
   */
  @OrgLinkData(idName="custPackId",tabComment="客户对账打包")
  public CustomerPack addCustomerPack(CustomerPack customerPack) {
    String sysOrgCode=AppSession.getCurrentSysOrgCode();
    String id = codeService.createCheckFinCode(sysOrgCode, customerPack.getProjectId());
    // 生成id
    customerPack.setCustPackId(id);
    // 对账状态(0-对账待审核 1-对账财务审核通过 2-对账财务审核不通过 3-已解包 )
    customerPack.setCheckingStatus(0);
    // 结算审核状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)
    customerPack.setSettleAuditType(0);
    // 发票状态(0-新建 1-已登入 2-发票作废待审核 3-已作废 4-作废审核不通过)
    customerPack.setInvoiceStatus(0);
    // 是否删除(0-未删除 1-删除)
    customerPack.setDeleteFlag(0);
    // 创建时间
    customerPack.setCreateDate(DateUtils.date2Str(DateUtils.datetimeFormat));
    // 创建人
    customerPack.setCreateUserId(AppSession.getCurrentUserId());

    List<String> orderIdList = getOrderIds(customerPack);//customerPack.getOrderIds();

    Integer orderCount = 0;// 运单总数
    BigDecimal totalWeight = new BigDecimal(0);// 总净重
    Integer totalPiece = 0;// 总件数
    BigDecimal produceMoney = new BigDecimal(0);// 产生金额 运费
    if (!CollectionUtils.isEmpty(orderIdList)) {
      for (String orderId : orderIdList) {
        if (StringUtil.isEmpty(orderId))
          continue;
        // 回单-第一个集装箱到货净重
        BigDecimal containerOneSendNet = new BigDecimal(0);
        // 回单-第一个集装箱到货净重
        BigDecimal containerTwoSendNet = new BigDecimal(0);
        // 短驳费用
        BigDecimal shortBargeCost = new BigDecimal(0);
        // 得到运单信息
        if (customerPack.getPackType().intValue()==0 || customerPack.getPackType().intValue()==1
            || customerPack.getPackType().intValue()==2) {
          TbOrder tbOrder = tbOrderMapper.selectBoxManagerOrderById(Integer.valueOf(orderId));
          // 回单-第一个集装箱到货净重
          containerOneSendNet = tbOrder.getContainerOneSendNet() == null ? new BigDecimal(0)
              : tbOrder.getContainerOneSendNet();
          // 回单-第二个集装箱到货净重
          containerOneSendNet = tbOrder.getContainerTwoSendNet() == null ? new BigDecimal(0)
              : tbOrder.getContainerTwoSendNet();
          // 短驳费用
          shortBargeCost = null==tbOrder.getShortBargeCost()?new BigDecimal(0):tbOrder.getShortBargeCost();
          int pieceNew =null==tbOrder.getPieceNumber()?0:tbOrder.getPieceNumber().intValue();
          totalPiece = totalPiece+pieceNew;
        } else if (customerPack.getPackType().intValue()==3) {
          TbTrainOrder tbTrainOrder = trainOrderMapper.selectByPrimaryKey(Integer.valueOf(orderId));
          containerOneSendNet = tbTrainOrder.getEntruckWeight() == null ? new BigDecimal(0)
              : tbTrainOrder.getEntruckWeight();// 到货载重
        }

        totalWeight = totalWeight.add(containerOneSendNet.add(containerTwoSendNet));
        produceMoney = produceMoney.add(shortBargeCost);
        CustomerPackOrder customerPackOrder = new CustomerPackOrder();
        // 主键
        customerPackOrder.setCustPackOrderId(AppUtils.randomUUID());
        // 客户对账打包信息id
        customerPackOrder.setCustPackId(customerPack.getCustPackId());
        // 运单id
        customerPackOrder.setOrderId(Integer.valueOf(orderId));
        // 是否删除(0-未删除 1-删除)
        customerPackOrder.setDeleteFlag(0);
        customerPackOrderMapper.addCustomerPackOrder(customerPackOrder);
        orderCount++;
      }
    }
    Map<String, Object> form = Maps.newHashMap();
    CustomerCheckingConf customerCheckingConf = new CustomerCheckingConf();
    form.put("custCheckConId", customerPack.getCustCheckConId());
    customerCheckingConf.setCustCheckConId(customerPack.getCustCheckConId());
    if (null == customerPack.getPackType()) {
    }
    // 0接取
    else if (1 == customerPack.getPackType().intValue()) {
      customerCheckingConf.setReceiveCheckingStatus(1);
      form.put("stepSelectCode", 0);
    }
    // 1送达
    else if (2 == customerPack.getPackType().intValue()) {
      customerCheckingConf.setDeliCheckingStatus(1);
      form.put("stepSelectCode", 1);
    }
    // 2汽运
    else if (0 == customerPack.getPackType().intValue()) {
      customerCheckingConf.setBusCheckingStatus(1);
      form.put("stepSelectCode", 2);
    }
    // 3火运
    else if (3 == customerPack.getPackType().intValue()) {
      customerCheckingConf.setTrainCheckingStatus(1);
      form.put("stepSelectCode", 3);
    } else {
    }
    form.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
    List<Map<String, Object>> custChecks =
        customerCheckingConfMapper.getCustomerCheckingConfs(form);
    if (CollectionUtils.isEmpty(custChecks) || CollectionUtils.isEmpty(custChecks.get(0))) {
      customerPack.setOrderCount(orderCount);// 运单总数
      customerPack.setTotalWeight(totalWeight);// 总净重
      customerPack.setTotalPiece(totalPiece);// 总件数
      customerPack.setProduceMoney(produceMoney);// 产生金额
      // 保存客户对账打包信息表
      customerPackMapper.addCustomerPack(customerPack);
      return customerPack;
    }
    Map<String, Object> data = custChecks.get(0);
    orderCount =
        null == data.get("totalOrder") ? 0 : Integer.parseInt(data.get("totalOrder").toString());
    totalWeight = null == data.get("totalWeight") ? new BigDecimal("0")
        : new BigDecimal(data.get("totalWeight").toString());
    totalPiece =
        null == data.get("totalPiece") ? 0 : Integer.parseInt(data.get("totalPiece").toString());
    produceMoney = null == data.get("produceMoney") ? new BigDecimal("0")
        : new BigDecimal(data.get("produceMoney").toString());
    BigDecimal taxMoney = null == data.get("taxMoney") ? new BigDecimal("0")
        : new BigDecimal(data.get("taxMoney").toString());
    String startDate = null == data.get("startDate") ? null : data.get("startDate").toString();
    String endDate = null == data.get("endDate") ? null : data.get("endDate").toString();
    customerPack.setOrderCount(orderCount);// 运单总数
    customerPack.setTotalWeight(totalWeight);// 总净重
    customerPack.setTotalPiece(totalPiece);// 总件数
    customerPack.setProduceMoney(produceMoney);// 产生金额
    customerPack.setTaxMoney(taxMoney);
    customerPack.setStartDate(startDate);
    customerPack.setEndDate(endDate);
    // 保存客户对账打包信息表
    customerPackMapper.addCustomerPack(customerPack);
    
    customerCheckingConfMapper.editCustomerCheckingConfById(customerCheckingConf);
    return customerPack;
  }

  private List<String> getOrderIds(CustomerPack customerPack){
    Map<String, Object> map = ImmutableMap.of("projectId", customerPack.getProjectId()
        , "custCheckConId", customerPack.getCustCheckConId(), "orderType", customerPack.getOrderType());
    List<Map<String, Object>> list = customerCheckingConfService.getOrdersByCustCheckConId(0, 10000, map);
    if(CollectionUtils.isEmpty(list)){
      return Lists.newArrayList();
    }
    List<String> ids = Lists.newArrayList();
    for(Map<String, Object> orderinfo:list){
      if(CollectionUtils.isEmpty(orderinfo) || null==orderinfo.get("id")
          ||StringUtils.isBlank(orderinfo.get("id").toString())){
        continue;
      }
      ids.add(orderinfo.get("id").toString());
    }
    return ids;
  }
  /**
   * 查看客户对账打包信息表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  public Map<String, Object> getCustomerPackById(String id) {
    return customerPackMapper.getCustomerPackById(id);
  }

  /**
   * 修改客户对账打包信息表.
   * 
   * @param customerPack 客户对账打包信息表实体
   */
  public void editCustomerPackById(CustomerPack customerPack) {
    customerPackMapper.editCustomerPackById(customerPack);
  }

  /**
   * 删除指定客户对账打包信息表.
   * 
   * @param id 主键
   */
  public void delCustomerPackById(String id) {
    customerPackMapper.delCustomerPackById(id);
  }

  /**
   * 批量删除指定客户对账打包信息表.
   * 
   * @param ids 主键集合
   */
  public void delCustomerPackByIds(List<String> ids) {
    customerPackMapper.delCustomerPackByIds(ids);
  }

  /**
   * 清空计量单位表.
   */
  public void delCustomerPacks() {
    customerPackMapper.delCustomerPacks();
  }

  /**
   * 获取所有客户对账打包信息表.
   * 
   * @return 客户对账打包信息表分页
   */
  public List<Map<String, Object>> getCustomerPacks(int start, int pageSize,
      Map<String, Object> form) {
    PageHelper.offsetPage(start, pageSize);
    return this.getCustomerPacks(form);
  }

  /**
   * 获取所有客户对账打包信息表.
   * 
   * @return 客户对账打包信息表
   */
  public List<Map<String, Object>> getCustomerPacks(Map<String, Object> form) {
    return customerPackMapper.getCustomerPacks(form);
  }

  @Override
  public void editInitialFinanceAuditCustomerPackById(String custPackIds, String passFlag ,TbSystemUser user) {
    if (StringUtil.isEmpty(custPackIds) || StringUtil.isEmpty(passFlag))
      return;
    List<String> custPackIdList = Arrays.asList(custPackIds.trim().split(","));
    CustomerPack customerPack = new CustomerPack();
    if (passFlag.trim().equals("0")) {// 审核通过
      customerPack.setCheckingStatus(1);// 对账状态(0-对账待审核 1-对账财务审核通过
      customerPack.setCheckAuditFlag("finace");
      customerPack.setCheckAuditId(null==AppSession.getCurrentUserId()?user.getId().toString():AppSession.getCurrentUserId().toString());
      customerPack.setCheckAuditDate(DateUtils.date2Str(DateUtils.datetimeFormat));
      Map<String,Object> requestMap = ImmutableMap.of("custPackIds", custPackIdList,"sysOrgCode", null==user?AppSession.getCurrentSysOrgCode():user.getSysOrgCode());
      List<Map<String,Object>> list = customerPackMapper.getCustomerPacks(requestMap);
      invoiceService.addInvoices(list,"1");                                  // 2-对账财务审核不通过 3-已解包 )
    } else if (passFlag.trim().equals("1")) {
      customerPack.setCheckingStatus(2);
      customerPack.setCheckAuditFlag("finace");
      customerPack.setCheckAuditId(null==AppSession.getCurrentUserId()?null:AppSession.getCurrentUserId().toString());
      customerPack.setCheckAuditDate(DateUtils.date2Str(DateUtils.datetimeFormat));
    }
    customerPackMapper.editCustomerPackByIds(customerPack, custPackIdList);
  }
  
  @Override
  public void editDissolveCustomerPackById(String custPackIds) {
    if (StringUtil.isEmpty(custPackIds))
      return;
    List<String> custPackIdList = Arrays.asList(custPackIds.trim().split(","));
    //
    if(CollectionUtils.isEmpty(custPackIdList)){
      return;
    }
    for (String id : custPackIdList) {
      Map<String, Object> map = customerPackMapper.getCustomerPackById(id);
      CustomerCheckingConf customerCheckingConf = new CustomerCheckingConf();
      customerCheckingConf.setCustCheckConId(map.get("custCheckConId").toString());
      if(null==map.get("packType")){
      }else if ("0".equals(map.get("packType").toString())) {
        customerCheckingConf.setBusCheckingStatus(0);
      } else if ("1".equals(map.get("packType").toString())) {
        customerCheckingConf.setReceiveCheckingStatus(0);
      } else if ("2".equals(map.get("packType").toString())) {
        customerCheckingConf.setDeliCheckingStatus(0);
      } else if ("3".equals(map.get("packType").toString())) {
        customerCheckingConf.setTrainCheckingStatus(0);
      }
      customerCheckingConfMapper.editCustomerCheckingConfById(customerCheckingConf);
    }
    CustomerPack customerPack = new CustomerPack();
    customerPack.setDeleteFlag(1);// 已删除
    customerPack.setCheckingStatus(3);// 已解包
    // customerPackMapper.editCustomerPackByIds(customerPack, custPackIdList);
    customerPackMapper.delCustomerPackByIds(custPackIdList);
    // 解包后 清理客户打包运单中间表信息
    List<Map<String, Object>> customerPackOrderMapList =
        customerPackOrderMapper.listCustomerPackOrderByCustPackIds(custPackIdList);
    if (customerPackOrderMapList == null || customerPackOrderMapList.size() == 0)
      return;
    List<String> shPackOrderIdList = new ArrayList<String>();
    for (Map<String, Object> map : customerPackOrderMapList) {
      if (map.get("custPackOrderId") == null)
        continue;
      shPackOrderIdList.add(map.get("custPackOrderId").toString());
    }
    CustomerPackOrder customerPackOrder = new CustomerPackOrder();
    customerPackOrder.setDeleteFlag(1);//// 是否删除(0-未删除 1-删除)
    // customerPackOrderMapper.editCustomerPackOrderByIds(customerPackOrder, shPackOrderIdList);
    customerPackOrderMapper.delCustomerPackOrderByIds(shPackOrderIdList);
  }

  @Override
  public void editInitialAgainstAuditCustomerPackById(String custPackIds) {
    if (StringUtil.isEmpty(custPackIds))
      return;
    List<String> custPackIdList = Arrays.asList(custPackIds.trim().split(","));
    CustomerPack customerPack = new CustomerPack();
    customerPack.setCheckingStatus(0);// 对账状态(0-对账待审核 1-对账财务审核通过 2-对账财务审核不通过
                                      // 3-已解包 )
    customerPack.setCheckAuditFlag("finace");
    customerPack.setCheckAuditId(null);
    customerPack.setCheckAuditDate(null);
    customerPackMapper.editCustomerPackByIds(customerPack, custPackIdList);
  }

  /**
   * 结算
   */
  @Override
  public void editSettlePassCustomerPackById(CustomerPack customerPack) {
    if (StringUtil.isEmpty(customerPack.getCustPackId()))
      return;
    TbSystemUser user = AppSession.getCurrentUser();
    // List<String> custPackIdList = Arrays.asList(custPackIds.trim().split(","));
    // 结算审核状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)
    customerPack.setSettleAuditType(1);
    customerPack.setSettleId(user.getId().toString());
    customerPack.setSettleDate(DateUtils.date2Str(DateUtils.datetimeFormat));
    customerPackMapper.editCustomerPackById(customerPack);
  }

  // 财务审核
  @Override
  public void editSettleFinanceAuditCustomerPackById(String custPackIds, String passFlag) {
    if (StringUtil.isEmpty(custPackIds) || StringUtil.isEmpty(passFlag))
      return;
    // List<String> custPackIdList = Arrays.asList(custPackIds.trim().split(","));
    CustomerPack customerPack = new CustomerPack();
    TbSystemUser user = AppSession.getCurrentUser();
    // 查询当前结算金额、已结算金额等
    Map<String, Object> map = customerPackMapper.queryAuditMoney(custPackIds);
    String settleMoneyStr = FormUtil.getMapValue(map, "settleMoney") == null ? "0"
        : FormUtil.getMapValue(map, "settleMoney");
    String settledMoneyStr = FormUtil.getMapValue(map, "settledMoney") == null ? "0"
        : FormUtil.getMapValue(map, "settledMoney");
    String besettledMoneyStr = FormUtil.getMapValue(map, "besettledMoney") == null ? "0"
        : FormUtil.getMapValue(map, "besettledMoney");
    String settleTypeStr = FormUtil.getMapValue(map, "settleType") == null ? "0"
        : FormUtil.getMapValue(map, "settleType");
    double settleMoney = Double.parseDouble(settleMoneyStr);
    double settledMoney = Double.parseDouble(settledMoneyStr);
    double besettledMoney = Double.parseDouble(besettledMoneyStr);
    int settleType = Integer.valueOf(settleTypeStr);
    besettledMoney = besettledMoney - settleMoney;
    settledMoney += settleMoney;
    customerPack.setCustPackId(custPackIds);
    customerPack.setAuditId(user.getId().toString());
    customerPack.setAuditDate(DateUtils.date2Str(DateUtils.datetimeFormat));
    if (passFlag.trim().equals("0")) {// 审核通过
      // 结算审核状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)
      customerPack.setSettleAuditType(2);
      customerPack.setSettleType(settleType);
      customerPack.setSettleMoney(BigDecimal.valueOf(settleMoney));
      customerPack.setSettledMoney(BigDecimal.valueOf(settledMoney));
      customerPack.setBesettledMoney(BigDecimal.valueOf(besettledMoney));

      Map<String, Object> mapp = customerPackMapper.getCustomerPackById(custPackIds);
      Map<String, Object> mapc =
          customerPackInvoiceMapper.getCustomerPackInvoiceByCustPackId(custPackIds);

      if (mapc != null) {
        FinanceAccountDetail financeAccountDetail = new FinanceAccountDetail();
        financeAccountDetail.setDepositAccountId((Integer) mapc.get("receiveCompanyId"));
        financeAccountDetail.setPayAccountId((Integer) mapc.get("provideCompanyId"));
        financeAccountDetail.setMoney((BigDecimal) mapp.get("settleMoney"));
        financeAccountDetail.setOperateId(user.getId());
        financeAccountDetail.setPayDate(DateUtils.date2Str(DateUtils.datetimeFormat));
        financeAccountDetail.setStatementNum(custPackIds);
        financeAccountDetailMapper.addFinanceAccountDetail(financeAccountDetail);
      }

    } else if (passFlag.trim().equals("1")) {// 审核不通过
      customerPack.setSettleAuditType(3);
    }
    customerPackMapper.editCustomerPackById(customerPack);
  }

  // 反审核
  @Override
  public void editSettleAgainstAuditCustomerPackById(String custPackIds) {
    if (StringUtil.isEmpty(custPackIds))
      return;
    CustomerPack customerPack = new CustomerPack();
    TbSystemUser user = AppSession.getCurrentUser();
    // 查询当前结算金额、已结算金额等
    Map<String, Object> map = customerPackMapper.queryAuditMoney(custPackIds);
    String settleMoneyStr = FormUtil.getMapValue(map, "settleMoney") == null ? "0"
        : FormUtil.getMapValue(map, "settleMoney");
    String settledMoneyStr = FormUtil.getMapValue(map, "settledMoney") == null ? "0"
        : FormUtil.getMapValue(map, "settledMoney");
    String besettledMoneyStr = FormUtil.getMapValue(map, "besettledMoney") == null ? "0"
        : FormUtil.getMapValue(map, "besettledMoney");
    String settleTypeStr = FormUtil.getMapValue(map, "settleType") == null ? "0"
        : FormUtil.getMapValue(map, "settleType");
    double settleMoney = Double.parseDouble(settleMoneyStr);
    double settledMoney = Double.parseDouble(settledMoneyStr);
    double besettledMoney = Double.parseDouble(besettledMoneyStr);
    int settleType = Integer.valueOf(settleTypeStr);
    besettledMoney += settleMoney;
    settledMoney -= settleMoney;
    customerPack.setSettleMoney(BigDecimal.valueOf(settleMoney));
    customerPack.setSettledMoney(BigDecimal.valueOf(settledMoney));
    customerPack.setBesettledMoney(BigDecimal.valueOf(besettledMoney));
    customerPack.setSettleType(settleType);
    customerPack.setCustPackId(custPackIds);
    customerPack.setAuditId(user.getId().toString());
    customerPack.setAuditDate(DateUtils.date2Str(DateUtils.datetimeFormat));

    // 结算审核状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)
    customerPack.setSettleAuditType(1);
    customerPackMapper.editCustomerPackById(customerPack);
  }

  @Override
  public void editInvoiceCancelApplyCustomerPackById(String custPackIds) {
    if (StringUtil.isEmpty(custPackIds))
      return;
    List<String> custPackIdList = Arrays.asList(custPackIds.trim().split(","));
    CustomerPack customerPack = new CustomerPack();
    // 发票状态(0-新建 1-已登入 2-发票作废待审核 3-已作废 4-作废审核不通过)
    customerPack.setInvoiceStatus(2);
    customerPackMapper.editCustomerPackByIds(customerPack, custPackIdList);
  }

  @Override
  public void editInvoiceCancelAuditCustomerPackById(String custPackIds, String passFlag) {
    if (StringUtil.isEmpty(custPackIds) || StringUtil.isEmpty(passFlag))
      return;
    List<String> custPackIdList = Arrays.asList(custPackIds.trim().split(","));
    CustomerPack customerPack = new CustomerPack();
    if (passFlag.trim().equals("0")) {// 审核通过
      // 发票状态(0-新建 1-已登入 2-发票作废待审核 3-已作废 4-作废审核不通过)
      customerPack.setInvoiceStatus(3);
    } else if (passFlag.trim().equals("1")) {
      customerPack.setInvoiceStatus(4);
    }
    customerPackMapper.editCustomerPackByIds(customerPack, custPackIdList);

  }

  @Override
  public void editInvoiceCancelAgainstAuditCustomerPackById(String custPackIds) {
    if (StringUtil.isEmpty(custPackIds))
      return;
    List<String> custPackIdList = Arrays.asList(custPackIds.trim().split(","));
    CustomerPack customerPack = new CustomerPack();
    // 发票状态(0-新建 1-已登入 2-发票作废待审核 3-已作废 4-作废审核不通过)
    customerPack.setInvoiceStatus(0);
    customerPackMapper.editCustomerPackByIds(customerPack, custPackIdList);
  }

  @Override
  public List<Map<String, Object>> getOrderInfoByCustPackId(int start, int pageSize,
      Map<String, Object> innerMap) {
    if (innerMap == null)
      return null;
    String custPackId = innerMap.get("custPackId").toString();
    if (StringUtil.isEmpty(custPackId))
      return null;
    Map<String, Object> customerPackMap = this.getCustomerPackById(custPackId);
    if (customerPackMap == null)
      return null;
    String packType = customerPackMap.get("packType").toString();
    customerPackMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
    List<Map<String, Object>> dataList = null;
    PageHelper.offsetPage(start, pageSize);
    if (packType.equals("0") || packType.equals("1") || packType.equals("2")) {// 0接取
                                                                               // 1：送达
                                                                               // 2汽运
                                                                               // 3火运
      dataList = tbOrderMapper.listTbOrderByCustomerPackMap(customerPackMap);
    } else if (packType.equals("3")) {
      dataList = trainOrderMapper.listTrainOrderByCustomerPackMap(customerPackMap);
    }
    return dataList;
  }

  @Override
  public List<Map<String, Object>> getCustomerPacksByProjectId(String projectId) {
    Map<String, Object> form = new HashMap<>();
    form.put("projectId", projectId);
    form.put("deleteFlag", 0);
    form.put("checkingStatus", 1);// 对账审核通过
    form.put("invoiceStatus", 0);// 发票未登入
    return this.getCustomerPacks(form);
  }

}
