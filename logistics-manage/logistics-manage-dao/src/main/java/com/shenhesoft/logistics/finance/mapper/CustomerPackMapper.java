package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.finance.CustomerPack;

/**
 * 客户对账打包信息表-Dao.
 * <p>
 * <a href="CustomerPackMapper.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustomerPackMapper {

  /**
   * 新增客户对账打包信息表.
   * 
   * @param customerPack 客户对账打包信息表实体
   * @return dao成功失败标志
   */
  int addCustomerPack(CustomerPack customerPack);

  /**
   * 批量新增客户对账打包信息表.
   * 
   * @param list 批量客户对账打包信息表实体
   */
  void addCustomerPacks(@Param(value = "list") List<CustomerPack> list);

  /**
   * 查看客户对账打包信息表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  Map<String, Object> getCustomerPackById(String id);
  /**
   * 查看客户对账打包信息表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  List<Map<String, Object>> getCustomerPackByIds(@Param("list") List<String> ids);
  /**
   * 修改客户对账打包信息表.
   * 
   * @param customerPack 客户对账打包信息表实体
   * @return dao成功失败标志
   */
  int editCustomerPackById(CustomerPack customerPack);

  /**
   * 批量修改客户对账打包信息表.
   * 
   * @param customerPack 客户对账打包信息表实体
   * @param ids 主键集合
   */
  void editCustomerPackByIds(@Param("map") CustomerPack customerPack,@Param("list") List<String> ids);

  /**
   * 删除指定客户对账打包信息表.
   * 
   * @param id 主键
   * @return dao成功失败标志
   */
  int delCustomerPackById(String id);
  
  /**
   * 批量删除指定客户对账打包信息表.
   * 
   * @param ids 主键集合
   * @return dao成功失败标志
   */
  int delCustomerPackByIds(@Param("list") List<String> ids);

  /**
    * 清空计量单位表.
    * 
    */
  void delCustomerPacks();

  /**
   * 获取所有客户对账打包信息表.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> getCustomerPacks(@Param(value = "map") Map<String, Object> form);
  
  /**
   * 查询审核金额
   */
  Map<String, Object> queryAuditMoney(String custPackId);
}