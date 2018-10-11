package com.shenhesoft.logistics.manage.pojo.anchord;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @description 挂靠记录
 * @author shilvfei
 * @date 2017年11月23日
 */
public class TbAnchoreRecord implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -146103427377727819L;

	private Integer id;

	private Integer anchoredId;

	private String anchoredName;

	private String anchoredPhone;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date anchoredDate;

	private Integer userId;

	private String userName;

	private Byte status;

	private String anchoredReason;

	// 1：个人挂车队
	// 2：个人挂公司
	// 3：车队挂公司
	private Byte type;

	private Byte deleteFlag;
	
	//操作员
	private Integer operator;

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Byte getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnchoredId() {
		return anchoredId;
	}

	public void setAnchoredId(Integer anchoredId) {
		this.anchoredId = anchoredId;
	}

	public String getAnchoredName() {
		return anchoredName;
	}

	public void setAnchoredName(String anchoredName) {
		this.anchoredName = anchoredName == null ? null : anchoredName.trim();
	}

	public Date getAnchoredDate() {
		return anchoredDate;
	}

	public void setAnchoredDate(Date anchoredDate) {
		this.anchoredDate = anchoredDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getAnchoredReason() {
		return anchoredReason;
	}

	public void setAnchoredReason(String anchoredReason) {
		this.anchoredReason = anchoredReason == null ? null : anchoredReason.trim();
	}

	public String getAnchoredPhone() {
		return anchoredPhone;
	}

	public void setAnchoredPhone(String anchoredPhone) {
		this.anchoredPhone = anchoredPhone;
	}

}