package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.helpPojo.CargoPointDetail;
import com.shenhesoft.logistics.manage.pojo.point.TbPoint;
import com.shenhesoft.logistics.manage.pojo.point.TbPointExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbPointMapper {
    int countByExample(TbPointExample example);

    int deleteByExample(TbPointExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbPoint record);

    int insertSelective(TbPoint record);

    List<TbPoint> selectByExample(TbPointExample example);

    TbPoint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbPoint record, @Param("example") TbPointExample example);

    int updateByExample(@Param("record") TbPoint record, @Param("example") TbPointExample example);

    int updateByPrimaryKeySelective(TbPoint record);

    int updateByPrimaryKey(TbPoint record);

	int deletePointByCargoId(Integer id);

	List<CargoPointDetail> selectPointDeatilByCargoId(Integer cargoId);

	List<CargoPointDetail> queryPointDetailByCargoId(Integer cargoId);
}