// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description 请车单号随机数
 *
 * @author LiangDeng
 *
 * @date 2017年12月26日
 */
public class PleaseCarNumUtil {
	
	public static String TimeDay(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date nowTime=new Date();
		int random=(int) (Math.random()*(9999-1000+1))+1000;
		String time=(sdf.format(nowTime)+String.valueOf(random)).trim();
		return time;
	}
	
	public static String serialNum(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date nowTime=new Date();
		String time=(sdf.format(nowTime)).trim();
		return time;
	}
}
