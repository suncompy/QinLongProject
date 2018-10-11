// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.trainOrder.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderCargoByBulkDetail;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderCargoCheck;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderCargoPlaceDetail;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderDetail;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderSearch;
import com.shenhesoft.logistics.business.mapper.BasicDataMapper;
import com.shenhesoft.logistics.business.mapper.TbHistoryLocationMapper;
import com.shenhesoft.logistics.business.mapper.TbStockMapper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderCargoPalceMapper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderMapper;
import com.shenhesoft.logistics.business.pojo.historyLocation.TbHistoryLocation;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrderExample;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrderExample.Criteria;
import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalce;
import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalceExample;
import com.shenhesoft.logistics.business.trainOrder.TrainOrderService;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.ImageUtil;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.PleaseCarNumUtil;
import com.shenhesoft.logistics.finance.AdvanceCharge;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.mapper.AdvanceSettlementMapper;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.helpPojo.ProjectAppHelp;
import com.shenhesoft.logistics.manage.helpPojo.TbBoxDetail;
import com.shenhesoft.logistics.manage.mapper.BranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbCargoLocationMapper;
import com.shenhesoft.logistics.manage.mapper.TbContainerMapper;
import com.shenhesoft.logistics.manage.mapper.TbFinanceAccountMapper;
import com.shenhesoft.logistics.manage.mapper.TbFreightYardMapper;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainTypeMapper;
import com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocation;
import com.shenhesoft.logistics.manage.pojo.box.TbContainer;
import com.shenhesoft.logistics.manage.pojo.box.TbContainerExample;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.pojo.trianType.TbTrainType;
import com.shenhesoft.logistics.system.CodeService;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月18日
 */
@Service
public class TrainServiceImpl implements TrainOrderService{
	
	@Autowired
	private TbTrainOrderMapper tbTrainOrderMapper;
	
	@Autowired
	private TbTrainOrderCargoPalceMapper trainOrderCargoPalceMapper;
	
	@Autowired
	private TbHistoryLocationMapper historyLocationMapper;
	
	@Autowired
	private TbProjectMapper tbProjectMapper;
	
	@Autowired
	private TbStockMapper tbStockMapper;
	
	@Autowired
	private TbBranchGroupMapper tbBranchGroupMapper;
	
	@Autowired
	private BranchGroupMapper branchGroupMapper;
	
	@Autowired
	private TbTrainStationMapper tbTrainStationMapper;
	
	@Autowired
	private TbCargoLocationMapper tlMapper;
	
	@Autowired
	private TbContainerMapper containerMapper;
	
	@Autowired
	private TbFinanceAccountMapper financeAccountMapper;
	
	@Autowired
	private AdvanceSettlementMapper advanceSettlementMapper;
	
	@Autowired
	private BasicDataMapper basicDataMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	@Autowired
	private TbTrainTypeMapper TbTrainTypeMapper;
	
	@Autowired
	private CodeService codeService;
	
