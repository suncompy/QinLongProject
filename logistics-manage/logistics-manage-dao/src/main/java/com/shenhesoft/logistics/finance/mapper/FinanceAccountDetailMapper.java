package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.finance.CustomerCheckingConf;
import com.shenhesoft.logistics.finance.FinanceAccountDetail;

/**
 * 客户对账设置表-Dao.
 * <p>
 * <a href="FinanceAccountDetailMapper.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FinanceAccountDetailMapper {

  /**
   * 新增账户流水详情表.
   * 
   * @param FinanceAccountDetail 客户对账设置表实体
   * @return dao成功失败标志
   */
  int addFinanceAccountDetail(FinanceAccountDetail financeAccountDetail);

  /**
   * 获取账户流水详情表.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> getFinanceAccountDetail(@Param(value = "map") Map<String, Object> form);
  
}