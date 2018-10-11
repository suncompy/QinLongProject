// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.helpPojo;

import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;

/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月26日
 */
public class TbTrainStationHelp extends TbTrainStation {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2612349061659991250L;

	//省
	private String province;

	//市
	private String city;

	//区
	private String district;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
