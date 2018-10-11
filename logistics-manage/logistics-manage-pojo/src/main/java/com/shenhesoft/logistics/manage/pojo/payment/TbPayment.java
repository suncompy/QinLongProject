package com.shenhesoft.logistics.manage.pojo.payment;

import java.io.Serializable;

public class TbPayment implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2601392738175011052L;

	/** */
    private Integer id;

    /** */
    private String name;

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
}