package com.shenhesoft.logistics.enterprise.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderCargoByBulkDetail;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderCargoPlaceDetail;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderSearch;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderCargoPalceMapper;
import com.shenhesoft.logistics.business.pojo.historyLocation.TbHistoryLocation;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;
import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalce;
import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalceExample;
import com.shenhesoft.logistics.business.project.manage.ProjectManagmentService;
import com.shenhesoft.logistics.business.shortbarge.order.ShortBargeOrderService;
import com.shenhesoft.logistics.business.trainOrder.TrainOrderService;
import com.shenhesoft.logistics.common.GeneralResponse;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.ImageUtil;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.enterprise.service.AppTbTrainOrderService;
import com.shenhesoft.logistics.finance.AdvanceCharge;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.helpPojo.ProjectAppHelp;
import com.shenhesoft.logistics.manage.interfaces.SiteManageService;
import com.shenhesoft.logistics.manage.interfaces.TrainTypeService;
import com.shenhesoft.logistics.manage.mapper.TbContainerMapper;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocation;
import com.shenhesoft.logistics.manage.pojo.box.TbContainer;
import com.shenhesoft.logistics.manage.pojo.box.TbContainerExample;
import com.shenhesoft.logistics.manage.pojo.box.TbContainerExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.pojo.trianType.TbTrainType;
import com.shenhesoft.logistics.manage.user.UserService;
import com.shenhesoft.logistics.system.BasicDataService;

/**
 * 火运app service 实现
 * 
 * @author dusd
 * @date 2017年12月27日
 */
@Service
public class AppTbTrainOrderServiceImpl implements AppTbTrainOrderService {

	/**
	 * 火运订单 servcie
	 */
	@Autowired
	private TrainOrderService trainOrderService;
	/**
	 * 企业用户 service 接口
	 */
	@Autowired
	private UserService userService;
	/**
	 * 短驳 service
	 */
	@Autowired
	private ShortBargeOrderService shortBargeService;
	/**
	 * 网点分支 service
	 */
	@Autowired
	private BranchGroupService branchGroupService;
	/**
	 * 货场管理 service
	 */
	@Autowired
	private SiteManageService siteManageService;
	/**
	 * 火车车型 serice 接口
	 */
	@Autowired
	private TrainTypeService trainTypeService;
	/**
	 * 装车信息 mapper
	 */
	@Autowired
	private TbTrainOrderCargoPalceMapper tbTrainOrderCargoPalceMapper;
	/**
	 * 项目管理 service
	 */
	@Autowired
	private ProjectManagmentService projectManagmentService;
	/**
	 * 站点信息 mapper
	 */
	@Autowired
	private TbTrainStationMapper tbTrainStationMapper;

	@Autowired
	private TbProjectMapper tbProjectMapper;
	
	@Autowired
	private TbContainerMapper containerMapper;
	
	@Autowired
	private BasicDataService basicDataService;
	/**
	 * 通过火运运单id查询火运运单信息
	 * 
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 */
	private TbTrainOrder viewTbTrainOrderDataMap(Map<String, String> dataMap) {
		if (dataMap == null)
			return null;
		String strTrainOrderId = dataMap.get("trainOrderId");
		if (StringUtil.isEmpty(strTrainOrderId)) {
			return null;
		}
		Integer trainOrderId = Integer.valueOf(strTrainOrderId.trim());
		return trainOrderService.selectTrainOrderById(trainOrderId);
	}

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

	@Override
	public GeneralResponse listAllTbTrainOrderDifferentStatus(Map<String, String> dataMap, Integer pageLimit) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		
		String strPage = dataMap.get("page");
		String strStatus = dataMap.get("status");// 运单状态
		String sysOrgCode = dataMap.get("sysOrgCode");//
		
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询火运运单列表失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		
		if (StringUtil.isEmpty(strPage))
			strPage = "1";
		Integer page = Integer.valueOf(strPage.trim());

