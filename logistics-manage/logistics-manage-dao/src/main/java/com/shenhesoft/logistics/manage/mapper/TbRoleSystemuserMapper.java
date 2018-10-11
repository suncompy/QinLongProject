package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.pojo.role.TbRoleSystemuser;
import com.shenhesoft.logistics.manage.pojo.role.TbRoleSystemuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRoleSystemuserMapper {
    int countByExample(TbRoleSystemuserExample example);

    int deleteByExample(TbRoleSystemuserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbRoleSystemuser record);

    int insertSelective(TbRoleSystemuser record);

    List<TbRoleSystemuser> selectByExample(TbRoleSystemuserExample example);

    TbRoleSystemuser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbRoleSystemuser record, @Param("example") TbRoleSystemuserExample example);

    int updateByExample(@Param("record") TbRoleSystemuser record, @Param("example") TbRoleSystemuserExample example);

    int updateByPrimaryKeySelective(TbRoleSystemuser record);

    int updateByPrimaryKey(TbRoleSystemuser record);
}