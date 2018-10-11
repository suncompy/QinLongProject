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
 * 结算与交账-网点交账-控制层Action.
 * <p>
 * <a href="TbDotAccountPackController.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class TbDotAccountPackController {

	@Autowired
	private TbDotAccountPackService accountPackService;
	
	/**
	 * 添加 网点交账打包信息表.
	 * 
	 * @param TbDotAccountPack
	 *            {@linkplain com.shenhesoft.logistics.finance.TbDotAccountPack
	 *             网点交账打包表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.TbDotAccountPack}&gt; -
	 *         网点交账打包实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/addDotAccountPack", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse addDotAccountPack(@RequestBody TbDotAccountPack dotAccountPack) {
		// 验证表单内容
		AppUtils.validateModel(dotAccountPack);
		TbSystemUser user = AppSession.getCurrentUser();
		TbDotAccountPack tbDotAccountPack = accountPackService.addDotAccountPack(dotAccountPack,user);
		// 新增 网点交账打包信息
		return new ShResponse(HttpStatus.CREATED.value(), "保存成功", tbDotAccountPack);
	}
	
	/**
	 * 获取所有 网点交账打包信息表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            {@linkplain com.shenhesoft.logistics.finance.TbDotAccountPack
	 *            网点交账打包表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;List&lt;
	 *         {@link com.shenhesoft.logistics.finance.TbDotAccountPack}
	 *         &gt;&gt; - 网点交账打包表实体集的响应
	 *         </p>
	 */
	@RequestMapping(value = "/getDotAccountPacks", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getDotAccountPacks(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
		Map<String, Object> map = form.getInnerMap();
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = accountPackService.getDotAccountPacks(start, pageSize, map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	
	
	/**
	 * 网点交账打包信息 财务审核
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/dotAccountPack/financeAudit/{dotAccountPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse financeAuditShortPackByIds(@PathVariable("dotAccountPackIds") String dotAccountPackIds) {
		accountPackService.financeAuditShortPackByIds(dotAccountPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务审核成功");
	}
}
