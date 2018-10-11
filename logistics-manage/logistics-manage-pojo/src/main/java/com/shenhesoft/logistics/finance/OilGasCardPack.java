package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 油气卡信息表-Form.
 * <p>
 * <a href="OilGasCardPack.java"><i>View Source</i></a>
 * </p>
 * 
 * @author Jys
 * @date 2018-01-26
 * @version 1.0.0
 * @since 1.0.0
 */
public class OilGasCardPack {

	// 主键
	private String id;
	// 销售单位
	private String salesUnit;
	// 分支机构
	private Integer branchGroupId;
	// 采购品类(0油卡 1气卡)
	private Integer cardType;
	// 单位
	private String cardUnit;
	// 采购人
	private Integer purchaseId;
	// 采购时间
	private String purchaseDate;
	// 审核人
	private Integer auditId;
	// 审核时间
	private String auditDate;
	// 审核状态(0待审核 1:审核通过 2:审核不通过)
	private Integer auditStatus;
	//采购日期(精确到日期)
	private String purchaseDateD;
	//公司账户
	private String accountId;

	// 无参构造
	public OilGasCardPack() {
		super();
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getSalesUnit() {
		return salesUnit;
	}

	public void setSalesUnit(String salesUnit) {
		this.salesUnit = salesUnit;
	}

	public Integer getBranchGroupId() {
		return branchGroupId;
	}

	public void setBranchGroupId(Integer branchGroupId) {
		this.branchGroupId = branchGroupId;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getCardUnit() {
		return cardUnit;
	}

	public void setCardUnit(String cardUnit) {
		this.cardUnit = cardUnit;
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Integer getAuditId() {
		return auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getPurchaseDateD() {
		return purchaseDateD;
	}

	public void setPurchaseDateD(String purchaseDateD) {
		this.purchaseDateD = purchaseDateD;
	}

}