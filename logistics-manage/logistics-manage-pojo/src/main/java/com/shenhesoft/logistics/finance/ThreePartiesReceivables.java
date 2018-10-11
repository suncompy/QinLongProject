package com.shenhesoft.logistics.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

public class ThreePartiesReceivables implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -277658217674227296L;

	/** */
    private String id;

    /** 项目id*/
    private Integer projectId;

    /** 打包id*/
    private String customerPackId;

    /** 三方企业id*/
    private Integer threeCompaniesId;

    /** 发票金额*/
    private BigDecimal produceMoney;

    /** 支付比例*/
    private BigDecimal paymentRatio;

    /** 应付金额*/
    private BigDecimal payableMoney;

    /** 待结算金额*/
    private BigDecimal besettledMoney;

    /** 本次结算金额*/
    private BigDecimal settleMoney;

    /** 已结算金额*/
    private BigDecimal settledMoney;
    
    /** 创建者*/
    private String createBy;

    /** 创建时间*/
    private Date createDate;

    /** 更新者*/
    private String updateBy;

    /** 更新时间*/
    private Date updateDate;

    /** 备注信息*/
    private String remarks;

    /** 删除标记*/
    private String delFlag;

    /** 状态 0 待结算 1 结算财务待审核 2 结算审核通过 3结算审核不通过*/
    private Integer status;
    
    
    public BigDecimal getBesettledMoney() {
		return besettledMoney;
	}

	public void setBesettledMoney(BigDecimal besettledMoney) {
		this.besettledMoney = besettledMoney;
	}

	public BigDecimal getSettleMoney() {
		return settleMoney;
	}

	public void setSettleMoney(BigDecimal settleMoney) {
		this.settleMoney = settleMoney;
	}

	public BigDecimal getSettledMoney() {
		return settledMoney;
	}

	public void setSettledMoney(BigDecimal settledMoney) {
		this.settledMoney = settledMoney;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCustomerPackId() {
        return customerPackId;
    }

    public void setCustomerPackId(String customerPackId) {
        this.customerPackId = customerPackId == null ? null : customerPackId.trim();
    }

    public Integer getThreeCompaniesId() {
        return threeCompaniesId;
    }

    public void setThreeCompaniesId(Integer threeCompaniesId) {
        this.threeCompaniesId = threeCompaniesId;
    }

    public BigDecimal getProduceMoney() {
        return produceMoney;
    }

    public void setProduceMoney(BigDecimal produceMoney) {
        this.produceMoney = produceMoney;
    }

    public BigDecimal getPaymentRatio() {
        return paymentRatio;
    }

    public void setPaymentRatio(BigDecimal paymentRatio) {
        this.paymentRatio = paymentRatio;
    }

    public BigDecimal getPayableMoney() {
        return payableMoney;
    }

    public void setPayableMoney(BigDecimal payableMoney) {
        this.payableMoney = payableMoney;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}