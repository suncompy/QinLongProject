package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 客户对账打包信息表-业务层接口.
 * <p>
 * <a href="CustomerPackService.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustomerPackService {

	/**
	 * 添加客户对账打包信息表.
	 * 
	 * @param customerPack
	 *            客户对账打包信息表实体
	 * @return 页面表单
	 */
	public CustomerPack addCustomerPack(CustomerPack customerPack);

	/**
	 * 查看客户对账打包信息表详情.
	 * 
	 * @param id
	 *            主键
	 * @return 页面表单
	 */
	public Map<String, Object> getCustomerPackById(String id);

	/**
	 * 修改客户对账打包信息表.
	 * 
	 * @param customerPack
	 *            客户对账打包信息表实体
	 */
	public void editCustomerPackById(CustomerPack customerPack);

	/**
	 * 删除指定客户对账打包信息表.
	 * 
	 * @param id
	 *            主键
	 */
	public void delCustomerPackById(String id);

	/**
	 * 批量删除指定客户对账打包信息表.
	 * 
	 * @param ids
	 *            主键集合.
	 */
	public void delCustomerPackByIds(List<String> ids);

	/**
	 * 清空计量单位表.
	 */
	public void delCustomerPacks();

	/**
	 * 获取所有客户对账打包信息表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> getCustomerPacks(int start, int pageSize, Map<String, Object> form);

	/**
	 * 获取所有客户对账打包信息表.
	 * 
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> getCustomerPacks(Map<String, Object> form);

	/**
	 * 汽运(接取/火运/送达)对账明细 财务审核 
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custPackIds
	 * @param passFlag
	 * @param user 
	 */
	public void editInitialFinanceAuditCustomerPackById(String custPackIds, String passFlag, TbSystemUser user);

	/**
	 * 客户对账 汽运(接取/火运/送达)对账明细  解包
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custPackIds 客户包ids
	 */
	public void editDissolveCustomerPackById(String custPackIds);

	/**
	 * 汽运(接取/火运/送达)对账明细 反审核
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custPackIds
	 */
	public void editInitialAgainstAuditCustomerPackById(String custPackIds);

	/**
	 * 客户结算 结算明细 结算
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custPackIds
	 */
	public void editSettlePassCustomerPackById(CustomerPack customerPack);

	/**
	 * 客户结算 结算明细 结算 财务审核 
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custPackIds
	 * @param passFlag
	 */
	public void editSettleFinanceAuditCustomerPackById(String custPackIds, String passFlag);

	/**
	 * 客户结算 结算明细 反审核
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custPackIds
	 */
	public void editSettleAgainstAuditCustomerPackById(String custPackIds);

	/**
	 * 费用账目 发票管理 发票作废申请
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custPackIds
	 */
	public void editInvoiceCancelApplyCustomerPackById(String custPackIds);

	/**
	 * 发票作废审核
	 * @param passFlag 财务审核是否通过 0-通过 1-不通过
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custPackIds
	 * @param passFlag
	 */
	public void editInvoiceCancelAuditCustomerPackById(String custPackIds, String passFlag);

	/**
	 * 费用账目 发票管理 发票作废反审核
	 * @author dusd
	 * @date 2018年1月19日
	 * @param custPackIds
	 */
	public void editInvoiceCancelAgainstAuditCustomerPackById(String custPackIds);

	/**
	 * 点击包信息查询对应的运单列表
	 * @author dusd
	 * @date 2018年1月19日
	 * @param start
	 * @param pageSize
	 * @param innerMap
	 * @return
	 */
	public List<Map<String, Object>> getOrderInfoByCustPackId(int start, int pageSize, Map<String, Object> innerMap);

	
	/**
	 * @description 通过项目id获取客户对账信息
	 * @date 2018年2月10日
	 * @author shilvfei
	 * @param projectId
	 * @return
	 */
	public List<Map<String, Object>> getCustomerPacksByProjectId(String projectId);

}