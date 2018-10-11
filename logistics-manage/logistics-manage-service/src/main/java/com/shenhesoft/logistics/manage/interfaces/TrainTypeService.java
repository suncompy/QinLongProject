// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.interfaces;

import java.util.List;

import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.manage.pojo.trianType.TbTrainType;

/**
 * 火车车型 serice 接口
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月7日
 */
public interface TrainTypeService {

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月7日
	 * @param 
	 * @return
	*/
	boolean insertTrainType(TbTrainType tbTrainType);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月7日
	 * @param 
	 * @return
	*/
	boolean delete(List<Integer> idList);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	DataGridResult selectTrainTypeByPages(Integer page, Integer limits);

	List<TbTrainType> selectTrainTypeByPage(String sysOrgCode);

}
