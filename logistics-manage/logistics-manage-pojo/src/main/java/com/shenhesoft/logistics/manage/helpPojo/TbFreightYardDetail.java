// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.helpPojo;

import java.util.List;

import com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocation;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;

/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月9日
 */
public class TbFreightYardDetail extends TbFreightYard {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6445169256267445224L;

	private String stationName;

	private List<TbCargoLocation> clList;

	// 货位json串
	private String json;

	// 货位数量
	private Integer localCount;

	//省
	private String province;

	//市
	private String city;

	//县区
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

	public Integer getLocalCount() {
		return localCount;
	}

	public void setLocalCount(Integer localCount) {
		this.localCount = localCount;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public List<TbCargoLocation> getClList() {
		return clList;
	}

	public void setClList(List<TbCargoLocation> clList) {
		this.clList = clList;
	}

}
