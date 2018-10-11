// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.helpPojo;

import java.io.Serializable;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月7日
 */
public class CargoPointDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6696918648257745440L;
	private Integer id;
	//序列
	private Integer serial;
	 /** 其他指标*/
    private String pointName;

    /** 指标界定最小值*/
    private Integer pointMin;

    /** 指标界定最大值*/
    private Integer pointMax;
    
    private byte type;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
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

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}
    
}
