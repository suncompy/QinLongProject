// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.finacialManagement.interfaces;

import java.util.List;

import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.helpPojo.AccountManagementDetail;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbAccountRecordDetail;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月13日
 */
public interface AccountmanagementService {

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月13日
	 * @param 
	 * @return
	*/
	DataGridResult listAccountByPage(Integer page, Integer limit,TbFinanceAccount financeAccount);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月13日
	 * @param 
	 * @return
	*/
	int addAccount(TbFinanceAccount account);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月13日
	 * @param 
	 * @return
	*/
	int delAccount(Integer id);

	/**
	 * @description 根据选择账户种类 查询对应名称
	 * @date 2018年2月4日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	LogisticsResult getAccountNameByType(byte type);

	TbFinanceAccount getAccountDetailById(Integer id);

	int updAccount(TbFinanceAccount account);

	LogisticsResult getTaxByTypeAndId(byte type, Integer id);

	LogisticsResult getAccountNameByTypeAndProjectId(byte type, Integer projectId);

	TbFinanceAccount lookAccountDetail(Integer id);

	List<TbAccountRecordDetail> selectAccountDetailById(Integer id);
	
}
