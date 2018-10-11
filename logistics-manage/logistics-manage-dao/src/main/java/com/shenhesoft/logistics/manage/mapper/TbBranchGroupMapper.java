package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.pojo.anchoredCompany.TbAnchoredCompany;
import com.shenhesoft.logistics.manage.pojo.anchoredCompany.TbAnchoredCompanyExample;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroupExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface TbBranchGroupMapper {
    int countByExample(TbBranchGroupExample example);

    int deleteByExample(TbBranchGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbBranchGroup record);

    int insertSelective(TbBranchGroup record);

    List<TbBranchGroup> selectByExample(TbBranchGroupExample example);

    TbBranchGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbBranchGroup record, @Param("example") TbBranchGroupExample example);

    int updateByExample(@Param("record") TbBranchGroup record, @Param("example") TbBranchGroupExample example);

    int updateByPrimaryKeySelective(TbBranchGroup record);

    int updateByPrimaryKey(TbBranchGroup record);
    
    List<TbBranchGroup> selectBranchGroupByCusId(Integer id);

	List<DotBranchDetail> selectDotBranchDetailByExample(TbBranchGroupExample example);
	
	DotBranchDetail selectDotBranchDetailById(Integer id);
	
	 
	/**
	 * @description  根据当前登录的用户查询该用户下的所有分支机构
	 * @date 2018年1月5日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<TbBranchGroup> selectDotBranchByUid(@Param("map")Map<String,Object> map);
	
	/**
	 * @description  根据当前登录的用户查询该用户下的所有分支机构的ID
	 * @date 2018年1月5日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<Integer> selectDotBranchIdsByUid(Integer id);
	
	/**
	 * 根据当前登录人id查询用户所任职机构
	 * @description 
	 * @author liangLin
	 * @date 2018年2月6日
	 * @param 
	 * @return
	 */
	Map<String,Object> getCurTopBranchByUid(Integer id);

	
	/**
	 * @description 更新挂靠公司名称
	 * @date 2018年2月7日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	void updateCompanyName(String name);
	
	
	/**
	 * @description 查询挂靠公司信息
	 * @date 2018年4月14日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<TbAnchoredCompany> getAnchoredCompanys(@Param(value = "map") Map<String, Object> form);
	
	/**
	 * @description 新增挂靠公司
	 * @date 2018年2月7日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	int insertAnchoredCompany(TbAnchoredCompany anchoredCompany);

	
	/**
	 * @description 更新挂靠公司
	 * @date 2018年4月14日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	void updateAnchoredCompany(TbAnchoredCompany anchoredCompany);
	
	int deleteAnchoredCompanyByKey(Integer id);

	List<DotBranchDetail> getDotBranchs(Map<String, Object> map);
}