package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.pojo.carTeam.TbCarTeam;
import com.shenhesoft.logistics.manage.pojo.carTeam.TbCarTeamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCarTeamMapper {
    int countByExample(TbCarTeamExample example);

    int deleteByExample(TbCarTeamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCarTeam record);

    int insertSelective(TbCarTeam record);

    List<TbCarTeam> selectByExample(TbCarTeamExample example);

    TbCarTeam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCarTeam record, @Param("example") TbCarTeamExample example);

    int updateByExample(@Param("record") TbCarTeam record, @Param("example") TbCarTeamExample example);

    int updateByPrimaryKeySelective(TbCarTeam record);

    int updateByPrimaryKey(TbCarTeam record);
    
    List<TbCarTeam> selectCarTeamByCompanyId(Integer id);
}