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
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 三方应收款管理表-控制层Action.
 * <p>
 * <a href="ThreePartiesReceivablesController.java"><i>View Source</i></a>
 * </p>
 * @author shilvfei
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class ThreePartiesReceivablesController {

	@Autowired
	private ThreePartiesReceivablesService threePartiesReceivablesService;
	
	/**
	 * 添加财务调整信息表.
	 * 
	 * @param ThreePartiesReceivables
	 *            {@linkplain com.shenhesoft.logistics.finance.ThreePartiesReceivables
	 *            财务调整信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.ThreePartiesReceivables}&gt; -
	 *         财务调整信息实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/threePartiesReceivables", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse addThreePartiesReceivables(@RequestBody ThreePartiesReceivables threePartiesReceivables) {
		// 验证表单内容
		AppUtils.validateModel(threePartiesReceivables);
		TbSystemUser user = AppSession.getCurrentUser();
		// 新增财务调整信息
		return new ShResponse(HttpStatus.CREATED.value(), "保存成功", threePartiesReceivablesService.addThreePartiesReceivables(threePartiesReceivables,user));
	}
	
	/**
	 * 获取所有三方收款信息表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            {@linkplain com.shenhesoft.logistics.finance.ThreePartiesReceivables
	 *            三方收款信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;List&lt;
	 *         {@link com.shenhesoft.logistics.finance.ThreePartiesReceivables}&gt;&gt; -
	 *         三方收款信息表实体集的响应
	 *         </p>
	 */
	@RequestMapping(value = "/getAllThreePartiesReceivables", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getAllThreePartiesReceivables(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
       map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = threePartiesReceivablesService.getThreePartiesReceivables(start, pageSize, map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
}
