package com.shenhesoft.logistics.common.session;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shenhesoft.logistics.manage.pojo.menu.TbMenu;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 系统全局属性及用户全局属性操作.
 *
 * @author LiuJiefeng
 */
public class AppSession {
	private static final String SESSION_USER = "systemUser";

	private static final String SESSION_PERMISSION ="permissions";
	
	private static final String PERMISSON_CODE = "permissionCodes";
	
	/**
	 * 获取当前操作用户.
	 * 
	 * @return
	 * @throws SubjectNotFountException
	 */
	public static final TbSystemUser getCurrentUser() {
		TbSystemUser user = null;
		try{
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			user = (TbSystemUser) request.getSession().getAttribute(SESSION_USER);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 获取当前操作用户id.
	 * 
	 * @return
	 * @throws SubjectNotFountException
	 */
	public static final Integer getCurrentUserId() {
		TbSystemUser user = getCurrentUser();
		return null!=user?user.getId():null;
	}
	
	/**
     * 获取当前操作用户的系统机构编码.
     * 
     * @return
     * @throws SubjectNotFountException
     */
    public static final String getCurrentSysOrgCode() {
        TbSystemUser user = getCurrentUser();
        return null!=user?user.getSysOrgCode():null;
    }
	
	/**
	 * 注销当前操作用户.
	 * 
	 */
	public static final void logoutCurrentUser() {
		removeAttribute(SESSION_USER);
	}
	
	/**
	 * 获取当前用户的所有权限
	 * */
	public static final List<TbMenu> getUserPermissions() {
		List<TbMenu> permissions = null;
		try{
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			permissions = (List<TbMenu>) request.getSession().getAttribute(SESSION_PERMISSION);
		}catch(Exception e){
			e.printStackTrace();
		}
		return permissions;
	}
	
	/**
	 * 获取当前用户的所有权限Code
	 * */
	public static final List<String> getUserPermissionCodes() {
		List<String> permissionCodes = null;
		try{
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			permissionCodes = (List<String>) request.getSession().getAttribute(PERMISSON_CODE);
		}catch(Exception e){
			e.printStackTrace();
		}
		return permissionCodes;
	}
	
	
	public static final HttpServletRequest getHttpServletRequest() {
      try{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
      }catch(Exception e){
        e.printStackTrace();
      }
      return null;
    }
	public static final String getBasePath() {
      try{
        HttpServletRequest request = getHttpServletRequest();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        return basePath;
      }catch(Exception e){
        e.printStackTrace();
      }
      return null;
	}
	public static final String getAttribute(String key) {
		Object o =getRequest().getSession().getAttribute(key);
		return null==o?null:o.toString();
	}	
	public static final void setAttribute(String key,Object value) {
		getRequest().getSession().setAttribute(key,value);
	}
	public static final void removeAttribute(String key) {
		getRequest().getSession().removeAttribute(key);
		if(null != getAttribute(key)){
		  getRequest().getSession().invalidate();
		}
	}
	private static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
}
