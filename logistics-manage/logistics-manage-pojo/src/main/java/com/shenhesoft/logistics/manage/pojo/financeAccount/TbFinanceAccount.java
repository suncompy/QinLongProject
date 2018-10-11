package com.shenhesoft.logistics.manage.pojo.financeAccount;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TbFinanceAccount implements Serializable{
    /**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月13日
	 * @param 
	 * @return
	*/
	private static final long serialVersionUID = 1824278428645046494L;

	/** id*/
    private Integer id;

    /** 账号名称*/
    @NotBlank
    private String name;

    /** 开户时间*/
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date accountOpeningTime;
    
    /*@NotBlank
    private String time;*/
    
    /** 账户种类*/
    @NotNull
    private Byte accountKind;
    
    /** 账户类型*/
    @NotNull
    private Byte accountType;

    /** 选择账户（客户，站点，公司）*/
    @NotNull
    private Integer chooseAccountId;
    
    /** 选择账户（客户，站点，公司） 对应的名称*/
    private String relationName;
    
    /** 期初余额*/
    @NotNull
    private BigDecimal startAccountBalance;

    /** 账户余额*/
    private BigDecimal accountBalance;

    /** 警戒金额*/
    @NotNull
    private BigDecimal vigilanceAmount;

    /** 停用金额*/
    @NotNull
    private BigDecimal nonUseAmount;

    /** 账户名*/
    @NotBlank
    private String accountName;

    /** 账号*/
    @NotBlank
    private String accountNum;

    /** 开户行*/
    @NotBlank
    private String openBank;

    /** 行号*/
    @NotBlank
    private String bankNum;

    /** 纳税识别号*/
    private String taxIdentificationNumber;

    /** 删除状态：0未删除，1已删除*/
    private Byte status;

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

    public Date getAccountOpeningTime() {
        return accountOpeningTime;
    }

    public void setAccountOpeningTime(Date accountOpeningTime) {
        this.accountOpeningTime = accountOpeningTime;
    }

    public Byte getAccountType() {
        return accountType;
    }

    public void setAccountType(Byte accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getVigilanceAmount() {
        return vigilanceAmount;
    }

    public void setVigilanceAmount(BigDecimal vigilanceAmount) {
        this.vigilanceAmount = vigilanceAmount;
    }

    public BigDecimal getNonUseAmount() {
        return nonUseAmount;
    }

    public void setNonUseAmount(BigDecimal nonUseAmount) {
        this.nonUseAmount = nonUseAmount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum == null ? null : accountNum.trim();
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank == null ? null : openBank.trim();
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum == null ? null : bankNum.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

	public Byte getAccountKind() {
		return accountKind;
	}

	public void setAccountKind(Byte accountKind) {
		this.accountKind = accountKind;
	}

	public Integer getChooseAccountId() {
		return chooseAccountId;
	}

	public void setChooseAccountId(Integer chooseAccountId) {
		this.chooseAccountId = chooseAccountId;
	}

	public BigDecimal getStartAccountBalance() {
		return startAccountBalance;
	}

	public void setStartAccountBalance(BigDecimal startAccountBalance) {
		this.startAccountBalance = startAccountBalance;
	}

	public String getTaxIdentificationNumber() {
		return taxIdentificationNumber;
	}

	public void setTaxIdentificationNumber(String taxIdentificationNumber) {
		this.taxIdentificationNumber = taxIdentificationNumber;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
    
	
}