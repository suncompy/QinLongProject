package com.shenhesoft.logistics.business.inventory.check;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.shenhesoft.logistics.business.helpPojo.StuckHelpPojo;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderDetail;
import com.shenhesoft.logistics.business.inventory.InventoryCheckService;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.office.ExcelUtil;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.cargo.interfaces.CargomanagementService;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
/**
 * @description 库存盘查
 * 
 * @author shilvfei
 * 
 * @date 2017年12月28日
 */
@Controller
@RequestMapping("/inventory")
public class InventoryCheckController {

	@Autowired
	private InventoryCheckService checkService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${PROJECT_PAGE_LIMIT}")
	private Integer PROJECT_PAGE_LIMIT;
	
	/**
	 * @description 返回库存盘查列表信息
	 * @date  2017年12月28日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping("/inventoryCheck")
	public String getProjectInventory(HttpSession session, Model model){
		//查询列表信息
		DataGridResult dataGridResult = checkService.listProjectInventoryByPage(PAGE_NUM,PROJECT_PAGE_LIMIT,null,AppSession.getCurrentUserId());
		model.addAttribute("inventoryChecks", dataGridResult);
		//分发转向
		return "/html/business/inventory/inventoryCheck";
	}
	
	/**
	 * 导出
	 * @description 
	 * @author LiangDeng
	 * @date 2018年3月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/export1", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse export1(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map =FormUtil.getParameterMap(request);
		int page = Integer.parseInt(map.get("pageNo").toString());
		TbSystemUser user = AppSession.getCurrentUser();
		TbProject search = FormUtil.populate(TbProject.class, map, false);
		DataGridResult result = checkService.listProjectInventoryByPage(page,PROJECT_PAGE_LIMIT,search,AppSession.getCurrentUserId());
		String[] heads = {"项目编号","项目类型","联运模式","分支机构","货物品名","发货企业","始发站点","货场","货位","入库吨位","出库吨位","库存吨位","库存调整吨位","收货企业","到达站点","货场","货位","入库吨位","出库吨位","库存吨位","库存调整吨位","盘查时间"};
		String[] headCodes = {"projectCode","exportProjectType","exportTransportType","branchGroupName","cargoName","sendCargoCompanyName","beginStationName","beginFreightYardName","beginCargoLocationName","beginEnterQty","beginOutQty","beginCurrentQty","beginAdjustQty","receiveCargoCompanyName","endStationName","endFreightYardName","endCargoLocationName","endEnterQty","endOutQty","endCurrentQty","endAdjustQty","queryTime"};
		
		List<StuckHelpPojo> list = result.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {
			for (StuckHelpPojo detail : list) {
				exportChangeContent(detail);
				datas.add(FormUtil.populate(detail));
			}
		}
		if(ExcelUtil.createExcel(request,response, "库存盘查", "库存盘查", heads, headCodes, datas)){
			return new ShResponse(HttpStatus.OK.value(), "","下载成功");
		}
		return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
	}
	
	public StuckHelpPojo exportChangeContent(StuckHelpPojo detail) {
		if(detail.getTransportType() != null) {
			if(detail.getTransportType() == 0) {
				detail.setExportTransportType("汽运");
			}else if(detail.getTransportType() == 1) {
				detail.setExportTransportType("接取");
			}else if(detail.getTransportType() == 2) {
				detail.setExportTransportType("送达");
			}else if(detail.getTransportType() == 3) {
				detail.setExportTransportType("火运");
			}else if(detail.getTransportType() == 4) {
				detail.setExportTransportType("接取+火运");
			}else if(detail.getTransportType() == 5) {
				detail.setExportTransportType("火运+送达");
			}else if(detail.getTransportType() == 6) {
				detail.setExportTransportType("联运");
			}else if(detail.getTransportType() == 7) {
				detail.setExportTransportType("接取+送达");
			}
		}
		
		if(detail.getProjectType() == 0) {
			detail.setExportProjectType("集装箱");
		}else if(detail.getProjectType() == 1) {
			detail.setExportProjectType("散装箱");
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		detail.setQueryTime(DateUtils.date2Str(detail.getAdjustDate(),sdf));
		return detail;
	}
	/**
	 * @description 返回仓位图
	 * @date  2017年12月28日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getCargoLocationImg",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getCargoLocationImg(Integer id){
		LogisticsResult logisticsResult = checkService.getCargoLocationImg(id);
		return logisticsResult;
	}
	
	/**
	 * @description 库存盘查 查看项目详情
	 * @date 2018年1月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@RequestMapping("/getInventoryCheckDetail")
	@ResponseBody
	public LogisticsResult getInventoryCheckDetail(Integer projectId){
		if(projectId==null){
			return LogisticsResult.build(404, "项目id不能为空");
		}
		return checkService.getInventoryCheckDetail(projectId);
	}
	
	/**
	 * @description 库存盘查： 根据货场id获取货位库存
	 * @date 2018年1月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@RequestMapping("/getStockByFreightYardId")
	@ResponseBody
	public LogisticsResult getStockByFreightYardId(Integer projectId,Integer freightYardId){
		if(projectId==null || freightYardId==null ){
			return LogisticsResult.build(404, "项目id和货场id不能为空");
		}
		return checkService.getStockByFreightYardId(projectId, freightYardId);
	}
	

	/**
	 * @description 库存盘查： 库存调整
	 * @date 2018年1月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@RequestMapping("/updateStock")
	@ResponseBody
	public LogisticsResult updateStock(String stocks,HttpSession session){
		if(stocks==null){
			return LogisticsResult.build(404, "请把信息补全!");
		}
		
		List<TbStock> list = JsonUtils.jsonToList(stocks, TbStock.class);
		
		TbSystemUser systemUser = (TbSystemUser) session.getAttribute("systemUser");
		
		return checkService.updateStock(list,systemUser);
	}
	
	/**
	 * @description 库存盘查： 分页并条件查询
	 * @date  2017年12月28日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listProjectInventoryByPage",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult listProjectInventoryByPage(Integer page,String search,HttpSession session){
		TbSystemUser user = (TbSystemUser) session.getAttribute("systemUser");
		TbProject project = JsonUtils.jsonToPojo(search, TbProject.class);
		DataGridResult dataGridResult = checkService.listProjectInventoryByPage(page,PROJECT_PAGE_LIMIT,project,user.getId());
		return LogisticsResult.ok(dataGridResult);
	}
	
	/**
	 * @description 库存盘查：入库功能
	 * @date  2017年12月28日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/storageInventory",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult storageInventory(TbStock stock){
		return checkService.storageInventory(stock);
	}
	
	
}