		List<Byte> statusList = new ArrayList<Byte>();
		if (!StringUtil.isEmpty(strStatus)) {
			String[] arrStatus = strStatus.split(",");
			for (String tmpStatus : arrStatus) {
				if (StringUtil.isEmpty(tmpStatus))
					continue;
				statusList.add(Byte.valueOf(tmpStatus.trim()));
			}
		}
		TrainOrderSearch trainOrderSearch = new TrainOrderSearch();
		trainOrderSearch.setStatusList(statusList);
		DataGridResult result = trainOrderService.selectTrainOrderByPage(page, pageLimit, (byte) 3, tbSystemUser,
				trainOrderSearch);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("获得火运订单列表成功");
		generalResponse.setObj(result);
		return generalResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public GeneralResponse viewTbTrainOrderById(Map<String, String> dataMap) throws Exception {
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
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到火运运单信息-查询火运运单信息失败");
			return generalResponse;
		}
		// 判断火运运单信息 end

		GeneralResponse tbTrainOrderCargoPalceGeneralResponse = this
				.listTbTrainOrderCargoPalceByTrainOrderIdApp(dataMap);
		if (tbTrainOrderCargoPalceGeneralResponse.getState() == Constants.YES) {
			tbTrainOrder
					.setTrainCargoList((List<TbTrainOrderCargoPalce>) tbTrainOrderCargoPalceGeneralResponse.getObj());
		}

		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询火运运单信息成功");
		generalResponse.setObj(tbTrainOrder);
		return generalResponse;
	}

	@Override
	public GeneralResponse listHistoryLocationTbTrainOrder(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询某订单位置信息列表失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到火运运单信息-查询某订单位置信息列表失败");
			return generalResponse;
		}
		// 判断火运运单信息 end
		List<TbHistoryLocation> historyLocation = trainOrderService.selectHistoryLocationById(tbTrainOrder.getId(),
				tbTrainOrder.getType());
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询某订单位置信息列表成功");
		generalResponse.setObj(historyLocation);
		return generalResponse;
	}

	@Override
	public GeneralResponse saveLocationTbTrainOrderApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String location = dataMap.get("location");// 位置信息
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-保存某订单位置信息失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到火运运单信息-保存某订单位置信息失败");
			return generalResponse;
		}
		// 判断火运运单信息 end
		if (StringUtil.isEmpty(location)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("位置信息不能为空-保存某订单位置信息失败");
			return generalResponse;
		}
		int row = trainOrderService.insertNewLocation(tbTrainOrder.getId(), location.trim());
		if (row != 1) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("保存某订单位置信息失败");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("保存某订单位置信息成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse listTrainCargoApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询车辆列表失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到火运运单信息-查询车辆列表失败");
			return generalResponse;
		}
		// 判断火运运单信息 end
		List<TbTrainOrderCargoPalce> trainCargoList = trainOrderService.selectTrainCargoByOrdeId(tbTrainOrder.getId());
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询车辆列表成功");
		generalResponse.setObj(trainCargoList);
		return generalResponse;
	}

	@Override
	public GeneralResponse updateTrainOrderStatusByParamApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strStatus = dataMap.get("status");// 状态位 4-等待发运 5-在途运载 10-等待回单
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-更新运单状态失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		
		// 判断登录信息 end
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到运单信息-更新运单状态失败");
			return generalResponse;
		}
		// 判断火运运单信息 end
		if (StringUtil.isEmpty(strStatus)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未判断当前运单状态-更新运单状态失败");
			return generalResponse;
		}
		int row = trainOrderService.updOrderStatusByParam(tbTrainOrder.getId(), Byte.valueOf(strStatus),
				tbSystemUser);
		if (row != 1) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("更新运单状态失败");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("更新运单状态成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse saveWaybillReceiptTrainOrderApp(Map<String, String> dataMap, HttpSession session)
			throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String trainOrderCargoPalceId = dataMap.get("trainOrderCargoPalceId");// 车辆火运中间表主键
		String deliverGoodsImg = dataMap.get("deliverGoodsImg");// 图片 发货运单
		String arrivalImg = dataMap.get("arrivalImg");// 图片 到货运单
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-保存发货运单和到货运单信息失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到运单信息-保存发货运单和到货运单信息失败");
			return generalResponse;
		}
		/*if (StringUtil.isEmpty(deliverGoodsImg) && StringUtil.isEmpty(arrivalImg)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("发货运单图片和到货运单图片都为空-保存发货运单和到货运单信息失败");
			return generalResponse;
		}*/
		// 判断火运运单信息 end
		if (!StringUtil.isEmpty(deliverGoodsImg)) {// 发货运单
			LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo", deliverGoodsImg.trim(), null);
			if (base64UpLoad.getStatus() == 200) {
				deliverGoodsImg = base64UpLoad.getData().toString();
			} else {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("发货运单图片上传失败-保存发货运单和到货运单信息失败");
				return generalResponse;
			}
			trainOrderService.deleteSendImgById(Integer.valueOf(trainOrderCargoPalceId));
			int row = trainOrderService.updateSendImgById(Integer.valueOf(trainOrderCargoPalceId), deliverGoodsImg);
			if (row != 1) {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("保存发货运单信息失败");
				return generalResponse;
			}
		}
		if (!StringUtil.isEmpty(arrivalImg)) {// 到货运单
			LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo", arrivalImg.trim(), null);
			if (base64UpLoad.getStatus() == 200) {
				arrivalImg = base64UpLoad.getData().toString();
			} else {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("到货运单图片上传失败-保存发货运单和到货运单信息失败");
				return generalResponse;
			}
			trainOrderService.deleteArriveImg(Integer.valueOf(trainOrderCargoPalceId));
			int row = trainOrderService.updateArriveImgById(Integer.valueOf(trainOrderCargoPalceId), arrivalImg);
			if (row != 1) {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("保存到货运单信息失败");
				return generalResponse;
			}
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("保存发货运单和到货运单信息成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse listTbFreightYardByTrainOrderIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String type = dataMap.get("type");
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-通过火运运单id查询货场列表失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到运单信息-通过火运运单id查询货场列表失败");
			return generalResponse;
		}
		// 判断火运运单信息 end
		// 根据运单始发的中心站点查询所包含的货场货位
		TbProject tp = shortBargeService.selectDetailProject(tbTrainOrder.getProjectId());
		Integer siteId = null;
		if (type.trim().equals("0")) {// 装车
			siteId = tp.getBeginSiteId();
		} else {// 到货
			siteId = tp.getEndSiteId();
		}
		List<TbFreightYard> freightYardList = siteManageService.getFreightYardsByTrainStationId(siteId);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("通过火运运单id查询货场列表成功");
		generalResponse.setObj(freightYardList);
		return generalResponse;
	}

	@Override
	public GeneralResponse listTbCargoLocationByYardIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strYardId = dataMap.get("yardId");// 货场id
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-通过货场id查询货位列表失败");
			return generalResponse;
		}
		// 判断登录信息 end
		if (StringUtil.isEmpty(strYardId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到货场信息-通过货场id查询货位列表失败");
			return generalResponse;
		}
		List<TbCargoLocation> tbCargoLocationList = siteManageService
				.getAllcargoLocationsByYardId(Integer.valueOf(strYardId));
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("通过货场id查询货位列表成功");
		generalResponse.setObj(tbCargoLocationList);
		return generalResponse;
	}

	@Override
	public GeneralResponse listTbTrainTypeApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询火车车型列表失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		
		// 判断登录信息 end
		List<TbTrainType> tbTrainTypeList = trainTypeService.selectTrainTypeByPage(sysOrgCode);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询火车车型列表成功");
		generalResponse.setObj(tbTrainTypeList);
		return generalResponse;
	}

	@Override
	public GeneralResponse listTbTrainOrderCargoPalceByTrainOrderIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询运单装车信息列表失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到火运运单信息-查询运单装车信息列表失败");
			return generalResponse;
		}
		// 判断火运运单信息 end
		List<TbTrainOrderCargoPalce> trainCargoList = trainOrderService.selectTrainCargoByOrdeId(tbTrainOrder.getId());
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询运单装车信息列表成功");
		generalResponse.setObj(trainCargoList);
		return generalResponse;
	}

	@Override
	public GeneralResponse saveTbTraninOrderTruckLoadApp(Map<String, String> dataMap, HttpSession session)
			throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String entruckInfoJson = dataMap.get("entruckInfoJson");// 货场货位装车信息json
		String entruckNumbe  = dataMap.get("entruckNumbe");// 装车数
		String containerNums = dataMap.get("containerNums");// 集装箱数
		String entruckWeight = dataMap.get("entruckWeight");// 装车载重
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-保存运单装车信息失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		// 判断登录信息 end
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到火运运单信息-保存运单装车信息失败");
			return generalResponse;
		}
		// 判断火运运单信息 end
		if (StringUtil.isEmpty(entruckInfoJson)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到装车信息-保存运单装车信息失败");
			return generalResponse;
		}
		LogisticsResult row = null;
		// 0:集装箱 1:散装箱
		if (tbTrainOrder.getProjectType() == 0) {
			TrainOrderCargoPlaceDetail trainOrderCargoPlaceDetail = new TrainOrderCargoPlaceDetail();
			trainOrderCargoPlaceDetail.setOrderId(tbTrainOrder.getId());
			trainOrderCargoPlaceDetail.setHidenProjectId(tbTrainOrder.getProjectId());
			trainOrderCargoPlaceDetail.setEntruckNumbe(Integer.valueOf(entruckNumbe));
			trainOrderCargoPlaceDetail.setContainerNums(Integer.valueOf(containerNums));
			trainOrderCargoPlaceDetail.setEntruckWeight(new BigDecimal(entruckWeight));
			trainOrderCargoPlaceDetail.setEntruckInfoJson(entruckInfoJson);
			row = trainOrderService.addWaitEntruck(trainOrderCargoPlaceDetail, tbSystemUser, session);
		} else {
			TrainOrderCargoByBulkDetail trainOrderCargoByBulkDetail = new TrainOrderCargoByBulkDetail();
			trainOrderCargoByBulkDetail.setOrderId(tbTrainOrder.getId());
			trainOrderCargoByBulkDetail.setHidenProjectId(tbTrainOrder.getProjectId());
			trainOrderCargoByBulkDetail.setEntruckNumbe(Integer.valueOf(entruckNumbe));
			trainOrderCargoByBulkDetail.setEntruckWeight(new BigDecimal(entruckWeight));
			trainOrderCargoByBulkDetail.setEntruckInfoJson(entruckInfoJson);
			row = trainOrderService.addWaitEntruckOfBulk(trainOrderCargoByBulkDetail, tbSystemUser, session);
		}

		if (row.getStatus() != 200) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg(row.getMsg());
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("保存运单装车信息成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse listTbTrainOrderCargoPalceByTrainOrderIdCarTypeId(Map<String, String> dataMap)
			throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strCarTypeId = dataMap.get("carTypeId");// 车型id
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-通过运单和车型查询所有的车号信息失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到火运运单信息-通过运单和车型查询所有的车号信息失败");
			return generalResponse;
		}
		// 判断火运运单信息 end
		if (StringUtil.isEmpty(strCarTypeId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到车型信息-通过运单和车型查询所有的车号信息失败");
			return generalResponse;
		}
		Integer carTypeId = Integer.valueOf(strCarTypeId);
		TbTrainOrderCargoPalceExample paramExample = new TbTrainOrderCargoPalceExample();
		TbTrainOrderCargoPalceExample.Criteria criteria = paramExample.createCriteria();
		criteria.andTrainOrderIdEqualTo(tbTrainOrder.getId());
		criteria.andCarTypeIdEqualTo(carTypeId);
		List<TbTrainOrderCargoPalce> trainCargoList = tbTrainOrderCargoPalceMapper.selectByExample(paramExample);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("通过运单和车型查询所有的车号信息成功");
		generalResponse.setObj(trainCargoList);
		return generalResponse;
	}

	@Override
	public GeneralResponse viewTbTrainOrderCargoPalceById(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strOrderCargoPalceId = dataMap.get("orderCargoPalceId");// 装车信息主键
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-通过装车信息主键查询详细装车信息失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到火运运单信息-通过装车信息主键查询详细装车信息失败");
			return generalResponse;
		}
		// 判断火运运单信息 end
		if (StringUtil.isEmpty(strOrderCargoPalceId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到装车信息-通过装车信息主键查询详细装车信息失败");
			return generalResponse;
		}
		Integer orderCargoPalceId = Integer.valueOf(strOrderCargoPalceId);
		TbTrainOrderCargoPalce tbTrainOrderCargoPalce = tbTrainOrderCargoPalceMapper
				.selectByPrimaryKey(orderCargoPalceId);
		if (tbTrainOrderCargoPalce == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到装车信息-通过装车信息主键查询详细装车信息失败");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("通过装车信息主键查询详细装车信息成功");
		generalResponse.setObj(tbTrainOrderCargoPalce);
		return generalResponse;
	}

	@Override
	public GeneralResponse saveTbTrainOrderArrivalApp(Map<String, String> dataMap, HttpSession session)
			throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-保存到货信息失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		// 判断登录信息 end
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到火运运单信息-保存到货信息失败");
			return generalResponse;
		}
		String type = dataMap.get("type");
		if (Integer.parseInt(type) == 0) {
			// type=0 只保存运单信息，不跟新状态
			String arrivalInfoJson = dataMap.get("arrivalInfoJson");// 到货详单信息
			// 判断火运运单信息 end
			if (StringUtil.isEmpty(arrivalInfoJson)) {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("未获取到到货信息-保存到货信息失败");
				return generalResponse;
			}
			// 保存到货信息
			List<TbTrainOrderCargoPalce> cargoPlaceList = JsonUtils.jsonToList(arrivalInfoJson,
					TbTrainOrderCargoPalce.class);
			if (cargoPlaceList == null || cargoPlaceList.size() == 0) {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("未获取到到货信息-保存到货信息失败");
				return generalResponse;
			}
			/*
			 * for (TbTrainOrderCargoPalce tbTrainOrderCargoPalce :
			 * cargoPlaceList) {
			 * trainOrderService.updateUnloadWeight(tbTrainOrderCargoPalce); }
			 */
			// 0:集装箱 1:散装箱
			if (tbTrainOrder.getProjectType() == 0) {
				int r = trainOrderService.unloadInfoByList(cargoPlaceList, tbTrainOrder.getProjectId(),tbSystemUser, session);
				if (r != cargoPlaceList.size()) {
					generalResponse.setState(Constants.NO);
					generalResponse.setObj(null);
					generalResponse.setMsg("保存到货信息失败");
					return generalResponse;
				}
			} else {
				int r = trainOrderService.unloadInfoByListOfBulk(cargoPlaceList, tbTrainOrder.getProjectId(), session);
				if (r != cargoPlaceList.size()) {
					generalResponse.setState(Constants.NO);
					generalResponse.setObj(null);
					generalResponse.setMsg("保存到货信息失败");
					return generalResponse;
				}
			}
		} else {
			// 到货更新运单状态
			int row = trainOrderService.updOrderStatusByParam(tbTrainOrder.getId(), (byte) 6, tbSystemUser);
			if (row != 1) {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("更新运单状态失败");
				return generalResponse;
			}
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("保存到货信息成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse viewTbTrainOrderRepertoryByProjectIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strProjectId = dataMap.get("projectId");// 项目id
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-通过项目信息计算库存数量失败");
			return generalResponse;
		}
		// 判断登录信息 end
		if (StringUtil.isEmpty(strProjectId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-通过项目信息计算库存数量失败");
			return generalResponse;
		}
		Integer projectId = Integer.valueOf(strProjectId.trim());
		LogisticsResult logisticsResult = projectManagmentService.selectProject(projectId);
		ProjectDetail projectDetail = (ProjectDetail) logisticsResult.getData();
		if (projectDetail == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-通过项目信息计算库存数量失败");
			return generalResponse;
		}
		List<TbStock> stockList = trainOrderService.selectStockList(projectDetail.getId(),
				projectDetail.getBeginSiteId());
		BigDecimal currentQty = new BigDecimal(0);
		TbStock stock = new TbStock();
		if (stockList != null && stockList.size() > 0) {
			if (stockList.size() > 1) {
				for (TbStock tbStock : stockList) {
					currentQty = currentQty.add(tbStock.getCurrentQty());
					stock.setCurrentQty(currentQty);
					stock.setCargoLocationName("多货");
					stock.setFreightYardName("多位");
				}
			} else {
				currentQty = stockList.get(0).getCurrentQty();
				stock.setCurrentQty(currentQty);
				stock.setCargoLocationName(stockList.get(0).getCargoLocationName());
				stock.setFreightYardName(stockList.get(0).getFreightYardName());
			}
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("通过项目信息计算库存数量成功");
		generalResponse.setObj(stock);
		return generalResponse;
	}

	@Override
	public GeneralResponse viewPredictMoneyByProjectIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strProjectId = dataMap.get("projectId");// 项目id
		String strPleaseTrainNum = dataMap.get("pleaseTrainNum");// 请车数
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-计算预计支出金额失败");
			return generalResponse;
		}
		// 判断登录信息 end
		if (StringUtil.isEmpty(strProjectId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-计算预计支出金额失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strPleaseTrainNum)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到请车数-计算预计支出金额失败");
			return generalResponse;
		}
		Integer projectId = Integer.valueOf(strProjectId.trim());
		LogisticsResult logisticsResult = projectManagmentService.selectProject(projectId);
		ProjectDetail projectDetail = (ProjectDetail) logisticsResult.getData();
		if (projectDetail == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-计算预计支出金额失败");
			return generalResponse;
		}

		Integer pleaseTrainNum = Integer.valueOf(strPleaseTrainNum);// 请车数
		BigDecimal freightSum = projectDetail.getFreightSum();// 运费合计
		if (freightSum == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("运费合计为空-计算预计支出金额失败");
			return generalResponse;
		}
		// 运费合计
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("通过项目信息计算库存数量成功");
		generalResponse.setObj(freightSum.multiply(new BigDecimal(pleaseTrainNum)));
		return generalResponse;
	}

	@Override
	public GeneralResponse saveTbTrainOrderApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strProjectId = dataMap.get("projectId");// 项目id
		String strPleaseTrainNum = dataMap.get("pleaseTrainNum");// 请车数
		String strPleaseCarTypeId = dataMap.get("pleaseCarTypeId");// 请车类型id
		String estimateWeight = dataMap.get("estimateWeight");// 预计载重
		String estimateCost = dataMap.get("estimateCost");// 预计支出金额
		//String receiveAccountId = dataMap.get("receiveAccountId");// 预付款账户id
		String receiveAccountName = dataMap.get("receiveAccountName");// 预付款账户名字
		String depositAmount = dataMap.get("depositAmount");// 账户余额
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-保存新建请车信息失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		// 判断登录信息 end
		if (StringUtil.isEmpty(strProjectId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-保存新建请车信息失败");
			return generalResponse;
		}
		Integer projectId = Integer.valueOf(strProjectId.trim());
		LogisticsResult logisticsResult = projectManagmentService.selectProject(projectId);
		ProjectDetail projectDetail = (ProjectDetail) logisticsResult.getData();
		if (projectDetail == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到项目信息-保存新建请车信息失败");
			return generalResponse;
		}
		// 站点信息
		// TbTrainStation tbTrainStation =
		// tbTrainStationMapper.selectByPrimaryKey(projectDetail.getBeginCenterSiteId());
		TbTrainStation tbTrainStation = trainOrderService.selectTrainStationById(projectDetail.getBeginCenterSiteId());

		TbTrainOrder tbTrainOrder = new TbTrainOrder();
		tbTrainOrder.setProjectId(projectId);// 项目id
		tbTrainOrder.setProjectCode(projectDetail.getProjectCode());// 项目编号
		tbTrainOrder.setProjectType(projectDetail.getProjectType()); // 项目类型
		tbTrainOrder.setBranchId(projectDetail.getBranchGroupId());// 分支id
		tbTrainOrder.setBranchName(projectDetail.getBranchGroupName());// 分支机构
		tbTrainOrder.setBeginSite(projectDetail.getBeginSiteName());// 始发站点
		tbTrainOrder.setBeginPlace(projectDetail.getBeginAddress());// 始发地
		tbTrainOrder.setEndSite(projectDetail.getEndSiteName());// 到达站点
		tbTrainOrder.setEndPlace(projectDetail.getEndAddress());// 运抵地
		tbTrainOrder.setPleaseCarNum(strPleaseTrainNum);// 请车数量
		tbTrainOrder.setPleaseCarTypeId(Integer.valueOf(strPleaseCarTypeId));// 请车类型id
		tbTrainOrder.setEstimateWeight(estimateWeight);// 预计载重
		tbTrainOrder.setEstimateCost(estimateCost);// 预计费用
		tbTrainOrder.setCargoName(projectDetail.getCargoName());// 货物品名
		tbTrainOrder.setCargoSpecifications(projectDetail.getCargoSpecifications());// 规格
		tbTrainOrder.setAdvanceChargeAccount(receiveAccountName);//
		tbTrainOrder.setAdvanceCharge(new BigDecimal(depositAmount));// 预付款金额
		int row = trainOrderService.addTrainOrder(tbTrainOrder,tbSystemUser);// 保存运单
		if (row != 1) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("保存新建请车信息失败");
			return generalResponse;
		}

		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("保存新建请车信息成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse saveTbTrainOrderAdmitCarNum(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strSureNum = dataMap.get("sureNum");// 承认车数
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-保存承运车数失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断火运运单信息 start
		TbTrainOrder tbTrainOrder = this.viewTbTrainOrderDataMap(dataMap);// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到火运运单信息-保存承运车数失败");
			return generalResponse;
		}
		// 判断火运运单信息 end
		if (StringUtil.isEmpty(strSureNum)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到承认车数-保存承运车数失败");
			return generalResponse;
		}
		Integer sureNum = Integer.valueOf(strSureNum);
		// 承认车数小于0
		if (sureNum.intValue() < 0) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("承认车数不能小于0-保存承运车数失败");
			return generalResponse;
		}
		// 承认车数大于请车数
		if (sureNum.intValue() > Integer.valueOf(tbTrainOrder.getPleaseCarNum()).intValue()) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("承认车数大于请车数-保存承运车数失败");
			return generalResponse;
		}
		if (sureNum.equals(0)) {// 承认车数是0 自动放入回收站
			trainOrderService.deleteTrainOrderById(tbTrainOrder.getId(), tbSystemUser.getName(), sureNum);
			generalResponse.setState(Constants.YES);
			generalResponse.setMsg("承认车数是0,自动放入回收站");
			return generalResponse;
		}
		int row = trainOrderService.updateTrainOrderById(tbTrainOrder.getId(), tbSystemUser.getName(), sureNum);
		if (row != 1) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("保存承运车数失败");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("保存承运车数成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse trainListTbProjectApp(Map<String, String> dataMap) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询项目列表失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("未获取到当前登录人信息-查询项目列表失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		List<ProjectAppHelp> projectResult = trainOrderService.selectAppAllProject(userId,sysOrgCode);
		for (ProjectAppHelp project : projectResult) {
			List<TbStock> stockList = trainOrderService.selectStockList(project.getId(), project.getBeginSiteId());
			BigDecimal currentQty = new BigDecimal(0);
			if (stockList != null && stockList.size() > 0) {
				if (stockList.size() > 1) {
					for (TbStock tbStock : stockList) {
						currentQty = currentQty.add(tbStock.getCurrentQty());
						project.setCurrentQty(currentQty);
						project.setCargoLocationName("多货");
						project.setFreightYardName("多位");
					}
				} else {
					currentQty = stockList.get(0).getCurrentQty();
					project.setCurrentQty(currentQty);
					project.setCargoLocationName(stockList.get(0).getCargoLocationName());
					project.setFreightYardName(stockList.get(0).getFreightYardName());
				}
			}
			AdvanceCharge advance = trainOrderService.selectAccountListById(project.getId(),project.getBeginCenterSiteId());
			project.setAdvanceCharge(advance);
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询项目列表成功");
		generalResponse.setObj(projectResult);
		return generalResponse;
	}

	@Override
	public GeneralResponse trainAppContainerList(Map<String, String> dataMap) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");
		String trainOrderId = dataMap.get("trainOrderId");
		String text = dataMap.get("text");
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询集装箱号列表失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("未获取到当前登录人信息-查询集装箱号列表失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		if (StringUtil.isEmpty(trainOrderId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到火运运单信息-查询集装箱号列表失败");
			return generalResponse;
		}
		TbTrainOrder tbTrainOrder = trainOrderService.selectTrainOrderById(Integer.valueOf(trainOrderId.trim()));// trainOrderId
		if (tbTrainOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到火运运单信息-查询集装箱号列表失败");
			return generalResponse;
		}
		TbProject tp = tbProjectMapper.selectDetailProjectById(tbTrainOrder.getProjectId());
		String appId = tp.getId()+","+tp.getBeginSiteId()+","+3+","+sysOrgCode+","+0;
		/*TbContainerExample example= new TbContainerExample();
		Criteria containerCriteria = example.createCriteria();
		containerCriteria.andTrainLocationIdEqualTo(tp.getBeginSiteId());
		List<TbContainer> list = containerMapper.selectByExample(example);*/
		List<Map<String, Object>> list = basicDataService.getSelectionsByStationId("testType", text.trim(), appId);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询集装箱号成功");
		generalResponse.setObj(list);
		return generalResponse;
	}

}
