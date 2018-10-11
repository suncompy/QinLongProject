/*
 * @copyright 2014-2015 Future-data Inc. All rights reserved.
 */

package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 客户对账打包信息表-Form.
 * <p>
 * <a href="CustomerPack.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-19
 * @version 1.0.0
 * @since 1.0.0
 */
public class CustomerPack {

  // 主键
  private String custPackId;
  // 客户对账设置id
  private String custCheckConId;
  // 项目id
  private Integer projectId;
  // 打包类型(0 汽运 1 接取 2 送达 3 火运)
  private Integer packType;
  // 对账状态(0-对账待审核 1-对账财务审核通过 2-对账财务审核不通过 3-已解包 )
  private Integer checkingStatus;
  // 结算审核状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)
  private Integer settleAuditType;
  // 打包车辆
  private Integer packTruckNum;
  // 打包车次
  private Integer packTruckDegree;
  // 发票状态(0-新建 1-已登入 2-发票作废待审核 3-已作废 4-作废审核不通过)
  private Integer invoiceStatus;
  // 备注
  private String remark;
  // 是否删除(0-未删除 1-删除)
  private Integer deleteFlag;
  // 创建时间
  private String createDate;
  // 创建人
  private Integer createUserId;
  // 修改时间
  private String modifiyDate;
  // 开始日期
  private String startDate;
  // 终止日期
  private String endDate;
  // 运单总数
  private Integer orderCount;
  // 总净重
  private BigDecimal totalWeight;
  // 总件数
  private Integer totalPiece;
  // 产生金额
  private BigDecimal produceMoney;
  // 应开总税额
  private BigDecimal taxMoney;
  // 待结算金额
  private BigDecimal besettledMoney;
  // 已结算金额
  private BigDecimal settledMoney;
  // 本次结算金额
  private BigDecimal settleMoney;
  // 结算方式(0:现金,1:银行卡,2:信用卡)
  private Integer settleType;
  // 审核人
  private String auditId;
  // 审核时间
  private String auditDate;
  // 结算人
  private String settleId;
  // 结算时间
  private String settleDate;
  // 退回人
  private String backId;
  // 退回时间
  private String backDate;
  // 临时字段 运单ids 用来打包是保存
  private Integer isHistroy;
  private String checkAuditId;
  private String checkAuditDate;

  private String orderIds;
  private String packFlag;
  private String orderType;
  // 结算客户(人员)
  private String customerName;
  // 结算客户所谓单位(人员)
  private String customerUnit;
  //财务审核反审核finace结算审核反审核settle
  private String checkAuditFlag;
  private String beginAddress;
  private String endAddress;
  // 无参构造
  public CustomerPack() {
    super();
  }

  public String getCustPackId() {
    return custPackId;
  }

  public void setCustPackId(String custPackId) {
    this.custPackId = custPackId;
  }

  public String getCustCheckConId() {
    return custCheckConId;
  }

  public void setCustCheckConId(String custCheckConId) {
    this.custCheckConId = custCheckConId;
  }

  public Integer getProjectId() {
    return projectId;
  }

  public void setProjectId(Integer projectId) {
    this.projectId = projectId;
  }

  public Integer getPackType() {
    return packType;
  }

  public void setPackType(Integer packType) {
    this.packType = packType;
  }

  public Integer getCheckingStatus() {
    return checkingStatus;
  }

  public void setCheckingStatus(Integer checkingStatus) {
    this.checkingStatus = checkingStatus;
  }

  public Integer getSettleAuditType() {
    return settleAuditType;
  }

  public void setSettleAuditType(Integer settleAuditType) {
    this.settleAuditType = settleAuditType;
  }

  public Integer getPackTruckNum() {
    return packTruckNum;
  }

  public void setPackTruckNum(Integer packTruckNum) {
    this.packTruckNum = packTruckNum;
  }

  public Integer getPackTruckDegree() {
    return packTruckDegree;
  }

  public void setPackTruckDegree(Integer packTruckDegree) {
    this.packTruckDegree = packTruckDegree;
  }

  public Integer getInvoiceStatus() {
    return invoiceStatus;
  }

