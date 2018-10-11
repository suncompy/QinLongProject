package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 财户调整信息表-控制层Action.
 * <p>
 * <a href="AdjustAccountController.java"><i>View Source</i></a>
 * </p>
 * @author shilvfei
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class AdjustAccountController {
	@Autowired
	private AdjustAccountService adjustAccountService;
	
	/**
	 * 添加财务调整信息表.
	 * 
	 * @param AdjustAccount
	 *            {@linkplain com.shenhesoft.logistics.finance.AdjustAccount
	 *            财务调整信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.AdjustAccount}&gt; -
	 *         财务调整信息实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/adjustAccount", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse addAdjustAccount(@RequestBody AdjustAccount adjustAccount) {
		// 验证表单内容
		AppUtils.validateModel(adjustAccount);
		TbSystemUser user = AppSession.getCurrentUser();
		adjustAccount.setAdjustCode(DateUtils.getCurrentTime());
		// 新增财务调整信息
		return new ShResponse(HttpStatus.CREATED.value(), "保存成功", adjustAccountService.addAdjustAccount(adjustAccount,user));
	}
	
	/**
	 * 获取所有财务调整信息表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            {@linkplain com.shenhesoft.logistics.finance.AdjustAccount
	 *            财务调整信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;List&lt;
	 *         {@link com.shenhesoft.logistics.finance.AdjustAccount}&gt;&gt; -
	 *         财务调整信息表实体集的响应
	 *         </p>
	 */
	@RequestMapping(value = "/adjustAccounts", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getAdjustAccounts(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = adjustAccountService.getAdjustAccounts(start, pageSize, map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
}
