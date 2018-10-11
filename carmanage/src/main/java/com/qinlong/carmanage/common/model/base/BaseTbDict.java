package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbDict<M extends BaseTbDict<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setDicCode(java.lang.String dicCode) {
		set("dic_code", dicCode);
		return (M)this;
	}
	
	public java.lang.String getDicCode() {
		return getStr("dic_code");
	}

	public M setDicName(java.lang.String dicName) {
		set("dic_name", dicName);
		return (M)this;
	}
	
	public java.lang.String getDicName() {
		return getStr("dic_name");
	}

}