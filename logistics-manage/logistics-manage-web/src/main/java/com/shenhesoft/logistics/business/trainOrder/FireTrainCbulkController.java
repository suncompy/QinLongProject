// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.trainOrder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.shenhesoft.logistics.business.helpPojo.ProjectOperationDetail;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderCargoByBulkDetail;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderCargoPlaceDetail;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderSearch;
import com.shenhesoft.logistics.business.pojo.historyLocation.TbHistoryLocation;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;
import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalce;
import com.shenhesoft.logistics.business.shortbarge.order.ShortBargeOrderService;
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
import com.shenhesoft.logistics.finance.AdvanceCharge;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.cargo.interfaces.CargomanagementService;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.helpPojo.TbCargoMainPoint;
import com.shenhesoft.logistics.manage.interfaces.SiteManageService;
import com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocation;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargo;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.pojo.trianType.TbTrainType;

/**
 * @description 火运管理散装箱
 *
 * @author LiangDeng
 *
 * @date 2017年12月25日
 */
@Controller
@RequestMapping("/fireTrainCbulkLoading")
public class FireTrainCbulkController {
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;
	
	@Autowired
	private TrainOrderService trainOrderService;
	
	@Autowired
	private ShortBargeOrderService shortBargeService;
	
	/**
	 * 站点服务
	 */
	@Autowired
	private SiteManageService siteManageService;
	
	/**
	 * 网点分支服务
	 */
	@Autowired
	private BranchGroupService branchGroupService;
	
	/**
	 * 货物服务
	 */
	@Autowired
	private CargomanagementService cargomanagementService;
	
	/**
	 * @description 集装箱，散装箱管理
	 * @author LiangDeng
	 * @date 2017年12月18日
	 * @param type:0,集装箱  1,散装箱
	 * @return
	 */
	@RequestMapping(value = "/showTrainOrderByType", method = RequestMethod.GET)
	public String trainOrderByType(Model model,HttpSession session){
		byte type = 1;
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		
		//获取该用户所属的网点分支
		List<TbBranchGroup> branchGroups = branchGroupService.selectBranchGroupByUid(user.getId());
		model.addAttribute("branchGroups", branchGroups);
		
		//获取所有货物品名
		List<TbCargoMainPoint> cargos = cargomanagementService.listCargo();
		model.addAttribute("cargos", cargos);
		
		TrainOrderSearch search = new TrainOrderSearch();
		//运单列表
		DataGridResult result = trainOrderService.selectTrainOrderByPage(PAGE_NUM,CUSTOMER_PAGE_LIMIT,type,user,search);
		model.addAttribute("trainOrderList", result);
		//历史记录列表
		DataGridResult historyResult = trainOrderService.selectHistoryOrderByPage(PAGE_NUM,CUSTOMER_PAGE_LIMIT,type,user,search);
		model.addAttribute("historyResult", historyResult);
		//异常运单
		DataGridResult exceptionResult = trainOrderService.selectExceptionOrderByPage(PAGE_NUM,CUSTOMER_PAGE_LIMIT,type,user,search);
		model.addAttribute("exceptionResult", exceptionResult);
		//删除运单
		DataGridResult deleteResult = trainOrderService.selectDeleteOrderByPage(PAGE_NUM,CUSTOMER_PAGE_LIMIT,type,user,search);
		model.addAttribute("deleteResult", deleteResult);
		return "/html/business/trianTransport/fireTrainCbulkLoading";
	}
	
