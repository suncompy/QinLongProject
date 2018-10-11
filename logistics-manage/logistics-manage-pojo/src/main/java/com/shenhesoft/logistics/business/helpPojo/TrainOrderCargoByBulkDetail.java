// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalce;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月25日
 */
public class TrainOrderCargoByBulkDetail extends TbTrainOrderCargoPalce implements Serializable{
	
	/**
	 * @description serialVersionUID
	*/
	private static final long serialVersionUID = -2069723383389866284L;

	//火运id
	private Integer orderId;
	
	//项目id
	private Integer hidenProjectId;
	
	//装车数
	@NotNull
	private Integer entruckNumbe;
	
	//装载吨位
	@NotNull
	private BigDecimal entruckWeight;
	
	@NotBlank
	private String entruckInfoJson;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getHidenProjectId() {
		return hidenProjectId;
	}

	public void setHidenProjectId(Integer hidenProjectId) {
		this.hidenProjectId = hidenProjectId;
	}

	public Integer getEntruckNumbe() {
		return entruckNumbe;
	}

	public void setEntruckNumbe(Integer entruckNumbe) {
		this.entruckNumbe = entruckNumbe;
	}

	public BigDecimal getEntruckWeight() {
		return entruckWeight;
	}

	public void setEntruckWeight(BigDecimal entruckWeight) {
		this.entruckWeight = entruckWeight;
	}

	public String getEntruckInfoJson() {
		return entruckInfoJson;
	}

	public void setEntruckInfoJson(String entruckInfoJson) {
		this.entruckInfoJson = entruckInfoJson;
	}
	
	
}
