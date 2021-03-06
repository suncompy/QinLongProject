package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbStock<M extends BaseTbStock<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setStationId(java.lang.Integer stationId) {
		set("station_id", stationId);
		return (M)this;
	}
	
	public java.lang.Integer getStationId() {
		return getInt("station_id");
	}

	public M setStationName(java.lang.String stationName) {
		set("station_name", stationName);
		return (M)this;
	}
	
	public java.lang.String getStationName() {
		return getStr("station_name");
	}

	public M setFreightYardId(java.lang.Integer freightYardId) {
		set("freight_yard_id", freightYardId);
		return (M)this;
	}
	
	public java.lang.Integer getFreightYardId() {
		return getInt("freight_yard_id");
	}

	public M setFreightYardName(java.lang.String freightYardName) {
		set("freight_yard_name", freightYardName);
		return (M)this;
	}
	
	public java.lang.String getFreightYardName() {
		return getStr("freight_yard_name");
	}

	public M setCargoLocationId(java.lang.Integer cargoLocationId) {
		set("cargo_location_id", cargoLocationId);
		return (M)this;
	}
	
	public java.lang.Integer getCargoLocationId() {
		return getInt("cargo_location_id");
	}

	public M setCargoLocationName(java.lang.String cargoLocationName) {
		set("cargo_location_name", cargoLocationName);
		return (M)this;
	}
	
	public java.lang.String getCargoLocationName() {
		return getStr("cargo_location_name");
	}

	public M setEnterQty(java.math.BigDecimal enterQty) {
		set("enter_qty", enterQty);
		return (M)this;
	}
	
	public java.math.BigDecimal getEnterQty() {
		return get("enter_qty");
	}

	public M setOutQty(java.math.BigDecimal outQty) {
		set("out_qty", outQty);
		return (M)this;
	}
	
	public java.math.BigDecimal getOutQty() {
		return get("out_qty");
	}

	public M setContainerNum(java.lang.Integer containerNum) {
		set("container_num", containerNum);
		return (M)this;
	}
	
	public java.lang.Integer getContainerNum() {
		return getInt("container_num");
	}

	public M setCurrentQty(java.math.BigDecimal currentQty) {
		set("current_qty", currentQty);
		return (M)this;
	}
	
	public java.math.BigDecimal getCurrentQty() {
		return get("current_qty");
	}

	public M setAdjustQty(java.math.BigDecimal adjustQty) {
		set("adjust_qty", adjustQty);
		return (M)this;
	}
	
	public java.math.BigDecimal getAdjustQty() {
		return get("adjust_qty");
	}

	public M setProjectId(java.lang.Integer projectId) {
		set("project_id", projectId);
		return (M)this;
	}
	
	public java.lang.Integer getProjectId() {
		return getInt("project_id");
	}

	public M setProjectCode(java.lang.String projectCode) {
		set("project_code", projectCode);
		return (M)this;
	}
	
	public java.lang.String getProjectCode() {
		return getStr("project_code");
	}

	public M setProjectType(java.lang.Integer projectType) {
		set("project_type", projectType);
		return (M)this;
	}
	
	public java.lang.Integer getProjectType() {
		return getInt("project_type");
	}

	public M setType(java.lang.Integer type) {
		set("type", type);
		return (M)this;
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public M setAdjustDate(java.util.Date adjustDate) {
		set("adjust_date", adjustDate);
		return (M)this;
	}
	
	public java.util.Date getAdjustDate() {
		return get("adjust_date");
	}

}
