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
import com.shenhesoft.logistics.enterprise.service.AppTbOrderService;

/**
 * app 短驳运单 控制层
 * 
 * @author dusd
 * @date 2017年12月23日
 */
@RestController
@RequestMapping("app/order/")
public class AppTbOrderController {
	
	/**
	 * 企业app列表分页数量
	 */
	@Value("${APP_ENTERPRISE_PAGE_LIMIT}")
	private Integer APP_ENTERPRISE_PAGE_LIMIT;
	
	/**
	 * 人工设定异常原因
	 */
	@Value("${ORDER_EXCEPTION_REASON}")
	private String ORDER_EXCEPTION_REASON;
	
	/**
	 * 驳回原因列表，用;隔开
	 */
	@Value("${TURNDOWN_REASON}")
	private String TURNDOWN_REASON;
	

	/**
	 * app 短驳运单 service 接口
	 */
	@Autowired
	private AppTbOrderService appTbOrderService;

	/**
	 * 调度审核（等待调度）列表
	 * 
	 * @author dusd
	 * @date 2017年12月23日
	 * @param userId
	 *            当前登录人主键
	 * @param page
	 *            当前页 如果为空 则定为1
	 * @return
	 */
	@RequestMapping(value = "/listTbOrderWaitDispatchApp", method = RequestMethod.POST)
	public GeneralResponse listTbOrderWaitDispatchApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listAllTbOrderDifferentStatus(dataMap,APP_ENTERPRISE_PAGE_LIMIT);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("获得等待调度列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}

	/**
	 * 企业 核准操作（调度)
	 * 
	 * @author dusd
	 * @date 2017年12月23日
	 * @param userId 企业用户id
	 * @param orderId 运单id 如果有多个 用逗号隔开
	 * @param containerNumber1Id 运单id 如果有多个 用逗号隔开
	 * @param containerNumber1 运单id 如果有多个 用逗号隔开
	 * @param containerNumber1Id 运单id 如果有多个 用逗号隔开
	 * @param containerNumber1 运单id 如果有多个 用逗号隔开
	 * @return
	 */
	@RequestMapping(value = "/approvalTbOrderApp", method = RequestMethod.POST)
	public GeneralResponse approvalTbOrderApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.approvalTbOrderApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("调度出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}

	/**
	 * 企业用户驳回运单
	 * 
	 * @author dusd
	 * @date 2017年12月23日
	 * @param userId
	 *            企业用户id
	 * @param orderId
	 *            运单id
	 * @param remark
	 *            驳回理由
	 * @return
	 */
	@RequestMapping(value = "/turndownTbOrderApp", method = RequestMethod.POST)
	public GeneralResponse turndownTbOrderApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.turndownTbOrderApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("驳回失败出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 查询驳回列表
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listTbOrderTurndownApp", method = RequestMethod.POST)
	public GeneralResponse listTbOrderTurndownApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listTbOrderTurndownApp(dataMap,APP_ENTERPRISE_PAGE_LIMIT);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询驳回列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 还原被驳回的运单
	 * @author dusd
	 * @date 2017年12月24日
	 * @param userId
	 *            企业用户id
	 * @param orderId
	 *            运单id
	 * @return
	 */
	@RequestMapping(value = "/restoreTbOrderTurndownApp", method = RequestMethod.POST)
	public GeneralResponse restoreTbOrderTurndownApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.restoreTbOrderTurndownApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("还原被驳回的运单出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 查询不同状态的运单列表 
	 * @author dusd
	 * @date 2017年12月24日
	 * @param userId 当前登录人主键
	 * @param page 当前页 如果为空 则定为1
	 * @param status 运单状态 如果是多种状态 用逗号隔开 
	 * 等待调度 -1 等待发运 - 2 在途运载 - 3 货位引导 - 4 等待回单 - 5 等待计费 - 6 已完成 - 7
	 * @return
	 */
	@RequestMapping(value = "/listAllTbOrderDifferentStatus", method = RequestMethod.POST)
	public GeneralResponse listAllTbOrderDifferentStatus(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listAllTbOrderDifferentStatus(dataMap,APP_ENTERPRISE_PAGE_LIMIT);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询运单出现异常");
			e.printStackTrace();
			return generalResponse;
		}
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
	@RequestMapping(value = "/saveTbOrderForwardInfo", method = RequestMethod.POST)
	public GeneralResponse saveTbOrderForwardInfo(@RequestBody Map<String, String> dataMap,HttpSession session) {
		try {
			return appTbOrderService.saveTbOrderForwardInfo(dataMap,session);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("保存发运信息出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * app新建运单-货场列表
	 * @author liangdeng
	 * @date 2018年3月9日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listFreYardAppOfAdd", method = RequestMethod.POST)
	public GeneralResponse listFreYardAppOfAdd(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listFreYardAppOfAdd(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询新建运单-货场列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 取消发运
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/cancelTbOrderForward", method = RequestMethod.POST)
	public GeneralResponse cancelTbOrderForward(@RequestBody Map<String, String> dataMap,HttpSession session) {
		try {
			return appTbOrderService.cancelTbOrderForward(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("取消发运出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 货位引导-货场列表
	 * @author dusd
	 * @date 2017年12月24日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listGoodsYardApp", method = RequestMethod.POST)
	public GeneralResponse listGoodsYardApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listGoodsYardApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询货位引导-货场列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 等待调度-货场列表
	 * @author liangdeng
	 * @date 2018年3月8日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listFreYardAppOfDispatch", method = RequestMethod.POST)
	public GeneralResponse listFreYardAppOfDispatch(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listFreYardAppOfDispatch(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询等待调度-货场列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 等待调度-提交货场货位信息
	 * @author liangdeng
	 * @date 2018年3月8日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/saveDisptachTbOrderApp", method = RequestMethod.POST)
	public GeneralResponse saveDisptachTbOrderApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.saveDisptachTbOrderApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("等待调度-提交货场货位信息出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	/**
	 * 货位引导-根据货场获得货位信息
	 * @author dusd
	 * @date 2017年12月25日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listTbCargoLocationByFreightIdApp", method = RequestMethod.POST)
	public GeneralResponse listTbCargoLocationByFreightIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listTbCargoLocationByFreightIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询货位引导-货场列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 货位引导-提交货场货位信息
	 * @author dusd
	 * @date 2017年12月25日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/saveGuideTbOrderApp", method = RequestMethod.POST)
	public GeneralResponse saveGuideTbOrderApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.saveGuideTbOrderApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("货位引导-提交货场货位信息出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 查询异常运单列表
	 * @author dusd
	 * @date 2017年12月25日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listTbOrderAbnormalApp", method = RequestMethod.POST)
	public GeneralResponse listTbOrderAbnormalApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listTbOrderAbnormalApp(dataMap,APP_ENTERPRISE_PAGE_LIMIT);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询异常列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}

	/**
	 * 查询短驳到货确认和计费确认异常运单列表
	 * @author liangdeng
	 * @date 2018年4月4日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listTbOrderExceptionApp", method = RequestMethod.POST)
	public GeneralResponse listTbOrderExceptionApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listTbOrderExceptionApp(dataMap,APP_ENTERPRISE_PAGE_LIMIT);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询异常列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 查询项目列表
	 * @author dusd
	 * @date 2017年12月25日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listTbProjectApp", method = RequestMethod.POST)
	public GeneralResponse listTbProjectApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listTbProjectApp(dataMap,APP_ENTERPRISE_PAGE_LIMIT);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询项目列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 通过项目id 查询项目详细信息
	 * 0 汽运,1 接取,2 火运,3 送达,4 接取+火运,5 火运+送达,6 联运,7接取+送达
	 * @author dusd
	 * @date 2017年12月26日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/viewTbProjectByIdApp", method = RequestMethod.POST)
	public GeneralResponse viewTbProjectByIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.viewTbProjectByIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询项目列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 根据项目id查询车辆列表
	 * @author dusd
	 * @date 2017年12月26日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listCarByPorjectIdApp", method = RequestMethod.POST)
	public GeneralResponse listCarByPorjectIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listCarByPorjectIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询车辆列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 通过driverId查询汽车信息
	 * @author dusd
	 * @date 2017年12月26日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/viewCarInfoByDriverIdApp", method = RequestMethod.POST)
	public GeneralResponse viewCarInfoByDriverIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.viewCarInfoByDriverIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询车辆信息出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 通过牌照查询汽车信息
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/viewTbOrderCarDetailByPlateNumber", method = RequestMethod.POST)
	public GeneralResponse viewTbOrderCarDetailByPlateNumber(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.viewTbOrderCarDetailByPlateNumber(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询车辆信息出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	
	/**
	 * 保存集装箱运单/散堆装运单信息 
	 * 新建运单
	 * @author dusd
	 * @date 2017年12月26日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/saveTbOrderApp", method = RequestMethod.POST)
	public GeneralResponse saveTbOrderApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.saveTbOrderApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("保存集装箱运单/散堆装运单信息异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 等待回单
	 * @author dusd
	 * @date 2017年12月26日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/receipterTbOrderApp", method = RequestMethod.POST)
	public GeneralResponse receipterTbOrderApp(@RequestBody Map<String, String> dataMap,HttpSession session) {
		try {
			return appTbOrderService.receipterTbOrderApp(dataMap,session);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("等待回单出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 驳回原因列表
	 * @author dusd
	 * @date 2018年1月9日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listTurndownReasonApp", method = RequestMethod.POST)
	public GeneralResponse listTurndownReasonApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listTurndownReasonApp(dataMap,TURNDOWN_REASON);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询驳回原因列表异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 查询异常原因列表
	 * @author dusd
	 * @date 2018年1月6日
	 * @param dataMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/listOrderExceptionReasonApp", method = RequestMethod.POST)
	public GeneralResponse listOrderExceptionReasonApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listOrderExceptionReasonApp(dataMap,ORDER_EXCEPTION_REASON);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询异常原因列表异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 保存异常原因信息
	 * @author dusd
	 * @date 2018年1月6日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/saveTbExceptionMsgApp", method = RequestMethod.POST)
	public GeneralResponse saveTbExceptionMsgApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.saveTbExceptionMsgApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("保存异常原因信息异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 查询短驳异常列表
	 * @author dusd
	 * @date 2018年1月6日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listShortExceptionInfoByUserIdApp", method = RequestMethod.POST)
	public GeneralResponse listShortExceptionInfoByUserIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.listShortExceptionInfoByUserIdApp(dataMap,APP_ENTERPRISE_PAGE_LIMIT);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询短驳异常列表异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * app端通过异常id查询异常信息
	 * @author dusd
	 * @date 2018年1月6日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/viewTbExceptionMsgByIdApp", method = RequestMethod.POST)
	public GeneralResponse viewTbExceptionMsgByIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.viewTbExceptionMsgByIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("app端通过异常id查询异常信息异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 通过运单id查询运单异常信息
	 * @author dusd
	 * @date 2018年1月9日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/viewTbExceptionMsgByOrderIdApp", method = RequestMethod.POST)
	public GeneralResponse viewTbExceptionMsgByOrderIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.viewTbExceptionMsgByOrderIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("通过运单id查询运单异常信息异常");
			e.printStackTrace();
			return generalResponse;
		}
		
	}
	
	/**
	 * 通过运单id查询运单详细信息
	 * @author dusd
	 * @date 2018年1月9日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/viewTbOrderByIdApp", method = RequestMethod.POST)
	public GeneralResponse viewTbOrderByIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.viewTbOrderByIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("通过运单id查询运单详细信息异常");
			e.printStackTrace();
			return generalResponse;
		}
		
	}
	
	/**
	 * 短驳查询项目列表
	 * @author LiangDeng
	 * @date 2017年1月22日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/bulkListTbProjectApp", method = RequestMethod.POST)
	public GeneralResponse bulkListTbProjectApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.bulkListTbProjectApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询项目列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	
	/**
	 * @description 获取集装箱号
	 * @date 2018年3月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getContainerNum", method = RequestMethod.POST)
	public GeneralResponse getContainerNum(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.getContainerNum(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询集装箱号列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}

	/**
	 * 通过运单id 更新运单状态
	 * @author liangdeng
	 * @date 2018年3月9日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/appUpdateOrderStatus", method = RequestMethod.POST)
	public GeneralResponse appUpdateOrderStatus(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.appUpdateOrderStatus(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("更新运单状态出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 短驳新建运单查看是否发布任务
	 * @author liangdeng
	 * @date 2018年3月10日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/appAddOrderIsPublish", method = RequestMethod.POST)
	public GeneralResponse appAddOrderIsPublish(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.appAddOrderIsPublish(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("新建运单出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 短驳首页运单数量统计
	 * @author liangdeng
	 * @date 2018年3月30日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/appOrderCounts", method = RequestMethod.POST)
	public GeneralResponse appOrderCounts(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.appOrderCounts(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("运单统计出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 短驳等待计费确认数量统计
	 * @author liangdeng
	 * @date 2018年4月24日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/appWaitBillentCounts", method = RequestMethod.POST)
	public GeneralResponse appWaitBillentCounts(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbOrderService.appWaitBillentCounts(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("计费确认数量统计出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
}
