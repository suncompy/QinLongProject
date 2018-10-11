package com.shenhesoft.logistics.business.pojo.log;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TbProjectOperationLog {
    /** */
    private Integer id;

    /** 创建时间*/
    @JsonFormat(pattern="yyyy.MM.dd HH:mm",timezone="GMT+8")
    private Date createDate;

    /** 操作人id*/
    private Integer operatorId;

    /** 内容*/
    private String content;

    /** 项目id*/
    private Integer projectId;

    /** 类型 0 删除 1修改 2新增 */
    private Byte type;

    /**操作人 */
    private String operatorName;

    /** 操作项目*/
    private String projectCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }
}