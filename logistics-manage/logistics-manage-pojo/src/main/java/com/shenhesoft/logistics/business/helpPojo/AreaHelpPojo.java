package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月21日
 */
public class AreaHelpPojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2534167966307771915L;
	private String province;
	private String city;
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
	
	@Override
    public boolean equals(Object o) {  
        if (o == this) 
        	return true;  
        if (!(o instanceof AreaHelpPojo)) {  
            return false;  
        }  
        AreaHelpPojo area = (AreaHelpPojo) o;  
        return area.province.equals(province) &&  
        		area.city.equals(city) &&  
        		area.district.equals(district);  
    }
  
    //重写hashCode方法，把对象的province和city和district属性转为一个字符串，返回次字符串的hashCode值  
    @Override  
    public int hashCode() {  
        String id = this.province + this.city + this.district;  
        return id.hashCode();  
    }  
	
}
