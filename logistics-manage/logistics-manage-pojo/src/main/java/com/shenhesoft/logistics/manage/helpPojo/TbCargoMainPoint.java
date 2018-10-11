// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.helpPojo;

import java.io.Serializable;
import java.util.List;

import com.shenhesoft.logistics.manage.pojo.specifications.TbSpecifications;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月5日
 */
public class TbCargoMainPoint implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5203436285155409955L;

	/** 货物品名id*/
	private Integer id;

    /** 货物品名*/
    private String cargoName;

    /** 编号*/
    private String cargoCode;
    
    /** 指标id*/
	private Integer pointId;
    
	/** 主要指标*/
    private String pointName;

    /** 指标界定最小值*/
    private Integer pointMin;

    /** 指标界定最大值*/
    private Integer pointMax;

    /** 指标类型*/
    private Byte type;
    
    /** 规格id*/
	private Integer specificationId;
	
    /** 规格名*/
    private List<TbSpecifications> listName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public Integer getPointMin() {
		return pointMin;
	}

	public void setPointMin(Integer pointMin) {
		this.pointMin = pointMin;
	}

	public Integer getPointMax() {
		return pointMax;
	}

	public void setPointMax(Integer pointMax) {
		this.pointMax = pointMax;
	}

	public String getCargoCode() {
		return cargoCode;
	}

	public void setCargoCode(String cargoCode) {
		this.cargoCode = cargoCode;
	}

	public Integer getPointId() {
		return pointId;
	}

	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Integer getSpecificationId() {
		return specificationId;
	}

	public void setSpecificationId(Integer specificationId) {
		this.specificationId = specificationId;
	}

	public List<TbSpecifications> getListName() {
		return listName;
	}

	public void setListName(List<TbSpecifications> listName) {
		this.listName = listName;
	}

    
}
