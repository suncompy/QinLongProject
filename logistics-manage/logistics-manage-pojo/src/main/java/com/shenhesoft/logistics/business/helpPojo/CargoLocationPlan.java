package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.util.List;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
/**
 * @description 货位平面图
 * 
 * @author shilvfei
 * 
 * @date 2018年3月5日
 */
public class CargoLocationPlan implements Serializable {
	private Integer cargoLocaionId;
	private String cargoLocationName;
	private List<TbStock> stocks;
	private Integer length;
	
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getCargoLocaionId() {
		return cargoLocaionId;
	}
	public void setCargoLocaionId(Integer cargoLocaionId) {
		this.cargoLocaionId = cargoLocaionId;
	}
	public String getCargoLocationName() {
		return cargoLocationName;
	}
	public void setCargoLocationName(String cargoLocationName) {
		this.cargoLocationName = cargoLocationName;
	}
	public List<TbStock> getStocks() {
		return stocks;
	}
	public void setStocks(List<TbStock> stocks) {
		this.stocks = stocks;
	}
}