  public void setInvoiceStatus(Integer invoiceStatus) {
    this.invoiceStatus = invoiceStatus;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(Integer deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

  public String getCreateDate() {
    if ("".equals(createDate)) {
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
    if ("".equals(modifiyDate)) {
      return null;
    }
    return modifiyDate;
  }

  public void setModifiyDate(String modifiyDate) {
    this.modifiyDate = modifiyDate;
  }

  public String getStartDate() {
    if ("".equals(startDate)) {
      return null;
    }
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    if ("".equals(endDate)) {
      return null;
    }
    return endDate;
  }

  public String getCheckAuditId() {
    return checkAuditId;
  }

  public void setCheckAuditId(String checkAuditId) {
    this.checkAuditId = checkAuditId;
  }

  public String getCheckAuditDate() {
    if ("".equals(checkAuditDate)) {
      return null;
    }
    return checkAuditDate;
  }

  public void setCheckAuditDate(String checkAuditDate) {
    this.checkAuditDate = checkAuditDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public Integer getOrderCount() {
    return orderCount;
  }

  public void setOrderCount(Integer orderCount) {
    this.orderCount = orderCount;
  }

  public BigDecimal getTotalWeight() {
    return totalWeight;
  }

  public void setTotalWeight(BigDecimal totalWeight) {
    this.totalWeight = totalWeight;
  }

  public Integer getTotalPiece() {
    return totalPiece;
  }

  public void setTotalPiece(Integer totalPiece) {
    this.totalPiece = totalPiece;
  }

  public BigDecimal getProduceMoney() {
    return produceMoney;
  }

  public void setProduceMoney(BigDecimal produceMoney) {
    this.produceMoney = produceMoney;
  }

  public BigDecimal getTaxMoney() {
    return taxMoney;
  }

  public void setTaxMoney(BigDecimal taxMoney) {
    this.taxMoney = taxMoney;
  }

  public BigDecimal getBesettledMoney() {
    return besettledMoney;
  }

  public void setBesettledMoney(BigDecimal besettledMoney) {
    this.besettledMoney = besettledMoney;
  }

  public BigDecimal getSettledMoney() {
    return settledMoney;
  }

  public void setSettledMoney(BigDecimal settledMoney) {
    this.settledMoney = settledMoney;
  }

  public BigDecimal getSettleMoney() {
    return settleMoney;
  }

  public void setSettleMoney(BigDecimal settleMoney) {
    this.settleMoney = settleMoney;
  }

  public Integer getSettleType() {
    return settleType;
  }

  public void setSettleType(Integer settleType) {
    this.settleType = settleType;
  }

  public String getAuditId() {
    return auditId;
  }

  public void setAuditId(String auditId) {
    this.auditId = auditId;
  }

  public String getAuditDate() {
    return auditDate;
  }

  public void setAuditDate(String auditDate) {
    this.auditDate = auditDate;
  }

  public String getSettleDate() {
    return settleDate;
  }

  public void setSettleDate(String settleDate) {
    this.settleDate = settleDate;
  }

  public String getOrderIds() {
    return orderIds;
  }

  public void setOrderIds(String orderIds) {
    this.orderIds = orderIds;
  }

  public String getPackFlag() {
    return packFlag;
  }

  public void setPackFlag(String packFlag) {
    this.packFlag = packFlag;
  }

  public String getSettleId() {
    return settleId;
  }

  public String getBackId() {
    return backId;
  }

  public void setBackId(String backId) {
    this.backId = backId;
  }

  public String getBackDate() {
    return backDate;
  }

  public void setBackDate(String backDate) {
    this.backDate = backDate;
  }

  public Integer getIsHistroy() {
    return isHistroy;
  }

  public void setIsHistroy(Integer isHistroy) {
    this.isHistroy = isHistroy;
  }

  public void setSettleId(String settleId) {
    this.settleId = settleId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerUnit() {
    return customerUnit;
  }

  public void setCustomerUnit(String customerUnit) {
    this.customerUnit = customerUnit;
  }

  public String getCheckAuditFlag() {
    return checkAuditFlag;
  }

  public void setCheckAuditFlag(String checkAuditFlag) {
    this.checkAuditFlag = checkAuditFlag;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
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
