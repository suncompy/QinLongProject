package com.shenhesoft.logistics.finance.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.shenhesoft.logistics.business.bussinessCount.BussinessCountService;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderMapper;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.common.util.StringUtils;
import com.shenhesoft.logistics.finance.CustomerCheckingConf;
import com.shenhesoft.logistics.finance.CustomerCheckingConfService;
import com.shenhesoft.logistics.finance.InvoiceService;
import com.shenhesoft.logistics.finance.mapper.CustomerCheckingConfMapper;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.CodeService;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 客户对账设置表-业务实现.
 * <p>
 * <a href="CustomerCheckingConfServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CustomerCheckingConfServiceImpl implements CustomerCheckingConfService {

	@Autowired
	private CustomerCheckingConfMapper customerCheckingConfMapper;
	@Autowired
    private BussinessCountService bussinessCountService;
	@Autowired
    private CodeService codeService;
	  @Autowired
	  private InvoiceService invoiceService;
	/**
	 * 短驳运单 mapper
	 */
	@Autowired
	private TbOrderMapper tbOrderMapper;
	/**
	 * 火运运单 mapper
	 */
	@Autowired
	private TbTrainOrderMapper trainOrderMapper;

	/**
	 * 新增客户对账设置表.
	 * 
	 * @param customerCheckingConf
	 *            客户对账设置表实体
	 * @return 页面表单
	 */
	@OrgLinkData(idName="custCheckConId",tabComment="客户对账")
	public CustomerCheckingConf addCustomerCheckingConf(CustomerCheckingConf customerCheckingConf) {
	  String sysOrgCode=AppSession.getCurrentSysOrgCode();
	  String id = codeService.createPackOrderCode(sysOrgCode, customerCheckingConf.getProjectId());
		// 生成id
		customerCheckingConf.setCustCheckConId(id);

		customerCheckingConf.setCheckingStatus(0);// 对账状态(0-待确认 1-待审核 2-财务审核通过)
		customerCheckingConf.setDeleteFlag(0);//// 是否删除(0-未删除 1-删除)
		customerCheckingConf.setCreateDate(DateUtils.date2Str(DateUtils.datetimeFormat));
		TbSystemUser user = AppSession.getCurrentUser();
		customerCheckingConf.setCreateUserId(user.getId());// 创建人
		// 保存客户对账设置表
		customerCheckingConfMapper.addCustomerCheckingConf(customerCheckingConf);
		return customerCheckingConf;
	}

	/**
	 * 查看客户对账设置表详情.
	 * 
	 * @param id
	 *            主键
	 * @return 页面表单
	 */
	public Map<String, Object> getCustomerCheckingConfById(String id) {
		return customerCheckingConfMapper.getCustomerCheckingConfById(id);
	}

	/**
	 * 修改客户对账设置表.
	 * 
	 * @param customerCheckingConf
	 *            客户对账设置表实体
	 */
	public void editCustomerCheckingConfById(CustomerCheckingConf customerCheckingConf) {
		customerCheckingConfMapper.editCustomerCheckingConfById(customerCheckingConf);
	}

	/**
	 * 删除指定客户对账设置表.
	 * 
	 * @param id
	 *            主键
	 */
	public void delCustomerCheckingConfById(String id) {
		customerCheckingConfMapper.delCustomerCheckingConfById(id);
	}

	/**
	 * 批量删除指定客户对账设置表.
	 * 
	 * @param ids
	 *            主键集合
	 */
	public void delCustomerCheckingConfByIds(List<String> ids) {
		customerCheckingConfMapper.delCustomerCheckingConfByIds(ids);
	}

	/**
	 * 清空计量单位表.
	 */
	public void delCustomerCheckingConfs() {
		customerCheckingConfMapper.delCustomerCheckingConfs();
	}

	/**
	 * 获取所有客户对账设置表.
	 * 
	 * @return 客户对账设置表分页
	 */
	public List<Map<String, Object>> getCustomerCheckingConfs(int start, int pageSize, Map<String, Object> form) {
		PageHelper.offsetPage(start, pageSize);
		return this.getCustomerCheckingConfs(form);
	}
    /**
     * 获取客户对账列表app.
     * @param form 页面表单
     * @return 结果集合
     */
  public List<Map<String, Object>> getCustomerCheckingApp(int start, int pageSize, Map<String, Object> form){
      PageHelper.offsetPage(start, pageSize);
      return customerCheckingConfMapper.getCustomerCheckingApp(form);
  }
	/**
	 * 获取所有客户对账设置表.
	 * 
	 * @return 客户对账设置表
	 */
	public List<Map<String, Object>> getCustomerCheckingConfs(Map<String, Object> form) {
		return customerCheckingConfMapper.getCustomerCheckingConfs(form);
	}

	@Override
	public void editSureCheckingCustomerCheckingConfById(String custCheckConIds,int checkingStatus) {
		if(StringUtil.isEmpty(custCheckConIds))
			return;
		List<String> custCheckConIdList = Arrays.asList(custCheckConIds.trim().split(","));
		CustomerCheckingConf customerCheckingConf = new CustomerCheckingConf();
		customerCheckingConf.setCheckingStatus(checkingStatus);//1-待审核
		customerCheckingConfMapper.editCustomerCheckingConfByIds(customerCheckingConf, custCheckConIdList);
	}

	@Override
	public void editAgainstVerifyCustomerCheckingConfById(String custCheckConIds) {
		if(StringUtil.isEmpty(custCheckConIds))
			return;
		List<String> custCheckConIdList = Arrays.asList(custCheckConIds.trim().split(","));
		CustomerCheckingConf customerCheckingConf = new CustomerCheckingConf();
		customerCheckingConf.setCheckingStatus(0);//0-待确认 1-待审核 2-财务审核通过3确认不通过4审核不通过)
		customerCheckingConfMapper.editCustomerCheckingConfByIds(customerCheckingConf, custCheckConIdList);
	}

	@Override
	public void editFinanceAuditCustomerCheckingConfById(String custCheckConIds,int checkingStatus,TbSystemUser user) {
		if(StringUtil.isEmpty(custCheckConIds))
			return;
		List<String> custCheckConIdList = Arrays.asList(custCheckConIds.trim().split(","));
		CustomerCheckingConf customerCheckingConf = new CustomerCheckingConf();
		customerCheckingConf.setCheckingStatus(checkingStatus);//0-待确认 1-待审核 2-财务审核通过3确认不通过4审核不通过
		if(2==checkingStatus){
		  Map<String,Object> requestMap = ImmutableMap.of("custCheckConIds", custCheckConIdList,"sysOrgCode", null==user?AppSession.getCurrentSysOrgCode():user.getSysOrgCode());
		  List<Map<String, Object>> list = customerCheckingConfMapper.getCustomerCheckingConfs(requestMap);
		  List<Map<String, Object>> listNew = Lists.newArrayList();
		  for(Map<String, Object> oldMap:list){
		    //transportType.
		    if(!CollectionUtils.isEmpty(oldMap) && StringUtils.isNotBlank(oldMap.get("transportType"))){
		      String transportType = oldMap.get("transportType").toString();
		      if(Integer.parseInt(transportType) == 6){
		        listNew.add(oldMap);
		      }
		    }
		  }
		  if(!CollectionUtils.isEmpty(listNew)){
		    invoiceService.addInvoices(listNew,"0");
		  }
		}
		customerCheckingConfMapper.editCustomerCheckingConfByIds(customerCheckingConf, custCheckConIdList);
	}

	@Override
	public void editAgainstAuditCustomerCheckingConfById(String custCheckConIds) {
		if(StringUtil.isEmpty(custCheckConIds))
			return;
		List<String> custCheckConIdList = Arrays.asList(custCheckConIds.trim().split(","));
		CustomerCheckingConf customerCheckingConf = new CustomerCheckingConf();
		customerCheckingConf.setCheckingStatus(1);//0-待确认 1-待审核 2-财务审核通过
		customerCheckingConfMapper.editCustomerCheckingConfByIds(customerCheckingConf, custCheckConIdList);
	}

	@Override
	public List<Map<String, Object>> getOrdersByCustCheckConId(int start, int pageSize, Map<String, Object> innerMap) {
		String orderType = innerMap.get("orderType") == null ? "" : innerMap.get("orderType").toString().trim();
		if(StringUtil.isEmpty(orderType)) 
			return null;
		String custCheckConId = innerMap.get("custCheckConId") == null ? "" : innerMap.get("custCheckConId").toString().trim();
		//得到客户对账信息
		Map<String, Object> customerCheckingConfMap = getCustomerCheckingConfById(custCheckConId);
		customerCheckingConfMap.put("orderType", orderType);
	    customerCheckingConfMap.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> dataList = null;
		PageHelper.offsetPage(start, pageSize);
		if(orderType.equals("0") || orderType.equals("1") || orderType.equals("2")) {//0接取 1：送达 2汽运 3火运
			dataList = tbOrderMapper.listTbOrderByCustomerCheckingConfMap(customerCheckingConfMap);
		} else if(orderType.equals("3")) {
			dataList = trainOrderMapper.listTrainOrderByCustomerCheckingConfMap(customerCheckingConfMap);
		}
		return dataList;
	}

}