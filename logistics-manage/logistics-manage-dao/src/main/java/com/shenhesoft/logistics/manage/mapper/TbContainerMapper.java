package com.shenhesoft.logistics.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.manage.helpPojo.TbBoxDetail;
import com.shenhesoft.logistics.manage.pojo.box.TbContainer;
import com.shenhesoft.logistics.manage.pojo.box.TbContainerExample;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYardExample;

public interface TbContainerMapper {
    int countByExample(TbContainerExample example);

    int deleteByExample(TbContainerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbContainer record);

    int insertSelective(TbContainer record);

    List<TbContainer> selectByExample(TbContainerExample example);

    TbContainer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbContainer record, @Param("example") TbContainerExample example);

    int updateByExample(@Param("record") TbContainer record, @Param("example") TbContainerExample example);

    int updateByPrimaryKeySelective(TbContainer record);

    int updateByPrimaryKey(TbContainer record);

	List<TbBoxDetail> selectBoxByPage();

	int deleteBoxBatch(List<Integer> list);

	List<TbBoxDetail> selectBoxByExample(TbContainerExample example);

	/**
	 * 通过集装箱名称查询唯一的集装箱信息
	 * @author dusd
	 * @date 2017年12月28日
	 * @param containerId
	 * @return
	 */
	TbContainer veiwTbContainerByContainerId(String containerId);

	int selectContainNumByStationId(Integer stationId);

	String selectProjectCodeByProjectId(Integer projectId);

	int updateContainerStatus(Map<String, Object> cMap);

}