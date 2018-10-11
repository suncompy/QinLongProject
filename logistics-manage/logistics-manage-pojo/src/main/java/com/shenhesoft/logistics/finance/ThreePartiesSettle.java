package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;
import java.util.Date;

public class ThreePartiesSettle {
    /** */
    private String id;

    private String custPackId;
    
    /** 对账id*/
    private String threePartiesReceivablesId;

    /** 存入账户id*/
    private Integer receiveAccountId;

    /** 支出账户id*/
    private Integer payAccountId;

    /** */
    private BigDecimal settleMoney;

    /** */
    private String settleStatus;

    /** */
    private Date createDate;

    /** */
    private String createBy;

    /** */
    private String auitBy;

    /** */
    private Date auitDate;

    /** 删除标记*/
    private String delFlag;

    
    public String getCustPackId() {
		return custPackId;
	}

	public void setCustPackId(String custPackId) {
		this.custPackId = custPackId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    public String getThreePartiesReceivablesId() {
		return threePartiesReceivablesId;
	}

	public void setThreePartiesReceivablesId(String threePartiesReceivablesId) {
		this.threePartiesReceivablesId = threePartiesReceivablesId;
	}

	public Integer getReceiveAccountId() {
        return receiveAccountId;
    }

    public void setReceiveAccountId(Integer receiveAccountId) {
        this.receiveAccountId = receiveAccountId;
    }

    public Integer getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Integer payAccountId) {
        this.payAccountId = payAccountId;
    }

    public BigDecimal getSettleMoney() {
		return settleMoney;
	}

	public void setSettleMoney(BigDecimal settleMoney) {
		this.settleMoney = settleMoney;
	}

	public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus == null ? null : settleStatus.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getAuitBy() {
        return auitBy;
    }

    public void setAuitBy(String auitBy) {
        this.auitBy = auitBy == null ? null : auitBy.trim();
    }

    public Date getAuitDate() {
        return auitDate;
    }

    public void setAuitDate(Date auitDate) {
        this.auitDate = auitDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

}