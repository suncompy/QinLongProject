package com.shenhesoft.logistics.manage.project.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria;
import com.shenhesoft.logistics.manage.project.ProjectService;

/**
 * @description:项目服务
 * 
 * @author shilvfei
 * 
 * @date 2017年12月13日
 */
@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private TbProjectMapper projectMapper;
	
	@Override
	public List<ProjectDetail> selectProject() {
		TbProjectExample projectExample = new TbProjectExample();
		Criteria criteria = projectExample.createCriteria();
		List<Byte> status=new ArrayList<>();
		status.add(Constants.PROJECT_STATUS_RUNNING);//正在运行
		status.add(Constants.PROJECT_STATUS_UNUSED);//未使用
		criteria.andStatusIn(status);// 状态
		criteria.andFinishDateIsNull();
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		criteria.andTabNameEqualTo("tb_project");
		criteria.andDeleteFlagEqualTo((byte)0);
		 List<ProjectDetail> projects = projectMapper.selectProjectDetailByExample(projectExample);
		return projects;
	}

}
