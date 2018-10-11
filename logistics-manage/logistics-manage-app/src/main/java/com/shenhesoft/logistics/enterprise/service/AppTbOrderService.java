package com.shenhesoft.logistics.enterprise.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.shenhesoft.logistics.common.GeneralResponse;

/**
 * app 短驳运单 service 接口
 * @author dusd
 * @date 2017年12月23日
 */
public interface AppTbOrderService {

	/**
	 * 调度审核（等待调度）列表
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @param pageLimit 每页显示条数 
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listTbOrderWaitDispatchApp(Map<String, String> dataMap, Integer pageLimit) throws Exception;

	/**
	 * 企业用户驳回运单
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse turndownTbOrderApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 企业 核准操作（调度)
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse approvalTbOrderApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 查询驳回运单数据
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listTbOrderTurndownApp(Map<String, String> dataMap, Integer pageLimit) throws Exception;

	/**
	 * 还原被驳回的运单
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse restoreTbOrderTurndownApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 查询不同状态的运单列表 如果是多种状态 用逗号隔开
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @param pageLimit
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listAllTbOrderDifferentStatus(Map<String, String> dataMap, Integer pageLimit) throws Exception;

	/**
	 * 保存发运信息
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @param session 
	 * @return
	 * @throws Exception
	 */
	GeneralResponse saveTbOrderForwardInfo(Map<String, String> dataMap, HttpSession session) throws Exception;

	/**
	 * 取消发运
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse cancelTbOrderForward(Map<String, String> dataMap) throws Exception;

	/**
	 * 货位引导-货场列表
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listGoodsYardApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 根据货场id查询货位列表
	 * @author dusd
	 * @date 2017年12月25日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listTbCargoLocationByFreightIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 货位引导-提交货场货位信息
	 * @author dusd
	 * @date 2017年12月25日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse saveGuideTbOrderApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 异常运单列表
	 * @author dusd
	 * @date 2017年12月25日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listTbOrderAbnormalApp(Map<String, String> dataMap, Integer pageLimit) throws Exception;

	/**
	 * 查询项目列表
	 * @author dusd
	 * @date 2017年12月25日
	 * @param dataMap
	 * @param pageLimit
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listTbProjectApp(Map<String, String> dataMap, Integer pageLimit) throws Exception;

	/**
	 * 通过项目id 查询项目详细信息
	 * @author dusd
	 * @date 2017年12月26日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse viewTbProjectByIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 通过项目id查询车辆列表
	 * @author dusd
	 * @date 2017年12月26日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listCarByPorjectIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 通过driverId查询汽车信息
	 * @author dusd
	 * @date 2017年12月26日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse viewCarInfoByDriverIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 保存集装箱运单/散堆装运单信息 
	 * 新建运单
	 * @author dusd
	 * @date 2017年12月26日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse saveTbOrderApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 保存等待回单信息
	 * @author dusd
	 * @date 2017年12月26日
	 * @param dataMap
	 * @param session 
	 * @return
	 * @throws Exception
	 */
	GeneralResponse receipterTbOrderApp(Map<String, String> dataMap, HttpSession session) throws Exception;

	/**
	 * 通过牌照查询汽车信息
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse viewTbOrderCarDetailByPlateNumber(Map<String, String> dataMap) throws Exception;

	/**
	 * 查询异常原因列表
	 * @author dusd
	 * @date 2018年1月6日
	 * @param dataMap
	 * @param orderExceptionReason 
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listOrderExceptionReasonApp(Map<String, String> dataMap, String orderExceptionReason) throws Exception;

	/**
	 * 保存异常原因信息
	 * @author dusd
	 * @date 2018年1月6日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse saveTbExceptionMsgApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 查询短驳异常列表
	 * @author dusd
	 * @date 2018年1月6日
	 * @param dataMap
	 * @param pageLimit
	 * @return
	 */
	GeneralResponse listShortExceptionInfoByUserIdApp(Map<String, String> dataMap, Integer pageLimit);

	/**
	 * app端通过异常id查询异常信息
	 * @author dusd
	 * @date 2018年1月6日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse viewTbExceptionMsgByIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 通过运单id查询运单详细信息
	 * @author dusd
	 * @date 2018年1月9日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse viewTbOrderByIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 通过运单id查询运单异常信息
	 * @author dusd
	 * @date 2018年1月9日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse viewTbExceptionMsgByOrderIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 驳回原因列表
	 * @author dusd
	 * @date 2018年1月9日
	 * @param dataMap
	 * @param turndownReason
	 * @return
	 */
	GeneralResponse listTurndownReasonApp(Map<String, String> dataMap, String turndownReason);

	/**
	 * 短驳查询项目列表
	 * @author LiangDeng
	 * @date 2018年1月22日
	 * @param dataMap
	 * @param turndownReason
	 * @return
	 */
	GeneralResponse bulkListTbProjectApp(Map<String, String> dataMap);

	/**
	 * @description 获取集装箱号
	 * @date 2018年3月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	GeneralResponse getContainerNum(Map<String, String> dataMap);

	/**
	 * @description 等待调度获取货场货位
	 * @date 2018年3月8日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	GeneralResponse listFreYardAppOfDispatch(Map<String, String> dataMap);

	/**
	 * @description 等待调度提交货场货位
	 * @date 2018年3月8日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	GeneralResponse saveDisptachTbOrderApp(Map<String, String> dataMap);

	/**
	 * @description 新建运单获取货场货位
	 * @date 2018年3月9日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	GeneralResponse listFreYardAppOfAdd(Map<String, String> dataMap);

	/**
	 * @description 更新运单状态
	 * @date 2018年3月9日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	GeneralResponse appUpdateOrderStatus(Map<String, String> dataMap);

	/**
	 * @description 新建运单查看是否发布任务
	 * @date 2018年3月10日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	GeneralResponse appAddOrderIsPublish(Map<String, String> dataMap);

	/**
	 * 短驳首页运单数量统计
	 * @author liangdeng
	 * @date 2018年3月30日
	 * @param dataMap
	 * @return
	 */
	GeneralResponse appOrderCounts(Map<String, String> dataMap);

	GeneralResponse listTbOrderExceptionApp(Map<String, String> dataMap, Integer pageLimit) throws Exception;

	GeneralResponse appWaitBillentCounts(Map<String, String> dataMap);

}
