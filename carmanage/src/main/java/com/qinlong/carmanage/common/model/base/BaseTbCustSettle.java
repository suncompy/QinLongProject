package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbCustSettle<M extends BaseTbCustSettle<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setPayDepositOrg(java.lang.String payDepositOrg) {
		set("pay_deposit_org", payDepositOrg);
		return (M)this;
	}
	
	public java.lang.String getPayDepositOrg() {
		return getStr("pay_deposit_org");
	}

	public M setPayDepositType(java.lang.String payDepositType) {
		set("pay_deposit_type", payDepositType);
		return (M)this;
	}
	
	public java.lang.String getPayDepositType() {
		return getStr("pay_deposit_type");
	}

	public M setSettleStatus(java.lang.Integer settleStatus) {
		set("settle_status", settleStatus);
		return (M)this;
	}
	
	public java.lang.Integer getSettleStatus() {
		return getInt("settle_status");
	}

	public M setInvoiceId(java.lang.String invoiceId) {
		set("invoice_id", invoiceId);
		return (M)this;
	}
	
	public java.lang.String getInvoiceId() {
		return getStr("invoice_id");
	}

	public M setSettledMoney(java.math.BigDecimal settledMoney) {
		set("settled_money", settledMoney);
		return (M)this;
	}
	
	public java.math.BigDecimal getSettledMoney() {
		return get("settled_money");
	}

	public M setSettingMoney(java.math.BigDecimal settingMoney) {
		set("setting_money", settingMoney);
		return (M)this;
	}
	
	public java.math.BigDecimal getSettingMoney() {
		return get("setting_money");
	}

	public M setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
		return (M)this;
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
		return (M)this;
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

}
