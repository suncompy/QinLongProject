// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.helpPojo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.shenhesoft.logistics.manage.pojo.cargo.TbCargo;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月5日
 */
public class TbCargoInfomation extends TbCargo implements Serializable{

    /**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月14日
	 * @param 
	 * @return
	*/
	private static final long serialVersionUID = 3316598868987181981L;

	//指标序列化
	@NotBlank
    private String pointJsonIds;

    //规格序列化
	@NotBlank
    private String specJsonIds;

	public String getPointJsonIds() {
		return pointJsonIds;
	}

	public void setPointJsonIds(String pointJsonIds) {
		this.pointJsonIds = pointJsonIds;
	}

	public String getSpecJsonIds() {
		return specJsonIds;
	}

	public void setSpecJsonIds(String specJsonIds) {
		this.specJsonIds = specJsonIds;
	}

}
