package com.shenhesoft.logistics.business.project.manage;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月18日
 */
public interface ProjectManagmentService {

	/**
	 * @description 获取项目信息
	 * @date 2017年12月18日
	 * @author shilvfei
	 * @param
	 * @param 
	 * @param 
	 * @return
	 */
	DataGridResult getProjectManagment(Integer page, Integer limit, List<Byte> status,Integer uid,ProjectDetail projectDetail);

	/**
	 * @description 保存项目
	 * @date 2017年12月20日
	 * @author shilvfei
	 * @param project
	 * @return
	 */
	LogisticsResult save(ProjectDetail project, TbSystemUser user);

	/**
	 * @description 更新项目
	 * @date 2017年12月20日
	 * @author shilvfei
	 * @param project
	 * @param user
	 * @return
	 */
	LogisticsResult update(ProjectDetail project, TbSystemUser user);

	/**
	 * @description 逻辑删除项目
	 * @date 2017年12月20日
	 * @author shilvfei
	 * @param project
	 * @param user
	 * @return
	 */
	LogisticsResult delProject(Integer id, TbSystemUser user,String reason);

	/**
	 * @description 查看项目详情
	 * @date 2017年12月20日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	LogisticsResult selectProject(Integer id);

	/**
	 * @description 完成项目
	 * @date 2017年12月22日
	 * @author shilvfei
	 * @param id
	 * @param user
	 * @return
	 */
	LogisticsResult completeProject(Integer id, TbSystemUser user);

	/**
	 * @description 还原项目
	 * @date 2017年12月22日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	LogisticsResult restoreProject(Integer id,TbSystemUser user);

	/**
	 * @description 获取所有项目
	 * @author LiangDeng
	 * @param map 
	 * @date 2018年1月17日
	 * @param 
	 * @return
	*/
	List<TbProject> getProjectByFinance(Map<String, Object> map);

}
