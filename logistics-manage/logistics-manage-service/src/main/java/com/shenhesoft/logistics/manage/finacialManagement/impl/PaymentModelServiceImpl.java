// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.finacialManagement.impl;

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
import com.shenhesoft.logistics.manage.finacialManagement.interfaces.PaymentModelService;
import com.shenhesoft.logistics.manage.helpPojo.PaymentDetail;
import com.shenhesoft.logistics.manage.helpPojo.SystemSceneDetail;
import com.shenhesoft.logistics.manage.mapper.TbPaymentMapper;
import com.shenhesoft.logistics.manage.pojo.payment.TbPayment;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月11日
 */
@Service
public class PaymentModelServiceImpl implements PaymentModelService{
	
	@Autowired
	private TbPaymentMapper paymentMapper;

	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	@Override
	public DataGridResult paymentList(Integer page, Integer limit) {
		PageHelper.startPage(page, limit);
		Map<String, Object> map=new HashMap<>();
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<PaymentDetail> list = paymentMapper.selectPaymentList(map);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setSerial(page*limit-(limit-1)+i);
		}
		PageInfo<PaymentDetail> pageInfo = new PageInfo<PaymentDetail>(list);
	    long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list, limit);
	}

	@Override
	@Transactional
	public int addPayment(String name) {
		TbPayment payment = new TbPayment();
		payment.setName(name);
		int r = paymentMapper.insertSelective(payment);

		if (r != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}

		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(payment.getId().toString());
		branchGroupLink.setTabName("tb_payment");
		branchGroupLink.setTabComment("支付方式");
		branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);

		return r;
	}

	@Override
	@Transactional
	public int delPaymentById(Integer id) {
		int r = paymentMapper.deleteByPrimaryKey(id);
		if(r != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return r;
		}
		return r;
	}

	@Override
	public List<PaymentDetail> listAllPayment() {
		Map<String, Object> map=new HashMap<>();
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<PaymentDetail> list = paymentMapper.selectPaymentList(map);
		return list;
	}

	/*@Override
	public List<Integer> selectShortBargeIdById(Integer id) {
		return paymentMapper.selectShortBargeIdById(id);
	}*/

}
