package com.shenhesoft.logistics.manage.pojo.box;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class TbContainer implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7758344964046250966L;

	/** id*/
    private Integer id;

    /** 箱型*/
    @NotNull
    private Integer containerTypeId;

    /** 编码*/
    @NotBlank
    private String containerNum;

    /** 箱东*/
    @NotBlank
    private String eastContainer;

    /** 箱类*/
    @NotBlank
    private String containerKind;

    /** 箱码*/
    @NotBlank
    private String containerCode;

    /** 集装箱箱号*/
    @NotBlank
    private String containerId;

    /** 长*/
    @NotNull
    private Integer length;

    /** 宽*/
    @NotNull
    private Integer width;

    /** 高*/
    @NotNull
    private Integer hight;

    /** 尺寸*/
    @NotNull
    private Integer size;

    /** 容积*/
    @NotNull
    private Integer volume;

    /** 自重*/
    @NotNull
    private Integer selfWeight;

    /** 载重*/
    @NotNull
    private Integer weight;

    /** 集装箱站点*/
    private Integer trainLocationId;
    
    /** 集装箱状态： 0空闲，1使用中，2运输中*/
    private Integer status;

    /** 项目id*/
    private Integer projectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContainerTypeId() {
        return containerTypeId;
    }

    public void setContainerTypeId(Integer containerTypeId) {
        this.containerTypeId = containerTypeId;
    }

    public String getContainerNum() {
        return containerNum;
    }

    public void setContainerNum(String containerNum) {
        this.containerNum = containerNum == null ? null : containerNum.trim();
    }

    public String getEastContainer() {
        return eastContainer;
    }

    public void setEastContainer(String eastContainer) {
        this.eastContainer = eastContainer == null ? null : eastContainer.trim();
    }

    public String getContainerKind() {
        return containerKind;
    }

    public void setContainerKind(String containerKind) {
        this.containerKind = containerKind == null ? null : containerKind.trim();
    }

    public String getContainerCode() {
        return containerCode;
    }

    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode == null ? null : containerCode.trim();
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId == null ? null : containerId.trim();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHight() {
        return hight;
    }

    public void setHight(Integer hight) {
        this.hight = hight;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getSelfWeight() {
        return selfWeight;
    }

    public void setSelfWeight(Integer selfWeight) {
        this.selfWeight = selfWeight;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getTrainLocationId() {
        return trainLocationId;
    }

    public void setTrainLocationId(Integer trainLocationId) {
        this.trainLocationId = trainLocationId;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "TbContainer [id=" + id + ", containerTypeId=" + containerTypeId + ", containerNum=" + containerNum
				+ ", eastContainer=" + eastContainer + ", containerKind=" + containerKind + ", containerCode="
				+ containerCode + ", containerId=" + containerId + ", length=" + length + ", width=" + width
				+ ", hight=" + hight + ", size=" + size + ", volume=" + volume + ", selfWeight=" + selfWeight
				+ ", weight=" + weight + ", trainLocationId=" + trainLocationId + ", status=" + status + ", projectId="
				+ projectId + "]";
	}

}