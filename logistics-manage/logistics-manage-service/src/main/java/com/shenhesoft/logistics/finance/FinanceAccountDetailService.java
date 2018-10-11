package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 客户对账设置表-业务层接口.
 * <p>
 * <a href="CustomerCheckingConfService.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FinanceAccountDetailService {

	/**
	 * 添加账户流水详情表.
	 * 
	 * @param financeAccountDetail
	 *            客户对账设置表实体
	 * @return 页面表单
	 */
	public FinanceAccountDetail addFinanceAccountDetail(FinanceAccountDetail financeAccountDetail);


	/**
	 * 获取账户流水详情表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> getFinanceAccountDetail(int start, int pageSize, Map<String, Object> form);

	/**
	 * 获取账户流水详情表.
	 * 
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> getFinanceAccountDetail(Map<String, Object> form);

}