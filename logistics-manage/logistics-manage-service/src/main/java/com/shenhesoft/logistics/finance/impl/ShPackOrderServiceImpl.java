package com.shenhesoft.logistics.finance.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.finance.ShPackOrder;
import com.shenhesoft.logistics.finance.ShPackOrderService;
import com.shenhesoft.logistics.finance.mapper.ShPackOrderMapper;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 短驳打包-运单中间表-业务实现.
 * <p>
 * <a href="ShPackOrderServiceImpl.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class ShPackOrderServiceImpl implements ShPackOrderService {
  
  @Autowired
  private ShPackOrderMapper shPackOrderMapper;
  
  /**
   * 新增短驳打包-运单中间表.
   * 
   * @param shPackOrder 短驳打包-运单中间表实体
   * @return 页面表单
   */
  @OrgLinkData(idName="shPackOrderId",tabComment="打包运单中间表")
  public ShPackOrder addShPackOrder(ShPackOrder shPackOrder) {
    // 生成id
    shPackOrder.setShPackId(AppUtils.randomUUID());
    // 保存短驳打包-运单中间表
    shPackOrderMapper.addShPackOrder(shPackOrder);
    return shPackOrder;
  }

  /**
   * 查看短驳打包-运单中间表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  public Map<String, Object> getShPackOrderById(String id) {
    return shPackOrderMapper.getShPackOrderById(id);
  }

  /**
   * 修改短驳打包-运单中间表.
   * 
   * @param shPackOrder 短驳打包-运单中间表实体
   */
  public void editShPackOrderById(ShPackOrder shPackOrder) {
    shPackOrderMapper.editShPackOrderById(shPackOrder);
  }

  /**
   * 删除指定短驳打包-运单中间表.
   * 
   * @param id 主键
   */
  public void delShPackOrderById(String id) {
    shPackOrderMapper.delShPackOrderById(id);
  }

  /**
   * 批量删除指定短驳打包-运单中间表.
   * 
   * @param ids 主键集合
   */
  public void delShPackOrderByIds(List<String> ids) {
    shPackOrderMapper.delShPackOrderByIds(ids);
  }

  /**
   * 清空计量单位表.
   */
  public void delShPackOrders() {
    shPackOrderMapper.delShPackOrders();
  }

  /**
  * 获取所有短驳打包-运单中间表.
  * 
  * @return 短驳打包-运单中间表分页
  */
  public List<Map<String, Object>> getShPackOrders(int start, int pageSize, Map<String, Object> form) {
    PageHelper.offsetPage(start, pageSize);
    return this.getShPackOrders(form);
  }

  /**
  * 获取所有短驳打包-运单中间表.
  * 
  * @return 短驳打包-运单中间表
  */
  public List<Map<String, Object>> getShPackOrders(Map<String, Object> form) {
    return shPackOrderMapper.getShPackOrders(form);
  }

}