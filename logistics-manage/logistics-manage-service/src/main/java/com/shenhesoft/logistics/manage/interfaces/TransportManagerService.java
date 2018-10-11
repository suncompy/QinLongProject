// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.interfaces;

import java.util.List;

import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.manage.helpPojo.CarTeamDetail;
import com.shenhesoft.logistics.manage.helpPojo.TbBoxDetail;
import com.shenhesoft.logistics.manage.pojo.box.TbContainer;
import com.shenhesoft.logistics.manage.pojo.box.TbContainerType;
import com.shenhesoft.logistics.manage.search.VehicleSearch;




/**
 * @description 运输管理
 *
 * @author LiangLin
 *
 * @date 2017年12月5日
 */

public interface TransportManagerService {

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月5日
	 * @param 
	 * @return
	*/
	List<CarTeamDetail> selectAnchoredCarTeam(Integer companyId);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月5日
	 * @param 
	 * @return
	*/
	List<CarTeamDetail> selectAnchoredDrivers(Integer companyId);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月5日
	 * @param 记录表ids
	 * @return
	*/
	boolean insertCancelRecord(List<Integer> ids);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	boolean updateAnchordRelate(List<Integer> ids);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	boolean updateAnchordRelateStop(List<Integer> ids);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	List<CarTeamDetail> selectRejectCarTeamRecord(Integer companyId);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	List<CarTeamDetail> selectRejectDriverRecord(Integer companyId);


	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	boolean insertAnchoredRelate(List<Integer> ids);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	boolean insertAgreeRecord(List<Integer> ids,Integer userId);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	boolean insertRejectRecord(List<Integer> ids, Integer id);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	boolean insertNotice(List<Integer> ids, Integer companyId,byte type);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月7日
	 * @param 
	 * @return
	*/
	boolean deleteRejectRecord(List<Integer> idList);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月8日
	 * @param 
	 * @return
	*/
	List<TbBoxDetail> selectBoxByPage();

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月8日
	 * @param 
	 * @return
	*/
	boolean insertContainType(TbContainerType tbType);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月8日
	 * @param 
	 * @return
	*/
	java.util.List<TbContainerType> selectBoxTypeByPage();

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月8日
	 * @param 
	 * @return
	*/
	boolean insertTbContainer(TbContainer tcr);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月8日
	 * @param 
	 * @return
	*/
	boolean deleteBox(java.util.List<Integer> idList);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月9日
	 * @param 
	 * @return
	*/
	boolean deleteBoxType(java.util.List<Integer> idList);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月14日
	 * @param 
	 * @return
	*/
	boolean selectIsRelateBoxDeleteById(java.util.List<Integer> idList);

	/**
	 * @description 集装箱 分页查询
	 * @author liangLin
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	DataGridResult selectBoxByPages(Integer page, Integer limit,TbBoxDetail boxDetail);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	DataGridResult selectAnchoredCarTeamByPages(Integer pAGE_NUM, Integer cUSTOMER_PAGE_LIMIT,VehicleSearch vehicleSearch);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	DataGridResult selectAnchoredDriversByPages(Integer pAGE_NUM, Integer cUSTOMER_PAGE_LIMIT, VehicleSearch vehicleSearch);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	*/
	DataGridResult selectRejectCarTeamRecordByPages(Integer pAGE_NUM, Integer cUSTOMER_PAGE_LIMIT, VehicleSearch vehicleSearch);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	*/
	DataGridResult selectRejectDriverRecordByPages(Integer pAGE_NUM, Integer cUSTOMER_PAGE_LIMIT, int i);

	/**
	 * @description 开始
	 * @author LiangDeng
	 * @date 2017年12月18日
	 * @param ids
	 * @return
	 */
	boolean updateAnchordRelateStrat(List<Integer> ids);

	/**
	 * @description 校验集装箱号是否存在
	 * @author LiangDeng
	 * @param id 
	 * @date 2018年3月12日
	 * @param ids
	 * @return
	 */
	List<TbContainer> checkContainerIsExist(String containerId, Integer id);

	/**
	 * @description 修改集装箱获得详情
	 * @author LiangDeng
	 * @date 2018年3月19日
	 * @param ids
	 * @return
	 */
	TbContainer getContainerDetail(Integer id);

	/**
	 * @description 修改集装箱
	 * @author LiangDeng
	 * @date 2018年3月19日
	 * @param ids
	 * @return
	 */
	boolean updateTbContainer(TbContainer tcr);

	TbContainer selectContainByConId(String containerNumber1, String sysOrgCode);

}

