package com.shenhesoft.logistics.business.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.shenhesoft.logistics.business.pojo.map.ExceptionMsg;
import com.shenhesoft.logistics.business.pojo.map.MapPoint;
import com.shenhesoft.logistics.finance.RoadInfo;

public interface BussinessHomeMapper {
  
  
  /**
   * 新增异常信息.
   * 
   * @param exceptionMsg
   *            异常信息实体
   * @return dao成功失败标志
   */
   int addExceptionMsg(ExceptionMsg exceptionMsg);
	/**
	 * 火运状态统计
	 * @author LiangDeng
	 * @param map 
	 * @date 2018年1月26日
	 * @return
	 */
	List<Map<String, Object>> trainStatusCount(Map<String, Object> map);

	/**
	 * 短驳运输统计
	 * @author LiangDeng
	 * @param map 
	 * @date 2018年1月26日
	 * @return
	 */
	List<Map<String, Object>> getBulkTrainSprotCount(Map<String, Object> map);
	  /**
	   * 新增地图标记表.
	   * 
	   * @param MapPoint 地图标记表实体
	   * @return dao成功失败标志
	   */
	  int addMapPoint(MapPoint MapPoint);
	  int addRoadInfo(RoadInfo roadInfo);
	  List<Map<String, Object>> getRoadInfos(Map<String, Object> map);

	  /**
	   * 批量新增地图标记表.
	   * 
	   * @param list 批量地图标记表实体
	   */
	  void addMapPoints(@Param(value = "list") List<MapPoint> list);

	  /**
	   * 查看地图标记表详情.
	   * 
	   * @param id 主键
	   * @return 页面表单
	   */
	  Map<String, Object> getMapPointById(String id);
	  /**
       * 查看第四层菜单.
       * 
       * @param id 主键
       * @return 页面表单
       */
	  List<Map<String, Object>> getMenuFourthLevel(String id);
	  /**
       * 根据最大id，查询车号分组carrier_vehicle_id的最新运单记录.
       * 
       * @param map 参数
       * @return 页面表单
       */
      Map<String, Object> getNewTimeOrder(@Param("map") Map<String, Object> map);
	  /**
	   * 修改地图标记表.
	   * 
	   * @param MapPoint 地图标记表实体
	   * @return dao成功失败标志
	   */
	  int editMapPointById(MapPoint MapPoint);

	  /**
	   * 批量修改地图标记表.
	   * 
	   * @param MapPoint 地图标记表实体
	   * @param ids 主键集合
	   */
	  void editMapPointByIds(@Param("map") MapPoint MapPoint,@Param("list") List<String> ids);
	/**
     * 检索地图标记列表
     * @param map 
     * @date 2018年1月26日
     * @return
     */
    List<Map<String, Object>> getMapPoints(@Param("map") Map<String, Object> map);
    /**
     * 检索地图标记列表
     * @param map 
     * @date 2018年1月26日
     * @return
     */
    List<Map<String, Object>> getOrderMapPointPcs(@Param("map") Map<String, Object> map);
    /**
	 * 短驳状态统计
	 * @author LiangDeng
	 * @param map 
	 * @date 2018年1月30日
	 * @return
	 */
	List<Map<String, Object>> boxBulkStatusCount(Map<String, Object> map);

	/**
	 * 火运运输统计
	 * @author LiangDeng
	 * @param map 
	 * @date 2018年1月30日
	 * @return
	 */
	List<Map<String, Object>> getTrainSprotCount(Map<String, Object> map);
  /**
   * @description 
   * @author liangLin
   * @date 2018年2月9日
   * @param 
   * @return
  */
  List<Map<String, Object>> getExceptionMsgs(@Param("map") Map<String, Object> form);
  /**
   * 检索异常订单
   * @description 
   * @author liangLin
   * @date 2018年2月9日
   * @param 
   * @return
   */
  Map<String, Object> getOrderExceptionByOrderId(Integer id);
  /**
   * 获取项目概览
   * @param form 
   */
  List<Map<String, Object>> getProjectSurvey(@Param("map") Map<String, Object> form);
  Map<String, Object> getProjectCodeMax(Map<String, Object> form);
  Map<String, Object> getOrderCodeMaxByProjectId(Map<String, Object> form);
  Map<String, Object> getPleaseTrainNumberMaxByProjectId(Map<String, Object> form);
  Map<String, Object> getPackIdMax(Map<String, Object> form);
  Map<String, Object> getCheckIdMax(Map<String, Object> form);
}