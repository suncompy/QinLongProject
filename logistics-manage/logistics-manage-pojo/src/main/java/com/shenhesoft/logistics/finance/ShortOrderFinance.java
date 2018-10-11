package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 短驳运单财务表-Form.
 * <p>
 * <a href="ShortOrderFinance.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShortOrderFinance {
  
  //主键
  private String shOrderFinId;
  //项目id
  private Integer projectId;
  //运单id
  private Integer orderId;
  //财务状态(0-待确认、1-待计算、2-待审核、3-已审核)
  private Integer financeStatus;
  //补交项目
  private String arrearsItem;
  //追加人id
  private Integer addUserId;
  //追加人时间
  private String addDate;
  //是否被打包(0-未打包 1-已打包)
  private Integer packFlag;
  //是否删除(0-未删除 1-删除)
  private Integer deleteFlag;
  //运输费用
  private BigDecimal shortBargeCost;
  //扣损费用
  private BigDecimal buckleFigure;
  //补加金额
  private BigDecimal subsidy;
  //应付费用
  private BigDecimal shouldPayFigure;
  //确认人
  private Integer billingUserId;
  //确认计费时间
  private String billingDate;
  //审核人
  private Integer auditUserId;
  //财务审核时间
  private String financeAuditDate;
  //完成时间
  private String completeDate;
  //异常时间
  private String unusualDate;
  //异常提报人id
  private Integer unusualUserId;
  //异常原因
  private String unusualReason;
  //创建时间
  private String createDate;
  //创建人
  private Integer createUserId;
  //修改时间
  private String modifiyDate;

  //无参构造
  public ShortOrderFinance() {
    super();
  }

  public String getShOrderFinId() {
    return shOrderFinId;
  }
  
  public void setShOrderFinId(String shOrderFinId) {
    this.shOrderFinId = shOrderFinId;
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
  
  public Integer getFinanceStatus() {
    return financeStatus;
  }
  
  public void setFinanceStatus(Integer financeStatus) {
    this.financeStatus = financeStatus;
  }
  
  public String getArrearsItem() {
    return arrearsItem;
  }
  
  public void setArrearsItem(String arrearsItem) {
    this.arrearsItem = arrearsItem;
  }
  
  public Integer getAddUserId() {
    return addUserId;
  }
  
  public void setAddUserId(Integer addUserId) {
    this.addUserId = addUserId;
  }
  
  public String getAddDate() {
    if("".equals(addDate)){
      return null;
    }
    return addDate;
  }
  
  public void setAddDate(String addDate) {
    this.addDate = addDate;
  }
  
  public Integer getPackFlag() {
    return packFlag;
  }
  
  public void setPackFlag(Integer packFlag) {
    this.packFlag = packFlag;
  }
  
  public Integer getDeleteFlag() {
    return deleteFlag;
  }
  
  public void setDeleteFlag(Integer deleteFlag) {
    this.deleteFlag = deleteFlag;
  }
  
  public BigDecimal getShortBargeCost() {
    return shortBargeCost;
  }
  
  public void setShortBargeCost(BigDecimal shortBargeCost) {
    this.shortBargeCost = shortBargeCost;
  }
  
  public BigDecimal getBuckleFigure() {
    return buckleFigure;
  }
  
  public void setBuckleFigure(BigDecimal buckleFigure) {
    this.buckleFigure = buckleFigure;
  }
  
  public BigDecimal getSubsidy() {
    return subsidy;
  }
  
  public void setSubsidy(BigDecimal subsidy) {
    this.subsidy = subsidy;
  }
  
  public BigDecimal getShouldPayFigure() {
    return shouldPayFigure;
  }
  
  public void setShouldPayFigure(BigDecimal shouldPayFigure) {
    this.shouldPayFigure = shouldPayFigure;
  }
  
  public String getBillingDate() {
    if("".equals(billingDate)){
      return null;
    }
    return billingDate;
  }
  
  public void setBillingDate(String billingDate) {
    this.billingDate = billingDate;
  }
  
  public String getFinanceAuditDate() {
    if("".equals(financeAuditDate)){
      return null;
    }
    return financeAuditDate;
  }
  
  public void setFinanceAuditDate(String financeAuditDate) {
    this.financeAuditDate = financeAuditDate;
  }
  
  public String getCompleteDate() {
    if("".equals(completeDate)){
      return null;
    }
    return completeDate;
  }
  
  public void setCompleteDate(String completeDate) {
    this.completeDate = completeDate;
  }
  
  public String getUnusualDate() {
    if("".equals(unusualDate)){
      return null;
    }
    return unusualDate;
  }
  
  public void setUnusualDate(String unusualDate) {
    this.unusualDate = unusualDate;
  }
  
  public Integer getUnusualUserId() {
    return unusualUserId;
  }
  
  public void setUnusualUserId(Integer unusualUserId) {
    this.unusualUserId = unusualUserId;
  }
  
  public String getUnusualReason() {
    return unusualReason;
  }
  
  public void setUnusualReason(String unusualReason) {
    this.unusualReason = unusualReason;
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
  
  public String getModifiyDate() {
    if("".equals(modifiyDate)){
      return null;
    }
    return modifiyDate;
  }
  
  public void setModifiyDate(String modifiyDate) {
    this.modifiyDate = modifiyDate;
  }

  public Integer getBillingUserId() {
    return billingUserId;
  }

  public void setBillingUserId(Integer billingUserId) {
    this.billingUserId = billingUserId;
  }

  public Integer getAuditUserId() {
    return auditUserId;
  }

  public void setAuditUserId(Integer auditUserId) {
    this.auditUserId = auditUserId;
  }
  
}