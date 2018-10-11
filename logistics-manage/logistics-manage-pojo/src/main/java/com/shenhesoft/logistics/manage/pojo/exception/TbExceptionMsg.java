package com.shenhesoft.logistics.manage.pojo.exception;

import java.util.Date;

import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

public class TbExceptionMsg {
    /** 主键*/
    private Integer exceptionId;

    /** 项目id*/
    private Integer projectId;

    /** 运单id*/
    private Integer orderId;

    /** 火运运单id*/
    private Integer trainOrderId;

    /** 短驳还是火运 0-短驳 1-火运*/
    private Byte shortTrainFlag;

    /** 运单状态(阶段)*/
    private Byte orderStatus;

    /** 异常原因id*/
    private Integer exceptionReasonId;

    /** 异常原因*/
    private String exceptionReason;

    /** 异常原因详细(其他)*/
    private String exceptionReasonDetail;

    /** 异常来源 0-自动 1-手动*/
    private Byte exceptionSource;

    /** 提交人*/
    private Integer submitUserId;

    /** 提交时间*/
    private Date submitDate;

    /** 确认状态 0-未确认 1-确认通过 2-确认驳回*/
    private Byte affirmStatus;

    /** 确认人*/
    private Integer affirmUserId;

    /** 确认时间*/
    private Date affirmDate;

    /** 是否解决 0 未解决 1解决*/
    private Byte resolveStatus;

    /** 解决时间*/
    private Date resolveDate;

    /** 解决人*/
    private Integer resolveUserId;

    /** 是否删除 0 未删除 1 删除*/
    private Byte deleteFlag;

    /** 删除人*/
    private Integer deleteUserId;
    
    /**
     * 提报人 对象
     */
    private TbSystemUser submitUser;
    /**
     * 操作人  对象
     */
    private TbSystemUser affirmUser;
    

    public Integer getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTrainOrderId() {
        return trainOrderId;
    }

    public void setTrainOrderId(Integer trainOrderId) {
        this.trainOrderId = trainOrderId;
    }

    public Byte getShortTrainFlag() {
        return shortTrainFlag;
    }

    public void setShortTrainFlag(Byte shortTrainFlag) {
        this.shortTrainFlag = shortTrainFlag;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getExceptionReasonId() {
        return exceptionReasonId;
    }

    public void setExceptionReasonId(Integer exceptionReasonId) {
        this.exceptionReasonId = exceptionReasonId;
    }

    public String getExceptionReason() {
        return exceptionReason;
    }

    public void setExceptionReason(String exceptionReason) {
        this.exceptionReason = exceptionReason == null ? null : exceptionReason.trim();
    }

    public String getExceptionReasonDetail() {
        return exceptionReasonDetail;
    }

    public void setExceptionReasonDetail(String exceptionReasonDetail) {
        this.exceptionReasonDetail = exceptionReasonDetail == null ? null : exceptionReasonDetail.trim();
    }

    public Byte getExceptionSource() {
        return exceptionSource;
    }

    public void setExceptionSource(Byte exceptionSource) {
        this.exceptionSource = exceptionSource;
    }

    public Integer getSubmitUserId() {
        return submitUserId;
    }

    public void setSubmitUserId(Integer submitUserId) {
        this.submitUserId = submitUserId;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Byte getAffirmStatus() {
        return affirmStatus;
    }

    public void setAffirmStatus(Byte affirmStatus) {
        this.affirmStatus = affirmStatus;
    }

    public Integer getAffirmUserId() {
        return affirmUserId;
    }

    public void setAffirmUserId(Integer affirmUserId) {
        this.affirmUserId = affirmUserId;
    }

    public Date getAffirmDate() {
        return affirmDate;
    }

    public void setAffirmDate(Date affirmDate) {
        this.affirmDate = affirmDate;
    }

    public Byte getResolveStatus() {
        return resolveStatus;
    }

    public void setResolveStatus(Byte resolveStatus) {
        this.resolveStatus = resolveStatus;
    }

    public Date getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Date resolveDate) {
        this.resolveDate = resolveDate;
    }

    public Integer getResolveUserId() {
        return resolveUserId;
    }

    public void setResolveUserId(Integer resolveUserId) {
        this.resolveUserId = resolveUserId;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(Integer deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

	public TbSystemUser getSubmitUser() {
		return submitUser;
	}

	public void setSubmitUser(TbSystemUser submitUser) {
		this.submitUser = submitUser;
	}

	public TbSystemUser getAffirmUser() {
		return affirmUser;
	}

	public void setAffirmUser(TbSystemUser affirmUser) {
		this.affirmUser = affirmUser;
	}
}