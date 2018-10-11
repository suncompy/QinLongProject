package com.shenhesoft.logistics.common.util;

import java.util.Random;

/**
 * 
 * <p>Title: logistics-common</p>
 * <p>Description: 生成随机数</p>
 * <p>Company: shenhesoft</p> 
 * @version 1.0
 */
public class RandomNum {
	private static Random rnd = new Random();  
	public static Integer getRandomNumber(int digCount) {  
	    StringBuilder sb = new StringBuilder(digCount);  
	    for(int i=0; i < digCount; i++)  
	        sb.append((char)('0' + rnd.nextInt(10)));  
	    int num = Integer.parseInt(sb.toString());
	    return num;
	}  
	
	
	/**
	 * @description 获取验证码
	 * @date 2017年11月28日
	 * @author shilvfei
	 * @return
	 */
	public static Integer getCheckCode() {  
		int result = (int) ((Math.random()*9+1)*1000);
		return result;
	}  
	
	
	/**
	 * @description 生成随机id
	 * @date 2017年11月28日
	 * @author shilvfei
	 * @return
	 */
	public static Integer getUUid(){
		int result = (int) ((Math.random()*9+1)*100000);
		return result;
	}
}
