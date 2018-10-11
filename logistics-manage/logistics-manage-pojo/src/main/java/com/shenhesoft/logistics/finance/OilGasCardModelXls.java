package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 模板-Form.
 * @author Jys
 * @date 2018-01-30
 * @version 1.0.0
 * @since 1.0.0
 */
public class OilGasCardModelXls {

	// 卡号
	private String cardNum;
	// 金额
	private BigDecimal cardMoney;

	// 无参构造
	public OilGasCardModelXls() {
		super();
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public BigDecimal getCardMoney() {
		return cardMoney;
	}

	public void BigDecimal(BigDecimal cardMoney) {
		this.cardMoney = cardMoney;
	}


}