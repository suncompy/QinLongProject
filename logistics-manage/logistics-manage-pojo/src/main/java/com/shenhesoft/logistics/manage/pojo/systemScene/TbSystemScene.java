package com.shenhesoft.logistics.manage.pojo.systemScene;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class TbSystemScene implements Serializable{
    /**
	 * @description serialVersionUID
	*/
	private static final long serialVersionUID = 4114895202529698610L;

	/** */
    private Integer id;

    /** 情景名称*/
    @NotBlank
    private String sceneName;

    /** 关联分支机构id*/
    private String branchGroupId;

    /** 原因类型*/
    @NotNull
    private Byte reasonType;

    /** 各种原因对应的数值比例*/
    @NotBlank
    private String reasonScale;

    /** 备注说明*/
    @NotBlank
    private String remark;

    /** 删除状态：0未删除，1已删除*/
    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName == null ? null : sceneName.trim();
    }

	public String getBranchGroupId() {
		return branchGroupId;
	}

	public void setBranchGroupId(String branchGroupId) {
		this.branchGroupId = branchGroupId;
	}

	public Byte getReasonType() {
        return reasonType;
    }

    public void setReasonType(Byte reasonType) {
        this.reasonType = reasonType;
    }

    public String getReasonScale() {
        return reasonScale;
    }

    public void setReasonScale(String reasonScale) {
        this.reasonScale = reasonScale == null ? null : reasonScale.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}