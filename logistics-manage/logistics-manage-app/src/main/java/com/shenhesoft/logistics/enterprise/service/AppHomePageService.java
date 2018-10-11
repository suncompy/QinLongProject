package com.shenhesoft.logistics.enterprise.service;

import java.util.Map;

import com.shenhesoft.logistics.common.GeneralResponse;

/**
 * app 首页 service 接口
 * 当月完成订单 当日完成订单 当日在途订单 待调度审核数量 待到货确认数量 待计费确认数量
 * @author dusd
 * @date 2017年12月28日
 */
public interface AppHomePageService {

	/**
	 * 运单统计相关数据 首页展示
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse allOrderStatisticsApp(Map<String, String> dataMap) throws Exception;

}
