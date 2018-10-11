package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CostPack {
    /** 主键*/
    private String shPackId;

    /** */
    private Integer projectId;

    private String costPackCode;
    
    /** 对账状态(0-对账待审核 1-对账财务审核通过 2-对账财务审核不通过 3-已解包 )',*/
    private Byte checkingStatus;

    /** 结算状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)',*/
    private Byte cashSettleStatus;

    /**
     * 发票状态(0-新建 1-已登入 2-发票作废待审核 3-已作废 4-作废审核不通过)
     */
    private Byte invoiceStatus;
    
    /** 发货皮重*/
    private BigDecimal sendTare;

    /** */
    private BigDecimal sendGross;

    /** */
    private BigDecimal sendNet;

    /** */
    private BigDecimal cargoPrice;

    /** 货物单价*/
    private BigDecimal cargoUnitPrice;

    /** 现金支付方式(0-现金 1-转账 2-支票)*/
    private Byte cashPayType;

    /** 备注*/
    private String remark;

    /** */
    private Byte deleteFlag;

    /** */
    private Date createDate;

    /** */
    private Integer createUserId;

    /** */
    private Date modifiyDate;

    /** */
    private Date checkingAuditDate;

    /** */
    private Date settleAuditDate;

    /** */
    private Integer auditId;
    
    private String costOrderFinIds;
    
    /**
     * 打包车次
     */
    private Integer packTruckNum;
    
    private Integer backId;
    
    private Date backDate;

    /** 应开总税额*/
    private BigDecimal taxMoney;

    /** 待结算金额*/
    private BigDecimal besettledMoney;

    /** 已结算金额*/
    private BigDecimal settledMoney;

    /** 本次结算金额*/
    private BigDecimal settleMoney;

    /** 结算方式(0:现金,1:支票,2:转账)*/
    private Integer settleType;

    /** 结算时间*/
    private Date settleDate;

    /** 结算人*/
    private String settleId;

    /** 结算审核人*/
    private Integer settleAuditUid;
    
    /** 是否为历史记录(0:不是,1:是)*/
    private Integer isHistroy;
    
    /** 收款账户 */
    private Integer receiveAccountId;
    @NotNull(message="税率不能为空")
    @Min(value=0, message="税率必须大于0！")     
    @Max(value=100,message="税率不能大于100!")
    private BigDecimal taxRate;
    
    public Integer getReceiveAccountId() {
		return receiveAccountId;
	}

	public void setReceiveAccountId(Integer receiveAccountId) {
		this.receiveAccountId = receiveAccountId;
	}

	private BigDecimal totalMoney;
    
    public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getSettleAuditUid() {
		return settleAuditUid;
	}

	public void setSettleAuditUid(Integer settleAuditUid) {
		this.settleAuditUid = settleAuditUid;
	}

	public BigDecimal getTaxMoney() {
		return taxMoney;
	}

	public void setTaxMoney(BigDecimal taxMoney) {
		this.taxMoney = taxMoney;
	}

	public BigDecimal getBesettledMoney() {
		return besettledMoney;
	}

	public void setBesettledMoney(BigDecimal besettledMoney) {
		this.besettledMoney = besettledMoney;
	}

	public BigDecimal getSettledMoney() {
		return settledMoney;
	}

	public void setSettledMoney(BigDecimal settledMoney) {
		this.settledMoney = settledMoney;
	}

	public BigDecimal getSettleMoney() {
		return settleMoney;
	}

	public void setSettleMoney(BigDecimal settleMoney) {
		this.settleMoney = settleMoney;
	}

	public Integer getSettleType() {
		return settleType;
	}

	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public String getSettleId() {
		return settleId;
	}

	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}

	public Integer getIsHistroy() {
		return isHistroy;
	}

	public void setIsHistroy(Integer isHistroy) {
		this.isHistroy = isHistroy;
	}

	public Integer getBackId() {
		return backId;
	}

	public void setBackId(Integer backId) {
		this.backId = backId;
	}

	public Date getBackDate() {
		return backDate;
	}

	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	public Byte getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(Byte invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getCostPackCode() {
		return costPackCode;
	}

	public void setCostPackCode(String costPackCode) {
		this.costPackCode = costPackCode;
	}

	public String getCostOrderFinIds() {
		return costOrderFinIds;
	}

	public void setCostOrderFinIds(String costOrderFinIds) {
		this.costOrderFinIds = costOrderFinIds;
	}

	public Integer getPackTruckNum() {
		return packTruckNum;
	}

	public void setPackTruckNum(Integer packTruckNum) {
		this.packTruckNum = packTruckNum;
	}
	
    public String getShPackId() {
		return shPackId;
	}

	public void setShPackId(String shPackId) {
		this.shPackId = shPackId;
	}

	public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Byte getCheckingStatus() {
        return checkingStatus;
    }

    public void setCheckingStatus(Byte checkingStatus) {
        this.checkingStatus = checkingStatus;
    }

    public Byte getCashSettleStatus() {
        return cashSettleStatus;
    }

    public void setCashSettleStatus(Byte cashSettleStatus) {
        this.cashSettleStatus = cashSettleStatus;
    }

    public BigDecimal getSendTare() {
        return sendTare;
    }

    public void setSendTare(BigDecimal sendTare) {
        this.sendTare = sendTare;
    }

    public BigDecimal getSendGross() {
        return sendGross;
    }

    public void setSendGross(BigDecimal sendGross) {
        this.sendGross = sendGross;
    }

    public BigDecimal getSendNet() {
        return sendNet;
    }

    public void setSendNet(BigDecimal sendNet) {
        this.sendNet = sendNet;
    }

    public BigDecimal getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice(BigDecimal cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public BigDecimal getCargoUnitPrice() {
        return cargoUnitPrice;
    }

    public void setCargoUnitPrice(BigDecimal cargoUnitPrice) {
        this.cargoUnitPrice = cargoUnitPrice;
    }

    public Byte getCashPayType() {
        return cashPayType;
    }

    public void setCashPayType(Byte cashPayType) {
        this.cashPayType = cashPayType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getModifiyDate() {
        return modifiyDate;
    }

    public void setModifiyDate(Date modifiyDate) {
        this.modifiyDate = modifiyDate;
    }

    public Date getCheckingAuditDate() {
        return checkingAuditDate;
    }

    public void setCheckingAuditDate(Date checkingAuditDate) {
        this.checkingAuditDate = checkingAuditDate;
    }

    public Date getSettleAuditDate() {
        return settleAuditDate;
    }

    public void setSettleAuditDate(Date settleAuditDate) {
        this.settleAuditDate = settleAuditDate;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public BigDecimal getTaxRate() {
      return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
      this.taxRate = taxRate;
    }
    
}