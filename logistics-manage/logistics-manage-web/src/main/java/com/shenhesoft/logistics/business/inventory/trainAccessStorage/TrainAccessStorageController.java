// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.inventory.trainAccessStorage;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderDetail;
import com.shenhesoft.logistics.business.shortbarge.order.ShortBargeOrderService;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.office.ExcelUtil;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.cargo.interfaces.CargomanagementService;
import com.shenhesoft.logistics.manage.customer.CustomerInfoService;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.TbCargoMainPoint;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargo;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.search.OrderSearch;

/**
 * @description 火运出入库查询
 *
 * @author LiangDeng
 *
 * @date 2018年1月3日
 */
@Controller
@RequestMapping("/trainAccessStorage")
public class TrainAccessStorageController {
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;
	
	@Autowired
	private TrainAccessStorageService trainAccessStorageService;
	
	@Autowired
	private ShortBargeOrderService shortBargeService;
	
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
	
	@Autowired
	private CustomerInfoService customerInfoService;
	/**
	 * @description 火运出入库查询项目列表
	 * @author LiangDeng
	 * @date 2018年1月3日
	 * @param 
	 * @return
	 */
	@RequestMapping("/trainAccessStorageList")
	public String trainAccessStorageList(Model model,HttpSession session){
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		
		//获取该用户所属的网点分支
		List<TbBranchGroup> branchGroups = branchGroupService.selectBranchGroupByUid(user.getId());
		model.addAttribute("branchGroups", branchGroups);
		
		//获取所有货物品名
		List<TbCargoMainPoint> cargos = cargomanagementService.listCargo();
		model.addAttribute("cargos", cargos);
		
		//获取所有的企业customer
		List<CustomerInfo> customers = customerInfoService.selectCustomers(Constants.CUSTOMER_STATUS_YES);
		model.addAttribute("customers", customers);
		TrainOrderDetail trainOrderDetail = new TrainOrderDetail();
		DataGridResult result = trainAccessStorageService.selectTrainAccessStorageList(PAGE_NUM,CUSTOMER_PAGE_LIMIT, trainOrderDetail);
		model.addAttribute("trainAccessStorageList", result);
		return "/html/business/inventory/trainAccessStorage";
	}
	
	public TrainOrderDetail exportChangeContent(TrainOrderDetail detail) {
		if(detail.getTransportType() == 3) {
			detail.setExportTransportType("火运");
		}else if(detail.getTransportType() == 4) {
			detail.setExportTransportType("接取+火运");
		}else if(detail.getTransportType() == 5) {
			detail.setExportTransportType("火运+送达");
		}else if(detail.getTransportType() == 6) {
			detail.setExportTransportType("联运");
		}else if(detail.getTransportType() == 7) {
			detail.setExportTransportType("接取+送达");
		}
		if(detail.getProjectType() == 0) {
			detail.setExportProjectType("集装箱");
		}else if(detail.getProjectType() == 1) {
			detail.setExportProjectType("散装箱");
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		detail.setExportSendDate(DateUtils.date2Str(detail.getSendDate(),sdf));
		detail.setExportArriveDate(DateUtils.date2Str(detail.getArriveDate(),sdf));
		return detail;
	}
	/**
	 * 导出
	 * @description 
	 * @author LiangDeng
	 * @date 2018年3月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse export1(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map =FormUtil.getParameterMap(request);
		int page = Integer.parseInt(map.get("pageNo").toString());
		TbSystemUser user = AppSession.getCurrentUser();
		TrainOrderDetail trainOrderDetail = FormUtil.populate(TrainOrderDetail.class, map, false);
		DataGridResult result = trainAccessStorageService.selectTrainAccessStorageList(page,CUSTOMER_PAGE_LIMIT, trainOrderDetail);
		String[] heads = {"项目编号","项目类型","请车单号","联运模式","分支机构","发货企业","收货企业","货物品名","车型","车号","始发站点","装载吨位","装载货场","装载货位","发送时间","到达站点","到货吨位","到达货场","到达货位","到货时间","耗损"};
		String[] headCodes = {"projectCode","exportProjectType","pleaseTrainNumber","exportTransportType","branchName","sendCargoCompanyName","receiveCargoCompanyName","cargoName","carType","carNumber","beginSiteName","entruckWeight","cargoPlaceName","cargoSiteName","exportSendDate","endSiteName","arriveWeight","arriveCargoPlaceName","arriveCargoSiteName","exportArriveDate","wastage"};
		
		List<TrainOrderDetail> list = result.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {
			for (TrainOrderDetail detail : list) {
				exportChangeContent(detail);
				datas.add(FormUtil.populate(detail));
			}
		}
		if(ExcelUtil.createExcel(request,response, "火运出入库", "火运出入库", heads, headCodes, datas)){
			return new ShResponse(HttpStatus.OK.value(), "","下载成功");
		}
		return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
	}
	
	//分页
	@RequestMapping("/trainAccessStorageListByPage")
	@ResponseBody
	public LogisticsResult selectTrainOrderByPage(Integer page,String trainOrderDetail){
		TrainOrderDetail jsonToPojo = JsonUtils.jsonToPojo(trainOrderDetail,TrainOrderDetail.class);
		DataGridResult result = trainAccessStorageService.selectTrainAccessStorageList(page,CUSTOMER_PAGE_LIMIT,jsonToPojo);
		return LogisticsResult.ok(result);
	}
	
	/**
	 * @description 查询统计
	 * @author LiangDeng
	 * @date 2018年1月4日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/trainAccessStorageDteail", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult queryStatistic(@RequestParam Integer projectId){
		TbProject tp = shortBargeService.selectDetailProject(projectId);
		Map<String, Object> sumMap= trainAccessStorageService.queryStatistic(projectId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tp", tp);
		map.put("trainOrderDetailList", sumMap.get("list"));
		map.put("sumDetailInfo", sumMap.get("sumDetail"));
		return LogisticsResult.ok(map);
	}
}
