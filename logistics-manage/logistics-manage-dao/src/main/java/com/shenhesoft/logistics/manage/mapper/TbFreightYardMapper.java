package com.shenhesoft.logistics.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.business.helpPojo.TbFreightLocationDetail;
import com.shenhesoft.logistics.manage.helpPojo.TbFreightYardDetail;
import com.shenhesoft.logistics.manage.pojo.CargoLocation.TbCargoLocation;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYardExample;

public interface TbFreightYardMapper {
    int countByExample(TbFreightYardExample example);

    int deleteByExample(TbFreightYardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbFreightYard record);

    int insertSelective(TbFreightYard record);

    List<TbFreightYard> selectByExample(TbFreightYardExample example);

    TbFreightYard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbFreightYard record, @Param("example") TbFreightYardExample example);

    int updateByExample(@Param("record") TbFreightYard record, @Param("example") TbFreightYardExample example);

    int updateByPrimaryKeySelective(TbFreightYard record);

    int updateByPrimaryKey(TbFreightYard record);

	List<TbFreightYardDetail> selectFreightYardsByPage();

	int selectLocaltionCountByYardId(Integer trainStationId);

	int deleteBatch(List<Integer> list);

	TbFreightYardDetail selectFreightYardsById(Integer id);

	List<TbCargoLocation> selectCargoLocationByfreightYardId(Integer id);

	int updateFreightYardById(TbFreightYardDetail tfyd);

	int deleteRelateBatchCargoLocation(Integer id);

	List<TbFreightYardDetail> selectFreightYardsByExample(TbFreightYardExample example);

	List<TbFreightYardDetail> getAllFreightYards();

	List<TbCargoLocation> getAllcargoLocationsByYardId(Integer id);

	List<TbFreightLocationDetail> getAllFreightLocationsByYardId(@Param("id") Integer id);

	/**
	 * @description 
	 * @author LiangDeng
	 * @param id 
	 * @date 2018年1月12日
	 * @param 
	 * @return
	*/
	List<TbFreightYard> getAllFreightYardsOfIsolate(Integer trainStationId);

	/**
	 * @description 根据货场id查询货位
	 * @author LiangDeng
	 * @date 2018年1月18日
	 * @param 
	 * @return
	*/
	List<TbCargoLocation> selecCargotByid(Integer ids);

	TbFreightYardDetail selectFreightYardsByIdOfNoStation(Integer id);

	int checkCargoLocation(Integer id);

	int deleteCargoLocationById(Integer id);

	int checkFreightIsUseById(Integer id);


}