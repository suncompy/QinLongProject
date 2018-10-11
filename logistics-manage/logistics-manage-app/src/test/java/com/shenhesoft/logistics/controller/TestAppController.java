package com.shenhesoft.logistics.controller;

import java.util.HashMap;
import java.util.Map;

import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.httpclient.HttpClientUtil;

public class TestAppController {
	
	/**
	 * 访问地址
	 */
	//private static final String URL  = "http://localhost:8082/";
	
	private static final String URL  = "http://192.168.2.182:8088/logistics-manage-web/";
	/**
	 * Controller 空间 
	 */
	private static String nameSpace = ""; 
	/**
	 * 方法名
	 */
	private static String methodName = "";
	
	
	public static void main(String[] args){  
		//企业用户登录测试
	//	testSystemUserDoLogin();
		//调度审核（等待调度）列表
//		testListTbOrderWaitDispatchApp();
		//企业用户驳回运单
//		testTurndownTbOrderApp();
		//企业 核准操作（调度)
//		testApprovalTbOrderApp();
		//查询驳回列表
//		testListTbOrderTurndownApp();
		//还原被驳回的运单
//		testRestoreTbOrderTurndownApp();
		//查询不同状态的运单列表 
		//testListAllTbOrderDifferentStatus();
		//保存发运信息
//		testSaveTbOrderForwardInfo();
		//取消发运
//		testCancelTbOrderForward();
		//查询全部货场
//		testListGoodsYardApp();
		//根据货场信息查询货位信息列表
//		testListTbCargoLocationByFreightIdApp();
		//货位引导-提交货场货位信息
//		testSaveGuideTbOrderApp();
		//查询异常运单列表
	//	testListTbOrderAbnormalApp();
		//查询项目列表
//		testListTbProjectApp();
		//通过项目id查询项目详细信息
//		testViewTbProjectByIdApp();
		//根据项目id查询车辆列表
//		testListCarByPorjectIdApp();
		//通过driverId查询汽车信息
		//testViewCarInfoByDriverIdApp();
		//新建运单
//		testSaveTbOrderApp();
		//等待回单
//		testReceipterTbOrderApp();
		//查询不同状态下的火运运单列表
//		testListAllTbTrainOrderDifferentStatus();
		//通过火运运单id查询火运运单信息
//		testViewTbTrainOrderById();
		//通过火运运单id查询火运信息
//		testViewTbOrderCarDetailByPlateNumber();
		//首页数据加载
//		testAllOrderStatisticsApp();
		//项目查询-项目运营管理列表
//		testListProjectOperationApp();
		//在途追踪-查询某订单位置信息列表
//		testListHistoryLocationTbTrainOrder();
		//在途追踪-保存某订单位置信息
//		testSaveLocationTbTrainOrderApp();
		//运单车型车号列表
//		testListTrainCargoApp();
		//等待发运,在途运载,等待回单更新状态 状态位 4-等待发运 5-在途运载 10-等待回单
//		testUpdateTrainOrderStatusByParamApp();
		//等待回单-保存发货运单和到货运单信息
//		testSaveWaybillReceiptTrainOrderApp();
		//通过火运运单id查询货场列表
//		testListTbFreightYardByTrainOrderIdApp();
		// 通过货场id查询货位列表
//		testListTbCargoLocationByYardIdApp();
		//查询火车车型列表
//		testListTbTrainTypeApp();
		//查询运单装车信息列表
//		testListTbTrainOrderCargoPalceByTrainOrderIdApp();
		//保存运单装车信息
//		testSaveTbTraninOrderTruckLoadApp();
		//通过运单和车型查询所有的车号信息
//		testListTbTrainOrderCargoPalceByTrainOrderIdCarTypeId();
		//通过装车信息主键查询详细装车信息
//		testViewTbTrainOrderCargoPalceById();
		//保存到货信息
//		testSaveTbTrainOrderArrivalApp();
		//通过项目信息计算库存数量
//		testViewTbTrainOrderRepertoryByProjectIdApp();
		//计算预计支出金额
//		testViewPredictMoneyByProjectIdApp();
		//保存新建请车信息
//		testSaveTbTrainOrderApp();
		//库存调整 - 查询某项目的所属站点列表
//		testListSiteProjectApp();
		//库存调整 - 通过站点id查询所有的货场
//		testListFreightYardBySiteIdApp();
		//库存调整 - 查询某货场下的某项目的货位信息
//		testListStockByFreightYardIdApp();
		// 火运 - 保存承运车数
//		testSaveTbTrainOrderAdmitCarNum();
		//通过项目id查询仓位平面图
//		testListCargoLocationImgByProjectId();
		//发送验证码
//		testViewVerificationByregisterPhoneApp();
		//用户修改密码
//		testChangeTbSystemUserPasswordApp();
		//用户修改手机号码
//		testChangeTbSystmUserPhoneApp();
		//项目任务分配 APP
//		testSaveTbProjectDistributionApp();
		//查询可分配项目列表
//		testListTbProjectForDistributionApp();
		//暂停项目分配
//		testStopTbProjectDistributionApp();
		//开始项目分配
//		testBeginTbProjectDistributionApp();
		//库存调整 - 保存调整后的库存信息
//		testChangeStockApp();
		//查询异常原因列表
//		testListOrderExceptionReasonApp();
		//保存异常原因信息
//		testSaveTbExceptionMsgApp();
		// 查询短驳异常列表
//		testListShortExceptionInfoByUserIdApp();
		//app端通过异常id查询异常信息
//		testViewTbExceptionMsgByIdApp();
		// 通过运单id查询运单详细信息
//		testViewTbOrderByIdApp();
		//通过运单id查询运单异常信息
		//	testViewTbExceptionMsgByOrderIdApp();
		//驳回原因列表
	//	testListTurndownReasonApp();
		//测试定位 根据项目获取车辆信息
//		testListCarByPorjectIdApp();
		//testTrainContainer();
		//testOrderContainer();
		testOrder();
	} 
	
