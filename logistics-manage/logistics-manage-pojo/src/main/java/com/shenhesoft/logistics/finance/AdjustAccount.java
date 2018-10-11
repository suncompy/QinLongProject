package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;
import java.util.Date;

public class AdjustAccount {
    /** */
    private String adjustAccountId;

    /** 流水号*/
    private String adjustCode;

    /** 支出账户id*/
    private Integer expenditureAccountId;

    /** 存入账户id*/
    private Integer depositAccountId;

    /** 调整金额*/
    private BigDecimal adjustMoney;

    /** 支付方式 1 现金 2 支票 3 网银*/
    private Integer payment;

    /** 创建者*/
    private String createBy;

    /** 创建时间*/
    private Date createDate;

    /** 更新者*/
    private String updateBy;

    /** 更新时间*/
    private Date updateDate;

    /** 备注信息*/
    private String remarks;

    /** 删除标记*/
    private String delFlag;

    public String getAdjustAccountId() {
        return adjustAccountId;
    }

    public void setAdjustAccountId(String adjustAccountId) {
        this.adjustAccountId = adjustAccountId == null ? null : adjustAccountId.trim();
    }

    public String getAdjustCode() {
        return adjustCode;
    }

    public void setAdjustCode(String adjustCode) {
        this.adjustCode = adjustCode == null ? null : adjustCode.trim();
    }

    public Integer getExpenditureAccountId() {
        return expenditureAccountId;
    }

    public void setExpenditureAccountId(Integer expenditureAccountId) {
        this.expenditureAccountId = expenditureAccountId;
    }

    public Integer getDepositAccountId() {
        return depositAccountId;
    }

    public void setDepositAccountId(Integer depositAccountId) {
        this.depositAccountId = depositAccountId;
    }

    public BigDecimal getAdjustMoney() {
        return adjustMoney;
    }

    public void setAdjustMoney(BigDecimal adjustMoney) {
        this.adjustMoney = adjustMoney;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}