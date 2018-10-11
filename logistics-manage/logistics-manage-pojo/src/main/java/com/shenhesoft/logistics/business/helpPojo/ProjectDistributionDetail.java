package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.util.Date;

import com.shenhesoft.logistics.manage.pojo.project.TbProject;

/**
 * @description 任務列表
 * 
 * @author shilvfei
 * 
 * @date 2018年1月17日
 */
public class ProjectDistributionDetail extends TbProject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7772755627290031998L;
	
	private Integer sendCompanyId;
	
	private String sendCompanyName ;
	
	private String sendCompanyNameAddress;
	
	private Integer receiptCompanyId; 
	
	private String receiptCompanyName; 
	
	private String receiptCompanyAddress;
	
	/**
	 * 1 接取 2 送达 3 汽运
	 */
	private Byte taskType;//任務 type
	
	/** 已经被的领取分配数 */
	private Integer alreadyRecNum;
	
	/**
	 * 待领数量
	 */
	private Integer waitRecNum;
	
	/**
	 * 今日完成数量
	 */
	private Integer completeTodayNum;
	
	/**
	 * 累积完成任务数
	 */
	private Integer completeNumSum;
	
	private String beginDate;
	
	private String endDate;
	
	private String sysOrgCode;
	
	public String getSysOrgCode() {
		return sysOrgCode;
	}
	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
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
	public Integer getCompleteNumSum() {
		return completeNumSum;
	}
	public void setCompleteNumSum(Integer completeNumSum) {
		this.completeNumSum = completeNumSum;
	}
	public Integer getAlreadyRecNum() {
		return alreadyRecNum;
	}
	public void setAlreadyRecNum(Integer alreadyRecNum) {
		this.alreadyRecNum = alreadyRecNum;
	}
	public Integer getWaitRecNum() {
		return waitRecNum;
	}
	public void setWaitRecNum(Integer waitRecNum) {
		this.waitRecNum = waitRecNum;
	}
	public Integer getCompleteTodayNum() {
		return completeTodayNum;
	}
	public void setCompleteTodayNum(Integer completeTodayNum) {
		this.completeTodayNum = completeTodayNum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSendCompanyId() {
		return sendCompanyId;
	}
	public void setSendCompanyId(Integer sendCompanyId) {
		this.sendCompanyId = sendCompanyId;
	}
	public String getSendCompanyName() {
		return sendCompanyName;
	}
	public void setSendCompanyName(String sendCompanyName) {
		this.sendCompanyName = sendCompanyName;
	}
	public Integer getReceiptCompanyId() {
		return receiptCompanyId;
	}
	public void setReceiptCompanyId(Integer receiptCompanyId) {
		this.receiptCompanyId = receiptCompanyId;
	}
	public String getReceiptCompanyName() {
		return receiptCompanyName;
	}
	public void setReceiptCompanyName(String receiptCompanyName) {
		this.receiptCompanyName = receiptCompanyName;
	}
	public Byte getTaskType() {
		return taskType;
	}
	public void setTaskType(Byte taskType) {
		this.taskType = taskType;
	}
	public String getSendCompanyNameAddress() {
		return sendCompanyNameAddress;
	}
	public void setSendCompanyNameAddress(String sendCompanyNameAddress) {
		this.sendCompanyNameAddress = sendCompanyNameAddress;
	}
	public String getReceiptCompanyAddress() {
		return receiptCompanyAddress;
	}
	public void setReceiptCompanyAddress(String receiptCompanyAddress) {
		this.receiptCompanyAddress = receiptCompanyAddress;
	}
}
