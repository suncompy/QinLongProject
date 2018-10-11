package com.shenhesoft.logistics.manage.role.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.ResultCodeUtils;
import com.shenhesoft.logistics.common.util.ResultContentUtils;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.PermissionInformation;
import com.shenhesoft.logistics.manage.helpPojo.RoleInformation;
import com.shenhesoft.logistics.manage.mapper.TbPermissionMapper;
import com.shenhesoft.logistics.manage.mapper.TbRoleMapper;
import com.shenhesoft.logistics.manage.mapper.TbRolePerssionMapper;
import com.shenhesoft.logistics.manage.pojo.permission.TbPermission;
import com.shenhesoft.logistics.manage.pojo.permission.TbRolePerssion;
import com.shenhesoft.logistics.manage.pojo.permission.TbRolePerssionExample;
import com.shenhesoft.logistics.manage.pojo.permission.TbRolePerssionExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.role.TbRole;
import com.shenhesoft.logistics.manage.pojo.role.TbRoleExample;
import com.shenhesoft.logistics.manage.role.RoleService;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月7日
 */
@Service
public class RoleServiceImpl implements RoleService{

	/**
	 * 角色Mapper
	 */
	@Autowired
	private TbRoleMapper roleMapper;
	
	/**
	 * 权限Mapper
	 */
	@Autowired
	private TbPermissionMapper permissionMapper; 
	
	/**
	 *   角色关联权限Mapper
	 */
	@Autowired
	private TbRolePerssionMapper rolePerssionMapper;
	
