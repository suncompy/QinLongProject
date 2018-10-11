package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.shenhesoft.logistics.common.annotation.RequestJsonParam;
import com.shenhesoft.logistics.common.page.DatatablesViewPage;
import com.shenhesoft.logistics.common.page.MapWapper;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
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
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class ShortOrderFinanceController {
	@Autowired
	private ShortOrderFinanceService shortOrderFinanceService;

	/**
	 * 补交项目，用;隔开
	 */
	@Value("${RETROACTIVELY_PAY_PROJECT}")
	private String RETROACTIVELY_PAY_PROJECT;

	/**
	 * 添加短驳运单财务表.
	 * 
	 * @param shortOrderFinance
	 *            {@linkplain com.shenhesoft.logistics.finance.ShortOrderFinance
	 *            短驳运单财务表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.ShortOrderFinance}&gt;
	 *         - 短驳运单财务表实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/shortOrderFinance", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse addShortOrderFinance(@RequestBody ShortOrderFinance shortOrderFinance) {
		// 验证表单内容
		AppUtils.validateModel(shortOrderFinance);
		// 新增短驳运单财务表
		return new ShResponse(HttpStatus.CREATED.value(), "保存成功",
				shortOrderFinanceService.addShortOrderFinance(shortOrderFinance));
	}

	/**
	 * 查看短驳运单财务表详情.
	 * 
	 * @param id
	 *            主键
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.ShortOrderFinance}&gt;
	 *         - 短驳运单财务表实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/shortOrderFinances/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getShortOrderFinanceById(@PathVariable("id") String id) {
		Map<String, Object> form = shortOrderFinanceService.getShortOrderFinanceById(id);
		// 获取短驳运单财务表详情
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}
	
	/**
     * 通过projectId查询待对账信息-费用对账.
     * 
     * @param id
     *            主键
     * @return
     *         <p>
     *         {@link ShResponse}&lt;
     *         {@link com.shenhesoft.logistics.finance.ShortOrderFinance}&gt;
     *         - 短驳运单财务表实体的响应
     *         </p>
     */
    @RequestMapping(value = "/checkConf/projectId/pickup/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ShResponse getCheckConfPickupByProjectId(@PathVariable("id") String id) {
      String sysOrgCode = AppSession.getCurrentSysOrgCode();
      Map<String, Object> map = ImmutableMap.of("id", id,"sysOrgCode",sysOrgCode);
      Map<String, Object> form = shortOrderFinanceService.getCheckConfPickupByProjectId(map);
      // 获取短驳运单财务表详情
      return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
    }
	/**
     * 通过projectId查询待对账信息.
     * 
     * @param id
     *            主键
     * @return
     *         <p>
     *         {@link ShResponse}&lt;
     *         {@link com.shenhesoft.logistics.finance.ShortOrderFinance}&gt;
     *         - 短驳运单财务表实体的响应
     *         </p>
     */
    @RequestMapping(value = "/checkConf/projectId/{id}/{checkFlag}", method = RequestMethod.GET)
    @ResponseBody
    public ShResponse getCheckConfByProjectId(@PathVariable("id") String id,@PathVariable("checkFlag") Integer checkFlag) {
      String sysOrgCode = AppSession.getCurrentSysOrgCode();
      Map<String, Object> map = ImmutableMap.of("id", id,"sysOrgCode",sysOrgCode,"checkFlag",checkFlag);
      Map<String, Object> form = shortOrderFinanceService.getCheckConfByProjectId(map);
      // 获取短驳运单财务表详情
      return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
    }

	/**
	 * 修改短驳运单财务表.
	 * 
	 * @param shortOrderFinance
	 *            {@linkplain com.shenhesoft.logistics.finance.ShortOrderFinance
	 *            短驳运单财务表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.ShortOrderFinance}&gt;
	 *         - 短驳运单财务表实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/shortOrderFinance", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse editShortOrderFinanceById(@RequestBody ShortOrderFinance shortOrderFinance) {
		// 验证表单内容
		AppUtils.validateModel(shortOrderFinance);
		// 更新短驳运单财务表信息
		shortOrderFinanceService.editShortOrderFinanceById(shortOrderFinance);

		return new ShResponse(HttpStatus.OK.value(), "修改成功", shortOrderFinance);
	}

	/**
	 * 删除指定短驳运单财务表.
	 * 
	 * @param id
	 *            主键
	 * @return 删除提示
	 */
	@RequestMapping(value = "/shortOrderFinances/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ShResponse delShortOrderFinanceById(@PathVariable("id") String id) {

		shortOrderFinanceService.delShortOrderFinanceById(id);

		return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
	}

	/**
	 * 批量删除指定短驳运单财务表.
	 * 
	 * @param ids
	 *            主键集合
	 * @return 删除提示
	 */
	@RequestMapping(value = "/shortOrderFinances/batch", method = RequestMethod.DELETE)
	@ResponseBody
	public ShResponse delShortOrderFinanceByIds(@RequestJsonParam(value = "ids", required = false) List<String> ids) {

		shortOrderFinanceService.delShortOrderFinanceByIds(ids);

		return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
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
	 *         {@link com.shenhesoft.logistics.finance.ShortOrderFinance}
	 *         &gt;&gt; - 短驳运单财务表实体集的响应
	 *         </p>
	 */
	@RequestMapping(value = "/shortOrderFinances", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getShortOrderFinances(
			@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = shortOrderFinanceService.getShortOrderFinances(start, pageSize,
				map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	
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
	@RequestMapping(value = "/getShortOrderFinancesByProjectId", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getShortOrderFinancesByProjectId(
			@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
		Map<String, Object> queryMap = form.getInnerMap();
		if(queryMap.get("projectId") == null) {
			return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(Lists.newArrayList()));
		}
		queryMap.put("financeStatus", 3);
        queryMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = shortOrderFinanceService.getShortOrderFinances(start, pageSize,queryMap);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}

	/**
	 * 批量进行计费确认
	 * 
	 * @param ids
	 *            主键集合
	 * @return 计费确认提示
	 */
	@RequestMapping(value = "/shortOrderFinance/billingVerify/{shOrderFinIds}/{flag}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse billingVerifyShortOrderFinanceByIds(@PathVariable("shOrderFinIds") String shOrderFinIds,@PathVariable("flag") String flag,@RequestBody(required = false) TbSystemUser user) {
		shortOrderFinanceService.billingVerifyShortOrderFinanceByIds(shOrderFinIds,user,flag);
		String msg = "";
		if(flag.equals("0")) {
			msg = "计费确认成功";
		}else {
			msg = "计费确认不通过";
		}
		return new ShResponse(HttpStatus.NO_CONTENT.value(), msg);
	}

	/**
	 * 批量进行计费反确认
	 * 
	 * @param ids
	 *            主键集合
	 * @return 计费确认提示
	 */
	@RequestMapping(value = "/shortOrderFinance/againstVerify/{shOrderFinIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse againstVerifyShortOrderFinanceByIds(@PathVariable("shOrderFinIds") String shOrderFinIds) {
		shortOrderFinanceService.againstVerifyShortOrderFinanceByIds(shOrderFinIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "计费反确认成功");
	}

	/**
	 * 批量进行财务审核
	 * 
	 * @param ids
	 *            主键集合
	 * @return 计费确认提示
	 */
	@RequestMapping(value = "/shortOrderFinance/financeAudit/{shOrderFinIds}/{flag}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse financeAuditShortOrderFinanceByIds(@PathVariable("shOrderFinIds") String shOrderFinIds,@PathVariable("flag") String flag) {
		shortOrderFinanceService.financeAuditShortOrderFinanceByIds(shOrderFinIds,new TbSystemUser(),flag);
		String msg = "";
		if(flag.equals("0")) {
			msg = "财务审核成功";
		}else {
			msg = "财务审核不通过";
		}
		return new ShResponse(HttpStatus.NO_CONTENT.value(), msg);
	}

	/**
	 * 批量进行财务反审核
	 * 
	 * @param ids
	 *            主键集合
	 * @return 计费确认提示
	 */
	@RequestMapping(value = "/shortOrderFinance/againstAudit/{shOrderFinIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse againstAuditShortOrderFinanceByIds(@PathVariable("shOrderFinIds") String shOrderFinIds) {
		shortOrderFinanceService.againstAuditShortOrderFinanceByIds(shOrderFinIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务反审核成功");
	}

	/**
	 * 批量进行计算运费
	 * 
	 * @param ids
	 *            主键集合
	 * @return 计费确认提示
	 */
	@RequestMapping(value = "/shortOrderFinance/billingFreight/{shOrderFinIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse billingFreightShortOrderFinanceByIds(@PathVariable("shOrderFinIds") String shOrderFinIds) {
		shortOrderFinanceService.billingFreightShortOrderFinanceByIds(shOrderFinIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "计算运费成功");
	}

	/**
	 * 获得补交项目列表
	 * 
	 * @author dusd
	 * @date 2018年1月17日
	 * @return
	 */
	@RequestMapping(value = "/shortOrderFinance/retroactivelyPayProject", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse retroactivelyPayProject() {
		List<String> retroactivelyPayProjectList = shortOrderFinanceService
				.listRetroactivelyPayProject(RETROACTIVELY_PAY_PROJECT);
		return new ShResponse(HttpStatus.OK.value(), "计算运费成功", retroactivelyPayProjectList);
	}

	/**
	 * 保存追加金额
	 * 
	 * @author dusd
	 * @date 2018年1月17日
	 * @param shortOrderFinance
	 * @return
	 */
	@RequestMapping(value = "/shortOrderFinance/subsidyShortOrderFinance", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse subsidyShortOrderFinance(@RequestBody ShortOrderFinance shortOrderFinance,
			HttpSession httpSession) {
		// 验证表单内容
		AppUtils.validateModel(shortOrderFinance);
		TbSystemUser user = (TbSystemUser) httpSession.getAttribute("systemUser");
		// 保存追加金额
		shortOrderFinanceService.subsidyShortOrderFinance(shortOrderFinance, user);
		return new ShResponse(HttpStatus.OK.value(), "保存追加金额成功", shortOrderFinance);
	}

}