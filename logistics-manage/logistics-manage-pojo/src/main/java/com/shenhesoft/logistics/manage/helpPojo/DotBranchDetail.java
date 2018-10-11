package com.shenhesoft.logistics.manage.helpPojo;

import java.io.Serializable;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import com.shenhesoft.logistics.business.helpPojo.AreaHelpPojo;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月14日
 */
public class DotBranchDetail extends TbBranchGroup implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6136713985521759877L;

	private List<TbSystemUser> responsiblers;
	
	private TbBranchGroup branchGroup;
	
	private TbTrainStation station;
	
	private List<AreaHelpPojo> relationBeginAddress;
	
	private Integer ascriptionParentId;
	
	private String province;
	private String city;
	private String district;
	
	 /** 账户*/
    private String userName;

    /** 密码*/
    private String passwd;
	
    /**
     * 手机号
     */
    private String phone;
    
	/**
	 * 员工数
	 */
	private Integer employeeNum;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(Integer employeeNum) {
		this.employeeNum = employeeNum;
	}

	public Integer getAscriptionParentId() {
		return ascriptionParentId;
	}

	public void setAscriptionParentId(Integer ascriptionParentId) {
		this.ascriptionParentId = ascriptionParentId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public TbBranchGroup getBranchGroup() {
		return branchGroup;
	}

	public void setBranchGroup(TbBranchGroup branchGroup) {
		this.branchGroup = branchGroup;
	}

	public List<TbSystemUser> getResponsiblers() {
		return responsiblers;
	}

	public void setResponsiblers(List<TbSystemUser> responsiblers) {
		this.responsiblers = responsiblers;
	}

	public TbTrainStation getStation() {
		return station;
	}

	public void setStation(TbTrainStation station) {
		this.station = station;
	}

	public List<AreaHelpPojo> getRelationBeginAddress() {
		return relationBeginAddress;
	}

	public void setRelationBeginAddress(List<AreaHelpPojo> relationBeginAddress) {
		this.relationBeginAddress = relationBeginAddress;
	}
	
}
