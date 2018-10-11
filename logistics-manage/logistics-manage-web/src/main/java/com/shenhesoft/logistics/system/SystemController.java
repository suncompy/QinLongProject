package com.shenhesoft.logistics.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.util.AppUtils;

/**
 *系统管理-控制层Action.
 * <p>
 * <a href="SystemController.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class SystemController {
  @Autowired
  private SystemService systemService;
  

  /**
   * 验证码.
   * 
   * @param system {@linkplain com.shenhesoft.logistics.finance.System 短驳打包-运单中间表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.System}&gt; - 短驳打包-运单中间表实体的响应</p>
   */
  @RequestMapping(value = "/sendCHkCode", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse getCHkCode(@RequestBody SystemManage system) {
    // 验证表单内容
    AppUtils.validateModel(system);
    // 新增短驳打包-运单中间表
    return new ShResponse(HttpStatus.CREATED.value(), "保存成功",systemService.getCHkCode(system));
  }

  /**
   * 验证码.
   * 
   * @param system {@linkplain com.shenhesoft.logistics.finance.System 短驳打包-运单中间表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.System}&gt; - 短驳打包-运单中间表实体的响应</p>
   */
  @RequestMapping(value = "/resetPasswd", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse updatePasswd(@RequestBody SystemManage system) {
    // 验证表单内容
    AppUtils.validateModel(system);
    // 新增短驳打包-运单中间表
    return new ShResponse(HttpStatus.CREATED.value(), "密码已修改成功!",systemService.updatePasswd(system));
  }
  
  /**
   * 验证码.
   * 
   * @param system {@linkplain com.shenhesoft.logistics.finance.System 短驳打包-运单中间表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.System}&gt; - 短驳打包-运单中间表实体的响应</p>
   */
  @RequestMapping(value = "/alterPasswd", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse alterPasswd(@RequestBody SystemManage system) {
    // 验证表单内容
    AppUtils.validateModel(system);
    // 新增短驳打包-运单中间表
    return new ShResponse(HttpStatus.CREATED.value(), "密码已修改成功!",systemService.alterPasswd(system));
  }
  
  /**
   * 验证码.
   * 
   * @param system {@linkplain com.shenhesoft.logistics.finance.System 短驳打包-运单中间表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.System}&gt; - 短驳打包-运单中间表实体的响应</p>
   */
  @RequestMapping(value = "/initPage", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse initPage(@RequestBody SystemManage system) {
    // 验证表单内容
    AppUtils.validateModel(system);
    // 新增短驳打包-运单中间表
    return new ShResponse(HttpStatus.CREATED.value(), "初始化成功!",systemService.initPage(system));
  }
}