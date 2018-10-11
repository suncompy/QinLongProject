package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * @description 用户短驳出入库查询统计
 * 
 * @author shilvfei
 * 
 * @date 2018年1月3日
 */
public class TbOrderHelpPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -894113305252513602L;

	/** 项目编号*/
    private String projectCode;

    /** 分支机构名称*/
    private String branchGroupName;

    /** 项目类型
            0 集装箱
            1 散装*/
    private Byte projectType;

    /** 联运模式
            0 汽运
            1 接取
            2 送达
            3 火运
            4 接取+火运
            5 火运+送达
            6 联运 
            7接取+送达*/
    private Byte transportType;

    /** 货物名称*/
    private String cargoName;

    /** 规格*/
    private String cargoSpecifications;

    /** 货物价格*/
    private BigDecimal cargoPrice;

    /** 计价单位
            0 吨
            1 位*/
    private Byte valuationUnitName;
    /**
     * 该项目下的运单
     * */
    private List orders;

    /**
     * 总计运单
     */
    private Integer totalOrder;
    
    /**
     * 总计提货吨位
     */
    private BigDecimal totalPickUpWeight;
    
    /**
     * 总计损耗
     */
    private BigDecimal totalWastageWeight;
    
    /**
     * 总计到货吨位
     */
    private BigDecimal totalArriveWeight;
    
	public Integer getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(Integer totalOrder) {
		this.totalOrder = totalOrder;
	}

	public BigDecimal getTotalPickUpWeight() {
		return totalPickUpWeight;
	}

	public void setTotalPickUpWeight(BigDecimal totalPickUpWeight) {
		this.totalPickUpWeight = totalPickUpWeight;
	}

	public BigDecimal getTotalWastageWeight() {
		return totalWastageWeight;
	}

	public void setTotalWastageWeight(BigDecimal totalWastageWeight) {
		this.totalWastageWeight = totalWastageWeight;
	}

	public BigDecimal getTotalArriveWeight() {
		return totalArriveWeight;
	}

	public void setTotalArriveWeight(BigDecimal totalArriveWeight) {
		this.totalArriveWeight = totalArriveWeight;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getBranchGroupName() {
		return branchGroupName;
	}

	public void setBranchGroupName(String branchGroupName) {
		this.branchGroupName = branchGroupName;
	}

	public Byte getProjectType() {
		return projectType;
	}

	public void setProjectType(Byte projectType) {
		this.projectType = projectType;
	}

	public Byte getTransportType() {
		return transportType;
	}

	public void setTransportType(Byte transportType) {
		this.transportType = transportType;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getCargoSpecifications() {
		return cargoSpecifications;
	}

	public void setCargoSpecifications(String cargoSpecifications) {
		this.cargoSpecifications = cargoSpecifications;
	}

	public BigDecimal getCargoPrice() {
		return cargoPrice;
	}

	public void setCargoPrice(BigDecimal cargoPrice) {
		this.cargoPrice = cargoPrice;
	}

	public Byte getValuationUnitName() {
		return valuationUnitName;
	}

	public void setValuationUnitName(Byte valuationUnitName) {
		this.valuationUnitName = valuationUnitName;
	}

	public List getOrders() {
		return orders;
	}

	public void setOrders(List orders) {
		this.orders = orders;
	}
    
}
