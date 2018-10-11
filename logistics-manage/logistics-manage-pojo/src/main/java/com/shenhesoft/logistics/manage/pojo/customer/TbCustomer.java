package com.shenhesoft.logistics.manage.pojo.customer;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

import com.shenhesoft.logistics.business.helpPojo.AreaHelpPojo;

public class TbCustomer implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5243264052302785363L;

	/** 客户id*/
    private Integer id;

    /** */
    private Date createDate;

    /** 登录账号名*/
    private String account;

    /** 登录密码*/
    private String passwd;

    /** 企业名称*/
    @NotBlank(message="客户名称不能为空")
    private String companyName;

    /** 企业简称*/
    @NotBlank(message="客户简称不能为空")
    private String shortName;

    /** 分支id*/
    private Integer branchId;

    /** 企业地址*/
    private String addressCode;

    /** 企业详细地址*/
    @NotBlank(message="客户详细地址不能为空")
    private String detailAddress;

    /** 企业联系人*/
    @NotBlank(message="客户联系人不能为空")
    private String companyContacts;

    /** 部门*/
    @NotBlank(message="部门不能为空")
    private String department;

    /** 联系方式*/
    @NotBlank(message="联系方式不能为空")
    //@Pattern(regexp = "^[1][3,4,5,8][0-9]{9}$", message = "号码格式不正确") 
    private String stationPhone;

    /** 传真*/
    @NotBlank(message="传真不能为空")
    private String stationFax;

    /** 邮箱*/
    @NotBlank(message="邮箱不能为空")
    @Pattern(regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", message = "邮箱账号格式不正确")  
    private String email;

    /** 银行账户*/
    @NotBlank(message="银行账户不能为空")
    private String bankAccount;

    /** 户名*/
    private String accountName;

    /** 开户行*/
    @NotBlank(message="开户行不能为空")
    private String openBank;

    /** 行号*/
    @NotBlank(message="行号不能为空")
    private String openBankNum;

    /** 税号*/
    @NotBlank(message="税号不能为空")
    private String dutyParagraph;
    
    /** 状态:0可用 1不可用*/
    private Byte status;

    /**发运地 */
    private String relationBeginLocation;
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode == null ? null : addressCode.trim();
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    public String getCompanyContacts() {
        return companyContacts;
    }

    public void setCompanyContacts(String companyContacts) {
        this.companyContacts = companyContacts == null ? null : companyContacts.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getStationPhone() {
        return stationPhone;
    }

    public void setStationPhone(String stationPhone) {
        this.stationPhone = stationPhone == null ? null : stationPhone.trim();
    }

    public String getStationFax() {
        return stationFax;
    }

    public void setStationFax(String stationFax) {
        this.stationFax = stationFax == null ? null : stationFax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank == null ? null : openBank.trim();
    }

    public String getOpenBankNum() {
        return openBankNum;
    }

    public void setOpenBankNum(String openBankNum) {
        this.openBankNum = openBankNum == null ? null : openBankNum.trim();
    }

    public String getDutyParagraph() {
        return dutyParagraph;
    }

    public void setDutyParagraph(String dutyParagraph) {
        this.dutyParagraph = dutyParagraph == null ? null : dutyParagraph.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
    
    public String getRelationBeginLocation() {
        return relationBeginLocation;
    }

    public void setRelationBeginLocation(String relationBeginLocation) {
        this.relationBeginLocation = relationBeginLocation == null ? null : relationBeginLocation.trim();
    }
    
	@Override  
    public boolean equals(Object o) { 
		if (o == this) 
	        	return true;  
        if (!(o instanceof TbCustomer)) {  
            return false;  
        }  
    	TbCustomer s=(TbCustomer)o; 
    	return id.equals(s.id);
    }   
    @Override
    public int hashCode() {  
    	String in =  this.stationPhone;  
    	return in.hashCode();  
    }
}