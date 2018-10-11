package com.qinlong.carmanage.common.util;

import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	/**
    * 获取现在时间
    * 
    * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
    */
	public static Date getNowDate() {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(currentTime);
	    ParsePosition pos = new ParsePosition(0);
	    Date currentTime_2 = formatter.parse(dateString, pos);
	    return currentTime_2;
	 }
	
	/**
	 * 获取当前时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd
	 */
	public static Date getNowDateToSort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 返回短时间字符串格式HH:mm:ss (小时:分:秒 )
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 将字符串 转换为时间格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return Date
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return String
	 */
	public static String dateToStrLong(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @return String
	 */
	public static String dateToStr(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return String
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 计算两个时间之间相差多少秒（用于验证码过期验证）
	 * 
	 * @param starttime(最初时间)、endtime(最终时间)；字符串类型，格式为yyyy-MM-dd
	 *            HH:mm:ss
	 * @return int （返回相差的秒数）
	 */
	public static int bewteenStartAndEnd(String starttime, String endtime) throws Exception {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int x = 0;
		if(starttime!=null && !"".equals(starttime) && endtime!=null && !"".equals(endtime)){
			Date begin = dfs.parse(starttime);
			Date end = dfs.parse(endtime);
			long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
			x = (int) between;
		}else{
			x=1000; 
		}
		return x;
	}

	public static void main(String[] args) throws Exception {
	}
}
