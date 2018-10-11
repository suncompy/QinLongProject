// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.smsPlan;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderMapper;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.SendMsgUtils;
import com.shenhesoft.logistics.manage.helpPojo.SmsCustomerHelp;
import com.shenhesoft.logistics.manage.mapper.TbSmsCodeModelMapper;
import com.shenhesoft.logistics.manage.mapper.TbSmsPlanMapper;
import com.shenhesoft.logistics.manage.mapper.TbSmsSendMapper;
import com.shenhesoft.logistics.manage.pojo.codeModel.TbSmsCodeModel;
import com.shenhesoft.logistics.manage.pojo.smsPlan.TbSmsPlan;
import com.shenhesoft.logistics.manage.pojo.stationBusiness.TbStationBusiness;
import com.shenhesoft.logistics.manage.pojo.tbSmsSend.TbSmsSend;

/**
 * @description 
 *
 * @author LiangLin
 *
 * @date 2017年12月15日
 */
@Service
public class SmsPlanServiceImpl implements SmsPalnService{
	
	@Autowired
	TbSmsPlanMapper tbSmsPlanMapper;
	
	@Autowired
	TbSmsCodeModelMapper tbSmsCodeModelMapper;

	@Autowired
	private TbSmsSendMapper tbSmsSendMapper;
	
	@Autowired
	private TbTrainOrderMapper tbTrainOrderMapper;
	
	@Autowired
	private TbOrderMapper tbOrderMapper;
	
	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.smsPlan.SmsPalnService#insertPlan(com.shenhesoft.logistics.manage.pojo.smsPlan.TbSmsPlan)
	 */
	@Override
	@Transactional
	public boolean insertPlan(TbSmsPlan tbSmsPlan) {
		int row = tbSmsPlanMapper.insertSelective(tbSmsPlan);
		if(row != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.smsPlan.SmsPalnService#selectContentByCode(java.lang.String)
	 */
	@Override
	public TbSmsCodeModel selectContentByCode(String code) {
		return tbSmsCodeModelMapper.selectContentByCode(code);
	}

	@Override
	public DataGridResult listCustomerAndBusnissByPage(Integer page, Integer limit,Map<String,Object> map) {
		/*Map<String,Object> map = new HashMap<String,Object>();
		 List<String> customerId = Lists.newArrayList();
		 customerId.add("tb_customer-13");
		 customerId.add("tb_station_business-10");
		map.put("sch", sch);
		map.put("customerId", customerId);*/
		PageHelper.startPage(page, limit);
		//List<SmsCustomerHelp> list = tbSmsCodeModelMapper.listCustomerAndBusnissByPage(map);
		List<SmsCustomerHelp> list = tbSmsCodeModelMapper.listCustomerAndBusnissByPage(map);
		PageInfo<SmsCustomerHelp> pageInfo = new PageInfo<SmsCustomerHelp>(list);
	    long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list, limit);
	}

	@Override
	public DataGridResult queryCustomerAndBusnissMsgList(Integer page, Integer limit, TbSmsSend smsSend) {
		PageHelper.startPage(page, limit);
		List<TbSmsSend> list = tbSmsCodeModelMapper.queryCustomerAndBusnissMsgList(smsSend);
		PageInfo<TbSmsSend> pageInfo = new PageInfo<TbSmsSend>(list);
	    long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list, limit);
	}
	
