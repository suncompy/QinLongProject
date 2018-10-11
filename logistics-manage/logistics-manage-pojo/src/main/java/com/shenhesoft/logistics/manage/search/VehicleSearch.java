package com.shenhesoft.logistics.manage.search;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 车辆授权查询条件
 * @author liangdeng
 *
 */
public class VehicleSearch implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6635434594884070064L;

	/** 挂靠方**/
	private String userName;
	
	/** 类型 **/
	private Byte userType;
	
	/** 联系方式 **/
	private String phone;
	
	/**
	 * 时间段:起始时间 
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date beginDate;
	
	/**
	 * 时间段:终止时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date endDate; 
	
	/** 公司id **/
	private Integer companyId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Byte getUserType() {
		return userType;
	}

	public void setUserType(Byte userType) {
		this.userType = userType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
}
