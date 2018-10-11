package com.shenhesoft.logistics.system.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shenhesoft.logistics.common.exception.BusinessException;
import com.shenhesoft.logistics.common.exception.NotFoundException;
import com.shenhesoft.logistics.common.exception.ParameterException;
import com.shenhesoft.logistics.common.page.ExceptionResponse;

// HTTP 响应代码 代码含义
// 200 已创建，请求成功且服务器已创建了新的资源。
// 201 是否只显示处于警告状态的应用实例
// 301 重定向 , 请求的网页已被永久移动到新位置。服务器返回此响应时，会自动将请求者转到新位置。
// 302 重定向 , 请求的网页临时移动到新位置，但求者应继续使用原有位置来进行以后的请求。302 会自动将请求者转到不同的临时位置。
// 304 未修改，自从上次请求后，请求的网页未被修改过。服务器返回此响应时，不会返回网页内容。
// 400 错误请求 , 服务器不理解请求的语法。
// 401 未授权 , 请求要求进行身份验证。
// 403 已禁止 , 服务器拒绝请求。
// 404 未找到 , 服务器找不到请求的网页。
// 405 方法禁用 , 禁用请求中所指定的方法。
// 406 不接受 , 无法使用请求的内容特性来响应请求的网页。
// 408 请求超时 , 服务器等候请求时超时。
// 410 已删除 , 如果请求的资源已被永久删除，那么，服务器会返回此响应。
// 412 未满足前提条件 , 服务器未满足请求者在请求中设置的其中一个前提条件。
// 415 不支持的媒体类型 , 请求的格式不受请求页面的支持。
// 500 内部服务器错误。

/**
 * 全局异常处理, 通用的异常在此进行处理, 例如: http method不匹配(405), 用户为未认证(401), 用户未获取到授权(403) 所有异常均向上抛,
 * 统一在controller层进行处理.
 *
 * @author LiuJiefeng
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * 处理Http method使用不正确的错误(例如: 新增应该使用POST, 但实际使用了GET).
   * 
   * @param req 请求
   * @param ex 异常
   * @return 异常响应
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ResponseBody
  public ExceptionResponse handleHttp405Exception(HttpServletRequest req, Exception ex) {
    logger.error("系统异常", ex);
    return ExceptionResponse.create(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage());
  }

  /**
   * 处理未发现的异常(如用户不存在等, 抛出http status 404).
   * 
   * @param req 请求
   * @param ex 异常
   * @return 异常响应
   */
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ExceptionResponse handleNotFoundException(HttpServletRequest req, Exception ex) {
    logger.error("请求的资源不存在", ex);
    return ExceptionResponse.create(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }

  /**
   * 处理参数校验失败的异常(如用户名输入超过长度等).
   * 
   * @param req 请求
   * @param ex 异常
   * @return 异常响应
   */
  @ExceptionHandler(ParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ExceptionResponse handleParameterException(HttpServletRequest req, Exception ex) {
    logger.error("参数校验失败", ex);
    return ExceptionResponse.create(901, ex.getMessage());
  }

  /**
   * 处理业务校验失败，(如删除的班级下，存在课程，无法删除）.
   * 
   * @param req 请求
   * @param ex 异常
   * @return 异常响应
   */
  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ExceptionResponse handleBusinessException(HttpServletRequest req, Exception ex) {
    logger.error("业务校验失败", ex);
    return ExceptionResponse.create(801, ex.getMessage());
  }

  /**
   * 其他异常(以上异常之外的异常).
   * 
   * @param req 请求
   * @param ex 异常
   * @return 异常响应
   */
  @ExceptionHandler({Exception.class, Error.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ExceptionResponse handleException(HttpServletRequest req, Exception ex) {
    logger.error("系统异常", ex);
    return ExceptionResponse.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请联系管理员.");
  }

}
