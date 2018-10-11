package com.shenhesoft.logistics.common.util;
/**
 * @description
 * 
 * @author shilvfei
 * 
 * @date 2018年1月24日
 */
public class AreaCodeUtils {
	
	/**
	 * @description 获取区域
	 * @date 2018年1月18日
	 * @author shilvfei
	 * @param
	 * @return
	 */
	public static String getAreaCode(String adressCode) {
		if (adressCode != null) {
			String[] split = adressCode.split(",");
			if (split != null && split.length >= 2) {
				adressCode = split[0]+split[1];
				if (split.length == 3 && !split[2].equals("null")) {
					adressCode = adressCode + split[2];
				}
			}
		}
		return adressCode;
	}
	
	/**
	 * @description 获取省份
	 * @date 2018年1月18日
	 * @author shilvfei
	 * @param
	 * @return
	 */
	public static String getProvince(String adressCode) {
		if (StringUtils.isNotBlank(adressCode)) {
			String[] split = adressCode.split(",");
			if (split == null || split.length == 0) {
				return "";
			}
			if ( split.length == 2 || split.length == 3) {
				return split[0];
			}
		}
		return "";
	}
	
	/**
	 * @description 获取市
	 * @date 2018年1月18日
	 * @author shilvfei
	 * @param
	 * @return
	 */
	public static String getCity(String adressCode) {
		if (StringUtils.isNotBlank(adressCode)) {
			String[] split = adressCode.split(",");
			if (split == null || split.length == 0) {
				return "";
			}
			if ( split.length == 2) {
				return split[0];
			}
			if ( split.length == 3) {
				return split[1];
			}
		}
		return "";
	}
	
	/**
	 * @description 获取区域
	 * @date 2018年1月18日
	 * @author shilvfei
	 * @param
	 * @return
	 */
	public static String getDistrict(String adressCode) {
		if (StringUtils.isNotBlank(adressCode)) {
			String[] split = adressCode.split(",");
			if (split == null || split.length == 0) {
				return "";
			}
			if ( split.length == 2) {
				return split[1];
			}
			if ( split.length == 3) {
				return split[2];
			}
		}
		return "";
	}
}
