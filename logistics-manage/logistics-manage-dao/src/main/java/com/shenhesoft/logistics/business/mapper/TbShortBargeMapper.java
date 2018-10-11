package com.shenhesoft.logistics.business.mapper;

import com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBarge;
import com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBargeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbShortBargeMapper {
    int countByExample(TbShortBargeExample example);

    int deleteByExample(TbShortBargeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbShortBarge record);

    int insertSelective(TbShortBarge record);

    List<TbShortBarge> selectByExample(TbShortBargeExample example);

    TbShortBarge selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbShortBarge record, @Param("example") TbShortBargeExample example);

    int updateByExample(@Param("record") TbShortBarge record, @Param("example") TbShortBargeExample example);

    int updateByPrimaryKeySelective(TbShortBarge record);

    int updateByPrimaryKey(TbShortBarge record);
}