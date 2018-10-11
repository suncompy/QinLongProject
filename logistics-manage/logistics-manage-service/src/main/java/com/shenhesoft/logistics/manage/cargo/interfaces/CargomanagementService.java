// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.cargo.interfaces;

import java.util.List;

import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.manage.helpPojo.CargoPointDetail;
import com.shenhesoft.logistics.manage.helpPojo.CargoSpecificteDetail;
import com.shenhesoft.logistics.manage.helpPojo.TbCargoInfomation;
import com.shenhesoft.logistics.manage.helpPojo.TbCargoMainPoint;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargo;
import com.shenhesoft.logistics.manage.pojo.point.TbPoint;
import com.shenhesoft.logistics.manage.pojo.specifications.TbSpecifications;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月5日
 */
public interface CargomanagementService {

	/**
	 * @description 货物信息 :分页条件查询
	 * @author LiangDeng
	 * @date 2017年12月5日
	 * @param 
	 * @return
	*/
	DataGridResult listCargoByPage(Integer page, Integer limit,TbCargo cargo);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月6日
	 * @param 
	 * @return
	*/
	boolean deleteCargoInfo(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月7日
	 * @param 
	 * @return
	*/
	List<TbSpecifications> selectSpecListBycargoId(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月7日
	 * @param 
	 * @return
	*/
	TbCargoMainPoint selectMainPointDetailById(Integer cargoId);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月7日
	 * @param 
	 * @return
	*/
	List<CargoPointDetail> selectPointDetailByCargoId(Integer cargoId);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月7日
	 * @param 
	 * @return
	*/
	List<CargoSpecificteDetail> selectSpecificDetailByCargoId(Integer cargoId);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月11日
	 * @param 
	 * @return
	*/
	int addCargo(TbCargo cargo);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月11日
	 * @param 
	 * @return
	*/
	int addPoint(TbPoint point);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月11日
	 * @param 
	 * @return
	*/
	int addSpecs(TbSpecifications specs);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	List<CargoPointDetail> queryPointDetailByCargoId(Integer cargoId);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	int updCargo(TbCargo cargo);

	/**
	 * @description 
	 * @author LiangDeng
	 * @param pointList 
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	int delPointDetail(Integer cargoId);

	/**
	 * @description 
	 * @author LiangDeng
	 * @param specList 
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	int delSpeciDetail(Integer cargoId);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	List<Integer> selectProjectIdByCargoId(Integer id);

	/**
	 * @description 获取所有的获取信息
	 * @date 2017年12月19日
	 * @author shilvfei
	 * @return
	 */
	List<TbCargoMainPoint> listCargo();

}
