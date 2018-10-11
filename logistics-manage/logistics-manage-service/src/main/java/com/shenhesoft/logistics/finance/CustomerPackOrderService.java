package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

/**
 * 客户打包-运单中间表-业务层接口.
 * <p>
 * <a href="CustomerPackOrderService.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustomerPackOrderService {

  /**
   * 添加客户打包-运单中间表.
   * 
   * @param customerPackOrder 客户打包-运单中间表实体
   * @return 页面表单
   */
  public CustomerPackOrder addCustomerPackOrder(CustomerPackOrder customerPackOrder);

  /**
   * 查看客户打包-运单中间表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  public Map<String, Object> getCustomerPackOrderById(String id);

  /**
   * 修改客户打包-运单中间表.
   * 
   * @param customerPackOrder 客户打包-运单中间表实体
   */
  public void editCustomerPackOrderById(CustomerPackOrder customerPackOrder);

  /**
   * 删除指定客户打包-运单中间表.
   * 
   * @param id 主键
   */
  public void delCustomerPackOrderById(String id);

  /**
   * 批量删除指定客户打包-运单中间表.
   * 
   * @param ids 主键集合.
   */
  public void delCustomerPackOrderByIds(List<String> ids);

  /**
   * 清空计量单位表.
   */
  public void delCustomerPackOrders();

  /**
   * 获取所有客户打包-运单中间表.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getCustomerPackOrders(int start, int pageSize, Map<String, Object> form);

  /**
   * 获取所有客户打包-运单中间表.
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getCustomerPackOrders(Map<String, Object> form);

}