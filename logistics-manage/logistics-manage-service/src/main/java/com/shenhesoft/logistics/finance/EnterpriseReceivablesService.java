package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description
 * 
 * @author shilvfei
 * 
 * @date 2018年4月24日
 */
public interface EnterpriseReceivablesService {

	TbEnterpriseReceivables addEnterpriseReceivables(TbEnterpriseReceivables enterpriseReceivables, TbSystemUser user);

	List<Map<String, Object>> getEnterpriseReceivables(int start, int pageSize, Map<String, Object> map);
	
	List<Map<String, Object>> getEnterpriseReceivables(Map<String, Object> map);

	void settleFinanceAuditCostPackByIds(String enterpriseReceivablesIds, String passFlag);
}
