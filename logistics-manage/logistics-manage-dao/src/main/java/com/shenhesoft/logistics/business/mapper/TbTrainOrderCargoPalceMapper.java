package com.shenhesoft.logistics.business.mapper;

import com.shenhesoft.logistics.business.helpPojo.TrainOrderDetail;
import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalce;
import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalceExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbTrainOrderCargoPalceMapper {
    int countByExample(TbTrainOrderCargoPalceExample example);

    int deleteByExample(TbTrainOrderCargoPalceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbTrainOrderCargoPalce record);

    int insertSelective(TbTrainOrderCargoPalce record);

    List<TbTrainOrderCargoPalce> selectByExample(TbTrainOrderCargoPalceExample example);

    TbTrainOrderCargoPalce selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbTrainOrderCargoPalce record, @Param("example") TbTrainOrderCargoPalceExample example);

    int updateByExample(@Param("record") TbTrainOrderCargoPalce record, @Param("example") TbTrainOrderCargoPalceExample example);

    int updateByPrimaryKeySelective(TbTrainOrderCargoPalce record);

    int updateByPrimaryKey(TbTrainOrderCargoPalce record);

	/**
	 * @description 火运出入库列表
	 * @author LiangDeng
	 * @param orderDetail 
	 * @date 2018年1月3日
	 * @param 
	 * @return
	*/
	List<TrainOrderDetail> selectTrainAccessStorageList(TrainOrderDetail orderDetail);

	/**
	 * @description 查询统计
	 * @author LiangDeng
	 * @date 2018年1月4日
	 * @param 
	 * @return
	*/
	List<TrainOrderDetail> queryStatisticByProjectId(Integer projectId);

	/**
	 * @description 总计信息
	 * @author LiangDeng
	 * @date 2018年1月4日
	 * @param 
	 * @return
	*/
	TrainOrderDetail selectSumInfo(Integer projectId);
}