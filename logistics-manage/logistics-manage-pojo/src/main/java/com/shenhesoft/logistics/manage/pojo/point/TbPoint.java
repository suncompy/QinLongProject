package com.shenhesoft.logistics.manage.pojo.point;

import java.io.Serializable;

public class TbPoint implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2273792440703371247L;

	/** */
    private Integer id;

    /** 主要指标*/
    private String pointName;

    /** 指标界定最小值*/
    private Integer pointMin;

    /** 指标界定最大值*/
    private Integer pointMax;

    /** 指标类型
            0：主要指标
            1:其他指标*/
    private Byte type;

    /** 货物id*/
    private Integer cargoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName == null ? null : pointName.trim();
    }

    public Integer getPointMin() {
        return pointMin;
    }

    public void setPointMin(Integer pointMin) {
        this.pointMin = pointMin;
    }

    public Integer getPointMax() {
        return pointMax;
    }

    public void setPointMax(Integer pointMax) {
        this.pointMax = pointMax;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getCargoId() {
        return cargoId;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }
}