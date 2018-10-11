package com.shenhesoft.logistics.manage.pojo.stationBusiness;

import java.io.Serializable;

/**
 * @description  业务联系人
 * @author shilvfei
 * @date 2017年12月14日
 * @param 
 * @return
*/
public class TbStationBusiness implements Serializable{
	 /**
	 * serialVersionUID
	*/
	private static final long serialVersionUID = -1109265062303080496L;

	/** */
    private Integer id;

    /** 站点或客户id*/
    private Integer relateId;

    /** 姓名*/
    private String name;

    /** 部门*/
    private String department;

    /** 职务*/
    private String job;

    /** 联系方式*/
    private String phone;

    /** 关联项目id*/
    private Integer relateProjectId;

    /** 关联项目编号*/
    private String relateProjectCode;

    /**区域 */
    private String areaCode;

    /** 位置*/
    private String location;

    /** 阶段 0 接取 1 送达 2 汽运 3 火运*/
    private Byte stage;

    /** 联系人类别
            1:车站业务联系人
            2:客户业务联系人*/
    private Byte type;

    /** 备注*/
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRelateId() {
        return relateId;
    }

    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getRelateProjectId() {
        return relateProjectId;
    }

    public void setRelateProjectId(Integer relateProjectId) {
        this.relateProjectId = relateProjectId;
    }

    public String getRelateProjectCode() {
        return relateProjectCode;
    }

    public void setRelateProjectCode(String relateProjectCode) {
        this.relateProjectCode = relateProjectCode == null ? null : relateProjectCode.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Byte getStage() {
        return stage;
    }

    public void setStage(Byte stage) {
        this.stage = stage;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}