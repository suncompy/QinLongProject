package com.shenhesoft.logistics.manage.index;

import java.util.List;

import com.shenhesoft.logistics.manage.helpPojo.MenuDetail;
import com.shenhesoft.logistics.manage.pojo.menu.TbMenu;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月9日
 */
public interface IndexService {

	/**
	 * 获取用户所有的权限list
	 * @author dusd
	 * @date 2017年12月18日
	 * @param userId 用户id
	 * @return
	 */
	List<TbMenu> listMenuDetailByUserId(Integer userId);

	/**
	 * 重组用户当前的权限 拼接成菜单信息
	 * @author dusd
	 * @date 2017年12月18日
	 * @param menuDetailList
	 * @return
	 */
	List<MenuDetail> getFirstMenuDetailList(List<TbMenu> menuDetailList);

}
