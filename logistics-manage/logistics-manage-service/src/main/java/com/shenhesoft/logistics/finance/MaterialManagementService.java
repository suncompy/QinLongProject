package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

/**
 * 油气卡信息表-业务层接口.
 * <p>
 * <a href="Material.java"><i>View Source</i></a>
 * </p>
 * 
 * @author Jys
 * @date 2018-01-26
 * @version 1.0.0
 * @since 1.0.0
 */
public interface MaterialManagementService {

	/**
	 * 添加油气卡信息表.
	 * 
	 * @param Material
	 *            油气卡信息表实体
	 * @param user 
	 * @return 页面表单
	 */
	public void addMaterial(Material materialManagement);
	
	/**
	 * 获取所有油气卡信息表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> queryMaterial(int start, int pageSize, Map<String, Object> form);
	
	/**
	 * 获取所有油气卡信息表.
	 * 
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> queryMaterial(Map<String, Object> form);
	
	/**
	 * 修改审核状态
	 */
	public void updateAuditStatus(String passFlag,String id);

	/**
	 * 获取公司账户.
	 * 
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> queryCompanyAccount();
	
	/**
	 * 通过id获取信息
	 */
	public Map<String, Object> queryMaterialById(String id);
}