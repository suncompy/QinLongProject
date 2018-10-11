package com.shenhesoft.logistics.finance.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.finance.TbEnterpriseReceivables;

public interface TbEnterpriseReceivablesMapper {

    int insert(TbEnterpriseReceivables record);

    int insertSelective(TbEnterpriseReceivables record);

    TbEnterpriseReceivables selectByPrimaryKey(String enterpriseReceivablesId);

    int updateByPrimaryKeySelective(TbEnterpriseReceivables record);

    int updateByPrimaryKey(TbEnterpriseReceivables record);

	List<Map<String, Object>> getEnterpriseReceivables(@Param("map")Map<String, Object> map);
}