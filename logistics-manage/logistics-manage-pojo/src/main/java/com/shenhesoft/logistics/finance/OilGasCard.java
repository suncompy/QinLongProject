package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 油气卡信息表-Form.
 * <p>
 * <a href="OilGasCard.java"><i>View Source</i></a>
 * </p>
 * 
 * @author Jys
 * @date 2018-01-26
 * @version 1.0.0
 * @since 1.0.0
 */
public class OilGasCard {

	// 主键
	private String id;
	//油气打包id
	private String oilGasCardsId;
	// 卡号
	private String cardNum;
	// 金额
	private BigDecimal cardMoney;
	// 使用状态(0未使用 1:已使用)
	private Integer cardStatus;

	// 无参构造
	public OilGasCard() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOilGasCardsId() {
		return oilGasCardsId;
	}

	public void setOilGasCardsId(String oilGasCardsId) {
		this.oilGasCardsId = oilGasCardsId;
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

	public void setCardMoney(BigDecimal cardMoney) {
		this.cardMoney = cardMoney;
	}

	public Integer getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}


}