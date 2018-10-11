package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 三方应收款信息表-业务层接口.
 * <p>
 * <a href="ThreePartiesReceivablesService.java"><i>View Source</i></a>
 * </p>
 * 
 * @author shilvfei
 * @date 2018-01-19
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ThreePartiesReceivablesService {
	
	ThreePartiesReceivables addThreePartiesReceivables(ThreePartiesReceivables threePartiesReceivables, TbSystemUser user);
	
	public List<Map<String, Object>> getThreePartiesReceivables(int start, int pageSize, Map<String, Object> form);
	
	public List<Map<String, Object>> getThreePartiesReceivables(Map<String, Object> form);
}
