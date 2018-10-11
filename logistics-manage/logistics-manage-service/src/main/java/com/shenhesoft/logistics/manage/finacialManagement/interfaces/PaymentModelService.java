// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.finacialManagement.interfaces;

import java.util.List;

import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.manage.helpPojo.PaymentDetail;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月11日
 */
public interface PaymentModelService {

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月11日
	 * @param 
	 * @return
	*/
	DataGridResult paymentList(Integer page, Integer limit);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月11日
	 * @param 
	 * @return
	*/
	int addPayment(String name);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月11日
	 * @param 
	 * @return
	*/
	int delPaymentById(Integer id);

	/**
	 * 查询所有的支付模式
	 * @author dusd
	 * @date 2018年1月18日
	 * @return
	 */
	List<PaymentDetail> listAllPayment();

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月13日
	 * @param 
	 * @return
	*/
	/*List<Integer> selectShortBargeIdById(Integer id);*/

}
