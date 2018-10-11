package com.shenhesoft.logistics.manage.pojo.permission;

import java.io.Serializable;

public class TbPermission implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6854174275323178373L;

	/** */
    private Integer id;

    /** */
    private String name;

    /** 菜单编号*/
    private Integer menuId;

    /** 二级菜单*/
    private Integer menuParentId;

    /** 权限描述*/
    private String description;

    /** 删除标识*/
    private Byte deleteFlag;

    /** 范围 1 业务 2财务 3设置 */
    private Byte range;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Byte getRange() {
        return range;
    }

    public void setRange(Byte range) {
        this.range = range;
    }
}