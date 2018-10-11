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
 * 短驳打包-运单中间表-控制层Action.
 * <p>
 * <a href="ShPackOrderController.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class ShPackOrderController {
  @Autowired
  private ShPackOrderService shPackOrderService;
  

  /**
   * 添加短驳打包-运单中间表.
   * 
   * @param shPackOrder {@linkplain com.shenhesoft.logistics.finance.ShPackOrder 短驳打包-运单中间表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.ShPackOrder}&gt; - 短驳打包-运单中间表实体的响应</p>
   */
  @RequestMapping(value = "/shPackOrder", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse addShPackOrder(@RequestBody ShPackOrder shPackOrder) {
    // 验证表单内容
    AppUtils.validateModel(shPackOrder);
    // 新增短驳打包-运单中间表
    return new ShResponse(HttpStatus.CREATED.value(), "保存成功",shPackOrderService.addShPackOrder(shPackOrder));
  }

  /**
   * 查看短驳打包-运单中间表详情.
   * 
   * @param id 主键
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.ShPackOrder}&gt; - 短驳打包-运单中间表实体的响应</p>
   */
  @RequestMapping(value = "/shPackOrders/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getShPackOrderById(@PathVariable("id") String id) {
    Map<String,Object> form = shPackOrderService.getShPackOrderById(id);
    // 获取短驳打包-运单中间表详情
    return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
  }

  /**
   * 修改短驳打包-运单中间表.
   * 
   * @param shPackOrder {@linkplain com.shenhesoft.logistics.finance.ShPackOrder 短驳打包-运单中间表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.ShPackOrder}&gt; - 短驳打包-运单中间表实体的响应</p>
   */
  @RequestMapping(value = "/shPackOrder", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editShPackOrderById(@RequestBody  ShPackOrder shPackOrder) {
    // 验证表单内容
    AppUtils.validateModel(shPackOrder);
    // 更新短驳打包-运单中间表信息
    shPackOrderService.editShPackOrderById(shPackOrder);

    return new ShResponse(HttpStatus.OK.value(), "修改成功", shPackOrder);
  }

  /**
   * 删除指定短驳打包-运单中间表.
   * 
   * @param id 主键
   * @return 删除提示
   */
  @RequestMapping(value = "/shPackOrders/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delShPackOrderById(@PathVariable("id") String id) {

    shPackOrderService.delShPackOrderById(id);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
  * 批量删除指定短驳打包-运单中间表.
  * 
  * @param ids 主键集合
  * @return 删除提示
  */
  @RequestMapping(value = "/shPackOrders/batch", method = RequestMethod.DELETE)
  @ResponseBody
  public ShResponse delShPackOrderByIds(@RequestJsonParam(value = "ids", required = false) List<String> ids) {

    shPackOrderService.delShPackOrderByIds(ids);

    return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
  }

  /**
   * 获取所有短驳打包-运单中间表.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.ShPackOrder 短驳打包-运单中间表实体}
   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.ShPackOrder}&gt;&gt; - 短驳打包-运单中间表实体集的响应</p>
   */
  @RequestMapping(value = "/shPackOrders", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getShPackOrders(
      @RequestParam(value = "start", required = false, defaultValue = "0") int start,
      @RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
      @RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
    Map<String, Object> map = form.getInnerMap();
    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
    List<Map<String, Object>> list = shPackOrderService.getShPackOrders(start,pageSize,map);
    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",new DatatablesViewPage(list));
  }

}