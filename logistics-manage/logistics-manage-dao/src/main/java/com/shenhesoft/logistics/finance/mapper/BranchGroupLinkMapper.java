package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.shenhesoft.logistics.finance.BranchGroupLink;

/**
 * 短驳打包-运单中间表-Dao.
 * <p>
 * <a href="BranchGroupLinkMapper.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface BranchGroupLinkMapper {

	/**
	 * 新增短驳打包-运单中间表.
	 * 
	 * @param branchGroupLink
	 *            短驳打包-运单中间表实体
	 * @return dao成功失败标志
	 */
	int addBranchGroupLink(BranchGroupLink branchGroupLink);

	/**
	 * 获取所有短驳打包-运单中间表.
	 * 
	 * @param form
	 *            页面表单
	 * @return 结果集合
	 */
	List<Map<String, Object>> getBranchGroupLinks(@Param(value = "map") Map<String, Object> form);
	
	/**
     * 获取所有短驳打包-运单中间表.
     * 
     * @param form
     *            页面表单
     * @return 结果集合
     */
    Map<String, String> getSysOrgCode(Integer id);
}