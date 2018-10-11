package com.shenhesoft.logistics.manage.pojo.specifications;

import java.io.Serializable;

public class TbSpecifications implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -394352827178551425L;

	/** */
    private Integer id;

    /** 规格名*/
    private String name;

    /** 货物id*/
    private Integer cargoId;

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

    public Integer getCargoId() {
        return cargoId;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }
}