package com.shenhesoft.logistics.business.inventory.shortbarge;

import java.math.BigDecimal;
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
import com.shenhesoft.logistics.business.helpPojo.TbOrderHelpPojo;
import com.shenhesoft.logistics.business.inventory.ShortAccessStorageService;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.office.ExcelUtil;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.search.OrderSearch;

/**
 * @description 短驳出入库查询
 * 
 * @author shilvfei
 * 
 * @date 2018年1月2日
 */
@Controller
@RequestMapping("/inventory")
public class ShortAccessStorageController {

	/**
	 * 短驳出入库 服务层
	 */
	@Autowired
	private ShortAccessStorageService shortAccessStorageService ;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${PROJECT_PAGE_LIMIT}")
	private Integer PROJECT_PAGE_LIMIT;
	
	/**
	 * @description 短驳出入库查询 项目列表信息
	 * @date  2017年12月28日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping("/shortAccessStorage")
	public String getShortAccessStorage(){
		return "/html/business/inventory/shortAccessStorage";
	}
	
	/**
	 * @description 分页查询
	 * @date  2017年12月28日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping("/shortAccessStorageByPage")
	@ResponseBody
	public LogisticsResult getShortAccessStorageByPage(Integer page,String search){
		//分发转向
		OrderSearch orderSearch = JsonUtils.jsonToPojo(search, OrderSearch.class);
		DataGridResult dataGridResult =  shortAccessStorageService.getShortAccessStorage(page,PROJECT_PAGE_LIMIT,orderSearch);
		return LogisticsResult.ok(dataGridResult);
	}
	
	/**
	 * @description 查询统计
	 * @date  2017年12月28日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping("/shortAccessStorageStatistics")
	@ResponseBody
	public LogisticsResult shortAccessStorageStatistics(Integer projectId){
		TbOrderHelpPojo orderHelpPojo =  shortAccessStorageService.shortAccessStorageStatistics(projectId);
		return LogisticsResult.ok(orderHelpPojo);
	}
	
	/**
	 * 导出1
	 * @param request 页面表单
	 * @param response 输出流
	 * @return
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse export1(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map =FormUtil.getParameterMap(request);
		int page = Integer.parseInt(map.get("pageNo").toString());
		TbSystemUser user = AppSession.getCurrentUser();
		OrderSearch orderSearch = FormUtil.populate(OrderSearch.class, map, false);
		//全部
		DataGridResult result = shortAccessStorageService.getShortAccessStorage(page,PROJECT_PAGE_LIMIT,orderSearch);
		String[] heads = {"项目编号","项目类型","运单编号","联运模式","分支机构","阶段","发货单位","收货单位","货物品名","车号","提货时间","提货吨位","到货时间","到货吨位","耗损","货场","货位"};
		String[] headCodes = {"projectCode","exportProjectType","orderCode","exportTransportType","branchGroupName","exportStep","sendCompany","receiptCompany","cargoName","carPlateNumber","exportTakeDeliveryDate","exportSumSendNet","exportArriveDate","exportSumReceiptNet","wastage","huochangName","huoweiName"};
		
		List<TbOrder> list = result.getRows();
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(list)) {
			for(TbOrder order:list) {
				exportChangeContent(order);
				datas.add(FormUtil.populate(order));
			}	
		}
		if(ExcelUtil.createExcel(request,response, "短驳出入库", "短驳出入库", heads, headCodes, datas)){
			return new ShResponse(HttpStatus.OK.value(), "","下载成功");
		}
		return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
	}
	
	public TbOrder exportChangeContent(TbOrder order) {
		if(order.getTransportType() != null) {
			if(order.getTransportType() == 0) {
				order.setExportTransportType("汽运");
			}else if(order.getTransportType() == 1) {
				order.setExportTransportType("接取");
			}else if(order.getTransportType() == 2) {
				order.setExportTransportType("送达");
			}else if(order.getTransportType() == 3) {
				order.setExportTransportType("火运");
			}else if(order.getTransportType() == 4) {
				order.setExportTransportType("接取+火运");
			}else if(order.getTransportType() == 5) {
				order.setExportTransportType("火运+送达");
			}else if(order.getTransportType() == 6) {
				order.setExportTransportType("联运");
			}else if(order.getTransportType() == 7) {
				order.setExportTransportType("接取+送达");
			}
		}
		
		if(order.getType() == 1) {
			order.setExportProjectType("集装箱");
		}else if(order.getType() == 2) {
			order.setExportProjectType("散装箱");
		}
		
		if(order.getStepSelectCode() == 0) {
			order.setExportStep("接取");
			//根据阶段显示货场货位
			order.setHuochangName(order.getDistributionCargoPlace());
			order.setHuoweiName(order.getDistributionCargoSite());
		}else if(order.getStepSelectCode() == 1){
			order.setExportStep("送达");
			order.setHuochangName(order.getTakeCarogoPlaceName());
			order.setHuoweiName(order.getTakeCargoSiteName());
		}else {
			order.setExportStep("汽运");
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		order.setExportArriveDate(DateUtils.date2Str(order.getReceipterDate(),sdf));
		order.setExportTakeDeliveryDate(DateUtils.date2Str(order.getTakeDeliveryDate(),sdf));
		
		if(order.getContainerOneSendNet() == null) {
			order.setContainerOneSendNet(new BigDecimal(0));
		}
		if(order.getContainerTwoSendNet() == null) {
			order.setContainerTwoSendNet(new BigDecimal(0));
		}
		if(order.getContainerOneReceiptNet() == null) {
			order.setContainerOneReceiptNet(new BigDecimal(0));
		}
		if(order.getContainerTwoReceiptNet() == null) {
			order.setContainerTwoReceiptNet(new BigDecimal(0));
		}
		order.setExportSumSendNet(order.getContainerOneSendNet().add(order.getContainerTwoSendNet()));
		order.setExportSumReceiptNet(order.getContainerOneReceiptNet().add(order.getContainerTwoReceiptNet()));
		//耗损
		order.setWastage(order.getExportSumSendNet().subtract(order.getExportSumReceiptNet()));
		return order;
	}
}
