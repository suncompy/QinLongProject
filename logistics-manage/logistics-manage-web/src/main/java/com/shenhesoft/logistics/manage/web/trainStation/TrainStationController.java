// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.web.trainStation;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.helpPojo.TbTrainStationHelp;
import com.shenhesoft.logistics.manage.interfaces.TrainStationService;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;

/**
 * @description 站点信息
 *
 * @author LiangLin
 *
 * @date 2017年12月11日
 */
@Controller
@RequestMapping("/trainStation")
public class TrainStationController {

	@Autowired
	private TrainStationService trainStationService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;
	/**
	 * 
	 * @description 货场货位页面的下拉列表 
	 * @author liangLin
	 * @date 2017年12月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/getAll",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getAll(Model model) {
		List<TbTrainStation> list = trainStationService.selectTrainStationByPage();
		return LogisticsResult.ok(list);
	}
	
	
	/**
	 * 
	 * @description 页面展示
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		DataGridResult result = trainStationService.selectTrainStationByPages(PAGE_NUM,CUSTOMER_PAGE_LIMIT,new TbTrainStation());
		//List<TbTrainStation> list = trainStationService.selectTrainStationByPage();
		model.addAttribute("stationList", result);
		return "/html/manage/siteManagement/siteInformation";
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
		TbTrainStation trainStation = JsonUtils.jsonToPojo(search, TbTrainStation.class);
		DataGridResult result = trainStationService.selectTrainStationByPages(page,CUSTOMER_PAGE_LIMIT,trainStation);
		return LogisticsResult.ok(result);
	}
	
	/**
	 * 
	 * @description 新增 
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult add( @Valid TbTrainStationHelp tbStion, BindingResult result) throws JsonParseException, JsonMappingException, IOException {
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把必填信息填上"); 
		}
		
		//传id为修改
		if(tbStion.getId() != null){
			boolean flag  = trainStationService.updateTbTrainStationById(tbStion);
			if(flag != true){
				return LogisticsResult.build(0, "修改失败"); 
			}
		}else{
			//新增
			boolean flag  = trainStationService.insertTbTrainStation(tbStion);
			if(flag != true){
				return LogisticsResult.build(0, "添加失败"); 
			}
		}
		return LogisticsResult.build(1, "操作成功"); 
	}
	
	/**
	 * 
	 * @description 修改-获取单个详情 
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult get(@RequestParam Integer id){
		TbTrainStationHelp ts = trainStationService.selectTrainStationByIs(id);
		return LogisticsResult.ok(ts);
	}
	
/*	*//**
	 * 
	 * @description 修改
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	 *//*
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult update( @Valid TbTrainStation tbStion, BindingResult result) throws JsonParseException, JsonMappingException, IOException {
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把必填信息填上"); 
		}
		boolean flag  = trainStationService.updateTbTrainStationById(tbStion);
		if(flag != true){
			return LogisticsResult.build(0, "添加失败"); 
		}
		return LogisticsResult.build(1, "添加成功"); 
	}*/
	
	
	
	/**
	 * 
	 * @description 删除 
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult delete(@RequestParam("idList[]") List<Integer> idList,Model model) {
		//先判断关联关系是否可以删除
		boolean flag = trainStationService.selectIsFreightYardDeleteById(idList);
		if(flag != true){
			return LogisticsResult.build(0, "删除失败,该站点关联了货场货位"); 
		}
		
		flag = trainStationService.selectIsContainerDeleteById(idList);
		if(flag != true){
			return LogisticsResult.build(0, "删除失败,该站点关联了集装箱"); 
		}
		
		flag = trainStationService.selectIsStationBusinessDeleteById(idList);
		if(flag != true){
			return LogisticsResult.build(0, "删除失败,该站点关联了业务联系人"); 
		}
		
		flag = trainStationService.selectChildrenStationById(idList);
		if(flag != true){
			return LogisticsResult.build(0, "删除失败,该站点关联了子站点"); 
		}
		
		flag = trainStationService.selectProjectByStationId(idList);
		if(flag != true){
			return LogisticsResult.build(0, "删除失败,该站点关联了项目"); 
		}
		
		flag = trainStationService.delete(idList);
		if(flag != true){
			return LogisticsResult.build(0, "删除失败"); 
		}
		return LogisticsResult.build(1, "删除成功"); 
	}
	
	
	/**
	 * 
	 * @description 新增站点页面的   -根据当前所选站点级别，查询父级站点 
	 * @author liangLin
	 * @date 2017年12月25日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/parent/get",method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult getParent(@RequestParam Integer id) {
		List<TbTrainStation> list = trainStationService.getParentsById(id);
		return LogisticsResult.ok(list);
	}
	
	/**
	 * 
	 * @description  -根据当前所选站点级别，查询子级站点
	 * @author liangLin
	 * @date 2017年12月25日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/getchildrenStationById",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getChildrenStationById(Integer id) {
		List<TbTrainStation> list = trainStationService.getChildrenStationById(id);
		return LogisticsResult.ok(list);
	}
	
	/**
	 * 
	 * @description  -查询中心站点
	 * @author liangLin
	 * @date 2017年12月25日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/selectThreeTrainStationByLevel",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult selectThreeTrainStationByLevel() {
		List<TbTrainStation> list = trainStationService.selectThreeTrainStationByLevel(Constants.SITE_LEVEL_TWO);
		return LogisticsResult.ok(list);
	}
	
	/**
	 * 
	 * @description  根据级别查询站点信息
	 * @author liangLin
	 * @date 2017年12月25日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/listTrainStationByLevel",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult listTrainStationByLevel(Byte level) {
		List<TbTrainStation> list = trainStationService.selectThreeTrainStationByLevel(level);
		return LogisticsResult.ok(list);
	}
	
	/**
	 * 
	 * @description  根据级别查询站点信息
	 * @author liangLin
	 * @date 2017年12月25日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/listTrainStationByArea",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult listTrainStationByArea(List<String> areas) {
		List<TbTrainStation> list = trainStationService.listTrainStationByArea(areas);
		return LogisticsResult.ok(list);
	}
}
