package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.shenhesoft.logistics.business.pojo.log.TbProjectOperationLog;
import com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBarge;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;

/**
 * @description:项目详情
 * 
 * @author shilvfei
 * 
 * @date 2017年12月22日
 */
public class ProjectDetail extends TbProject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9062833152186882481L;

    /** 送达的 短驳承运方*/
    private String sendShortBargeCarrierName;
	
	/**
	 * 发货企业
	 */
	private CustomerInfo sendCargoCompany;
	/**
	 * 收货企业
	 */
	private CustomerInfo receiveCargoCompany;
	/**
	 * 发货单位
	 */
	private CustomerInfo sendCargoUnit;
	/**
	 * 收货单位
	 */
	private CustomerInfo receivingDepartment;
	
	/**
	 * //收货站点
	 */
	private TbTrainStation receiveTrainStation;
	
	/**
	 * 接取站点
	 */
	private TbTrainStation sendTrainStation;
	
	 /** 项目类型
    0 集装箱
    1 散装*/
	private String projectTypeName;
	
	/** 联运模式
	    0 汽运
	    1 接取
	    2 送达
	    3 火运
	    4 接取+火运
	    5 火运+送达
	    6 联运 
	    7接取+送达*/
	private String transportTypeName;
	
	//计量单位
	private String valuationUnit;
	/**
	 * 删除人
	 */
	private String delUser;
	
	/**
	 * 删除时间
	 */
	private String delDate;
	
	/**
	 * 删除原因
	 */
	private String delReason;
	
	/**
	 * 条件查询 起始时间
	 */
	private String beginDate;
	
	/**
	 * 条件查询 终止时间
	 */
	private String endDate;
	
	private String sendCargoCompanyAddress;
	
	private String receiveCargoCompanyAddress;
	
	//临时字段 汽运单价 
	private BigDecimal truckPrice;
	
	private String beginSiteFreightYardName;
	
	private String endSiteFreightYardName;
	
	private String receiveCenterCargoSiteName;//到达中心站
	
	private TbFreightYard receiveCargoSiteFreightYard;//接取站点 货场
	
	private String forwardingCenterSiteName;//发货中心站

	private TbFreightYard forwardingSiteFreightYard;//接取货场
	
	private String projectFlow;//项目流程
	
	public String getProjectFlow() {
		return projectFlow;
	}
	public void setProjectFlow(String projectFlow) {
		this.projectFlow = projectFlow;
	}
	public String getReceiveCenterCargoSiteName() {
		return receiveCenterCargoSiteName;
	}
	public void setReceiveCenterCargoSiteName(String receiveCenterCargoSiteName) {
		this.receiveCenterCargoSiteName = receiveCenterCargoSiteName;
	}
	public TbFreightYard getReceiveCargoSiteFreightYard() {
		return receiveCargoSiteFreightYard;
	}
	public void setReceiveCargoSiteFreightYard(TbFreightYard receiveCargoSiteFreightYard) {
		this.receiveCargoSiteFreightYard = receiveCargoSiteFreightYard;
	}
	public String getForwardingCenterSiteName() {
		return forwardingCenterSiteName;
	}
	public void setForwardingCenterSiteName(String forwardingCenterSiteName) {
		this.forwardingCenterSiteName = forwardingCenterSiteName;
	}
	public TbFreightYard getForwardingSiteFreightYard() {
		return forwardingSiteFreightYard;
	}
	public void setForwardingSiteFreightYard(TbFreightYard forwardingSiteFreightYard) {
		this.forwardingSiteFreightYard = forwardingSiteFreightYard;
	}
	public String getBeginSiteFreightYardName() {
		return beginSiteFreightYardName;
	}
	public void setBeginSiteFreightYardName(String beginSiteFreightYardName) {
		this.beginSiteFreightYardName = beginSiteFreightYardName;
	}
	public String getEndSiteFreightYardName() {
		return endSiteFreightYardName;
	}
	public void setEndSiteFreightYardName(String endSiteFreightYardName) {
		this.endSiteFreightYardName = endSiteFreightYardName;
	}
	public BigDecimal getTruckPrice() {
		return truckPrice;
	}
	public void setTruckPrice(BigDecimal truckPrice) {
		this.truckPrice = truckPrice;
	}
	public String getValuationUnit() {
		if(this.getValuationUnitName()==0){// 0 件
	    	this.valuationUnit="件";
	    }else if(this.getValuationUnitName()==1){//1 吨
	    	this.valuationUnit="吨";
	    }
		return valuationUnit;
	}
	public void setValuationUnit(String valuationUnit) {
		this.valuationUnit = valuationUnit;
	}
	public String getProjectTypeName() {
	    if(this.getProjectType()==0){//0 集装箱
	    	this.projectTypeName="集装箱";
	    }else if(this.getProjectType()==1){//1 散装
	    	this.projectTypeName="散装";
	    }
		return projectTypeName;
	}
	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}
	public String getTransportTypeName() {
		/** 联运模式
	    */
		if(super.getTransportType()==0){//0 汽运
	    	this.transportTypeName="汽运";
	    }else if(super.getTransportType()==1){//1 接取
	    	this.transportTypeName="接取";
	    }else if(super.getTransportType()==2){//2 送达
	    	this.transportTypeName="送达";
	    }else if(super.getTransportType()==3){//3火运
	    	this.transportTypeName="火运";
	    }else if(super.getTransportType()==4){//4 接取+火运
	    	this.transportTypeName="接取+火运";
	    }else if(super.getTransportType()==5){//5 火运+送达
	    	this.transportTypeName="火运+送达";
	    }else if(super.getTransportType()==6){//6 联运
	    	this.transportTypeName="联运";
	    }else if(super.getTransportType()==7){//7接取+送达
	    	this.transportTypeName="接取+送达";
	    }
		return transportTypeName;
	}
	public void setTransportTypeName(String transportTypeName) {
		this.transportTypeName = transportTypeName;
	}
	public String getDelUser() {
		return delUser;
	}
	public void setDelUser(String delUser) {
		this.delUser = delUser;
	}
	public String getDelDate() {
		return delDate;
	}
	public void setDelDate(String delDate) {
		this.delDate = delDate;
	}
	public String getDelReason() {
		return delReason;
	}
	public void setDelReason(String delReason) {
		this.delReason = delReason;
	}
	public String getSendCargoCompanyAddress() {
		return sendCargoCompanyAddress;
	}
	public void setSendCargoCompanyAddress(String sendCargoCompanyAddress) {
		this.sendCargoCompanyAddress = sendCargoCompanyAddress;
	}
	public String getReceiveCargoCompanyAddress() {
		return receiveCargoCompanyAddress;
	}
	public void setReceiveCargoCompanyAddress(String receiveCargoCompanyAddress) {
		this.receiveCargoCompanyAddress = receiveCargoCompanyAddress;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public TbTrainStation getReceiveTrainStation() {
		return receiveTrainStation;
	}
	public void setReceiveTrainStation(TbTrainStation receiveTrainStation) {
		this.receiveTrainStation = receiveTrainStation;
	}
	public TbTrainStation getSendTrainStation() {
		return sendTrainStation;
	}
	public void setSendTrainStation(TbTrainStation sendTrainStation) {
		this.sendTrainStation = sendTrainStation;
	}
	/**
	 * 短驳承运方
	 */
	private List<TbShortBarge> shortBarges;
	
	public String getSendShortBargeCarrierName() {
		return sendShortBargeCarrierName;
	}
	public void setSendShortBargeCarrierName(String sendShortBargeCarrierName) {
		this.sendShortBargeCarrierName = sendShortBargeCarrierName;
	}
	//删除或更改的操作日志
    private List<TbProjectOperationLog> operationLogs;
    
	public List<TbProjectOperationLog> getOperationLogs() {
		return operationLogs;
	}
	public void setOperationLogs(List<TbProjectOperationLog> operationLogs) {
		this.operationLogs = operationLogs;
	}
	public TbCustomer getSendCargoCompany() {
		return sendCargoCompany;
	}
	public void setSendCargoCompany(CustomerInfo sendCargoCompany) {
		this.sendCargoCompany = sendCargoCompany;
	}
	public TbCustomer getReceiveCargoCompany() {
		return receiveCargoCompany;
	}
	public void setReceiveCargoCompany(CustomerInfo receiveCargoCompany) {
		this.receiveCargoCompany = receiveCargoCompany;
	}
	public TbCustomer getSendCargoUnit() {
		return sendCargoUnit;
	}
	public void setSendCargoUnit(CustomerInfo sendCargoUnit) {
		this.sendCargoUnit = sendCargoUnit;
	}
	public TbCustomer getReceivingDepartment() {
		return receivingDepartment;
	}
	public void setReceivingDepartment(CustomerInfo receivingDepartment) {
		this.receivingDepartment = receivingDepartment;
	}
	public List<TbShortBarge> getShortBarges() {
		return shortBarges;
	}
	public void setShortBarges(List<TbShortBarge> shortBarges) {
		this.shortBarges = shortBarges;
	}
		
}
