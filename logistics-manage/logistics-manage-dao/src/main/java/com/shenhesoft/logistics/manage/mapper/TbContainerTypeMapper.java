package com.shenhesoft.logistics.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.manage.pojo.box.TbContainerType;
import com.shenhesoft.logistics.manage.pojo.box.TbContainerTypeExample;

public interface TbContainerTypeMapper {
    int countByExample(TbContainerTypeExample example);

    int deleteByExample(TbContainerTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbContainerType record);

    int insertSelective(TbContainerType record);

    List<TbContainerType> selectByExample(TbContainerTypeExample example);

    TbContainerType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbContainerType record, @Param("example") TbContainerTypeExample example);

    int updateByExample(@Param("record") TbContainerType record, @Param("example") TbContainerTypeExample example);

    int updateByPrimaryKeySelective(TbContainerType record);

    int updateByPrimaryKey(TbContainerType record);

	List<TbContainerType> selectBoxTypeByPage(@Param("map") Map<String, Object> map);

	int deleteBoxTypeBatch(List<Integer> list);

	int selectIsRelateBoxDeleteById(List<Integer> list);
}