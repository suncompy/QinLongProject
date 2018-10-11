package com.shenhesoft.logistics.business.inventory;

import com.shenhesoft.logistics.business.helpPojo.TbOrderHelpPojo;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.manage.search.OrderSearch;

/**
 * @description 短驳出入库查询
 * 
 * @author shilvfei
 * 
 * @date 2018年1月2日
 */
public interface ShortAccessStorageService {

	
	/**
	 * @description 短驳出入库查询 项目列表
	 * @date 2018年1月3日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	DataGridResult getShortAccessStorage(Integer page, Integer limit,OrderSearch orderSearch);

	
	/**
	 * @description 短驳出入库查询 查询统计
	 * @date 2018年1月3日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	TbOrderHelpPojo shortAccessStorageStatistics(Integer projectId);
	
}
