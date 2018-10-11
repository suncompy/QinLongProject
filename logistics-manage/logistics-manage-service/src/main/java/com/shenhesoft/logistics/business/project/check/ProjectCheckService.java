package com.shenhesoft.logistics.business.project.check;
import java.util.List;

import com.shenhesoft.logistics.business.helpPojo.ProjectOperationDetail;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;

/**
 * @description:项目核查
 * 
 * @author shilvfei
 * 
 * @date 2017年12月25日
 */
public interface ProjectCheckService {

	/**
	 * @description 分页查询项目核查的项目列表
	 * @date 2017年12月25日
	 * @author shilvfei
	 * @return
	 */
	DataGridResult listProjectCheckByPage(Integer page, Integer limit,Integer uid,TbProject project);

}
