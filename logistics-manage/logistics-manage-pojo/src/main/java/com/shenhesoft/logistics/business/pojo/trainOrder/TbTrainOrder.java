package com.shenhesoft.logistics.business.pojo.trainOrder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce.TbTrainOrderCargoPalce;

/**
 * 火运运单
 */
public class TbTrainOrder {
    /** */
    private Integer id;

    /** 项目id*/
    private Integer projectId;

    /** 项目编号*/
    private String projectCode;

    /** 项目类型*/
    private Byte projectType;

    /** 分支id*/
    private Integer branchId;

    /** 分支机构*/
    private String branchName;

    /** 始发站点*/
    private String beginSite;

    /** 始发地*/
    private String beginPlace;

    /** 到达站点*/
    private String endSite;

    /** 运抵地*/
    private String endPlace;

    /** 请车数量*/
    private String pleaseCarNum;

    /** 请车类型id*/
    private Integer pleaseCarTypeId;

    /** 预计载重*/
    private String estimateWeight;

    private BigDecimal kuCun;
    
    /** 预计费用*/
    private String estimateCost;

    /** 货物品名*/
    private String cargoName;

    /** 规格*/
    private String cargoSpecifications;

    /** 预付款账户*/
    private String advanceChargeAccount;

    /** 预付款金额*/
    private BigDecimal advanceCharge;

    /** 承认车辆数*/
    private Integer sureCarNum;

    /** 发运时间*/
    private Date sendDate;

    /** 发运操作员*/
    private String sendOperatorId;

    /** 更新位置id*/
    private Integer updateLocationId;

    /** 类型
            0:集装箱
            1:散堆装*/
    private Byte type;

    /** 请车单号*/
    private String pleaseTrainNumber;

    /** 创建时间*/
    private Date createDate;

    /** 运单状态:0:取消,1:等待承认,2:等待装车,3:等待发运,4:在途运载,5:等待卸货,6:等待回单,7:已完成*/
    private Byte status;

    /** 运单更新时间*/
    private Date updateDate;

    /** 装车时间*/
    private Date entruckDate;

    /** 落车数*/
    private Integer loseCarNum;

    /** 是否异常*/
    private Byte isException;

    /** 异常原因*/
    private String exceptionReason;

    /** 异常上报时间*/
    private Date exceptionReportDate;

    /** 异常上报人*/
    private String exceptionReportPerson;

    /** 删除时间*/
    private Date deleteDate;

    /** 删除操作人*/
    private String deletePerson;

    /** 删除原因*/
    private String deleteReason;

    /** 人工上报异常id*/
    private Integer artificialReportId;

    /** 装车数*/
    private Integer entruckNumbe;

    /** 集装箱数*/
    private Integer containerNums;

    /** 装载吨位*/
    private BigDecimal entruckWeight;

    /** 到货载重*/
    private BigDecimal arriveWeight;

    /** 到货时间*/
    private Date arriveDate;

    /** 删除标志:1已删除,0:未删除*/
    private Byte deleteFlag;

    /** 发货单位*/
    private String sendCompany;

    /** 收货单位*/
    private String receiptCompany;
    
    /**
     * 火运装车详单列表
     */
    List<TbTrainOrderCargoPalce> trainCargoList;
    
    /** 状态名称*/
    private String statusName;

    private String exportCreatTime;
    private String exportSendTime;
    private String exportUpdateTime;
    private String exportEntruckTime;
    private String exportArriveDate;
    private String exportDeleteDate; 
    private String exportExceptionReportDate;
    
    public BigDecimal getKuCun() {
		return kuCun;
	}

	public void setKuCun(BigDecimal kuCun) {
		this.kuCun = kuCun;
	}

	public String getExportDeleteDate() {
		return exportDeleteDate;
	}

	public void setExportDeleteDate(String exportDeleteDate) {
		this.exportDeleteDate = exportDeleteDate;
	}

	public String getExportExceptionReportDate() {
		return exportExceptionReportDate;
	}

	public void setExportExceptionReportDate(String exportExceptionReportDate) {
		this.exportExceptionReportDate = exportExceptionReportDate;
	}

	public String getExportArriveDate() {
		return exportArriveDate;
	}

	public void setExportArriveDate(String exportArriveDate) {
		this.exportArriveDate = exportArriveDate;
	}

	public String getExportCreatTime() {
		return exportCreatTime;
	}

	public void setExportCreatTime(String exportCreatTime) {
		this.exportCreatTime = exportCreatTime;
	}

	public String getExportSendTime() {
		return exportSendTime;
	}

	public void setExportSendTime(String exportSendTime) {
		this.exportSendTime = exportSendTime;
	}

	public String getExportUpdateTime() {
		return exportUpdateTime;
	}

	public void setExportUpdateTime(String exportUpdateTime) {
		this.exportUpdateTime = exportUpdateTime;
	}

	public String getExportEntruckTime() {
		return exportEntruckTime;
	}

