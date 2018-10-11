package com.shenhesoft.logistics.business.mapper;

import com.shenhesoft.logistics.business.helpPojo.ProjectDistributionDetail;
import com.shenhesoft.logistics.business.helpPojo.TbProjectDetail;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample;
import com.shenhesoft.logistics.manage.pojo.project.distribution.TbProjectDistribution;
import com.shenhesoft.logistics.manage.pojo.project.distribution.TbProjectDistributionExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbProjectDistributionMapper {
    int countByExample(TbProjectDistributionExample example);

    int deleteByExample(TbProjectDistributionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbProjectDistribution record);

    int insertSelective(TbProjectDistribution record);

    List<TbProjectDistribution> selectByExample(TbProjectDistributionExample example);

    TbProjectDistribution selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbProjectDistribution record, @Param("example") TbProjectDistributionExample example);

    int updateByExample(@Param("record") TbProjectDistribution record, @Param("example") TbProjectDistributionExample example);

    int updateByPrimaryKeySelective(TbProjectDistribution record);

    int updateByPrimaryKey(TbProjectDistribution record);
    
    List<TbProjectDetail> selectTbProjectDetailByExample(TbProjectExample projectExample);

	TbProjectDistribution selectTodayalreadyRecNumsByProjectId(Map<String, Object> map);
	
	/**
	 * @description  查询发布任务列表
	 * @date 2018年1月17日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<ProjectDistributionDetail> selectProjectByExample(TbProjectExample projectExample);
	
	/**
	 * 清理未接受的短驳运单
	 * 每天凌晨两点清理，比如说分配了100辆车 有10个司机接单，到两点是清理掉剩下的90个分配的任务
	 * @author dusd
	 * @date 2018年1月8日
	 */
	void clearNoReceiveTbOrderTask();
	
	
	/**
	 * @description 查询已领任务
	 * @date 2018年1月17日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	Integer selectAlreadyrecNumByProjectId(Map<String, Object> map);
	
	/**
	 * @description 查询今日待领任务
	 * @date 2018年1月17日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	Integer selectTodayCarNumByProjectId(Map<String, Object> map);
	
	
	/**
	 * @description 查询今日完成任务
	 * @date 2018年1月17日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	Integer selectTodayCompleteNumByProjectId(Map<String, Object> map);
	
	/**
	 * @description 查询累积完成任务数
	 * @date 2018年1月17日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	Integer selectSumCompleteNumByProjectId(Map<String, Object> map);

	
	/**
	 * @description 获取当天任务的状态
	 * @date 2018年1月24日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	Byte getProjectDistributionStatus(Map<String, Object> map);

	
	/**
	 * @description 根据项目id获取当天任务信息
	 * @date 2018年1月24日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	TbProjectDistribution getTodayProjectDistributionByProjectId(Map<String, Object> map);

	
	/**
	 * @description 根据项目id获取任务 id
	 * @date 2018年2月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	Integer isHaveJob(Map<String, Object> map);
}