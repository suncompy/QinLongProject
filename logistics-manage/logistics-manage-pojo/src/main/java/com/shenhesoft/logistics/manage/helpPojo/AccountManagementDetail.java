// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.helpPojo;

import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月13日
 */
public class AccountManagementDetail extends TbFinanceAccount{
	
	//分支机构名称
	private String branchGroupName;
	
	//分支机构地址
	private String branchGroupAddress;
	
	//账户开户时间转String
	private String openTime;
	
	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getBranchGroupName() {
		return branchGroupName;
	}

	public void setBranchGroupName(String branchGroupName) {
		this.branchGroupName = branchGroupName;
	}

	public String getBranchGroupAddress() {
		return branchGroupAddress;
	}

	public void setBranchGroupAddress(String branchGroupAddress) {
		this.branchGroupAddress = branchGroupAddress;
	}
	
}
