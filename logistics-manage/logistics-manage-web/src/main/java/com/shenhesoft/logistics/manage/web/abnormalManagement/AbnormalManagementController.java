// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.web.abnormalManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.abnormalManagement.AbnormalManagementService;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.helpPojo.SystemSceneDetail;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.systemScene.TbSystemScene;

/**
 * @description 异常管理:系统情景设定
 *
 * @author LiangDeng
 *
 * @date 2017年12月15日
 */
@Controller
@RequestMapping("/abnormal")
public class AbnormalManagementController {
	
	@Autowired
	private AbnormalManagementService abnormalManagementService;
	
	@Autowired
	private BranchGroupService branchGroupService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;
	
	/**
	 * @description 系统情景列表
	 * @author LiangDeng
	 * @date 2017年12月15日
	 * @param 
	 * @return
	 */
	@RequestMapping("/abnormalList")
	public String abnormalList(Model model){
		
		TbSystemScene search = new TbSystemScene();
		DataGridResult result = abnormalManagementService.selectAbnormalList(PAGE_NUM,CUSTOMER_PAGE_LIMIT,search);
		model.addAttribute("abnormalList", result);
		/*List<TbBranchGroup> branchGroups = branchGroupService.selectBranchGroup(Constants.BRANCH_GROUP_STATUS_YES);
		model.addAttribute("branchGroups", branchGroups);*/
		//获取所有分支机构
		//List<DotBranchDetail> branchGroupList = branchGroupService.getDotBranchs(new HashMap<>());
		List<ProjectDetail> projectList = abnormalManagementService.queryAllProjectOfSence();
		model.addAttribute("projectList", projectList);
		return "/html/manage/abnormalManagement/artificialScenario";
	}
	
	//分页查询
	@RequestMapping("/selectAbnormal")
	@ResponseBody
	public LogisticsResult selectAbnormal(Integer page,String search){
		TbSystemScene sceneSearch = JsonUtils.jsonToPojo(search,TbSystemScene.class);
		//获取客户信息
		DataGridResult result = abnormalManagementService.selectAbnormalList(page,CUSTOMER_PAGE_LIMIT,sceneSearch);
		return LogisticsResult.ok(result);
	}
		
	/**
	 * @description 查询所有管理项目部
	 * @author LiangDeng
	 * @date 2017年12月15日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/ProjectDepartment", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult queryProjectDepartment(){
		Map<String, Object> form= new HashMap<>();
		form.put("status", Constants.DOT_BRANCH_STATUS_YES);
		List<DotBranchDetail> branchGroups = branchGroupService.getDotBranchs(form);
		return LogisticsResult.ok(branchGroups);
	}
	
	/**
	 * @description 当前登录人所关联项目
	 * @author LiangDeng
	 * @date 2018年1月17日
	 * @param 
	 * @return
	 */
	/*@RequestMapping(value = "/getAllProjectBylogin", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult project_getAll(HttpSession session) {
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		//获取所有分支机构
		List<TbBranchGroup> branchGroupList = branchGroupService.selectBranchGroupByUid(user.getId());
		List<TbProject> projectList = abnormalManagementService.queryAllProjectOfSence(branchGroupList);
		return LogisticsResult.ok(projectList);
	}*/
	
	/**
	 * @description 新增系统情景
	 * @author LiangDeng
	 * @date 2017年12月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addAbnormal", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addAbnormal(@Valid SystemSceneDetail systemSceneInfo,BindingResult result){
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把信息补全"); 
		}
		//解析关联项目部
		List<String> projectCodes = JsonUtils.jsonToList(systemSceneInfo.getProjectCode(), String.class);
		int ap = 0;
		for (String projectCode : projectCodes) {
			TbSystemScene systemScence = new TbSystemScene();
			systemScence.setSceneName(systemSceneInfo.getSceneName());
			systemScence.setBranchGroupId(projectCode);
			systemScence.setReasonType(systemSceneInfo.getReasonType());
			systemScence.setReasonScale(systemSceneInfo.getReasonScale());
			systemScence.setRemark(systemSceneInfo.getRemark());
			systemScence.setStatus((byte)0);
			ap = ap + abnormalManagementService.addAbnormal(systemScence);
		}
		if(ap != projectCodes.size()){
			return LogisticsResult.build(400, "新增失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 删除系统情景
	 * @author LiangDeng
	 * @date 2017年12月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/deleteAbnormal", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult deleteAbnormal(@RequestParam Integer id){
		int dr = abnormalManagementService.deleteAbnormal(id);
		if(dr != 1){
			return LogisticsResult.build(400, "删除失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 获得某条情景详情
	 * @author LiangDeng
	 * @date 2017年12月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getOneAbnormal", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getOneAbnormal(@RequestParam Integer id){
		SystemSceneDetail abnormalDetailsDetail = abnormalManagementService.selectAbnormalDeatilById(id);
		return LogisticsResult.ok(abnormalDetailsDetail);
	}
	
	/**
	 * @description 修改系统情景
	 * @author LiangDeng
	 * @date 2017年12月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updAbnormal", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult updAbnormal(@Valid SystemSceneDetail systemSceneInfo,BindingResult result){
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把信息补全"); 
		}
		//解析关联项目部
		List<String> projectCodes = JsonUtils.jsonToList(systemSceneInfo.getProjectCode(), String.class);
		int up = 0;
		for (String projectCode : projectCodes) {
			TbSystemScene systemScence = new TbSystemScene();
			systemScence.setId(systemSceneInfo.getId());
			systemScence.setSceneName(systemSceneInfo.getSceneName());
			systemScence.setBranchGroupId(projectCode);
			systemScence.setReasonType(systemSceneInfo.getReasonType());
			systemScence.setReasonScale(systemSceneInfo.getReasonScale());
			systemScence.setRemark(systemSceneInfo.getRemark());
			systemScence.setStatus((byte)0);
			up = up + abnormalManagementService.updAbnormal(systemScence);
		}
		if(up != projectCodes.size()){
			return LogisticsResult.build(400, "修改失败");
		}
		return LogisticsResult.ok();
	}
}
