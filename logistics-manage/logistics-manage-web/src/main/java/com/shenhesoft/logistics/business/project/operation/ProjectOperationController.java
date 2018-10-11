package com.shenhesoft.logistics.business.project.operation;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.business.helpPojo.ProjectOperationDetail;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.ResultCodeUtils;
import com.shenhesoft.logistics.common.util.ResultContentUtils;
import com.shenhesoft.logistics.common.util.office.ExcelUtil;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.cargo.interfaces.CargomanagementService;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.helpPojo.TbCargoMainPoint;
import com.shenhesoft.logistics.manage.interfaces.TrainStationService;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargo;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;

/**
 * @description:项目运营管理
 * 
 * @author shilvfei
 * 
 * @date 2017年12月25日
 */
@Controller
@RequestMapping("/project")
public class ProjectOperationController {
	@Autowired
	private ProjectOperationService operationService;
	
	@Autowired
	private TrainStationService trainStationService;
	
	@Autowired
	private BranchGroupService branchGroupService;
	
	@Autowired
	private CargomanagementService cargomanagementService;
	
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
	@RequestMapping("/projectOperation")
	public String getProjectManagment(Model model){
		//获取所有分支机构
		Map<String, Object> form = new HashMap<>();
		form.put("status", Constants.DOT_BRANCH_STATUS_YES);
		List<DotBranchDetail> branchGroups = branchGroupService.getDotBranchs(form);	
		model.addAttribute("branchGroups", branchGroups);
		//获取所有货物品名
		List<TbCargoMainPoint> cargos = cargomanagementService.listCargo();
		model.addAttribute("cargos", cargos);
		//获取所有的火车最明细站点
		List<TbTrainStation> threeLevelStations = trainStationService.selectThreeTrainStationByLevel(Constants.SITE_LEVEL_THREE);
		//获取所有的火车二级站点
		List<TbTrainStation> twoLevelStations = trainStationService.selectThreeTrainStationByLevel(Constants.SITE_LEVEL_TWO);
		model.addAttribute("twoLevelStations", twoLevelStations);
		model.addAttribute("threeLevelStations", threeLevelStations);
		DataGridResult projectOperations = operationService.listProjectOperationByPage(PAGE_NUM,PROJECT_PAGE_LIMIT,AppSession.getCurrentUserId(),new TbProject());
		model.addAttribute("projectOperations", projectOperations);
		//分发转向
		return "/html/business/project/projectOperation";
	}
	
	/**
	 * @description 获取货场货位图
	 * @date 2017年12月26日
	 * @author shilvfei
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/getCargoLocation")
	@ResponseBody
	public LogisticsResult getCargoLocation(HttpSession session, Integer id){
		if(id==null){
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,ResultContentUtils.INSERT_EMPTY);
		}
		LogisticsResult logisticsResult = operationService.getCargoLocationById(id);
		return logisticsResult;
	}
	
	/**
	 * @description 分页获取项目运营管理项目列表
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/getProjectOperationByPage")
	@ResponseBody
	public LogisticsResult getProjectOperationByPage(HttpSession session,Integer page,String search){
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		TbProject project = JsonUtils.jsonToPojo(search, TbProject.class);
		DataGridResult projectOperations = operationService.listProjectOperationByPage(page,PROJECT_PAGE_LIMIT,user.getId(),project);
		return LogisticsResult.ok(projectOperations);
	}
	
	
	/**
	 * @description 通过id获取项目运营管理项目详情
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/getProjectOperationByPid")
	@ResponseBody
	public LogisticsResult getProjectOperationByPid(Integer id){
		ProjectOperationDetail operationDetail =  operationService.getProjectOperationByPid(id);
		return LogisticsResult.ok(operationDetail);
	}
	
	/**
	 * 项目导出 
	 * @param request 页面表单
	 * @param response 输出流
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse export(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map =FormUtil.getParameterMap(request);
		TbProject project = FormUtil.populate(TbProject.class, map, false);
		//全部
		DataGridResult projectOperations = operationService.listProjectOperationByPage(PAGE_NUM,PROJECT_PAGE_LIMIT,AppSession.getCurrentUserId(),project);
		
		List<ProjectOperationDetail> list = projectOperations.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {
			for(ProjectOperationDetail projectOperation:list) {
				datas.add(FormUtil.populate(projectOperation));
			}	
		}
		String[] heads = {"项目编号","项目类型","联运模式","分支机构","发货企业","收货企业","货物品名","规格","接取提货吨位","接取提货车次","接取到货吨位","接取到货车次","火运发送吨位","火运发送车次","火运到货吨位","火运到货车次","送达提货吨位","送达提货车次","送达到货吨位","送达到货车次","运输完成吨位","在途运输吨位","中转库存吨位"};
		String[] headCodes = {"projectCode","projectType","transportType","branchGroupName","sendCargoCompanyName","receiveCargoCompanyName","cargoName","cargoSpecifications","receiveGetCargoWeight","receiveGetCargoCarNum","receiveArriveCargoWeight","receiveArriveCargoCarNum","trainSendCargoWeight","trainSendCargoTrainNum","trainArriveCargoWeight","trainArriveCargoTrainNum","sendGetCargoWeight","sendGetCargoCarNum","sendArriveCargoWeight","sendArriveCargoNum","finishCargoWeight","runningCargoWeight","transitCargoWeight"};
		if(ExcelUtil.createExcel(request,response, "项目运营列表信息", "项目运营列表信息", heads, headCodes, datas)){
			return new ShResponse(HttpStatus.OK.value(), "","下载成功");
		}
		return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
	}
}
