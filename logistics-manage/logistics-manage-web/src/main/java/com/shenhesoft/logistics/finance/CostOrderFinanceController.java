package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.shenhesoft.logistics.common.annotation.RequestJsonParam;
import com.shenhesoft.logistics.common.page.DatatablesViewPage;
import com.shenhesoft.logistics.common.page.MapWapper;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;

/**
 * 费用对账信息表-控制层Action.
 * <p>
 * <a href="CostOrderFinanceController.java"><i>View Source</i></a>
 * </p>
 * @author shilvfei
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class CostOrderFinanceController {

	@Autowired
	private CostOrderFinanceService costOrderFinanceService;

	/**
	 * 获取所有短驳运单财务表 司机对账 如果项目id是空 则不查询任何数据
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            {@linkplain com.shenhesoft.logistics.finance.ShortOrderFinance
	 *            短驳运单财务表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;List&lt;
	 *         {@link com.shenhesoft.logistics.finance.ShortOrderFinance}
	 *         &gt;&gt; - 短驳运单财务表实体集的响应
	 *         </p>
	 */
	@RequestMapping(value = "/getCostOrderFinancesByProjectId", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getCostOrderFinancesByProjectId(
			@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
		Map<String, Object> queryMap = form.getInnerMap();
		if(queryMap.get("projectId") == null) {
			return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(Lists.newArrayList()));
		}
        queryMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = costOrderFinanceService.getCostOrderFinances(start, pageSize,queryMap);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	
	/**
	 * 获取所有短驳运单财务表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            {@linkplain com.shenhesoft.logistics.finance.ShortOrderFinance
	 *            短驳运单财务表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;List&lt;
	 *         {@link com.shenhesoft.logistics.finance.CostOrderFinance}
	 *         &gt;&gt; - 短驳运单财务表实体集的响应
	 *         </p>
	 */
	@RequestMapping(value = "/costOrderFinances", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getCostOrderFinances(
			@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = costOrderFinanceService.getCostOrderFinances(start, pageSize,
				map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
}
