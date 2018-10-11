package com.shenhesoft.logistics.manage.pojo.role;

public class TbRole {
    /** 角色id*/
    private Integer id;

    /** 角色名*/
    private String name;

    /** is_default*/
    private Byte isDefault;

    /** 删除标识*/
    private Byte deleteFlag;

    private Integer[] perssionIds;
    
    private String sysOrgCode;
    
    public String getSysOrgCode() {
		return sysOrgCode;
	}

	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	public Integer[] getPerssionIds() {
		return perssionIds;
	}

	public void setPerssionIds(Integer[] perssionIds) {
		this.perssionIds = perssionIds;
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

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}