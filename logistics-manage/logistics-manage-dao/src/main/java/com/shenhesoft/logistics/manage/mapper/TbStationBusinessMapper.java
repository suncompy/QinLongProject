package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.helpPojo.TbStationBusinessHelp;
import com.shenhesoft.logistics.manage.pojo.stationBusiness.TbStationBusiness;
import com.shenhesoft.logistics.manage.pojo.stationBusiness.TbStationBusinessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbStationBusinessMapper {
    int countByExample(TbStationBusinessExample example);

    int deleteByExample(TbStationBusinessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbStationBusiness record);

    int insertSelective(TbStationBusiness record);

    List<TbStationBusiness> selectByExample(TbStationBusinessExample example);

    TbStationBusiness selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbStationBusiness record, @Param("example") TbStationBusinessExample example);

    int updateByExample(@Param("record") TbStationBusiness record, @Param("example") TbStationBusinessExample example);

    int updateByPrimaryKeySelective(TbStationBusiness record);

    int updateByPrimaryKey(TbStationBusiness record);
    
    List<TbStationBusinessHelp> selectTbStationBusinessByExample(TbStationBusinessExample example);
}