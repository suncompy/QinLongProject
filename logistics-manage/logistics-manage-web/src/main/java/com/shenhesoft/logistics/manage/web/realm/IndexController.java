package com.shenhesoft.logistics.manage.web.realm;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.manage.helpPojo.MenuDetail;
import com.shenhesoft.logistics.manage.index.IndexService;
import com.shenhesoft.logistics.manage.pojo.menu.TbMenu;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description:登陆成功处理
 * 
 * @author shilvfei
 * 
 * @date 2017年12月12日
 */
@Controller
public class IndexController {
	
	@Autowired
	private IndexService indexService;
	
    @RequestMapping(value = "/html/index")//首页
    public String homePage(Model model){
    	TbSystemUser user = AppSession.getCurrentUser();
    	//获取用户所有的权限list
    	List<TbMenu> menuDetailList = indexService.listMenuDetailByUserId(user.getId());
    	
    	List<String> codes = new ArrayList<>();
    	for (TbMenu tbMenu : menuDetailList) {
    		codes.add(tbMenu.getCode());
		}
    	//一级菜单信息
    	List<MenuDetail> firstMenuDetailList = indexService.getFirstMenuDetailList(menuDetailList);
    	model.addAttribute("firstMenuDetailList", firstMenuDetailList);
    	model.addAttribute("userName", user.getName());
    	AppSession.setAttribute("permissions", menuDetailList);
    	AppSession.setAttribute("permissionCodes", codes);
        return "/html/index";
    }
    

}
