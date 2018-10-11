package com.shenhesoft.logistics.finance;

import java.util.HashMap;
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
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;

/**
 * 客户打包-发票信息表-控制层Action.
 * <p>
 * <a href="CustomerPackInvoiceController.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-19
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class CustomerPackInvoiceController {
  @Autowired
  private CustomerPackInvoiceService customerPackInvoiceService;
  

  /**
   * 添加客户打包-发票信息表.
   * 
   * @param customerPackInvoice {@linkplain com.shenhesoft.logistics.finance.CustomerPackInvoice 客户打包-发票信息表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustomerPackInvoice}&gt; - 客户打包-发票信息表实体的响应</p>
   */
  @RequestMapping(value = "/customerPackInvoice", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse addCustomerPackInvoice(@RequestBody CustomerPackInvoice customerPackInvoice) {
    // 验证表单内容
    AppUtils.validateModel(customerPackInvoice);
    // 新增客户打包-发票信息表
    return new ShResponse(HttpStatus.CREATED.value(), "保存成功",customerPackInvoiceService.addCustomerPackInvoice(customerPackInvoice));
  }

  /**
   * 查看客户打包-发票信息表详情.
   * 
   * @param id 主键
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustomerPackInvoice}&gt; - 客户打包-发票信息表实体的响应</p>
   */
  @RequestMapping(value = "/customerPackInvoices/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustomerPackInvoiceById(@PathVariable("id") String id) {
    Map<String,Object> form = customerPackInvoiceService.getCustomerPackInvoiceById(id);
    // 获取客户打包-发票信息表详情
    return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
  }

  /**
   * 修改客户打包-发票信息表.
   * 
   * @param customerPackInvoice {@linkplain com.shenhesoft.logistics.finance.CustomerPackInvoice 客户打包-发票信息表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustomerPackInvoice}&gt; - 客户打包-发票信息表实体的响应</p>
   */
  @RequestMapping(value = "/customerPackInvoice", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editCustomerPackInvoiceById(@RequestBody  CustomerPackInvoice customerPackInvoice) {
    // 验证表单内容
    AppUtils.validateModel(customerPackInvoice);
    // 更新客户打包-发票信息表信息
    customerPackInvoiceService.editCustomerPackInvoiceById(customerPackInvoice);

    return new ShResponse(HttpStatus.OK.value(), "修改成功", customerPackInvoice);
  }

  /**
   * 删除指定客户打包-发票信息表.
   * 
   * @param id 主键
   * @return 删除提示
   */
  @RequestMapping(value = "/customerPackInvoices/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delCustomerPackInvoiceById(@PathVariable("id") String id) {

    customerPackInvoiceService.delCustomerPackInvoiceById(id);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
  * 批量删除指定客户打包-发票信息表.
  * 
  * @param ids 主键集合
  * @return 删除提示
  */
  @RequestMapping(value = "/customerPackInvoices/batch", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delCustomerPackInvoiceByIds(@RequestJsonParam(value = "ids", required = false) List<String> ids) {

    customerPackInvoiceService.delCustomerPackInvoiceByIds(ids);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
   * 获取所有客户打包-发票信息表.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.CustomerPackInvoice 客户打包-发票信息表实体}
   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.CustomerPackInvoice}&gt;&gt; - 客户打包-发票信息表实体集的响应</p>
   */
  @RequestMapping(value = "/customerPackInvoices", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustomerPackInvoices(
      @RequestParam(value = "start", required = false, defaultValue = "0") int start,
      @RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
      @RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
    List<Map<String, Object>> list = customerPackInvoiceService.getCustomerPackInvoices(start,pageSize,form.getInnerMap());
    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",new DatatablesViewPage(list));
  }
  
  /**
   * 获取所有客户打包-发票信息表. 查询客户信息列表
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.CustomerPackInvoice 客户打包-发票信息表实体}
   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.CustomerPackInvoice}&gt;&gt; - 客户打包-发票信息表实体集的响应</p>
   */
  @RequestMapping(value = "/customerPackInvoices/customerInfo", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustomerInfos() {
	  List<Map<String, Object>> list = customerPackInvoiceService.getCustomerInfos();
	  return new ShResponse(HttpStatus.OK.value(),"查询客户信息列表成功",list);
  }
  
  /*
   * 根据id获取信息
   */
  @RequestMapping(value = "/customerPackInvoices/getCustomerInfoByCid/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustomerInfoByCid(@PathVariable("id") String id) {
	  Integer idInt = Integer.valueOf(id);
	  TbCustomer tbCustomer = customerPackInvoiceService.selectTbCustomerInfoById(idInt);
	  return new ShResponse(HttpStatus.OK.value(),"查询客户信息成功",tbCustomer);
  }
  
  /*
   * 发票登入
   */
  @RequestMapping(value = "/insertCustomerPackInvoice", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse insertCustomerPackInvoice(@RequestBody CustomerPackInvoice customerPackInvoice) {
    // 验证表单内容
    AppUtils.validateModel(customerPackInvoice);
    // 新增客户打包-发票信息表
    return new ShResponse(HttpStatus.CREATED.value(), "保存成功",customerPackInvoiceService.insertCustomerPackInvoice(customerPackInvoice));
  }
  
  /*
   * 发票作废——审核——反审核
   */
  @RequestMapping(value = "/updateInvoiceManagementCancel", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse updateInvoiceManagementCancel(@RequestBody CustomerPack customerPack) {
    // 验证表单内容
    AppUtils.validateModel(customerPack);
    
    customerPackInvoiceService.updateInvoiceManagementCancel(customerPack);
    return new ShResponse(HttpStatus.CREATED.value(), "修改成功");
  }
  
  /**
   * 获取发票列表.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.CustomerPackInvoice 客户打包-发票信息表实体}
   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.CustomerPackInvoice}&gt;&gt; - 客户打包-发票信息表实体集的响应</p>
   */
  @RequestMapping(value = "/getInvoiceManagement", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getInvoiceManagement(
      @RequestParam(value = "start", required = false, defaultValue = "0") int start,
      @RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
      @RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
    List<Map<String, Object>> list = customerPackInvoiceService.getInvoiceManagement(start,pageSize,form.getInnerMap());
    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",new DatatablesViewPage(list));
  }
  
  /**
   * 根据项目id获取发票管理信息
   * @param projectId 项目id
   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.CustomerPack}&gt;&gt; - 客户对账打包信息表实体集的响应</p>
   */
  @RequestMapping(value = "/getInvoiceManagementByProjectId", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getInvoiceManagementByProjectId(
		  @RequestParam(value = "projectId", required = false, defaultValue = "0") String projectId,
	      @RequestParam(value = "custPackId", required = false, defaultValue = "0") String custPackId,
	      @RequestParam(value = "invoiceStatus", required = false) String invoiceStatus
		 ) {
	Map<String, Object> form = new HashMap<>();
	form.put("projectId", projectId);
	form.put("invoiceStatus", invoiceStatus);
	if(custPackId.equals("0")){
		custPackId=null;
	}
	form.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
	form.put("custPackId", custPackId);
    List<Map<String, Object>> list = customerPackInvoiceService.getInvoiceManagement(form);
    DatatablesViewPage datatablesViewPage = new DatatablesViewPage(list);
    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",datatablesViewPage);
  }
  
}