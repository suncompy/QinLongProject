package com.shenhesoft.logistics.manage.pojo.project;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shenhesoft.logistics.business.helpPojo.TbProjectDetail;

public class TbProject implements Serializable , Comparable<TbProjectDetail>{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5825832911021171099L;

	/** */
    protected Integer id;

    /** 项目编号*/
    private String projectCode;

    /** 分支机构id*/
    @NotNull(message="请选择分支机构")
    private Integer branchGroupId;

    /** 分支机构名称*/
    @NotBlank(message="请选择分支机构")
    private String branchGroupName;

    /** 项目类型
            0 集装箱
            1 散装*/
    @NotNull(message="请选择项目类型")
    private Byte projectType;

    /** 联运模式
            0 汽运
            1 接取
            2 送达
            3 火运
            4 接取+火运
            5 火运+送达
            6 联运 
            7接取+送达*/
    @NotNull(message="请选择联运模式")
    @Range(min=0, max=7)
    private Byte transportType;

    /** 货物id*/
    @NotNull(message="请选择货物")
    private Integer cargoId;

    /** 货物名称*/
    @NotBlank(message="请选择货物")
    private String cargoName;

    /** 规格*/
    @NotBlank(message="请选择规格")
    private String cargoSpecifications;

    /** 货物价格*/
    @NotNull(message="货物单价不能为空")
    private BigDecimal cargoPrice;

    /** 计价单位
            1 吨
            0 件*/
    @NotNull(message="请选择计价单位")
    private Byte valuationUnitName;

    /** 发货企业id*/
    @NotNull(message="请选择发货企业")
    private Integer sendCargoCompanyId;

    /** 发货企业名称*/
    @NotBlank(message="请选择发货企业")
    private String sendCargoCompanyName;

    /** 收货企业id*/
    @NotNull(message="请选择收货企业")
    private Integer receiveCargoCompanyId;

    /** 收货企业名称*/
    @NotBlank(message="请选择收货企业")
    private String receiveCargoCompanyName;

    /** 发货单位id*/
    private Integer sendCargoUnitId;

    /** 发货单位*/
    private String sendCargoUnitName;
    
    /** 收货单位id*/
    private Integer receivingDepartmentId;

    /** 收货单位名称*/
    private String receivingDepartmentName;
    

    /** 收货站点id*/
    private Integer receiveCargoSiteId;

    /** 收货站点*/
    private String receiveCargoSite;

    /** 接取:到达中心站*/
    private Integer receiveCenterCargoSiteId;

    /** 接取:收货站点货场*/
    private Integer receiveCargoSiteFreightYardId;
    
    /** 短驳承运方式
            0 平台
            1 自选*/
    /**
     *接取  短驳承运方式
     */
    private Byte shortBargeCarrierMode;

    /** 短驳承运方id*/
    private Integer shortBargeCarrierId;

    /**  短驳承运方*/
    private String shortBargeCarrierName;
    
    /** 送达 短驳承运方*/
    private Byte sendShortBargeCarrierMode;
    
    /** 起运中心站id*/
    private Integer beginCenterSiteId;

    /** 起运中心站*/
    private String beginCenterSiteName;
    
    /** 始发站点id*/
    private Integer beginSiteId;

    /** 始发站点*/
    private String beginSiteName;

    /** 始发站点货场*/
    private String beginSiteFreightYard;
    
    /** 始发地*/
    private String beginAddress;

    /** 到达中心站id*/
    private Integer endCenterSiteId;

    /** 到达中心站*/
    private String endCenterSiteName;

    /** 到站站点id*/
    private Integer endSiteId;

    /** 到达站点*/
    private String endSiteName;
    
    /** 到达站点货场*/
    private String endSiteFreightYard;

    /** 到达地*/
    private String endAddress;

    /** 运费核计*/
    private BigDecimal freight;

    /** 材料费*/
    private BigDecimal materialCost;

    /** 篷布使用费*/
    private BigDecimal tarpaulinCost;

    /** 发站装卸费*/
    private BigDecimal beginStevedoringCost;

    /** 到站装卸费*/
    private BigDecimal endStevedoringCost;

    /** 运费合计*/
    private BigDecimal freightSum;

    /** 接取站点id*/
    private Integer forwardingSiteId;

    /** 接取站点名称*/
    private String forwardingSiteName;
    
    /**  送达:发货中心站*/
    private Integer forwardingCenterSiteId;

    /** 送达:接取站点货场*/
    private Integer forwardingSiteFreightYardId;

    /** 送达:发货单位id */
    private Integer forwardingUnitId;

