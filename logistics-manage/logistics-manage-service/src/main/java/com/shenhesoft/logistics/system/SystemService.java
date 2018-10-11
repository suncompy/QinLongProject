package com.shenhesoft.logistics.system;

import java.util.List;
import java.util.Map;

/**
 * 短驳打包-运单中间表-业务层接口.
 * <p>
 * <a href="SystemService.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface SystemService {

  /**
   * 验证码.
   * 
   * @param system 实体
   * @return 页面表单
   */
  public SystemManage getCHkCode(SystemManage system);

  /**
   * 验证码.
   * 
   * @param system 实体
   * @return 页面表单
   */
  public SystemManage updatePasswd(SystemManage system);

  /**
   * 验证码.
   * 
   * @param system 实体
   * @return 页面表单
   */
  public SystemManage alterPasswd(SystemManage system);

  /**
   * @description 
   * @date 2018年2月26日
   * @param 
   * @return
  */
  public List<Map<String, Object>> initPage(SystemManage system);
}