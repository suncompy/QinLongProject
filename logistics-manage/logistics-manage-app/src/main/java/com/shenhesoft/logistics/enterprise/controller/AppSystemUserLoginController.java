package com.shenhesoft.logistics.enterprise.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shenhesoft.logistics.common.GeneralResponse;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.enterprise.service.AppSystemUserLoginService;

/**
 * app端 客户登录 controller
 * @author dusd
 * @date 2017年12月23日
 */
@RestController
@RequestMapping("app/login/")
public class AppSystemUserLoginController {
	
	/**
	 * app端 客户登录 service 接口
	 */
	@Autowired
	private AppSystemUserLoginService appSystemUserLoginService;
	
	/**
	 * 企业用户通过用户名、密码在app端登录
	 * @author dusd
	 * @date 2017年12月23日
	 * @param userName 用户名
	 * @param password 密码
	 * @return
	 */
	@RequestMapping(value = "/systemUserDoLogin", method = RequestMethod.POST)
	public GeneralResponse systemUserDoLogin(@RequestBody Map<String, String> dataMap) {
		try {
			return appSystemUserLoginService.systemUserDoLogin(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("企业app登录异常");
			e.printStackTrace();
			return generalResponse;
		}
	} 
	
	/**
	 * 通过注册时手机号发送验证码
	 * @author dusd
	 * @date 2018年1月3日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/viewVerificationByregisterPhoneApp", method = RequestMethod.POST)
	public GeneralResponse viewVerificationByregisterPhoneApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appSystemUserLoginService.viewVerificationByregisterPhoneApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("通过注册时手机号发送验证码异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 用户修改密码
	 * @author dusd
	 * @date 2018年1月4日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/changeTbSystemUserPasswordApp", method = RequestMethod.POST)
	public GeneralResponse changeTbSystemUserPasswordApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appSystemUserLoginService.changeTbSystemUserPasswordApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("用户修改密码异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 用户修改手机号码
	 * @author dusd
	 * @date 2018年1月4日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/changeTbSystmUserPhoneApp", method = RequestMethod.POST)
	public GeneralResponse changeTbSystmUserPhoneApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appSystemUserLoginService.changeTbSystmUserPhoneApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("用户修改手机号码异常");
			e.printStackTrace();
			return generalResponse;
		}
		
	}

	/**
	 * 用户修改头像
	 * @author dusd
	 * @date 2018年1月4日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/changeTbSystmUserIconApp", method = RequestMethod.POST)
	public GeneralResponse changeTbSystmUserIconApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appSystemUserLoginService.changeTbSystmUserIconApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("用户修改头像异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
}
