package com.shenhesoft.logistics.manage.helpPojo;

import java.io.Serializable;
import java.util.List;

import com.shenhesoft.logistics.manage.pojo.permission.TbPermission;
import com.shenhesoft.logistics.manage.pojo.role.TbRole;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月8日
 */
public class RoleInformation extends TbRole implements Serializable{
	
	private List<TbPermission> permissions;
	
	public List<TbPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<TbPermission> permissions) {
		this.permissions = permissions;
	}
	
}
