package com.shenhesoft.logistics.business.project.manage.impl;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.business.helpPojo.AreaHelpPojo;
import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.mapper.TbProjectOperationLogMapper;
import com.shenhesoft.logistics.business.mapper.TbShortBargeMapper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderMapper;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrderExample;
import com.shenhesoft.logistics.business.pojo.log.TbProjectOperationLog;
import com.shenhesoft.logistics.business.pojo.log.TbProjectOperationLogExample;
import com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBarge;
import com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBargeExample;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrderExample;
import com.shenhesoft.logistics.business.project.manage.ProjectManagmentService;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.AreaCodeUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.finance.AdvanceCharge;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.mapper.AdvanceSettlementMapper;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.helpPojo.CustomerInfo;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbCustomerMapper;
import com.shenhesoft.logistics.manage.mapper.TbFreightYardMapper;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月18日
 */
@Service
public class ProjectManagmentServiceImpl implements ProjectManagmentService {

	@Autowired
	private TbProjectMapper projectMapper;

	@Autowired
	private TbShortBargeMapper shortBargeMapper;

	@Autowired
	private TbProjectOperationLogMapper logMapper;

	@Autowired
	private TbCustomerMapper customerMapper;

	@Autowired
	private TbTrainStationMapper stationMapper;

	@Autowired
	private TbBranchGroupMapper branchGroupMapper;

	@Autowired
	private AdvanceSettlementMapper advanceSettlementMapper;
	
	@Autowired
	private TbFreightYardMapper freightYardMapper;
	
	@Autowired
	private TbOrderMapper tbOrderMapper;
	
	@Autowired
	private TbTrainOrderMapper tbTrainOrderMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	/**
	 * @description 获取项目信息
	 * @date 2017年12月18日
	 * @author shilvfei
	 * @return
	 */
	@Override
	public DataGridResult getProjectManagment(Integer page, Integer limit, List<Byte> status, Integer uid,
			ProjectDetail projectDetail) {
		TbProjectExample projectExample = new TbProjectExample();
		Criteria criteria = projectExample.createCriteria();
		criteria.andStatusIn(status);// 状态
		// 分支机构id
		List<Integer> branchIds = branchGroupMapper.selectDotBranchIdsByUid(uid);
		if (branchIds == null || branchIds.size() == 0) {
			return new DataGridResult();
		}
		criteria.andBranchGroupIdIn(branchIds);
		projectCriteria(projectDetail, criteria);// 查询条件
		// 按照时间排序
		projectExample.setOrderByClause("create_date desc");
		PageHelper.startPage(page, limit);
		List<ProjectDetail> list = projectMapper.selectProjectDetailByExample(projectExample);
		for (ProjectDetail tbProject : list) {
			// 判断是否为短驳承运方式
			// 获取短驳承运方
			TbShortBargeExample example = new TbShortBargeExample();
			com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBargeExample.Criteria shortBargecriteria = example
					.createCriteria();
			shortBargecriteria.andProjectIdEqualTo(tbProject.getId());
			List<TbShortBarge> shortBargelist = shortBargeMapper.selectByExample(example);
			String shortBargeName = "";
			for (TbShortBarge tbShortBarge : shortBargelist) {
				shortBargeName = shortBargeName + tbShortBarge.getShortBargeName() + ";";
			}
			tbProject.setShortBargeCarrierName(shortBargeName);
			if (status.contains(Constants.PROJECT_STATUS_CANCLE)) {
				// 获取删除理由和删除时间 和删除人
				TbProjectOperationLogExample logExample = new TbProjectOperationLogExample();
				com.shenhesoft.logistics.business.pojo.log.TbProjectOperationLogExample.Criteria logCriteria = logExample
						.createCriteria();
				logExample.setOrderByClause("create_date desc");// 根据时间倒序
				logCriteria.andProjectIdEqualTo(tbProject.getId());
				logCriteria.andTypeEqualTo(Constants.PROJECT_LOG_DEL);// 获取删除日志
				List<TbProjectOperationLog> delLoglist = logMapper.selectByExample(logExample);
				tbProject.setOperationLogs(delLoglist);
				if(delLoglist!=null && delLoglist.size()!=0){
					tbProject.setDelUser(delLoglist.get(0).getOperatorName());
					tbProject.setDelDate(DateUtils.date2Str(delLoglist.get(0).getCreateDate(),DateUtils.date_sdf));
					tbProject.setDelReason(delLoglist.get(0).getContent());
				}
			}
			tbProject.setShortBarges(shortBargelist);
		}
		PageInfo<ProjectDetail> pageInfo = new PageInfo<ProjectDetail>(list);
		long total = pageInfo.getTotal(); // 获取总记录数
		return new DataGridResult(total, list, limit);
	}

