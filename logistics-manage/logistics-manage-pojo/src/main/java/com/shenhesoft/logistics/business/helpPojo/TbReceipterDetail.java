// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

/**
 * @description 等待回单-
 *
 * @author LiangLin
 *
 * @date 2017年12月22日
 */
public class TbReceipterDetail implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7911908915155649660L;

	@NotNull
	private Integer orderId;

	// 发货皮重
	@NotNull
	private BigDecimal receiptTare;

	// 发货毛重
	@NotNull
	private BigDecimal receiptGross;

	// 第一个集装箱的发货净重
	@NotNull
	private BigDecimal containerOneReceiptNet;

	// 第二个集装箱的发货净重
	@NotNull
	private BigDecimal containerTwoReceiptNet;

	private String img;

	private Integer imgType;
	
	public Integer getImgType() {
		return imgType;
	}

	public void setImgType(Integer imgType) {
		this.imgType = imgType;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getReceiptTare() {
		return receiptTare;
	}

	public void setReceiptTare(BigDecimal receiptTare) {
		this.receiptTare = receiptTare;
	}

	public BigDecimal getReceiptGross() {
		return receiptGross;
	}

	public void setReceiptGross(BigDecimal receiptGross) {
		this.receiptGross = receiptGross;
	}

	public BigDecimal getContainerOneReceiptNet() {
		return containerOneReceiptNet;
	}

	public void setContainerOneReceiptNet(BigDecimal containerOneReceiptNet) {
		this.containerOneReceiptNet = containerOneReceiptNet;
	}

	public BigDecimal getContainerTwoReceiptNet() {
		return containerTwoReceiptNet;
	}

	public void setContainerTwoReceiptNet(BigDecimal containerTwoReceiptNet) {
		this.containerTwoReceiptNet = containerTwoReceiptNet;
	}

}
