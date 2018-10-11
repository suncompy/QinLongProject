// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.web.cargoManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.cargo.interfaces.CargomanagementService;
import com.shenhesoft.logistics.manage.helpPojo.CargoPointDetail;
import com.shenhesoft.logistics.manage.helpPojo.CargoSpecificteDetail;
import com.shenhesoft.logistics.manage.helpPojo.TbCargoInfomation;
import com.shenhesoft.logistics.manage.helpPojo.TbCargoMainPoint;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargo;
import com.shenhesoft.logistics.manage.pojo.point.TbPoint;
import com.shenhesoft.logistics.manage.pojo.specifications.TbSpecifications;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月5日
 */
@Controller
@RequestMapping("/cargo")
public class CargoManagementController {
	
	@Autowired
	private CargomanagementService cargomanagementService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;
	
	/**
	 * @description 货物信息
	 * @author LiangDeng
	 * @date 2017年12月5日
	 * @param 
	 * @return
	 */
	@RequestMapping("/cargoList")
	public String cargoList(Model model){
		DataGridResult result = cargomanagementService.listCargoByPage(PAGE_NUM,CUSTOMER_PAGE_LIMIT,null);
		model.addAttribute("tbCargoMainPoint", result);
		return "/html/manage/cargoManagement/cargoInformation";
	}
	
	
	/**
	 * @description 货物 分页查询
	 * @date 2018年1月13日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@RequestMapping("/listCargoByPage")
	@ResponseBody
	public LogisticsResult listCargoByPage(Integer page,String search){
		//获取客户信息
		TbCargo cargo = JsonUtils.jsonToPojo(search, TbCargo.class);
		DataGridResult result = cargomanagementService.listCargoByPage(page,CUSTOMER_PAGE_LIMIT,cargo);
		return LogisticsResult.ok(result);
	}
		
	/**
	 * @description 删除货物信息
	 * @author LiangDeng
	 * @date 2017年12月6日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/deleteCargo", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult deleteCargo(@RequestParam Integer id){
		//删除之前先查询项目表是否关联
		List<Integer> projectId = cargomanagementService.selectProjectIdByCargoId(id);
		if(projectId !=null && projectId.size()>0){
			return LogisticsResult.build(401, "该货场关联了项目，不可删除");
		}
		boolean flag = false;
		flag = cargomanagementService.deleteCargoInfo(id);
		if(!flag){
			return LogisticsResult.build(400, "删除失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 货物信息详情
	 * @author LiangDeng
	 * @date 2017年12月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value ="/cargoInfoDetail", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult cargoInfoDetail(@RequestParam Integer cargoId){
		TbCargoMainPoint cargoMainPointDetail = cargomanagementService.selectMainPointDetailById(cargoId);
		//其他指标
		List<CargoPointDetail> cargoPointDetail = cargomanagementService.selectPointDetailByCargoId(cargoId);
		//规格
		List<CargoSpecificteDetail> cargoSpecificteDetail = cargomanagementService.selectSpecificDetailByCargoId(cargoId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cargoMainPointDetail", cargoMainPointDetail);
		map.put("cargoPointDetail", cargoPointDetail);
		map.put("cargoSpecificteDetail", cargoSpecificteDetail);
		return LogisticsResult.ok(map);
	}
	
	/**
	 * @description 新增货物
	 * @author LiangDeng
	 * @date 2017年12月11日
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value ="/addCargo", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addCargo(@Valid TbCargoInfomation cargoInfomation,BindingResult result) throws JsonParseException, JsonMappingException, IOException{
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把信息补全"); 
		}
		//解析指标
		String pointJson = cargoInfomation.getPointJsonIds();
		List<TbPoint> pointList = JsonUtils.jsonToList(pointJson,TbPoint.class);
		//ObjectMapper pointMapper = new ObjectMapper();
		// json转list<T>
		//JavaType pointType = pointMapper.getTypeFactory().constructParametricType(ArrayList.class, TbPoint.class);
		//List<TbPoint> pointList = (List<TbPoint>) pointMapper.readValue(pointJson, pointType);
		//解析规格
		String specJson = cargoInfomation.getSpecJsonIds();
		List<TbSpecifications> specList = JsonUtils.jsonToList(specJson,TbSpecifications.class);
		TbCargo cargo = new TbCargo();
		cargo.setCargoCode(cargoInfomation.getCargoCode());
		cargo.setCargoName(cargoInfomation.getCargoName());
		int rp = 0;
		rp = cargomanagementService.addCargo(cargo);
		//获得cargoId
		Integer cargoId = cargo.getId();
		int i=0;
		for (TbPoint point : pointList) {
			point.setCargoId(cargoId);
			//去除集合中的null值或""
			if(point.getType() != null && (point.getPointName() == null || point.getPointName() =="")){
				
			}else{
				rp = rp + cargomanagementService.addPoint(point);
				i++;
			}
		}
		for (TbSpecifications specs : specList) {
			specs.setCargoId(cargoId);
			if(specs.getName() == null || specs.getName() == ""){
				
			}else{
				rp = rp + cargomanagementService.addSpecs(specs);
				i++;
			}
		}
		int row = i+1;
		if(rp != row){
			return LogisticsResult.build(400, "新增失败");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * @description 获得某一条货物信息
	 * @author LiangDeng
	 * @date 2017年12月12日
	 * @param 
	 * @return
	 */
	@RequestMapping(value ="/getOneCargo", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getOneCargoInfo(@RequestParam Integer cargoId){
		TbCargoMainPoint cargoMainPointDetails = cargomanagementService.selectMainPointDetailById(cargoId);
		//其他指标
		List<CargoPointDetail> cargoPointDetails = cargomanagementService.queryPointDetailByCargoId(cargoId);
		//规格
		List<CargoSpecificteDetail> cargoSpecificteDetails = cargomanagementService.selectSpecificDetailByCargoId(cargoId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cargoMainPointDetails", cargoMainPointDetails);
		map.put("cargoPointDetails", cargoPointDetails);
		map.put("cargoSpecificteDetails", cargoSpecificteDetails);
		return LogisticsResult.ok(map);
	}
	
	@RequestMapping(value ="/updateCargo", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult updateCargo(@Valid TbCargoInfomation cargoInfomation,BindingResult result) throws JsonParseException, JsonMappingException, IOException{
		if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把信息补全"); 
		}
		//解析指标
		String pointJson = cargoInfomation.getPointJsonIds();
		List<TbPoint> pointList = JsonUtils.jsonToList(pointJson,TbPoint.class);
		//解析规格
		String specJson = cargoInfomation.getSpecJsonIds();
		List<TbSpecifications> specList = JsonUtils.jsonToList(specJson,TbSpecifications.class);
		TbCargo cargo = new TbCargo();
		cargo.setCargoCode(cargoInfomation.getCargoCode());
		cargo.setCargoName(cargoInfomation.getCargoName());
		cargo.setId(cargoInfomation.getId());
		int rp = 0;
		rp = cargomanagementService.updCargo(cargo);
		//获得cargoId
		Integer cargoId = cargoInfomation.getId();
		//先删除原来的 再重新添加
		int i=0;
		int dp = cargomanagementService.delPointDetail(cargoId);
		for (TbPoint point : pointList) {
			point.setCargoId(cargoId);
			//去除集合中的null值或""
			if(point.getType() != null && (point.getPointName() == null || point.getPointName() =="")){
				
			}else{
				rp = rp + cargomanagementService.addPoint(point);
				i++;
			}
		}
		int sp = cargomanagementService.delSpeciDetail(cargoId);
		for (TbSpecifications specs : specList) {
			specs.setCargoId(cargoId);
			if(specs.getName() == null || specs.getName() == ""){
				
			}else{
				rp = rp + cargomanagementService.addSpecs(specs);
				i++;
			}
		}
		int row = i+1;
		if(rp != row){
			return LogisticsResult.build(400, "修改失败");
		}
		return LogisticsResult.ok();
	}
	

	/**
	 * @description 获得某一条货物信息
	 * @author LiangDeng
	 * @date 2017年12月12日
	 * @param 
	 * @return
	 */
	@RequestMapping(value ="/getOneCargoSpecificte", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getOneCargoSpecificte(Integer cargoId){
		//规格
		List<CargoSpecificteDetail> cargoSpecificteDetails = cargomanagementService.selectSpecificDetailByCargoId(cargoId);
		return LogisticsResult.ok(cargoSpecificteDetails);
	}
	
	

	/**
	 * @description 获取所有的货物信息
	 * @author LiangDeng
	 * @date 2017年12月12日
	 * @param 
	 * @return
	 */
	@RequestMapping(value ="/listCargo", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult listCargo(){
		List<TbCargoMainPoint> list = cargomanagementService.listCargo();
		return LogisticsResult.ok(list);
	}
	
	
}
