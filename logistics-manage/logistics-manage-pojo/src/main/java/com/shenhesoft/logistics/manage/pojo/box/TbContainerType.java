package com.shenhesoft.logistics.manage.pojo.box;

import java.io.Serializable;

public class TbContainerType implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7702868580531967266L;

	/** */
    private Integer id;

    /** 名称*/
    private String name;

    /** 编码*/
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}