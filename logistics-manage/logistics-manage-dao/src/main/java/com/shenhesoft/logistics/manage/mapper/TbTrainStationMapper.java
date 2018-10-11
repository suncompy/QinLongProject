package com.shenhesoft.logistics.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.manage.helpPojo.TbTrainStationHelp;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStationExample;

public interface TbTrainStationMapper {
    int countByExample(TbTrainStationExample example);

    int deleteByExample(TbTrainStationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbTrainStation record);

    int insertSelective(TbTrainStation record);

    List<TbTrainStation> selectByExample(TbTrainStationExample example);

    TbTrainStation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbTrainStation record, @Param("example") TbTrainStationExample example);

    int updateByExample(@Param("record") TbTrainStation record, @Param("example") TbTrainStationExample example);

    int updateByPrimaryKeySelective(TbTrainStation record);

    int updateByPrimaryKey(TbTrainStation record);

	List<TbTrainStation> selectTrainStationByPage(TbTrainStationExample example);

	TbTrainStationHelp selectTrainStationByIs(Integer id);

	int updateTbTrainStationById(TbTrainStation tbStion);

	int deleteBatch(List<Integer> list);

	int selectIsFreightYardDeleteById(List<Integer> list);

	int selectIsContainerDeleteById(List<Integer> list);

	int selectIsStationBusinessDeleteById(List<Integer> list);

	List<TbTrainStation> getParentsById(Map<String,Object> map);

	String selectStationNameByParentId(Integer parentId);
	
	int updateContainNumLocation(Map<String,Object> map);

	int selectChildrenStationById(List<Integer> list);

	int selectProjectByStationId(List<Integer> list);

	
	/**
	 * @description 根据区域找到站点
	 * @date 2018年4月16日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<TbTrainStation> listTrainStationByArea(@Param("map")Map<String, Object> map);
}