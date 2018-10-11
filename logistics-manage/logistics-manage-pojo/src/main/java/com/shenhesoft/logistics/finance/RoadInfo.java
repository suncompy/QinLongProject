package com.shenhesoft.logistics.finance;

/**
 * 路况信息.
 * <p>
 * <a href="RoadInfo.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-04-13
 * @version 1.0.0
 * @since 1.0.0
 */
public class RoadInfo {
  private String id;
  private String driverMsg;
  private String driverId;
  private String driverName;
  private String remark;
  private String createTime;
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getDriverMsg() {
    return driverMsg;
  }
  public void setDriverMsg(String driverMsg) {
    this.driverMsg = driverMsg;
  }
  public String getDriverId() {
    return driverId;
  }
  public void setDriverId(String driverId) {
    this.driverId = driverId;
  }
  public String getDriverName() {
    return driverName;
  }
  public void setDriverName(String driverName) {
    this.driverName = driverName;
  }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  public String getCreateTime() {
    if ("".equals(createTime)) {
      return null;
    }
    return createTime;
  }
  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }
  
}
