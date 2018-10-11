package com.shenhesoft.logistics.business.shortbarge.order.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import com.shenhesoft.logistics.app.PushUtil;
import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.business.helpPojo.TbBulkSubForwardDetail;
import com.shenhesoft.logistics.business.helpPojo.TbOrderCarDetail;
import com.shenhesoft.logistics.business.helpPojo.TbReceipterDetail;
import com.shenhesoft.logistics.business.helpPojo.TbSubForwardDetail;
import com.shenhesoft.logistics.business.helpPojo.TbWaitDispatchDetail;
import com.shenhesoft.logistics.business.helpPojo.TbguideDetail;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.mapper.TbProjectDistributionMapper;
import com.shenhesoft.logistics.business.mapper.TbStockMapper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderMapper;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrderExample;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrderExample.Criteria;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
import com.shenhesoft.logistics.business.project.manage.ProjectManagmentService;
import com.shenhesoft.logistics.business.shortbarge.order.ShortBargeOrderService;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.CostOrderFinance;
import com.shenhesoft.logistics.finance.CostOrderFinanceService;
import com.shenhesoft.logistics.finance.ShortOrderFinance;
import com.shenhesoft.logistics.finance.ShortOrderFinanceService;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.finance.mapper.ShortOrderFinanceMapper;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.helpPojo.ProjectAppHelp;
import com.shenhesoft.logistics.manage.mapper.TbAnchoredRecordMapper;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbContainerMapper;
import com.shenhesoft.logistics.manage.mapper.TbCustomerMapper;
import com.shenhesoft.logistics.manage.mapper.TbFreightYardMapper;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.pojo.box.TbContainer;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.notice.TbNotice;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.distribution.TbProjectDistribution;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.search.OrderSearch;
import com.shenhesoft.logistics.system.CodeService;

/**
 * @description
 * 
 * @author shilvfei
 * 
 * @date 2018年1月24日
 */
@Service
public class ShortBargeOrderServiceImpl implements ShortBargeOrderService {

	@Autowired
	private TbProjectMapper tbProjectMapper;

	@Autowired
	private TbOrderMapper tbOrderMapper;

	@Autowired
	private TbTrainOrderMapper tbTrainOrderMapper;

	@Autowired
	private TbStockMapper tbStockMapper;
	/**
	 * 短驳运单财务表-业务层接口.
	 */
	@Autowired
	private ShortOrderFinanceService shortOrderFinanceService;
	@Autowired 
	private ProjectManagmentService projectManagmentService;
	/**
	 * 短驳运单财务表-业务层接口.
	 */
	@Autowired
	private ShortOrderFinanceMapper orderFinanceMapper;
	
	@Autowired
	private CostOrderFinanceService costOrderFinanceService;

	@Autowired
	private TbBranchGroupMapper branchGroupMapper;

	@Autowired
	private TbProjectDistributionMapper tbProjectDistributionMapper;
	
	@Autowired
	private TbCustomerMapper customerMapper;
	
	@Autowired
	private TbTrainStationMapper stationMapper;
	
	@Autowired
	private TbFreightYardMapper tbFreightYardMapper;
	
	@Autowired
	private TbContainerMapper containerMapper;
	
	@Autowired
	private TbAnchoredRecordMapper anchoredRecordMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	@Autowired
	private CodeService codeService;
	
	/*@Autowired
	private BranchGroupService branchGroupService;*/
	
	@Override
	public DataGridResult selectBoxManagerOrderByPages(Integer page, Integer limit, byte type, OrderSearch searchOrder,
			TbSystemUser user,byte isBulk) {
		TbOrderExample example = new TbOrderExample();
		example.setOrderByClause("A.id desc");
		// 查询条件
		Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagEqualTo((byte) 0);
		
		criteria.andIsCancelEqualTo((byte) 0);
		criteria.andBranchIdIn(branchGroupMapper.selectDotBranchIdsByUid(user.getId()));// 分支机构id
		/**
		 * type :0 :全部 1 :运单 2 :异常运单 3 :历史运单
		 */
		if (type == 1) {
			criteria.andExceptionStatusEqualTo((byte) 0);
			criteria.andStatusNotEqualTo((byte) 7);
		}
		if (type == 2) {
			criteria.andExceptionStatusEqualTo((byte) 1);
		}

		if (type == 3) {
			criteria.andExceptionStatusEqualTo((byte) 0);
			criteria.andStatusEqualTo((byte) 7);
		}
		orderCriteria(searchOrder, criteria); // 条件查询
		if(isBulk == 3) {
			criteria.andSysOrgCodeEqualTo(user.getSysOrgCode());
		}else {
			criteria.andTypeEqualTo(isBulk);
			criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		}
		// 分页处理，显示第一页的20条数据
		PageHelper.startPage(page, limit);
		List<TbOrder> list = tbOrderMapper.selectByExample(example);// 查询
		for (TbOrder tbOrder : list) {
			Integer orderId = tbOrder.getId();
			Map<String, Object> form = new  HashMap<>();
			form.put("orderId", orderId);
			form.put("sysOrgCode", user.getSysOrgCode());
			List<Map<String,Object>> shortOrderFinances = orderFinanceMapper.getShortOrderFinances(form);
			if(shortOrderFinances !=null && shortOrderFinances.size()!=0){
				Map<String, Object> map = shortOrderFinances.get(0);
				String shOrderFinId = (String)map.get("shOrderFinId");
				tbOrder.setShOrderFinId(shOrderFinId);
			}
			if(tbOrder.getContainerOneSendNet() == null) {
				tbOrder.setContainerOneSendNet(new BigDecimal(0));
			}
			if(tbOrder.getContainerTwoSendNet() == null) {
				tbOrder.setContainerTwoSendNet(new BigDecimal(0));
			}
			if(tbOrder.getContainerOneReceiptNet() == null) {
				tbOrder.setContainerOneReceiptNet(new BigDecimal(0));
			}
			if(tbOrder.getContainerTwoReceiptNet() == null) {
				tbOrder.setContainerTwoReceiptNet(new BigDecimal(0));
			}
			tbOrder.setExportSumSendNet(tbOrder.getContainerOneSendNet().add(tbOrder.getContainerTwoSendNet()));
			tbOrder.setExportSumReceiptNet(tbOrder.getContainerOneReceiptNet().add(tbOrder.getContainerTwoReceiptNet()));
		}
		// 取分页信息
		PageInfo<TbOrder> pageInfo = new PageInfo<TbOrder>(list);
		long total = pageInfo.getTotal(); // 获取总记录数

		return new DataGridResult(total, list, limit);
	}
	
	@Override
	public TbOrder selectBoxManagerOrderById(Integer id) {
		return tbOrderMapper.selectBoxManagerOrderById(id);
	}

	@Override
	public TbOrder getDispatchMsgByProjectId(Integer id) {
		return tbOrderMapper.getDispatchMsgByProjectId(id);
	}

	@Override
	public List<TbContainer> getTbContainerNumbers() {
		return tbOrderMapper.getTbContainerNumbers();
	}

