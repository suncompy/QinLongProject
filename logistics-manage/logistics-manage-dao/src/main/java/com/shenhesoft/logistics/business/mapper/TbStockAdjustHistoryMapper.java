package com.shenhesoft.logistics.business.mapper;

import com.shenhesoft.logistics.business.pojo.stock.adjusthistory.TbStockAdjustHistory;
import com.shenhesoft.logistics.business.pojo.stock.adjusthistory.TbStockAdjustHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbStockAdjustHistoryMapper {
    int countByExample(TbStockAdjustHistoryExample example);

    int deleteByExample(TbStockAdjustHistoryExample example);

    int insert(TbStockAdjustHistory record);

    int insertSelective(TbStockAdjustHistory record);

    List<TbStockAdjustHistory> selectByExample(TbStockAdjustHistoryExample example);

    int updateByExampleSelective(@Param("record") TbStockAdjustHistory record, @Param("example") TbStockAdjustHistoryExample example);

    int updateByExample(@Param("record") TbStockAdjustHistory record, @Param("example") TbStockAdjustHistoryExample example);
}