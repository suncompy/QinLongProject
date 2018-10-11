package com.shenhesoft.logistics.business.shortbarge.publish;

import java.io.IOException;
import java.util.List;

import com.shenhesoft.logistics.business.helpPojo.ProjectDistributionDetail;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;

/**
 * @description 短驳任务发布的service
 * 
 * @author shilvfei
 * 
 * @date 2018年1月24日
 */
public interface PublishJobService {

	/**
	 * @description 发布任务list
	 * @author liangLin
	 * @date 2017年12月19日
	 * @param page 当前页
	 * 		  limit 每页显示记录数
	 * @return 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	*/
	DataGridResult listPublishJobByPage(Integer page, Integer limit,ProjectDistributionDetail project) throws IOException, ClassNotFoundException;
	
	/**
	 * @description 暂停分配每日任务
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	*/
	boolean stopJob(List<Integer> idList,List<Integer> projectStageList);

	/**
	 * @description 开始分配每日任务
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	*/
	boolean beginJob(List<Integer> idList,List<Integer> projectStageList);

	/**
	 * @description 分配任务
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 项目id
	 *            ,分配数量 type 1 接取 2 送达 3 汽运
	 * @return
	 */
	boolean putDistributeJob(Integer projectId, Integer carNum, Integer userId, byte type);
	
	/**
	 * @description 分配点击后的  页面字段信息获取 
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	*/
	TbProject getMsgByProjectId(Integer id);
	
	/**
	 * @description 判断  -目前分配任务 不得低于 今日已领取任务 
	 * @author liangLin
	 * @date 2017年12月20日
	 * @param 
	 * @return
	*/
	boolean IsHigherByTodayNum(Integer projectId, Integer num, byte projectType);

	
	/**
	 * @description 判断该项目 今日是否有分配任务
	 * @date 2018年2月1日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	boolean isHaveJob(Integer projectId, Integer shortType);

}
