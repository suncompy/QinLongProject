package com.shenhesoft.logistics.manage.employee;

import java.util.List;

import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.helpPojo.EmployInfo;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.search.TbSystemSearch;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月9日
 */
public interface EmployeeInformationService {

	/**
	 * @description 分页获取员工信息
	 * @date 2017年12月11日
	 * @author shilvfei
	 * @param page
	 * @param limit
	 * @return
	 */
	DataGridResult listEmployByPage(Integer page, Integer limit,TbSystemSearch systemSearch);

	/**
	 * @description 添加员工信息
	 * @date 2017年12月11日
	 * @author shilvfei
	 * @param employ
	 * @param roleId
	 * @return
	 */
	LogisticsResult addEmployee(EmployInfo employ, Integer roleId);

	/**
	 * @description 校验手机号是否存在
	 * @date 2017年12月11日
	 * @author shilvfei
	 * @param param
	 * @param type
	 * @return
	 */
	public LogisticsResult checkUserInfo(String param,int type);

	/**
	 * @description 禁用员工信息 
	 * @date 2017年12月11日
	 * @author shilvfei
	 * @param uid
	 * @return
	 */
	LogisticsResult delEmployee(List<Integer> ids);

	/**
	 * @description 更新员工信息
	 * @date 2017年12月11日
	 * @author shilvfei
	 * @param employ
	 * @param roleId
	 * @return
	 */
	LogisticsResult updateEmployee(EmployInfo employ, Integer roleId);

	/**
	 * @description 获取单个员工信息
	 * @date 2017年12月14日
	 * @author shilvfei
	 * @param integer
	 * @return
	 */
	LogisticsResult getEmployeeById(Integer integer);

	/**
	 * @description 获取角色为负责人
	 * @date 2017年12月21日
	 * @author shilvfei
	 * @param customerStatusYes
	 * @return
	 */
	List<TbSystemUser> selectReponsibler(byte status);

	
	/**
	 * @description 根据用户名密码查询用户信息
	 * @date 2018年4月17日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	TbSystemUser getUserByUserNamePassword(String username, String password);
	
}
