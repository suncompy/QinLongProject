package com.shenhesoft.logistics.finance.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.finance.ThreePartiesReceivables;
public interface ThreePartiesReceivablesMapper {

    int deleteByPrimaryKey(String id);

    int addThreePartiesReceivables(ThreePartiesReceivables record);

    ThreePartiesReceivables selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ThreePartiesReceivables record);

    int updateByPrimaryKey(ThreePartiesReceivables record);

	List<Map<String, Object>> getThreePartiesReceivables(@Param("map")Map<String, Object> form);
}