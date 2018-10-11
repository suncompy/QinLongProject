// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月22日
 */
public class TbSubForwardDetail implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8797379743196023292L;

	@NotNull
	private Integer orderId;

	/**
	 * 发货皮重
	 */
	@NotNull
	private BigDecimal sendTare;

	// 发货毛重
	@NotNull
	private BigDecimal sendGross;

	// 第一个集装箱的发货净重
	@NotNull
	private BigDecimal containerOneSendNet;

	// 第二个集装箱的发货净重
	@NotNull
	private BigDecimal containerTwoSendNet;

	// 发货:化验指标
	@NotBlank
	private String testIndicators;

	// 1同意发运
	@NotNull
	private byte IsAgree;

	// 图片
	private String img;

	// 拒绝原因
	private String remark;

	private Integer pieceNumber;
	
	public Integer getPieceNumber() {
		return pieceNumber;
	}

	public void setPieceNumber(Integer pieceNumber) {
		this.pieceNumber = pieceNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public byte getIsAgree() {
		return IsAgree;
	}

	public void setIsAgree(byte isAgree) {
		IsAgree = isAgree;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getTestIndicators() {
		return testIndicators;
	}

	public void setTestIndicators(String testIndicators) {
		this.testIndicators = testIndicators;
	}

	public BigDecimal getSendTare() {
		return sendTare;
	}

	public void setSendTare(BigDecimal sendTare) {
		this.sendTare = sendTare;
	}

	public BigDecimal getSendGross() {
		return sendGross;
	}

	public void setSendGross(BigDecimal sendGross) {
		this.sendGross = sendGross;
	}

	public BigDecimal getContainerOneSendNet() {
		return containerOneSendNet;
	}

	public void setContainerOneSendNet(BigDecimal containerOneSendNet) {
		this.containerOneSendNet = containerOneSendNet;
	}

	public BigDecimal getContainerTwoSendNet() {
		return containerTwoSendNet;
	}

	public void setContainerTwoSendNet(BigDecimal containerTwoSendNet) {
		this.containerTwoSendNet = containerTwoSendNet;
	}

}
