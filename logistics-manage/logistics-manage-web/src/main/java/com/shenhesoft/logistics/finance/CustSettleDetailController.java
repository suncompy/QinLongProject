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
 * 客户结算明细-控制层Action.
 * <p>
 * <a href="CustSettleDetailController.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class CustSettleDetailController {
  @Autowired
  private CustSettleDetailService custSettleDetailService;
  

  /**
   * 添加客户结算明细.
   * 
   * @param custSettleDetail {@linkplain com.shenhesoft.logistics.finance.CustSettleDetail 客户结算明细实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustSettleDetail}&gt; - 客户结算明细实体的响应</p>
   */
  @RequestMapping(value = "/custSettleDetail", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse addCustSettleDetail(@RequestBody CustSettleDetail custSettleDetail) {
    // 验证表单内容
    AppUtils.validateModel(custSettleDetail);
    // 新增客户结算明细
    return new ShResponse(HttpStatus.CREATED.value(), "保存成功",custSettleDetailService.addCustSettleDetail(custSettleDetail));
  }

  /**
   * 查看客户结算明细详情.
   * 
   * @param id 主键
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustSettleDetail}&gt; - 客户结算明细实体的响应</p>
   */
  @RequestMapping(value = "/custSettleDetails/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustSettleDetailById(@PathVariable("id") String id) {
    Map<String,Object> form = custSettleDetailService.getCustSettleDetailById(id);
    // 获取客户结算明细详情
    return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
  }

  /**
   * 修改客户结算明细.
   * 
   * @param custSettleDetail {@linkplain com.shenhesoft.logistics.finance.CustSettleDetail 客户结算明细实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustSettleDetail}&gt; - 客户结算明细实体的响应</p>
   */
  @RequestMapping(value = "/custSettleDetail", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editCustSettleDetailById(@RequestBody  CustSettleDetail custSettleDetail) {
    // 验证表单内容
    AppUtils.validateModel(custSettleDetail);
    // 更新客户结算明细信息
    custSettleDetailService.editCustSettleDetailById(custSettleDetail);

    return new ShResponse(HttpStatus.OK.value(), "操作成功", custSettleDetail);
  }
  
  /**
   * 修改客户结算明细.
   * 
   * @param custSettleDetail {@linkplain com.shenhesoft.logistics.finance.CustSettleDetail 客户结算明细实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.CustSettleDetail}&gt; - 客户结算明细实体的响应</p>
   */
  @RequestMapping(value = "/custSettleDetail/{ids}", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editCustSettleDetailByIds(@PathVariable("ids") String ids,@RequestBody  CustSettleDetail custSettleDetail) {
    // 验证表单内容
    AppUtils.validateModel(custSettleDetail);
    // 更新客户结算明细信息
    custSettleDetailService.editCustSettleDetailByIds(custSettleDetail,ids);

    return new ShResponse(HttpStatus.OK.value(), "操作成功", custSettleDetail);
  }

  /**
   * 删除指定客户结算明细.
   * 
   * @param id 主键
   * @return 删除提示
   */
  @RequestMapping(value = "/custSettleDetails/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delCustSettleDetailById(@PathVariable("id") String id) {

    custSettleDetailService.delCustSettleDetailById(id);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
  * 批量删除指定客户结算明细.
  * 
  * @param ids 主键集合
  * @return 删除提示
  */
  @RequestMapping(value = "/custSettleDetails/batch", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delCustSettleDetailByIds(@RequestJsonParam(value = "ids", required = false) List<String> ids) {

    custSettleDetailService.delCustSettleDetailByIds(ids);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
   * 获取所有客户结算明细.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.CustSettleDetail 客户结算明细实体}
   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.CustSettleDetail}&gt;&gt; - 客户结算明细实体集的响应</p>
   */
  @RequestMapping(value = "/custSettleDetails", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustSettleDetails(
      @RequestParam(value = "start", required = false, defaultValue = "0") int start,
      @RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
      @RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
    Map<String, Object> map = form.getInnerMap();
    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
    List<Map<String, Object>> list = custSettleDetailService.getCustSettleDetails(start,pageSize,map);
    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",new DatatablesViewPage(list));
  }

}