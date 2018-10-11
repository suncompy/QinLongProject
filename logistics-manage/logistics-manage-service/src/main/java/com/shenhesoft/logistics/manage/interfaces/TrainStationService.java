// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.interfaces;

import java.util.List;

import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.manage.helpPojo.TbTrainStationHelp;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;

/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月11日
 */
public interface TrainStationService {

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月11日
	 * @param 
	 * @return
	*/
	List<TbTrainStation> selectTrainStationByPage();

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	boolean insertTbTrainStation(TbTrainStationHelp tbStion);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	TbTrainStationHelp selectTrainStationByIs(Integer id);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	boolean updateTbTrainStationById(TbTrainStationHelp tbStion);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	boolean delete(List<Integer> idList);

	/**
	 * @description 根据站点id和货场货位的关联关系,判断是否可以删除
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	boolean selectIsFreightYardDeleteById(List<Integer> idList);

	/**
	 * @description 根据站点id和集装箱的关联关系,判断是否可以删除
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	boolean selectIsContainerDeleteById(List<Integer> idList);

	/**
	 * @description 根据站点id和业务联系人的关联关系,判断是否可以删除
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	boolean selectIsStationBusinessDeleteById(List<Integer> idList);

	/**
	 * @description 分页展示
	 * @author liangLin
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	DataGridResult selectTrainStationByPages(Integer page, Integer limit,TbTrainStation trainStation);

	/**
	 * @description 通过级别获取
	 * @date 2017年12月20日
	 * @author shilvfei
	 * @param siteLevelThree
	 * @return
	 */
	List<TbTrainStation> selectThreeTrainStationByLevel(byte siteLevelThree);

	/**
	 * @description 新增站点页面的   -根据当前所选站点级别，查询父级站点 
	 * @author liangLin
	 * @date 2017年12月25日
	 * @param 
	 * @return
	*/
	List<TbTrainStation> getParentsById(Integer id);

	
	/**
	 * 
	 * @description  -根据当前所选站点级别，查询子级站点
	 * @author liangLin
	 * @date 2017年12月25日
	 * @param 
	 * @return
	 */
	List<TbTrainStation> getChildrenStationById(Integer id);

	/**
	 * @description 根据站点id查询是否有子站点关联关系,判断是否可以删除
	 * @author liangdeng
	 * @date 2018年4月8日
	 * @param 
	 * @return
	*/
	boolean selectChildrenStationById(List<Integer> idList);

	/**
	 * @description 根据站点id查询是否关联项目,判断是否可以删除
	 * @author liangdeng
	 * @date 2018年4月8日
	 * @param 
	 * @return
	*/
	boolean selectProjectByStationId(List<Integer> idList);

	
	/**
	 * @description 根据区域找到站点
	 * @date 2018年4月16日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<TbTrainStation> listTrainStationByArea(List<String> areas);

}