	@Override
	public LogisticsResult save(ProjectDetail project, TbSystemUser user) {
		LogisticsResult valiteAreaIsAlike = valiteAreaIsAlike(project);
		if(valiteAreaIsAlike.getStatus()!=200){
			return valiteAreaIsAlike;
		}
		//创建时间
		Date date =new Date();
		project.setCreateDate(date);
		//删除标识 0 未删除
		project.setDeleteFlag(Constants.DELETE_FLAG_FALSE);
		//项目状态
		project.setStatus(Constants.PROJECT_STATUS_UNUSED);//未使用
		//是否可以发布任务
		project.setIsDistribution(Constants.PROJECT_IS_DISTRIBUTE_YES);
		List<TbShortBarge> shortBargelist=null;
		List<TbShortBarge> sendShortBargelist=null;
		LogisticsResult validShortBarge = LogisticsResult.ok(); 
		if(project.getTransportType()!=Constants.TRANSPORTTYPE_TRAIN){
			if(project.getTransportType()==Constants.TRANSPORTTYPE_TRUCK //汽运 接取 接取+火运
					|| 	project.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE
					||  project.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_TRAIN){
				project.setSendShortBargeCarrierMode(null);
			}
			
			if(project.getTransportType()==Constants.TRANSPORTTYPE_DELIVERY //送达 火运+送达
					|| 	project.getTransportType()==Constants.TRANSPORTTYPE_TRAIN_DELIVERY){
				project.setShortBargeCarrierMode(null);
			}
			
			if(project.getShortBargeCarrierMode()!=null){
				//前端传过来的字符串转成list对象
				String shortBargeCarriers = project.getShortBargeCarrierName();
				if(StringUtils.isBlank(shortBargeCarriers) || shortBargeCarriers.equals("[]")){
					return LogisticsResult.build(404, "请添加短驳承运方~") ;
				}
				shortBargelist = JsonUtils.jsonToList(shortBargeCarriers, TbShortBarge.class);
				if(shortBargelist!=null && shortBargelist.size()!=0) {
					validShortBarge = validShortBarge(shortBargelist);
				}
			}
			if(validShortBarge.getStatus()!=200){
				return validShortBarge;
			}
			project.setShortBargeCarrierName(null);
			//等于联运 或接取+送达 送达 接取+送达
			if(project.getSendShortBargeCarrierMode()!=null){//联运送达自选
				//前端传过来的字符串转成list对象
				String shortBargeCarriers = project.getSendShortBargeCarrierName();
				if(StringUtils.isBlank(shortBargeCarriers) || shortBargeCarriers.equals("[]")){
					return LogisticsResult.build(404, "请添加短驳承运方~") ;
				}
				sendShortBargelist = JsonUtils.jsonToList(shortBargeCarriers, TbShortBarge.class);
				if(sendShortBargelist!=null && sendShortBargelist.size()!=0) {
					validShortBarge = validShortBarge(sendShortBargelist);
				}
			}
			if(validShortBarge.getStatus()!=200){
				return validShortBarge;
			}
		}else{
			project.setShortBargeCarrierMode(null);
			project.setSendShortBargeCarrierMode(null);
		}
		
		//插入到数据库
		projectMapper.insert(project);
		
		TbBranchGroup branchGroup  = branchGroupMapper.selectByPrimaryKey(project.getBranchGroupId());
		
		BranchGroupLink branchGroupLink = new BranchGroupLink();
	    branchGroupLink.setId(AppUtils.randomUUID());
	    branchGroupLink.setRowId(project.getId().toString());
	    branchGroupLink.setTabName("tb_project");
	    branchGroupLink.setTabComment("项目表");
	    branchGroupLink.setSysOrgCode(branchGroup.getSysOrgCode());
	    branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		
		if(project.getTransportType()==Constants.TRANSPORTTYPE_TRUCK //汽运 接取 接取+火运 接取+送达 联运
		|| 	project.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE
		|| project.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_TRAIN
		|| project.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY
		|| project.getTransportType()==Constants.TRANSPORTTYPE_UNION){
			//判断短驳承运方是平台还是自选
			if(project.getShortBargeCarrierMode()!=null){//不为null
				saveShortBarge(project, shortBargelist,Constants.SHORT_BRAGE_TYPE_RECEIVE,project.getShortBargeCarrierMode(),user);
			}
			project.setShortBargeCarrierName(null);
		}
		
		if(project.getTransportType()==Constants.TRANSPORTTYPE_DELIVERY	//送达 火运 +送达 接取+送达 联运
				|| 	project.getTransportType()==Constants.TRANSPORTTYPE_TRAIN_DELIVERY
				|| project.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY
				|| project.getTransportType()==Constants.TRANSPORTTYPE_UNION){
				//等于联运 或接取+送达	
				if(project.getSendShortBargeCarrierMode()!=null){//联运送达自选
					saveShortBarge(project, sendShortBargelist,Constants.SHORT_BRAGE_TYPE_DELIVERY,project.getSendShortBargeCarrierMode(),user);
				}
		}
		//预付款结算表中插入记录
		Integer projectId = project.getId();
		TbProject p = projectMapper.selectByPrimaryKey(projectId);
		AdvanceCharge advanceCharge = new AdvanceCharge();
		advanceCharge.setProjectId(p.getId());
		advanceCharge.setProjectCode(p.getProjectCode());
		advanceCharge.setBranchId(p.getBranchGroupId());
		advanceCharge.setBranchName(p.getBranchGroupName());
		advanceCharge.setType((byte)2);
		advanceCharge.setStatus((byte)2);
		advanceCharge.setDeleteFlag((byte)0);
		advanceCharge.setAssessorDate(new Date());
		advanceSettlementMapper.addAdvanceCharge(advanceCharge);
		
		return LogisticsResult.ok(project.getId());
	}

	private void saveShortBarge(ProjectDetail project, List<TbShortBarge> list, Byte type, Byte mode,
			TbSystemUser user) {
		if (list != null && list.size() != 0) {
			for (TbShortBarge tbShortBarge : list) {
				tbShortBarge.setShortBargeType(Constants.TYPE_OPTIONAL);
				if (mode == Constants.TYPE_PLATFORM) {
					tbShortBarge.setShortBargeId(user.getCompanyId());// 该员工挂靠的公司id
					tbShortBarge.setShortBargeType(Constants.TYPE_PLATFORM);
				}
				tbShortBarge.setProjectId(project.getId());
				tbShortBarge.setCreateDate(new Date());
				// 短驳承运方类型
				if (project.getTransportType() == Constants.TRANSPORTTYPE_TRUCK) {
					tbShortBarge.setType(Constants.SHORT_BRAGE_TYPE_CARRUN);// 汽运
				}
				if (project.getTransportType() == Constants.TRANSPORTTYPE_DELIVERY
						|| project.getTransportType() == Constants.TRANSPORTTYPE_TRAIN_DELIVERY) {
					tbShortBarge.setType(Constants.SHORT_BRAGE_TYPE_DELIVERY);
				} else if (project.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE
						|| project.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE_TRAIN) {
					tbShortBarge.setType(Constants.SHORT_BRAGE_TYPE_RECEIVE);
				}
				if (project.getTransportType() == Constants.TRANSPORTTYPE_UNION) {
					tbShortBarge.setType(type);
				}
				if (project.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE_DELIVERY) {
					tbShortBarge.setType(type);
				}
				shortBargeMapper.insert(tbShortBarge);
			}
		}
	}