    /** 发货单位名称*/
    private String forwardingUnitName;

    /** 取货地*/
    private String takePlace;

    /** 取货地址*/
    private String takePlaceDetail;

    /** 运抵地*/
    private String arrivePlace;

    /** 运抵地址*/
    private String arrivePlaceAddress;

    /** 接取单价*/
    private BigDecimal pickUpPrice;

    /** 火运单价*/
    private BigDecimal trainPrice;

    /** 送达单价*/
    private BigDecimal arrivePrice;

    /** 汽运单价*/
    private BigDecimal transportPrice;

    /** 备注*/
    private String remark;

    private String projectDistributionId;
    
    /** 创建时间*/
    @JsonFormat(pattern="yyyy.MM.dd HH:mm",timezone="GMT+8")
    private Date createDate;

    /** 更新时间*/
    @JsonFormat(pattern="yyyy.MM.dd HH:mm",timezone="GMT+8")
    private Date editDate;

    /** 项目状态
            0  未过期取消(24小时之内可以在历史回收站可以看到的)
            1 正在运行
            2 已完成
            3 未使用*/
    private Byte status;

    /** 创建人id*/
    private Integer creatorId;

    /** 委托方 0收货企业 1发货企业*/
    @NotNull(message="委托方不能为空")
    private Byte principal;

    /** 是否进行任务分配：  1开始  0暂停*/
    private Byte isDistribution;

    /** 删除标识  0未删除 1 删除*/
    private Byte deleteFlag;

    /** 完成时间*/
    @JsonFormat(pattern="yyyy.MM.dd HH:mm",timezone="GMT+8")
    private Date finishDate;
    
    public Integer getReceiveCenterCargoSiteId() {
		return receiveCenterCargoSiteId;
	}

	public void setReceiveCenterCargoSiteId(Integer receiveCenterCargoSiteId) {
		this.receiveCenterCargoSiteId = receiveCenterCargoSiteId;
	}

	public Integer getReceiveCargoSiteFreightYardId() {
		return receiveCargoSiteFreightYardId;
	}

	public void setReceiveCargoSiteFreightYardId(Integer receiveCargoSiteFreightYardId) {
		this.receiveCargoSiteFreightYardId = receiveCargoSiteFreightYardId;
	}

	public Integer getForwardingCenterSiteId() {
		return forwardingCenterSiteId;
	}

	public void setForwardingCenterSiteId(Integer forwardingCenterSiteId) {
		this.forwardingCenterSiteId = forwardingCenterSiteId;
	}

	public Integer getForwardingSiteFreightYardId() {
		return forwardingSiteFreightYardId;
	}

	public void setForwardingSiteFreightYardId(Integer forwardingSiteFreightYardId) {
		this.forwardingSiteFreightYardId = forwardingSiteFreightYardId;
	}

	public String getProjectDistributionId() {
		return projectDistributionId;
	}

	public void setProjectDistributionId(String projectDistributionId) {
		this.projectDistributionId = projectDistributionId;
	}

	public String getBeginSiteFreightYard() {
		return beginSiteFreightYard;
	}

	public void setBeginSiteFreightYard(String beginSiteFreightYard) {
		this.beginSiteFreightYard = beginSiteFreightYard;
	}

	public String getEndSiteFreightYard() {
		return endSiteFreightYard;
	}

