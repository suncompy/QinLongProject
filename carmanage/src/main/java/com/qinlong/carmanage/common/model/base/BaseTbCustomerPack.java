package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbCustomerPack<M extends BaseTbCustomerPack<M>> extends Model<M> implements IBean {

	public M setCustPackId(java.lang.String custPackId) {
		set("cust_pack_id", custPackId);
		return (M)this;
	}
	
	public java.lang.String getCustPackId() {
		return getStr("cust_pack_id");
	}

	public M setCustCheckConId(java.lang.String custCheckConId) {
		set("cust_check_con_id", custCheckConId);
		return (M)this;
	}
	
	public java.lang.String getCustCheckConId() {
		return getStr("cust_check_con_id");
	}

	public M setProjectId(java.lang.Integer projectId) {
		set("project_id", projectId);
		return (M)this;
	}
	
	public java.lang.Integer getProjectId() {
		return getInt("project_id");
	}

	public M setPackType(java.lang.Integer packType) {
		set("pack_type", packType);
		return (M)this;
	}
	
	public java.lang.Integer getPackType() {
		return getInt("pack_type");
	}

	public M setCheckingStatus(java.lang.Integer checkingStatus) {
		set("checking_status", checkingStatus);
		return (M)this;
	}
	
	public java.lang.Integer getCheckingStatus() {
		return getInt("checking_status");
	}

	public M setSettleAuditType(java.lang.Integer settleAuditType) {
		set("settle_audit_type", settleAuditType);
		return (M)this;
	}
	
	public java.lang.Integer getSettleAuditType() {
		return getInt("settle_audit_type");
	}

	public M setPackTruckNum(java.lang.Integer packTruckNum) {
		set("pack_truck_num", packTruckNum);
		return (M)this;
	}
	
	public java.lang.Integer getPackTruckNum() {
		return getInt("pack_truck_num");
	}

	public M setPackTruckDegree(java.lang.Integer packTruckDegree) {
		set("pack_truck_degree", packTruckDegree);
		return (M)this;
	}
	
	public java.lang.Integer getPackTruckDegree() {
		return getInt("pack_truck_degree");
	}

	public M setInvoiceStatus(java.lang.Integer invoiceStatus) {
		set("invoice_status", invoiceStatus);
		return (M)this;
	}
	
	public java.lang.Integer getInvoiceStatus() {
		return getInt("invoice_status");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

	public M setDeleteFlag(java.lang.Integer deleteFlag) {
		set("delete_flag", deleteFlag);
		return (M)this;
	}
	
	public java.lang.Integer getDeleteFlag() {
		return getInt("delete_flag");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("create_date");
	}

	public M setCreateUserId(java.lang.Integer createUserId) {
		set("create_user_id", createUserId);
		return (M)this;
	}
	
	public java.lang.Integer getCreateUserId() {
		return getInt("create_user_id");
	}

	public M setModifiyDate(java.util.Date modifiyDate) {
		set("modifiy_date", modifiyDate);
		return (M)this;
	}
	
	public java.util.Date getModifiyDate() {
		return get("modifiy_date");
	}

	public M setStartDate(java.util.Date startDate) {
		set("start_date", startDate);
		return (M)this;
	}
	
	public java.util.Date getStartDate() {
		return get("start_date");
	}

	public M setEndDate(java.util.Date endDate) {
		set("end_date", endDate);
		return (M)this;
	}
	
	public java.util.Date getEndDate() {
		return get("end_date");
	}

	public M setOrderCount(java.lang.Integer orderCount) {
		set("order_count", orderCount);
		return (M)this;
	}
	
	public java.lang.Integer getOrderCount() {
		return getInt("order_count");
	}

	public M setTotalWeight(java.math.BigDecimal totalWeight) {
		set("total_weight", totalWeight);
		return (M)this;
	}
	
	public java.math.BigDecimal getTotalWeight() {
		return get("total_weight");
	}

	public M setTotalPiece(java.lang.Integer totalPiece) {
		set("total_piece", totalPiece);
		return (M)this;
	}
	
	public java.lang.Integer getTotalPiece() {
		return getInt("total_piece");
	}

	public M setProduceMoney(java.math.BigDecimal produceMoney) {
		set("produce_money", produceMoney);
		return (M)this;
	}
	
	public java.math.BigDecimal getProduceMoney() {
		return get("produce_money");
	}

	public M setTaxMoney(java.math.BigDecimal taxMoney) {
		set("tax_money", taxMoney);
		return (M)this;
	}
	
	public java.math.BigDecimal getTaxMoney() {
		return get("tax_money");
	}

	public M setBesettledMoney(java.math.BigDecimal besettledMoney) {
		set("besettled_money", besettledMoney);
		return (M)this;
	}
	
	public java.math.BigDecimal getBesettledMoney() {
		return get("besettled_money");
	}

	public M setSettledMoney(java.math.BigDecimal settledMoney) {
		set("settled_money", settledMoney);
		return (M)this;
	}
	
	public java.math.BigDecimal getSettledMoney() {
		return get("settled_money");
	}

	public M setSettleMoney(java.math.BigDecimal settleMoney) {
		set("settle_money", settleMoney);
		return (M)this;
	}
	
	public java.math.BigDecimal getSettleMoney() {
		return get("settle_money");
	}

	public M setSettleType(java.lang.Integer settleType) {
		set("settle_type", settleType);
		return (M)this;
	}
	
	public java.lang.Integer getSettleType() {
		return getInt("settle_type");
	}

	public M setCheckAuditId(java.lang.String checkAuditId) {
		set("check_audit_id", checkAuditId);
		return (M)this;
	}
	
	public java.lang.String getCheckAuditId() {
		return getStr("check_audit_id");
	}

	public M setCheckAuditDate(java.util.Date checkAuditDate) {
		set("check_audit_date", checkAuditDate);
		return (M)this;
	}
	
	public java.util.Date getCheckAuditDate() {
		return get("check_audit_date");
	}

	public M setAuditId(java.lang.String auditId) {
		set("audit_id", auditId);
		return (M)this;
	}
	
	public java.lang.String getAuditId() {
		return getStr("audit_id");
	}

	public M setAuditDate(java.util.Date auditDate) {
		set("audit_date", auditDate);
		return (M)this;
	}
	
	public java.util.Date getAuditDate() {
		return get("audit_date");
	}

	public M setSettleDate(java.util.Date settleDate) {
		set("settle_date", settleDate);
		return (M)this;
	}
	
	public java.util.Date getSettleDate() {
		return get("settle_date");
	}

	public M setSettleId(java.lang.String settleId) {
		set("settle_id", settleId);
		return (M)this;
	}
	
	public java.lang.String getSettleId() {
		return getStr("settle_id");
	}

	public M setBackId(java.lang.String backId) {
		set("back_id", backId);
		return (M)this;
	}
	
	public java.lang.String getBackId() {
		return getStr("back_id");
	}

	public M setBackDate(java.util.Date backDate) {
		set("back_date", backDate);
		return (M)this;
	}
	
	public java.util.Date getBackDate() {
		return get("back_date");
	}

	public M setIsHistroy(java.lang.Integer isHistroy) {
		set("is_histroy", isHistroy);
		return (M)this;
	}
	
	public java.lang.Integer getIsHistroy() {
		return getInt("is_histroy");
	}

	public M setCustomerName(java.lang.String customerName) {
		set("customer_name", customerName);
		return (M)this;
	}
	
	public java.lang.String getCustomerName() {
		return getStr("customer_name");
	}

	public M setCustomerUnit(java.lang.String customerUnit) {
		set("customer_unit", customerUnit);
		return (M)this;
	}
	
	public java.lang.String getCustomerUnit() {
		return getStr("customer_unit");
	}

	public M setBeginAddress(java.lang.String beginAddress) {
		set("beginAddress", beginAddress);
		return (M)this;
	}
	
	public java.lang.String getBeginAddress() {
		return getStr("beginAddress");
	}

	public M setEndAddress(java.lang.String endAddress) {
		set("endAddress", endAddress);
		return (M)this;
	}
	
	public java.lang.String getEndAddress() {
		return getStr("endAddress");
	}

}