	public TbTrainOrder exportChangeContent(TbTrainOrder order) {
		/** 运单状态:0:取消,1:等待承认,2:等待装车,3:等待发运,4:在途运载,5:等待卸货,6:等待回单,7:已完成*/
		if(order.getStatus() == 1) {
			order.setStatusName("等待承认");
		}else if(order.getStatus() == 2) {
			order.setStatusName("等待装车");
		}else if(order.getStatus() == 3) {
			order.setStatusName("等待发运");
		}else if(order.getStatus() == 4) {
			order.setStatusName("在途运载");
		}else if(order.getStatus() == 5) {
			order.setStatusName("等待卸货");
		}else if(order.getStatus() == 6) {
			order.setStatusName("等待回单");
		}else if(order.getStatus() == 7) {
			order.setStatusName("已完成");
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		order.setExportEntruckTime(DateUtils.date2Str(order.getEntruckDate(),sdf));
		order.setExportCreatTime(DateUtils.date2Str(order.getCreateDate(),sdf));
		order.setExportUpdateTime(DateUtils.date2Str(order.getUpdateDate(),sdf));
		order.setExportSendTime(DateUtils.date2Str(order.getSendDate(),sdf));
		order.setExportArriveDate(DateUtils.date2Str(order.getArriveDate(),sdf));
		order.setExportDeleteDate(DateUtils.date2Str(order.getDeleteDate(),sdf));
		order.setExportExceptionReportDate(DateUtils.date2Str(order.getExceptionReportDate(),sdf));
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
		TrainOrderSearch search = FormUtil.populate(TrainOrderSearch.class, map, false);
		byte type = 1;
		//全部
		DataGridResult result = trainOrderService.selectTrainOrderByPage(page,CUSTOMER_PAGE_LIMIT,type,user,search);
		String[] heads = {"项目编号","运单编号","创建时间","运单状态","状态更新时间","物流员","分支机构","货物品名","始发站点","装车时间","发车时间","到达站点","请车数","承认车数","落车数"};
		String[] headCodes = {"projectCode","pleaseTrainNumber","exportCreatTime","statusName","exportUpdateTime","sendOperatorId","branchName","cargoName","beginSite","exportEntruckTime","exportSendTime","endSite","pleaseCarNum","sureCarNum","loseCarNum"};
		
		List<TbTrainOrder> list = result.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {
			for(TbTrainOrder order:list) {
				exportChangeContent(order);
				datas.add(FormUtil.populate(order));
			}	
		}
		if(ExcelUtil.createExcel(request,response, "散堆装管理", "散堆装管理", heads, headCodes, datas)){
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
		TrainOrderSearch search = FormUtil.populate(TrainOrderSearch.class, map, false);
		byte type = 1;
		//全部
		DataGridResult exceptionResult = trainOrderService.selectExceptionOrderByPage(page,CUSTOMER_PAGE_LIMIT,type,user,search);
		String[] heads = {"项目编号","运单编号","创建时间","运单状态","状态更新时间","物流员","分支机构","货物品名","始发站点","装车时间","发车时间","到达站点","请车数","承认车数","落车数","填报时间","异常提报人","异常原因"};
		String[] headCodes = {"projectCode","pleaseTrainNumber","exportCreatTime","statusName","exportUpdateTime","sendOperatorId","branchName","cargoName","beginSite","exportEntruckTime","exportSendTime","endSite","pleaseCarNum","sureCarNum","loseCarNum","exportExceptionReportDate","exceptionReportPerson","exceptionReason"};
		
		List<TbTrainOrder> list = exceptionResult.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {
			for(TbTrainOrder order:list) {
				exportChangeContent(order);
				datas.add(FormUtil.populate(order));
			}	
		}
		if(ExcelUtil.createExcel(request,response, "散堆装管理", "散堆装管理", heads, headCodes, datas)){
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
		TrainOrderSearch search = FormUtil.populate(TrainOrderSearch.class, map, false);
		byte type = 1;
		//全部
		DataGridResult historyResult = trainOrderService.selectHistoryOrderByPage(page,CUSTOMER_PAGE_LIMIT,type,user,search);
		String[] heads = {"项目编号","运单编号","创建时间","运单状态","状态更新时间","物流员","分支机构","货物品名","始发站点","装车时间","发车时间","到达站点","请车数","承认车数","落车数"};
		String[] headCodes = {"projectCode","pleaseTrainNumber","exportCreatTime","statusName","exportUpdateTime","sendOperatorId","branchName","cargoName","beginSite","exportEntruckTime","exportSendTime","endSite","pleaseCarNum","sureCarNum","loseCarNum"};
		
		List<TbTrainOrder> list = historyResult.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {
			for(TbTrainOrder order:list) {
				exportChangeContent(order);
				datas.add(FormUtil.populate(order));
			}	
		}
		if(ExcelUtil.createExcel(request,response, "散堆装管理", "散堆装管理", heads, headCodes, datas)){
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
		TrainOrderSearch search = FormUtil.populate(TrainOrderSearch.class, map, false);
		byte type = 1;
		//全部
		DataGridResult deleteResult = trainOrderService.selectDeleteOrderByPage(page,CUSTOMER_PAGE_LIMIT,type,user,search);
		String[] heads = {"项目编号","运单编号","创建时间","运单状态","状态更新时间","物流员","分支机构","货物品名","始发站点","装车时间","发车时间","到达站点","请车数","承认车数","落车数","删除时间","删除人","删除原因"};
		String[] headCodes = {"projectCode","pleaseTrainNumber","exportCreatTime","statusName","exportUpdateTime","sendOperatorId","branchName","cargoName","beginSite","exportEntruckTime","exportSendTime","endSite","pleaseCarNum","sureCarNum","loseCarNum","exportDeleteDate","deletePerson","deleteReason"};
		
		List<TbTrainOrder> list = deleteResult.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {//exportCreatTime statusName exportUpdateTime exportEntruckTime exportSendTime
			for(TbTrainOrder order:list) {
				exportChangeContent(order);
				datas.add(FormUtil.populate(order));
			}	
		}
		if(ExcelUtil.createExcel(request,response, "散堆装管理", "散堆装管理", heads, headCodes, datas)){
			return new ShResponse(HttpStatus.OK.value(), "","下载成功");
		}
		return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
	}
	
	//分页
	@RequestMapping("/selectTrainOrderByPage")
	@ResponseBody
	public LogisticsResult selectTrainOrderByPage(Integer page,String trainOrderSearch,HttpSession session){
		byte type = 1;
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		TrainOrderSearch search = JsonUtils.jsonToPojo(trainOrderSearch,TrainOrderSearch.class);
		//运单列表 和历史记录列表
		DataGridResult result = trainOrderService.selectTrainOrderByPage(page,CUSTOMER_PAGE_LIMIT,type,user,search);
		return LogisticsResult.ok(result);
	}
	
	@RequestMapping("/selectHistoryOrderByPage")
	@ResponseBody
	public LogisticsResult selectHistoryOrderByPage(Integer page,String trainOrderSearch,HttpSession session){
		byte type = 1;
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		TrainOrderSearch search = JsonUtils.jsonToPojo(trainOrderSearch,TrainOrderSearch.class);
		//历史列表 
		DataGridResult historyResult = trainOrderService.selectHistoryOrderByPage(page,CUSTOMER_PAGE_LIMIT,type,user,search);
		return LogisticsResult.ok(historyResult);
	}
	
	@RequestMapping("/selectExceptionOrderByPage")
	@ResponseBody
	public LogisticsResult selectExceptionOrderByPage(Integer page,String trainOrderSearch,HttpSession session){
		byte type = 1;
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		TrainOrderSearch search = JsonUtils.jsonToPojo(trainOrderSearch,TrainOrderSearch.class);
		//异常运单
		DataGridResult exceptionResult = trainOrderService.selectExceptionOrderByPage(page,CUSTOMER_PAGE_LIMIT,type,user,search);
		return LogisticsResult.ok(exceptionResult);
	}
	
	@RequestMapping("/selectDeleteOrderByPage")
	@ResponseBody
	public LogisticsResult selectDeleteOrderByPage(Integer page,String trainOrderSearch,HttpSession session){
		byte type = 1;
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		TrainOrderSearch search = JsonUtils.jsonToPojo(trainOrderSearch,TrainOrderSearch.class);
		//删除运单 
		DataGridResult exceptionResult = trainOrderService.selectDeleteOrderByPage(page,CUSTOMER_PAGE_LIMIT,type,user,search);
		return LogisticsResult.ok(exceptionResult);
	}
	
	/**
	 * @description 条件查询火运管理 散堆装的运单列表
	 * @date 2018年1月9日
	 * @author shilvfei
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/listTrainOrderByCriteria")
	@ResponseBody
	public LogisticsResult listTrainOrderByCriteria(TrainOrderSearch trainOrderSearch,HttpSession session){
		TbSystemUser user =  (TbSystemUser) session.getAttribute("systemUser");
		List<TbTrainOrder> trainOrders =  trainOrderService.listTrainOrderByCriteria(trainOrderSearch,user);
		return LogisticsResult.ok(trainOrders);
	}
	
	/**
	 * @description 运单详情
	 * @author LiangDeng
	 * @date 2017年12月24日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/trainOrderDetailById", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult trainOrderDetailById(@RequestParam Integer id,@RequestParam byte type){
		TbTrainOrder trainOrderdetail = trainOrderService.selectTrainOrderById(id);
		List<TbHistoryLocation> historyLocationDetail = trainOrderService.selectHistoryLocationById(id,type);
		List<TbTrainOrderCargoPalce> detail = trainOrderService.selectTrainCargoByOrdeId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		TbProject tp = shortBargeService.selectDetailProject(trainOrderdetail.getProjectId());
		//实际运输费用
		BigDecimal cost = new BigDecimal(0);
		if(trainOrderdetail.getEntruckWeight() != null && tp.getFreightSum() != null) {
			cost = trainOrderdetail.getEntruckWeight().multiply(tp.getFreightSum());
		}
		//查询项目下起始站点货场货位
		List<TbStock> stockList = trainOrderService.selectStockList(tp.getId(),tp.getBeginSiteId());
		BigDecimal currentQtys = new BigDecimal(0);
		if(stockList!=null && stockList.size()>0){
			if(stockList.size()>1){
				for (TbStock tbStock : stockList) {
					currentQtys = currentQtys.add(tbStock.getCurrentQty());
				}
				map.put("sumCurrentQtys",currentQtys);
				map.put("stocks",1);
			}else{
				map.put("stocks",2);
				map.put("freightYardNames",stockList.get(0).getFreightYardName());
				map.put("cargoLocationNames",stockList.get(0).getCargoLocationName());
				map.put("currentQtys",stockList.get(0).getCurrentQty());
			}
		}
		map.put("trainOrderdetail", trainOrderdetail);
		map.put("historyLocationDetail", historyLocationDetail);
		map.put("detail", detail);
		map.put("cost", cost);
		return LogisticsResult.ok(map);
	}
	
	/**
	 * @description 新增运单
	 * @author LiangDeng
	 * @date 2017年12月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addTrainOrder", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addTrainOrder(@Valid TbTrainOrder trainOrder,BindingResult result,HttpSession session){
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把信息补全"); 
		}
		//compareTo方法，-1小于，0相等，1大于
		int res1 = trainOrder.getKuCun().compareTo(new BigDecimal(trainOrder.getEstimateWeight()));
		if(res1 == -1) {
			return LogisticsResult.build(0, "预载重不能大于库存");
		}
		int res2 = trainOrder.getAdvanceCharge().compareTo(new BigDecimal(trainOrder.getEstimateCost()));
		if(res2 == -1) {
			return LogisticsResult.build(0, "预计费用不能大于账户款余额");
		}
		TbSystemUser user =  (TbSystemUser) session.getAttribute("systemUser");
		trainOrder.setSendOperatorId(user.getName());
		user.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		int row = trainOrderService.addTrainOrder(trainOrder,user);
		if(row != 1){
			return LogisticsResult.build(0, "新增失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * 
	 * @description 新建运单-根据下拉项目-查看项目详情
	 * @author LiangDeng
	 * @date 2017年12月26日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/selectProject", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult selectProject(@RequestParam Integer id) {
		TbProject tp = shortBargeService.selectDetailProject(id);
		//TbTrainStation trainStation = trainOrderService.selectTrainStationById(tp.getBeginCenterSiteId());
		//查询中心站点下的预付款账户
		AdvanceCharge advance = trainOrderService.selectAccountListById(tp.getId(),tp.getBeginCenterSiteId());
		//查询项目下起始站点货场货位
		List<TbStock> stockList = trainOrderService.selectStockList(tp.getId(),tp.getBeginSiteId());
		Map<String, Object> map = new HashMap<String, Object>();
		BigDecimal currentQty = new BigDecimal(0);
		if(stockList!=null && stockList.size()>0){
			if(stockList.size()>1){
				for (TbStock tbStock : stockList) {
					currentQty = currentQty.add(tbStock.getCurrentQty());
				}
				map.put("sumCurrentQty",currentQty);
				map.put("stock",1);
			}else{
				map.put("stock",2);
				map.put("freightYardName",stockList.get(0).getFreightYardName());
				//查询货位编号
				TbCargoLocation cl = trainOrderService.selectCargoLocationById(stockList.get(0).getCargoLocationId());
				String codeAndName = cl.getCode() +" " + cl.getName();
				//map.put("cargoLocationName",stockList.get(0).getCargoLocationName());
				map.put("cargoLocationName",codeAndName);
				map.put("currentQty",stockList.get(0).getCurrentQty());
			}
		}
		map.put("tp", tp);
		//map.put("trainStation", trainStation);
		map.put("advance", advance);
		return LogisticsResult.ok(map);
	}
	
	/**
	 * @description 项目下拉
	 * @author liangLin
	 * @date 2017年12月25日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getAllProject", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult project_getAll(@RequestParam byte projectType) {
		//获取所有分支机构
		Map<String, Object> form = new HashMap<>();
		form.put("status", Constants.DOT_BRANCH_STATUS_YES);//正常使用的
		List<DotBranchDetail> branchGroups = branchGroupService.getDotBranchs(form);
		List<TbProject> projectList = trainOrderService.selectAllProject(projectType,branchGroups);
		return LogisticsResult.ok(projectList);
	}

	/**
	 * @description 请车类型下拉
	 * @author liangLin
	 * @date 2017年12月25日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/pleaseTrainType", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult pleaseTrainType() {
		List<TbTrainType> trainTypeList = trainOrderService.selectAllTrainType();
		return LogisticsResult.ok(trainTypeList);
	}
	/**
	 * @description 删除运单
	 * @author LiangDeng
	 * @date 2017年12月25日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/deleteTrainOrderByParam", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult deleteTrainOrderByParam(@RequestParam Integer id,@RequestParam(required = false) String reason,HttpSession session){
		if(reason == null || reason == ""){
			return LogisticsResult.build(1, "请填写删除原因");
		}
		TbSystemUser user =  (TbSystemUser) session.getAttribute("systemUser");
		int row = trainOrderService.deleteTrainOrderByParam(id,reason,user.getName());
		if(row != 1){
			return LogisticsResult.build(0, "删除失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 获取等待承运承运信息
	 * @author LiangDeng
	 * @date 2017年12月22日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getCarrierMsg", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult getCarrierMsg(@RequestParam Integer id){
		TbTrainOrder trainOrder = trainOrderService.selectTrainOrderById(id);
		//根据运单始发的中心站点查询所包含的货场货位
		TbProject tp = shortBargeService.selectDetailProject(trainOrder.getProjectId());
		List<TbFreightYard> freightYardLists = siteManageService.getFreightYardsByTrainStationId(tp.getBeginSiteId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trainOrder", trainOrder);
		map.put("freightYardLists", freightYardLists);
		return LogisticsResult.ok(map);
	}
	
	/**
	 * @description 等待承运
	 * @author LiangDeng
	 * @date 2017年12月22日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addCarrier", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addCarrier(@RequestParam Integer id,@RequestParam Integer sureNum,HttpSession session){
		if(sureNum == null){
			return LogisticsResult.build(0, "承认车数不能为空");
		}
		TbSystemUser user =  (TbSystemUser) session.getAttribute("systemUser");
		int row = 0;
		//承认车数为0，自动放入回收站
		if(sureNum == 0){
			row = trainOrderService.deleteTrainOrderById(id,user.getName(),sureNum);
			return LogisticsResult.build(1, "该运单已放入回收站");
		}
		row = trainOrderService.updateTrainOrderById(id,user.getName(),sureNum);
		if(row != 1){
			return LogisticsResult.build(2, "操作失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 删除订单还原
	 * @author LiangDeng
	 * @date 2017年12月22日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/resetDeleteOrder", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult resetDeleteOrder(@RequestParam Integer id){
		TbTrainOrder trainOrder = trainOrderService.selectTrainOrderById(id);
		Date time = new Date();
		long t = time.getTime() - trainOrder.getDeleteDate().getTime()>0?(time.getTime() - trainOrder.getDeleteDate().getTime())/60/1000:0;
		if(t > 1440){
			return LogisticsResult.build(0, "还原失败,请勿勾选删除时长未超过24小时的订单。");
		}
		int row = trainOrderService.resetOrderById(id);
		if(row != 1){
			return LogisticsResult.build(1, "还原失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 等待装车
	 * @author LiangDeng
	 * @date 2017年12月23日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addWaitEntruck", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addWaitEntruck(@Valid TrainOrderCargoByBulkDetail orderCargoPlaceDetail,BindingResult result,HttpSession session){
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把信息补全"); 
		}
		TbSystemUser user =  (TbSystemUser) session.getAttribute("systemUser");
		user.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		LogisticsResult row = trainOrderService.addWaitEntruckOfBulk(orderCargoPlaceDetail,user,session);
		/*if(row.getStatus() != 200){
			return LogisticsResult.build(1, "操作失败");
		}*/
		return row;
	}
	
	/**
	 * @description 获取等待发运信息
	 * @author LiangDeng
	 * @date 2017年12月23日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getSendMsg", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult getSendMsg(@RequestParam Integer id){
		TbTrainOrder trainOrder = trainOrderService.selectTrainOrderById(id);
		List<TbTrainOrderCargoPalce> trainCargoList = trainOrderService.selectTrainCargoByOrdeId(id);
		//根据运单到达的中心站点查询所包含的货场货位
		TbProject tp = shortBargeService.selectDetailProject(trainOrder.getProjectId());
		List<TbFreightYard> freightYardList = siteManageService.getFreightYardsByTrainStationId(tp.getEndSiteId());
		//历史位置信息
		List<TbHistoryLocation> historyLocation = trainOrderService.selectHistoryLocationById(id,(byte)1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trainOrder", trainOrder);
		map.put("trainCargoList", trainCargoList);
		map.put("freightYardList", freightYardList);
		map.put("historyLocation", historyLocation);
		return LogisticsResult.ok(map);
	}
	
	/**
	 * @description 获取在途运载信息
	 * @author LiangDeng
	 * @date 2017年12月23日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getHistoryLocationMsg", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult getHistoryLocationMsg(@RequestParam Integer id,@RequestParam byte type){
		List<TbHistoryLocation> historyLocation = trainOrderService.selectHistoryLocationById(id,type);
		List<TbTrainOrderCargoPalce> hlTrainCargoList = trainOrderService.selectTrainCargoByOrdeId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("historyLocation", historyLocation);
		map.put("hlTrainCargoList", hlTrainCargoList);
		return LogisticsResult.ok(map);
	}
	
	/**
	 * @description 更新实时位置
	 * @author LiangDeng
	 * @date 2017年12月23日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult updateLocation(@RequestParam Integer orderIdLocation,@RequestParam String location){
		int row = trainOrderService.insertNewLocation(orderIdLocation,location);
		if(row != 1){
			return LogisticsResult.build(1, "操作失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 上传发运订单
	 * @author LiangDeng
	 * @date 2017年12月23日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateSendImg", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult updateSendImg(@RequestParam Integer id,@RequestParam String sendImg,HttpSession session){
		String img = "";
		if(!StringUtils.isBlank(sendImg)){
			 LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo",sendImg,null);
			 System.out.println(base64UpLoad.getStatus());
			 if(base64UpLoad.getStatus()==200){
				 img=base64UpLoad.getData().toString();
			 }else{
				 return LogisticsResult.build(400, "上传图片失败!");
			 }
		 }
		int row = trainOrderService.updateSendImgById(id,img);
		if(row != 1){
			return LogisticsResult.build(1, "操作失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 上传到货订单
	 * @author LiangDeng
	 * @date 2017年12月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateArriveImg", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult updateArriveImg(@RequestParam Integer id,@RequestParam String arriveImg,HttpSession session){
		String img = "";
		if(!StringUtils.isBlank(arriveImg)){
			 LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo",arriveImg,null);
			 System.out.println(base64UpLoad.getStatus());
			 if(base64UpLoad.getStatus()==200){
				 img=base64UpLoad.getData().toString();
			 }else{
				 return LogisticsResult.build(400, "上传图片失败!");
			 }
		 }
		int row = trainOrderService.updateArriveImgById(id,img);
		if(row != 1){
			return LogisticsResult.build(1, "操作失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 删除上传发运运单
	 * @author LiangDeng
	 * @date 2017年12月24日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/deleteSendImg", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult deleteSendImg(@RequestParam Integer id){
		int row = trainOrderService.deleteSendImgById(id);
		if(row != 1){
			return LogisticsResult.build(1, "操作失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 删除到货上传运单
	 * @author LiangDeng
	 * @date 2017年12月24日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/deleteArriveImg", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult deleteArriveImg(@RequestParam Integer id){
		int row = trainOrderService.deleteArriveImg(id);
		if(row != 1){
			return LogisticsResult.build(1, "操作失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 等待发运和在途运载更新状态
	 * @author LiangDeng
	 * @date 2017年12月24日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updOrderStatusByParam", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult updOrderStatusByParam(@RequestParam Integer id,@RequestParam byte status,HttpSession session){
		TbSystemUser user =  (TbSystemUser) session.getAttribute("systemUser");
		user.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		int row = trainOrderService.updOrderStatusByParam(id,status,user);
		if(row != 1){
			return LogisticsResult.build(1, "操作失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 驳回异常运单
	 * @author LiangDeng
	 * @date 2017年12月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/handleExceptionById", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult handleExceptionById(@RequestParam Integer handleEcxptionId){
		int row = trainOrderService.handleExceptionById(handleEcxptionId);
		if(row != 1){
			return LogisticsResult.build(1, "操作失败");
		}
		return LogisticsResult.ok();
	}
	
	//异常运单确认无误
	
	/**
	 * @description 等待卸货分配卸货货场货位
	 * @author LiangDeng
	 * @date 2017年12月29日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateUnloadWeight", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult updateUnloadWeight(@RequestParam String jsonArray,@RequestParam Integer projectId,HttpSession session){
		//TbProject project = shortBargeService.selectDetailProject(projectId);
		List<TbTrainOrderCargoPalce> cargoPlaceList = JsonUtils.jsonToList(jsonArray,TbTrainOrderCargoPalce.class);
		int row = trainOrderService.unloadInfoByListOfBulk(cargoPlaceList,projectId,session);
		
		if(row != cargoPlaceList.size()){
			return LogisticsResult.build(1, "操作失败");
		}
		return LogisticsResult.ok();
	}

}
