// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;

/**
 * @description 火运出入库查询bean
 *
 * @author LiangDeng
 *
 * @date 2017年12月19日
 */
public class TrainOrderDetail implements Serializable{
	
	/**
	 * @description 
	*/
	private static final long serialVersionUID = -6432913795875152609L;

	/** id*/
    private Integer id;
    
    /** 项目id*/
    private Integer projectId;
    
    /** 项目编号*/
    private String projectCode;

    /** 项目类型*/
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
    @Range(min=0, max=7)
    private Byte transportType;

    /** 分支id*/
    private Integer branchId;

    /** 分支机构*/
    private String branchName;
    
	/** 发货企业id*/
	private Integer sendCargoCompanyId;
	
	/** 发货企业名称*/
	private String sendCargoCompanyName;
	
	/** 收货企业id*/
	private Integer receiveCargoCompanyId;
	
	/** 收货企业名称*/
	private String receiveCargoCompanyName;
	
	/** 货物id*/
    private Integer cargoId;

    /** 货物名称*/
    private String cargoName;

    /** 规格*/
    private String cargoSpecifications;

    /** 货物价格*/
    private BigDecimal cargoPrice;

    /** 计价单位 0 吨  1 位*/
    private Byte valuationUnitName;
    
    /** 始发站点id*/
    private Integer beginSiteId;

    /** 始发站点*/
    private String beginSiteName;

    /** 到站站点id*/
    private Integer endSiteId;

    /** 到达站点*/
    private String endSiteName;
    
    /** 火运订单id*/
    private Integer trainOrderId;
    
    /** 请车单号*/
    private String pleaseTrainNumber;
    
    /** 车型id*/
    private Integer carTypeId;

    /** 车型*/
    private String carType;

    /** 车牌号*/
    private String carNumber;
    
    /** 运单状态:0:取消,1:等待承认,2:等待装车,3:等待发运,4:在途运载,5:等待卸货,6:等待回单,7:已完成*/
    private Byte status;
    
    /** 发运时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date sendDate;
    
    /** 装载总吨位*/
    private BigDecimal entruckWeight;
    
    /** 发货载重*/
    private BigDecimal sendWeight;
    
    private BigDecimal conSendWeight2;
    
    private BigDecimal conUnloadWeight2;
    
    /** 到货总载重*/
    private BigDecimal arriveWeight;
    
    /** 卸货载重*/
    private BigDecimal unloadWeight;
    
    /** 到货时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date arriveDate;
    
    /** 卸货时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date arriveUnloadTime;
    
    /** 货场id*/
    private Integer cargoPlaceId;

    /** 货场*/
    private String cargoPlaceName;

    /** 货位id*/
    private Integer cargoSiteId;

    /** 货位*/
    private String cargoSiteName;
    
    /** 集装箱号1*/
    private String containerNumber1;

    /** 集装箱号2*/
    private String containerNumber2;
    
    /** 卸货货场id*/
    private Integer arriveCargoPlaceId;

    /** 卸货货场名称*/
    private String arriveCargoPlaceName;

    /** 卸货货位id*/
    private Integer arriveCargoSiteId;

    /** 卸货货位名称*/
    private String arriveCargoSiteName;
    
    /** 总计请车次数*/
    private Integer sumPleaseNum;
    
    /** 总计请车数*/
    private Integer sumCarNum;
    
    /** 总计请车吨位*/
    private BigDecimal sumPleaseWeight;
    
    /** 总计到货吨位*/
    private BigDecimal sumArriveWeight;
    
    /** 总计损耗*/
    private BigDecimal sumWastage;
    
    /** 损耗*/
    private BigDecimal wastage;
    
    /** 条件查询发送时间*/
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date newSendDate;
    
    /** 条件查询到货时间*/
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date newArriveDate;
    
    private String exportProjectType;
    private String exportTransportType;
    private String exportSendDate;
    private String exportArriveDate;

    private String sysOrgCode;
    
	public String getSysOrgCode() {
		return sysOrgCode;
	}

	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	public BigDecimal getConSendWeight2() {
		return conSendWeight2;
	}

