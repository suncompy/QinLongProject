package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.pojo.permission.TbRolePerssion;
import com.shenhesoft.logistics.manage.pojo.permission.TbRolePerssionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRolePerssionMapper {
    int countByExample(TbRolePerssionExample example);

    int deleteByExample(TbRolePerssionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbRolePerssion record);

    int insertSelective(TbRolePerssion record);

    List<TbRolePerssion> selectByExample(TbRolePerssionExample example);

    TbRolePerssion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbRolePerssion record, @Param("example") TbRolePerssionExample example);

    int updateByExample(@Param("record") TbRolePerssion record, @Param("example") TbRolePerssionExample example);

    int updateByPrimaryKeySelective(TbRolePerssion record);

    int updateByPrimaryKey(TbRolePerssion record);
}