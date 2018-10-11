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
public class CargoSpecificteDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5775189053764587983L;
	private Integer id;
	//序列
	private Integer serial;
	
	/** 规格名*/
    private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
