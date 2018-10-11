package com.shenhesoft.logistics.common.exception;

/**
 * 捕捉到未知错误时,抛出此异常.
 * 
 * @author LiuJiefeng
 *
 */
public class SystemException extends RuntimeException {

  private static final long serialVersionUID = -3079654634997274129L;

  public SystemException() {}

  public SystemException(String message) {
    super(message);
  }
}
