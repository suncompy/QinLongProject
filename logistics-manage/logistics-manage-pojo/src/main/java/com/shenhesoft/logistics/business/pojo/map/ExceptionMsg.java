package com.shenhesoft.logistics.business.pojo.map;

/**
 * 短驳打包-运单中间表-Form.
 * <p>
 * <a href="ExceptionMsg.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-18
 * @version 1.0.0
 * @since 1.0.0
 */
public class ExceptionMsg {
  
  //主键
  private String exceptionId;
  //打包信息id
  private Integer shortTrainFlag;
  //运单id
  private Integer orderId;
  private String nodeId;
  private String nodeType;
  //是否删除(0-未删除 1-删除)
  private Integer exceptionSource;
  //运单财务信息主键
  private String exceptionReason;
  private String exceptionReasonDetail;
  private Integer submitUserId;
  private String submitDate;
  
  private String projectCode;
  private String orderCode;
  private String carPlateNumber;
  private String cargoName;
  private String sendCompany;
  private String receiptCompany;
  //无参构造
  public ExceptionMsg() {
    super();
  }
  
  public String getExceptionId() {
    return exceptionId;
  }
  public void setExceptionId(String exceptionId) {
    this.exceptionId = exceptionId;
  }
  public Integer getShortTrainFlag() {
    return shortTrainFlag;
  }
  public void setShortTrainFlag(Integer shortTrainFlag) {
    this.shortTrainFlag = shortTrainFlag;
  }
  public Integer getOrderId() {
    return orderId;
  }
  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }
  public Integer getExceptionSource() {
    return exceptionSource;
  }
  public void setExceptionSource(Integer exceptionSource) {
    this.exceptionSource = exceptionSource;
  }
  public String getExceptionReason() {
    return exceptionReason;
  }
  public void setExceptionReason(String exceptionReason) {
    this.exceptionReason = exceptionReason;
  }
  public String getExceptionReasonDetail() {
    return exceptionReasonDetail;
  }
  public void setExceptionReasonDetail(String exceptionReasonDetail) {
    this.exceptionReasonDetail = exceptionReasonDetail;
  }
  public Integer getSubmitUserId() {
    return submitUserId;
  }
  public void setSubmitUserId(Integer submitUserId) {
    this.submitUserId = submitUserId;
  }
  public String getSubmitDate() {
    return submitDate;
  }
  public void setSubmitDate(String submitDate) {
    this.submitDate = submitDate;
  }

  public String getNodeType() {
    return nodeType;
  }

  public void setNodeType(String nodeType) {
    this.nodeType = nodeType;
  }

  public String getProjectCode() {
    return projectCode;
  }

  public void setProjectCode(String projectCode) {
    this.projectCode = projectCode;
  }

  public String getOrderCode() {
    return orderCode;
  }

  public void setOrderCode(String orderCode) {
    this.orderCode = orderCode;
  }

  public String getCarPlateNumber() {
    return carPlateNumber;
  }

  public void setCarPlateNumber(String carPlateNumber) {
    this.carPlateNumber = carPlateNumber;
  }

  public String getCargoName() {
    return cargoName;
  }

  public void setCargoName(String cargoName) {
    this.cargoName = cargoName;
  }

  public String getSendCompany() {
    return sendCompany;
  }

  public void setSendCompany(String sendCompany) {
    this.sendCompany = sendCompany;
  }

  public String getReceiptCompany() {
    return receiptCompany;
  }

  public void setReceiptCompany(String receiptCompany) {
    this.receiptCompany = receiptCompany;
  }

  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

}