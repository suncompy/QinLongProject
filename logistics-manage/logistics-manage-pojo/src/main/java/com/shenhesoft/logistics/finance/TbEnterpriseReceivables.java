package com.shenhesoft.logistics.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TbEnterpriseReceivables implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1407415575993649308L;

	/** 企业应收款结算id*/
    private String enterpriseReceivablesId;

    /** */
    private String costPackId;

    /** 结算金额*/
    private BigDecimal settleMoney;

    /** 审核状态 0 未审核 1 已审核*/
    private Integer auditStatus;

    /** 收款账户*/
    private String receiveAccount;

    /** */
    private String settleUserId;

    /** */
    private Date settleDate;

    /** */
    private Date createDate;

    /** 审核人*/
    private String auditUserId;

    /** 审核时间*/
    private Date auditDate;

    /** 网点分支id*/
    private Integer branchId;

    /** */
    private Integer delFlag;

    public String getEnterpriseReceivablesId() {
        return enterpriseReceivablesId;
    }

    public void setEnterpriseReceivablesId(String enterpriseReceivablesId) {
        this.enterpriseReceivablesId = enterpriseReceivablesId == null ? null : enterpriseReceivablesId.trim();
    }

    public String getCostPackId() {
        return costPackId;
    }

    public void setCostPackId(String costPackId) {
        this.costPackId = costPackId == null ? null : costPackId.trim();
    }

    public BigDecimal getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(BigDecimal settleMoney) {
        this.settleMoney = settleMoney;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount == null ? null : receiveAccount.trim();
    }

    public String getSettleUserId() {
        return settleUserId;
    }

    public void setSettleUserId(String settleUserId) {
        this.settleUserId = settleUserId == null ? null : settleUserId.trim();
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId == null ? null : auditUserId.trim();
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}