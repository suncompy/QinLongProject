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
 * @date 2017年12月5日
 */
public class TbCargoInfomationDetail implements Serializable{
	
	 /**
	 * @description serialVersionUID
	*/
	private static final long serialVersionUID = -5851779849095863263L;

	/** 主要指标*/
    private String pointName;

    /** 指标界定最小值*/
    private Integer pointMin;

    /** 指标界定最大值*/
    private Integer pointMax;

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
    
    
}
