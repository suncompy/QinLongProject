package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * @description 库存盘查
 * 
 * @author shilvfei
 * 
 * @date 2017年12月28日
 */
public class StuckHelpPojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -961770555365151724L;

	/** */
    protected Integer projectId;
	
	  /** 项目编号*/
    private String projectCode;

    /** 分支机构名称*/
    private String branchGroupName;

    /** 项目类型
            0 集装箱
            1 散装*/
    private Byte projectType;

    /** 联运模式
            0 汽运
            1 接取
            2 送达
            3 火运
            4 接取+火运
            5 火运+送达
            6 联运 
            7接取+送达*/
    private Byte transportType;

    /** 货物名称*/
    private String cargoName;
    
    /** 发货企业名称*/
    private String sendCargoCompanyName;
    
    /** 收货企业名称*/
    private String receiveCargoCompanyName;
    
    /** 始发站点ID*/
    private Integer beginStationId;
    
    /** 始发站点名称*/
    private String beginStationName;
    
    /** 始发站点地址*/
    private String beginStationAddress;

    /** 货场 
     * 多个就显示多货场
     * 单个就是显示货场名称
     * */
    private String beginFreightYardName;

    /** 货位名称
     * 多个就显示多货位
     * 单个就是显示货位名称*
     * */
    private String beginCargoLocationName;

    /** 入库吨位*/
    private BigDecimal beginEnterQty;
    
    /** 出库吨位*/
    private BigDecimal beginOutQty;
    
    /**
     * 现有库存
     */
    private BigDecimal beginCurrentQty;
    
    /** 库存调整吨位*/
    private BigDecimal beginAdjustQty;
    
    /** 到达站点名称*/
    private Integer endStationId;
    
    /** 到达站点名称*/
    private String endStationName;
    
    /** 到达站点地址*/
    private String endStationAddress;

    /** 货场 */
    private String endFreightYardName;

    /** 货位名称*/
    private String endCargoLocationName;

    /** 入库吨位*/
    private BigDecimal endEnterQty;

    /** 出库吨位*/
    private BigDecimal endOutQty;
    
    /**
     * 现有库存
     */
    private BigDecimal endCurrentQty;
    
    /** 库存调整吨位*/
    private BigDecimal endAdjustQty;
    
    /** 盘查时间   */
    private Date adjustDate;
    
    /**库存明细**/
    private List stocks;
    
    //库存盘查辅助字段
    private String exportProjectType;
    private String exportTransportType;
    private String queryTime;
    
	public String getExportProjectType() {
		return exportProjectType;
	}

	public void setExportProjectType(String exportProjectType) {
		this.exportProjectType = exportProjectType;
	}

	public String getExportTransportType() {
		return exportTransportType;
	}

	public void setExportTransportType(String exportTransportType) {
		this.exportTransportType = exportTransportType;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public String getBeginStationAddress() {
		return beginStationAddress;
	}

	public void setBeginStationAddress(String beginStationAddress) {
		this.beginStationAddress = beginStationAddress;
	}

	public String getEndStationAddress() {
		return endStationAddress;
	}

	public void setEndStationAddress(String endStationAddress) {
		this.endStationAddress = endStationAddress;
	}

	public Integer getBeginStationId() {
		return beginStationId;
	}

	public void setBeginStationId(Integer beginStationId) {
		this.beginStationId = beginStationId;
	}

	public Integer getEndStationId() {
		return endStationId;
	}

	public void setEndStationId(Integer endStationId) {
		this.endStationId = endStationId;
	}

	public List getStocks() {
		return stocks;
	}

	public void setStocks(List stocks) {
		this.stocks = stocks;
	}

	public BigDecimal getBeginCurrentQty() {
		return beginCurrentQty;
	}

	public void setBeginCurrentQty(BigDecimal beginCurrentQty) {
		this.beginCurrentQty = beginCurrentQty;
	}

	public BigDecimal getEndCurrentQty() {
		return endCurrentQty;
	}

	public void setEndCurrentQty(BigDecimal endCurrentQty) {
		this.endCurrentQty = endCurrentQty;
	}

	public String getBeginStationName() {
		return beginStationName;
	}

	public void setBeginStationName(String beginStationName) {
		this.beginStationName = beginStationName;
	}

	public String getBeginFreightYardName() {
		return beginFreightYardName;
	}

	public void setBeginFreightYardName(String beginFreightYardName) {
		this.beginFreightYardName = beginFreightYardName;
	}

	public String getBeginCargoLocationName() {
		return beginCargoLocationName;
	}

	public void setBeginCargoLocationName(String beginCargoLocationName) {
		this.beginCargoLocationName = beginCargoLocationName;
	}

	public BigDecimal getBeginEnterQty() {
		return beginEnterQty;
	}

	public void setBeginEnterQty(BigDecimal beginEnterQty) {
		this.beginEnterQty = beginEnterQty;
	}

	public BigDecimal getBeginOutQty() {
		return beginOutQty;
	}

	public void setBeginOutQty(BigDecimal beginOutQty) {
		this.beginOutQty = beginOutQty;
	}

	public BigDecimal getBeginAdjustQty() {
		return beginAdjustQty;
	}

	public void setBeginAdjustQty(BigDecimal beginAdjustQty) {
		this.beginAdjustQty = beginAdjustQty;
	}

	public String getEndStationName() {
		return endStationName;
	}

	public void setEndStationName(String endStationName) {
		this.endStationName = endStationName;
	}

	public String getEndFreightYardName() {
		return endFreightYardName;
	}

	public void setEndFreightYardName(String endFreightYardName) {
		this.endFreightYardName = endFreightYardName;
	}

	public String getEndCargoLocationName() {
		return endCargoLocationName;
	}

	public void setEndCargoLocationName(String endCargoLocationName) {
		this.endCargoLocationName = endCargoLocationName;
	}

	public BigDecimal getEndEnterQty() {
		return endEnterQty;
	}

	public void setEndEnterQty(BigDecimal endEnterQty) {
		this.endEnterQty = endEnterQty;
	}

	public BigDecimal getEndOutQty() {
		return endOutQty;
	}

	public void setEndOutQty(BigDecimal endOutQty) {
		this.endOutQty = endOutQty;
	}

	public BigDecimal getEndAdjustQty() {
		return endAdjustQty;
	}

	public void setEndAdjustQty(BigDecimal endAdjustQty) {
		this.endAdjustQty = endAdjustQty;
	}

	public Date getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	public String getSendCargoCompanyName() {
		return sendCargoCompanyName;
	}

	public void setSendCargoCompanyName(String sendCargoCompanyName) {
		this.sendCargoCompanyName = sendCargoCompanyName;
	}

	public String getReceiveCargoCompanyName() {
		return receiveCargoCompanyName;
	}

	public void setReceiveCargoCompanyName(String receiveCargoCompanyName) {
		this.receiveCargoCompanyName = receiveCargoCompanyName;
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
		this.projectCode = projectCode;
	}

	public String getBranchGroupName() {
		return branchGroupName;
	}

	public void setBranchGroupName(String branchGroupName) {
		this.branchGroupName = branchGroupName;
	}

	public Byte getProjectType() {
		return projectType;
	}

	public void setProjectType(Byte projectType) {
		this.projectType = projectType;
	}

	public Byte getTransportType() {
		return transportType;
	}

	public void setTransportType(Byte transportType) {
		this.transportType = transportType;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
    
    
	
}
