package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbPlanMange<M extends BaseTbPlanMange<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setLklsId(java.lang.Long lklsId) {
		set("lkls_id", lklsId);
		return (M)this;
	}
	
	public java.lang.Long getLklsId() {
		return getLong("lkls_id");
	}

	public M setPlanName(java.lang.String planName) {
		set("plan_name", planName);
		return (M)this;
	}
	
	public java.lang.String getPlanName() {
		return getStr("plan_name");
	}

	public M setRemarks(java.lang.String remarks) {
		set("remarks", remarks);
		return (M)this;
	}
	
	public java.lang.String getRemarks() {
		return getStr("remarks");
	}

}