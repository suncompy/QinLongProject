// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;

/**
 * @description 货场货位省市联动
 *
 * @author LiangLin
 *
 * @date 2017年12月22日
 */
public class TbFreightLocationDetail implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9182545200409088620L;

	private Integer freightId;

	private String freightName;

	private Integer cargoId;

	private String cargoName;

	private String cargoCode;

	public String getCargoCode() {
		return cargoCode;
	}

	public void setCargoCode(String cargoCode) {
		this.cargoCode = cargoCode;
	}

	public Integer getFreightId() {
		return freightId;
	}

	public void setFreightId(Integer freightId) {
		this.freightId = freightId;
	}

	public String getFreightName() {
		return freightName;
	}

	public void setFreightName(String freightName) {
		this.freightName = freightName;
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

}
