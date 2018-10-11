package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 费用对账信息表-控制层Action.
 * <p>
 * <a href="CostPackController.java"><i>View Source</i></a>
 * </p>
 * @author shilvfei
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class CostPackController {

	@Autowired
	private CostPackService costPackService;
	
	@Value("${COST_PACK_CODE}")
	private String COST_PACK_CODE;
	
	/**
	 * 添加短驳打包信息表.
	 * 
	 * @param shortPack
	 *            {@linkplain com.shenhesoft.logistics.finance.CostPack
	 *            短驳打包信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.CostPack}&gt; -
	 *         短驳打包信息表实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/costPack", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse addShortPack(@RequestBody CostPack costPack) {
		// 验证表单内容
		AppUtils.validateModel(costPack);
		TbSystemUser user = AppSession.getCurrentUser();
		String shOrderFinIds = costPack.getCostOrderFinIds();
		costPack.setCostPackCode(COST_PACK_CODE+DateUtils.getCurrentTime());
		// 新增短驳打包信息表
		return new ShResponse(HttpStatus.CREATED.value(), "打包成功", costPackService.addCostPack(costPack,shOrderFinIds,user));
	}
	
	/**
	 * 获取所有短驳打包信息表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            {@linkplain com.shenhesoft.logistics.finance.ShortPack
	 *            短驳打包信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;List&lt;
	 *         {@link com.shenhesoft.logistics.finance.ShortPack}&gt;&gt; -
	 *         短驳打包信息表实体集的响应
	 *         </p>
	 */
	@RequestMapping(value = "/costPacks", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getCostPacks(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = costPackService.getCostPacks(start, pageSize, map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	
	/**
	 * 费用打包 对账明细 解包
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/costPacks/dissolve/{costPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse dissolveCostPacksByIds(@PathVariable("costPackIds") String costPackIds) {
		costPackService.dissolveCostPacksByIds(costPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "解包成功");
	}
	
	/**
	 * 司机打包 对账明细 财务审核
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/costPacks/financeAudit/{costPackIds}/{flag}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse financeAuditShortPackByIds(@PathVariable("costPackIds") String costPackIds,@PathVariable("flag") String flag) {
		costPackService.financeAuditCostPackByIds(costPackIds,flag);
		String msg = "";
		if(flag.equals("0")) {
			msg = "财务审核成功";
		}else {
			msg = "财务审核不通过";
		}
		return new ShResponse(HttpStatus.NO_CONTENT.value(), msg);
	}
	
	/**
	 * 司机打包 对账明细 反审核
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/costPacks/againstAudit/{costPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse againstAuditShortPackByIds(@PathVariable("costPackIds") String costPackIds) {
		costPackService.againstAuditCostPackByIds(costPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "反审核成功");
	}
	
	/**
	 * 企业应收款结算
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/costPacks/settlePass", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse settlePassShortPackByIds(@RequestBody CostPack costPack) {
		costPackService.settlePassCostPackByIds(costPack);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "结算成功");
	}
	/**
	 * 企业应收款结算 受理明细 财务审核 
	 * 
	 * @param ids 主键集合
	 * @param passFlag 财务审核是否通过 0-通过 1-不通过
	 *            
	 */
	@RequestMapping(value = "/costPacks/settleFinanceAudit/{shPackIds}/{passFlag}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse settleFinanceAuditShortPackByIds(@PathVariable("shPackIds") String shPackIds,@PathVariable("passFlag") String passFlag) {
		costPackService.settleFinanceAuditCostPackByIds(shPackIds,passFlag);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务审核成功");
	}
	
	/**
	 * 企业应收款结算 受理明细 反审核 
	 * 
	 * @param ids 主键集合
	 *            
	 */
	@RequestMapping(value = "/costPacks/settleAgainstAudit/{shPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse settleAgainstAuditShortPackByIds(@PathVariable("shPackIds") String shPackIds) {
		costPackService.settleAgainstAuditCostPackByIds(shPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "反审核成功");
	}
}
