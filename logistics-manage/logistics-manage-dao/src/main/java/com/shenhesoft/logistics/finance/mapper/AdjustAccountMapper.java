package com.shenhesoft.logistics.finance.mapper;

import com.shenhesoft.logistics.finance.AdjustAccount;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AdjustAccountMapper {

    int deleteByPrimaryKey(String adjustAccountId);

    int insert(AdjustAccount record);

    int addAdjustAccount(AdjustAccount record);

    AdjustAccount selectByPrimaryKey(String adjustAccountId);

    int updateByPrimaryKeySelective(AdjustAccount record);

    int updateByPrimaryKey(AdjustAccount record);
    
    /**
	 * 获取所有账户调整记录
	 * 
	 * @param form
	 *            页面表单
	 * @return 结果集合
	 */
	List<Map<String, Object>> getAdjustAccounts(@Param(value = "map") Map<String, Object> form);
}