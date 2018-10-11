package com.shenhesoft.logistics.business.project.operation.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.business.helpPojo.CargoLocationPlan;
import com.shenhesoft.logistics.business.helpPojo.ProjectOperationDetail;
import com.shenhesoft.logistics.business.mapper.ProjectCheck2Mapper;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.mapper.TbStockMapper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderMapper;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
import com.shenhesoft.logistics.business.project.operation.ProjectOperationService;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.mapper.TbCargoLocationMapper;
import com.shenhesoft.logistics.manage.mapper.TbFreightYardMapper;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月25日
 */
@Service
public class ProjectOperationServiceImpl implements ProjectOperationService{

	@Autowired
	private TbProjectMapper projectMapper;
	
	@Autowired
	private TbOrderMapper tbOrderMapper;
	
	@Autowired
	private TbTrainOrderMapper trainOrderMapper;

	@Autowired
	private TbStockMapper stockMapper;
	
	@Autowired
	private TbFreightYardMapper freightYardMapper;
	
	@Autowired
	private TbCargoLocationMapper cargoLocationMapper;
	
	@Autowired
	private ProjectCheck2Mapper projectCheck2Mapper;
	
	@Override
	public DataGridResult listProjectOperationByPage(Integer page, Integer limit,Integer uid,TbProject project){
		TbProjectExample example=new TbProjectExample();
		example.setOrderByClause("create_date desc");
    	com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria criteria = example.createCriteria();
    	criteria.andStatusNotEqualTo(Constants.PROJECT_STATUS_CANCLE);//未取消
    	//criteria.andStatusNotEqualTo(Constants.PROJECT_STATUS_UNUSED);//未删除
    	//criteria.andStatusNotEqualTo(Constants.PROJECT_STATUS_FINISH);//已完成
    	projectCriteria(project, criteria);
    	PageHelper.startPage(page, limit);
		List<ProjectOperationDetail> projectList = listProjectOperation(example);
		PageInfo<ProjectOperationDetail> pageInfo = new PageInfo<ProjectOperationDetail>(projectList);
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, projectList, limit);
	}
	

	private List<ProjectOperationDetail> listProjectOperation(TbProjectExample example) {
		List<ProjectOperationDetail> projectList = projectMapper.selectProjectOperationDetail(example);
		Map<String, Object> map = new HashMap<>();
		for (ProjectOperationDetail projectOperationDetail : projectList) {
			//根据类型查询运单
			Byte transportType = projectOperationDetail.getTransportType();
			Integer id = projectOperationDetail.getId();
			BigDecimal onwayDecimal = new BigDecimal(0) ;
			//0 汽运  1 接取 4 接取+火运 6 联运 7 接取+送达
			if(transportType==Constants.TRANSPORTTYPE_TRUCK || transportType==Constants.TRANSPORTTYPE_RECEIVE
				 || transportType==Constants.TRANSPORTTYPE_RECEIVE_TRAIN ||
			transportType==Constants.TRANSPORTTYPE_UNION || transportType==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY){
				List<Byte> status1 = new ArrayList<>();
				status1.add(Constants.SMS_POINT_ON_ROAD);
				status1.add(Constants.SMS_POINT_LOCATION_GUIDE);
				status1.add(Constants.SMS_POINT_WAIT_RETURN);
				
				//等待计费下的状态
				List<Byte> status2 = new ArrayList<>();
				status2.add(Constants.SMS_POINT_WAIT_RETURN);
				status2.add(Constants.SMS_POINT_WAIT_CHARGE);//等待计费
				status2.add(Constants.SMS_POINT_ORDER_DONE);//已完成
				
				//在途运载状态下的车次
				Byte type=0;//接取
				if(transportType==Constants.TRANSPORTTYPE_TRUCK){
					type=2;//汽运
				}
			    map.put("stepSelectCode", type);
			    map.put("status", status1);
			    map.put("id", id);
			    map.put("receipterDate", null);
			    
				onwayDecimal = tbOrderMapper.countSendGrossByProjectId(map) == null ? new BigDecimal(0) : tbOrderMapper.countSendGrossByProjectId(map) ;
				//设置在途运输吨位
				projectOperationDetail.setRunningCargoWeight(onwayDecimal);
				
				//6:等待确认         7:已完成
				status1.add(Constants.SMS_POINT_WAIT_CHARGE);
				status1.add(Constants.SMS_POINT_ORDER_DONE);
				
				//提货
				map.put("status", status1);
				map.put("receipterDate",0);
				//接取提货车次
				Integer receiveGetCargoCarNum = tbOrderMapper.countCarCountByProjectId(map);
				projectOperationDetail.setReceiveGetCargoCarNum(receiveGetCargoCarNum);
				//接取提货吨位
				BigDecimal receiveGetCargoWeight = tbOrderMapper.countSendGrossByProjectId(map);
				projectOperationDetail.setReceiveGetCargoWeight(receiveGetCargoWeight);
				
				//到货
				map.put("status", status2);
				map.put("receipterDate",1);
				//接取到货车次
				Integer arriveCarCount = tbOrderMapper.countCarCountByProjectId(map);
				projectOperationDetail.setReceiveArriveCargoCarNum(arriveCarCount);
				//接取到货吨位
				BigDecimal receiveArriveCargoWeight = tbOrderMapper.countReceiptGrossByProjectId(map);
				projectOperationDetail.setReceiveArriveCargoWeight(receiveArriveCargoWeight);
				
				//运输完成吨位
				if(transportType==Constants.TRANSPORTTYPE_TRUCK || transportType==Constants.TRANSPORTTYPE_RECEIVE){//汽运 接取
					projectOperationDetail.setFinishCargoWeight(receiveArriveCargoWeight);
				}
			}
			//  火运+送达 //送达
			if(transportType==Constants.TRANSPORTTYPE_DELIVERY || transportType==Constants.TRANSPORTTYPE_TRAIN_DELIVERY
				||transportType==Constants.TRANSPORTTYPE_UNION || transportType==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY	){
				List<Byte> status1 = new ArrayList<>();
				status1.add(Constants.SMS_POINT_ON_ROAD);
				status1.add(Constants.SMS_POINT_LOCATION_GUIDE);
				status1.add(Constants.SMS_POINT_WAIT_RETURN);
				
				//等待计费下的状态
				List<Byte> status2 = new ArrayList<>();
				status2.add(Constants.SMS_POINT_WAIT_RETURN);
				status2.add(Constants.SMS_POINT_WAIT_CHARGE);//等待计费
				status2.add(Constants.SMS_POINT_ORDER_DONE);//已完成
				
				//获取送达的运单
				Byte type=1;
				map.put("stepSelectCode", type);
			    map.put("status", status1);
			    map.put("id", id);
			    map.put("receipterDate",null);
				//把3和6的都查出来 
				 onwayDecimal = onwayDecimal.add(tbOrderMapper.countSendGrossByProjectId(map)==null ? new BigDecimal(0):tbOrderMapper.countSendGrossByProjectId(map));
				//设置在途运输吨位
				projectOperationDetail.setRunningCargoWeight(onwayDecimal);
				
				//6:等待确认         7:已完成
				status1.add(Constants.SMS_POINT_WAIT_CHARGE);
				status1.add(Constants.SMS_POINT_ORDER_DONE);
				
				//提货
				map.put("status", status1);
				map.put("receipterDate",0);
				//送达提货车次
				Integer sendGetCargoCarNum = tbOrderMapper.countCarCountByProjectId(map);
				projectOperationDetail.setSendGetCargoCarNum(sendGetCargoCarNum);
				//送达到货吨位
				BigDecimal sendGetCargoWeight = tbOrderMapper.countSendGrossByProjectId(map);
				projectOperationDetail.setSendGetCargoWeight(sendGetCargoWeight);
				
				//到货
				map.put("status", status2);
				map.put("receipterDate",1);//回单完成时间不为null  表示该运单已完成
				//送达到货车次
				Integer arriveCarCount = tbOrderMapper.countCarCountByProjectId(map);
				projectOperationDetail.setSendArriveCargoNum(arriveCarCount);
				//送达到货吨位
				BigDecimal sendArriveCargoWeight = tbOrderMapper.countReceiptGrossByProjectId(map);
				projectOperationDetail.setSendArriveCargoWeight(sendArriveCargoWeight);
				//运输完成吨位
				projectOperationDetail.setFinishCargoWeight(sendArriveCargoWeight);
			}
			
			//火运  接取+火运 火运+送达 联运
			if(transportType==Constants.TRANSPORTTYPE_TRAIN || transportType==Constants.TRANSPORTTYPE_RECEIVE_TRAIN
					|| transportType==Constants.TRANSPORTTYPE_TRAIN_DELIVERY || transportType==Constants.TRANSPORTTYPE_UNION){
				List<Byte> status1 = new ArrayList<>();
				status1.add(Constants.SMS_POINT_ON_ROAD);
				status1.add(Constants.SMS_POINT_LOCATION_GUIDE);
				//status1.add(Constants.SMS_POINT_WAIT_RETURN);
				
				//等待计费下的状态
				List<Byte> status2 = new ArrayList<>();
				status2.add(Constants.SMS_POINT_WAIT_RETURN);
				status2.add(Constants.SMS_POINT_WAIT_CHARGE);//等待计费
				status2.add(Constants.SMS_POINT_ORDER_DONE);//已完成
				
				//在途运载吨位
				onwayDecimal = onwayDecimal.add(trainOrderMapper.countEntruckWeightByProjectId(status1, id)==null ? new BigDecimal(0):trainOrderMapper.countEntruckWeightByProjectId(status1, id));
				
				//6:等待确认         7:已完成
				status1.add(Constants.SMS_POINT_WAIT_CHARGE);
				status1.add(Constants.SMS_POINT_ORDER_DONE);
				//提货车次
				Integer trainSendCargoTrainNum = trainOrderMapper.countCarCountByProjectId(status1, id);
				projectOperationDetail.setTrainSendCargoTrainNum(trainSendCargoTrainNum);
				//提货吨位
				BigDecimal trainSendCargoWeight = trainOrderMapper.countEntruckWeightByProjectId(status1, id);
				projectOperationDetail.setTrainSendCargoWeight(trainSendCargoWeight);
				
				Integer trainArriveCargoTrainNum = trainOrderMapper.countCarCountByProjectId(status2, id);
				//到货吨位
				BigDecimal trainArriveCargoWeight = trainOrderMapper.countArriveWeightByProjectId(status2, id);
				projectOperationDetail.setTrainArriveCargoTrainNum(trainArriveCargoTrainNum);
				projectOperationDetail.setTrainArriveCargoWeight(trainArriveCargoWeight);
				//运输完成吨位
				if(transportType==Constants.TRANSPORTTYPE_TRAIN || transportType==Constants.TRANSPORTTYPE_RECEIVE_TRAIN){//火运 接取+火运
					projectOperationDetail.setFinishCargoWeight(trainArriveCargoWeight);
				}
				//中转库存
				//（接取到货吨位-火运发货吨位）-（火运到货吨位-送达发货吨位）=中转库存吨位
				if(transportType!=Constants.TRANSPORTTYPE_TRAIN){
					BigDecimal beginQty = stockMapper.selectCurrentQtyByProjectId(Constants.STOCK_TYPE_BEGIN, id) == null ? new BigDecimal(0):stockMapper.selectCurrentQtyByProjectId(Constants.STOCK_TYPE_BEGIN, id);//始发站点库存
					BigDecimal endQty = stockMapper.selectCurrentQtyByProjectId(Constants.STOCK_TYPE_END, id) == null ? new BigDecimal(0):stockMapper.selectCurrentQtyByProjectId(Constants.STOCK_TYPE_END, id);//到达站点库存
					BigDecimal transitCargoWeight = beginQty.add(endQty);
					//	BigDecimal transitCargoWeight = projectOperationDetail.getReceiveArriveCargoWeight().subtract(projectOperationDetail.getTrainSendCargoWeight())
					//		.subtract(projectOperationDetail.getTrainArriveCargoWeight()).add(projectOperationDetail.getSendGetCargoWeight());
					projectOperationDetail.setTransitCargoWeight(transitCargoWeight);
				}
			}
			
		}
		return projectList;
	}

	/**
	* @description 获取货位图
	* @date 2017年12月31日
	* @author shilvfei
	* @param 
	* @return
	 */
	@Override
	public LogisticsResult getCargoLocationById(Integer freightYardId) {
		List<CargoLocationPlan> list = cargoLocationMapper.getCargoLocationPlans(freightYardId);
		for (CargoLocationPlan cargoLocationPlan : list) {
			List<TbStock> stocks = cargoLocationPlan.getStocks();
			cargoLocationPlan.setLength(stocks.size());
		}
		return LogisticsResult.ok(list);
	}

	/**
	 * @description 项目核查:查询的条件
	 * @date 2018年1月12日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private void projectCriteria(TbProject project,
			com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria criteria) {
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		criteria.andTabNameEqualTo("tb_project");
		
		if(project == null)
			return;
		//条件查询
		if(StringUtils.isNotBlank(project.getProjectCode())){//项目编号
			criteria.andProjectCodeLike(project.getProjectCode());
		}
		if(project.getBranchGroupId()!=null){//分支机构
			criteria.andBranchGroupIdEqualTo(project.getBranchGroupId());
		}
		if(project.getProjectType()!=null){//项目类型
			criteria.andProjectTypeEqualTo(project.getProjectType());
		}
		if(project.getTransportType()!=null){//联运模式
			criteria.andTransportTypeEqualTo(project.getTransportType());
		}
		if(project.getCargoId()!=null){//货物id
			criteria.andCargoIdEqualTo(project.getCargoId());
		}
		if(StringUtils.isNotBlank(project.getSendCargoCompanyName())){//发货企业
			criteria.andSendCargoCompanyNameLike(project.getSendCargoCompanyName());
		}
		if(StringUtils.isNotBlank(project.getReceiveCargoCompanyName())){//收货企业
			criteria.andReceiveCargoCompanyNameLike(project.getReceiveCargoCompanyName());
		}
	}

	/**
	 * @description 通过id获取项目运营管理项目详情
	 * @date 2018年1月8日
	 * @author shilvfei
	 * @param id
	 * @return
	 */
	@Override
	public ProjectOperationDetail getProjectOperationByPid(Integer id) {
		TbProjectExample example=new TbProjectExample();
    	com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria criteria = example.createCriteria();
    	criteria.andIdEqualTo(id);
    	List<ProjectOperationDetail> list = listProjectOperation(example);
    	ProjectOperationDetail operationDetail = new ProjectOperationDetail();
    	if(list!=null && list.size()!=0){
    		operationDetail=list.get(0);
    	}
		return operationDetail;
	}


	/* 
	 * 项目核算
	 */
	@Override
	public List<Map<String, Object>> queryProjectCheck2(int start, int pageSize, Map<String, Object> form) {
		PageHelper.offsetPage(start, pageSize);
		return this.queryProjectCheck2(form);
	}


	/* 
	 * 项目核算
	 */
	@Override
	public List<Map<String, Object>> queryProjectCheck2(Map<String, Object> form) {
		form.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		return projectCheck2Mapper.queryProjectCheck2(form);
	}
	
}
