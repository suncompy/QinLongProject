package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbTrainStation<M extends BaseTbTrainStation<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setStationName(java.lang.String stationName) {
		set("station_name", stationName);
		return (M)this;
	}
	
	public java.lang.String getStationName() {
		return getStr("station_name");
	}

	public M setStationLevel(java.lang.Integer stationLevel) {
		set("station_level", stationLevel);
		return (M)this;
	}
	
	public java.lang.Integer getStationLevel() {
		return getInt("station_level");
	}

	public M setParentId(java.lang.Integer parentId) {
		set("parent_id", parentId);
		return (M)this;
	}
	
	public java.lang.Integer getParentId() {
		return getInt("parent_id");
	}

	public M setResponsibler(java.lang.String responsibler) {
		set("responsibler", responsibler);
		return (M)this;
	}
	
	public java.lang.String getResponsibler() {
		return getStr("responsibler");
	}

	public M setAdressCode(java.lang.String adressCode) {
		set("adress_code", adressCode);
		return (M)this;
	}
	
	public java.lang.String getAdressCode() {
		return getStr("adress_code");
	}

	public M setDetailAddress(java.lang.String detailAddress) {
		set("detail_address", detailAddress);
		return (M)this;
	}
	
	public java.lang.String getDetailAddress() {
		return getStr("detail_address");
	}

	public M setStationContacts(java.lang.String stationContacts) {
		set("station_contacts", stationContacts);
		return (M)this;
	}
	
	public java.lang.String getStationContacts() {
		return getStr("station_contacts");
	}

	public M setDepartment(java.lang.String department) {
		set("department", department);
		return (M)this;
	}
	
	public java.lang.String getDepartment() {
		return getStr("department");
	}

	public M setStationPhone(java.lang.String stationPhone) {
		set("station_phone", stationPhone);
		return (M)this;
	}
	
	public java.lang.String getStationPhone() {
		return getStr("station_phone");
	}

	public M setStationFax(java.lang.String stationFax) {
		set("station_fax", stationFax);
		return (M)this;
	}
	
	public java.lang.String getStationFax() {
		return getStr("station_fax");
	}

	public M setEmail(java.lang.String email) {
		set("email", email);
		return (M)this;
	}
	
	public java.lang.String getEmail() {
		return getStr("email");
	}

	public M setBankAccount(java.lang.String bankAccount) {
		set("bank_account", bankAccount);
		return (M)this;
	}
	
	public java.lang.String getBankAccount() {
		return getStr("bank_account");
	}

	public M setAccountName(java.lang.String accountName) {
		set("account_name", accountName);
		return (M)this;
	}
	
	public java.lang.String getAccountName() {
		return getStr("account_name");
	}

	public M setOpenBank(java.lang.String openBank) {
		set("open_bank", openBank);
		return (M)this;
	}
	
	public java.lang.String getOpenBank() {
		return getStr("open_bank");
	}

	public M setOpenBankNum(java.lang.String openBankNum) {
		set("open_bank_num", openBankNum);
		return (M)this;
	}
	
	public java.lang.String getOpenBankNum() {
		return getStr("open_bank_num");
	}

	public M setDutyParagraph(java.lang.String dutyParagraph) {
		set("duty_paragraph", dutyParagraph);
		return (M)this;
	}
	
	public java.lang.String getDutyParagraph() {
		return getStr("duty_paragraph");
	}

	public M setBankLastAmount(java.math.BigDecimal bankLastAmount) {
		set("bank_last_amount", bankLastAmount);
		return (M)this;
	}
	
	public java.math.BigDecimal getBankLastAmount() {
		return get("bank_last_amount");
	}

	public M setDeleteFlag(java.lang.Integer deleteFlag) {
		set("delete_flag", deleteFlag);
		return (M)this;
	}
	
	public java.lang.Integer getDeleteFlag() {
		return getInt("delete_flag");
	}

	public M setRelationPlace(java.lang.String relationPlace) {
		set("relation_place", relationPlace);
		return (M)this;
	}
	
	public java.lang.String getRelationPlace() {
		return getStr("relation_place");
	}

}
