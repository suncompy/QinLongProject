package com.shenhesoft.logistics.finance.mapper;
import com.shenhesoft.logistics.finance.CostOrderFinance;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CostOrderFinanceMapper {
	/**
	 * 新增短驳运单财务表.
	 * 
	 * @param CostOrderFinance
	 *            短驳运单财务表实体
	 * @return dao成功失败标志
	 */
	int addCostOrderFinance(CostOrderFinance CostOrderFinance);

	/**
	 * 批量新增短驳运单财务表.
	 * 
	 * @param list
	 *            批量短驳运单财务表实体
	 */
	void addCostOrderFinances(@Param(value = "list") List<CostOrderFinance> list);

	/**
	 * 查看短驳运单财务表详情.
	 * 
	 * @param id
	 *            主键
	 * @return 页面表单
	 */
	Map<String, Object> getCostOrderFinanceById(String id);

	/**
	 * 修改短驳运单财务表.
	 * 
	 * @param CostOrderFinance
	 *            短驳运单财务表实体
	 * @return dao成功失败标志
	 */
	int editCostOrderFinanceById(CostOrderFinance shortOrderFinance);

	/**
	 * 批量修改短驳运单财务表.
	 * 
	 * @param CostOrderFinance
	 *            短驳运单财务表实体
	 * @param ids
	 *            主键集合
	 */
	void editCostOrderFinanceByIds(@Param("map") CostOrderFinance shortOrderFinance, @Param("list") List<String> ids);

	/**
	 * 删除指定短驳运单财务表.
	 * 
	 * @param id
	 *            主键
	 * @return dao成功失败标志
	 */
	int delCostOrderFinanceById(String id);

	/**
	 * 批量删除指定短驳运单财务表.
	 * 
	 * @param ids
	 *            主键集合
	 * @return dao成功失败标志
	 */
	int delCostOrderFinanceByIds(@Param("list") List<String> ids);

	/**
	 * 清空计量单位表.
	 * 
	 */
	void delCostOrderFinances();

	/**
	 * 获取所有短驳运单财务表.
	 * 
	 * @param form
	 *            页面表单
	 * @return 结果集合
	 */
	List<Map<String, Object>> getCostOrderFinances(@Param(value = "map") Map<String, Object> form);

	/**
	 * 运单打包领取人列表
	 * @author dusd
	 * @date 2018年1月18日
	 * @return
	 */
	List<Map<String, Object>> listPackReceiveDriverByIds(@Param("list") List<String> ids);
}