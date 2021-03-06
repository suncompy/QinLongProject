package com.qinlong.carmanage.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbFinanceAccountDetail<M extends BaseTbFinanceAccountDetail<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setPayAccountId(java.lang.Integer payAccountId) {
		set("pay_account_id", payAccountId);
		return (M)this;
	}
	
	public java.lang.Integer getPayAccountId() {
		return getInt("pay_account_id");
	}

	public M setPayAccountType(java.lang.Integer payAccountType) {
		set("pay_account_type", payAccountType);
		return (M)this;
	}
	
	public java.lang.Integer getPayAccountType() {
		return getInt("pay_account_type");
	}

	public M setDepositAccountId(java.lang.Integer depositAccountId) {
		set("deposit_account_id", depositAccountId);
		return (M)this;
	}
	
	public java.lang.Integer getDepositAccountId() {
		return getInt("deposit_account_id");
	}

	public M setDepositAccountType(java.lang.Integer depositAccountType) {
		set("deposit_account_type", depositAccountType);
		return (M)this;
	}
	
	public java.lang.Integer getDepositAccountType() {
		return getInt("deposit_account_type");
	}

	public M setMoney(java.math.BigDecimal money) {
		set("money", money);
		return (M)this;
	}
	
	public java.math.BigDecimal getMoney() {
		return get("money");
	}

	public M setOperateId(java.lang.Integer operateId) {
		set("operate_id", operateId);
		return (M)this;
	}
	
	public java.lang.Integer getOperateId() {
		return getInt("operate_id");
	}

	public M setPayDate(java.util.Date payDate) {
		set("pay_date", payDate);
		return (M)this;
	}
	
	public java.util.Date getPayDate() {
		return get("pay_date");
	}

	public M setStatementNum(java.lang.String statementNum) {
		set("statement_num", statementNum);
		return (M)this;
	}
	
	public java.lang.String getStatementNum() {
		return getStr("statement_num");
	}

}
