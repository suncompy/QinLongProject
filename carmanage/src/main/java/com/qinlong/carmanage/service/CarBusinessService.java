package com.qinlong.carmanage.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.qinlong.carmanage.common.model.TbAnchoreRecord;
import com.qinlong.carmanage.common.model.TbAnchoredCompany;
import com.qinlong.carmanage.common.model.TbCarTeam;
import com.qinlong.carmanage.common.model.TbLoginUser;
import com.qinlong.carmanage.common.util.CommonUtils;
import com.qinlong.carmanage.common.util.ResultSetUtils;

public class CarBusinessService {

	//可以申请需要挂靠的公司
	public List<Record> getCompany(TbLoginUser tbuser){
		List<Record> list  = new ArrayList<Record>();
		String companysql = " SELECT com.id,com.name,com.phone,com.branch_id,re.anchored_id,re.anchored_name,"
				+ "re.user_id,re.user_name,re.status,re.type,re.delete_flag FROM (SELECT * FROM tb_anchored_company WHERE STATUS = '0') com "
				+ "LEFT JOIN (SELECT record.* FROM tb_login_user users,tb_anchore_record record WHERE record.user_id = users.id AND users.mobile_phone = ? ) re "
				+ "ON com.id = re.anchored_id AND re.delete_flag = 0 ";
		list=Db.find(companysql,tbuser.getMobilePhone());
		return list;
	}
	
	//可以申请需要挂靠的车队
	public List<Record> getCarTeam(TbLoginUser tbuser){
		List<Record> list  = new ArrayList<Record>();
		String companysql = " SELECT car.id,car.name,car.phone,car.car_item_name,re.anchored_id,re.anchored_name, "
				+ "re.user_id,re.user_name,re.status,re.type,re.delete_flag FROM ( SELECT * FROM tb_car_team ) car "
				+ "LEFT JOIN (SELECT record.* FROM tb_login_user users,tb_anchore_record record WHERE record.user_id = users.id AND users.mobile_phone = ? ) re "
				+ "ON car.id = re.anchored_id AND re.delete_flag = 0 ";
		list=Db.find(companysql,tbuser.getMobilePhone());
		return list;
	}
	
	//获取当前用户挂靠中的公司
	public List<Record> getCompanyByAffiliated(TbLoginUser tbuser){
		List<Record> list  = new ArrayList<Record>();
		String sqlstr = "SELECT record.* FROM tb_anchore_record record,tb_login_user users WHERE record.user_id = users.id AND "
				+ "record.`delete_flag` = 0 AND users.`mobile_phone` = ?";
		list=Db.find(sqlstr,tbuser.getMobilePhone());
		return list;
	}
	
	//取消挂靠
	public int delInfoByAffiliated(Integer id){
		int i = 0;
		i=Db.update("update tb_anchore_record set delete_flag= ?,anchored_date = ?,status = ? where id= ?", 1,CommonUtils.getNowDate(), 0 ,id);
		return i;
	}
	
	//获取历史挂靠信息
	public List<Record> getAffiliatedInfoByHistory(TbLoginUser tbuser){
		List<Record> list  = new ArrayList<Record>();
		String sqlstr = "SELECT record.* FROM tb_login_user users, tb_anchore_record record WHERE record.user_id = users.id AND "
				+ "users.mobile_phone = ? AND record.`delete_flag` = 1 ORDER BY record.`anchored_date` DESC";
		list=Db.find(sqlstr,tbuser.getMobilePhone());
		return list;
	}
	
	//判断车辆是否已经在挂靠中
	public ResultSetUtils getCntByAffiliated(TbLoginUser tbuser,TbAnchoredCompany com){
		ResultSetUtils rset = new ResultSetUtils();
		TbAnchoreRecord tbar = new TbAnchoreRecord();
		int i = 0;
		String Affiliatedsql = "SELECT COUNT(*) FROM tb_login_user users, tb_anchore_record record "
				+ "WHERE record.user_id = users.id AND users.mobile_phone = ? AND delete_flag = 0 ";
		i = Db.queryInt(Affiliatedsql,tbuser.getMobilePhone());
		if (i>0) {
			rset.setBool(false);
			rset.setState("-1");
			rset.setMessage("已在挂靠队列中！");
		}else{
			if(tbuser!=null && com != null){
				tbar.setAnchoredId(com.getId());
				tbar.setAnchoredName(com.getName());
				tbar.setAnchoredPhone(com.getPhone());
				tbar.setAnchoredDate(CommonUtils.getNowDate());
				tbar.setUserId(tbuser.getId());
				tbar.setUserName(tbuser.getUsername());
				tbar.setStatus(3);
				tbar.setType("2");
				tbar.setOperator(tbuser.getId());
			}
			if (tbar!=null && !"".equals(tbar)) {
				tbar.save();
			}
			rset.setBool(true);
			rset.setState("0");
			rset.setMessage("挂靠成功！");
		}
		return rset;
	}
}
