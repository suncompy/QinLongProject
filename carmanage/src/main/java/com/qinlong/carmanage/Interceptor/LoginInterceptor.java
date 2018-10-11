package com.qinlong.carmanage.Interceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class LoginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		Controller con = inv.getController();
		String username = con.getSessionAttr("username");
		if(username!=null && !"".equals(username)){
			inv.invoke();
		}else{
			con.redirect("tologin");
		}
	}

}
