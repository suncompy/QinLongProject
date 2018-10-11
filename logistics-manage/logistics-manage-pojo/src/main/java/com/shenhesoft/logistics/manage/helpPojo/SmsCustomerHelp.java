package com.shenhesoft.logistics.manage.helpPojo;

import java.io.Serializable;

public class SmsCustomerHelp implements Serializable{

	/**
	 * serialVersionUID
	*/
	private static final long serialVersionUID = 7082167213469987817L;
	
	private String id;
	
	private String name;
	
	private String phone;
	
	private String companyName;
	
	private Byte stage;

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Byte getStage() {
		return stage;
	}

	public void setStage(Byte stage) {
		this.stage = stage;
	}
	
	

}
