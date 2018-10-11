/*
 * @copyright 2014-2015 Shenhe soft Inc. All rights reserved.
 */
 
package com.shenhesoft.logistics.finance.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.finance.CustSettle;
import com.shenhesoft.logistics.finance.CustSettleService;
import com.shenhesoft.logistics.finance.mapper.CustSettleMapper;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 客户结算表-业务实现.
 * <p>
 * <a href="CustSettleServiceImpl.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-03-29
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CustSettleServiceImpl implements CustSettleService {
  
  @Autowired
  private CustSettleMapper custSettleMapper;
  
  /**
   * 新增客户结算表.
   * 
   * @param custSettle 客户结算表实体
   * @return 页面表单
   */
  @OrgLinkData(tabComment="客户结算")
  public CustSettle addCustSettle(CustSettle custSettle) {
    // 生成id
    custSettle.setId(AppUtils.randomUUID());
    // 保存客户结算表
    custSettleMapper.addCustSettle(custSettle);

    return custSettle;
  }

  /**
   * 查看客户结算表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  public Map<String, Object> getCustSettleById(String id) {
    return custSettleMapper.getCustSettleById(id);
  }

  /**
   * 修改客户结算表.
   * 
   * @param custSettle 客户结算表实体
   */
  public void editCustSettleById(CustSettle custSettle) {
    custSettleMapper.editCustSettleById(custSettle);
  }

  /**
   * 删除指定客户结算表.
   * 
   * @param id 主键
   */
  public void delCustSettleById(String id) {
    custSettleMapper.delCustSettleById(id);
  }

  /**
   * 批量删除指定客户结算表.
   * 
   * @param ids 主键集合
   */
  public void delCustSettleByIds(List<String> ids) {
    custSettleMapper.delCustSettleByIds(ids);
  }

  /**
   * 清空计量单位表.
   */
  public void delCustSettles() {
    custSettleMapper.delCustSettles();
  }

  /**
  * 获取所有客户结算表.
  * 
  * @return 客户结算表分页
  */
  public List<Map<String, Object>> getCustSettles(int start, int pageSize, Map<String, Object> form) {
    PageHelper.offsetPage(start, pageSize);
    return this.getCustSettles(form);
  }

  /**
  * 获取所有客户结算表.
  * 
  * @return 客户结算表
  */
  public List<Map<String, Object>> getCustSettles(Map<String, Object> form) {
    return custSettleMapper.getCustSettles(form);
  }

}