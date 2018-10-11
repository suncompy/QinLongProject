// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.abnormalManagement.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.abnormalManagement.AbnormalManagementService;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.SystemSceneDetail;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.mapper.TbSystemSceneMapper;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.systemScene.TbSystemScene;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月15日
 */
@Service
public class AbnormalManagementServiceImpl implements AbnormalManagementService{
	
	@Autowired
	private TbSystemSceneMapper systemSceneMapper;
	
	@Autowired
	private TbProjectMapper tbProjectMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.abnormalManagement.AbnormalManagementService#selectAbnormalList()
	 */
	@Override
	public DataGridResult selectAbnormalList(Integer page, Integer limit,TbSystemScene search) {
		PageHelper.startPage(page, limit);
		Map<String, Object> map = new HashMap<>();
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<SystemSceneDetail> list = systemSceneMapper.selectAbnormalList(map);
		PageInfo<SystemSceneDetail> pageInfo = new PageInfo<SystemSceneDetail>(list);
	    long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list, limit);
	}

	@Override
	@Transactional
	public int addAbnormal(TbSystemScene systemScence) {
		int r = systemSceneMapper.insertSelective(systemScence);
		if (r != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}

		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(systemScence.getId().toString());
		branchGroupLink.setTabName("tb_system_scene");
		branchGroupLink.setTabComment("系统情景设定");
		branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);

		return r;
	}

	@Override
	@Transactional
	public int deleteAbnormal(Integer id) {
		int r = systemSceneMapper.updateSceneById(id);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	public SystemSceneDetail selectAbnormalDeatilById(Integer id) {
		return systemSceneMapper.selectAbnormalDeatilById(id);
	}

	@Override
	@Transactional
	public int updAbnormal(TbSystemScene systemScence) {
		int r = systemSceneMapper.updateByPrimaryKeySelective(systemScence);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	public List<ProjectDetail> queryAllProjectOfSence() {
		/*Map<String, Object> map = new HashMap<String, Object>();
		//分支机构id
		List<Integer> branchIds = new ArrayList<>();
		for (TbBranchGroup tbBranchGroup : branchGroupList) {
			branchIds.add(tbBranchGroup.getId());
		}
		map.put("branchGroupIdList", branchIds);*/
		
		TbProjectExample projectExample = new TbProjectExample();
		Criteria criteria = projectExample.createCriteria();
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		criteria.andTabNameEqualTo("tb_project");
		
		return tbProjectMapper.selectProjectDetailByExample(projectExample);
	}

}
