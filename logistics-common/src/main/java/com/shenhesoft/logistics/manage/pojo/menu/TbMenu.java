package com.shenhesoft.logistics.manage.pojo.menu;

import java.io.Serializable;

import com.shenhesoft.logistics.common.util.excel.ExcelVOAttribute;

public class TbMenu implements Serializable {
	/** 唯一编号 */
	@ExcelVOAttribute(name = "id", column = "A", isExport = true)
	private Integer id;

	/** 菜单名称(10字符以内) */
	@ExcelVOAttribute(name = "name", column = "B", isExport = true)
	private String name;

	/** 菜单编码(30字符以内) */
	@ExcelVOAttribute(name = "code", column = "C", isExport = true)
	private String code;

	/**
	 * 菜单访问地址
	 */
	@ExcelVOAttribute(name = "url", column = "D", isExport = true)
	private String url;

	
	/** 图标样式(20字符以内) */
	@ExcelVOAttribute(name = "iconClass", column = "E", isExport = true)
	private String iconClass;

	/** 父级编号 */
	@ExcelVOAttribute(name = "parentId", column = "F", isExport = true)
	private Integer parentId;

	/** 删除标识 */
	@ExcelVOAttribute(name = "deleteFlag", column = "G", isExport = true)
	private Byte deleteFlag;

	/**
	 * 菜单级别
	 */
	@ExcelVOAttribute(name = "level", column = "H", isExport = true)
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

	@Override
	public String toString() {
		return "TbMenu [id=" + id + ", name=" + name + ", code=" + code + ", url=" + url + ", iconClass=" + iconClass
				+ ", parentId=" + parentId + ", deleteFlag=" + deleteFlag + ", level=" + level + "]";
	}
	
	
}