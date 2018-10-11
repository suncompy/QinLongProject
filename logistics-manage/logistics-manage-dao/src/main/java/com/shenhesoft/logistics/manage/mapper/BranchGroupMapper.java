package com.shenhesoft.logistics.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;

public interface BranchGroupMapper {
	/**
	 * @description 获取所有的网点分支
	 * @date 2018年4月11日
	 * @author shilvfei
	 * @param form
	 * @return
	 */
	List<DotBranchDetail> getBranchGroups(@Param(value = "map") Map<String, Object> form);

	
	/**
	 * @description 开始或者暂停顶级机构
	 * @date 2018年4月17日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	int updateBranchGroupBeginOrStop(@Param(value = "map")Map<String, Object> form);


	
	/**
	 * @description 顶级机构重置密码
	 * @date 2018年4月18日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	int restPassWd(@Param(value = "map")Map<String, Object> form);
}