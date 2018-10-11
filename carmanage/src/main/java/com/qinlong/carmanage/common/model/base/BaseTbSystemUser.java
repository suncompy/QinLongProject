package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbSystemUser<M extends BaseTbSystemUser<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("create_date");
	}

	public M setAccount(java.lang.String account) {
		set("account", account);
		return (M)this;
	}
	
	public java.lang.String getAccount() {
		return getStr("account");
	}

	public M setPasswd(java.lang.String passwd) {
		set("passwd", passwd);
		return (M)this;
	}
	
	public java.lang.String getPasswd() {
		return getStr("passwd");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setAge(java.lang.Integer age) {
		set("age", age);
		return (M)this;
	}
	
	public java.lang.Integer getAge() {
		return getInt("age");
	}

	public M setSex(java.lang.Integer sex) {
		set("sex", sex);
		return (M)this;
	}
	
	public java.lang.Integer getSex() {
		return getInt("sex");
	}

	public M setUserIcon(java.lang.String userIcon) {
		set("user_icon", userIcon);
		return (M)this;
	}
	
	public java.lang.String getUserIcon() {
		return getStr("user_icon");
	}

	public M setIsMarry(java.lang.Integer isMarry) {
		set("is_marry", isMarry);
		return (M)this;
	}
	
	public java.lang.Integer getIsMarry() {
		return getInt("is_marry");
	}

	public M setEducation(java.lang.Integer education) {
		set("education", education);
		return (M)this;
	}
	
	public java.lang.Integer getEducation() {
		return getInt("education");
	}

	public M setPhone(java.lang.String phone) {
		set("phone", phone);
		return (M)this;
	}
	
	public java.lang.String getPhone() {
		return getStr("phone");
	}

	public M setBranchGroupId(java.lang.Integer branchGroupId) {
		set("branch_group_id", branchGroupId);
		return (M)this;
	}
	
	public java.lang.Integer getBranchGroupId() {
		return getInt("branch_group_id");
	}

	public M setEmail(java.lang.String email) {
		set("email", email);
		return (M)this;
	}
	
	public java.lang.String getEmail() {
		return getStr("email");
	}

	public M setAreaCode(java.lang.String areaCode) {
		set("area_code", areaCode);
		return (M)this;
	}
	
	public java.lang.String getAreaCode() {
		return getStr("area_code");
	}

	public M setAddress(java.lang.String address) {
		set("address", address);
		return (M)this;
	}
	
	public java.lang.String getAddress() {
		return getStr("address");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

	public M setWorkStatus(java.lang.Integer workStatus) {
		set("work_status", workStatus);
		return (M)this;
	}
	
	public java.lang.Integer getWorkStatus() {
		return getInt("work_status");
	}

	public M setIdcard(java.lang.String idcard) {
		set("idcard", idcard);
		return (M)this;
	}
	
	public java.lang.String getIdcard() {
		return getStr("idcard");
	}

	public M setStartWorkDate(java.util.Date startWorkDate) {
		set("start_work_date", startWorkDate);
		return (M)this;
	}
	
	public java.util.Date getStartWorkDate() {
		return get("start_work_date");
	}

	public M setLeaveOfficeDate(java.util.Date leaveOfficeDate) {
		set("leave_office_date", leaveOfficeDate);
		return (M)this;
	}
	
	public java.util.Date getLeaveOfficeDate() {
		return get("leave_office_date");
	}

	public M setLastLoginDate(java.util.Date lastLoginDate) {
		set("last_login_date", lastLoginDate);
		return (M)this;
	}
	
	public java.util.Date getLastLoginDate() {
		return get("last_login_date");
	}

	public M setCompanyId(java.lang.Integer companyId) {
		set("company_id", companyId);
		return (M)this;
	}
	
	public java.lang.Integer getCompanyId() {
		return getInt("company_id");
	}

	public M setCheckedCode(java.lang.String checkedCode) {
		set("checked_code", checkedCode);
		return (M)this;
	}
	
	public java.lang.String getCheckedCode() {
		return getStr("checked_code");
	}

	public M setCheckedCodeDate(java.util.Date checkedCodeDate) {
		set("checked_code_date", checkedCodeDate);
		return (M)this;
	}
	
	public java.util.Date getCheckedCodeDate() {
		return get("checked_code_date");
	}

}