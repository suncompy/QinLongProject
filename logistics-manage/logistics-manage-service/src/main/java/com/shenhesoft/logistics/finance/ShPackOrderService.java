package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

/**
 * 短驳打包-运单中间表-业务层接口.
 * <p>
 * <a href="ShPackOrderService.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ShPackOrderService {

  /**
   * 添加短驳打包-运单中间表.
   * 
   * @param shPackOrder 短驳打包-运单中间表实体
   * @return 页面表单
   */
  public ShPackOrder addShPackOrder(ShPackOrder shPackOrder);

  /**
   * 查看短驳打包-运单中间表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  public Map<String, Object> getShPackOrderById(String id);

  /**
   * 修改短驳打包-运单中间表.
   * 
   * @param shPackOrder 短驳打包-运单中间表实体
   */
  public void editShPackOrderById(ShPackOrder shPackOrder);

  /**
   * 删除指定短驳打包-运单中间表.
   * 
   * @param id 主键
   */
  public void delShPackOrderById(String id);

  /**
   * 批量删除指定短驳打包-运单中间表.
   * 
   * @param ids 主键集合.
   */
  public void delShPackOrderByIds(List<String> ids);

  /**
   * 清空计量单位表.
   */
  public void delShPackOrders();

  /**
   * 获取所有短驳打包-运单中间表.
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getShPackOrders(int start, int pageSize, Map<String, Object> form);

  /**
   * 获取所有短驳打包-运单中间表.
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getShPackOrders(Map<String, Object> form);

}