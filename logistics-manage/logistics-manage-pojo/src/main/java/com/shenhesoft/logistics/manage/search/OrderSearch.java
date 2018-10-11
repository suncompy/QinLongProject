package com.shenhesoft.logistics.manage.search;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2018年1月11日
 */
public class OrderSearch extends TbOrder{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3615397244801727492L;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date beginDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date arriveThingDate;
	
	/**
	 * 状态查询条件 多个状态
	 */
	private List<Byte> statusList;

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
	
	public Date getArriveThingDate() {
		return arriveThingDate;
	}

	public void setArriveThingDate(Date arriveThingDate) {
		this.arriveThingDate = arriveThingDate;
	}

	public List<Byte> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Byte> statusList) {
		this.statusList = statusList;
	}
	
}
