// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.business.helpPojo.TbFreightLocationDetail;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.TbFreightYardDetail;
import com.shenhesoft.logistics.manage.interfaces.SiteManageService;
import com.shenhesoft.logistics.manage.mapper.TbCargoLocationMapper;
import com.shenhesoft.logistics.manage.mapper.TbCustomerMapper;
import com.shenhesoft.logistics.manage.mapper.TbFreightYardMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocation;
import com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocationExample;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomerExample;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYardExample;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYardExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;

/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月9日
 */
@Service
public class SiteManageServiceImpl implements SiteManageService {

	@Autowired
	TbFreightYardMapper fyMapper;

	@Autowired
	TbCargoLocationMapper tlMapper;

	@Autowired
	private TbTrainStationMapper tsMapper;
	
	@Autowired
	private TbCustomerMapper customerMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shenhesoft.logistics.manage.interfaces.SiteManageService#
	 * selectFreightYardsByPage()
	 */
	@Override
	public List<TbFreightYardDetail> selectFreightYardsByPage() {
		List<TbFreightYardDetail> list = fyMapper.selectFreightYardsByPage();
		for (TbFreightYardDetail it : list) {
			int localCount = fyMapper.selectLocaltionCountByYardId(it.getId());
			it.setLocalCount(localCount);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shenhesoft.logistics.manage.interfaces.SiteManageService#
	 * insertFreightYard(com.shenhesoft.logistics.manage.helpPojo.
	 * TbFreightYardDetail)
	 */
	@Override
	@Transactional
	public boolean insertFreightYard(TbFreightYardDetail tfyd)
			throws JsonParseException, JsonMappingException, IOException {
		if(tfyd.getCity()==null) {
			tfyd.setCity("");
		}
		if(tfyd.getDistrict()==null) {
			tfyd.setDistrict("");
		}
		tfyd.setAddressCode(tfyd.getProvince()+","+tfyd.getCity()+","+tfyd.getDistrict());
		tfyd.setDeleteFlag((byte)0);
		int row = fyMapper.insertSelective(tfyd);
		if (row != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		if(!tfyd.getJson().isEmpty()){
			String jsopn = tfyd.getJson();
			ObjectMapper mapper = new ObjectMapper();
			// json转list<T>
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, TbCargoLocation.class);
			@SuppressWarnings("unchecked")
			List<TbCargoLocation> list = (List<TbCargoLocation>) mapper.readValue(jsopn, javaType);
			for (TbCargoLocation its : list) {
				its.setFreightYardId(tfyd.getId());
				its.setDeleteFlag((byte)0);
				if(!its.getCode().equals("")){
					int rpw = tlMapper.insertSelective(its);
					if (rpw != 1) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return false;
					}
				}
			}
		}
		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(tfyd.getId().toString());
		branchGroupLink.setTabName("tb_freight_yard");
		branchGroupLink.setTabComment("货场");
		branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.SiteManageService#delete(java.util.List)
	 */
	@Override
	@Transactional
	public boolean delete(List<Integer> list) {
		for(Integer ids :list){
			//先查有多少条记录
			List<TbCargoLocation> cls = fyMapper.selecCargotByid(ids);
			//删除关联货位
			int row2 = fyMapper.deleteRelateBatchCargoLocation(ids);
			if(row2 != cls.size()){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
		}
		int row = fyMapper.deleteBatch(list);
		if(row != list.size()){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.SiteManageService#selectFreightYardsById(java.lang.Integer)
	 */
	@Override
	public TbFreightYardDetail selectFreightYardsById(Integer id) {
		TbFreightYardDetail fyDetail = fyMapper.selectFreightYardsByIdOfNoStation(id);
		/*if(fyDetail.getTrainStationId() != null) {
			fyDetail = fyMapper.selectFreightYardsById(id);
		}*/
		//TbFreightYardDetail fyDetail = fyMapper.selectFreightYardsById(id);
		String addr=fyDetail.getAddressCode();
		String[] split =addr.split(",");
		if(split!=null && split.length!=0){
			fyDetail.setProvince(split[0]);
			fyDetail.setCity(split[1]);
			if(split.length==3){
				if(!split[2].equals("null")){
					fyDetail.setDistrict(split[2]);
				}
			}
		}
		List<TbCargoLocation>  locationList = fyMapper.selectCargoLocationByfreightYardId(fyDetail.getId());
		fyDetail.setClList(locationList);
		return fyDetail;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.SiteManageService#updateFreightYardById(com.shenhesoft.logistics.manage.helpPojo.TbFreightYardDetail)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public boolean updateFreightYardById(TbFreightYardDetail tfyd) throws JsonParseException, JsonMappingException, IOException {
		if(tfyd.getCity()==null) {
			tfyd.setCity("");
		}
		if(tfyd.getDistrict()==null) {
			tfyd.setDistrict("");
		}
		tfyd.setAddressCode(tfyd.getProvince()+","+tfyd.getCity()+","+tfyd.getDistrict());
		//由不是独立货场修改为独立货场 清空上级站点
		/*if(tfyd.getIsIsolated() == 1) {
			tfyd.setTrainStationId(null);
		}*/
		TbFreightYard fydl = fyMapper.selectByPrimaryKey(tfyd.getId());
		if((!fydl.getFreightYardImg().equals("")) && (fydl.getFreightYardImg() != null)) {
			tfyd.setFreightYardImg(fydl.getFreightYardImg());
		}
		int row = fyMapper.updateFreightYardById(tfyd);
		if (row != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		//删除关联货位
		//fyMapper.deleteRelateBatchCargoLocation(tfyd.getId());
		
		/*TbCargoLocationExample example = new TbCargoLocationExample();
		com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocationExample.Criteria criteria = example.createCriteria();
		criteria.andFreightYardIdEqualTo(fydl.getId());
		criteria.andDeleteFlagEqualTo((byte)0);
		List<TbCargoLocation> huowei = tlMapper.selectByExample(example);*/
		
		if(!tfyd.getJson().isEmpty()){
			String jsopn = tfyd.getJson();
			ObjectMapper mapper = new ObjectMapper();
			// json转list<T>
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, TbCargoLocation.class);
			List<TbCargoLocation> list = (List<TbCargoLocation>) mapper.readValue(jsopn, javaType);
			for (TbCargoLocation its : list) {
				if(its.getId() != null) {
					int rc = tlMapper.updateByPrimaryKeySelective(its);
					if (rc != 1) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return false;
					}
				}else {
					its.setFreightYardId(tfyd.getId());
					its.setDeleteFlag((byte)0);
					if(!its.getCode().equals("")){
						int rpw = tlMapper.insertSelective(its);
						if (rpw != 1) {
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.SiteManageService#selectFreightYardsByPages(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DataGridResult listFreightYardsByPage(Integer page, Integer limit,TbFreightYard freightYard) {
		TbFreightYardExample example = new TbFreightYardExample();
		example.setOrderByClause("A.id desc");
		Criteria criteria = example.createCriteria();
		  //分页处理，显示第一页的20条数据
        PageHelper.startPage(page, limit);
        freightYardCriteria(freightYard, criteria);//条件查询
		List<TbFreightYardDetail> list = fyMapper.selectFreightYardsByExample(example);
		for (TbFreightYardDetail it : list) {
			int localCount = fyMapper.selectLocaltionCountByYardId(it.getId());
			it.setLocalCount(localCount);
			if(it.getTrainStationId() != null) {
				if(it.getIsIsolated() == 0) {
					TbTrainStation station = tsMapper.selectByPrimaryKey(it.getTrainStationId());
					it.setStationName(null==station?null:station.getStationName());
				}else {
					//查询客户公司名称
					CustomerInfo customer = customerMapper.selectCustomerInfoByCid(it.getTrainStationId());
					if(customer != null) {
						it.setStationName(customer.getCompanyName());
					}
				}
			}
		}
		  // 取分页信息
        PageInfo<TbFreightYardDetail> pageInfo = new PageInfo<TbFreightYardDetail>(list);
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list, limit);
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.SiteManageService#getAllFreightYards()
	 */
	@Override
	public List<TbFreightYardDetail> getAllFreightYards() {
		return fyMapper.getAllFreightYards();
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.SiteManageService#getAllcargoLocationsByYardId(java.lang.Integer)
	 */
	@Override
	public List<TbCargoLocation> getAllcargoLocationsByYardId(Integer id) {
		return fyMapper.getAllcargoLocationsByYardId(id);
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.SiteManageService#getAllFreightLocationsByYardId(java.lang.Integer)
	 */
	@Override
	public List<TbFreightLocationDetail> getAllFreightLocationsByYardId(Integer id) {
		return fyMapper.getAllFreightLocationsByYardId(id);
	}

	@Override
	public List<TbFreightYard> getFreightYardsByTrainStationId(Integer id) {
		TbFreightYardExample example = new TbFreightYardExample();
		Criteria criteria = example.createCriteria();
		criteria.andTrainStationIdEqualTo(id);
		criteria.andIsIsolatedEqualTo((byte)0);
		criteria.andDeleteFlagEqualTo((byte)0);
		List<TbFreightYard> list = fyMapper.selectByExample(example);
		return list;
	}

	/**
	 * @description 货场货位的查询条件
	 * @date 2018年1月13日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private void freightYardCriteria(TbFreightYard freightYard, Criteria criteria) {
		//criteria.andIdIsNotNull();
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
        criteria.andTabNameEqualTo("tb_freight_yard");
		if(freightYard==null){
			return;
		}
		//货场名称
		if(StringUtils.isNotBlank(freightYard.getName())){
			criteria.andNameLike(freightYard.getName());
		}
		//上级站点
		if(freightYard.getTrainStationId()!=null){
			criteria.andTrainStationIdEqualTo(freightYard.getTrainStationId());
		}
		//货场联系人
		if(StringUtils.isNotBlank(freightYard.getLinkman())){
			criteria.andLinkmanLike(freightYard.getLinkman());
		}
		//联系方式
		if(StringUtils.isNotBlank(freightYard.getPhone())){
			criteria.andPhoneLike(freightYard.getPhone());
		}
	}

	@Override
	public List<TbFreightYard> getAllFreightYardOfIsolated(Integer id) {
		return fyMapper.getAllFreightYardsOfIsolate(id);
	}

	@Override
	public List<CustomerInfo> getAllCustomter() {
		TbCustomerExample example = new TbCustomerExample();
		com.shenhesoft.logistics.manage.pojo.customer.TbCustomerExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo((byte)0);
	    criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
	    criteria.andTabNameEqualTo("tb_customer");
		return customerMapper.selectCustomerInfoByExample(example);
	}

	@Override
	public int checkCargoLocation(Integer id) {
		int num = fyMapper.checkCargoLocation(id);
		return num;
	}

	@Override
	public int deleteCargoLocationById(Integer id) {
		return fyMapper.deleteCargoLocationById(id);
	}

	@Override
	public boolean checkFreightIsUseById(List<Integer> idList) {
		for(Integer id :idList){
			int row2 = fyMapper.checkFreightIsUseById(id);
			if(row2 > 0){
				return false;
			}
		}
		return true;
	}
}
