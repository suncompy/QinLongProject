package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;

/**
 * 客户打包-发票信息表-Form.
 * <p>
 * <a href="CustomerPackInvoice.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-19
 * @version 1.0.0
 * @since 1.0.0
 */
public class CustomerPackInvoice {

	// 主键
	private String custPackInvoId;
	// 客户对账打包主键
	private String custPackId;
	// 费用对账打包主键
	private String shPackId;
	// 受票方id
	private Integer receiveCompanyId;
	// 出具方id
	private Integer provideCompanyId;
	// 开票金额
	private BigDecimal invoiceMoney;
	// 税率
	private BigDecimal taxRate;
	// 税额
	private BigDecimal taxMoney;
	// 合计金额
	private BigDecimal totalMoney;
	// 创建时间
	private String createDate;
	// 创建人
	private Integer createUserId;
	// 修改时间
	// 审核人
	private Integer auditUserId;
	// 审核时间
	// 是否删除(0-未删除 1-删除)
	private Integer deleteFlag;
	//开票项目
	private Integer invoiceType;
	private String modifiyDate;
	private String auditDate;
	// 无参构造
	public CustomerPackInvoice() {
		super();
	}

	public String getCustPackInvoId() {
		return custPackInvoId;
	}

	public void setCustPackInvoId(String custPackInvoId) {
		this.custPackInvoId = custPackInvoId;
	}

	public String getCustPackId() {
		return custPackId;
	}

	public void setCustPackId(String custPackId) {
		this.custPackId = custPackId;
	}

	public Integer getReceiveCompanyId() {
		return receiveCompanyId;
	}

	public void setReceiveCompanyId(Integer receiveCompanyId) {
		this.receiveCompanyId = receiveCompanyId;
	}

	public Integer getProvideCompanyId() {
		return provideCompanyId;
	}

	public void setProvideCompanyId(Integer provideCompanyId) {
		this.provideCompanyId = provideCompanyId;
	}

	public BigDecimal getInvoiceMoney() {
		return invoiceMoney;
	}

	public void setInvoiceMoney(BigDecimal invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTaxMoney() {
		return taxMoney;
	}

	public void setTaxMoney(BigDecimal taxMoney) {
		this.taxMoney = taxMoney;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getShPackId() {
		return shPackId;
	}

	public void setShPackId(String shPackId) {
		this.shPackId = shPackId;
	}

  public String getModifiyDate() {
    if("".equals(modifiyDate)){
      return null;
    }
    return modifiyDate;
  }

  public void setModifiyDate(String modifiyDate) {
    this.modifiyDate = modifiyDate;
  }

  public String getAuditDate() {
    if("".equals(auditDate)){
      return null;
    }
    return auditDate;
  }

  public void setAuditDate(String auditDate) {
    this.auditDate = auditDate;
  }

}