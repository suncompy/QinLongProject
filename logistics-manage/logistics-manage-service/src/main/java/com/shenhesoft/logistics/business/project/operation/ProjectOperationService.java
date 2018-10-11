package com.shenhesoft.logistics.business.project.operation;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.business.helpPojo.ProjectOperationDetail;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;

/**
 * @description:项目运营
 * 
 * @author shilvfei
 * 
 * @date 2017年12月25日
 */
public interface ProjectOperationService {

	/**
	 * @description 分页查询项目信息
	 * @date 2017年12月25日
	 * @author shilvfei
	 * @return
	 */
	DataGridResult listProjectOperationByPage(Integer page, Integer limit,Integer uid,TbProject project);

	
	/**
	 * @description 获取货位图
	 * @date 2017年12月31日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	LogisticsResult getCargoLocationById(Integer id);


	/**
	 * @description 通过id获取项目运营管理项目详情
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	ProjectOperationDetail getProjectOperationByPid(Integer id);
	
	/**
	 * 项目核算
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> queryProjectCheck2(int start, int pageSize, Map<String, Object> form);

	/**
	 * 项目核算
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> queryProjectCheck2(Map<String, Object> form);

}
