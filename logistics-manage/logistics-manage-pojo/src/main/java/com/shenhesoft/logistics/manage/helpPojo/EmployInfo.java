package com.shenhesoft.logistics.manage.helpPojo;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description:员工信息表
 * 
 * @author shilvfei
 * 
 * @date 2017年12月11日
 */
public class EmployInfo extends TbSystemUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7063942055684281763L;
	
	private Integer id;
	private String  roleId;
	private String  roleName;
	private Integer  groupId;
	private String  groupName;
	@NotBlank(message="请选择省市区")
	private String  province;//省
	@NotBlank(message="请选择省市区")
	private String  city;//市
	private String  district;//区
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
