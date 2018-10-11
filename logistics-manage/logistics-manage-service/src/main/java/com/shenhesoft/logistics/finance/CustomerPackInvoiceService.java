package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;
import com.shenhesoft.logistics.finance.CustomerPackInvoice;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;

/**
 * 客户打包-发票信息表-业务层接口.
 * <p>
 * <a href="CustomerPackInvoiceService.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-19
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustomerPackInvoiceService {

	/**
	 * 添加客户打包-发票信息表.
	 * 
	 * @param customerPackInvoice
	 *            客户打包-发票信息表实体
	 * @return 页面表单
	 */
	public CustomerPackInvoice addCustomerPackInvoice(CustomerPackInvoice customerPackInvoice);

	/**
	 * 查看客户打包-发票信息表详情.
	 * 
	 * @param id
	 *            主键
	 * @return 页面表单
	 */
	public Map<String, Object> getCustomerPackInvoiceById(String id);

	/**
	 * 修改客户打包-发票信息表.
	 * 
	 * @param customerPackInvoice
	 *            客户打包-发票信息表实体
	 */
	public void editCustomerPackInvoiceById(CustomerPackInvoice customerPackInvoice);

	/**
	 * 删除指定客户打包-发票信息表.
	 * 
	 * @param id
	 *            主键
	 */
	public void delCustomerPackInvoiceById(String id);

	/**
	 * 批量删除指定客户打包-发票信息表.
	 * 
	 * @param ids
	 *            主键集合.
	 */
	public void delCustomerPackInvoiceByIds(List<String> ids);

	/**
	 * 清空计量单位表.
	 */
	public void delCustomerPackInvoices();

	/**
	 * 获取所有客户打包-发票信息表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> getCustomerPackInvoices(int start, int pageSize, Map<String, Object> form);

	/**
	 * 获取所有客户打包-发票信息表.
	 * 
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> getCustomerPackInvoices(Map<String, Object> form);

	/**
	 * 查询客户信息列表
	 * @author dusd
	 * @date 2018年1月19日
	 * @return
	 */
	public List<Map<String, Object>> getCustomerInfos();
	
	/**
	 * 根据id查询客户信息
	 */
	public TbCustomer selectTbCustomerInfoById(Integer id);
	
	/*
	 * 发票登入
	 */
	public CustomerPackInvoice insertCustomerPackInvoice(CustomerPackInvoice customerPackInvoice);

	/*
	 * 发票作废——审核——反审核
	 */
	public void updateInvoiceManagementCancel(CustomerPack customerPack);
	
	/**
	 * 查询发票列表
	 * @author JiangYS
	 * @date 2018年2月8日
	 * @return
	 */
	public List<Map<String, Object>> getInvoiceManagement(int start, int pageSize, Map<String, Object> form);
	
	/**
	 * 查询发票列表.
	 * 
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> getInvoiceManagement(Map<String, Object> form);
}