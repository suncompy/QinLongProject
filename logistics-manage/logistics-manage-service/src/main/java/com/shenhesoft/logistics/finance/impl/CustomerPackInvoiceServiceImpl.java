package com.shenhesoft.logistics.finance.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.finance.CostPack;
import com.shenhesoft.logistics.finance.CustomerPack;
import com.shenhesoft.logistics.finance.CustomerPackInvoice;
import com.shenhesoft.logistics.finance.CustomerPackInvoiceService;
import com.shenhesoft.logistics.finance.mapper.CostPackMapper;
import com.shenhesoft.logistics.finance.mapper.CustomerPackInvoiceMapper;
import com.shenhesoft.logistics.finance.mapper.CustomerPackMapper;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.mapper.TbCustomerMapper;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 客户打包-发票信息表-业务实现.
 * <p>
 * <a href="CustomerPackInvoiceServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-19
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CustomerPackInvoiceServiceImpl implements CustomerPackInvoiceService {

	@Autowired
	private CustomerPackInvoiceMapper customerPackInvoiceMapper;
	@Autowired
	private CostPackMapper costPackMapper;
	/**
	 * 客户信息 列表
	 */
	@Autowired
	private TbCustomerMapper customerMapper;
	/**
	 * 客户对账打包信息表-Dao. 
	 */
	@Autowired
	private CustomerPackMapper customerPackMapper;

	/**
	 * 新增客户打包-发票信息表.
	 * 
	 * @param customerPackInvoice
	 *            客户打包-发票信息表实体
	 * @return 页面表单
	 */
	public CustomerPackInvoice addCustomerPackInvoice(CustomerPackInvoice customerPackInvoice) {
		// 生成id
		customerPackInvoice.setCustPackId(AppUtils.randomUUID());

		customerPackInvoice.setCreateDate(DateUtils.date2Str(DateUtils.datetimeFormat)); // 创建时间
		customerPackInvoice.setCreateUserId(AppSession.getCurrentUserId());// 创建人
		customerPackInvoice.setDeleteFlag(0);// 未删除
		// 保存客户打包-发票信息表
		customerPackInvoiceMapper.addCustomerPackInvoice(customerPackInvoice);
		
		//修改 客户打包 发票状态信息
		CustomerPack customerPack = new CustomerPack();
		// 客户对账打包信息表 包id
		customerPack.setCustPackId(customerPackInvoice.getCustPackId());
		//发票状态(0-新建 1-已登入 2-发票作废待审核 3-已作废 4-作废审核不通过)
		customerPack.setInvoiceStatus(1);
		customerPackMapper.editCustomerPackById(customerPack);
		return customerPackInvoice;
	}

	/**
	 * 查看客户打包-发票信息表详情.
	 * 
	 * @param id
	 *            主键
	 * @return 页面表单
	 */
	public Map<String, Object> getCustomerPackInvoiceById(String id) {
		return customerPackInvoiceMapper.getCustomerPackInvoiceById(id);
	}

	/**
	 * 修改客户打包-发票信息表.
	 * 
	 * @param customerPackInvoice
	 *            客户打包-发票信息表实体
	 */
	public void editCustomerPackInvoiceById(CustomerPackInvoice customerPackInvoice) {
		customerPackInvoiceMapper.editCustomerPackInvoiceById(customerPackInvoice);
	}

	/**
	 * 删除指定客户打包-发票信息表.
	 * 
	 * @param id
	 *            主键
	 */
	public void delCustomerPackInvoiceById(String id) {
		customerPackInvoiceMapper.delCustomerPackInvoiceById(id);
	}

	/**
	 * 批量删除指定客户打包-发票信息表.
	 * 
	 * @param ids
	 *            主键集合
	 */
	public void delCustomerPackInvoiceByIds(List<String> ids) {
		customerPackInvoiceMapper.delCustomerPackInvoiceByIds(ids);
	}

	/**
	 * 清空计量单位表.
	 */
	public void delCustomerPackInvoices() {
		customerPackInvoiceMapper.delCustomerPackInvoices();
	}

	/**
	 * 获取所有客户打包-发票信息表.
	 * 
	 * @return 客户打包-发票信息表分页
	 */
	public List<Map<String, Object>> getCustomerPackInvoices(int start, int pageSize, Map<String, Object> form) {
		PageHelper.offsetPage(start, pageSize);
		return this.getCustomerPackInvoices(form);
	}

	/**
	 * 获取所有客户打包-发票信息表.
	 * 
	 * @return 客户打包-发票信息表
	 */
	public List<Map<String, Object>> getCustomerPackInvoices(Map<String, Object> form) {
		return customerPackInvoiceMapper.getCustomerPackInvoices(form);
	}

	@Override
	public List<Map<String, Object>> getCustomerInfos() {
		return customerMapper.listAllCustomerInfo();
	}

	@Override
	public TbCustomer selectTbCustomerInfoById(Integer id) {
		return customerMapper.selectTbCustomerInfoById(id);
	}

	/* 
	 * 发票登入
	 */
	@Override
	public CustomerPackInvoice insertCustomerPackInvoice(CustomerPackInvoice customerPackInvoice) {
		TbSystemUser user = AppSession.getCurrentUser();
		customerPackInvoice.setCreateDate(DateUtils.date2Str(DateUtils.datetimeFormat));
		customerPackInvoice.setCreateUserId(user.getId());
		customerPackInvoice.setDeleteFlag(0);
		int invoiceType = customerPackInvoice.getInvoiceType();
		if(invoiceType == 1) {
			//修改客户打包表
			CustomerPack customerPack = new CustomerPack();
			customerPack.setCustPackId(customerPackInvoice.getCustPackId());
			customerPack.setInvoiceStatus(1);
			customerPack.setTaxMoney(customerPackInvoice.getTaxMoney());
			customerPack.setBesettledMoney(customerPackInvoice.getTotalMoney());
			customerPackMapper.editCustomerPackById(customerPack);
		}else if(invoiceType == 2) {
			//修改费用打包表
			CostPack costPack = new CostPack();
			costPack.setShPackId(customerPackInvoice.getCustPackId());
			costPack.setInvoiceStatus((byte) 1);
			costPack.setTaxMoney(customerPackInvoice.getTaxMoney());
			costPack.setBesettledMoney(customerPackInvoice.getTotalMoney());
			costPackMapper.editCostPackById(costPack);
			customerPackInvoice.setShPackId(customerPackInvoice.getCustPackId());
			customerPackInvoice.setCustPackId(null);
		}
		customerPackInvoice.setModifiyDate(DateUtils.date2Str(DateUtils.datetimeFormat));
		customerPackInvoiceMapper.insertCustomerPackInvoice(customerPackInvoice);
		
		return customerPackInvoice;
	}

	/* 
	 * 发票作废——审核——反审核
	 */
	@Override
	public void updateInvoiceManagementCancel(CustomerPack customerPack) {
		TbSystemUser user = AppSession.getCurrentUser();
		Integer invoiceStatus = customerPack.getInvoiceStatus();
		if(invoiceStatus == 3) {
			customerPack.setBackId(user.getId().toString());
			customerPack.setBackDate(DateUtils.date2Str(DateUtils.datetimeFormat));
		}else if(invoiceStatus == 1) {
			//发票反审核
			customerPack.setInvoiceStatus(2);
			customerPack.setBackId(null);
			customerPack.setBackDate(null);
		}else if(invoiceStatus == 2) {
			customerPack.setInvoiceStatus(2);
		}
		customerPackMapper.editCustomerPackById(customerPack);
	}

	/* 
	 * 获取发票列表
	 */
	@Override
	public List<Map<String, Object>> getInvoiceManagement(int start, int pageSize, Map<String, Object> form) {
		PageHelper.offsetPage(start, pageSize);
		return this.getInvoiceManagement(form);
	}

	/* 
	 * 获取发票列表
	 */
	@Override
	public List<Map<String, Object>> getInvoiceManagement(Map<String, Object> form) {
		return customerPackInvoiceMapper.getInvoiceManagement(form);
	}

}