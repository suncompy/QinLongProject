package com.shenhesoft.logistics.enterprise.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.shenhesoft.logistics.common.GeneralResponse;

/**
 * 火运app service 接口
 * @author dusd
 * @date 2017年12月27日
 */
public interface AppTbTrainOrderService {

	/**
	 * 查询不同状态下的火运运单列表
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * @param pageLimit
	 * @return
	 */
	GeneralResponse listAllTbTrainOrderDifferentStatus(Map<String, String> dataMap, Integer pageLimit) throws Exception;

	/**
	 * 通过火运主键查询火运运单信息
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse viewTbTrainOrderById(Map<String, String> dataMap) throws Exception;

	/**
	 * 在途追踪-查询某订单位置信息列表
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listHistoryLocationTbTrainOrder(Map<String, String> dataMap) throws Exception;

	/**
	 * 在途追踪-保存某订单位置信息
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse saveLocationTbTrainOrderApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 运单车型车号列表
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listTrainCargoApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 等待发运,在途运载,等待回单更新状态
	 * 状态位 4-等待发运 5-在途运载 10-等待回单
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse updateTrainOrderStatusByParamApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 等待回单-保存发货运单和到货运单信息
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @param session 
	 * @return
	 * @throws Exception
	 */
	GeneralResponse saveWaybillReceiptTrainOrderApp(Map<String, String> dataMap, HttpSession session) throws Exception;

	/**
	 * 通过火运运单id查询货场列表
	 * type 0-装车货场 1-到货货场
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listTbFreightYardByTrainOrderIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 通过货场id查询货位列表
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listTbCargoLocationByYardIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 查询火车车型列表
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listTbTrainTypeApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 查询运单装车信息列表
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listTbTrainOrderCargoPalceByTrainOrderIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 保存运单装车信息
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @param session 
	 * @return
	 * @throws Exception
	 */
	GeneralResponse saveTbTraninOrderTruckLoadApp(Map<String, String> dataMap, HttpSession session) throws Exception;

	/**
	 * 通过运单和车型查询所有的车号信息
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listTbTrainOrderCargoPalceByTrainOrderIdCarTypeId(Map<String, String> dataMap) throws Exception;

	/**
	 * 通过装车信息主键查询详细装车信息
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse viewTbTrainOrderCargoPalceById(Map<String, String> dataMap) throws Exception;

	/**
	 * 保存到货信息
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @param session 
	 * @return
	 * @throws Exception
	 */
	GeneralResponse saveTbTrainOrderArrivalApp(Map<String, String> dataMap, HttpSession session) throws Exception;

	/**
	 * 通过项目信息计算库存数量
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse viewTbTrainOrderRepertoryByProjectIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 计算预计支出金额
	 * 请车数 * 运费合计（干线费用）
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse viewPredictMoneyByProjectIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 保存新建请车信息
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse saveTbTrainOrderApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 火运 - 保存承运车数
	 * @author dusd
	 * @date 2018年1月3日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse saveTbTrainOrderAdmitCarNum(Map<String, String> dataMap) throws Exception;

	/**
	 * 火运查询项目列表
	 * @author liangdeng
	 * @date 2018年1月22日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse trainListTbProjectApp(Map<String, String> dataMap);

	/**
	 * 等待装车查询集装箱号列表
	 * @author liangdeng
	 * @date 2018年3月10日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse trainAppContainerList(Map<String, String> dataMap);


}
