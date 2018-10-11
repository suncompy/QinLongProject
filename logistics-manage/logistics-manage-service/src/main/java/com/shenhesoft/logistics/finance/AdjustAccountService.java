package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 账户调整信息表-业务层接口.
 * <p>
 * <a href="AdjustAccountService.java"><i>View Source</i></a>
 * </p>
 * 
 * @author shilvfei
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AdjustAccountService {

	 
	/**
	 * @description 添加账户调整信息
	 * @return
	 */
	public AdjustAccount addAdjustAccount(AdjustAccount adjustAccount, TbSystemUser user);

	/**
	 * @description 获取所有账户调整信息
	 * @return
	 */
	public List<Map<String, Object>> getAdjustAccounts(int start, int pageSize, Map<String, Object> innerMap);
	
}
