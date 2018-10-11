// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.abnormalManagement;

import java.util.List;

import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.manage.helpPojo.SystemSceneDetail;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemScene.TbSystemScene;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月15日
 */
public interface AbnormalManagementService {

	/**
	 * @description 
	 * @author LiangDeng
	 * @param search 
	 * @param cUSTOMER_PAGE_LIMIT 
	 * @param pAGE_NUM 
	 * @date 2017年12月15日
	 * @param 
	 * @return
	*/
	DataGridResult selectAbnormalList(Integer page, Integer limit, TbSystemScene search);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	int addAbnormal(TbSystemScene systemScence);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	int deleteAbnormal(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	SystemSceneDetail selectAbnormalDeatilById(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	int updAbnormal(TbSystemScene systemScence);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2018年1月17日
	 * @param 
	 * @return
	*/
	List<ProjectDetail> queryAllProjectOfSence();

}
