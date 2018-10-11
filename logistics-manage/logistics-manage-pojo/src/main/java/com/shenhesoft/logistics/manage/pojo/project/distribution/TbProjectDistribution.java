package com.shenhesoft.logistics.manage.pojo.project.distribution;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TbProjectDistribution implements Serializable {
	/** */
    private Integer id;

    /** 项目id*/
    private Integer projectId;

    /** 项目编号*/
    private String projectCode;

    /** 项目类型*/
    private Byte projectType;

    /** 联运模式*/
    private Byte transportType;

    /** 发货单位id*/
    private Integer sendCompanyId;

    /** 收货单位id*/
    private Integer receiveCompanyId;

    /** 每日车数*/
    private Integer carNum;

    /** 单车重量*/
    private BigDecimal singleWeight;

    /** 创建人id*/
    private Integer creatorId;

    /** 创建时间*/
    private Date createDate;
    
    /** 过期时间*/
    private Date expireDate;
    
    /** 1 开始 2 暂停 */
    private Byte status;

    /** 1:接取  2:送达 3:汽运*/
    private Byte type;

    /** 已经被的领取分配数*/
    private Integer alreadyRecNum;

    /** 完成任务数*/
    private Integer completeNum;

    /** 逻辑删除  1删除*/
    private Byte deleteFlag;

    /** 是否已结束分配 0-未结束 1-已结束*/
    private Byte overFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public Byte getProjectType() {
        return projectType;
    }

    public void setProjectType(Byte projectType) {
        this.projectType = projectType;
    }

    public Byte getTransportType() {
        return transportType;
    }

    public void setTransportType(Byte transportType) {
        this.transportType = transportType;
    }

    public Integer getSendCompanyId() {
        return sendCompanyId;
    }

    public void setSendCompanyId(Integer sendCompanyId) {
        this.sendCompanyId = sendCompanyId;
    }

    public Integer getReceiveCompanyId() {
        return receiveCompanyId;
    }

    public void setReceiveCompanyId(Integer receiveCompanyId) {
        this.receiveCompanyId = receiveCompanyId;
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public BigDecimal getSingleWeight() {
        return singleWeight;
    }

    public void setSingleWeight(BigDecimal singleWeight) {
        this.singleWeight = singleWeight;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getAlreadyRecNum() {
        return alreadyRecNum;
    }

    public void setAlreadyRecNum(Integer alreadyRecNum) {
        this.alreadyRecNum = alreadyRecNum;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Byte getOverFlag() {
        return overFlag;
    }

    public void setOverFlag(Byte overFlag) {
        this.overFlag = overFlag;
    }

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
}
