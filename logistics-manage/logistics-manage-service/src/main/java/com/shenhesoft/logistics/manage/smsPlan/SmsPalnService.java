// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.smsPlan;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.manage.pojo.codeModel.TbSmsCodeModel;
import com.shenhesoft.logistics.manage.pojo.smsPlan.TbSmsPlan;
import com.shenhesoft.logistics.manage.pojo.tbSmsSend.TbSmsSend;

/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月15日
 */
public interface SmsPalnService {

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月15日
	 * @param 
	 * @return
	*/
	boolean insertPlan(TbSmsPlan tbSmsPlan);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月15日
	 * @param 
	 * @return
	*/
	TbSmsCodeModel selectContentByCode(String code);

	DataGridResult listCustomerAndBusnissByPage(Integer page, Integer limit,Map<String,Object> map);

	boolean sendMsg(List<String> ids, String content, String accessKeyId, String accessKeySecret, String signName, String tpl);

	DataGridResult queryCustomerAndBusnissMsgList(Integer page, Integer limit, TbSmsSend smsSend);

	boolean sendMsgOfTrain(List<String> ids, String content, String accessKeyId, String accessKeySecret, String signName, String tpl);

	List<TbTrainOrder> chechTrainBussinss(List<String> ids);

	List<TbOrder> chechTbOrderBussinss(List<String> ids);

	boolean sendMsgOfBulk(List<String> ids, String content, String accessKeyId, String accessKeySecret, String signName, String tpl);

}
