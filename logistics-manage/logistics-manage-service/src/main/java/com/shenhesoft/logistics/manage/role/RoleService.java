package com.shenhesoft.logistics.manage.role;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.helpPojo.RoleInformation;
import com.shenhesoft.logistics.manage.pojo.role.TbRole;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月7日
 */
public interface RoleService {

	/**
	 * @description 获取所有的角色信息
	 * @date 2017年12月16日
	 * @author shilvfei
	 * @param page
	 * @param limit
	 * @return
	 */
	DataGridResult getInformation(Integer page,Integer limit);

	
	List<TbRole> getRoles(@Param(value = "map") Map<String, Object> form);
	
	/**
	 * @description 新增角色
	 * @date 2017年12月8日
	 * @author shilvfei
	 * @param role
	 * @return
	 */
	LogisticsResult insertRole(TbRole role);

	/**
	 * @description 获取某一角色信息
	 * @date 2017年12月9日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	LogisticsResult selectRoleByRoleID(Integer roleId);

	/**
	 * @description 删除角色信息
	 * @date 2017年12月9日
	 * @author shilvfei
	 * @param idArray
	 * @return
	 */
	LogisticsResult delRole(List<Integer> ids);

	/**
	 * @description 
	 * @date 2017年12月11日
	 * @author shilvfei
	 * @param deleteFlagFalse
	 * @return
	 */
	List<TbRole> selectTbRole(Byte deleteFlagFalse);

	/**
	 * @description 更新角色信息
	 * @date 2017年12月15日
	 * @author shilvfei
	 * @param ids
	 * @param roleName
	 * @param roleId
	 * @return
	 */
	LogisticsResult updateRole(Integer[] ids, String roleName, Integer roleId);

	LogisticsResult selectMenus();

	LogisticsResult getRolesByRoleId(Integer roleId);

}
