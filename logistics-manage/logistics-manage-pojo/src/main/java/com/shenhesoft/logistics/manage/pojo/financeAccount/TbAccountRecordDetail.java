package com.shenhesoft.logistics.manage.pojo.financeAccount;

import java.io.Serializable;
import java.math.BigDecimal;

public class TbAccountRecordDetail implements Serializable{

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2018年4月25日
	 * @param 
	 * @return
	*/
	private static final long serialVersionUID = 5210986330546504721L;

	private String id;
	
	private Integer projectId;
	
	private String projectCode;
	
	private Integer accountId;
	
	private BigDecimal amount;
	
	private Integer type;
	
	private Byte deleteFlag;

	private BigDecimal blance;
	
	private BigDecimal depositAmount;
	
	private BigDecimal purposeAmount;
	
	private BigDecimal cashAmount;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Byte getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public BigDecimal getBlance() {
		return blance;
	}

	public void setBlance(BigDecimal blance) {
		this.blance = blance;
	}

	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}

	public BigDecimal getPurposeAmount() {
		return purposeAmount;
	}

	public void setPurposeAmount(BigDecimal purposeAmount) {
		this.purposeAmount = purposeAmount;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}
	
}
