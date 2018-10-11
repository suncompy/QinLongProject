package com.shenhesoft.logistics.enterprise.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.app.PushUtil;
import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.business.helpPojo.TbBulkSubForwardDetail;
import com.shenhesoft.logistics.business.helpPojo.TbFreightLocationDetail;
import com.shenhesoft.logistics.business.helpPojo.TbOrderCarDetail;
import com.shenhesoft.logistics.business.helpPojo.TbReceipterDetail;
import com.shenhesoft.logistics.business.helpPojo.TbSubForwardDetail;
import com.shenhesoft.logistics.business.helpPojo.TbWaitDispatchDetail;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.mapper.TbProjectDistributionMapper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderMapper;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrderExample;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;
import com.shenhesoft.logistics.business.project.manage.ProjectManagmentService;
import com.shenhesoft.logistics.business.shortbarge.order.ShortBargeOrderService;
import com.shenhesoft.logistics.business.shortbarge.publish.PublishJobService;
import com.shenhesoft.logistics.common.GeneralResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.ImageUtil;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.enterprise.service.AppTbOrderService;
import com.shenhesoft.logistics.enterprise.service.AppTbTrainOrderService;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.ProjectAppHelp;
import com.shenhesoft.logistics.manage.helpPojo.TbFreightYardDetail;
import com.shenhesoft.logistics.manage.interfaces.SiteManageService;
import com.shenhesoft.logistics.manage.interfaces.TransportManagerService;
import com.shenhesoft.logistics.manage.mapper.TbAnchoredCompanyMapper;
import com.shenhesoft.logistics.manage.mapper.TbAnchoredRecordMapper;
import com.shenhesoft.logistics.manage.mapper.TbCarTeamMapper;
import com.shenhesoft.logistics.manage.mapper.TbCargoLocationMapper;
import com.shenhesoft.logistics.manage.mapper.TbContainerMapper;
import com.shenhesoft.logistics.manage.mapper.TbCustomerMapper;
import com.shenhesoft.logistics.manage.mapper.TbExceptionMsgMapper;
import com.shenhesoft.logistics.manage.mapper.TbFreightYardMapper;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocation;
import com.shenhesoft.logistics.manage.pojo.box.TbContainer;
import com.shenhesoft.logistics.manage.pojo.box.TbContainerExample;
import com.shenhesoft.logistics.manage.pojo.box.TbContainerExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.carTeam.TbCarTeam;
import com.shenhesoft.logistics.manage.pojo.exception.TbExceptionMsg;
import com.shenhesoft.logistics.manage.pojo.exception.TbExceptionMsgExample;
import com.shenhesoft.logistics.manage.pojo.notice.TbNotice;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.distribution.TbProjectDistribution;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.search.OrderSearch;
import com.shenhesoft.logistics.manage.user.UserService;
import com.shenhesoft.logistics.system.BasicDataService;

/**
 * app 短驳运单 service 实现
 * 
 * @author dusd
 * @date 2017年12月24日
 */
@Service
public class AppTbOrderServiceImpl implements AppTbOrderService {

	/**
	 * 短驳订单 service
	 */
	@Autowired
	private ShortBargeOrderService shortBargeService;
	/**
	 * 企业用户 service 接口
	 */
	@Autowired
	private UserService userService;
	/**
	 * 货场货位管理service
	 */
	@Autowired
	private SiteManageService siteManageService;
	/**
	 * 项目管理 service
	 */
	@Autowired
	private ProjectManagmentService projectManagmentService;
	/**
	 * 网点分支管理 service
	 */
	@Autowired
	private BranchGroupService branchGroupService;
	/**
	 * 集装箱 mapper
	 */
	@Autowired
	private TbContainerMapper tbContainerMapper;
	/**
	 * 短驳运单 mapper
	 */
	@Autowired
	private TbOrderMapper tbOrderMapper;

	/**
	 * 项目 mapper
	 */
	@Autowired
	private TbProjectMapper tbProjectMapper;
	/**
	 * 运单异常原因 mapper
	 */
	@Autowired
	private TbExceptionMsgMapper tbExceptionMsgMapper;
	/**
	 * 火运app service 接口
	 */
	@Autowired
	private AppTbTrainOrderService appTbTrainOrderService;
	/**
	 * 火运 mapper
	 */
	@Autowired
	private TbTrainOrderMapper tbTrainOrderMapper;
	/**
	 * 车队 mapper
	 */
	@Autowired
	private TbCarTeamMapper tbCarTeamMapper;
	/**
	 * 挂靠公司 mapper
	 */
	@Autowired
	private TbAnchoredCompanyMapper tbAnchoredCompanyMapper;

	/**
	 * 货场mapper
	 */
	@Autowired
	private TbFreightYardMapper freightYardMapper;

	/**
	 * 货位mapper
	 */
	@Autowired
	private TbCargoLocationMapper cargoLocationMapper;

	/**
	 * 任务Mapper
	 */
	@Autowired
	private TbProjectDistributionMapper distributionMapper;

	@Autowired
	private TbContainerMapper containerMapper;
	
	@Autowired
	private TbCustomerMapper customerMapper;
	
	@Autowired
	private TbTrainStationMapper stationMapper;
	
	@Autowired
	private PublishJobService publishJobService;
	
	@Autowired
	private BasicDataService basicDataService;
	
	@Autowired
	private TransportManagerService contService;
	
	@Autowired
	private TbAnchoredRecordMapper anchoredRecordMapper;
	
	/**
	 * 通过短驳运单id查询短驳运单信息
	 * 
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 */
	private TbOrder viewTbOrderDataMap(Map<String, String> dataMap) {
		if (dataMap == null)
			return null;
		String strOrderId = dataMap.get("orderId");
		if (StringUtil.isEmpty(strOrderId)) {
			return null;
		}
		Integer orderId = Integer.valueOf(strOrderId.trim());
		return tbOrderMapper.selectByPrimaryKey(orderId);
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
	public GeneralResponse listTbOrderWaitDispatchApp(Map<String, String> dataMap, Integer pageLimit) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");
		String strPage = dataMap.get("page");
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-获得等待调度列表失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		if (StringUtil.isEmpty(strPage))
			strPage = "1";
		Integer page = Integer.valueOf(strPage.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-获得等待调度列表失败");
			return generalResponse;
		}
		List<Byte> statusList = new ArrayList<Byte>();
		statusList.add(Byte.valueOf("1"));
		OrderSearch orderSearch = new OrderSearch();
		orderSearch.setStatusList(statusList);
		DataGridResult result = shortBargeService.selectBoxManagerOrderByPages(page, pageLimit, (byte) 1, orderSearch,
				tbSystemUser, (byte) 3);// 3 代表不区分集装箱 散装箱
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("获得等待调度列表成功");
		generalResponse.setObj(result);
		return generalResponse;
	}

