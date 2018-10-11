// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.inventory.trainAccessStorage;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.business.helpPojo.TrainOrderDetail;
import com.shenhesoft.logistics.common.util.DataGridResult;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2018年1月3日
 */
public interface TrainAccessStorageService {

	/**
	 * @description 列表展示
	 * @author LiangDeng
	 * @param trainOrderDetail 
	 * @date 2018年1月3日
	 * @param 
	 * @return
	*/
	DataGridResult selectTrainAccessStorageList(Integer page, Integer limit, TrainOrderDetail trainOrderDetail);

	/**
	 * @description 查询统计
	 * @author LiangDeng
	 * @date 2018年1月4日
	 * @param 
	 * @return
	*/
	Map<String, Object> queryStatistic(Integer projectId);

}
