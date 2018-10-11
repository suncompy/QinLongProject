package com.shenhesoft.logistics.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.manage.pojo.smsPlan.TbSmsPlan;
import com.shenhesoft.logistics.manage.pojo.smsPlan.TbSmsPlanExample;

public interface TbSmsPlanMapper {
    int countByExample(TbSmsPlanExample example);

    int deleteByExample(TbSmsPlanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSmsPlan record);

    int insertSelective(TbSmsPlan record);

    List<TbSmsPlan> selectByExample(TbSmsPlanExample example);

    TbSmsPlan selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSmsPlan record, @Param("example") TbSmsPlanExample example);

    int updateByExample(@Param("record") TbSmsPlan record, @Param("example") TbSmsPlanExample example);

    int updateByPrimaryKeySelective(TbSmsPlan record);

    int updateByPrimaryKey(TbSmsPlan record);
}