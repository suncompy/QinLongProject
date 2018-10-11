package com.shenhesoft.logistics.business.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrderExample;
import com.shenhesoft.logistics.manage.pojo.box.TbContainer;

/**
 * 短驳运单 mapper
 */
public interface TbOrderMapper {
    int countByExample(TbOrderExample example);

    int deleteByExample(TbOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    List<TbOrder> selectByExample(TbOrderExample example);

    TbOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbOrder record, @Param("example") TbOrderExample example);

    int updateByExample(@Param("record") TbOrder record, @Param("example") TbOrderExample example);

    int updateByPrimaryKeySelective(TbOrder record);

    int updateByPrimaryKey(TbOrder record);

	TbOrder selectBoxManagerOrderById(Integer id);

	TbOrder getDispatchMsgByProjectId(Integer id);

	List<TbContainer> getTbContainerNumbers();

	int updateDispatchOrderByMap(Map<String, Object> map);

	int updateDeleteOrderByMap(Map<String, Object> map);

	int updateSubForwardingOrderByMap(Map<String, Object> map);

	int updateCarryAddOrderByMap(Map<String, Object> map);

	int updateGuideAddOrderByMap(Map<String, Object> map);

	int updateReceipterOrderByMap(Map<String, Object> map);

	int deleteOrderByParam(Map<String, Object> map);

	int revertOrderById(Integer id);

	int insertSelective2(TbOrder tbOrder);

	/**
	 * @description 获取在途运载发货吨位
	 * @date 2017年12月25日
	 * @author shilvfei
	 * @param  Byte stepSelectCode,  List<Byte> status, Integer id
	 * @return 
	 */
	BigDecimal countSendGrossByProjectId(Map<String, Object> map);

	/**
	 * @description 获取车次
	 * @date 2017年12月25日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	Integer countCarCountByProjectId(Map<String, Object> map);
	
	/**
	 * @description 获取到货吨位
	 * @date 2017年12月25日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	BigDecimal countReceiptGrossByProjectId(Map<String, Object> map);

	TbOrder getExceptionByOrderId(Integer id);

	int rejectExceptionByMap(Map<String, Object> map);

	/**
	 * 企业用户 当月完成订单 数量
	 * @author dusd
	 * @date 2018年1月4日
	 * @param userId
	 * @return
	 */
	Integer completeTbOrderNumUserIdMonth(@Param("userId")Integer userId);
	/**
	 * 企业用户 当日完成订单 数量
	 * @author dusd
	 * @date 2018年1月4日
	 * @param userId
	 * @return
	 */
	Integer completeTbOrderNumUserIdDay(@Param("userId")Integer userId);
	/**
	 * 企业用户 不同状态运单数量
	 * @author dusd
	 * @date 2018年1月4日
	 * @param userId
	 * @return
	 */
	Integer differentStatusTbOrderNumUserId(Map<String, Object> map);

	/**
	 * 通过客户对账设置信息 查询 符合条件的运单列表
	 * @author dusd
	 * @date 2018年1月19日
	 * @param customerCheckingConfMap
	 * @return
	 */
	List<Map<String, Object>> listTbOrderByCustomerCheckingConfMap(@Param("map")Map<String, Object> customerCheckingConfMap);

	/**
	 * 通过打包信息map查询运单列表
	 * @author dusd
	 * @date 2018年1月19日
	 * @param customerPackMap
	 * @return
	 */
	List<Map<String, Object>> listTbOrderByCustomerPackMap(@Param("map")Map<String, Object> customerPackMap);

	
	/**
	 * @description 费用对账 获取列表信息
	 * @date 2018年1月27日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<Map<String, Object>> getCostReconciliations(Map<String, Object> form);
	
	/**
	 * @description 财务计费确认修改运单状态为已完成
	 * @date 2018年2月1日
	 * @author liangdeng
	 * @param map 
	 * @param 
	 * @return
	 */
	int updateOrderStatusOfFince(Map<String, Object> map);
	
	/**
	 * @description 更新司机运单状态 已接单
	 * @date 2018年2月5日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	int updateDriverOrderStatus(Map<String, Object> map);
	
	/**
	 * @description 根据司机id获取司机的运单状态
	 * @date 2018年2月5日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	int selectDriverStatusByDriverId(Integer driverId);

	/**
	 * @description app保存等待调度货场货位
	 * @date 2018年3月8日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	int updateDisptchAddOrderByMap(Map<String, Object> map);

	/**
	 * @description app更新运单
	 * @date 2018年3月9日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	int appUpdateOrderStatus(Map<String, Object> map);

	/**
	 * @description app端首页订单统计
	 * @date 2018年3月9日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	Integer queryCountsOrderOfDays(Map<String, Object> map);

	Integer selectCarteamIdByDriverId(Integer driverId);

	Integer appWaitBillentCounts(Map<String, Object> map);
}