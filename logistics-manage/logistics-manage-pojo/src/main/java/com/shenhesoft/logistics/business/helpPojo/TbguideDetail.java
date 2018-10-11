// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;

/**
 * @description 货位引导 辅助类
 *
 * @author LiangLin
 *
 * @date 2017年12月22日
 */
public class TbguideDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2778379166142642238L;

	// 运单id
	private Integer orderId;

	// 货场名
	private String distributionCargoPlace;

	// 货位名
	private String distributionCargoSite;
	
	// 货场id
	private Integer distributionCargoPlaceId;
	
	// 货位id
	private Integer distributionCargoSiteId;
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getDistributionCargoPlace() {
		return distributionCargoPlace;
	}

	public void setDistributionCargoPlace(String distributionCargoPlace) {
		this.distributionCargoPlace = distributionCargoPlace;
	}

	public String getDistributionCargoSite() {
		return distributionCargoSite;
	}

	public void setDistributionCargoSite(String distributionCargoSite) {
		this.distributionCargoSite = distributionCargoSite;
	}

	public Integer getDistributionCargoPlaceId() {
		return distributionCargoPlaceId;
	}

	public void setDistributionCargoPlaceId(Integer distributionCargoPlaceId) {
		this.distributionCargoPlaceId = distributionCargoPlaceId;
	}

	public Integer getDistributionCargoSiteId() {
		return distributionCargoSiteId;
	}

	public void setDistributionCargoSiteId(Integer distributionCargoSiteId) {
		this.distributionCargoSiteId = distributionCargoSiteId;
	}

}