	/**
	 * 机构关系Mapper
	 */
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	@Override
	public DataGridResult getInformation(Integer page, Integer limit) {
		List<RoleInformation> roleInformations= new ArrayList<RoleInformation>();
		//TbRoleExample example=new TbRoleExample();
		//com.shenhesoft.logistics.manage.pojo.role.TbRoleExample.Criteria criteria = example.createCriteria();
		//criteria.andDeleteFlagEqualTo(Constants.DELETE_FLAG_FALSE);
		PageHelper.startPage(page, limit);
		Map<String, Object> map = new HashMap<>();
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		map.put("deleteFlag", Constants.DELETE_FLAG_FALSE);
		List<TbRole> roles = roleMapper.getRoles(map);
		for (TbRole tbRole : roles) {
			RoleInformation roleInformation = new RoleInformation();
			roleInformation.setId(tbRole.getId());
			roleInformation.setName(tbRole.getName());
			roleInformation.setIsDefault(tbRole.getIsDefault());
			roleInformation.setDeleteFlag(tbRole.getDeleteFlag());
			List<TbPermission> permissions = permissionMapper.selectPermissionByRoleId(tbRole.getId());
			roleInformation.setPermissions(permissions);
			roleInformations.add(roleInformation);
		}
		// 取分页信息
        PageInfo<TbRole> pageInfo = new PageInfo<TbRole>(roles);
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, roleInformations, limit);
	}

	@Override
	public List<TbRole> getRoles(Map<String, Object> form) {
		form.put("deleteFlag", Constants.DELETE_FLAG_FALSE);
		return roleMapper.getRoles(form);
	}

	
	@Override
	public LogisticsResult insertRole(TbRole tbRole) {
		try {
			tbRole.setDeleteFlag(Constants.DELETE_FLAG_FALSE);//是否被删除
			int result = roleMapper.insert(tbRole);
			if(result==0){
				return LogisticsResult.build(ResultCodeUtils.CONNECT_DB_FAIL, ResultContentUtils.CONNECT_DB_FAIL);
			}
			
			BranchGroupLink branchGroupLink = new BranchGroupLink();
		    branchGroupLink.setId(AppUtils.randomUUID());
		    branchGroupLink.setRowId(tbRole.getId().toString());
		    branchGroupLink.setTabName("tb_role");
		    branchGroupLink.setTabComment("角色表");
		    branchGroupLink.setSysOrgCode(tbRole.getSysOrgCode());
		    branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
			
			for (int i = 0; i < tbRole.getPerssionIds().length; i++) {
				TbRolePerssion rolePerssion = new TbRolePerssion();
				rolePerssion.setRoleId(tbRole.getId());
				rolePerssion.setPerssionId(tbRole.getPerssionIds()[i]);
				rolePerssionMapper.insert(rolePerssion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return LogisticsResult.build(ResultCodeUtils.CONNECT_DB_FAIL, ResultContentUtils.CONNECT_DB_FAIL);
			
		}
		return LogisticsResult.ok();
	}

	@Override
	public LogisticsResult selectRoleByRoleID(Integer roleId) {
		/*List<PermissionInformation> resultList = new ArrayList<>();
		//业务
		PermissionInformation permissionInformation1 = new PermissionInformation();
		Map<String, Object> map1= new HashMap<>();
		map1.put("id", id);
		map1.put("range",Constants.BUSINESS_PERMISSION);
		List<TbPermission> list = permissionMapper.selectPermissionByRoleIdAndRange(map1);
		permissionInformation1.setName("business");
		permissionInformation1.setPermissions(list);
		resultList.add(permissionInformation1);
		//财务
		PermissionInformation permissionInformation2 = new PermissionInformation();
		Map<String, Object> map2= new HashMap<>();
		map2.put("id", id);
		map2.put("range",Constants.FINANCE_PERMISSION);
		List<TbPermission> list2 = permissionMapper.selectPermissionByRoleIdAndRange(map2);
		permissionInformation2.setName("finance");
		permissionInformation2.setPermissions(list2);
		resultList.add(permissionInformation2);
		//设置
		PermissionInformation permissionInformation3 = new PermissionInformation();
		Map<String, Object> map3= new HashMap<>();
		map3.put("id", id);
		map3.put("range",Constants.SET_PERMISSION);
		List<TbPermission> list3 = permissionMapper.selectPermissionByRoleIdAndRange(map3);
		permissionInformation3.setName("set");
		permissionInformation3.setPermissions(list3);
		resultList.add(permissionInformation3);*/
		
		return LogisticsResult.ok(roleMapper.selectMenuByRoleId(roleId));
	}

	@Override
	public LogisticsResult delRole(List<Integer> ids) {
		Map<String, Object> map = new HashMap<>();
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		map.put("deleteFlag", Constants.DELETE_FLAG_FALSE);
		map.put("ids",ids);
		//逻辑删除
		try {
			List<TbRole> roles = roleMapper.getRoles(map);
			boolean flag = false;
			for (TbRole tbRole : roles) {
				if(tbRole.getIsDefault()!=Constants.ROLE_DEFAULT_YES){//不是默认角色 可以删除
					tbRole.setDeleteFlag(Constants.DELETE_FLAG_TRUE);
					roleMapper.updateByPrimaryKeySelective(tbRole);
				}else{//是默认角色
					flag=true;
				}
			}
			if(flag){
				return LogisticsResult.build(ResultCodeUtils.DEL_ROLE_ERROR,"删除的角色包含默认角色！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return LogisticsResult.build(ResultCodeUtils.DEL_ROLE_ERROR,ResultContentUtils.DEL_ROLE_ERROR);
		}
		return LogisticsResult.ok();
	}

	@Override
	public List<TbRole> selectTbRole(Byte deleteFlagFalse) {
		TbRoleExample roleExample=new TbRoleExample();
		if(deleteFlagFalse!=null){
			com.shenhesoft.logistics.manage.pojo.role.TbRoleExample.Criteria criteria = roleExample.createCriteria();
			criteria.andDeleteFlagEqualTo(deleteFlagFalse);
		}
		return roleMapper.selectByExample(roleExample);
	}

	@Override
	public LogisticsResult updateRole(Integer[] ids, String roleName, Integer roleId) {
		try {
			TbRole tbRole = roleMapper.selectByPrimaryKey(roleId);//根据id获取角色信息
			tbRole.setName(roleName);
			int result = roleMapper.updateByPrimaryKeySelective(tbRole);
			if(result==0){
				return LogisticsResult.build(ResultCodeUtils.CONNECT_DB_FAIL, ResultContentUtils.CONNECT_DB_FAIL);
			}
			//清空角色权限表信息
			TbRolePerssionExample rolePerssionExample  = new TbRolePerssionExample();
			Criteria criteria = rolePerssionExample.createCriteria();
			criteria.andRoleIdEqualTo(tbRole.getId());
			rolePerssionMapper.deleteByExample(rolePerssionExample);
			
			//去除null
			List<Integer> idList = Arrays.asList(ids);
			for (int i = 0; i < idList.size(); i++) {
				if(idList.get(i)==null){
					idList.remove(i);
				}
			}
			ids = (Integer[]) idList.toArray();
			
			//角色更新权限信息
			for (int i = 0; i < ids.length; i++) {
				System.out.println("开始遍历..................开始插入到数据库");
				TbRolePerssion rolePerssion = new TbRolePerssion();
				rolePerssion.setRoleId(tbRole.getId());
				rolePerssion.setPerssionId(ids[i]);
				rolePerssionMapper.insert(rolePerssion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return LogisticsResult.build(ResultCodeUtils.CONNECT_DB_FAIL, ResultContentUtils.CONNECT_DB_FAIL);
			
		}
		return LogisticsResult.ok();
	}

	@Override
	public LogisticsResult selectMenus() {
		return LogisticsResult.ok(roleMapper.selectMenus());
	}

	@Override
	public LogisticsResult getRolesByRoleId(Integer roleId) {
		List<PermissionInformation> resultList = new ArrayList<>();
		//业务
		PermissionInformation permissionInformation1 = new PermissionInformation();
		Map<String, Object> map1= new HashMap<>();
		map1.put("id", roleId);
		map1.put("range",Constants.BUSINESS_PERMISSION);
		List<TbPermission> list = permissionMapper.selectPermissionByRoleIdAndRange(map1);
		permissionInformation1.setName("business");
		permissionInformation1.setPermissions(list);
		resultList.add(permissionInformation1);
		//财务
		PermissionInformation permissionInformation2 = new PermissionInformation();
		Map<String, Object> map2= new HashMap<>();
		map2.put("id", roleId);
		map2.put("range",Constants.FINANCE_PERMISSION);
		List<TbPermission> list2 = permissionMapper.selectPermissionByRoleIdAndRange(map2);
		permissionInformation2.setName("finance");
		permissionInformation2.setPermissions(list2);
		resultList.add(permissionInformation2);
		//设置
		PermissionInformation permissionInformation3 = new PermissionInformation();
		Map<String, Object> map3= new HashMap<>();
		map3.put("id", roleId);
		map3.put("range",Constants.SET_PERMISSION);
		List<TbPermission> list3 = permissionMapper.selectPermissionByRoleIdAndRange(map3);
		permissionInformation3.setName("set");
		permissionInformation3.setPermissions(list3);
		resultList.add(permissionInformation3);
		return LogisticsResult.ok(resultList);
	}

}
