package com.shenhesoft.logistics.common.page;

/**
 * ShResponse : 通用响应对象, 封装数据如下： {"code":200,"message":"创建成功","data":{""}}.
 * 
 * @author LiuJiefeng
 */
public class ShResponse<T> {

	private int code;
	private String message;
	private T data;

	public ShResponse() {}
	
	public ShResponse(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	public ShResponse(int code, String message) {
		this.code = code;
		this.message = message;
		this.data = (T) new Object();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
