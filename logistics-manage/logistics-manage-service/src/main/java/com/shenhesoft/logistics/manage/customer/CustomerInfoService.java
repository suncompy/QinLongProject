package com.shenhesoft.logistics.manage.customer;

import java.util.List;

import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.helpPojo.TbStationBusinessHelp;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;

/**
 * @description:客户信息  service 接口
 * 
 * @author shilvfei
 * 
 * @date 2017年12月12日
 */
public interface CustomerInfoService {

	/**
	 * @description 获取客户信息
	 * @date 2017年12月12日
	 * @author shilvfei
	 * @param page
	 * @param limt
	 * @param status
	 * @return
	 */
	DataGridResult listCustomerByPage(Integer page, Integer limt,TbCustomer customer);
	
	/**
	 * @description 保存客户信息
	 * @date 2017年12月13日
	 * @author shilvfei
	 * @param customer
	 * @param branchIds
	 * @param businessHelps
	 */
	LogisticsResult insertCustomer(CustomerInfo customer, List<Integer> branchIds, List<TbStationBusinessHelp> businessHelps);

	/**
	 * @description 更新客户信息
	 * @date 2017年12月14日
	 * @author shilvfei
	 * @param customer
	 * @param branchIds
	 * @param businessHelps
	 */
	LogisticsResult updateCustomer(CustomerInfo customer, List<Integer> branchIds, List<TbStationBusinessHelp> businessHelps);

	/**
	 * @description 
	 * @date 2017年12月14日
	 * @author shilvfei
	 * @param integer
	 * @return
	 */
	LogisticsResult getCustomerById(Integer integer);

	/**
	 * @description 删除客户信息
	 * @date 2017年12月14日
	 * @author shilvfei
	 * @param integer
	 * @return
	 */
	LogisticsResult delCustomerById(Integer integer);

	/**
	 * @description 获取所有的客户信息
	 * @date 2017年12月19日
	 * @author shilvfei
	 * @param status
	 * @return
	 */
	List<CustomerInfo> selectCustomers(byte status);

	/**
	 * @description 获取所有的客户 核心信息
	 * @date 2017年12月19日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	LogisticsResult getCustomerShortInfoById(Integer id);

	/**
	 * 通过用户名和密码查询 客户信息
	 * @author dusd
	 * @date 2017年12月23日
	 * @param userName
	 * @param password
	 * @return
	 */
	TbCustomer getTbCustomerByUserNamePassword(String userName,
			String password);

	
	/**
	 * @description 修改密码
	 * @date 2018年3月21日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	LogisticsResult updatePassWd(String cusId, String oldPassWord, String newPassword, String twoPassword);

	List<TbCustomer> getCustomerByBranchGroup(Integer branchId);
}
