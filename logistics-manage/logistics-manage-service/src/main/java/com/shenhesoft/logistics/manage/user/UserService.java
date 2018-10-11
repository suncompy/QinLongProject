package com.shenhesoft.logistics.manage.user;

import java.util.Set;

import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月7日
 */
public interface UserService {

	/**
	 * @description 更新登录时间
	 * @date 2017年12月9日
	 * @author shilvfei
	 * @param username
	 */
	TbSystemUser updateLoginTime(String username);

	/**
	 * 通过用户名和密码查询 客户信息
	 * @author dusd
	 * @date 2017年12月23日
	 * @param userName
	 * @param password
	 * @return
	 */
	TbSystemUser getTbSystemUserByUserNamePassword(String userName, String password);

	/**
	 * 通过用户id 得到用户信息
	 * @author dusd
	 * @date 2017年12月23日
	 * @param userId 用户主键
	 * @return
	 */
	TbSystemUser getTbSystemUserById(Integer userId);

	
	/**
	 * @description 校验用户信息
	 * @date 2018年1月30日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	public LogisticsResult checkUserInfo(String param,int type);
	
	/**
	 * @description 根据用户名获取用户信息
	 * @date 2018年1月30日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	public TbSystemUser getUserByUserName(String userName);

	/**
	 *获取所有权限
	 */
	Set<String> getPermissions(String userName);
	
}
