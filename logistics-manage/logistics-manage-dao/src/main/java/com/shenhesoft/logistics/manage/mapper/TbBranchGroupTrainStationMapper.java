package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroupTrainStation;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroupTrainStationExample;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbBranchGroupTrainStationMapper {
    int countByExample(TbBranchGroupTrainStationExample example);

    int deleteByExample(TbBranchGroupTrainStationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbBranchGroupTrainStation record);

    int insertSelective(TbBranchGroupTrainStation record);

    List<TbBranchGroupTrainStation> selectByExample(TbBranchGroupTrainStationExample example);

    TbBranchGroupTrainStation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbBranchGroupTrainStation record, @Param("example") TbBranchGroupTrainStationExample example);

    int updateByExample(@Param("record") TbBranchGroupTrainStation record, @Param("example") TbBranchGroupTrainStationExample example);

    int updateByPrimaryKeySelective(TbBranchGroupTrainStation record);

    int updateByPrimaryKey(TbBranchGroupTrainStation record);
    
    List<TbTrainStation> selectTrainStationByBranchId(Integer id);
}