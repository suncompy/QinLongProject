package com.shenhesoft.logistics.manage.pojo.carTeam;

import java.io.Serializable;
import java.util.Date;

public class TbCarTeam implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8887161018650039192L;

	/** 车队负责人id*/
    private Integer id;

    /** 车队负责人名*/
    private String name;

    /** 出生年月*/
    private Date birthday;

    /** 住址*/
    private String areaCode;

    /** 详细地址*/
    private String address;

    /** 性别*/
    private Byte sex;

    /** 手机号*/
    private String phone;

    /** 车队负责人身份证号*/
    private String idcardNumber;

    /** 身份证正面图片*/
    private String idcardPhotoFront;

    /** 身份证反面图片*/
    private String idcardPhotoBack;

    /** 车队名称*/
    private String carItemName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber == null ? null : idcardNumber.trim();
    }

    public String getIdcardPhotoFront() {
        return idcardPhotoFront;
    }

    public void setIdcardPhotoFront(String idcardPhotoFront) {
        this.idcardPhotoFront = idcardPhotoFront == null ? null : idcardPhotoFront.trim();
    }

    public String getIdcardPhotoBack() {
        return idcardPhotoBack;
    }

    public void setIdcardPhotoBack(String idcardPhotoBack) {
        this.idcardPhotoBack = idcardPhotoBack == null ? null : idcardPhotoBack.trim();
    }

    public String getCarItemName() {
        return carItemName;
    }

    public void setCarItemName(String carItemName) {
        this.carItemName = carItemName == null ? null : carItemName.trim();
    }
}