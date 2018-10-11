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
 * 客户对账打包信息表-控制层Action.
 * <p>
 * <a href="CustomerPackController.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class CustomerPackController {
  @Autowired
  private CustomerPackService customerPackService;
  

  /**
   * 添加客户对账打包信息表.
   * 
   * @param customerPack {@linkplain com.shenhesoft.logistics.finance.CustomerPack 客户对账打包信息表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustomerPack}&gt; - 客户对账打包信息表实体的响应</p>
   */
  @RequestMapping(value = "/customerPack", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse addCustomerPack(@RequestBody CustomerPack customerPack) {
    // 验证表单内容
    AppUtils.validateModel(customerPack);
    // 新增客户对账打包信息表
    return new ShResponse(HttpStatus.CREATED.value(), "保存成功",customerPackService.addCustomerPack(customerPack));
  }

  /**
   * 查看客户对账打包信息表详情.
   * 
   * @param id 主键
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustomerPack}&gt; - 客户对账打包信息表实体的响应</p>
   */
  @RequestMapping(value = "/customerPacks/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustomerPackById(@PathVariable("id") String id) {
    Map<String,Object> form = customerPackService.getCustomerPackById(id);
    // 获取客户对账打包信息表详情
    return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
  }

  /**
   * 修改客户对账打包信息表.
   * 
   * @param customerPack {@linkplain com.shenhesoft.logistics.finance.CustomerPack 客户对账打包信息表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustomerPack}&gt; - 客户对账打包信息表实体的响应</p>
   */
  @RequestMapping(value = "/customerPack", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editCustomerPackById(@RequestBody  CustomerPack customerPack) {
    // 验证表单内容
    AppUtils.validateModel(customerPack);
    // 更新客户对账打包信息表信息
    customerPackService.editCustomerPackById(customerPack);

    return new ShResponse(HttpStatus.OK.value(), "修改成功", customerPack);
  }

  /**
   * 删除指定客户对账打包信息表.
   * 
   * @param id 主键
   * @return 删除提示
   */
  @RequestMapping(value = "/customerPacks/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delCustomerPackById(@PathVariable("id") String id) {

    customerPackService.delCustomerPackById(id);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
  * 批量删除指定客户对账打包信息表.
  * 
  * @param ids 主键集合
  * @return 删除提示
  */
  @RequestMapping(value = "/customerPacks/batch", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delCustomerPackByIds(@RequestJsonParam(value = "ids", required = false) List<String> ids) {

    customerPackService.delCustomerPackByIds(ids);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
   * 获取所有客户对账打包信息表.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.CustomerPack 客户对账打包信息表实体}
   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.CustomerPack}&gt;&gt; - 客户对账打包信息表实体集的响应</p>
   */
  @RequestMapping(value = "/customerPacks", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustomerPacks(
      @RequestParam(value = "start", required = false, defaultValue = "0") int start,
      @RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
      @RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
    Map<String, Object> map = form.getInnerMap();
    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
    List<Map<String, Object>> list = customerPackService.getCustomerPacks(start,pageSize,map);
    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",new DatatablesViewPage(list));
  }
  
	/**
	 * 汽运(接取/火运/送达)对账明细 财务审核 
	 * 
	 * @param ids 主键集合
	 * @param passFlag 财务审核是否通过 0-通过 1-不通过
	 *            
	 */
	@RequestMapping(value = "/customerPack/initialFinanceAudit/{custPackIds}/{passFlag}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse editInitialFinanceAuditCustomerPackById(@PathVariable("custPackIds") String custPackIds,@PathVariable("passFlag") String passFlag,@RequestBody(required = false) TbSystemUser user) {
		customerPackService.editInitialFinanceAuditCustomerPackById(custPackIds,passFlag,user);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务审核成功");
	}
	
	/**
	 * 汽运(接取/火运/送达)对账明细 反审核
	 * 
	 * @param ids 主键集合
	 *            
	 */
	@RequestMapping(value = "/customerPack/initialAgainstAudit/{custPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse editInitialAgainstAuditCustomerPackById(@PathVariable("custPackIds") String custPackIds) {
		customerPackService.editInitialAgainstAuditCustomerPackById(custPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "反审核成功");
	}
	
	/**
	 * 点击包信息查询对应的运单列表
	 */
	@RequestMapping(value = "/customerPacks/orderInfo", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getOrderInfoByCustPackId(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
  	    Map<String, Object> map = form.getInnerMap();
        map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = customerPackService.getOrderInfoByCustPackId(start, pageSize, map);
		return new ShResponse(HttpStatus.OK.value(), "获取运单列表成功", new DatatablesViewPage(list));
	}
	
	/**
	 * 客户对账 汽运(接取/火运/送达)对账明细  解包
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/customerPack/dissolve/{custPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse editDissolveCustomerPackById(@PathVariable("custPackIds") String custPackIds) {
		customerPackService.editDissolveCustomerPackById(custPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "解包成功");
	}
	
	/**
	 * 客户结算 结算明细 结算
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/customerPack/settlePass", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse editSettlePassCustomerPackById(@RequestBody CustomerPack customerPack) {
		customerPackService.editSettlePassCustomerPackById(customerPack);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "结算成功");
	}
	
	/**
	 * 客户结算 结算明细 结算 财务审核 
	 * 
	 * @param ids 主键集合
	 * @param passFlag 财务审核是否通过 0-通过 1-不通过
	 *            
	 */
	@RequestMapping(value = "/customerPack/settleFinanceAudit/{custPackIds}/{passFlag}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse editSettleFinanceAuditCustomerPackById(@PathVariable("custPackIds") String custPackIds,@PathVariable("passFlag") String passFlag) {
		customerPackService.editSettleFinanceAuditCustomerPackById(custPackIds,passFlag);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务审核成功");
	}
	
	/**
	 * 客户结算 结算明细 反审核
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/customerPack/settleAgainstAudit/{custPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse editSettleAgainstAuditCustomerPackById(@PathVariable("custPackIds") String custPackIds) {
		customerPackService.editSettleAgainstAuditCustomerPackById(custPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "反审核成功");
	}
	
	/**
	 * 费用账目 发票管理 发票作废申请
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/customerPack/invoiceCancelApply/{custPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse editInvoiceCancelApplyCustomerPackById(@PathVariable("custPackIds") String custPackIds) {
		customerPackService.editInvoiceCancelApplyCustomerPackById(custPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "发票作废申请成功");
	}
	
	/**
	 * 费用账目 发票管理 发票作废审核
	 * 
	 * @param ids
	 *            主键集合
	 * @param passFlag 财务审核是否通过 0-通过 1-不通过
	 */
	@RequestMapping(value = "/customerPack/invoiceCancelAudit/{custPackIds}/{passFlag}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse editInvoiceCancelAuditCustomerPackById(@PathVariable("custPackIds") String custPackIds,@PathVariable("passFlag") String passFlag) {
		customerPackService.editInvoiceCancelAuditCustomerPackById(custPackIds,passFlag);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "发票作废审核成功");
	}
	
	/**
	 * 费用账目 发票管理 发票作废反审核
	 * 
	 * @param ids
	 *            主键集合
	 * @param passFlag 财务审核是否通过 0-通过 1-不通过
	 */
	@RequestMapping(value = "/customerPack/invoiceCancelAgainstAudit/{custPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse editInvoiceCancelAgainstAuditCustomerPackById(@PathVariable("custPackIds") String custPackIds) {
		customerPackService.editInvoiceCancelAgainstAuditCustomerPackById(custPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "发票作废反审核成功");
	}

	
	 /**
	   * 根据项目id获取所有客户对账打包信息表.
	   * @param projectId 项目id
	   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.CustomerPack}&gt;&gt; - 客户对账打包信息表实体集的响应</p>
	   */
	  @RequestMapping(value = "/customerPacksByProjectId/{id}", method = RequestMethod.GET)
	  @ResponseBody
	  public ShResponse customerPacksByProjectId(
			  @PathVariable("id") String projectId) {
	    List<Map<String, Object>> list = customerPackService.getCustomerPacksByProjectId(projectId);
	    DatatablesViewPage datatablesViewPage = new DatatablesViewPage(list);
	    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",datatablesViewPage);
	  }
}