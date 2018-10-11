package com.shenhesoft.logistics.common.exception;

/**
 * FdParameterException : 表单数据校验未通过时,抛出此异常
 *
 * @author liujiefeng
 */
public class ParameterException extends RuntimeException {

  private static final long serialVersionUID = -3079654634997274129L;

  public ParameterException() {}

  public ParameterException(String message) {
    super(message);
  }
}