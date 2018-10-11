package com.shenhesoft.logistics.business.bussinessCount.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.shenhesoft.logistics.business.bussinessCount.BussinessCountService;
import com.shenhesoft.logistics.business.mapper.BussinessHomeMapper;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.pojo.map.ExceptionMsg;
import com.shenhesoft.logistics.business.pojo.map.MapPoint;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.StringUtils;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.RoadInfo;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.branchgroup.BranchGroupService;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbSystemUserMapper;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

@Service
public class BussinessCountServiceImpl implements BussinessCountService{

	@Autowired
	private BussinessHomeMapper bussinessHomeMapper;
	
	@Autowired
	private TbBranchGroupMapper branchGroupMapper;
	
	@Autowired
	private TbOrderMapper tbOrderMapper;
	
	@Autowired
	private TbSystemUserMapper systemMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	@Autowired
	private BranchGroupService branchGroupService;
	
	  /**
	   * 新增异常信息.
	   * 
	   * @param exceptionMsg
	   *            异常信息实体
	   * @return dao成功失败标志
	   */
	public ExceptionMsg addExceptionMsg(ExceptionMsg exceptionMsg){
	  if(null==exceptionMsg.getOrderId()){
        return exceptionMsg;
      }
      Map<String,Object> result = bussinessHomeMapper.getOrderExceptionByOrderId(exceptionMsg.getOrderId());
      exceptionMsg = FormUtil.populate(exceptionMsg, result, false);
      
      exceptionMsg.setExceptionId(AppUtils.randomUUID());
      exceptionMsg.setShortTrainFlag(0);
      exceptionMsg.setExceptionSource(1);
	  Integer userId = exceptionMsg.getSubmitUserId();
	  exceptionMsg.setSubmitUserId(null==userId?AppSession.getCurrentUserId():userId);
	  
	  bussinessHomeMapper.addExceptionMsg(exceptionMsg);
	  
	  TbOrder record = new TbOrder();
	  record.setExceptionStatus((byte) 1);
	  record.setId(exceptionMsg.getOrderId());
	  record.setExceptionReoportId(null==userId?AppSession.getCurrentUserId():userId);
	  if(null!=userId) {
		  TbSystemUser suser = systemMapper.selectByPrimaryKey(userId);
		  record.setExceptionReoportName(suser.getName());
	  }
	  String reason1 = null==exceptionMsg.getExceptionReason()?"":exceptionMsg.getExceptionReason();
	  String reason2 = null==exceptionMsg.getExceptionReasonDetail()?"":exceptionMsg.getExceptionReasonDetail();
	  record.setExceptionReoportReason(reason1+" "+reason2);
	  record.setExceptionTime(new Date());
	  tbOrderMapper.updateByPrimaryKeySelective(record);
	  return exceptionMsg;
	}
	  /**
	   * @description 
	   * @author liangLin
	   * @date 2018年2月9日
	   * @param 
	   * @return
	  */
	public List<Map<String, Object>> getExceptionMsgs(int start, int pageSize, Map<String, Object> form){
	  PageHelper.offsetPage(start, pageSize);
	  return bussinessHomeMapper.getExceptionMsgs(form);
	}
	@Override
	public List<Map<String, Object>> getTrainOrdersCount(Map<String, Object> map) {
	    map = CollectionUtils.isEmpty(map)?Maps.newHashMap():map;
		//map.put("branchGroupIdCount", getBranchIds(map));
		return bussinessHomeMapper.trainStatusCount(map);
	}
	public Map<String, Object> getCurOrg(Map<String, Object> map){
	  String userIdStr = FormUtil.getMapValue(map, "userId");
      Integer userId = null==userIdStr?AppSession.getCurrentUserId():Integer.parseInt(userIdStr);
      Map<String, Object> curOrg = branchGroupMapper.getCurTopBranchByUid(userId);
      return curOrg;
	}
	public List<Integer> getBranchIds(Map<String, Object> map){
	  String userIdStr = FormUtil.getMapValue(map, "userId");
      Integer userId = null==userIdStr?AppSession.getCurrentUserId():Integer.parseInt(userIdStr);
      //当前面登录人分支机构
      map.clear();
      map.put("status",Constants.DOT_BRANCH_STATUS_YES);
      //List<TbBranchGroup> branchGroups = branchGroupMapper.selectDotBranchByUid(map);
	  List<DotBranchDetail> branchGroups = branchGroupService.getDotBranchs(map);
      if(CollectionUtils.isEmpty(branchGroups)){
          return null;
      }
      //分支机构id
      List<Integer> branchIds = new ArrayList<>();
      for (TbBranchGroup tbBranchGroup : branchGroups) {
          branchIds.add(tbBranchGroup.getId());
      }
      return branchIds;
	}
	@Override
	public List<Map<String, Object>> getBulkTrainSprotCount(Map<String, Object> map) {
		//map.put("branchGroupIdBulkTrainSport", getBranchIds(map));
		return bussinessHomeMapper.getBulkTrainSprotCount(map);
	}
    /**
     * 新增地图标记表.
     * 
     * @param MapPoint 地图标记表实体
     * @return dao成功失败标志
     */
	public int addMapPoint(MapPoint mapPoint){
	  mapPoint.setHistoryFlag(1);
	  bussinessHomeMapper.editMapPointById(mapPoint);
	  mapPoint = this.loadMapPoint(mapPoint);
	  bussinessHomeMapper.addMapPoint(mapPoint);
	  
      BranchGroupLink branchGroupLink = new BranchGroupLink();
      branchGroupLink.setId(AppUtils.randomUUID());
      branchGroupLink.setRowId(mapPoint.getId());
      branchGroupLink.setTabName("tb_map_point");
      branchGroupLink.setTabComment("地图管理");
      branchGroupLink.setSysOrgCode(StringUtils.isBlank(mapPoint.getSysOrgCode())?AppSession.getCurrentSysOrgCode():mapPoint.getSysOrgCode());
      branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
	  return 0;
	}
	//@OrgLinkData(tabComment="路况信息")
	public int addRoadInfo(RoadInfo roadInfo){
	  roadInfo.setId(AppUtils.randomUUID());
	  return bussinessHomeMapper.addRoadInfo(roadInfo);
	}
	public List<Map<String, Object>> getRoadInfos(Map<String, Object> map){
	  return bussinessHomeMapper.getRoadInfos(map);
	}
	public List<Map<String, Object>> getRoadInfos(int start, int length, Map<String, Object> map){
	  PageHelper.offsetPage(start, length);
	  return this.getRoadInfos(map);
	}
    /**
     * 批量新增地图标记表.
     * 
     * @param list 批量地图标记表实体
     */
    public void addMapPoints(List<MapPoint> list){
      if(CollectionUtils.isEmpty(list)){
        return;
      }
      for(MapPoint mapPoint:list){
        mapPoint = this.loadMapPoint(mapPoint);
      }
      bussinessHomeMapper.addMapPoints(list);
    }
    private MapPoint loadMapPoint(MapPoint mapPoint){
      mapPoint.setId(AppUtils.randomUUID());
      mapPoint.setHistoryFlag(0);
      mapPoint.setCreateUserId(mapPoint.getUpdateUserId());
      //根据carId查询最新运单projectId,orderId,carNo
      Map<String,Object> param = FormUtil.populate(mapPoint);
      Map<String,Object> order = bussinessHomeMapper.getNewTimeOrder(param);
      //mapPoint = FormUtil.populate(mapPoint, order, true);
      mapPoint.setCarNo(FormUtil.getMapValue(order, "carNo"));
      String projectId = FormUtil.getMapValue(order, "projectId");
      String orderId = FormUtil.getMapValue(order, "orderId");
      mapPoint.setProjectId(StringUtils.isBlank(projectId)?null:Integer.parseInt(projectId));
      mapPoint.setOrderId(StringUtils.isBlank(orderId)?null:Integer.parseInt(orderId));
      return mapPoint;
    }