	@Override
	public DataGridResult selectTrainOrderByPage(Integer page, Integer limit, byte type, TbSystemUser user,TrainOrderSearch search) {
		TbTrainOrderExample orderExample = new  TbTrainOrderExample();
		Criteria orderCriteria = orderExample.createCriteria();
		
		if(type == 3) {
			//3是表示app查询列表
			orderCriteria.andSysOrgCodeEqualTo(user.getSysOrgCode());
		}else {
			orderCriteria.andTypeEqualTo(type);
			orderCriteria.andStatusNotEqualTo((byte)7);
			orderCriteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		}
		orderCriteria.andBranchIdIn(tbBranchGroupMapper.selectDotBranchIdsByUid(user.getId()));//分支机构id
		orderCriteria.andIsExceptionEqualTo((byte)0);
		orderCriteria.andDeleteFlagEqualTo((byte)0);
		
		criteriaByPage(orderCriteria,search);
		orderExample.setOrderByClause("A.id desc");
		//查询
		PageHelper.startPage(page, limit);
		List<TbTrainOrder> orderList = tbTrainOrderMapper.selectByExample(orderExample);
		for (TbTrainOrder order : orderList) {
			if(order.getPleaseCarNum() != null && order.getSureCarNum() != null){
				int loseCarNum = Integer.parseInt(order.getPleaseCarNum()) - order.getSureCarNum();
				order.setLoseCarNum(loseCarNum);
			}
		}
		PageInfo<TbTrainOrder> pageInfo = new PageInfo<TbTrainOrder>(orderList);
	    long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, orderList, limit);
	}
	
	@Override
	public DataGridResult selectHistoryOrderByPage(Integer page, Integer limit,byte type, TbSystemUser user,TrainOrderSearch search) {
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		//分支机构id
		List<Integer> branchIds =branchGroupMapper.selectDotBranchIdsByUid(user.getId());
		map.put("branchIds", branchIds);
		List<TbTrainOrder> orderList = tbTrainOrderMapper.selectHistoryOrderByPage(map);*/
		TbTrainOrderExample orderExample = new  TbTrainOrderExample();
		Criteria orderCriteria = orderExample.createCriteria();
		
		orderCriteria.andTypeEqualTo(type);
		orderCriteria.andBranchIdIn(tbBranchGroupMapper.selectDotBranchIdsByUid(user.getId()));//分支机构id
		orderCriteria.andIsExceptionEqualTo((byte)0);
		orderCriteria.andDeleteFlagEqualTo((byte)0);
		orderCriteria.andStatusEqualTo((byte)7);
		orderCriteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		criteriaByPage(orderCriteria,search);
		orderExample.setOrderByClause("A.id desc");
		//查询
		PageHelper.startPage(page, limit);
		List<TbTrainOrder> orderList = tbTrainOrderMapper.selectByExample(orderExample);
		for (TbTrainOrder order : orderList) {
			if(order.getPleaseCarNum() != null && order.getSureCarNum() != null){
				int loseCarNum = Integer.parseInt(order.getPleaseCarNum()) - order.getSureCarNum();
				order.setLoseCarNum(loseCarNum);
			}
		}
		PageInfo<TbTrainOrder> pageInfo = new PageInfo<TbTrainOrder>(orderList);
	    long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, orderList, limit);
	}
	
	@Override
	public DataGridResult selectExceptionOrderByPage(Integer page,Integer limit, byte type, TbSystemUser user,TrainOrderSearch search) {
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		//分支机构id
		List<Integer> branchIds = branchGroupMapper.selectDotBranchIdsByUid(user.getId());
		map.put("branchIds", branchIds);
		List<TbTrainOrder> orderList = tbTrainOrderMapper.selectExceptionOrderByPage(map);*/
		TbTrainOrderExample orderExample = new  TbTrainOrderExample();
		Criteria orderCriteria = orderExample.createCriteria();
		
		orderCriteria.andTypeEqualTo(type);
		orderCriteria.andBranchIdIn(tbBranchGroupMapper.selectDotBranchIdsByUid(user.getId()));//分支机构id
		orderCriteria.andIsExceptionEqualTo((byte)1);
		orderCriteria.andDeleteFlagEqualTo((byte)0);
		orderCriteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		criteriaByPage(orderCriteria,search);
		orderExample.setOrderByClause("A.id desc");
		//查询
		PageHelper.startPage(page, limit);
		List<TbTrainOrder> orderList = tbTrainOrderMapper.selectByExample(orderExample);
		for (TbTrainOrder order : orderList) {
			if(order.getPleaseCarNum() != null && order.getSureCarNum() != null){
				int loseCarNum = Integer.parseInt(order.getPleaseCarNum()) - order.getSureCarNum();
				order.setLoseCarNum(loseCarNum);
			}
		}
		PageInfo<TbTrainOrder> pageInfo = new PageInfo<TbTrainOrder>(orderList);
	    long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, orderList, limit);
	}

	@Override
	public DataGridResult selectDeleteOrderByPage(Integer page, Integer limit,byte type,TbSystemUser user,TrainOrderSearch search) {
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		//分支机构id
		List<Integer> branchIds = branchGroupMapper.selectDotBranchIdsByUid(user.getId());
		map.put("branchIds", branchIds);
		List<TbTrainOrder> orderList = tbTrainOrderMapper.selectDeleteOrderByPage(map);*/
		TbTrainOrderExample orderExample = new  TbTrainOrderExample();
		Criteria orderCriteria = orderExample.createCriteria();
		
		orderCriteria.andTypeEqualTo(type);
		orderCriteria.andBranchIdIn(tbBranchGroupMapper.selectDotBranchIdsByUid(user.getId()));//分支机构id
		orderCriteria.andIsExceptionEqualTo((byte)0);
		orderCriteria.andDeleteFlagEqualTo((byte)1);
		orderCriteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		criteriaByPage(orderCriteria,search);
		orderExample.setOrderByClause("A.id desc");
		//查询
		PageHelper.startPage(page, limit);
		List<TbTrainOrder> orderList = tbTrainOrderMapper.selectByExample(orderExample);
		for (TbTrainOrder order : orderList) {
			if(order.getPleaseCarNum() != null && order.getSureCarNum() != null){
				int loseCarNum = Integer.parseInt(order.getPleaseCarNum()) - order.getSureCarNum();
				order.setLoseCarNum(loseCarNum);
			}
		}
		PageInfo<TbTrainOrder> pageInfo = new PageInfo<TbTrainOrder>(orderList);
	    long total = pageInfo.getTotal(); //获取总记录数
	    //设置总记录数
		return new DataGridResult(total, orderList, limit);
	}

	@Override
	@Transactional
	public int addTrainOrder(TbTrainOrder trainOrder,TbSystemUser tbSystemUser) {
		trainOrder.setCreateDate(new Date());
		//trainOrder.setPleaseTrainNumber("PLC"+PleaseCarNumUtil.TimeDay());
		trainOrder.setPleaseTrainNumber(codeService.createTrainOrderCode(tbSystemUser.getSysOrgCode(), trainOrder.getProjectId()));
		trainOrder.setStatus((byte)1);
		trainOrder.setType(trainOrder.getProjectType());
		trainOrder.setIsException((byte)0);
		trainOrder.setDeleteFlag((byte)0);
		int r = tbTrainOrderMapper.insertSelective(trainOrder);
		//修改项目表状态 为使用中
		TbProject project = new TbProject();
		project.setId(trainOrder.getProjectId());
		project.setStatus((byte)1);
		int rp = tbProjectMapper.updateByPrimaryKeySelective(project);
		if(rp != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return rp;
		}
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		
		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(trainOrder.getId().toString());
		branchGroupLink.setTabName("tb_train_order");
		branchGroupLink.setTabComment("火运运单");
		branchGroupLink.setSysOrgCode(tbSystemUser.getSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		return r;
	}

	@Override
	public TbTrainOrder selectTrainOrderById(Integer id) {
		return tbTrainOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int deleteTrainOrderById(Integer id,String name, Integer sureName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("sendOperatorId", name);
		map.put("sureCarNum", sureName);
		map.put("deleteFlag", 1);
		map.put("deletePerson", name);
		map.put("deleteReason", "承认车数为0");
		map.put("deleteDate", new Date());
		int r = tbTrainOrderMapper.deleteTrainOrderById(map);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	@Transactional
	public int updateTrainOrderById(Integer id, String name, Integer sureName) {
		TbTrainOrder order = tbTrainOrderMapper.selectByPrimaryKey(id);
		Integer loseCarNum = Integer.parseInt(order.getPleaseCarNum()) - sureName;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("sendOperatorId", name);
		map.put("sureCarNum", sureName);
		map.put("loseCarNum", loseCarNum);
		map.put("updateDate", new Date());
		map.put("status", 2);
		int r = tbTrainOrderMapper.updateTrainOrderById(map);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	@Transactional
	public int resetOrderById(Integer id) {
		int r = tbTrainOrderMapper.resetTrainOrderById(id);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	@Transactional 
	public LogisticsResult addWaitEntruck(TrainOrderCargoPlaceDetail orderCargoPlaceDetail, TbSystemUser tbSystemUser,HttpSession session) {
		
		TbProject project = tbProjectMapper.selectDetailProjectById(orderCargoPlaceDetail.getHidenProjectId());
		String entruckInfoJson = orderCargoPlaceDetail.getEntruckInfoJson();
		List<TbTrainOrderCargoPalce> cargoPlaceList = JsonUtils.jsonToList(entruckInfoJson,TbTrainOrderCargoPalce.class);
		if(cargoPlaceList == null) {
			return LogisticsResult.build(9, "请至少填写一条装车信息");
		}
		int nullflag = 0;
		for (TbTrainOrderCargoPalce tbTrainOrderCargoPalce : cargoPlaceList) {
			//如果传过来的td全为空 则返回提示
			if((tbTrainOrderCargoPalce.getCarType() == "" || tbTrainOrderCargoPalce.getCarType() == null ) && (tbTrainOrderCargoPalce.getCarNumber() == "" || tbTrainOrderCargoPalce.getCarNumber() == null ) 
					&& tbTrainOrderCargoPalce.getSendWeight() == null && tbTrainOrderCargoPalce.getConSendWeight2()  == null) {
				nullflag ++;
			}
		}
		if(nullflag == cargoPlaceList.size()) {
			return LogisticsResult.build(10, "请至少填写一条装车信息");
		}
		
		//在集合不为Null的情况下 比对库存
		for (int i = 0; i < cargoPlaceList.size(); i++) {
			//去除一条为null的数据
			if((cargoPlaceList.get(i).getCarType() == "" || cargoPlaceList.get(i).getCarType() == null ) && (cargoPlaceList.get(i).getCarNumber() == "" || cargoPlaceList.get(i).getCarNumber() == null ) 
					&& cargoPlaceList.get(i).getSendWeight() == null && cargoPlaceList.get(i).getConSendWeight2()  == null) {
				cargoPlaceList.remove(cargoPlaceList.get(i));
			}
		}
		//排序
		Collections.sort(cargoPlaceList, new Comparator<TbTrainOrderCargoPalce>() {
			@Override
			public int compare(TbTrainOrderCargoPalce o1, TbTrainOrderCargoPalce o2) {
			         return o1.getCargoSiteId() - o2.getCargoSiteId();
			}
		});
		//比对集装箱号是否重复
		List<String> repeatList = new ArrayList<String>();
		for (TbTrainOrderCargoPalce tcp : cargoPlaceList) {
			if(tcp.getContainerNumber1() != "") {
				repeatList.add(tcp.getContainerNumber1());
				//判断集装箱号有没有被同时操作 
				TbContainerExample example = new TbContainerExample();
				com.shenhesoft.logistics.manage.pojo.box.TbContainerExample.Criteria criteria = example.createCriteria();
				criteria.andContainerIdEqualTo(tcp.getContainerNumber1());
				criteria.andIdIsNotNull();
				criteria.andTabNameEqualTo("tb_container");
				criteria.andSysOrgCodeEqualTo(tbSystemUser.getSysOrgCode());
				TbContainer cont = containerMapper.selectByExample(example).get(0);
				if(project.getTransportType() == 3 || project.getTransportType() == 5) {
					if(cont.getStatus() != 0) {
						return LogisticsResult.build(17, tcp.getContainerNumber1()+"集装箱已被装车，请查看");
					}
				}
				if(project.getTransportType() == 4 || project.getTransportType() == 6) {
					if(cont.getStatus() != 1) {
						return LogisticsResult.build(17, tcp.getContainerNumber1()+"集装箱已被装车，请查看");
					}
				}
			}
			if(tcp.getContainerNumber2() != "") {
				repeatList.add(tcp.getContainerNumber2());
				//判断集装箱号有没有被同时操作 
				TbContainerExample example = new TbContainerExample();
				com.shenhesoft.logistics.manage.pojo.box.TbContainerExample.Criteria criteria = example.createCriteria();
				criteria.andContainerIdEqualTo(tcp.getContainerNumber2());
				criteria.andIdIsNotNull();
				criteria.andTabNameEqualTo("tb_container");
				criteria.andSysOrgCodeEqualTo(tbSystemUser.getSysOrgCode());
				TbContainer cont = containerMapper.selectByExample(example).get(0);
				if(project.getTransportType() == 3 || project.getTransportType() == 5) {
					if(cont.getStatus() != 0) {
						return LogisticsResult.build(17, tcp.getContainerNumber2()+"集装箱已被装车，请查看");
					}
				}
				if(project.getTransportType() == 4 || project.getTransportType() == 6) {
					if(cont.getStatus() != 1) {
						return LogisticsResult.build(17, tcp.getContainerNumber2()+"集装箱已被装车，请查看");
					}
				}
			}
		}
		String repeatCont = "";
		StringBuilder builder = new StringBuilder();  
        for(String str : repeatList) {  
            // 如果不存在返回 -1。  
            if(builder.indexOf(","+str+",") > -1) {  
                repeatCont = str + "," + repeatCont;
            } else {  
                builder.append(",").append(str).append(",");  
            }  
        }  
        if(repeatCont != "") {
        	return LogisticsResult.build(16, repeatCont+"集装箱号重复");
        }
		List<TrainOrderCargoCheck> checkList = new ArrayList<TrainOrderCargoCheck>();
		TrainOrderCargoCheck checkCargo = new TrainOrderCargoCheck();
		TbTrainOrderCargoPalce cp = cargoPlaceList.get(0);
		BigDecimal cpsend1 = cp.getSendWeight()==null?new BigDecimal(0):cp.getSendWeight();
		BigDecimal cpsend2 = cp.getConSendWeight2()==null?new BigDecimal(0):cp.getConSendWeight2();
		checkCargo.setCheckWeight(cpsend1.add(cpsend2));
		
		if(cargoPlaceList != null && cargoPlaceList.size() <2) {
			//只有一条数据情况下
			checkCargo.setCheckCargoPlaceId(cp.getCargoPlaceId());
    		checkCargo.setCheckCargoSiteId(cp.getCargoSiteId());
    		checkCargo.setCheckCargoPlaceName(cp.getCargoPlaceName());
    		checkCargo.setCheckCargoSiteName(cp.getCargoSiteName());
    		checkCargo.setCheckWeight(cpsend1.add(cpsend2));
    		checkList.add(checkCargo);
		}else {
			boolean flag = true;
			for (int i = 1; i < cargoPlaceList.size(); i++) {
				TbTrainOrderCargoPalce cps = cargoPlaceList.get(i);
				BigDecimal cpssend1 = cps.getSendWeight()==null?new BigDecimal(0):cps.getSendWeight();
				BigDecimal cpssend2 = cps.getConSendWeight2()==null?new BigDecimal(0):cps.getConSendWeight2();
				if (cps.equals(cp)) {
					checkCargo.setCheckCargoPlaceId(cps.getCargoPlaceId());
					checkCargo.setCheckCargoSiteId(cps.getCargoSiteId());
					checkCargo.setCheckCargoPlaceName(cps.getCargoPlaceName());
		    		checkCargo.setCheckCargoSiteName(cps.getCargoSiteName());
					checkCargo.setCheckWeight(checkCargo.getCheckWeight().add(cpssend1).add(cpssend2));
					flag = false;
					//最后一条记录 添加至checkList 跳出循环
					if(i == cargoPlaceList.size() - 1) {
						checkList.add(checkCargo);
						break;
					}
					//排序之后的数据 判断一下一条数据是否还一样，若一样继续累积，否则保存此时累积的数据
					if(!cps.equals(cargoPlaceList.get(i+1))) {
						checkList.add(checkCargo);
						checkCargo = new TrainOrderCargoCheck();
					}
	            }
	            else {
	            	if(flag) {
	            		//第二条比对时，就和第一条不相同，即排序后的第一条货场货位数据就一个 此时需要将对象赋值
	                	//循环排序后的数据，和前一条不相同，此时需要将对象赋值 如相同进入equals方法 累加
	                	checkCargo.setCheckCargoPlaceId(cp.getCargoPlaceId());
	            		checkCargo.setCheckCargoSiteId(cp.getCargoSiteId());
	            		checkCargo.setCheckCargoPlaceName(cp.getCargoPlaceName());
	            		checkCargo.setCheckCargoSiteName(cp.getCargoSiteName());
	            		checkCargo.setCheckWeight(cpsend1.add(cpsend2));
	            		checkList.add(checkCargo);
	            		checkCargo = new TrainOrderCargoCheck();
	            	}
	            	cp = cps;
	            	cpsend1 = cp.getSendWeight()==null?new BigDecimal(0):cp.getSendWeight();
	        		cpsend2 = cp.getConSendWeight2()==null?new BigDecimal(0):cp.getConSendWeight2();
	        		//最后一条记录 添加至checkList 跳出循环
	        		if(i == cargoPlaceList.size() - 1) {
	        			checkCargo.setCheckCargoPlaceId(cp.getCargoPlaceId());
	            		checkCargo.setCheckCargoSiteId(cp.getCargoSiteId());
	            		checkCargo.setCheckCargoPlaceName(cp.getCargoPlaceName());
	            		checkCargo.setCheckCargoSiteName(cp.getCargoSiteName());
	            		checkCargo.setCheckWeight(cpsend1.add(cpsend2));
						checkList.add(checkCargo);
						break;
					}
	        		//排序之后的数据 判断一下一条数据是否还一样，若不一样保存此条数据，若一样进入累积循环
	        		if(!cp.equals(cargoPlaceList.get(i+1))) {
	        			checkCargo.setCheckCargoPlaceId(cp.getCargoPlaceId());
	            		checkCargo.setCheckCargoSiteId(cp.getCargoSiteId());
	            		checkCargo.setCheckCargoPlaceName(cp.getCargoPlaceName());
	            		checkCargo.setCheckCargoSiteName(cp.getCargoSiteName());
	            		checkCargo.setCheckWeight(cpsend1.add(cpsend2));
						checkList.add(checkCargo);
						checkCargo = new TrainOrderCargoCheck();
					}
	        		checkCargo = new TrainOrderCargoCheck();
	        		checkCargo.setCheckWeight(cpsend1.add(cpsend2));
	            }
			}
		}
		//比对库存
		for (TrainOrderCargoCheck trainOrderCargoCheck : checkList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("projectId", orderCargoPlaceDetail.getHidenProjectId());
			map.put("stationId", project.getBeginSiteId());
			map.put("freightYardId", trainOrderCargoCheck.getCheckCargoPlaceId());
			map.put("cargoLocationId", trainOrderCargoCheck.getCheckCargoSiteId());
			map.put("type", (byte)0);
			TbStock oldStock = tbTrainOrderMapper.selectStockByMap(map);
			if(oldStock == null) {
				//此货场货位物库存 需要模拟入库 或者接取卸货
				return LogisticsResult.build(11, trainOrderCargoCheck.getCheckCargoPlaceName()+" "+trainOrderCargoCheck.getCheckCargoSiteName()+" "+"无库存，无法装车");
			}else {
				int result = trainOrderCargoCheck.getCheckWeight().compareTo(oldStock.getCurrentQty()==null?new BigDecimal(0):oldStock.getCurrentQty());
				//compareTo方法，-1小于，0相等，1大于
				if(result == 1){
					return LogisticsResult.build(12, "装车载重大于"+" "+trainOrderCargoCheck.getCheckCargoPlaceName()+" "+trainOrderCargoCheck.getCheckCargoSiteName()+" "+"库存，无法装车");
				}
			}
		}
		for (TbTrainOrderCargoPalce orderCargoPalce : cargoPlaceList) {
			//如果td一行全为空 则不保存信息
			if((orderCargoPalce.getCarType() == "" || orderCargoPalce.getCarType() == null ) && (orderCargoPalce.getCarNumber() == "" || orderCargoPalce.getCarNumber() == null ) && orderCargoPalce.getSendWeight() == null) {
				continue;
			}
			String picture = "";
			int i = 0;
			int j = 0;
			if(orderCargoPalce.getContainerNumber1() != ""){
				i = 1;
				//修改集装箱状态 
				Map<String,Object> cMap = new HashMap<String,Object>();
				cMap.put("status", 2);
				cMap.put("projectId", orderCargoPlaceDetail.getHidenProjectId());
				cMap.put("containerId", orderCargoPalce.getContainerNumber1());
				cMap.put("sysOrgCode", tbSystemUser.getSysOrgCode());
				containerMapper.updateContainerStatus(cMap);
			}
			if(orderCargoPalce.getContainerNumber2() != ""){
				j = 1;
				Map<String,Object> cMap = new HashMap<String,Object>();
				cMap.put("status", 2);
				cMap.put("projectId", orderCargoPlaceDetail.getHidenProjectId());
				cMap.put("containerId", orderCargoPalce.getContainerNumber2());
				cMap.put("sysOrgCode", tbSystemUser.getSysOrgCode());
				containerMapper.updateContainerStatus(cMap);
			}
			//更新库存  先查
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("projectId", orderCargoPlaceDetail.getHidenProjectId());
			map.put("stationId", project.getBeginSiteId());
			map.put("freightYardId", orderCargoPalce.getCargoPlaceId());
			map.put("cargoLocationId", orderCargoPalce.getCargoSiteId());
			map.put("type", (byte)0);
			TbStock oldStock = tbTrainOrderMapper.selectStockByMap(map);
			if(oldStock != null){
				/*if(oldStock.getOutQty() != null || (oldStock.getOutQty().equals(BigDecimal.ZERO))){
					oldStock.setOutQty(oldStock.getOutQty().add(orderCargoPalce.getSendWeight()));
				}*/
				BigDecimal c1Weight = orderCargoPalce.getSendWeight() == null?new BigDecimal(0):orderCargoPalce.getSendWeight();
				BigDecimal c2Weight = orderCargoPalce.getConSendWeight2() == null?new BigDecimal(0):orderCargoPalce.getConSendWeight2();
				if(oldStock.getOutQty() == null){
					//oldStock.setOutQty(orderCargoPalce.getSendWeight());
					oldStock.setOutQty(c1Weight.add(c2Weight));
				}else {
					oldStock.setOutQty(oldStock.getOutQty().add(c1Weight).add(c2Weight));
				}
				if(oldStock.getCurrentQty() != null || !(oldStock.getCurrentQty().equals(BigDecimal.ZERO))){
					oldStock.setCurrentQty(oldStock.getCurrentQty().subtract(c1Weight).subtract(c2Weight));
				}
				//模拟入库操作时 查询该站点的集装箱个数
				int contaNum = containerMapper.selectContainNumByStationId(oldStock.getStationId());
				oldStock.setContainerNum(contaNum);
				if(oldStock.getContainerNum() != 0){
					oldStock.setContainerNum(oldStock.getContainerNum()-(i+j));
				}
				oldStock.setAdjustDate(new Date());
				int rstock = tbStockMapper.updateByPrimaryKeySelective(oldStock);
				if(rstock != 1){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return LogisticsResult.build(13, "更新库存失败");
				}
			}
			if(orderCargoPalce.getSendImg() != null && orderCargoPalce.getSendImg() != ""){
				picture = orderCargoPalce.getSendImg();
			}
			//tb_train_order_cargo_palce插入装车信息
			orderCargoPalce.setTrainOrderId(orderCargoPlaceDetail.getOrderId());
			orderCargoPalce.setSendImg(null);
			trainOrderCargoPalceMapper.insertSelective(orderCargoPalce);
			//获取id 
			Integer cId = orderCargoPalce.getId();
			if(picture != null && picture != ""){
				String img = "";
				LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo",picture,null);
				if(base64UpLoad.getStatus()==200){
					img=base64UpLoad.getData().toString();
				}else{
					return LogisticsResult.build(14, "上传图片失败");
				}
				int irow1 = updateSendImgById(cId,img);
				//orderCargoPalce.setSendImg(img);
			}
		}
		
		TbTrainOrder trainOrder = new TbTrainOrder();
		trainOrder.setId(orderCargoPlaceDetail.getOrderId());
		if(orderCargoPlaceDetail.getEntruckNumbe() != null){
			trainOrder.setEntruckNumbe(orderCargoPlaceDetail.getEntruckNumbe());
		}
		if(orderCargoPlaceDetail.getContainerNums() != null){
			trainOrder.setContainerNums(orderCargoPlaceDetail.getContainerNums());
		}
		if(orderCargoPlaceDetail.getEntruckWeight() != null){
			trainOrder.setEntruckWeight(orderCargoPlaceDetail.getEntruckWeight());
		}
		trainOrder.setUpdateDate(new Date());
		trainOrder.setEntruckDate(new Date());
		trainOrder.setStatus((byte)3);
		trainOrder.setSendOperatorId(tbSystemUser.getName());
		int r = tbTrainOrderMapper.updateByPrimaryKeySelective(trainOrder);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return LogisticsResult.build(15, "更新运单失败");
		}
		return LogisticsResult.ok();
	}

	@Override
	public List<TbTrainOrderCargoPalce> selectTrainCargoByOrdeId(Integer id) {
		TbTrainOrderCargoPalceExample example = new TbTrainOrderCargoPalceExample();
		com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalceExample.Criteria criteria = example.createCriteria();
		criteria.andTrainOrderIdEqualTo(id);
		return trainOrderCargoPalceMapper.selectByExample(example);
	}

	@Override
	public List<TbHistoryLocation> selectHistoryLocationById(Integer id,byte type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		return tbTrainOrderMapper.selectHistoryLocationById(map);
	}

	@Override
	public int insertNewLocation(Integer orderIdLocation, String location) {
		TbHistoryLocation historyLocation = new TbHistoryLocation();
		historyLocation.setLocation(location);
		historyLocation.setTime(new Date());
		historyLocation.setOrderId(orderIdLocation);
		historyLocation.setType((byte)2);
		int r = historyLocationMapper.insertSelective(historyLocation);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	@Transactional
	public int updateSendImgById(Integer id, String img) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("sendImg", img);
		int r = tbTrainOrderMapper.updateSendImgById(map);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	@Transactional
	public int deleteSendImgById(Integer id) {
		int r = tbTrainOrderMapper.deleteSendImgById(id);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	@Transactional
	public int updOrderStatusByParam(Integer id, byte status, TbSystemUser tbSystemUser) {
		//Map<String, Object> map = new HashMap<String, Object>();
		//等待发运 需要更新发运时间
		TbTrainOrder trainOrder = new TbTrainOrder();
		if(status == 4){
			trainOrder.setId(id);
			trainOrder.setUpdateDate(new Date());
			trainOrder.setSendDate(new Date());
			trainOrder.setStatus(status);
			trainOrder.setSendOperatorId(tbSystemUser.getName());
			
			//生成预付款抵用信息
			TbTrainOrder train = tbTrainOrderMapper.selectByPrimaryKey(id);
			TbProject project = tbProjectMapper.selectByPrimaryKey(train.getProjectId());
			//TbFinanceAccount finance = financeAccountMapper.selectByPrimaryKey(Integer.valueOf(train.getAdvanceChargeAccount()));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("projectId", train.getProjectId());
			AdvanceCharge alreadyAdvance = advanceSettlementMapper.selectExistAdvanceCharge(map);
			AdvanceCharge advance = new AdvanceCharge();
			advance.setProjectId(train.getProjectId());
			advance.setProjectCode(train.getProjectCode());
			advance.setBranchId(train.getBranchId());
			advance.setBranchName(train.getBranchName());
			advance.setBillName((byte)1);//运费
			advance.setAdvanceType((byte)1);
			advance.setTrainOrderId(id);
			advance.setPleaseTrainNum(train.getPleaseTrainNumber());
			advance.setEntruckWeight(train.getEntruckWeight());
			advance.setSumCost(project.getFreightSum());
			//抵用账户 预付款已经存入账户
			advance.setPayAccountId(alreadyAdvance.getReceiveAccountId());
			advance.setPayAccountName(alreadyAdvance.getReceiveAccountName());
			//抵用账户
			advance.setPayNumber(alreadyAdvance.getReceiveNumber());
			//计费明细显示账户 ，方便运费和货款用同一个显示
			advance.setAlreadyDeposeAccount(alreadyAdvance.getReceiveNumber());
			advance.setPurposeAmount(train.getEntruckWeight().multiply(project.getFreightSum()));
			//存入单位
			advance.setReceiveUnitId(alreadyAdvance.getReceiveUnitId());
			advance.setReceiveUnitName(alreadyAdvance.getReceiveUnitName());
			advance.setSerialNumber(train.getPleaseTrainNumber());
			advance.setStatus((byte)4);//新增状态 待抵用
			advance.setType((byte)1);//抵用
			advance.setDeleteFlag((byte)0);
			advance.setProduceTime(new Date());
			advance.setSheetNumber(train.getEntruckNumbe());
			advance.setInvalidNumber(train.getSureCarNum() - train.getEntruckNumbe());
			
			Map<String, Object> requestMap = ImmutableMap.of("sysOrgCode", null==AppSession.getCurrentSysOrgCode()?tbSystemUser.getSysOrgCode():AppSession.getCurrentSysOrgCode());
            List<Map<String, Object>> selections = basicDataMapper.getOrgTop(requestMap);
			TbBranchGroup branch = FormUtil.populate(TbBranchGroup.class, selections.get(0), false);
			branch.setName(branch.getText());
			//支出单位
			advance.setPayUnitId(branch.getId());
			advance.setPayUnitName(branch.getName());
			advanceSettlementMapper.addAdvanceCharge(advance);
			
		}else if(status == 5){
			//在途运载
			trainOrder.setId(id);
			trainOrder.setUpdateDate(new Date());
			trainOrder.setStatus(status);
			trainOrder.setSendOperatorId(tbSystemUser.getName());
		}else if(status == 6){
			//等待卸货
			trainOrder.setId(id);
			trainOrder.setUpdateDate(new Date());
			trainOrder.setStatus(status);
			trainOrder.setSendOperatorId(tbSystemUser.getName());
		}else{
			//等待回单
			trainOrder.setId(id);
			trainOrder.setUpdateDate(new Date());
			trainOrder.setStatus(status);
			trainOrder.setSendOperatorId(tbSystemUser.getName());
		}
		int r = tbTrainOrderMapper.updateByPrimaryKeySelective(trainOrder);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	@Transactional
	public int deleteTrainOrderByParam(Integer id, String reason, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("deleteReason", reason);
		map.put("deletePerson", name);
		map.put("deleteDate", new Date());
		map.put("deleteFlag", 1);
		int r = tbTrainOrderMapper.deleteTrainOrderByParam(map);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	public List<TbProject> selectAllProject(byte projectType, List<DotBranchDetail> branchGroups) {
		Map<String, Object> map = new HashMap<String, Object>();
		//分支机构id
		List<Integer> branchIds = new ArrayList<>();
		for (TbBranchGroup tbBranchGroup : branchGroups) {
			branchIds.add(tbBranchGroup.getId());
		}
		map.put("branchGroupIds", branchIds);
		map.put("projectType", projectType);
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		return tbProjectMapper.selectAllProject(map);
	}

	@Override
	public List<TbTrainType> selectAllTrainType() {
		Map<String, Object> map = new HashMap<>();
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
    	List<TbTrainType> list = TbTrainTypeMapper.selectTrainTypeList(map);
		//return tbTrainOrderMapper.selectAllTrainType();
    	return list;
	}

	@Override
	@Transactional
	public LogisticsResult addWaitEntruckOfBulk(TrainOrderCargoByBulkDetail orderCargoPlaceDetail, TbSystemUser tbSystemUser,HttpSession session) {
		TbProject project = tbProjectMapper.selectDetailProjectById(orderCargoPlaceDetail.getHidenProjectId());
		String entruckInfoJson = orderCargoPlaceDetail.getEntruckInfoJson();
		List<TbTrainOrderCargoPalce> cargoPlaceList = JsonUtils.jsonToList(entruckInfoJson,TbTrainOrderCargoPalce.class);
		
		if(cargoPlaceList == null) {
			return LogisticsResult.build(9, "请至少填写一条装车信息");
		}
		int nullflag = 0;
		for (TbTrainOrderCargoPalce tbTrainOrderCargoPalce : cargoPlaceList) {
			//如果传过来的td全为空 则返回提示
			if((tbTrainOrderCargoPalce.getCarType() == "" || tbTrainOrderCargoPalce.getCarType() == null ) && (tbTrainOrderCargoPalce.getCarNumber() == "" || tbTrainOrderCargoPalce.getCarNumber() == null ) 
					&& tbTrainOrderCargoPalce.getSendWeight() == null) {
				nullflag ++;
			}
		}
		if(nullflag == cargoPlaceList.size()) {
			return LogisticsResult.build(10, "请至少填写一条装车信息");
		}
		
		//在集合不为Null的情况下 比对库存
		for (int i = 0; i < cargoPlaceList.size(); i++) {
			//去除一条为null的数据
			if((cargoPlaceList.get(i).getCarType() == "" || cargoPlaceList.get(i).getCarType() == null ) && (cargoPlaceList.get(i).getCarNumber() == "" || cargoPlaceList.get(i).getCarNumber() == null ) 
					&& cargoPlaceList.get(i).getSendWeight() == null) {
				cargoPlaceList.remove(cargoPlaceList.get(i));
			}
		}
		//排序
		Collections.sort(cargoPlaceList, new Comparator<TbTrainOrderCargoPalce>() {
			@Override
			public int compare(TbTrainOrderCargoPalce o1, TbTrainOrderCargoPalce o2) {
			         return o1.getCargoSiteId() - o2.getCargoSiteId();
			}
		});
		List<TrainOrderCargoCheck> checkList = new ArrayList<TrainOrderCargoCheck>();
		TrainOrderCargoCheck checkCargo = new TrainOrderCargoCheck();
		TbTrainOrderCargoPalce cp = cargoPlaceList.get(0);
		BigDecimal cpsend1 = cp.getSendWeight()==null?new BigDecimal(0):cp.getSendWeight();
		checkCargo.setCheckWeight(cpsend1);
		
		if(cargoPlaceList != null && cargoPlaceList.size() <2) {
			//只有一条数据情况下
			checkCargo.setCheckCargoPlaceId(cp.getCargoPlaceId());
    		checkCargo.setCheckCargoSiteId(cp.getCargoSiteId());
    		checkCargo.setCheckCargoPlaceName(cp.getCargoPlaceName());
    		checkCargo.setCheckCargoSiteName(cp.getCargoSiteName());
    		checkCargo.setCheckWeight(cpsend1);
    		checkList.add(checkCargo);
		}else {
			boolean flag = true;
			for (int i = 1; i < cargoPlaceList.size(); i++) {
				TbTrainOrderCargoPalce cps = cargoPlaceList.get(i);
				BigDecimal cpssend1 = cps.getSendWeight()==null?new BigDecimal(0):cps.getSendWeight();
				if (cps.equals(cp)) {
					checkCargo.setCheckCargoPlaceId(cps.getCargoPlaceId());
					checkCargo.setCheckCargoSiteId(cps.getCargoSiteId());
					checkCargo.setCheckCargoPlaceName(cps.getCargoPlaceName());
		    		checkCargo.setCheckCargoSiteName(cps.getCargoSiteName());
					checkCargo.setCheckWeight(checkCargo.getCheckWeight().add(cpssend1));
					flag = false;
					//最后一条记录 添加至checkList 跳出循环
					if(i == cargoPlaceList.size() - 1) {
						checkList.add(checkCargo);
						break;
					}
					//排序之后的数据 判断一下一条数据是否还一样，若一样继续累积，否则保存此时累积的数据
					if(!cps.equals(cargoPlaceList.get(i+1))) {
						checkList.add(checkCargo);
						checkCargo = new TrainOrderCargoCheck();
					}
	            }
	            else {
	            	if(flag) {
	            		//第二条比对时，就和第一条不相同，即排序后的第一条货场货位数据就一个 此时需要将对象赋值
	                	//循环排序后的数据，和前一条不相同，此时需要将对象赋值 如相同进入equals方法 累加
	                	checkCargo.setCheckCargoPlaceId(cp.getCargoPlaceId());
	            		checkCargo.setCheckCargoSiteId(cp.getCargoSiteId());
	            		checkCargo.setCheckCargoPlaceName(cp.getCargoPlaceName());
	            		checkCargo.setCheckCargoSiteName(cp.getCargoSiteName());
	            		checkCargo.setCheckWeight(cpsend1);
	            		checkList.add(checkCargo);
	            		checkCargo = new TrainOrderCargoCheck();
	            	}
	            	cp = cps;
	            	cpsend1 = cp.getSendWeight()==null?new BigDecimal(0):cp.getSendWeight();
	        		//最后一条记录 添加至checkList 跳出循环
	        		if(i == cargoPlaceList.size() - 1) {
	        			checkCargo.setCheckCargoPlaceId(cp.getCargoPlaceId());
	            		checkCargo.setCheckCargoSiteId(cp.getCargoSiteId());
	            		checkCargo.setCheckCargoPlaceName(cp.getCargoPlaceName());
	            		checkCargo.setCheckCargoSiteName(cp.getCargoSiteName());
	            		checkCargo.setCheckWeight(cpsend1);
						checkList.add(checkCargo);
						break;
					}
	        		//排序之后的数据 判断一下一条数据是否还一样，若不一样保存此条数据，若一样进入累积循环
	        		if(!cp.equals(cargoPlaceList.get(i+1))) {
	        			checkCargo.setCheckCargoPlaceId(cp.getCargoPlaceId());
	            		checkCargo.setCheckCargoSiteId(cp.getCargoSiteId());
	            		checkCargo.setCheckCargoPlaceName(cp.getCargoPlaceName());
	            		checkCargo.setCheckCargoSiteName(cp.getCargoSiteName());
	            		checkCargo.setCheckWeight(cpsend1);
						checkList.add(checkCargo);
						checkCargo = new TrainOrderCargoCheck();
					}
	        		checkCargo = new TrainOrderCargoCheck();
	        		checkCargo.setCheckWeight(cpsend1);
	            }
			}
		}
		//比对库存
		for (TrainOrderCargoCheck trainOrderCargoCheck : checkList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("projectId", orderCargoPlaceDetail.getHidenProjectId());
			map.put("stationId", project.getBeginSiteId());
			map.put("freightYardId", trainOrderCargoCheck.getCheckCargoPlaceId());
			map.put("cargoLocationId", trainOrderCargoCheck.getCheckCargoSiteId());
			map.put("type", (byte)0);
			TbStock oldStock = tbTrainOrderMapper.selectStockByMap(map);
			if(oldStock == null) {
				//此货场货位物库存 需要模拟入库 或者接取卸货
				return LogisticsResult.build(11, trainOrderCargoCheck.getCheckCargoPlaceName()+" "+trainOrderCargoCheck.getCheckCargoSiteName()+" "+"无库存，无法装车");
			}else {
				int result = trainOrderCargoCheck.getCheckWeight().compareTo(oldStock.getCurrentQty()==null?new BigDecimal(0):oldStock.getCurrentQty());
				//compareTo方法，-1小于，0相等，1大于
				if(result == 1){
					return LogisticsResult.build(12, "装车载重大于"+" "+trainOrderCargoCheck.getCheckCargoPlaceName()+" "+trainOrderCargoCheck.getCheckCargoSiteName()+" "+"库存，无法装车");
				}
			}
		}
		
		for (TbTrainOrderCargoPalce orderCargoPalce : cargoPlaceList) {
			//如果td一行全为空 则不保存信息
			if((orderCargoPalce.getCarType() == "" || orderCargoPalce.getCarType() == null ) && (orderCargoPalce.getCarNumber() == "" || orderCargoPalce.getCarNumber() == null ) && orderCargoPalce.getSendWeight() == null) {
				continue;
			}
			String picture = "";
			//更新库存  先查
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("projectId", orderCargoPlaceDetail.getHidenProjectId());
			map.put("stationId", project.getBeginSiteId());
			map.put("freightYardId", orderCargoPalce.getCargoPlaceId());
			map.put("cargoLocationId", orderCargoPalce.getCargoSiteId());
			map.put("type", (byte)0);
			TbStock oldStock = tbTrainOrderMapper.selectStockByMap(map);
			if(oldStock != null){
				/*if(oldStock.getOutQty() != null || (oldStock.getOutQty().equals(BigDecimal.ZERO))){
					oldStock.setOutQty(oldStock.getOutQty().add(orderCargoPalce.getSendWeight()));
				}*/
				if(oldStock.getOutQty() == null){
					oldStock.setOutQty(orderCargoPalce.getSendWeight());
				}else {
					oldStock.setOutQty(oldStock.getOutQty().add(orderCargoPalce.getSendWeight()));
				}
				if(oldStock.getCurrentQty() != null || !(oldStock.getCurrentQty().equals(BigDecimal.ZERO))){
					oldStock.setCurrentQty(oldStock.getCurrentQty().subtract(orderCargoPalce.getSendWeight()));
				}
				oldStock.setAdjustDate(new Date());
				int rstock = tbStockMapper.updateByPrimaryKeySelective(oldStock);
				if(rstock != 1){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return LogisticsResult.build(13, "更新库存失败");
				}
			}
			if(orderCargoPalce.getSendImg() != null && orderCargoPalce.getSendImg() != ""){
				picture = orderCargoPalce.getSendImg();
			}
			//tb_train_order_cargo_palce插入装车信息
			orderCargoPalce.setTrainOrderId(orderCargoPlaceDetail.getOrderId());
			orderCargoPalce.setSendImg(null);
			trainOrderCargoPalceMapper.insertSelective(orderCargoPalce);
			//获取id 
			Integer cId = orderCargoPalce.getId();
			if(picture != null && picture != ""){
				String img = "";
				LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo",picture,null);
				if(base64UpLoad.getStatus()==200){
					img=base64UpLoad.getData().toString();
				}else{
					return LogisticsResult.build(14, "上传图片失败");
				}
				int irow1 = updateSendImgById(cId,img);
				//orderCargoPalce.setSendImg(img);
			}
		}
		
		TbTrainOrder trainOrder = new TbTrainOrder();
		trainOrder.setId(orderCargoPlaceDetail.getOrderId());
		if(orderCargoPlaceDetail.getEntruckNumbe() != null){
			trainOrder.setEntruckNumbe(orderCargoPlaceDetail.getEntruckNumbe());
		}
		if(orderCargoPlaceDetail.getEntruckWeight() != null){
			trainOrder.setEntruckWeight(orderCargoPlaceDetail.getEntruckWeight());
		}
		trainOrder.setUpdateDate(new Date());
		trainOrder.setEntruckDate(new Date());
		trainOrder.setStatus((byte)3);
		trainOrder.setSendOperatorId(tbSystemUser.getName());
		int r = tbTrainOrderMapper.updateByPrimaryKeySelective(trainOrder);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return LogisticsResult.build(15, "更新运单失败");
		}
		return LogisticsResult.ok();
	}

	@Override
	@Transactional
	public int handleExceptionById(Integer handleEcxptionId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", handleEcxptionId);
		map.put("isException", (byte)0);
		map.put("deleteFlag", (byte)0);
		int r = tbTrainOrderMapper.updateExceptionByParam(map);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	public TbTrainStation selectTrainStationById(Integer beginCenterSiteId) {
		return tbTrainOrderMapper.selectTrainStationById(beginCenterSiteId);
	}

	@Override
	@Transactional
	public int updateArriveImgById(Integer id, String img) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("unloadImg", img);
		int r = tbTrainOrderMapper.updateArriveImgById(map);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	@Transactional
	public int deleteArriveImg(Integer id) {
		int r = tbTrainOrderMapper.deleteArriveImgById(id);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	public List<TrainOrderDetail> selectDetailById(Integer id) {
		return tbTrainOrderMapper.selectDetailById(id);
	}

	@Override
	@Transactional
	public int updateUnloadWeight(TbTrainOrderCargoPalce tbTrainOrderCargoPalce) {
		//tb_train_order_cargo_palce 更新卸货时间和卸货吨位
		tbTrainOrderCargoPalce.setArriveUnloadTime(new Date());
		int rc = trainOrderCargoPalceMapper.updateByPrimaryKeySelective(tbTrainOrderCargoPalce);
		if(rc != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return rc;
		}
		return rc;
	}

	@Override
	public TbStock selectStockByMap(Map<String, Object> map) {
		return tbTrainOrderMapper.selectStockByMap(map);
	}

	@Override
	@Transactional
	public int updateStcok(TbStock stock) {
		int r = tbStockMapper.updateByPrimaryKeySelective(stock);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	@Transactional
	public int addStock(TbStock newStock) {
		int rs = tbStockMapper.insertSelective(newStock);
		if(rs != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return rs;
		}
		return rs;
	}

	@Override
	public TbTrainOrderCargoPalce selectCompareCargoPalceById(Integer id) {
		return trainOrderCargoPalceMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TbStock> selectStockList(Integer id, Integer beginCenterSiteId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", id);
		map.put("stationId", beginCenterSiteId);
		map.put("type", (byte)0);
		return tbTrainOrderMapper.selectStockList(map);
	}

	@Override
	public int updateSumUnloadWeght(TbTrainOrder trainOrderSumUnload) {
		//给运单表中卸货时间更新 到最后一次卸货
		trainOrderSumUnload.setArriveDate(new Date());
		int r = tbTrainOrderMapper.updateByPrimaryKeySelective(trainOrderSumUnload);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	public List<TbTrainOrder> listTrainOrderByCriteria(TrainOrderSearch trainOrderSearch,TbSystemUser user) {
		TbTrainOrderExample orderExample = new  TbTrainOrderExample();
		Criteria orderCriteria = orderExample.createCriteria();
		//正常运单列表
		if(trainOrderSearch.getOrderStatus() == 1){
			orderCriteria.andTypeEqualTo(trainOrderSearch.getProjectType());
			orderCriteria.andIsExceptionEqualTo((byte)0);
			orderCriteria.andDeleteFlagEqualTo((byte)0);
			orderCriteria.andStatusNotEqualTo((byte)7);
		}else if(trainOrderSearch.getOrderStatus() == 2){
			//异常运单
			orderCriteria.andTypeEqualTo(trainOrderSearch.getProjectType());
			orderCriteria.andIsExceptionEqualTo((byte)1);
			orderCriteria.andDeleteFlagEqualTo((byte)0);
		}else if(trainOrderSearch.getOrderStatus() == 3){
			//历史运单
			orderCriteria.andTypeEqualTo(trainOrderSearch.getProjectType());
			orderCriteria.andIsExceptionEqualTo((byte)0);
			orderCriteria.andDeleteFlagEqualTo((byte)0);
			orderCriteria.andStatusEqualTo((byte)7);
		}else{
			//运单回收站
			orderCriteria.andTypeEqualTo(trainOrderSearch.getProjectType());
			orderCriteria.andIsExceptionEqualTo((byte)0);
			orderCriteria.andDeleteFlagEqualTo((byte)1);
		}
		//orderCriteria.andProjectTypeEqualTo(trainOrderSearch.getProjectType());
		
		orderCriteria.andBranchIdIn(tbBranchGroupMapper.selectDotBranchIdsByUid(user.getId()));//分支机构id
		
		//条件查询
		if(StringUtils.isNotBlank(trainOrderSearch.getProjectCode())){//项目编号
			orderCriteria.andProjectCodeLike(trainOrderSearch.getProjectCode());
		}
		if(trainOrderSearch.getBranchId()!=null){//分支机构
			orderCriteria.andBranchIdEqualTo(trainOrderSearch.getBranchId());
		}
		if(trainOrderSearch.getProjectType()!=null){//项目类型
			orderCriteria.andProjectTypeEqualTo(trainOrderSearch.getProjectType());
		}
		if(StringUtils.isNotBlank(trainOrderSearch.getPleaseTrainNumber())){//运单编号
			orderCriteria.andPleaseTrainNumberLike(trainOrderSearch.getPleaseTrainNumber());
		}
		if(StringUtils.isNotBlank(trainOrderSearch.getCargoName())){//货物名称
			orderCriteria.andCargoNameLike(trainOrderSearch.getCargoName());
		}
		if(trainOrderSearch.getBeginDate()!=null && trainOrderSearch.getEndDate()!=null){//创建时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String strBgeinDate = DateUtils.date2Str(trainOrderSearch.getBeginDate(),sdf);
			String strEndDate = DateUtils.date2Str(trainOrderSearch.getEndDate(),sdf);
	    	orderCriteria.andCreateDateGreaterThanOrEqualTo(strBgeinDate);
	    	orderCriteria.andCreateDateLessThanOrEqualTo(strEndDate);
	    }
		
		if(StringUtils.isNotBlank(trainOrderSearch.getBeginSite())){//始发站点
			orderCriteria.andBeginSiteLike(trainOrderSearch.getBeginSite());
		}
		
		if(StringUtils.isNotBlank(trainOrderSearch.getEndSite())){//到达站点
			orderCriteria.andEndSiteLike(trainOrderSearch.getEndSite());
		}
		
		if(trainOrderSearch.getEntruckDate()!=null){//装车时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String strEntruckDate = DateUtils.date2Str(trainOrderSearch.getEntruckDate(),sdf);
			//Date newEntruckDate = DateUtils.str2Date(strEntruckDate,sdf);
			orderCriteria.andEntruckDateEqualTo(strEntruckDate);
		}
		
		if(trainOrderSearch.getSendDate()!=null){//发车时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String strSendDate = DateUtils.date2Str(trainOrderSearch.getSendDate(),sdf);
			orderCriteria.andSendDateEqualTo(strSendDate);
		}
		
		if(trainOrderSearch.getArriveDate()!=null){//到货时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String strArriveDate = DateUtils.date2Str(trainOrderSearch.getArriveDate(),sdf);
			orderCriteria.andArriveDateEqualTo(strArriveDate);
		}
		orderExample.setOrderByClause("A.id desc");
		List<TbTrainOrder> list = tbTrainOrderMapper.selectByExample(orderExample);
		return list;
	}

	public void criteriaByPage(Criteria orderCriteria,TrainOrderSearch trainOrderSearch){
		
		orderCriteria.andTabNameEqualTo("tb_train_order");
		if(trainOrderSearch == null)
			return ;
		//条件查询
		if(trainOrderSearch.getStatusList() != null && trainOrderSearch.getStatusList().size() > 0) {
			orderCriteria.andStatusIn(trainOrderSearch.getStatusList());
		}
		if(StringUtils.isNotBlank(trainOrderSearch.getProjectCode())){//项目编号
			orderCriteria.andProjectCodeLike(trainOrderSearch.getProjectCode());
		}
		if(trainOrderSearch.getBranchId()!=null){//分支机构
			orderCriteria.andBranchIdEqualTo(trainOrderSearch.getBranchId());
		}
		if(trainOrderSearch.getProjectType()!=null){//项目类型
			orderCriteria.andProjectTypeEqualTo(trainOrderSearch.getProjectType());
		}
		if(StringUtils.isNotBlank(trainOrderSearch.getPleaseTrainNumber())){//运单编号
			orderCriteria.andPleaseTrainNumberLike(trainOrderSearch.getPleaseTrainNumber());
		}
		if(StringUtils.isNotBlank(trainOrderSearch.getCargoName())){//货物名称
			orderCriteria.andCargoNameLike(trainOrderSearch.getCargoName());
		}
		if(trainOrderSearch.getBeginDate()!=null && trainOrderSearch.getEndDate()!=null){//创建时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String strBgeinDate = DateUtils.date2Str(trainOrderSearch.getBeginDate(),sdf);
			String strEndDate = DateUtils.date2Str(trainOrderSearch.getEndDate(),sdf);
	    	orderCriteria.andCreateDateGreaterThanOrEqualTo(strBgeinDate);
	    	orderCriteria.andCreateDateLessThanOrEqualTo(strEndDate);
	    }
		
		if(StringUtils.isNotBlank(trainOrderSearch.getBeginSite())){//始发站点
			orderCriteria.andBeginSiteLike(trainOrderSearch.getBeginSite());
		}
		
		if(StringUtils.isNotBlank(trainOrderSearch.getEndSite())){//到达站点
			orderCriteria.andEndSiteLike(trainOrderSearch.getEndSite());
		}
		
		if(trainOrderSearch.getEntruckDate()!=null){//装车时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String strEntruckDate = DateUtils.date2Str(trainOrderSearch.getEntruckDate(),sdf);
			//Date newEntruckDate = DateUtils.str2Date(strEntruckDate,sdf);
			orderCriteria.andEntruckDateEqualTo(strEntruckDate);
		}
		
		if(trainOrderSearch.getSendDate()!=null){//发车时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String strSendDate = DateUtils.date2Str(trainOrderSearch.getSendDate(),sdf);
			orderCriteria.andSendDateEqualTo(strSendDate);
		}
		
		if(trainOrderSearch.getArriveDate()!=null){//到货时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String strArriveDate = DateUtils.date2Str(trainOrderSearch.getArriveDate(),sdf);
			orderCriteria.andArriveDateEqualTo(strArriveDate);
		}
	}

	@Override
	public int unloadInfoByList(List<TbTrainOrderCargoPalce> cargoPlaceList,Integer projectId,TbSystemUser tbSystemUser, HttpSession session) {
		TbProject project = tbProjectMapper.selectDetailProjectById(projectId);
		TbTrainOrder trainOrderUnloadWeight = selectTrainOrderById(cargoPlaceList.get(0).getTrainOrderId());
		int row = 0;
		for (TbTrainOrderCargoPalce tbTrainOrderCargoPalce : cargoPlaceList) {
			//上传发货运单
			if(tbTrainOrderCargoPalce.getSendImg() != null && tbTrainOrderCargoPalce.getSendImg() != ""){
				String img = "";
				LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo",tbTrainOrderCargoPalce.getSendImg(),null);
				if(base64UpLoad.getStatus()==200){
					img=base64UpLoad.getData().toString();
				}else{
					//return LogisticsResult.build(400, "上传图片失败!");
					return 0;
				}
				int irow1 = updateSendImgById(tbTrainOrderCargoPalce.getId(),img);
				tbTrainOrderCargoPalce.setSendImg(img);
			}
			//上传到货运单
			if(tbTrainOrderCargoPalce.getUnloadImg() != null && tbTrainOrderCargoPalce.getUnloadImg() != ""){
				String aimg = "";
				LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo",tbTrainOrderCargoPalce.getUnloadImg(),null);
				if(base64UpLoad.getStatus()==200){
					aimg=base64UpLoad.getData().toString();
				}else{
					return 0;
				}
				int irow2 = updateArriveImgById(tbTrainOrderCargoPalce.getId(),aimg);
				tbTrainOrderCargoPalce.setUnloadImg(aimg);
			}
			int i = 0;
			int j = 0;
			BigDecimal k = new BigDecimal(0);
			boolean flag = false;
			//查询原先卸货吨位
			TbTrainOrderCargoPalce compareCargoPalce = selectCompareCargoPalceById(tbTrainOrderCargoPalce.getId());
			BigDecimal com1UnWeight = compareCargoPalce.getUnloadWeight()==null?new BigDecimal(0):compareCargoPalce.getUnloadWeight();
			BigDecimal com2UnWeight = compareCargoPalce.getConUnloadWeight2()==null?new BigDecimal(0):compareCargoPalce.getConUnloadWeight2();
			BigDecimal a = com1UnWeight.add(com2UnWeight);
			BigDecimal new1UnWeight = tbTrainOrderCargoPalce.getUnloadWeight()==null?new BigDecimal(0):tbTrainOrderCargoPalce.getUnloadWeight();
			BigDecimal new2UnWeight = tbTrainOrderCargoPalce.getConUnloadWeight2()==null?new BigDecimal(0):tbTrainOrderCargoPalce.getConUnloadWeight2();
			BigDecimal b = new1UnWeight.add(new2UnWeight);
			
			//先比对卸货吨位是否改变 在卸货货场货位不变情况下
			if((compareCargoPalce.getUnloadWeight()!=null || compareCargoPalce.getConUnloadWeight2() != null) && (compareCargoPalce.getArriveCargoPlaceId()==tbTrainOrderCargoPalce.getArriveCargoPlaceId() && compareCargoPalce.getArriveCargoSiteId()==tbTrainOrderCargoPalce.getArriveCargoSiteId())){
				//比对  不相等
				int result = a.compareTo(b);
				//compareTo方法，-1小于，0相等，1大于
				if(result == 1 || result == -1){
					//BigDecimal a = compareCargoPalce.getUnloadWeight();
					//BigDecimal b = tbTrainOrderCargoPalce.getUnloadWeight();
					//k为两者之差
					k = a.subtract(b);
					//更改train_order表中的 总卸货吨位 减去修改之前的
					trainOrderUnloadWeight.setArriveWeight(trainOrderUnloadWeight.getArriveWeight().subtract(a));
					int rs1 = updateSumUnloadWeght(trainOrderUnloadWeight);
				}else{
					//相等（改变值 与之前相同）
					flag = true;
				}
			}
			//先比对卸货吨位是否改变 在卸货货场 货位改变情况下  减去库存表中原来的库存吨位
			if(((compareCargoPalce.getUnloadWeight()!=null || compareCargoPalce.getConUnloadWeight2() != null) && compareCargoPalce.getArriveCargoPlaceId()==tbTrainOrderCargoPalce.getArriveCargoPlaceId() && compareCargoPalce.getArriveCargoSiteId()!=tbTrainOrderCargoPalce.getArriveCargoSiteId())
					|| ((compareCargoPalce.getUnloadWeight()!=null || compareCargoPalce.getConUnloadWeight2() != null) && compareCargoPalce.getArriveCargoPlaceId()!=tbTrainOrderCargoPalce.getArriveCargoPlaceId() && compareCargoPalce.getArriveCargoSiteId()!=tbTrainOrderCargoPalce.getArriveCargoSiteId())){
				//比对  不相等
				//if(Math.abs(compareCargoPalce.getUnloadWeight()-tbTrainOrderCargoPalce.getUnloadWeight())>0){
					//若改变  减去库存表中原来的库存吨位
					//先查询是否有记录
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("projectId", projectId);
					map.put("stationId", project.getEndSiteId());
					map.put("freightYardId", compareCargoPalce.getArriveCargoPlaceId());
					map.put("cargoLocationId", compareCargoPalce.getArriveCargoSiteId());
					map.put("type", (byte)1);
					TbStock oldStock = selectStockByMap(map);
					TbStock updStock = new TbStock();
					if(oldStock != null){
						updStock.setId(oldStock.getId());
						//减去
						updStock.setEnterQty(oldStock.getEnterQty().subtract(a));
						updStock.setCurrentQty(oldStock.getCurrentQty().subtract(a));
						int updsk = updateStcok(updStock);
						if(updsk != 1){
							//return LogisticsResult.build(4, "更新原先库存失败");
							return 2000;
						}
					}
					//更改train_order表中的 总卸货吨位 减去修改之前的
					trainOrderUnloadWeight.setArriveWeight(trainOrderUnloadWeight.getArriveWeight().subtract(a));
					int rs2 = updateSumUnloadWeght(trainOrderUnloadWeight);
				//}
			}
			row = row + updateUnloadWeight(tbTrainOrderCargoPalce);
			
			//如果总卸货吨位减去了之前卸货吨位，再查一次
			TbTrainOrder trainOrderSumUnload = selectTrainOrderById(tbTrainOrderCargoPalce.getTrainOrderId());
			//减去后 加上修改后的值
			if(trainOrderSumUnload.getArriveWeight() == null){
				if(b != null){
					trainOrderSumUnload.setArriveWeight(b);
				}
			}else{
				//原来代码是 tbTrainOrderCargoPalce.getUnloadWeight() != null
				if(b != null){
					trainOrderSumUnload.setArriveWeight(trainOrderSumUnload.getArriveWeight().add(b));
				}
			}
			int rsum = updateSumUnloadWeght(trainOrderSumUnload);
			if(rsum != 1){
				//return LogisticsResult.build(5, "修改吨位失败");
				return 3000;
			}
			//如果集装箱都有则加 1
			if(tbTrainOrderCargoPalce.getContainerNumber1() != ""){
				i = 1;
				//更改集装箱位置  trainLocationId
				Map<String, Object> containNumMap = new HashMap<String, Object>();
				containNumMap.put("trainLocationId", project.getEndSiteId());
				containNumMap.put("containerId", tbTrainOrderCargoPalce.getContainerNumber1());
				containNumMap.put("sysOrgCode", tbSystemUser.getSysOrgCode());
				int upl1 = tbTrainStationMapper.updateContainNumLocation(containNumMap);
				//根据项目模式更改集装箱状态 project
				Map<String,Object> cMap = new HashMap<String,Object>();
				//项目模式 0 汽运  1 接取 2 送达 3 火运 4 接取+火运 5 火运+送达 6 联运 7 接取+送达
				if(project.getTransportType() == 3 || project.getTransportType() == 4) {
					cMap.put("status", 0);
					cMap.put("projectId", null);
				}else if(project.getTransportType() == 5 || project.getTransportType() == 6) {
					cMap.put("status", 1);
					cMap.put("projectId", project.getId());
				}
				cMap.put("containerId", tbTrainOrderCargoPalce.getContainerNumber1());
				cMap.put("sysOrgCode", tbSystemUser.getSysOrgCode());
				containerMapper.updateContainerStatus(cMap);
			}
			if(tbTrainOrderCargoPalce.getContainerNumber2() != ""){
				j = 1;
				Map<String, Object> containNumMap2 = new HashMap<String, Object>();
				containNumMap2.put("trainLocationId", project.getEndSiteId());
				containNumMap2.put("containerId", tbTrainOrderCargoPalce.getContainerNumber2());
				containNumMap2.put("sysOrgCode", tbSystemUser.getSysOrgCode());
				int upl2 = tbTrainStationMapper.updateContainNumLocation(containNumMap2);
				//根据项目模式更改集装箱状态 project
				Map<String,Object> cMap = new HashMap<String,Object>();
				//项目模式 0 汽运  1 接取 2 送达 3 火运 4 接取+火运 5 火运+送达 6 联运 7 接取+送达
				if(project.getTransportType() == 3 || project.getTransportType() == 4) {
					cMap.put("status", 0);
					cMap.put("projectId", null);
				}else if(project.getTransportType() == 5 || project.getTransportType() == 6) {
					cMap.put("status", 1);
					cMap.put("projectId", project.getId());
				}
				cMap.put("containerId", tbTrainOrderCargoPalce.getContainerNumber2());
				cMap.put("sysOrgCode", tbSystemUser.getSysOrgCode());
				containerMapper.updateContainerStatus(cMap);
			}
			//分配完之后更新库存表库存
			//先查询是否有记录
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("projectId", projectId);
			map.put("stationId", project.getEndSiteId());
			map.put("freightYardId", tbTrainOrderCargoPalce.getArriveCargoPlaceId());
			map.put("cargoLocationId", tbTrainOrderCargoPalce.getArriveCargoSiteId());
			map.put("type", (byte)1);
			TbStock stock = selectStockByMap(map);
			if(stock != null){
				//存在则更新库存
				//stock.setContainerNum(stock.getContainerNum()+(i+j));
				//更新卸货库存之前 先比对卸货吨位是否改变。
				if(!k.equals(BigDecimal.ZERO)){
					//用之前吨位减去 差 就是修改后的吨位
					stock.setEnterQty(stock.getEnterQty().subtract(k));
					stock.setCurrentQty(stock.getEnterQty().subtract(k));
				}else{
					if(flag){
						//值改变后与之前相同 就不更新库存值
					}else{
						stock.setContainerNum(stock.getContainerNum()+(i+j));
						if(stock.getEnterQty() == null) {
							stock.setEnterQty(b);
						}else {
							stock.setEnterQty(stock.getEnterQty().add(b));
						}
						if(stock.getCurrentQty() == null ) {
							stock.setCurrentQty(b);
						}else {
							stock.setCurrentQty(stock.getCurrentQty().add(b));
						}
					}
				}
				stock.setAdjustDate(new Date());
				int upds = updateStcok(stock);
				if(upds != 1){
					//return LogisticsResult.build(2, "更新库存失败");
					return 4000;
				}
			}else{
				TbStock newStock = new TbStock();
				newStock.setStationId(project.getEndSiteId());
				newStock.setStationName(project.getEndSiteName());
				newStock.setFreightYardId(tbTrainOrderCargoPalce.getArriveCargoPlaceId());
				newStock.setFreightYardName(tbTrainOrderCargoPalce.getArriveCargoPlaceName());
				newStock.setCargoLocationId(tbTrainOrderCargoPalce.getArriveCargoSiteId());
				newStock.setCargoLocationName(tbTrainOrderCargoPalce.getArriveCargoSiteName());
				if(b == null) {
					newStock.setEnterQty(new BigDecimal(0));
					newStock.setCurrentQty(new BigDecimal(0));
				}else {
					newStock.setEnterQty(b);
					newStock.setCurrentQty(b);
				}
				newStock.setOutQty(new BigDecimal(0));
				newStock.setContainerNum(i+j);
				newStock.setAdjustQty(new BigDecimal(0));
				newStock.setProjectId(projectId);
				newStock.setProjectCode(project.getProjectCode());
				newStock.setProjectType(project.getProjectType());
				newStock.setType((byte)1);
				newStock.setAdjustDate(new Date());
				int adds = addStock(newStock);
				if(adds != 1){
					//return LogisticsResult.build(2, "更新库存失败");
					return 5000;
				}
			}
		}
		return row;
	}
	
	//散装箱卸货
	@Override
	public int unloadInfoByListOfBulk(List<TbTrainOrderCargoPalce> cargoPlaceList, Integer projectId,HttpSession session) {
		TbProject project = tbProjectMapper.selectDetailProjectById(projectId);
		TbTrainOrder trainOrderUnloadWeight = selectTrainOrderById(cargoPlaceList.get(0).getTrainOrderId());
		int row = 0;
		for (TbTrainOrderCargoPalce tbTrainOrderCargoPalce : cargoPlaceList) {
			//上传发货运单
			if(tbTrainOrderCargoPalce.getSendImg() != null && tbTrainOrderCargoPalce.getSendImg() != ""){
				String img = "";
				LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo",tbTrainOrderCargoPalce.getSendImg(),null);
				if(base64UpLoad.getStatus()==200){
					img=base64UpLoad.getData().toString();
				}else{
					//return LogisticsResult.build(400, "上传图片失败!");
					return 0;
				}
				int irow1 = updateSendImgById(tbTrainOrderCargoPalce.getId(),img);
				tbTrainOrderCargoPalce.setSendImg(img);
			}
			//上传到货运单
			if(tbTrainOrderCargoPalce.getUnloadImg() != null && tbTrainOrderCargoPalce.getUnloadImg() != ""){
				String aimg = "";
				LogisticsResult base64UpLoad = ImageUtil.base64UpLoad("upload/photo",tbTrainOrderCargoPalce.getUnloadImg(),null);
				if(base64UpLoad.getStatus()==200){
					aimg=base64UpLoad.getData().toString();
				}else{
					//return LogisticsResult.build(400, "上传图片失败!");
				}
				int irow2 = updateArriveImgById(tbTrainOrderCargoPalce.getId(),aimg);
				tbTrainOrderCargoPalce.setUnloadImg(aimg);
			}
			BigDecimal k = new BigDecimal(0);
			boolean flag = false;
			//查询原先卸货吨位
			TbTrainOrderCargoPalce compareCargoPalce = selectCompareCargoPalceById(tbTrainOrderCargoPalce.getId());
			//先比对卸货吨位是否改变 在卸货货场货位不变情况下
			if(compareCargoPalce.getUnloadWeight()!=null && compareCargoPalce.getArriveCargoPlaceId()==tbTrainOrderCargoPalce.getArriveCargoPlaceId() && compareCargoPalce.getArriveCargoSiteId()==tbTrainOrderCargoPalce.getArriveCargoSiteId()){
				//比对  不相等
				int result = compareCargoPalce.getUnloadWeight().compareTo(tbTrainOrderCargoPalce.getUnloadWeight());
				//compareTo方法，-1小于，0相等，1大于
				if(result == 1 || result == -1){
					BigDecimal a = compareCargoPalce.getUnloadWeight();
					BigDecimal b = tbTrainOrderCargoPalce.getUnloadWeight();
					//k为两者之差
					k = a.subtract(b);
					//更改train_order表中的 总卸货吨位 减去修改之前的
					trainOrderUnloadWeight.setArriveWeight(trainOrderUnloadWeight.getArriveWeight().subtract(compareCargoPalce.getUnloadWeight()));
					int rs1 = updateSumUnloadWeght(trainOrderUnloadWeight);
				}else{
					//相等（改变值 与之前相同）
					flag = true;
				}
			}
			//先比对卸货吨位是否改变 在卸货货场 货位改变情况下  减去库存表中原来的库存吨位
			if((compareCargoPalce.getUnloadWeight()!=null && compareCargoPalce.getArriveCargoPlaceId()==tbTrainOrderCargoPalce.getArriveCargoPlaceId() && compareCargoPalce.getArriveCargoSiteId()!=tbTrainOrderCargoPalce.getArriveCargoSiteId())
					|| (compareCargoPalce.getUnloadWeight()!=null && compareCargoPalce.getArriveCargoPlaceId()!=tbTrainOrderCargoPalce.getArriveCargoPlaceId() && compareCargoPalce.getArriveCargoSiteId()!=tbTrainOrderCargoPalce.getArriveCargoSiteId())){
				//比对  不相等
				//if(Math.abs(compareCargoPalce.getUnloadWeight()-tbTrainOrderCargoPalce.getUnloadWeight())>0){
					//若改变  减去库存表中原来的库存吨位
					//先查询是否有记录
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("projectId", projectId);
					map.put("stationId", project.getEndSiteId());
					map.put("freightYardId", compareCargoPalce.getArriveCargoPlaceId());
					map.put("cargoLocationId", compareCargoPalce.getArriveCargoSiteId());
					map.put("type", (byte)1);
					TbStock oldStock = selectStockByMap(map);
					TbStock updStock = new TbStock();
					if(oldStock != null){
						updStock.setId(oldStock.getId());
						//减去
						updStock.setEnterQty(oldStock.getEnterQty().subtract(compareCargoPalce.getUnloadWeight()));
						updStock.setCurrentQty(oldStock.getCurrentQty().subtract(compareCargoPalce.getUnloadWeight()));
						int updsk = updateStcok(updStock);
						if(updsk != 1){
							//return LogisticsResult.build(4, "更新原先库存失败");
							return 2000;
						}
					}
					//更改train_order表中的 总卸货吨位 减去修改之前的
					trainOrderUnloadWeight.setArriveWeight(trainOrderUnloadWeight.getArriveWeight().subtract(compareCargoPalce.getUnloadWeight()));
					int rs2 = updateSumUnloadWeght(trainOrderUnloadWeight);
				//}
			}
			row = row + updateUnloadWeight(tbTrainOrderCargoPalce);
			//如果总卸货吨位减去了之前卸货吨位，再查一次
			TbTrainOrder trainOrderSumUnload = selectTrainOrderById(tbTrainOrderCargoPalce.getTrainOrderId());
			//减去后 加上修改后的值
			if(trainOrderSumUnload.getArriveWeight() == null){
				if(tbTrainOrderCargoPalce.getUnloadWeight() != null){
					trainOrderSumUnload.setArriveWeight(tbTrainOrderCargoPalce.getUnloadWeight());
				}
			}else{
				if(tbTrainOrderCargoPalce.getUnloadWeight() != null){
					trainOrderSumUnload.setArriveWeight(trainOrderSumUnload.getArriveWeight().add(tbTrainOrderCargoPalce.getUnloadWeight()));
				}
			}
			int rsum = updateSumUnloadWeght(trainOrderSumUnload);
			if(rsum != 1){
				//return LogisticsResult.build(5, "修改吨位失败");
				return 3000;
			}
			//分配完之后更新库存表库存
			//先查询是否有记录
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("projectId", projectId);
			map.put("stationId", project.getEndSiteId());
			map.put("freightYardId", tbTrainOrderCargoPalce.getArriveCargoPlaceId());
			map.put("cargoLocationId", tbTrainOrderCargoPalce.getArriveCargoSiteId());
			map.put("type", (byte)1);
			TbStock stock = selectStockByMap(map);
			if(stock != null){
				//存在则更新库存
				//更新卸货库存之前 先比对卸货吨位是否改变。
				if(!k.equals(BigDecimal.ZERO)){
					//用之前吨位减去 差 就是修改后的吨位
					stock.setEnterQty(stock.getEnterQty().subtract(k));
					stock.setCurrentQty(stock.getEnterQty().subtract(k));
				}else{
					if(flag){
						//值改变后与之前相同 就不更新库存值
					}else{
						//stock.setEnterQty(stock.getEnterQty().add(tbTrainOrderCargoPalce.getUnloadWeight()));
						//stock.setCurrentQty(stock.getCurrentQty().add(tbTrainOrderCargoPalce.getUnloadWeight()));
						if(stock.getEnterQty() == null) {
							stock.setEnterQty(tbTrainOrderCargoPalce.getUnloadWeight());
						}else {
							stock.setEnterQty(stock.getEnterQty().add(tbTrainOrderCargoPalce.getUnloadWeight()));
						}
						if(stock.getCurrentQty() == null ) {
							stock.setCurrentQty(tbTrainOrderCargoPalce.getUnloadWeight());
						}else {
							stock.setCurrentQty(stock.getCurrentQty().add(tbTrainOrderCargoPalce.getUnloadWeight()));
						}
					}
				}
				stock.setAdjustDate(new Date());
				int upds = updateStcok(stock);
				if(upds != 1){
					//return LogisticsResult.build(2, "更新库存失败");
					return 4000;
				}
			}else{
				TbStock newStock = new TbStock();
				newStock.setStationId(project.getEndSiteId());
				newStock.setStationName(project.getEndSiteName());
				newStock.setFreightYardId(tbTrainOrderCargoPalce.getArriveCargoPlaceId());
				newStock.setFreightYardName(tbTrainOrderCargoPalce.getArriveCargoPlaceName());
				newStock.setCargoLocationId(tbTrainOrderCargoPalce.getArriveCargoSiteId());
				newStock.setCargoLocationName(tbTrainOrderCargoPalce.getArriveCargoSiteName());
				if(tbTrainOrderCargoPalce.getUnloadWeight() == null) {
					newStock.setEnterQty(new BigDecimal(0));
					newStock.setCurrentQty(new BigDecimal(0));
				}else {
					newStock.setEnterQty(tbTrainOrderCargoPalce.getUnloadWeight());
					newStock.setCurrentQty(tbTrainOrderCargoPalce.getUnloadWeight());
				}
				
				newStock.setOutQty(new BigDecimal(0));
				//newStock.setContainerNum(i+j);
				newStock.setAdjustQty(new BigDecimal(0));
				newStock.setProjectId(projectId);
				newStock.setProjectCode(project.getProjectCode());
				newStock.setProjectType(project.getProjectType());
				newStock.setType((byte)1);
				newStock.setAdjustDate(new Date());
				int adds = addStock(newStock);
				if(adds != 1){
					//return LogisticsResult.build(2, "更新库存失败");
					return 5000;
				}
			}
		}
		return row;
	}

	@Override
	public List<ProjectAppHelp> selectAppAllProject(Integer id,String sysOrgCode) {
		//当前面登录人分支机构
		Map<String, Object> map = new HashMap<>();
		map.put("status", Constants.DOT_BRANCH_STATUS_YES);
		map.put("sysOrgCode", sysOrgCode);
		//List<TbBranchGroup> branchGroups = branchGroupMapper.selectDotBranchByUid(map);
		List<DotBranchDetail> branchGroups = branchGroupMapper.getBranchGroups(map);
		if(branchGroups==null || branchGroups.size()==0){
			return null;
		}
		map.clear();
		//分支机构id
		List<Integer> branchIds = new ArrayList<>();
		for (TbBranchGroup tbBranchGroup : branchGroups) {
			branchIds.add(tbBranchGroup.getId());
		}
		map.put("branchGroupIdApps", branchIds);
		map.put("sysOrgCode", sysOrgCode);
		return tbProjectMapper.selectAppAllProject(map);
	}

	@Override
	public AdvanceCharge selectAccountListById(Integer projectId,Integer beginCenterSiteId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);
		map.put("beginCenterSiteId", beginCenterSiteId);
		return tbTrainOrderMapper.selectAccountListById(map);
	}

	@Override
	public TbCargoLocation selectCargoLocationById(Integer cargoLocationId) {
		return tlMapper.selectByPrimaryKey(cargoLocationId);
	}

	@Override
	public int checkEntruckWeight(String[] array) {
		Integer projectId = Integer.valueOf(array[0]);
		Integer stationId = Integer.valueOf(array[1]);
		Integer cargoId = Integer.valueOf(array[2]);
		Integer siteId = Integer.valueOf(array[3]);
		BigDecimal weight = new BigDecimal(array[4]);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);
		map.put("stationId", stationId);
		map.put("freightYardId", cargoId);
		map.put("cargoLocationId", siteId);
		map.put("type", (byte)0);
		TbStock oldStock = selectStockByMap(map);
		if(oldStock == null) {
			return 2;
		}else {
			BigDecimal compareWeight = oldStock.getCurrentQty() == null?new BigDecimal(0):oldStock.getCurrentQty();
			int res = weight.compareTo(compareWeight);
			if(res == 1) {
				//compareTo方法，-1小于，0相等，1大于
				return 3;
			}
		}
		return 1;
	}
}
