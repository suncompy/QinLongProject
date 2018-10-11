package com.shenhesoft.logistics.manage.web.employeeInformation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.CheckPhone;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.ResultCodeUtils;
import com.shenhesoft.logistics.common.util.ResultContentUtils;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.employee.EmployeeInformationService;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.helpPojo.EmployInfo;
import com.shenhesoft.logistics.manage.pojo.role.TbRole;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.role.RoleService;
import com.shenhesoft.logistics.manage.search.TbSystemSearch;

/**
 * @description:员工信息管理
 * 
 * @author shilvfei
 * 
 * @date 2017年12月12日
 */
@Controller
@RequestMapping("/humanOrganization")
public class EmployeeInformationController {

	@Autowired
	private BranchGroupService  branchGroupService ;
	
	@Autowired
	private RoleService  roleService;
	
	@Autowired
	private EmployeeInformationService employeeInformationService ;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${EMPLOYEE_PAGE_LIMIT}")
	private Integer EMPLOYEE_PAGE_LIMIT;
	
	/**
	 * @description 获取员工信息
	 * @date 2017年12月11日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping("/employeeInformation")
	public String selectEmployeeInformation(Model model){
		//获取所有正常使用的分支机构
		Map<String, Object> form = new HashMap<>();
		//form.put("userId", AppSession.getCurrentUserId());
		form.put("status", Constants.DOT_BRANCH_STATUS_YES);
		form.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<DotBranchDetail> branchGroups = branchGroupService.getDotBranchs(form);
		model.addAttribute("branchGroups", branchGroups);
		
		//获取所有未删除的角色
		form.clear();
		form.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<TbRole> roles = roleService.getRoles(form);
		model.addAttribute("roles", roles);
		return "/html/manage/humanOrganization/employeeInformation";
	}
	
	/**
	 * @description 分页处理
	 * @date 2017年12月16日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping("/listEmployByPage")
	@ResponseBody
	public LogisticsResult listEmployByPage(Integer page,String search){
		//获取在职的所有员工
		TbSystemSearch systemSearch =  JsonUtils.jsonToPojo(search, TbSystemSearch.class);
		DataGridResult employeeResult= employeeInformationService.listEmployByPage(page,EMPLOYEE_PAGE_LIMIT,systemSearch);
		if(employeeResult==null){
			LogisticsResult.build(404,"查询失败！");
		}
		return LogisticsResult.ok(employeeResult);
	}
	
	/**
	 * @description 添加员工信息
	 * @date 2017年12月11日
	 * @author shilvfei
	 * @param employ
	 * @param roleId
	 * @param dataResult
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addEmployee",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addEmployee(@Valid EmployInfo employ,BindingResult result,Integer roleId){
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			if(error.getCode().equals("typeMismatch")){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"数据类型不匹配!请检查您填写的数据"); 
			}
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPLOYEE_EMPTY, result.getFieldError().getDefaultMessage()); 
		}
		//校验手机号格式
		boolean flag = CheckPhone.isPhone(employ.getPhone());
		if(!flag){
			return LogisticsResult.build(ResultCodeUtils.SEND_MSG_PHONE_RULE_FAULT,ResultContentUtils.SEND_MSG_PHONE_RULE_FAULT);
		}
		//校验员工状态
		if(employ.getWorkStatus()==null){
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPLOYEE_EMPTY,"请选择员工状态!");
		}
		
		//获取当前登录用户的公司信息
		TbSystemUser user = AppSession.getCurrentUser();
		if(user==null){
			return LogisticsResult.build(ResultCodeUtils.MEMBER_ISNOT_EXIST,ResultContentUtils.MEMBER_ISNOT_EXIST);
		}
		//赋值
		employ.setCompanyId(user.getCompanyId());
		// 密码加密
		employ.setAccount(employ.getPhone());
		employ.setPasswd(DigestUtils.md5DigestAsHex(employ.getPhone().getBytes()));
		// 添加员工信息
		LogisticsResult addResult = employeeInformationService.addEmployee(employ,roleId);
		return addResult;
	}
	
	/**
	 * @description 禁用员工
	 * @date 2017年12月11日
	 * @author shilvfei
	 * @param employ
	 * @param roleId
	 * @param dataResult
	 * @param session
	 * @return
	 */
	@RequestMapping("/delEmployee")
	@ResponseBody
	public LogisticsResult delEmployee(@RequestParam("ids[]")List<Integer> ids){
		if(ids==null || ids.size()==0){
			return LogisticsResult.build(ResultCodeUtils.DEL_EMPLOYEE_EMPTY, ResultContentUtils.DEL_EMPLOYEE_EMPTY);
		}
		//赋值
		LogisticsResult result = employeeInformationService.delEmployee(ids);
		return result;
	}
	
	/**
	 * @description 更新员工信息
	 * @date 2017年12月11日
	 * @author shilvfei
	 * @param employ
	 * @param roleId
	 * @param dataResult
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateEmployee")
	@ResponseBody
	public LogisticsResult updateEmployee(@Valid EmployInfo employ,BindingResult dataResult,Integer roleId){
		if (dataResult.hasErrors()) {
			FieldError error = dataResult.getFieldError();
			if(error.getCode().equals("typeMismatch")){
				System.out.println(error);
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"数据类型不匹配!请检查您填写的数据"); 
			}
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,  error.getDefaultMessage()); 
		}
		//身份证号强校验
		/*if(employ.getPhone()==null || !IDCardCheckUtil.strongVerifyIdNumber(employ.getIdcard())){
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"身份证号格式有误!"); 
		}*/
		LogisticsResult result = employeeInformationService.updateEmployee(employ,roleId);
		return result;
	}
	
	
	/**
	 * @description 获取员工信息
	 * @date 2018年1月4日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/getEmployee",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getEmployeeById(@RequestParam("ids[]")List<Integer> ids){
		if(ids==null || ids.size()==0){
			return LogisticsResult.build(ResultCodeUtils.ACCOUNT_INSERT_FAILED, ResultContentUtils.ACCOUNT_INSERT_FAILED);
		}
		return employeeInformationService.getEmployeeById(ids.get(0));
	}
	
	/**
	 * @description 校验用户名和手机是否已存在
	 * @date 2017年11月24日
	 * @author shilvfei
	 * @param param
	 * @param type
	 * @return
	 */
	//校验账号是否存在
	@RequestMapping(value="/check",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult checkUserInfo(String param,Integer type){
		if(StringUtils.isBlank(param) || type==null){
			return LogisticsResult.build(400);
		}
		return employeeInformationService.checkUserInfo(param, type);
	}
}
