package com.shenhesoft.logistics.manage.pojo.systemuser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shenhesoft.logistics.manage.pojo.menu.TbMenu;

public class TbSystemUser  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2324114516064239927L;

	/** */
    private Integer id;

    /** */
    @JsonFormat(pattern="yyyy.MM.dd",timezone="GMT+8")
    private Date createDate;

    /** 账户*/
    private String account;

    /** 密码*/
    private String passwd;

    /** 名称*/
    @NotBlank(message="姓名不能为空")
    private String name;

    /**年龄 */
    @NotNull(message="年龄不能为空")
    @Min(value=18, message="必须年满18岁！")     
    @Max(value=100,message="年龄不符合要求!")
    private Integer age;

    /** 性别 1男  0女*/
    @NotNull(message="性别不能为空")
    private Byte sex;

    /**婚姻状况 0 未婚 1已婚 */
    @NotNull(message="婚姻状况不能为空")
    private Byte isMarry;

    /** 学历*/
    @NotNull(message="学历不能为空")
    private Byte education;

    /** 手机号*/
    @NotBlank(message="手机号不能为空")
    @Length(min=5, max=11, message="手机号长度不满足")  
    @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", message = "手机号格式不正确")  
    private String phone;

    /** 用户头像 */
    private String userIcon;
    
    /** 分支机构id*/
    private Integer branchGroupId;

    /** 邮箱*/
    @NotBlank(message="邮箱不能为空")
    @Pattern(regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", message = "邮箱账号格式不正确")  
    private String email;

    /** */
    private String areaCode;

    /** 地址*/
    @NotBlank(message="详细地址不能为空")
    private String address;

    /** 备注*/
    private String remark;

    /** 工作状态 0在职 1 离职*/
    private Byte workStatus;

    /** 身份证号*/
    @NotBlank(message="身份证号不能为空")
    private String idcard;

    /**入职时间 */
    @JsonFormat(pattern="yyyy.MM.dd",timezone="GMT+8")
    private Date startWorkDate;

    /** 离职时间*/
    @JsonFormat(pattern="yyyy.MM.dd",timezone="GMT+8")
    private Date leaveOfficeDate;

    /** 最后登录时间*/
    @JsonFormat(pattern="yyyy.MM.dd",timezone="GMT+8")
    private Date lastLoginDate;

    /** */
    private Integer companyId;
    
    /**
     * 短信验证码
     */
    private String checkedCode;
    /**
     * 验证码发送时间
     */
    private Date checkedCodeDate;
    
    /**
     * 融云 用户登录后获取的token
     */
    private String rongCloudToken;
    
    private String sysOrgCode;
    
    private List<TbMenu> meanList;
    
    public List<TbMenu> getMeanList() {
		return meanList;
	}

	public void setMeanList(List<TbMenu> meanList) {
		this.meanList = meanList;
	}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Byte getIsMarry() {
        return isMarry;
    }

    public void setIsMarry(Byte isMarry) {
        this.isMarry = isMarry;
    }

    public Byte getEducation() {
        return education;
    }

    public void setEducation(Byte education) {
        this.education = education;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getBranchGroupId() {
        return branchGroupId;
    }

    public void setBranchGroupId(Integer branchGroupId) {
        this.branchGroupId = branchGroupId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
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

    public Byte getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Byte workStatus) {
        this.workStatus = workStatus;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Date getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(Date startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public Date getLeaveOfficeDate() {
        return leaveOfficeDate;
    }

    public void setLeaveOfficeDate(Date leaveOfficeDate) {
        this.leaveOfficeDate = leaveOfficeDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

	public String getCheckedCode() {
		return checkedCode;
	}

	public void setCheckedCode(String checkedCode) {
		this.checkedCode = checkedCode;
	}

	public Date getCheckedCodeDate() {
		return checkedCodeDate;
	}

	public void setCheckedCodeDate(Date checkedCodeDate) {
		this.checkedCodeDate = checkedCodeDate;
	}

	public String getRongCloudToken() {
		return rongCloudToken;
	}

	public void setRongCloudToken(String rongCloudToken) {
		this.rongCloudToken = rongCloudToken;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

  public String getSysOrgCode() {
    return sysOrgCode;
  }

  public void setSysOrgCode(String sysOrgCode) {
    this.sysOrgCode = sysOrgCode;
  }
	
}