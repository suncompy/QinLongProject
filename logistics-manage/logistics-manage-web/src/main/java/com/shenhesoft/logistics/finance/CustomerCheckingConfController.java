package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenhesoft.logistics.common.annotation.RequestJsonParam;
import com.shenhesoft.logistics.common.page.DatatablesViewPage;
import com.shenhesoft.logistics.common.page.MapWapper;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 客户对账设置表-控制层Action.
 * <p>
 * <a href="CustomerCheckingConfController.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class CustomerCheckingConfController {
  @Autowired
  private CustomerCheckingConfService customerCheckingConfService;
  

  /**
   * 添加客户对账设置表.
   * 
   * @param customerCheckingConf {@linkplain com.shenhesoft.logistics.finance.CustomerCheckingConf 客户对账设置表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustomerCheckingConf}&gt; - 客户对账设置表实体的响应</p>
   */
  @RequestMapping(value = "/customerCheckingConf", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse addCustomerCheckingConf(@RequestBody CustomerCheckingConf customerCheckingConf) {
    // 验证表单内容
    AppUtils.validateModel(customerCheckingConf);
    // 新增客户对账设置表
    return new ShResponse(HttpStatus.CREATED.value(), "保存成功",customerCheckingConfService.addCustomerCheckingConf(customerCheckingConf));
  }

  /**
   * 查看客户对账设置表详情.
   * 
   * @param id 主键
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustomerCheckingConf}&gt; - 客户对账设置表实体的响应</p>
   */
  @RequestMapping(value = "/customerCheckingConfs/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustomerCheckingConfById(@PathVariable("id") String id) {
    Map<String,Object> form = customerCheckingConfService.getCustomerCheckingConfById(id);
    // 获取客户对账设置表详情
    return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
  }

  /**
   * 修改客户对账设置表.
   * 
   * @param customerCheckingConf {@linkplain com.shenhesoft.logistics.finance.CustomerCheckingConf 客户对账设置表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustomerCheckingConf}&gt; - 客户对账设置表实体的响应</p>
   */
  @RequestMapping(value = "/customerCheckingConf", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editCustomerCheckingConfById(@RequestBody  CustomerCheckingConf customerCheckingConf) {
    // 验证表单内容
    AppUtils.validateModel(customerCheckingConf);
    // 更新客户对账设置表信息
    customerCheckingConfService.editCustomerCheckingConfById(customerCheckingConf);

    return new ShResponse(HttpStatus.OK.value(), "修改成功", customerCheckingConf);
  }

  /**
   * 删除指定客户对账设置表.
   * 
   * @param id 主键
   * @return 删除提示
   */
  @RequestMapping(value = "/customerCheckingConfs/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delCustomerCheckingConfById(@PathVariable("id") String id) {

    customerCheckingConfService.delCustomerCheckingConfById(id);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
  * 批量删除指定客户对账设置表.
  * 
  * @param ids 主键集合
  * @return 删除提示
  */
  @RequestMapping(value = "/customerCheckingConfs/batch", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delCustomerCheckingConfByIds(@RequestJsonParam(value = "ids", required = false) List<String> ids) {

    customerCheckingConfService.delCustomerCheckingConfByIds(ids);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
   * 获取所有客户对账设置表.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.CustomerCheckingConf 客户对账设置表实体}
   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.CustomerCheckingConf}&gt;&gt; - 客户对账设置表实体集的响应</p>
   */
  @RequestMapping(value = "/customerCheckingConfs", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustomerCheckingConfs(
      @RequestParam(value = "start", required = false, defaultValue = "0") int start,
      @RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
      @RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
    Map<String, Object> map = form.getInnerMap();
    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
    List<Map<String, Object>> list = customerCheckingConfService.getCustomerCheckingConfs(start,pageSize,map);
    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",new DatatablesViewPage(list));
  }
  
  /**
   * 客户对账 对账明细 确认对账
   * @author dusd
   * @date 2018年1月18日
   * @param customerCheckingConf
   * @return
   */
  @RequestMapping(value = "/customerCheckingConf/sureChecking/{custCheckConIds}/{flag}", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editSureCheckingCustomerCheckingConfById(@PathVariable("custCheckConIds") String custCheckConIds
      ,@PathVariable("flag") int flag) {
    // 更新客户对账设置表信息
    customerCheckingConfService.editSureCheckingCustomerCheckingConfById(custCheckConIds,flag);
    return new ShResponse(HttpStatus.NO_CONTENT.value(), "确认对账成功");
  }
  
  /**
   * 客户对账 对账明细 反确认
   * @author dusd
   * @date 2018年1月19日
   * @param custCheckConIds
   * @return
   */
  @RequestMapping(value = "/customerCheckingConf/againstVerify/{custCheckConIds}", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editAgainstVerifyCustomerCheckingConfById(@PathVariable("custCheckConIds") String custCheckConIds) {
    // 更新客户对账设置表信息
    customerCheckingConfService.editAgainstVerifyCustomerCheckingConfById(custCheckConIds);
    return new ShResponse(HttpStatus.NO_CONTENT.value(), "反确认成功");
  }
  
  /**
   * 客户对账 对账明细 财务审核
   * @author dusd
   * @date 2018年1月19日
   * @param custCheckConIds
   * @return
   */
  @RequestMapping(value = "/customerCheckingConf/financeAudit/{custCheckConIds}/{checkingStatus}", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editFinanceAuditCustomerCheckingConfById(@PathVariable("custCheckConIds") String custCheckConIds
      ,@PathVariable("checkingStatus") int checkingStatus,@RequestBody(required = false) TbSystemUser user) {
    // 更新客户对账设置表信息
    customerCheckingConfService.editFinanceAuditCustomerCheckingConfById(custCheckConIds,checkingStatus,user);
    return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务审核成功");
  }
  
  /**
   * 客户对账 对账明细 反审核
   * @author dusd
   * @date 2018年1月19日
   * @param custCheckConIds
   * @return
   */
  @RequestMapping(value = "/customerCheckingConf/againstAudit/{custCheckConIds}", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editAgainstAuditCustomerCheckingConfById(@PathVariable("custCheckConIds") String custCheckConIds) {
	  // 更新客户对账设置表信息
	  customerCheckingConfService.editAgainstAuditCustomerCheckingConfById(custCheckConIds);
	  return new ShResponse(HttpStatus.NO_CONTENT.value(), "反审核成功");
  }
  
  /**
   * 通过客户对账设置表主键查询所有适合的运单列表
   * @param start 开始记录
   * @param pageSize 分页大小
   * orderType 0接取 1：送达 2汽运 3火运
   */
  @RequestMapping(value = "/customerCheckingConf/getOrdersByCustCheckConId", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getOrdersByCustCheckConId(
      @RequestParam(value = "start", required = false, defaultValue = "0") int start,
      @RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
      @RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
    Map<String, Object> map = form.getInnerMap();
    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
    List<Map<String, Object>> list = customerCheckingConfService.getOrdersByCustCheckConId(start,pageSize,map);
    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",new DatatablesViewPage(list));
  }

}