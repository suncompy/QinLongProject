package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.helpPojo.EmployInfo;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUserExample;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface TbSystemUserMapper {
    int countByExample(TbSystemUserExample example);

    int deleteByExample(TbSystemUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSystemUser record);

    int insertSelective(TbSystemUser record);

    List<TbSystemUser> selectByExample(TbSystemUserExample example);

    TbSystemUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSystemUser record, @Param("example") TbSystemUserExample example);

    int updateByExample(@Param("record") TbSystemUser record, @Param("example") TbSystemUserExample example);

    int updateByPrimaryKeySelective(TbSystemUser record);

    int updateByPrimaryKey(TbSystemUser record);
    
    List<EmployInfo> selectEmployInfo(Byte workStatus);

	List<EmployInfo> selectEmployInfoByExample(TbSystemUserExample example);
	
	EmployInfo selectEmployInfoBySid(Integer id);

	List<TbSystemUser> selectReponsibler(TbSystemUserExample example);

	TbSystemUser getTbSystemUserByUserNamePassword(TbSystemUser tbSystemUser);

	/**
	 * 通过用户id 得到用户信息
	 * @author dusd
	 * @date 2017年12月23日
	 * @param userId 用户主键
	 * @return
	 */
	TbSystemUser getTbSystemUserById(Integer userId);
	 /**
	   * 通过用户phone 得到有无用户信息.
	   * @param form 页面表单
	   * @return 结果集合
	   */
	Map<String, Object> getUserValiByPhone(@Param(value = "map") Map<String, Object> form);
	 /**
	   * 通过phone重置密码.
	   * @param form 页面表单
	   * @return 结果集合
	   */
	int updatePasswdByPhone(TbSystemUser tbSystemUser);

	Set<String> getPermissions(String userName);
}