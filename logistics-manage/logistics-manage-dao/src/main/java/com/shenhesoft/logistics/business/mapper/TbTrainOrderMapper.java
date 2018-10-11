package com.shenhesoft.logistics.business.mapper;

import com.shenhesoft.logistics.business.helpPojo.TrainOrderDetail;
import com.shenhesoft.logistics.business.pojo.historyLocation.TbHistoryLocation;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrderExample;
import com.shenhesoft.logistics.finance.AdvanceCharge;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.pojo.trianType.TbTrainType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbTrainOrderMapper {
    int countByExample(TbTrainOrderExample example);

    int deleteByExample(TbTrainOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbTrainOrder record);

    int insertSelective(TbTrainOrder record);

    List<TbTrainOrder> selectByExample(TbTrainOrderExample example);

    TbTrainOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbTrainOrder record, @Param("example") TbTrainOrderExample example);

    int updateByExample(@Param("record") TbTrainOrder record, @Param("example") TbTrainOrderExample example);

    int updateByPrimaryKeySelective(TbTrainOrder record);

    int updateByPrimaryKey(TbTrainOrder record);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月19日
	 * @param 
	 * @return
	*/
	List<TbTrainOrder> selectTrainOrderByPage(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月19日
	 * @param 
	 * @return
	*/
	List<TbTrainOrder> selectExceptionOrderByPage(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月19日
	 * @param 
	 * @return
	*/
	List<TbTrainOrder> selectDeleteOrderByPage(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	int updateTrainOrderById(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	int deleteTrainOrderById(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	int resetTrainOrderById(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	List<TbHistoryLocation> selectHistoryLocationById(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	int updateSendImgById(Map<String, Object> map);

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
	 * @date 2017年12月25日
	 * @param 
	 * @return
	*/
	int deleteTrainOrderByParam(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月25日
	 * @param 
	 * @return
	*/
	List<TbTrainType> selectAllTrainType();

	/**
	 * @description 获取在途运载发货吨位
	 * @date 2017年12月25日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	BigDecimal countEntruckWeightByProjectId(@Param("status")List<Byte>  status, @Param("id") Integer id);

	/**
	 * @description 获取发货车次
	 * @date 2017年12月25日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	Integer countCarCountByProjectId(@Param("status") List<Byte>  status, @Param("id") Integer id);
	
	/**
	 * @description 获取在途运载收货吨位
	 * @date 2017年12月25日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	BigDecimal countArriveWeightByProjectId(@Param("status") List<Byte>  status, @Param("id") Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月25日
	 * @param 
	 * @return
	*/
	List<TbTrainOrder> selectHistoryOrderByPage(Map<String, Object> map);
	
	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月26日
	 * @param 
	 * @return
	*/
	int updateExceptionByParam(Map<String, Object> map);

	/**
	 * @description 
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
	int updateArriveImgById(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月27日
	 * @param 
	 * @return
	*/
	int deleteArriveImgById(Integer id);

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
	 * @date 2017年12月31日
	 * @param 
	 * @return
	*/
	TbStock selectStockByMap(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月31日
	 * @param 
	 * @return
	*/
	List<TbStock> selectStockList(Map<String, Object> map);

	/**
	 * 通过客户对账设置信息 查询 符合条件的火运运单列表
	 * @author dusd
	 * @date 2018年1月19日
	 * @param customerCheckingConfMap
	 * @return
	 */
	List<Map<String, Object>> listTrainOrderByCustomerCheckingConfMap(@Param("map")Map<String, Object> customerCheckingConfMap);

	/**
	 * 通过打包信息map查询运单列表
	 * @author dusd
	 * @date 2018年1月19日
	 * @param customerPackMap
	 * @return
	 */
	List<Map<String, Object>> listTrainOrderByCustomerPackMap(@Param("map")Map<String, Object> customerPackMap);

	/**
	 * 
	 * @description 查询中心站点预付款账户
	 * @author LiangDeng
	 * @param beginCenterSiteId 
	 * @date 2018年2月12日
	 * @param 
	 * @return
	 */
	AdvanceCharge selectAccountListById(Map<String, Object> map);
}