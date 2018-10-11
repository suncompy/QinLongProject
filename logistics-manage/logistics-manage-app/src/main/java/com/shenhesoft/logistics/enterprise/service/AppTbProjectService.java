package com.shenhesoft.logistics.enterprise.service;

import java.util.Map;

import com.shenhesoft.logistics.common.GeneralResponse;

/**
 * app端 项目信息 service 接口
 * @author dusd
 * @date 2017年12月28日
 */
public interface AppTbProjectService {

	/**
	 * 
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listProjectCheckApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 项目查询-项目运营管理列表
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @param pageLimit 
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listProjectOperationApp(Map<String, String> dataMap, Integer pageLimit) throws Exception;

	/**
	 * 查询某项目的所属站点列表
	 * @author dusd
	 * @date 2018年1月3日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listSiteProjectApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 通过站点id查询所有的货场
	 * @author dusd
	 * @date 2018年1月3日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listFreightYardBySiteIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 库存调整 - 查询某货场下的某项目的货位信息
	 * @author dusd
	 * @date 2018年1月3日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listStockByFreightYardIdApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 通过项目id查询仓位平面图
	 * @author dusd
	 * @date 2018年1月3日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listCargoLocationImgByProjectId(Map<String, String> dataMap) throws Exception;

	/**
	 * 项目任务分配 APP
	 * @author dusd
	 * @date 2018年1月4日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse saveTbProjectDistributionApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 暂停项目分配
	 * @author dusd
	 * @date 2018年1月5日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse stopTbProjectDistributionApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 查询可分配项目列表
	 * @author dusd
	 * @date 2018年1月5日
	 * @param dataMap
	 * @param pageLimit
	 * @return
	 * @throws Exception
	 */
	GeneralResponse listTbProjectForDistributionApp(Map<String, String> dataMap, Integer pageLimit) throws Exception;

	/**
	 * 开始项目分配
	 * @author dusd
	 * @date 2018年1月5日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse beginTbProjectDistributionApp(Map<String, String> dataMap) throws Exception;

	/**
	 * 库存调整 - 保存调整后的库存信息
	 * @author dusd
	 * @date 2018年1月5日
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	GeneralResponse changeStockApp(Map<String, String> dataMap) throws Exception;

}
