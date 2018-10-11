package com.shenhesoft.logistics.business.pojo.TbOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TbOrder implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6454652213591546228L;

	 /** 订单id*/
    private Integer id;

    /** 订单编号*/
    private String orderCode;

    /** 项目id*/
    private Integer projectId;

    /** 项目编码*/
    private String projectCode;

    /** 项目类型*/
    private Byte projectType;

    /** 联运模式*/
    private Byte transportType;

    /** 驾驶员id*/
    private Integer driverId;

    /** 创建人id*/
    private Integer creatorId;

    /** 创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;

    /** 每次运单状态变更的时间点*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;

    /** 调度员id*/
    private Integer userDispatchId;

    /** */
    private Integer sendCompanyId;
    private Integer receiptCompanyId;
    
    /** 调度员姓名*/
    private String userDispatchName;

    /** 任务id*/
    private Integer projectDistributionId;

    /** 运单类型
            1:集装箱
            2:散堆装*/
    private Byte type;

    /** 1:等待调度
            2:等待发运
            3:在途运载
            4:货位引导
            5:等待回单
            6:等待确认
            7:已完成*/
    private Byte status;

    /** 异常订单下的状态
            0 非异常
            1 异常*/
    private Byte exceptionStatus;

    /** 人工上报异常关联表id*/
    private Integer exceptionId;

    /** 异常提报人id*/
    private Integer exceptionReoportId;

    /** 异常提报人姓名*/
    private String exceptionReoportName;

    /** 异常提报时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date exceptionTime;

    /** 异常原因*/
    private String exceptionReoportReason;

    /** 驳回原因*/
    private String remark;

    /** 阶段选择：0接取 1：送达 2汽运*/
    private Byte stepSelectCode;

    /** 阶段选择name*/
    private String stepSelect;

    /** 分支id*/
    private Integer branchId;
    
    /** 分支机构*/
    private String branchGroupName;

    /** 发货单位*/
    private String sendCompany;

    /** 取货地*/
    private String pickupPlace;

    /** 取货详细地址*/
    private String pickupPlaceAddress;

    /** 收货单位*/
    private String receiptCompany;

    /** 运抵地*/
    private String arrivePlace;

    /** 运抵地址详情*/
    private String arriveAddress;

    /** 运抵货场*/
    private String arriveFreightYrad;

    /** 运抵货位*/
    private String arriveFreightSite;

    /** 承运车辆id*/
    private String carrierVehicleId;

    /** 承运车辆名称*/
    private String carrierVehicleName;

    /** 承运车辆车牌号*/
    private String carPlateNumber;

    /** 车型*/
    private String carType;

    /** 司机*/
    private String driverName;

    /** 驾驶员手机号*/
    private String driverPhone;

    /** 集装箱号1id*/
    private String containerNumber1Id;

    /** 集装箱号1*/
    private String containerNumber1;

    /** 集装箱号2id*/
    private String containerNumber2Id;

    /** 集装箱号2*/
    private String containerNumber2;

    /** 货位品名*/
    private String cargoName;

    /** 规格*/
    private String specifications;

    /** 扣损比率*/
    private BigDecimal deductionRate;

    /** 短驳费用*/
    private BigDecimal shortBargeCost;

    /** 补贴*/
    private BigDecimal subsidy;

    /** 扣损单价*/
    private BigDecimal deductionPrice;

    /** 发货皮重*/
    private BigDecimal sendTare;

    /** 发货毛重*/
    private BigDecimal sendGross;

    /** 第一个集装箱的发货净重       在散堆装时候；作为发货净重*/
    private BigDecimal containerOneSendNet;

    /** 第二个集装箱的发货净重*/
    private BigDecimal containerTwoSendNet;

    /** 发货:化验指标*/
    private String testIndicators;

    /** 运单图片地址*/
    private String orderImg;

    /** 分配货场*/
    private String distributionCargoPlace;

    /** 分配货位*/
    private String distributionCargoSite;

    /** 回单-收货皮重*/
    private BigDecimal receiptTare;

    /** 回单-收货毛重*/
    private BigDecimal receiptGross;

    /** 回单-第一个集装箱到货净重*/
    private BigDecimal containerOneReceiptNet;

    /** 回单-回单-第二个集装箱到货净重*/
    private BigDecimal containerTwoReceiptNet;

    /** 到货:化验指标 */
    private String receiptTestIndicators;

    /** 到货图片上传*/
    private String arriveredImg;

    /** */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date editDate;

    /** 件数*/
    private Integer pieceNumber;

    /** 计价单位 1吨 2 件*/
    private Byte valuationUnitType;

    /** 车队id*/
    private Integer carteamId;

    /** 运单位置关联id*/
    private Integer placeNowId;

    /** 1:pc端   2:APP端*/
    private Byte orderOrigin;

    /** 删除人id*/
    private Integer deletorId;

    /** 删除人姓名*/
    private String deleteName;

    /** 删除时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date deleteTime;

    /** 删除原因*/
    private String deleteReason;

    /** 逻辑删除
            0:未删除
            1:已删除
            */
    private Byte deleteFlag;

    /** 是否被取消 0未取消 1已取消*/
    private Byte isCancel;

    /** 取消原因*/
    private String cancelReason;

    /** 取消原因-详细*/
    private String cancelReasonDetail;

    /** 取消时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date cancelDate;

    /** 回单完成时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date receipterDate;

    /** 提货货场id*/
    private Integer takeCargoPlaceId;

    /** 提货货场名称*/
    private String takeCarogoPlaceName;

    /** 提货货位id*/
    private Integer takeCargoSiteId;

    /** 提货货位名称*/
    private String takeCargoSiteName;

    /** 提货时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date takeDeliveryDate;

    /** 分配货场id*/
    private Integer distributionCargoPlaceId;

    /** 分配货位id*/
    private Integer distributionCargoSiteId;

    /**
	 * 所属车队名称 - 临时字段
	 */
	private String carItemName;
	/**
	 * 当前司机历史运单数量 - 临时字段
	 */
	private Integer historyTbOrderNumDriverId;

	private String shOrderFinId;
	
	private BigDecimal containerReceiptNet;
	
	private BigDecimal containerSendNet;
	
	//短驳导出辅助字段
	 /** 状态名称*/
    private String statusName;
    private String exportCreatTime;
    private String exportUpdateTime;
    private String exportArriveDate;
    private String exportDeleteDate; 
    private String exportExceptionReportDate;
    private BigDecimal exportSumSendNet;
    private BigDecimal exportSumReceiptNet;
    
    //短驳出入库查询导出辅助字段
    private String exportTakeDeliveryDate;
    private String exportProjectType;
    private String exportTransportType;
    private String exportStep;
    private BigDecimal wastage;
    private String huochangName;
    private String huoweiName;
    
	public String getHuochangName() {
		return huochangName;
	}

	public void setHuochangName(String huochangName) {
		this.huochangName = huochangName;
	}

	public String getHuoweiName() {
		return huoweiName;
	}

	public void setHuoweiName(String huoweiName) {
		this.huoweiName = huoweiName;
	}

	public BigDecimal getWastage() {
		return wastage;
	}

	public void setWastage(BigDecimal wastage) {
		this.wastage = wastage;
	}

	public String getExportTakeDeliveryDate() {
		return exportTakeDeliveryDate;
	}

	public void setExportTakeDeliveryDate(String exportTakeDeliveryDate) {
		this.exportTakeDeliveryDate = exportTakeDeliveryDate;
	}

	public String getExportProjectType() {
		return exportProjectType;
	}

	public void setExportProjectType(String exportProjectType) {
		this.exportProjectType = exportProjectType;
	}

	public String getExportTransportType() {
		return exportTransportType;
	}

	public void setExportTransportType(String exportTransportType) {
		this.exportTransportType = exportTransportType;
	}

	public String getExportStep() {
		return exportStep;
	}

	public void setExportStep(String exportStep) {
		this.exportStep = exportStep;
	}

	public BigDecimal getExportSumSendNet() {
		return exportSumSendNet;
	}

	public void setExportSumSendNet(BigDecimal exportSumSendNet) {
		this.exportSumSendNet = exportSumSendNet;
	}

	public BigDecimal getExportSumReceiptNet() {
		return exportSumReceiptNet;
	}

	public void setExportSumReceiptNet(BigDecimal exportSumReceiptNet) {
		this.exportSumReceiptNet = exportSumReceiptNet;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getExportCreatTime() {
		return exportCreatTime;
	}

	public void setExportCreatTime(String exportCreatTime) {
		this.exportCreatTime = exportCreatTime;
	}

	public String getExportUpdateTime() {
		return exportUpdateTime;
	}

	public void setExportUpdateTime(String exportUpdateTime) {
		this.exportUpdateTime = exportUpdateTime;
	}

	public String getExportArriveDate() {
		return exportArriveDate;
	}

	public void setExportArriveDate(String exportArriveDate) {
		this.exportArriveDate = exportArriveDate;
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

	public BigDecimal getContainerReceiptNet() {
		return containerReceiptNet;
	}

	public void setContainerReceiptNet(BigDecimal containerReceiptNet) {
		this.containerReceiptNet = containerReceiptNet;
	}

	public BigDecimal getContainerSendNet() {
		return containerSendNet;
	}

	public void setContainerSendNet(BigDecimal containerSendNet) {
		this.containerSendNet = containerSendNet;
	}

	public String getCarItemName() {
		return carItemName;
	}

	public void setCarItemName(String carItemName) {
		this.carItemName = carItemName;
	}

	public Integer getHistoryTbOrderNumDriverId() {
		return historyTbOrderNumDriverId;
	}

	public void setHistoryTbOrderNumDriverId(Integer historyTbOrderNumDriverId) {
		this.historyTbOrderNumDriverId = historyTbOrderNumDriverId;
	}

	public Integer getSendCompanyId() {
		return sendCompanyId;
	}

	public void setSendCompanyId(Integer sendCompanyId) {
		this.sendCompanyId = sendCompanyId;
	}

	public Integer getReceiptCompanyId() {
		return receiptCompanyId;
	}

	public void setReceiptCompanyId(Integer receiptCompanyId) {
		this.receiptCompanyId = receiptCompanyId;
	}

	public Byte getProjectType() {
		return projectType;
	}

	public String getContainerNumber1Id() {
		return containerNumber1Id;
	}

	public String getContainerNumber2Id() {
		return containerNumber2Id;
	}

	public String getDeleteName() {
		return deleteName;
	}

	public void setDeleteName(String deleteName) {
		this.deleteName = deleteName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode == null ? null : orderCode.trim();
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

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getUserDispatchId() {
		return userDispatchId;
	}

	public void setUserDispatchId(Integer userDispatchId) {
		this.userDispatchId = userDispatchId;
	}

	public String getUserDispatchName() {
		return userDispatchName;
	}

	public void setUserDispatchName(String userDispatchName) {
		this.userDispatchName = userDispatchName == null ? null : userDispatchName.trim();
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getExceptionStatus() {
		return exceptionStatus;
	}

	public void setExceptionStatus(Byte exceptionStatus) {
		this.exceptionStatus = exceptionStatus;
	}

	public Integer getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(Integer exceptionId) {
		this.exceptionId = exceptionId;
	}

	public Integer getExceptionReoportId() {
		return exceptionReoportId;
	}

	public void setExceptionReoportId(Integer exceptionReoportId) {
		this.exceptionReoportId = exceptionReoportId;
	}

	public String getExceptionReoportName() {
		return exceptionReoportName;
	}

	public void setExceptionReoportName(String exceptionReoportName) {
		this.exceptionReoportName = exceptionReoportName == null ? null : exceptionReoportName.trim();
	}

	public Date getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(Date exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

	public String getExceptionReoportReason() {
		return exceptionReoportReason;
	}

	public void setExceptionReoportReason(String exceptionReoportReason) {
		this.exceptionReoportReason = exceptionReoportReason == null ? null : exceptionReoportReason.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Byte getStepSelectCode() {
		return stepSelectCode;
	}

	public void setStepSelectCode(Byte stepSelectCode) {
		this.stepSelectCode = stepSelectCode;
	}

	public String getStepSelect() {
		return stepSelect;
	}

	public void setStepSelect(String stepSelect) {
		this.stepSelect = stepSelect == null ? null : stepSelect.trim();
	}
	
	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getBranchGroupName() {
		return branchGroupName;
	}

	public void setBranchGroupName(String branchGroupName) {
		this.branchGroupName = branchGroupName == null ? null : branchGroupName.trim();
	}

	public String getSendCompany() {
		return sendCompany;
	}

	public void setSendCompany(String sendCompany) {
		this.sendCompany = sendCompany == null ? null : sendCompany.trim();
	}

	public String getPickupPlace() {
		return pickupPlace;
	}

	public void setPickupPlace(String pickupPlace) {
		this.pickupPlace = pickupPlace == null ? null : pickupPlace.trim();
	}

	public String getPickupPlaceAddress() {
		return pickupPlaceAddress;
	}

	public void setPickupPlaceAddress(String pickupPlaceAddress) {
		this.pickupPlaceAddress = pickupPlaceAddress == null ? null : pickupPlaceAddress.trim();
	}

	public String getReceiptCompany() {
		return receiptCompany;
	}

	public void setReceiptCompany(String receiptCompany) {
		this.receiptCompany = receiptCompany == null ? null : receiptCompany.trim();
	}

	public String getArrivePlace() {
		return arrivePlace;
	}

	public void setArrivePlace(String arrivePlace) {
		this.arrivePlace = arrivePlace == null ? null : arrivePlace.trim();
	}

	public String getArriveAddress() {
		return arriveAddress;
	}

	public void setArriveAddress(String arriveAddress) {
		this.arriveAddress = arriveAddress == null ? null : arriveAddress.trim();
	}

	public String getArriveFreightYrad() {
		return arriveFreightYrad;
	}

	public void setArriveFreightYrad(String arriveFreightYrad) {
		this.arriveFreightYrad = arriveFreightYrad == null ? null : arriveFreightYrad.trim();
	}

	public String getArriveFreightSite() {
		return arriveFreightSite;
	}

	public void setArriveFreightSite(String arriveFreightSite) {
		this.arriveFreightSite = arriveFreightSite == null ? null : arriveFreightSite.trim();
	}

	public String getCarrierVehicleId() {
		return carrierVehicleId;
	}

	public void setCarrierVehicleId(String carrierVehicleId) {
		this.carrierVehicleId = carrierVehicleId == null ? null : carrierVehicleId.trim();
	}

	public String getCarrierVehicleName() {
		return carrierVehicleName;
	}

	public void setCarrierVehicleName(String carrierVehicleName) {
		this.carrierVehicleName = carrierVehicleName == null ? null : carrierVehicleName.trim();
	}

	public String getCarPlateNumber() {
		return carPlateNumber;
	}

	public void setCarPlateNumber(String carPlateNumber) {
		this.carPlateNumber = carPlateNumber == null ? null : carPlateNumber.trim();
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType == null ? null : carType.trim();
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName == null ? null : driverName.trim();
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone == null ? null : driverPhone.trim();
	}
	
	public String getContainerNumber1() {
		return containerNumber1;
	}

	public void setContainerNumber1(String containerNumber1) {
		this.containerNumber1 = containerNumber1;
	}

	public String getContainerNumber2() {
		return containerNumber2;
	}

	public void setContainerNumber2(String containerNumber2) {
		this.containerNumber2 = containerNumber2 == null ? null : containerNumber2.trim();
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName == null ? null : cargoName.trim();
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications == null ? null : specifications.trim();
	}

	public BigDecimal getShortBargeCost() {
		return shortBargeCost;
	}

	public void setShortBargeCost(BigDecimal shortBargeCost) {
		this.shortBargeCost = shortBargeCost;
	}

	public BigDecimal getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(BigDecimal subsidy) {
		this.subsidy = subsidy;
	}

	public BigDecimal getDeductionPrice() {
		return deductionPrice;
	}

	public void setDeductionPrice(BigDecimal deductionPrice) {
		this.deductionPrice = deductionPrice;
	}

	public BigDecimal getSendTare() {
		return sendTare;
	}

	public void setSendTare(BigDecimal sendTare) {
		this.sendTare = sendTare;
	}

	public BigDecimal getSendGross() {
		return sendGross;
	}

	public void setSendGross(BigDecimal sendGross) {
		this.sendGross = sendGross;
	}

	public BigDecimal getContainerOneSendNet() {
		return containerOneSendNet;
	}

	public void setContainerOneSendNet(BigDecimal containerOneSendNet) {
		this.containerOneSendNet = containerOneSendNet;
	}

	public BigDecimal getContainerTwoSendNet() {
		return containerTwoSendNet;
	}

	public void setContainerTwoSendNet(BigDecimal containerTwoSendNet) {
		this.containerTwoSendNet = containerTwoSendNet;
	}

	public String getTestIndicators() {
		return testIndicators;
	}

	public void setTestIndicators(String testIndicators) {
		this.testIndicators = testIndicators == null ? null : testIndicators.trim();
	}

	public String getOrderImg() {
		return orderImg;
	}

	public void setOrderImg(String orderImg) {
		this.orderImg = orderImg == null ? null : orderImg.trim();
	}

	public String getDistributionCargoPlace() {
		return distributionCargoPlace;
	}

	public void setDistributionCargoPlace(String distributionCargoPlace) {
		this.distributionCargoPlace = distributionCargoPlace == null ? null : distributionCargoPlace.trim();
	}

	public String getDistributionCargoSite() {
		return distributionCargoSite;
	}

	public void setDistributionCargoSite(String distributionCargoSite) {
		this.distributionCargoSite = distributionCargoSite == null ? null : distributionCargoSite.trim();
	}

	public BigDecimal getReceiptTare() {
		return receiptTare;
	}

	public void setReceiptTare(BigDecimal receiptTare) {
		this.receiptTare = receiptTare;
	}

	public BigDecimal getReceiptGross() {
		return receiptGross;
	}

	public void setReceiptGross(BigDecimal receiptGross) {
		this.receiptGross = receiptGross;
	}

	public BigDecimal getContainerOneReceiptNet() {
		return containerOneReceiptNet;
	}

	public void setContainerOneReceiptNet(BigDecimal containerOneReceiptNet) {
		this.containerOneReceiptNet = containerOneReceiptNet;
	}

	public BigDecimal getContainerTwoReceiptNet() {
		return containerTwoReceiptNet;
	}

	public void setContainerTwoReceiptNet(BigDecimal containerTwoReceiptNet) {
		this.containerTwoReceiptNet = containerTwoReceiptNet;
	}

	public String getReceiptTestIndicators() {
		return receiptTestIndicators;
	}

	public void setReceiptTestIndicators(String receiptTestIndicators) {
		this.receiptTestIndicators = receiptTestIndicators == null ? null : receiptTestIndicators.trim();
	}

	public String getArriveredImg() {
		return arriveredImg;
	}

	public void setArriveredImg(String arriveredImg) {
		this.arriveredImg = arriveredImg == null ? null : arriveredImg.trim();
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public Integer getPieceNumber() {
		return pieceNumber;
	}

	public void setPieceNumber(Integer pieceNumber) {
		this.pieceNumber = pieceNumber;
	}

	public Byte getValuationUnitType() {
		return valuationUnitType;
	}

	public void setValuationUnitType(Byte valuationUnitType) {
		this.valuationUnitType = valuationUnitType;
	}

	public Byte getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Byte isCancel) {
		this.isCancel = isCancel;
	}

	public Integer getCarteamId() {
		return carteamId;
	}

	public void setCarteamId(Integer carteamId) {
		this.carteamId = carteamId;
	}

	public Integer getPlaceNowId() {
		return placeNowId;
	}

	public void setPlaceNowId(Integer placeNowId) {
		this.placeNowId = placeNowId;
	}

	public Byte getOrderOrigin() {
		return orderOrigin;
	}

	public void setOrderOrigin(Byte orderOrigin) {
		this.orderOrigin = orderOrigin;
	}

	public Integer getDeletorId() {
		return deletorId;
	}

	public void setDeletorId(Integer deletorId) {
		this.deletorId = deletorId;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason == null ? null : deleteReason.trim();
	}

	public Byte getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getCancelReasonDetail() {
		return cancelReasonDetail;
	}

	public void setCancelReasonDetail(String cancelReasonDetail) {
		this.cancelReasonDetail = cancelReasonDetail;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public Byte getTransportType() {
		return transportType;
	}

	public void setTransportType(Byte transportType) {
		this.transportType = transportType;
	}

	public Integer getProjectDistributionId() {
		return projectDistributionId;
	}

	public void setProjectDistributionId(Integer projectDistributionId) {
		this.projectDistributionId = projectDistributionId;
	}

	public BigDecimal getDeductionRate() {
		return deductionRate;
	}

	public void setDeductionRate(BigDecimal deductionRate) {
		this.deductionRate = deductionRate;
	}

	public Date getReceipterDate() {
		return receipterDate;
	}

	public void setReceipterDate(Date receipterDate) {
		this.receipterDate = receipterDate;
	}

	public Integer getTakeCargoPlaceId() {
		return takeCargoPlaceId;
	}

	public void setTakeCargoPlaceId(Integer takeCargoPlaceId) {
		this.takeCargoPlaceId = takeCargoPlaceId;
	}

	public String getTakeCarogoPlaceName() {
		return takeCarogoPlaceName;
	}

	public void setTakeCarogoPlaceName(String takeCarogoPlaceName) {
		this.takeCarogoPlaceName = takeCarogoPlaceName;
	}

	public Integer getTakeCargoSiteId() {
		return takeCargoSiteId;
	}

	public void setTakeCargoSiteId(Integer takeCargoSiteId) {
		this.takeCargoSiteId = takeCargoSiteId;
	}

	public String getTakeCargoSiteName() {
		return takeCargoSiteName;
	}

	public void setTakeCargoSiteName(String takeCargoSiteName) {
		this.takeCargoSiteName = takeCargoSiteName;
	}

	public Date getTakeDeliveryDate() {
		return takeDeliveryDate;
	}

	public void setTakeDeliveryDate(Date takeDeliveryDate) {
		this.takeDeliveryDate = takeDeliveryDate;
	}

	public Integer getDistributionCargoPlaceId() {
		return distributionCargoPlaceId;
	}

	public void setDistributionCargoPlaceId(Integer distributionCargoPlaceId) {
		this.distributionCargoPlaceId = distributionCargoPlaceId;
	}

	public Integer getDistributionCargoSiteId() {
		return distributionCargoSiteId;
	}

	public void setDistributionCargoSiteId(Integer distributionCargoSiteId) {
		this.distributionCargoSiteId = distributionCargoSiteId;
	}

	public void setProjectType(Byte projectType) {
		this.projectType = projectType;
	}

	public void setContainerNumber1Id(String containerNumber1Id) {
		this.containerNumber1Id = containerNumber1Id;
	}

	public void setContainerNumber2Id(String containerNumber2Id) {
		this.containerNumber2Id = containerNumber2Id;
	}

	public String getShOrderFinId() {
		return shOrderFinId;
	}

	public void setShOrderFinId(String shOrderFinId) {
		this.shOrderFinId = shOrderFinId;
	}
	
}