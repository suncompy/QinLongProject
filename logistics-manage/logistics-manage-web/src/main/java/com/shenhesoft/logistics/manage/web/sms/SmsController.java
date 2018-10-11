// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.web.sms;

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

import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.ResultCodeUtils;
import com.shenhesoft.logistics.common.util.ResultContentUtils;
import com.shenhesoft.logistics.manage.customer.CustomerInfoService;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.SmsCustomerHelp;
import com.shenhesoft.logistics.manage.helpPojo.TbStationBusinessHelp;
import com.shenhesoft.logistics.manage.pojo.codeModel.TbSmsCodeModel;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.smsPlan.TbSmsPlan;
import com.shenhesoft.logistics.manage.pojo.tbSmsSend.TbSmsSend;
import com.shenhesoft.logistics.manage.smsPlan.SmsPalnService;

/**
 * @description 短信管理
 *
 * @author LiangLin
 *
 * @date 2017年12月14日
 */
@Controller
@RequestMapping("/system/sms")
public class SmsController {

	@Autowired
	private CustomerInfoService customerInfoService;
	
	@Autowired
	private SmsPalnService smsPalnService;
	
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;

	@Value("${business.sendmsg.signName}")
	private String signName;
	@Value("${business.sendmsg.tpl}")
	private String tpl;
	
	@Value("${accessKeyId}")
	private String accessKeyId;
	@Value("${accessKeySecret}")
	private String accessKeySecret;
	
	/**
	 * 
	 * @description 客户信息列表 
	 * @author liangLin
	 * @date 2017年12月15日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public String anlist(HttpSession session, Model model) {
		/*// 获取客户信息
		TbCustomer customer = new TbCustomer();
		customer.setStatus(Constants.CUSTOMER_STATUS_YES);
		DataGridResult result = customerInfoService.listCustomerByPage(PAGE_NUM, CUSTOMER_PAGE_LIMIT,
				customer);
		model.addAttribute("customerList", result);*/
		Map<String,Object> map = new HashMap<>();
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		DataGridResult result = smsPalnService.listCustomerAndBusnissByPage(PAGE_NUM, CUSTOMER_PAGE_LIMIT,map);
		model.addAttribute("customerList", result);
		TbSmsSend smsSend = new TbSmsSend();
		DataGridResult result2 = smsPalnService.queryCustomerAndBusnissMsgList(PAGE_NUM, CUSTOMER_PAGE_LIMIT,smsSend);
		model.addAttribute("msgList", result2);
		
		return "/html/manage/systemManagement/messageManagement";
	}
	
	/**
	 * 
	 * @description 发送短信分页
	 * @author LiangDeng
	 * @date 2018年2月9日
	 * @param 
	 * @return
	 */
	@RequestMapping("/needSendMsgByPage")
	@ResponseBody
	public LogisticsResult needSendMsgByPage(Integer page){
		Map<String, Object> map = new HashMap<>();
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		//TbFinanceAccount financeAccount = JsonUtils.jsonToPojo(search, TbFinanceAccount.class);
		DataGridResult result = smsPalnService.listCustomerAndBusnissByPage(page,CUSTOMER_PAGE_LIMIT,map);
		return LogisticsResult.ok(result);
	}
	
	/**
	 * 
	 * @description 短信列表分页
	 * @author LiangDeng
	 * @date 2018年2月9日
	 * @param 
	 * @return
	 */
	@RequestMapping("/msgListByPage")
	@ResponseBody
	public LogisticsResult msgListByPage(Integer page){
		TbSmsSend smsSend = new TbSmsSend();
		//TbFinanceAccount financeAccount = JsonUtils.jsonToPojo(search, TbFinanceAccount.class);
		DataGridResult result = smsPalnService.queryCustomerAndBusnissMsgList(page,CUSTOMER_PAGE_LIMIT,smsSend);
		return LogisticsResult.ok(result);
	}
	
	/**
	 * 
	 * @description 新增计划 
	 * @author liangLin
	 * @date 2017年12月15日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/plan/add",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addPlan(@Valid TbSmsPlan tbSmsPlan,BindingResult result ){
		if(result.hasErrors()){
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY,ResultContentUtils.INSERT_EMPTY);
		}
		boolean flag = smsPalnService.insertPlan(tbSmsPlan);
		if(flag != true){
			return LogisticsResult.build(0, "新增失败"); 
		}
		return LogisticsResult.build(1, "新增成功"); 
	}
	
	/**
	 * 
	 * @description 发送短信 
	 * @author liangLin
	 * @date 2017年12月15日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/sendMsg",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult sendMsg(@RequestParam List<String> ids,@RequestParam String content){
		
		boolean flag = smsPalnService.sendMsg(ids,content,accessKeyId,accessKeySecret,signName,tpl);
		if(!flag) {
			return LogisticsResult.build(2, "发送失败");
		}
		return LogisticsResult.build(1, "已发送"); 
	}
	
	
	/**
	 * 
	 * @description 获取短信模板内容 
	 * @author liangLin
	 * @date 2017年12月15日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/getModel",method=RequestMethod.GET)
	@ResponseBody
	public LogisticsResult changeModel(@RequestParam String code){
		TbSmsCodeModel  tbSmsCodeModel= smsPalnService.selectContentByCode(code);
		return LogisticsResult.ok(tbSmsCodeModel); 
	}
	
	
	/**
	 * 
	 * @description 火运发送短信 
	 * @author liangdeng
	 * @date 2018年3月2日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/sendMsgOfTrain",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult sendMsgOfTrain(@RequestParam List<String> ids,@RequestParam String content){
		
		boolean flag = smsPalnService.sendMsgOfTrain(ids,content,accessKeyId,accessKeySecret,signName,tpl);
		if(!flag) {
			return LogisticsResult.build(2, "发送失败");
		}
		return LogisticsResult.build(1, "已发送"); 
	}
	
	/**
	 * 
	 * @description 火运查询是否有业务联系人 
	 * @author liangdeng
	 * @date 2018年3月2日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/checkTrainBussinss",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult checkTrainBussinss(@RequestParam List<String> ids){
		
		List<TbTrainOrder> list = smsPalnService.chechTrainBussinss(ids);
		return LogisticsResult.ok(list); 
	}
	
	/**
	 * 
	 * @description 短驳查询是否有业务联系人 
	 * @author liangdeng
	 * @date 2018年3月2日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/checkBulkBussinss",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult checkBulkBussinss(@RequestParam List<String> ids){
		
		List<TbOrder> list = smsPalnService.chechTbOrderBussinss(ids);
		return LogisticsResult.ok(list); 
	}
	
	
	/**
	 * 
	 * @description 短驳发送短信 
	 * @author liangdeng
	 * @date 2018年3月2日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/sendMsgOfBulk",method=RequestMethod.POST)
	@ResponseBody
	public LogisticsResult sendMsgOfBulk(@RequestParam List<String> ids,@RequestParam String content){
		
		boolean flag = smsPalnService.sendMsgOfBulk(ids,content,accessKeyId,accessKeySecret,signName,tpl);
		if(!flag) {
			return LogisticsResult.build(2, "发送失败");
		}
		return LogisticsResult.build(1, "已发送"); 
	}
	
	
	
	
	
	
	
	
	
}
