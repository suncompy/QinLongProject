/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */
 
package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.shenhesoft.logistics.finance.CustSettleDetail;

/**
 * 客户结算明细-Dao.
 * <p>
 * <a href="CustSettleDetailMapper.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustSettleDetailMapper {

  /**
   * 新增客户结算明细.
   * 
   * @param custSettleDetail 客户结算明细实体
   * @return dao成功失败标志
   */
  int addCustSettleDetail(CustSettleDetail custSettleDetail);

  /**
   * 批量新增客户结算明细.
   * 
   * @param list 批量客户结算明细实体
   */
  void addCustSettleDetails(@Param(value = "list") List<CustSettleDetail> list);

  /**
   * 查看客户结算明细详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  Map<String, Object> getCustSettleDetailById(String id);

  /**
   * 修改客户结算明细.
   * 
   * @param custSettleDetail 客户结算明细实体
   * @return dao成功失败标志
   */
  int editCustSettleDetailById(CustSettleDetail custSettleDetail);

  /**
   * 批量修改客户结算明细.
   * 
   * @param custSettleDetail 客户结算明细实体
   * @param ids 主键集合
   */
  void editCustSettleDetailByIds(@Param("map") CustSettleDetail custSettleDetail,@Param("list") List<String> ids);

  /**
   * 删除指定客户结算明细.
   * 
   * @param id 主键
   * @return dao成功失败标志
   */
  int delCustSettleDetailById(String id);
  
  /**
   * 批量删除指定客户结算明细.
   * 
   * @param ids 主键集合
   * @return dao成功失败标志
   */
  int delCustSettleDetailByIds(@Param("list") List<String> ids);

  /**
    * 清空计量单位表.
    * 
    */
  void delCustSettleDetails();

  /**
   * 获取所有客户结算明细.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> getCustSettleDetails(@Param(value = "map") Map<String, Object> form);
}