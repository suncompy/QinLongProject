package com.shenhesoft.logistics.manage.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver{

	private static Logger logger= Logger.getLogger(GlobalExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception e) {
		 try {
			 logger.error("运行时异常", e);
			if (e instanceof UnauthorizedException) {  
				 ModelAndView modelAndView = new ModelAndView();
				modelAndView.setViewName("/error/jsp/unauthorized");
				modelAndView.addObject("message","您当前权限不能访问该功能,请与管理员联系");
				//e1.printStackTrace();
				logger.error("无权访问");
				return modelAndView;
			  }  
			 if (e instanceof AuthorizationException) {  
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.setViewName("/error/jsp/unauthorized");
				modelAndView.addObject("message","您当前权限不能访问该功能,请与管理员联系");
				//e1.printStackTrace();
				logger.error("无权访问");
				return modelAndView;
			  }
		} catch (Exception e1) {
			//写日志 文件 
			logger.error("异常...", e);
			//e1.printStackTrace();
		} 
			
		/*finally {
			modelAndView = new ModelAndView();
			modelAndView.setViewName("/error/jsp/404");
			modelAndView.addObject("message","您当前权限不能访问该功能,请与管理员联系");
			return modelAndView;
			//finally 结构使代码总会执行，而不管有无异常发生
			modelAndView = new ModelAndView();
			modelAndView.setViewName("login");
			//提示错误消息
			modelAndView.addObject("message","您的网络异常,请稍后重试...");
		}*/
		return new ModelAndView("login");
		
	}

}
