package com.shenhesoft.logistics.enterprise.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.shenhesoft.logistics.common.GeneralResponse;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.enterprise.service.AppTbTrainOrderService;

/**
 * 火运相关app 接口  controller
 * @author dusd
 * @date 2017年12月27日
 */
@RestController
@RequestMapping("app/trainOrder/")
public class AppTbTrainOrderController {
	
	/**
	 * 企业app列表分页数量
	 */
	@Value("${APP_ENTERPRISE_PAGE_LIMIT}")
	private Integer APP_ENTERPRISE_PAGE_LIMIT;
	
	/**
	 * 火运app service 接口
	 */
	@Autowired
	private AppTbTrainOrderService appTbTrainOrderService;
	
	/**
	 * 查询不同状态下的火运运单列表
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * 短驳状态：1:等待调度 2:等待发运 3:在途运载 4:货位引导 5:等待回单 6:等待确认 7:已完成
	 * 火运状态：1:等待承认,2:等待装车,3:等待发运,4:在途运载,5:等待卸货,6:等待回单,7:已完成
	 * @return
	 */
	@RequestMapping(value = "/listAllTbTrainOrderDifferentStatus", method = RequestMethod.POST)
	public GeneralResponse listAllTbTrainOrderDifferentStatus(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.listAllTbTrainOrderDifferentStatus(dataMap,APP_ENTERPRISE_PAGE_LIMIT);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询火运运单出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 通过火运运单id查询火运信息
	 * @author dusd
	 * @date 2017年12月27日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/viewTbTrainOrderById", method = RequestMethod.POST)
	public GeneralResponse viewTbTrainOrderById(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.viewTbTrainOrderById(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询火运运单出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 在途追踪-查询某订单位置信息列表
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listHistoryLocationTbTrainOrder", method = RequestMethod.POST)
	public GeneralResponse listHistoryLocationTbTrainOrder(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.listHistoryLocationTbTrainOrder(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询某订单位置信息列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 在途追踪-保存某订单位置信息
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/saveLocationTbTrainOrderApp", method = RequestMethod.POST)
	public GeneralResponse saveLocationTbTrainOrderApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.saveLocationTbTrainOrderApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询某订单位置信息列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 运单车型车号列表
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listTrainCargoApp", method = RequestMethod.POST)
	public GeneralResponse listTrainCargoApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.listTrainCargoApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询运单车型车号列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 等待发运,在途运载,等待回单更新状态
	 * 状态位 4-等待发运 5-在途运载 10-等待回单
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/updateTrainOrderStatusByParamApp", method = RequestMethod.POST)
	public GeneralResponse updateTrainOrderStatusByParamApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.updateTrainOrderStatusByParamApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("回单更新状态出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 等待回单-保存发货运单和到货运单信息
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/saveWaybillReceiptTrainOrderApp", method = RequestMethod.POST)
	public GeneralResponse saveWaybillReceiptTrainOrderApp(@RequestBody Map<String, String> dataMap,HttpSession session) {
		try {
			return appTbTrainOrderService.saveWaybillReceiptTrainOrderApp(dataMap,session);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("保存发货运单和到货运单信息出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 通过火运运单id查询货场列表
	 * type 0-装车货场 1-到货货场
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/listTbFreightYardByTrainOrderIdApp", method = RequestMethod.POST)
	public GeneralResponse listTbFreightYardByTrainOrderIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.listTbFreightYardByTrainOrderIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("通过火运运单id查询货场列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
		
	}
	
	/**
	 * 通过货场id查询货位列表
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listTbCargoLocationByYardIdApp", method = RequestMethod.POST)
	public GeneralResponse listTbCargoLocationByYardIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.listTbCargoLocationByYardIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("通过货场id查询货位列表异常");
			e.printStackTrace();
			return generalResponse;
		}
		
	}
	
	/**
	 * 查询火车车型列表
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listTbTrainTypeApp", method = RequestMethod.POST)
	public GeneralResponse listTbTrainTypeApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.listTbTrainTypeApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询火车车型列表异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 查询运单装车信息列表
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listTbTrainOrderCargoPalceByTrainOrderIdApp", method = RequestMethod.POST)
	public GeneralResponse listTbTrainOrderCargoPalceByTrainOrderIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.listTbTrainOrderCargoPalceByTrainOrderIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询火车车型列表异常");
			e.printStackTrace();
			return generalResponse;
		}		
	}
	
	/**
	 * 保存运单装车信息
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/saveTbTraninOrderTruckLoadApp", method = RequestMethod.POST)
	public GeneralResponse saveTbTraninOrderTruckLoadApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.saveTbTraninOrderTruckLoadApp(dataMap,null);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("保存运单装车信息异常");
			e.printStackTrace();
			return generalResponse;
		}		
	}
	
	/**
	 * 通过运单和车型查询所有的车号信息
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listTbTrainOrderCargoPalceByTrainOrderIdCarTypeId", method = RequestMethod.POST)
	public GeneralResponse listTbTrainOrderCargoPalceByTrainOrderIdCarTypeId(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.listTbTrainOrderCargoPalceByTrainOrderIdCarTypeId(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("通过运单和车型查询所有的车号信息异常");
			e.printStackTrace();
			return generalResponse;
		}		
	}
	
	/**
	 * 通过装车信息主键查询详细装车信息
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/viewTbTrainOrderCargoPalceById", method = RequestMethod.POST)
	public GeneralResponse viewTbTrainOrderCargoPalceById(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.viewTbTrainOrderCargoPalceById(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("通过装车信息主键查询详细装车信息异常");
			e.printStackTrace();
			return generalResponse;
		}		
	}
	
	/**
	 * 保存到货信息
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/saveTbTrainOrderArrivalApp", method = RequestMethod.POST)
	public GeneralResponse saveTbTrainOrderArrivalApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.saveTbTrainOrderArrivalApp(dataMap,null);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("保存到货信息异常");
			e.printStackTrace();
			return generalResponse;
		}		
	}
	
	/**
	 * 通过项目信息计算库存数量
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/viewTbTrainOrderRepertoryByProjectIdApp", method = RequestMethod.POST)
	public GeneralResponse viewTbTrainOrderRepertoryByProjectIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.viewTbTrainOrderRepertoryByProjectIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("通过项目信息计算库存数量异常");
			e.printStackTrace();
			return generalResponse;
		}		
	}
	
	/**
	 * 计算预计支出金额
	 * 请车数 * 运费合计（干线费用）
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/viewPredictMoneyByProjectIdApp", method = RequestMethod.POST)
	public GeneralResponse viewPredictMoneyByProjectIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.viewPredictMoneyByProjectIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("计算预计支出金额异常");
			e.printStackTrace();
			return generalResponse;
		}		
		
	}
	
	
	/**
	 * 保存新建请车信息
	 * @author dusd
	 * @date 2018年1月2日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/saveTbTrainOrderApp", method = RequestMethod.POST)
	public GeneralResponse saveTbTrainOrderApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.saveTbTrainOrderApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("保存新建请车信息异常");
			e.printStackTrace();
			return generalResponse;
		}		
		
	}
	
	/**
	 * 火运 - 保存承运车数
	 * @author dusd
	 * @date 2018年1月3日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/saveTbTrainOrderAdmitCarNum", method = RequestMethod.POST)
	public GeneralResponse saveTbTrainOrderAdmitCarNum(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.saveTbTrainOrderAdmitCarNum(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("保存承运车数异常");
			e.printStackTrace();
			return generalResponse;
		}		
	}

	/**
	 * 火运查询项目列表
	 * @author LiangDeng
	 * @date 2017年1月22日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/trainListTbProjectApp", method = RequestMethod.POST)
	public GeneralResponse trainListTbProjectApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.trainListTbProjectApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询项目列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 等待装车查询集装箱号
	 * @author LiangDeng
	 * @date 2018年3月10日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/trainAppContainerList", method = RequestMethod.POST)
	public GeneralResponse trainAppContainerList(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbTrainOrderService.trainAppContainerList(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询集装箱号列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
}
