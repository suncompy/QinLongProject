package com.shenhesoft.logistics.manage.customer.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.business.helpPojo.AreaHelpPojo;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.customer.CustomerInfoService;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.EmployInfo;
import com.shenhesoft.logistics.manage.helpPojo.TbStationBusinessHelp;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbCustomerBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbCustomerMapper;
import com.shenhesoft.logistics.manage.mapper.TbStationBusinessMapper;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomerBranchGroup;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomerBranchGroupExample;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomerExample;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomerExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.stationBusiness.TbStationBusiness;
import com.shenhesoft.logistics.manage.pojo.stationBusiness.TbStationBusinessExample;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUserExample;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月12日
 */
@Service
public class CustomerInfoServiceImpl implements CustomerInfoService{

	@Autowired
	private TbCustomerMapper customerMapper;
	
	@Autowired
	private TbBranchGroupMapper branchGroupMapper;
	
	@Autowired
	private TbCustomerBranchGroupMapper customerBranchGroupMapper;
	
	@Autowired
	private TbStationBusinessMapper stationBusinessMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	@Override
	public DataGridResult listCustomerByPage(Integer page, Integer limit,TbCustomer customer) {
        List<CustomerInfo> list = customerCriteria(customer,page,limit);//条件查询
        // 取分页信息
        PageInfo<CustomerInfo> pageInfo = new PageInfo<CustomerInfo>(list);
        for (CustomerInfo customerInfo : list) {
        	getArea(customerInfo);
		}
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list, limit);
	}
	
	/**
	 * @description 获取客户关联的网点分支
	 * @date 2018年1月13日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private void getBranchGroup(List<CustomerInfo> list) {
		for (CustomerInfo customerInfo : list) {
        	List<TbBranchGroup> branchGroups = branchGroupMapper.selectBranchGroupByCusId(customerInfo.getId());//查询
        	String str = "";
        	for (int i = 0; i < branchGroups.size(); i++) {
        		if(i==branchGroups.size()-1){
        			str=str+branchGroups.get(i).getName();
        		}else{
        			str=str+branchGroups.get(i).getName()+",";
        		}
			}
        	customerInfo.setBrachIds(str);
        	customerInfo.setBranchGroups(branchGroups);
		}
	}

	@Override
	public LogisticsResult insertCustomer(CustomerInfo customer, List<Integer> branchIds,
			List<TbStationBusinessHelp> businessHelps) {
		//校验手机号
		LogisticsResult result = checkCustomerPhone(customer.getStationPhone(), 1);
		if(result.getStatus()!=200){
			return result;
		}
		
		//保存客户信息
		TbCustomer tbCustomer = customer;
		String cusAreaCode = customer.getProvince()+","+customer.getCity()+","+customer.getDistrict();
		tbCustomer.setAddressCode(cusAreaCode);
		tbCustomer.setAccount(tbCustomer.getStationPhone());
		tbCustomer.setPasswd(DigestUtils.md5DigestAsHex(tbCustomer.getStationPhone().getBytes()));
		customerMapper.insertSelective(tbCustomer);
		Integer cusId = tbCustomer.getId();
		
//		System.out.println(cusId);
		//保存客户与网点分支的关系
		for (Integer branchId: branchIds) {
			TbCustomerBranchGroup branchGroup = new TbCustomerBranchGroup();
			branchGroup.setBranchGroupId(branchId);
			branchGroup.setCustomerId(cusId);
			customerBranchGroupMapper.insert(branchGroup);		
		}
		//保存客户业务联系人
		for (TbStationBusinessHelp tbStationBusinessHelp : businessHelps) {
				TbStationBusiness business = tbStationBusinessHelp;
				business.setRelateId(cusId);//关联客户id
				business.setType(Constants.CUSTOMER_RELATE_PERSON);//客户联系人
				stationBusinessMapper.insert(business);
		}
	      BranchGroupLink branchGroupLink = new BranchGroupLink();
	      branchGroupLink.setId(AppUtils.randomUUID());
	      branchGroupLink.setRowId(cusId.toString());
	      branchGroupLink.setTabName("tb_customer");
	      branchGroupLink.setTabComment("客户表");
	      branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
	      branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		
		return LogisticsResult.ok();
	}

	@Override
	public LogisticsResult updateCustomer(CustomerInfo customer, List<Integer> branchIds,
			List<TbStationBusinessHelp> businessHelps) {
		
		//判断更新的手机号是否原来的一样
		String stationPhone = customer.getStationPhone();
		
		TbCustomer tbCustomer = customerMapper.selectByPrimaryKey(customer.getId());
		
		if(tbCustomer==null){
			return LogisticsResult.build(1, "您要修改的客户不存在!");
		}
		
		//不一样,判断数据库是否存在相同的手机号
		if(!tbCustomer.getStationPhone().equals(stationPhone)){
			LogisticsResult result = checkCustomerPhone(stationPhone, 1);
			if(result.getStatus()!=200){
				return result;
			}
		}
		
		//更新客户信息
		//更新省市区
		String cusAreaCode = customer.getProvince()+","+customer.getCity()+","+customer.getDistrict();
		customer.setAddressCode(cusAreaCode);
		customerMapper.updateByPrimaryKeySelective(customer);
		//更新客户与网点分支的关系
		//通过所有与cusId关联的的分支 //去除相同的分支
		/*HashSet h = new HashSet(branchIds);   
		branchIds.clear();   
		branchIds.addAll(h); 
		TbCustomerBranchGroupExample branchGroupExample = new TbCustomerBranchGroupExample();
		com.shenhesoft.logistics.manage.pojo.customer.TbCustomerBranchGroupExample.Criteria criteria = branchGroupExample.createCriteria();
		criteria.andCustomerIdEqualTo(customer.getId());
		//清空
		customerBranchGroupMapper.deleteByExample(branchGroupExample);
		
		for (Integer branchId: branchIds) {
			TbCustomerBranchGroup branchGroup = new TbCustomerBranchGroup();
			branchGroup.setBranchGroupId(branchId);
			branchGroup.setCustomerId(customer.getId());
			customerBranchGroupMapper.insert(branchGroup);		
		}*/
		
		//更新客户业务联系人
		for (TbStationBusinessHelp tbStationBusinessHelp : businessHelps) {
			TbStationBusiness business = tbStationBusinessHelp;
			business.setRelateId(customer.getId());//关联客户id
			business.setType(Constants.CUSTOMER_RELATE_PERSON);//客户联系人
			if(tbStationBusinessHelp.getId()==null){
				stationBusinessMapper.insert(business);
			}else{
				stationBusinessMapper.updateByPrimaryKeySelective(business);
			}
		}
		return LogisticsResult.ok();
	}

	@Override
	public LogisticsResult getCustomerById(Integer id) {
		CustomerInfo customerInfo = customerMapper.selectCustomerInfoByCid(id);
		getArea(customerInfo);//获取省市区
    	List<TbBranchGroup> branchGroups = branchGroupMapper.selectBranchGroupByCusId(customerInfo.getId());//查询
    	customerInfo.setBranchGroups(branchGroups);
    	TbStationBusinessExample example = new TbStationBusinessExample();
    	com.shenhesoft.logistics.manage.pojo.stationBusiness.TbStationBusinessExample.Criteria criteria = example.createCriteria();
    	criteria.andRelateIdEqualTo(id);
    	List<TbStationBusinessHelp> list = stationBusinessMapper.selectTbStationBusinessByExample(example);
    	customerInfo.setBusinessContacts(list);
		return LogisticsResult.ok(customerInfo);
	}

	private void getArea(CustomerInfo customerInfo) {
		String addressCode = customerInfo.getAddressCode();
		String[] split = addressCode.split(",");
		if(split!=null && split.length!=0){
			customerInfo.setProvince(split[0]);
			customerInfo.setCity(split[1]);
			if(split.length==3){
				if(!split[2].equals("null")){
					customerInfo.setDistrict(split[2]);
				}
			}
		}
	}
	
	@Override
	public LogisticsResult delCustomerById(Integer id) {
		/*TbCustomerBranchGroupExample branchGroupExample = new TbCustomerBranchGroupExample();
		com.shenhesoft.logistics.manage.pojo.customer.TbCustomerBranchGroupExample.Criteria branchGroupCriteria = branchGroupExample.createCriteria();
		branchGroupCriteria.andCustomerIdEqualTo(id);
		//清空关联的分支机构
		customerBranchGroupMapper.deleteByExample(branchGroupExample);
		//删除关联的业务联系人
    	TbStationBusinessExample example = new TbStationBusinessExample();
    	com.shenhesoft.logistics.manage.pojo.stationBusiness.TbStationBusinessExample.Criteria criteria = example.createCriteria();
    	criteria.andRelateIdEqualTo(id);
    	stationBusinessMapper.deleteByExample(example);
    	//删除客户信息
    	customerMapper.deleteByPrimaryKey(id);*/
    	//逻辑删除
		TbCustomer tbCustomer = customerMapper.selectByPrimaryKey(id);
		tbCustomer.setStatus(Constants.CUSTOMER_STATUS_NO);
		customerMapper.updateByPrimaryKeySelective(tbCustomer);
		return LogisticsResult.ok();
	}

	@Override
	public List<CustomerInfo> selectCustomers(byte status) {
		TbCustomerExample example= new TbCustomerExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(status);
        criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
        criteria.andTabNameEqualTo("tb_customer");
		return customerMapper.selectCustomerInfoByExample(example);
	}

	@Override
	public LogisticsResult getCustomerShortInfoById(Integer id) {
		CustomerInfo customerInfo = customerMapper.selectCustomerInfoByCid(id);
		String addressCode = customerInfo.getAddressCode();
		String[] split = addressCode.split(",");
		if(split!=null && split.length!=0){
			addressCode="";
			customerInfo.setProvince(split[0]);
			customerInfo.setCity(split[1]);
			addressCode=split[0]+split[1];
			if(split.length==3 && !split[2].equals("null")){
				customerInfo.setDistrict(split[2]);
				addressCode=addressCode+split[2];
			}
			customerInfo.setAddressCode(addressCode);
		}
		return LogisticsResult.ok(customerInfo);
	}

	@Override
	public TbCustomer getTbCustomerByUserNamePassword(String userName, String password) {
		TbCustomer tbCustomer = new TbCustomer();
		tbCustomer.setAccount(userName.trim());
		tbCustomer.setPasswd(DigestUtils.md5DigestAsHex(password.trim().getBytes()));
		return customerMapper.getTbCustomerByUserNamePassword(tbCustomer);
	}


	private List<CustomerInfo> customerCriteria(TbCustomer customer,Integer page,Integer limit) {
		TbCustomerExample customerExample = new TbCustomerExample();
		//按照创建时间排序
		customerExample.setOrderByClause("createDate desc");
        Criteria criteria = customerExample.createCriteria();
        //状态为可用
        criteria.andStatusEqualTo(customer.getStatus());
        criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
        criteria.andTabNameEqualTo("tb_customer");
        
		if(StringUtils.isNotBlank(customer.getCompanyName())){
			criteria.andCompanyNameLike(customer.getCompanyName());
		}
		if(StringUtils.isNotBlank(customer.getShortName())){
			criteria.andShortNameLike(customer.getShortName());
		}
		Integer branchId=null;
		if(customer.getBranchId()!=null){
			branchId=customer.getBranchId();
		}
		if(StringUtils.isNotBlank(customer.getCompanyContacts())){
			criteria.andCompanyContactsLike(customer.getCompanyContacts());
		}
		if(StringUtils.isNotBlank(customer.getStationPhone())){
			criteria.andStationPhoneLike(customer.getStationPhone());
		}
		//分页处理，显示第一页的20条数据
        PageHelper.startPage(page, limit);
		List<CustomerInfo> list = customerMapper.selectCustomerInfoByExample(customerExample);
		getBranchGroup(list);
		List<CustomerInfo> resultList = new ArrayList<>();
		if(branchId!=null){
			for (CustomerInfo customerInfo : list) {
				List<TbBranchGroup> branchGroups = customerInfo.getBranchGroups();
				for (TbBranchGroup tbBranchGroup : branchGroups) {
					if(tbBranchGroup.getId()==branchId){
						resultList.add(customerInfo);
					}
				}
			}
			list=resultList;
		}
		return list;
	}


	@Override
	public LogisticsResult updatePassWd(String cusId, String oldPassWord, String newPassword, String twoPassword) {
		TbCustomer customer = customerMapper.selectByPrimaryKey(Integer.valueOf(cusId));
		if(customer==null){
			return LogisticsResult.build(404,"修改密码失败，用户信息不存在");
		}
		if(!customer.getPasswd().equals(DigestUtils.md5DigestAsHex(oldPassWord.getBytes()))){
		   return LogisticsResult.build(404,"修改密码失败，旧密码不正确!");
		}
		customer.setPasswd(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
		customerMapper.updateByPrimaryKeySelective(customer);
		return LogisticsResult.ok();
	}

	@Override
	public List<TbCustomer> getCustomerByBranchGroup(Integer branchId) {
		List<TbCustomer> customers = null;
		TbBranchGroup branchGroup = branchGroupMapper.selectByPrimaryKey(branchId);
		String relationBeginLocation = branchGroup.getRelationBeginLocation();
		if(StringUtils.isNotBlank(relationBeginLocation)){
			List<AreaHelpPojo> list = JsonUtils.jsonToList(relationBeginLocation, AreaHelpPojo.class);
			List<String> areas = new ArrayList<>();
			//获取区域
			for (AreaHelpPojo area : list) {
				String address = "";
				if(StringUtils.isNotBlank(area.getProvince())){
					address += area.getProvince()+",";
				}
				if(StringUtils.isNotBlank(area.getCity())){
					address += area.getCity()+",";
				}
				//if(StringUtils.isNotBlank(area.getDistrict())){
					address += area.getDistrict();
				//}
				areas.add(address);
			}
			Map<String, Object> map = new HashMap<>();
			map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
			map.put("areas", areas);
			customers = customerMapper.getCustomerByArea(map);
		}
		return customers;
	}


	//校验账号是否存在
	public LogisticsResult checkCustomerPhone(String param,int type) {
		TbCustomerExample customerExample  = new TbCustomerExample();
	     Criteria criteria = customerExample.createCriteria();
		//判断校验的数据类型
		//1、phone
		if (type == 1) {
			criteria.andStationPhoneEqualTo(param);
			criteria.andStatusEqualTo(Constants.CUSTOMER_STATUS_YES);
		}
		//执行查询
		List<TbCustomer> list = customerMapper.selectByExample(customerExample);
		if (list == null || list.size() == 0) {
			return LogisticsResult.ok(true);
		}
		return LogisticsResult.build(404,"客户手机号已存在!");
	}
	
}
