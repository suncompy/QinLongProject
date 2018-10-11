// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.shortbarge.box;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.shenhesoft.logistics.business.helpPojo.TbOrderCarDetail;
import com.shenhesoft.logistics.business.helpPojo.TbReceipterDetail;
import com.shenhesoft.logistics.business.helpPojo.TbSubForwardDetail;
import com.shenhesoft.logistics.business.helpPojo.TbWaitDispatchDetail;
import com.shenhesoft.logistics.business.helpPojo.TbguideDetail;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.project.manage.ProjectManagmentService;
import com.shenhesoft.logistics.business.shortbarge.order.ShortBargeOrderService;
import com.shenhesoft.logistics.business.shortbarge.publish.PublishJobService;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.ImageUtil;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.office.ExcelUtil;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.cargo.interfaces.CargomanagementService;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.helpPojo.ProjectAppHelp;
import com.shenhesoft.logistics.manage.helpPojo.TbCargoMainPoint;
import com.shenhesoft.logistics.manage.interfaces.SiteManageService;
import com.shenhesoft.logistics.manage.interfaces.TransportManagerService;
import com.shenhesoft.logistics.manage.pojo.box.TbContainer;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargo;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.search.OrderSearch;

/**
 * @description 短驳订单-集装箱管理
 *
 * @author LiangLin
 *
 * @date 2017年12月19日
 */
@Controller
@RequestMapping("/business/short/boxManager")
public class BoxManagerOrderController {

	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;

	@Autowired
	private ShortBargeOrderService shortBargeService;
	
	@Autowired
	private ProjectManagmentService projectManagmentService;

	@Autowired
	private BranchGroupService branchGroupService;
	
	@Autowired
	private CargomanagementService cargomanagementService;
	
	@Autowired
	private SiteManageService siteManageService;
	
	@Autowired
	private PublishJobService publishJobService;
	
	@Autowired
	private TransportManagerService contService;
	
