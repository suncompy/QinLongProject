package com.shenhesoft.logistics.manage.web.authorityManagement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.collect.Lists;
import com.shenhesoft.logistics.common.exception.SystemException;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.ResultCodeUtils;
import com.shenhesoft.logistics.common.util.ResultContentUtils;
import com.shenhesoft.logistics.common.util.office.ExcelUtil;
import com.shenhesoft.logistics.manage.helpPojo.PermissionInformation;
import com.shenhesoft.logistics.manage.mapper.TbMenuMapper;
import com.shenhesoft.logistics.manage.mapper.TbPermissionMapper;
import com.shenhesoft.logistics.manage.permission.PermissionService;
import com.shenhesoft.logistics.manage.pojo.menu.TbMenu;
import com.shenhesoft.logistics.manage.pojo.menu.TbMenuExample;
import com.shenhesoft.logistics.manage.pojo.permission.TbPermission;
import com.shenhesoft.logistics.manage.pojo.role.TbRole;
import com.shenhesoft.logistics.manage.role.RoleService;

/**
 * @description: 权限管理
 * 
 * @author shilvfei
 * 
 * @date 2017年12月8日
 */
@Controller()
@RequestMapping("/systemManagement")
public class AuthorityManageController {

	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private RoleService roleService;
	
	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${AUTHORITY_PAGE_LIMIT}")
	private Integer AUTHORITY_PAGE_LIMIT;
	
	/**
	 * @description 获取权限
	 * @date 2017年12月9日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping("/authorityManagemen")
	public String getRole(Model model){
		//获取所有的角色信息
		DataGridResult roleList  =  roleService.getInformation(PAGE_NUM,AUTHORITY_PAGE_LIMIT);
		//获取所有的权限信息
		List<PermissionInformation> permissionList  =permissionService.getAllPermission();
		//存储到model
		model.addAttribute("roleList", roleList);
		model.addAttribute("permissionList", permissionList);
		//分发转向
		return "/html/manage/systemManagement/authorityManagemen";
	}
	
	/**
	 * @description 分页获取权限
	 * @date 2017年12月9日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectRole")
	@ResponseBody
	public LogisticsResult selectRole(Integer page){
		//获取所有的角色信息
		DataGridResult roleList  =  roleService.getInformation(page,AUTHORITY_PAGE_LIMIT);
		return LogisticsResult.ok(roleList.getRows());
	}
	
	/**
	 * @description 新增角色 （需要校验是否有权限设置）
	 * @date 2017年12月8日
	 * @author shilvfei
	 * @param ids
	 * @param permissionName
	 * @return
	 */
	@RequiresPermissions(value={"addRole"})
	@RequestMapping(value="/addRole",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getAllPermissions(@RequestParam("ids[]")Integer[] ids,String roleName){
		//判断传过来的值是否为空
		if(ids==null || StringUtils.isBlank(roleName)){
			return LogisticsResult.build(ResultCodeUtils.INSERT_ROLE_EMPTY, ResultContentUtils.INSERT_ROLE_EMPTY);
		}
		List<Integer> idList = Arrays.asList(ids);
		for (int i = 0; i < idList.size(); i++) {
			if(idList.get(i)==null){
				idList.remove(i);
			}
		}
		ids = (Integer[]) idList.toArray();
		TbRole role = new TbRole();
		role.setName(roleName);
		role.setPerssionIds(ids);
		role.setIsDefault(Constants.ROLE_DEFAULT_NO);//是否是默认角色?
		role.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		LogisticsResult result = roleService.insertRole(role);
		return result;
	}
	
	/**
	 * @description 更新角色信息
	 * @date 2017年12月15日
	 * @author shilvfei
	 * @param ids 权限id
	 * @param roleName 角色名
	 * @param roleId 角色id
	 * @return
	 */
	@RequestMapping(value="/updateRole",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult updateRole(@RequestParam("ids[]")Integer[] ids,String roleName,Integer roleId){
		//判断传过来的值是否为空
		if(ids==null || StringUtils.isBlank(roleName) || roleId==null){
			return LogisticsResult.build(ResultCodeUtils.INSERT_EMPTY, ResultContentUtils.INSERT_EMPTY);
		}
		LogisticsResult result = roleService.updateRole(ids,roleName,roleId);
		return result;
	}
	
	/**
	 * @description 查看某一个角色
	 * @date 2017年12月9日
	 * @author shilvfei
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/getRole",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult selectRoleByRoleID(Integer roleId){
		//判断传过来的值是否为空
		if(roleId==null){
			return LogisticsResult.build(ResultCodeUtils.SELECT_ROLE_ERROR, ResultContentUtils.SELECT_ROLE_ERROR);
		}
		LogisticsResult result = roleService.selectRoleByRoleID(roleId);
		return result;
	}
	
	/**
	 * @description 查看某一个角色
	 * @date 2017年12月9日
	 * @author shilvfei
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/getRolesByRoleId",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult getRolesByRoleId(Integer roleId){
		//判断传过来的值是否为空
		if(roleId==null){
			return LogisticsResult.build(ResultCodeUtils.SELECT_ROLE_ERROR, ResultContentUtils.SELECT_ROLE_ERROR);
		}
		LogisticsResult result = roleService.getRolesByRoleId(roleId);
		return result;
	}
	
	/**
	 * @description 删除角色（需要校验是否有权限设置）
	 * @date 2017年12月8日
	 * @author shilvfei
	 * @param ids
	 * @param permissionName
	 * @return
	 */
	@RequiresPermissions(value={"delRole"})
	@RequestMapping(value="/delRole",method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult delRole(@RequestParam("ids[]")List<Integer> ids){
		//判断传过来的值是否为空
		if(ids==null){
			return LogisticsResult.build(ResultCodeUtils.DEL_ROLE_EMPTY, ResultContentUtils.DEL_ROLE_EMPTY);
		}
		/*for (int i = 0; i < ids.size(); i++) {
			if(ids.get(i)==Constants.ADMIN_ROLE || ids.get(i)==Constants.RESPONSIBLER_ROLE){
				ids.remove(i);
			}
		}*/
		if(ids==null || ids.size()==0){
			return LogisticsResult.build(ResultCodeUtils.DEL_ROLE_EMPTY, ResultContentUtils.DEL_ROLE_EMPTY);
		}
		LogisticsResult result = roleService.delRole(ids);
		return result;
	}
	
	/**
	 * @description 分页获取权限
	 * @date 2017年12月9日
	 * @author shilvfei
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectMenus")
	@ResponseBody
	public LogisticsResult selectMenus(){
		//获取所有的角色信息
		return roleService.selectMenus();
	}
	
	/*@Autowired
	private TbMenuMapper menuMapper;
	
	*//**
	 * 项目导出 
	 * @param request 页面表单
	 * @param response 输出流
	 * @return
	 * @throws Exception 
	 *//*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//全部
		TbMenuExample example= new TbMenuExample();
		List<TbMenu> menuList = menuMapper.selectByExample(example);
		
		List<Map<String,Object>> datas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(menuList)) {
			for(TbMenu menu:menuList) {
				datas.add(FormUtil.populate(menu));
			}	
		}
		String[] heads = {"id","name","code","url","icon_class","parent_id","delete_flag","level"};
		String[] headCodes =  {"id","name","code","url","iconClass","parentId","deleteFlag","level"};
		if(ExcelUtil.createExcel(request,response, "菜单列表信息", "菜单列表信息", heads, headCodes, datas)){
			return new ShResponse(HttpStatus.OK.value(), "","下载成功");
		}
		return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
	}*/
	
}
