package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 客户对账设置表-Form.
 * <p>
 * <a href="CustomerCheckingConf.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-18
 * @version 1.0.0
 * @since 1.0.0
 */
public class CustomerCheckingConf {
  
  //主键
  private String custCheckConId;
  //项目id
  private Integer projectId;
  //开始日期
  private String startDate;
  //结束日期
  private String endDate;
  //对账状态(0-待确认 1-待审核 2-财务审核通过)
  private Integer checkingStatus;
  //是否删除(0-未删除 1-删除)
  private Integer deleteFlag;
  //创建时间
  private String createDate;
  //创建人
  private Integer createUserId;
  //修改时间
  private String modifiyDate;
  //运单总数
  private Integer orderCount;
  //往期运单
  private Integer previousOrderCount;
  //总净重
  private BigDecimal totalWeight;
  //总件数
  private Integer totalPiece;
  //产生金额
  private BigDecimal produceMoney;
  private BigDecimal taxRate;
  //应开税额
  private BigDecimal taxMoney;
  //汽运对账状态 0-待对账 1-已对账 2-无汽运包
  private Integer busCheckingStatus;
  //汽运运单数量
  private Integer busOrderCount;
  //汽运对账金额
  private BigDecimal busOrderMoney;
  //接取对账状态 0-待对账 1-已对账 2-无汽运包
  private Integer receiveCheckingStatus;
  //接取运单数量
  private Integer receiveOrderCount;
  //接取对账金额
  private BigDecimal receiveOrderMoney;
  //火运对账状态 0-待对账 1-已对账 2-无汽运包
  private Integer trainCheckingStatus;
  //火运运单数量
  private Integer trainOrderCount;
  //火运对账金额
  private BigDecimal trainOrderMoney;
  //送达对账状态 0-待对账 1-已对账 2-无汽运包
  private Integer deliCheckingStatus;
  //送达运单数量
  private Integer deliOrderCount;
  //送达对账金额
  private BigDecimal deliOrderMoney;

  //无参构造
  public CustomerCheckingConf() {
    super();
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
  
  public String getStartDate() {
    if("".equals(startDate)){
      return null;
    }
    return startDate;
  }
  
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  
  public String getEndDate() {
    if("".equals(endDate)){
      return null;
    }
    return endDate;
  }
  
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  
  public Integer getCheckingStatus() {
    return checkingStatus;
  }
  
  public void setCheckingStatus(Integer checkingStatus) {
    this.checkingStatus = checkingStatus;
  }
  
  public Integer getDeleteFlag() {
    return deleteFlag;
  }
  
  public void setDeleteFlag(Integer deleteFlag) {
    this.deleteFlag = deleteFlag;
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
  
  public Integer getOrderCount() {
    return orderCount;
  }
  
  public void setOrderCount(Integer orderCount) {
    this.orderCount = orderCount;
  }
  
  public Integer getPreviousOrderCount() {
    return previousOrderCount;
  }
  
  public void setPreviousOrderCount(Integer previousOrderCount) {
    this.previousOrderCount = previousOrderCount;
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
  
  public BigDecimal getTaxRate() {
    return taxRate;
  }

  public void setTaxRate(BigDecimal taxRate) {
    this.taxRate = taxRate;
  }

  public Integer getBusCheckingStatus() {
    return busCheckingStatus;
  }
  
  public void setBusCheckingStatus(Integer busCheckingStatus) {
    this.busCheckingStatus = busCheckingStatus;
  }
  
  public Integer getBusOrderCount() {
    return busOrderCount;
  }
  
  public void setBusOrderCount(Integer busOrderCount) {
    this.busOrderCount = busOrderCount;
  }
  
  public BigDecimal getBusOrderMoney() {
    return busOrderMoney;
  }
  
  public void setBusOrderMoney(BigDecimal busOrderMoney) {
    this.busOrderMoney = busOrderMoney;
  }
  
  public Integer getReceiveCheckingStatus() {
    return receiveCheckingStatus;
  }
  
  public void setReceiveCheckingStatus(Integer receiveCheckingStatus) {
    this.receiveCheckingStatus = receiveCheckingStatus;
  }
  
  public Integer getReceiveOrderCount() {
    return receiveOrderCount;
  }
  
  public void setReceiveOrderCount(Integer receiveOrderCount) {
    this.receiveOrderCount = receiveOrderCount;
  }
  
  public BigDecimal getReceiveOrderMoney() {
    return receiveOrderMoney;
  }
  
  public void setReceiveOrderMoney(BigDecimal receiveOrderMoney) {
    this.receiveOrderMoney = receiveOrderMoney;
  }
  
  public Integer getTrainCheckingStatus() {
    return trainCheckingStatus;
  }
  
  public void setTrainCheckingStatus(Integer trainCheckingStatus) {
    this.trainCheckingStatus = trainCheckingStatus;
  }
  
  public Integer getTrainOrderCount() {
    return trainOrderCount;
  }
  
  public void setTrainOrderCount(Integer trainOrderCount) {
    this.trainOrderCount = trainOrderCount;
  }
  
  public BigDecimal getTrainOrderMoney() {
    return trainOrderMoney;
  }
  
  public void setTrainOrderMoney(BigDecimal trainOrderMoney) {
    this.trainOrderMoney = trainOrderMoney;
  }
  
  public Integer getDeliCheckingStatus() {
    return deliCheckingStatus;
  }
  
  public void setDeliCheckingStatus(Integer deliCheckingStatus) {
    this.deliCheckingStatus = deliCheckingStatus;
  }
  
  public Integer getDeliOrderCount() {
    return deliOrderCount;
  }
  
  public void setDeliOrderCount(Integer deliOrderCount) {
    this.deliOrderCount = deliOrderCount;
  }
  
  public BigDecimal getDeliOrderMoney() {
    return deliOrderMoney;
  }
  
  public void setDeliOrderMoney(BigDecimal deliOrderMoney) {
    this.deliOrderMoney = deliOrderMoney;
  }
  
}