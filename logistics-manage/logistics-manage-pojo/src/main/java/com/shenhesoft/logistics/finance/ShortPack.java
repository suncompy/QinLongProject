package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 短驳打包信息表-Form.
 * <p>
 * <a href="ShortPack.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-18
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShortPack {

	// 主键
	private String shPackId;
	// 项目id
	private Integer projectId;
	// 对账状态(0-对账待审核 1-对账财务审核通过 2-对账财务审核不通过 3-已解包 )
	private Integer checkingStatus;
	// 结算状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)
	private Integer cashSettleStatus;
	// 油汽卡结算状态(0-待领取 1-已领取 2-无需领取（非油汽卡结算）)
	private Integer suppliesSettleStatus;
	// 打包车辆
	private Integer packTruckNum;
	// 支付模式id
	private Integer paymentId;
	// 打包车次
	private Integer packTruckDegree;
	// 支付比例
	private BigDecimal payRatio;
	// 现金支付方式(0-现金 1-转账 2-支票)
	private Integer cashPayType;
	// 油气领取方式(0-网店领取 1-人员配送)
	private Integer suppliesReceiveType;
	// 领取人类型0车队负责人1车主
	private Integer receiveUserType;
	// 领取人id
	private Integer receiveUserId;
	private String receiver;
	// 银行信息
	private String openBank;
	// 账号信息
	private String bankAccount;
	// 备注
	private String remark;
	// 是否删除(0-未删除 1-删除)
	private Integer deleteFlag;
	// 创建时间
	private String createDate;
	// 创建人
	private Integer createUserId;
	// 修改时间
	private String modifiyDate;
	// 运费合计
	private BigDecimal freightChargeAmount;
	// 应付现金
	private BigDecimal cashAmount;
	// 油气金额
	private BigDecimal suppliesAmount;
    private Integer checkingAuditor;
	// 司机对账财务审核时间
	private String checkingAuditDate;
	// 司机结算财务审核时间
	private String settleAuditDate;
	// 审核人
	private int auditId;
	// 支出方
	private Integer provideCompanyId;
	// 费用状态(0-待领取现金 1-已领取现金)
	private Integer settleStatus;
	// 油气类型(0:油卡,1:气卡)
	private Integer suppliesType;
	// 油气卡结算执行人
	private Integer suppliesExecuteId;
	// 执行时间
	private String suppliesExecuteDate;
	// 油气卡ID
	private String oilgascardId;

	// ShortOrderFinance表主键
	private String shOrderFinIds;

	//网点交账打包ID
	private String dotAccountPackId;

	// 无参构造
	public ShortPack() {
		super();
	}

	public String getDotAccountPackId() {
		return dotAccountPackId;
	}

	public void setDotAccountPackId(String dotAccountPackId) {
		this.dotAccountPackId = dotAccountPackId;
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

	public Integer getCheckingStatus() {
		return checkingStatus;
	}

	public void setCheckingStatus(Integer checkingStatus) {
		this.checkingStatus = checkingStatus;
	}

	public Integer getCashSettleStatus() {
		return cashSettleStatus;
	}

	public void setCashSettleStatus(Integer cashSettleStatus) {
		this.cashSettleStatus = cashSettleStatus;
	}

	public Integer getSuppliesSettleStatus() {
		return suppliesSettleStatus;
	}

	public void setSuppliesSettleStatus(Integer suppliesSettleStatus) {
		this.suppliesSettleStatus = suppliesSettleStatus;
	}

	public Integer getPackTruckNum() {
		return packTruckNum;
	}

	public void setPackTruckNum(Integer packTruckNum) {
		this.packTruckNum = packTruckNum;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getPackTruckDegree() {
		return packTruckDegree;
	}

	public void setPackTruckDegree(Integer packTruckDegree) {
		this.packTruckDegree = packTruckDegree;
	}

	public BigDecimal getPayRatio() {
		return payRatio;
	}

	public void setPayRatio(BigDecimal payRatio) {
		this.payRatio = payRatio;
	}

	public Integer getCashPayType() {
		return cashPayType;
	}

	public void setCashPayType(Integer cashPayType) {
		this.cashPayType = cashPayType;
	}

	public Integer getSuppliesReceiveType() {
		return suppliesReceiveType;
	}

	public void setSuppliesReceiveType(Integer suppliesReceiveType) {
		this.suppliesReceiveType = suppliesReceiveType;
	}

	public Integer getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getCreateDate() {
		if ("".equals(createDate)) {
			return null;
		}
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getModifiyDate() {
		if ("".equals(modifiyDate)) {
			return null;
		}
		return modifiyDate;
	}

	public void setModifiyDate(String modifiyDate) {
		this.modifiyDate = modifiyDate;
	}

	public BigDecimal getFreightChargeAmount() {
		return freightChargeAmount;
	}

	public void setFreightChargeAmount(BigDecimal freightChargeAmount) {
		this.freightChargeAmount = freightChargeAmount;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public BigDecimal getSuppliesAmount() {
		return suppliesAmount;
	}

	public void setSuppliesAmount(BigDecimal suppliesAmount) {
		this.suppliesAmount = suppliesAmount;
	}

	public String getCheckingAuditDate() {
		if ("".equals(checkingAuditDate)) {
			return null;
		}
		return checkingAuditDate;
	}

	public void setCheckingAuditDate(String checkingAuditDate) {
		this.checkingAuditDate = checkingAuditDate;
	}

	public String getSettleAuditDate() {
		if ("".equals(settleAuditDate)) {
			return null;
		}
		return settleAuditDate;
	}

	public void setSettleAuditDate(String settleAuditDate) {
		this.settleAuditDate = settleAuditDate;
	}

	public String getShOrderFinIds() {
		return shOrderFinIds;
	}

	public void setShOrderFinIds(String shOrderFinIds) {
		this.shOrderFinIds = shOrderFinIds;
	}

	public Integer getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(Integer settleStatus) {
		this.settleStatus = settleStatus;
	}

	public Integer getSuppliesType() {
		return suppliesType;
	}

	public void setSuppliesType(Integer suppliesType) {
		this.suppliesType = suppliesType;
	}

	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}

	public Integer getProvideCompanyId() {
		return provideCompanyId;
	}

	public void setProvideCompanyId(Integer provideCompanyId) {
		this.provideCompanyId = provideCompanyId;
	}

	public Integer getSuppliesExecuteId() {
		return suppliesExecuteId;
	}

	public void setSuppliesExecuteId(Integer suppliesExecuteId) {
		this.suppliesExecuteId = suppliesExecuteId;
	}

	public String getSuppliesExecuteDate() {
		return suppliesExecuteDate;
	}

	public void setSuppliesExecuteDate(String suppliesExecuteDate) {
		this.suppliesExecuteDate = suppliesExecuteDate;
	}

	public String getOilgascardId() {
		return oilgascardId;
	}

	public void setOilgascardId(String oilgascardId) {
		this.oilgascardId = oilgascardId;
	}

	public Integer getReceiveUserType() {
		return receiveUserType;
	}

	public void setReceiveUserType(Integer receiveUserType) {
		this.receiveUserType = receiveUserType;
	}

  public Integer getCheckingAuditor() {
    return checkingAuditor;
  }

  public void setCheckingAuditor(Integer checkingAuditor) {
    this.checkingAuditor = checkingAuditor;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

}