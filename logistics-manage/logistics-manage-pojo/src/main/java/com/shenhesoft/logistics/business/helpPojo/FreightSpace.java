/**
 * 
 */
package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.util.List;

import com.shenhesoft.logistics.business.pojo.stock.TbStock;

/**
 * @description 仓位平面图
 * 
 * @date 2017年12月29日
 * 
 * @author shilvfei
 *
 */
public class FreightSpace extends TbStock implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2481412754854066633L;
	
	 /** 货物品名
    */
	private String cargoName;
	
	/**
	 * 货场图片url
	 * */
	private String img;

	private List<FreightSpace> stockList;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public List<FreightSpace> getStockList() {
		return stockList;
	}

	public void setStockList(List<FreightSpace> stockList) {
		this.stockList = stockList;
	}
	
}
