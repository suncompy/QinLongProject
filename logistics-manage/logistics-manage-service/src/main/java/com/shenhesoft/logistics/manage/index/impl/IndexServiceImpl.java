package com.shenhesoft.logistics.manage.index.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenhesoft.logistics.manage.helpPojo.MenuDetail;
import com.shenhesoft.logistics.manage.index.IndexService;
import com.shenhesoft.logistics.manage.mapper.TbMenuMapper;
import com.shenhesoft.logistics.manage.pojo.menu.TbMenu;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月9日
 */
@Service
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	private TbMenuMapper menuMapper;
	
	@Override
	public List<TbMenu> listMenuDetailByUserId(Integer userId) {
		return menuMapper.listMenuDetailByUserId(userId);
	}

	@Override
	public List<MenuDetail> getFirstMenuDetailList(List<TbMenu> menuDetailList) {
		//加载一级菜单
    	List<MenuDetail> firstMenuDetailList = new ArrayList<>();
    	for (TbMenu tbMenu : menuDetailList) {
			if(tbMenu.getLevel().equals(1)) {
				MenuDetail menuDetail = new MenuDetail();
				tbMenuToMenuDetail(tbMenu, menuDetail);
				firstMenuDetailList.add(menuDetail);
			}
		}
    	//加载二级菜单
    	for (MenuDetail menuDetail : firstMenuDetailList) {
    		//二级菜单list
    		List<MenuDetail> secondMenuDetailList = new ArrayList<>();
    		menuDetail.setMenus(secondMenuDetailList);
			Integer menuId = menuDetail.getId();
			for (TbMenu tbMenu : menuDetailList) {
				if(tbMenu.getLevel() == null || !tbMenu.getLevel().equals(2)) {
					continue;
				}
				Integer tbMenuParentId = tbMenu.getParentId();
				if(menuId.equals(tbMenuParentId)) {//属于当前一级菜单的二级菜单
					MenuDetail secondMenuDetail = new MenuDetail();
					tbMenuToMenuDetail(tbMenu, secondMenuDetail);
					secondMenuDetailList.add(secondMenuDetail);
				}
			}
			//加载三级菜单
			for (MenuDetail secondMenuDetail : secondMenuDetailList) {
				//三级菜单list
	    		List<MenuDetail> thirdMenuDetailList = new ArrayList<>();
	    		secondMenuDetail.setMenus(thirdMenuDetailList);
	    		Integer secondMenuId = secondMenuDetail.getId();
	    		for (TbMenu tbMenu : menuDetailList) {
					if(tbMenu.getLevel() == null || !tbMenu.getLevel().equals(3)) {
						continue;
					}
					Integer tbMenuParentId = tbMenu.getParentId();
					if(secondMenuId.equals(tbMenuParentId)) {//属于当前二级菜单的三级菜单
						MenuDetail thirdMenuDetail = new MenuDetail();
						tbMenuToMenuDetail(tbMenu, thirdMenuDetail);
						thirdMenuDetailList.add(thirdMenuDetail);
					}
				}
			}
		}
		return firstMenuDetailList;
	}
	
    /**
     * TbMenu对象 转成 子类 MenuDetail对象
     * @author dusd
     * @date 2017年12月18日
     * @param tbMenu
     * @param menuDetail
     */
    private void tbMenuToMenuDetail(TbMenu tbMenu,MenuDetail menuDetail) {
    	menuDetail.setId(tbMenu.getId());
		menuDetail.setName(tbMenu.getName());
		menuDetail.setCode(tbMenu.getCode());
		menuDetail.setIconClass(tbMenu.getIconClass());
		menuDetail.setParentId(tbMenu.getParentId());
		menuDetail.setDeleteFlag(tbMenu.getDeleteFlag());
		menuDetail.setLevel(tbMenu.getLevel());
		menuDetail.setUrl(tbMenu.getUrl());
    }

}
