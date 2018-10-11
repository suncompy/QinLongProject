package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.finance.CustomerPackOrder;

/**
 * 客户打包-运单中间表-Dao.
 * <p>
 * <a href="CustomerPackOrderMapper.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustomerPackOrderMapper {

	/**
	 * 新增客户打包-运单中间表.
	 * 
	 * @param customerPackOrder
	 *            客户打包-运单中间表实体
	 * @return dao成功失败标志
	 */
	int addCustomerPackOrder(CustomerPackOrder customerPackOrder);

	/**
	 * 批量新增客户打包-运单中间表.
	 * 
	 * @param list
	 *            批量客户打包-运单中间表实体
	 */
	void addCustomerPackOrders(@Param(value = "list") List<CustomerPackOrder> list);

	/**
	 * 查看客户打包-运单中间表详情.
	 * 
	 * @param id
	 *            主键
	 * @return 页面表单
	 */
	Map<String, Object> getCustomerPackOrderById(String id);

	/**
	 * 修改客户打包-运单中间表.
	 * 
	 * @param customerPackOrder
	 *            客户打包-运单中间表实体
	 * @return dao成功失败标志
	 */
	int editCustomerPackOrderById(CustomerPackOrder customerPackOrder);

	/**
	 * 批量修改客户打包-运单中间表.
	 * 
	 * @param customerPackOrder
	 *            客户打包-运单中间表实体
	 * @param ids
	 *            主键集合
	 */
	void editCustomerPackOrderByIds(@Param("map") CustomerPackOrder customerPackOrder, @Param("list") List<String> ids);

	/**
	 * 删除指定客户打包-运单中间表.
	 * 
	 * @param id
	 *            主键
	 * @return dao成功失败标志
	 */
	int delCustomerPackOrderById(String id);

	/**
	 * 批量删除指定客户打包-运单中间表.
	 * 
	 * @param ids
	 *            主键集合
	 * @return dao成功失败标志
	 */
	int delCustomerPackOrderByIds(@Param("list") List<String> ids);

	/**
	 * 清空计量单位表.
	 * 
	 */
	void delCustomerPackOrders();

	/**
	 * 获取所有客户打包-运单中间表.
	 * 
	 * @param form
	 *            页面表单
	 * @return 结果集合
	 */
	List<Map<String, Object>> getCustomerPackOrders(@Param(value = "map") Map<String, Object> form);

	/**
	 * 通过打包信息ids查询 客户打包-运单中间表 列表
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custPackIdList
	 * @return
	 */
	List<Map<String, Object>> listCustomerPackOrderByCustPackIds(List<String> custPackIdList);
}