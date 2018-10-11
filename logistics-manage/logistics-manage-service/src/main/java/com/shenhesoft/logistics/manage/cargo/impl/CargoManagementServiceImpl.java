// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.cargo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.cargo.interfaces.CargomanagementService;
import com.shenhesoft.logistics.manage.helpPojo.AccountManagementDetail;
import com.shenhesoft.logistics.manage.helpPojo.CargoPointDetail;
import com.shenhesoft.logistics.manage.helpPojo.CargoSpecificteDetail;
import com.shenhesoft.logistics.manage.helpPojo.TbCargoInfomation;
import com.shenhesoft.logistics.manage.helpPojo.TbCargoMainPoint;
import com.shenhesoft.logistics.manage.mapper.TbCargoMapper;
import com.shenhesoft.logistics.manage.mapper.TbPointMapper;
import com.shenhesoft.logistics.manage.mapper.TbSpecificationsMapper;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargo;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargoExample;
import com.shenhesoft.logistics.manage.pojo.point.TbPoint;
import com.shenhesoft.logistics.manage.pojo.point.TbPointExample;
import com.shenhesoft.logistics.manage.pojo.point.TbPointExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.specifications.TbSpecifications;
import com.shenhesoft.logistics.manage.pojo.specifications.TbSpecificationsExample;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月5日
 */
@Service
public class CargoManagementServiceImpl implements CargomanagementService{
	
	@Autowired
	private TbCargoMapper tbCargoMapper;
	
	@Autowired
	private TbPointMapper tbPointMapper;
	
