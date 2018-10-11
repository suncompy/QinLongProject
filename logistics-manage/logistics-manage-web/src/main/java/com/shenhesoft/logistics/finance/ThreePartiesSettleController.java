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
 * 三方结算管理表-控制层Action.
 * <p>
 * <a href="ThreePartiesSettleController.java"><i>View Source</i></a>
 * </p>
 * @author shilvfei
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class ThreePartiesSettleController {

	@Autowired
	private ThreePartiesSettleService threePartiesSettleService;
	
	/**
	 * 添加三方结算信息表.
	 * 
	 * @param ThreePartiesSettle
	 *            {@linkplain com.shenhesoft.logistics.finance.ThreePartiesSettle
	 *            三方结算信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.ThreePartiesSettle}&gt; -
	 *         三方结算信息实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/threePartiesSettle", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse addThreePartiesSettle(@RequestBody ThreePartiesSettle threePartiesSettle) {
		// 验证表单内容
		AppUtils.validateModel(threePartiesSettle);
		TbSystemUser user = AppSession.getCurrentUser();
		// 新增三方结算信息
		return new ShResponse(HttpStatus.CREATED.value(), "保存成功", threePartiesSettleService.addThreePartiesSettle(threePartiesSettle,user));
	}
	
	/**
	 * 获取所有三方结算信息表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            {@linkplain com.shenhesoft.logistics.finance.ThreePartiesSettle
	 *            三方结算信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;List&lt;
	 *         {@link com.shenhesoft.logistics.finance.ThreePartiesSettle}&gt;&gt; -
	 *         三方结算信息表实体集的响应
	 *         </p>
	 */
	@RequestMapping(value = "/getAllThreePartiesSettle", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getAllThreePartiesSettle(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = threePartiesSettleService.getThreePartiesSettle(start, pageSize, map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	
	/**
	 * 三方结算财务审核
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/threePartiesSettle/financeAudit/{settleIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse financeAuditShortPackByIds(@PathVariable("settleIds") String settleIds) {
		threePartiesSettleService.financeAuditShortPackByIds(settleIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务审核成功");
	}
	
	/**
	 * 三方结算反审核
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/threePartiesSettle/againstAudit/{settleIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse againstAuditShortPackByIds(@PathVariable("settleIds") String settleIds) {
		threePartiesSettleService.againstAuditShortPackByIds(settleIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "反审核成功");
	}
}
