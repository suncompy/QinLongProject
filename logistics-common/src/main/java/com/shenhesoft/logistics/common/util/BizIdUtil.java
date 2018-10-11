package com.shenhesoft.logistics.common.util;

/**
 * 业务主键.
 * <p>
 * <a href="BizIdUtil.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuJiefeng
 * @date 2018-04-13
 * @version 1.0.0
 * @since 1.0.0
 */
public class BizIdUtil {
  /**
   * 字符串转数字001转1.
   * 
   * @param "001"
   * @return int 返回类型
   */
  public static int toInt(String str) {
    return Integer.valueOf(str).intValue();
  }

  /**
   * 3位及以内数字转字符串，例如1转001.
   * 
   * @param
   * @return String 返回类型
   */
  public static String toStr(Integer i) {
    if ((i + "").length() == 1) {
      return "00" + i;
    } else if ((i + "").length() == 2) {
      return "0" + i;
    } else if ((i + "").length() == 3) {
      return "" + i;
    }
    return "" + i;
  }

  /**
   * 截取后3位数字+1后，拼接。例如D001变D002.
   * 
   * @param
   * @return String 返回类型
   */
  public static String sub3Add1(String str) {
	if(null==str) {
		return "";
	}
    String sub3 = str.substring(str.length() - 3, str.length());
    int i = toInt(sub3);
    String lit = toStr(i+1);
    String strNew = str.substring(0, str.length() - 3) + lit;
    return strNew;
  }
}
