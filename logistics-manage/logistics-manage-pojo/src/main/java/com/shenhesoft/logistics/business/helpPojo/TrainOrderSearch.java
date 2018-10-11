package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;

/**
 * @description:火运管理条件搜索
 * 
 * @author shilvfei
 * 
 * @date 2018年1月9日
 */

public class TrainOrderSearch extends TbTrainOrder implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3701454257019999170L;

	
	/**
	 * 时间段:起始时间 
	 */
	private Date beginDate;
	
	/**
	 * 时间段:终止时间
	 */
	private Date endDate;
	
	/**
	 * 运单列表展示： 1、运单列表 2、异常运单 3、历史运单 4、运单回收站
	 */
	private byte orderStatus;
	
	private Integer cargoId;
	
	/**
	 * 状态查询条件 多个状态
	 */
	private List<Byte> statusList;
	
	public Integer getCargoId() {
		return cargoId;
	}

	public void setCargoId(Integer cargoId) {
		this.cargoId = cargoId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public byte getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(byte orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<Byte> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Byte> statusList) {
		this.statusList = statusList;
	}
	
	
	
}
