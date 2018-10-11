package com.shenhesoft.logistics.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TbDotAccountPack implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1790077085652036075L;
	/** 打包id */
	private String dotAccountPackId;

	/** 项目编号 */
	private String projectCode;

	/** 分支机构 */
	private String branchName;

	/** 交账时间(创建时间) */
	private Date createDate;

	/** 总单数 */
	private Integer packTruckNum;

	/** 总金额 */
	private BigDecimal suppliesAmount;

	/** 状态 0 未审核 1已审核 */
	private Integer status;

	/** 交账人 */
	private Integer createUserId;

	/** 审核人id */
	private Integer auditId;

	/** 审核时间 */
	private Date auditDate;

	private String shPackIds;
	
	
	public String getDotAccountPackId() {
		return dotAccountPackId;
	}

	public void setDotAccountPackId(String dotAccountPackId) {
		this.dotAccountPackId = dotAccountPackId == null ? null : dotAccountPackId.trim();
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode == null ? null : projectCode.trim();
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName == null ? null : branchName.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getPackTruckNum() {
		return packTruckNum;
	}

	public void setPackTruckNum(Integer packTruckNum) {
		this.packTruckNum = packTruckNum;
	}

	public BigDecimal getSuppliesAmount() {
		return suppliesAmount;
	}

	public void setSuppliesAmount(BigDecimal suppliesAmount) {
		this.suppliesAmount = suppliesAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getAuditId() {
		return auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getShPackIds() {
		return shPackIds;
	}

	public void setShPackIds(String shPackIds) {
		this.shPackIds = shPackIds;
	}
	
}