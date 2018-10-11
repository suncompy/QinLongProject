// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.shenhesoft.logistics.manage.interfaces.TrainTypeService;
import com.shenhesoft.logistics.manage.mapper.TbTrainTypeMapper;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomerExample;
import com.shenhesoft.logistics.manage.pojo.trianType.TbTrainType;
import com.shenhesoft.logistics.manage.pojo.trianType.TbTrainTypeExample;

/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月7日
 */
@Service
@Transactional
public class TrainTypeServiceImpl implements TrainTypeService{
	
	@Autowired
	TbTrainTypeMapper TbTrainTypeDao;

	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainTypeService#selectTrainTypeByPage()
	 */
	@Override
	public List<TbTrainType> selectTrainTypeByPage(String sysOrgCode) {
		//return TbTrainTypeDao.selectTrainTypeByPage();
		Map<String, Object> map = new HashMap<>();
		map.put("sysOrgCode", sysOrgCode);
		return TbTrainTypeDao.selectTrainTypeList(map);
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainTypeService#insert(com.shenhesoft.logistics.manage.trianType.TbTrainType)
	 */
	@Override
	public boolean insertTrainType(TbTrainType tbTrainType) {
		
		int row = TbTrainTypeDao.insertSelective(tbTrainType);
		if(row != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(tbTrainType.getId().toString());
		branchGroupLink.setTabName("tb_train_type");
		branchGroupLink.setTabComment("火车车型");
		branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainTypeService#delete(java.util.List)
	 */
	@Override
	@Transactional
	public boolean delete(List<Integer> list) {
		
		int row = TbTrainTypeDao.deleteBatch(list);
		if(row != list.size()){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainTypeService#selectTrainTypeByPages(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DataGridResult selectTrainTypeByPages(Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		/*TbTrainTypeExample example = new TbTrainTypeExample();
		example.setOrderByClause("id desc");
        //分页处理，显示第一页的20条数据
        PageHelper.startPage(page, limit);
    	List<TbTrainType> list = TbTrainTypeDao.selectTrainTypeByExample(example);//查询*/
		PageHelper.startPage(page, limit);
    	List<TbTrainType> list = TbTrainTypeDao.selectTrainTypeList(map);
        // 取分页信息
        PageInfo<TbTrainType> pageInfo = new PageInfo<TbTrainType>(list);
        long total = pageInfo.getTotal(); //获取总记录数
        
		return new DataGridResult(total, list, limit);
	}

}
