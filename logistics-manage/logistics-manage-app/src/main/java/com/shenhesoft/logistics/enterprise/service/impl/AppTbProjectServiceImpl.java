package com.shenhesoft.logistics.enterprise.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.shenhesoft.logistics.business.helpPojo.ProjectDistributionDetail;
import com.shenhesoft.logistics.business.helpPojo.TbProjectDetail;
import com.shenhesoft.logistics.business.inventory.InventoryCheckService;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
import com.shenhesoft.logistics.business.project.manage.ProjectManagmentService;
import com.shenhesoft.logistics.business.project.operation.ProjectOperationService;
import com.shenhesoft.logistics.business.shortbarge.order.ShortBargeOrderService;
import com.shenhesoft.logistics.business.shortbarge.publish.PublishJobService;
import com.shenhesoft.logistics.common.GeneralResponse;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.enterprise.service.AppTbProjectService;
import com.shenhesoft.logistics.manage.interfaces.SiteManageService;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.user.UserService;

/**
 * app端 项目信息 service 实现
 * 
 * @author dusd
 * @date 2017年12月28日
 */
@Service
public class AppTbProjectServiceImpl implements AppTbProjectService {

	/**
	 * 企业用户 service 接口
	 */
	@Autowired
	private UserService userService;
	/**
	 * 项目运营 service 接口
	 */
	@Autowired
	private ProjectOperationService operationService;
	/**
	 * 项目管理 service
	 */
	@Autowired
	private ProjectManagmentService projectManagmentService;
	/**
	 * 站点 service
	 */
	@Autowired
	private SiteManageService siteManageService;
	/**
	 * 库存盘查 service
	 */
	@Autowired
	private InventoryCheckService checkService;
	/**
	 * 短驳 service
	 */
	@Autowired
	private PublishJobService publishJobService;

	/**
	 * 通过用户id查询用户信息
	 * 
	 * @author dusd
	 * @date 2017年12月28日
	 * @return
	 */
	private TbSystemUser viewTbSystemUserDataMap(Map<String, String> dataMap) {
		if (dataMap == null)
			return null;
		String strUserId = dataMap.get("userId");
		if (StringUtil.isEmpty(strUserId)) {
			return null;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		return userService.getTbSystemUserById(userId);
	}

	/**
	 * 通过projectId查询项目信息
	 * 
	 * @author dusd
	 * @date 2018年1月3日
	 * @param dataMap
	 * @return
	 */
	private TbProject viewTbProjectDataMap(Map<String, String> dataMap) {
		if (dataMap == null)
			return null;
		String strProjectId = dataMap.get("projectId");
		if (StringUtil.isEmpty(strProjectId)) {
			return null;
		}
		Integer projectId = Integer.valueOf(strProjectId.trim());
		LogisticsResult logisticsResult = projectManagmentService.selectProject(projectId);
		return (TbProject) logisticsResult.getData();

	}

	@Override
	public GeneralResponse listProjectCheckApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询火运运单信息失败");
			return generalResponse;
		}
		// 判断登录信息 end

