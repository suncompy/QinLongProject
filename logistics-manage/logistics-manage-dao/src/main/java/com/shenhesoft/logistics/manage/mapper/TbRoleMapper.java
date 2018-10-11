package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.pojo.role.TbRole;
import com.shenhesoft.logistics.manage.pojo.role.TbRoleExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbRoleMapper {
    int countByExample(TbRoleExample example);

    int deleteByExample(TbRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbRole record);

    int insertSelective(TbRole record);

    List<TbRole> selectByExample(TbRoleExample example);

    TbRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbRole record, @Param("example") TbRoleExample example);

    int updateByExample(@Param("record") TbRole record, @Param("example") TbRoleExample example);

    int updateByPrimaryKeySelective(TbRole record);

    int updateByPrimaryKey(TbRole record);
    
    /**
	 * @description 获取所有的角色
	 * @date 2018年4月11日
	 * @author shilvfei
	 * @param form
	 * @return
	 */
	List<TbRole> getRoles(@Param(value = "map") Map<String, Object> form);

	List<Map<String, Object>> selectMenus();
	
	/**
	 *根据权限id获取菜单
	 */
	List<Map<String, Object>>  selectMenuByRoleId(Integer roleId);
}