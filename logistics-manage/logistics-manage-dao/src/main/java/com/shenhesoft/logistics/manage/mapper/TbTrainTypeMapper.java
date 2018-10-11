package com.shenhesoft.logistics.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.manage.pojo.customer.TbCustomerExample;
import com.shenhesoft.logistics.manage.pojo.trianType.TbTrainType;
import com.shenhesoft.logistics.manage.pojo.trianType.TbTrainTypeExample;

public interface TbTrainTypeMapper {
    int countByExample(TbTrainTypeExample example);

    int deleteByExample(TbTrainTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbTrainType record);

    int insertSelective(TbTrainType record);

    List<TbTrainType> selectByExample(TbTrainTypeExample example);

    TbTrainType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbTrainType record, @Param("example") TbTrainTypeExample example);

    int updateByExample(@Param("record") TbTrainType record, @Param("example") TbTrainTypeExample example);

    int updateByPrimaryKeySelective(TbTrainType record);

    int updateByPrimaryKey(TbTrainType record);

	List<TbTrainType> selectTrainTypeByPage();

	int deleteBatch(List<Integer> list);

	List<TbTrainType> selectTrainTypeByExample(TbTrainTypeExample example);

	List<TbTrainType> selectTrainTypeList(@Param("map") Map<String, Object> map);
}