	@Override
	public LogisticsResult update(ProjectDetail project, TbSystemUser user) {
		// 创建时间
		Date date = new Date();
		project.setEditDate(date);

		// 通过id获取短驳承运方
		Integer id = project.getId();
		TbShortBargeExample shortBargeExample = new TbShortBargeExample();
		com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBargeExample.Criteria criteria = shortBargeExample
				.createCriteria();
		criteria.andProjectIdEqualTo(id);
		// 清空原有短驳承运方
		shortBargeMapper.deleteByExample(shortBargeExample);

		// 插入最新短驳承运方
		List<TbShortBarge> shortBargelist = null;
		List<TbShortBarge> sendShortBargelist = null;
		LogisticsResult validShortBarge = LogisticsResult.ok(); 
		if (project.getTransportType() != Constants.TRANSPORTTYPE_TRAIN) {
			//判断短驳承运方是平台还是自选
			if(project.getShortBargeCarrierMode()!=null){//自选
				//前端传过来的字符串转成list对象
				String shortBargeCarriers = project.getShortBargeCarrierName();
				shortBargelist = JsonUtils.jsonToList(shortBargeCarriers, TbShortBarge.class);
			   if(project.getShortBargeCarrierMode()==0) {//平台
				   if(shortBargelist!=null && shortBargelist.size()!=0) {
						 validShortBarge = validShortBarge(shortBargelist);
					}
			   }
			}
			if(validShortBarge.getStatus()!=200){
				return validShortBarge;
			}
			project.setShortBargeCarrierName(null);
			//等于联运 或接取+送达 送达 接取+送达
			if(project.getSendShortBargeCarrierMode()!=null){//联运送达自选
				//前端传过来的字符串转成list对象
				String shortBargeCarriers = project.getSendShortBargeCarrierName();
				sendShortBargelist = JsonUtils.jsonToList(shortBargeCarriers, TbShortBarge.class);
				if(project.getShortBargeCarrierMode()==0) {//平台
					   if(shortBargelist!=null && shortBargelist.size()!=0) {
							validShortBarge = validShortBarge(sendShortBargelist);
						}
				   }
			}
			if(validShortBarge.getStatus()!=200){
				return validShortBarge;
			}
			if(project.getTransportType()==Constants.TRANSPORTTYPE_TRUCK //汽运 接取 接取+火运
					|| 	project.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE
					||  project.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_TRAIN){
				project.setSendShortBargeCarrierMode(null);
			}
			
			if(project.getTransportType()==Constants.TRANSPORTTYPE_DELIVERY //送达 火运+送达
					|| 	project.getTransportType()==Constants.TRANSPORTTYPE_TRAIN_DELIVERY){
				project.setShortBargeCarrierMode(null);
			}
		}
		
		if(project.getTransportType()==Constants.TRANSPORTTYPE_TRUCK //汽运 接取 接取+火运 接取+送达 联运
			|| 	project.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE
			|| project.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_TRAIN
			|| project.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY
			|| project.getTransportType()==Constants.TRANSPORTTYPE_UNION){
				//判断短驳承运方是平台还是自选
				if(project.getShortBargeCarrierMode()!=null){//不为null
					saveShortBarge(project, shortBargelist,Constants.SHORT_BRAGE_TYPE_RECEIVE,project.getShortBargeCarrierMode(),user);
				}
				project.setShortBargeCarrierName(null);
			}
			
		if(project.getTransportType()==Constants.TRANSPORTTYPE_DELIVERY	//送达 火运 +送达 接取+送达 联运
			|| 	project.getTransportType()==Constants.TRANSPORTTYPE_TRAIN_DELIVERY
			|| project.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY
			|| project.getTransportType()==Constants.TRANSPORTTYPE_UNION){
			//等于联运 或接取+送达	
			if(project.getSendShortBargeCarrierMode()!=null){//联运送达自选
				saveShortBarge(project, sendShortBargelist,Constants.SHORT_BRAGE_TYPE_DELIVERY,project.getSendShortBargeCarrierMode(),user);
			}
		}
		projectMapper.updateByPrimaryKeySelective(project);
		return LogisticsResult.ok();
	}

	private LogisticsResult validShortBarge(List<TbShortBarge> shortBargelist) {
		for (TbShortBarge tbShortBarge : shortBargelist) {
			if(tbShortBarge.getTransportPrice()==null){
				return LogisticsResult.build(404, "短驳承运方运输单价不能为空");
			}
			if(tbShortBarge.getDeductionRate()==null){
				return LogisticsResult.build(404, "短驳承运方扣损比率不能为空");
			}
			if(tbShortBarge.getDeductionPrice()==null){
				return LogisticsResult.build(404, "短驳承运方扣损单价不能为空");
			}
			tbShortBarge.setShortBargeId(AppSession.getCurrentUser().getCompanyId());
		}
		return LogisticsResult.ok();
	}

