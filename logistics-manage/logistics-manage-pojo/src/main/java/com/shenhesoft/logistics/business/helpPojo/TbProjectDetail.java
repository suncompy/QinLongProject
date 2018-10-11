// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.helpPojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.shenhesoft.logistics.manage.pojo.project.TbProject;

/**
 * @description 发布任务list 辅助类
 *
 * @author LiangLin
 *
 * @date 2017年12月19日
 */
public class TbProjectDetail extends TbProject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 79747856129234171L;

	/** 项目id */
	private Integer projectId;

	/** 每日车数 */
	private Integer carNum;

	/** 单车重量 */
	private BigDecimal singleWeight;

	/** 创建人id */
	private Integer creatorId;

	/** 创建时间 */
	private Date createDate;

	/** 1:接去 2:送达 */
	private Byte type;

	/** 已经被的领取分配数 */
	private Integer alreadyRecNum;
	
	/**
	 * 待领数量
	 */
	private Integer waitRecNum;
	
	/**
	 * 今日完成数量
	 */
	private Integer completeTodayNum;

	private Date beginDate;
	
	private Date endDate;
	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getCarNum() {
		return carNum;
	}

	public void setCarNum(Integer carNum) {
		this.carNum = carNum;
	}

	public BigDecimal getSingleWeight() {
		return singleWeight;
	}

	public void setSingleWeight(BigDecimal singleWeight) {
		this.singleWeight = singleWeight;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Integer getAlreadyRecNum() {
		return alreadyRecNum;
	}

	public void setAlreadyRecNum(Integer alreadyRecNum) {
		this.alreadyRecNum = alreadyRecNum;
	}

	public Integer getCompleteTodayNum() {
		return completeTodayNum;
	}

	public void setCompleteTodayNum(Integer completeTodayNum) {
		this.completeTodayNum = completeTodayNum;
	}

	public Integer getWaitRecNum() {
		return waitRecNum;
	}

	public void setWaitRecNum(Integer waitRecNum) {
		this.waitRecNum = waitRecNum;
	}

}
