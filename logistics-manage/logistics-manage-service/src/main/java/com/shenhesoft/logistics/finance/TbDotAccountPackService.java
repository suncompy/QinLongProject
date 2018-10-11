package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description
 * 
 * @author shilvfei
 * 
 * @date 2018年4月23日
 */
public interface TbDotAccountPackService {

	public List<Map<String, Object>> getDotAccountPacks(Map<String, Object> form);
	
	public List<Map<String, Object>> getDotAccountPacks(Integer page,Integer limit,Map<String, Object> form);

	public TbDotAccountPack addDotAccountPack(TbDotAccountPack dotAccountPack, TbSystemUser user);

	public void financeAuditShortPackByIds(String dotAccountPackIds);
}
