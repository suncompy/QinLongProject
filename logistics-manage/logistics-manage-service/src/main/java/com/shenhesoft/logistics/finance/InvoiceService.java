/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */

package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;
import com.shenhesoft.logistics.finance.Invoice;

/**
 * 发票-业务层接口.
 * <p>
 * <a href="InvoiceService.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
public interface InvoiceService {

  /**
   * 添加发票.
   * 
   * @param invoice 发票实体
   * @return 页面表单
   */
  public Invoice addInvoice(Invoice invoice);
  /**
   * 
   * @description 
   * @date 2018年3月29日
   * @param packType 流水类型：0客户对账总1客户对账分2费用对账
   * @return
   */
  public void addInvoices(List<Map<String,Object>> list,String packType);
  /**
   * 查看发票详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  public Map<String, Object> getInvoiceById(String id);

  /**
   * 修改发票.
   * 
   * @param invoice 发票实体
   */
  public void editInvoiceById(Invoice invoice);

  /**
   * 删除指定发票.
   * 
   * @param id 主键
   */
  public void delInvoiceById(String id);

  /**
   * 批量删除指定发票.
   * 
   * @param ids 主键集合.
   */
  public void delInvoiceByIds(List<String> ids);

  /**
   * 清空计量单位表.
   */
  public void delInvoices();

  /**
   * 获取所有发票.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getInvoices(int start, int pageSize, Map<String, Object> form);

  /**
   * 获取所有发票.
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getInvoices(Map<String, Object> form);

}