// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.web.siteManagement;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.ImageUtil;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.TbFreightYardDetail;
import com.shenhesoft.logistics.manage.interfaces.SiteManageService;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;

/**
 * @description 站点管理- 货场货位
 *
 * @author LiangLin
 *
 * @date 2017年12月9日
 */
@Controller
@RequestMapping("/siteManager")
public class SiteManagementController {

		
	@Autowired
	private SiteManageService siteManageService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;
	/**
	 * 
	 * @description 货场货位页面展示 
	 * @author liangLin
	 * @date 2017年12月9日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/freight/yard/list", method = RequestMethod.GET)
	public String yardList(Model model) {
		DataGridResult result = siteManageService.listFreightYardsByPage(PAGE_NUM,CUSTOMER_PAGE_LIMIT,null);
		//List<TbFreightYardDetail> yardList = siteManageService.selectFreightYardsByPage();
		model.addAttribute("yardList", result);
		return "/html/manage/siteManagement/goodsAllocation";
	}
	
	
	
	/**
	 * 
	 * @description 分页查询
	 * @author liangLin
	 * @date 2017年12月16日
	 * @param 
	 * @return
	 */
	@RequestMapping("/listFreightYardsByPage")
	@ResponseBody
	public LogisticsResult listFreightYardsByPage(Integer page,String search){
		TbFreightYard freightYard = JsonUtils.jsonToPojo(search, TbFreightYard.class);
		DataGridResult result = siteManageService.listFreightYardsByPage(page,CUSTOMER_PAGE_LIMIT,freightYard);
		return LogisticsResult.ok(result);
	}
	
	/**
	 * 
	 * @description  新增或修改  - 货场货位
	 * @author liangLin
	 * @date 2017年12月9日
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/freight/addOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult add( @Valid TbFreightYardDetail tfyd, BindingResult result,HttpSession session) throws JsonParseException, JsonMappingException, IOException {
		 if(tfyd.getIsIsolated() == 0) {
			//独立货场 上级站点必填
			 if(tfyd.getTrainStationId() == null) {
				 return LogisticsResult.build(0, "请把必填信息填上");
			 }
		 }
		 if(!StringUtils.isBlank(tfyd.getFreightYardImg())){
			 LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo",tfyd.getFreightYardImg(), null);
			 if(base64UpLoad.getStatus()==200){
				 tfyd.setFreightYardImg(base64UpLoad.getData().toString());
			 }else{
				 return LogisticsResult.build(400, "上传图片失败!");
			 }
		 }
		 if (result.hasErrors()) {
			return LogisticsResult.build(0, "请把必填信息填上"); 
		}
		 
		if(tfyd.getId() != null){
			//更新
			boolean flag  = siteManageService.updateFreightYardById(tfyd);
			if(flag != true){
				return LogisticsResult.build(0, "修改失败"); 
			}
		}else{
			//新增
			boolean flag  = siteManageService.insertFreightYard(tfyd);
			if(flag != true){
				return LogisticsResult.build(0, "添加失败"); 
			}
		}
		 
		
		return LogisticsResult.build(1, "操作成功"); 
	}
	
	
	/**
	 * 
	 * @description 删除 
	 * @author liangLin
	 * @date 2017年12月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/freight/delete", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult delete(@RequestParam("idList[]") List<Integer> idList,Model model) {
		boolean f = siteManageService.checkFreightIsUseById(idList);
		if(f != true) {
			return LogisticsResult.build(0, "删除失败,该货场关联了项目或者运单或者库存"); 
		}
		boolean flag = siteManageService.delete(idList);
		if(flag != true){
			return LogisticsResult.build(0, "删除失败"); 
		}
		return LogisticsResult.build(1, "删除成功"); 
	}
	
	/**
	 * 
	 * @description 修改 - 获取详情 
	 * @author liangLin
	 * @date 2017年12月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/freight/yard/get", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult yardget(@RequestParam Integer id) {
		return LogisticsResult.ok(siteManageService.selectFreightYardsById(id));
	}
	
	
	
	/**
	 * 
	 * @description 货场下拉 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/freight/getAll", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult freightgetAll() {
		return LogisticsResult.ok(siteManageService.getAllFreightYards());
	}
	
	
	/**
	 * 
	 * @description 根据货场id 获取货位 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/cargo/location/getAll", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult getlocation(@RequestParam Integer id) {
		return LogisticsResult.ok(siteManageService.getAllcargoLocationsByYardId(id));
	}
	
	
	/**
	 * 
	 * @description 货场货位联动下拉
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/freight/location/getAll", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult getAll(@RequestParam(value="id",required=false) Integer id) {
		return LogisticsResult.ok(siteManageService.getAllFreightLocationsByYardId(id));
	}
	
	
	/**
	 * @description 根据火车站点获取货场信息
	 * @date 2017年12月26日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getFreightYardByStationId", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getFreightYardByStationId(Integer id) {
		return LogisticsResult.ok(siteManageService.getFreightYardsByTrainStationId(id));
	}
	
	/**
	 * @description 查询所有独立货场
	 * @author LiangDeng
	 * @date 2018年1月12日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getAllFreightYardOfIsolated", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult getAllFreightYardOfIsolated(@RequestParam Integer id) {
		List<TbFreightYard> list = siteManageService.getAllFreightYardOfIsolated(id);
		return LogisticsResult.ok(list);
	}
	
	/**
	 * @description 企业货场 查询所有客户
	 * @author LiangDeng
	 * @date 2018年3月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getAllCustomter", method = RequestMethod.GET)
	@ResponseBody
	public LogisticsResult getAllCustomter() {
		List<CustomerInfo> list = siteManageService.getAllCustomter();
		return LogisticsResult.ok(list);
	}
	
	/**
	 * 
	 * @description 修改 - 如果点击删除货位 校验是否被使用 
	 * @author liangdeng
	 * @date 2017年12月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/checkCargoLocation", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult checkCargoLocation(@RequestParam Integer id) {
		int sumCount = siteManageService.checkCargoLocation(id);
		if(sumCount > 0) {
			return LogisticsResult.build(0, "删除失败,该货位关联了运单或者库存");
		}
		return LogisticsResult.ok();
	}
	
	/**
	 * 
	 * @description 修改 - 如果点击删除货位 
	 * @author liangdeng
	 * @date 2017年12月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/deleteCargoLocationById", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult deleteCargoLocationById(@RequestParam Integer id) {
		int row = siteManageService.deleteCargoLocationById(id);
		if(row != 1) {
			return LogisticsResult.build(0, "删除异常");
		}
		return LogisticsResult.ok();
	}
}
