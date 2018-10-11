package com.shenhesoft.logistics.business.pojo.shortBarge;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TbShortBarge implements Serializable{
	 /** */
    private Integer id;

    /** 项目id*/
    private Integer projectId;

    /** 短驳承运方*/
    private String shortBargeName;

    /** 短驳承运方id*/
    private Integer shortBargeId;

    /** 创建时间*/
    private Date createDate;

    /** 运输单价*/
    private BigDecimal transportPrice;

    /** 扣损比率*/
    private Float deductionRate;

    /** 扣损单价*/
    private BigDecimal deductionPrice;

    /** 付款周期， 0日结/ 1周结/ 2月结
*/
    private Integer payment;

    /** 短驳承运方式类型
            0：接取
            1：送达 2:汽运*/
    private Byte type;

    /** 0 平台 1 自选*/
    private Byte shortBargeType;

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

    public String getShortBargeName() {
        return shortBargeName;
    }

    public void setShortBargeName(String shortBargeName) {
        this.shortBargeName = shortBargeName == null ? null : shortBargeName.trim();
    }

    public Integer getShortBargeId() {
        return shortBargeId;
    }

    public void setShortBargeId(Integer shortBargeId) {
        this.shortBargeId = shortBargeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getTransportPrice() {
        return transportPrice;
    }

    public void setTransportPrice(BigDecimal transportPrice) {
        this.transportPrice = transportPrice;
    }

    public Float getDeductionRate() {
        return deductionRate;
    }

    public void setDeductionRate(Float deductionRate) {
        this.deductionRate = deductionRate;
    }

    public BigDecimal getDeductionPrice() {
        return deductionPrice;
    }

    public void setDeductionPrice(BigDecimal deductionPrice) {
        this.deductionPrice = deductionPrice;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getShortBargeType() {
        return shortBargeType;
    }

    public void setShortBargeType(Byte shortBargeType) {
        this.shortBargeType = shortBargeType;
    }
}