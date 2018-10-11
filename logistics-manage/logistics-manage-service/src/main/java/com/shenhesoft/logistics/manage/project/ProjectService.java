package com.shenhesoft.logistics.manage.project;

import java.util.List;

import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月13日
 */
public interface ProjectService {

	/**
	 * @description 获取所有的项目
	 * @date 2017年12月13日
	 * @author shilvfei
	 * @return
	 */
	List<ProjectDetail> selectProject();

}
