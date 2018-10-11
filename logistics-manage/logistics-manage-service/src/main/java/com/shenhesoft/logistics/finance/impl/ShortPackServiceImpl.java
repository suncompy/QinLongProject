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
import com.google.common.collect.Maps;
import com.shenhesoft.logistics.common.exception.ParameterException;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.FinanceAccountDetail;
import com.shenhesoft.logistics.finance.ShPackOrder;
import com.shenhesoft.logistics.finance.ShortOrderFinance;
import com.shenhesoft.logistics.finance.ShortPack;
import com.shenhesoft.logistics.finance.ShortPackService;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.finance.mapper.FinanceAccountDetailMapper;
import com.shenhesoft.logistics.finance.mapper.ShPackOrderMapper;
import com.shenhesoft.logistics.finance.mapper.ShortOrderFinanceMapper;
import com.shenhesoft.logistics.finance.mapper.ShortPackMapper;
import com.shenhesoft.logistics.manage.mapper.TbFinanceAccountMapper;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.CodeService;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 短驳打包信息表-业务实现.
 * <p>
 * <a href="ShortPackServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class ShortPackServiceImpl implements ShortPackService {

  @Autowired
  private ShortPackMapper shortPackMapper;
  @Autowired
  private TbFinanceAccountMapper financeAccountMapper;
  @Autowired
  private FinanceAccountDetailMapper financeAccountDetailMapper;
  @Autowired
  private BranchGroupLinkMapper branchGroupLinkMapper;
  /**
   * 短驳运单财务表-Dao.
   */
  @Autowired
  private ShortOrderFinanceMapper shortOrderFinanceMapper;
  /**
   * 短驳打包-运单中间表-Dao.
   */
  @Autowired
  private ShPackOrderMapper shPackOrderMapper;
  @Autowired
  private CodeService codeService;

  /**
   * 新增短驳打包信息表.
   * 
   * @param shortPack 短驳打包信息表实体
   * @return 页面表单
   */
  @OrgLinkData(idName="shPackId",tabComment="打包")
  public ShortPack addShortPack(ShortPack shortPack, String shOrderFinIds, TbSystemUser user) {
    if (StringUtil.isEmpty(shOrderFinIds))
      return null;
    String sysOrgCode=AppSession.getCurrentSysOrgCode();
    String id = codeService.createCheckFinCode(sysOrgCode, shortPack.getProjectId());
    // 生成id
    shortPack.setShPackId(id);
    // 对账状态(0-对账待审核 1-对账财务审核通过 2-对账财务审核不通过 3-已解包 ) checking_status
    shortPack.setCheckingStatus(0);
    // 现金结算状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过) cash_settle_status
    shortPack.setCashSettleStatus(-1);
    // 是否删除(0-未删除 1-删除) delete_flag
    shortPack.setDeleteFlag(0);
    // 创建时间 create_date
    shortPack.setCreateDate(DateUtils.date2Str(DateUtils.datetimeFormat));
    // 创建人 create_user_id
    shortPack.setCreateUserId(user.getId());

    BigDecimal freightChargeAmount = new BigDecimal(0);// 运费合计
    BigDecimal cashAmount = new BigDecimal(0);// 应付现金
    BigDecimal suppliesAmount = new BigDecimal(0);// 油气金额

    // 保存司机打包运单信息中间表
    String[] arrShOrderFinId = shOrderFinIds.trim().split(",");
    for (String shOrderFinId : arrShOrderFinId) {
      if (StringUtil.isEmpty(shOrderFinId))
        continue;
      // 查询短驳运单财务信息
      Map<String, Object> form = Maps.newHashMap();
      form.put("shOrderFinId", shOrderFinId.trim());
      form.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
      List<Map<String, Object>> shortOrderFinanceMaps =
          shortOrderFinanceMapper.getShortOrderFinances(form);
      // .getShortOrderFinanceById(shOrderFinId.trim());
      if (CollectionUtils.isEmpty(shortOrderFinanceMaps)) {
        continue;
      }
      Map<String, Object> shortOrderFinanceMap = shortOrderFinanceMaps.get(0);
      if (shortOrderFinanceMap == null)
        continue;
      // 当前运单应付总额
      BigDecimal nowOrderShouldPayFigure = null == shortOrderFinanceMap.get("payableTransitMoney")
          ? new BigDecimal(0) : (BigDecimal) shortOrderFinanceMap.get("payableTransitMoney");
      freightChargeAmount = freightChargeAmount.add(nowOrderShouldPayFigure);

      ShPackOrder shPackOrder = new ShPackOrder();
      shPackOrder.setShPackOrderId(AppUtils.randomUUID());// 主键
      shPackOrder.setShPackId(shortPack.getShPackId());// 包id
      shPackOrder.setShOrderFinId(shOrderFinId.trim());// 财务运单id
      shPackOrder.setDeleteFlag(0);
      shPackOrderMapper.addShPackOrder(shPackOrder);
      // 标注运单已打包
      ShortOrderFinance shortOrderFinance = new ShortOrderFinance();
      shortOrderFinance.setPackFlag(1);// 已打包
      shortOrderFinance.setShOrderFinId(shOrderFinId);
      shortOrderFinanceMapper.editShortOrderFinanceById(shortOrderFinance);
    }

    // 运费合计
    shortPack.setFreightChargeAmount(freightChargeAmount);
    // 油汽卡结算状态(0-待领取 1-已领取 2-无需领取（非油汽卡结算）) supplies_settle_status
    shortPack.setSuppliesSettleStatus(-1);
    if (0 == shortPack.getPaymentId()) {
      cashAmount = freightChargeAmount;
      // 油汽卡结算状态(0-待领取 1-已领取 2-无需领取（非油汽卡结算）) supplies_settle_status
      shortPack.setSuppliesSettleStatus(2);
    } else if (3 == shortPack.getPaymentId() && null != shortPack.getPayRatio()) {
      suppliesAmount = freightChargeAmount.multiply(shortPack.getPayRatio());
      suppliesAmount = new BigDecimal(Math.ceil(suppliesAmount.doubleValue() / 100000) * 1000);
      cashAmount = freightChargeAmount.subtract(suppliesAmount);
    } else {
      suppliesAmount = freightChargeAmount;
    }
    // 应付现金
    shortPack.setCashAmount(cashAmount);
    // 油气金额
    shortPack.setSuppliesAmount(suppliesAmount);
    // 保存短驳打包信息表
    shortPackMapper.addShortPack(shortPack);

    return shortPack;
  }

  /**
   * 查看短驳打包信息表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  @Override
  public Map<String, Object> getShortPackById(String id) {
    return shortPackMapper.getShortPackById(id);
  }

  /**
   * 修改短驳打包信息表.
   * 
   * @param shortPack 短驳打包信息表实体
   */
  @Override
  public void editShortPackById(ShortPack shortPack) {
    shortPackMapper.editShortPackById(shortPack);
  }

  /**
   * 删除指定短驳打包信息表.
   * 
   * @param id 主键
   */
  @Override
  public void delShortPackById(String id) {
    shortPackMapper.delShortPackById(id);
  }

  /**
   * 批量删除指定短驳打包信息表.
   * 
   * @param ids 主键集合
   */
  @Override
  public void delShortPackByIds(List<String> ids) {
    shortPackMapper.delShortPackByIds(ids);
  }

  /**
   * 清空计量单位表.
   */
  @Override
  public void delShortPacks() {
    shortPackMapper.delShortPacks();
  }

  /**
   * 获取所有短驳打包信息表.
   * 
   * @return 短驳打包信息表分页
   */
  @Override
  public List<Map<String, Object>> getShortPacks(int start, int pageSize,
      Map<String, Object> form) {
    PageHelper.offsetPage(start, pageSize);
    return this.getShortPacks(form);
  }

  /**
   * 获取所有短驳打包信息表.
   * 
   * @return 短驳打包信息表
   */
  @Override
  public List<Map<String, Object>> getShortPacks(Map<String, Object> form) {
    return shortPackMapper.getShortPacks(form);
  }

  /**
   * 获取网点交账表.
   * 
   * @return 短驳打包信息表分页
   */
  @Override
  public List<Map<String, Object>> getShortPackAbilitys(int start, int pageSize,
      Map<String, Object> form) {
    PageHelper.offsetPage(start, pageSize);
    return this.getShortPackAbilitys(form);
  }

  /**
   * 获取网点交账表.
   * 
   * @return 短驳打包信息表
   */
  @Override
  public List<Map<String, Object>> getShortPackAbilitys(Map<String, Object> form) {
    return shortPackMapper.getShortPackAbilitys(form);
  }

  @Override
  public List<Map<String, Object>> listPackReceiveDriverByIds(String shOrderFinIds) {
    if (StringUtil.isEmpty(shOrderFinIds))
      return null;
    List<String> shOrderFinIdList = Arrays.asList(shOrderFinIds.trim().split(","));
    List<Map<String, Object>> list =
        shortOrderFinanceMapper.listPackReceiveDriverByIds(shOrderFinIdList);
    return list;
  }

  /**
   * 根据领取人-返回司机开户行信息
   * 
   * @author dusd
   * @date 2018年1月18日
   * @return
   */
  public Map<String, Object> getDriverBankByDriverId(String id) {
    return shortOrderFinanceMapper.getDriverBankByDriverId(id);
  }

  @Override
  public void financeAuditShortPackByIds(String shPackIds, String flag,TbSystemUser user) {
    if (StringUtil.isEmpty(shPackIds))
      return;
    List<String> shPackIdList = Arrays.asList(shPackIds.trim().split(","));
    ShortPack shortPack = new ShortPack();
    if(flag.equals("0")) {
    	shortPack.setCheckingStatus(1);// 对账财务审核通过
    }else {
    	shortPack.setCheckingStatus(2);// 对账财务审核不通过
    }
    shortPack.setCheckingAuditor(null==AppSession.getCurrentUserId()?user.getId():AppSession.getCurrentUserId());
    // 财务审核时间
    shortPack.setCheckingAuditDate(DateUtils.date2Str(DateUtils.datetimeFormat));
    shortPackMapper.editShortPackByIds(shortPack, shPackIdList);
  }

  /* (non-Javadoc)
   * @see com.shenhesoft.logistics.finance.ShortPackService#settleStart(java.lang.String)
   */
  @Override
  public void settleStart(String shPackIds) {
    // TODO Auto-generated method stub
    if (StringUtil.isEmpty(shPackIds))
      return;
    List<String> shPackIdList = Arrays.asList(shPackIds.trim().split(","));
    ShortPack shortPack = new ShortPack();
    shortPack.setCashSettleStatus(0);
    shortPack.setSuppliesSettleStatus(0);
    shortPackMapper.editShortPackByIds(shortPack, shPackIdList);
  }

  @Override
  public void againstAuditShortPackByIds(String shPackIds) {
    if (StringUtil.isEmpty(shPackIds))
      return;
    List<String> shPackIdList = Arrays.asList(shPackIds.trim().split(","));
    ShortPack shortPack = new ShortPack();
    shortPack.setCheckingStatus(0);// 对账待审核
    shortPackMapper.editShortPackByIds(shortPack, shPackIdList);
  }

  @Override
  public void dissolveShortPackByIds(String shPackIds) {
    if (StringUtil.isEmpty(shPackIds))
      return;
    List<String> shPackIdList = Arrays.asList(shPackIds.trim().split(","));
    ShortPack shortPack = new ShortPack();
    shortPack.setDeleteFlag(1);// 已删除
    shortPack.setCheckingStatus(3);// 已解包
    shortPackMapper.editShortPackByIds(shortPack, shPackIdList);

    List<Map<String, Object>> shPackOrderMapList =
        shPackOrderMapper.listShPackOrderByShPackIds(shPackIdList);
    if (shPackOrderMapList == null || shPackOrderMapList.size() == 0)
      return;
    List<String> shOrderFinIdList = new ArrayList<String>();
    for (Map<String, Object> map : shPackOrderMapList) {
      if (map.get("shOrderFinId") == null)
        continue;
      shOrderFinIdList.add(map.get("shOrderFinId").toString());
    }
    // 修改所有的财务运单状态为未打包
    ShortOrderFinance shortOrderFinance = new ShortOrderFinance();
    shortOrderFinance.setPackFlag(0);// 未打包
    shortOrderFinanceMapper.editShortOrderFinanceByIds(shortOrderFinance, shOrderFinIdList);
  }

  // 司机结算
  @Override
  public void settlePassShortPackByIds(String shPackIds,ShortPack shortPack) {
    if (StringUtil.isEmpty(shPackIds))
      return;
    List<String> shPackIdList = Arrays.asList(shPackIds.trim().split(","));
    TbSystemUser user = AppSession.getCurrentUser();

    shortPack.setCashSettleStatus(1);// 1-结算财务待审核
    shortPack.setSuppliesExecuteId(user.getId());
    shortPack.setSuppliesExecuteDate(DateUtils.date2Str(DateUtils.datetimeFormat));
    shortPackMapper.editShortPackByIds(shortPack, shPackIdList);
  }

  @Override
  public void settleFinanceAuditShortPackByIds(String shPackIds, String passFlag) {
    if (StringUtil.isEmpty(shPackIds) || StringUtil.isEmpty(passFlag))
      return;
    List<String> shPackIdList = Arrays.asList(shPackIds.trim().split(","));
    ShortPack shortPack = new ShortPack();
    if (passFlag.trim().equals("0")) {// 审核通过
      shortPack.setCashSettleStatus(2);// 2-结算审核通过
      // 结算时收支序时账明细插入
      Map<String, Object> map = shortPackMapper.getShortPackById(shPackIds);
      FinanceAccountDetail financeAccountDetail = new FinanceAccountDetail();
      TbSystemUser user = AppSession.getCurrentUser();
      financeAccountDetail.setPayAccountId((Integer) map.get("provideCompanyId"));
      financeAccountDetail.setMoney((BigDecimal) map.get("cashAmount"));
      financeAccountDetail.setDepositAccountId((Integer) map.get("receiveUserId"));
      financeAccountDetail.setDepositAccountType(3);
      financeAccountDetail.setOperateId(user.getId());
      financeAccountDetail.setPayDate(DateUtils.date2Str(DateUtils.datetimeFormat));
      financeAccountDetail.setStatementNum(shPackIds);
      financeAccountDetailMapper.addFinanceAccountDetail(financeAccountDetail);
      
      TbFinanceAccount tbFinanceAccount= financeAccountMapper.selectByPrimaryKey(Integer.parseInt(map.get("provideCompanyId").toString()));
      double accountBalance = tbFinanceAccount.getAccountBalance().doubleValue();
      double money = Double.parseDouble(map.get("cashAmount").toString());
      if(accountBalance<money){
        throw new ParameterException("支出账户余额不足");
      }
      Map<String,Object> requestMap = ImmutableMap.of("money", map.get("cashAmount"),"id", map.get("provideCompanyId"));
      shortPackMapper.editFinanceMoneySubById(requestMap);
      
      BranchGroupLink branchGroupLink = new BranchGroupLink();
      branchGroupLink.setId(AppUtils.randomUUID());
      branchGroupLink.setRowId(financeAccountDetail.getId().toString());
      branchGroupLink.setTabName("tb_finance_account_detail");
      branchGroupLink.setTabComment("收支时序账");
      branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
      branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
    } else if (passFlag.trim().equals("1")) {
      shortPack.setCashSettleStatus(3);// 3-结算审核不通过
    }
    // 司机结算财务审核时间
    shortPack.setAuditId(AppSession.getCurrentUserId());
    shortPack.setSettleAuditDate(DateUtils.date2Str(DateUtils.datetimeFormat));
    shortPackMapper.editShortPackByIds(shortPack, shPackIdList);
  }

  @Override
  public void settleAgainstAuditShortPackByIds(String shPackIds) {
    if (StringUtil.isEmpty(shPackIds))
      return;
    List<String> shPackIdList = Arrays.asList(shPackIds.trim().split(","));
    ShortPack shortPack = new ShortPack();
    shortPack.setCashSettleStatus(1);// 1-结算财务待审核
    shortPackMapper.editShortPackByIds(shortPack, shPackIdList);
  }

  /**
   * 网点结算
   */
  @Override
  public void settlePassNodeShortPackByIds(String shPackIds) {
    if (StringUtil.isEmpty(shPackIds))
      return;
    // List<String> shPackIdList = Arrays.asList(shPackIds.trim().split(","));
    TbSystemUser user = AppSession.getCurrentUser();
    ShortPack shortPack = new ShortPack();
    //String id = idStr.substring(0, idStr.length() - 1);
    //shortPack.setOilgascardId(id);
    shortPack.setShPackId(shPackIds);
    shortPack.setSuppliesSettleStatus(1);// 1-已领取
    shortPack.setSuppliesExecuteDate(DateUtils.date2Str(DateUtils.datetimeFormat));
    shortPack.setSuppliesExecuteId(user.getId());
    shortPackMapper.editShortPackById(shortPack);
    /*
    String[] oilgascardIdStr = id.split(",");
    for (int a = 0; a < oilgascardIdStr.length; a++) {
      String oilgascardId = oilgascardIdStr[a];
      if (oilgascardId != null && oilgascardId != "") {
        OilGasCard oilGasCard = new OilGasCard();
        oilGasCard.setId(oilgascardId);
        oilGasCard.setCardStatus(1);
        oilGasCardMapper.updateOilGasCardStatus(oilGasCard);
      }
    }*/
  }

  /*
   * 获取下拉款的油气卡号
   */
  @Override
  public String queryOilGasCard(String type, String text) {
    String selections = null;
    switch (type) {
      case "suppliesNums":
        selections = shortPackMapper.queryOilGasCard(text);
        break;
      default:
        break;
    }
    return selections;
  }

  /**
   * 获取网点结算油气卡表.
   * 
   * @param form 页面表单
   * @return 结果集合
   */
  public List<Map<String, Object>> getOilGasCardUsed(Map<String, Object> form) {
    return shortPackMapper.getOilGasCardUsed(form);
  }

  /**
   * 获取网点结算油气卡表.
   * 
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getOilGasCardUsed(int start, int pageSize,
      Map<String, Object> form) {
    PageHelper.offsetPage(start, pageSize);
    return this.getOilGasCardUsed(form);
  }

	@Override
	public Map<String, Object> getOilGasCardMoney(String branchGroupName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("branchGroupName", branchGroupName);
		map.put("cardType", Constants.CARD_TYPE_OIL);
		//油卡
		BigDecimal oilCardMoney = shortPackMapper.getOilGasCardMoney(map) == null ? 
				new BigDecimal("0") : shortPackMapper.getOilGasCardMoney(map) ;
		
		//气卡
		map.put("cardType", Constants.CARD_TYPE_GAS);
		BigDecimal gasCardMoney = shortPackMapper.getOilGasCardMoney(map) == null ? 
				new BigDecimal("0") : shortPackMapper.getOilGasCardMoney(map) ;
				
		map.clear();
		map.put("oilCardMoney", oilCardMoney);
		map.put("gasCardMoney", gasCardMoney);
		return map;
	}
}
