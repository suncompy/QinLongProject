package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 账户明细信息表-Form.
 * <p>
 * <a href="FinanceAccountDetail.java"><i>View Source</i></a>
 * </p>
 * 
 * @author Jys
 * @date 2018-02-8
 * @version 1.0.0
 * @since 1.0.0
 */
public class FinanceAccountDetail {

	// 主键
	private Integer id;
	// 支出账户
	private Integer payAccountId;
	//支出账户类型
	private Integer payAccountType;
	// 存入账户
	private Integer depositAccountId;
	//支出账户类型
	private Integer depositAccountType;
	// 金额
	private BigDecimal money;
	// 操作人
	private Integer operateId;
	// 时间
	private String payDate;
	// 流水号
	private String statementNum;

	// 无参构造
	public FinanceAccountDetail() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPayAccountId() {
		return payAccountId;
	}

	public void setPayAccountId(Integer payAccountId) {
		this.payAccountId = payAccountId;
	}

	public Integer getDepositAccountId() {
		return depositAccountId;
	}

	public void setDepositAccountId(Integer depositAccountId) {
		this.depositAccountId = depositAccountId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getStatementNum() {
		return statementNum;
	}

	public void setStatementNum(String statementNum) {
		this.statementNum = statementNum;
	}

	public Integer getPayAccountType() {
		return payAccountType;
	}

	public void setPayAccountType(Integer payAccountType) {
		this.payAccountType = payAccountType;
	}

	public Integer getDepositAccountType() {
		return depositAccountType;
	}

	public void setDepositAccountType(Integer depositAccountType) {
		this.depositAccountType = depositAccountType;
	}


}