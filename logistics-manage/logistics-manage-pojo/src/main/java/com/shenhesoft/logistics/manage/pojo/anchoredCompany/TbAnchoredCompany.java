package com.shenhesoft.logistics.manage.pojo.anchoredCompany;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 挂靠公司 信息
 */
public class TbAnchoredCompany implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8941421625123945595L;

	/** 挂靠公司id*/
    private Integer id;

    /** 挂靠公司名称*/
    private String name;

    /** 手机号*/
    private String phone;

    /** 状态 0 不可用 1 可用*/
    private String status;

    /**创建时间 */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createDate;

    /** 地址*/
    private String address;

    /** 联系方式*/
    private String linkman;

    private Integer branchId;//关联的网点分支id
    
    public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }
}