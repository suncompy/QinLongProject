package com.shenhesoft.logistics.manage.branchgroup;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.search.TbBranchGroupSearch;

/**
 * @description:网点分支
 * 
 * @author shilvfei
 * 
 * @date 2017年12月11日
 */
public interface BranchGroupService {
	
	/**
	 * @description 查询网点分支
	 * @date 2017年12月24日
	 * @author shilvfei
	 * @param page
	 * @param limit
	 * @param status
	 * @return
	 */
	/*DataGridResult listDotBranchByPage(Integer page, Integer limit, byte status,TbBranchGroupSearch branchGroupSearch);*/
	
	/**
	 * @description 查询网点分支
	 * @date 2018年4月11日
	 * @author shilvfei
	 * @param form
	 * @return
	 */
	public DataGridResult getDotBranchs(Integer page, Integer limit,Map<String, Object> form);
	
	/**
	 * @description 查询网点分支
	 * @date 2018年4月11日
	 * @author shilvfei
	 * @param form
	 * @return
	 */
	public List<DotBranchDetail> getDotBranchs(Map<String, Object> form);

	/**
	 * @description 获取一级网点
	 * @date 2017年12月21日
	 * @author shilvfei
	 * @param branchLevelOne
	 * @return
	 */
	List<TbBranchGroup> selectDotBranchByLevel(byte branchLevelOne);

	/**
	 * @description 保存网点分支
	 * @date 2017年12月21日
	 * @author shilvfei
	 * @param branchGroup
	 * @return
	 */
	LogisticsResult saveBranchGroup(DotBranchDetail branchGroup);

	/**
	 * @description 删除网点分支
	 * @date 2017年12月24日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	LogisticsResult del(Integer id);

	/**
	 * @description 更新网点分支
	 * @date 2017年12月24日
	 * @author shilvfei
	 * @param branchGroup
	 * @return
	 */
	LogisticsResult update(DotBranchDetail branchGroup);

	/**
	 * @description 根据一级分支机构获取二级分支机构信息
	 * @date 2017年12月24日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	LogisticsResult getDotBranchByFirstLevel(Integer id);
	
	/**
	 * @description 根据id查询网点分支
	 * @date 2017年12月24日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	public LogisticsResult getDotBranchById(Integer id) ;
	

	/**
	 * @description 获取所有的分支机构
	 * @date 2017年12月11日
	 * @author shilvfei
	 * @return
	 */
	List<TbBranchGroup> selectBranchGroup(Byte status);

	
	/**
	 * @description 根据登录的用户获取网点分支
	 * @date 2018年1月5日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<TbBranchGroup> selectBranchGroupByUid(Integer id);

	
	/**
	 * @description 开始或者暂停顶级机构
	 * @date 2018年4月17日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	public boolean updateBranchGroupBeginOrStop(Map<String, Object> form);

	
	/**
	 * @description 重置密码
	 * @date 2018年4月18日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	public LogisticsResult restPassWd(Map<String, Object> form);

	
	/**
	 * @description 新增顶级机构
	 * @date 2018年4月23日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	public LogisticsResult insertTopDotBranch(DotBranchDetail branchGroup);

}
