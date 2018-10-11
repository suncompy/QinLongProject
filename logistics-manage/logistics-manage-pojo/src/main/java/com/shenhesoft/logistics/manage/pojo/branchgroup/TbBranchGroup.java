package com.shenhesoft.logistics.manage.pojo.branchgroup;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class TbBranchGroup implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3819580174029505435L;

	  /** 分支机构id*/
    private Integer id;

    /** 编号*/
    @NotBlank(message="网点分支编码不能为空")
    private String code;

    /** 机构名称*/
    @NotBlank(message="网点分支名称不能为空")
    private String name;

    /** 简称*/
    @NotBlank(message="网点分支简称不能为空")
    private String shortName;

    /** 简称代码*/
    private String shortCode;

    /** 网点归属id*/
    private Integer ascriptionId;

    /** 网点归属名*/
    private String ascriptionName;

    /** 级别*/
    private Byte level;

    /** 负责人*/
    private String responsibler;

    /** 负责人id*/
    private Integer responsiblerid;

    /** 区域id*/
    private String areaId;

    /** 地址*/
    @NotBlank(message="地址不能为空")
    private String address;

    /** 关联火车站*/
    private Integer relationTrainLocationId;

    /** 关联火车站名称*/
    private String relationTrainLocationName;

    /** 关联发运地id*/
    private Integer relationBeginLocationId;

    /** 关联发运地*/
    private String relationBeginLocation;

    /** 备注*/
    private String comment;

    /** 状态:0  已禁用                1 正在使用*/
    private Byte status;

    /** 创建时间*/
    private Date createDate;

    /** 纳税识别号*/
    private String dutyParagraph;
    private String sysOrgCode;
    
    private String text;
    
    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDutyParagraph() {
		return dutyParagraph;
	}

	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode == null ? null : shortCode.trim();
    }

    public Integer getAscriptionId() {
        return ascriptionId;
    }

    public void setAscriptionId(Integer ascriptionId) {
        this.ascriptionId = ascriptionId;
    }

    public String getAscriptionName() {
        return ascriptionName;
    }

    public void setAscriptionName(String ascriptionName) {
        this.ascriptionName = ascriptionName == null ? null : ascriptionName.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getResponsibler() {
        return responsibler;
    }

    public void setResponsibler(String responsibler) {
        this.responsibler = responsibler == null ? null : responsibler.trim();
    }

    public Integer getResponsiblerid() {
        return responsiblerid;
    }

    public void setResponsiblerid(Integer responsiblerid) {
        this.responsiblerid = responsiblerid;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getRelationTrainLocationId() {
        return relationTrainLocationId;
    }

    public void setRelationTrainLocationId(Integer relationTrainLocationId) {
        this.relationTrainLocationId = relationTrainLocationId;
    }

    public String getRelationTrainLocationName() {
        return relationTrainLocationName;
    }

    public void setRelationTrainLocationName(String relationTrainLocationName) {
        this.relationTrainLocationName = relationTrainLocationName == null ? null : relationTrainLocationName.trim();
    }

    public Integer getRelationBeginLocationId() {
        return relationBeginLocationId;
    }

    public void setRelationBeginLocationId(Integer relationBeginLocationId) {
        this.relationBeginLocationId = relationBeginLocationId;
    }

    public String getRelationBeginLocation() {
        return relationBeginLocation;
    }

    public void setRelationBeginLocation(String relationBeginLocation) {
        this.relationBeginLocation = relationBeginLocation == null ? null : relationBeginLocation.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSysOrgCode() {
      return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
      this.sysOrgCode = sysOrgCode;
    }
    
}