	   /**
     * 检索地图标记列表
     * @param map 
     * @date 2018年1月26日
     * @return
     */
	public List<Map<String, Object>> getMapPoints(Map<String, Object> map){
	  return bussinessHomeMapper.getMapPoints(map);
	}
    /**
     * 检索地图标记列表
     * @param map 
     * @date 2018年1月26日
     * @return
     */
	public List<Map<String, Object>> getOrderMapPointPcs(Map<String, Object> map){
      return bussinessHomeMapper.getOrderMapPointPcs(map);
    }
	@Override
	public List<Map<String, Object>> getBulkOrderStatusCount(Map<String, Object> map) {
		map = CollectionUtils.isEmpty(map)?Maps.newHashMap():map;
		//map.put("branchGroupIdBulkCount", getBranchIds(map));
		return bussinessHomeMapper.boxBulkStatusCount(map);
	}

	@Override
	public List<Map<String, Object>> getTrainSprotCount(Map<String, Object> map) {
		//map.put("branchGroupIdTrainSport", getBranchIds(map));
		return bussinessHomeMapper.getTrainSprotCount(map);
	}
	
	  /**
	   * 获取项目概览
	   */
	public List<Map<String, Object>> getProjectSurvey(Map<String, Object> form){
        return bussinessHomeMapper.getProjectSurvey(form);
    }
}
