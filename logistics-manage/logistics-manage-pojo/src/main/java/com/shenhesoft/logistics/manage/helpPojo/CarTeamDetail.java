// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.helpPojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @description 挂靠车队
 *
 * @author LiangLin
 *
 * @date 2017年12月5日
 */
public class CarTeamDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3612652729702313366L;

	private Integer recordId;

	private Integer anchoredId;

	private Integer userId;

	// 被挂靠方
	private String name;

	// 挂靠方
	private String userName;

	// 0 个人 1车队
	private String type;

	//
	private byte status;

	private String changerName;

	private String phone;

	private String reason;

	// 挂靠时间
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date anchoredDate;

	// 申请时间
	private String applydate;

	// 操作人
	private String operateName;

	//条件查询
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

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApplydate() {
		return applydate;
	}

	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}

	public Integer getAnchoredId() {
		return anchoredId;
	}

	public void setAnchoredId(Integer anchoredId) {
		this.anchoredId = anchoredId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Date getAnchoredDate() {
		return anchoredDate;
	}

	public void setAnchoredDate(Date anchoredDate) {
		this.anchoredDate = anchoredDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getChangerName() {
		return changerName;
	}

	public void setChangerName(String changerName) {
		this.changerName = changerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
