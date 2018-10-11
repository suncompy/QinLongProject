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

/**
 * 客户打包-运单中间表-控制层Action.
 * <p>
 * <a href="CustomerPackOrderController.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class CustomerPackOrderController {
  @Autowired
  private CustomerPackOrderService customerPackOrderService;
  

  /**
   * 添加客户打包-运单中间表.
   * 
   * @param customerPackOrder {@linkplain com.shenhesoft.logistics.finance.CustomerPackOrder 客户打包-运单中间表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustomerPackOrder}&gt; - 客户打包-运单中间表实体的响应</p>
   */
  @RequestMapping(value = "/customerPackOrder", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse addCustomerPackOrder(@RequestBody CustomerPackOrder customerPackOrder) {
    // 验证表单内容
    AppUtils.validateModel(customerPackOrder);
    // 新增客户打包-运单中间表
    return new ShResponse(HttpStatus.CREATED.value(), "保存成功",customerPackOrderService.addCustomerPackOrder(customerPackOrder));
  }

  /**
   * 查看客户打包-运单中间表详情.
   * 
   * @param id 主键
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustomerPackOrder}&gt; - 客户打包-运单中间表实体的响应</p>
   */
  @RequestMapping(value = "/customerPackOrders/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustomerPackOrderById(@PathVariable("id") String id) {
    Map<String,Object> form = customerPackOrderService.getCustomerPackOrderById(id);
    // 获取客户打包-运单中间表详情
    return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
  }

  /**
   * 修改客户打包-运单中间表.
   * 
   * @param customerPackOrder {@linkplain com.shenhesoft.logistics.finance.CustomerPackOrder 客户打包-运单中间表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustomerPackOrder}&gt; - 客户打包-运单中间表实体的响应</p>
   */
  @RequestMapping(value = "/customerPackOrder", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editCustomerPackOrderById(@RequestBody  CustomerPackOrder customerPackOrder) {
    // 验证表单内容
    AppUtils.validateModel(customerPackOrder);
    // 更新客户打包-运单中间表信息
    customerPackOrderService.editCustomerPackOrderById(customerPackOrder);

    return new ShResponse(HttpStatus.OK.value(), "修改成功", customerPackOrder);
  }

  /**
   * 删除指定客户打包-运单中间表.
   * 
   * @param id 主键
   * @return 删除提示
   */
  @RequestMapping(value = "/customerPackOrders/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delCustomerPackOrderById(@PathVariable("id") String id) {

    customerPackOrderService.delCustomerPackOrderById(id);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
  * 批量删除指定客户打包-运单中间表.
  * 
  * @param ids 主键集合
  * @return 删除提示
  */
  @RequestMapping(value = "/customerPackOrders/batch", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delCustomerPackOrderByIds(@RequestJsonParam(value = "ids", required = false) List<String> ids) {

    customerPackOrderService.delCustomerPackOrderByIds(ids);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
   * 获取所有客户打包-运单中间表.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.CustomerPackOrder 客户打包-运单中间表实体}
   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.CustomerPackOrder}&gt;&gt; - 客户打包-运单中间表实体集的响应</p>
   */
  @RequestMapping(value = "/customerPackOrders", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustomerPackOrders(
      @RequestParam(value = "start", required = false, defaultValue = "0") int start,
      @RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
      @RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
    Map<String, Object> map = form.getInnerMap();
    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
    List<Map<String, Object>> list = customerPackOrderService.getCustomerPackOrders(start,pageSize,map);
    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",new DatatablesViewPage(list));
  }

}