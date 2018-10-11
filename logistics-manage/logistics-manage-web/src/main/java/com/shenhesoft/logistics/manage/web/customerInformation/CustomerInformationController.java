package com.shenhesoft.logistics.manage.web.customerInformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.ResultCodeUtils;
import com.shenhesoft.logistics.common.util.ResultContentUtils;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.customer.CustomerInfoService;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.helpPojo.TbStationBusinessHelp;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.project.ProjectService;
/**
 * @description:客户管理
 * 
 * @author shilvfei
 * 
 * @date 2017年12月12日
 */
@Controller()
@RequestMapping("/customerManagement")
public class CustomerInformationController {

	@Autowired
	private CustomerInfoService customerInfoService;
	
	@Autowired
	private BranchGroupService branchGroupService;
	
	@Autowired
	private ProjectService projectService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;
	
	/**
	 * @description 获取客户信息
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping("/customerInformation")
	public String getCustomerInfo(Model model){
		//获取客户信息
		TbCustomer customer = new TbCustomer();
		customer.setStatus(Constants.CUSTOMER_STATUS_YES);
		DataGridResult result = customerInfoService.listCustomerByPage(PAGE_NUM,CUSTOMER_PAGE_LIMIT,customer);
		model.addAttribute("customerList", result);
		//所有的分支
		//获取所有正常使用的分支机构
		Map<String, Object> form = new HashMap<>();
		//form.put("userId", AppSession.getCurrentUserId());
		form.put("status", Constants.DOT_BRANCH_STATUS_YES);
		form.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<DotBranchDetail> branchGroups = branchGroupService.getDotBranchs(form);
		model.addAttribute("branchGroups", branchGroups);
		//所有的项目
		List<ProjectDetail> projects =  projectService.selectProject();
		model.addAttribute("projects", projects);
		return "/html/manage/customerManagement/customerInformation";
	}
	/**
	 * @description 分页查询客户信息
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param page
	 * @return
	 */
	@RequestMapping("/listCustomerByPage")
	@ResponseBody
	public LogisticsResult listCustomerByPage(Integer page,String search){
		//获取客户信息
		TbCustomer customer = JsonUtils.jsonToPojo(search,TbCustomer.class);
		customer.setStatus(Constants.CUSTOMER_STATUS_YES);//正常使用
		DataGridResult result = customerInfoService.listCustomerByPage(page,CUSTOMER_PAGE_LIMIT,customer);
		return LogisticsResult.ok(result);
	}
	
	/**
	 * @description 添加客户信息
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param customer
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/addCustomer",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addCustomer(@Valid CustomerInfo customer,BindingResult result ){
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			if(error.getCode().equals("typeMismatch")){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"数据类型不匹配!请检查您填写的数据"); 
			}
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPLOYEE_EMPTY, result.getFieldError().getDefaultMessage()); 
		}
		List<Integer> branchIds = JsonUtils.jsonToList(customer.getBrachIds(), Integer.class);
		List<TbStationBusinessHelp> businessHelps = JsonUtils.jsonToList(customer.getBusinessContact(), TbStationBusinessHelp.class);
		for (TbStationBusinessHelp tbStationBusinessHelp : businessHelps) {
			if(StringUtils.isBlank(tbStationBusinessHelp.getName())){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"联系人名称不能为空"); 
			}
			if(StringUtils.isBlank(tbStationBusinessHelp.getPhone())){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"联系人手机号不能为空"); 
			}
		}
		return customerInfoService.insertCustomer(customer,branchIds,businessHelps);
	}
	
	/**
	 * @description 更新客户信息
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param customer
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/updateCustomer",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult updateCustomer(@Valid CustomerInfo customer,BindingResult result ){
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			if(error.getCode().equals("typeMismatch")){
				return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,"数据类型不匹配!请检查您填写的数据"); 
			}
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPLOYEE_EMPTY, result.getFieldError().getDefaultMessage()); 
		}
		List<Integer> branchIds = JsonUtils.jsonToList(customer.getBrachIds(), Integer.class);
		List<TbStationBusinessHelp> businessHelps = JsonUtils.jsonToList(customer.getBusinessContact(), TbStationBusinessHelp.class);
		return customerInfoService.updateCustomer(customer,branchIds,businessHelps);
	}
	
	/**
	 * @description 通过id获取客户信息
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getCustomerByIds",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getCustomerByIds(Integer id){
		if(id==null){
			return LogisticsResult.build(ResultCodeUtils.ACCOUNT_INSERT_FAILED, ResultContentUtils.ACCOUNT_INSERT_FAILED);
		}
		return customerInfoService.getCustomerById(id);
	}
	
	/**
	 * @description 删除客户信息
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delCustomerById",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult delCustomerById(Integer id){
		if(id==null){
			return LogisticsResult.build(ResultCodeUtils.ACCOUNT_INSERT_FAILED, ResultContentUtils.ACCOUNT_INSERT_FAILED);
		}
		return customerInfoService.delCustomerById(id);
	}
	
	/**
	 * @description 获取客户简要信息
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getCustomerById",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getCustomerById(Integer id){
		return customerInfoService.getCustomerShortInfoById(id);
	}
	
	/**
	 * @description 获取所有的客户信息
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/listCustomers",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult listCustomers(){
		return LogisticsResult.ok(customerInfoService.selectCustomers(Constants.CUSTOMER_STATUS_YES));
	}
	
	
	/**
	 * @description 获取所有的客户信息
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getCustomerByBranchGroup",method=RequestMethod.GET)
	@ResponseBody
	public LogisticsResult getCustomerByBranchGroup(Integer branchId){
		return LogisticsResult.ok(customerInfoService.getCustomerByBranchGroup(branchId));
	}
	
}
