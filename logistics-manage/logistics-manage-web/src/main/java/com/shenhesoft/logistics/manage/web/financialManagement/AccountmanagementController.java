// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.web.financialManagement;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.finacialManagement.interfaces.AccountmanagementService;
import com.shenhesoft.logistics.manage.helpPojo.AccountManagementDetail;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbAccountRecordDetail;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description 财务管理:账户管理
 *
 * @author LiangDeng
 *
 * @date 2017年12月13日
 */
@Controller
@RequestMapping("/account")
public class AccountmanagementController {
	
	@Autowired
	private AccountmanagementService accountmanagementService;
	
	@Autowired
	private BranchGroupService branchGroupService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;
	
	/**
	 * @description 账户管理显示
	 * @author LiangDeng
	 * @date 2017年12月13日
	 * @param 
	 * @return
	 */
	@RequestMapping("/accountList")
	public String accountList(Model model){
		DataGridResult result = accountmanagementService.listAccountByPage(PAGE_NUM,CUSTOMER_PAGE_LIMIT,null);
		model.addAttribute("accountList", result);
		return "/html/manage/financialManagement/accountManagement";
	}
	
	/**
	 * @description  账户管理 分页查询
	 * @date 2018年1月13日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@RequestMapping("/listAccountByPage")
	@ResponseBody
	public LogisticsResult listAccountByPage(Integer page,String search){
		//获取客户信息
		TbFinanceAccount financeAccount = JsonUtils.jsonToPojo(search, TbFinanceAccount.class);
		DataGridResult result = accountmanagementService.listAccountByPage(page,CUSTOMER_PAGE_LIMIT,financeAccount);
		return LogisticsResult.ok(result);
	}
		
	/**
	 * @description 查询所有分支机构
	 * @author LiangDeng
	 * @date 2017年12月13日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryBranchGroup", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult queryBranchGroup(){
		List<TbBranchGroup> branchGroupList = branchGroupService.selectBranchGroup((byte)1);
		return LogisticsResult.ok(branchGroupList);
	}
	
	/**
	 * @description 新增账户
	 * @author LiangDeng
	 * @date 2017年12月13日
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/addAccountManage", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addAccountManage(@Valid TbFinanceAccount account,BindingResult result) throws ParseException{
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把信息补全"); 
		}
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//Date time = simpleDateFormat.parse(account.getTime());
		account.setAccountOpeningTime(new Date());
		account.setAccountBalance(account.getStartAccountBalance());
		account.setStatus((byte)0);
		int row = accountmanagementService.addAccount(account);
		if(row != 1){
			return LogisticsResult.build(400, "新增失败");
		}
		return LogisticsResult.ok();
	}
	
	
	/**
	 * @description 账户管理:删除
	 * @date 2018年1月13日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult deleteAccount(@RequestParam Integer id){
		int r = accountmanagementService.delAccount(id);
		if(r != 1){
			return LogisticsResult.build(400, "删除失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 根据选择账户种类 查询对应名称
	 * @date 2018年2月6日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getAccountNameByType", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getAccountNameByType(@RequestParam byte type){
		LogisticsResult result = accountmanagementService.getAccountNameByType(type);
		return LogisticsResult.ok(result);
	}
	
	/**
	 * @description 获取账户详情
	 * @date 2018年2月6日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getAccountDetailById", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getAccountDetailById(@RequestParam Integer id){
		TbFinanceAccount account  = accountmanagementService.getAccountDetailById(id);
		return LogisticsResult.ok(account);
	}
	
	/**
	 * @description 修改账户
	 * @author LiangDeng
	 * @date 2017年2月6日
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/updAccountManage", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult updAccountManage(@Valid TbFinanceAccount account,BindingResult result) throws ParseException{
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把信息补全"); 
		}
		int row = accountmanagementService.updAccount(account);
		if(row != 1){
			return LogisticsResult.build(400, "修改失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 根据选择账户种类 查询对应纳税号
	 * @date 2018年2月6日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getTaxByTypeAndId", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getTaxByTypeAndId(@RequestParam byte type,@RequestParam Integer id){
		LogisticsResult result = accountmanagementService.getTaxByTypeAndId(type,id);
		return LogisticsResult.ok(result);
	}
	
	/**
	 * @description 根据选择账户种类 项目id 查询站点或客户
	 * @date 2018年4月11日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getAccountNameByTypeAndProjectId", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getAccountNameByTypeAndProjectId(@RequestParam byte type,@RequestParam Integer projectId){
		LogisticsResult result = accountmanagementService.getAccountNameByTypeAndProjectId(type,projectId);
		return LogisticsResult.ok(result);
	}
	
	/**
	 * @description查看账户详情
	 * @date 2018年4月25日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/lookAccountDetail", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult lookAccountDetail(@RequestParam Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		TbFinanceAccount account  = accountmanagementService.lookAccountDetail(id);
		List<TbAccountRecordDetail> list = accountmanagementService.selectAccountDetailById(id);
		map.put("account", account);
		map.put("list", list);
		return LogisticsResult.ok(map);
	}
}
