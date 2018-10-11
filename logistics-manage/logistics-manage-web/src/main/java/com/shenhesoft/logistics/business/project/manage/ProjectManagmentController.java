package com.shenhesoft.logistics.business.project.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.shenhesoft.logistics.business.caretam.CarTeamService;
import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.business.helpPojo.TbProjectDetail;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBarge;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.ResultCodeUtils;
import com.shenhesoft.logistics.common.util.ResultContentUtils;
import com.shenhesoft.logistics.common.util.office.ExcelUtil;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.cargo.interfaces.CargomanagementService;
import com.shenhesoft.logistics.manage.customer.CustomerInfoService;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.interfaces.TrainStationService;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.carTeam.TbCarTeam;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargo;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.search.OrderSearch;
import com.shenhesoft.logistics.system.CodeService;

/**
 * @description:项目管理(项目管理)
 * 
 * @author shilvfei
 * 
 * @date 2017年12月18日
 */
@Controller
@RequestMapping("/projectManagment")
public class ProjectManagmentController {

	@Autowired
	private ProjectManagmentService projectManagmentService;
	
	@Autowired
	private CarTeamService  carTeamService;
	
	@Autowired
	private CodeService codeService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${PROJECT_PAGE_LIMIT}")
	private Integer PROJECT_PAGE_LIMIT;
	
	/**
	 * @description 获取项目信息
	 * @date 2017年12月9日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping("/projectManagment")
	public String getProjectManagment(Model model){
		//获取短驳承运方
		Integer companyId = AppSession.getCurrentUser().getCompanyId();
		List<TbCarTeam> carTeams = carTeamService.selectAnchoredCarTeam(companyId);
		model.addAttribute("carTeams", carTeams);
		//分发转向
		return "/html/business/project/projectManagment";
	}
	
	/**
	 * @description 获取下拉框的数据
	 * @date 2017年12月19日
	 * @author shilvfei
	 * @param status
	 * @return
	 */
	/*@RequestMapping("/getProjectSelect")
	@ResponseBody
	public LogisticsResult getProjectSelect(HttpSession session,Byte status){
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		
		//根据不同的联运模式获取信息
		Map<String, Object> map = new HashMap<>();
		map.put("status", Constants.DOT_BRANCH_STATUS_YES);
		//获取所有分支机构
		List<DotBranchDetail> branchGroups = branchGroupService.getDotBranchs(map);
		
		//获取所有货物品名
		List<TbCargo> cargos = cargomanagementService.listCargo();
		//获取所有的企业customer
		List<CustomerInfo> customers = customerInfoService.selectCustomers(Constants.CUSTOMER_STATUS_YES);
		//获取短驳承运方
		Integer companyId = user.getCompanyId();
		List<TbCarTeam> carTeams = carTeamService.selectAnchoredCarTeam(companyId);
		//获取所有的火车最明细站点
		List<TbTrainStation> threeLevelStations = trainStationService.selectThreeTrainStationByLevel(Constants.SITE_LEVEL_THREE);
		
		//获取所有的火车二级站点
		List<TbTrainStation> twoLevelStations = trainStationService.selectThreeTrainStationByLevel(Constants.SITE_LEVEL_TWO);
		
		map.put("branchGroups", branchGroups);
		map.put("cargos", cargos);
		map.put("customers", customers);
		map.put("carTeams", carTeams);
		map.put("threeLevelStations", threeLevelStations);
		map.put("twoLevelStations", twoLevelStations);
		return LogisticsResult.ok(map);
	}*/
	
