package com.shenhesoft.logistics.business.helpPojo;

import java.math.BigDecimal;

public class TrainOrderCargoCheck {

	private Integer checkCargoPlaceId;
	
	private Integer checkCargoSiteId;
	
	private String checkCargoPlaceName;
	
	private String checkCargoSiteName;
	
	private BigDecimal checkWeight;

	public Integer getCheckCargoPlaceId() {
		return checkCargoPlaceId;
	}

	public void setCheckCargoPlaceId(Integer checkCargoPlaceId) {
		this.checkCargoPlaceId = checkCargoPlaceId;
	}

	public Integer getCheckCargoSiteId() {
		return checkCargoSiteId;
	}

	public void setCheckCargoSiteId(Integer checkCargoSiteId) {
		this.checkCargoSiteId = checkCargoSiteId;
	}

	public String getCheckCargoPlaceName() {
		return checkCargoPlaceName;
	}

	public void setCheckCargoPlaceName(String checkCargoPlaceName) {
		this.checkCargoPlaceName = checkCargoPlaceName;
	}

	public String getCheckCargoSiteName() {
		return checkCargoSiteName;
	}

	public void setCheckCargoSiteName(String checkCargoSiteName) {
		this.checkCargoSiteName = checkCargoSiteName;
	}

	public BigDecimal getCheckWeight() {
		return checkWeight;
	}

	public void setCheckWeight(BigDecimal checkWeight) {
		this.checkWeight = checkWeight;
	}
	
	
}
