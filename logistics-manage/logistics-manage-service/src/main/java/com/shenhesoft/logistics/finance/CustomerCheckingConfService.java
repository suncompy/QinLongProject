package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

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
public interface CustomerCheckingConfService {

	/**
	 * 添加客户对账设置表.
	 * 
	 * @param customerCheckingConf
	 *            客户对账设置表实体
	 * @return 页面表单
	 */
	public CustomerCheckingConf addCustomerCheckingConf(CustomerCheckingConf customerCheckingConf);

	/**
	 * 查看客户对账设置表详情.
	 * 
	 * @param id
	 *            主键
	 * @return 页面表单
	 */
	public Map<String, Object> getCustomerCheckingConfById(String id);

	/**
	 * 修改客户对账设置表.
	 * 
	 * @param customerCheckingConf
	 *            客户对账设置表实体
	 */
	public void editCustomerCheckingConfById(CustomerCheckingConf customerCheckingConf);

	/**
	 * 删除指定客户对账设置表.
	 * 
	 * @param id
	 *            主键
	 */
	public void delCustomerCheckingConfById(String id);

	/**
	 * 批量删除指定客户对账设置表.
	 * 
	 * @param ids
	 *            主键集合.
	 */
	public void delCustomerCheckingConfByIds(List<String> ids);

	/**
	 * 清空计量单位表.
	 */
	public void delCustomerCheckingConfs();

	/**
	 * 获取所有客户对账设置表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> getCustomerCheckingConfs(int start, int pageSize, Map<String, Object> form);

	/**
	 * 获取所有客户对账设置表.
	 * 
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> getCustomerCheckingConfs(Map<String, Object> form);

	/**
	 * 客户对账 对账明细 确认对账
	 * @author dusd
	 * @date 2018年1月18日
	 * @param custCheckConIds
	 * @param flag 
	 */
	public void editSureCheckingCustomerCheckingConfById(String custCheckConIds, int checkingStatus);

	/**
	 * 反确认
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custCheckConIds
	 */
	public void editAgainstVerifyCustomerCheckingConfById(String custCheckConIds);

	/**
	 * 客户对账 对账明细 财务审核
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custCheckConIds
	 * @param user 
	 */
	public void editFinanceAuditCustomerCheckingConfById(String custCheckConIds,int checkingStatus, TbSystemUser user);

	/**
	 * 客户对账 对账明细 反审核
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custCheckConIds
	 */
	public void editAgainstAuditCustomerCheckingConfById(String custCheckConIds);

	/**
	 * 通过客户对账设置表主键查询所有适合的运单列表
	 * @author dusd
	 * @date 2018年1月19日
	 * @param start
	 * @param pageSize
	 * @param innerMap
	 * @return
	 */
	public List<Map<String, Object>> getOrdersByCustCheckConId(int start, int pageSize, Map<String, Object> innerMap);
	  /**
	   * 获取客户对账列表app.
	   * @param form 页面表单
	   * @return 结果集合
	   */
	public List<Map<String, Object>> getCustomerCheckingApp(int start, int pageSize, Map<String, Object> form);
}