// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.helpPojo;

import org.hibernate.validator.constraints.NotBlank;

import com.shenhesoft.logistics.manage.pojo.systemScene.TbSystemScene;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月15日
 */
public class SystemSceneDetail extends TbSystemScene{
	
	
	/**
	 * @description serialVersionUID
	*/
	private static final long serialVersionUID = 463484626203585112L;

	private String groupIds;
	
	private Integer projectId;
	
	@NotBlank
	private String projectCode;
	
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	
}
