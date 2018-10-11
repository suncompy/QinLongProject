// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.shenhesoft.logistics.manage.helpPojo.CarTeamDetail;
import com.shenhesoft.logistics.manage.helpPojo.TbBoxDetail;
import com.shenhesoft.logistics.manage.interfaces.TransportManagerService;
import com.shenhesoft.logistics.manage.mapper.TbAnchoredRecordMapper;
import com.shenhesoft.logistics.manage.mapper.TbContainerMapper;
import com.shenhesoft.logistics.manage.mapper.TbContainerTypeMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.pojo.anchord.TbAnchoreRecord;
import com.shenhesoft.logistics.manage.pojo.anchord.TbCarTeamExample;
import com.shenhesoft.logistics.manage.pojo.anchord.TbCarTeamExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.box.TbContainer;
import com.shenhesoft.logistics.manage.pojo.box.TbContainerExample;
import com.shenhesoft.logistics.manage.pojo.box.TbContainerType;
import com.shenhesoft.logistics.manage.pojo.notice.TbNotice;
import com.shenhesoft.logistics.manage.search.VehicleSearch;

/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月5日
 */
@Service
public class TransportManagerImpl implements TransportManagerService {

	@Autowired
	private TbAnchoredRecordMapper tbAnchoredRecordMapper;
	
	@Autowired
	private TbContainerMapper tc;
	
	@Autowired
	private TbContainerTypeMapper tyc;
	
