package com.shenhesoft.logistics.manage.search;

import java.io.Serializable;

/**
 * @description 网点分支搜索条件
 * 
 * @author shilvfei
 * 
 * @date 2018年1月5日
 */
public class TbBranchGroupSearch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3697990874625252561L;

	private String branchGroupName;
	
	private String shortName;
	
	private String shortCode;
	
	private String ascriptionName;

	public String getBranchGroupName() {
		return branchGroupName;
	}

	public void setBranchGroupName(String branchGroupName) {
		this.branchGroupName = branchGroupName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getAscriptionName() {
		return ascriptionName;
	}

	public void setAscriptionName(String ascriptionName) {
		this.ascriptionName = ascriptionName;
	}

	
}
