package com.qinlong.carmanage.controller;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.qinlong.carmanage.common.model.TbAnchoredCompany;
import com.qinlong.carmanage.common.model.TbLoginUser;
import com.qinlong.carmanage.common.util.ResultSetUtils;
import com.qinlong.carmanage.service.CarBusinessService;

//@Before(LoginInterceptor.class)
public class CarBusinessController extends Controller {

	private CarBusinessService carbusservice;
	//车辆管理平台首页
	public void carManIndex(){
		renderJsp("driverIndex.jsp");
	}
	
	//车辆挂靠展示界面（affiliated 附属，挂靠）
	public void toAffiliated(){
		carbusservice = new CarBusinessService();
		List<Record> companylist = new ArrayList<Record>();
		List<Record> carteamlist = new ArrayList<Record>();
		List<Record> affcompanylist = new ArrayList<Record>();
		List<Record> affhistorylist = new ArrayList<Record>();
		TbLoginUser tbuser = new TbLoginUser();
		List<TbLoginUser> userlist = getSessionAttr("users");
		if (userlist!=null ) {
			tbuser = userlist.get(0);
		}
		companylist = carbusservice.getCompany(tbuser);
		carteamlist = carbusservice.getCarTeam(tbuser);
		affcompanylist = carbusservice.getCompanyByAffiliated(tbuser);
		affhistorylist = carbusservice.getAffiliatedInfoByHistory(tbuser);
		setAttr("companylist", companylist);
		setAttr("carteamlist", carteamlist);
		setAttr("affcompanylist", affcompanylist);
		setAttr("affhistorylist", affhistorylist);
		renderJsp("anchoredInfo.jsp");
	}
	
	//验证车辆是否已经在挂靠队列中
	public void checkAffiliated(){
		TbLoginUser tbuser = new TbLoginUser();
		TbAnchoredCompany com = new TbAnchoredCompany();
		ResultSetUtils resultset = new ResultSetUtils();
		carbusservice = new CarBusinessService();
		String comid = getPara("comid");
		String comname = getPara("comname");
		String comphone = getPara("comphone");
		if(comid!=null && comname!=null && comphone!=null ){
			com.setId(Integer.parseInt(comid));
			com.setName(comname);
			com.setPhone(comphone);
		}
		List<TbLoginUser> userlist = getSessionAttr("users");
		if (userlist!=null ) {
			tbuser = userlist.get(0);
		}
		resultset = carbusservice.getCntByAffiliated(tbuser,com);
		renderJson("rset", resultset);
	}
	
	//取消挂靠
	public void cancelAffiliated(){
		ResultSetUtils resultset = new ResultSetUtils();
		carbusservice = new CarBusinessService();
		String cancelid = getPara("id");
		int i = 0;
		i = carbusservice.delInfoByAffiliated(Integer.parseInt(cancelid));
		if (i>0) {
			resultset.setBool(true);
			resultset.setState("0");
			resultset.setMessage("取消挂靠成功！");
		}else{
			resultset.setBool(false);
			resultset.setState("-1");
			resultset.setMessage("取消挂靠失败！");
		}
		renderJson("rset", resultset);
	}
}