	public void setExportEntruckTime(String exportEntruckTime) {
		this.exportEntruckTime = exportEntruckTime;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public Byte getProjectType() {
        return projectType;
    }

    public void setProjectType(Byte projectType) {
        this.projectType = projectType;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public String getBeginSite() {
        return beginSite;
    }

    public void setBeginSite(String beginSite) {
        this.beginSite = beginSite == null ? null : beginSite.trim();
    }

    public String getBeginPlace() {
        return beginPlace;
    }

    public void setBeginPlace(String beginPlace) {
        this.beginPlace = beginPlace == null ? null : beginPlace.trim();
    }

    public String getEndSite() {
        return endSite;
    }

    public void setEndSite(String endSite) {
        this.endSite = endSite == null ? null : endSite.trim();
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace == null ? null : endPlace.trim();
    }

    public String getPleaseCarNum() {
        return pleaseCarNum;
    }

    public void setPleaseCarNum(String pleaseCarNum) {
        this.pleaseCarNum = pleaseCarNum == null ? null : pleaseCarNum.trim();
    }

    public Integer getPleaseCarTypeId() {
        return pleaseCarTypeId;
    }

    public void setPleaseCarTypeId(Integer pleaseCarTypeId) {
        this.pleaseCarTypeId = pleaseCarTypeId;
    }

    public String getEstimateWeight() {
        return estimateWeight;
    }

    public void setEstimateWeight(String estimateWeight) {
        this.estimateWeight = estimateWeight == null ? null : estimateWeight.trim();
    }

    public String getEstimateCost() {
        return estimateCost;
    }

    public void setEstimateCost(String estimateCost) {
        this.estimateCost = estimateCost == null ? null : estimateCost.trim();
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName == null ? null : cargoName.trim();
    }

    public String getCargoSpecifications() {
        return cargoSpecifications;
    }

    public void setCargoSpecifications(String cargoSpecifications) {
        this.cargoSpecifications = cargoSpecifications == null ? null : cargoSpecifications.trim();
    }

    public String getAdvanceChargeAccount() {
        return advanceChargeAccount;
    }

    public void setAdvanceChargeAccount(String advanceChargeAccount) {
        this.advanceChargeAccount = advanceChargeAccount == null ? null : advanceChargeAccount.trim();
    }

    public BigDecimal getAdvanceCharge() {
        return advanceCharge;
    }

    public void setAdvanceCharge(BigDecimal advanceCharge) {
        this.advanceCharge = advanceCharge;
    }

    public Integer getSureCarNum() {
        return sureCarNum;
    }

    public void setSureCarNum(Integer sureCarNum) {
        this.sureCarNum = sureCarNum;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendOperatorId() {
        return sendOperatorId;
    }

    public void setSendOperatorId(String sendOperatorId) {
        this.sendOperatorId = sendOperatorId == null ? null : sendOperatorId.trim();
    }

    public Integer getUpdateLocationId() {
        return updateLocationId;
    }

    public void setUpdateLocationId(Integer updateLocationId) {
        this.updateLocationId = updateLocationId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getPleaseTrainNumber() {
        return pleaseTrainNumber;
    }

    public void setPleaseTrainNumber(String pleaseTrainNumber) {
        this.pleaseTrainNumber = pleaseTrainNumber == null ? null : pleaseTrainNumber.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getEntruckDate() {
        return entruckDate;
    }

    public void setEntruckDate(Date entruckDate) {
        this.entruckDate = entruckDate;
    }

    public Integer getLoseCarNum() {
        return loseCarNum;
    }

    public void setLoseCarNum(Integer loseCarNum) {
        this.loseCarNum = loseCarNum;
    }

    public Byte getIsException() {
        return isException;
    }

    public void setIsException(Byte isException) {
        this.isException = isException;
    }

    public String getExceptionReason() {
        return exceptionReason;
    }

    public void setExceptionReason(String exceptionReason) {
        this.exceptionReason = exceptionReason == null ? null : exceptionReason.trim();
    }

    public Date getExceptionReportDate() {
        return exceptionReportDate;
    }

    public void setExceptionReportDate(Date exceptionReportDate) {
        this.exceptionReportDate = exceptionReportDate;
    }

    public String getExceptionReportPerson() {
        return exceptionReportPerson;
    }

    public void setExceptionReportPerson(String exceptionReportPerson) {
        this.exceptionReportPerson = exceptionReportPerson == null ? null : exceptionReportPerson.trim();
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getDeletePerson() {
        return deletePerson;
    }

    public void setDeletePerson(String deletePerson) {
        this.deletePerson = deletePerson == null ? null : deletePerson.trim();
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason == null ? null : deleteReason.trim();
    }

    public Integer getArtificialReportId() {
        return artificialReportId;
    }

    public void setArtificialReportId(Integer artificialReportId) {
        this.artificialReportId = artificialReportId;
    }

    public Integer getEntruckNumbe() {
        return entruckNumbe;
    }

    public void setEntruckNumbe(Integer entruckNumbe) {
        this.entruckNumbe = entruckNumbe;
    }

    public Integer getContainerNums() {
        return containerNums;
    }

    public void setContainerNums(Integer containerNums) {
        this.containerNums = containerNums;
    }

    public BigDecimal getEntruckWeight() {
        return entruckWeight;
    }

    public void setEntruckWeight(BigDecimal entruckWeight) {
        this.entruckWeight = entruckWeight;
    }

    public BigDecimal getArriveWeight() {
        return arriveWeight;
    }

    public void setArriveWeight(BigDecimal arriveWeight) {
        this.arriveWeight = arriveWeight;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getSendCompany() {
        return sendCompany;
    }

    public void setSendCompany(String sendCompany) {
        this.sendCompany = sendCompany == null ? null : sendCompany.trim();
    }

    public String getReceiptCompany() {
        return receiptCompany;
    }

    public void setReceiptCompany(String receiptCompany) {
        this.receiptCompany = receiptCompany == null ? null : receiptCompany.trim();
    }

	public List<TbTrainOrderCargoPalce> getTrainCargoList() {
		return trainCargoList;
	}

	public void setTrainCargoList(List<TbTrainOrderCargoPalce> trainCargoList) {
		this.trainCargoList = trainCargoList;
	}
}