package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

/**
 * 预付款结算.
 * <p>
 * <a href="CostOrderFinanceController.java"><i>View Source</i></a>
 * </p>
 * @author Liangdeng
 * @date 2018-2-1
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AdvanceSettlementService {

	List<Map<String, Object>> getProjectAdvance(int start, int pageSize, Map<String, Object> innerMap);

	List<Map<String, Object>> getProjectAdvanceByProjectId(int start, int pageSize, Map<String, Object> innerMap);

	List<Map<String, Object>> getAccountByUnitId(Map<String, Object> map);

	Map<String, Object> getAccountDetailById(Map<String, Object> map);

	public AdvanceCharge addAdvanceCharge(AdvanceCharge advanceCharge);

	public void accountAuditStatusByIds(String accountChargeIds, String flag);

	public void backAccountAuditStatusByIds(String accountChargeIds);

	List<Map<String, Object>> getDepostAccountByProjectId(Map<String, Object> map);

	List<Map<String, Object>> getTrainInfoByBillName(Map<String, Object> map);

	Map<String, Object> getTrainInfoByTrainId(Map<String, Object> map);

	Map<String, Object> getDetailInfoByAcId(Map<String, Object> map);

	List<Map<String, Object>> getCostByBillName(Map<String, Object> map);

	Map<String, Object> getCostInfoByCostId(Map<String, Object> map);

	List<Map<String, Object>> getCousterAgentById(Map<String, Object> map);

	List<Map<String, Object>> getCashAccountByProjectId(Map<String, Object> map);

	Map<String, Object> getDepostAccountByProjectIdDetail(Map<String, Object> map);

	public void deleteAdvance(String accountChargeId);


}
