package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbProjectOperationLog<M extends BaseTbProjectOperationLog<M>> extends Model<M> implements IBean {

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

	public M setOperatorId(java.lang.Integer operatorId) {
		set("operator_id", operatorId);
		return (M)this;
	}
	
	public java.lang.Integer getOperatorId() {
		return getInt("operator_id");
	}

	public M setContent(java.lang.String content) {
		set("content", content);
		return (M)this;
	}
	
	public java.lang.String getContent() {
		return getStr("content");
	}

	public M setProjectId(java.lang.Integer projectId) {
		set("project_id", projectId);
		return (M)this;
	}
	
	public java.lang.Integer getProjectId() {
		return getInt("project_id");
	}

	public M setType(java.lang.Integer type) {
		set("type", type);
		return (M)this;
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public M setOperatorName(java.lang.String operatorName) {
		set("operator_name", operatorName);
		return (M)this;
	}
	
	public java.lang.String getOperatorName() {
		return getStr("operator_name");
	}

	public M setProjectCode(java.lang.String projectCode) {
		set("project_code", projectCode);
		return (M)this;
	}
	
	public java.lang.String getProjectCode() {
		return getStr("project_code");
	}

}