	@Override
	public LogisticsResult delProject(Integer id, TbSystemUser user, String reason) {
		// 判断该项目是否正在使用
		// 判断该项目下是否有运单
		TbProject project = projectMapper.selectByPrimaryKey(id);
		// 判断该项目的状态是否正在使用
		if (project.getStatus() == Constants.PROJECT_STATUS_RUNNING
				|| project.getStatus() == Constants.PROJECT_STATUS_FINISH) {
			return LogisticsResult.build(404, "此项目正在运行或已完成,不允许删除~");
		}
		project.setDeleteFlag(Constants.DELETE_FLAG_TRUE);// 逻辑删除
		project.setStatus(Constants.PROJECT_STATUS_CANCLE);// 设置状态
		project.setIsDistribution(Constants.PROJECT_IS_DISTRIBUTE_NO);// 停止分配任务
		projectMapper.updateByPrimaryKeySelective(project);
		// 更新操作日志
		TbProjectOperationLog record = new TbProjectOperationLog();
		Date createDate = new Date();
		record.setCreateDate(createDate);
		// SimpleDateFormat dateFormat = new
		// SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		// record.setContent(user.getName()+"在"+dateFormat.format(createDate)+"删除编号为"+project.getProjectCode()+"的项目");
		record.setContent(reason);// 删除原因
		record.setOperatorId(user.getId());
		record.setOperatorName(user.getName());
		record.setProjectId(id);
		record.setProjectCode(project.getProjectCode());
		record.setType(Constants.PROJECT_LOG_DEL);
		logMapper.insert(record);
		
		//逻辑删除预付款项目信息
		advanceSettlementMapper.delAdvanceByProjectId(id);
		return LogisticsResult.ok();
	}

	@Override
	public LogisticsResult selectProject(Integer id) {
		ProjectDetail projectDetail = projectMapper.selectProjectDetailByPrimaryKey(id);
		if(projectDetail==null){
			return LogisticsResult.build(404, "此项目信息不存在");
		}
		projectDetail.setTransportTypeName(projectDetail.getTransportTypeName());
		projectDetail.setProjectTypeName(projectDetail.getProjectTypeName());
		// 发货企业
		Integer sendCargoCompanyId = projectDetail.getSendCargoCompanyId();
		CustomerInfo sendCargoCompany = customerMapper.selectCustomerInfoByCid(sendCargoCompanyId);
		String addressCode = sendCargoCompany.getAddressCode();
		sendCargoCompany.setAddressCode(AreaCodeUtils.getAreaCode(addressCode));
		projectDetail.setSendCargoCompanyAddress(AreaCodeUtils.getAreaCode(addressCode));
		projectDetail.setSendCargoCompany(sendCargoCompany);
		// 收货企业
		Integer receiveCargoCompanyId = projectDetail.getReceiveCargoCompanyId();
		CustomerInfo receiveCargoCompany = customerMapper.selectCustomerInfoByCid(receiveCargoCompanyId);
		String adressCode = receiveCargoCompany.getAddressCode();
		receiveCargoCompany.setAddressCode(AreaCodeUtils.getAreaCode(adressCode));
		projectDetail.setReceiveCargoCompany(receiveCargoCompany);
		projectDetail.setReceiveCargoCompanyAddress(AreaCodeUtils.getAreaCode(adressCode));
		
		String projectFlow = "";
		
		// 获取短驳承运方
		TbShortBargeExample example = new TbShortBargeExample();
		com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBargeExample.Criteria criteria = example
				.createCriteria();
		criteria.andProjectIdEqualTo(projectDetail.getId());
		List<TbShortBarge> shortBargelist = shortBargeMapper.selectByExample(example);
		projectDetail.setShortBarges(shortBargelist);
		
		// 判断项目类型
		if (projectDetail.getTransportType() == Constants.TRANSPORTTYPE_TRUCK
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE_DELIVERY
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_UNION
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE_TRAIN) {
			// 发货单位
			Integer sendCargoUnitId = projectDetail.getSendCargoUnitId();
			if(sendCargoUnitId!=null){
				CustomerInfo sendCargoUnit = customerMapper.selectCustomerInfoByCid(sendCargoUnitId);
				String sendCargoUnitAddressCode = sendCargoUnit.getAddressCode();
				sendCargoUnit.setAddressCode(AreaCodeUtils.getAreaCode(sendCargoUnitAddressCode));
				projectDetail.setSendCargoUnit(sendCargoUnit);
				projectFlow=sendCargoUnit.getCompanyName()+" - ";
			}
		}
		if (projectDetail.getTransportType() == Constants.TRANSPORTTYPE_TRUCK
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_DELIVERY
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE_DELIVERY
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_UNION
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_TRAIN_DELIVERY) {
			// 收货单位
			Integer  receivingDepartmentId= projectDetail.getReceivingDepartmentId();
			if(receivingDepartmentId!=null){
				CustomerInfo receivingDepartment = customerMapper.selectCustomerInfoByCid(receivingDepartmentId);
				String receivingDepartmentAddressCode = receivingDepartment.getAddressCode();
				receivingDepartment.setAddressCode(AreaCodeUtils.getAreaCode(receivingDepartmentAddressCode));
				projectDetail.setReceivingDepartment(receivingDepartment);
				projectFlow=projectFlow+receivingDepartment.getCompanyName()+" - ";
			}
		}
		if (projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE_TRAIN
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE_DELIVERY
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_UNION) {// 接取
			//收货中心站
			Integer receiveCenterCargoSiteId = projectDetail.getReceiveCenterCargoSiteId();
			if(receiveCenterCargoSiteId!=null){
				TbTrainStation tbTrainStation = stationMapper.selectByPrimaryKey(receiveCenterCargoSiteId);
				projectDetail.setReceiveCenterCargoSiteName(tbTrainStation.getStationName());
			}
			
			//收货站点
			Integer receiveCargoSiteId = projectDetail.getReceiveCargoSiteId();
			if(receiveCargoSiteId!=null){
				TbTrainStation trainStation = stationMapper.selectByPrimaryKey(receiveCargoSiteId);
				projectDetail.setReceiveTrainStation(trainStation);
				projectFlow=projectFlow+trainStation.getStationName()+" - ";
			}
			//收货货场
			Integer receiveCargoSiteFreightYardId = projectDetail.getReceiveCargoSiteFreightYardId();
			if(receiveCargoSiteFreightYardId!=null){
				projectDetail.setReceiveCargoSiteFreightYard(freightYardMapper.selectByPrimaryKey(receiveCargoSiteFreightYardId));
			}
		}
		
		if (projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE_TRAIN
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_UNION
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_TRAIN_DELIVERY
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_TRAIN
				) {
			//始发货场
			String beginSiteFreightYard = projectDetail.getBeginSiteFreightYard();
			if(StringUtils.isNotBlank(beginSiteFreightYard)){
				TbFreightYard tbFreightYard = freightYardMapper.selectByPrimaryKey(Integer.valueOf(beginSiteFreightYard));
				projectDetail.setBeginSiteFreightYardName(tbFreightYard.getName());
			}
			projectFlow=projectFlow+projectDetail.getBeginSiteName()+" - "+projectDetail.getEndCenterSiteName()+" - ";
			//到达货场
			String endSiteFreightYard = projectDetail.getEndSiteFreightYard();
			if(StringUtils.isNotBlank(endSiteFreightYard)){
				TbFreightYard tbFreightYard = freightYardMapper.selectByPrimaryKey(Integer.valueOf(endSiteFreightYard));
				projectDetail.setEndSiteFreightYardName(tbFreightYard.getName());
			}
		}
		
		if (projectDetail.getTransportType() == Constants.TRANSPORTTYPE_DELIVERY
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE_DELIVERY
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_UNION
				|| projectDetail.getTransportType() == Constants.TRANSPORTTYPE_TRAIN_DELIVERY) {// 送达
			//接取中心站
			 Integer forwardingCenterSiteId = projectDetail.getForwardingCenterSiteId();
			if(forwardingCenterSiteId!=null){
				TbTrainStation tbTrainStation = stationMapper.selectByPrimaryKey(forwardingCenterSiteId);
				projectDetail.setForwardingCenterSiteName(tbTrainStation.getStationName());
			}
			
			//接取站点
			Integer forwardingSiteId = projectDetail.getForwardingSiteId();
			if(forwardingSiteId!=null){
				TbTrainStation trainStation = stationMapper.selectByPrimaryKey(forwardingSiteId);
				projectDetail.setSendTrainStation(trainStation);
				projectFlow=projectFlow+trainStation.getStationName()+" - ";
			}
			
			//接取货场
			Integer forwardingSiteFreightYardId = projectDetail.getForwardingSiteFreightYardId();
			if(forwardingSiteFreightYardId!=null){
				projectDetail.setForwardingSiteFreightYard(freightYardMapper.selectByPrimaryKey(forwardingSiteFreightYardId));
			}
		}
		
		projectDetail.setProjectFlow(projectFlow.substring(0, projectFlow.length()-2));
		
		if (projectDetail.getStatus() == Constants.PROJECT_STATUS_CANCLE) {
			// 获取删除理由和删除时间 和删除人
			TbProjectOperationLogExample logExample = new TbProjectOperationLogExample();
			com.shenhesoft.logistics.business.pojo.log.TbProjectOperationLogExample.Criteria logCriteria = logExample
					.createCriteria();
			logExample.setOrderByClause("create_date desc");// 根据时间倒序
			logCriteria.andProjectIdEqualTo(projectDetail.getId());
			logCriteria.andTypeEqualTo(Constants.PROJECT_LOG_DEL);// 获取删除日志
			List<TbProjectOperationLog> delLoglist = logMapper.selectByExample(logExample);
			projectDetail.setOperationLogs(delLoglist);
		}
		return LogisticsResult.ok(projectDetail);
	}

