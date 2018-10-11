package com.shenhesoft.logistics.business.pojo.stock;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TbStock implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -616670966920585658L;

	 /** */
    private Integer id;

    /** 站点id*/
    private Integer stationId;

    /** 站点名称*/
    private String stationName;

    /** 货场id*/
    private Integer freightYardId;

    /** 货场名*/
    private String freightYardName;

    /** 货位id*/
    private Integer cargoLocationId;

    /** 货位名称*/
    private String cargoLocationName;

    /** 入库吨位*/
    private BigDecimal enterQty;

    /** 出库吨位*/
    private BigDecimal outQty;

    /** 集装箱组数*/
    private Integer containerNum;

    /** 库存*/
    private BigDecimal currentQty;

    /** 调整吨位*/
    private BigDecimal adjustQty;

    /** 项目id*/
    private Integer projectId;

    /** 项目编号*/
    private String projectCode;

    /** 项目类型*/
    private Byte projectType;

    /** 0 始发站点 1 到达站点*/
    private Byte type;

    /** 最新一次调整时间*/
    private Date adjustDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    public Integer getFreightYardId() {
        return freightYardId;
    }

    public void setFreightYardId(Integer freightYardId) {
        this.freightYardId = freightYardId;
    }

    public String getFreightYardName() {
        return freightYardName;
    }

    public void setFreightYardName(String freightYardName) {
        this.freightYardName = freightYardName == null ? null : freightYardName.trim();
    }

    public Integer getCargoLocationId() {
        return cargoLocationId;
    }

    public void setCargoLocationId(Integer cargoLocationId) {
        this.cargoLocationId = cargoLocationId;
    }

    public String getCargoLocationName() {
        return cargoLocationName;
    }

    public void setCargoLocationName(String cargoLocationName) {
        this.cargoLocationName = cargoLocationName == null ? null : cargoLocationName.trim();
    }

    public BigDecimal getEnterQty() {
        return enterQty;
    }

    public void setEnterQty(BigDecimal enterQty) {
        this.enterQty = enterQty;
    }

    public BigDecimal getOutQty() {
        return outQty;
    }

    public void setOutQty(BigDecimal outQty) {
        this.outQty = outQty;
    }

    public Integer getContainerNum() {
        return containerNum;
    }

    public void setContainerNum(Integer containerNum) {
        this.containerNum = containerNum;
    }

    public BigDecimal getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(BigDecimal currentQty) {
        this.currentQty = currentQty;
    }

    public BigDecimal getAdjustQty() {
        return adjustQty;
    }

    public void setAdjustQty(BigDecimal adjustQty) {
        this.adjustQty = adjustQty;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public Byte getProjectType() {
        return projectType;
    }

    public void setProjectType(Byte projectType) {
        this.projectType = projectType;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getAdjustDate() {
        return adjustDate;
    }

    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
    }
}