package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.finance.CustomerCheckingConf;

/**
 * 客户对账设置表-Dao.
 * <p>
 * <a href="CustomerCheckingConfMapper.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustomerCheckingConfMapper {

  /**
   * 新增客户对账设置表.
   * 
   * @param customerCheckingConf 客户对账设置表实体
   * @return dao成功失败标志
   */
  int addCustomerCheckingConf(CustomerCheckingConf customerCheckingConf);

  /**
   * 批量新增客户对账设置表.
   * 
   * @param list 批量客户对账设置表实体
   */
  void addCustomerCheckingConfs(@Param(value = "list") List<CustomerCheckingConf> list);

  /**
   * 查看客户对账设置表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  Map<String, Object> getCustomerCheckingConfById(String id);

  /**
   * 修改客户对账设置表.
   * 
   * @param customerCheckingConf 客户对账设置表实体
   * @return dao成功失败标志
   */
  int editCustomerCheckingConfById(CustomerCheckingConf customerCheckingConf);

  /**
   * 批量修改客户对账设置表.
   * 
   * @param customerCheckingConf 客户对账设置表实体
   * @param ids 主键集合
   */
  void editCustomerCheckingConfByIds(@Param("map") CustomerCheckingConf customerCheckingConf,@Param("list") List<String> ids);

  /**
   * 删除指定客户对账设置表.
   * 
   * @param id 主键
   * @return dao成功失败标志
   */
  int delCustomerCheckingConfById(String id);
  
  /**
   * 批量删除指定客户对账设置表.
   * 
   * @param ids 主键集合
   * @return dao成功失败标志
   */
  int delCustomerCheckingConfByIds(@Param("list") List<String> ids);

  /**
    * 清空计量单位表.
    * 
    */
  void delCustomerCheckingConfs();

  /**
   * 获取所有客户对账设置表.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> getCustomerCheckingConfs(@Param(value = "map") Map<String, Object> form);
  
  /**
   * 获取客户对账列表app.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> getCustomerCheckingApp(@Param(value = "map") Map<String, Object> form);
}