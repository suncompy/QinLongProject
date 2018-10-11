package com.shenhesoft.logistics.manage.pojo.site;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class TbFreightYard implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8243990637842317806L;

	/** 货场id */
	private Integer id;

	/** 名称 */
	@NotBlank
	private String name;

	/** 是否独立 */
	@NotNull
	private Byte isIsolated;

	/** 联系人 */
	@NotBlank
	private String linkman;

	/** 联系方式 */
	@NotBlank
	private String phone;

	/** 地址 */
	private String address;

	/** 备注 */
	private String remark;

	/** 货场平面图 */
	private String freightYardImg;

	/** 火车站点 */
	private Integer trainStationId;

	private String addressCode;

	/** 删除标识*/
    private Byte deleteFlag;
	
	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
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

	public Byte getIsIsolated() {
		return isIsolated;
	}

	public void setIsIsolated(Byte isIsolated) {
		this.isIsolated = isIsolated;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman == null ? null : linkman.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getFreightYardImg() {
		return freightYardImg;
	}

	public void setFreightYardImg(String freightYardImg) {
		this.freightYardImg = freightYardImg == null ? null : freightYardImg.trim();
	}

	public Integer getTrainStationId() {
		return trainStationId;
	}

	public void setTrainStationId(Integer trainStationId) {
		this.trainStationId = trainStationId;
	}

	public Byte getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}