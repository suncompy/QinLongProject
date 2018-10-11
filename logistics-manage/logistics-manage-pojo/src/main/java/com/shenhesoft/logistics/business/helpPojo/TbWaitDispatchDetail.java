// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @description 等待调度pojo
 *
 * @author LiangLin
 *
 * @date 2017年12月21日
 */
public class TbWaitDispatchDetail implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4934271076600736643L;

	@NotNull
	private Integer orderId;
	
	// 1 同意 0不同意
	@NotNull
	private byte isAgree;

	private String containerNumber1;

	private String containerNumber2;

	private Integer containerNumber1Id;

	private Integer containerNumber2Id;
	
	private Integer takeCargoPlaceId;
	
	private String takeCarogoPlaceName;
	
	private Integer takeCargoSiteId;
	
	private String takeCargoSiteName;

	// 驳回理由
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public byte getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(byte isAgree) {
		this.isAgree = isAgree;
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

	public Integer getContainerNumber1Id() {
		return containerNumber1Id;
	}

	public void setContainerNumber1Id(Integer containerNumber1Id) {
		this.containerNumber1Id = containerNumber1Id;
	}

	public Integer getContainerNumber2Id() {
		return containerNumber2Id;
	}

	public void setContainerNumber2Id(Integer containerNumber2Id) {
		this.containerNumber2Id = containerNumber2Id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getTakeCargoPlaceId() {
		return takeCargoPlaceId;
	}

	public void setTakeCargoPlaceId(Integer takeCargoPlaceId) {
		this.takeCargoPlaceId = takeCargoPlaceId;
	}

	public String getTakeCarogoPlaceName() {
		return takeCarogoPlaceName;
	}

	public void setTakeCarogoPlaceName(String takeCarogoPlaceName) {
		this.takeCarogoPlaceName = takeCarogoPlaceName;
	}

	public Integer getTakeCargoSiteId() {
		return takeCargoSiteId;
	}

	public void setTakeCargoSiteId(Integer takeCargoSiteId) {
		this.takeCargoSiteId = takeCargoSiteId;
	}

	public String getTakeCargoSiteName() {
		return takeCargoSiteName;
	}

	public void setTakeCargoSiteName(String takeCargoSiteName) {
		this.takeCargoSiteName = takeCargoSiteName;
	}

	
}
