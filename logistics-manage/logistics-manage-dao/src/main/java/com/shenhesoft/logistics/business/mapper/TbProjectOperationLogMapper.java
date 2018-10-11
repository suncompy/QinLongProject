package com.shenhesoft.logistics.business.mapper;

import com.shenhesoft.logistics.business.pojo.log.TbProjectOperationLog;
import com.shenhesoft.logistics.business.pojo.log.TbProjectOperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProjectOperationLogMapper {
    int countByExample(TbProjectOperationLogExample example);

    int deleteByExample(TbProjectOperationLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbProjectOperationLog record);

    int insertSelective(TbProjectOperationLog record);

    List<TbProjectOperationLog> selectByExample(TbProjectOperationLogExample example);

    TbProjectOperationLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbProjectOperationLog record, @Param("example") TbProjectOperationLogExample example);

    int updateByExample(@Param("record") TbProjectOperationLog record, @Param("example") TbProjectOperationLogExample example);

    int updateByPrimaryKeySelective(TbProjectOperationLog record);

    int updateByPrimaryKey(TbProjectOperationLog record);
}