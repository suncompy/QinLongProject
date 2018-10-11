package com.shenhesoft.logistics.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AdvanceCharge implements Serializable{
    /**
     * serialVersionUID
	*/
	private static final long serialVersionUID = -2634445187597397996L;

	/** 预付款存入id*/
    private Integer id;

    /** 项目id*/
    private Integer projectId;

    /** 项目编号*/
    private String projectCode;

    /** 分支机构id*/
    private Integer branchId;

    /** 分支机构名称*/
    private String branchName;

    /** 流水号*/
    private String serialNumber;

    /** 状态 0：已审核，1：待审核，2：审核失败*/
    private Byte status;

    /** 操作标志： 0：存入，1：抵用*/
    private Byte type;

    /** 存入金额*/
    private BigDecimal depositAmount;

    /** 余额*/
    private BigDecimal blance;
    
    /** 抵用金额*/
    private BigDecimal purposeAmount;
    
    /** 提现金额*/
    private BigDecimal cashAmount;
    
    /** 待处理数量*/
    private Integer needHandle;
    
    /** 操作时间*/
    private Date operationDate;

    /** 操作人*/
    private String operationPerson;

    /** 收款类型 0：客户，1：中心站点，2：公司*/
    private Byte receiveType;

    /** 收款的单位id*/
    private Integer receiveUnitId;

    /** 收款单位*/
    private String receiveUnitName;

    /** 收款账户*/
    private Integer receiveAccountId;

    private String receiveAccountName;
    /** 收款经办人*/
    private String receiveAgent;

    /** 收款账号*/
    private String receiveNumber;

    /** 收款行号*/
    private String receiveBankNumber;

    /** 收款凭证号*/
    private String receiveTaxNumber;

    /** 支出类型*/
    private Byte payType;

    /** 支出单位id*/
    private Integer payUnitId;

    /** 支出单位名称*/
    private String payUnitName;

    /** 支出账户*/
    private Integer payAccountId;
    
    private String payAccountName;
    /** 支出经办人*/
    private String payAgent;

    /** 支出账号*/
    private String payNumber;

    /** 支出行号*/
    private String payBankNumber;

    /** 支出纳税识别号*/
    private String payTaxNumber;

    /** 预付类型 0：货款，1：运费，2：其他*/
    private Byte advanceType;

    /** 支付方式 0：现金，1：支票，2：网银*/
    private Byte payment;

    /** 审核人id*/
    //private Integer assessorId;
    
    /** 审核人*/
    private String assessor;

    /** 审核时间*/
    private Date assessorDate;

    /** 抵用选择账户*/
    private Integer purposeChooseAccount;

    private String purposeChooseAccountName;
    /** 票据名称 0：火运费用，1：货物费用*/
    private Byte billName;

    /** 起号*/
    private String startNumber;

    /** 止号*/
    private String endNumber;

    /** 张数*/
    private Integer sheetNumber;

    /** 作废张数*/
    private Integer invalidNumber;

    /** 货物品名*/
    private String cargoName;

    /** 吨数*/
    private BigDecimal tonnage;

    /** 总金额*/
    private BigDecimal totalAmount;
    
    /** 删除标志*/
    private Byte deleteFlag;

    /** 火运订单id*/
    private Integer trainOrderId;

    /** 请车单号*/
    private String pleaseTrainNum;
    
    /** 对账单号id*/
    private String costPackId;

    /** 对账单号*/
    private String costPackNum;
    
    /** 抵用已经存入账号*/
    private String alreadyDeposeAccount;
    
    /** 对方凭证*/
    private Integer otherProof;
    
    /** 对方凭证起始*/
    private String otherProofStart;
    
    /** 对方凭证终止*/
    private String otherProofEnd;
    
    /** 对方凭证编号*/
    private String otherProofNum;
    
    /** 己方凭证*/
    private Integer selfProof;
    
    /** 己方凭证起始*/
    private String selfProofStart;
    
    /** 己方凭证终止*/
    private String selfProofEnd;
    
    /** 己方凭证编号*/
    private String selfProofNum;
    
    /** 装车总重*/
    private BigDecimal entruckWeight;
    
    /** 运费合计*/
    private BigDecimal sumCost;
    
    /** 产生时间*/
    private Date produceTime;
    
    public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public Integer getNeedHandle() {
		return needHandle;
	}

	public void setNeedHandle(Integer needHandle) {
		this.needHandle = needHandle;
	}

	public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public BigDecimal getBlance() {
		return blance;
	}

	public void setBlance(BigDecimal blance) {
		this.blance = blance;
	}

	public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationPerson() {
        return operationPerson;
    }

    public void setOperationPerson(String operationPerson) {
        this.operationPerson = operationPerson == null ? null : operationPerson.trim();
    }

    public Byte getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Byte receiveType) {
        this.receiveType = receiveType;
    }

    public Integer getReceiveUnitId() {
        return receiveUnitId;
    }

    public void setReceiveUnitId(Integer receiveUnitId) {
        this.receiveUnitId = receiveUnitId;
    }

    public String getReceiveUnitName() {
        return receiveUnitName;
    }

    public void setReceiveUnitName(String receiveUnitName) {
        this.receiveUnitName = receiveUnitName == null ? null : receiveUnitName.trim();
    }

    public Integer getReceiveAccountId() {
		return receiveAccountId;
	}

	public void setReceiveAccountId(Integer receiveAccountId) {
		this.receiveAccountId = receiveAccountId;
	}

	public String getReceiveAgent() {
        return receiveAgent;
    }

    public void setReceiveAgent(String receiveAgent) {
        this.receiveAgent = receiveAgent == null ? null : receiveAgent.trim();
    }

    public String getReceiveNumber() {
        return receiveNumber;
    }

    public void setReceiveNumber(String receiveNumber) {
        this.receiveNumber = receiveNumber == null ? null : receiveNumber.trim();
    }

    public String getReceiveBankNumber() {
        return receiveBankNumber;
    }

    public void setReceiveBankNumber(String receiveBankNumber) {
        this.receiveBankNumber = receiveBankNumber == null ? null : receiveBankNumber.trim();
    }

    public String getReceiveTaxNumber() {
        return receiveTaxNumber;
    }

    public void setReceiveTaxNumber(String receiveTaxNumber) {
        this.receiveTaxNumber = receiveTaxNumber == null ? null : receiveTaxNumber.trim();
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Integer getPayUnitId() {
        return payUnitId;
    }

    public void setPayUnitId(Integer payUnitId) {
        this.payUnitId = payUnitId;
    }

    public String getPayUnitName() {
        return payUnitName;
    }

    public void setPayUnitName(String payUnitName) {
        this.payUnitName = payUnitName == null ? null : payUnitName.trim();
    }

    public Integer getPayAccountId() {
		return payAccountId;
	}

	public void setPayAccountId(Integer payAccountId) {
		this.payAccountId = payAccountId;
	}

	public String getPayAgent() {
        return payAgent;
    }

    public void setPayAgent(String payAgent) {
        this.payAgent = payAgent == null ? null : payAgent.trim();
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber == null ? null : payNumber.trim();
    }

    public String getPayBankNumber() {
        return payBankNumber;
    }

    public void setPayBankNumber(String payBankNumber) {
        this.payBankNumber = payBankNumber == null ? null : payBankNumber.trim();
    }

    public String getPayTaxNumber() {
        return payTaxNumber;
    }

    public void setPayTaxNumber(String payTaxNumber) {
        this.payTaxNumber = payTaxNumber == null ? null : payTaxNumber.trim();
    }

    public Byte getAdvanceType() {
        return advanceType;
    }

    public void setAdvanceType(Byte advanceType) {
        this.advanceType = advanceType;
    }

    public Byte getPayment() {
        return payment;
    }

    public void setPayment(Byte payment) {
        this.payment = payment;
    }

    public String getAssessor() {
        return assessor;
    }

    public void setAssessor(String assessor) {
        this.assessor = assessor == null ? null : assessor.trim();
    }

    public Date getAssessorDate() {
        return assessorDate;
    }

    public void setAssessorDate(Date assessorDate) {
        this.assessorDate = assessorDate;
    }

    public Integer getPurposeChooseAccount() {
		return purposeChooseAccount;
	}

	public void setPurposeChooseAccount(Integer purposeChooseAccount) {
		this.purposeChooseAccount = purposeChooseAccount;
	}

	public Byte getBillName() {
        return billName;
    }

    public void setBillName(Byte billName) {
        this.billName = billName;
    }

    public String getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(String startNumber) {
        this.startNumber = startNumber == null ? null : startNumber.trim();
    }

    public String getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(String endNumber) {
        this.endNumber = endNumber == null ? null : endNumber.trim();
    }

    public Integer getSheetNumber() {
        return sheetNumber;
    }

    public void setSheetNumber(Integer sheetNumber) {
        this.sheetNumber = sheetNumber;
    }

    public Integer getInvalidNumber() {
        return invalidNumber;
    }

    public void setInvalidNumber(Integer invalidNumber) {
        this.invalidNumber = invalidNumber;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName == null ? null : cargoName.trim();
    }

    public BigDecimal getTonnage() {
        return tonnage;
    }

    public void setTonnage(BigDecimal tonnage) {
        this.tonnage = tonnage;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

	public Byte getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getTrainOrderId() {
		return trainOrderId;
	}

	public void setTrainOrderId(Integer trainOrderId) {
		this.trainOrderId = trainOrderId;
	}

	public String getPleaseTrainNum() {
		return pleaseTrainNum;
	}

	public void setPleaseTrainNum(String pleaseTrainNum) {
		this.pleaseTrainNum = pleaseTrainNum;
	}

	public String getCostPackId() {
		return costPackId;
	}

	public void setCostPackId(String costPackId) {
		this.costPackId = costPackId;
	}

	public String getCostPackNum() {
		return costPackNum;
	}

	public void setCostPackNum(String costPackNum) {
		this.costPackNum = costPackNum;
	}

	public String getAlreadyDeposeAccount() {
		return alreadyDeposeAccount;
	}

	public void setAlreadyDeposeAccount(String alreadyDeposeAccount) {
		this.alreadyDeposeAccount = alreadyDeposeAccount;
	}

	public String getReceiveAccountName() {
		return receiveAccountName;
	}

	public void setReceiveAccountName(String receiveAccountName) {
		this.receiveAccountName = receiveAccountName;
	}

	public String getPayAccountName() {
		return payAccountName;
	}

	public void setPayAccountName(String payAccountName) {
		this.payAccountName = payAccountName;
	}

	public String getPurposeChooseAccountName() {
		return purposeChooseAccountName;
	}

	public void setPurposeChooseAccountName(String purposeChooseAccountName) {
		this.purposeChooseAccountName = purposeChooseAccountName;
	}

	public Integer getOtherProof() {
		return otherProof;
	}

	public void setOtherProof(Integer otherProof) {
		this.otherProof = otherProof;
	}

	public String getOtherProofStart() {
		return otherProofStart;
	}

	public void setOtherProofStart(String otherProofStart) {
		this.otherProofStart = otherProofStart;
	}

	public String getOtherProofEnd() {
		return otherProofEnd;
	}

	public void setOtherProofEnd(String otherProofEnd) {
		this.otherProofEnd = otherProofEnd;
	}

	public String getOtherProofNum() {
		return otherProofNum;
	}

	public void setOtherProofNum(String otherProofNum) {
		this.otherProofNum = otherProofNum;
	}

	public Integer getSelfProof() {
		return selfProof;
	}

	public void setSelfProof(Integer selfProof) {
		this.selfProof = selfProof;
	}

	public String getSelfProofStart() {
		return selfProofStart;
	}

	public void setSelfProofStart(String selfProofStart) {
		this.selfProofStart = selfProofStart;
	}

	public String getSelfProofEnd() {
		return selfProofEnd;
	}

	public void setSelfProofEnd(String selfProofEnd) {
		this.selfProofEnd = selfProofEnd;
	}

	public String getSelfProofNum() {
		return selfProofNum;
	}

	public void setSelfProofNum(String selfProofNum) {
		this.selfProofNum = selfProofNum;
	}

	public BigDecimal getEntruckWeight() {
		return entruckWeight;
	}

	public void setEntruckWeight(BigDecimal entruckWeight) {
		this.entruckWeight = entruckWeight;
	}

	public BigDecimal getSumCost() {
		return sumCost;
	}

	public void setSumCost(BigDecimal sumCost) {
		this.sumCost = sumCost;
	}

	public Date getProduceTime() {
		return produceTime;
	}

	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
	}

}