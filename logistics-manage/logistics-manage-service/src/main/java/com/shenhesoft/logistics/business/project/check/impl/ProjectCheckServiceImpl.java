package com.shenhesoft.logistics.business.project.check.impl;

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
import com.shenhesoft.logistics.business.helpPojo.ProjectOperationDetail;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderMapper;
import com.shenhesoft.logistics.business.project.check.ProjectCheckService;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample;

/**
 * @description: 项目核查服务
 * 
 * @author shilvfei
 * 
 * @date 2017年12月25日
 */
@Service
public class ProjectCheckServiceImpl implements ProjectCheckService {

	@Autowired
	private TbProjectMapper projectMapper;
	
	@Autowired
	private TbOrderMapper tbOrderMapper;
	
	@Autowired
	private TbTrainOrderMapper trainOrderMapper;
	
	@Override
	public DataGridResult listProjectCheckByPage(Integer page, Integer limit,Integer uid,TbProject project) {
		TbProjectExample example=new TbProjectExample();
    	com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria criteria = example.createCriteria();
    	criteria.andStatusNotEqualTo(Constants.PROJECT_STATUS_CANCLE);//未取消
    	criteria.andStatusNotEqualTo(Constants.PROJECT_STATUS_UNUSED);//未删除
    	PageHelper.startPage(page, limit);
    	
    	projectCriteria(project, criteria);//条件查询
    	
		List<ProjectOperationDetail> projectList = listProjectCheck(example);
		PageInfo<ProjectOperationDetail> pageInfo = new PageInfo<ProjectOperationDetail>(projectList);
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, projectList, limit);
	}
	
	/**
	 * @description 查询项目核查信息
	 * @date 2018年1月9日
	 * @author shilvfei
	 * @param example
	 * @return
	 */
	private List<ProjectOperationDetail> listProjectCheck(TbProjectExample example) {
		List<ProjectOperationDetail> projectList = projectMapper.selectProjectOperationDetail(example);
		List<Byte> status1 = new ArrayList<>();
		status1.add(Constants.SMS_POINT_ON_ROAD);
		status1.add(Constants.SMS_POINT_LOCATION_GUIDE);
		status1.add(Constants.SMS_POINT_WAIT_RETURN);
		
		//等待计费下的状态
		List<Byte> status2 = new ArrayList<>();
		status2.add(Constants.SMS_POINT_WAIT_RETURN);
		status2.add(Constants.SMS_POINT_WAIT_CHARGE);//等待计费
		status2.add(Constants.SMS_POINT_ORDER_DONE);//已完成
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
				//接取到货车次
				Integer arriveCarCount = tbOrderMapper.countCarCountByProjectId(map);
				projectOperationDetail.setReceiveArriveCargoCarNum(arriveCarCount);
				//接取到货吨位
				BigDecimal receiveArriveCargoWeight = tbOrderMapper.countReceiptGrossByProjectId(map);
				projectOperationDetail.setReceiveArriveCargoWeight(receiveArriveCargoWeight);
			}
			
			//火运  接取+火运 火运+送达
			if(transportType==Constants.TRANSPORTTYPE_TRAIN || transportType==Constants.TRANSPORTTYPE_RECEIVE_TRAIN
					|| transportType==Constants.TRANSPORTTYPE_TRAIN_DELIVERY || transportType==Constants.TRANSPORTTYPE_UNION){
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
				
			}
			//  火运+送达 //送达
			if(transportType==Constants.TRANSPORTTYPE_DELIVERY || transportType==Constants.TRANSPORTTYPE_TRAIN_DELIVERY
				||transportType==Constants.TRANSPORTTYPE_UNION || transportType==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY	){
				//获取送达的运单
				Byte type=1;
				map.put("stepSelectCode", type);
			    map.put("status", status1);
			    map.put("id", id);
				//把3和6的都查出来 
				 onwayDecimal = onwayDecimal.add(tbOrderMapper.countSendGrossByProjectId(map)==null ? new BigDecimal(0):tbOrderMapper.countSendGrossByProjectId(map));
				//设置在途运输吨位
				projectOperationDetail.setRunningCargoWeight(onwayDecimal);
				
				//6:等待确认         7:已完成
				status1.add(Constants.SMS_POINT_WAIT_CHARGE);
				status1.add(Constants.SMS_POINT_ORDER_DONE);
				
				//提货
				map.put("status", status1);
				map.put("receipterDate",null);
				//送达提货车次
				Integer sendGetCargoCarNum = tbOrderMapper.countCarCountByProjectId(map);
				projectOperationDetail.setSendGetCargoCarNum(sendGetCargoCarNum);
				//送达到货吨位
				BigDecimal sendGetCargoWeight = tbOrderMapper.countSendGrossByProjectId(map);
				projectOperationDetail.setSendGetCargoWeight(sendGetCargoWeight);
				
				//到货
				map.put("status", status2);
				map.put("receipterDate",0);//回单完成时间不为null  表示该运单已完成
				//送达到货车次
				Integer arriveCarCount = tbOrderMapper.countCarCountByProjectId(map);
				projectOperationDetail.setSendArriveCargoNum(arriveCarCount);
				//送达到货吨位
				BigDecimal sendArriveCargoWeight = tbOrderMapper.countReceiptGrossByProjectId(map);
				projectOperationDetail.setSendArriveCargoWeight(sendArriveCargoWeight);
			}
		}
		return projectList;
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
		if(project==null){
			return ;
		}
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

}