	@Override
	public LogisticsResult completeProject(Integer id, TbSystemUser user) {
		//判断是否还有未完成的运单
		TbOrderExample example=new TbOrderExample();
		com.shenhesoft.logistics.business.pojo.TbOrder.TbOrderExample.Criteria orderCriteria = example.createCriteria();
		orderCriteria.andProjectIdEqualTo(id);
		orderCriteria.andStatusNotEqualTo(Constants.SMS_POINT_ORDER_DONE);
		orderCriteria.andIsCancelEqualTo((byte)0);
		orderCriteria.andDeleteFlagEqualTo((byte)0);
		List<TbOrder> list = tbOrderMapper.selectByExample(example);
		if(list!=null && list.size()!=0){
			return LogisticsResult.build(400, "该项目还存在未完成的运单信息");
		}
		
		TbTrainOrderExample trainOrderExample=new TbTrainOrderExample();
		com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrderExample.Criteria trainOrderCriteria = trainOrderExample.createCriteria();
		trainOrderCriteria.andProjectIdEqualTo(id);
		trainOrderCriteria.andDeleteFlagEqualTo((byte)0);
		trainOrderCriteria.andIsExceptionNotEqualTo((byte)1);
		List<TbTrainOrder> trainOrders = tbTrainOrderMapper.selectByExample(trainOrderExample);
		if(trainOrders!=null && trainOrders.size()!=0){
			return LogisticsResult.build(400, "该项目还存在未完成的运单信息");
		}
		
		TbProject project = projectMapper.selectByPrimaryKey(id);
		Date createDate = new Date();
		project.setStatus(Constants.PROJECT_STATUS_FINISH);// 设置状态
		project.setIsDistribution(Constants.PROJECT_IS_DISTRIBUTE_NO);// 停止分配任务
		project.setFinishDate(createDate);// 完成时间
		projectMapper.updateByPrimaryKeySelective(project);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		String content = user.getName() + "在" + dateFormat.format(createDate) + "设置编号为" + project.getProjectCode()
				+ "的项目为完成状态";
		saveLog(user, project, content, Constants.PROJECT_LOG_UPDATE);
		return LogisticsResult.ok();
	}

	private void saveLog(TbSystemUser user, TbProject project, String content, Byte type) {
		// 更新操作日志
		Date date = new Date();
		TbProjectOperationLog record = new TbProjectOperationLog();
		record.setCreateDate(date);
		record.setContent(content);// 删除原因
		record.setOperatorId(user.getId());
		record.setOperatorName(user.getName());
		record.setProjectId(user.getId());
		record.setProjectCode(project.getProjectCode());
		record.setType(type);
		logMapper.insert(record);
	}

