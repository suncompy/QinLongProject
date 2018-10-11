package com.shenhesoft.logistics.manage.pojo.tbSmsSend;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TbSmsSend implements Serializable{
    /**
	 * serialVersionUID
	*/
	private static final long serialVersionUID = 8963768264208737458L;

	/** 主键标识*/
    private Integer id;

    /** 发送时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date sendTime;

    /** 接收手机号*/
    private String recivePhone;

    /** 短信内容*/
    private String msg;

    /** 接收人*/
    private Integer reciveUserId;

    /** 接收人姓名*/
    private String reciveUserName;

    /** 订单编号*/
    private String orderCode;

    /** 订单标识*/
    private Integer orderId;

    /** 短信状态0已发送1发送失败*/
    private Integer msgStatus;

    /** 操作人姓名*/
    private String optUserName;

    /** 操作用户*/
    private Integer optUserId;

    /** 短信签名*/
    private String msgSignName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getRecivePhone() {
        return recivePhone;
    }

    public void setRecivePhone(String recivePhone) {
        this.recivePhone = recivePhone == null ? null : recivePhone.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Integer getReciveUserId() {
        return reciveUserId;
    }

    public void setReciveUserId(Integer reciveUserId) {
        this.reciveUserId = reciveUserId;
    }

    public String getReciveUserName() {
        return reciveUserName;
    }

    public void setReciveUserName(String reciveUserName) {
        this.reciveUserName = reciveUserName == null ? null : reciveUserName.trim();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getOptUserName() {
        return optUserName;
    }

    public void setOptUserName(String optUserName) {
        this.optUserName = optUserName == null ? null : optUserName.trim();
    }

    public Integer getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(Integer optUserId) {
        this.optUserId = optUserId;
    }

    public String getMsgSignName() {
        return msgSignName;
    }

    public void setMsgSignName(String msgSignName) {
        this.msgSignName = msgSignName == null ? null : msgSignName.trim();
    }
}