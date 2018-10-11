package com.qinlong.carmanage.controller;

import java.util.Date;

//import org.eclipse.jetty.server.session.JDBCSessionManager.Session;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.qinlong.carmanage.Interceptor.LoginInterceptor;
import com.qinlong.carmanage.common.model.TbLoginUser;
import com.qinlong.carmanage.common.util.CommonUtils;
import com.qinlong.carmanage.common.util.MoblieMessageUtil;
import com.qinlong.carmanage.common.util.ResultSetUtils;
import com.qinlong.carmanage.service.CarUserService;

public class CarUserController extends Controller {
	
	private CarUserService caruserservice;
	//跳转到车辆管理系统的登录界面
	public void tologin(){
		if(getSessionAttr("username")!=null || "".equals(getSessionAttr("username"))){
			removeSessionAttr("username");
		}
		renderJsp("login.jsp");
	}
	
	
	//跳转到车辆管理系统的登录界面
	public void loginOut(){
		if(getSessionAttr("username")!=null || "".equals(getSessionAttr("username"))){
			removeSessionAttr("username");
		}
		renderJsp("login.jsp");
	}
	
	//用户登录
	public void login(){
		ResultSetUtils resultset = new ResultSetUtils();
		caruserservice = new CarUserService();
		TbLoginUser tbuser = new TbLoginUser();
		String username = getPara("username");
		String password = getPara("password");
		setSessionAttr("username", username);
		if("".equals(username) || "".equals(password)){
			resultset.setBool(false);
			resultset.setState("0");
			resultset.setMessage("用户名或密码不能为空！");
		}else{
			tbuser.setUsername(username);
			tbuser.setPasswd(password);
			resultset = caruserservice.checkUserLogin(tbuser);
			if(resultset.getList()!=null && resultset.getList().size()>0){
				setSessionAttr("users", resultset.getList());
			}else{
				setSessionAttr("users", "");
			}
		}
		renderJson("rset", resultset);
	}
	
	//登录后跳转管理页面
	@Before(LoginInterceptor.class)
	public void driverindex(){
		renderJsp("/driver/index.jsp");
	}
	
	//跳转到车队和车辆的注册界面
	public void toregister(){
		renderJsp("register.jsp");
	}
	
	//验证手机号码是否存在
	public void checkUserRepeatData(){
		TbLoginUser tbuser = new TbLoginUser();
		caruserservice = new CarUserService();
		String userdata = getPara("userdata");
		String type = getPara("type");
		if (type.equals("username")) {
			tbuser.setUsername(userdata);
		}else if (type.equals("userIdCard")) {
			tbuser.setUserIdcard(userdata);
		}else if (type.equals("phone")) {
			tbuser.setMobilePhone(userdata);
		}
		int x= caruserservice.getUserByUserInfo(tbuser);
		renderJson("cnt", x);
	}
	
	//发送短信验证码
	public void sendMessage(){
		MoblieMessageUtil mmu = new MoblieMessageUtil();
		int code  = (int)(Math.random()*999999)+100000;
		String messdate = CommonUtils.getStringDate();
		setSessionAttr("mobileMess", code);
		setSessionAttr("mobileMessdate", messdate);
		String mobile = getPara("telephone");
		SendSmsResponse ssr = mmu.sendIdentifyingCode(mobile, code);
		renderJson("ssr", ssr);
	}
	
	//用户注册
	public void register(){
		ResultSetUtils resultset = new ResultSetUtils();
		caruserservice = new CarUserService();
		String begintime = "";
		if(getSessionAttr("mobileMessdate")!=null && !"".equals(getSessionAttr("mobileMessdate"))){
			begintime = getSessionAttr("mobileMessdate").toString();
		}
		String endtime = CommonUtils.getStringDate();
		int second = 0;
		try {
			second = CommonUtils.bewteenStartAndEnd(begintime, endtime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(second<=120){
			String code = getSessionAttr("mobileMess").toString();
			String code1 = getPara("ownerCheckCode");
			if (code.equals(code1)) {
				TbLoginUser tbuser = new TbLoginUser();
//				tbuser.setId(CommonUtils.buildRandomUUID());
				tbuser.setUsername(getPara("username"));
				tbuser.setUserIdcard(getPara("userIdcard"));
				tbuser.setPasswd(getPara("userrepasswd"));
				tbuser.setMobilePhone(getPara("phone"));
				tbuser.setCheckedCode(code1);
				tbuser.setCheckedCodeDate(new Date());
				resultset = caruserservice.saveUser(tbuser);
			}else{
				resultset.setState("2");
				resultset.setMessage("验证码不匹配");
			}
		}else{
			resultset.setState("0");
			resultset.setMessage("验证码超时");
		}
		renderJson("rset", resultset);
	}
	
}
