package com.shenhesoft.logistics.finance;

/**
 * 短驳打包-运单中间表-Form.
 * <p>
 * <a href="ShPackOrder.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-18
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShPackOrder {
  
  //主键
  private String shPackOrderId;
  //打包信息id
  private String shPackId;
  //运单id
  private Integer orderId;
  //是否删除(0-未删除 1-删除)
  private Integer deleteFlag;
  //运单财务信息主键
  private String shOrderFinId;

  //无参构造
  public ShPackOrder() {
    super();
  }

  public String getShPackOrderId() {
    return shPackOrderId;
  }
  
  public void setShPackOrderId(String shPackOrderId) {
    this.shPackOrderId = shPackOrderId;
  }
  
  public String getShPackId() {
    return shPackId;
  }
  
  public void setShPackId(String shPackId) {
    this.shPackId = shPackId;
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
  
  public String getShOrderFinId() {
    return shOrderFinId;
  }
  
  public void setShOrderFinId(String shOrderFinId) {
    this.shOrderFinId = shOrderFinId;
  }
  
}