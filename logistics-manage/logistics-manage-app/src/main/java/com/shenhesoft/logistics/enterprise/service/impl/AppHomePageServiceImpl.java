package com.shenhesoft.logistics.enterprise.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.common.GeneralResponse;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.enterprise.service.AppHomePageService;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.user.UserService;

/**
 * app 首页 service 实现
 * @author dusd
 * @date 2017年12月28日
 */
@Service
public class AppHomePageServiceImpl implements AppHomePageService {
	
	/**
	 * 企业用户 service 接口
	 */
	@Autowired
	private UserService userService;
	/**
	 * 短驳运单 mapper
	 */
	@Autowired
	private TbOrderMapper tbOrderMapper;

	@Override
	public GeneralResponse allOrderStatisticsApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);
		if(tbSystemUser == null) {
			generalResponse.setState(Constants.NO);  generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-首页展示失败");
			return generalResponse;
		}
		//当月完成订单 
		Integer completeOrderTodayNum = tbOrderMapper.completeTbOrderNumUserIdMonth(tbSystemUser.getId());
		//当日完成订单 
		Integer completeOrderMonthNum = tbOrderMapper.completeTbOrderNumUserIdDay(tbSystemUser.getId());
		//当日在途订单
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", tbSystemUser.getId());
		paramMap.put("status", "'1','2','3','4','5'");
		Integer onRoadOrderTodayNum = tbOrderMapper.differentStatusTbOrderNumUserId(paramMap);
		//待调度审核数量 
		paramMap.put("status", "'1'");
		Integer waitDispatchOrderNum = tbOrderMapper.differentStatusTbOrderNumUserId(paramMap);
		//待到货确认数量 
		paramMap.put("status", "'4'");
		Integer adviceAffirmOrderNum = tbOrderMapper.differentStatusTbOrderNumUserId(paramMap);
		//待计费确认数量
		paramMap.put("status", "'6'");
		Integer waitBillingOrderNum = tbOrderMapper.differentStatusTbOrderNumUserId(paramMap);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("completeOrderTodayNum",completeOrderTodayNum);
		map.put("completeOrderMonthNum",completeOrderMonthNum);
		map.put("onRoadOrderTodayNum",onRoadOrderTodayNum);
		map.put("waitDispatchOrderNum",waitDispatchOrderNum);
		map.put("adviceAffirmOrderNum",adviceAffirmOrderNum);
		map.put("waitBillingOrderNum",waitBillingOrderNum);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("加载首页数据成功");
		generalResponse.setObj(map);
		return generalResponse;
	}
	
	/**
	 * 通过用户id查询用户信息
	 * @author dusd
	 * @date 2017年12月28日
	 * @return
	 */
	private TbSystemUser viewTbSystemUserDataMap(Map<String, String> dataMap) {
		if(dataMap == null)
			return null;
		String strUserId = dataMap.get("userId");
		if (StringUtil.isEmpty(strUserId)) {
			return null;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		return userService.getTbSystemUserById(userId);
	}

}
