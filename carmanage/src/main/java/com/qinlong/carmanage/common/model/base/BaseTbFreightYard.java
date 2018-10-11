package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbFreightYard<M extends BaseTbFreightYard<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setIsIsolated(java.lang.Integer isIsolated) {
		set("is_isolated", isIsolated);
		return (M)this;
	}
	
	public java.lang.Integer getIsIsolated() {
		return getInt("is_isolated");
	}

	public M setLinkman(java.lang.String linkman) {
		set("linkman", linkman);
		return (M)this;
	}
	
	public java.lang.String getLinkman() {
		return getStr("linkman");
	}

	public M setPhone(java.lang.String phone) {
		set("phone", phone);
		return (M)this;
	}
	
	public java.lang.String getPhone() {
		return getStr("phone");
	}

	public M setAddressCode(java.lang.String addressCode) {
		set("address_code", addressCode);
		return (M)this;
	}
	
	public java.lang.String getAddressCode() {
		return getStr("address_code");
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

	public M setFreightYardImg(java.lang.String freightYardImg) {
		set("freight_yard_img", freightYardImg);
		return (M)this;
	}
	
	public java.lang.String getFreightYardImg() {
		return getStr("freight_yard_img");
	}

	public M setTrainStationId(java.lang.Integer trainStationId) {
		set("train_station_id", trainStationId);
		return (M)this;
	}
	
	public java.lang.Integer getTrainStationId() {
		return getInt("train_station_id");
	}

	public M setDeleteFlag(java.lang.Integer deleteFlag) {
		set("delete_flag", deleteFlag);
		return (M)this;
	}
	
	public java.lang.Integer getDeleteFlag() {
		return getInt("delete_flag");
	}

}