	public void setEndSiteFreightYard(String endSiteFreightYard) {
		this.endSiteFreightYard = endSiteFreightYard;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public Byte getSendShortBargeCarrierMode() {
		return sendShortBargeCarrierMode;
	}

	public void setSendShortBargeCarrierMode(Byte sendShortBargeCarrierMode) {
		this.sendShortBargeCarrierMode = sendShortBargeCarrierMode;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public Integer getBranchGroupId() {
        return branchGroupId;
    }

    public void setBranchGroupId(Integer branchGroupId) {
        this.branchGroupId = branchGroupId;
    }

    public String getBranchGroupName() {
        return branchGroupName;
    }

    public void setBranchGroupName(String branchGroupName) {
        this.branchGroupName = branchGroupName == null ? null : branchGroupName.trim();
    }

    public Byte getProjectType() {
        return projectType;
    }

    public void setProjectType(Byte projectType) {
        this.projectType = projectType;
    }

    public Byte getTransportType() {
        return transportType;
    }

    public void setTransportType(Byte transportType) {
        this.transportType = transportType;
    }

    public Integer getCargoId() {
        return cargoId;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
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

    public BigDecimal getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice(BigDecimal cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public Byte getValuationUnitName() {
        return valuationUnitName;
    }

    public void setValuationUnitName(Byte valuationUnitName) {
        this.valuationUnitName = valuationUnitName;
    }

    public Integer getSendCargoCompanyId() {
        return sendCargoCompanyId;
    }

    public void setSendCargoCompanyId(Integer sendCargoCompanyId) {
        this.sendCargoCompanyId = sendCargoCompanyId;
    }

    public String getSendCargoCompanyName() {
        return sendCargoCompanyName;
    }

    public void setSendCargoCompanyName(String sendCargoCompanyName) {
        this.sendCargoCompanyName = sendCargoCompanyName == null ? null : sendCargoCompanyName.trim();
    }

    public Integer getReceiveCargoCompanyId() {
        return receiveCargoCompanyId;
    }

    public void setReceiveCargoCompanyId(Integer receiveCargoCompanyId) {
        this.receiveCargoCompanyId = receiveCargoCompanyId;
    }

    public String getReceiveCargoCompanyName() {
        return receiveCargoCompanyName;
    }

    public void setReceiveCargoCompanyName(String receiveCargoCompanyName) {
        this.receiveCargoCompanyName = receiveCargoCompanyName == null ? null : receiveCargoCompanyName.trim();
    }

    public Integer getSendCargoUnitId() {
        return sendCargoUnitId;
    }

    public void setSendCargoUnitId(Integer sendCargoUnitId) {
        this.sendCargoUnitId = sendCargoUnitId;
    }

    public String getSendCargoUnitName() {
        return sendCargoUnitName;
    }

    public void setSendCargoUnitName(String sendCargoUnitName) {
        this.sendCargoUnitName = sendCargoUnitName == null ? null : sendCargoUnitName.trim();
    }

    public Integer getReceiveCargoSiteId() {
        return receiveCargoSiteId;
    }

    public void setReceiveCargoSiteId(Integer receiveCargoSiteId) {
        this.receiveCargoSiteId = receiveCargoSiteId;
    }

    public String getReceiveCargoSite() {
        return receiveCargoSite;
    }

    public void setReceiveCargoSite(String receiveCargoSite) {
        this.receiveCargoSite = receiveCargoSite == null ? null : receiveCargoSite.trim();
    }

    public Byte getShortBargeCarrierMode() {
        return shortBargeCarrierMode;
    }

    public void setShortBargeCarrierMode(Byte shortBargeCarrierMode) {
        this.shortBargeCarrierMode = shortBargeCarrierMode;
    }

    public Integer getShortBargeCarrierId() {
        return shortBargeCarrierId;
    }

    public void setShortBargeCarrierId(Integer shortBargeCarrierId) {
        this.shortBargeCarrierId = shortBargeCarrierId;
    }

    public String getShortBargeCarrierName() {
        return shortBargeCarrierName;
    }

    public void setShortBargeCarrierName(String shortBargeCarrierName) {
        this.shortBargeCarrierName = shortBargeCarrierName == null ? null : shortBargeCarrierName.trim();
    }

    public Integer getBeginCenterSiteId() {
        return beginCenterSiteId;
    }

    public void setBeginCenterSiteId(Integer beginCenterSiteId) {
        this.beginCenterSiteId = beginCenterSiteId;
    }

    public String getBeginCenterSiteName() {
        return beginCenterSiteName;
    }

    public void setBeginCenterSiteName(String beginCenterSiteName) {
        this.beginCenterSiteName = beginCenterSiteName == null ? null : beginCenterSiteName.trim();
    }

    public Integer getBeginSiteId() {
        return beginSiteId;
    }

    public void setBeginSiteId(Integer beginSiteId) {
        this.beginSiteId = beginSiteId;
    }

    public String getBeginSiteName() {
        return beginSiteName;
    }

    public void setBeginSiteName(String beginSiteName) {
        this.beginSiteName = beginSiteName == null ? null : beginSiteName.trim();
    }

    public String getBeginAddress() {
        return beginAddress;
    }

    public void setBeginAddress(String beginAddress) {
        this.beginAddress = beginAddress == null ? null : beginAddress.trim();
    }

    public Integer getEndCenterSiteId() {
        return endCenterSiteId;
    }

    public void setEndCenterSiteId(Integer endCenterSiteId) {
        this.endCenterSiteId = endCenterSiteId;
    }

    public String getEndCenterSiteName() {
        return endCenterSiteName;
    }

    public void setEndCenterSiteName(String endCenterSiteName) {
        this.endCenterSiteName = endCenterSiteName == null ? null : endCenterSiteName.trim();
    }

    public Integer getEndSiteId() {
        return endSiteId;
    }

    public void setEndSiteId(Integer endSiteId) {
        this.endSiteId = endSiteId;
    }

    public String getEndSiteName() {
        return endSiteName;
    }

    public void setEndSiteName(String endSiteName) {
        this.endSiteName = endSiteName == null ? null : endSiteName.trim();
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress == null ? null : endAddress.trim();
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getTarpaulinCost() {
        return tarpaulinCost;
    }

    public void setTarpaulinCost(BigDecimal tarpaulinCost) {
        this.tarpaulinCost = tarpaulinCost;
    }

    public BigDecimal getBeginStevedoringCost() {
        return beginStevedoringCost;
    }

    public void setBeginStevedoringCost(BigDecimal beginStevedoringCost) {
        this.beginStevedoringCost = beginStevedoringCost;
    }

    public BigDecimal getEndStevedoringCost() {
        return endStevedoringCost;
    }

    public void setEndStevedoringCost(BigDecimal endStevedoringCost) {
        this.endStevedoringCost = endStevedoringCost;
    }

    public BigDecimal getFreightSum() {
        return freightSum;
    }

    public void setFreightSum(BigDecimal freightSum) {
        this.freightSum = freightSum;
    }

    public Integer getForwardingSiteId() {
        return forwardingSiteId;
    }

    public void setForwardingSiteId(Integer forwardingSiteId) {
        this.forwardingSiteId = forwardingSiteId;
    }

    public String getForwardingSiteName() {
        return forwardingSiteName;
    }

    public void setForwardingSiteName(String forwardingSiteName) {
        this.forwardingSiteName = forwardingSiteName == null ? null : forwardingSiteName.trim();
    }

    public Integer getForwardingUnitId() {
        return forwardingUnitId;
    }

    public void setForwardingUnitId(Integer forwardingUnitId) {
        this.forwardingUnitId = forwardingUnitId;
    }

    public String getForwardingUnitName() {
        return forwardingUnitName;
    }

    public void setForwardingUnitName(String forwardingUnitName) {
        this.forwardingUnitName = forwardingUnitName == null ? null : forwardingUnitName.trim();
    }

    public String getTakePlace() {
        return takePlace;
    }

    public void setTakePlace(String takePlace) {
        this.takePlace = takePlace == null ? null : takePlace.trim();
    }

    public String getTakePlaceDetail() {
        return takePlaceDetail;
    }

    public void setTakePlaceDetail(String takePlaceDetail) {
        this.takePlaceDetail = takePlaceDetail == null ? null : takePlaceDetail.trim();
    }

    public Integer getReceivingDepartmentId() {
        return receivingDepartmentId;
    }

    public void setReceivingDepartmentId(Integer receivingDepartmentId) {
        this.receivingDepartmentId = receivingDepartmentId;
    }

    public String getReceivingDepartmentName() {
        return receivingDepartmentName;
    }

    public void setReceivingDepartmentName(String receivingDepartmentName) {
        this.receivingDepartmentName = receivingDepartmentName == null ? null : receivingDepartmentName.trim();
    }

    public String getArrivePlace() {
        return arrivePlace;
    }

    public void setArrivePlace(String arrivePlace) {
        this.arrivePlace = arrivePlace == null ? null : arrivePlace.trim();
    }

    public String getArrivePlaceAddress() {
        return arrivePlaceAddress;
    }

    public void setArrivePlaceAddress(String arrivePlaceAddress) {
        this.arrivePlaceAddress = arrivePlaceAddress == null ? null : arrivePlaceAddress.trim();
    }

    public BigDecimal getPickUpPrice() {
        return pickUpPrice;
    }

    public void setPickUpPrice(BigDecimal pickUpPrice) {
        this.pickUpPrice = pickUpPrice;
    }

    public BigDecimal getTrainPrice() {
        return trainPrice;
    }

    public void setTrainPrice(BigDecimal trainPrice) {
        this.trainPrice = trainPrice;
    }

    public BigDecimal getArrivePrice() {
        return arrivePrice;
    }

    public void setArrivePrice(BigDecimal arrivePrice) {
        this.arrivePrice = arrivePrice;
    }

    public BigDecimal getTransportPrice() {
        return transportPrice;
    }

    public void setTransportPrice(BigDecimal transportPrice) {
        this.transportPrice = transportPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Byte getPrincipal() {
        return principal;
    }

    public void setPrincipal(Byte principal) {
        this.principal = principal;
    }

    public Byte getIsDistribution() {
        return isDistribution;
    }

    public void setIsDistribution(Byte isDistribution) {
        this.isDistribution = isDistribution;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TbProjectDetail o) {
		  return  this.id.compareTo(o.id);
	}
}