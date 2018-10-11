package com.shenhesoft.logistics.manage.pojo.CargoLocation;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 货位信息
 */
public class TbCargoLocation implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1146511943644842195L;

	/** id*/
    private Integer id;

    /** 编号*/
    @NotBlank
    private String code;

    /** 名称*/
    @NotBlank
    private String name;

    /** 吨位*/
    private Float tonnage;

    /** 货场id*/
    private Integer freightYardId;

    /** 删除标识*/
    private Byte deleteFlag;
    
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

    public Float getTonnage() {
        return tonnage;
    }

    public void setTonnage(Float tonnage) {
        this.tonnage = tonnage;
    }

    public Integer getFreightYardId() {
        return freightYardId;
    }

    public void setFreightYardId(Integer freightYardId) {
        this.freightYardId = freightYardId;
    }

	public Byte getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
    
}