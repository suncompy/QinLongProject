package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbAdvanceCharge<M extends BaseTbAdvanceCharge<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setProjectId(java.lang.Integer projectId) {
		set("project_id", projectId);
		return (M)this;
	}
	
	public java.lang.Integer getProjectId() {
		return getInt("project_id");
	}

	public M setProjectCode(java.lang.String projectCode) {
		set("project_code", projectCode);
		return (M)this;
	}
	
	public java.lang.String getProjectCode() {
		return getStr("project_code");
	}

	public M setBranchId(java.lang.Integer branchId) {
		set("branch_id", branchId);
		return (M)this;
	}
	
	public java.lang.Integer getBranchId() {
		return getInt("branch_id");
	}

	public M setBranchName(java.lang.String branchName) {
		set("branch_name", branchName);
		return (M)this;
	}
	
	public java.lang.String getBranchName() {
		return getStr("branch_name");
	}

	public M setSerialNumber(java.lang.String serialNumber) {
		set("serial_number", serialNumber);
		return (M)this;
	}
	
	public java.lang.String getSerialNumber() {
		return getStr("serial_number");
	}

	public M setStatus(java.lang.Integer status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public M setType(java.lang.Integer type) {
		set("type", type);
		return (M)this;
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public M setDepositAmount(java.math.BigDecimal depositAmount) {
		set("deposit_amount", depositAmount);
		return (M)this;
	}
	
	public java.math.BigDecimal getDepositAmount() {
		return get("deposit_amount");
	}

	public M setPurposeAmount(java.math.BigDecimal purposeAmount) {
		set("purpose_amount", purposeAmount);
		return (M)this;
	}
	
	public java.math.BigDecimal getPurposeAmount() {
		return get("purpose_amount");
	}

	public M setCashAmount(java.math.BigDecimal cashAmount) {
		set("cash_amount", cashAmount);
		return (M)this;
	}
	
	public java.math.BigDecimal getCashAmount() {
		return get("cash_amount");
	}

	public M setOperationDate(java.util.Date operationDate) {
		set("operation_date", operationDate);
		return (M)this;
	}
	
	public java.util.Date getOperationDate() {
		return get("operation_date");
	}

	public M setOperationPerson(java.lang.String operationPerson) {
		set("operation_person", operationPerson);
		return (M)this;
	}
	
	public java.lang.String getOperationPerson() {
		return getStr("operation_person");
	}

	public M setReceiveType(java.lang.Integer receiveType) {
		set("receive_type", receiveType);
		return (M)this;
	}
	
	public java.lang.Integer getReceiveType() {
		return getInt("receive_type");
	}

	public M setReceiveUnitId(java.lang.Integer receiveUnitId) {
		set("receive_unit_id", receiveUnitId);
		return (M)this;
	}
	
	public java.lang.Integer getReceiveUnitId() {
		return getInt("receive_unit_id");
	}

	public M setReceiveUnitName(java.lang.String receiveUnitName) {
		set("receive_unit_name", receiveUnitName);
		return (M)this;
	}
	
	public java.lang.String getReceiveUnitName() {
		return getStr("receive_unit_name");
	}

	public M setReceiveAccountId(java.lang.Integer receiveAccountId) {
		set("receive_account_id", receiveAccountId);
		return (M)this;
	}
	
	public java.lang.Integer getReceiveAccountId() {
		return getInt("receive_account_id");
	}

	public M setReceiveAccountName(java.lang.String receiveAccountName) {
		set("receive_account_name", receiveAccountName);
		return (M)this;
	}
	
	public java.lang.String getReceiveAccountName() {
		return getStr("receive_account_name");
	}

	public M setReceiveAgent(java.lang.String receiveAgent) {
		set("receive_agent", receiveAgent);
		return (M)this;
	}
	
	public java.lang.String getReceiveAgent() {
		return getStr("receive_agent");
	}

	public M setReceiveNumber(java.lang.String receiveNumber) {
		set("receive_number", receiveNumber);
		return (M)this;
	}
	
	public java.lang.String getReceiveNumber() {
		return getStr("receive_number");
	}

	public M setReceiveBankNumber(java.lang.String receiveBankNumber) {
		set("receive_bank_number", receiveBankNumber);
		return (M)this;
	}
	
	public java.lang.String getReceiveBankNumber() {
		return getStr("receive_bank_number");
	}

	public M setReceiveTaxNumber(java.lang.String receiveTaxNumber) {
		set("receive_tax_number", receiveTaxNumber);
		return (M)this;
	}
	
	public java.lang.String getReceiveTaxNumber() {
		return getStr("receive_tax_number");
	}

	public M setPayType(java.lang.Integer payType) {
		set("pay_type", payType);
		return (M)this;
	}
	
	public java.lang.Integer getPayType() {
		return getInt("pay_type");
	}

	public M setPayUnitId(java.lang.Integer payUnitId) {
		set("pay_unit_id", payUnitId);
		return (M)this;
	}
	
	public java.lang.Integer getPayUnitId() {
		return getInt("pay_unit_id");
	}

	public M setPayUnitName(java.lang.String payUnitName) {
		set("pay_unit_name", payUnitName);
		return (M)this;
	}
	
	public java.lang.String getPayUnitName() {
		return getStr("pay_unit_name");
	}

	public M setPayAccountName(java.lang.String payAccountName) {
		set("pay_account_name", payAccountName);
		return (M)this;
	}
	
	public java.lang.String getPayAccountName() {
		return getStr("pay_account_name");
	}

	public M setPayAccountId(java.lang.Integer payAccountId) {
		set("pay_account_id", payAccountId);
		return (M)this;
	}
	
	public java.lang.Integer getPayAccountId() {
		return getInt("pay_account_id");
	}

	public M setPayAgent(java.lang.String payAgent) {
		set("pay_agent", payAgent);
		return (M)this;
	}
	
	public java.lang.String getPayAgent() {
		return getStr("pay_agent");
	}

	public M setPayNumber(java.lang.String payNumber) {
		set("pay_number", payNumber);
		return (M)this;
	}
	
	public java.lang.String getPayNumber() {
		return getStr("pay_number");
	}

	public M setPayBankNumber(java.lang.String payBankNumber) {
		set("pay_bank_number", payBankNumber);
		return (M)this;
	}
	
	public java.lang.String getPayBankNumber() {
		return getStr("pay_bank_number");
	}

	public M setPayTaxNumber(java.lang.String payTaxNumber) {
		set("pay_tax_number", payTaxNumber);
		return (M)this;
	}
	
	public java.lang.String getPayTaxNumber() {
		return getStr("pay_tax_number");
	}

	public M setAdvanceType(java.lang.Integer advanceType) {
		set("advance_type", advanceType);
		return (M)this;
	}
	
	public java.lang.Integer getAdvanceType() {
		return getInt("advance_type");
	}

	public M setPayment(java.lang.Integer payment) {
		set("payment", payment);
		return (M)this;
	}
	
	public java.lang.Integer getPayment() {
		return getInt("payment");
	}

	public M setAssessor(java.lang.String assessor) {
		set("assessor", assessor);
		return (M)this;
	}
	
	public java.lang.String getAssessor() {
		return getStr("assessor");
	}

	public M setAssessorDate(java.util.Date assessorDate) {
		set("assessor_date", assessorDate);
		return (M)this;
	}
	
	public java.util.Date getAssessorDate() {
		return get("assessor_date");
	}

	public M setPurposeChooseAccountName(java.lang.String purposeChooseAccountName) {
		set("purpose_choose_account_name", purposeChooseAccountName);
		return (M)this;
	}
	
	public java.lang.String getPurposeChooseAccountName() {
		return getStr("purpose_choose_account_name");
	}

	public M setPurposeChooseAccount(java.lang.Integer purposeChooseAccount) {
		set("purpose_choose_account", purposeChooseAccount);
		return (M)this;
	}
	
	public java.lang.Integer getPurposeChooseAccount() {
		return getInt("purpose_choose_account");
	}

	public M setBillName(java.lang.Integer billName) {
		set("bill_name", billName);
		return (M)this;
	}
	
	public java.lang.Integer getBillName() {
		return getInt("bill_name");
	}

	public M setStartNumber(java.lang.String startNumber) {
		set("start_number", startNumber);
		return (M)this;
	}
	
	public java.lang.String getStartNumber() {
		return getStr("start_number");
	}

	public M setEndNumber(java.lang.String endNumber) {
		set("end_number", endNumber);
		return (M)this;
	}
	
	public java.lang.String getEndNumber() {
		return getStr("end_number");
	}

	public M setSheetNumber(java.lang.Integer sheetNumber) {
		set("sheet_number", sheetNumber);
		return (M)this;
	}
	
	public java.lang.Integer getSheetNumber() {
		return getInt("sheet_number");
	}

	public M setInvalidNumber(java.lang.Integer invalidNumber) {
		set("invalid_number", invalidNumber);
		return (M)this;
	}
	
	public java.lang.Integer getInvalidNumber() {
		return getInt("invalid_number");
	}

	public M setCargoName(java.lang.String cargoName) {
		set("cargo_name", cargoName);
		return (M)this;
	}
	
	public java.lang.String getCargoName() {
		return getStr("cargo_name");
	}

	public M setTonnage(java.math.BigDecimal tonnage) {
		set("tonnage", tonnage);
		return (M)this;
	}
	
	public java.math.BigDecimal getTonnage() {
		return get("tonnage");
	}

	public M setTotalAmount(java.math.BigDecimal totalAmount) {
		set("total_amount", totalAmount);
		return (M)this;
	}
	
	public java.math.BigDecimal getTotalAmount() {
		return get("total_amount");
	}

	public M setDeleteFlag(java.lang.Integer deleteFlag) {
		set("delete_flag", deleteFlag);
		return (M)this;
	}
	
	public java.lang.Integer getDeleteFlag() {
		return getInt("delete_flag");
	}

	public M setPleaseTrainNum(java.lang.String pleaseTrainNum) {
		set("please_train_num", pleaseTrainNum);
		return (M)this;
	}
	
	public java.lang.String getPleaseTrainNum() {
		return getStr("please_train_num");
	}

	public M setTrainOrderId(java.lang.Integer trainOrderId) {
		set("train_order_id", trainOrderId);
		return (M)this;
	}
	
	public java.lang.Integer getTrainOrderId() {
		return getInt("train_order_id");
	}

	public M setCostPackId(java.lang.String costPackId) {
		set("cost_pack_id", costPackId);
		return (M)this;
	}
	
	public java.lang.String getCostPackId() {
		return getStr("cost_pack_id");
	}

	public M setCostPackNum(java.lang.String costPackNum) {
		set("cost_pack_num", costPackNum);
		return (M)this;
	}
	
	public java.lang.String getCostPackNum() {
		return getStr("cost_pack_num");
	}

	public M setAlreadyDeposeAccount(java.lang.String alreadyDeposeAccount) {
		set("already_depose_account", alreadyDeposeAccount);
		return (M)this;
	}
	
	public java.lang.String getAlreadyDeposeAccount() {
		return getStr("already_depose_account");
	}

	public M setOtherProof(java.lang.Integer otherProof) {
		set("other_proof", otherProof);
		return (M)this;
	}
	
	public java.lang.Integer getOtherProof() {
		return getInt("other_proof");
	}

	public M setOtherProofStart(java.lang.String otherProofStart) {
		set("other_proof_start", otherProofStart);
		return (M)this;
	}
	
	public java.lang.String getOtherProofStart() {
		return getStr("other_proof_start");
	}

	public M setOtherProofEnd(java.lang.String otherProofEnd) {
		set("other_proof_end", otherProofEnd);
		return (M)this;
	}
	
	public java.lang.String getOtherProofEnd() {
		return getStr("other_proof_end");
	}

	public M setOtherProofNum(java.lang.String otherProofNum) {
		set("other_proof_num", otherProofNum);
		return (M)this;
	}
	
	public java.lang.String getOtherProofNum() {
		return getStr("other_proof_num");
	}

	public M setSelfProof(java.lang.Integer selfProof) {
		set("self_proof", selfProof);
		return (M)this;
	}
	
	public java.lang.Integer getSelfProof() {
		return getInt("self_proof");
	}

	public M setSelfProofStart(java.lang.String selfProofStart) {
		set("self_proof_start", selfProofStart);
		return (M)this;
	}
	
	public java.lang.String getSelfProofStart() {
		return getStr("self_proof_start");
	}

	public M setSelfProofEnd(java.lang.String selfProofEnd) {
		set("self_proof_end", selfProofEnd);
		return (M)this;
	}
	
	public java.lang.String getSelfProofEnd() {
		return getStr("self_proof_end");
	}

	public M setSelfProofNum(java.lang.String selfProofNum) {
		set("self_proof_num", selfProofNum);
		return (M)this;
	}
	
	public java.lang.String getSelfProofNum() {
		return getStr("self_proof_num");
	}

	public M setEntruckWeight(java.math.BigDecimal entruckWeight) {
		set("entruck_weight", entruckWeight);
		return (M)this;
	}
	
	public java.math.BigDecimal getEntruckWeight() {
		return get("entruck_weight");
	}

	public M setSumCost(java.math.BigDecimal sumCost) {
		set("sum_cost", sumCost);
		return (M)this;
	}
	
	public java.math.BigDecimal getSumCost() {
		return get("sum_cost");
	}

	public M setProduceTime(java.util.Date produceTime) {
		set("produce_time", produceTime);
		return (M)this;
	}
	
	public java.util.Date getProduceTime() {
		return get("produce_time");
	}

}