	@Override
	@Transactional
	public boolean dispatchAdd(TbWaitDispatchDetail tbWaitDispatchDetail, TbSystemUser user) throws Exception {
		if (tbWaitDispatchDetail.getIsAgree() != 0) {
			// 更新order
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", tbWaitDispatchDetail.getOrderId());
			map.put("containerNumber1", tbWaitDispatchDetail.getContainerNumber1());
			map.put("containerNumber2", tbWaitDispatchDetail.getContainerNumber2());
			// map.put("containerNumber1Id",
			// tbWaitDispatchDetail.getContainerNumber1Id());
			// map.put("containerNumber2Id",
			// tbWaitDispatchDetail.getContainerNumber2Id());
			map.put("takeCargoPlaceId", tbWaitDispatchDetail.getTakeCargoPlaceId());
			map.put("takeCarogoPlaceName", tbWaitDispatchDetail.getTakeCarogoPlaceName());
			map.put("takeCargoSiteId", tbWaitDispatchDetail.getTakeCargoSiteId());
			map.put("takeCargoSiteName", tbWaitDispatchDetail.getTakeCargoSiteName());
			map.put("status", Constants.SMS_POINT_WAIT_SEND);
			int row = tbOrderMapper.updateDispatchOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
			TbOrder order = tbOrderMapper.selectByPrimaryKey(tbWaitDispatchDetail.getOrderId());
			
			//任务数减一
			//更新任务数
			TbProjectDistribution projectDistribution = tbProjectDistributionMapper.selectByPrimaryKey(order.getProjectDistributionId());
			//更新已领任务数
			if(projectDistribution.getAlreadyRecNum()==null){
				projectDistribution.setAlreadyRecNum(0);
			}
			projectDistribution.setAlreadyRecNum(projectDistribution.getAlreadyRecNum()+1);//已领任务+1
			//待领取任务-1
			if(projectDistribution.getCarNum()==null){
				projectDistribution.setCarNum(0);
			}
			projectDistribution.setCarNum(projectDistribution.getCarNum()-1);//待领取任务-1
			tbProjectDistributionMapper.updateByPrimaryKey(projectDistribution);//更新到数据库
			
			//根据order获取司机id
			Integer driverId = order.getDriverId();
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
			List<TbOrder> list = tbOrderMapper.selectByExample(orderExample);
			for (TbOrder orderW : list) {
				orderW.setIsCancel((byte)1);//已取消
				orderW.setDeleteFlag((byte)1);//已删除
				orderW.setDeleteTime(new Date());
				tbOrderMapper.updateByPrimaryKey(orderW);//更新数据库
			}
			PushUtil pushUtil = new PushUtil();
			pushUtil.sendAndroidCustomizedcast(driverId.toString(), "您已接单成功", "请查看取货地点", "2");
			
			//消息通知
			anchoredRecordMapper.insertNoticeByApp(driverId,"您已接单成功, 请查看取货地点");
			
			//更新集装箱状态为装车中，并绑定项目id
			if(order.getProjectType() == 0) {
				Map<String,Object> cMap = new HashMap<>();
				if(tbWaitDispatchDetail.getContainerNumber1() != "") {
					cMap.put("status", 3);
					cMap.put("projectId", order.getProjectId());
					cMap.put("containerId", tbWaitDispatchDetail.getContainerNumber1());
					cMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
					containerMapper.updateContainerStatus(cMap);
				}
				if(tbWaitDispatchDetail.getContainerNumber2() != "") {
					cMap.put("status", 3);
					cMap.put("projectId", order.getProjectId());
					cMap.put("containerId", tbWaitDispatchDetail.getContainerNumber2());
					cMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
					containerMapper.updateContainerStatus(cMap);
				}
			}
		} else {
			TbOrder orderc = tbOrderMapper.selectByPrimaryKey(tbWaitDispatchDetail.getOrderId());
			// 不同意做什么操作 逻辑删除-放入回收站
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", tbWaitDispatchDetail.getOrderId());
			map.put("userId", user.getId());
			map.put("userName", user.getName());
			map.put("remark", tbWaitDispatchDetail.getRemark());
			map.put("isCancel", Constants.ORDER_CANCLE_YES);// 运单状态 已取消
			map.put("deleteFlag", Constants.DELETE_FLAG_TRUE);
			int row = tbOrderMapper.updateDeleteOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
			//取消发运 推送消息
			PushUtil pushUtil = new PushUtil();
			pushUtil.sendAndroidCustomizedcast(orderc.getDriverId().toString(), "您的运单被取消", "请重新接单或联系调度员", String.valueOf(Constants.SMS_POINT_WAIT_DIS));
			
			//消息通知
			anchoredRecordMapper.insertNoticeByApp(orderc.getDriverId(),"您的运单被取消, 请重新接单或联系调度员");
		}
		return true;
	}

	@Override
	@Transactional
	public int subForwardingAdd(TbSubForwardDetail tbSubForwardDetail, TbSystemUser user) throws Exception {
		if (tbSubForwardDetail.getIsAgree() != 1) { // 0-同意发运 1-不同意发运
			// 更新库存
			// 为接取状态下 货场货位是独立的 不做操作 为送达状态下 更改发货站点货场货位库存
			TbOrder order = tbOrderMapper.selectByPrimaryKey(tbSubForwardDetail.getOrderId());
			if (order.getStepSelectCode() == 0 || order.getStepSelectCode() == 2) {
				// 接取 + 汽运 不做处理
			} else {
				int i = 0;
				int j = 0;
				if (tbSubForwardDetail.getContainerOneSendNet() != null) {
					i = 1;
				}
				if (tbSubForwardDetail.getContainerTwoSendNet() != null) {
					j = 1;
				}
				// 获得送达的货场货位
				TbProject project = tbProjectMapper.selectDetailProjectById(order.getProjectId());
				Map<String, Object> stockMap = new HashMap<String, Object>();
				stockMap.put("projectId", project.getId());
				stockMap.put("freightYardId", order.getTakeCargoPlaceId());
				stockMap.put("cargoLocationId", order.getTakeCargoSiteId());
				// 发货站点
				stockMap.put("stationId", project.getForwardingSiteId());
				// stockMap.put("type", (byte)0);
				stockMap.put("type", (byte) 1);
				TbStock oldStock = tbTrainOrderMapper.selectStockByMap(stockMap);
				if (oldStock != null) {
					//送达阶段 发货重量不能大于现有库存
					BigDecimal sw = tbSubForwardDetail.getContainerOneSendNet().add(tbSubForwardDetail.getContainerTwoSendNet());
					if (oldStock.getCurrentQty() != null) {
						int compareResult = oldStock.getCurrentQty().compareTo(sw);
						//-1表示小于
						if(compareResult == -1) {
							//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							return 602;
						}
					}
					oldStock.setOutQty(oldStock.getOutQty()==null?new BigDecimal(0): oldStock.getOutQty());
					if (oldStock.getOutQty() != null || (oldStock.getOutQty().equals(BigDecimal.ZERO))) {
						oldStock.setOutQty(oldStock.getOutQty().add(tbSubForwardDetail.getContainerOneSendNet())
								.add(tbSubForwardDetail.getContainerTwoSendNet()));
					}
					if (oldStock.getCurrentQty() != null || !(oldStock.getCurrentQty().equals(BigDecimal.ZERO))) {
						oldStock.setCurrentQty(
								oldStock.getCurrentQty().subtract(tbSubForwardDetail.getContainerOneSendNet())
										.subtract(tbSubForwardDetail.getContainerTwoSendNet()));
					}
					oldStock.setContainerNum(oldStock.getContainerNum()==null?0: oldStock.getContainerNum());
					if (oldStock.getContainerNum() != null || oldStock.getContainerNum() != 0) {
						oldStock.setContainerNum(oldStock.getContainerNum() - (i + j));
					}
					oldStock.setAdjustDate(new Date());
					int rstock = tbStockMapper.updateByPrimaryKeySelective(oldStock);
					if (rstock != 1) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return 603;
					}
				}else {
					return 607;
				}
			}
			//更新tb_order表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", tbSubForwardDetail.getOrderId());
			map.put("sendTare", tbSubForwardDetail.getSendTare());
			map.put("sendGross", tbSubForwardDetail.getSendGross());
			map.put("containerOneSendNet", tbSubForwardDetail.getContainerOneSendNet());
			map.put("containerTwoSendNet", tbSubForwardDetail.getContainerTwoSendNet());
			map.put("testIndicators", tbSubForwardDetail.getTestIndicators());
			map.put("img", tbSubForwardDetail.getImg());
			map.put("status", Constants.SMS_POINT_ON_ROAD);
			//map.put("pieceNumber", tbSubForwardDetail.getPieceNumber());
			int row = tbOrderMapper.updateSubForwardingOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return 601;
			}
			// 推送消息
			PushUtil pushUtil = new PushUtil();
			pushUtil.sendAndroidCustomizedcast(order.getDriverId().toString(), "您的装货货场已分配", "请查看装货地点", String.valueOf(Constants.SMS_POINT_WAIT_SEND));
			
			//消息通知
			anchoredRecordMapper.insertNoticeByApp(order.getDriverId(),"您的装货货场已分配, 请查看装货地点");
			
