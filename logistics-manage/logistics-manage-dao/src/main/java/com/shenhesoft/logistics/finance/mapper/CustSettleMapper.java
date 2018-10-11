/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */
 
package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.shenhesoft.logistics.finance.CustSettle;

/**
 * 客户结算表-Dao.
 * <p>
 * <a href="CustSettleMapper.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustSettleMapper {

  /**
   * 新增客户结算表.
   * 
   * @param custSettle 客户结算表实体
   * @return dao成功失败标志
   */
  int addCustSettle(CustSettle custSettle);

  /**
   * 批量新增客户结算表.
   * 
   * @param list 批量客户结算表实体
   */
  void addCustSettles(@Param(value = "list") List<CustSettle> list);

  /**
   * 查看客户结算表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  Map<String, Object> getCustSettleById(String id);

  /**
   * 修改客户结算表.
   * 
   * @param custSettle 客户结算表实体
   * @return dao成功失败标志
   */
  int editCustSettleById(CustSettle custSettle);

  /**
   * 批量修改客户结算表.
   * 
   * @param custSettle 客户结算表实体
   * @param ids 主键集合
   */
  void editCustSettleByIds(@Param("map") CustSettle custSettle,@Param("list") List<String> ids);

  /**
   * 删除指定客户结算表.
   * 
   * @param id 主键
   * @return dao成功失败标志
   */
  int delCustSettleById(String id);
  
  /**
   * 批量删除指定客户结算表.
   * 
   * @param ids 主键集合
   * @return dao成功失败标志
   */
  int delCustSettleByIds(@Param("list") List<String> ids);

  /**
    * 清空计量单位表.
    * 
    */
  void delCustSettles();

  /**
   * 获取所有客户结算表.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> getCustSettles(@Param(value = "map") Map<String, Object> form);
}