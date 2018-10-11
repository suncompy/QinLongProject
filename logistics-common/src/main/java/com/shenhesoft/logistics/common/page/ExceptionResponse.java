package com.shenhesoft.logistics.common.page;

/**
 * 异常响应, 消息将封装至返回json的meta中.
 * 
 * @author LiuJiefeng
 */
public class ExceptionResponse {

	private int code;
	private String message;

	public ExceptionResponse() {
	}

	public ExceptionResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 创建异常响应.
	 * 
	 * @param code
	 *            消息code
	 * @param message
	 *            消息内容
	 * @return 异常响应
	 */
	public static ExceptionResponse create(Integer code, String message) {
		return new ExceptionResponse(code, message);
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
