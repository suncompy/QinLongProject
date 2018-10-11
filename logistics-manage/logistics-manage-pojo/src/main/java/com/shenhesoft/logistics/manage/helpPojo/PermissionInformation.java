package com.shenhesoft.logistics.manage.helpPojo;

import java.io.Serializable;
import java.util.List;

import com.shenhesoft.logistics.manage.pojo.permission.TbPermission;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月8日
 */
public class PermissionInformation implements Serializable{
	
	private String name;
	private List<TbPermission> permissions;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TbPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<TbPermission> permissions) {
		this.permissions = permissions;
	}
	
}
