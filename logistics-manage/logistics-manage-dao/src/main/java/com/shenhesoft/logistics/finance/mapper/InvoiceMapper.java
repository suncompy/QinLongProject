/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */
 
package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.shenhesoft.logistics.finance.Invoice;

/**
 * 发票-Dao.
 * <p>
 * <a href="InvoiceMapper.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
public interface InvoiceMapper {

  /**
   * 新增发票.
   * 
   * @param invoice 发票实体
   * @return dao成功失败标志
   */
  int addInvoice(Invoice invoice);

  /**
   * 批量新增发票.
   * 
   * @param list 批量发票实体
   */
  void addInvoices(@Param(value = "list") List<Invoice> list);

  /**
   * 查看发票详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  Map<String, Object> getInvoiceById(String id);

  /**
   * 修改发票.
   * 
   * @param invoice 发票实体
   * @return dao成功失败标志
   */
  int editInvoiceById(Invoice invoice);

  /**
   * 批量修改发票.
   * 
   * @param invoice 发票实体
   * @param ids 主键集合
   */
  void editInvoiceByIds(@Param("map") Invoice invoice,@Param("list") List<String> ids);

  /**
   * 删除指定发票.
   * 
   * @param id 主键
   * @return dao成功失败标志
   */
  int delInvoiceById(String id);
  
  /**
   * 批量删除指定发票.
   * 
   * @param ids 主键集合
   * @return dao成功失败标志
   */
  int delInvoiceByIds(@Param("list") List<String> ids);

  /**
    * 清空计量单位表.
    * 
    */
  void delInvoices();

  /**
   * 获取所有发票.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> getInvoices(@Param(value = "map") Map<String, Object> form);
}