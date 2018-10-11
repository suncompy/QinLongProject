
package com.shenhesoft.logistics.business.pojo.map;

import java.math.BigDecimal;

/**
 * 短驳打包-运单中间表-Form.
 * <p>
 * <a href="MapPoint.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-01-18
 * @version 1.0.0
 * @since 1.0.0
 */
public class MapPoint {
  
  //主键
  private String id;
  //项目id
  private Integer projectId;
  //运单id
  private Integer orderId;
  //经度
  private BigDecimal lon;
  //纬度
  private BigDecimal lat;
  //位置
  private String position;
  //车辆标识
  private String carId;
  //车辆编号
  private String carNo;
  //备注
  private String remark;
  //历史记录(0-当前记录 1-历史记录)
  private Integer historyFlag;
  
  private String createDate;
  private String updateDate;
  private Integer createUserId;
  private Integer updateUserId;
  private String sysOrgCode;
  //无参构造
  public MapPoint() {
    super();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public BigDecimal getLon() {
    return lon;
  }

  public void setLon(BigDecimal lon) {
    this.lon = lon;
  }

  public BigDecimal getLat() {
    return lat;
  }

  public void setLat(BigDecimal lat) {
    this.lat = lat;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getCarId() {
    return carId;
  }

  public void setCarId(String carId) {
    this.carId = carId;
  }

  public String getCarNo() {
    return carNo;
  }

  public void setCarNo(String carNo) {
    this.carNo = carNo;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getHistoryFlag() {
    return historyFlag;
  }

  public void setHistoryFlag(Integer historyFlag) {
    this.historyFlag = historyFlag;
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

  public String getUpdateDate() {
    if("".equals(updateDate)){
      return null;
    }
    return updateDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }

  public Integer getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(Integer createUserId) {
    this.createUserId = createUserId;
  }

  public Integer getUpdateUserId() {
    return updateUserId;
  }

  public void setUpdateUserId(Integer updateUserId) {
    this.updateUserId = updateUserId;
  }

  public String getSysOrgCode() {
    return sysOrgCode;
  }

  public void setSysOrgCode(String sysOrgCode) {
    this.sysOrgCode = sysOrgCode;
  }
  
}