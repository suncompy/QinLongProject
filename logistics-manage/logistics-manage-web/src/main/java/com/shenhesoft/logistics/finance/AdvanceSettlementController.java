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
 * 预付款结算-控制层Action.
 * <p>
 * <a href="CostOrderFinanceController.java"><i>View Source</i></a>
 * </p>
 * @author Liangdeng
 * @date 2018-2-1
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class AdvanceSettlementController {

	@Autowired
	private AdvanceSettlementService advanceSettlementService;
	
	/**
	 * @description 预付款-项目 总金额信息
	 * @author LiangDeng
	 * @date 2018年2月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/projectAdvance", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getProjectAdvance(
			@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = advanceSettlementService.getProjectAdvance(start, pageSize,map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	
	/**
	 * @description 预付款-项目 总金额详细存入抵用信息
	 * @author LiangDeng
	 * @date 2018年2月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/projectAdvanceByProjectId", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse projectAdvanceByProjectId(
			@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = advanceSettlementService.getProjectAdvanceByProjectId(start, pageSize,map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	
	/**
	 * @description 预付款-项目 获取单位下账户列表
	 * @author LiangDeng
	 * @date 2018年2月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getAccountByUnitId", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getAccountByUnitId(@RequestParam Map<String, Object> map) {
        map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> form = advanceSettlementService.getAccountByUnitId(map);
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}
	
	/**
	 * @description 预付款-项目 根据账户获取纳税识别号
	 * @author LiangDeng
	 * @date 2018年2月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getAccountDetailById", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getAccountDetailById(@RequestParam Map<String, Object> map) {
        map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		Map<String, Object> form = advanceSettlementService.getAccountDetailById(map);
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}
	
	/**
	 * @description 存入 抵用 保存
	 * @author LiangDeng
	 * @date 2018年2月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addAdvanceCharge", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse addAdvanceCharge(@RequestBody AdvanceCharge advanceCharge) {
		// 验证表单内容
		AppUtils.validateModel(advanceCharge);
		// 新增短驳运单财务表
		return new ShResponse(HttpStatus.CREATED.value(), "保存成功",advanceSettlementService.addAdvanceCharge(advanceCharge));
	}
	
	/**
	 * @description 财务审核
	 * @author LiangDeng
	 * @date 2018年2月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/accountAudit/{accountChargeIds}/{flag}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse accountAuditByIds(@PathVariable("accountChargeIds") String accountChargeIds,@PathVariable("flag") String flag) {
		advanceSettlementService.accountAuditStatusByIds(accountChargeIds,flag);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务审核成功");
	}
	
	/**
	 * @description 反审核
	 * @author LiangDeng
	 * @date 2018年2月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/backAccountAudit/{accountChargeIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse backAccountAuditByIds(@PathVariable("accountChargeIds") String accountChargeIds) {
		advanceSettlementService.backAccountAuditStatusByIds(accountChargeIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "反审核成功");
	}
	
	/**
	 * @description 根据项目id查询已经存入的账户
	 * @author LiangDeng
	 * @date 2018年2月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getDepostAccountByProjectId", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getDepostAccountByProjectId(@RequestParam Map<String, Object> map) {
        map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> form = advanceSettlementService.getDepostAccountByProjectId(map);
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}
	
	/**
	 * @description 根据项目id查询存入时的支出账户 （提现账户）
	 * @author LiangDeng
	 * @date 2018年3月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getCashAccountByProjectId", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getCashAccountByProjectId(@RequestParam Map<String, Object> map) {
        map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> form = advanceSettlementService.getCashAccountByProjectId(map);
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}
	
	/**
	 * @description 根据项目id查询火运请车单号
	 * @author LiangDeng
	 * @date 2018年2月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getTrainInfoByBillName", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getTrainInfoByBillName(@RequestParam Map<String, Object> map) {
        map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> form = advanceSettlementService.getTrainInfoByBillName(map);
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}
	
	/**
	 * @description 根据火运id查询火运运单详情
	 * @author LiangDeng
	 * @date 2018年2月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getTrainInfoByTrainId", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getTrainInfoByTrainId(@RequestParam Map<String, Object> map) {
        map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		Map<String, Object> form = advanceSettlementService.getTrainInfoByTrainId(map);
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}
	
	/**
	 * @description 详情
	 * @author LiangDeng
	 * @date 2018年2月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getDetailInfoByAcId", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getDetailInfoByAcId(@RequestParam Map<String, Object> map) {
        map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		Map<String, Object> form = advanceSettlementService.getDetailInfoByAcId(map);
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}
	
	/**
	 * @description 根据项目id查询费用对账id
	 * @author LiangDeng
	 * @date 2018年2月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getCostByBillName", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getCostByBillName(@RequestParam Map<String, Object> map) {
        map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> form = advanceSettlementService.getCostByBillName(map);
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}
	
	/**
	 * @description 根据费用对账id查询对账详情
	 * @author LiangDeng
	 * @date 2018年2月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getCostInfoByCostId", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getCostInfoByCostId(@RequestParam Map<String, Object> map) {
        map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		Map<String, Object> form = advanceSettlementService.getCostInfoByCostId(map);
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}
	
	/**
	 * @description 客户 获取经办人
	 * @author LiangDeng
	 * @date 2018年2月10日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getCousterAgentById", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getCousterAgentById(@RequestParam Map<String, Object> map) {
	    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> form = advanceSettlementService.getCousterAgentById(map);
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}
	
	/**
	 * @description 根据项目id查询已经存入的账户的信息
	 * @author LiangDeng
	 * @date 2018年2月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getDepostAccountByProjectIdDetail", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getDepostAccountByProjectIdDetail(@RequestParam Map<String, Object> map) {
        map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		Map<String, Object> form = advanceSettlementService.getDepostAccountByProjectIdDetail(map);
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}
	
	/**
	 * @description 财务审核
	 * @author LiangDeng
	 * @date 2018年2月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/deleteAdvance/{accountChargeId}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse deleteAdvance(@PathVariable("accountChargeId") String accountChargeId) {
		advanceSettlementService.deleteAdvance(accountChargeId);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
	}
}