	/**
	 * @description 新增项目
	 * @date 2018年1月9日
	 * @author shilvfei
	 * @param project
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addProject", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addProject(@Valid ProjectDetail project,BindingResult result){
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			if(error.getCode().equals("typeMismatch")){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"数据类型不匹配!请检查您填写的数据"); 
			}
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPLOYEE_EMPTY, result.getFieldError().getDefaultMessage()); 
		}
		//判断联运模式是哪一种
		if(project.getTransportType()==Constants.TRANSPORTTYPE_TRUCK){//汽运
			if(project.getSendCargoUnitId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"发货单位不能为空!"); 
			}
			if(project.getReceivingDepartmentId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"收货单位不能为空!"); 
			}
			//汽运单价不能为空
			if(project.getTruckPrice()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"汽运单价不能为空!"); 
			}else{
				project.setTransportPrice(project.getTruckPrice());
			}
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_RECEIVE){//接取
			LogisticsResult validData = validData(project, Constants.TRANSPORTTYPE_RECEIVE);
			if(validData.getStatus()!=200){
				return validData;
			}
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_DELIVERY){//送达
			LogisticsResult validData = validData(project, Constants.TRANSPORTTYPE_DELIVERY);
			if(validData.getStatus()!=200){
				return validData;
			}
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_TRAIN){//火运
			LogisticsResult validData = validData(project, Constants.TRANSPORTTYPE_TRAIN);
			if(validData.getStatus()!=200){
				return validData;
			}
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_RECEIVE_TRAIN){//接取+火运
			LogisticsResult validData = validData(project, Constants.TRANSPORTTYPE_RECEIVE);
			if(validData.getStatus()!=200){
				return validData;
			}
			validData = validData(project, Constants.TRANSPORTTYPE_TRAIN);
			if(validData.getStatus()!=200){
				return validData;
			}
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_TRAIN_DELIVERY){//火运+送达
			LogisticsResult validData = validData(project, Constants.TRANSPORTTYPE_TRAIN);
			if(validData.getStatus()!=200){
				return validData;
			}
			validData = validData(project, Constants.TRANSPORTTYPE_DELIVERY);
			if(validData.getStatus()!=200){
				return validData;
			}
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_UNION){//联运
			LogisticsResult validData = validData(project, Constants.TRANSPORTTYPE_RECEIVE);
			if(validData.getStatus()!=200){
				return validData;
			}
			validData = validData(project, Constants.TRANSPORTTYPE_TRAIN);
			if(validData.getStatus()!=200){
				return validData;
			}
			validData = validData(project, Constants.TRANSPORTTYPE_DELIVERY);
			if(validData.getStatus()!=200){
				return validData;
			}
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY){//接取+送达
			LogisticsResult validData = validData(project, Constants.TRANSPORTTYPE_RECEIVE);
			if(validData.getStatus()!=200){
				return validData;
			}
			validData = validData(project, Constants.TRANSPORTTYPE_DELIVERY);
			if(validData.getStatus()!=200){
				return validData;
			}
		}
		//project.setProjectCode(PROJECT_CODE+DateUtils.getCurrentTime());//项目编号
		project.setProjectCode(codeService.createProjectCode(AppSession.getCurrentSysOrgCode()));//项目编号
		project.setCreatorId(AppSession.getCurrentUserId()); //创建人id
		return  projectManagmentService.save(project,AppSession.getCurrentUser());
	}
	
	
	@RequestMapping(value="/updateProject", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult updateProject(String projectJson,HttpSession session){
		System.out.println(projectJson);
		ProjectDetail project = JsonUtils.jsonToPojo(projectJson, ProjectDetail.class);
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		//判断联运模式是哪一种
		LogisticsResult logisticsResult = new LogisticsResult(404,"您要修改的项目信息不存在,请与管理员联系", null);
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_TRUCK){//汽运
			logisticsResult = projectManagmentService.update(project,user);
			return logisticsResult;
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_RECEIVE){//接取
			logisticsResult = projectManagmentService.update(project,user);
			return logisticsResult;
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_DELIVERY){//送达
			logisticsResult = projectManagmentService.update(project,user);
			return logisticsResult;
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_TRAIN){//火运
			logisticsResult = projectManagmentService.update(project,user);
			return logisticsResult;
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_RECEIVE_TRAIN){//接取+火运
			logisticsResult = projectManagmentService.update(project,user);
			return logisticsResult;
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_TRAIN_DELIVERY){//火运+送达
			logisticsResult = projectManagmentService.update(project,user);
			return logisticsResult;
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_UNION){//联运
			logisticsResult = projectManagmentService.update(project,user);
			return logisticsResult;
		}
		if(project.getTransportType() ==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY){//接取+送达
			logisticsResult = projectManagmentService.update(project,user);
			return logisticsResult;
		}
		
		return logisticsResult;
	}
	
	/**
	 * @description 查看项目详情
	 * @date 2017年12月20日
	 * @author shilvfei
	 * @param project
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getProject", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getProject(Integer id){
		LogisticsResult logisticsResult = projectManagmentService.selectProject(id);
		return logisticsResult;
	}
	
	/**
	 * @description 逻辑删除项目
	 * @date 2017年12月20日
	 * @author shilvfei
	 * @param project
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/delProject", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult delProject(Integer id,String reason,HttpSession session){
		if(id==null){
			return LogisticsResult.build(ResultCodeUtils.DEL_PROJECT_EMPTY, ResultContentUtils.DEL_PROJECT_EMPTY);
		}
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		LogisticsResult logisticsResult = projectManagmentService.delProject(id,user,reason);
		return logisticsResult;
	}
	
	/**
	 * @description 完成项目
	 * @date 2017年12月20日
	 * @author shilvfei
	 * @param project
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/completeProject", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult completeProject(Integer id,HttpSession session){
		if(id==null){
			return LogisticsResult.build(ResultCodeUtils.FINISH_PROJECT_EMPTY, ResultContentUtils.FINISH_PROJECT_EMPTY);
		}
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		LogisticsResult logisticsResult = projectManagmentService.completeProject(id,user);
		return logisticsResult;
	}
	
	/**
	 * @description 还原项目
	 * @date 2017年12月20日
	 * @author shilvfei
	 * @param project
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/restoreProject", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult restoreProject(Integer id,HttpSession session){
		if(id==null){
			return LogisticsResult.build(ResultCodeUtils.REST_PROJECT_EMPTY, ResultContentUtils.REST_PROJECT_EMPTY);
		}
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		LogisticsResult logisticsResult = projectManagmentService.restoreProject(id,user);
		return logisticsResult;
	}
	
	/**
	 * @description 分页查询项目
	 * @date 2017年12月20日
	 * @author shilvfei
	 * @param project
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getProjectPage", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getProjectPage(Integer page,String search){
		ProjectDetail projectDetail = JsonUtils.jsonToPojo(search, ProjectDetail.class);
		List<Byte> statu=new ArrayList<>();
		statu.add(projectDetail.getStatus());//在运行
		if(projectDetail.getStatus()==Constants.PROJECT_STATUS_RUNNING){
			statu.add(Constants.PROJECT_STATUS_UNUSED);//未使用
		}
		DataGridResult projectList = projectManagmentService.getProjectManagment(page,PROJECT_PAGE_LIMIT,statu,AppSession.getCurrentUserId(),projectDetail);
		return LogisticsResult.ok(projectList);
	}
	
	/**
	 * @description 财务 获取所有项目
	 * @author LiangDeng
	 * @date 2018年1月17日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/getProjectByFinance", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult getProjectByFinance(@RequestParam Map<String, Object> map){
	    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<TbProject> projectList = projectManagmentService.getProjectByFinance(map);
		return LogisticsResult.ok(projectList);
	}
	
	/**
	 * 项目导出 
	 * @param request 页面表单
	 * @param response 输出流
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> map =FormUtil.getParameterMap(request);
		ProjectDetail projectDetail = FormUtil.populate(ProjectDetail.class, map, false);
		List<Byte> statu=new ArrayList<>();
		statu.add(projectDetail.getStatus()==null ? 0 : projectDetail.getStatus());//在运行
		if(projectDetail.getStatus()==Constants.PROJECT_STATUS_RUNNING){
			statu.add(Constants.PROJECT_STATUS_UNUSED);//未使用
		}
		//全部
		DataGridResult result = projectManagmentService.getProjectManagment(PAGE_NUM,PROJECT_PAGE_LIMIT,statu,AppSession.getCurrentUserId(),projectDetail);
		
		List<ProjectDetail> list = result.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {
			for(ProjectDetail project:list) {
				datas.add(FormUtil.populate(project));
			}	
		}
		
		//status 1 项目列表 2历史项目 3 项目回收站
		if(projectDetail.getStatus()==1){
			String[] heads = {"项目编号","项目类型","联运模式","分支机构","发货企业","收货企业","货物品名","短驳承运方式","短驳承运方","计价单位","备注信息"};
			String[] headCodes = {"projectCode","projectTypeName","transportTypeName","branchGroupName","sendCargoCompanyName","receiveCargoCompanyName","cargoName","shortBargeCarrierMode","shortBargeCarrierName","valuationUnit","remark"};
			if(ExcelUtil.createExcel(request,response, "项目列表信息", "项目列表信息", heads, headCodes, datas)){
				return new ShResponse(HttpStatus.OK.value(), "","下载成功");
			}
		}else if(projectDetail.getStatus()==2){//historyPage
			String[] heads = {"项目编号","项目类型","联运模式","分支机构","发货企业","收货企业","货物品名","短驳承运方式","短驳承运方","计价单位","备注信息","完成时间"};
			String[] headCodes = {"projectCode","projectTypeName","transportTypeName","branchGroupName","sendCargoCompanyName","receiveCargoCompanyName","cargoName","shortBargeCarrierMode","shortBargeCarrierName","valuationUnit","remark","finishDate"};
			if(ExcelUtil.createExcel(request,response, "项目历史列表信息", "项目历史列表信息", heads, headCodes, datas)){
				return new ShResponse(HttpStatus.OK.value(), "","下载成功");
			}
		}else{//回收站 recyclePage
			String[] heads = {"项目编号","项目类型","联运模式","分支机构","发货企业","收货企业","货物品名","短驳承运方式","短驳承运方","计价单位","备注信息","删除人","删除时间","删除原因"};
			String[] headCodes = {"projectCode","projectTypeName","transportTypeName","branchGroupName","sendCargoCompanyName","receiveCargoCompanyName","cargoName","shortBargeCarrierMode","shortBargeCarrierName","valuationUnit","remark","delUser","delDate","delReason"};
			if(ExcelUtil.createExcel(request,response, "项目回收站", "项目回收站", heads, headCodes, datas)){
				return new ShResponse(HttpStatus.OK.value(), "","下载成功");
			}
		}
		return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
	}
	

	
	/**
	 * @description 校验project是否为空
	 * @date 2018年3月10日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	public LogisticsResult validData(ProjectDetail  project,Byte type){
		if(type==1){
			if(project.getSendCargoUnitId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"发货单位不能为空!"); 
			}
			if(project.getReceiveCenterCargoSiteId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"到达中心站不能为空!"); 
			}
			if(project.getReceiveCargoSiteId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"收货站点不能为空!"); 
			}
			if(project.getReceiveCargoSiteFreightYardId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"卸货货场不能为空!"); 
			}
			//接取单价不能为空
			if(project.getPickUpPrice()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"接取单价不能为空!"); 
			}
		}
		if(type==2){
			
			if(project.getForwardingCenterSiteId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"发货中心站不能为空!"); 
			}
			
			if(project.getForwardingSiteId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"接取站点不能为空!"); 
			}
			
			if(project.getForwardingSiteFreightYardId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"取货货场不能为空!"); 
			}
			if(project.getReceivingDepartmentId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"收货单位不能为空!"); 
			}
			//送达单价不能为空
			if(project.getArrivePrice()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"送达单价不能为空!"); 
			}
		}
		if(type==3){
			
			if(project.getBeginCenterSiteId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"起运中心站不能为空!"); 
			}
			if(project.getBeginSiteId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"始发站点不能为空!"); 
			}
			if(project.getEndCenterSiteId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"到达中心站不能为空!"); 
			}
			if(project.getEndSiteId()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"到达站点不能为空!"); 
			}
			if(project.getFreight()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"运费核计不能为空!"); 
			}
			if(project.getMaterialCost()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"加固材料费不能为空!"); 
			}
			if(project.getTarpaulinCost()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"篷布使用费不能为空!"); 
			}
			if(project.getBeginStevedoringCost()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"发站装卸费不能为空!"); 
			}
			if(project.getEndStevedoringCost()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"到站装卸费不能为空!"); 
			}
			if(project.getFreightSum()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"运费合计不能为空!"); 
			}
			//火运单价不能为空
			if(project.getTrainPrice()==null){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"火运单价不能为空!"); 
			}
		}
		return LogisticsResult.ok();
	}

}
