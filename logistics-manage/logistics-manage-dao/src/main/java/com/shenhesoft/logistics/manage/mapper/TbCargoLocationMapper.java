package com.shenhesoft.logistics.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.business.helpPojo.CargoLocationPlan;
import com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocation;
import com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocationExample;

public interface TbCargoLocationMapper {
    int countByExample(TbCargoLocationExample example);

    int deleteByExample(TbCargoLocationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCargoLocation record);

    int insertSelective(TbCargoLocation record);

    List<TbCargoLocation> selectByExample(TbCargoLocationExample example);

    TbCargoLocation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCargoLocation record, @Param("example") TbCargoLocationExample example);

    int updateByExample(@Param("record") TbCargoLocation record, @Param("example") TbCargoLocationExample example);

    int updateByPrimaryKeySelective(TbCargoLocation record);

    int updateByPrimaryKey(TbCargoLocation record);

    List<CargoLocationPlan> getCargoLocationPlans(Integer freightYardId);
}