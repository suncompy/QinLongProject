package com.shenhesoft.logistics.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.business.helpPojo.ProjectOperationDetail;
import com.shenhesoft.logistics.business.helpPojo.TbOrderCarDetail;
import com.shenhesoft.logistics.manage.helpPojo.ProjectAppHelp;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample;

public interface TbProjectMapper {
    int countByExample(TbProjectExample example);

    int deleteByExample(TbProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbProject record);

    int insertSelective(TbProject record);

    List<TbProject> selectByExample(TbProjectExample example);

    TbProject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbProject record, @Param("example") TbProjectExample example);

    int updateByExample(@Param("record") TbProject record, @Param("example") TbProjectExample example);

    int updateByPrimaryKeySelective(TbProject record);

    int updateByPrimaryKey(TbProject record);

	/**
	 * @description 批量暂停项目每日分配功能
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	*/
	int stopJobByList(List<Integer> list);

	/**
	 * @description  批量开始项目每日分配功能 
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	*/
	int beginJobByList(List<Integer> list);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月20日
	 * @param 
	 * @return
	*/
	TbProject selectPartDetailByPrimaryKey(Integer id);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月21日
	 * @param 
	 * @return
	*/
	int selectIsDistribution(Integer its);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月21日
	 * @param 
	 * @return
	*/
	int beginJobByMap(Map<String, Object> map);
	
    /**
     * @description 获取详情
     * @date 2017年12月22日
     * @author shilvfei
     * @param id
     * @return
     */
    ProjectDetail selectProjectDetailByPrimaryKey(Integer id);

	/**
	 * @description
	 * @date 2017年12月22日
	 * @author shilvfei
	 * @param projectExample
	 * @return
	 */
	List<ProjectDetail> selectProjectDetailByExample(TbProjectExample projectExample);
	
	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	List<TbProject> getProjects();
	
	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	TbProject selectDetailProjectById(Integer id);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	List<TbOrderCarDetail> selectCarTeams(Map<String,Object> map);
	
	
	List<ProjectOperationDetail> selectProjectOperationDetail();
	
	/**
	 * @description 
	 * @author LiangDeng
	 * @param map 
	 * @date 2017年12月25日
	 * @param 
	 * @return
	*/
	List<TbProject> selectAllProject(Map<String, Object> map);
	
	/**
	 * @description
	 * @date 2017年12月26日
	 * @author shilvfei
	 * @param example
	 * @return
	 */
	List<ProjectOperationDetail> selectProjectOperationDetail(TbProjectExample example);
	
	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月26日
	 * @param 
	 * @return
	*/
	int updateStatusById(Integer projectId);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月26日
	 * @param 
	 * @return
	*/
	List<TbOrderCarDetail> selectCarTeamsByPlatform(Map<String, Object> map);

	/**
	 * 通过车牌号查询车辆信息
	 * @author dusd
	 * @date 2017年12月28日
	 * @param plateNumber
	 * @return
	 */
	TbOrderCarDetail viewTbOrderCarDetailByPlateNumber(Map<String, Object> map);
	
	/**
	 * 短驳查询当前登录人所管理项目
	 * @author LiangDeng
	 * @date 2017年12月28日
	 * @param 
	 * @return
	 */
	List<TbProject> getProjectsByShort(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2018年1月17日
	 * @param 
	 * @return
	*/
	List<TbProject> queryAllProjectOfSence(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @param map 
	 * @date 2018年1月17日
	 * @param 
	 * @return
	*/
	List<TbProject> getAllProjectByFince(Map<String, Object> map);

	/**
	 * @description app火运查询项目列表
	 * @author LiangDeng
	 * @date 2018年1月22日
	 * @param 
	 * @return
	*/
	List<ProjectAppHelp> selectAppAllProject(Map<String, Object> map);

	/**
	 * @description app短驳查询项目列表
	 * @author LiangDeng
	 * @date 2018年1月22日
	 * @param 
	 * @return
	*/
	List<ProjectAppHelp> selectAppAllProjectByBulk(Map<String, Object> map);

	
	/**
	 * @description 根据项目id获取 正在运行的车辆
	 * @date 2018年3月15日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<TbOrderCarDetail> selectRuningCarTeams(Integer projectId);

	
	/**
	 * @description 根据司机id获取司机信息
	 * @date 2018年3月16日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	TbOrderCarDetail selectDriverByDriverId(Integer driverId);
}