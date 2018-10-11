package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;

/**
 * 费用对账-业务层接口.
 * <p>
 * <a href="CostOrderFinanceService.java"><i>View Source</i></a>
 * </p>
 * 
 * @author shilvfei
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CostOrderFinanceService {

	/**
	 * 获取费用对账列表
	 * 
	 * @return 短驳运单财务表分页
	 */
	public List<Map<String, Object>> getCostOrderFinances(int start, int pageSize, Map<String, Object> queryMap);
	
	/**
	 * 短驳运单 等待回单后 保存一条财务信息
	 * 
	 * @author dusd
	 * @date 2018年1月16日
	 * @param order
	 *            运单信息
	 * @param userId
	 *            当前登录用户id
	 */
	public void addCostOrderFinanceByTborder(TbOrder order, Integer id);
	public CostOrderFinance addCostOrderFinance(CostOrderFinance costOrderFinance);
}
