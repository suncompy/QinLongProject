package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.finance.ShortOrderFinance;

/**
 * 短驳运单财务表-Dao.
 * <p>
 * <a href="ShortOrderFinanceMapper.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ShortOrderFinanceMapper {

	/**
	 * 新增短驳运单财务表.
	 * 
	 * @param shortOrderFinance
	 *            短驳运单财务表实体
	 * @return dao成功失败标志
	 */
	int addShortOrderFinance(ShortOrderFinance shortOrderFinance);

	/**
	 * 批量新增短驳运单财务表.
	 * 
	 * @param list
	 *            批量短驳运单财务表实体
	 */
	void addShortOrderFinances(@Param(value = "list") List<ShortOrderFinance> list);

	/**
	 * 查看短驳运单财务表详情.
	 * 
	 * @param id
	 *            主键
	 * @return 页面表单
	 */
	Map<String, Object> getShortOrderFinanceById(String id);
	/**
     * 通过projectId查询待对账信息.
     * @param checkFlag 0司机对账1费用对账2客户对账
     * @param id
     *            主键
     * @return 页面表单
     */
	Map<String, Object> getCheckConfPickupByProjectId(Map<String, Object> form);
	/**
     * 通过projectId查询待对账信息.
     * @param checkFlag 0司机对账1费用对账2客户对账
     * @param id
     *            主键
     * @return 页面表单
     */
    Map<String, Object> getCheckConfByProjectId(Map<String, Object> form);
	/**
	 * 修改短驳运单财务表.
	 * 
	 * @param shortOrderFinance
	 *            短驳运单财务表实体
	 * @return dao成功失败标志
	 */
	int editShortOrderFinanceById(ShortOrderFinance shortOrderFinance);

	/**
	 * 批量修改短驳运单财务表.
	 * 
	 * @param shortOrderFinance
	 *            短驳运单财务表实体
	 * @param ids
	 *            主键集合
	 */
	void editShortOrderFinanceByIds(@Param("map") ShortOrderFinance shortOrderFinance, @Param("list") List<String> ids);

	/**
	 * 删除指定短驳运单财务表.
	 * 
	 * @param id
	 *            主键
	 * @return dao成功失败标志
	 */
	int delShortOrderFinanceById(String id);

	/**
	 * 批量删除指定短驳运单财务表.
	 * 
	 * @param ids
	 *            主键集合
	 * @return dao成功失败标志
	 */
	int delShortOrderFinanceByIds(@Param("list") List<String> ids);

	/**
	 * 清空计量单位表.
	 * 
	 */
	void delShortOrderFinances();

	/**
	 * 获取所有短驳运单财务表.
	 * 
	 * @param form
	 *            页面表单
	 * @return 结果集合
	 */
	List<Map<String, Object>> getShortOrderFinances(@Param(value = "map") Map<String, Object> form);

	/**
	 * 运单打包领取人列表
	 * @author dusd
	 * @date 2018年1月18日
	 * @return
	 */
	List<Map<String, Object>> listPackReceiveDriverByIds(@Param("list") List<String> ids);
	/**
     * 根据领取人-返回司机开户行信息
     * @author dusd
     * @date 2018年1月18日
     * @return
     */
    Map<String, Object> getDriverBankByDriverId(String id);
}