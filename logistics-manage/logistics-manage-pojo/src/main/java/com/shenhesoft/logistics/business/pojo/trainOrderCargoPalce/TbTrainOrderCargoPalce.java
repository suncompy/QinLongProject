package com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/** 火运 装车信息*/
public class TbTrainOrderCargoPalce implements  Comparable<TbTrainOrderCargoPalce>, Serializable{
    /**
	 * @description serialVersionUID
	*/
	private static final long serialVersionUID = -6615015602414389924L;

	/** */
    private Integer id;

    /** 火运订单id*/
    private Integer trainOrderId;

    /** 货场id*/
    private Integer cargoPlaceId;

    /** 货场*/
    private String cargoPlaceName;

    /** 货位id*/
    private Integer cargoSiteId;

    /** 货位*/
    private String cargoSiteName;

    /** 车型id*/
    private Integer carTypeId;

    /** 车型*/
    private String carType;

    /** 车牌号*/
    private String carNumber;

    /** 集装箱号1*/
    private String containerNumber1;

    /** 集装箱号2*/
    private String containerNumber2;

    /** 发货载重*/
    private BigDecimal sendWeight;
    
    /** 集装箱号2发货载重*/
    private BigDecimal conSendWeight2;

    /** 到货载重*/
    private BigDecimal unloadWeight;

    /** 集装箱号2卸货载重*/
    private BigDecimal conUnloadWeight2;
    
    /** 运单上传*/
    private String sendImg;

    /** 到货运单上传*/
    private String unloadImg;

    /** 卸货货场id*/
    private Integer arriveCargoPlaceId;

    /** 卸货货场名称*/
    private String arriveCargoPlaceName;

    /** 卸货货位id*/
    private Integer arriveCargoSiteId;

    /** 卸货货位名称*/
    private String arriveCargoSiteName;

    /** 卸货时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date arriveUnloadTime;

    /** 删除标志：0未删除，1已删除*/
    private Byte deleteFlag;

    
    public BigDecimal getConSendWeight2() {
		return conSendWeight2;
	}

	public void setConSendWeight2(BigDecimal conSendWeight2) {
		this.conSendWeight2 = conSendWeight2;
	}

	public BigDecimal getConUnloadWeight2() {
		return conUnloadWeight2;
	}

	public void setConUnloadWeight2(BigDecimal conUnloadWeight2) {
		this.conUnloadWeight2 = conUnloadWeight2;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainOrderId() {
        return trainOrderId;
    }

    public void setTrainOrderId(Integer trainOrderId) {
        this.trainOrderId = trainOrderId;
    }

    public Integer getCargoPlaceId() {
        return cargoPlaceId;
    }

    public void setCargoPlaceId(Integer cargoPlaceId) {
        this.cargoPlaceId = cargoPlaceId;
    }

    public String getCargoPlaceName() {
        return cargoPlaceName;
    }

    public void setCargoPlaceName(String cargoPlaceName) {
        this.cargoPlaceName = cargoPlaceName == null ? null : cargoPlaceName.trim();
    }

    public Integer getCargoSiteId() {
        return cargoSiteId;
    }

    public void setCargoSiteId(Integer cargoSiteId) {
        this.cargoSiteId = cargoSiteId;
    }

    public String getCargoSiteName() {
        return cargoSiteName;
    }

    public void setCargoSiteName(String cargoSiteName) {
        this.cargoSiteName = cargoSiteName == null ? null : cargoSiteName.trim();
    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public String getContainerNumber1() {
        return containerNumber1;
    }

    public void setContainerNumber1(String containerNumber1) {
        this.containerNumber1 = containerNumber1 == null ? null : containerNumber1.trim();
    }

    public String getContainerNumber2() {
        return containerNumber2;
    }

    public void setContainerNumber2(String containerNumber2) {
        this.containerNumber2 = containerNumber2 == null ? null : containerNumber2.trim();
    }

    public BigDecimal getSendWeight() {
        return sendWeight;
    }

    public void setSendWeight(BigDecimal sendWeight) {
        this.sendWeight = sendWeight;
    }

    public BigDecimal getUnloadWeight() {
        return unloadWeight;
    }

    public void setUnloadWeight(BigDecimal unloadWeight) {
        this.unloadWeight = unloadWeight;
    }

    public String getSendImg() {
        return sendImg;
    }

    public void setSendImg(String sendImg) {
        this.sendImg = sendImg == null ? null : sendImg.trim();
    }

    public String getUnloadImg() {
        return unloadImg;
    }

    public void setUnloadImg(String unloadImg) {
        this.unloadImg = unloadImg == null ? null : unloadImg.trim();
    }

    public Integer getArriveCargoPlaceId() {
        return arriveCargoPlaceId;
    }

    public void setArriveCargoPlaceId(Integer arriveCargoPlaceId) {
        this.arriveCargoPlaceId = arriveCargoPlaceId;
    }

    public String getArriveCargoPlaceName() {
        return arriveCargoPlaceName;
    }

    public void setArriveCargoPlaceName(String arriveCargoPlaceName) {
        this.arriveCargoPlaceName = arriveCargoPlaceName == null ? null : arriveCargoPlaceName.trim();
    }

    public Integer getArriveCargoSiteId() {
        return arriveCargoSiteId;
    }

    public void setArriveCargoSiteId(Integer arriveCargoSiteId) {
        this.arriveCargoSiteId = arriveCargoSiteId;
    }

    public String getArriveCargoSiteName() {
        return arriveCargoSiteName;
    }

    public void setArriveCargoSiteName(String arriveCargoSiteName) {
        this.arriveCargoSiteName = arriveCargoSiteName == null ? null : arriveCargoSiteName.trim();
    }

    public Date getArriveUnloadTime() {
        return arriveUnloadTime;
    }

    public void setArriveUnloadTime(Date arriveUnloadTime) {
        this.arriveUnloadTime = arriveUnloadTime;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

	@Override
	public int compareTo(TbTrainOrderCargoPalce o) {
		if (o == null) {
	        return 1;
        }
        if (o.getCargoPlaceId().intValue() == this.cargoPlaceId.intValue() && o.getCargoSiteId().intValue() == this.cargoSiteId.intValue()) {
            return 0;
        }
        return 1;
	}
	public boolean equals(TbTrainOrderCargoPalce o) {
        if (o == null) {
            return false;
        }
        if (this.compareTo(o) == 0) {
            return true;
        }
        return false;
    }
 
    public TbTrainOrderCargoPalce clone() {
        return this.clone();
    }
}