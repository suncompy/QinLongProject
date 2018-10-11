/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */

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
 * 客户结算表-控制层Action.
 * <p>
 * <a href="CustSettleController.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class CustSettleController {
  @Autowired
  private CustSettleService custSettleService;
  

  /**
   * 添加客户结算表.
   * 
   * @param custSettle {@linkplain com.shenhesoft.logistics.finance.CustSettle 客户结算表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustSettle}&gt; - 客户结算表实体的响应</p>
   */
  @RequestMapping(value = "/custSettle", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse addCustSettle(@RequestBody CustSettle custSettle) {
    // 验证表单内容
    AppUtils.validateModel(custSettle);
    // 新增客户结算表
    return new ShResponse(HttpStatus.CREATED.value(), "保存成功",custSettleService.addCustSettle(custSettle));
  }

  /**
   * 查看客户结算表详情.
   * 
   * @param id 主键
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustSettle}&gt; - 客户结算表实体的响应</p>
   */
  @RequestMapping(value = "/custSettles/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustSettleById(@PathVariable("id") String id) {
    Map<String,Object> form = custSettleService.getCustSettleById(id);
    // 获取客户结算表详情
    return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
  }

  /**
   * 修改客户结算表.
   * 
   * @param custSettle {@linkplain com.shenhesoft.logistics.finance.CustSettle 客户结算表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustSettle}&gt; - 客户结算表实体的响应</p>
   */
  @RequestMapping(value = "/custSettle", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editCustSettleById(@RequestBody  CustSettle custSettle) {
    // 验证表单内容
    AppUtils.validateModel(custSettle);
    // 更新客户结算表信息
    custSettleService.editCustSettleById(custSettle);

    return new ShResponse(HttpStatus.OK.value(), "修改成功", custSettle);
  }

  /**
   * 删除指定客户结算表.
   * 
   * @param id 主键
   * @return 删除提示
   */
  @RequestMapping(value = "/custSettles/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delCustSettleById(@PathVariable("id") String id) {

    custSettleService.delCustSettleById(id);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
  * 批量删除指定客户结算表.
  * 
  * @param ids 主键集合
  * @return 删除提示
  */
  @RequestMapping(value = "/custSettles/batch", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delCustSettleByIds(@RequestJsonParam(value = "ids", required = false) List<String> ids) {

    custSettleService.delCustSettleByIds(ids);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
   * 获取所有客户结算表.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.CustSettle 客户结算表实体}
   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.CustSettle}&gt;&gt; - 客户结算表实体集的响应</p>
   */
  @RequestMapping(value = "/custSettles", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustSettles(
      @RequestParam(value = "start", required = false, defaultValue = "0") int start,
      @RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
      @RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
    Map<String, Object> map = form.getInnerMap();
    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
    List<Map<String, Object>> list = custSettleService.getCustSettles(start,pageSize,map);
    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",new DatatablesViewPage(list));
  }

}