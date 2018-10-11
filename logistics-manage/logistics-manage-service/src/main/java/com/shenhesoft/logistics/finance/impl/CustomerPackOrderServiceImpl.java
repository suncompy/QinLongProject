package com.shenhesoft.logistics.finance.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.finance.CustomerPackOrder;
import com.shenhesoft.logistics.finance.CustomerPackOrderService;
import com.shenhesoft.logistics.finance.mapper.CustomerPackOrderMapper;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 客户打包-运单中间表-业务实现.
 * <p>
 * <a href="CustomerPackOrderServiceImpl.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CustomerPackOrderServiceImpl implements CustomerPackOrderService {
  
  @Autowired
  private CustomerPackOrderMapper customerPackOrderMapper;
  
  /**
   * 新增客户打包-运单中间表.
   * 
   * @param customerPackOrder 客户打包-运单中间表实体
   * @return 页面表单
   */
  @OrgLinkData(idName="custPackOrderId",tabComment="客户对账打包运单中间表")
  public CustomerPackOrder addCustomerPackOrder(CustomerPackOrder customerPackOrder) {
    // 生成id
    customerPackOrder.setCustPackId(AppUtils.randomUUID());
    // 保存客户打包-运单中间表
    customerPackOrderMapper.addCustomerPackOrder(customerPackOrder);

    return customerPackOrder;
  }

  /**
   * 查看客户打包-运单中间表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  public Map<String, Object> getCustomerPackOrderById(String id) {
    return customerPackOrderMapper.getCustomerPackOrderById(id);
  }

  /**
   * 修改客户打包-运单中间表.
   * 
   * @param customerPackOrder 客户打包-运单中间表实体
   */
  public void editCustomerPackOrderById(CustomerPackOrder customerPackOrder) {
    customerPackOrderMapper.editCustomerPackOrderById(customerPackOrder);
  }

  /**
   * 删除指定客户打包-运单中间表.
   * 
   * @param id 主键
   */
  public void delCustomerPackOrderById(String id) {
    customerPackOrderMapper.delCustomerPackOrderById(id);
  }

  /**
   * 批量删除指定客户打包-运单中间表.
   * 
   * @param ids 主键集合
   */
  public void delCustomerPackOrderByIds(List<String> ids) {
    customerPackOrderMapper.delCustomerPackOrderByIds(ids);
  }

  /**
   * 清空计量单位表.
   */
  public void delCustomerPackOrders() {
    customerPackOrderMapper.delCustomerPackOrders();
  }

  /**
  * 获取所有客户打包-运单中间表.
  * 
  * @return 客户打包-运单中间表分页
  */
  public List<Map<String, Object>> getCustomerPackOrders(int start, int pageSize, Map<String, Object> form) {
    PageHelper.offsetPage(start, pageSize);
    return this.getCustomerPackOrders(form);
  }

  /**
  * 获取所有客户打包-运单中间表.
  * 
  * @return 客户打包-运单中间表
  */
  public List<Map<String, Object>> getCustomerPackOrders(Map<String, Object> form) {
    return customerPackOrderMapper.getCustomerPackOrders(form);
  }

}