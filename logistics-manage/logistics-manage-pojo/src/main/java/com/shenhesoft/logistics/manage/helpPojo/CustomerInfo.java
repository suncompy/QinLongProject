package com.shenhesoft.logistics.manage.helpPojo;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月12日
 */
public class CustomerInfo extends TbCustomer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8717774519243497137L;
	private List<TbBranchGroup> branchGroups;
	private List<TbStationBusinessHelp> businessContacts;
	
	private String brachIds;
	@NotBlank
	private String businessContact;//业务联系人
	@NotBlank
	private String province;
	@NotBlank
	private String city="";
	
	private String district;
	
   /* private String relationBeginLocation;
    
	public String getRelationBeginLocation() {
		return relationBeginLocation;
	}

	public void setRelationBeginLocation(String relationBeginLocation) {
		this.relationBeginLocation = relationBeginLocation;
	}*/

	public List<TbStationBusinessHelp> getBusinessContacts() {
		return businessContacts;
	}

	public void setBusinessContacts(List<TbStationBusinessHelp> businessContacts) {
		this.businessContacts = businessContacts;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public List<TbBranchGroup> getBranchGroups() {
		return branchGroups;
	}

	public void setBranchGroups(List<TbBranchGroup> branchGroups) {
		this.branchGroups = branchGroups;
	}

	public String getBrachIds() {
		return brachIds;
	}

	public void setBrachIds(String brachIds) {
		this.brachIds = brachIds;
	}

	public String getBusinessContact() {
		return businessContact;
	}

	public void setBusinessContact(String businessContact) {
		this.businessContact = businessContact;
	}

	@Override
	public String toString() {
		return String.format(
				"CustomerInfo [branchGroups=%s, brachIds=%s, businessContact=%s, province=%s, city=%s, district=%s, getProvince()=%s, getCity()=%s, getDistrict()=%s, getBranchGroups()=%s, getBrachIds()=%s, getBusinessContact()=%s]",
				branchGroups, brachIds, businessContact, province, city, district, getProvince(), getCity(),
				getDistrict(), getBranchGroups(), getBrachIds(), getBusinessContact());
	}
	
}