	@Autowired
	private TbSpecificationsMapper tbSpecificationsMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	/**
	 * @description 货物信息 :分页条件查询
	 * @author LiangDeng
	 * @date 2017年12月5日
	 * @param 
	 * @return
	*/
	@Override
	public DataGridResult listCargoByPage(Integer page, Integer limit,TbCargo cargo) {
		PageHelper.startPage(page, limit);
		TbCargoExample cargoExample=new TbCargoExample();
		cargoExample.setOrderByClause("A.id desc");
		com.shenhesoft.logistics.manage.pojo.cargo.TbCargoExample.Criteria criteria = cargoExample.createCriteria();
		criteria.andCargoNameIsNotNull();
		cargoCriteria(cargo, criteria);//条件查询
		List<TbCargoMainPoint> list = tbCargoMapper.selectCargoList(cargoExample);
		if(list!=null && list.size()>0){
			for (TbCargoMainPoint mainPoint : list) {
				List<TbSpecifications> specList = selectSpecListBycargoId(mainPoint.getId());
				if(specList!=null && specList.size()>0){
					mainPoint.setListName(specList);
				}
			}
		}
		PageInfo<TbCargoMainPoint> pageInfo = new PageInfo<TbCargoMainPoint>(list);
		return new DataGridResult(pageInfo.getTotal(), list, limit);
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.cargo.interfaces.CargomanagementService#deleteCargoInfo(java.lang.Integer)
	 */
	@Override
	@Transactional
	public boolean deleteCargoInfo(Integer id) {
		//删除point表中的指标
		TbPointExample tbPointExample = new TbPointExample();
		Criteria criteria = tbPointExample.createCriteria();
		criteria.andCargoIdEqualTo(id);
		List<TbPoint> tbPointList = tbPointMapper.selectByExample(tbPointExample);
		int pointRow = tbPointMapper.deletePointByCargoId(id);
		if(tbPointList.size() != pointRow){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		//删除规格表中的关联规格
		TbSpecificationsExample tbSpecificationsExample = new TbSpecificationsExample();
		tbSpecificationsExample.createCriteria().andCargoIdEqualTo(id);
		List<TbSpecifications> specificateList = tbSpecificationsMapper.selectByExample(tbSpecificationsExample);
		int specRow = tbSpecificationsMapper.deleteSpecByCargoId(id);
		if(specificateList.size() != specRow){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		//删除货物表
		int cargoRow = tbCargoMapper.deleteByPrimaryKey(id);
		if(cargoRow != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public List<TbSpecifications> selectSpecListBycargoId(Integer id) {
		TbSpecificationsExample tbSpecificationsExample = new TbSpecificationsExample();
		tbSpecificationsExample.createCriteria().andCargoIdEqualTo(id);
		List<TbSpecifications> specificateList = tbSpecificationsMapper.selectByExample(tbSpecificationsExample);
		return specificateList;
	}

	@Override
	public TbCargoMainPoint selectMainPointDetailById(Integer cargoId) {
		return tbCargoMapper.selectMainPointDetailById(cargoId);
	}

	@Override
	public List<CargoPointDetail> selectPointDetailByCargoId(Integer cargoId) {
		return tbPointMapper.selectPointDeatilByCargoId(cargoId);
	}

	@Override
	public List<CargoSpecificteDetail> selectSpecificDetailByCargoId(Integer cargoId) {
		return tbSpecificationsMapper.selectSpecificDetailByCargoId(cargoId);
	}

	@Override
	@Transactional
	public int addCargo(TbCargo cargo) {
		int r = tbCargoMapper.insertCargo(cargo);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(cargo.getId().toString());
		branchGroupLink.setTabName("tb_cargo");
		branchGroupLink.setTabComment("货物信息");
		branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		
		return r;
	}

	@Override
	@Transactional
	public int addPoint(TbPoint point) {
		int r = tbPointMapper.insertSelective(point);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	@Transactional
	public int addSpecs(TbSpecifications specs) {
		int r = tbSpecificationsMapper.insertSelective(specs);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	public List<CargoPointDetail> queryPointDetailByCargoId(Integer cargoId) {
		return tbPointMapper.queryPointDetailByCargoId(cargoId);
	}

	@Override
	@Transactional
	public int updCargo(TbCargo cargo) {
		int r = tbCargoMapper.updateByPrimaryKeySelective(cargo);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	@Transactional
	public int delPointDetail(Integer cargoId) {
		TbPointExample tbPointExample = new TbPointExample();
		Criteria criteria = tbPointExample.createCriteria();
		criteria.andCargoIdEqualTo(cargoId);
		List<TbPoint> tbPointList = tbPointMapper.selectByExample(tbPointExample);
		int pointRow = tbPointMapper.deletePointByCargoId(cargoId);
		if(tbPointList.size() != pointRow){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return pointRow;
		}
		return pointRow;
	}

	@Override
	@Transactional
	public int delSpeciDetail(Integer cargoId) {
		TbSpecificationsExample tbSpecificationsExample = new TbSpecificationsExample();
		tbSpecificationsExample.createCriteria().andCargoIdEqualTo(cargoId);
		List<TbSpecifications> specificateList = tbSpecificationsMapper.selectByExample(tbSpecificationsExample);
		int specRow = tbSpecificationsMapper.deleteSpecByCargoId(cargoId);
		if(specificateList.size() != specRow){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return specRow;
		}
		return specRow;
	}

	@Override
	public List<Integer> selectProjectIdByCargoId(Integer id) {
		return tbCargoMapper.selectProjectIdByCargoId(id);
	}

	/**
	 * @description 获取所有的获取信息
	 * @date 2017年12月19日
	 * @author shilvfei
	 * @return
	 */
	@Override
	public List<TbCargoMainPoint> listCargo() {
		TbCargoExample example=new TbCargoExample();
		com.shenhesoft.logistics.manage.pojo.cargo.TbCargoExample.Criteria criteria = example.createCriteria();
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
        criteria.andTabNameEqualTo("tb_cargo");
        criteria.andCargoNameIsNotNull();
		return tbCargoMapper.selectCargoList(example);
	}
	
	/**
	 * @description 货物信息的查询条件
	 * @date 2018年1月13日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private void cargoCriteria(TbCargo cargo,
			com.shenhesoft.logistics.manage.pojo.cargo.TbCargoExample.Criteria criteria) {
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
        criteria.andTabNameEqualTo("tb_cargo");
        criteria.andCargoNameIsNotNull();
		
		if(cargo==null){
			return;
		}
		
		if(StringUtils.isNotBlank(cargo.getCargoName())){//货物名称
			criteria.andCargoNameLike(cargo.getCargoName());
		}
		if(StringUtils.isNotBlank(cargo.getCargoCode())){//货物编号
			criteria.andCargoCodeLike(cargo.getCargoCode());
		}
	}

}
