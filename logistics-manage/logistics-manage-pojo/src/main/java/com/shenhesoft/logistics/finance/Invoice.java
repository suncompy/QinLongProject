/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */
 
package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 发票-Form.
 * <p>
 * <a href="Invoice.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
public class Invoice {
  
  //主键
  private String invoiceId;
  private String provideCompanyName;
  private String provideType;
  private String receiveCompanyName;
  private String receiveType;
  //打包主键或客户对账总主键
  private String packId;
  //流水类型：0客户对账总1客户对账分2费用对账
  private String packType;
  //受票方id
  private Integer receiveCompanyId;
  //出具方id
  private Integer provideCompanyId;
  //开票金额
  private BigDecimal invoiceMoney;
  //税率
  private BigDecimal taxRate;
  //税额
  private BigDecimal taxMoney;
  //合计金额
  private BigDecimal totalMoney;
  //创建时间
  private String createDate;
  //创建人
  private Integer createUserId;
  //审核人
  private Integer auditUserId;
  //审核时间
  private String auditDate;
  //回退人
  private Integer backUserId;
  //回退日期
  private String backDate;
  //回退部门
  private String backOrgName;
  //修改时间
  private String modifiyDate;
  //是否删除(0-未删除 1-删除)
  private Integer deleteFlag;
  //项目标识
  private Integer projectId;
  //运单标识
  private Integer orderId;
  //干线标志0非干线1干线
  private Integer trainFlag;
  //状态0待登入1已登入
  private Integer invoiceStatus;
  private String beginAddress;
  private String endAddress;

  //无参构造
  public Invoice() {
    super();
  }

  public String getInvoiceId() {
    return invoiceId;
  }
  
  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }
  
  public String getPackId() {
    return packId;
  }
  
  public void setPackId(String packId) {
    this.packId = packId;
  }
  
  public String getPackType() {
    return packType;
  }
  
  public void setPackType(String packType) {
    this.packType = packType;
  }
  
  public Integer getReceiveCompanyId() {
    return receiveCompanyId;
  }
  
  public void setReceiveCompanyId(Integer receiveCompanyId) {
    this.receiveCompanyId = receiveCompanyId;
  }
  
  public Integer getProvideCompanyId() {
    return provideCompanyId;
  }
  
  public void setProvideCompanyId(Integer provideCompanyId) {
    this.provideCompanyId = provideCompanyId;
  }
  
  public BigDecimal getInvoiceMoney() {
    return invoiceMoney;
  }
  
  public void setInvoiceMoney(BigDecimal invoiceMoney) {
    this.invoiceMoney = invoiceMoney;
  }
  
  public BigDecimal getTaxRate() {
    return taxRate;
  }
  
  public void setTaxRate(BigDecimal taxRate) {
    this.taxRate = taxRate;
  }
  
  public BigDecimal getTaxMoney() {
    return taxMoney;
  }
  
  public void setTaxMoney(BigDecimal taxMoney) {
    this.taxMoney = taxMoney;
  }
  
  public BigDecimal getTotalMoney() {
    return totalMoney;
  }
  
  public void setTotalMoney(BigDecimal totalMoney) {
    this.totalMoney = totalMoney;
  }
  
  public String getCreateDate() {
    if("".equals(createDate)){
      return null;
    }
    return createDate;
  }
  
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  
  public Integer getCreateUserId() {
    return createUserId;
  }
  
  public void setCreateUserId(Integer createUserId) {
    this.createUserId = createUserId;
  }
  
  public Integer getAuditUserId() {
    return auditUserId;
  }
  
  public void setAuditUserId(Integer auditUserId) {
    this.auditUserId = auditUserId;
  }
  
  public String getAuditDate() {
    if("".equals(auditDate)){
      return null;
    }
    return auditDate;
  }
  
  public void setAuditDate(String auditDate) {
    this.auditDate = auditDate;
  }
  
  public Integer getBackUserId() {
    return backUserId;
  }
  
  public void setBackUserId(Integer backUserId) {
    this.backUserId = backUserId;
  }
  
  public String getBackDate() {
    if("".equals(backDate)){
      return null;
    }
    return backDate;
  }
  
  public void setBackDate(String backDate) {
    this.backDate = backDate;
  }
  
  public String getBackOrgName() {
    return backOrgName;
  }
  
  public void setBackOrgName(String backOrgName) {
    this.backOrgName = backOrgName;
  }
  
  public String getModifiyDate() {
    if("".equals(modifiyDate)){
      return null;
    }
    return modifiyDate;
  }
  
  public void setModifiyDate(String modifiyDate) {
    this.modifiyDate = modifiyDate;
  }
  
  public Integer getDeleteFlag() {
    return deleteFlag;
  }
  
  public void setDeleteFlag(Integer deleteFlag) {
    this.deleteFlag = deleteFlag;
  }
  
  public Integer getProjectId() {
    return projectId;
  }
  
  public void setProjectId(Integer projectId) {
    this.projectId = projectId;
  }
  
  public Integer getOrderId() {
    return orderId;
  }
  
  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }
  
  public Integer getTrainFlag() {
    return trainFlag;
  }
  
  public void setTrainFlag(Integer trainFlag) {
    this.trainFlag = trainFlag;
  }
  
  public Integer getInvoiceStatus() {
    return invoiceStatus;
  }
  
  public void setInvoiceStatus(Integer invoiceStatus) {
    this.invoiceStatus = invoiceStatus;
  }

  public String getProvideCompanyName() {
    return provideCompanyName;
  }

  public void setProvideCompanyName(String provideCompanyName) {
    this.provideCompanyName = provideCompanyName;
  }

  public String getProvideType() {
    return provideType;
  }

  public void setProvideType(String provideType) {
    this.provideType = provideType;
  }

  public String getReceiveCompanyName() {
    return receiveCompanyName;
  }

  public void setReceiveCompanyName(String receiveCompanyName) {
    this.receiveCompanyName = receiveCompanyName;
  }

  public String getReceiveType() {
    return receiveType;
  }

  public void setReceiveType(String receiveType) {
    this.receiveType = receiveType;
  }

  public String getBeginAddress() {
    return beginAddress;
  }

  public void setBeginAddress(String beginAddress) {
    this.beginAddress = beginAddress;
  }

  public String getEndAddress() {
    return endAddress;
  }

  public void setEndAddress(String endAddress) {
    this.endAddress = endAddress;
  }
  
}