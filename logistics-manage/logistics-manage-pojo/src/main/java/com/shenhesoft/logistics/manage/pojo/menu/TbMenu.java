package com.shenhesoft.logistics.manage.pojo.menu;

import java.io.Serializable;


public class TbMenu implements Serializable {
	/** 唯一编号 */
	private Integer id;

	/** 菜单名称(10字符以内) */
	private String name;

	/** 菜单编码(30字符以内) */
	private String code;

	/**
	 * 菜单访问地址
	 */
	private String url;

	
	/** 图标样式(20字符以内) */
	private String iconClass;

	/** 父级编号 */
	private Integer parentId;

	/** 删除标识 */
	private Byte deleteFlag;

	/**
	 * 菜单级别
	 */
	private Integer level;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass == null ? null : iconClass.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Byte getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}