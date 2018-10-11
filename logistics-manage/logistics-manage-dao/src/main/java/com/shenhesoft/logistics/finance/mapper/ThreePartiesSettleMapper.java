package com.shenhesoft.logistics.finance.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.shenhesoft.logistics.finance.ThreePartiesSettle;

public interface ThreePartiesSettleMapper {

    int deleteByPrimaryKey(String id);

    int addThreePartiesSettle(ThreePartiesSettle record);

    int editThreePartiesSettle(ThreePartiesSettle record);
    
    int editThreePartiesSettleByIds(@Param("map")ThreePartiesSettle record,@Param("list") List<String> settleIdList);
	
    ThreePartiesSettle getThreePartiesSettleById(String id);
    
    List<ThreePartiesSettle> getThreePartiesSettleByIds(@Param("list") List<String> settleIdList);
    
	/**
	 * @description 获取所有三方结算信息
	 * @date 2018年2月11日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<Map<String, Object>> getThreePartiesSettle(@Param(value = "map")Map<String, Object> form);

}