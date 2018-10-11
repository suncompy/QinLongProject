package com.shenhesoft.logistics.manage.search;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 员工信息搜索条件
 * 
 * @author shilvfei
 * 
 * @date 2018年1月4日
 */
public class TbSystemSearch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8216726045150322880L;

	private String name;
	
	private Byte sex;
	
	private Byte isMarry;
	
	private Byte education;
	
	private Integer roleId;
	
	private String phone;
	
	private Integer branchGroupId;
	
	private String idcard;
	
	private String beginDate;
	
	private String endDate;
	
	private Byte workStatus;

	public Byte getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Byte workStatus) {
		this.workStatus = workStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Byte getIsMarry() {
		return isMarry;
	}

	public void setIsMarry(Byte isMarry) {
		this.isMarry = isMarry;
	}

	public Byte getEducation() {
		return education;
	}

	public void setEducation(Byte education) {
		this.education = education;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getBranchGroupId() {
		return branchGroupId;
	}

	public void setBranchGroupId(Integer branchGroupId) {
		this.branchGroupId = branchGroupId;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
