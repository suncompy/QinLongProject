package com.shenhesoft.logistics.enterprise.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Maps;
import com.shenhesoft.logistics.business.bussinessCount.BussinessCountService;
import com.shenhesoft.logistics.common.ParamForm;
import com.shenhesoft.logistics.common.page.DatatablesViewPage;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.finance.CustomerCheckingConfService;
import com.shenhesoft.logistics.finance.CustomerPackService;
import com.shenhesoft.logistics.finance.RoadInfo;
import com.shenhesoft.logistics.finance.ShortOrderFinanceService;
import com.shenhesoft.logistics.finance.ShortPackService;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 短驳运单财务表-控制层Action.
 * <p>
 * <a href="ShortOrderFinanceController.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Controller
@RequestMapping("/api/link")
public class ShortOrderFinanceLinkController {
  @Autowired
  private ShortOrderFinanceService shortOrderFinanceService;
  @Autowired
  private ShortPackService shortPackService;
  @Autowired
  private CustomerCheckingConfService customerCheckingConfService;
  @Autowired
  private CustomerPackService customerPackService;
  @Autowired
  private BussinessCountService bussinessCountService;

  /**
   * 添加地图标记表.
   * 
   * @param mapPoint {@linkplain com.shenhesoft.logistics.finance.MapPoint 地图标记表实体}
   * @return
   *         <p>
   *         {@link ShResponse}&lt; {@link com.shenhesoft.logistics.finance.MapPoint}&gt; -
   *         地图标记表实体的响应
   *         </p>
   */
  @RequestMapping(value = "/roadInfo", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse addMapPoint(@RequestBody RoadInfo roadInfo) {
    // 验证表单内容
    AppUtils.validateModel(roadInfo);
    bussinessCountService.addRoadInfo(roadInfo);
    // 新增地图标记表
    return new ShResponse(HttpStatus.CREATED.value(), "保存成功");
  }

  /**
   * 获取所有短驳打包信息表.
   * 
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.ShortPack 短驳打包信息表实体}
   * @return
   *         <p>
   *         {@link ShResponse}&lt;List&lt; {@link com.shenhesoft.logistics.finance.ShortPack}
   *         &gt;&gt; - 短驳打包信息表实体集的响应
   *         </p>
   */
  @RequestMapping(value = "/roadInfos", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse getRoadInfos(@RequestBody ParamForm form) {
    int start = form.getStart();
    int length = form.getLength();
    String condition = form.getCondition();
    Map<String, Object> conditionMap = null==condition?Maps.newHashMap():JsonUtils.jsonToPojo(condition, Map.class);
    conditionMap.put("sysOrgCode", form.getSysOrgCode());
    List<Map<String, Object>> list =
        bussinessCountService.getRoadInfos(start, length, conditionMap);
    return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
  }

  /**
   * 获取所有短驳运单财务表.
   * 
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.ShortOrderFinance 短驳运单财务表实体}
   * @return
   *         <p>
   *         {@link ShResponse}&lt;List&lt;
   *         {@link com.shenhesoft.logistics.finance.ShortOrderFinance} &gt;&gt; - 短驳运单财务表实体集的响应
   *         </p>
   */
  @RequestMapping(value = "/shortOrderFinances", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse getShortOrderFinances(@RequestBody ParamForm form) {
    int start = form.getStart();
    int length = form.getLength();
    String condition = form.getCondition();
    Map<String, Object> conditionMap = JsonUtils.jsonToPojo(condition, Map.class);
    conditionMap.put("sysOrgCode", form.getSysOrgCode());
    List<Map<String, Object>> list =
        shortOrderFinanceService.getShortOrderFinances(start, length, conditionMap);
    return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
  }

  /**
   * 获取所有短驳打包信息表.
   * 
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.ShortPack 短驳打包信息表实体}
   * @return
   *         <p>
   *         {@link ShResponse}&lt;List&lt; {@link com.shenhesoft.logistics.finance.ShortPack}
   *         &gt;&gt; - 短驳打包信息表实体集的响应
   *         </p>
   */
  @RequestMapping(value = "/shortPacks", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse getShortPacks(@RequestBody ParamForm form) {
    int start = form.getStart();
    int length = form.getLength();
    String condition = form.getCondition();
    Map<String, Object> conditionMap = JsonUtils.jsonToPojo(condition, Map.class);
    conditionMap.put("sysOrgCode", form.getSysOrgCode());
    List<Map<String, Object>> list = shortPackService.getShortPacks(start, length, conditionMap);
    return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
  }

  /**
   * 获取所有客户对账设置表.
   * 
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.CustomerCheckingConf 客户对账设置表实体}
   * @return
   *         <p>
   *         {@link ShResponse}&lt;List&lt;
   *         {@link com.shenhesoft.logistics.finance.CustomerCheckingConf}&gt;&gt; - 客户对账设置表实体集的响应
   *         </p>
   */
  @RequestMapping(value = "/customerCheckingApp", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse getCustomerCheckingApp(@RequestBody ParamForm form) {
    int start = form.getStart();
    int length = form.getLength();
    String condition = form.getCondition();
    Map<String, Object> conditionMap = JsonUtils.jsonToPojo(condition, Map.class);
    conditionMap.put("sysOrgCode", form.getSysOrgCode());
    List<Map<String, Object>> list =
        customerCheckingConfService.getCustomerCheckingApp(start, length, conditionMap);
    return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
  }

  /**
   * 批量进行计费确认
   * 
   * @param ids 主键集合
   * @return 计费确认提示
   */
  @RequestMapping(value = "/shortOrderFinance/billingVerify/{shOrderFinIds}",
      method = RequestMethod.POST)
  @ResponseBody
  public ShResponse billingVerifyShortOrderFinanceByIds(
      @PathVariable("shOrderFinIds") String shOrderFinIds, @RequestBody TbSystemUser user) {
    shortOrderFinanceService.billingVerifyShortOrderFinanceByIds(shOrderFinIds, user, "0");
    return new ShResponse(HttpStatus.NO_CONTENT.value(), "计费确认成功");
  }

  /**
   * 客户对账 对账明细 财务审核
   * 
   * @author dusd
   * @date 2018年1月19日
   * @param custCheckConIds
   * @return
   */
  @RequestMapping(value = "/customerCheckingConf/financeAudit/{checkId}/{packType}",
      method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse editFinanceAuditCustomerCheckingConfById(
      @PathVariable("checkId") String checkId, @PathVariable("packType") String packType,
      @RequestBody TbSystemUser user) {
    // 更新客户对账设置表信息
    if ("9".equals(packType)) {
      customerCheckingConfService.editFinanceAuditCustomerCheckingConfById(checkId, 2, user);
    } else {
      customerPackService.editInitialFinanceAuditCustomerPackById(checkId, "0", user);
    }
    return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务审核成功");
  }

  /**
   * 司机打包 对账明细 财务审核
   * 
   * @param ids 主键集合
   */
  @RequestMapping(value = "/shortPacksApp/financeAuditApp/{shPackIds}", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse financeAuditShortPackByIdsApp(@PathVariable("shPackIds") String shPackIds,
      @RequestBody TbSystemUser user) {
    shortPackService.financeAuditShortPackByIds(shPackIds, "0", user);
    return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务审核成功");
  }

  /**
   * 运费支出财务审核
   * 
   * @param ids 主键集合
   * @return 计费确认提示
   */
  @RequestMapping(value = "/shortOrderFinanceApp/financeAuditApp/{shOrderFinIds}",
      method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse financeAuditShortOrderFinanceByIds(
      @PathVariable("shOrderFinIds") String shOrderFinIds, @RequestBody TbSystemUser user) {
    shortOrderFinanceService.financeAuditShortOrderFinanceByIds(shOrderFinIds, user, "0");
    return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务审核成功");
  }

  /**
   * 获取所有客户对账设置表.
   * 
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.CustomerCheckingConf 客户对账设置表实体}
   * @return
   *         <p>
   *         {@link ShResponse}&lt;List&lt;
   *         {@link com.shenhesoft.logistics.finance.CustomerCheckingConf}&gt;&gt; - 客户对账设置表实体集的响应
   *         </p>
   */
  @RequestMapping(value = "/customerCheckingConfs", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustomerCheckingConfs(@RequestBody ParamForm form) {
    int start = form.getStart();
    int length = form.getLength();
    String condition = form.getCondition();
    Map<String, Object> conditionMap = JsonUtils.jsonToPojo(condition, Map.class);
    conditionMap.put("sysOrgCode", form.getSysOrgCode());
    List<Map<String, Object>> list =
        customerCheckingConfService.getCustomerCheckingConfs(start, length, conditionMap);
    return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
  }

  /**
   * 获取所有客户对账打包信息表.
   * 
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.CustomerPack 客户对账打包信息表实体}
   * @return
   *         <p>
   *         {@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.CustomerPack}
   *         &gt;&gt; - 客户对账打包信息表实体集的响应
   *         </p>
   */
  @RequestMapping(value = "/customerPacks", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getCustomerPacks(@RequestBody ParamForm form) {
    int start = form.getStart();
    int length = form.getLength();
    String condition = form.getCondition();
    Map<String, Object> conditionMap = JsonUtils.jsonToPojo(condition, Map.class);
    conditionMap.put("sysOrgCode", form.getSysOrgCode());
    List<Map<String, Object>> list =
        customerPackService.getCustomerPacks(start, length, conditionMap);
    return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
  }
}
