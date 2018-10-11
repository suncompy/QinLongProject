// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.helpPojo;

import com.shenhesoft.logistics.manage.pojo.box.TbContainer;

/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月8日
 */
public class TbBoxDetail extends TbContainer {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3573072064392079632L;

	private Integer typeId;

	/**
	 * 厢型
	 */
	private String name;

	private String code;

	private String stationName;
	
	private String projectCode;
	
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
}
