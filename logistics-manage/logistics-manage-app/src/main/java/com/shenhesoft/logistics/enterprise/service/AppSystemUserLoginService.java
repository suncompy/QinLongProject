package com.shenhesoft.logistics.enterprise.service;

import java.util.Map;

import com.shenhesoft.logistics.common.GeneralResponse;

/**
 * app端 客户登录 service 接口
 * @author dusd
 * @date 2017年12月24日
 */
public interface AppSystemUserLoginService {

	/**
	 * 企业用户通过用户名、密码在app端登录
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse systemUserDoLogin(Map<String, String> dataMap) throws Exception;

	/**
	 * 通过注册时手机号发送验证码
	 * @author dusd
	 * @date 2018年1月3日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse viewVerificationByregisterPhoneApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 用户修改密码
	 * @author dusd
	 * @date 2018年1月4日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse changeTbSystemUserPasswordApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 用户修改手机号码
	 * @author dusd
	 * @date 2018年1月4日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse changeTbSystmUserPhoneApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 用户修改头像
	 * @author dusd
	 * @date 2018年1月4日
	 * @param dataMap
	 * @return
	 */
	GeneralResponse changeTbSystmUserIconApp(Map<String, String> dataMap);

}
