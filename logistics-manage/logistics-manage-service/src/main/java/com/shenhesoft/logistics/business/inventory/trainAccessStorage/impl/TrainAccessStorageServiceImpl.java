// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.inventory.trainAccessStorage.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderDetail;
import com.shenhesoft.logistics.business.inventory.trainAccessStorage.TrainAccessStorageService;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderCargoPalceMapper;
import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalce;
import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalceExample;
import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalceExample.Criteria;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.StringUtils;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2018年1月3日
 */
@Service
public class TrainAccessStorageServiceImpl implements TrainAccessStorageService{
	
	@Autowired
	private TbTrainOrderCargoPalceMapper tbTrainOrderCargoPalceMapper;
	
	@Override
	public DataGridResult selectTrainAccessStorageList(Integer page,Integer limit,TrainOrderDetail orderDetail) {
		PageHelper.startPage(page, limit);
		//查出项目和火运运单关联信息
		orderDetail.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		List<TrainOrderDetail> trainAccessStorageList = tbTrainOrderCargoPalceMapper.selectTrainAccessStorageList(orderDetail);
		for (TrainOrderDetail trainOrderDetail : trainAccessStorageList) {
			TbTrainOrderCargoPalceExample example = new TbTrainOrderCargoPalceExample();
			TbTrainOrderCargoPalceExample exampleArrive = new TbTrainOrderCargoPalceExample();
			Criteria criteria = example.createCriteria();
			Criteria criteriaArrive = exampleArrive.createCriteria();
			criteria.andTrainOrderIdEqualTo(trainOrderDetail.getTrainOrderId());
			criteriaArrive.andTrainOrderIdEqualTo(trainOrderDetail.getTrainOrderId());
			boolean cargoFlag = false;
			boolean siteFlag = false;
			boolean arriveCargoFlag = false;
			boolean arriveSiteFlag = false;
			int isNull = 0;
			//根据运单id查出装载和卸货货场货位
			List<TbTrainOrderCargoPalce> cargoPalce = tbTrainOrderCargoPalceMapper.selectByExample(example);
			if(cargoPalce != null && cargoPalce.size()>0) {
				for (int i = 0; i < cargoPalce.size(); i++) {
					for (int j = 0; j < cargoPalce.size(); j++) {
					    if(null==cargoPalce.get(i) || null==cargoPalce.get(i).getCargoPlaceId()){
					      continue;
					    }
						if(!cargoPalce.get(i).getCargoPlaceId().equals(cargoPalce.get(j).getCargoPlaceId())){
							//表明装载货场有多个
							cargoFlag = true;
							break;
						}
					}
					if(cargoFlag){
						break;
					}
				}
				if(cargoFlag){
					trainOrderDetail.setCargoPlaceName("多货");
					trainOrderDetail.setCargoSiteName("多位");
				}else{
  				    if(null==cargoPalce.get(0) || null==cargoPalce.get(0).getCargoPlaceId()){
                      continue;
                    }
					//可能存在一个装载货场 多装载个货位
					trainOrderDetail.setCargoPlaceId(cargoPalce.get(0).getCargoPlaceId());
					trainOrderDetail.setCargoPlaceName(cargoPalce.get(0).getCargoPlaceName());
					criteria.andCargoPlaceIdEqualTo(cargoPalce.get(0).getCargoPlaceId());
					List<TbTrainOrderCargoPalce> cargoSite = tbTrainOrderCargoPalceMapper.selectByExample(example);
					for (int m = 0; m < cargoSite.size(); m++) {
						for (int n = 0; n < cargoSite.size(); n++) {
							if(!cargoSite.get(m).getCargoSiteId().equals(cargoSite.get(n).getCargoSiteId())){
								siteFlag = true;
								break;
							}
						}
						if(siteFlag){
							break;
						}
					}
					if(siteFlag){
						trainOrderDetail.setCargoSiteName("多位");
					}else{
						trainOrderDetail.setCargoSiteId(cargoSite.get(0).getCargoSiteId());
						trainOrderDetail.setCargoSiteName(cargoSite.get(0).getCargoSiteName());
					}
				}
				//卸货货物货位
				isNull = cargoPalce.size();
				for (int i = 0; i < cargoPalce.size(); i++) {
					if(cargoPalce.get(i).getArriveCargoPlaceId() != null){
						for (int j = 0; j < cargoPalce.size(); j++) {
							if(cargoPalce.get(j).getArriveCargoPlaceId() != null){
								if(!cargoPalce.get(i).getArriveCargoPlaceId().equals(cargoPalce.get(j).getArriveCargoPlaceId())){
									//表明卸载货场有多个
									arriveCargoFlag = true;
									break;
								}
							}
						}
					}else{
						//有的还未到达等待卸货状态  就为null
						isNull--;
					}
					if(arriveCargoFlag){
						break;
					}
				}
				if(arriveCargoFlag){
					trainOrderDetail.setArriveCargoPlaceName("多货");
					trainOrderDetail.setArriveCargoSiteName("多位");
				}else{
					if(isNull==0){
						//全为空 全部在等待卸货之前状态
					}else{
						//可能存在一个卸载货场 多个卸载货位
						trainOrderDetail.setArriveCargoPlaceId(cargoPalce.get(0).getArriveCargoPlaceId());
						trainOrderDetail.setArriveCargoPlaceName(cargoPalce.get(0).getArriveCargoPlaceName());
						criteriaArrive.andArriveCargoPlaceIdEqualTo(cargoPalce.get(0).getArriveCargoPlaceId());
						List<TbTrainOrderCargoPalce> arriveCargoSite = tbTrainOrderCargoPalceMapper.selectByExample(exampleArrive);
						for (int m = 0; m < arriveCargoSite.size(); m++) {
							for (int n = 0; n < arriveCargoSite.size(); n++) {
								if(!arriveCargoSite.get(m).getArriveCargoSiteId().equals(arriveCargoSite.get(n).getArriveCargoSiteId())){
									arriveSiteFlag = true;
									break;
								}
							}
							if(arriveSiteFlag){
								break;
							}
						}
						if(arriveSiteFlag){
							trainOrderDetail.setArriveCargoSiteName("多位");
						}else{
							trainOrderDetail.setArriveCargoSiteId(arriveCargoSite.get(0).getArriveCargoSiteId());
							trainOrderDetail.setArriveCargoSiteName(arriveCargoSite.get(0).getArriveCargoSiteName());
						}
					}
				}
				//车型 车号
				trainOrderDetail.setCarType(cargoPalce.get(0).getCarType());
				//trainOrderDetail.setCarNumber(cargoPalce.get(0).getCarNumber());
				trainOrderDetail.setCarNumber("多号");
				//损耗
				if(trainOrderDetail.getEntruckWeight() != null && trainOrderDetail.getArriveWeight() != null){
					trainOrderDetail.setWastage(trainOrderDetail.getEntruckWeight().subtract(trainOrderDetail.getArriveWeight()));
				}
			}
		}
		PageInfo<TrainOrderDetail> pageInfo = new PageInfo<TrainOrderDetail>(trainAccessStorageList);
	    long total = pageInfo.getTotal(); //获取总记录数
	    return new DataGridResult(total, trainAccessStorageList, limit);
	}

