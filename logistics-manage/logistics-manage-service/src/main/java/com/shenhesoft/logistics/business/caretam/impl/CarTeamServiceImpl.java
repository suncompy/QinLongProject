package com.shenhesoft.logistics.business.caretam.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenhesoft.logistics.business.caretam.CarTeamService;
import com.shenhesoft.logistics.manage.helpPojo.CarTeamDetail;
import com.shenhesoft.logistics.manage.mapper.TbCarTeamMapper;
import com.shenhesoft.logistics.manage.pojo.carTeam.TbCarTeam;
import com.shenhesoft.logistics.manage.pojo.carTeam.TbCarTeamExample;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月19日
 */
@Service
public class CarTeamServiceImpl implements CarTeamService{

	@Autowired
	private TbCarTeamMapper carTeamMapper;

	@Override
	public List<TbCarTeam> selectAnchoredCarTeam(Integer companyId) {
		return carTeamMapper.selectCarTeamByCompanyId(companyId);
	}
	
}