	public void setConSendWeight2(BigDecimal conSendWeight2) {
		this.conSendWeight2 = conSendWeight2;
	}

	public BigDecimal getConUnloadWeight2() {
		return conUnloadWeight2;
	}

	public void setConUnloadWeight2(BigDecimal conUnloadWeight2) {
		this.conUnloadWeight2 = conUnloadWeight2;
	}

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

	public String getExportSendDate() {
		return exportSendDate;
	}

	public void setExportSendDate(String exportSendDate) {
		this.exportSendDate = exportSendDate;
	}

	public String getExportArriveDate() {
		return exportArriveDate;
	}

	public void setExportArriveDate(String exportArriveDate) {
		this.exportArriveDate = exportArriveDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Integer getSendCargoCompanyId() {
		return sendCargoCompanyId;
	}

	public void setSendCargoCompanyId(Integer sendCargoCompanyId) {
		this.sendCargoCompanyId = sendCargoCompanyId;
	}

	public String getSendCargoCompanyName() {
		return sendCargoCompanyName;
	}

	public void setSendCargoCompanyName(String sendCargoCompanyName) {
		this.sendCargoCompanyName = sendCargoCompanyName;
	}

	public Integer getReceiveCargoCompanyId() {
		return receiveCargoCompanyId;
	}

	public void setReceiveCargoCompanyId(Integer receiveCargoCompanyId) {
		this.receiveCargoCompanyId = receiveCargoCompanyId;
	}

	public String getReceiveCargoCompanyName() {
		return receiveCargoCompanyName;
	}

	public void setReceiveCargoCompanyName(String receiveCargoCompanyName) {
		this.receiveCargoCompanyName = receiveCargoCompanyName;
	}

	public Integer getCargoId() {
		return cargoId;
	}

	public void setCargoId(Integer cargoId) {
		this.cargoId = cargoId;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getCargoSpecifications() {
		return cargoSpecifications;
	}

	public void setCargoSpecifications(String cargoSpecifications) {
		this.cargoSpecifications = cargoSpecifications;
	}

	public BigDecimal getCargoPrice() {
		return cargoPrice;
	}

	public void setCargoPrice(BigDecimal cargoPrice) {
		this.cargoPrice = cargoPrice;
	}

	public Byte getValuationUnitName() {
		return valuationUnitName;
	}

	public void setValuationUnitName(Byte valuationUnitName) {
		this.valuationUnitName = valuationUnitName;
	}

	public Integer getBeginSiteId() {
		return beginSiteId;
	}

	public void setBeginSiteId(Integer beginSiteId) {
		this.beginSiteId = beginSiteId;
	}

	public String getBeginSiteName() {
		return beginSiteName;
	}

	public void setBeginSiteName(String beginSiteName) {
		this.beginSiteName = beginSiteName;
	}

	public Integer getEndSiteId() {
		return endSiteId;
	}

	public void setEndSiteId(Integer endSiteId) {
		this.endSiteId = endSiteId;
	}

	public String getEndSiteName() {
		return endSiteName;
	}

	public void setEndSiteName(String endSiteName) {
		this.endSiteName = endSiteName;
	}

	public Integer getTrainOrderId() {
		return trainOrderId;
	}

	public void setTrainOrderId(Integer trainOrderId) {
		this.trainOrderId = trainOrderId;
	}

	public String getPleaseTrainNumber() {
		return pleaseTrainNumber;
	}

	public void setPleaseTrainNumber(String pleaseTrainNumber) {
		this.pleaseTrainNumber = pleaseTrainNumber;
	}

	public Integer getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public BigDecimal getEntruckWeight() {
		return entruckWeight;
	}

	public void setEntruckWeight(BigDecimal entruckWeight) {
		this.entruckWeight = entruckWeight;
	}

	public BigDecimal getSendWeight() {
		return sendWeight;
	}

	public void setSendWeight(BigDecimal sendWeight) {
		this.sendWeight = sendWeight;
	}

	public BigDecimal getUnloadWeight() {
		return unloadWeight;
	}

	public void setUnloadWeight(BigDecimal unloadWeight) {
		this.unloadWeight = unloadWeight;
	}

	public BigDecimal getArriveWeight() {
		return arriveWeight;
	}

	public void setArriveWeight(BigDecimal arriveWeight) {
		this.arriveWeight = arriveWeight;
	}

	public Date getArriveUnloadTime() {
		return arriveUnloadTime;
	}

	public void setArriveUnloadTime(Date arriveUnloadTime) {
		this.arriveUnloadTime = arriveUnloadTime;
	}

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	public Integer getCargoPlaceId() {
		return cargoPlaceId;
	}

	public void setCargoPlaceId(Integer cargoPlaceId) {
		this.cargoPlaceId = cargoPlaceId;
	}

	public String getCargoPlaceName() {
		return cargoPlaceName;
	}

	public void setCargoPlaceName(String cargoPlaceName) {
		this.cargoPlaceName = cargoPlaceName;
	}

	public Integer getCargoSiteId() {
		return cargoSiteId;
	}

	public void setCargoSiteId(Integer cargoSiteId) {
		this.cargoSiteId = cargoSiteId;
	}

	public String getCargoSiteName() {
		return cargoSiteName;
	}

	public void setCargoSiteName(String cargoSiteName) {
		this.cargoSiteName = cargoSiteName;
	}

	public String getContainerNumber1() {
		return containerNumber1;
	}

	public void setContainerNumber1(String containerNumber1) {
		this.containerNumber1 = containerNumber1;
	}

	public String getContainerNumber2() {
		return containerNumber2;
	}

	public void setContainerNumber2(String containerNumber2) {
		this.containerNumber2 = containerNumber2;
	}

	public Integer getArriveCargoPlaceId() {
		return arriveCargoPlaceId;
	}

	public void setArriveCargoPlaceId(Integer arriveCargoPlaceId) {
		this.arriveCargoPlaceId = arriveCargoPlaceId;
	}

	public String getArriveCargoPlaceName() {
		return arriveCargoPlaceName;
	}

	public void setArriveCargoPlaceName(String arriveCargoPlaceName) {
		this.arriveCargoPlaceName = arriveCargoPlaceName;
	}

	public Integer getArriveCargoSiteId() {
		return arriveCargoSiteId;
	}

	public void setArriveCargoSiteId(Integer arriveCargoSiteId) {
		this.arriveCargoSiteId = arriveCargoSiteId;
	}

	public String getArriveCargoSiteName() {
		return arriveCargoSiteName;
	}

	public void setArriveCargoSiteName(String arriveCargoSiteName) {
		this.arriveCargoSiteName = arriveCargoSiteName;
	}

	public Integer getSumPleaseNum() {
		return sumPleaseNum;
	}

	public void setSumPleaseNum(Integer sumPleaseNum) {
		this.sumPleaseNum = sumPleaseNum;
	}

	public Integer getSumCarNum() {
		return sumCarNum;
	}

	public void setSumCarNum(Integer sumCarNum) {
		this.sumCarNum = sumCarNum;
	}

	public BigDecimal getSumPleaseWeight() {
		return sumPleaseWeight;
	}

	public void setSumPleaseWeight(BigDecimal sumPleaseWeight) {
		this.sumPleaseWeight = sumPleaseWeight;
	}

	public BigDecimal getSumArriveWeight() {
		return sumArriveWeight;
	}

	public void setSumArriveWeight(BigDecimal sumArriveWeight) {
		this.sumArriveWeight = sumArriveWeight;
	}

	public BigDecimal getSumWastage() {
		return sumWastage;
	}

	public void setSumWastage(BigDecimal sumWastage) {
		this.sumWastage = sumWastage;
	}

	public BigDecimal getWastage() {
		return wastage;
	}

	public void setWastage(BigDecimal wastage) {
		this.wastage = wastage;
	}

	public Date getNewSendDate() {
		return newSendDate;
	}

	public void setNewSendDate(Date newSendDate) {
		this.newSendDate = newSendDate;
	}

	public Date getNewArriveDate() {
		return newArriveDate;
	}

	public void setNewArriveDate(Date newArriveDate) {
		this.newArriveDate = newArriveDate;
	}

    
}