	@Override
	public Map<String, Object> queryStatistic(Integer projectId) {
		List<TrainOrderDetail> list = tbTrainOrderCargoPalceMapper.queryStatisticByProjectId(projectId);
		//总计请车吨位
		BigDecimal i = new BigDecimal(0);
		//总计到货吨位
		BigDecimal j = new BigDecimal(0);
		//总计损耗 
		BigDecimal k = new BigDecimal(0);
		for (TrainOrderDetail trainOrderDetail : list) {
			BigDecimal s1 = trainOrderDetail.getSendWeight() == null?new BigDecimal(0):trainOrderDetail.getSendWeight();
			BigDecimal s2 = trainOrderDetail.getConSendWeight2() == null?new BigDecimal(0):trainOrderDetail.getConSendWeight2();
			BigDecimal sumS12 = s1.add(s2);
			BigDecimal u1 = trainOrderDetail.getUnloadWeight() == null?new BigDecimal(0):trainOrderDetail.getUnloadWeight();
			BigDecimal u2 = trainOrderDetail.getConUnloadWeight2() == null?new BigDecimal(0):trainOrderDetail.getConUnloadWeight2();
			BigDecimal sumU12 = u1.add(u2);
			if((trainOrderDetail.getSendWeight()!=null || trainOrderDetail.getConSendWeight2() != null) && (trainOrderDetail.getUnloadWeight()!=null || trainOrderDetail.getConUnloadWeight2() != null)){
				trainOrderDetail.setWastage(sumS12.subtract(sumU12));
				k = k.add(trainOrderDetail.getWastage());
			}
				i = i.add(sumS12);
				j = j.add(sumU12);
			/*if(trainOrderDetail.getSendWeight()!=null && trainOrderDetail.getUnloadWeight()!=null){
				trainOrderDetail.setWastage(trainOrderDetail.getSendWeight().subtract(trainOrderDetail.getUnloadWeight()));
				k = k.add(trainOrderDetail.getWastage());
			}
			if(trainOrderDetail.getSendWeight()!=null){
				i = i.add(trainOrderDetail.getSendWeight());
			}
			if(trainOrderDetail.getUnloadWeight()!=null){
				j = j.add(trainOrderDetail.getUnloadWeight());
			}*/
		}
		TrainOrderDetail sumDetail = tbTrainOrderCargoPalceMapper.selectSumInfo(projectId);
		sumDetail.setSumPleaseWeight(i);
		sumDetail.setSumArriveWeight(j);
		sumDetail.setSumWastage(k);
		Map<String, Object> sumMapInfo = new HashMap<String, Object>();
		sumMapInfo.put("list", list);
		sumMapInfo.put("sumDetail", sumDetail);
		return sumMapInfo;
	}

}