	@Override
	public LogisticsResult restoreProject(Integer id, TbSystemUser user) {
		// 判断该项目是否存在
		TbProject project = projectMapper.selectByPrimaryKey(id);
		if (project == null) {
			return LogisticsResult.build(404, "不存在此项目信息");
		}
		if (project.getStatus() == Constants.PROJECT_STATUS_RUNNING) {
			return LogisticsResult.build(404, "此项目信息未删除");
		}
		// 判断该项目是否删除
		if (project.getDeleteFlag() == Constants.DELETE_FLAG_FALSE
				&& project.getStatus() == Constants.PROJECT_STATUS_RUNNING) {
			return LogisticsResult.build(404, "此项目信息未删除");
		}

		if (project.getStatus() == Constants.PROJECT_STATUS_FINISH) {// 已完成的项目的信息
			// 不确定 是否是复制一条 还是还原一条..
			// 现在取的是复制一条
			TbProject newProject = project;
			newProject.setId(null);// 清空项目id
			newProject.setProjectCode(Constants.PROJECT_CODE + DateUtils.getCurrentTime());

			// 创建时间
			Date date = new Date();
			newProject.setCreateDate(date);
			// 删除标识 0 未删除
			newProject.setDeleteFlag(Constants.DELETE_FLAG_FALSE);
			// 项目状态
			newProject.setStatus(Constants.PROJECT_STATUS_UNUSED);// 未使用
			// 是否可以发布任务
			newProject.setIsDistribution(Constants.PROJECT_IS_DISTRIBUTE_YES);

			int row = projectMapper.insert(newProject);

			if (row != 0) {
				Integer projectId = newProject.getId();
				boolean flag = false;
				// 获取短驳承运方
				TbShortBargeExample example = new TbShortBargeExample();
				com.shenhesoft.logistics.business.pojo.shortBarge.TbShortBargeExample.Criteria criteria = example
						.createCriteria();
				criteria.andProjectIdEqualTo(id);
				List<TbShortBarge> shortBargelist = shortBargeMapper.selectByExample(example);
				for (TbShortBarge tbShortBarge : shortBargelist) {
					tbShortBarge.setId(null);
					tbShortBarge.setProjectId(projectId);
					int shortBargeRow = shortBargeMapper.insert(tbShortBarge);// 插入到数据库
					if (shortBargeRow == 0) {// 如果插入失败就删除
						flag = true;
						break;
					}
				}
				if (flag) {
					projectMapper.deleteByPrimaryKey(projectId);
					return LogisticsResult.build(400, "还原失败！");
				}
			}
			return LogisticsResult.ok();
		}

		Date nowDate = new Date();
		if (project.getDeleteFlag() == Constants.DELETE_FLAG_TRUE) {
			// 获取该项目的删除时间
			TbProjectOperationLogExample logExample = new TbProjectOperationLogExample();
			com.shenhesoft.logistics.business.pojo.log.TbProjectOperationLogExample.Criteria logCriteria = logExample
					.createCriteria();
			logExample.setOrderByClause("create_date desc");// 根据时间倒序
			logCriteria.andProjectIdEqualTo(project.getId());
			logCriteria.andTypeEqualTo(Constants.PROJECT_LOG_DEL);// 获取删除日志
			List<TbProjectOperationLog> delLoglist = logMapper.selectByExample(logExample);
			if (delLoglist == null || delLoglist.size() == 0) {
				return LogisticsResult.build(404, "此项目信息有异常！请与管理员联系");
			}
			TbProjectOperationLog operationLog = delLoglist.get(0);
			Date delDate = operationLog.getCreateDate();
			long time = nowDate.getTime() - delDate.getTime();
			double result = time * 1.0 / (1000 * 60 * 60);
			if (result > 24) {
				return LogisticsResult.build(404, "此项目已过期,无法恢复！请与管理员联系");
			}
		}
		project.setDeleteFlag(Constants.DELETE_FLAG_FALSE);// 逻辑未删除
		project.setStatus(Constants.PROJECT_STATUS_UNUSED);// 设置成未使用状态
		project.setIsDistribution(Constants.PROJECT_IS_DISTRIBUTE_YES);// 可以分配任务
		projectMapper.updateByPrimaryKeySelective(project);
		// 更新操作日志
		TbProjectOperationLog record = new TbProjectOperationLog();
		record.setOperatorId(user.getId());
		record.setOperatorName(user.getName());
		record.setProjectId(project.getId());
		record.setProjectCode(project.getProjectCode());
		record.setType(Constants.PROJECT_LOG_RESTORE);
		record.setCreateDate(nowDate);
		logMapper.insert(record);
		return LogisticsResult.ok();
	}

	/**
	 * @description 项目的查询条件
	 * @date 2018年1月12日
	 * @author shilvfei
	 * @param
	 * @return
	 */
	private void projectCriteria(ProjectDetail projectDetail, Criteria criteria) {
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		criteria.andTabNameEqualTo("tb_project");
		
		if (projectDetail == null) {
			return;
		}
		// 条件查询
		if (StringUtils.isNotBlank(projectDetail.getProjectCode())) {// 项目编号
			criteria.andProjectCodeLike(projectDetail.getProjectCode());
		}
		if (projectDetail.getBranchGroupId() != null) {// 分支机构
			criteria.andBranchGroupIdEqualTo(projectDetail.getBranchGroupId());
		}
		if (projectDetail.getProjectType() != null) {// 项目类型
			criteria.andProjectTypeEqualTo(projectDetail.getProjectType());
		}
		if (projectDetail.getTransportType() != null) {// 联运模式
			criteria.andTransportTypeEqualTo(projectDetail.getTransportType());
		}
		if (projectDetail.getCargoId() != null) {// 货物id
			criteria.andCargoIdEqualTo(projectDetail.getCargoId());
		}
		if (StringUtils.isNotBlank(projectDetail.getBeginDate())
				&& StringUtils.isNotBlank(projectDetail.getEndDate())) {// 创建时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
			Date beginDate = DateUtils.str2Date(projectDetail.getBeginDate(), sdf);
			Date endDate = DateUtils.str2Date(projectDetail.getEndDate(), sdf);
			criteria.andCreateDateGreaterThanOrEqualTo(beginDate);
			criteria.andCreateDateLessThanOrEqualTo(endDate);
		}

		if (StringUtils.isNotBlank(projectDetail.getSendCargoCompanyName())) {// 发货企业
			criteria.andSendCargoCompanyNameLike(projectDetail.getSendCargoCompanyName());
		}
		if (StringUtils.isNotBlank(projectDetail.getReceiveCargoCompanyName())) {// 收货企业
			criteria.andReceiveCargoCompanyNameLike(projectDetail.getReceiveCargoCompanyName());
		}
	}

