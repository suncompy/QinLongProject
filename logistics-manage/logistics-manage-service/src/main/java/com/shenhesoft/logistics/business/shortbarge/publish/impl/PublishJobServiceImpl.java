package com.shenhesoft.logistics.business.shortbarge.publish.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.business.helpPojo.ProjectDistributionDetail;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.mapper.TbProjectDistributionMapper;
import com.shenhesoft.logistics.business.mapper.TbShortBargeMapper;
import com.shenhesoft.logistics.business.mapper.TbStockMapper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderMapper;
import com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBarge;
import com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBargeExample;
import com.shenhesoft.logistics.business.shortbarge.publish.PublishJobService;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AreaCodeUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.finance.ShortOrderFinanceService;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbCustomerMapper;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample;
import com.shenhesoft.logistics.manage.pojo.project.distribution.TbProjectDistribution;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;

/**
 * @description 短驳任务发布的service实现类
 * 
 * @author shilvfei
 * 
 * @date 2018年1月24日
 */
@Service
public class PublishJobServiceImpl implements PublishJobService{

	@Autowired
	private TbShortBargeMapper shortBargeMapper;

	@Autowired
	private TbProjectMapper tbProjectMapper;

	@Autowired
	private TbProjectDistributionMapper tbProjectDistributionMapper;

	@Autowired
	private TbCustomerMapper customerMapper;

