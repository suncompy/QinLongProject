package com.shenhesoft.logistics.manage.helpPojo;

import java.io.Serializable;

import com.shenhesoft.logistics.manage.pojo.stationBusiness.TbStationBusiness;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月13日
 */
public class TbStationBusinessHelp extends TbStationBusiness implements Serializable{
	private String province;
	private String city="";
	private String district;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
}
