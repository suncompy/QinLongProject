// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.business.trainOrder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.shenhesoft.logistics.business.helpPojo.TrainOrderCargoByBulkDetail;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderCargoPlaceDetail;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderDetail;
import com.shenhesoft.logistics.business.helpPojo.TrainOrderSearch;
import com.shenhesoft.logistics.business.pojo.historyLocation.TbHistoryLocation;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;
import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalce;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.finance.AdvanceCharge;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.helpPojo.ProjectAppHelp;
import com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocation;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.pojo.trianType.TbTrainType;

/**
 * @description
 *
 * @author LiangDeng
 *
 * @date 2017年12月18日
 */
public interface TrainOrderService {

	/**
	 * @description 运单列表
	 * @author LiangDeng
	 * @param search 
	 * @param branchGroups 
	 * @date 2017年12月19日
	 * @param 
	 * @return
	*/
	DataGridResult selectTrainOrderByPage(Integer page, Integer limit, byte type,TbSystemUser user, TrainOrderSearch search);

	/**
	 * @description 异常运单
	 * @author LiangDeng
	 * @param search 
	 * @date 2017年12月19日
	 * @param 
	 * @return
	*/
	
	DataGridResult selectExceptionOrderByPage(Integer page, Integer limit, byte type, TbSystemUser user, TrainOrderSearch search);
	
	/**
	 * @description 历史运单
	 * @author LiangDeng
	 * @param search 
	 * @date 2017年12月25日
	 * @param 
	 * @return
	*/
	DataGridResult selectHistoryOrderByPage(Integer page, Integer limit, byte type, TbSystemUser user, TrainOrderSearch search);
	
	/**
	 * @description 运单回收站
	 * @author LiangDeng
	 * @param search 
	 * @date 2017年12月19日
	 * @param 
	 * @return
	*/
	DataGridResult selectDeleteOrderByPage(Integer page, Integer limit, byte type, TbSystemUser user, TrainOrderSearch search);

	/**
	 * @description 
	 * @author LiangDeng
	 * @param tbSystemUser 
	 * @date 2017年12月19日
	 * @param 
	 * @return
	*/
	int addTrainOrder(TbTrainOrder trainOrder, TbSystemUser tbSystemUser);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	TbTrainOrder selectTrainOrderById(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	int deleteTrainOrderById(Integer id, String name, Integer sureName);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	int updateTrainOrderById(Integer id, String name, Integer sureName);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	int resetOrderById(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @param session 
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	LogisticsResult addWaitEntruck(TrainOrderCargoPlaceDetail orderCargoPlaceDetail, TbSystemUser tbSystemUser, HttpSession session);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	List<TbTrainOrderCargoPalce> selectTrainCargoByOrdeId(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @param type 
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	List<TbHistoryLocation> selectHistoryLocationById(Integer id, byte type);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	int insertNewLocation(Integer orderIdLocation, String location);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	int updateSendImgById(Integer id, String img);

	/**
	 * @description 删除运单
	 * @author LiangDeng
	 * @date 2017年12月24日
	 * @param 
	 * @return
	*/
	int deleteSendImgById(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月24日
	 * @param 
	 * @return
	*/
	int updOrderStatusByParam(Integer id, byte status, TbSystemUser tbSystemUser);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月25日
	 * @param 
	 * @return
	*/
	int deleteTrainOrderByParam(Integer id, String reason, String name);

	/**
	 * @description 
	 * @author LiangDeng
	 * @param branchGroups 
	 * @date 2017年12月25日
	 * @param 
	 * @return
	*/
	List<TbProject> selectAllProject(byte projectType,List<DotBranchDetail> branchGroups);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月25日
	 * @param 
	 * @return
	*/
	List<TbTrainType> selectAllTrainType();

	/**
	 * @description 
	 * @author LiangDeng
	 * @param session 
	 * @date 2017年12月25日
	 * @param 
	 * @return
	*/
	LogisticsResult addWaitEntruckOfBulk(TrainOrderCargoByBulkDetail orderCargoPlaceDetail,TbSystemUser tbSystemUser, HttpSession session);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月26日
	 * @param 
	 * @return
	*/
	int handleExceptionById(Integer handleEcxptionId);

	/**
	 * @description 根据项目中的中心站点id查询账户
	 * @author LiangDeng
	 * @date 2017年12月26日
	 * @param 
	 * @return
	*/
	TbTrainStation selectTrainStationById(Integer beginCenterSiteId);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月27日
	 * @param 
	 * @return
	*/
	int updateArriveImgById(Integer id, String img);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月27日
	 * @param 
	 * @return
	*/
	int deleteArriveImg(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月28日
	 * @param 
	 * @return
	*/
	List<TrainOrderDetail> selectDetailById(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月29日
	 * @param 
	 * @return
	*/
	int updateUnloadWeight(TbTrainOrderCargoPalce tbTrainOrderCargoPalce);

	/**
	 * @description 运单卸货查询库存
	 * @author LiangDeng
	 * @date 2017年12月31日
	 * @param 
	 * @return
	*/
	TbStock selectStockByMap(Map<String, Object> map);

	/**
	 * @description 运单卸货修改库存
	 * @author LiangDeng
	 * @date 2017年12月31日
	 * @param 
	 * @return
	*/
	int updateStcok(TbStock stock);

	/**
	 * @description 运单卸货新增库存
	 * @author LiangDeng
	 * @date 2017年12月31日
	 * @param 
	 * @return
	*/
	int addStock(TbStock newStock);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月31日
	 * @param 
	 * @return
	*/
	TbTrainOrderCargoPalce selectCompareCargoPalceById(Integer id);

	/**
	 * @description 查询起始站点库存和货场货位
	 * @author LiangDeng
	 * @date 2017年12月31日
	 * @param 
	 * @return
	*/
	List<TbStock> selectStockList(Integer id, Integer beginCenterSiteId);

	/**
	 * @description 修改总吨数
	 * @author LiangDeng
	 * @date 2018年1月2日
	 * @param 
	 * @return
	*/
	int updateSumUnloadWeght(TbTrainOrder trainOrderSumUnload);

	/**
	 * @description 条件查询火运管理 散堆装的运单列表
	 * @date 2018年1月9日
	 * @author shilvfei
	 * @param trainOrderSearch
	 * @return
	 */
	List<TbTrainOrder> listTrainOrderByCriteria(TrainOrderSearch trainOrderSearch,TbSystemUser user);

	/**
	 * @description 
	 * @author LiangDeng
	 * @param tbSystemUser 
	 * @date 2018年1月12日
	 * @param 
	 * @return
	*/
	int unloadInfoByList(List<TbTrainOrderCargoPalce> cargoPlaceList,Integer projectId, TbSystemUser tbSystemUser, HttpSession session);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2018年1月12日
	 * @param 
	 * @return
	*/
	int unloadInfoByListOfBulk(List<TbTrainOrderCargoPalce> cargoPlaceList,Integer projectId, HttpSession session);

	/**
	 * @param id
	 * @param sysOrgCode 
	 * @return
	 */
	List<ProjectAppHelp> selectAppAllProject(Integer id, String sysOrgCode);

	AdvanceCharge selectAccountListById(Integer projectId, Integer beginCenterSiteId);

	TbCargoLocation selectCargoLocationById(Integer cargoLocationId);

	int checkEntruckWeight(String[] array);
}
