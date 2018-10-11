package com.shenhesoft.logistics.manage.helpPojo;

import java.math.BigDecimal;

import com.shenhesoft.logistics.finance.AdvanceCharge;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;

public class ProjectAppHelp extends TbProject{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2878204252491878252L;
	
	 /** 货场名*/
    private String freightYardName;

    /** 货位名称*/
    private String cargoLocationName;

    /** 库存*/
    private BigDecimal currentQty;
    
    private AdvanceCharge advanceCharge;

	public AdvanceCharge getAdvanceCharge() {
		return advanceCharge;
	}

	public void setAdvanceCharge(AdvanceCharge advanceCharge) {
		this.advanceCharge = advanceCharge;
	}

	public String getFreightYardName() {
		return freightYardName;
	}

	public void setFreightYardName(String freightYardName) {
		this.freightYardName = freightYardName;
	}

	public String getCargoLocationName() {
		return cargoLocationName;
	}

	public void setCargoLocationName(String cargoLocationName) {
		this.cargoLocationName = cargoLocationName;
	}

	public BigDecimal getCurrentQty() {
		return currentQty;
	}

	public void setCurrentQty(BigDecimal currentQty) {
		this.currentQty = currentQty;
	}

    
}
