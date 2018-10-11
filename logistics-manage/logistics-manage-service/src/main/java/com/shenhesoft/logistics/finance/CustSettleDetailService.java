/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */

package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;
import com.shenhesoft.logistics.finance.CustSettleDetail;

/**
 * 客户结算明细-业务层接口.
 * <p>
 * <a href="CustSettleDetailService.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustSettleDetailService {

  /**
   * 添加客户结算明细.
   * 
   * @param custSettleDetail 客户结算明细实体
   * @return 页面表单
   */
  public CustSettleDetail addCustSettleDetail(CustSettleDetail custSettleDetail);

  /**
   * 查看客户结算明细详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  public Map<String, Object> getCustSettleDetailById(String id);

  /**
   * 修改客户结算明细.
   * 
   * @param custSettleDetail 客户结算明细实体
   */
  public void editCustSettleDetailById(CustSettleDetail custSettleDetail);

  /**
   * 删除指定客户结算明细.
   * 
   * @param id 主键
   */
  public void delCustSettleDetailById(String id);

  /**
   * 批量删除指定客户结算明细.
   * 
   * @param ids 主键集合.
   */
  public void delCustSettleDetailByIds(List<String> ids);

  /**
   * 清空计量单位表.
   */
  public void delCustSettleDetails();

  /**
   * 获取所有客户结算明细.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getCustSettleDetails(int start, int pageSize, Map<String, Object> form);

  /**
   * 获取所有客户结算明细.
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getCustSettleDetails(Map<String, Object> form);

  /**
   * @description 
   * @author liangLin
   * @date 2018年3月30日
   * @param 
   * @return
  */
  public void editCustSettleDetailByIds(CustSettleDetail custSettleDetail, String id);

}