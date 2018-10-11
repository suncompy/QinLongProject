package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomerExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbCustomerMapper {
    int countByExample(TbCustomerExample example);

    int deleteByExample(TbCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCustomer record);

    int insertSelective(TbCustomer record);

    List<TbCustomer> selectByExample(TbCustomerExample example);

    List<CustomerInfo> selectCustomerInfoByExample(TbCustomerExample example);
    
    CustomerInfo selectCustomerInfoByCid(Integer id);
    
    TbCustomer selectTbCustomerInfoById(Integer id);
    
    TbCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCustomer record, @Param("example") TbCustomerExample example);

    int updateByExample(@Param("record") TbCustomer record, @Param("example") TbCustomerExample example);

    int updateByPrimaryKeySelective(TbCustomer record);

    int updateByPrimaryKey(TbCustomer record);

    /**
     * 通过用户名和密码查询 客户信息
     * @author dusd
     * @date 2017年12月23日
     * @param tbCustomer 客户对象 存放用户名和密码
     * @return
     */
	TbCustomer getTbCustomerByUserNamePassword(TbCustomer tbCustomer);

	/**
	 * 查询所有的用户信息列表 返回 list map
	 * @author dusd
	 * @date 2018年1月19日
	 * @return
	 */
	List<Map<String, Object>> listAllCustomerInfo();

	
	/**
	 * @description 根据区域获取客户信息
	 * @date 2018年4月13日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<TbCustomer> getCustomerByArea(@Param("map")Map<String, Object> form);
	
}