		// 此列表待修改
		DataGridResult result = null;
		// DataGridResult result =
		// trainOrderService.selectTrainOrderByPage(page,pageLimit,(byte)0);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("获得火运订单列表成功");
		generalResponse.setObj(result);
		return generalResponse;
	}

	@Override
	public GeneralResponse listProjectOperationApp(Map<String, String> dataMap, Integer pageLimit) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strPage = dataMap.get("page");
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询项目运营管理列表失败");
			return generalResponse;
		}
		// 判断登录信息 end
		if (StringUtil.isEmpty(strPage))
			strPage = "1";
		Integer page = Integer.valueOf(strPage.trim());
		DataGridResult result = operationService.listProjectOperationByPage(page, pageLimit, tbSystemUser.getId(),
				null);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("获得项目运营管理列表成功");
		generalResponse.setObj(result);
		return generalResponse;
	}

	@Override
	public GeneralResponse listSiteProjectApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询某项目的所属站点列表失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断项目信息 start
		TbProject tbProject = this.viewTbProjectDataMap(dataMap);// projectId
		if (tbProject == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-查询某项目的所属站点列表失败");
			return generalResponse;
		}
		// 判断项目信息 end
		// 拼接站点列表
		List<Map<String, String>> siteList = new ArrayList<Map<String, String>>();
		String beginSiteId = tbProject.getBeginSiteId() == null ? "" : tbProject.getBeginSiteId() + "";// 始发站点id
		String beginSiteName = tbProject.getBeginSiteName();// 始发站点
		String endSiteId = tbProject.getEndSiteId() == null ? "" : tbProject.getEndSiteId() + "";// 到站站点id
		String endSiteName = tbProject.getEndSiteName();// 到达站点

		Map<String, String> startMap = new HashMap<String, String>();
		startMap.put("siteId", beginSiteId);
		startMap.put("siteName", beginSiteName);
		Map<String, String> endMap = new HashMap<String, String>();
		endMap.put("siteId", endSiteId);
		endMap.put("siteName", endSiteName);

		switch (tbProject.getTransportType()) {
		case 0: {// 汽运
			break;
		}
		case 1: {// 接取
			siteList.add(startMap);
			break;
		}
		case 2: {// 送达
			siteList.add(endMap);
			break;
		}
		case 3: {// 火运
			siteList.add(startMap);
			siteList.add(endMap);
			break;
		}
		case 4: {// 接取+火运
			siteList.add(startMap);
			siteList.add(endMap);
			break;
		}
		case 5: {// 火运+送达
			siteList.add(startMap);
			siteList.add(endMap);
			break;
		}
		case 6: {// 联运
			siteList.add(startMap);
			siteList.add(endMap);
			break;
		}
		case 7: {// 接取+送达
			siteList.add(startMap);
			siteList.add(endMap);
			break;
		}
		default:
			break;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询某项目的所属站点列表成功");
		generalResponse.setObj(siteList);
		return generalResponse;
	}

	@Override
	public GeneralResponse listFreightYardBySiteIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strSiteId = dataMap.get("siteId");
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-通过站点id查询所有的货场失败");
			return generalResponse;
		}
		// 判断登录信息 end
		LogisticsResult result = LogisticsResult
				.ok(siteManageService.getFreightYardsByTrainStationId(Integer.valueOf(strSiteId)));
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("通过站点id查询所有的货场成功");
		generalResponse.setObj(result.getData());
		return generalResponse;
	}

	@Override
	public GeneralResponse listStockByFreightYardIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strFreightYardId = dataMap.get("freightYardId");
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询某货场下的某项目的货位信息失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断项目信息 start
		TbProject tbProject = this.viewTbProjectDataMap(dataMap);// projectId
		if (tbProject == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-查询某项目的所属站点列表失败");
			return generalResponse;
		}
		// 判断项目信息 end
		if (StringUtil.isEmpty(strFreightYardId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取货场信息-查询某货场下的某项目的货位信息失败");
			return generalResponse;
		}
		LogisticsResult result = checkService.getStockByFreightYardId(tbProject.getId(),
				Integer.valueOf(strFreightYardId));
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询某货场下的某项目的货位信息成功");
		generalResponse.setObj(result.getData());
		return generalResponse;
	}

	@Override
	public GeneralResponse listCargoLocationImgByProjectId(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-通过项目id查询仓位平面图失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断项目信息 start
		TbProject tbProject = this.viewTbProjectDataMap(dataMap);// projectId
		if (tbProject == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-通过项目id查询仓位平面图失败");
			return generalResponse;
		}
		// 判断项目信息 end
		LogisticsResult result = checkService.getCargoLocationImg(tbProject.getId());
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("通过项目id查询仓位平面图成功");
		generalResponse.setObj(result.getData());
		return generalResponse;
	}

	@Override
	public GeneralResponse saveTbProjectDistributionApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strDistributionNum = dataMap.get("distributionNum");// 分配数量
		Integer taskType = Integer.parseInt(dataMap.get("taskType")); // taskType
																		// 1 接取
																		// 2 送达
																		// 3 汽运
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-项目任务分配失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断项目信息 start
		TbProject tbProject = this.viewTbProjectDataMap(dataMap);// projectId
		if (tbProject == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-项目任务分配失败");
			return generalResponse;
		}
		// 判断项目信息 end
		if (StringUtil.isEmpty(strDistributionNum)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-项目任务分配失败");
			return generalResponse;
		}
		Integer distributionNum = Integer.valueOf(strDistributionNum);
		// 判断 -目前分配任务 不得低于 今日已领取任务
		/*
		 * boolean falgs =
		 * publishJobService.IsHigherByTodayNum(tbProject.getId(),
		 * distributionNum,taskType.byteValue()); if(!falgs){
		 * generalResponse.setState(Constants.NO); generalResponse.setObj(null);
		 * generalResponse.setMsg("当前分配的车辆数不得低于已领取任务车辆数-项目任务分配失败"); return
		 * generalResponse; }
		 */
		boolean flag = publishJobService.putDistributeJob(tbProject.getId(), distributionNum, tbSystemUser.getId(),
				taskType.byteValue());
		if (flag != true) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("项目任务分配失败");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("项目任务分配成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse stopTbProjectDistributionApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String projectIds = dataMap.get("projectId");// 项目id 多个用逗号隔开
		String projectStages = dataMap.get("projectStages");// 1-接取 2-送达 多个用逗号隔开
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-暂停项目分配失败");
			return generalResponse;
		}
		// 判断登录信息 end
		if (StringUtil.isEmpty(projectIds)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-暂停项目分配失败");
			return generalResponse;
		}
		String[] arrProjectId = projectIds.split(",");
		String[] arrProjectStage = projectStages.split(",");
		// 遍历 去空字符串
		List<Integer> projectIdList = new ArrayList<Integer>();
		List<Integer> projectStageList = new ArrayList<Integer>();
		for (String strProjectId : arrProjectId) {
			if (StringUtil.isEmpty(strProjectId))
				continue;
			projectIdList.add(Integer.valueOf(strProjectId.trim()));
		}
		for (String strProjectStage : arrProjectStage) {
			if (StringUtil.isEmpty(strProjectStage))
				continue;
			projectStageList.add(Integer.valueOf(strProjectStage.trim()));
		}
		if (projectIdList.size() != projectStageList.size()) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("数据不正确-暂停项目分配失败");
			return generalResponse;
		}
		boolean flag = publishJobService.stopJob(projectIdList, projectStageList);
		if (!flag) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("暂停项目分配失败");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("暂停项目分配成功");
		return generalResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public GeneralResponse listTbProjectForDistributionApp(Map<String, String> dataMap, Integer pageLimit)
			throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strPage = dataMap.get("page");
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询可分配项目列表失败");
			return generalResponse;
		}
		// 判断登录信息 end
		if (StringUtil.isEmpty(strPage))
			strPage = "1";
		Integer page = Integer.valueOf(strPage.trim());
		ProjectDistributionDetail projectDetail = new ProjectDistributionDetail();
		projectDetail.setSysOrgCode(sysOrgCode);
		DataGridResult result = publishJobService.listPublishJobByPage(page, pageLimit, projectDetail);

		/*
		 * List<TbProjectDetail> tbProjectDetailList = result.getRows();
		 * if(tbProjectDetailList != null) { for (TbProjectDetail
		 * tbProjectDetail : tbProjectDetailList) { Integer carNum =
		 * tbProjectDetail.getCarNum() == null ? 0 :
		 * tbProjectDetail.getCarNum(); Integer alreadyRecNum =
		 * tbProjectDetail.getAlreadyRecNum() == null ? 0 :
		 * tbProjectDetail.getAlreadyRecNum(); Integer waitRecNum = carNum -
		 * alreadyRecNum; waitRecNum = waitRecNum < 0 ? 0 : waitRecNum;
		 * tbProjectDetail.setWaitRecNum(waitRecNum);//待领运单 } }
		 */

		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询可分配项目列表成功");
		generalResponse.setObj(result);
		return generalResponse;
	}

	@Override
	public GeneralResponse beginTbProjectDistributionApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String projectIds = dataMap.get("projectId");// 项目id 多个用逗号隔开
		String projectStages = dataMap.get("projectStages");// 1-接取 2-送达 3 汽运
															// 多个用逗号隔开
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-开始项目分配失败");
			return generalResponse;
		}
		// 判断登录信息 end
		if (StringUtil.isEmpty(projectIds)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-开始项目分配失败");
			return generalResponse;
		}
		String[] arrProjectId = projectIds.split(",");
		String[] arrProjectStage = projectStages.split(",");
		// 遍历 去空字符串
		List<Integer> projectIdList = new ArrayList<Integer>();
		List<Integer> projectStageList = new ArrayList<Integer>();
		for (String strProjectId : arrProjectId) {
			if (StringUtil.isEmpty(strProjectId))
				continue;
			projectIdList.add(Integer.valueOf(strProjectId.trim()));
		}
		for (String strProjectStage : arrProjectStage) {
			if (StringUtil.isEmpty(strProjectStage))
				continue;
			projectStageList.add(Integer.valueOf(strProjectStage.trim()));
		}
		if (projectIdList.size() != projectStageList.size()) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("数据不正确-开始项目分配失败");
			return generalResponse;
		}
		boolean flag = publishJobService.beginJob(projectIdList, projectStageList);
		if (!flag) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("开始项目分配失败");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("开始项目分配成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse changeStockApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strStockId = dataMap.get("stockId");// 货位id
		String strAdjustQty = dataMap.get("adjustQty");// 调整后的库存
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-保存调整后的库存信息失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strStockId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到货位信息-保存调整后的库存信息失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strAdjustQty)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到调整后库存-保存调整后的库存信息失败");
			return generalResponse;
		}
		Integer stockId = Integer.valueOf(strStockId.trim());
		BigDecimal adjustQty = new BigDecimal(strAdjustQty.trim());
		List<TbStock> tbStockList = new ArrayList<TbStock>();
		TbStock tbStock = new TbStock();
		tbStock.setId(stockId);
		tbStock.setAdjustQty(adjustQty);
		tbStockList.add(tbStock);
		checkService.updateStock(tbStockList, tbSystemUser);
		// 判断登录信息 end
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("保存调整后的库存信息成功");
		return generalResponse;
	}

}
