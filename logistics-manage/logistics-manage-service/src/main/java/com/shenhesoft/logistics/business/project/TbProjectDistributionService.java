package com.shenhesoft.logistics.business.project;

/**
 * 项目分配信息 service 接口
 * @author dusd
 * @date 2018年1月8日
 */
public interface TbProjectDistributionService {

	/**
	 * 清理未接受的短驳运单
	 * 每天凌晨两点清理，比如说分配了100辆车 有10个司机接单，到两点是清理掉剩下的90个分配的任务
	 * @author dusd
	 * @date 2018年1月8日
	 */
	void clearNoReceiveTbOrderTask() throws Exception;

}
