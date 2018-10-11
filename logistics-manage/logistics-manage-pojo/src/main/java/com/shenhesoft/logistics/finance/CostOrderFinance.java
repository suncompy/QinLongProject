package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;
import java.util.Date;

public class CostOrderFinance {
    /** */
    private String costOrderFinId;

    /** 项目id*/
    private Integer projectId;

    /** 运单id*/
    private Integer orderId;

    /** 财务状态*/
    private Byte financeStatus;

    /** */
    private Byte packFlag;

    /** 是否删除	0 未删除 1 已删除*/
    private Byte deleteFlag;

    /** 发货皮重*/
    private BigDecimal sendTare;

    /** 发货毛重*/
    private BigDecimal sendGross;

    /** 发货净重*/
    private BigDecimal sendNet;

    /** 货物单价*/
    private BigDecimal cargoUnitPrice;

    /** 货物价格*/
    private BigDecimal cargoPrice;

    /** 创建时间*/
    private Date createDate;

    /** 创建人*/
    private Integer createUserId;

    /** 修改时间*/
    private Date modifiyDate;

    /** 打包的id*/
    private String tbCostPackId;

    public String getCostOrderFinId() {
		return costOrderFinId;
	}

	public void setCostOrderFinId(String costOrderFinId) {
		this.costOrderFinId = costOrderFinId;
	}

	public void setTbCostPackId(String tbCostPackId) {
		this.tbCostPackId = tbCostPackId;
	}

	public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Byte getFinanceStatus() {
		return financeStatus;
	}

	public void setFinanceStatus(Byte financeStatus) {
		this.financeStatus = financeStatus;
	}

	public Byte getPackFlag() {
        return packFlag;
    }

    public void setPackFlag(Byte packFlag) {
        this.packFlag = packFlag;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public BigDecimal getCargoUnitPrice() {
        return cargoUnitPrice;
    }

    public void setCargoUnitPrice(BigDecimal cargoUnitPrice) {
        this.cargoUnitPrice = cargoUnitPrice;
    }

    public BigDecimal getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice(BigDecimal cargoPrice) {
        this.cargoPrice = cargoPrice;
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

	public String getTbCostPackId() {
		return tbCostPackId;
	}

}