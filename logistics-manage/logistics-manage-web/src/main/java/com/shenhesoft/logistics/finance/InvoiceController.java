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
 * 发票-控制层Action.
 * <p>
 * <a href="InvoiceController.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class InvoiceController {
  @Autowired
  private InvoiceService invoiceService;
  

  /**
   * 添加发票.
   * 
   * @param invoice {@linkplain com.shenhesoft.logistics.finance.Invoice 发票实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.Invoice}&gt; - 发票实体的响应</p>
   */
  @RequestMapping(value = "/invoice", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse addInvoice(@RequestBody Invoice invoice) {
    // 验证表单内容
    AppUtils.validateModel(invoice);
    // 新增发票
    return new ShResponse(HttpStatus.CREATED.value(), "保存成功",invoiceService.addInvoice(invoice));
  }

  /**
   * 查看发票详情.
   * 
   * @param id 主键
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.Invoice}&gt; - 发票实体的响应</p>
   */
  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getInvoiceById(@PathVariable("id") String id) {
    Map<String,Object> form = invoiceService.getInvoiceById(id);
    // 获取发票详情
    return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
  }

  /**
   * 修改发票.
   * 
   * @param invoice {@linkplain com.shenhesoft.logistics.finance.Invoice 发票实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.Invoice}&gt; - 发票实体的响应</p>
   */
  @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editInvoiceById(@RequestBody  Invoice invoice) {
    // 验证表单内容
    AppUtils.validateModel(invoice);
    // 更新发票信息
    invoiceService.editInvoiceById(invoice);

    return new ShResponse(HttpStatus.OK.value(), "操作成功", invoice);
  }

  /**
   * 删除指定发票.
   * 
   * @param id 主键
   * @return 删除提示
   */
  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delInvoiceById(@PathVariable("id") String id) {

    invoiceService.delInvoiceById(id);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
  * 批量删除指定发票.
  * 
  * @param ids 主键集合
  * @return 删除提示
  */
  @RequestMapping(value = "/invoices/batch", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delInvoiceByIds(@RequestJsonParam(value = "ids", required = false) List<String> ids) {

    invoiceService.delInvoiceByIds(ids);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
   * 获取所有发票.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.Invoice 发票实体}
   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.Invoice}&gt;&gt; - 发票实体集的响应</p>
   */
  @RequestMapping(value = "/invoices", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getInvoices(
      @RequestParam(value = "start", required = false, defaultValue = "0") int start,
      @RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
      @RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
    Map<String, Object> map = form.getInnerMap();
    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
    List<Map<String, Object>> list = invoiceService.getInvoices(start,pageSize,map);
    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",new DatatablesViewPage(list));
  }

}