package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.helpPojo.SystemSceneDetail;
import com.shenhesoft.logistics.manage.pojo.systemScene.TbSystemScene;
import com.shenhesoft.logistics.manage.pojo.systemScene.TbSystemSceneExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbSystemSceneMapper {
    int countByExample(TbSystemSceneExample example);

    int deleteByExample(TbSystemSceneExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSystemScene record);

    int insertSelective(TbSystemScene record);

    List<TbSystemScene> selectByExample(TbSystemSceneExample example);

    TbSystemScene selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSystemScene record, @Param("example") TbSystemSceneExample example);

    int updateByExample(@Param("record") TbSystemScene record, @Param("example") TbSystemSceneExample example);

    int updateByPrimaryKeySelective(TbSystemScene record);

    int updateByPrimaryKey(TbSystemScene record);

	/**
	 * @description 
	 * @author LiangDeng
	 * @param search 
	 * @date 2017年12月15日
	 * @param 
	 * @return
	*/
	List<SystemSceneDetail> selectAbnormalList(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	int updateSceneById(Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月16日
	 * @param 
	 * @return
	*/
	SystemSceneDetail selectAbnormalDeatilById(Integer id);
}