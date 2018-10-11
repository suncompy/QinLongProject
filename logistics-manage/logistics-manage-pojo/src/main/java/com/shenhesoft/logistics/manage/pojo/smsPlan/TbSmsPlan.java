package com.shenhesoft.logistics.manage.pojo.smsPlan;

import java.io.Serializable;

public class TbSmsPlan implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6841130041931182763L;

	/** */
    private Integer id;

    /** 名称*/
    private String name;

    /** 发送节点
（
   1：   等待调度
   2：等待发运
   3  ：在途运载
   4：货位引导
   5 ：等待回单
   6：等待计费
   7：已完成
）*/
    private Byte point;

    /** 短信模板id*/
    private Integer smsModelId;

    /** 短信模板code*/
    private String smsModelCode;

    /** 接受方id*/
    private Integer receiveId;

    /** */
    private String remark;

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

    public Byte getPoint() {
        return point;
    }

    public void setPoint(Byte point) {
        this.point = point;
    }

    public Integer getSmsModelId() {
        return smsModelId;
    }

    public void setSmsModelId(Integer smsModelId) {
        this.smsModelId = smsModelId;
    }

    public String getSmsModelCode() {
        return smsModelCode;
    }

    public void setSmsModelCode(String smsModelCode) {
        this.smsModelCode = smsModelCode == null ? null : smsModelCode.trim();
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}