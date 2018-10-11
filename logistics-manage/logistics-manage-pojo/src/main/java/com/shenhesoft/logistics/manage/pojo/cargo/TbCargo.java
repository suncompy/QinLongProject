package com.shenhesoft.logistics.manage.pojo.cargo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class TbCargo implements Serializable{
    /**
	 * @description serialVersionUID
	*/
	private static final long serialVersionUID = 8159068492408785137L;

	/** */
    private Integer id;

    /** 货物品名*/
    @NotBlank
    private String cargoName;

    /** 编号*/
    @NotBlank
    private String cargoCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName == null ? null : cargoName.trim();
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode == null ? null : cargoCode.trim();
    }
}