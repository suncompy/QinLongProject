package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 油气卡信息表-Form.
 * <p>
 * <a href="MaterialManagement.java"><i>View Source</i></a>
 * </p>
 * 
 * @author JiangYS
 * @date 2018-02-07
 * @version 1.0.0
 * @since 1.0.0
 */
public class Material {

	// 主键
	private String id;
	// 销售单位
	private String salesUnit;
	// 分支机构
	private Integer branchGroupId;
	// 货物名称
	private String materialName;
	// 规格型号
	private String materialType;
	// 单位
	private String materialUnit;
	// 购入数量
	private Integer materialNum;
	// 单价
	private BigDecimal materialUnitPrice;
	// 总金额
	private BigDecimal totalMoney;
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
	// 已使用数量
	private Integer usedNum;
	//流水号
	private String serialNumber;
	//账户
	private Integer accountId;

	// 无参构造
	public Material() {
		super();
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

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialUnit() {
		return materialUnit;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	public Integer getMaterialNum() {
		return materialNum;
	}

	public void setMaterialNum(Integer materialNum) {
		this.materialNum = materialNum;
	}

	public BigDecimal getMaterialUnitPrice() {
		return materialUnitPrice;
	}

	public void setMaterialUnitPrice(BigDecimal materialUnitPrice) {
		this.materialUnitPrice = materialUnitPrice;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
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

	public Integer getUsedNum() {
		return usedNum;
	}

	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}


}