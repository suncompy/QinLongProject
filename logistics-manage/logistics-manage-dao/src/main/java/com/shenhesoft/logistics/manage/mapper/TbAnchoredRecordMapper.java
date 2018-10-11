// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.manage.helpPojo.CarTeamDetail;
import com.shenhesoft.logistics.manage.pojo.anchord.TbAnchoreRecord;
import com.shenhesoft.logistics.manage.pojo.anchord.TbCarTeamExample;
import com.shenhesoft.logistics.manage.pojo.box.TbContainer;
import com.shenhesoft.logistics.manage.pojo.box.TbContainerExample;
import com.shenhesoft.logistics.manage.pojo.notice.TbNotice;
import com.shenhesoft.logistics.manage.search.VehicleSearch;


/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月5日
 */
public interface TbAnchoredRecordMapper {

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月5日
	 * @param 
	 * @return
	*/
	List<CarTeamDetail> selectAnchoredCarTeams(Integer id);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月5日
	 * @param 
	 * @return
	*/
	List<CarTeamDetail> selectAnchoredDrivers(Integer id);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月5日
	 * @param 
	 * @return
	*/
	TbAnchoreRecord selectRecordById(Integer id);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	int insertSelective(TbAnchoreRecord tcrc);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	int updateAnchordRelate(Map<String, Object> map);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	int updateAnchordRelateStop(Map<String, Object> map);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	List<CarTeamDetail> selectRejectCarTeamRecord(Integer id);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	String selectApplyRecordByMap(Map<String, Object> map);

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
	int insertAnchoredRelate(Map<String, Object> map);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	int updateRecordDelete(Integer id);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	int insertNewNotice(TbNotice tbn);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月7日
	 * @param 
	 * @return
	*/
	int deleteBatch(List<Integer> list);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月8日
	 * @param 
	 * @return
	*/
	List<TbContainer> selectBoxByPage();

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	List<CarTeamDetail> selectAnchoredCarTeamsExample(VehicleSearch vehicleSearch);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	List<CarTeamDetail> selectAnchoredDriversByExample(VehicleSearch vehicleSearch);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	*/
	List<CarTeamDetail> selectRejectCarTeamRecordByExample(VehicleSearch vehicleSearch);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	*/
	List<CarTeamDetail> selectRejectDriverRecordByExample(TbCarTeamExample example);

	/**
	 * @description 开始
	 * @author liangdeng
	 * @date 2017年12月18日
	 * @param map
	 * @return
	 */
	int updateAnchordRelateStart(Map<String, Object> map);


	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	int insertNoticeByApp(@Param("driverId")Integer driverId,@Param("content")String content);
}
