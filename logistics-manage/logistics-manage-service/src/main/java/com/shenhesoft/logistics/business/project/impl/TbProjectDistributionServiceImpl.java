package com.shenhesoft.logistics.business.project.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenhesoft.logistics.business.mapper.TbProjectDistributionMapper;
import com.shenhesoft.logistics.business.project.TbProjectDistributionService;

/**
 * 项目分配信息 service 实现
 * @author dusd
 * @date 2018年1月8日
 */
@Service
public class TbProjectDistributionServiceImpl implements TbProjectDistributionService {
	
	/**
	 * 项目分配信息 mapper 接口
	 */
	@Autowired
	private TbProjectDistributionMapper tbProjectDistributionMapper;

	@Override
	public void clearNoReceiveTbOrderTask() throws Exception {
		tbProjectDistributionMapper.clearNoReceiveTbOrderTask();
	}

}
