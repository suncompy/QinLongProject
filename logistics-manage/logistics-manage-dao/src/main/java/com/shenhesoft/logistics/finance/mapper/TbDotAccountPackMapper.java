package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.finance.TbDotAccountPack;

public interface TbDotAccountPackMapper {

    int insert(TbDotAccountPack record);

    int insertSelective(TbDotAccountPack record);

    TbDotAccountPack selectByPrimaryKey(String dotAccountPackId);

    int updateByPrimaryKeySelective(TbDotAccountPack record);

    int updateByPrimaryKey(TbDotAccountPack record);
    
    
    /**
     * @description 获取网点交账
     * @date 2018年4月23日
     * @author shilvfei
     * @param 
     * @return
     */
    List<Map<String, Object>> getDotAccountPacks(@Param("map")Map<String, Object> form);
}