	@Override
	public GeneralResponse approvalTbOrderApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strOrderId = dataMap.get("orderId");// 运单号可以有多个 用逗号隔开
		// String strContainerNumber1Id = dataMap.get("containerNumber1Id");//
		// 第一个集装箱id
		String containerNumber1 = dataMap.get("containerNumber1");// 第一个集装箱名称
		// String strContainerNumber2Id = dataMap.get("containerNumber2Id");//
		// 第二个集装箱id
		String containerNumber2 = dataMap.get("containerNumber2");// 第二个集装箱名称
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-调度失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-调度失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		if (StringUtil.isEmpty(strOrderId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单号-调度失败");
			return generalResponse;
		}
		
		TbOrder order2 = shortBargeService.getDispatchMsgByProjectId(Integer.valueOf(strOrderId.trim()));
		if(order2.getProjectType() == 0) {
			if((containerNumber1 != "") && (containerNumber2 != "")) {
				if(containerNumber1.equals(containerNumber2)) {
					generalResponse.setState(Constants.NO);
					generalResponse.setObj(null);
					generalResponse.setMsg("集装箱号不能相同");
					return generalResponse;
				}
			}
			if(containerNumber1 != "") {
				//存在多人同时操作一个项目下的集装箱 即多个运单选择同一集装箱号 此时不能新建运单
				TbContainer cont = contService.selectContainByConId(containerNumber1,sysOrgCode);
				if(order2.getTransportType() == 1 || order2.getTransportType() == 2 || order2.getTransportType() == 4) {
					if(cont.getStatus() != 0) {
						generalResponse.setState(Constants.NO);
						generalResponse.setObj(null);
						generalResponse.setMsg(containerNumber1+"集装箱已被装车，请查看");
						return generalResponse;
					}
				}
				if(order2.getTransportType() == 6 || order2.getTransportType() == 7) {
					if(order2.getStepSelectCode() == 0) {
						if(cont.getStatus() != 0) {
							generalResponse.setState(Constants.NO);
							generalResponse.setObj(null);
							generalResponse.setMsg(containerNumber1+"集装箱已被装车，请查看");
							return generalResponse;
						}
					}else if(order2.getStepSelectCode() == 1){
						if(cont.getStatus() != 1) {
							generalResponse.setState(Constants.NO);
							generalResponse.setObj(null);
							generalResponse.setMsg(containerNumber1+"集装箱已被装车，请查看");
							return generalResponse;
						}
					}
				}
				if(order2.getTransportType() == 5) {
					if(order2.getStepSelectCode() == 1) {
						if(cont.getStatus() != 1) {
							generalResponse.setState(Constants.NO);
							generalResponse.setObj(null);
							generalResponse.setMsg(containerNumber1+"集装箱已被装车，请查看");
							return generalResponse;
						}
					}
				}
			}
			if(containerNumber2 != "") {
				//存在多人同时操作一个项目下的集装箱 即多个运单选择同一集装箱号 此时不能新建运单
				TbContainer cont = contService.selectContainByConId(containerNumber2,sysOrgCode);
				if(order2.getTransportType() == 1 || order2.getTransportType() == 2 || order2.getTransportType() == 4) {
					if(cont.getStatus() != 0) {
						generalResponse.setState(Constants.NO);
						generalResponse.setObj(null);
						generalResponse.setMsg(containerNumber2+"集装箱已被装车，请查看");
						return generalResponse;
					}
				}
				if(order2.getTransportType() == 6 || order2.getTransportType() == 7) {
					if(order2.getStepSelectCode() == 0) {
						if(cont.getStatus() != 0) {
							generalResponse.setState(Constants.NO);
							generalResponse.setObj(null);
							generalResponse.setMsg(containerNumber2+"集装箱已被装车，请查看");
							return generalResponse;
						}
					}else if(order2.getStepSelectCode() == 1){
						if(cont.getStatus() != 1) {
							generalResponse.setState(Constants.NO);
							generalResponse.setObj(null);
							generalResponse.setMsg(containerNumber2+"集装箱已被装车，请查看");
							return generalResponse;
						}
					}
				}
				if(order2.getTransportType() == 5) {
					if(order2.getStepSelectCode() == 1) {
						if(cont.getStatus() != 1) {
							generalResponse.setState(Constants.NO);
							generalResponse.setObj(null);
							generalResponse.setMsg(containerNumber2+"集装箱已被装车，请查看");
							return generalResponse;
						}
					}
				}
			}
		}
		
		/*Integer containerNumber1Id = null;
		if (!StringUtil.isEmpty(containerNumber1)) {
			TbContainer tbContainer1 = tbContainerMapper.veiwTbContainerByContainerId(containerNumber1);
			if (tbContainer1 != null) {
				containerNumber1Id = tbContainer1.getId();
			}
		}
		Integer containerNumber2Id = null;
		if (!StringUtil.isEmpty(containerNumber2)) {
			TbContainer tbContainer2 = tbContainerMapper.veiwTbContainerByContainerId(containerNumber2);
			if (tbContainer2 != null) {
				containerNumber2Id = tbContainer2.getId();
			}
		}*/

		String[] arrOrderId = strOrderId.trim().split(",");
		for (String strOrderIdTmp : arrOrderId) {
			if (StringUtil.isEmpty(strOrderIdTmp))
				continue;
			Integer orderId = Integer.valueOf(strOrderIdTmp.trim());

			// 根据orderId找到任务id
			TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);
			Integer distributionId = tbOrder.getProjectDistributionId();
			Boolean flag = false;
			if (distributionId != null) {
				// 根据任务id获取任务对象
				TbProjectDistribution distribution = distributionMapper.selectByPrimaryKey(distributionId);
				if (distribution.getCarNum() == null || distribution.getCarNum() == 0) {
					flag = true;
				} else {
					distribution.setCarNum(distribution.getCarNum() - 1);// 代领任务-1
					distribution.setAlreadyRecNum(distribution.getAlreadyRecNum() + 1);// 已领任务+1
				}
				distributionMapper.updateByPrimaryKeySelective(distribution);
			}
			if (flag) {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("任务个数已分配完-调度失败");
				return generalResponse;
			}

			// 更新order
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			map.put("containerNumber1", containerNumber1);
			map.put("containerNumber2", containerNumber2);
			/*if (containerNumber1Id != null)
				map.put("containerNumber1Id", containerNumber1Id);
			if (containerNumber2Id != null)
				map.put("containerNumber2Id", containerNumber2Id);*/
			map.put("status", Constants.SMS_POINT_WAIT_SEND);
			// 调度员id
			map.put("userDispatchId", tbSystemUser.getId());
			// 调度员
			map.put("userDispatchName", tbSystemUser.getName());

			int row = tbOrderMapper.updateDispatchOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("保存数据出现异常-调度失败");
				return generalResponse;
			}
			//根据order获取司机id
			Integer driverId = tbOrder.getDriverId();
			map  = new HashMap<>();
			map.put("driverId", driverId);
			map.put("status",  Constants.DRIVER_ORDER_STATUS_NO);
			
			//更新司机订单状态
			tbOrderMapper.updateDriverOrderStatus(map);//已接单
			
			//根据司机id获取该司机的其他未调度审核的运单
			TbOrderExample orderExample = new TbOrderExample();
			com.shenhesoft.logistics.business.pojo.TbOrder.TbOrderExample.Criteria orderCriteria = orderExample.createCriteria();
			orderCriteria.andDriverIdEqualTo(driverId);
			orderCriteria.andStatusEqualTo(Constants.SMS_POINT_WAIT_DIS);//等待调度
			orderCriteria.andIsCancelEqualTo((byte)0);//未取消
			orderCriteria.andDeleteFlagEqualTo((byte)0);//未删除
			orderCriteria.andSysOrgCodeEqualTo(sysOrgCode);
			orderCriteria.andTabNameEqualTo("tb_order");
			List<TbOrder> list = tbOrderMapper.selectByExample(orderExample);
			for (TbOrder order : list) {
				order.setIsCancel((byte)1);//已取消
				order.setDeleteFlag((byte)1);//已删除
				tbOrderMapper.updateByPrimaryKey(order);//更新数据库
			}
			PushUtil pushUtil = new PushUtil();
			pushUtil.sendAndroidCustomizedcast(driverId.toString(), "您已接单成功", "请查看取货地点", "2");
			
