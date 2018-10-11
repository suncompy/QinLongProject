package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description
 * 
 * @author shilvfei
 * 
 * @date 2018年1月30日
 */
public interface CostPackService {

	CostPack addCostPack(CostPack costPack, String shOrderFinIds, TbSystemUser user);
	
	/**
	 * 获取费用对账明细列表
	 * 
	 * @return 短驳运单财务表分页
	 */
	List<Map<String, Object>> getCostPacks(int start, int pageSize, Map<String, Object> innerMap);

	/**
	 * 获取费用对账明细列表
	 * 
	 * @return 短驳运单财务表不分页
	 */
	public List<Map<String, Object>> getCostPacks(Map<String, Object> form) ;

	/**
	 * 司机打包 对账明细 解包
	 * 
	 */
	void dissolveCostPacksByIds(String costPackIds);

	/**
	 * 司机打包 对账明细 财务审核
	 * @param flag 
	 * 
	 * @param ids
	 *            主键集合
	 */
	void financeAuditCostPackByIds(String costPackIds, String flag);
	
	/**
	 * 司机打包 对账明细 反审核
	 * 
	 * @param ids
	 *            主键集合
	 */
	void againstAuditCostPackByIds(String costPackIds);

	
	/**
	 * 企业应收款 结算
	 * 
	 * @param 
	 */
	void settlePassCostPackByIds(CostPack costPack);
	
	/**
	 * 企业应收款结算 受理明细 财务审核 
	 * 
	 * @param ids 主键集合
	 * @param passFlag 财务审核是否通过 0-通过 1-不通过
	 *            
	 */
	void settleFinanceAuditCostPackByIds(String shPackIds, String passFlag);

	/**
	 * 企业应收款结算 受理明细 反审核 
	 * 
	 * @param ids 主键集合
	 *            
	 */
	void settleAgainstAuditCostPackByIds(String shPackIds);
}