	@Autowired
	private TbTrainStationMapper stationMapper;

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
	@Override
	public DataGridResult listPublishJobByPage(Integer page, Integer limit, ProjectDistributionDetail project)
			throws IOException, ClassNotFoundException {
		PageHelper.startPage(page, limit);
		TbProjectExample projectExample = projectDistributionCriteria(project);
		List<ProjectDistributionDetail> list = tbProjectDistributionMapper.selectProjectByExample(projectExample);
		for (ProjectDistributionDetail projectDistributionDetail : list) {
			getShortBargeAndRecNum(projectDistributionDetail);// 获取短驳承运方 和统计任务
		}
		PageInfo<ProjectDistributionDetail> pageInfo = new PageInfo<ProjectDistributionDetail>(list);
		long total = pageInfo.getTotal(); // 获取总记录数

		return new DataGridResult(total, list, limit);
	}

	
	/**
	 * @description 暂停任务
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param
	 * @return
	 */
	@Override
	@Transactional
	public boolean stopJob(List<Integer> projectIdlist, List<Integer> projectStageList) {
		for (int i = 0; i < projectIdlist.size(); i++) {
			Integer projectId = projectIdlist.get(i);
			Integer type = projectStageList.get(i);// 1 接取 2 送达 3 汽运

			Map<String, Object> map = new HashMap<String, Object>();
			Date nowDate = new Date();//现在的时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date tomorrowDate = DateUtils.addDate(nowDate, 1);
			String expireDate = DateUtils.date2Str(tomorrowDate,sdf)+" 2:00:00";//过期时间
			map.put("projectId", projectId);
			map.put("taskType", type);//1 接取 2送达 3汽运
			map.put("expireDate", expireDate);
			map.put("nowDate", DateUtils.date2Str(nowDate,sdf));//当前时间

			//查询任务信息
			TbProjectDistribution distribution = tbProjectDistributionMapper.getTodayProjectDistributionByProjectId(map);	
			
			if(distribution==null){
				return false;
			}
			
			Byte status = distribution.getStatus();//判断状态
			
			if(status==Constants.DISTRIBUTION_STATUS_RUNNING){
				distribution.setStatus(Constants.DISTRIBUTION_STATUS_STOP);
				tbProjectDistributionMapper.updateByPrimaryKey(distribution);
			}else{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @description 开始分配每日任务
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	*/
	@Override
	@Transactional
	public boolean beginJob(List<Integer> list, List<Integer> projectStageList) {
		// types 1：接取的开始 2：送达的开始
		for (int i = 0; i < list.size(); i++) {
			Integer projectId = list.get(i);
			Integer type = projectStageList.get(i);
			
			Map<String, Object> map = new HashMap<String, Object>();
			Date nowDate = new Date();//现在的时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date tomorrowDate = DateUtils.addDate(nowDate, 1);
			String expireDate = DateUtils.date2Str(tomorrowDate,sdf)+" 2:00:00";//过期时间
			map.put("projectId", projectId);
			map.put("taskType", type);//1 接取 2送达 3汽运
			map.put("expireDate", expireDate);
			map.put("nowDate", DateUtils.date2Str(nowDate,sdf));//当前时间

			//查询任务信息
			TbProjectDistribution distribution = tbProjectDistributionMapper.getTodayProjectDistributionByProjectId(map);	
			
			if(distribution==null){
				return false;
			}
			
			Byte status = distribution.getStatus();//判断状态
			
			if(status==Constants.DISTRIBUTION_STATUS_STOP){
				distribution.setStatus(Constants.DISTRIBUTION_STATUS_RUNNING);
				tbProjectDistributionMapper.updateByPrimaryKey(distribution);
			}else{
				return false;
			}
		}

		return true;
	}

	/**
	 * @description 分配任务
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 项目id
	 *            ,分配数量 type 1 接取 2 送达 3 汽运
	 * @return
	 */
	@Override
	@Transactional
	public boolean putDistributeJob(Integer id, Integer num, Integer userId, byte type) {

		// 获取项目信息
		TbProject project = tbProjectMapper.selectByPrimaryKey(id);

		// 判断是新分配还是更新
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", id);
		map.put("taskType", type);//1 接取 2送达 3汽运
		
		TbProjectDistribution tb = tbProjectDistributionMapper.selectTodayalreadyRecNumsByProjectId(map);
		if (tb != null) {
			// 获取待领取分配的车数
			tb.setCarNum(tb.getCarNum() + num);// 分配数+剩余车数
			tb.setCreatorId(userId);

			// 更新操作
			int row = tbProjectDistributionMapper.updateByPrimaryKeySelective(tb);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
		} else {
			// 新增
			TbProjectDistribution tbProjectDistribution = new TbProjectDistribution();
			tbProjectDistribution.setProjectId(id);
			// 项目编号
			tbProjectDistribution.setProjectCode(project.getProjectCode());
			// 项目类型
			tbProjectDistribution.setProjectType(project.getProjectType());
			// 项目联运模式
			tbProjectDistribution.setTransportType(project.getTransportType());

			tbProjectDistribution.setCarNum(num);
			tbProjectDistribution.setCreatorId(userId);
			
			Date nowDate = new Date();//现在的时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
			//当前日期的两点 nowDataTwoTime
			Date nowDataTwoTime = DateUtils.str2Date(DateUtils.date2Str(nowDate,sdf)+" 2:00:00",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			
			//判断当前时间 是大于凌晨两点还是小于凌晨两点
			Date expireDate = null;
			if(nowDataTwoTime.getTime()-nowDate.getTime()>0){//大于当前时间（说明是凌晨两点之前）
				expireDate = nowDataTwoTime;
			}else{//凌晨两点之后
				Date tomorrowDate = DateUtils.addDate(nowDate, 1);
				expireDate = DateUtils.str2Date( DateUtils.date2Str(tomorrowDate,sdf)+" 2:00:00",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));//过期时间
			}
			
			tbProjectDistribution.setCreateDate(nowDate);// 任务发布时间
			tbProjectDistribution.setExpireDate(expireDate);//任务过期时间
			
			tbProjectDistribution.setAlreadyRecNum(0);// 已经被领取分配数
			tbProjectDistribution.setType(type);
			tbProjectDistribution.setDeleteFlag((byte) 0);
			tbProjectDistribution.setOverFlag((byte) 0);
			tbProjectDistribution.setStatus(Constants.DISTRIBUTION_STATUS_RUNNING);//更新状态 运行中
			// 修改项目状态
			project.setStatus(Constants.PROJECT_STATUS_RUNNING);
			// 通过 项目id获取发货单位和收货单位

			// 判断type
			if (type == Constants.PROJECT_TASK_TYPE_RECEIVE) {// 接取 1
				// 发货单位
				Integer sendCargoUnitId = project.getSendCargoUnitId();
				tbProjectDistribution.setSendCompanyId(sendCargoUnitId);

				// 收货站点
				Integer receiveCargoSiteId = project.getReceiveCargoSiteId();
				tbProjectDistribution.setReceiveCompanyId(receiveCargoSiteId);

				// 修改项目的isDistribution
				project.setIsDistribution(Constants.PROJECT_IS_DISTRIBUTE_RECEIVE);
			} else if (type == Constants.PROJECT_TASK_TYPE_SEND) {// 送达 2
				// 接取站点
				Integer sendCargoSiteId = project.getForwardingSiteId();
				tbProjectDistribution.setSendCompanyId(sendCargoSiteId);

				// 收货单位
				Integer receivingDepartmentId = project.getReceivingDepartmentId();
				tbProjectDistribution.setReceiveCompanyId(receivingDepartmentId);

				project.setIsDistribution(Constants.PROJECT_IS_DISTRIBUTE_SEND);
			} else {// 汽运
					// 发货单位
				Integer sendCargoUnitId = project.getSendCargoUnitId();
				tbProjectDistribution.setSendCompanyId(sendCargoUnitId);

				// 收货单位
				Integer receivingDepartmentId = project.getReceivingDepartmentId();
				tbProjectDistribution.setReceiveCompanyId(receivingDepartmentId);
				project.setIsDistribution(Constants.PROJECT_BEGIN_DISTRIBUTE_TRUCK);
			}

			int row = tbProjectDistributionMapper.insertSelective(tbProjectDistribution);
			if (row != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			} else {
				tbProjectMapper.updateByPrimaryKey(project);// 更新项目信息
			}
		}
		return true;

	}
	
	/**
	 * @description 发布任务的条件查询
	 * @date 2018年1月18日
	 * @author shilvfei
	 * @param
	 * @return
	 */
	private TbProjectExample projectDistributionCriteria(ProjectDistributionDetail project) {
		TbProjectExample projectExample = new TbProjectExample();
		com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria criteria = projectExample
				.createCriteria();
		criteria.andDeleteFlagEqualTo((byte) 0);
		criteria.andFinishDateIsNull();
		criteria.andStatusNotEqualTo((byte) 0);
		criteria.andTabNameEqualTo("tb_project");
		projectExample.setOrderByClause("id desc");
		
		if (project == null) {
			criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
			return projectExample;
		}else {
			if(null == project.getSysOrgCode()) {
				criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
			}else {
				criteria.andSysOrgCodeEqualTo(project.getSysOrgCode());
				return projectExample;
			}
		}
		// 条件查询
		if (StringUtils.isNotBlank(project.getProjectCode())) {// 项目编号
			criteria.andProjectCodeLike(project.getProjectCode());
		}
		if (project.getBranchGroupId() != null) {// 分支机构
			criteria.andBranchGroupIdEqualTo(project.getBranchGroupId());
		}
		if (project.getProjectType() != null) {// 项目类型
			criteria.andProjectTypeEqualTo(project.getProjectType());
		}
		/*
		 * if(project.getTaskType()!=null){//阶段选择
		 * criteria.andTaskTypeEqualTo(project.getTaskType()); }
		 */
		if (project.getCargoId() != null) {// 货物id
			criteria.andCargoIdEqualTo(project.getCargoId());
		}
		// 项目创建时间
		if (StringUtils.isNotBlank(project.getBeginDate()) && StringUtils.isNotBlank(project.getEndDate())) {// 创建时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
			Date beginDate = DateUtils.str2Date(project.getBeginDate(), sdf);
			Date endDate = DateUtils.str2Date(project.getEndDate(), sdf);
			criteria.andProjectCreateDateGreaterThanOrEqualTo(beginDate);
			criteria.andProjectCreateDateLessThanOrEqualTo(endDate);
		}

		if (StringUtils.isNotBlank(project.getSendCargoCompanyName())) {// 发货企业
			criteria.andSendCargoCompanyNameLike(project.getSendCargoCompanyName());
		}
		if (StringUtils.isNotBlank(project.getReceiveCargoCompanyName())) {// 收货企业
			criteria.andReceiveCargoCompanyNameLike(project.getReceiveCargoCompanyName());
		}
		return projectExample;
	}
	
	
	/**
	 * @description 获取短驳承运方 和 代领任务数 和 已领取任务数  完成任务数 累积完成任务数 
 	 * @date 2018年1月24日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private void getShortBargeAndRecNum(ProjectDistributionDetail projectDistributionDetail) {
		// 获取发货单位和收货单位的地址
		// 判断type
		Byte type = projectDistributionDetail.getTaskType();
		if (type == Constants.PROJECT_TASK_TYPE_RECEIVE) {// 接取 1
			// 发货单位
			Integer sendCompanyId = projectDistributionDetail.getSendCompanyId();
			CustomerInfo customer = customerMapper.selectCustomerInfoByCid(sendCompanyId);
			String addressCode = customer.getAddressCode();
			projectDistributionDetail.setSendCompanyNameAddress(AreaCodeUtils.getAreaCode(addressCode) + customer.getDetailAddress());

			// 收货站点
			Integer receiveCargoSiteId = projectDistributionDetail.getReceiptCompanyId();
			if(receiveCargoSiteId!=null){
				TbTrainStation tbTrainStation = stationMapper.selectByPrimaryKey(receiveCargoSiteId);
				projectDistributionDetail.setReceiptCompanyAddress(
						AreaCodeUtils.getAreaCode(tbTrainStation.getAdressCode()) + tbTrainStation.getDetailAddress());
			}
		} else if (type == Constants.PROJECT_TASK_TYPE_SEND) {// 送达 2
			// 接取站点
			Integer sendCargoSiteId = projectDistributionDetail.getSendCompanyId();
			if(sendCargoSiteId!=null){
				TbTrainStation tbTrainStation = stationMapper.selectByPrimaryKey(sendCargoSiteId);
				projectDistributionDetail.setSendCompanyNameAddress(
						AreaCodeUtils.getAreaCode(tbTrainStation.getAdressCode()) + tbTrainStation.getDetailAddress());
			}
			// 收货单位
			Integer receivingDepartmentId = projectDistributionDetail.getReceiptCompanyId();
			CustomerInfo customer = customerMapper.selectCustomerInfoByCid(receivingDepartmentId);
			String addressCode = customer.getAddressCode();
			projectDistributionDetail.setReceiptCompanyAddress(AreaCodeUtils.getAreaCode(addressCode) + customer.getDetailAddress());
		} else {// 汽运
				// 发货单位
			Integer sendCargoUnitId = projectDistributionDetail.getSendCompanyId();
			CustomerInfo sendCargoUnit = customerMapper.selectCustomerInfoByCid(sendCargoUnitId);
			projectDistributionDetail.setSendCompanyNameAddress(
					AreaCodeUtils.getAreaCode(sendCargoUnit.getAddressCode()) + sendCargoUnit.getDetailAddress());

			// 收货单位
			Integer receivingDepartmentId = projectDistributionDetail.getReceiptCompanyId();
			CustomerInfo receivingDepartment = customerMapper.selectCustomerInfoByCid(receivingDepartmentId);
			projectDistributionDetail.setReceiptCompanyAddress(
					AreaCodeUtils.getAreaCode(receivingDepartment.getAddressCode()) + receivingDepartment.getDetailAddress());
		}

		Integer projectId = projectDistributionDetail.getId();
		// 判断是否为短驳承运方式
		// 获取短驳承运方
		TbShortBargeExample example = new TbShortBargeExample();
		com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBargeExample.Criteria shortBargecriteria = example
				.createCriteria();
		shortBargecriteria.andProjectIdEqualTo(projectId);
		shortBargecriteria.andTypeEqualTo(projectDistributionDetail.getTaskType());
		List<TbShortBarge> shortBargelist = shortBargeMapper.selectByExample(example);
		String shortBargeName = "";
		for (TbShortBarge tbShortBarge : shortBargelist) {
			shortBargeName = shortBargeName + tbShortBarge.getShortBargeName() + ";";
		}
		projectDistributionDetail.setShortBargeCarrierName(shortBargeName);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);
		map.put("taskType", projectDistributionDetail.getTaskType());// 根据taskType查询
		//获取当天任务的status
		Byte status = tbProjectDistributionMapper.getProjectDistributionStatus(map);
 		if(status ==null){
			projectDistributionDetail.setStatus((byte)0);
		}else{
			projectDistributionDetail.setStatus(status);
		}
	
		// 已领任务（调度审核通过之后）
		Integer alreadyRecNum = tbProjectDistributionMapper.selectAlreadyrecNumByProjectId(map);
		projectDistributionDetail.setAlreadyRecNum(alreadyRecNum);

		// 待领任务 (当天任务数)
		Integer waitRecNum = tbProjectDistributionMapper.selectTodayCarNumByProjectId(map);
		projectDistributionDetail.setWaitRecNum(waitRecNum);

		// 完成任务(当天完成任务)
		Integer completeTodayNum = tbProjectDistributionMapper.selectTodayCompleteNumByProjectId(map);
		projectDistributionDetail.setCompleteTodayNum(completeTodayNum);

		// 累积完成任务
		Integer completeNumSum = tbProjectDistributionMapper.selectSumCompleteNumByProjectId(map);
		projectDistributionDetail.setCompleteNumSum(completeNumSum);
	}

	/**
	 * @description 分配点击后的  页面字段信息获取 
	 * @author liangLin
	 * @date 2017年12月18日
	 * @param 
	 * @return
	*/
	@Override
	public TbProject getMsgByProjectId(Integer id) {
		return tbProjectMapper.selectPartDetailByPrimaryKey(id);
	}
	
	/**
	 * @description 判断  -目前分配任务 不得低于 今日已领取任务 
	 * @author liangLin
	 * @date 2017年12月20日
	 * @param 
	 * @return
	*/
	@Override
	public boolean IsHigherByTodayNum(Integer projectId, Integer num, byte projectType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);
		map.put("projectType", projectType);
		TbProjectDistribution tpd = tbProjectDistributionMapper.selectTodayalreadyRecNumsByProjectId(map);
		if (tpd != null && tpd.getAlreadyRecNum() >= num) {
			return false;
		}
		return true;
	}

	/**
	* @description 判断该项目 今日是否有分配任务
	* @date 2018年2月1日
	* @author shilvfei
	* @param 
	* @return
	 */
	@Override
	public boolean isHaveJob(Integer projectId, Integer shortType) {
		Map<String,Object> map = new HashMap<>();
		if(shortType==0){
			shortType=1;
		}else if(shortType==1){
			shortType=2;
		}else if(shortType==2){
			shortType=3;
		}
		map.put("projectId", projectId);
		map.put("taskType", shortType);
		Integer taskNum = tbProjectDistributionMapper.selectTodayCarNumByProjectId(map);//查询今日待领任务数
		//判断任务数
		if(taskNum != null && taskNum>0){
			return true;
		}
		return false;
	}
}
