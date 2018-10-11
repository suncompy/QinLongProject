// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.interfaces;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.shenhesoft.logistics.business.helpPojo.TbFreightLocationDetail;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.TbFreightYardDetail;
import com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocation;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;

/**
 * @description
 *
 * @author LiangLin
 *
 * @date 2017年12月9日
 */
public interface SiteManageService {

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月9日
	 * @param 
	 * @return
	*/
	List<TbFreightYardDetail> selectFreightYardsByPage();

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月9日
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	*/
	boolean insertFreightYard(TbFreightYardDetail tfyd) throws JsonParseException, JsonMappingException, IOException;

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月11日
	 * @param 
	 * @return
	*/
	boolean delete(List<Integer> idList);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月11日
	 * @param 
	 * @return
	*/
	TbFreightYardDetail selectFreightYardsById(Integer id);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月12日
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	*/
	boolean updateFreightYardById(TbFreightYardDetail tfyd) throws JsonParseException, JsonMappingException, IOException;

	/**
	 * @description 分页查询 货场货位
	 * @author liangLin
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	DataGridResult listFreightYardsByPage(Integer page, Integer limit,TbFreightYard freightYard);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	List<TbFreightYardDetail> getAllFreightYards();

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	List<TbCargoLocation> getAllcargoLocationsByYardId(Integer id);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	List<TbFreightLocationDetail> getAllFreightLocationsByYardId(Integer id);
	
	/**
	 * @description 根据火车站点获取货场
	 * @date 2017年12月26日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	List<TbFreightYard> getFreightYardsByTrainStationId(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @param id 
	 * @date 2018年1月12日
	 * @param 
	 * @return
	*/
	List<TbFreightYard> getAllFreightYardOfIsolated(Integer id);

	List<CustomerInfo> getAllCustomter();

	int checkCargoLocation(Integer id);

	int deleteCargoLocationById(Integer id);

	boolean checkFreightIsUseById(List<Integer> idList);

}
