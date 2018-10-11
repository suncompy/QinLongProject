// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月18日
 */
public class DistributeDetail implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1839632790320749440L;

	private Integer projectId;

	private byte projectType;

	@NotNull
	private Integer num;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public byte getProjectType() {
		return projectType;
	}

	public void setProjectType(byte projectType) {
		this.projectType = projectType;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
