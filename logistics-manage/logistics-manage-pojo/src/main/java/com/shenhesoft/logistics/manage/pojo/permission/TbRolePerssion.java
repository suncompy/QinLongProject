package com.shenhesoft.logistics.manage.pojo.permission;

import java.io.Serializable;

public class TbRolePerssion implements Serializable{
    /** */
    private Integer id;

    /** */
    private Integer roleId;

    /** */
    private Integer perssionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPerssionId() {
        return perssionId;
    }

    public void setPerssionId(Integer perssionId) {
        this.perssionId = perssionId;
    }
}