package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.finance.AdvanceCharge;

public interface AdvanceSettlementMapper {

	List<Map<String, Object>> getProjectAdvance(@Param("map") Map<String, Object> form);

	List<Map<String, Object>> getProjectAdvanceByProjectId(@Param("map") Map<String, Object> form);

	List<Map<String, Object>> getAccountByUnitId(@Param("map") Map<String, Object> map);

	Map<String, Object> getAccountDetailById(@Param("map") Map<String, Object> map);

	int addAdvanceCharge(AdvanceCharge advanceCharge);

	void accountAuditStatusByIds(@Param("map") AdvanceCharge ac,  @Param("list") List<String> accountIdList);

	Map<String, Object> getAdvanceChargeDetailByid(@Param("id") Integer id);

	List<Map<String, Object>> getDepostAccountByProjectId(@Param("map") Map<String, Object> map);

	List<Map<String, Object>> getTrainInfoByBillName(@Param("map") Map<String, Object> map);

	List<Map<String, Object>> getCostByBillName(@Param("map") Map<String, Object> map);

	Map<String, Object> getCostInfoByCostId(@Param("map") Map<String, Object> map);

	List<Map<String, Object>> getCousterAgentById(@Param("map") Map<String, Object> map);

	List<Map<String, Object>> getCashAccountByProjectId(@Param("map") Map<String, Object> map);

	AdvanceCharge selectExistAdvanceCharge(@Param("map") Map<String, Object> map);

	int updateAdvanceCharge(AdvanceCharge advanceCharge);

	Map<String, Object> getDepostAccountByProjectIdDetail(@Param("map") Map<String, Object> map);

	void deleteAdvanceById(@Param("id") Integer id);

	int delAdvanceByProjectId(@Param("id") Integer id);

}
