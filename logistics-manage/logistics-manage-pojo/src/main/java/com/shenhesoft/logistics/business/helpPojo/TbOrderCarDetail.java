// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.shenhesoft.logistics.manage.pojo.anchoredCompany.TbAnchoredCompany;

/**
 * @description 订单中 -
 *
 * @author LiangLin
 *
 * @date 2017年12月22日
 */
public class TbOrderCarDetail implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9107529834815961016L;

	// 驾驶员id
	private Integer driverId;

	/**
	 * TbUserInformation 用户信息表主键
	 */
	private Integer informationId;
	/** 驾驶员姓名*/
	private String driverName;
	/** 车的类型*/
	private String motorcycleType;

	/** 驾驶员电话*/
	private String driverPhone;
	/** 车牌号*/
	private String plateNumber;

	// 运输单价
	private BigDecimal transportPrice;

	// 扣损比率
	private float deductionRate;

	// 扣损单价
	private BigDecimal deductionPrice;

	// 车队负责人姓名
	private String changerName;

	// 车队名称
	private String teamName;
	
	/**
	 * 车队id
	 */
	private String teamId;
	
	/**
	 * 车主姓名
	 */
	private String ownerCarName;
	
	/**
	 * 车辆品牌
	 */
	private String brand;
	/**
	 * 车辆型号
	 */
	private String model;
	
	/**
	 * 任务id 
	 */
	private Integer distributionId;
	
	/**
	 * 当前司机历史运单数量 - 临时字段
	 */
	private Integer historyTbOrderNumDriverId;
	
	/**
	 * 挂靠车队list
	 */
	private List<TbAnchoredCompany> tbAnchoredCompanyList;
	
	
	public Integer getDistributionId() {
		return distributionId;
	}

	public void setDistributionId(Integer distributionId) {
		this.distributionId = distributionId;
	}

	public String getChangerName() {
		return changerName;
	}

	public void setChangerName(String changerName) {
		this.changerName = changerName;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public BigDecimal getTransportPrice() {
		return transportPrice;
	}

	public void setTransportPrice(BigDecimal transportPrice) {
		this.transportPrice = transportPrice;
	}

	public float getDeductionRate() {
		return deductionRate;
	}

	public void setDeductionRate(float deductionRate) {
		this.deductionRate = deductionRate;
	}

	public BigDecimal getDeductionPrice() {
		return deductionPrice;
	}

	public void setDeductionPrice(BigDecimal deductionPrice) {
		this.deductionPrice = deductionPrice;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getInformationId() {
		return informationId;
	}

	public void setInformationId(Integer informationId) {
		this.informationId = informationId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getMotorcycleType() {
		return motorcycleType;
	}

	public void setMotorcycleType(String motorcycleType) {
		this.motorcycleType = motorcycleType;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public List<TbAnchoredCompany> getTbAnchoredCompanyList() {
		return tbAnchoredCompanyList;
	}

	public void setTbAnchoredCompanyList(List<TbAnchoredCompany> tbAnchoredCompanyList) {
		this.tbAnchoredCompanyList = tbAnchoredCompanyList;
	}

	public String getOwnerCarName() {
		return ownerCarName;
	}

	public void setOwnerCarName(String ownerCarName) {
		this.ownerCarName = ownerCarName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getHistoryTbOrderNumDriverId() {
		return historyTbOrderNumDriverId;
	}

	public void setHistoryTbOrderNumDriverId(Integer historyTbOrderNumDriverId) {
		this.historyTbOrderNumDriverId = historyTbOrderNumDriverId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	} 

}
