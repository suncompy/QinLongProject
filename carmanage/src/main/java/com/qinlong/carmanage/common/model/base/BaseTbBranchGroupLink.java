package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbBranchGroupLink<M extends BaseTbBranchGroupLink<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setRowId(java.lang.String rowId) {
		set("row_id", rowId);
		return (M)this;
	}
	
	public java.lang.String getRowId() {
		return getStr("row_id");
	}

	public M setTabName(java.lang.String tabName) {
		set("tab_name", tabName);
		return (M)this;
	}
	
	public java.lang.String getTabName() {
		return getStr("tab_name");
	}

	public M setTabComment(java.lang.String tabComment) {
		set("tab_comment", tabComment);
		return (M)this;
	}
	
	public java.lang.String getTabComment() {
		return getStr("tab_comment");
	}

	public M setSysOrgCode(java.lang.String sysOrgCode) {
		set("sys_org_code", sysOrgCode);
		return (M)this;
	}
	
	public java.lang.String getSysOrgCode() {
		return getStr("sys_org_code");
	}

}
