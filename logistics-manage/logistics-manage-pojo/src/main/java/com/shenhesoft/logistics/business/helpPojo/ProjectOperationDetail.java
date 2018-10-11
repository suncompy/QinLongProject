package com.shenhesoft.logistics.business.helpPojo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.shenhesoft.logistics.manage.pojo.project.TbProject;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月25日
 */
public class ProjectOperationDetail extends TbProject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3329145215713809197L;
	
	/**
	 * 接取提货吨位
	 */
	private BigDecimal receiveGetCargoWeight;
	/**
	 * 接取提货车次
	 */
	private Integer receiveGetCargoCarNum;
	/**
	 * 接取到货吨位
	 */
	private BigDecimal receiveArriveCargoWeight;
	/**
	 * 接取到货车次
	 */
	private Integer receiveArriveCargoCarNum;
	
	/**
	 * 火运发货吨位
	 */
	private BigDecimal trainSendCargoWeight;
	/**
	 * 火运发货车次
	 */
	private Integer trainSendCargoTrainNum;
	
	/**
	 * 火运到货吨位
	 */
	private BigDecimal trainArriveCargoWeight;
	/**
	 * 火运到货车次
	 */
	private Integer trainArriveCargoTrainNum;
	
	/**
	 * 送达提货吨位
	 */
	private BigDecimal sendGetCargoWeight;
	/**
	 * 送达提货车次
	 */
	private Integer sendGetCargoCarNum;
	
	/**
	 * 送达到货吨位
	 */
	private BigDecimal sendArriveCargoWeight;

	/**
	 * 送达到货车次
	 */
	private Integer sendArriveCargoNum;
	
	/**
	 * 运输完成吨位
	 */
	private BigDecimal finishCargoWeight;

	/**
	 * 在途运输吨位
	 */
	private BigDecimal runningCargoWeight;

	/**
	 * 中转库存吨位
	 */
	private BigDecimal transitCargoWeight;
	
	public BigDecimal getReceiveGetCargoWeight() {
		return receiveGetCargoWeight;
	}
	public void setReceiveGetCargoWeight(BigDecimal receiveGetCargoWeight) {
		this.receiveGetCargoWeight = receiveGetCargoWeight;
	}
	public Integer getReceiveGetCargoCarNum() {
		return receiveGetCargoCarNum;
	}
	public void setReceiveGetCargoCarNum(Integer receiveGetCargoCarNum) {
		this.receiveGetCargoCarNum = receiveGetCargoCarNum;
	}
	public BigDecimal getReceiveArriveCargoWeight() {
		return receiveArriveCargoWeight;
	}
	public void setReceiveArriveCargoWeight(BigDecimal receiveArriveCargoWeight) {
		this.receiveArriveCargoWeight = receiveArriveCargoWeight;
	}
	public Integer getReceiveArriveCargoCarNum() {
		return receiveArriveCargoCarNum;
	}
	public void setReceiveArriveCargoCarNum(Integer receiveArriveCargoCarNum) {
		this.receiveArriveCargoCarNum = receiveArriveCargoCarNum;
	}
	public BigDecimal getTrainSendCargoWeight() {
		return trainSendCargoWeight;
	}
	public void setTrainSendCargoWeight(BigDecimal trainSendCargoWeight) {
		this.trainSendCargoWeight = trainSendCargoWeight;
	}
	public Integer getTrainSendCargoTrainNum() {
		return trainSendCargoTrainNum;
	}
	public void setTrainSendCargoTrainNum(Integer trainSendCargoTrainNum) {
		this.trainSendCargoTrainNum = trainSendCargoTrainNum;
	}
	public BigDecimal getTrainArriveCargoWeight() {
		return trainArriveCargoWeight;
	}
	public void setTrainArriveCargoWeight(BigDecimal trainArriveCargoWeight) {
		this.trainArriveCargoWeight = trainArriveCargoWeight;
	}
	public Integer getTrainArriveCargoTrainNum() {
		return trainArriveCargoTrainNum;
	}
	public void setTrainArriveCargoTrainNum(Integer trainArriveCargoTrainNum) {
		this.trainArriveCargoTrainNum = trainArriveCargoTrainNum;
	}
	public BigDecimal getSendGetCargoWeight() {
		return sendGetCargoWeight;
	}
	public void setSendGetCargoWeight(BigDecimal sendGetCargoWeight) {
		this.sendGetCargoWeight = sendGetCargoWeight;
	}
	public Integer getSendGetCargoCarNum() {
		return sendGetCargoCarNum;
	}
	public void setSendGetCargoCarNum(Integer sendGetCargoCarNum) {
		this.sendGetCargoCarNum = sendGetCargoCarNum;
	}
	public BigDecimal getSendArriveCargoWeight() {
		return sendArriveCargoWeight;
	}
	public void setSendArriveCargoWeight(BigDecimal sendArriveCargoWeight) {
		this.sendArriveCargoWeight = sendArriveCargoWeight;
	}
	public Integer getSendArriveCargoNum() {
		return sendArriveCargoNum;
	}
	public void setSendArriveCargoNum(Integer sendArriveCargoNum) {
		this.sendArriveCargoNum = sendArriveCargoNum;
	}
	public BigDecimal getFinishCargoWeight() {
		return finishCargoWeight;
	}
	public void setFinishCargoWeight(BigDecimal finishCargoWeight) {
		this.finishCargoWeight = finishCargoWeight;
	}
	public BigDecimal getRunningCargoWeight() {
		return runningCargoWeight;
	}
	public void setRunningCargoWeight(BigDecimal runningCargoWeight) {
		this.runningCargoWeight = runningCargoWeight;
	}
	public BigDecimal getTransitCargoWeight() {
		return transitCargoWeight;
	}
	public void setTransitCargoWeight(BigDecimal transitCargoWeight) {
		this.transitCargoWeight = transitCargoWeight;
	}
	
}
