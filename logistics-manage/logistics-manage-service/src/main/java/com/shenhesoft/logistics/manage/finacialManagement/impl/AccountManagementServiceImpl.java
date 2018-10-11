// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.finacialManagement.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderSearch;
import com.shenhesoft.logistics.business.mapper.BasicDataMapper;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.finacialManagement.interfaces.AccountmanagementService;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbCustomerMapper;
import com.shenhesoft.logistics.manage.mapper.TbFinanceAccountMapper;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroupExample;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomerExample;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbAccountRecordDetail;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccountExample;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccountExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStationExample;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月13日
 */
@Service
public class AccountManagementServiceImpl implements AccountmanagementService{
	
	@Autowired
	private TbFinanceAccountMapper financeAccountMapper;
	
	@Autowired
	private TbBranchGroupMapper branchGroupMapper;
	
	@Autowired
	private TbProjectMapper projectMapper;
	
	@Autowired
	private TbTrainStationMapper trainStationMapper;
	
	@Autowired
	private TbCustomerMapper customerMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	@Autowired
	private BasicDataMapper basicDataMapper;
	
	@Override
	public DataGridResult listAccountByPage(Integer page, Integer limit,TbFinanceAccount account) {
		PageHelper.startPage(page, limit);
		TbFinanceAccountExample example = new TbFinanceAccountExample();
		example.setOrderByClause("A.id desc");
		Criteria criteria = example.createCriteria();
		accountCriteria(account, criteria);//条件查询
		//List<AccountManagementDetail> list = financeAccountMapper.showAccountList(example);
		List<TbFinanceAccount> list = financeAccountMapper.selectAccountList(example);
		for (TbFinanceAccount tbFinanceAccount : list) {
			String rName = "";
			if(tbFinanceAccount.getAccountKind() == 0) {
				rName = financeAccountMapper.selectCustomserNameById(tbFinanceAccount.getChooseAccountId());
				if(rName != "" && rName != null) {
					tbFinanceAccount.setRelationName(rName);
				}
			}else if(tbFinanceAccount.getAccountKind() == 1) {
				rName = financeAccountMapper.selectTrainStationNameById(tbFinanceAccount.getChooseAccountId());
				if(rName != "" && rName != null) {
					tbFinanceAccount.setRelationName(rName);
				}
			}else {
				Map<String, Object> requestMap = ImmutableMap.of("sysOrgCode", AppSession.getCurrentSysOrgCode());
				List<Map<String, Object>> selections = basicDataMapper.getOrgTop(requestMap);
				TbBranchGroup branch = FormUtil.populate(TbBranchGroup.class, selections.get(0), false);
				branch.setName(branch.getText());
				tbFinanceAccount.setRelationName(branch.getText());
			}
		}
		PageInfo<TbFinanceAccount> pageInfo = new PageInfo<TbFinanceAccount>(list);
		//PageInfo<AccountManagementDetail> pageInfo = new PageInfo<AccountManagementDetail>(list);
		
		return new DataGridResult(pageInfo.getTotal(), list, limit);
	}

	@Override
	@Transactional
	public int addAccount(TbFinanceAccount account) {
		int r = financeAccountMapper.insertSelective(account);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(account.getId().toString());
		branchGroupLink.setTabName("tb_finance_account");
		branchGroupLink.setTabComment("账户管理");
		branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		return r;
	}

	@Override
	@Transactional
	public int delAccount(Integer id) {
		int r = financeAccountMapper.deleteAccountByPrimaryKey(id);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}
	