	@Override
	@Transactional
	public boolean sendMsg(List<String> ids, String content, String accessKeyId, String accessKeySecret, String signName, String tpl ) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("customerId", ids);
		List<SmsCustomerHelp> list = tbSmsCodeModelMapper.listCustomerAndBusniss(map);
		for (SmsCustomerHelp smsCustomerHelp : list) {
			String phone = smsCustomerHelp.getPhone();
			Map<String,Object> msgMap = new LinkedHashMap<String,Object>();
			msgMap.put("content", content);
		    boolean b =SendMsgUtils.sendMsgOK(accessKeyId, accessKeySecret, phone, signName, tpl, msgMap);
		    TbSmsSend smsSend = new TbSmsSend();
		    smsSend.setMsg(content);
		    smsSend.setMsgSignName(signName);
		    smsSend.setSendTime(new Date());
		    smsSend.setRecivePhone(smsCustomerHelp.getPhone());
		    smsSend.setOptUserName(AppSession.getCurrentUser().getName());
		    smsSend.setReciveUserName(smsCustomerHelp.getName());
		    if(b) {
		    	smsSend.setMsgStatus(0);
		    }else {
		    	smsSend.setMsgStatus(1);
		    }
		    int r = tbSmsSendMapper.insertSelective(smsSend);
		    if(r != 1) {
		    	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		    	return false;
		    }
		}
		return true;
	}

	@Override
	@Transactional
	public boolean sendMsgOfTrain(List<String> ids, String content, String accessKeyId, String accessKeySecret, String signName, String tpl) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("trainOrderId", ids);
		List<TbStationBusiness> list = tbSmsCodeModelMapper.listStationBusniss(map);
		for (TbStationBusiness tbStationBusiness : list) {
			String phone = tbStationBusiness.getPhone();
			Map<String,Object> msgMap = new LinkedHashMap<String,Object>();
			msgMap.put("content", content);
		    boolean b =SendMsgUtils.sendMsgOK(accessKeyId, accessKeySecret, phone, signName, tpl, msgMap);
		    TbSmsSend smsSend = new TbSmsSend();
		    smsSend.setMsg(content);
		    smsSend.setMsgSignName(signName);
		    smsSend.setSendTime(new Date());
		    smsSend.setRecivePhone(tbStationBusiness.getPhone());
		    smsSend.setOptUserName(AppSession.getCurrentUser().getName());
		    smsSend.setReciveUserName(tbStationBusiness.getName());
		    if(b) {
		    	smsSend.setMsgStatus(0);
		    }else {
		    	smsSend.setMsgStatus(1);
		    }
		    int r = tbSmsSendMapper.insertSelective(smsSend);
		    if(r != 1) {
		    	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		    	return false;
		    }
		}
		return true;
	}

	@Override
	public List<TbTrainOrder> chechTrainBussinss(List<String> ids) {
		List<TbTrainOrder> list = new ArrayList<TbTrainOrder>();
		for (String trainId : ids) {
			Integer id = Integer.valueOf(trainId);
			List<TbStationBusiness> tbusiness = tbSmsCodeModelMapper.selectTbStationBusinessById(id);
			if(tbusiness.size() == 0) {
				TbTrainOrder trainOrder = tbTrainOrderMapper.selectByPrimaryKey(id);
				list.add(trainOrder);
			}
		}
		return list;
	}

	@Override
	public List<TbOrder> chechTbOrderBussinss(List<String> ids) {
		List<TbOrder> list = new ArrayList<TbOrder>();
		for (String orderId : ids) {
			Integer id = Integer.valueOf(orderId);
			TbOrder  tbOrder = tbOrderMapper.selectByPrimaryKey(id);
			Byte stepType = tbOrder.getStepSelectCode();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("stepType", stepType);
			map.put("id", id);
			List<TbStationBusiness> tbusiness = tbSmsCodeModelMapper.selectBulkBusinessByMap(map);
			if (tbusiness.size() == 0) {
				list.add(tbOrder);
			}
		}
		return list;
	}

	@Override
	@Transactional
	public boolean sendMsgOfBulk(List<String> ids, String content, String accessKeyId, String accessKeySecret, String signName, String tpl) {
		//查询出所选短驳运单的阶段
		Map<String,Object> mapStep = new HashMap<String,Object>();
		mapStep.put("ids", ids);
		List<Byte> orderStep = tbSmsCodeModelMapper.selectBulkStepCodeByIds(mapStep);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId", ids);
		map.put("orderStep", orderStep);
		List<TbStationBusiness> list = tbSmsCodeModelMapper.listBulkStationBusniss(map);
		for (TbStationBusiness tbStationBusiness : list) {
			String phone = tbStationBusiness.getPhone();
			Map<String,Object> msgMap = new LinkedHashMap<String,Object>();
			msgMap.put("content", content);
		    boolean b =SendMsgUtils.sendMsgOK(accessKeyId, accessKeySecret, phone, signName, tpl, msgMap);
		    TbSmsSend smsSend = new TbSmsSend();
		    smsSend.setMsg(content);
		    smsSend.setMsgSignName(signName);
		    smsSend.setSendTime(new Date());
		    smsSend.setRecivePhone(tbStationBusiness.getPhone());
		    smsSend.setOptUserName(AppSession.getCurrentUser().getName());
		    smsSend.setReciveUserName(tbStationBusiness.getName());
		    if(b) {
		    	smsSend.setMsgStatus(0);
		    }else {
		    	smsSend.setMsgStatus(1);
		    }
		    int r = tbSmsSendMapper.insertSelective(smsSend);
		    if(r != 1) {
		    	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		    	return false;
		    }
		}
		return true;
	}


}
