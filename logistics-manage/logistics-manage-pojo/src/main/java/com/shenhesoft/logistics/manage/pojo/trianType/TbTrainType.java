package com.shenhesoft.logistics.manage.pojo.trianType;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 火车车型
 */
public class TbTrainType implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2446030853124506466L;

	/** id*/
    private Integer id;

    /** 车种*/
    @NotBlank
    private String trainKind;

    /** 车种代码*/
    @NotBlank
    private String trainKindCode;

    /** 车型代码*/
    @NotBlank
    private String trainTypeCode;

    /** 自重*/
    @NotNull
    private Float selfWeight;

    /** 载重*/
    @NotNull
    private Float weight;

    /** 容积*/
    private Integer volume;

    /** 计费载重*/
    @NotNull
    private Float loadPrice;

    /** 长度*/
    @NotNull
    private String length;

    /** 宽度*/
    @NotBlank
    private String width;

    /** 高度*/
    private String hight;

    /** 备注*/
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrainKind() {
        return trainKind;
    }

    public void setTrainKind(String trainKind) {
        this.trainKind = trainKind == null ? null : trainKind.trim();
    }

    public String getTrainKindCode() {
        return trainKindCode;
    }

    public void setTrainKindCode(String trainKindCode) {
        this.trainKindCode = trainKindCode == null ? null : trainKindCode.trim();
    }

    public String getTrainTypeCode() {
        return trainTypeCode;
    }

    public void setTrainTypeCode(String trainTypeCode) {
        this.trainTypeCode = trainTypeCode == null ? null : trainTypeCode.trim();
    }

    public Float getSelfWeight() {
        return selfWeight;
    }

    public void setSelfWeight(Float selfWeight) {
        this.selfWeight = selfWeight;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Float getLoadPrice() {
        return loadPrice;
    }

    public void setLoadPrice(Float loadPrice) {
        this.loadPrice = loadPrice;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length == null ? null : length.trim();
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width == null ? null : width.trim();
    }

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight == null ? null : hight.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}