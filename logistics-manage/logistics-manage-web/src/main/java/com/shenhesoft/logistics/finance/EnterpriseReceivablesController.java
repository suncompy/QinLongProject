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
 * 企业应收款管理表-控制层Action.
 * <p>
 * <a href="EnterpriseReceivablesController.java"><i>View Source</i></a>
 * </p>
 * @author shilvfei
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class EnterpriseReceivablesController {

	@Autowired
	private EnterpriseReceivablesService enterpriseReceivablesService;
	
	/**
	 * 添加企业应收款信息表.
	 * 
	 * @param ThreePartiesReceivables
	 *            {@linkplain com.shenhesoft.logistics.finance.TbEnterpriseReceivables
	 *            企业应收款表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.TbEnterpriseReceivables}&gt; -
	 *         企业应收款实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/enterpriseReceivables", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse addEnterpriseReceivables(@RequestBody TbEnterpriseReceivables enterpriseReceivables) {
		// 验证表单内容
		AppUtils.validateModel(enterpriseReceivables);
		TbSystemUser user = AppSession.getCurrentUser();
		// 新增企业应收款信息
		return new ShResponse(HttpStatus.CREATED.value(), "保存成功", enterpriseReceivablesService.addEnterpriseReceivables(enterpriseReceivables,user));
	}
	
	/**
	 * 获取所有企业应收款信息表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            {@linkplain com.shenhesoft.logistics.finance.TbEnterpriseReceivables
	 *            三方收款信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;List&lt;
	 *         {@link com.shenhesoft.logistics.finance.TbEnterpriseReceivables}&gt;&gt; -
	 *         企业应收款实体集的响应
	 *         </p>
	 */
	@RequestMapping(value = "/getAllEnterpriseReceivables", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getAllEnterpriseReceivables(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
       map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = enterpriseReceivablesService.getEnterpriseReceivables(start, pageSize, map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	
	/**
	 * 企业应收款结算 受理明细 财务审核 
	 * 
	 * @param ids 主键集合
	 * @param passFlag 财务审核是否通过 0-通过 1-不通过
	 *            
	 */
	@RequestMapping(value = "/enterpriseReceivables/settleFinanceAudit/{enterpriseReceivablesIds}/{passFlag}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse settleFinanceAuditShortPackByIds(@PathVariable("enterpriseReceivablesIds") String enterpriseReceivablesIds,@PathVariable("passFlag") String passFlag) {
		enterpriseReceivablesService.settleFinanceAuditCostPackByIds(enterpriseReceivablesIds,passFlag);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务审核成功");
	}
}