	/**
	 * @description 账户管理的查询条件
	 * @date 2018年1月13日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private void accountCriteria(TbFinanceAccount account, Criteria criteria) {
		criteria.andStatusEqualTo((byte)0);
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
        criteria.andTabNameEqualTo("tb_finance_account");
		if(account==null){
			return;
		}
		if(StringUtils.isNotBlank(account.getName())){
			criteria.andNameLike(account.getName());
		}
		if(account.getAccountType()!=null){
			criteria.andAccountTypeEqualTo(account.getAccountType());
		}
		if(StringUtils.isNotBlank(account.getOpenBank())){
			criteria.andOpenBankLike(account.getOpenBank());
		}
	}

	@Override
	public LogisticsResult getAccountNameByType(byte type) {
		//0:客户 1:站点 2:公司
		if(type == 0) {
			//List<TbCustomer> customserList = financeAccountMapper.selectCustomserListByType();
			TbCustomerExample example   = new TbCustomerExample();
			com.shenhesoft.logistics.manage.pojo.customer.TbCustomerExample.Criteria criteria = example.createCriteria();
			criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
			criteria.andTabNameEqualTo("tb_customer");
			List<CustomerInfo> customserList = customerMapper.selectCustomerInfoByExample(example);
			return LogisticsResult.ok(customserList);
		}else if(type == 1) {
			TbTrainStationExample example = new TbTrainStationExample();
			com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStationExample.Criteria criteria = example.createCriteria();
			criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
			criteria.andTabNameEqualTo("tb_train_station");
			criteria.andStationLevelEqualTo((byte)1);
			List<TbTrainStation> trainStationList = trainStationMapper.selectTrainStationByPage(example);
			return LogisticsResult.ok(trainStationList);
		}else if(type == 2){
			/*TbBranchGroupExample example= new TbBranchGroupExample();
			com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroupExample.Criteria createCriteria = example.createCriteria();
			createCriteria.andStatusEqualTo(Constants.DOT_BRANCH_STATUS_YES);
			createCriteria.andLevelEqualTo(Constants.BRANCH_LEVEL_ONE);
			List<TbBranchGroup> list = branchGroupMapper.selectByExample(example);
			if(list!=null && list.size() != 0){
				return LogisticsResult.ok(list.get(0));
			}*/
		    Map<String, Object> requestMap = ImmutableMap.of("sysOrgCode", AppSession.getCurrentSysOrgCode());
            List<Map<String, Object>> selections = basicDataMapper.getOrgTop(requestMap);
			TbBranchGroup branch = FormUtil.populate(TbBranchGroup.class, selections.get(0), false);
			branch.setName(branch.getText());
			List<TbBranchGroup> list = Lists.newArrayList();
			list.add(branch);
			return LogisticsResult.ok(list.get(0));
		}
		return LogisticsResult.ok();
	}

	@Override
	public TbFinanceAccount getAccountDetailById(Integer id) {
		/*TbFinanceAccountExample example = new TbFinanceAccountExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		criteria.andStatusEqualTo((byte)0);*/
		TbFinanceAccount account = financeAccountMapper.selectByPrimaryKey(id);
		return account;
	}

	@Override
	@Transactional
	public int updAccount(TbFinanceAccount account) {
		int r = financeAccountMapper.updateByPrimaryKeySelective(account);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	public LogisticsResult getTaxByTypeAndId(byte type, Integer id) {
		//0:客户 1:站点 2:公司
		if(type == 0) {
			TbCustomer customser = financeAccountMapper.selectCustomserByTypeAndId(id);
			
			return LogisticsResult.ok(customser);
		}else if(type == 1) {
			TbTrainStation trainStation = financeAccountMapper.selectTrainStationByTypeAndId(id);
			return LogisticsResult.ok(trainStation);
		}else {
			TbBranchGroup branchGroup = financeAccountMapper.selectBranchGroupByTypeAndId(id);
			return LogisticsResult.ok(branchGroup);
		}
	}

	@Override
	public LogisticsResult getAccountNameByTypeAndProjectId(byte type, Integer projectId) {
		List<TbCustomer> customserList  = Lists.newArrayList();
		List<TbCustomer> noRepeitCustomserList  = Lists.newArrayList();
		List<TbTrainStation> trainStationList  = Lists.newArrayList();
		List<TbTrainStation> noRepeitTrainStationList  = Lists.newArrayList();
		TbProject tb =  projectMapper.selectByPrimaryKey(projectId);
		//根据项目的联运模式来获取客户或者站点
		//0:客户 1:站点 2:公司
		if(type == 0) {
			TbCustomer c1 = null;
			TbCustomer c2 = null;
			TbCustomer c3 = null;
			TbCustomer c4 = null;
			if(tb.getTransportType() == 0 || tb.getTransportType() == 6 || tb.getTransportType() == 7) {
				/*c1.setId(tb.getSendCargoUnitId());
				c1.setCompanyName(tb.getSendCargoUnitName());
				customserList.add(c1);
				c2.setId(tb.getReceivingDepartmentId());
				c2.setCompanyName(tb.getReceivingDepartmentName());*/
				
				// 发货单位和收货单位 发货企业和收货企业 都取，可能存在不一样的。如果是一样的，去重
				c1 = customerMapper.selectByPrimaryKey(tb.getSendCargoUnitId());
				customserList.add(c1);
				c2 = customerMapper.selectByPrimaryKey(tb.getReceivingDepartmentId());
				customserList.add(c2);
				//收发货企业
				c3 = customerMapper.selectByPrimaryKey(tb.getSendCargoCompanyId());
				customserList.add(c3);
				c4 = customerMapper.selectByPrimaryKey(tb.getReceiveCargoCompanyId());
				customserList.add(c4);
			}
			if(tb.getTransportType() == 1 || tb.getTransportType() == 4) {
				//发货单位
				c1 = customerMapper.selectByPrimaryKey(tb.getSendCargoUnitId());
				customserList.add(c1);
				//收发货企业
				c3 = customerMapper.selectByPrimaryKey(tb.getSendCargoCompanyId());
				customserList.add(c3);
				c4 = customerMapper.selectByPrimaryKey(tb.getReceiveCargoCompanyId());
				customserList.add(c4);
			}
			if(tb.getTransportType() == 2 || tb.getTransportType() == 5) {
				//收货单位
				c2 = customerMapper.selectByPrimaryKey(tb.getReceivingDepartmentId());
				customserList.add(c2);
				//收发货企业
				c3 = customerMapper.selectByPrimaryKey(tb.getSendCargoCompanyId());
				customserList.add(c3);
				c4 = customerMapper.selectByPrimaryKey(tb.getReceiveCargoCompanyId());
				customserList.add(c4);
			}
			//去空值
			customserList.removeAll(Collections.singleton(null)); 
			//去重
			for(int i=0;i<customserList.size();i++){  
	            if(!noRepeitCustomserList.contains(customserList.get(i))){  
	            	noRepeitCustomserList.add(customserList.get(i));  
	            }  
	        }  
			return LogisticsResult.ok(noRepeitCustomserList);
		}else if(type == 1) {
			TbTrainStation c3 = null;
			if(tb.getTransportType() == 3 || tb.getTransportType() == 4 || tb.getTransportType() == 5 || tb.getTransportType() == 6) {
				/*c3.setId(tb.getBeginCenterSiteId());
				c3.setStationName(tb.getBeginCenterSiteName());*/
				c3 = trainStationMapper.selectByPrimaryKey(tb.getBeginCenterSiteId());
				trainStationList.add(c3);
			}
			if(tb.getTransportType() == 1) {
				c3 = trainStationMapper.selectByPrimaryKey(tb.getReceiveCenterCargoSiteId());
				trainStationList.add(c3);
			}
			if(tb.getTransportType() == 2) {
				c3 = trainStationMapper.selectByPrimaryKey(tb.getForwardingCenterSiteId());
				trainStationList.add(c3);
			}
			trainStationList.removeAll(Collections.singleton(null)); 
			for(int i=0;i<trainStationList.size();i++){  
	            if(!noRepeitTrainStationList.contains(trainStationList.get(i))){  
	            	noRepeitTrainStationList.add(trainStationList.get(i));  
	            }  
	        }  
			return LogisticsResult.ok(noRepeitTrainStationList);
		}
		return LogisticsResult.ok();
	}

	@Override
	public TbFinanceAccount lookAccountDetail(Integer id) {
		TbFinanceAccount account = financeAccountMapper.selectByPrimaryKey(id);
		String rName = "";
		if(account.getAccountKind() == 0) {
			rName = financeAccountMapper.selectCustomserNameById(account.getChooseAccountId());
			if(rName != "" && rName != null) {
				account.setRelationName(rName);
			}
		}else if(account.getAccountKind() == 1) {
			rName = financeAccountMapper.selectTrainStationNameById(account.getChooseAccountId());
			if(rName != "" && rName != null) {
				account.setRelationName(rName);
			}
		}else {
			Map<String, Object> requestMap = ImmutableMap.of("sysOrgCode", AppSession.getCurrentSysOrgCode());
			List<Map<String, Object>> selections = basicDataMapper.getOrgTop(requestMap);
			TbBranchGroup branch = FormUtil.populate(TbBranchGroup.class, selections.get(0), false);
			branch.setName(branch.getText());
			account.setRelationName(branch.getText());
		}
		return account;
	}

	@Override
	public List<TbAccountRecordDetail> selectAccountDetailById(Integer id) {
		List<TbAccountRecordDetail> list = financeAccountMapper.selectAccountDetailById(id);
		return list;
	}

}
