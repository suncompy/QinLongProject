// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.common;

/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2018年2月3日
 */
public class ParamForm {
  private int start;
  private int length;
  private String condition;
  private String sysOrgCode;
  public int getStart() {
    return start;
  }
  public void setStart(int start) {
    this.start = start;
  }

  public int getLength() {
    return length;
  }
  public void setLength(int length) {
    this.length = length;
  }
  public String getCondition() {
    return condition;
  }
  public void setCondition(String condition) {
    this.condition = condition;
  }
	public String getSysOrgCode() {
		return sysOrgCode;
	}
	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}
  
}
