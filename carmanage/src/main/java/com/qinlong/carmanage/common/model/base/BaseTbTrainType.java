package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbTrainType<M extends BaseTbTrainType<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setTrainKind(java.lang.String trainKind) {
		set("train_kind", trainKind);
		return (M)this;
	}
	
	public java.lang.String getTrainKind() {
		return getStr("train_kind");
	}

	public M setTrainKindCode(java.lang.String trainKindCode) {
		set("train_kind_code", trainKindCode);
		return (M)this;
	}
	
	public java.lang.String getTrainKindCode() {
		return getStr("train_kind_code");
	}

	public M setTrainTypeCode(java.lang.String trainTypeCode) {
		set("train_type_code", trainTypeCode);
		return (M)this;
	}
	
	public java.lang.String getTrainTypeCode() {
		return getStr("train_type_code");
	}

	public M setSelfWeight(java.lang.Float selfWeight) {
		set("self_weight", selfWeight);
		return (M)this;
	}
	
	public java.lang.Float getSelfWeight() {
		return getFloat("self_weight");
	}

	public M setWeight(java.lang.Float weight) {
		set("weight", weight);
		return (M)this;
	}
	
	public java.lang.Float getWeight() {
		return getFloat("weight");
	}

	public M setVolume(java.lang.Integer volume) {
		set("volume", volume);
		return (M)this;
	}
	
	public java.lang.Integer getVolume() {
		return getInt("volume");
	}

	public M setLoadPrice(java.lang.Float loadPrice) {
		set("load_price", loadPrice);
		return (M)this;
	}
	
	public java.lang.Float getLoadPrice() {
		return getFloat("load_price");
	}

	public M setLength(java.lang.String length) {
		set("length", length);
		return (M)this;
	}
	
	public java.lang.String getLength() {
		return getStr("length");
	}

	public M setWidth(java.lang.String width) {
		set("width", width);
		return (M)this;
	}
	
	public java.lang.String getWidth() {
		return getStr("width");
	}

	public M setHight(java.lang.String hight) {
		set("hight", hight);
		return (M)this;
	}
	
	public java.lang.String getHight() {
		return getStr("hight");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

}