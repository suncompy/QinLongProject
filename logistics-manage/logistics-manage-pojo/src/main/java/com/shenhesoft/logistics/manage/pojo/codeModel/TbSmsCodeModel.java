package com.shenhesoft.logistics.manage.pojo.codeModel;

import java.io.Serializable;

public class TbSmsCodeModel implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3335233518371887731L;

	/** */
    private Integer id;

    /** 模板名称*/
    private String name;

    /** 模板code*/
    private String code;

    /** 模板内容*/
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}