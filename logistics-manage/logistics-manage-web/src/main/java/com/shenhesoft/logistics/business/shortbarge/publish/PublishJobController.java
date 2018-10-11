// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.shortbarge.publish;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
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

import com.shenhesoft.logistics.business.helpPojo.DistributeDetail;
import com.shenhesoft.logistics.business.helpPojo.ProjectDistributionDetail;
import com.shenhesoft.logistics.business.helpPojo.ProjectOperationDetail;
import com.shenhesoft.logistics.business.helpPojo.TbProjectDetail;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.cargo.interfaces.CargomanagementService;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargo;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description 短驳管理 -任务发布
 *
 * @author LiangLin
 *
 * @date 2017年12月18日
 */
@Controller
@RequestMapping("/business/short/job")
public class PublishJobController {
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;
	
	@Autowired
	private PublishJobService publishJobService;
	
	/**
	 * 
	 * @description 页面 
	 * @author liangLin
	 * @date 2017年12月19日
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpSession session,Model model) throws ClassNotFoundException, IOException {
		DataGridResult result = publishJobService.listPublishJobByPage(PAGE_NUM,CUSTOMER_PAGE_LIMIT,null);
		model.addAttribute("projectList", result);
		return "/html/business/shortBarge/distribution";
	}
	
	
	/**
	 * 
	 * @description 分页 
	 * @author liangLin
	 * @date 2017年12月19日
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@RequestMapping(value = "/byPage/list")
	@ResponseBody
	public LogisticsResult bypageList(Integer page,String search) throws IOException, ClassNotFoundException {
		ProjectDistributionDetail distributionDetail = JsonUtils.jsonToPojo(search, ProjectDistributionDetail.class);
		DataGridResult result = publishJobService.listPublishJobByPage(page,CUSTOMER_PAGE_LIMIT,distributionDetail);
		return LogisticsResult.ok(result);
	}
	
	/**
	 * 
	 * @description 暂停 
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/distribute/stop", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult stop(@RequestParam("idList[]") List<Integer> idList,@RequestParam("typeList[]") List<Integer> typeList) {
		boolean flag = publishJobService.stopJob(idList,typeList);
		if(flag != true){
			return LogisticsResult.build(0, "暂停失败"); 
		}
		return LogisticsResult.build(1, "暂停成功"); 
	}
	
	
	
	/**
	 * 
	 * @description 开始  -开始只能针对已经暂停的
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/distribute/begin", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult begin(@RequestParam("idList[]") List<Integer> idList,@RequestParam("typeList[]") List<Integer> typeList,Model model) {
		
		boolean flag = publishJobService.beginJob(idList,typeList);
		if(flag != true){
			return LogisticsResult.build(0, "开始失败"); 
		}
		return LogisticsResult.build(1, "开始成功"); 
	}
	
	/**
	 * @RequestParam Integer id,@RequestParam Integer num,@RequestParam byte type,
	 * @description 分配 
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 项目id  ,分配数量 
	 * @return 
	 */
	@RequestMapping(value = "/distribute/put", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult put(@Valid DistributeDetail distributeDetail,BindingResult result,  HttpSession session) {
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把必填信息填上"); 
		}
		
		TbSystemUser user =  (TbSystemUser) session.getAttribute("systemUser");
		//判断  -目前分配任务 不得低于 今日已领取任务 
		/*boolean falgs = publishJobService.IsHigherByTodayNum(distributeDetail.getProjectId(),distributeDetail.getNum(),distributeDetail.getProjectType());
		if(falgs != true){
			return LogisticsResult.build(0, "分配失败,当前分配的车辆数 不得低于已领取任务车辆数"); 
		}*/
		boolean flag = publishJobService.putDistributeJob(distributeDetail.getProjectId(),distributeDetail.getNum(),user.getId(),distributeDetail.getProjectType());
		if(flag != true){
			return LogisticsResult.build(0, "分配失败"); 
		}
		return LogisticsResult.build(1, "分配成功"); 
	}
	
	
	/**
	 * 
	 * @description 分配点击后的  页面字段信息获取 
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param  项目id
	 * @return
	 */
	@RequestMapping(value = "/distribute/getMsg", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult put(@RequestParam Integer id,Model model,HttpSession session) {
		TbProject project= publishJobService.getMsgByProjectId(id);
		return LogisticsResult.ok(project); 
	}
	
}
