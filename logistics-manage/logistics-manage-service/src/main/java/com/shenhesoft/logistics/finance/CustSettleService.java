/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */

package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;
import com.shenhesoft.logistics.finance.CustSettle;

/**
 * 客户结算表-业务层接口.
 * <p>
 * <a href="CustSettleService.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustSettleService {

  /**
   * 添加客户结算表.
   * 
   * @param custSettle 客户结算表实体
   * @return 页面表单
   */
  public CustSettle addCustSettle(CustSettle custSettle);

  /**
   * 查看客户结算表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  public Map<String, Object> getCustSettleById(String id);

  /**
   * 修改客户结算表.
   * 
   * @param custSettle 客户结算表实体
   */
  public void editCustSettleById(CustSettle custSettle);

  /**
   * 删除指定客户结算表.
   * 
   * @param id 主键
   */
  public void delCustSettleById(String id);

  /**
   * 批量删除指定客户结算表.
   * 
   * @param ids 主键集合.
   */
  public void delCustSettleByIds(List<String> ids);

  /**
   * 清空计量单位表.
   */
  public void delCustSettles();

  /**
   * 获取所有客户结算表.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getCustSettles(int start, int pageSize, Map<String, Object> form);

  /**
   * 获取所有客户结算表.
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getCustSettles(Map<String, Object> form);

}