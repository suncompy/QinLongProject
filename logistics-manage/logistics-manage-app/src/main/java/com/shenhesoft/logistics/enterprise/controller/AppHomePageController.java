package com.shenhesoft.logistics.enterprise.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shenhesoft.logistics.common.GeneralResponse;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.enterprise.service.AppHomePageService;

/**
 * app 首页 controller
 * @author dusd
 * @date 2017年12月28日
 */
@RestController
@RequestMapping("app/homePage/")
public class AppHomePageController {
	
	/**
	 * app 首页 service 接口
	 */
	@Autowired
	private AppHomePageService appHomePageService;
	
	/**
	 * 首页运单统计 当月完成订单 当日完成订单 当日在途订单 待调度审核数量 待到货确认数量 待计费确认数量
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/allOrderStatisticsApp", method = RequestMethod.POST)
	public GeneralResponse allOrderStatisticsApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appHomePageService.allOrderStatisticsApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("企业app登录异常");
			e.printStackTrace();
			return generalResponse;
		}
	} 

}