	@Autowired
	private TbTrainStationMapper tsMapper;

	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#
	 * selectAnchoredCarTeam(java.lang.Integer)
	 */
	@Override
	public List<CarTeamDetail> selectAnchoredCarTeam(Integer companyId) {
		return tbAnchoredRecordMapper.selectAnchoredCarTeams(companyId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#
	 * selectAnchoredDrivers(java.lang.Integer)
	 */
	@Override
	public List<CarTeamDetail> selectAnchoredDrivers(Integer companyId) {
		return tbAnchoredRecordMapper.selectAnchoredDrivers(companyId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#
	 * insertCancelRecord(java.util.List)
	 */
	@Override
	@Transactional
	public boolean insertCancelRecord(List<Integer> ids) {
		for (Integer id : ids) {

			// 将之前申请记录 改成delete_flag = 1 .表示已经处理
			int row = tbAnchoredRecordMapper.updateRecordDelete(id);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}

			// 根据挂靠记录表 id 查关联关系
			TbAnchoreRecord tcrc = tbAnchoredRecordMapper.selectRecordById(id);
			tcrc.setId(null);
			tcrc.setStatus((byte) 0);
			tcrc.setAnchoredReason(null);
			row = tbAnchoredRecordMapper.insertSelective(tcrc);
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
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#
	 * updateAnchordRelate(java.util.List)
	 */
	@Override
	@Transactional
	public boolean updateAnchordRelate(List<Integer> ids) {

		for (Integer id : ids) {
			// 根据挂靠记录表 id 查关联关系
			TbAnchoreRecord tcrc = tbAnchoredRecordMapper.selectRecordById(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("anchoredId", tcrc.getAnchoredId());
			map.put("userId", tcrc.getUserId());
			int row = tbAnchoredRecordMapper.updateAnchordRelate(map);
			/*if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}*/
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#
	 * updateAnchordRelateStop(java.util.List)
	 */
	@Override
	@Transactional
	public boolean updateAnchordRelateStop(List<Integer> ids) {
		for (Integer id : ids) {
			// 将关联关系变成stop=1
			TbAnchoreRecord tcrc = tbAnchoredRecordMapper.selectRecordById(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("anchoredId", tcrc.getAnchoredId());
			map.put("userId", tcrc.getUserId());
			int row = tbAnchoredRecordMapper.updateAnchordRelateStop(map);
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
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#
	 * selectRejectCarTeamRecord(java.lang.Integer)
	 */
	@Override
	public List<CarTeamDetail> selectRejectCarTeamRecord(Integer companyId) {
		List<CarTeamDetail> list = tbAnchoredRecordMapper.selectRejectCarTeamRecord(companyId);
		for (CarTeamDetail it : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("anchoredId", it.getAnchoredId());
			map.put("userId", it.getUserId());
			map.put("status", 3);
			String ct = tbAnchoredRecordMapper.selectApplyRecordByMap(map);
			it.setApplydate(ct);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#
	 * selectRejectDriverRecord(java.lang.Integer)
	 */
	@Override
	public List<CarTeamDetail> selectRejectDriverRecord(Integer companyId) {
		List<CarTeamDetail> list = tbAnchoredRecordMapper.selectRejectDriverRecord(companyId);
		for (CarTeamDetail it : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("anchoredId", it.getAnchoredId());
			map.put("userId", it.getUserId());
			map.put("status", 3);
			String ct = tbAnchoredRecordMapper.selectApplyRecordByMap(map);
			it.setApplydate(ct);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#
	 * insertAnchoredRelate(java.util.List)
	 */
	@Override
	public boolean insertAnchoredRelate(List<Integer> ids) {
		for (Integer id : ids) {
			// 将关联关系变成is_stop=0  默认可以接单
			TbAnchoreRecord tcrc = tbAnchoredRecordMapper.selectRecordById(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", tcrc.getUserId());
			map.put("anchoredCompanyId", tcrc.getAnchoredId());
			if(tcrc.getType() == 2) {
				map.put("type", (byte)0);
			}else if(tcrc.getType() == 3) {
				map.put("type", (byte)1);
			}else {
				map.put("type", null);
			}
			int row = tbAnchoredRecordMapper.insertAnchoredRelate(map);
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
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#
	 * insertAgreeRecord(java.util.List)
	 */
	@Override
	@Transactional
	public boolean insertAgreeRecord(List<Integer> ids, Integer userId) {
		for (Integer id : ids) {
			// 将之前申请记录 改成delete_flag = 1 .表示已经处理
			int row = tbAnchoredRecordMapper.updateRecordDelete(id);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
			//判断申请的用户是车队还是个人
			
			
			// 根据挂靠记录表 id 查关联关系
			TbAnchoreRecord tcrc = tbAnchoredRecordMapper.selectRecordById(id);
			tcrc.setId(null);
			tcrc.setStatus((byte) 1);
			tcrc.setAnchoredReason(null);
			// 此操作的操作人
			tcrc.setOperator(userId);
			row = tbAnchoredRecordMapper.insertSelective(tcrc);
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
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#
	 * insertRejectRecord(java.util.List, java.lang.Integer)
	 */
	@Override
	@Transactional
	public boolean insertRejectRecord(List<Integer> ids, Integer userId) {

		for (Integer id : ids) {
			// 将之前申请记录 改成delete_flag = 1 .表示已经处理
			int row = tbAnchoredRecordMapper.updateRecordDelete(id);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}

			// 根据挂靠记录表 id 查关联关系
			TbAnchoreRecord tcrc = tbAnchoredRecordMapper.selectRecordById(id);
			tcrc.setId(null);
			tcrc.setStatus((byte) 2);
			tcrc.setAnchoredReason(null);
			// 此操作的操作人
			tcrc.setOperator(userId);
			row = tbAnchoredRecordMapper.insertSelective(tcrc);
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
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#
	 * insertRejectNotice(java.util.List, java.lang.Integer)
	 */
	@Override
	public boolean insertNotice(List<Integer> ids, Integer companyId, byte type) {
		for (Integer id : ids) {
			TbAnchoreRecord tcrc = tbAnchoredRecordMapper.selectRecordById(id);
			TbNotice tbn = new TbNotice();
			if (type == 1) {
				// agree
				tbn.setContent("您对" + tcrc.getAnchoredName() + "的申请挂靠操作,已被同意");
			} else if (type == 2) {
				// reject
				tbn.setContent("您对" + tcrc.getAnchoredName() + "的申请挂靠操作,已被驳回");
			}
			tbn.setUserId(tcrc.getUserId());
			int row = tbAnchoredRecordMapper.insertNewNotice(tbn);
			if (row != 1) {
				return false;
			}

		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#
	 * deleteRejectRecord(java.util.List)
	 */
	@Override
	@Transactional
	public boolean deleteRejectRecord(List<Integer> list) {
		int row = tbAnchoredRecordMapper.deleteBatch(list);
		if (row != list.size()) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public List<TbBoxDetail> selectBoxByPage() {
		return tc.selectBoxByPage();
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#insertContainType(com.shenhesoft.logistics.manage.pojo.box.TbContainerType)
	 */
	@Override
	@Transactional
	public boolean insertContainType(TbContainerType tbType) {
		int row = tyc.insertSelective(tbType);
		if(row != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(tbType.getId().toString());
		branchGroupLink.setTabName("tb_container_type");
		branchGroupLink.setTabComment("集装箱箱型");
		branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#selectBoxTypeByPage()
	 */
	@Override
	public List<TbContainerType> selectBoxTypeByPage() {
		Map<String, Object> map = new HashMap<>();
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		return tyc.selectBoxTypeByPage(map);
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#insertTbContainer(com.shenhesoft.logistics.manage.pojo.box.TbContainer)
	 */
	@Override
	@Transactional
	public boolean insertTbContainer(TbContainer tcr) {
		tcr.setStatus(0);
		int row = tc.insertSelective(tcr);
		if(row != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(tcr.getId().toString());
		branchGroupLink.setTabName("tb_container");
		branchGroupLink.setTabComment("集装箱管理");
		branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#deleteBox(java.util.List)
	 */
	@Override
	@Transactional
	public boolean deleteBox(List<Integer> list) {
		int row = tc.deleteBoxBatch(list);
		if (row != list.size()) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#deleteBoxType(java.util.List)
	 */
	@Override
	@Transactional
	public boolean deleteBoxType(List<Integer> list) {
		int row = tyc.deleteBoxTypeBatch(list);
		if (row != list.size()) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#selectIsRelateBoxDeleteById(java.util.List)
	 */
	@Override
	public boolean selectIsRelateBoxDeleteById(List<Integer> list) {
		int count = tyc.selectIsRelateBoxDeleteById(list);
		if (count != 0) {
			return false;
		}
		return true;
	}

	/**
	 * @description 集装箱 分页查询
	 * @author liangLin
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	@Override
	public DataGridResult selectBoxByPages(Integer page, Integer limit,TbBoxDetail boxDetail){	
		TbContainerExample example = new TbContainerExample();
		example.setOrderByClause("A.id desc");
		com.shenhesoft.logistics.manage.pojo.box.TbContainerExample.Criteria criteria = example.createCriteria();
		criteria.andIdIsNotNull();
		  //分页处理，显示第一页的20条数据
        PageHelper.startPage(page, limit);
        boxDetailCriteria(boxDetail, criteria);//条件查询
        List<TbBoxDetail> list = tc.selectBoxByExample(example);
        for (TbBoxDetail tbBoxDetail : list) {
			if(null != tbBoxDetail.getTrainLocationId()){
				String stationName = tsMapper.selectStationNameByParentId(tbBoxDetail.getTrainLocationId());
				tbBoxDetail.setStationName(stationName);
			}
			if(null != tbBoxDetail.getProjectId()){
				String projectCode = tc.selectProjectCodeByProjectId(tbBoxDetail.getProjectId());
				tbBoxDetail.setProjectCode(projectCode);
			}
		}
        PageInfo<TbBoxDetail> pageInfo = new PageInfo<TbBoxDetail>(list);
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list, limit);
	}

	/**
	 * @description 挂靠车队的 分页查询
	 * @author liangLin
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	@Override
	public DataGridResult selectAnchoredCarTeamByPages(Integer page, Integer limit,VehicleSearch vehicleSearch) {
		//TbCarTeamExample example = new TbCarTeamExample();
		//Criteria criteria = example.createCriteria();
		//criteria.andAnchoredIdEqualTo(companyId);
		  //分页处理，显示第一页的20条数据
        PageHelper.startPage(page,limit);
        List<CarTeamDetail>  list = tbAnchoredRecordMapper.selectAnchoredCarTeamsExample(vehicleSearch);
        PageInfo<CarTeamDetail> pageInfo = new PageInfo<CarTeamDetail>(list);
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list, limit);
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#selectAnchoredDriversByPages(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DataGridResult selectAnchoredDriversByPages(Integer page, Integer limit,VehicleSearch vehicleSearch) {
		//TbCarTeamExample example = new TbCarTeamExample();
		//Criteria criteria = example.createCriteria();
		//criteria.andAnchoredIdEqualTo(companyId);
		//分页处理，显示第一页的20条数据
        PageHelper.startPage(page, limit);
        List<CarTeamDetail>  list  = tbAnchoredRecordMapper.selectAnchoredDriversByExample(vehicleSearch);
        PageInfo<CarTeamDetail> pageInfo = new PageInfo<CarTeamDetail>(list);
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list, limit);
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#selectRejectCarTeamRecordByPages(java.lang.Integer, java.lang.Integer, int)
	 */
	@Override
	public DataGridResult selectRejectCarTeamRecordByPages(Integer page, Integer limit,VehicleSearch vehicleSearch) {
		//TbCarTeamExample example = new TbCarTeamExample();
		//Criteria criteria = example.createCriteria();
		//criteria.andAnchoredIdEqualTo(companyId);
		//分页处理，显示第一页的20条数据
        PageHelper.startPage(page, limit);
        
        List<CarTeamDetail> list = tbAnchoredRecordMapper.selectRejectCarTeamRecordByExample(vehicleSearch);
		for (CarTeamDetail it : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("anchoredId", it.getAnchoredId());
			map.put("userId", it.getUserId());
			map.put("status", 3);
			String ct = tbAnchoredRecordMapper.selectApplyRecordByMap(map);
			it.setApplydate(ct);
		}
        PageInfo<CarTeamDetail> pageInfo = new PageInfo<CarTeamDetail>(list);
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list, limit);
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TransportManagerService#selectRejectDriverRecordByPages(java.lang.Integer, java.lang.Integer, int)
	 */
	@Override
	public DataGridResult selectRejectDriverRecordByPages(Integer page, Integer limit, int companyId) {
		TbCarTeamExample example = new TbCarTeamExample();
		Criteria criteria = example.createCriteria();
		criteria.andAnchoredIdEqualTo(companyId);
		//分页处理，显示第一页的20条数据
        PageHelper.startPage(page, limit);
        
        List<CarTeamDetail> list = tbAnchoredRecordMapper.selectRejectDriverRecordByExample(example);
		for (CarTeamDetail it : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("anchoredId", it.getAnchoredId());
			map.put("userId", it.getUserId());
			map.put("status", 3);
			String ct = tbAnchoredRecordMapper.selectApplyRecordByMap(map);
			it.setApplydate(ct);
		}
		
        PageInfo<CarTeamDetail> pageInfo = new PageInfo<CarTeamDetail>(list);
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list, limit);
	}
	
	/**
	 * @description  集装箱的查询条件
	 * @date 2018年1月13日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private void boxDetailCriteria(TbBoxDetail boxDetail,
			com.shenhesoft.logistics.manage.pojo.box.TbContainerExample.Criteria criteria) {
		criteria.andIdIsNotNull();
		criteria.andTabNameEqualTo("tb_container");
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		
		if(boxDetail==null){
			return;
		}
		//集装箱箱型id
		if(boxDetail.getContainerTypeId()!=null){
			criteria.andContainerTypeIdEqualTo(boxDetail.getContainerTypeId());
		}
		//集装箱号
		if(StringUtils.isNotBlank(boxDetail.getContainerId())){
			criteria.andContainerIdLike(boxDetail.getContainerId());
		}
	}

	@Override
	public boolean updateAnchordRelateStrat(List<Integer> ids) {
		for (Integer id : ids) {
			// 将关联关系变成stop=0
			TbAnchoreRecord tcrc = tbAnchoredRecordMapper.selectRecordById(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("anchoredId", tcrc.getAnchoredId());
			map.put("userId", tcrc.getUserId());
			int row = tbAnchoredRecordMapper.updateAnchordRelateStart(map);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
		}
		return true;
	}

	@Override
	public List<TbContainer> checkContainerIsExist(String containerId,Integer id) {
		TbContainerExample example = new TbContainerExample();
		com.shenhesoft.logistics.manage.pojo.box.TbContainerExample.Criteria criteria = example.createCriteria();
		criteria.andContainerIdEqualTo(containerId);
		criteria.andIdIsNotNull();
		criteria.andTabNameEqualTo("tb_container");
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		if(id != null) {
			criteria.andIdNotEqualTo(id);
		}
		return tc.selectByExample(example);
	}

	@Override
	public TbContainer getContainerDetail(Integer id) {
		return tc.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public boolean updateTbContainer(TbContainer tcr) {
		int row = tc.updateByPrimaryKeySelective(tcr);
		if(row != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public TbContainer selectContainByConId(String containerNumber1,String sysOrgCode) {
		TbContainerExample example = new TbContainerExample();
		com.shenhesoft.logistics.manage.pojo.box.TbContainerExample.Criteria criteria = example.createCriteria();
		criteria.andContainerIdEqualTo(containerNumber1);
		criteria.andIdIsNotNull();
		criteria.andTabNameEqualTo("tb_container");
		criteria.andSysOrgCodeEqualTo(sysOrgCode);
		/*if(sysOrgCode.equals("")) {
			criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		}else {
			criteria.andSysOrgCodeEqualTo(sysOrgCode);
		}*/
		return tc.selectByExample(example).get(0);
	}

}
