package com.qinlong.carmanage.service;


import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.qinlong.carmanage.common.model.TbLoginUser;
import com.qinlong.carmanage.common.util.ResultSetUtils;

public class CarUserService {
	
	//根据传入的user信息获取user对象（验证用户是否存在）
	public int getUserByUserInfo(TbLoginUser user){
		int i = 0;
		String usersql = "select count(*) from tb_login_user where 1=1 ";
		String userdata = "";
		if (user.getUsername()!=null && !"".equals(user.getUsername())) {
			usersql += " and username = ? ";
			userdata = user.getUsername();
		}else if (user.getUserIdcard()!=null && !"".equals(user.getUserIdcard())) {
			usersql += " and user_idcard = ? ";
			userdata = user.getUserIdcard();
		}
		else if (user.getMobilePhone()!=null && !"".equals(user.getMobilePhone())) {
			usersql += " and mobile_phone = ? ";
			userdata = user.getMobilePhone();
		}
		i = Db.queryInt(usersql, userdata);
		return i;
	}
	
	
	//用户注册，保存用户信息
	public ResultSetUtils saveUser(TbLoginUser user){
		ResultSetUtils result = new ResultSetUtils();
		boolean bool = false;
		bool = user.save();
		if (bool) {
			result.setState("1");
			result.setMessage("save user access");
			result.setBool(bool);
		}else{
			result.setState("2");
			result.setMessage("save user fail");
			result.setBool(bool);
		}
		return result;
	}
	
	//用户登录验证
	public ResultSetUtils checkUserLogin(TbLoginUser user){
		ResultSetUtils result = new ResultSetUtils();
		String loginstr = "select * from tb_login_user where 1=1 and (username = ? or mobile_phone = ?) and passwd = ?";
		List<TbLoginUser> userlist = user.find(loginstr, user.getUsername(),user.getUsername(),user.getPasswd());
		if (userlist.size() < 1) {
			result.setBool(false);
			result.setState("-1");
			result.setMessage("用户名或密码有误！");
		}else if(userlist.size() == 1){
			result.setBool(true);
			result.setState("1");
			result.setList(userlist);
			result.setMessage("登录成功！");
		}else{
			result.setBool(false);
			result.setState("-2");
			result.setMessage("用户数据有误，请联系管理员！");
		}
		return result;
	}
}
