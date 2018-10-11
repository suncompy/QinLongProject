package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.helpPojo.CargoSpecificteDetail;
import com.shenhesoft.logistics.manage.pojo.specifications.TbSpecifications;
import com.shenhesoft.logistics.manage.pojo.specifications.TbSpecificationsExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbSpecificationsMapper {
    int countByExample(TbSpecificationsExample example);

    int deleteByExample(TbSpecificationsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSpecifications record);

    int insertSelective(TbSpecifications record);

    List<TbSpecifications> selectByExample(TbSpecificationsExample example);

    TbSpecifications selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSpecifications record, @Param("example") TbSpecificationsExample example);

    int updateByExample(@Param("record") TbSpecifications record, @Param("example") TbSpecificationsExample example);

    int updateByPrimaryKeySelective(TbSpecifications record);

    int updateByPrimaryKey(TbSpecifications record);

	int deleteSpecByCargoId(Integer id);

	List<CargoSpecificteDetail> selectSpecificDetailByCargoId(Integer cargoId);
}