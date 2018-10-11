/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */
 
package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 客户结算明细-Form.
 * <p>
 * <a href="CustSettleDetail.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
public class CustSettleDetail {
  private String id;
  //结算标识
  private String settleId;
  //本次结算状态
  private Integer applyStatus;
  //结算单位
  private String settleOrg;
  //人员
  private Integer settleUserId;
  //存入账号
  private Integer settleAccountId;
  //申请金额
  private BigDecimal applyMoney;
  //结算方式
  private Integer settleModel;
  //操作人
  private Integer optUserId;
  //审核人
  private Integer auditUserId;
  //审核时间
  private String auditTime;

  //无参构造
  public CustSettleDetail() {
    super();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSettleId() {
    return settleId;
  }
  
  public void setSettleId(String settleId) {
    this.settleId = settleId;
  }
  
  public Integer getApplyStatus() {
    return applyStatus;
  }
  
  public void setApplyStatus(Integer applyStatus) {
    this.applyStatus = applyStatus;
  }
  
  public String getSettleOrg() {
    return settleOrg;
  }
  
  public void setSettleOrg(String settleOrg) {
    this.settleOrg = settleOrg;
  }
  
  public Integer getSettleUserId() {
    return settleUserId;
  }
  
  public void setSettleUserId(Integer settleUserId) {
    this.settleUserId = settleUserId;
  }
  
  public Integer getSettleAccountId() {
    return settleAccountId;
  }
  
  public void setSettleAccountId(Integer settleAccountId) {
    this.settleAccountId = settleAccountId;
  }
  
  public BigDecimal getApplyMoney() {
    return applyMoney;
  }
  
  public void setApplyMoney(BigDecimal applyMoney) {
    this.applyMoney = applyMoney;
  }
  
  public Integer getSettleModel() {
    return settleModel;
  }
  
  public void setSettleModel(Integer settleModel) {
    this.settleModel = settleModel;
  }
  
  public Integer getOptUserId() {
    return optUserId;
  }
  
  public void setOptUserId(Integer optUserId) {
    this.optUserId = optUserId;
  }
  
  public Integer getAuditUserId() {
    return auditUserId;
  }
  
  public void setAuditUserId(Integer auditUserId) {
    this.auditUserId = auditUserId;
  }
  
  public String getAuditTime() {
    if("".equals(auditTime)){
      return null;
    }
    return auditTime;
  }
  
  public void setAuditTime(String auditTime) {
    this.auditTime = auditTime;
  }
  
}