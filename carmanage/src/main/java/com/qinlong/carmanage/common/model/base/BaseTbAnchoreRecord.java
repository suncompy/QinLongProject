package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbAnchoreRecord<M extends BaseTbAnchoreRecord<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setAnchoredId(java.lang.Integer anchoredId) {
		set("anchored_id", anchoredId);
		return (M)this;
	}
	
	public java.lang.Integer getAnchoredId() {
		return getInt("anchored_id");
	}

	public M setAnchoredName(java.lang.String anchoredName) {
		set("anchored_name", anchoredName);
		return (M)this;
	}
	
	public java.lang.String getAnchoredName() {
		return getStr("anchored_name");
	}

	public M setAnchoredPhone(java.lang.String anchoredPhone) {
		set("anchored_phone", anchoredPhone);
		return (M)this;
	}
	
	public java.lang.String getAnchoredPhone() {
		return getStr("anchored_phone");
	}

	public M setAnchoredDate(java.util.Date anchoredDate) {
		set("anchored_date", anchoredDate);
		return (M)this;
	}
	
	public java.util.Date getAnchoredDate() {
		return get("anchored_date");
	}

	public M setUserId(java.lang.Integer userId) {
		set("user_id", userId);
		return (M)this;
	}
	
	public java.lang.Integer getUserId() {
		return getInt("user_id");
	}

	public M setUserName(java.lang.String userName) {
		set("user_name", userName);
		return (M)this;
	}
	
	public java.lang.String getUserName() {
		return getStr("user_name");
	}

	public M setStatus(java.lang.Integer status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public M setAnchoredReason(java.lang.String anchoredReason) {
		set("anchored_reason", anchoredReason);
		return (M)this;
	}
	
	public java.lang.String getAnchoredReason() {
		return getStr("anchored_reason");
	}

	public M setType(java.lang.String type) {
		set("type", type);
		return (M)this;
	}
	
	public java.lang.String getType() {
		return getStr("type");
	}

	public M setOperator(java.lang.Integer operator) {
		set("operator", operator);
		return (M)this;
	}
	
	public java.lang.Integer getOperator() {
		return getInt("operator");
	}

	public M setDeleteFlag(java.lang.Integer deleteFlag) {
		set("delete_flag", deleteFlag);
		return (M)this;
	}
	
	public java.lang.Integer getDeleteFlag() {
		return getInt("delete_flag");
	}

}