	@Override
	public List<TbProject> getProjectByFinance(Map<String, Object> map) {
		return projectMapper.getAllProjectByFince(map);
	}

	/**
	 * 校验发运地是否相同
	 * 
	 */
	public LogisticsResult valiteAreaIsAlike(ProjectDetail projectDetail) {
		// 获取branchId
		Integer branchGroupId = projectDetail.getBranchGroupId();// 网点分支id

		if (branchGroupId == null) {
			return LogisticsResult.build(404);
		}

		TbBranchGroup branchGroup = branchGroupMapper.selectByPrimaryKey(branchGroupId);
		if (branchGroup == null) {
			return LogisticsResult.build(404, "您选择网点分支不存在!");
		}
		String relationBeginLocation = branchGroup.getRelationBeginLocation();
		if (StringUtils.isBlank(relationBeginLocation)) {
			return LogisticsResult.build(404, "您选择的网点分支暂未关联发运地!");
		}

		List<AreaHelpPojo> relationBeginLocations = JsonUtils.jsonToList(relationBeginLocation, AreaHelpPojo.class);

		if (relationBeginLocations == null || relationBeginLocations.size() == 0) {
			return LogisticsResult.build(404, "您选择的网点分支暂未关联发运地!");
		}
		
		int size = relationBeginLocations.size();
		for (AreaHelpPojo area : relationBeginLocations) {
			// 一个一个的去比较
			CustomerInfo sendCargoCompany = customerMapper.selectCustomerInfoByCid(projectDetail.getSendCargoCompanyId());

			if (sendCargoCompany == null ) {
				return LogisticsResult.build(404, "您选择的发货企业暂未关联发运地!");
			}
			List<AreaHelpPojo> sendCargoCompanyRelationBeginLocations = JsonUtils.jsonToList(sendCargoCompany.getRelationBeginLocation(), AreaHelpPojo.class);

			if (sendCargoCompanyRelationBeginLocations == null || sendCargoCompanyRelationBeginLocations.size() == 0) {
				return LogisticsResult.build(404, "您选择的发货企业暂未关联发运地!");
			}
			//判断发货企业的发运地是否包含区域
			if(!sendCargoCompanyRelationBeginLocations.contains(area)){
				continue;
			}
			
			//收货企业
			CustomerInfo receiveCargoCompany = customerMapper.selectCustomerInfoByCid(projectDetail.getReceiveCargoCompanyId());

			if (receiveCargoCompany == null ) {
				return LogisticsResult.build(404, "您选择的收货企业暂未关联发运地!");
			}
			List<AreaHelpPojo> receiveCargoCompanyRelationBeginLocations = JsonUtils.jsonToList(receiveCargoCompany.getRelationBeginLocation(), AreaHelpPojo.class);

			if (receiveCargoCompanyRelationBeginLocations == null || receiveCargoCompanyRelationBeginLocations.size() == 0) {
				return LogisticsResult.build(404, "您选择的收货企业暂未关联发运地!");
			}

			if (!receiveCargoCompanyRelationBeginLocations.contains(area)) {
				continue;
			}

			// 汽运 0 -- 发货单位 与收货单位地址是否一致
			if (projectDetail.getTransportType() == Constants.TRANSPORTTYPE_TRUCK) {
				// 发货单位
				CustomerInfo sendUnitCompany = customerMapper.selectCustomerInfoByCid(projectDetail.getSendCargoUnitId());

				if (sendUnitCompany == null ) {
					return LogisticsResult.build(404, "您选择的发货企业暂未关联发运地!");
				}
				List<AreaHelpPojo> sendUnitCompanyRelationBeginLocations = JsonUtils.jsonToList(sendUnitCompany.getRelationBeginLocation(), AreaHelpPojo.class);

				if (sendUnitCompanyRelationBeginLocations == null || sendUnitCompanyRelationBeginLocations.size() == 0) {
					return LogisticsResult.build(404, "您选择的发货企业暂未关联发运地!");
				}
				//判断发货企业的发运地是否包含区域
				if(!sendUnitCompanyRelationBeginLocations.contains(area)){
					continue;
				}
				
				// 收货单位
				CustomerInfo receiveUnitCompany = customerMapper.selectCustomerInfoByCid(projectDetail.getReceivingDepartmentId());

				if (receiveUnitCompany == null ) {
					return LogisticsResult.build(404, "您选择的收货企业暂未关联发运地!");
				}
				List<AreaHelpPojo> receiveUnitCompanyRelationBeginLocations = JsonUtils.jsonToList(receiveUnitCompany.getRelationBeginLocation(), AreaHelpPojo.class);

				if (receiveUnitCompanyRelationBeginLocations == null || receiveUnitCompanyRelationBeginLocations.size() == 0) {
					return LogisticsResult.build(404, "您选择的收货企业暂未关联发运地!");
				}

				if (!receiveUnitCompanyRelationBeginLocations.contains(area)) {
					continue;
				}
				
			}
			Map<String, Object> map = new HashMap<>();
			if (projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE || // 接取
					projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE_TRAIN || // 接取+火运
					projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE_DELIVERY || // 接取+送达
					projectDetail.getTransportType() == Constants.TRANSPORTTYPE_UNION // 联运
			) {
				// 发货单位
				CustomerInfo sendUnitCompany = customerMapper.selectCustomerInfoByCid(projectDetail.getSendCargoUnitId());

				if (sendUnitCompany == null ) {
					return LogisticsResult.build(404, "您选择的发货企业暂未关联发运地!");
				}
				List<AreaHelpPojo> sendUnitCompanyRelationBeginLocations = JsonUtils.jsonToList(sendUnitCompany.getRelationBeginLocation(), AreaHelpPojo.class);

				if (sendUnitCompanyRelationBeginLocations == null || sendUnitCompanyRelationBeginLocations.size() == 0) {
					return LogisticsResult.build(404, "您选择的发货企业暂未关联发运地!");
				}
				//判断发货企业的发运地是否包含区域
				if(!sendUnitCompanyRelationBeginLocations.contains(area)){
					continue;
				}
				
				// 到达中心站--到达站点
				TbTrainStation trainStation = stationMapper.selectByPrimaryKey(projectDetail.getReceiveCargoSiteId());
				
				if (trainStation == null ) {
					return LogisticsResult.build(404, "您选择的到达中心站暂未关联发运地!");
				}
				List<AreaHelpPojo> trainStationRelationBeginLocations = JsonUtils.jsonToList(trainStation.getRelationPlace(), AreaHelpPojo.class);
				
				if (trainStationRelationBeginLocations == null || trainStationRelationBeginLocations.size() == 0) {
					return LogisticsResult.build(404, "您选择的到达中心站暂未关联发运地!");
				}
				//判断到达中心站的发运地是否包含此区域
				if(!trainStationRelationBeginLocations.contains(area)){
					continue;
				}
			}

			if (projectDetail.getTransportType() == Constants.TRANSPORTTYPE_TRAIN || // 火运
					projectDetail.getTransportType() == Constants.TRANSPORTTYPE_TRAIN_DELIVERY || // 火运+送达
					projectDetail.getTransportType() == Constants.TRANSPORTTYPE_UNION // 联运
			) {
				// 始发站点
				TbTrainStation beginStation = stationMapper.selectByPrimaryKey(projectDetail.getBeginSiteId());
				
				if (beginStation == null ) {
					return LogisticsResult.build(404, "您选择的始发站点暂未关联发运地!");
				}
				List<AreaHelpPojo> beginStationRelationBeginLocations = JsonUtils.jsonToList(beginStation.getRelationPlace(), AreaHelpPojo.class);
				
				if (beginStationRelationBeginLocations == null || beginStationRelationBeginLocations.size() == 0) {
					return LogisticsResult.build(404, "您选择的始发站点暂未关联发运地!");
				}
				//判断到达中心站的发运地是否包含此区域
				if(!beginStationRelationBeginLocations.contains(area)){
					continue;
				}
				
				
				// 到达站点
				TbTrainStation endStation = stationMapper.selectByPrimaryKey(projectDetail.getEndSiteId());
				
				if (endStation == null ) {
					return LogisticsResult.build(404, "您选择的到达站点暂未关联发运地!");
				}
				List<AreaHelpPojo> endStationRelationBeginLocations = JsonUtils.jsonToList(endStation.getRelationPlace(), AreaHelpPojo.class);
				
				if (endStationRelationBeginLocations == null || endStationRelationBeginLocations.size() == 0) {
					return LogisticsResult.build(404, "您选择的到达站点暂未关联发运地!");
				}
				//判断到达中心站的发运地是否包含此区域
				if(!endStationRelationBeginLocations.contains(area)){
					continue;
				}
			}

			if (projectDetail.getTransportType() == Constants.TRANSPORTTYPE_DELIVERY || // 送达
					projectDetail.getTransportType() == Constants.TRANSPORTTYPE_TRAIN_DELIVERY || // 火运+送达
					projectDetail.getTransportType() == Constants.TRANSPORTTYPE_RECEIVE_DELIVERY || // 接取+送达
					projectDetail.getTransportType() == Constants.TRANSPORTTYPE_UNION // 联运
			) {
				// 发货中心站 ---  取货站点
				TbTrainStation trainStation = stationMapper.selectByPrimaryKey(projectDetail.getForwardingSiteId());
				
				if (trainStation == null ) {
					return LogisticsResult.build(404, "您选择的发货中心站下的取货站点暂未关联发运地!");
				}
				List<AreaHelpPojo> trainStationRelationBeginLocations = JsonUtils.jsonToList(trainStation.getRelationPlace(), AreaHelpPojo.class);
				
				if (trainStationRelationBeginLocations == null || trainStationRelationBeginLocations.size() == 0) {
					return LogisticsResult.build(404, "您选择的发货中心站下的取货站点暂未关联发运地!");
				}
				//判断到达中心站的发运地是否包含此区域
				if(!trainStationRelationBeginLocations.contains(area)){
					continue;
				}
				
				// 收货单位
				CustomerInfo receiveUnitCompany = customerMapper.selectCustomerInfoByCid(projectDetail.getReceivingDepartmentId());

				if (receiveUnitCompany == null ) {
					return LogisticsResult.build(404, "您选择的收货企业暂未关联发运地!");
				}
				List<AreaHelpPojo> receiveUnitCompanyRelationBeginLocations = JsonUtils.jsonToList(receiveUnitCompany.getRelationBeginLocation(), AreaHelpPojo.class);

				if (receiveUnitCompanyRelationBeginLocations == null || receiveUnitCompanyRelationBeginLocations.size() == 0) {
					return LogisticsResult.build(404, "您选择的收货企业暂未关联发运地!");
				}

				if (!receiveUnitCompanyRelationBeginLocations.contains(area)) {
					continue;
				}
			}
			size--;
		}
		if(size!=relationBeginLocations.size()){
			return LogisticsResult.ok();
		}
		return LogisticsResult.build(404,"网点分支与客户或者站点的发运地不一致!");
	}

}