	/**
	 * 
	 * @description 集装箱或散装箱管理 运单展示
	 * @author liangLin
	 * @date 2017年12月19日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model,HttpSession session) {
		//获取所有分支机构
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		List<TbBranchGroup> branchGroups = branchGroupService.selectBranchGroupByUid(user.getId());	
		model.addAttribute("branchGroups", branchGroups);
		
		//获取所有货物品名
		List<TbCargoMainPoint> cargos = cargomanagementService.listCargo();
		model.addAttribute("cargos", cargos);
		
		OrderSearch orderSearch = new OrderSearch();
		//全部
		DataGridResult result = shortBargeService.selectBoxManagerOrderByPages(PAGE_NUM, CUSTOMER_PAGE_LIMIT, (byte) 1,orderSearch,user,(byte) 1);
		model.addAttribute("boxOrderList", result);
		//异常
		DataGridResult exceptionResult = shortBargeService.selectBoxManagerOrderByPages(PAGE_NUM, CUSTOMER_PAGE_LIMIT, (byte) 2,orderSearch,user,(byte) 1);
		model.addAttribute("exceptionResult", exceptionResult);
		//历史
		DataGridResult historyResult = shortBargeService.selectBoxManagerOrderByPages(PAGE_NUM, CUSTOMER_PAGE_LIMIT, (byte) 3,orderSearch,user,(byte) 1);
		model.addAttribute("historyResult", historyResult);
		//回收站
		DataGridResult result2 = shortBargeService.selectBoxManagerOrderDeleteByPages(PAGE_NUM,CUSTOMER_PAGE_LIMIT,orderSearch,user,(byte) 1);
		model.addAttribute("orderDeleteList", result2);
		return "/html/business/shortBarge/truckContainer";
	}
	
	public TbOrder exportChangeContent(TbOrder order) {
		if(order.getStatus() == 1) {
			order.setStatusName("等待调度");
		}else if(order.getStatus() == 2) {
			order.setStatusName("等待发运");
		}else if(order.getStatus() == 3) {
			order.setStatusName("在途运载");
		}else if(order.getStatus() == 4) {
			order.setStatusName("货位引导");
		}else if(order.getStatus() == 5) {
			order.setStatusName("等待回单");
		}else if(order.getStatus() == 6) {
			order.setStatusName("计费确认");
		}else if(order.getStatus() == 7) {
			order.setStatusName("已完成");
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		order.setExportCreatTime(DateUtils.date2Str(order.getCreateDate(),sdf));
		order.setExportUpdateTime(DateUtils.date2Str(order.getUpdateDate(),sdf));
		order.setExportExceptionReportDate(DateUtils.date2Str(order.getExceptionTime(),sdf));
		order.setExportArriveDate(DateUtils.date2Str(order.getReceipterDate(),sdf));
		order.setExportDeleteDate(DateUtils.date2Str(order.getDeleteTime(),sdf));
		
		if(order.getContainerOneSendNet() == null) {
			order.setContainerOneSendNet(new BigDecimal(0));
		}
		if(order.getContainerTwoSendNet() == null) {
			order.setContainerTwoSendNet(new BigDecimal(0));
		}
		if(order.getContainerOneReceiptNet() == null) {
			order.setContainerOneReceiptNet(new BigDecimal(0));
		}
		if(order.getContainerTwoReceiptNet() == null) {
			order.setContainerTwoReceiptNet(new BigDecimal(0));
		}
		order.setExportSumSendNet(order.getContainerOneSendNet().add(order.getContainerTwoSendNet()));
		order.setExportSumReceiptNet(order.getContainerOneReceiptNet().add(order.getContainerTwoReceiptNet()));
		return order;
	}
	/**
	 * 导出1
	 * @param request 页面表单
	 * @param response 输出流
	 * @return
	 */
	@RequestMapping(value = "/export1", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse export1(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map =FormUtil.getParameterMap(request);
		int page = Integer.parseInt(map.get("pageNo").toString());
		TbSystemUser user = AppSession.getCurrentUser();
		OrderSearch orderSearch = FormUtil.populate(OrderSearch.class, map, false);
		//全部
		DataGridResult result = shortBargeService.selectBoxManagerOrderByPages(page, CUSTOMER_PAGE_LIMIT, (byte) 1,orderSearch,user,(byte) 1);
		String[] heads = {"项目编号","运单编号","创建时间","运单状态","状态更新时间","调度员","分支机构","货物品名","化验指标","发货单位","发货净重","收货单位","收货净重","承运车辆","集装箱号","集装箱号"};
		String[] headCodes = {"projectCode","orderCode","exportCreatTime","statusName","exportUpdateTime","userDispatchName","branchGroupName","cargoName","testIndicators","sendCompany","exportSumSendNet","receiptCompany","exportSumReceiptNet","carPlateNumber","containerNumber1","containerNumber2"};
		
		List<TbOrder> list = result.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {
			for(TbOrder order:list) {
				exportChangeContent(order);
				datas.add(FormUtil.populate(order));
			}	
		}
		if(ExcelUtil.createExcel(request,response, "集装箱管理", "集装箱管理", heads, headCodes, datas)){
			return new ShResponse(HttpStatus.OK.value(), "","下载成功");
		}
		return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
	}
	
	/**
	 * 导出2
	 * @param request 页面表单
	 * @param response 输出流
	 * @return
	 */
	@RequestMapping(value = "/export2", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse export2(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map =FormUtil.getParameterMap(request);
		int page = Integer.parseInt(map.get("pageNo").toString());
		TbSystemUser user = AppSession.getCurrentUser();
		OrderSearch orderSearch = FormUtil.populate(OrderSearch.class, map, false);
		//全部
		DataGridResult exceptionResult = shortBargeService.selectBoxManagerOrderByPages(page, CUSTOMER_PAGE_LIMIT, (byte) 2,orderSearch,user,(byte) 1);
		String[] heads = {"项目编号","运单编号","创建时间","运单状态","状态更新时间","调度员","分支机构","货物品名","化验指标","发货单位","发货净重","收货单位","收货净重","承运车辆","集装箱号","集装箱号","提报时间","异常提报人","异常原因"};
		String[] headCodes = {"projectCode","orderCode","exportCreatTime","statusName","exportUpdateTime","userDispatchName","branchGroupName","cargoName","testIndicators","sendCompany","exportSumSendNet","receiptCompany","exportSumReceiptNet","carPlateNumber","containerNumber1","containerNumber2","exportExceptionReportDate","exceptionReoportName","exceptionReoportReason"};
		
		List<TbOrder> list = exceptionResult.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {
			for(TbOrder order:list) {
				exportChangeContent(order);
				datas.add(FormUtil.populate(order));
			}	
		}
		if(ExcelUtil.createExcel(request,response, "集装箱管理异常运单", "集装箱管理异常运单", heads, headCodes, datas)){
			return new ShResponse(HttpStatus.OK.value(), "","下载成功");
		}
		return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
	}
	
	/**
	 * 导出3
	 * @param request 页面表单
	 * @param response 输出流
	 * @return
	 */
	@RequestMapping(value = "/export3", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse export3(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map =FormUtil.getParameterMap(request);
		int page = Integer.parseInt(map.get("pageNo").toString());
		TbSystemUser user = AppSession.getCurrentUser();
		OrderSearch orderSearch = FormUtil.populate(OrderSearch.class, map, false);
		//全部
		DataGridResult historyResult = shortBargeService.selectBoxManagerOrderByPages(page, CUSTOMER_PAGE_LIMIT, (byte) 3,orderSearch,user,(byte) 1);
		String[] heads = {"项目编号","运单编号","创建时间","状态更新时间","到货时间","调度员","分支机构","货物品名","化验指标","发货单位","发货净重","收货单位","收货净重","承运车辆","集装箱号","集装箱号"};
		String[] headCodes = {"projectCode","orderCode","exportCreatTime","exportUpdateTime","exportArriveDate","userDispatchName","branchGroupName","cargoName","testIndicators","sendCompany","exportSumSendNet","receiptCompany","exportSumReceiptNet","carPlateNumber","containerNumber1","containerNumber2"};
		
		List<TbOrder> list = historyResult.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {
			for(TbOrder order:list) {
				exportChangeContent(order);
				datas.add(FormUtil.populate(order));
			}	
		}
		if(ExcelUtil.createExcel(request,response, "集装箱管理历史运单", "集装箱管理历史运单", heads, headCodes, datas)){
			return new ShResponse(HttpStatus.OK.value(), "","下载成功");
		}
		return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
	}
	
	/**
	 * 导出4
	 * @param request 页面表单
	 * @param response 输出流
	 * @return
	 */
	@RequestMapping(value = "/export4", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse export4(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map =FormUtil.getParameterMap(request);
		int page = Integer.parseInt(map.get("pageNo").toString());
		TbSystemUser user = AppSession.getCurrentUser();
		OrderSearch orderSearch = FormUtil.populate(OrderSearch.class, map, false);
		//全部
		DataGridResult result2 = shortBargeService.selectBoxManagerOrderDeleteByPages(page,CUSTOMER_PAGE_LIMIT,orderSearch,user,(byte) 1);
		String[] heads = {"项目编号","运单编号","创建时间","运单状态","状态更新时间","调度员","分支机构","货物品名","化验指标","发货单位","发货净重","收货单位","收货净重","承运车辆","集装箱号","集装箱号","删除时间","删除人","删除原因"};
		String[] headCodes = {"projectCode","orderCode","exportCreatTime","statusName","exportUpdateTime","userDispatchName","branchGroupName","cargoName","testIndicators","sendCompany","exportSumSendNet","receiptCompany","exportSumReceiptNet","carPlateNumber","containerNumber1","containerNumber2","exportDeleteDate","deleteName","deleteReason"};
		
		List<TbOrder> list = result2.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {//exportCreatTime exportUpdateTime exportSendTime statusName //exportSumSendNet exportSumReceiptNet
			for(TbOrder order:list) {
				exportChangeContent(order);
				datas.add(FormUtil.populate(order));
			}	
		}
		if(ExcelUtil.createExcel(request,response, "集装箱管理运单回收站", "集装箱管理运单回收站", heads, headCodes, datas)){
			return new ShResponse(HttpStatus.OK.value(), "","下载成功");
		}
		return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
	}
	
	
	/**
	 * 
	 * @description 异常运单
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param
	 * @return
	 *//*
	@RequestMapping(value = "/exception/list", method = RequestMethod.GET)
	public LogisticsResult exceptionList(Model model) {
		DataGridResult result = shortBargeService.selectBoxManagerOrderByPages(PAGE_NUM, 3, (byte) 2);
		model.addAttribute("errorOrderList", result);
		return LogisticsResult.ok();
	}*/

	/**
	 * 
	 * @description 分页查询
	 * @author liangLin
	 * @date 2017年12月19日
	 * @param type:2:异常运单
	 * @return
	 */
	@RequestMapping("/byPage/list")
	@ResponseBody
	public LogisticsResult ListByPage(Integer page, byte type,String order,HttpSession session) {
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		OrderSearch searchOrder = JsonUtils.jsonToPojo(order, OrderSearch.class);
		DataGridResult result = shortBargeService.selectBoxManagerOrderByPages(page,CUSTOMER_PAGE_LIMIT, type,searchOrder,user,(byte) 1);
		return LogisticsResult.ok(result);
	}
	
	/**
	 * 
	 * @description 运单回收站 
	 * @author liangLin
	 * @date 2017年12月23日
	 * @param 
	 * @return
	 */
	@RequestMapping("/delete/byPage/list")
	@ResponseBody
	public LogisticsResult ListByPage(Integer page,String order,HttpSession session) {
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		OrderSearch searchOrder = JsonUtils.jsonToPojo(order, OrderSearch.class);
		DataGridResult result = shortBargeService.selectBoxManagerOrderDeleteByPages(page, CUSTOMER_PAGE_LIMIT,searchOrder,user,(byte) 1);
		return LogisticsResult.ok(result);
	}
	/**
	 * 
	 * @description 单个详情
	 * @author liangLin
	 * @date 2017年12月19日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult get(@RequestParam Integer id) {
		TbOrder tbOrder = shortBargeService.selectBoxManagerOrderById(id);
		return LogisticsResult.ok(tbOrder);
	}


	/**
	 * 
	 * @description 等待调度获取详情 （其他）
	 * @author liangLin
	 * @date 2017年12月21日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/dispatch/getMsg", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult dispatchGetMsg(@RequestParam Integer id, Model model) {
		TbOrder tbOrder = shortBargeService.getDispatchMsgByProjectId(id);
		return LogisticsResult.ok(tbOrder);
	}
	
	/**
	 * @description 等待调度获取详情
	 * @author LiangDeng
	 * @date 2018年1月13日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/dispatch/getInfo", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult dispatchGetInfo(@RequestParam Integer id, Model model) {
		TbOrder tbOrder = shortBargeService.getDispatchMsgByProjectId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		// 0:接取 1:送达
		if(tbOrder.getStepSelectCode() == 0){
			List<TbFreightYard> list = siteManageService.getAllFreightYardOfIsolated(tbOrder.getSendCompanyId());
			map.put("list", list);
		}else if(tbOrder.getStepSelectCode() == 1){
			TbProject tp = shortBargeService.selectDetailProject(tbOrder.getProjectId());
			//发货站点
			List<TbFreightYard> list = siteManageService.getFreightYardsByTrainStationId(tp.getForwardingSiteId());
			map.put("list", list);
		}else{
			List<TbFreightYard> list = siteManageService.getAllFreightYardOfIsolated(tbOrder.getSendCompanyId());
			map.put("list", list);
		}
		map.put("tbOrder", tbOrder);
		return LogisticsResult.ok(map);
	}
	
	/**
	 * @description 货位引导获取详情
	 * @author LiangDeng
	 * @date 2018年1月13日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/dispatch/guideGetInfo", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult guideGetInfo(@RequestParam Integer id, Model model) {
		TbOrder tbOrder = shortBargeService.getDispatchMsgByProjectId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		// 0:接取 1:送达
		//分配货场货位选择  如果是接取 则在这里查询卸货的货场货位 与等待调度相反
		if(tbOrder.getStepSelectCode() == 0){
			TbProject tp = shortBargeService.selectDetailProject(tbOrder.getProjectId());
			//这里是接取  货位引导分配货场货位时  是根据收货站点id查询
			List<TbFreightYard> list = siteManageService.getFreightYardsByTrainStationId(tp.getReceiveCargoSiteId());
			map.put("list", list);
		}else if(tbOrder.getStepSelectCode() == 1){
			//反之 查询独立货场货位
			List<TbFreightYard> list = siteManageService.getAllFreightYardOfIsolated(tbOrder.getReceiptCompanyId());
			map.put("list", list);
		}else{
			List<TbFreightYard> list = siteManageService.getAllFreightYardOfIsolated(tbOrder.getReceiptCompanyId());
			map.put("list", list);
		}
		map.put("tbOrder", tbOrder);
		return LogisticsResult.ok(map);
	}
	
	/**
	 * 
	 * @description 集装箱号 下拉框
	 * @author liangLin
	 * @date 2017年12月21日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/boxNumber/getMsg", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult boxNumberGetMsg() {
		List<TbContainer> list = shortBargeService.getTbContainerNumbers();
		return LogisticsResult.ok(list);
	}

	/**
	 * 
	 * @description 等待调度
	 * @author liangLin
	 * @date 2017年12月21日
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/dispatch/add", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult dispatchAdd(@Valid TbWaitDispatchDetail tbWaitDispatchDetail, BindingResult result,
			HttpSession session) throws Exception {
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把信息补全");
		}
		if(tbWaitDispatchDetail.getIsAgree() != 0) {
			if((tbWaitDispatchDetail.getContainerNumber1() == "") && (tbWaitDispatchDetail.getContainerNumber2() == "")) {
				return LogisticsResult.build(0, "请把信息补全");
			}
			if((tbWaitDispatchDetail.getTakeCarogoPlaceName() == "") || (tbWaitDispatchDetail.getTakeCargoSiteName() == "")) {
				return LogisticsResult.build(0, "请把信息补全");
			}
			if((tbWaitDispatchDetail.getContainerNumber1() != "") && (tbWaitDispatchDetail.getContainerNumber2() != "")) {
				if(tbWaitDispatchDetail.getContainerNumber1().equals(tbWaitDispatchDetail.getContainerNumber2())) {
					return LogisticsResult.build(0, "集装箱号不能相同");
				}
			}
			TbOrder tbOrder = shortBargeService.getDispatchMsgByProjectId(tbWaitDispatchDetail.getOrderId());
			if(tbWaitDispatchDetail.getContainerNumber1() != "") {
				//存在多人同时操作一个项目下的集装箱 即多个运单选择同一集装箱号 此时不能新建运单
				TbContainer cont = contService.selectContainByConId(tbWaitDispatchDetail.getContainerNumber1(),AppSession.getCurrentSysOrgCode());
				if(tbOrder.getTransportType() == 1 || tbOrder.getTransportType() == 2 || tbOrder.getTransportType() == 4) {
					if(cont.getStatus() != 0) {
						return LogisticsResult.build(0, tbWaitDispatchDetail.getContainerNumber1()+"集装箱已被装车，请查看");
					}
				}
				if(tbOrder.getTransportType() == 6 || tbOrder.getTransportType() == 7) {
					if(tbOrder.getStepSelectCode() == 0) {
						if(cont.getStatus() != 0) {
							return LogisticsResult.build(0, tbWaitDispatchDetail.getContainerNumber1()+"集装箱已被装车，请查看");
						}
					}else if(tbOrder.getStepSelectCode() == 1){
						if(cont.getStatus() != 1) {
							return LogisticsResult.build(0, tbWaitDispatchDetail.getContainerNumber1()+"集装箱已被装车，请查看");
						}
					}
				}
				if(tbOrder.getTransportType() == 5) {
					if(tbOrder.getStepSelectCode() == 1) {
						if(cont.getStatus() != 1) {
							return LogisticsResult.build(0, tbWaitDispatchDetail.getContainerNumber1()+"集装箱已被装车，请查看");
						}
					}
				}
			}
			if(tbWaitDispatchDetail.getContainerNumber2() != "") {
				//存在多人同时操作一个项目下的集装箱 即多个运单选择同一集装箱号 此时不能新建运单
				TbContainer cont = contService.selectContainByConId(tbWaitDispatchDetail.getContainerNumber2(),AppSession.getCurrentSysOrgCode());
				if(tbOrder.getTransportType() == 1 || tbOrder.getTransportType() == 2 || tbOrder.getTransportType() == 4) {
					if(cont.getStatus() != 0) {
						return LogisticsResult.build(0, tbWaitDispatchDetail.getContainerNumber2()+"集装箱已被装车，请查看");
					}
				}
				if(tbOrder.getTransportType() == 6 || tbOrder.getTransportType() == 7) {
					if(tbOrder.getStepSelectCode() == 0) {
						if(cont.getStatus() != 0) {
							return LogisticsResult.build(0, tbWaitDispatchDetail.getContainerNumber2()+"集装箱已被装车，请查看");
						}
					}else if(tbOrder.getStepSelectCode() == 1){
						if(cont.getStatus() != 1) {
							return LogisticsResult.build(0, tbWaitDispatchDetail.getContainerNumber2()+"集装箱已被装车，请查看");
						}
					}
				}
				if(tbOrder.getTransportType() == 5) {
					if(tbOrder.getStepSelectCode() == 1) {
						if(cont.getStatus() != 1) {
							return LogisticsResult.build(0, tbWaitDispatchDetail.getContainerNumber2()+"集装箱已被装车，请查看");
						}
					}
				}
			}
		}
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		user.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		boolean flag = shortBargeService.dispatchAdd(tbWaitDispatchDetail, user);
		if (flag != true) {
			return LogisticsResult.build(0, "操作失败");
		}
		return LogisticsResult.build(1, "操作成功");
	}

	/**
	 * 
	 * @description 等待发运
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/subForwarding/add", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult subForwarding(@Valid TbSubForwardDetail tbSubForwardDetail, BindingResult result,
			HttpSession session) throws Exception {
		if(tbSubForwardDetail.getIsAgree() == 0) {
			if (result.hasErrors()) {
				return LogisticsResult.build(0, "请把信息补全"); 
			}
			/*if(tbSubForwardDetail.getPieceNumber() == null) {
				return LogisticsResult.build(6, "请把信息补全");
			}*/
			if(tbSubForwardDetail.getImg() == "" || tbSubForwardDetail.getImg() == null) {
				return LogisticsResult.build(401, "请上传发货运单");
			}
		}
		if (!StringUtils.isBlank(tbSubForwardDetail.getImg())) {
			LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo", tbSubForwardDetail.getImg(), null);
			if (base64UpLoad.getStatus() == 200) {
				tbSubForwardDetail.setImg(base64UpLoad.getData().toString());
			} else {
				return LogisticsResult.build(400, "上传图片失败!");
			}
		}
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		user.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		
		int flag = shortBargeService.subForwardingAdd(tbSubForwardDetail, user);
		if (flag != 1) {
			if(flag == 602) {
				return LogisticsResult.build(flag, "发货净重大于库存，无法发运");
			}else if(flag == 607){
				return LogisticsResult.build(flag, "此站点下没有库存，无法发运");
			}else {
				return LogisticsResult.build(flag, "操作失败");
			}
		}
		return LogisticsResult.build(1, "操作成功");
	}

	/**
	 * 
	 * @description 在途运载
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/carry/add", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult carryAdd(@Valid TbWaitDispatchDetail tbWaitDispatchDetail, BindingResult result,
			HttpSession session) {
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");

		boolean flag = shortBargeService.carryAdd(tbWaitDispatchDetail, user);
		if (flag != true) {
			return LogisticsResult.build(0, "操作失败");
		}
		return LogisticsResult.build(1, "操作成功");
	}

	/**
	 * 
	 * @description 货物引导
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/guide/add", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult guideAdd(@Valid TbguideDetail tbguideDetail, BindingResult result, HttpSession session) {
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");

		boolean flag = shortBargeService.guideAdd(tbguideDetail, user);
		if (flag != true) {
			return LogisticsResult.build(0, "操作失败");
		}
		return LogisticsResult.build(1, "操作成功");
	}

	/**
	 * 
	 * @description 等待回单
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/receipter/add", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult receipterAdd(@Valid TbReceipterDetail tbReceipterDetail, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把信息补全"); 
		}
		if(tbReceipterDetail.getImg() == "" || tbReceipterDetail.getImg() == null) {
			return LogisticsResult.build(401, "请上传到货运单");
		}
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		user.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		if(tbReceipterDetail.getImgType() != null) {
			if(tbReceipterDetail.getImgType() == 0) {
				//什么都不操作
			}
		}else {
			if (!StringUtils.isBlank(tbReceipterDetail.getImg())) {
				LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo", tbReceipterDetail.getImg(), null);
				if (base64UpLoad.getStatus() == 200) {
					tbReceipterDetail.setImg(base64UpLoad.getData().toString());
				} else {
					return LogisticsResult.build(400, "上传图片失败!");
				}
			}
		}
		boolean flag = shortBargeService.receipterAdd(tbReceipterDetail, user);
		if (flag != true) {
			return LogisticsResult.build(0, "操作失败");
		}
		return LogisticsResult.build(1, "操作成功");
	}

	/**
	 * 
	 * @description 删除运单
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/order/delete", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult delete(@RequestParam("idList[]") List<Integer> idList,
			@RequestParam(required = false) String reason,HttpSession session) {
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		boolean flag = shortBargeService.delete(idList, reason,user);
		if (flag != true) {
			return LogisticsResult.build(0, "删除失败");
		}
		return LogisticsResult.build(1, "删除成功");
	}

	/**
	 * 
	 * @description 运单新增
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/order/add", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult orderAdd(@Valid TbOrder tbOrder, BindingResult result, HttpSession session) {
		
		if(tbOrder.getProjectType() == 0) {
			if((tbOrder.getContainerNumber1() == "") && (tbOrder.getContainerNumber2() == "")) {
				return LogisticsResult.build(0, "请把信息补全");
			}
			if((tbOrder.getContainerNumber1() != "") && (tbOrder.getContainerNumber2() != "")) {
				if(tbOrder.getContainerNumber1().equals(tbOrder.getContainerNumber2())) {
					return LogisticsResult.build(0, "集装箱号不能相同");
				}
			}
			if(tbOrder.getContainerNumber1() != "") {
				//存在多人同时操作一个项目下的集装箱 即多个运单选择同一集装箱号 此时不能新建运单
				TbContainer cont = contService.selectContainByConId(tbOrder.getContainerNumber1(),AppSession.getCurrentSysOrgCode());
				if(tbOrder.getTransportType() == 1 || tbOrder.getTransportType() == 2 || tbOrder.getTransportType() == 4) {
					if(cont.getStatus() != 0) {
						return LogisticsResult.build(0, tbOrder.getContainerNumber1()+"集装箱已被装车，请查看");
					}
				}
				if(tbOrder.getTransportType() == 6 || tbOrder.getTransportType() == 7) {
					if(tbOrder.getStepSelectCode() == 0) {
						if(cont.getStatus() != 0) {
							return LogisticsResult.build(0, tbOrder.getContainerNumber1()+"集装箱已被装车，请查看");
						}
					}else if(tbOrder.getStepSelectCode() == 1){
						if(cont.getStatus() != 1) {
							return LogisticsResult.build(0, tbOrder.getContainerNumber1()+"集装箱已被装车，请查看");
						}
					}
				}
				if(tbOrder.getTransportType() == 5) {
					if(tbOrder.getStepSelectCode() == 1) {
						if(cont.getStatus() != 1) {
							return LogisticsResult.build(0, tbOrder.getContainerNumber1()+"集装箱已被装车，请查看");
						}
					}
				}
			}
			if(tbOrder.getContainerNumber2() != "") {
				//存在多人同时操作一个项目下的集装箱 即多个运单选择同一集装箱号 此时不能新建运单
				TbContainer cont = contService.selectContainByConId(tbOrder.getContainerNumber2(),AppSession.getCurrentSysOrgCode());
				if(tbOrder.getTransportType() == 1 || tbOrder.getTransportType() == 2 || tbOrder.getTransportType() == 4) {
					if(cont.getStatus() != 0) {
						return LogisticsResult.build(0, tbOrder.getContainerNumber2()+"集装箱已被装车，请查看");
					}
				}
				if(tbOrder.getTransportType() == 6 || tbOrder.getTransportType() == 7) {
					if(tbOrder.getStepSelectCode() == 0) {
						if(cont.getStatus() != 0) {
							return LogisticsResult.build(0, tbOrder.getContainerNumber2()+"集装箱已被装车，请查看");
						}
					}else if(tbOrder.getStepSelectCode() == 1){
						if(cont.getStatus() != 1) {
							return LogisticsResult.build(0, tbOrder.getContainerNumber2()+"集装箱已被装车，请查看");
						}
					}
				}
				if(tbOrder.getTransportType() == 5) {
					if(tbOrder.getStepSelectCode() == 1) {
						if(cont.getStatus() != 1) {
							return LogisticsResult.build(0, tbOrder.getContainerNumber2()+"集装箱已被装车，请查看");
						}
					}
				}
			}
		}
		/*if(tbOrder.getSubsidy() == null) {
			return LogisticsResult.build(0, "请把信息补全");
		}*/
		/*if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把信息补全"); 
		}*/
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		user.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		boolean flag = shortBargeService.orderAdd(tbOrder, user,Constants.SHORT_ORDER_TYPE_BOX);
		if (flag != true) {
			return LogisticsResult.build(0, "新建失败");
		}
		return LogisticsResult.build(1, "新建成功");
	}

	/**
	 * 
	 * @description 项目下拉
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/project/getAll", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult project_getAll(@RequestParam byte projectType,HttpSession session) {
		//获取所有分支机构
		Map<String, Object> form = new HashMap<>();
		form.put("status", Constants.DOT_BRANCH_STATUS_YES);//正常使用的
		List<DotBranchDetail> branchGroups = branchGroupService.getDotBranchs(form);
		//获取该分支下的所有项目
		List<TbProject> projectList = shortBargeService.getProjects(projectType,branchGroups);
		return LogisticsResult.ok(projectList);
	}

	/**
	 * 
	 * @description 新建运单-根据下拉项目-查看项目详情
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/selectProject", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult selectProject(@RequestParam Integer id) {
		//TbProject tp = shortBargeService.selectDetailProject(id);
		LogisticsResult logisticsResult = projectManagmentService.selectProject(id);
		return logisticsResult;
	}

	/**
	 * 
	 * @description 获取车辆下拉 -此车辆必须是该项目下的 短驳承运方车队的其中一个
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 	mode 0 平台 1 自选
	 * 			shortType 0 接取 1 送达 2 汽运
	 * @return
	 */
	@RequestMapping(value = "/carTeam/select", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult selectCarTeam(@RequestParam(required=false) Integer id,@RequestParam(required=false) Integer driverId,@RequestParam(required=false) Integer model,@RequestParam(required=false) Integer shortType) {
		boolean flag = false;
		if(id!=null){
			//判断项目id shortType 今日是否有分配任务 
			/*
			 * true  有任务
			 * false 无任务
			 * */
			flag =  publishJobService.isHaveJob(id,shortType);
		}
		List<TbOrderCarDetail> tbCarTeam =null;
		if(model != null){
			if(model ==1){
				//自选
				/*
				 * 1 通过项目id mode 找到短驳承运方表
				 * 2 通过短驳承运方 shortBargeId (车队id) 找到车队下的所有车辆
				 * */
				 tbCarTeam = shortBargeService.selectCarTeams(id,driverId,shortType);
			}else if(model == 0){
				//平台 
				/*
				 * 1 通过项目id mode 找到短驳承运方表
				 * 2 通过短驳承运方 shortBargeId (挂靠公司id) ，再取此公司下所有挂靠的车队和司机
				 * */
				tbCarTeam = shortBargeService.selectCarTeamsByPlatform(id,driverId,shortType);
			}
		}
		if(!flag){
			return LogisticsResult.build(1001,"您选择的项目今日未分配任务!",tbCarTeam);
		}
		return LogisticsResult.ok(tbCarTeam);
	}
	
	/**
	 * 
	 * @description 运单回收站  运单还原 
	 * @author liangLin
	 * @date 2017年12月23日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/order/revert", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult orderRevert(@RequestParam("idList[]") List<Integer> idList) {
		LogisticsResult logisticsResult= shortBargeService.revertOrder(idList);
		return logisticsResult;
	}
	
	/**
	 * 
	 * @description 异常处理信息获取 
	 * @author liangLin
	 * @date 2017年12月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exception/get", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult exception(@RequestParam Integer id) {
		TbOrder tbOder= shortBargeService.getExceptionByOrderId(id);
		return LogisticsResult.ok(tbOder);
	}
	
	/**
	 * 
	 * @description  异常处理-驳回
	 * @author liangLin
	 * @date 2017年12月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exception/reject", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult rejectException(@RequestParam Integer id) {
		boolean flag = shortBargeService.rejectException(id);
		if (flag != true) {
			return LogisticsResult.build(0, "操作失败");
		}
		return LogisticsResult.build(1, "操作成功");
	}	
	
	/**
	 * @description APP项目下拉
	 * @author LiangDeng
	 * @date 2017年1月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/appGetAllProjectByBulk", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult appGetAllProjectByBulk(HttpSession session) {
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		List<ProjectAppHelp> projectList = shortBargeService.appGetAllProjectByBulk(user.getId(),null);
		return LogisticsResult.ok(projectList);
	}
	
}
