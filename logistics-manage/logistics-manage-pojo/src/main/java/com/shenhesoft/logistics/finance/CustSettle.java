/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */
 
package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 客户结算表-Form.
 * <p>
 * <a href="CustSettle.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
public class CustSettle {
  private String id;
  //往来公司
  private String payDepositOrg;
  //往来类别
  private String payDepositType;
  //结算状态
  private Integer settleStatus;
  //发票标识
  private String invoiceId;
  //已结算金额
  private BigDecimal settledMoney;
  //待结算金额
  private BigDecimal settingMoney;

  private String updateTime;
  private String createTime;
  //无参构造
  public CustSettle() {
    super();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPayDepositOrg() {
    return payDepositOrg;
  }
  
  public void setPayDepositOrg(String payDepositOrg) {
    this.payDepositOrg = payDepositOrg;
  }
  
  public String getPayDepositType() {
    return payDepositType;
  }
  
  public void setPayDepositType(String payDepositType) {
    this.payDepositType = payDepositType;
  }
  
  public Integer getSettleStatus() {
    return settleStatus;
  }
  
  public void setSettleStatus(Integer settleStatus) {
    this.settleStatus = settleStatus;
  }
  
  public String getInvoiceId() {
    return invoiceId;
  }
  
  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }
  
  public BigDecimal getSettledMoney() {
    return settledMoney;
  }
  
  public void setSettledMoney(BigDecimal settledMoney) {
    this.settledMoney = settledMoney;
  }
  
  public BigDecimal getSettingMoney() {
    return settingMoney;
  }
  
  public void setSettingMoney(BigDecimal settingMoney) {
    this.settingMoney = settingMoney;
  }

  public String getUpdateTime() {
    if("".equals(updateTime)){
      return null;
    }
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public String getCreateTime() {
    if("".equals(createTime)){
      return null;
    }
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }
  
}