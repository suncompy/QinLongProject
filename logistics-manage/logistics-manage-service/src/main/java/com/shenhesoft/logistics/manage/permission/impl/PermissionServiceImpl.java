package com.shenhesoft.logistics.manage.permission.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.manage.helpPojo.PermissionInformation;
import com.shenhesoft.logistics.manage.mapper.TbPermissionMapper;
import com.shenhesoft.logistics.manage.permission.PermissionService;
import com.shenhesoft.logistics.manage.pojo.permission.TbPermission;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月7日
 */
@Service
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private TbPermissionMapper permissionMapper;
	
	@Override
	public List<PermissionInformation> getAllPermission() {
		 List<PermissionInformation> resultList = new ArrayList<>();
		//业务
		PermissionInformation permissionInformation1 = new PermissionInformation();
		List<TbPermission> list = permissionMapper.selectPermissionByRange(Constants.BUSINESS_PERMISSION);
		permissionInformation1.setName("业务");
		permissionInformation1.setPermissions(list);
		resultList.add(permissionInformation1);
		//财务
		PermissionInformation permissionInformation2 = new PermissionInformation();
		List<TbPermission> list2 = permissionMapper.selectPermissionByRange(Constants.FINANCE_PERMISSION);
		permissionInformation2.setName("财务");
		permissionInformation2.setPermissions(list2);
		resultList.add(permissionInformation2);
		//设置
		PermissionInformation permissionInformation3 = new PermissionInformation();
		List<TbPermission> list3 = permissionMapper.selectPermissionByRange(Constants.SET_PERMISSION);
		permissionInformation3.setName("设置");
		permissionInformation3.setPermissions(list3);
		resultList.add(permissionInformation3);
		return resultList;
	}

}
