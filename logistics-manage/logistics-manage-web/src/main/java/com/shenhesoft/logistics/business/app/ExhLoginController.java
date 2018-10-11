package com.shenhesoft.logistics.business.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.ResultCodeUtils;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.employee.EmployeeInformationService;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description 后台分配登录界面
 * 
 * @author shilvfei
 * 
 * @date 2018年3月20日
 */
@Controller
@RequestMapping("/exhibition")
public class ExhLoginController {

	@Autowired
	private EmployeeInformationService employeeInformationService;
	
	@Autowired
	private BranchGroupService branchGroupService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${TOP_DOT_BRANCH_PAGE_LIMIT}")
	private Integer DOT_BRANCH_PAGE_LIMIT;
	
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult doLogin(String username, String password){
		if(StringUtils.isBlank(username)){
			return new LogisticsResult(404, "用户名和密码有误!",null);
		}
		if(StringUtils.isBlank(password)){
			return new LogisticsResult(404, "用户名和密码有误!",null);
		}
		TbSystemUser systemUser =  employeeInformationService.getUserByUserNamePassword(username, password);
		if(systemUser==null){
			return new LogisticsResult(404, "用户不存在!",null);
		}
		
		if(systemUser.getId()!=1){
			return LogisticsResult.build(404, "此账户不是超级管理员!");
		}
		AppSession.setAttribute("systemUser", systemUser);
		return new LogisticsResult(systemUser);
	}
	
	/**
	 * @description 新增顶级机构
	 * @date 2017年12月21日
	 * @author shilvfei
	 * @param project
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/insertTopDotBranch", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult insertTopDotBranch(@Valid DotBranchDetail branchGroup,BindingResult result){
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			if(error.getCode().equals("typeMismatch")){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"数据类型不匹配!请检查您填写的数据"); 
			}
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPLOYEE_EMPTY, result.getFieldError().getDefaultMessage()); 
		}
		LogisticsResult logisticsResult =  branchGroupService.insertTopDotBranch(branchGroup);
		return logisticsResult;
	}
	
	@RequestMapping(value="/branchGroupManage",method=RequestMethod.GET)
	public String branchGroupManage(Model model){
		if(AppSession.getCurrentUserId()!=1){
			 return"/exhibition/exhLogin";
		}
		Map<String, Object> form= new HashMap<>();
		form.put("userId", AppSession.getCurrentUserId());
		List<Byte> levels = new ArrayList<>();
		levels.add(Constants.BRANCH_LEVEL_ONE);
		form.put("levels", levels);
		DataGridResult dataGridResult = branchGroupService.getDotBranchs(PAGE_NUM,DOT_BRANCH_PAGE_LIMIT,form);
		model.addAttribute("branchGroupDataGridResult", dataGridResult);
		return"/exhibition/branchGroupManage";
	}
	
	@RequestMapping(value="/branchGroupManageByPage",method=RequestMethod.GET)
	@ResponseBody
	public LogisticsResult branchGroupManageByPage(Integer page){
		if(AppSession.getCurrentUserId()!=1){
			return LogisticsResult.build(404);
		}
		Map<String, Object> form= new HashMap<>();
		form.put("userId", AppSession.getCurrentUserId());
		List<Byte> levels = new ArrayList<>();
		levels.add(Constants.BRANCH_LEVEL_ONE);
		form.put("levels", levels);
		return LogisticsResult.ok(branchGroupService.getDotBranchs(page,DOT_BRANCH_PAGE_LIMIT,form));
	}
	
	@RequestMapping(value="/branchGroupBeginOrStop",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult branchGroupBeginOrStop(Byte status,Integer branchId){
		if(AppSession.getCurrentUserId()!=1){
			return LogisticsResult.build(404);
		}
		Map<String, Object> form= new HashMap<>();
		form.put("status", status);
		LogisticsResult logisticsResult = branchGroupService.getDotBranchById(branchId);
		TbBranchGroup branchGroup = (TbBranchGroup)logisticsResult.getData();
		form.put("sysOrgCode", branchGroup.getCode());
		form.put("branchId", branchId);
		boolean flag = branchGroupService.updateBranchGroupBeginOrStop(form);
		if(flag){
			return LogisticsResult.ok();
		}
		return LogisticsResult.build(500);
	}
	
	@RequestMapping(value="/restPassWd",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult restPassWd(Integer branchId){
		if(AppSession.getCurrentUserId()!=1){
			return LogisticsResult.build(404);
		}
		Map<String, Object> form= new HashMap<>();
		form.put("branchId",branchId);
		form.put("passwd",DigestUtils.md5DigestAsHex(Constants.RESET_PASSWD.getBytes()));
		return  branchGroupService.restPassWd(form);
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(){
		AppSession.logoutCurrentUser();
		return"/exhibition/exhLogin";
	}
}
