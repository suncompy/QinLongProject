// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.web.transportManager;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenhesoft.logistics.business.caretam.CarTeamService;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.interfaces.TransportManagerService;
import com.shenhesoft.logistics.manage.pojo.carTeam.TbCarTeam;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.search.VehicleSearch;

/**
 * @description 运输管理controller
 *
 * @author LiangLin
 *
 * @date 2017年12月5日
 */
@Controller
@RequestMapping("/transport")
public class TransportController {

	@Autowired
	private TransportManagerService transService;

	@Autowired
	private CarTeamService  carTeamService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;

	/**
	 * 
	 * @description 获取挂靠列表
	 * @author liangLin
	 * @date 2017年12月5日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/anchord/an_list", method = RequestMethod.GET)
	public String anlist(Model model) {
		// 从session获取登录人的id
		VehicleSearch vehicleSearch = new VehicleSearch();
		vehicleSearch.setCompanyId(AppSession.getCurrentUser().getCompanyId());
		//User user = new User();
		// 挂靠记录
		DataGridResult result  = transService.selectAnchoredCarTeamByPages(PAGE_NUM,CUSTOMER_PAGE_LIMIT,vehicleSearch);
		//List<CarTeamDetail> carlist = transService.selectAnchoredCarTeam(user.getCompanyId());
		//List<CarTeamDetail> driverlist = transService.selectAnchoredDrivers(user.getCompanyId());
		//result.getRows().addAll(result2.getRows());
		model.addAttribute("anList", result);
		
		// 申请列表
		DataGridResult result2 =  transService.selectAnchoredDriversByPages(PAGE_NUM,CUSTOMER_PAGE_LIMIT,vehicleSearch);
		model.addAttribute("applyList", result2);
		
		// 驳回列表
		// 车队
		DataGridResult result3 = transService.selectRejectCarTeamRecordByPages(PAGE_NUM,CUSTOMER_PAGE_LIMIT,vehicleSearch);
		//List<CarTeamDetail> carTeamReject = transService.selectRejectCarTeamRecord(/*user.getCompanyId()*/1);
		// 个人
		//DataGridResult result4 = transService.selectRejectDriverRecordByPages(PAGE_NUM,CUSTOMER_PAGE_LIMIT,1);
		//List<CarTeamDetail> driverReject = transService.selectRejectDriverRecord(1);
		//driverReject.addAll(carTeamReject);
		//result3.getRows().addAll(result4.getRows());
		model.addAttribute("rejectList", result3);
		return "/html/manage/transportationManagement/VehicleAuthorization";
	}

	
	/**
	 * 
	 * @description  挂靠列表分页
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	 */
	@RequestMapping("/byPage/anchored/list")
	@ResponseBody
	public LogisticsResult anchoredByPage(Integer page,String search,HttpSession session){
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		VehicleSearch vehicleSearch = JsonUtils.jsonToPojo(search,VehicleSearch.class);
		vehicleSearch.setCompanyId(user.getCompanyId());
		DataGridResult result  = transService.selectAnchoredCarTeamByPages(page,CUSTOMER_PAGE_LIMIT,vehicleSearch);
		//DataGridResult result2 = transService.selectRejectDriverRecordByPages(PAGE_NUM,CUSTOMER_PAGE_LIMIT,user.getCompanyId());
		//return LogisticsResult.ok(result.getRows().addAll(result2.getRows()));
		return LogisticsResult.ok(result);
	}
	
	/**
	 * 
	 * @description  申请列表分页
	 * @author liangDeng
	 * @date 2017年12月18日
	 * @param 
	 * @return
	 */
	@RequestMapping("/byPage/apply/list")
	@ResponseBody
	public LogisticsResult applyByPage(Integer page,String search){
		VehicleSearch vehicleSearch = JsonUtils.jsonToPojo(search,VehicleSearch.class);
		vehicleSearch.setCompanyId(AppSession.getCurrentUser().getCompanyId());
		DataGridResult applyResult  = transService.selectAnchoredDriversByPages(page,CUSTOMER_PAGE_LIMIT,vehicleSearch);
		//DataGridResult result2 = transService.selectRejectDriverRecordByPages(PAGE_NUM,CUSTOMER_PAGE_LIMIT,user.getCompanyId());
		//return LogisticsResult.ok(result.getRows().addAll(result2.getRows()));
		return LogisticsResult.ok(applyResult);
	}
	
	/**
	 * 
	 * @description  驳回页面分页
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	 */
	@RequestMapping("/byPage/reject/list")
	@ResponseBody
	public LogisticsResult rejectByPage(Integer page,String search){
		VehicleSearch vehicleSearch = JsonUtils.jsonToPojo(search,VehicleSearch.class);
		vehicleSearch.setCompanyId(AppSession.getCurrentUser().getCompanyId());
		DataGridResult rejectResult = transService.selectRejectCarTeamRecordByPages(page,CUSTOMER_PAGE_LIMIT,vehicleSearch);
		//DataGridResult result2 =  transService.selectAnchoredDriversByPages(PAGE_NUM,CUSTOMER_PAGE_LIMIT,user.getCompanyId());
		//return LogisticsResult.ok(result.getRows().addAll(result2.getRows()));
		return LogisticsResult.ok(rejectResult);
	}
	
	/**
	 * 
	 * @description 驳回列表
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param
	 * @return
	 *//*
	@RequestMapping(value = "/anchord/reject_list", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult rejectlist(HttpSession session, Model model) {
		// 从session获取登录人的id
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		// 车队
		List<CarTeamDetail> carTeamReject = transService.selectRejectCarTeamRecord(user.getCompanyId());
		// 个人
		List<CarTeamDetail> driverReject = transService.selectRejectDriverRecord(user.getCompanyId());
		model.addAttribute("rejectList", driverReject.addAll(carTeamReject));
		return LogisticsResult.build(1, "成功");
	}*/

	/**
	 * 
	 * @description 取消挂靠
	 * @author liangLin
	 * @date 2017年12月5日
	 * @param 记录表ids
	 *            List集合
	 * @return
	 */
	@RequestMapping(value = "/anchord/an_cancel", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult Ancancel(@RequestParam("ids[]") List<Integer> ids, Model model) {
		// 新增取消挂靠记录
		boolean flag = transService.insertCancelRecord(ids);
		if (flag != true) {
			return LogisticsResult.build(0, "取消操作记录失败");
		}
		// 去除关联关系
		flag = transService.updateAnchordRelate(ids);
		if (flag != true) {
			return LogisticsResult.build(0, "操作失败");
		}
		return LogisticsResult.build(1, "取消挂靠成功");
	}


	/**
	 * 
	 * @description 同意挂靠
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/anchord/agree", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult agree(@RequestParam("ids[]") List<Integer> ids, HttpSession session) {

		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		// 插入挂靠记录
		boolean flag = transService.insertAgreeRecord(ids, user.getId());
		if (flag != true) {
			return LogisticsResult.build(0, "操作失败");
		}
		// 建立挂靠关系
		flag = transService.insertAnchoredRelate(ids);
		if (flag != true) {
			return LogisticsResult.build(0, "操作失败");
		}

		// 插入司机的关联消息
		flag = transService.insertNotice(ids, user.getCompanyId(), (byte) 1);
		if (flag != true) {
			return LogisticsResult.build(0, "插入消息失败");
		}

		return LogisticsResult.build(1, "同意挂靠成功");
	}

	/**
	 * 
	 * @description 驳回
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/anchord/reject", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult reject(@RequestParam("ids[]") List<Integer> ids, HttpSession session) {

		// 获取当前登录用户
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		// 插入驳回记录
		boolean flag = false;
		flag = transService.insertRejectRecord(ids, user.getId());
		if (flag != true) {
			return LogisticsResult.build(0, "操作记录失败");
		}

		// 插入司机的关联消息
		flag = transService.insertNotice(ids, user.getCompanyId(), (byte) 2);
		if (flag != true) {
			return LogisticsResult.build(0, "插入消息失败");
		}

		return LogisticsResult.build(1, "驳回成功");
	}
	
	/**
	 * 
	 * @description 删除 
	 * @author liangLin
	 * @date 2017年12月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/rejectRecord/delete", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult delete(@RequestParam("ids[]") List<Integer> idList,Model model) {
		
		boolean flag = transService.deleteRejectRecord(idList);
		if(flag != true){
			return LogisticsResult.build(0, "删除失败"); 
		}
		return LogisticsResult.build(1, "删除成功"); 
	}

	/**
	 * 
	 * @description 获取公司下挂靠的所有车队
	 * @author liangLin
	 * @date 2017年12月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/listCarTeams", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult listCarTeams() {
		//获取短驳承运方
		Integer companyId = AppSession.getCurrentUser().getCompanyId();
		List<TbCarTeam> carTeams = carTeamService.selectAnchoredCarTeam(companyId);
		return LogisticsResult.ok(carTeams);
	}
	
	/**
	 * 
	 * @description 开始
	 * @author liangdeng
	 * @date 2017年1月23日
	 * @param 记录表ids
	 *            List集合
	 * @return
	 */
	@RequestMapping(value = "/anchord/start", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult start(@RequestParam("ids[]") List<Integer> ids) {
		// 将关联关系变成START
		boolean flag = transService.updateAnchordRelateStrat(ids);
		if (flag != true) {
			return LogisticsResult.build(0, "操作失败");
		}
		return LogisticsResult.build(1, "开始成功");
	}
	
	/**
	 * 
	 * @description 暂停功能
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/anchord/stop", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult Ancancel(@RequestParam("ids[]") List<Integer> ids) {

		// 将关联关系变成STOP
		boolean flag = transService.updateAnchordRelateStop(ids);
		if (flag != true) {
			return LogisticsResult.build(0, "操作失败");
		}
		return LogisticsResult.build(1, "暂停成功");
	}
}