	/**
	 * 驳回原因列表
	 * @author dusd
	 * @date 2018年1月9日
	 */
	public static void testListTurndownReasonApp() {
		nameSpace = "app/order/";
		methodName = "listTurndownReasonApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过运单id查询运单异常信息
	 * @author dusd
	 * @date 2018年1月9日
	 */
	public static void testViewTbExceptionMsgByOrderIdApp() {
		nameSpace = "app/order/";
		methodName = "viewTbExceptionMsgByOrderIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("orderId", "10");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过运单id查询运单详细信息
	 * @author dusd
	 * @date 2018年1月9日
	 */
	public static void testViewTbOrderByIdApp() {
		nameSpace = "app/order/";
		methodName = "viewTbOrderByIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("orderId", "10");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询短驳异常列表
	 * @author dusd
	 * @date 2018年1月6日
	 */
	public static void testViewTbExceptionMsgByIdApp() {
		nameSpace = "app/order/";
		methodName = "viewTbExceptionMsgByIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("exceptionId", "3");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询短驳异常列表
	 * @author dusd
	 * @date 2018年1月6日
	 */
	public static void testListShortExceptionInfoByUserIdApp() {
		nameSpace = "app/order/";
		methodName = "listShortExceptionInfoByUserIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询异常原因列表
	 * @author dusd
	 * @date 2018年1月6日
	 */
	public static void testSaveTbExceptionMsgApp() {
		nameSpace = "app/order/";
		methodName = "saveTbExceptionMsgApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "3");
		dataMap.put("orderId", "14");
		dataMap.put("shortTrainFlag", "0");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询异常原因列表
	 * @author dusd
	 * @date 2018年1月6日
	 */
	public static void testListOrderExceptionReasonApp() {
		nameSpace = "app/order/";
		methodName = "listOrderExceptionReasonApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 开始项目分配
	 * @author dusd
	 * @date 2018年1月5日
	 */
	public static void testChangeStockApp() {
		nameSpace = "app/project/";
		methodName = "changeStockApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("stockId", "1");
		dataMap.put("adjustQty", "123");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 开始项目分配
	 * @author dusd
	 * @date 2018年1月5日
	 */
	public static void testBeginTbProjectDistributionApp() {
		nameSpace = "app/project/";
		methodName = "beginTbProjectDistributionApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("projectIds", "144");
		dataMap.put("projectStages", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 暂停项目分配
	 * @author dusd
	 * @date 2018年1月5日
	 */
	public static void testStopTbProjectDistributionApp() {
		nameSpace = "app/project/";
		methodName = "stopTbProjectDistributionApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("projectIds", "144");
		dataMap.put("projectStages", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询可分配项目列表
	 * @author dusd
	 * @date 2018年1月5日
	 */
	public static void testListTbProjectForDistributionApp() {
		nameSpace = "app/project/";
		methodName = "listTbProjectForDistributionApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("page", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 项目任务分配 APP
	 * @author dusd
	 * @date 2018年1月4日
	 */
	public static void testSaveTbProjectDistributionApp() {
		nameSpace = "app/project/";
		methodName = "saveTbProjectDistributionApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("projectId", "1");
		dataMap.put("distributionNum", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 用户修改密码
	 * @author dusd
	 * @date 2018年1月4日
	 */
	public static void testChangeTbSystmUserPhoneApp() {
		nameSpace = "app/login/";
		methodName = "changeTbSystmUserPhoneApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("checkedCode", "1");
		dataMap.put("oldPhoneNum", "1");
		dataMap.put("newPasswordAgain", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 用户修改密码
	 * @author dusd
	 * @date 2018年1月4日
	 */
	public static void testChangeTbSystemUserPasswordApp() {
		nameSpace = "app/login/";
		methodName = "changeTbSystemUserPasswordApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("checkedCode", "1");
		dataMap.put("newPassword", "1");
		dataMap.put("newPasswordAgain", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过注册时手机号发送验证码
	 * @author dusd
	 * @date 2018年1月3日
	 */
	public static void testViewVerificationByregisterPhoneApp() {
		nameSpace = "app/login/";
		methodName = "viewVerificationByregisterPhoneApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("phoneNum", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过项目id查询仓位平面图
	 * @author dusd
	 * @date 2018年1月3日
	 */
	public static void testListCargoLocationImgByProjectId() {
		nameSpace = "app/project/";
		methodName = "listCargoLocationImgByProjectId.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("projectId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 火运 - 保存承运车数
	 * @author dusd
	 * @date 2018年1月3日
	 */
	public static void testSaveTbTrainOrderAdmitCarNum() {
		nameSpace = "app/trainOrder/";
		methodName = "saveTbTrainOrderAdmitCarNum.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("projectId", "1");
		dataMap.put("freightYardId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 库存调整 - 查询某货场下的某项目的货位信息
	 * @author dusd
	 * @date 2018年1月3日
	 */
	public static void testListStockByFreightYardIdApp() {
		nameSpace = "app/project/";
		methodName = "listStockByFreightYardIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("projectId", "1");
		dataMap.put("freightYardId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 库存调整 - 通过站点id查询所有的货场
	 * @author dusd
	 * @date 2018年1月3日
	 */
	public static void testListFreightYardBySiteIdApp() {
		nameSpace = "app/project/";
		methodName = "listFreightYardBySiteIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("siteId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 库存调整 - 查询某项目的所属站点列表
	 * @author dusd
	 * @date 2018年1月3日
	 */
	public static void testListSiteProjectApp() {
		nameSpace = "app/project/";
		methodName = "listSiteProjectApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("projectId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 保存新建请车信息
	 * @author dusd
	 * @date 2018年1月2日
	 */
	public static void testSaveTbTrainOrderApp() {
		nameSpace = "app/trainOrder/";
		methodName = "saveTbTrainOrderApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("projectId", "8");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 计算预计支出金额
	 * @author dusd
	 * @date 2018年1月2日
	 */
	public static void testViewPredictMoneyByProjectIdApp() {
		nameSpace = "app/trainOrder/";
		methodName = "viewPredictMoneyByProjectIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("projectId", "8");
		dataMap.put("pleaseTrainNum", "8");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过项目信息计算库存数量
	 * @author dusd
	 * @date 2018年1月2日
	 */
	public static void testViewTbTrainOrderRepertoryByProjectIdApp() {
		nameSpace = "app/trainOrder/";
		methodName = "viewTbTrainOrderRepertoryByProjectIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("projectId", "8");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过装车信息主键查询详细装车信息
	 * @author dusd
	 * @date 2018年1月2日
	 */
	public static void testSaveTbTrainOrderArrivalApp() {
		nameSpace = "app/trainOrder/";
		methodName = "saveTbTrainOrderArrivalApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("trainOrderId", "8");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过装车信息主键查询详细装车信息
	 * @author dusd
	 * @date 2018年1月2日
	 */
	public static void testViewTbTrainOrderCargoPalceById() {
		nameSpace = "app/trainOrder/";
		methodName = "viewTbTrainOrderCargoPalceById.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("trainOrderId", "8");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询运单装车信息列表
	 * @author dusd
	 * @date 2018年1月2日
	 */
	public static void testListTbTrainOrderCargoPalceByTrainOrderIdCarTypeId() {
		nameSpace = "app/trainOrder/";
		methodName = "listTbTrainOrderCargoPalceByTrainOrderIdCarTypeId.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("trainOrderId", "8");
		dataMap.put("carTypeId", "");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询运单装车信息列表
	 * @author dusd
	 * @date 2018年1月2日
	 */
	public static void testSaveTbTraninOrderTruckLoadApp() {
		nameSpace = "app/trainOrder/";
		methodName = "saveTbTraninOrderTruckLoadApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("trainOrderId", "8");
		dataMap.put("entruckInfoJson", "");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询运单装车信息列表
	 * @author dusd
	 * @date 2018年1月2日
	 */
	public static void testListTbTrainOrderCargoPalceByTrainOrderIdApp() {
		nameSpace = "app/trainOrder/";
		methodName = "listTbTrainOrderCargoPalceByTrainOrderIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("trainOrderId", "8");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询火车车型列表
	 * @author dusd
	 * @date 2018年1月2日
	 */
	public static void testListTbTrainTypeApp() {
		nameSpace = "app/trainOrder/";
		methodName = "listTbTrainTypeApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过火运运单id查询货场列表
	 * @author dusd
	 * @date 2018年1月2日
	 */
	public static void testListTbCargoLocationByYardIdApp() {
		nameSpace = "app/trainOrder/";
		methodName = "listTbCargoLocationByYardIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("yardId", "9");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过火运运单id查询货场列表
	 * @author dusd
	 * @date 2018年1月2日
	 */
	public static void testListTbFreightYardByTrainOrderIdApp() {
		nameSpace = "app/trainOrder/";
		methodName = "listTbFreightYardByTrainOrderIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("trainOrderId", "18");
		dataMap.put("type", "0");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 等待发运,在途运载,等待回单更新状态
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * @return
	 */
	public static void testSaveWaybillReceiptTrainOrderApp() {
		nameSpace = "app/trainOrder/";
		methodName = "saveWaybillReceiptTrainOrderApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("trainOrderId", "8");
		dataMap.put("trainOrderCargoPalceId", "trainOrderCargoPalceId");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 等待发运,在途运载,等待回单更新状态
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * @return
	 */
	public static void testUpdateTrainOrderStatusByParamApp() {
		nameSpace = "app/trainOrder/";
		methodName = "updateTrainOrderStatusByParamApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("trainOrderId", "8");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过牌照查询汽车信息
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * @return
	 */
	public static void testListTrainCargoApp() {
		nameSpace = "app/trainOrder/";
		methodName = "listTrainCargoApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("trainOrderId", "8");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过牌照查询汽车信息
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * @return
	 */
	public static void testSaveLocationTbTrainOrderApp() {
		nameSpace = "app/trainOrder/";
		methodName = "saveLocationTbTrainOrderApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("trainOrderId", "8");
		dataMap.put("location", "123");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过牌照查询汽车信息
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * @return
	 */
	public static void testListHistoryLocationTbTrainOrder() {
		nameSpace = "app/trainOrder/";
		methodName = "listHistoryLocationTbTrainOrder.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过牌照查询汽车信息
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * @return
	 */
	public static void testListProjectOperationApp() {
		nameSpace = "app/project/";
		methodName = "listProjectOperationApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过牌照查询汽车信息
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * @return
	 */
	public static void testAllOrderStatisticsApp() {
		nameSpace = "app/homePage/";
		methodName = "allOrderStatisticsApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过牌照查询汽车信息
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * @return
	 */
	public static void testViewTbOrderCarDetailByPlateNumber() {
		nameSpace = "app/order/";
		methodName = "viewTbOrderCarDetailByPlateNumber.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("plateNumber", "皖H78392");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过火运运单id查询火运运单信息
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * @return
	 */
	public static void testViewTbTrainOrderById() {
		nameSpace = "app/trainOrder/";
		methodName = "viewTbTrainOrderById.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "123");
		dataMap.put("trainOrderId", "2");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询不同状态下的火运运单列表
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * 短驳状态：1:等待调度 2:等待发运 3:在途运载 4:货位引导 5:等待回单 6:等待确认 7:已完成
	 * 火运状态：1:等待承认,2:等待装车,3:等待发运,4:在途运载,5:等待卸货,6:等待回单,7:已完成
	 * @return
	 */
	public static void testListAllTbTrainOrderDifferentStatus() {
		nameSpace = "app/trainOrder/";
		methodName = "listAllTbTrainOrderDifferentStatus.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
        dataMap.put("page", "1");
        dataMap.put("status", "1,2,3,4,5,6,7");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 等待回单
	 * @author dusd
	 * @date 2017年12月26日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @return
	 */
	public static void testReceipterTbOrderApp() {
		nameSpace = "app/order/";
		methodName = "receipterTbOrderApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("userId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过driverId查询汽车信息
	 * @author dusd
	 * @date 2017年12月26日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @return
	 */
	public static void testSaveTbOrderApp() {
		nameSpace = "app/order/";
		methodName = "saveTbOrderApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过driverId查询汽车信息
	 * @author dusd
	 * @date 2017年12月26日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @return
	 */
	public static void testViewCarInfoByDriverIdApp() {
		nameSpace = "app/order/";
		methodName = "viewCarInfoByDriverIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		//dataMap.put("projectId", "12");
		dataMap.put("driverId", "997994");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过项目id查询车辆列表
	 * @author dusd
	 * @date 2017年12月26日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @return
	 */
	public static void testListCarByPorjectIdApp() {
		nameSpace = "app/order/";
		methodName = "listCarByPorjectIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("projectId","200");
		dataMap.put("shortType",null);
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 通过项目id查询项目信息
	 * @author dusd
	 * @date 2017年12月25日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @return
	 */
	public static void testViewTbProjectByIdApp() {
		nameSpace = "app/order/";
		methodName = "viewTbProjectByIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("projectId", null);
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询项目列表
	 * @author dusd
	 * @date 2017年12月25日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @return
	 */
	public static void testListTbProjectApp() {
		nameSpace = "app/order/";
		methodName = "listTbProjectApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("page", null);
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询异常运单列表
	 * @author dusd
	 * @date 2017年12月25日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @return
	 */
	public static void testListTbOrderAbnormalApp() {
		nameSpace = "app/order/";
		methodName = "listTbOrderAbnormalApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("page", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 根据货场信息查询货位信息列表
	 * @author dusd
	 * @date 2017年12月25日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @return
	 */
	public static void testSaveGuideTbOrderApp() {
		nameSpace = "app/order/";
		methodName = "saveGuideTbOrderApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("orderId", "");
		dataMap.put("distributionCargoPlace", null);
		dataMap.put("distributionCargoSite", null);
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 根据货场信息查询货位信息列表
	 * @author dusd
	 * @date 2017年12月25日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @return
	 */
	public static void testListTbCargoLocationByFreightIdApp() {
		nameSpace = "app/order/";
		methodName = "listTbCargoLocationByFreightIdApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		dataMap.put("freightId", "33");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询货场信息
	 * @author dusd
	 * @date 2017年12月25日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @return
	 */
	public static void testListGoodsYardApp() {
		nameSpace = "app/order/";
		methodName = "listGoodsYardApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", "1");
		System.out.println(nameSpace + methodName);
		HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 取消发运
	 * @author dusd
	 * @date 2017年12月24日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @return
	 */
	public static void testCancelTbOrderForward() {
		nameSpace = "app/order/";
		methodName = "cancelTbOrderForward.do";
		Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("userId", "1");
        dataMap.put("orderId", null);
		System.out.println(nameSpace + methodName);
        HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 保存发运信息
	 * @author dusd
	 * @date 2017年12月24日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @param 承运信息 运单上传信息
	 * @return
	 */
	public static void testSaveTbOrderForwardInfo() {
		nameSpace = "app/order/";
		methodName = "saveTbOrderForwardInfo.do";
		Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("userId", "1");
        dataMap.put("orderId", null);
        dataMap.put("sendTare", null);
        dataMap.put("sendGross", null);
        dataMap.put("containerOneSendNet", null);
        dataMap.put("containerTwoSendNet", null);
        dataMap.put("testIndicators", null);
        dataMap.put("img", null);
		System.out.println(nameSpace + methodName);
        HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 查询不同状态的运单列表 
	 * @param userId 当前登录人主键
	 * @param page 当前页 如果为空 则定为1
	 * @param status 运单状态 如果是多种状态 用逗号隔开 
	 * 等待调度 -1 等待发运 - 2 在途运载 - 3 货位引导 - 4 等待回单 - 5 等待计费 - 6 已完成 - 7
	 * @author dusd
	 * @date 2017年12月24日
	 */
	public static void testListAllTbOrderDifferentStatus() {
		nameSpace = "app/order/";
		methodName = "listAllTbOrderDifferentStatus.do";
		Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("userId", "1");
        dataMap.put("page","1");
        dataMap.put("status", "2");
		System.out.println(nameSpace + methodName);
        HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
		
	}
	
	/**
	 * 查询驳回列表
	 * @author dusd
	 * @date 2017年12月24日
	 */
	public static void testRestoreTbOrderTurndownApp() {
		nameSpace = "app/order/";
		methodName = "restoreTbOrderTurndownApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("userId", null);
        dataMap.put("orderId", null);
		System.out.println(nameSpace + methodName);
        HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
		
	}
	
	/**
	 * 查询驳回列表
	 * @author dusd
	 * @date 2017年12月24日
	 */
	public static void testListTbOrderTurndownApp() {
		nameSpace = "app/order/";
		methodName = "listTbOrderTurndownApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("userId", null);
		System.out.println(nameSpace + methodName);
        HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
		
	}
	
	/**
	 * 企业 核准操作（调度)
	 * @author dusd
	 * @date 2017年12月24日
	 */
	public static void testApprovalTbOrderApp() {
		nameSpace = "app/order/";
		methodName = "approvalTbOrderApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("userId", null);
        dataMap.put("orderId", null);
		System.out.println(nameSpace + methodName);
        HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
		
	}

	/**
	 * 企业用户驳回运单
	 * @author dusd
	 * @date 2017年12月23日
	 * @param userId 企业用户id
	 * @param orderId 运单id
	 * @param remark 驳回理由
	 * @return
	 */
	public static void testTurndownTbOrderApp() {
		nameSpace = "app/order/";
		methodName = "turndownTbOrderApp.do";
		Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("userId", null);
        dataMap.put("page", null);
        dataMap.put("remark", null);
		System.out.println(nameSpace + methodName);
        HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 调度审核（等待调度）列表 测试
	 * @author dusd
	 * @date 2017年12月23日
	 * @param userId 当前登录人主键
	 * @param page 当前页  如果为空 则定为1
	 * @return
	 */
	public static void testListTbOrderWaitDispatchApp() {
		nameSpace = "app/order/";
		methodName = "listTbOrderWaitDispatchApp.do";
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("userId", null);
        dataMap.put("page", null);
        HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * 企业用户登录测试
	 * @author dusd
	 * @date 2017年12月23日
	 */
	public static void testSystemUserDoLogin() {
		nameSpace = "app/login/";
		methodName = "systemUserDoLogin.do";
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("userName", "ad1min");
        dataMap.put("password", "123456");
        System.out.println(nameSpace + methodName);
        HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	/**
	 * APP火运等待装车查询集装箱列表
	 * @author liangdeng
	 * @date 2018年3月26日
	 */
	public static void testTrainContainer() {
		nameSpace = "app/trainOrder/";
		methodName = "trainAppContainerList.do";
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("userId", "1");
        dataMap.put("trainOrderId", "124");
        dataMap.put("text", "");
        System.out.println(nameSpace + methodName);
        HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	
	/**
	 * APP短驳新建运单查询集装箱列表
	 * @author liangdeng
	 * @date 2018年3月27日
	 */
	public static void testOrderContainer() {
		nameSpace = "app/order/";
		methodName = "getContainerNum.do";
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("userId", "1");
        dataMap.put("projectId", "188");
        dataMap.put("stepSelectCode", "1");
        dataMap.put("receiptCompanyId", "");
        dataMap.put("sendCompanyId", "35");
        dataMap.put("text", "");
        System.out.println(nameSpace + methodName);
        HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
	
	public static void testOrder() {
		nameSpace = "app/order/";
		methodName = "listTbOrderWaitDispatchApp.do";
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("userId", "165");
        dataMap.put("page", "1");
        dataMap.put("status", "1,2,3,4,5,6,7");
        dataMap.put("sysOrgCode", "22");
        HttpClientUtil ac = new HttpClientUtil(URL + nameSpace + methodName);
		System.out.println(ac.post(JsonUtils.objectToJson(dataMap)));
	}
}