			//消息通知
			anchoredRecordMapper.insertNoticeByApp(driverId,"您已接单成功,请查看取货地点");
		}
		
		//更新集装箱状态为装车中，并绑定项目id
		if(order2.getProjectType() == 0) {
			Map<String,Object> cMap = new HashMap<>();
			if(containerNumber1 != "") {
				cMap.put("status", 3);
				cMap.put("projectId", order2.getProjectId());
				cMap.put("containerId", containerNumber1);
				cMap.put("sysOrgCode", sysOrgCode);
				containerMapper.updateContainerStatus(cMap);
			}
			if(containerNumber2 != "") {
				cMap.put("status", 3);
				cMap.put("projectId", order2.getProjectId());
				cMap.put("containerId", containerNumber2);
				cMap.put("sysOrgCode", sysOrgCode);
				containerMapper.updateContainerStatus(cMap);
			}
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("调度成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse turndownTbOrderApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strOrderId = dataMap.get("orderId");// 运单号
		String remark = dataMap.get("remark");// 驳回原因
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-驳回失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-驳回失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strOrderId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单号-驳回失败");
			return generalResponse;
		}
		Integer orderId = Integer.valueOf(strOrderId.trim());
		TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);
		// 驳回信息封装到TbWaitDispatchDetail中
		TbWaitDispatchDetail tbWaitDispatchDetail = new TbWaitDispatchDetail();
		tbWaitDispatchDetail.setIsAgree((byte) 0);// 指定是驳回
		tbWaitDispatchDetail.setOrderId(orderId);
		tbWaitDispatchDetail.setRemark(remark);
		boolean flag = shortBargeService.dispatchAdd(tbWaitDispatchDetail, tbSystemUser);
		if (flag) {// 驳回成功
			generalResponse.setState(Constants.YES);
			generalResponse.setMsg("驳回成功");
			PushUtil pushUtil = new PushUtil();
			pushUtil.sendAndroidCustomizedcast(tbOrder.getDriverId().toString(), "您的申请被取消", "请重新接单或联系调度员", "1");

			//消息通知
			anchoredRecordMapper.insertNoticeByApp(tbOrder.getDriverId(),"您的申请被取消,请重新接单或联系调度员");
			
			return generalResponse;
		} else {// 驳回失败
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("驳回失败出现异常");
			return generalResponse;
		}
	}

	@Override
	public GeneralResponse listTbOrderTurndownApp(Map<String, String> dataMap, Integer pageLimit) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");
		String strPage = dataMap.get("page");
		String sysOrgCode = dataMap.get("sysOrgCode");// 

		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-获得驳回运单数据列表失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		if (StringUtil.isEmpty(strPage))
			strPage = "1";
		Integer page = Integer.valueOf(strPage.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-获得驳回运单数据列表失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		// 运单回收站 - 被驳回的运单
		DataGridResult result = shortBargeService.selectBoxManagerOrderDeleteByPages(page, pageLimit, null,
				tbSystemUser, (byte) 3);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("获得驳回运单数据列表成功");
		generalResponse.setObj(result);
		return generalResponse;
	}

	@Override
	public GeneralResponse restoreTbOrderTurndownApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strOrderId = dataMap.get("orderId");// 运单号
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-还原失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-还原失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strOrderId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单号-还原失败");
			return generalResponse;
		}
		Integer orderId = Integer.valueOf(strOrderId.trim());
		List<Integer> orderIdList = new ArrayList<>();
		orderIdList.add(orderId);
		LogisticsResult logisticsResult = shortBargeService.revertOrder(orderIdList);
		if (logisticsResult.getStatus().equals(0)) {// 还原失败
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("还原失败,该运单已超过24小时");
			return generalResponse;
		} else {// 驳回失败
			generalResponse.setState(Constants.YES);
			generalResponse.setMsg("还原成功");
			return generalResponse;
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public GeneralResponse listAllTbOrderDifferentStatus(Map<String, String> dataMap, Integer pageLimit)
			throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");
		String strPage = dataMap.get("page");
		String strStatus = dataMap.get("status");// 运单状态 多个用,隔开
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询全部运单失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		if (StringUtil.isEmpty(strPage))
			strPage = "1";
		Integer page = Integer.valueOf(strPage.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询全部运单失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		// 查询 等待发运 在途运载 等待回单状态的列表
		List<Byte> statusList = new ArrayList<Byte>();
		if (!StringUtil.isEmpty(strStatus)) {
			String[] arrStatus = strStatus.split(",");
			for (String tmpStatus : arrStatus) {
				if (StringUtil.isEmpty(tmpStatus))
					continue;
				statusList.add(Byte.valueOf(tmpStatus.trim()));
			}
		}
		OrderSearch orderSearch = new OrderSearch();
		orderSearch.setStatusList(statusList);
		DataGridResult result = shortBargeService.selectBoxManagerOrderByPages(page, pageLimit, (byte) 1, orderSearch,
				tbSystemUser, (byte) 3);

		// 所属车队和历史运输信息
		List<TbOrder> tbOrderList = result.getRows();
		if (tbOrderList != null && tbOrderList.size() > 0) {
			for (TbOrder tbOrder : tbOrderList) {
				// 车队信息
				if (tbOrder.getCarteamId() != null) {
					TbCarTeam tbCarTeam = tbCarTeamMapper.selectByPrimaryKey(tbOrder.getCarteamId());
					if (tbCarTeam != null)
						tbOrder.setCarItemName(tbCarTeam.getCarItemName());
				}
				// 司机历史运单
				if (tbOrder.getDriverId() != null) {
					TbOrderExample example = new TbOrderExample();
					TbOrderExample.Criteria criteria = example.createCriteria();
					criteria.andDriverIdEqualTo(tbOrder.getDriverId());
					criteria.andDeleteFlagEqualTo((byte) 0);
					criteria.andIsCancelEqualTo((byte) 0);
					List<Byte> statusList1 = new ArrayList<Byte>();
					statusList1.add((byte) 6);
					statusList1.add((byte) 7);
					criteria.andStatusIn(statusList1);
					Integer historyTbOrderNumDriverId = tbOrderMapper.countByExample(example);
					tbOrder.setHistoryTbOrderNumDriverId(historyTbOrderNumDriverId);
				}
			}
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询运单成功");
		generalResponse.setObj(result);
		return generalResponse;
	}

	@Override
	public GeneralResponse saveTbOrderForwardInfo(Map<String, String> dataMap, HttpSession session) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strOrderId = dataMap.get("orderId");// 运单号
		String strSendTare = dataMap.get("sendTare"); // 发货皮重 BigDecimal
		String strSendGross = dataMap.get("sendGross");// 发货毛重 BigDecimal
		String strContainerOneSendNet = dataMap.get("containerOneSendNet");// 第一个集装箱的发货净重
																			// BigDecimal
		String strContainerTwoSendNet = dataMap.get("containerTwoSendNet");// 第二个集装箱的发货净重
																			// BigDecimal
		String testIndicators = dataMap.get("testIndicators");// 发货:化验指标
		String strType = dataMap.get("type");// 运单类型 1:集装箱 2:散堆装
		String img = dataMap.get("img");// 图片 base64
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-保存发运信息失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-保存发运信息失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		
		if (StringUtil.isEmpty(strOrderId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单号-保存发运信息失败");
			return generalResponse;
		}
		Integer orderId = Integer.valueOf(strOrderId.trim());
		// 发货皮重
		BigDecimal sendTare = strSendTare == null ? new BigDecimal("0") : new BigDecimal(strSendTare.trim());
		// 发货毛重 BigDecimal
		BigDecimal sendGross = strSendGross == null ? new BigDecimal("0") : new BigDecimal(strSendGross.trim());
		// 第一个集装箱的发货净重 BigDecimal
		BigDecimal containerOneSendNet = strContainerOneSendNet == null ? new BigDecimal("0")
				: new BigDecimal(strContainerOneSendNet.trim());
		// 第二个集装箱的发货净重 BigDecimal
		BigDecimal containerTwoSendNet = null;
		if (Integer.valueOf(strType).equals(1)) {// 集装箱
			containerTwoSendNet = strContainerTwoSendNet == null ? new BigDecimal("0")
					: new BigDecimal(strContainerTwoSendNet.trim());
		}
		if (!StringUtil.isEmpty(img)) {// 运单不为空
			LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo", img.trim(), null);
			if (base64UpLoad.getStatus() == 200) {
				img = base64UpLoad.getData().toString();
			} else {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("运单号图片上传失败-保存发运信息失败");
				return generalResponse;
			}
		}
		int flag = 0;
		if (Integer.valueOf(strType).equals(1)) {// 集装箱
			TbSubForwardDetail tbSubForwardDetail = new TbSubForwardDetail();
			tbSubForwardDetail.setOrderId(orderId);
			tbSubForwardDetail.setSendTare(sendTare);
			tbSubForwardDetail.setSendGross(sendGross);
			tbSubForwardDetail.setContainerOneSendNet(containerOneSendNet);
			tbSubForwardDetail.setContainerTwoSendNet(containerTwoSendNet);
			tbSubForwardDetail.setTestIndicators(testIndicators);
			tbSubForwardDetail.setImg(img);
			tbSubForwardDetail.setIsAgree((byte) 0);// 同意发运
			flag = shortBargeService.subForwardingAdd(tbSubForwardDetail, tbSystemUser);
		}else {
			TbBulkSubForwardDetail tbBulkSubForwardDetail = new TbBulkSubForwardDetail();
			tbBulkSubForwardDetail.setOrderId(orderId);
			tbBulkSubForwardDetail.setSendTare(sendTare);
			tbBulkSubForwardDetail.setSendGross(sendGross);
			tbBulkSubForwardDetail.setContainerOneSendNet(containerOneSendNet);
			tbBulkSubForwardDetail.setTestIndicators(testIndicators);
			tbBulkSubForwardDetail.setImg(img);
			tbBulkSubForwardDetail.setIsAgree((byte) 0);// 同意发运
			flag = shortBargeService.subBulkForwardingAdd(tbBulkSubForwardDetail, tbSystemUser);
		}
		
		
		if (flag != 1) {
			if(flag == 602) {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("发货净重大于库存，无法发运");
				return generalResponse;
			}
			if(flag == 607) {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("此站点下没有库存，无法发运");
				return generalResponse;
			}
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("保存发运信息失败");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("保存发运信息成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse cancelTbOrderForward(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strOrderId = dataMap.get("orderId");// 运单号
		String strType = dataMap.get("type");// 运单类型
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-取消发运失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-取消发运失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		if (StringUtil.isEmpty(strOrderId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单号-取消发运失败");
			return generalResponse;
		}
		Integer orderId = Integer.valueOf(strOrderId.trim());
		int flag = 0;
		if (Integer.valueOf(strType).equals(0)) {// 集装箱
			TbSubForwardDetail tbSubForwardDetail = new TbSubForwardDetail();
			tbSubForwardDetail.setOrderId(orderId);
			tbSubForwardDetail.setRemark(" ");
			tbSubForwardDetail.setIsAgree((byte) 1);// 同意发运
			flag = shortBargeService.subForwardingAdd(tbSubForwardDetail, tbSystemUser);
		}else {
			TbBulkSubForwardDetail tbBulkSubForwardDetail = new TbBulkSubForwardDetail();
			tbBulkSubForwardDetail.setOrderId(orderId);
			tbBulkSubForwardDetail.setRemark(" ");
			tbBulkSubForwardDetail.setIsAgree((byte) 1);// 同意发运
			flag = shortBargeService.subBulkForwardingAdd(tbBulkSubForwardDetail, tbSystemUser);
		}
		// 进入回收站
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("userId", tbSystemUser.getId());
		map.put("userName", tbSystemUser.getName());
		map.put("deleteFlag", Constants.DELETE_FLAG_TRUE);
		map.put("isCancle", Constants.ORDER_CANCLE_YES);// 运单状态 已取消
		int row = tbOrderMapper.updateDeleteOrderByMap(map);*/
		if (flag != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("保存取消发运失败");
			return generalResponse;
		} else {
			generalResponse.setState(Constants.YES);
			generalResponse.setMsg("保存取消发运成功");
			return generalResponse;
		}
	}

	@Override
	public GeneralResponse listGoodsYardApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询货位引导货场列表失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询货位引导货场列表失败");
			return generalResponse;
		}
		//List<TbFreightYardDetail> tbFreightYardDetailList = siteManageService.getAllFreightYards();
		String orderId = dataMap.get("orderId");// 运单id
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询货位引导货场列表失败");
			return generalResponse;
		}
		// 判断运单信息 
		if (StringUtil.isEmpty(orderId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单信息");
			return generalResponse;
		}
		Integer orderIds = Integer.valueOf(orderId.trim());
		TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderIds);
		if (tbOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单信息-通过运单id查询运单详细信息失败");
			return generalResponse;
		}
		List<TbFreightYard> tbFreightYardDetailList = null;
		//根据阶段 获取货场 0 接取 1 送达 2 汽运
		if(tbOrder.getStepSelectCode() == 0) {
			tbFreightYardDetailList = siteManageService.getFreightYardsByTrainStationId(tbOrder.getReceiptCompanyId());
		}else if(tbOrder.getStepSelectCode() == 1) {
			tbFreightYardDetailList = siteManageService.getAllFreightYardOfIsolated(tbOrder.getReceiptCompanyId());
		}else {
			tbFreightYardDetailList = siteManageService.getAllFreightYardOfIsolated(tbOrder.getReceiptCompanyId());
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询货位引导货场列表成功");
		generalResponse.setObj(tbFreightYardDetailList);
		return generalResponse;
	}

	@Override
	public GeneralResponse listTbCargoLocationByFreightIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strFreightId = dataMap.get("freightId");// 货场id
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-根据货场获得货位信息失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-根据货场获得货位信息失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strFreightId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到货场信息-根据货场获得货位信息失败");
			return generalResponse;
		}
		List<TbFreightLocationDetail> tbFreightLocationDetailList = siteManageService
				.getAllFreightLocationsByYardId(Integer.valueOf(strFreightId.trim()));
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("根据货场获得货位列表成功");
		generalResponse.setObj(tbFreightLocationDetailList);
		return generalResponse;
	}

	@Override
	public GeneralResponse saveGuideTbOrderApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strOrderId = dataMap.get("orderId");// 运单号
		String distributionCargoPlace = dataMap.get("distributionCargoPlace");// 货场名
		String distributionCargoSite = dataMap.get("distributionCargoSite");// 货位名
		String distributionCargoPlaceId = dataMap.get("distributionCargoPlaceId");// 货场id
		String distributionCargoSiteId = dataMap.get("distributionCargoSiteId");// 货位id
		
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-货位引导-提交货场货位信息失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-货位引导-提交货场货位信息失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strOrderId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单号-货位引导-提交货场货位信息失败");
			return generalResponse;
		}
		Integer orderId = Integer.valueOf(strOrderId.trim());
		Integer distributionCargoPlaceId2 = Integer.valueOf(distributionCargoPlaceId.trim());
		Integer distributionCargoSiteId2 = Integer.valueOf(distributionCargoSiteId.trim());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("status", Constants.SMS_POINT_WAIT_RETURN);
		map.put("distributionCargoPlace", distributionCargoPlace);
		map.put("distributionCargoSite", distributionCargoSite);
		map.put("distributionCargoPlaceId", distributionCargoPlaceId2);
		map.put("distributionCargoSiteId", distributionCargoSiteId2);
		int row = tbOrderMapper.updateGuideAddOrderByMap(map);
		if (row != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("保存货位信息失败");
			return generalResponse;
		}
		TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);
		PushUtil demo = new PushUtil();
		demo.sendAndroidCustomizedcast(tbOrder.getDriverId().toString(), "您的卸货货场已分配", "请查看卸货地点 ", "5");
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("保存货位信息成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse listTbOrderAbnormalApp(Map<String, String> dataMap, Integer pageLimit) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");
		String strPage = dataMap.get("page");
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询异常运单列表失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		if (StringUtil.isEmpty(strPage))
			strPage = "1";
		Integer page = Integer.valueOf(strPage.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询异常运单列表失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		DataGridResult result = shortBargeService.selectBoxManagerOrderByPages(page, pageLimit, (byte) 2, null,
				tbSystemUser, (byte) 3);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询异常运单列表成功");
		generalResponse.setObj(result);
		return generalResponse;
	}

	@Override
	public GeneralResponse listTbProjectApp(Map<String, String> dataMap, Integer pageLimit) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");
		String strPage = dataMap.get("page");
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("未获取到当前登录人信息-查询项目列表失败");
			generalResponse.setObj(null);
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		if (StringUtil.isEmpty(strPage))
			strPage = "1";
		Integer page = Integer.valueOf(strPage.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询项目列表失败");
			return generalResponse;
		}
		List<Byte> statusList = new ArrayList<>();
		statusList.add(Constants.PROJECT_STATUS_RUNNING);// 在运行
		ProjectDetail search = new ProjectDetail();
		// status.add(Constants.PROJECT_STATUS_UNUSED);//未使用
		DataGridResult result = projectManagmentService.getProjectManagment(page, pageLimit, statusList, userId,
				search);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询项目列表成功");
		generalResponse.setObj(result);
		return generalResponse;
	}

	@Override
	public GeneralResponse viewTbProjectByIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strProjectId = dataMap.get("projectId");// 项目id
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询项目详细信息失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询项目详细信息失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strProjectId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确项目-查询项目详细信息失败");
			return generalResponse;
		}
		Integer projectId = Integer.valueOf(strProjectId.trim());
		LogisticsResult logisticsResult = projectManagmentService.selectProject(projectId);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询项目详细信息成功");
		generalResponse.setObj(logisticsResult.getData());
		return generalResponse;
	}

	@Override
	public GeneralResponse listCarByPorjectIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strProjectId = dataMap.get("projectId");// 项目id
		String strShortType = dataMap.get("shortType");// 0接取 1 送达 2 汽运
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询项目详细信息失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询项目详细信息失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strProjectId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确项目-查询项目详细信息失败");
			return generalResponse;
		}
		Integer projectId = Integer.valueOf(strProjectId.trim());
		if(strShortType==null){
			List<TbOrderCarDetail> tbOrderCarDetailList =  shortBargeService.selectRuningCarTeams(projectId);
			generalResponse.setState(Constants.YES);
			//generalResponse.setMsg("保存货位信息成功");
			generalResponse.setObj(tbOrderCarDetailList);
			return generalResponse;
		}
		Integer shortType = Integer.valueOf(strShortType.trim());
		
		//获取project对象
		TbProject project = tbProjectMapper.selectByPrimaryKey(projectId);
		Byte model = null;
		if(shortType==0 || shortType==2){//接取或汽运
			model = project.getShortBargeCarrierMode();
		}else if(shortType==1){ //送达
			model = project.getSendShortBargeCarrierMode();
		}
		List<TbOrderCarDetail> tbOrderCarDetailList = new ArrayList<>();
		if(model != null){
			if(model ==Constants.TYPE_OPTIONAL){
				//自选
				tbOrderCarDetailList = shortBargeService.selectCarTeams(projectId,null,shortType);
			}else if(model == Constants.TYPE_PLATFORM){
				//平台 
				tbOrderCarDetailList = shortBargeService.selectCarTeamsByPlatform(projectId,null,shortType);
			}
		}
		
		generalResponse.setState(Constants.YES);
		//generalResponse.setMsg("保存货位信息成功");
		generalResponse.setObj(tbOrderCarDetailList);
		return generalResponse;
	}

	/*@Override
	public GeneralResponse viewCarInfoByDriverIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strProjectId = dataMap.get("projectId");// 项目id
		String strDriverId = dataMap.get("driverId");// 司机id
		String strShortType = dataMap.get("shortType");// 0接取 1 送达 2 汽运
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询汽车信息失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询汽车信息失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strProjectId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确项目-查询项目详细信息失败");
			return generalResponse;
		}
		Integer projectId = Integer.valueOf(strProjectId.trim());
		if (StringUtil.isEmpty(strDriverId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确司机-查询汽车信息失败");
			return generalResponse;
		}
		Integer driverId = Integer.valueOf(strDriverId.trim());
		Integer shortType = Integer.valueOf(strShortType.trim());
		
		//获取project对象
		TbProject project = tbProjectMapper.selectByPrimaryKey(projectId);
		Byte model = null;
		if(shortType==0 || shortType==2){//接取或汽运
			model = project.getShortBargeCarrierMode();
		}else if(shortType==1){ //送达
			model = project.getSendShortBargeCarrierMode();
		}
		List<TbOrderCarDetail> tbOrderCarDetailList = new ArrayList<>();
		if(model != null){
			if(model ==Constants.TYPE_OPTIONAL){
				//自选
				tbOrderCarDetailList = shortBargeService.selectCarTeams(projectId,driverId,shortType);
			}else if(model == Constants.TYPE_PLATFORM){
				//平台 
				tbOrderCarDetailList = shortBargeService.selectCarTeamsByPlatform(projectId,driverId,shortType);
			}
		}
		
		if (tbOrderCarDetailList == null || tbOrderCarDetailList.size() == 0) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("查询汽车信息失败");
			return generalResponse;
		}
		// 查询挂靠信息
		for (TbOrderCarDetail tbOrderCarDetail : tbOrderCarDetailList) {
			tbOrderCarDetail.setTbAnchoredCompanyList(
					tbAnchoredCompanyMapper.listTbAnchoredCompanyByUserId(tbOrderCarDetail.getDriverId()));

			if (tbOrderCarDetail.getDriverId() != null) {
				TbOrderExample example = new TbOrderExample();
				TbOrderExample.Criteria criteria = example.createCriteria();
				criteria.andDriverIdEqualTo(tbOrderCarDetail.getDriverId());
				criteria.andDeleteFlagEqualTo((byte) 0);
				criteria.andIsCancelEqualTo((byte) 0);
				List<Byte> statusList = new ArrayList<Byte>();
				statusList.add((byte) 6);
				statusList.add((byte) 7);
				criteria.andStatusIn(statusList);
				Integer historyTbOrderNumDriverId = tbOrderMapper.countByExample(example);
				tbOrderCarDetail.setHistoryTbOrderNumDriverId(historyTbOrderNumDriverId);
			}

		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询汽车信息成功");
		generalResponse.setObj(tbOrderCarDetailList.get(0));
		return generalResponse;
	}*/
	
	public GeneralResponse viewCarInfoByDriverIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strDriverId = dataMap.get("driverId");// 司机id
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询汽车信息失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询汽车信息失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strDriverId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确司机-查询汽车信息失败");
			return generalResponse;
		}
		Integer driverId = Integer.valueOf(strDriverId.trim());
		
		//获取司机信息
		TbOrderCarDetail tbOrderCarDetail=shortBargeService.selectDriverByDriverId(driverId);
		
		//挂靠信息
		tbOrderCarDetail.setTbAnchoredCompanyList(tbAnchoredCompanyMapper.listTbAnchoredCompanyByUserId(driverId));
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询汽车信息成功");
		generalResponse.setObj(tbOrderCarDetail);
		return generalResponse;
	}

	@Override
	public GeneralResponse saveTbOrderApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String projectId = dataMap.get("projectId");// projectId 项目id
		String projectCode = dataMap.get("projectCode");// projectCode 项目编号
		String stepSelectCode = dataMap.get("stepSelectCode");// stepSelectCode
																// 阶段选择
		String branchGroupName = dataMap.get("branchGroupName");// branchGroupName
																// 分支机构
		String arrivePlaceAddress = dataMap.get("arrivePlaceAddress");// arrivePlaceAddress
																		// 运抵地址
		String cargoName = dataMap.get("cargoName");// cargoName 货物品名
		String carTeamId = dataMap.get("carTeamId");// carTeamId 车队id
		String carPlateNumber = dataMap.get("carPlateNumber");// carPlateNumber
																// 承运车辆车牌号
		String carrierVehicleId = dataMap.get("carrierVehicleId");// carrierVehicleId
																	// 承运车辆id
		String carrierVehicleName = dataMap.get("carrierVehicleName");// carrierVehicleName
																		// 承运车辆名称
		String carType = dataMap.get("carType");// carType 车辆类型
		String driverName = dataMap.get("driverName");// driverName 驾驶员
		String driverPhone = dataMap.get("driverPhone");// driverPhone 联系方式
		String shortBargeCost = dataMap.get("shortBargeCost");// shortBargeCost
																// 短驳运费
		String deductionPrice = dataMap.get("deductionPrice");// deductionPrice
																// 折损单价
		String subsidy = dataMap.get("subsidy");// subsidy 补贴
		String containerNumber1 = dataMap.get("containerNumber1");// containerNumber1
																	// 集装箱1号名称
		String containerNumber2 = dataMap.get("containerNumber2");// containerNumber2
																	// 集装箱2号名称
		String strType = dataMap.get("type");// 运单类型 1:集装箱 2:散堆装
		String strTakeCargoPlaceId = dataMap.get("takeCargoPlaceId"); // 取货货场id
		String strTakeCargoSiteId = dataMap.get("takeCargoSiteId"); // 取货货位id
		String strTransportType = dataMap.get("transportType"); //项目模式
		String sysOrgCode = dataMap.get("sysOrgCode");//
		
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-保存运单信息失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-保存运单信息失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		TbOrder tbOrder = new TbOrder();

		if (StringUtil.isEmpty(strType)) {// 运单类型是空的
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到运单类型-保存运单信息失败");
			return generalResponse;
		}

		if (StringUtils.isBlank(strTakeCargoPlaceId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到货场信息-保存运单信息失败");
			return generalResponse;
		}

		if (StringUtils.isBlank(strTakeCargoSiteId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到货位信息-保存运单信息失败");
			return generalResponse;
		}

		Integer type = Integer.valueOf(strType);
		if (type == 1) {// 1:集装箱
			// 1号集装箱
			if (StringUtil.isEmpty(containerNumber1)&&StringUtil.isEmpty(containerNumber2)) {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("未获取到集装箱信息-保存运单信息失败");
				return generalResponse;
			}
			
			// 2号集装箱
			/*if (StringUtil.isEmpty(containerNumber2)) {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("未获取到2号集装箱信息-保存运单信息失败");
				return generalResponse;
			}*/
			/*TbContainer tbContainer1 = tbContainerMapper.veiwTbContainerByContainerId(containerNumber1);
			TbContainer tbContainer2 = tbContainerMapper.veiwTbContainerByContainerId(containerNumber2);
			if (tbContainer1 != null)
				tbOrder.setContainerNumber1Id(tbContainer1.getId() + "");
			tbOrder.setContainerNumber1(containerNumber1.trim());
			if (tbContainer2 != null)
				tbOrder.setContainerNumber2Id(tbContainer2.getId() + "");
			tbOrder.setContainerNumber2(containerNumber2);*/
			tbOrder.setContainerNumber1(containerNumber1.trim());
			tbOrder.setContainerNumber2(containerNumber2);
			//判断集装箱号
			if((containerNumber1 != "") && (containerNumber2 != "")) {
				if(containerNumber1.equals(containerNumber2)) {
					generalResponse.setState(Constants.NO);
					generalResponse.setObj(null);
					generalResponse.setMsg("集装箱号不能相同");
					return generalResponse;
				}
			}
			Integer transportType = Integer.valueOf(strTransportType);
			if(containerNumber1 != "") {
				//存在多人同时操作一个项目下的集装箱 即多个运单选择同一集装箱号 此时不能新建运单
				TbContainer cont = contService.selectContainByConId(containerNumber1,sysOrgCode);
				if(transportType == 1 || transportType == 2 || transportType == 4) {
					if(cont.getStatus() != 0) {
						generalResponse.setState(Constants.NO);
						generalResponse.setObj(null);
						generalResponse.setMsg(containerNumber1+"集装箱已被装车，请查看");
						return generalResponse;
					}
				}
				if(transportType == 6 || transportType == 7) {
					if(Integer.valueOf(stepSelectCode) == 0) {
						if(cont.getStatus() != 0) {
							generalResponse.setState(Constants.NO);
							generalResponse.setObj(null);
							generalResponse.setMsg(containerNumber1+"集装箱已被装车，请查看");
							return generalResponse;
						}
					}else if(Integer.valueOf(stepSelectCode) == 0){
						if(cont.getStatus() != 1) {
							generalResponse.setState(Constants.NO);
							generalResponse.setObj(null);
							generalResponse.setMsg(containerNumber1+"集装箱已被装车，请查看");
							return generalResponse;
						}
					}
				}
				if(transportType == 5) {
					if(Integer.valueOf(stepSelectCode) == 0) {
						if(cont.getStatus() != 1) {
							generalResponse.setState(Constants.NO);
							generalResponse.setObj(null);
							generalResponse.setMsg(containerNumber1+"集装箱已被装车，请查看");
							return generalResponse;
						}
					}
				}
			}
			if(containerNumber2 != "") {
				//存在多人同时操作一个项目下的集装箱 即多个运单选择同一集装箱号 此时不能新建运单
				TbContainer cont = contService.selectContainByConId(containerNumber2,sysOrgCode);
				if(transportType == 1 || transportType == 2 || transportType == 4) {
					if(cont.getStatus() != 0) {
						generalResponse.setState(Constants.NO);
						generalResponse.setObj(null);
						generalResponse.setMsg(containerNumber2+"集装箱已被装车，请查看");
						return generalResponse;
					}
				}
				if(transportType == 6 || transportType == 7) {
					if(Integer.valueOf(stepSelectCode) == 0) {
						if(cont.getStatus() != 0) {
							generalResponse.setState(Constants.NO);
							generalResponse.setObj(null);
							generalResponse.setMsg(containerNumber2+"集装箱已被装车，请查看");
							return generalResponse;
						}
					}else if(Integer.valueOf(stepSelectCode) == 0){
						if(cont.getStatus() != 1) {
							generalResponse.setState(Constants.NO);
							generalResponse.setObj(null);
							generalResponse.setMsg(containerNumber2+"集装箱已被装车，请查看");
							return generalResponse;
						}
					}
				}
				if(transportType == 5) {
					if(Integer.valueOf(stepSelectCode) == 0) {
						if(cont.getStatus() != 1) {
							generalResponse.setState(Constants.NO);
							generalResponse.setObj(null);
							generalResponse.setMsg(containerNumber2+"集装箱已被装车，请查看");
							return generalResponse;
						}
					}
				}
			}
		}
		// 得到项目信息
		TbProject tbProject = tbProjectMapper.selectByPrimaryKey(Integer.valueOf(projectId));
		if (tbProject == null)
			tbProject = new TbProject();
		
			if(Integer.parseInt(stepSelectCode)==0 || Integer.parseInt(stepSelectCode)==2){//接取 或汽运
				// 发货单位
				Integer sendCargoUnitId = tbProject.getSendCargoUnitId();
				CustomerInfo sendCargoUnit = customerMapper.selectCustomerInfoByCid(sendCargoUnitId);
				if(sendCargoUnit!=null){
					tbOrder.setSendCompanyId(sendCargoUnitId);
					tbOrder.setSendCompany(sendCargoUnit.getCompanyName());
					tbOrder.setPickupPlace(sendCargoUnit.getAddressCode());// 取货地
					tbOrder.setPickupPlaceAddress(sendCargoUnit.getDetailAddress());// 取货详细地址
				}
				if(Integer.parseInt(stepSelectCode)==0){//接取
					Integer receiveCargoSiteId = tbProject.getReceiveCargoSiteId();
					if(receiveCargoSiteId!=null){
						TbTrainStation trainStation = stationMapper.selectByPrimaryKey(receiveCargoSiteId);
						tbOrder.setReceiptCompanyId(receiveCargoSiteId);
						tbOrder.setReceiptCompany(trainStation.getStationName());
						tbOrder.setArrivePlace(trainStation.getAdressCode());
						tbOrder.setArriveAddress(trainStation.getDetailAddress());// 运抵地址详情
					}	
				}else{//汽运
					// 收货单位
					Integer  receivingDepartmentId= tbProject.getReceivingDepartmentId();
					if(receivingDepartmentId!=null){
						CustomerInfo receivingDepartment = customerMapper.selectCustomerInfoByCid(receivingDepartmentId);
						tbOrder.setReceiptCompanyId(receivingDepartmentId);
						tbOrder.setReceiptCompany(receivingDepartment.getCompanyName());
						tbOrder.setArrivePlace(receivingDepartment.getAddressCode());
						tbOrder.setArriveAddress(receivingDepartment.getDetailAddress());// 运抵地址详情
					}
				}
			}else if(Integer.parseInt(stepSelectCode)==1){//送达
				// 接取站点
				Integer sendCargoSiteId = tbProject.getForwardingSiteId();
				if(sendCargoSiteId!=null){
					TbTrainStation trainStation = stationMapper.selectByPrimaryKey(sendCargoSiteId);
					tbOrder.setSendCompanyId(sendCargoSiteId);
					tbOrder.setSendCompany(trainStation.getStationName());
					tbOrder.setPickupPlace(trainStation.getAdressCode());// 取货地
					tbOrder.setPickupPlaceAddress(trainStation.getDetailAddress());// 取货详细地址
				}
				// 收货单位
				Integer  receivingDepartmentId= tbProject.getReceivingDepartmentId();
				if(receivingDepartmentId!=null){
					CustomerInfo receivingDepartment = customerMapper.selectCustomerInfoByCid(receivingDepartmentId);
					tbOrder.setReceiptCompanyId(receivingDepartmentId);
					tbOrder.setReceiptCompany(receivingDepartment.getCompanyName());
					tbOrder.setArrivePlace(receivingDepartment.getAddressCode());
					tbOrder.setArriveAddress(receivingDepartment.getDetailAddress());// 运抵地址详情
				}
			}
		
		tbOrder.setType((byte) type.intValue());
		tbOrder.setProjectId(Integer.valueOf(projectId));// 项目主键
		tbOrder.setProjectCode(projectCode);// 项目编号
		tbOrder.setProjectType(tbProject.getProjectType());// 0 集装箱 1 散装
		tbOrder.setTransportType(tbProject.getTransportType());// 联运模式
		tbOrder.setBranchId(tbProject.getBranchGroupId());// 分支机构id
		tbOrder.setBranchGroupName(branchGroupName);// 分支机构名称
		tbOrder.setStepSelectCode(Byte.valueOf(stepSelectCode));// 0接取 1：送达 2:汽运
		tbOrder.setArrivePlace(arrivePlaceAddress);// 运抵地
		tbOrder.setCargoName(cargoName);// 货位品名
		tbOrder.setCarteamId(Integer.valueOf(carTeamId));// 车队id
		tbOrder.setCarPlateNumber(carPlateNumber);// 承运车辆车牌号
		tbOrder.setCarrierVehicleId(carrierVehicleId); // 承运车辆id
		tbOrder.setCarrierVehicleName(carrierVehicleName);// 承运车辆名称
		tbOrder.setCarType(carType);// 车型
		if (!StringUtil.isEmpty(carrierVehicleId)) {// 司机id
			tbOrder.setDriverId(Integer.valueOf(carrierVehicleId));
		}
		tbOrder.setDriverName(driverName);// 司机姓名
		tbOrder.setDriverPhone(driverPhone);// 驾驶员手机号
		tbOrder.setStatus((byte) 2);// 直接是等待发运
		tbOrder.setOrderOrigin((byte) 2);//app端
		
		tbOrder.setSpecifications(tbProject.getCargoSpecifications());// 规格
		int takeCargoPlaceId = Integer.parseInt(strTakeCargoPlaceId);
		// 通过货场id获取货场名称
		TbFreightYard tbFreightYard = freightYardMapper.selectByPrimaryKey(takeCargoPlaceId);
		if (tbFreightYard != null) {
			tbOrder.setTakeCargoPlaceId(takeCargoPlaceId);// 提货货场
			tbOrder.setTakeCarogoPlaceName(tbFreightYard.getName());// 提货货位名称
		}

		int takeCargoSiteId = Integer.parseInt(strTakeCargoSiteId);
		// 通过货位id获取货位名称
		TbCargoLocation cargoLocation = cargoLocationMapper.selectByPrimaryKey(takeCargoSiteId);
		if (cargoLocation != null) {
			tbOrder.setTakeCargoSiteId(takeCargoSiteId);// 提货货位
			tbOrder.setTakeCargoSiteName(cargoLocation.getName());// 提货货位名称
		}
		// 设置调度员
		tbOrder.setUserDispatchId(userId);
		tbOrder.setUserDispatchName(tbSystemUser.getName());
		if (!StringUtil.isEmpty(shortBargeCost))
			tbOrder.setShortBargeCost(new BigDecimal(shortBargeCost));
		if (!StringUtil.isEmpty(deductionPrice))
			tbOrder.setDeductionPrice(new BigDecimal(deductionPrice));
		if (!StringUtil.isEmpty(subsidy))
			tbOrder.setSubsidy(new BigDecimal(subsidy));
		boolean flag = true;
		if (type == 1) {
			flag = shortBargeService.orderAdd(tbOrder, tbSystemUser, Constants.SHORT_ORDER_TYPE_BOX);
		} else {
			flag = shortBargeService.orderAdd(tbOrder, tbSystemUser, Constants.SHORT_ORDER_TYPE_BULK);
		}
		if (!flag) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("保存运单信息出现异常-新建失败");
			return generalResponse;
		}
		// 更新项目状态 为正在使用中
		tbProject.setStatus(Constants.PROJECT_STATUS_RUNNING);
		tbProjectMapper.updateByPrimaryKeySelective(tbProject);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("保存运单信息成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse receipterTbOrderApp(Map<String, String> dataMap, HttpSession session) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String receiptTare = dataMap.get("receiptTare");// 发货皮重
		String receiptGross = dataMap.get("receiptGross");// 发货毛重
		String containerOneReceiptNet = dataMap.get("containerOneReceiptNet");// 第一个集装箱的发货净重
		String containerTwoReceiptNet = dataMap.get("containerTwoReceiptNet");// 第二个集装箱的发货净重
		String strType = dataMap.get("type");// 运单类型 1:集装箱 2:散堆装
		String img = dataMap.get("img");//
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-等待回单失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		// 判断登录信息 end
		// 判断运单信息 start
		TbOrder tbOrder = this.viewTbOrderDataMap(dataMap);// orderId
		if (tbOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单信息-等待回单失败");
			return generalResponse;
		}
		// 判断运单信息 end

		if (!StringUtil.isEmpty(img)) {// 运单不为空
			LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo", img.trim(), null);
			if (base64UpLoad.getStatus() == 200) {
				img = base64UpLoad.getData().toString();
			} else {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("运单号图片上传失败-等待回单失败");
				return generalResponse;
			}
		}
		if(tbOrder.getReceipterDate()!=null){
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("回单信息重复上传-等待回单失败");
			return generalResponse;
		}
		
		TbReceipterDetail tbReceipterDetail = new TbReceipterDetail();
		tbReceipterDetail.setOrderId(tbOrder.getId());
		tbReceipterDetail
				.setReceiptTare(StringUtil.isEmpty(receiptTare) ? new BigDecimal("0") : new BigDecimal(receiptTare));
		tbReceipterDetail
				.setReceiptGross(StringUtil.isEmpty(receiptGross) ? new BigDecimal("0") : new BigDecimal(receiptGross));
		tbReceipterDetail.setContainerOneReceiptNet(StringUtil.isEmpty(containerOneReceiptNet) ? new BigDecimal("0")
				: new BigDecimal(containerOneReceiptNet));
		if (Integer.valueOf(strType).equals(1)) {// 集装箱
			tbReceipterDetail.setContainerTwoReceiptNet(StringUtil.isEmpty(containerTwoReceiptNet) ? new BigDecimal("0")
					: new BigDecimal(containerTwoReceiptNet));
		}
		tbReceipterDetail.setImg(img);
		boolean flag = shortBargeService.receipterAdd(tbReceipterDetail, tbSystemUser);
		if (!flag) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("保存数据出现异常-等待回单失败");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("等待回单成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse viewTbOrderCarDetailByPlateNumber(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String plateNumber = dataMap.get("plateNumber");// 车牌号
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-通过牌照查询车辆信息失败");
			return generalResponse;
		}
		// 判断登录信息 end
		if (StringUtil.isEmpty(plateNumber)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到车牌照-通过牌照查询车辆信息失败");
			return generalResponse;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("plateNumber", plateNumber);
		TbOrderCarDetail tbOrderCarDetail = tbProjectMapper.viewTbOrderCarDetailByPlateNumber(map);
		if (tbOrderCarDetail == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未能通过车牌照获取到车辆信息-通过牌照查询车辆信息失败");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询车辆信息成功");
		generalResponse.setObj(tbOrderCarDetail);
		return generalResponse;
	}

	@Override
	public GeneralResponse listOrderExceptionReasonApp(Map<String, String> dataMap, String orderExceptionReason)
			throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询异常原因列表失败");
			return generalResponse;
		}
		// 判断登录信息 end
		String[] arrOrderExceptionReason = orderExceptionReason.split(";");
		// 遍历一下，去除空字符串
		List<String> orderExceptionReasonList = new ArrayList<String>();
		for (String tmpOrderExceptionReason : arrOrderExceptionReason) {
			if (StringUtil.isEmpty(tmpOrderExceptionReason))
				continue;
			orderExceptionReasonList.add(tmpOrderExceptionReason.trim());
		}

		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询异常原因列表成功");
		generalResponse.setObj(orderExceptionReasonList);
		return generalResponse;
	}

	@Override
	public GeneralResponse saveTbExceptionMsgApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String exceptionReason = dataMap.get("exceptionReason");// 异常原因
		String exceptionReasonDetail = dataMap.get("exceptionReasonDetail");// 异常原因详细（其他）
		String strShortTrainFlag = dataMap.get("shortTrainFlag");// 短驳还是火运 0-短驳
																	// 1-火运
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-保存异常原因信息失败");
			return generalResponse;
		}
		// 判断登录信息 end

		Integer projectId = null;// 项目id
		Byte status = null;// 运单状态
		Integer orderId = null;// 运单id
		Integer trainOrderId = null;// 火运运单id
		Integer shortTrainFlag = Integer.valueOf(strShortTrainFlag);
		if (shortTrainFlag.equals(0)) {
			TbOrder tbOrder = this.viewTbOrderDataMap(dataMap);// orderId
			if (tbOrder == null) {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("未获取到正确运单信息-保存异常原因信息失败");
				return generalResponse;
			}
			projectId = tbOrder.getProjectId();
			status = tbOrder.getStatus();
			orderId = tbOrder.getId();
		} else if (shortTrainFlag.equals(1)) {
			GeneralResponse trainOrderGeneralResponse = appTbTrainOrderService.viewTbTrainOrderById(dataMap);
			Object obj = trainOrderGeneralResponse.getObj();
			TbTrainOrder tbTrainOrder = obj == null ? null : (TbTrainOrder) obj;
			if (tbTrainOrder == null) {
				generalResponse.setState(Constants.NO);
				generalResponse.setObj(null);
				generalResponse.setMsg("未获取到正确火运运单信息-保存异常原因信息失败");
				return generalResponse;
			}
			projectId = tbTrainOrder.getProjectId();
			status = tbTrainOrder.getStatus();
			trainOrderId = tbTrainOrder.getId();
		}
		TbExceptionMsg tbExceptionMsg = new TbExceptionMsg();
		tbExceptionMsg.setProjectId(projectId);// 项目id
		tbExceptionMsg.setOrderId(orderId);// 运单id
		tbExceptionMsg.setTrainOrderId(trainOrderId);// 火运运单id
		tbExceptionMsg.setOrderStatus(status);// 运单状态(阶段)
		tbExceptionMsg.setExceptionReason(exceptionReason);// 异常原因
		tbExceptionMsg.setExceptionReasonDetail(exceptionReasonDetail);// 异常原因详细（其他）
		tbExceptionMsg.setSubmitUserId(tbSystemUser.getId());// 提交人
		tbExceptionMsg.setSubmitDate(new Date());// 提交时间
		tbExceptionMsg.setAffirmStatus((byte) 0);// 确认状态 0-未确认 1-确认通过 2-确认驳回
		tbExceptionMsg.setDeleteFlag((byte) 0);// 是否删除 0 未删除 1 删除
		tbExceptionMsg.setShortTrainFlag(Byte.valueOf(strShortTrainFlag));// 短驳还是火运
																			// 0-短驳
																			// 1-火运
		tbExceptionMsg.setExceptionSource((byte) 1);// 异常来源 0-自动 1-手动
		tbExceptionMsgMapper.insertSelective(tbExceptionMsg);

		// 修改运单的异常状态 标记为异常状态
		if (shortTrainFlag.equals(0)) {
			TbOrder record = new TbOrder();
			record.setExceptionStatus((byte) 1);
			record.setId(orderId);
			tbOrderMapper.updateByPrimaryKeySelective(record);
		} else if (shortTrainFlag.equals(1)) {
			TbTrainOrder record = new TbTrainOrder();
			record.setIsException((byte) 1);
			record.setId(trainOrderId);
			tbTrainOrderMapper.updateByPrimaryKeySelective(record);
		}

		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("保存异常原因信息成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse listShortExceptionInfoByUserIdApp(Map<String, String> dataMap, Integer pageLimit) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strPage = dataMap.get("page");
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询短驳异常列表失败");
			return generalResponse;
		}
		// 判断登录信息 end
		if (StringUtil.isEmpty(strPage))
			strPage = "1";
		PageHelper.startPage(Integer.valueOf(strPage), pageLimit);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", tbSystemUser.getId());

		List<Map<String, Object>> tbExceptionMsgList = tbExceptionMsgMapper.listShortExceptionInfoByUserId(paramMap);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(tbExceptionMsgList);
		long total = pageInfo.getTotal(); // 获取总记录数

		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询短驳异常列表成功");
		generalResponse.setObj(new DataGridResult(total, tbExceptionMsgList, pageLimit));
		return generalResponse;
	}

	@Override
	public GeneralResponse viewTbExceptionMsgByIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strExceptionId = dataMap.get("exceptionId");// 异常主键
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-app端通过异常id查询异常信息失败");
			return generalResponse;
		}
		// 判断登录信息 end

		TbExceptionMsg TbExceptionMsg = tbExceptionMsgMapper.selectByPrimaryKey(Integer.valueOf(strExceptionId));

		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("app端通过异常id查询异常信息成功");
		generalResponse.setObj(TbExceptionMsg);
		return generalResponse;
	}

	@Override
	public GeneralResponse viewTbOrderByIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-通过运单id查询运单详细信息失败");
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断运单信息 start
		TbOrder tbOrder = this.viewTbOrderDataMap(dataMap);// orderId
		if (tbOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单信息-通过运单id查询运单详细信息失败");
			return generalResponse;
		}
		// 判断运单信息 end
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("通过运单id查询运单详细信息成功");
		generalResponse.setObj(tbOrder);
		return generalResponse;
	}

	@Override
	public GeneralResponse viewTbExceptionMsgByOrderIdApp(Map<String, String> dataMap) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("未获取到当前登录人信息-通过运单id查询运单异常信息失败");
			generalResponse.setObj(null);
			return generalResponse;
		}
		// 判断登录信息 end
		// 判断运单信息 start
		TbOrder tbOrder = this.viewTbOrderDataMap(dataMap);// orderId
		if (tbOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("未获取到正确运单信息-通过运单id查询运单异常信息失败");
			generalResponse.setObj(null);
			return generalResponse;
		}
		// 判断运单信息 end
		TbExceptionMsg tbExceptionMsg = new TbExceptionMsg();
		TbExceptionMsgExample example = new TbExceptionMsgExample();
		TbExceptionMsgExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(tbOrder.getId());// 运单id
		criteria.andDeleteFlagEqualTo((byte) 0);// 是否删除 未删除
		example.setOrderByClause("submit_date desc");
		List<TbExceptionMsg> tbExceptionMsgList = tbExceptionMsgMapper.selectByExample(example);
		// 目前只考虑 一个运单同一时间只有一个待处理运单的情况
		if (tbExceptionMsgList != null && tbExceptionMsgList.size() > 0) {
			tbExceptionMsg = tbExceptionMsgList.get(0);
		}

		// 转换 提报人和操作人
		if (tbExceptionMsg.getSubmitUserId() != null) {
			TbSystemUser submitUser = userService.getTbSystemUserById(tbExceptionMsg.getSubmitUserId());
			if (submitUser != null) {
				tbExceptionMsg.setSubmitUser(submitUser);
			}
		}
		if (tbExceptionMsg.getAffirmUserId() != null) {
			TbSystemUser affirmUser = userService.getTbSystemUserById(tbExceptionMsg.getAffirmUserId());
			if (affirmUser != null) {
				tbExceptionMsg.setAffirmUser(affirmUser);
			}
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("通过运单id查询运单异常信息成功");
		generalResponse.setObj(tbExceptionMsg);
		return generalResponse;
	}

	@Override
	public GeneralResponse listTurndownReasonApp(Map<String, String> dataMap, String turndownReason) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		// 判断登录信息 start
		TbSystemUser tbSystemUser = this.viewTbSystemUserDataMap(dataMap);// userId
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("未获取到当前登录人信息-查询驳回原因列表失败");
			generalResponse.setObj(null);
			return generalResponse;
		}
		// 判断登录信息 end
		String[] arrTurndownReason = turndownReason.split(";");
		// 遍历一下，去除空字符串
		List<String> turndownReasonList = new ArrayList<String>();
		for (String tmpTurndownReason : arrTurndownReason) {
			if (StringUtil.isEmpty(tmpTurndownReason))
				continue;
			turndownReasonList.add(tmpTurndownReason.trim());
		}

		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询驳回原因列表成功");
		generalResponse.setObj(turndownReasonList);
		return generalResponse;
	}

	@Override
	public GeneralResponse bulkListTbProjectApp(Map<String, String> dataMap) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("未获取到当前登录人信息-查询项目列表失败");
			generalResponse.setObj(null);
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("未获取到当前登录人信息-查询项目列表失败");
			generalResponse.setObj(null);
			return generalResponse;
		}
		List<ProjectAppHelp> projectResult = shortBargeService.appGetAllProjectByBulk(userId,sysOrgCode);
		for (ProjectAppHelp projectAppHelp : projectResult) {
			if(projectAppHelp.getTransportType() == 1) {
				TbTrainStation station = stationMapper.selectByPrimaryKey(projectAppHelp.getReceiveCargoSiteId());
				if(station != null) {
					projectAppHelp.setReceiveCargoSite(station.getStationName());
				}
			}
			if(projectAppHelp.getTransportType() == 2) {
				TbTrainStation station = stationMapper.selectByPrimaryKey(projectAppHelp.getForwardingSiteId());
				if(station != null) {
					projectAppHelp.setForwardingSiteName(station.getStationName());
				}
			}
			if(projectAppHelp.getTransportType() == 7) {
				TbTrainStation station1 = stationMapper.selectByPrimaryKey(projectAppHelp.getReceiveCargoSiteId());
				if(station1 != null) {
					projectAppHelp.setBeginSiteId(projectAppHelp.getReceiveCargoSiteId());
					projectAppHelp.setBeginSiteName(station1.getStationName());
				}
				TbTrainStation station2 = stationMapper.selectByPrimaryKey(projectAppHelp.getForwardingSiteId());
				if(station2 != null) {
					projectAppHelp.setEndSiteId(projectAppHelp.getForwardingSiteId());
					projectAppHelp.setEndSiteName(station2.getStationName());
				}
				
			}
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询项目列表成功");
		generalResponse.setObj(projectResult);
		return generalResponse;
	}

	@Override
	public GeneralResponse getContainerNum(Map<String, String> dataMap) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String stepSelectCode = dataMap.get("stepSelectCode");// 阶段选择
		String receiptCompanyId = dataMap.get("receiptCompanyId");// 收货站点id
		String sendCompanyId = dataMap.get("sendCompanyId");// 发货站点id
		String text = dataMap.get("text");// 输入的变化值
		String projectId = dataMap.get("projectId");// 项目id
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		Integer stationId = null;
		String appId = "";
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
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询集装箱号列表失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		//根据阶段选择 查询站点集装箱号
		if(Integer.valueOf(stepSelectCode.trim()) == 0) {
			stationId = Integer.valueOf(receiptCompanyId.trim());
			appId = projectId+","+stationId+","+0+","+sysOrgCode+","+0;
		}else if(Integer.valueOf(stepSelectCode.trim()) == 1) {
			stationId = Integer.valueOf(sendCompanyId.trim());
			appId = projectId+","+stationId+","+1+","+sysOrgCode+","+0;
		}else {
			
		}
		
		/*TbContainerExample example= new TbContainerExample();
		Criteria containerCriteria = example.createCriteria();
		containerCriteria.andTrainLocationIdEqualTo(stationId);
		List<TbContainer> list = containerMapper.selectByExample(example);*/
		List<Map<String, Object>> list = basicDataService.getSelectionsByStationId("testType", text.trim(), appId);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询集装箱号成功");
		generalResponse.setObj(list);
		return generalResponse;
	}

	@Override
	public GeneralResponse listFreYardAppOfDispatch(Map<String, String> dataMap) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String orderId = dataMap.get("orderId");// 运单id
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询等待调度货场列表失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询等待调度货场列表失败");
			return generalResponse;
		}
		// 判断运单信息 
		if (StringUtil.isEmpty(orderId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单信息");
			return generalResponse;
		}
		Integer orderIds = Integer.valueOf(orderId.trim());
		TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderIds);
		if (tbOrder == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单信息-通过运单id查询运单详细信息失败");
			return generalResponse;
		}
		List<TbFreightYard> tbFreightYardDetailList = null;
		//根据阶段 获取货场 0 接取 1 送达 2 汽运
		if(tbOrder.getStepSelectCode() == 0) {
			tbFreightYardDetailList = siteManageService.getAllFreightYardOfIsolated(tbOrder.getSendCompanyId());
		}else if(tbOrder.getStepSelectCode() == 1) {
			tbFreightYardDetailList = siteManageService.getFreightYardsByTrainStationId(tbOrder.getSendCompanyId());
		}else {
			tbFreightYardDetailList = siteManageService.getAllFreightYardOfIsolated(tbOrder.getSendCompanyId());
		}
		//List<TbFreightYardDetail> tbFreightYardDetailList = siteManageService.getAllFreightYards();
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询等待调度货场列表成功");
		generalResponse.setObj(tbFreightYardDetailList);
		return generalResponse;
	}

	@Override
	public GeneralResponse saveDisptachTbOrderApp(Map<String, String> dataMap) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strOrderId = dataMap.get("orderId");// 运单号
		String takeCargoPlaceId = dataMap.get("takeCargoPlaceId");// 货场名
		String takeCarogoPlaceName = dataMap.get("takeCarogoPlaceName");// 货位名
		String takeCargoSiteId = dataMap.get("takeCargoSiteId");// 货场id
		String takeCargoSiteName = dataMap.get("takeCargoSiteName");// 货位id
		
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-等待调度-提交货场货位信息失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-等待调度-提交货场货位信息失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strOrderId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单号-等待调度-提交货场货位信息失败");
			return generalResponse;
		}
		Integer orderId = Integer.valueOf(strOrderId.trim());
		Integer takeCargoPlaceId2 = Integer.valueOf(takeCargoPlaceId.trim());
		Integer takeCargoSiteId2 = Integer.valueOf(takeCargoSiteId.trim());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("takeCarogoPlaceName", takeCarogoPlaceName);
		map.put("takeCargoSiteName", takeCargoSiteName);
		map.put("takeCargoPlaceId", takeCargoPlaceId2);
		map.put("takeCargoSiteId", takeCargoSiteId2);
		int row = tbOrderMapper.updateDisptchAddOrderByMap(map);
		if (row != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("保存货位信息失败");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("保存货位信息成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse listFreYardAppOfAdd(Map<String, String> dataMap) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String stepSelectCodes = dataMap.get("stepSelectCode");// 阶段
		String sendCompanyId = dataMap.get("sendCompanyId");// 发货单位或发货站点id
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询新建运单货场列表失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询新建运单货场列表失败");
			return generalResponse;
		}
		List<TbFreightYard> tbFreightYardDetailList = null;
		Integer stepSelectCode = Integer.valueOf(stepSelectCodes.trim());
		//根据阶段 获取货场 0 接取 1 送达 2 汽运
		if(stepSelectCode == 0) {
			tbFreightYardDetailList = siteManageService.getAllFreightYardOfIsolated(Integer.valueOf(sendCompanyId.trim()));
		}else if(stepSelectCode == 1) {
			tbFreightYardDetailList = siteManageService.getFreightYardsByTrainStationId(Integer.valueOf(sendCompanyId.trim()));
		}else {
			tbFreightYardDetailList = siteManageService.getAllFreightYardOfIsolated(Integer.valueOf(sendCompanyId.trim()));
		}
		//List<TbFreightYardDetail> tbFreightYardDetailList = siteManageService.getAllFreightYards();
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询新建运单货场列表成功");
		generalResponse.setObj(tbFreightYardDetailList);
		return generalResponse;
	}

	@Override
	public GeneralResponse appUpdateOrderStatus(Map<String, String> dataMap) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strOrderId = dataMap.get("orderId");// 运单id
		String strStatus = dataMap.get("status");// 状态 3在途运载 4货位引导 5等待回单
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-更新状态失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-更新状态失败");
			return generalResponse;
		}
		if (StringUtil.isEmpty(strOrderId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到正确运单号-更新状态失败");
			return generalResponse;
		}
		Integer orderId = Integer.valueOf(strOrderId.trim());
		Byte status = Byte.valueOf(strStatus);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("status", status);
		int row = tbOrderMapper.appUpdateOrderStatus(map);
		if (row != 1) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("更新运单失败");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("更新运单状态成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse appAddOrderIsPublish(Map<String, String> dataMap) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String stepSelectCode = dataMap.get("stepSelectCode");// 阶段
		String projectId = dataMap.get("projectId");// 项目id
		boolean flag = publishJobService.isHaveJob(Integer.valueOf(projectId.trim()), Integer.valueOf(stepSelectCode.trim()));
		if(!flag) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("该项目未发布任务");
			return generalResponse;
		}
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("可获取货场货位");
		return generalResponse;
	}

	@Override
	public GeneralResponse appOrderCounts(Map<String, String> dataMap) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String strType = dataMap.get("type");// 类型
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-运单统计失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-运单统计失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		Integer type = Integer.valueOf(strType.trim());
		//type 1 当日运单   2 当月运单   3 在途运单
		Integer num = 0;
		num = shortBargeService.queryCountsOrderOfDays(tbSystemUser,type);
		generalResponse.setState(Constants.YES);
		generalResponse.setObj(num);
		generalResponse.setMsg("运单统计成功");
		return generalResponse;
	}

	@Override
	public GeneralResponse listTbOrderExceptionApp(Map<String, String> dataMap, Integer pageLimit) throws Exception {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");
		String strPage = dataMap.get("page");
		String strStatus = dataMap.get("status");
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询异常运单列表失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		if (StringUtil.isEmpty(strPage))
			strPage = "1";
		Integer page = Integer.valueOf(strPage.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-查询异常运单列表失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		Integer status = Integer.valueOf(strStatus.trim());
		DataGridResult result = shortBargeService.selectExceptionOrderByStatus(page, pageLimit, status, tbSystemUser);
		generalResponse.setState(Constants.YES);
		generalResponse.setMsg("查询异常运单列表成功");
		generalResponse.setObj(result);
		return generalResponse;
	}

	@Override
	public GeneralResponse appWaitBillentCounts(Map<String, String> dataMap) {
		GeneralResponse generalResponse = new GeneralResponse(new JSONArray());
		String strUserId = dataMap.get("userId");// 用户id
		String sysOrgCode = dataMap.get("sysOrgCode");// 
		if (StringUtil.isEmpty(strUserId)) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-计费确认统计失败");
			return generalResponse;
		}
		Integer userId = Integer.valueOf(strUserId.trim());
		TbSystemUser tbSystemUser = userService.getTbSystemUserById(userId);
		if (tbSystemUser == null) {
			generalResponse.setState(Constants.NO);
			generalResponse.setObj(null);
			generalResponse.setMsg("未获取到当前登录人信息-计费确认统计失败");
			return generalResponse;
		}
		tbSystemUser.setSysOrgCode(sysOrgCode);
		Integer num = 0;
		num = shortBargeService.appWaitBillentCounts(tbSystemUser);
		generalResponse.setState(Constants.YES);
		generalResponse.setObj(num);
		generalResponse.setMsg("查询计费确认统计成功");
		return generalResponse;
	}

}
