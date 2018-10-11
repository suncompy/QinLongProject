package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.pojo.permission.TbPermission;
import com.shenhesoft.logistics.manage.pojo.permission.TbPermissionExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbPermissionMapper {
    int countByExample(TbPermissionExample example);

    int deleteByExample(TbPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbPermission record);

    int insertSelective(TbPermission record);

    List<TbPermission> selectByExample(TbPermissionExample example);

    TbPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbPermission record, @Param("example") TbPermissionExample example);

    int updateByExample(@Param("record") TbPermission record, @Param("example") TbPermissionExample example);

    int updateByPrimaryKeySelective(TbPermission record);

    int updateByPrimaryKey(TbPermission record);
    
    List<TbPermission>  selectPermissionByRange(Byte range);
    
    List<TbPermission>  selectPermissionByRoleId(Integer id);
    
    List<TbPermission>  selectPermissionByRoleIdAndRange(Map<String, Object> map);
    
    int insertPermission(TbPermission permission);
}