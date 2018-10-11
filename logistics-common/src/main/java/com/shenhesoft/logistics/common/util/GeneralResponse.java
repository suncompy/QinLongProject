// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.common.util;

/**
 * @description ajax请求响应类
 *
 * @author YongZhang
 *
 * @date 2017年5月19日
 */
public class GeneralResponse implements java.io.Serializable{
	private static final long serialVersionUID = -6796993654492127206L;

	/**
	 * 结果返回状态 0-错误 1-正确
	 */
	private byte state;

	/**
	 * 接口说明
	 */
	private String msg;

	/**
	 * 返回数据信息
	 */
	private Object obj;

	public GeneralResponse() {
		super();
	}

	public GeneralResponse(Object obj) {
		super();
		this.obj = obj;
	}
    public GeneralResponse(String msg,Object obj){
	     this.msg = msg;
	     this.obj = obj;
	}
    public GeneralResponse(byte state,String msg,Object obj){
      this.state = state;
      this.msg = msg;
      this.obj = obj;
    }
    public static GeneralResponse newInstance(byte state,String msg,Object obj){
      return new GeneralResponse(state, msg, obj);
    }
    public static GeneralResponse newInstanceYES(String msg,Object obj){
      return new GeneralResponse(Constants.YES, msg, obj);
    }
    public static GeneralResponse newInstanceNO(String msg,Object obj){
      return new GeneralResponse(Constants.NO, msg, obj);
    }
	
	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
