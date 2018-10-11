// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.web.trainType;

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

import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.interfaces.TrainTypeService;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trianType.TbTrainType;

/**
 * @description 火车车型
 *
 * @author LiangLin
 *
 * @date 2017年12月7日
 */
@Controller
@RequestMapping("/trainType")
public class TrainTypeController {

	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;
	
	@Autowired
	private TrainTypeService trainTypeService;
	
	/**
	 * 
	 * @description 火车车型 列表
	 * @author liangLin
	 * @date 2017年12月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String anlist(HttpSession session,Model model) {
		DataGridResult result = trainTypeService.selectTrainTypeByPages(PAGE_NUM,CUSTOMER_PAGE_LIMIT);
		model.addAttribute("tyList", result);
		return "/html/manage/transportationManagement/trainType";
	}
	
	
	/**
	 * 
	 * @description 分页查询
	 * @author liangLin
	 * @date 2017年12月16日
	 * @param 
	 * @return
	 */
	@RequestMapping("/byPage/list")
	@ResponseBody
	public LogisticsResult anListByPage(Integer page){
		DataGridResult result = trainTypeService.selectTrainTypeByPages(page,CUSTOMER_PAGE_LIMIT);
		return LogisticsResult.ok(result.getRows());
	}
	
	
	/**
	 * 
	 * @description 新增火车车型
	 * @author liangLin
	 * @date 2017年12月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult add( @Valid TbTrainType tbTrainType, BindingResult result,HttpSession session,Model model) {
		// 从session获取登录人的id
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把必填信息填上"); 
		}
		boolean flag  = trainTypeService.insertTrainType(tbTrainType);
		if(flag != true){
			return LogisticsResult.build(0, "添加失败"); 
		}
		return LogisticsResult.build(1, "添加成功"); 
	}
	
	
	
	/**
	 * 
	 * @description 删除 
	 * @author liangLin
	 * @date 2017年12月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult delete(@RequestParam("idList[]") List<Integer> idList,Model model) {
		
		boolean flag = trainTypeService.delete(idList);
		if(flag != true){
			return LogisticsResult.build(0, "删除失败"); 
		}
		return LogisticsResult.build(1, "删除成功"); 
	}
	
	
	
}
