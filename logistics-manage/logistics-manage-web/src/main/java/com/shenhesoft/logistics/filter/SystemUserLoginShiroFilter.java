package com.shenhesoft.logistics.filter;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

public class SystemUserLoginShiroFilter extends PathMatchingFilter {

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		
		 HttpServletRequest req = (HttpServletRequest) request;
		 HttpServletResponse resp = (HttpServletResponse) response;
		 TbSystemUser systemUser = (TbSystemUser) req.getSession().getAttribute("systemUser");
        if (null == systemUser && req.getHeader("x-requested-with") != null  
                && req.getHeader("x-requested-with").equalsIgnoreCase(  
                        "XMLHttpRequest")) {
        	resp.setHeader("sessionstatus", "timeout");  
        	resp.setStatus(9999);
            PrintWriter out = resp.getWriter();  
            out.flush();  
            out.close();  
            return false;
        }else if(null == systemUser){
        	String uri = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
			+ req.getContextPath();
        	SecurityUtils.getSubject().logout();
        	resp.sendRedirect(uri + "/login.do"); 
        	return false;
        }  
		return true;
	}

}
