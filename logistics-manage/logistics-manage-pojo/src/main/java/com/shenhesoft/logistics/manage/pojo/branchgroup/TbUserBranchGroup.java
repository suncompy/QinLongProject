package com.shenhesoft.logistics.manage.pojo.branchgroup;

public class TbUserBranchGroup {
    /** 主键id*/
    private Integer id;

    /** 公司内部用户id*/
    private Integer userId;

    /** 分支机构id*/
    private Integer branchGroupId;

    /** 类型 1个人 2 负责人*/
    private Byte type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBranchGroupId() {
        return branchGroupId;
    }

    public void setBranchGroupId(Integer branchGroupId) {
        this.branchGroupId = branchGroupId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}