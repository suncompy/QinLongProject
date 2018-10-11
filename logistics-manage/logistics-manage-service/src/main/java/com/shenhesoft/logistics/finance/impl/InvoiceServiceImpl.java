/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */
 
package com.shenhesoft.logistics.finance.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.CustSettle;
import com.shenhesoft.logistics.finance.CustSettleService;
import com.shenhesoft.logistics.finance.Invoice;
import com.shenhesoft.logistics.finance.InvoiceService;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.finance.mapper.InvoiceMapper;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 发票-业务实现.
 * <p>
 * <a href="InvoiceServiceImpl.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
  
  @Autowired
  private InvoiceMapper invoiceMapper;
  @Autowired
  private CustSettleService custSettleService;
  @Autowired
  private BranchGroupLinkMapper branchGroupLinkMapper;
  /**
   * 新增发票.
   * 
   * @param invoice 发票实体
   * @return 页面表单
   */
  @OrgLinkData(idName="invoiceId",tabComment="发票管理")
  public Invoice addInvoice(Invoice invoice) {
    // 生成id
    invoice.setPackId(AppUtils.randomUUID());
    // 保存发票
    invoiceMapper.addInvoice(invoice);

    return invoice;
  }
  
  /**
   * 
   * @description 
   * @date 2018年3月29日
   * @param packType 流水类型：0客户对账总1客户对账分2费用对账
   * @return
   */
  public void addInvoices(List<Map<String,Object>> list,String packType){
    List<Invoice> invoices = Lists.newArrayList();
    for(Map<String,Object> map:list){
      String packIdName = "";
      String taxMoneyName = "taxMoney";
      String beginAddress = "";
      String endAddress = "";
      if("0".equals(packType)){
        packIdName = "custCheckConId";
        beginAddress = "beginAddress";
        endAddress = "endAddress";
      }else if("1".equals(packType)){
        packIdName = "custPackId";
        beginAddress = "beginAddress";
        endAddress = "endAddress";
      }else{
        packIdName = "costPackId";
        beginAddress = "beginAddress";
        endAddress = "endAddress";
      }
      Invoice invoice = new Invoice();
      invoice.setInvoiceId(AppUtils.randomUUID());
      invoice.setPackId(map.get(packIdName).toString());
      invoice.setPackType(packType);
      invoice.setInvoiceMoney(new BigDecimal(null==map.get("produceMoney")?"0":map.get("produceMoney").toString()));
      invoice.setTaxRate(new BigDecimal(null==map.get("taxRate")?"0":map.get("taxRate").toString()));
      invoice.setTaxMoney(new BigDecimal(null==map.get(taxMoneyName)?"0":map.get(taxMoneyName).toString()));
      invoice.setTotalMoney(invoice.getInvoiceMoney().add(invoice.getTaxMoney()));
      invoice.setCreateUserId(AppSession.getCurrentUserId());
      invoice.setDeleteFlag(0);
      invoice.setProjectId(Integer.parseInt(map.get("projectId").toString()));
      invoice.setBeginAddress(null==map.get(beginAddress)?null:map.get(beginAddress).toString());
      invoice.setEndAddress(null==map.get(endAddress)?null:map.get(endAddress).toString());
      if("0".equals(packType)){
        invoice.setTrainFlag(null);
      }
      //费用对账
      else if("2".equals(packType)){
        //接取
        invoice.setTrainFlag(1);
      }else{
        invoice.setTrainFlag(Integer.parseInt(map.get("packType").toString()));
      }
      invoice.setInvoiceStatus(0);
      invoices.add(invoice);
      BranchGroupLink branchGroupLink = new BranchGroupLink();
      branchGroupLink.setId(AppUtils.randomUUID());
      branchGroupLink.setRowId(invoice.getInvoiceId());
      branchGroupLink.setTabName("tb_invoice");
      branchGroupLink.setTabComment("发票管理");
      branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
      branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
    }
    invoiceMapper.addInvoices(invoices);
  }

  /**
   * 查看发票详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  public Map<String, Object> getInvoiceById(String id) {
    return invoiceMapper.getInvoiceById(id);
  }

  /**
   * 修改发票.
   * 
   * @param invoice 发票实体
   */
  public void editInvoiceById(Invoice invoice) {
    Map<String, Object> map = invoiceMapper.getInvoiceById(invoice.getInvoiceId());
    if(CollectionUtils.isEmpty(map)){
      return;
    }
    String packType = map.get("packType").toString();
    if(invoice.getInvoiceStatus().intValue()==1){
      if("1".equals(packType)){
        CustSettle custSettle = new CustSettle();
        custSettle.setInvoiceId(invoice.getInvoiceId());
        custSettle.setSettledMoney(new BigDecimal("0"));
        custSettle.setSettingMoney(new BigDecimal(map.get("totalMoney").toString()));
        //custSettle.setPayDepositOrg(payDepositOrg);
        custSettle.setPayDepositType("0");
        custSettle.setSettleStatus(0);
        custSettleService.addCustSettle(custSettle);
      }
    }else if(invoice.getInvoiceStatus().intValue()==0){
      invoice.setBackUserId(AppSession.getCurrentUserId());
      //invoice.setBackOrgName(backOrgName);
      invoice.setBackDate(DateUtils.date2Str(DateUtils.datetimeFormat));
    }else if(invoice.getInvoiceStatus().intValue()==3 || invoice.getInvoiceStatus().intValue()==4){
      invoice.setAuditUserId(AppSession.getCurrentUserId());
      invoice.setAuditDate(DateUtils.date2Str(DateUtils.datetimeFormat));
    }
    
    invoiceMapper.editInvoiceById(invoice);
  }

  /**
   * 删除指定发票.
   * 
   * @param id 主键
   */
  public void delInvoiceById(String id) {
    invoiceMapper.delInvoiceById(id);
  }

  /**
   * 批量删除指定发票.
   * 
   * @param ids 主键集合
   */
  public void delInvoiceByIds(List<String> ids) {
    invoiceMapper.delInvoiceByIds(ids);
  }

  /**
   * 清空计量单位表.
   */
  public void delInvoices() {
    invoiceMapper.delInvoices();
  }

  /**
  * 获取所有发票.
  * 
  * @return 发票分页
  */
  public List<Map<String, Object>> getInvoices(int start, int pageSize, Map<String, Object> form) {
    PageHelper.offsetPage(start, pageSize);
    return this.getInvoices(form);
  }

  /**
  * 获取所有发票.
  * 
  * @return 发票
  */
  public List<Map<String, Object>> getInvoices(Map<String, Object> form) {
    return invoiceMapper.getInvoices(form);
  }

}