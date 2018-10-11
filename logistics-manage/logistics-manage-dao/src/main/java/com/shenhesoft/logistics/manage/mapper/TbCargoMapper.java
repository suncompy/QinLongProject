package com.shenhesoft.logistics.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.manage.helpPojo.TbCargoInfomation;
import com.shenhesoft.logistics.manage.helpPojo.TbCargoMainPoint;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargo;
import com.shenhesoft.logistics.manage.pojo.cargo.TbCargoExample;

public interface TbCargoMapper {
    int countByExample(TbCargoExample example);

    int deleteByExample(TbCargoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCargo record);

    int insertSelective(TbCargo record);

    List<TbCargo> selectByExample(TbCargoExample example);

    TbCargo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCargo record, @Param("example") TbCargoExample example);

    int updateByExample(@Param("record") TbCargo record, @Param("example") TbCargoExample example);

    int updateByPrimaryKeySelective(TbCargo record);

    int updateByPrimaryKey(TbCargo record);

    List<TbCargoMainPoint> selectCargoList(TbCargoExample example);

	TbCargoMainPoint selectMainPointDetailById(Integer cargoId);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月11日
	 * @param 
	 * @return
	*/
	int insertCargo(TbCargo cargo);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月12日
	 * @param 
	 * @return
	*/
	List<Integer> selectProjectIdByCargoId(Integer id);

	List<TbCargo> selectLsitCargo(TbCargoExample example);

}