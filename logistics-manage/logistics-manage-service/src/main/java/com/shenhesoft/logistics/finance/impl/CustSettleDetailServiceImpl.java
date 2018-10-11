/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */
 
package com.shenhesoft.logistics.finance.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import com.shenhesoft.logistics.common.exception.ParameterException;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.CustSettle;
import com.shenhesoft.logistics.finance.CustSettleDetail;
import com.shenhesoft.logistics.finance.CustSettleDetailService;
import com.shenhesoft.logistics.finance.FinanceAccountDetail;
import com.shenhesoft.logistics.finance.Invoice;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.finance.mapper.CustSettleDetailMapper;
import com.shenhesoft.logistics.finance.mapper.CustSettleMapper;
import com.shenhesoft.logistics.finance.mapper.FinanceAccountDetailMapper;
import com.shenhesoft.logistics.finance.mapper.InvoiceMapper;
import com.shenhesoft.logistics.finance.mapper.ShortPackMapper;
import com.shenhesoft.logistics.manage.mapper.TbFinanceAccountMapper;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 客户结算明细-业务实现.
 * <p>
 * <a href="CustSettleDetailServiceImpl.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CustSettleDetailServiceImpl implements CustSettleDetailService {
  
  @Autowired
  private CustSettleDetailMapper custSettleDetailMapper;
  @Autowired
  private CustSettleMapper custSettleMapper;
  @Autowired
  private InvoiceMapper invoiceMapper;
  @Autowired
  private TbFinanceAccountMapper financeAccountMapper;
  @Autowired
  private FinanceAccountDetailMapper financeAccountDetailMapper;
  @Autowired
  private ShortPackMapper shortPackMapper;
  @Autowired
  private BranchGroupLinkMapper branchGroupLinkMapper;
  /**
   * 新增客户结算明细.
   * 
   * @param custSettleDetail 客户结算明细实体
   * @return 页面表单
   */
  @OrgLinkData(tabComment="客户结算明细")
  public CustSettleDetail addCustSettleDetail(CustSettleDetail custSettleDetail) {
    // 生成id
    custSettleDetail.setId(AppUtils.randomUUID());
    custSettleDetail.setApplyStatus(1);
    custSettleDetail.setOptUserId(AppSession.getCurrentUserId());
    // 保存客户结算明细
    custSettleDetailMapper.addCustSettleDetail(custSettleDetail);
    return custSettleDetail;
  }

  /**
   * 查看客户结算明细详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  public Map<String, Object> getCustSettleDetailById(String id) {
    return custSettleDetailMapper.getCustSettleDetailById(id);
  }

  /**
   * 修改客户结算明细.
   * 
   * @param custSettleDetail 客户结算明细实体
   */
  public void editCustSettleDetailById(CustSettleDetail custSettleDetail) {
    custSettleDetail.setAuditUserId(AppSession.getCurrentUserId());
    custSettleDetail.setAuditTime(DateUtils.date2Str(DateUtils.datetimeFormat));
    custSettleDetailMapper.editCustSettleDetailById(custSettleDetail);
    if(custSettleDetail.getApplyStatus().intValue()==2){
      Map<String, Object> map = custSettleMapper.getCustSettleById(custSettleDetail.getSettleId());
      CustSettle custSettle = FormUtil.populate(CustSettle.class, map, false);
      custSettle.setId(custSettleDetail.getSettleId());
      
      if(custSettleDetail.getApplyMoney().compareTo(custSettle.getSettingMoney())==1){
        throw new ParameterException("申请结算金额不能大于待结算金额");
      }else if(custSettleDetail.getApplyMoney().compareTo(custSettle.getSettingMoney())==0){
        custSettle.setSettleStatus(2);
        addFinaceDetail(custSettle,custSettleDetail);
      }else{
        custSettle.setSettleStatus(1);
        addFinaceDetail(custSettle,custSettleDetail);
      }
      custSettle.setSettledMoney(custSettleDetail.getApplyMoney().add(custSettle.getSettledMoney()));
      custSettle.setSettingMoney(custSettle.getSettingMoney().subtract(custSettleDetail.getApplyMoney()));
      custSettle.setUpdateTime(DateUtils.date2Str(DateUtils.datetimeFormat));
      custSettleMapper.editCustSettleById(custSettle);
    }
  }
  private void addFinaceDetail(CustSettle custSettle,CustSettleDetail custSettleDetail){
    String sysOrgCode = AppSession.getCurrentSysOrgCode();
    Map<String, Object> requestMap = ImmutableMap.of("sysOrgCode", sysOrgCode, "invoiceId", custSettle.getInvoiceId());
    List<Map<String, Object>> list = invoiceMapper.getInvoices(requestMap);
    if(!CollectionUtils.isEmpty(list) && !CollectionUtils.isEmpty(list.get(0))){
      Invoice invoice = FormUtil.populate(Invoice.class, list.get(0), false);
      FinanceAccountDetail financeAccountDetail = new FinanceAccountDetail();
      financeAccountDetail.setDepositAccountId(invoice.getProvideCompanyId());
      financeAccountDetail.setPayAccountId(invoice.getReceiveCompanyId());
      financeAccountDetail.setMoney(custSettleDetail.getApplyMoney());
      financeAccountDetail.setOperateId(AppSession.getCurrentUserId());
      financeAccountDetail.setPayDate(DateUtils.date2Str(DateUtils.datetimeFormat));
      financeAccountDetail.setStatementNum(invoice.getPackId());
      financeAccountDetailMapper.addFinanceAccountDetail(financeAccountDetail);
      
      TbFinanceAccount tbFinanceAccount= financeAccountMapper.selectByPrimaryKey(invoice.getReceiveCompanyId());
      double accountBalance = tbFinanceAccount.getAccountBalance().doubleValue();
      double money = custSettleDetail.getApplyMoney().doubleValue();
      if(accountBalance<money){
        throw new ParameterException("支出账户余额不足");
      }
      Map<String,Object> requestMapSub = ImmutableMap.of("money",custSettleDetail.getApplyMoney(),"id", invoice.getReceiveCompanyId());
      shortPackMapper.editFinanceMoneySubById(requestMapSub);
      Map<String,Object> requestMapAdd = ImmutableMap.of("money",custSettleDetail.getApplyMoney(),"id", invoice.getProvideCompanyId());
      shortPackMapper.editFinanceMoneyAddById(requestMapAdd);
      
      BranchGroupLink branchGroupLink = new BranchGroupLink();
      branchGroupLink.setId(AppUtils.randomUUID());
      branchGroupLink.setRowId(financeAccountDetail.getId().toString());
      branchGroupLink.setTabName("tb_finance_account_detail");
      branchGroupLink.setTabComment("收支时序账");
      branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
      branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
    }
  }
  public void editCustSettleDetailByIds(CustSettleDetail custSettleDetail, String ids){
    List<String> idList = Arrays.asList(ids.trim().split(","));
    if(CollectionUtils.isEmpty(idList)){
      return;
    }
    custSettleDetail.setAuditUserId(AppSession.getCurrentUserId());
    custSettleDetail.setAuditTime(DateUtils.date2Str(DateUtils.datetimeFormat));
    custSettleDetailMapper.editCustSettleDetailByIds(custSettleDetail,idList);
    Map<String, Object> mapDetial = custSettleDetailMapper.getCustSettleDetailById(idList.get(0));
    custSettleDetail.setSettleId(mapDetial.get("settleId").toString());
    if(custSettleDetail.getApplyStatus().intValue()==2){
      Map<String, Object> map = custSettleMapper.getCustSettleById(custSettleDetail.getSettleId());
      CustSettle custSettle = FormUtil.populate(CustSettle.class, map, false);
      custSettle.setId(custSettleDetail.getSettleId());
      
      if(custSettleDetail.getApplyMoney().compareTo(custSettle.getSettingMoney())==1){
        throw new ParameterException("申请结算金额不能大于待结算金额");
      }else if(custSettleDetail.getApplyMoney().compareTo(custSettle.getSettingMoney())==0){
        custSettle.setSettleStatus(2);
        addFinaceDetail(custSettle,custSettleDetail);
      }else{
        custSettle.setSettleStatus(1);
        addFinaceDetail(custSettle,custSettleDetail);
      }
      custSettle.setSettledMoney(custSettleDetail.getApplyMoney().add(custSettle.getSettledMoney()));
      custSettle.setSettingMoney(custSettle.getSettingMoney().subtract(custSettleDetail.getApplyMoney()));
      custSettleMapper.editCustSettleById(custSettle);
    }
  }
  /**
   * 删除指定客户结算明细.
   * 
   * @param id 主键
   */
  public void delCustSettleDetailById(String id) {
    custSettleDetailMapper.delCustSettleDetailById(id);
  }

  /**
   * 批量删除指定客户结算明细.
   * 
   * @param ids 主键集合
   */
  public void delCustSettleDetailByIds(List<String> ids) {
    custSettleDetailMapper.delCustSettleDetailByIds(ids);
  }

  /**
   * 清空计量单位表.
   */
  public void delCustSettleDetails() {
    custSettleDetailMapper.delCustSettleDetails();
  }

  /**
  * 获取所有客户结算明细.
  * 
  * @return 客户结算明细分页
  */
  public List<Map<String, Object>> getCustSettleDetails(int start, int pageSize, Map<String, Object> form) {
    PageHelper.offsetPage(start, pageSize);
    return this.getCustSettleDetails(form);
  }

  /**
  * 获取所有客户结算明细.
  * 
  * @return 客户结算明细
  */
  public List<Map<String, Object>> getCustSettleDetails(Map<String, Object> form) {
    return custSettleDetailMapper.getCustSettleDetails(form);
  }

}