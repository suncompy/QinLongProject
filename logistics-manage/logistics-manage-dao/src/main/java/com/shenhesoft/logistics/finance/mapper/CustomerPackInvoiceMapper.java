package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.shenhesoft.logistics.finance.CustomerPackInvoice;

/**
 * 客户打包-发票信息表-Dao.
 * <p>
 * <a href="CustomerPackInvoiceMapper.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-19
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustomerPackInvoiceMapper {

  /**
   * 新增客户打包-发票信息表.
   * 
   * @param customerPackInvoice 客户打包-发票信息表实体
   * @return dao成功失败标志
   */
  int addCustomerPackInvoice(CustomerPackInvoice customerPackInvoice);

  /**
   * 批量新增客户打包-发票信息表.
   * 
   * @param list 批量客户打包-发票信息表实体
   */
  void addCustomerPackInvoices(@Param(value = "list") List<CustomerPackInvoice> list);

  /**
   * 查看客户打包-发票信息表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  Map<String, Object> getCustomerPackInvoiceById(String id);
  
  /**
   * 查看发票信息表详情.
   * 
   * @param custPackId 主键
   * @return 页面表单
   */
  Map<String, Object> getCustomerPackInvoiceByCustPackId(String custPackId);

  /**
   * 修改客户打包-发票信息表.
   * 
   * @param customerPackInvoice 客户打包-发票信息表实体
   * @return dao成功失败标志
   */
  int editCustomerPackInvoiceById(CustomerPackInvoice customerPackInvoice);

  /**
   * 批量修改客户打包-发票信息表.
   * 
   * @param customerPackInvoice 客户打包-发票信息表实体
   * @param ids 主键集合
   */
  void editCustomerPackInvoiceByIds(@Param("map") CustomerPackInvoice customerPackInvoice,@Param("list") List<String> ids);

  /**
   * 删除指定客户打包-发票信息表.
   * 
   * @param id 主键
   * @return dao成功失败标志
   */
  int delCustomerPackInvoiceById(String id);
  
  /**
   * 批量删除指定客户打包-发票信息表.
   * 
   * @param ids 主键集合
   * @return dao成功失败标志
   */
  int delCustomerPackInvoiceByIds(@Param("list") List<String> ids);

  /**
    * 清空计量单位表.
    * 
    */
  void delCustomerPackInvoices();

  /**
   * 获取所有客户打包-发票信息表.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> getCustomerPackInvoices(@Param(value = "map") Map<String, Object> form);
  
  /*
   * 发票登入
   */
  void insertCustomerPackInvoice(CustomerPackInvoice customerPackInvoice);
  
  /*
   * 获取发票列表
   */
  List<Map<String, Object>> getInvoiceManagement(@Param(value = "map") Map<String, Object> form);
  
}