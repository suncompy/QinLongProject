package com.shenhesoft.logistics.finance;

/**
 * 客户打包-运单中间表-Form.
 * <p>
 * <a href="CustomerPackOrder.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public class CustomerPackOrder {
  
  //主键
  private String custPackOrderId;
  //客户对账打包信息id
  private String custPackId;
  //运单id
  private Integer orderId;
  //是否删除(0-未删除 1-删除)
  private Integer deleteFlag;

  //无参构造
  public CustomerPackOrder() {
    super();
  }

  public String getCustPackOrderId() {
    return custPackOrderId;
  }
  
  public void setCustPackOrderId(String custPackOrderId) {
    this.custPackOrderId = custPackOrderId;
  }
  
  public String getCustPackId() {
    return custPackId;
  }
  
  public void setCustPackId(String custPackId) {
    this.custPackId = custPackId;
  }
  
  public Integer getOrderId() {
    return orderId;
  }
  
  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }
  
  public Integer getDeleteFlag() {
    return deleteFlag;
  }
  
  public void setDeleteFlag(Integer deleteFlag) {
    this.deleteFlag = deleteFlag;
  }
  
}