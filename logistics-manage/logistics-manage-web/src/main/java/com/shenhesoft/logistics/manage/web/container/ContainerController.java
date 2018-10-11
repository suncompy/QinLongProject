// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.web.container;

import java.util.List;

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
import com.shenhesoft.logistics.manage.helpPojo.TbBoxDetail;
import com.shenhesoft.logistics.manage.interfaces.TransportManagerService;
import com.shenhesoft.logistics.manage.pojo.box.TbContainer;
import com.shenhesoft.logistics.manage.pojo.box.TbContainerType;

/**
 * @description 集装箱管理
 *
 * @author LiangLin
 *
 * @date 2017年12月8日
 */

@RequestMapping("/container")
@Controller
public class ContainerController {

	@Autowired
	private TransportManagerService transService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;

	/**
	 * 
	 * @description 集装箱列表
	 * @author liangLin
	 * @date 2017年12月8日
	 * @param
	 * @return
	 */
	@RequestMapping("/list")
	public String List(Model model) {
		DataGridResult result = transService.selectBoxByPages(PAGE_NUM,CUSTOMER_PAGE_LIMIT,null);
		//List<TbBoxDetail> list = transService.selectBoxByPage();
		model.addAttribute("boxList", result);
		return "/html/manage/transportationManagement/boxManagement";
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
	public LogisticsResult anListByPage(Integer page,String search){
		TbBoxDetail boxDetail = JsonUtils.jsonToPojo(search, TbBoxDetail.class);
		DataGridResult result = transService.selectBoxByPages(page,CUSTOMER_PAGE_LIMIT,boxDetail);
		return LogisticsResult.ok(result);
	}
	
	/**
	 * 
	 * @description 新增集装箱 
	 * @author liangLin
	 * @date 2017年12月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult add(@Valid TbContainer tcr, BindingResult result) {
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把必填信息填上");
		}
		//校验集装箱号是否存在
		List<TbContainer> container = transService.checkContainerIsExist(tcr.getContainerId(),tcr.getId());
		if(container.size()>0 && container != null) {
			return LogisticsResult.build(0, "此集装箱号已存在");
		}
		if(tcr.getId() != null) {
			boolean flag = transService.updateTbContainer(tcr);
			if (flag != true) {
				return LogisticsResult.build(0, "修改失败");
			}
		}else {
			boolean flag = transService.insertTbContainer(tcr);
			if (flag != true) {
				return LogisticsResult.build(0, "添加失败");
			}
		}
		return LogisticsResult.build(1, "操作成功");
	}
	
	
	/**
	 * 
	 * @description 新增集装箱类型
	 * @author liangLin
	 * @date 2017年12月8日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/type/add", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult add(@Valid TbContainerType tbType, BindingResult result) {
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把必填信息填上");
		}
		boolean flag = transService.insertContainType(tbType);
		if (flag != true) {
			return LogisticsResult.build(0, "添加失败");
		}
		return LogisticsResult.build(1, "添加成功");
	}

	/**
	 * 
	 * @description 集装箱类型列表
	 * @author liangLin
	 * @date 2017年12月8日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/type/list", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult typeList(Model model) {
		List<TbContainerType> list = transService.selectBoxTypeByPage();
		return LogisticsResult.ok(list);
	}

	
	
	/**
	 * 
	 * @description 集装箱删除 
	 * @author liangLin
	 * @date 2017年12月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult delete(@RequestParam("idList[]") List<Integer> idList,Model model) {
		
		boolean flag = transService.deleteBox(idList);
		if(flag != true){
			return LogisticsResult.build(0, "删除失败"); 
		}
		return LogisticsResult.build(1, "删除成功"); 
	}
	
	
	
	
	
	/**
	 * 
	 * @description 删除集装箱类型
	 * @author liangLin
	 * @date 2017年12月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/type/delete", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult typeDelete(@RequestParam("idList[]") List<Integer> idList,Model model) {
		
		//判断是否关联集装箱
		boolean flag = transService.selectIsRelateBoxDeleteById(idList);
		if(flag != true){
			return LogisticsResult.build(0, "删除失败,请先删除此类型关联的集装箱"); 
		}
		
		flag = transService.deleteBoxType(idList);
		if(flag != true){
			return LogisticsResult.build(0, "删除失败"); 
		}
		return LogisticsResult.build(1, "删除成功"); 
	}
	
	/**
	 * @description 获得单个集装箱详情
	 * @author LiangDeng
	 * @date 2018年3月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getContainerDetail", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getContainerDetail(@RequestParam Integer id) {
		TbContainer container = transService.getContainerDetail(id);
		return LogisticsResult.ok(container); 
	}
}
