package com.shenhesoft.logistics.common.exception;

/**
 * 业务校验失败时, 抛出此异常.
 *
 * @author LiuJiefeng
 */
public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = -3079654634997274129L;

  public BusinessException() {}

  public BusinessException(String message) {
    super(message);
  }
}
