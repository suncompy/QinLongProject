package com.shenhesoft.logistics.manage.permission;

import java.util.List;

import com.shenhesoft.logistics.manage.helpPojo.PermissionInformation;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月7日
 */
public interface PermissionService {

	/**
	 * @description 获取所有的权限
	 * @date 2017年12月8日
	 * @author shilvfei
	 * @return
	 */
	List<PermissionInformation> getAllPermission();

}
