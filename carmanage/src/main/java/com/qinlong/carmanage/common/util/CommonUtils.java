package com.qinlong.carmanage.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonUtils extends DateUtils {

	/**
	 * @author fangjt
	 * @description 生成年月日时分秒+5位随机数的随机码，用于表中id的生成
	 * @date 2018年9月20日
	 * @param
	 * @return String
	 * */
	
	public static String buildRandomUUID(){
		String uuid = "";
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyyMMdd24HHmmSS");
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
		uuid = str+rannum;
		return uuid;
	}
	
	
	public static void main(String[] args) {

	}
}
