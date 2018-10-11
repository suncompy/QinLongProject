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
 * 客户对账设置表-控制层Action.
 * <p>
 * <a href="FinanceAccountDetailController.java"><i>View Source</i></a>
 * </p>
 * @author JiangYS
 * @date 2018-02-9
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class FinanceAccountDetailController {
	@Autowired
    private FinanceAccountDetailService financeAccountDetailService;
  

  /**
   * 添加账户流水详情表.
   * 
   * @param FinanceAccountDetail {@linkplain com.shenhesoft.logistics.finance.FinanceAccountDetail 客户对账设置表实体}
   * @return <p>{@link ShResponse}&lt;{@link com.shenhesoft.logistics.finance.FinanceAccountDetail}&gt; - 客户对账设置表实体的响应</p>
   */
  @RequestMapping(value = "/addFinanceAccountDetail", method = RequestMethod.POST)
  @ResponseBody
  public ShResponse addFinanceAccountDetail(@RequestBody FinanceAccountDetail financeAccountDetail) {
    // 验证表单内容
    AppUtils.validateModel(financeAccountDetail);
    // 新增客户对账设置表
    return new ShResponse(HttpStatus.CREATED.value(), "保存成功",financeAccountDetailService.addFinanceAccountDetail(financeAccountDetail));
  }

  /**
   * 获取账户流水详情表.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form {@linkplain com.shenhesoft.logistics.finance.FinanceAccountDetail 客户对账设置表实体}
   * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.FinanceAccountDetail}&gt;&gt; - 客户对账设置表实体集的响应</p>
   */
  @RequestMapping(value = "/getFinanceAccountDetail", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse getFinanceAccountDetail(
      @RequestParam(value = "start", required = false, defaultValue = "0") int start,
      @RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
      @RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
    Map<String, Object> map = form.getInnerMap();
    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
    List<Map<String, Object>> list = financeAccountDetailService.getFinanceAccountDetail(start,pageSize,map);
    return new ShResponse(HttpStatus.OK.value(),"获取列表成功",new DatatablesViewPage(list));
  }
  
}