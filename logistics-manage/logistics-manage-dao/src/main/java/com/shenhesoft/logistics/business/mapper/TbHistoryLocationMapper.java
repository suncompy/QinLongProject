package com.shenhesoft.logistics.business.mapper;

import com.shenhesoft.logistics.business.pojo.historyLocation.TbHistoryLocation;
import com.shenhesoft.logistics.business.pojo.historyLocation.TbHistoryLocationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbHistoryLocationMapper {
    int countByExample(TbHistoryLocationExample example);

    int deleteByExample(TbHistoryLocationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbHistoryLocation record);

    int insertSelective(TbHistoryLocation record);

    List<TbHistoryLocation> selectByExample(TbHistoryLocationExample example);

    TbHistoryLocation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbHistoryLocation record, @Param("example") TbHistoryLocationExample example);

    int updateByExample(@Param("record") TbHistoryLocation record, @Param("example") TbHistoryLocationExample example);

    int updateByPrimaryKeySelective(TbHistoryLocation record);

    int updateByPrimaryKey(TbHistoryLocation record);
}