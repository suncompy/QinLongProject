package com.shenhesoft.logistics.business.bussinessCount;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.shenhesoft.logistics.business.pojo.map.ExceptionMsg;
import com.shenhesoft.logistics.business.pojo.map.MapPoint;
import com.shenhesoft.logistics.finance.RoadInfo;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

public interface BussinessCountService {
  /**
   * 新增异常信息.
   * 
   * @param exceptionMsg
   *            异常信息实体
 * @param user 
   * @return dao成功失败标志
   */
  ExceptionMsg addExceptionMsg(ExceptionMsg exceptionMsg);

	/**
	 * @description 火运状态统计
	 * @author LiangDeng
	 * @date 2017年1月26日
	 * @param
	 * @return
	 */
	List<Map<String, Object>> getTrainOrdersCount(Map<String, Object> map);

	/**
	 * @description 短驳运输统计
	 * @author LiangDeng
	 * @param map 
	 * @date 2017年1月26日
	 * @param
	 * @return
	 */
	List<Map<String, Object>> getBulkTrainSprotCount(Map<String, Object> map);
	/**
	 * 根据userId获取branchIds
	 * @description 
	 * @author liangLin
	 * @date 2018年2月3日
	 * @param 
	 * @return
	 */
	List<Integer> getBranchIds(Map<String, Object> map);
	
	/**
     * 根据userId获取当前任职机构
     * @description 
     * @author liangLin
     * @date 2018年2月3日
     * @param 
     * @return
     */
	Map<String, Object> getCurOrg(Map<String, Object> map);
    /**
     * 新增地图标记表.
     * 
     * @param MapPoint 地图标记表实体
     * @return dao成功失败标志
     */
    int addMapPoint(MapPoint MapPoint);
    int addRoadInfo(RoadInfo roadInfo);
    List<Map<String, Object>> getRoadInfos(Map<String, Object> map);
    List<Map<String, Object>> getRoadInfos(int start, int length, Map<String, Object> map);
    /**
     * 批量新增地图标记表.
     * 
     * @param list 批量地图标记表实体
     */
    void addMapPoints(List<MapPoint> list);
	   /**
     * 检索地图标记列表
     * @param map 
     * @date 2018年1月26日
     * @return
     */
    List<Map<String, Object>> getMapPoints(Map<String, Object> map);
    /**
     * 检索地图标记列表
     * @param map 
     * @date 2018年1月26日
     * @return
     */
    List<Map<String, Object>> getOrderMapPointPcs(Map<String, Object> map);
    /**
	 * @description 短驳状态统计
	 * @author LiangDeng
	 * @date 2017年1月30日
	 * @param
	 * @return
	 */
	List<Map<String, Object>> getBulkOrderStatusCount(Map<String, Object> map);

	/**
	 * @description 火运运输统计
	 * @author LiangDeng
	 * @param map 
	 * @date 2017年1月30日
	 * @param
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
  List<Map<String, Object>> getExceptionMsgs(int start, int length, Map<String, Object> form);
  
  /**
   * 获取项目概览
   * @param form 
   */
  List<Map<String, Object>> getProjectSurvey(Map<String, Object> form);
}