			//更新集装箱状态为运输中，并绑定项目id
			if(order.getProjectType() == 0) {
				Map<String,Object> cMap = new HashMap<>();
				/*if(order.getOrderOrigin() == 2) {
					cMap.put("sysOrgCode", user.getSysOrgCode());
				}else {
					cMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
				}*/
				cMap.put("sysOrgCode", user.getSysOrgCode());
				if(order.getContainerNumber1() != "") {
					cMap.put("status", 2);
					cMap.put("projectId", order.getProjectId());
					cMap.put("containerId", order.getContainerNumber1());
					containerMapper.updateContainerStatus(cMap);
				}
				if(order.getContainerNumber2() != "") {
					cMap.put("status", 2);
					cMap.put("projectId", order.getProjectId());
					cMap.put("containerId", order.getContainerNumber2());
					containerMapper.updateContainerStatus(cMap);
				}
			}
		} else {
			// 进入回收站
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", tbSubForwardDetail.getOrderId());
			map.put("userId", user.getId());
			map.put("userName", user.getName());
			map.put("remark", tbSubForwardDetail.getRemark());
			map.put("isCancel", Constants.ORDER_CANCLE_YES);// 运单状态 已取消
			map.put("deleteFlag", Constants.DELETE_FLAG_TRUE);
			int row = tbOrderMapper.updateDeleteOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return 604;
			}
			TbOrder order = tbOrderMapper.selectByPrimaryKey(tbSubForwardDetail.getOrderId());
			
			//更新任务已领数 和待领数
			//更新任务数
			TbProjectDistribution projectDistribution = tbProjectDistributionMapper.selectByPrimaryKey(order.getProjectDistributionId());
			//更新已领任务数
			if(projectDistribution.getAlreadyRecNum()==null){
				projectDistribution.setAlreadyRecNum(0);
			}
			projectDistribution.setAlreadyRecNum(projectDistribution.getAlreadyRecNum()-1);//已领任务-1
			//待领取任务+1
			if(projectDistribution.getCarNum()==null){
				projectDistribution.setCarNum(0);
			}
			projectDistribution.setCarNum(projectDistribution.getCarNum()+1);//待领取任务+1
			tbProjectDistributionMapper.updateByPrimaryKey(projectDistribution);//更新到数据库
			
			//更新运单状态
			//根据order获取司机id
			Integer driverId = order.getDriverId();
			map  = new HashMap<>();
			map.put("driverId", driverId);
			map.put("status",  Constants.DRIVER_ORDER_STATUS_YES);
			//更新司机订单状态
			tbOrderMapper.updateDriverOrderStatus(map);//未接单
			
			PushUtil pushUtil = new PushUtil();
			pushUtil.sendAndroidCustomizedcast(driverId.toString(), "您的运单被取消", "请重新接单或联系调度员", "2");
			
			//消息通知
			anchoredRecordMapper.insertNoticeByApp(driverId,"您的运单被取消,请重新接单或联系调度员");
			
			//取消发运 根据项目模式更新集装箱状态
			if(order.getProjectType() == 0) {
				if(order.getStepSelectCode() == 0) {
					Map<String,Object> cMap = new HashMap<>();
					/*if(order.getOrderOrigin() == 2) {
						cMap.put("sysOrgCode", user.getSysOrgCode());
					}else {
						cMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
					}*/
					cMap.put("sysOrgCode", user.getSysOrgCode());
					if(order.getContainerNumber1() != "") {
						cMap.put("status", 0);
						cMap.put("projectId", null);
						cMap.put("containerId", order.getContainerNumber1());
						containerMapper.updateContainerStatus(cMap);
					}
					if(order.getContainerNumber2() != "") {
						cMap.put("status", 0);
						cMap.put("projectId", null);
						cMap.put("containerId", order.getContainerNumber2());
						containerMapper.updateContainerStatus(cMap);
					}
				}else if(order.getStepSelectCode() == 1){
					//项目模式 0 汽运  1 接取 2 送达 3 火运 4 接取+火运 5 火运+送达 6 联运 7 接取+送达
					if(order.getTransportType() == 2) {
						Map<String,Object> cMap = new HashMap<>();
						/*if(order.getOrderOrigin() == 2) {
							cMap.put("sysOrgCode", user.getSysOrgCode());
						}else {
							cMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
						}*/
						cMap.put("sysOrgCode", user.getSysOrgCode());
						if(order.getContainerNumber1() != "") {
							cMap.put("status", 0);
							cMap.put("projectId", null);
							cMap.put("containerId", order.getContainerNumber1());
							containerMapper.updateContainerStatus(cMap);
						}
						if(order.getContainerNumber2() != "") {
							cMap.put("status", 0);
							cMap.put("projectId", null);
							cMap.put("containerId", order.getContainerNumber2());
							containerMapper.updateContainerStatus(cMap);
						}
					}else if(order.getTransportType() == 5 || order.getTransportType() == 6 || order.getTransportType() == 7) {
						Map<String,Object> cMap = new HashMap<>();
						/*if(order.getOrderOrigin() == 2) {
							cMap.put("sysOrgCode", user.getSysOrgCode());
						}else {
							cMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
						}*/
						cMap.put("sysOrgCode", user.getSysOrgCode());
						if(order.getContainerNumber1() != "") {
							cMap.put("status", 1);
							cMap.put("projectId", order.getProjectId());
							cMap.put("containerId", order.getContainerNumber1());
							containerMapper.updateContainerStatus(cMap);
						}
						if(order.getContainerNumber2() != "") {
							cMap.put("status", 1);
							cMap.put("projectId", order.getProjectId());
							cMap.put("containerId", order.getContainerNumber2());
							containerMapper.updateContainerStatus(cMap);
						}
					}
				}
				
			}
		}
		return 1;
	}

	@Override
	@Transactional
	public boolean carryAdd(TbWaitDispatchDetail tbWaitDispatchDetail, TbSystemUser user) {
		if (tbWaitDispatchDetail.getIsAgree() != 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", tbWaitDispatchDetail.getOrderId());
			map.put("status", Constants.SMS_POINT_LOCATION_GUIDE);
			int row = tbOrderMapper.updateCarryAddOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
		} else {
			// 进入回收站
			/*Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", tbWaitDispatchDetail.getOrderId());
			map.put("userId", user.getId());
			map.put("userName", user.getName());
			map.put("remark", tbWaitDispatchDetail.getRemark());
			map.put("deleteFlag", Constants.DELETE_FLAG_TRUE);
			int row = tbOrderMapper.updateDeleteOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}*/
		}
		return true;
	}

	@Override
	public boolean guideAdd(TbguideDetail tbguideDetail, TbSystemUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", tbguideDetail.getOrderId());
		map.put("status", Constants.SMS_POINT_WAIT_RETURN);
		map.put("distributionCargoPlace", tbguideDetail.getDistributionCargoPlace());
		map.put("distributionCargoSite", tbguideDetail.getDistributionCargoSite());
		map.put("distributionCargoPlaceId", tbguideDetail.getDistributionCargoPlaceId());
		map.put("distributionCargoSiteId", tbguideDetail.getDistributionCargoSiteId());
		int row = tbOrderMapper.updateGuideAddOrderByMap(map);
		if (row != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public boolean receipterAdd(TbReceipterDetail tbReceipterDetail, TbSystemUser user) {
		TbOrder order = tbOrderMapper.selectByPrimaryKey(tbReceipterDetail.getOrderId());
		
		if(order.getReceipterDate()!=null){
			return false;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", tbReceipterDetail.getOrderId());
		map.put("receiptTare", tbReceipterDetail.getReceiptTare());
		map.put("receiptGross", tbReceipterDetail.getReceiptGross());
		map.put("containerOneReceiptNet", tbReceipterDetail.getContainerOneReceiptNet());
		map.put("containerTwoReceiptNet", tbReceipterDetail.getContainerTwoReceiptNet());
		if(tbReceipterDetail.getImgType() != null) {
			if(tbReceipterDetail.getImgType() == 0) {
				//是app上传成功后 后台不需要再改变图片了
				map.put("img", null);
			}
		}else {
			map.put("img", tbReceipterDetail.getImg());
		}
		map.put("status", Constants.SMS_POINT_WAIT_RETURN);//还是等待回单
		map.put("receipterDate", new Date());//更新回单完成的时间
		int row = tbOrderMapper.updateReceipterOrderByMap(map);
		if (row != 1) {
			return false;
		}
		
		//更新司机运单状态
		map = new HashMap<>();
		Integer driverId = order.getDriverId();
		map.put("driverId",driverId);
		map.put("status",  Constants.DRIVER_ORDER_STATUS_YES);
		tbOrderMapper.updateDriverOrderStatus(map);//司机可接单
		
		//更新任务数
		TbProjectDistribution projectDistribution = tbProjectDistributionMapper.selectByPrimaryKey(order.getProjectDistributionId());
		//更新完成任务数
		if(projectDistribution.getCompleteNum()==null){
			projectDistribution.setCompleteNum(0);
		}
		projectDistribution.setCompleteNum(projectDistribution.getCompleteNum()+1);
		//更新已领任务数
		if(projectDistribution.getAlreadyRecNum()==null){
			projectDistribution.setAlreadyRecNum(0);
		}
		projectDistribution.setAlreadyRecNum(projectDistribution.getAlreadyRecNum()-1);
		tbProjectDistributionMapper.updateByPrimaryKey(projectDistribution);//更新到数据库
		
		// 保存短驳财务信息
		addShortOrderFinanceByTbOrder(order, user);
		
		// 保存短驳 费用信息
		addCostOrderFinanceByTborder(order,user);
		
		// 更新库存
		// 为接取状态下 更改发货站点货场货位库存 为送达状态下 货场货位是独立的 不做操作
		if (order.getStepSelectCode() == 0) {
			int i = 0;
			int j = 0;
			BigDecimal isHaveTwo = new BigDecimal(0);
			if (tbReceipterDetail.getContainerOneReceiptNet() != null) {
				// 集装箱 库存表添加集装箱数量
				if (order.getProjectType() == 0) {
					i = 1;
				}
			}
			if (tbReceipterDetail.getContainerTwoReceiptNet() != null) {
				if (order.getProjectType() == 0) {
					j = 1;
				}
				isHaveTwo = tbReceipterDetail.getContainerTwoReceiptNet();
			}
			// 获得送达的货场货位
			TbProject project = tbProjectMapper.selectDetailProjectById(order.getProjectId());
			Map<String, Object> stockMap = new HashMap<String, Object>();
			stockMap.put("projectId", project.getId());
			stockMap.put("freightYardId", order.getDistributionCargoPlaceId());
			stockMap.put("cargoLocationId", order.getDistributionCargoSiteId());
			// 发货站点
			stockMap.put("stationId", project.getReceiveCargoSiteId());
			stockMap.put("type", Constants.STOCK_TYPE_BEGIN);//发货站点
			TbStock oldStock = tbTrainOrderMapper.selectStockByMap(stockMap);
			if (oldStock != null) {
				oldStock.setContainerNum(oldStock.getContainerNum() + (i + j));
				oldStock.setEnterQty(
						oldStock.getEnterQty().add(tbReceipterDetail.getContainerOneReceiptNet()).add(isHaveTwo));
				oldStock.setCurrentQty(
						oldStock.getCurrentQty().add(tbReceipterDetail.getContainerOneReceiptNet()).add(isHaveTwo));
				oldStock.setAdjustDate(new Date());
				int rstock = tbStockMapper.updateByPrimaryKeySelective(oldStock);
				if (rstock != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return false;
				}
			} else {
				TbStock newStock = new TbStock();
				newStock.setStationId(project.getReceiveCargoSiteId());
				newStock.setStationName(project.getReceiveCargoSite());
				newStock.setFreightYardId(order.getDistributionCargoPlaceId());
				newStock.setFreightYardName(order.getDistributionCargoPlace());
				newStock.setCargoLocationId(order.getDistributionCargoSiteId());
				newStock.setCargoLocationName(order.getDistributionCargoSite());
				newStock.setEnterQty(tbReceipterDetail.getContainerOneReceiptNet().add(isHaveTwo));
				newStock.setOutQty(new BigDecimal(0));
				newStock.setContainerNum(i + j);
				newStock.setCurrentQty(tbReceipterDetail.getContainerOneReceiptNet().add(isHaveTwo));
				newStock.setAdjustQty(new BigDecimal(0));
				newStock.setProjectId(project.getId());
				newStock.setProjectCode(project.getProjectCode());
				newStock.setProjectType(project.getProjectType());
				// 接取 是始发站点 + 库存 送达时到达站点 - 库存
				newStock.setType(Constants.STOCK_TYPE_BEGIN);
				newStock.setAdjustDate(new Date());
				int adds = tbStockMapper.insertSelective(newStock);
				if (adds != 1) {
					// return LogisticsResult.build(2, "更新库存失败");
					return false;
				}
			}
		} else {
			// 送达 + 汽运 不做处理
		}
		//更新集装箱状态为空闲，并解除项目id
		if(order.getProjectType() == 0) {
			Map<String,Object> cMap = new HashMap<>();
			/*if(order.getOrderOrigin() == 2) {
				cMap.put("sysOrgCode", user.getSysOrgCode());
			}else {
				cMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
			}*/
			cMap.put("sysOrgCode", user.getSysOrgCode());
			if(order.getContainerNumber1() != "") {
				//根据项目模式 改变集装箱卸货状态
				//项目模式 0 汽运  1 接取 2 送达 3 火运 4 接取+火运 5 火运+送达 6 联运 7 接取+送达
				if(order.getTransportType() == 1 || order.getTransportType() == 2 || order.getTransportType() == 5) {
					cMap.put("status", 0);
					cMap.put("projectId", null);
				}else if(order.getTransportType() == 4) {
					cMap.put("status", 1);
					cMap.put("projectId", order.getProjectId());
				}else if(order.getTransportType() == 6 || order.getTransportType() == 7) {
					//0 接取 1 送达
					if(order.getStepSelectCode() == 0) {
						cMap.put("status", 1);
						cMap.put("projectId", order.getProjectId());
					}else {
						cMap.put("status", 0);
						cMap.put("projectId", null);
					}
				}
				cMap.put("containerId", order.getContainerNumber1());
				containerMapper.updateContainerStatus(cMap);
			}
			if(order.getContainerNumber2() != "") {
				//根据项目模式 改变集装箱卸货状态
				//项目模式 0 汽运  1 接取 2 送达 3 火运 4 接取+火运 5 火运+送达 6 联运 7 接取+送达
				if(order.getTransportType() == 1 || order.getTransportType() == 2 || order.getTransportType() == 5) {
					cMap.put("status", 0);
					cMap.put("projectId", null);
				}else if(order.getTransportType() == 4) {
					cMap.put("status", 1);
					cMap.put("projectId", order.getProjectId());
				}else if(order.getTransportType() == 6 || order.getTransportType() == 7) {
					//0 接取 1 送达
					if(order.getStepSelectCode() == 0) {
						cMap.put("status", 1);
						cMap.put("projectId", order.getProjectId());
					}else {
						cMap.put("status", 0);
						cMap.put("projectId", null);
					}
				}
				cMap.put("containerId", order.getContainerNumber2());
				containerMapper.updateContainerStatus(cMap);
			}
		}
		return true;
	}
    private void addCostOrderFinanceByTborder(TbOrder order, TbSystemUser user) {
        if (order == null)
            return;
        CostOrderFinance costOrderFinance = new CostOrderFinance();
        // 项目id
        costOrderFinance.setProjectId(order.getProjectId());
        // 运单id
        costOrderFinance.setOrderId(order.getId());
        // 财务状态(0-待确认、1-待计算、2-待审核、3-已审核)
        costOrderFinance.setFinanceStatus((byte)0);
        // 是否被打包(0-未打包 1-已打包)
        costOrderFinance.setPackFlag((byte)0);
        // 是否删除(0-未删除 1-删除)
        costOrderFinance.setDeleteFlag((byte)0);
        // 毛重
        costOrderFinance.setSendGross(order.getSendGross());
        // 净重
        if(order.getContainerOneSendNet() != null ){
            if(order.getContainerTwoSendNet() != null){
                costOrderFinance.setSendNet(order.getContainerOneSendNet().add(order.getContainerTwoSendNet()));
            }else{
                costOrderFinance.setSendNet(order.getContainerOneSendNet());
            }
        }
        // 皮重
        costOrderFinance.setSendTare(order.getSendTare());
        
        //获取货物单价
        LogisticsResult logisticsResult = projectManagmentService.selectProject(order.getProjectId());
        
        if(logisticsResult.getStatus()==200){
            ProjectDetail projectDetail = (ProjectDetail)logisticsResult.getData();
            if(projectDetail!=null){
                //货物单价
                costOrderFinance.setCargoUnitPrice(projectDetail.getCargoPrice());
                
                if(costOrderFinance.getCargoUnitPrice()!=null && costOrderFinance.getSendNet() != null){
                    //货物价格
                    costOrderFinance.setCargoPrice(costOrderFinance.getCargoUnitPrice().multiply(costOrderFinance.getSendNet()));
                }
            }
        }
        
        // 创建时间
        costOrderFinance.setCreateDate(new Date());
        // 创建人
        costOrderFinance.setCreateUserId(user.getId());
        costOrderFinanceService.addCostOrderFinance(costOrderFinance);
        
        if(AppSession.getCurrentSysOrgCode() == null) {
        	  BranchGroupLink branchGroupLink = new BranchGroupLink();
	  	      branchGroupLink.setId(AppUtils.randomUUID());
	  	      branchGroupLink.setRowId(costOrderFinance.getCostOrderFinId());
	  	      branchGroupLink.setTabName("tb_cost_order_finance");
	  	      branchGroupLink.setTabComment("费用对账");
	  	      branchGroupLink.setSysOrgCode(user.getSysOrgCode());
	  	      branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
        }
        
    }
	private void addShortOrderFinanceByTbOrder(TbOrder order, TbSystemUser user) {
      if (null==order || null==order.getId())
          return;
      Map<String, Object> form = ImmutableMap.of("orderId", order.getId(),"sysOrgCode", user.getSysOrgCode());
      List<Map<String, Object>> existOrderFinances = shortOrderFinanceService.getShortOrderFinances(form);
      if(!CollectionUtils.isEmpty(existOrderFinances)){
        return;
      }
      ShortOrderFinance shortOrderFinance = new ShortOrderFinance();
      // 项目id
      shortOrderFinance.setProjectId(order.getProjectId());
      // 运单id
      shortOrderFinance.setOrderId(order.getId());
      // 财务状态(0-待确认、1-待计算、2-待审核、3-已审核)
      shortOrderFinance.setFinanceStatus(0);
      // 是否被打包(0-未打包 1-已打包)
      shortOrderFinance.setPackFlag(0);
      // 是否删除(0-未删除 1-删除)
      shortOrderFinance.setDeleteFlag(0);
      // 运输费用
      shortOrderFinance.setShortBargeCost(order.getShortBargeCost());
      // 补加金额
      shortOrderFinance.setSubsidy(new BigDecimal(0));
      // 应付费用
      shortOrderFinance.setShouldPayFigure(order.getShortBargeCost());
      // 创建时间
      shortOrderFinance.setCreateDate(DateUtils.date2Str(DateUtils.datetimeFormat));
      // 创建人
      shortOrderFinance.setCreateUserId(user.getId());
      shortOrderFinanceService.addShortOrderFinance(shortOrderFinance);
      
      if(AppSession.getCurrentSysOrgCode() == null) {
    	  BranchGroupLink branchGroupLink = new BranchGroupLink();
          branchGroupLink.setId(AppUtils.randomUUID());
          branchGroupLink.setRowId(shortOrderFinance.getShOrderFinId());
          branchGroupLink.setTabName("tb_short_order_finance");
          branchGroupLink.setTabComment("运费支出");
          branchGroupLink.setSysOrgCode(user.getSysOrgCode());
          branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
      }
      
  }
	@Override
	@Transactional
	public boolean delete(List<Integer> idList, String reason, TbSystemUser user) {
		for (Integer ids : idList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", ids);
			map.put("reason", reason);
			map.put("userName", user.getName());
			map.put("userId", user.getId());
			int row = tbOrderMapper.deleteOrderByParam(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shenhesoft.logistics.business.shortBargeService.ShortBargeService#
	 * orderAdd(com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder,
	 * java.lang.Integer)
	 */
	@Override
	@Transactional
	public boolean orderAdd(TbOrder tbOrder, TbSystemUser user, byte type) {

		//查询任务id
		Map<String,Object> map = new HashMap<>();
		Byte shortType = tbOrder.getStepSelectCode();
		if(shortType==0){
			shortType=1;
		}else if(shortType==1){
			shortType=2;
		}else if(shortType==2){
			shortType=3;
		}
		map.put("projectId", tbOrder.getProjectId());
		map.put("taskType", shortType);
		TbProject tbProject = tbProjectMapper.selectByPrimaryKey(tbOrder.getProjectId());
		Integer distributionId = tbProjectDistributionMapper.isHaveJob(map);
		
		if(distributionId==null){
			return false;
		}
		tbOrder.setProjectDistributionId(distributionId);
		// 新建运单时 把项目状态变成使用中
		tbProjectMapper.updateStatusById(tbOrder.getProjectId());

		tbOrder.setCreatorId(user.getId());
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//String sque = sdf.format(System.currentTimeMillis());
		// 生成运单code
		//tbOrder.setOrderCode("QLWY" + AppUtils.randomUUID());
		tbOrder.setOrderCode(codeService.createOrderCode(user.getSysOrgCode(), tbOrder.getProjectId()));
		// 产品说调度员id就是当前登录人id
		tbOrder.setUserDispatchId(user.getId());
		tbOrder.setUserDispatchName(user.getName());
		tbOrder.setType(type);
		if (tbOrder.getStatus() == null)
			tbOrder.setStatus((byte) 2);
		tbOrder.setExceptionStatus((byte) 0);
		if(tbOrder.getOrderOrigin() == null) {
			tbOrder.setOrderOrigin((byte) 1);
			//获取carTeamId
			Integer carTeamId = tbOrderMapper.selectCarteamIdByDriverId(tbOrder.getDriverId());
			tbOrder.setCarteamId(carTeamId);
		}else {
			tbOrder.setOrderOrigin((byte) 2);
		}
		tbOrder.setDeleteFlag((byte) 0);
		tbOrder.setIsCancel((byte) 0);
		if (tbOrder.getStepSelectCode() == 0) {
			tbOrder.setStepSelect("接取");
		} else if (tbOrder.getStepSelectCode() == 1) {
			tbOrder.setStepSelect("送达");
		} else {
			tbOrder.setStepSelect("汽运");
		}
		tbOrder.setCreateDate(new Date());
		
		TbProjectDistribution tbProjectDistribution = tbProjectDistributionMapper.selectByPrimaryKey(distributionId);
		
		if(tbProjectDistribution.getType()==Constants.SHORT_BRAGE_TYPE_RECEIVE){
			//发货单位
			Integer sendCargoUnitId = tbProjectDistribution.getSendCompanyId();
			TbCustomer sendCargoUnit = customerMapper.selectByPrimaryKey(sendCargoUnitId);
			tbOrder.setSendCompanyId(tbProjectDistribution.getSendCompanyId());//发货单位 sendCargoCompanyName
			tbOrder.setSendCompany(sendCargoUnit.getCompanyName());
			tbOrder.setPickupPlace(sendCargoUnit.getAddressCode());//取货地 takePlace
			tbOrder.setPickupPlaceAddress(sendCargoUnit.getDetailAddress());//取货详细地址 takePlaceDetail
			
			//收货站点
			Integer receiveCargoSiteId = tbProjectDistribution.getReceiveCompanyId();
			TbTrainStation trainStation = stationMapper.selectByPrimaryKey(receiveCargoSiteId);
			tbOrder.setReceiptCompanyId(receiveCargoSiteId);
			tbOrder.setReceiptCompany(trainStation.getStationName());
			//取收货站点货场
			Integer receiveCargoSiteFreightYardId = tbProject.getReceiveCargoSiteFreightYardId();
			if(receiveCargoSiteFreightYardId!=null){
				TbFreightYard freightYard = tbFreightYardMapper.selectByPrimaryKey(receiveCargoSiteFreightYardId);
				tbOrder.setArrivePlace(freightYard.getAddressCode());
				tbOrder.setArriveAddress(freightYard.getAddress());
			}
			
		}else if(tbProjectDistribution.getType()==Constants.SHORT_BRAGE_TYPE_DELIVERY){
			//接取站点
			Integer sendCargoSiteId =  tbProjectDistribution.getSendCompanyId();
			TbTrainStation trainStation = stationMapper.selectByPrimaryKey(sendCargoSiteId);
			tbOrder.setSendCompanyId(sendCargoSiteId);//发货单位 sendCargoCompanyName
			tbOrder.setSendCompany(trainStation.getStationName());
			
			//接取站点货场
			Integer forwardingSiteFreightYardId = tbProject.getForwardingSiteFreightYardId();
			if(forwardingSiteFreightYardId!=null){
				TbFreightYard tbFreightYard = tbFreightYardMapper.selectByPrimaryKey(forwardingSiteFreightYardId);
				tbOrder.setPickupPlace(tbFreightYard.getAddressCode());
				tbOrder.setPickupPlaceAddress(tbFreightYard.getAddress());
			}
			
			//收货单位
			Integer receivingDepartmentId = tbProjectDistribution.getReceiveCompanyId();
			TbCustomer receivingDepartment = customerMapper.selectByPrimaryKey(receivingDepartmentId);
			tbOrder.setReceiptCompanyId(receivingDepartmentId);
			tbOrder.setReceiptCompany(receivingDepartment.getCompanyName());//收货单位 receivingDepartmentName
			tbOrder.setArrivePlace(receivingDepartment.getAddressCode());//运抵地 arrivePlace
			tbOrder.setArriveAddress(receivingDepartment.getDetailAddress());//运抵地址详情 arrivePlaceAddress
			
		}else{
			//发货单位
			Integer sendCargoUnitId = tbProjectDistribution.getSendCompanyId();
			TbCustomer sendCargoUnit = customerMapper.selectByPrimaryKey(sendCargoUnitId);
			tbOrder.setSendCompanyId(tbProjectDistribution.getSendCompanyId());//发货单位 sendCargoCompanyName
			tbOrder.setSendCompany(sendCargoUnit.getCompanyName());
			tbOrder.setPickupPlace(sendCargoUnit.getAddressCode());
			tbOrder.setPickupPlaceAddress(sendCargoUnit.getDetailAddress());
			
			//收货单位
			Integer receivingDepartmentId = tbProjectDistribution.getReceiveCompanyId();
			TbCustomer receivingDepartment = customerMapper.selectByPrimaryKey(receivingDepartmentId);
			tbOrder.setReceiptCompanyId(receivingDepartmentId);
			tbOrder.setReceiptCompany(receivingDepartment.getCompanyName());
			tbOrder.setArrivePlace(receivingDepartment.getAddressCode());
			tbOrder.setArriveAddress(receivingDepartment.getDetailAddress());
		}
		
		int row = tbOrderMapper.insertSelective(tbOrder);
		if (row != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
		//更新司机 运单状态 (不可接单)
		//根据order获取司机id
		Integer driverId = tbOrder.getDriverId();
		map = new HashMap<>();
		map.put("driverId",driverId);
		map.put("status",  Constants.DRIVER_ORDER_STATUS_NO);
		//更新司机订单状态
		tbOrderMapper.updateDriverOrderStatus(map);//已接单
		
		//任务数减一
		//更新任务数
		
		//更新已领任务数
		if(tbProjectDistribution.getAlreadyRecNum()==null){
			tbProjectDistribution.setAlreadyRecNum(0);
		}
		tbProjectDistribution.setAlreadyRecNum(tbProjectDistribution.getAlreadyRecNum()+1);//已领任务+1
		//待领取任务-1
		if(tbProjectDistribution.getCarNum()==null){
			tbProjectDistribution.setCarNum(0);
		}
		tbProjectDistribution.setCarNum(tbProjectDistribution.getCarNum()-1);//待领取任务-1
		tbProjectDistributionMapper.updateByPrimaryKey(tbProjectDistribution);//更新到数据库
		
		//更新集装箱状态为装车中，并绑定项目id
		if(tbOrder.getProjectType() == 0) {
			Map<String,Object> cMap = new HashMap<>();
			if(tbOrder.getContainerNumber1() != "") {
				cMap.put("status", 3);
				cMap.put("projectId", tbOrder.getProjectId());
				cMap.put("containerId", tbOrder.getContainerNumber1());
				/*if(tbOrder.getOrderOrigin() == 2) {
					cMap.put("sysOrgCode", user.getSysOrgCode());
				}else {
					cMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
				}*/
				cMap.put("sysOrgCode", user.getSysOrgCode());
				containerMapper.updateContainerStatus(cMap);
			}
			if(tbOrder.getContainerNumber2() != "") {
				cMap.put("status", 3);
				cMap.put("projectId", tbOrder.getProjectId());
				cMap.put("containerId", tbOrder.getContainerNumber2());
				/*if(tbOrder.getOrderOrigin() == 2) {
					cMap.put("sysOrgCode", user.getSysOrgCode());
				}else {
					cMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
				}*/
				cMap.put("sysOrgCode", user.getSysOrgCode());
				containerMapper.updateContainerStatus(cMap);
			}
		}
		
		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(tbOrder.getId().toString());
		branchGroupLink.setTabName("tb_order");
		branchGroupLink.setTabComment("短驳运单");
		/*if(tbOrder.getOrderOrigin() == 2) {
			branchGroupLink.setSysOrgCode(user.getSysOrgCode());
		}else {
			branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		}*/
		branchGroupLink.setSysOrgCode(user.getSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shenhesoft.logistics.business.shortBargeService.ShortBargeService#
	 * getProjects()
	 */
	@Override
	public List<TbProject> getProjects(byte projectType, List<DotBranchDetail> branchGroups) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 分支机构id
		List<Integer> branchIds = new ArrayList<>();
		for (TbBranchGroup tbBranchGroup : branchGroups) {
			branchIds.add(tbBranchGroup.getId());
		}
		map.put("branchGroupIdss", branchIds);
		map.put("projectType", projectType);
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		return tbProjectMapper.getProjectsByShort(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shenhesoft.logistics.business.shortBargeService.ShortBargeService#
	 * selectDetailProject(java.lang.Integer)
	 */
	@Override
	public TbProject selectDetailProject(Integer id) {
		return tbProjectMapper.selectDetailProjectById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shenhesoft.logistics.business.shortBargeService.ShortBargeService#
	 * selectCarTeams()
	 */
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shenhesoft.logistics.business.shortBargeService.ShortBargeService#
	 * selectBoxManagerOrderDeleteByPages(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DataGridResult selectBoxManagerOrderDeleteByPages(Integer page, Integer limit, OrderSearch searchOrder,
			TbSystemUser user,byte isBulk) {
		TbOrderExample example = new TbOrderExample();
		example.setOrderByClause("delete_time desc");
		// 查询条件
		Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagEqualTo((byte) 1);
		
		criteria.andBranchIdIn(branchGroupMapper.selectDotBranchIdsByUid(user.getId()));// 分支机构id
		// 条件查询
		orderCriteria(searchOrder, criteria);
		if(isBulk == 3) {
			criteria.andSysOrgCodeEqualTo(user.getSysOrgCode());
		}else {
			criteria.andTypeEqualTo(isBulk);
			criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		}
		// 分页处理，显示第一页的20条数据
		PageHelper.startPage(page, limit);
		List<TbOrder> list = tbOrderMapper.selectByExample(example);// 查询
		for (TbOrder tbOrder : list) {
			if(tbOrder.getContainerOneSendNet() == null) {
				tbOrder.setContainerOneSendNet(new BigDecimal(0));
			}
			if(tbOrder.getContainerTwoSendNet() == null) {
				tbOrder.setContainerTwoSendNet(new BigDecimal(0));
			}
			if(tbOrder.getContainerOneReceiptNet() == null) {
				tbOrder.setContainerOneReceiptNet(new BigDecimal(0));
			}
			if(tbOrder.getContainerTwoReceiptNet() == null) {
				tbOrder.setContainerTwoReceiptNet(new BigDecimal(0));
			}
			tbOrder.setExportSumSendNet(tbOrder.getContainerOneSendNet().add(tbOrder.getContainerTwoSendNet()));
			tbOrder.setExportSumReceiptNet(tbOrder.getContainerOneReceiptNet().add(tbOrder.getContainerTwoReceiptNet()));
		}
		// 取分页信息
		PageInfo<TbOrder> pageInfo = new PageInfo<TbOrder>(list);
		long total = pageInfo.getTotal(); // 获取总记录数
		return new DataGridResult(total, list, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shenhesoft.logistics.business.shortBargeService.ShortBargeService#
	 * revertOrder(java.util.List)
	 */
	@Override
	public LogisticsResult revertOrder(List<Integer> idList) {
		for (Integer ids : idList) {
			int row = tbOrderMapper.revertOrderById(ids);
			if (row != 1) {
				// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return LogisticsResult.build(0, "还原失败,请勿勾选删除时长超过24小时的订单。");
			}
		}
		return LogisticsResult.build(1, "还原成功");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shenhesoft.logistics.business.shortBargeService.ShortBargeService#
	 * selectBulkPackingOrderByPages(java.lang.Integer, java.lang.Integer, byte)
	 */
	@Override
	public DataGridResult selectBulkPackingOrderByPages(Integer page, Integer limit, byte type, OrderSearch orderSearch,
			TbSystemUser user) {
		TbOrderExample example = new TbOrderExample();
		example.setOrderByClause("id desc");
		// 查询条件
		Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagEqualTo((byte) 0);
		criteria.andTypeEqualTo((byte) 2);
		criteria.andIsCancelEqualTo((byte) 0);
		criteria.andBranchIdIn(branchGroupMapper.selectDotBranchIdsByUid(user.getId()));// 分支机构id
		/**
		 * type :0 :全部 1 :运单 2 :异常运单 3 :历史运单
		 */
		if (type == 1) {
			criteria.andExceptionStatusEqualTo((byte) 0);
			criteria.andStatusNotEqualTo((byte) 7);
		}
		if (type == 2) {
			criteria.andExceptionStatusEqualTo((byte) 1);
		}
		if (type == 3) {
			criteria.andExceptionStatusEqualTo((byte) 0);
			criteria.andStatusEqualTo((byte) 7);
		}
		orderCriteria(orderSearch, criteria);// 条件查询
		// 分页处理，显示第一页的20条数据
		PageHelper.startPage(page, limit);
		List<TbOrder> list = tbOrderMapper.selectByExample(example);// 查询
		// 取分页信息
		PageInfo<TbOrder> pageInfo = new PageInfo<TbOrder>(list);
		long total = pageInfo.getTotal(); // 获取总记录数

		return new DataGridResult(total, list, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shenhesoft.logistics.business.shortBargeService.ShortBargeService#
	 * selectBulkPackingOrderDeleteByPages(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DataGridResult selectBulkPackingOrderDeleteByPages(Integer page, Integer limit, OrderSearch orderSearch,
			TbSystemUser user) {
		TbOrderExample example = new TbOrderExample();
		example.setOrderByClause("id desc");
		// 查询条件
		Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagEqualTo((byte) 1);
		criteria.andTypeEqualTo((byte) 2);
		criteria.andBranchIdIn(branchGroupMapper.selectDotBranchIdsByUid(user.getId()));// 分支机构id
		/**
		 * type :0 :全部 1 :运单 2 :异常运单 3 :历史运单
		 */

		// 条件查询
		orderCriteria(orderSearch, criteria); // 条件查询

		// 分页处理，显示第一页的20条数据
		PageHelper.startPage(page, limit);
		List<TbOrder> list = tbOrderMapper.selectByExample(example);// 查询
		// 取分页信息
		PageInfo<TbOrder> pageInfo = new PageInfo<TbOrder>(list);
		long total = pageInfo.getTotal(); // 获取总记录数
		return new DataGridResult(total, list, limit);
	}

	@Override
	@Transactional
	public boolean dispatchbulkPackingAdd(TbWaitDispatchDetail tbWaitDispatchDetail, TbSystemUser user) throws Exception {
		if (tbWaitDispatchDetail.getIsAgree() != 0) {
			// 更新order
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", tbWaitDispatchDetail.getOrderId());
			map.put("status", Constants.SMS_POINT_WAIT_SEND);
			map.put("takeCargoPlaceId", tbWaitDispatchDetail.getTakeCargoPlaceId());
			map.put("takeCarogoPlaceName", tbWaitDispatchDetail.getTakeCarogoPlaceName());
			map.put("takeCargoSiteId", tbWaitDispatchDetail.getTakeCargoSiteId());
			map.put("takeCargoSiteName", tbWaitDispatchDetail.getTakeCargoSiteName());
			int row = tbOrderMapper.updateDispatchOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
			TbOrder order = tbOrderMapper.selectByPrimaryKey(tbWaitDispatchDetail.getOrderId());
			
			//任务数减一
			//更新任务数
			TbProjectDistribution projectDistribution = tbProjectDistributionMapper.selectByPrimaryKey(order.getProjectDistributionId());
			//更新已领任务数
			if(projectDistribution.getAlreadyRecNum()==null){
				projectDistribution.setAlreadyRecNum(0);
			}
			projectDistribution.setAlreadyRecNum(projectDistribution.getAlreadyRecNum()+1);//已领任务+1
			//待领取任务-1
			if(projectDistribution.getCarNum()==null){
				projectDistribution.setCarNum(0);
			}
			projectDistribution.setCarNum(projectDistribution.getCarNum()-1);//待领取任务-1
			tbProjectDistributionMapper.updateByPrimaryKey(projectDistribution);//更新到数据库
			
			//根据order获取司机id
			Integer driverId = order.getDriverId();
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
			List<TbOrder> list = tbOrderMapper.selectByExample(orderExample);
			for (TbOrder orderW : list) {
				orderW.setIsCancel((byte)1);//已取消
				orderW.setDeleteFlag((byte)1);//已删除
				orderW.setDeleteTime(new Date());
				tbOrderMapper.updateByPrimaryKey(orderW);//更新数据库
			}
			PushUtil pushUtil = new PushUtil();
			pushUtil.sendAndroidCustomizedcast(driverId.toString(), "您已接单成功", "请查看取货地点", "2");
			
			//消息通知
			anchoredRecordMapper.insertNoticeByApp(driverId,"您已接单成功,请查看取货地点");
			
		} else {
			TbOrder orderc = tbOrderMapper.selectByPrimaryKey(tbWaitDispatchDetail.getOrderId());
			// 不同意做什么操作 逻辑删除-放入回收站
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", tbWaitDispatchDetail.getOrderId());
			map.put("userId", user.getId());
			map.put("userName", user.getName());
			map.put("remark", tbWaitDispatchDetail.getRemark());
			map.put("deleteFlag", Constants.DELETE_FLAG_TRUE);
			map.put("isCancel",Constants.ORDER_CANCLE_YES);//已取消
			int row = tbOrderMapper.updateDeleteOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
			//取消发运 推送消息
			PushUtil pushUtil = new PushUtil();
			pushUtil.sendAndroidCustomizedcast(orderc.getDriverId().toString(), "您的运单被取消", "请重新接单或联系调度员", String.valueOf(Constants.SMS_POINT_WAIT_DIS));
			
			//消息通知
			anchoredRecordMapper.insertNoticeByApp(orderc.getDriverId(),"您的运单被取消,请重新接单或联系调度员");
		}
		return true;
	}

	@Override
	public int subBulkForwardingAdd(TbBulkSubForwardDetail tbBulkSubForwardDetail, TbSystemUser user) throws Exception {
		if (tbBulkSubForwardDetail.getIsAgree() != 1) {
			// 更新库存
			// 为接取状态下 货场货位是独立的 不做操作 为送达状态下 更改发货站点货场货位库存
			TbOrder order = tbOrderMapper.selectByPrimaryKey(tbBulkSubForwardDetail.getOrderId());
			if (order.getStepSelectCode() == 0 || order.getStepSelectCode() == 2) {
				// 接取 + 汽运 不做处理
			} else {
				/*
				 * int i = 0; int j = 0;
				 * if(tbBulkSubForwardDetail.getContainerOneSendNet() != null ){
				 * i = 1; }
				 */
				// 获得送达的货场货位
				TbProject project = tbProjectMapper.selectDetailProjectById(order.getProjectId());
				Map<String, Object> stockMap = new HashMap<String, Object>();
				stockMap.put("projectId", project.getId());
				stockMap.put("freightYardId", order.getTakeCargoPlaceId());
				stockMap.put("cargoLocationId", order.getTakeCargoSiteId());
				// 发货站点
				stockMap.put("stationId", project.getForwardingSiteId());
				// stockMap.put("type", (byte)0);
				stockMap.put("type", (byte) 1);
				TbStock oldStock = tbTrainOrderMapper.selectStockByMap(stockMap);
				if (oldStock != null) {
					//送达阶段 发货重量不能大于现有库存
					BigDecimal sw = tbBulkSubForwardDetail.getContainerOneSendNet();
					if (oldStock.getOutQty() != null) {
						int compareResult = oldStock.getCurrentQty().compareTo(sw);
						//-1表示小于
						if(compareResult == -1) {
							//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							return 602;
						}
					}
					oldStock.setOutQty(oldStock.getOutQty()==null?new BigDecimal(0): oldStock.getOutQty());
					if (oldStock.getOutQty() != null || (oldStock.getOutQty().equals(BigDecimal.ZERO))) {
						oldStock.setOutQty(oldStock.getOutQty().add(tbBulkSubForwardDetail.getContainerOneSendNet()));
					}
					if (oldStock.getCurrentQty() != null || !(oldStock.getCurrentQty().equals(BigDecimal.ZERO))) {
						oldStock.setCurrentQty(
								oldStock.getCurrentQty().subtract(tbBulkSubForwardDetail.getContainerOneSendNet()));
					}
					/*
					 * if(oldStock.getContainerNum() != null ||
					 * oldStock.getContainerNum() != 0){
					 * oldStock.setContainerNum(oldStock.getContainerNum()-(i+j)
					 * ); }
					 */
					oldStock.setAdjustDate(new Date());
					int rstock = tbStockMapper.updateByPrimaryKeySelective(oldStock);
					if (rstock != 1) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return 603;
					}
				}else {
					return 607;
				}
			}
			//更新tb_order表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", tbBulkSubForwardDetail.getOrderId());
			map.put("sendTare", tbBulkSubForwardDetail.getSendTare());
			map.put("sendGross", tbBulkSubForwardDetail.getSendGross());
			map.put("containerOneSendNet", tbBulkSubForwardDetail.getContainerOneSendNet());
			map.put("testIndicators", tbBulkSubForwardDetail.getTestIndicators());
			map.put("img", tbBulkSubForwardDetail.getImg());
			map.put("status", Constants.SMS_POINT_ON_ROAD);
			int row = tbOrderMapper.updateSubForwardingOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return 601;
			}
			// 推送消息
			PushUtil pushUtil = new PushUtil();
			pushUtil.sendAndroidCustomizedcast(order.getDriverId().toString(), "您的装货货场已分配", "请查看装货地点", String.valueOf(Constants.SMS_POINT_WAIT_SEND));
		
			//消息通知
			anchoredRecordMapper.insertNoticeByApp(order.getDriverId(),"您的装货货场已分配,请查看装货地点");
		} else {
			// 进入回收站
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", tbBulkSubForwardDetail.getOrderId());
			map.put("userId", user.getId());
			map.put("userName", user.getName());
			map.put("remark", tbBulkSubForwardDetail.getRemark());
			map.put("isCancel",  Constants.DELETE_FLAG_TRUE);
			map.put("deleteFlag", Constants.DELETE_FLAG_TRUE);
			int row = tbOrderMapper.updateDeleteOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return 604;
			}
			TbOrder order = tbOrderMapper.selectByPrimaryKey(tbBulkSubForwardDetail.getOrderId());
			
			//更新任务已领数 和待领数
			//更新任务数
			TbProjectDistribution projectDistribution = tbProjectDistributionMapper.selectByPrimaryKey(order.getProjectDistributionId());
			//更新已领任务数
			if(projectDistribution.getAlreadyRecNum()==null){
				projectDistribution.setAlreadyRecNum(0);
			}
			projectDistribution.setAlreadyRecNum(projectDistribution.getAlreadyRecNum()-1);//已领任务-1
			//待领取任务+1
			if(projectDistribution.getCarNum()==null){
				projectDistribution.setCarNum(0);
			}
			projectDistribution.setCarNum(projectDistribution.getCarNum()+1);//待领取任务+1
			tbProjectDistributionMapper.updateByPrimaryKey(projectDistribution);//更新到数据库
			
			//更新运单状态
			//根据order获取司机id
			Integer driverId = order.getDriverId();
			map  = new HashMap<>();
			map.put("driverId", driverId);
			map.put("status",  Constants.DRIVER_ORDER_STATUS_YES);
			//更新司机订单状态
			tbOrderMapper.updateDriverOrderStatus(map);//未接单
			
			//取消发运 推送消息
			PushUtil pushUtil = new PushUtil();
			pushUtil.sendAndroidCustomizedcast(driverId.toString(), "您的运单被取消", "请重新接单或联系调度员", String.valueOf(Constants.SMS_POINT_WAIT_SEND));
		
			//消息通知
			anchoredRecordMapper.insertNoticeByApp(driverId,"您的运单被取消,请重新接单或联系调度员");
		}
		return 1;
	}

	@Override
	public boolean carryBulkAdd(TbWaitDispatchDetail tbWaitDispatchDetail, TbSystemUser user) {
		if (tbWaitDispatchDetail.getIsAgree() != 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", tbWaitDispatchDetail.getOrderId());
			map.put("status", Constants.SMS_POINT_LOCATION_GUIDE);
			int row = tbOrderMapper.updateCarryAddOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
		} else {
			// 进入回收站
			/*Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", tbWaitDispatchDetail.getOrderId());
			map.put("userId", user.getId());
			map.put("userName", user.getName());
			map.put("remark", tbWaitDispatchDetail.getRemark());
			map.put("deleteFlag", Constants.DELETE_FLAG_TRUE);
			int row = tbOrderMapper.updateDeleteOrderByMap(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}*/
		}
		return true;
	}

	@Override
	public TbOrder getExceptionByOrderId(Integer id) {
		return tbOrderMapper.getExceptionByOrderId(id);
	}

	@Override
	@Transactional
	public boolean rejectException(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("exceptionStatus", (byte) 0);
		map.put("exceptionReoportName", "");
		map.put("exceptionReoportReason", "");
		int row = tbOrderMapper.rejectExceptionByMap(map);
		if (row != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public List<TbOrderCarDetail> selectCarTeams(Integer projectId,Integer driverId,Integer shortType) {
		// 如果id是0 ,则此项目短驳承运方是平台
		Map<String, Object> map = new HashMap<>();
		if(shortType==0){
			shortType=1;
		}else if(shortType==1){
			shortType=2;
		}else if(shortType==2){
			shortType=3;
		}
		if(projectId!=null){
			map.put("projectId", projectId);
		}
		if(driverId!=null){
			map.put("driverId", driverId);
		}
		if(shortType!=null){
			map.put("shortType", shortType);
		}
		return tbProjectMapper.selectCarTeams(map);
	}
	
	@Override
	public List<TbOrderCarDetail> selectCarTeamsByPlatform(Integer projectId, Integer driverId,Integer shortType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(shortType==0){
			shortType=1;
		}else if(shortType==1){
			shortType=2;
		}else if(shortType==2){
			shortType=3;
		}
		if(projectId!=null){
			map.put("projectId", projectId);
		}
		if(driverId!=null){
			map.put("driverId", driverId);
		}
		if(shortType!=null){
			map.put("shortType", shortType);
		}
		return tbProjectMapper.selectCarTeamsByPlatform(map);
	}

	@Override
	public List<ProjectAppHelp> appGetAllProjectByBulk(Integer id,String sysOrgCode) {
		// 当前面登录人分支机构
		Map<String, Object> map = new HashMap<>();
		map.put("status", Constants.DOT_BRANCH_STATUS_YES);
		map.put("sysOrgCode", sysOrgCode);
      //List<TbBranchGroup> branchGroups = branchGroupMapper.selectDotBranchByUid(map);
		List<DotBranchDetail> branchGroups = branchGroupMapper.getDotBranchs(map);
		if (branchGroups == null || branchGroups.size() == 0) {
			return null;
		}
		map.clear();
		// 分支机构id
		List<Integer> branchIds = new ArrayList<>();
		for (TbBranchGroup tbBranchGroup : branchGroups) {
			branchIds.add(tbBranchGroup.getId());
		}
		map.put("branchGroupIdbs", branchIds);
		map.put("sysOrgCode", sysOrgCode);
		return tbProjectMapper.selectAppAllProjectByBulk(map);
	}
	
	/**
	 * @description 短驳运单 条件查询
	 * @date 2018年1月12日
	 * @author shilvfei
	 * @param
	 * @return
	 */
	private void orderCriteria(OrderSearch searchOrder, Criteria criteria) {
		
		criteria.andTabNameEqualTo("tb_order");
		if (searchOrder == null)
			return;
		// 条件查询
		if (searchOrder.getStatusList() != null && searchOrder.getStatusList().size() > 0) {// 运单状态
			criteria.andStatusIn(searchOrder.getStatusList());
		}
		if (StringUtils.isNotBlank(searchOrder.getProjectCode())) {// 项目编号
			criteria.andProjectCodeLike(searchOrder.getProjectCode());
		}
		if (StringUtils.isNotBlank(searchOrder.getBranchGroupName())) {// 分支机构
			criteria.andBranchGroupNameEqualTo(searchOrder.getBranchGroupName());
		}
		if (searchOrder.getProjectType() != null) {// 项目类型
			criteria.andProjectTypeEqualTo(searchOrder.getProjectType());
		}

		if (StringUtils.isNotBlank(searchOrder.getCargoName())) {// 货物名
			criteria.andCargoNameEqualTo(searchOrder.getCargoName());
		}

		if (StringUtils.isNotBlank(searchOrder.getOrderCode())) { // 运单编号
			criteria.andOrderCodeLike(searchOrder.getOrderCode());
		}
		if (searchOrder.getBeginDate() != null && searchOrder.getEndDate() != null) {// 创建时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String strBgeinDate = DateUtils.date2Str(searchOrder.getBeginDate(),sdf);
			String strEndDate = DateUtils.date2Str(searchOrder.getEndDate(),sdf);
			criteria.andCreateDateGreaterThanOrEqualTo(strBgeinDate);
			criteria.andCreateDateLessThanOrEqualTo(strEndDate);
		}
		if (StringUtils.isNotBlank(searchOrder.getSendCompany())) {// 发货单位
			criteria.andSendCompanyLike(searchOrder.getSendCompany());
		}
		if (StringUtils.isNotBlank(searchOrder.getReceiptCompany())) {// 收货单位
			criteria.andReceiptCompanyLike(searchOrder.getReceiptCompany());
		}
		if (StringUtils.isNotBlank(searchOrder.getCarPlateNumber())) {// 承运车辆
			criteria.andCarPlateNumberLike(searchOrder.getCarPlateNumber());
		}
		if (searchOrder.getStepSelectCode() != null) {// 阶段选择
			criteria.andStepSelectCodeEqualTo(searchOrder.getStepSelectCode());
		}
		if (searchOrder.getStatusList() != null && searchOrder.getStatusList().size() != 0) {
			criteria.andStatusIn(searchOrder.getStatusList());
		}
	}

	@Override
	public List<TbOrderCarDetail> selectRuningCarTeams(Integer projectId) {
		return tbProjectMapper.selectRuningCarTeams(projectId);
	}

	@Override
	public TbOrderCarDetail selectDriverByDriverId(Integer driverId) {
		return tbProjectMapper.selectDriverByDriverId(driverId);
	}

	@Override
	public Integer queryCountsOrderOfDays(TbSystemUser tbSystemUser,Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("branchIdsByUserId", getBranchIdsByUserId(tbSystemUser.getId()));
		map.put("dayType", type);
		//map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		map.put("sysOrgCode", tbSystemUser.getSysOrgCode());
		//待确定
		Integer num = tbOrderMapper.queryCountsOrderOfDays(map);
		return num;
	}

	public List<Integer> getBranchIdsByUserId(Integer userId){
        //当前面登录人分支机构
		Map<String, Object> map = new HashMap<>();
		map.put("id", userId);
		map.put("status", Constants.DOT_BRANCH_STATUS_YES);
        List<TbBranchGroup> branchGroups = branchGroupMapper.selectDotBranchByUid(map);
        if(CollectionUtils.isEmpty(branchGroups)){
            return null;
        }
        //分支机构id
        List<Integer> branchIds = new ArrayList<>();
        for (TbBranchGroup tbBranchGroup : branchGroups) {
            branchIds.add(tbBranchGroup.getId());
        }
        return branchIds;
	}

	@Override
	public DataGridResult selectExceptionOrderByStatus(Integer page, Integer pageLimit, Integer status,TbSystemUser tbSystemUser) {
		TbOrderExample example = new TbOrderExample();
		example.setOrderByClause("A.id desc");
		// 查询条件
		Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagEqualTo((byte) 0);
		criteria.andIsCancelEqualTo((byte) 0);
		criteria.andBranchIdIn(branchGroupMapper.selectDotBranchIdsByUid(tbSystemUser.getId()));// 分支机构id
		criteria.andExceptionStatusEqualTo((byte) 1);
		//status 4 到货确认  5 计费确认
		if(status == 4) {
			criteria.andStatusEqualTo((byte) 4);
		}
		if(status == 5) {
			criteria.andStatusEqualTo((byte) 5);
			criteria.andReceipterDateIsNotNull();
		}
		criteria.andSysOrgCodeEqualTo(tbSystemUser.getSysOrgCode());
		// 分页处理，显示第一页的20条数据
		PageHelper.startPage(page, pageLimit);
		List<TbOrder> list = tbOrderMapper.selectByExample(example);// 查询
		// 取分页信息
		PageInfo<TbOrder> pageInfo = new PageInfo<TbOrder>(list);
		long total = pageInfo.getTotal(); // 获取总记录数
		return new DataGridResult(total, list, pageLimit);
	}

	@Override
	public Integer appWaitBillentCounts(TbSystemUser tbSystemUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("branchIdsByUserIds", getBranchIdsByUserId(tbSystemUser.getId()));
		map.put("sysOrgCode", tbSystemUser.getSysOrgCode());
		Integer num = tbOrderMapper.appWaitBillentCounts(map);
		return num;
	}
}
