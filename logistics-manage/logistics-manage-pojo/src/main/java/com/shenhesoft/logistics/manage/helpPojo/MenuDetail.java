package com.shenhesoft.logistics.manage.helpPojo;

import java.io.Serializable;
import java.util.List;

import com.shenhesoft.logistics.manage.pojo.menu.TbMenu;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月9日
 */

public class MenuDetail  extends TbMenu implements Serializable {
  
	private Byte range;//分类 财务 业务 设置
	private List<MenuDetail>  menus;
	
	public Byte getRange() {
		return range;
	}
	public void setRange(Byte range) {
		this.range = range;
	}
	public List<MenuDetail> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuDetail> menus) {
		this.menus = menus;
	}
	
}
