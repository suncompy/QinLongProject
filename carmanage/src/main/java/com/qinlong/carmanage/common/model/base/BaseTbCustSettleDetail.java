package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbCustSettleDetail<M extends BaseTbCustSettleDetail<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setSettleId(java.lang.String settleId) {
		set("settle_id", settleId);
		return (M)this;
	}
	
	public java.lang.String getSettleId() {
		return getStr("settle_id");
	}

	public M setApplyStatus(java.lang.Integer applyStatus) {
		set("apply_status", applyStatus);
		return (M)this;
	}
	
	public java.lang.Integer getApplyStatus() {
		return getInt("apply_status");
	}

	public M setSettleOrg(java.lang.String settleOrg) {
		set("settle_org", settleOrg);
		return (M)this;
	}
	
	public java.lang.String getSettleOrg() {
		return getStr("settle_org");
	}

	public M setSettleUserId(java.lang.Integer settleUserId) {
		set("settle_user_id", settleUserId);
		return (M)this;
	}
	
	public java.lang.Integer getSettleUserId() {
		return getInt("settle_user_id");
	}

	public M setSettleAccountId(java.lang.Integer settleAccountId) {
		set("settle_account_id", settleAccountId);
		return (M)this;
	}
	
	public java.lang.Integer getSettleAccountId() {
		return getInt("settle_account_id");
	}

	public M setApplyMoney(java.math.BigDecimal applyMoney) {
		set("apply_money", applyMoney);
		return (M)this;
	}
	
	public java.math.BigDecimal getApplyMoney() {
		return get("apply_money");
	}

	public M setSettleModel(java.lang.Integer settleModel) {
		set("settle_model", settleModel);
		return (M)this;
	}
	
	public java.lang.Integer getSettleModel() {
		return getInt("settle_model");
	}

	public M setOptUserId(java.lang.Integer optUserId) {
		set("opt_user_id", optUserId);
		return (M)this;
	}
	
	public java.lang.Integer getOptUserId() {
		return getInt("opt_user_id");
	}

	public M setAuditUserId(java.lang.Integer auditUserId) {
		set("audit_user_id", auditUserId);
		return (M)this;
	}
	
	public java.lang.Integer getAuditUserId() {
		return getInt("audit_user_id");
	}

	public M setAuditTime(java.util.Date auditTime) {
		set("audit_time", auditTime);
		return (M)this;
	}
	
	public java.util.Date getAuditTime() {
		return get("audit_time");
	}

}
