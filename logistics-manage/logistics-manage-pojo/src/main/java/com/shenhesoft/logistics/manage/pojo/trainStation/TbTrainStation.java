package com.shenhesoft.logistics.manage.pojo.trainStation;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description 站点信息
 * @author shilvfei
 * @date 2017年12月23日
 * @param
 * @return
 */
public class TbTrainStation implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6969448839940620679L;

	/** */
	private Integer id;

	/** 站点名称 */
	private String stationName;

	/**
	 * 站点级别 0:铁路局 1:货运总局 2:营业厅
	 */
	private Byte stationLevel;

	/** 负责人 */
	private String responsibler;

	/** 企业地址 */
	private String adressCode;

	/** 企业详细地址 */
	private String detailAddress;

	/** 站点联系人 */
	private String stationContacts;

	/** 部门 */
	private String department;

	/** 站点联系方式 */
	private String stationPhone;

	/** 传真 */
	private String stationFax;

	/** 邮箱 */
	private String email;

	/** 银行账户 */
	private String bankAccount;

	/** 户名 */
	private String accountName;

	/** 开户行 */
	private String openBank;

	/** 行号 */
	private String openBankNum;

	/** 税号 */
	private String dutyParagraph;

	/** 余额 */
	private BigDecimal bankLastAmount;

	private byte deleteFlag;

	/** 上级站点id */
	private Integer parentId;

	/** 上级站点名称 */
	private String parentName;
	
	private String relationPlace;
	
	public String getRelationPlace() {
		return relationPlace;
	}

	public void setRelationPlace(String relationPlace) {
		this.relationPlace = relationPlace;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public BigDecimal getBankLastAmount() {
		return bankLastAmount;
	}

	public void setBankLastAmount(BigDecimal bankLastAmount) {
		this.bankLastAmount = bankLastAmount;
	}

	public byte getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName == null ? null : stationName.trim();
	}

	public Byte getStationLevel() {
		return stationLevel;
	}

	public void setStationLevel(Byte stationLevel) {
		this.stationLevel = stationLevel;
	}

	public String getResponsibler() {
		return responsibler;
	}

	public void setResponsibler(String responsibler) {
		this.responsibler = responsibler == null ? null : responsibler.trim();
	}

	public String getAdressCode() {
		return adressCode;
	}

	public void setAdressCode(String adressCode) {
		this.adressCode = adressCode == null ? null : adressCode.trim();
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress == null ? null : detailAddress.trim();
	}

	public String getStationContacts() {
		return stationContacts;
	}

	public void setStationContacts(String stationContacts) {
		this.stationContacts = stationContacts == null ? null : stationContacts.trim();
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department == null ? null : department.trim();
	}

	public String getStationPhone() {
		return stationPhone;
	}

	public void setStationPhone(String stationPhone) {
		this.stationPhone = stationPhone == null ? null : stationPhone.trim();
	}

	public String getStationFax() {
		return stationFax;
	}

	public void setStationFax(String stationFax) {
		this.stationFax = stationFax == null ? null : stationFax.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount == null ? null : bankAccount.trim();
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName == null ? null : accountName.trim();
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank == null ? null : openBank.trim();
	}

	public String getOpenBankNum() {
		return openBankNum;
	}

	public void setOpenBankNum(String openBankNum) {
		this.openBankNum = openBankNum == null ? null : openBankNum.trim();
	}

	public String getDutyParagraph() {
		return dutyParagraph;
	}

	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph == null ? null : dutyParagraph.trim();
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}