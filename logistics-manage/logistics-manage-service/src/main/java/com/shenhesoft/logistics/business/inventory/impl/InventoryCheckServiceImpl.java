package com.shenhesoft.logistics.business.inventory.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.business.helpPojo.FreightSpace;
import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.business.helpPojo.ProjectOperationDetail;
import com.shenhesoft.logistics.business.helpPojo.StuckHelpPojo;
import com.shenhesoft.logistics.business.inventory.InventoryCheckService;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.mapper.TbStockAdjustHistoryMapper;
import com.shenhesoft.logistics.business.mapper.TbStockMapper;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
import com.shenhesoft.logistics.business.pojo.stock.TbStockExample;
import com.shenhesoft.logistics.business.pojo.stock.adjusthistory.TbStockAdjustHistory;
import com.shenhesoft.logistics.business.pojo.stock.adjusthistory.TbStockAdjustHistoryExample;
import com.shenhesoft.logistics.business.pojo.stock.adjusthistory.TbStockAdjustHistoryExample.Criteria;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbFreightYardMapper;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYard;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;

/**
 * @description 库存盘查
 * 
 * @author shilvfei
 * 
 * @date 2017年12月28日
 */
@Service
public class InventoryCheckServiceImpl implements InventoryCheckService{

	/**
	 * 项目Mapper
	 */
	@Autowired
	private TbProjectMapper projectMapper;
	
	/**
	 * 库存Mapper
	 */
	@Autowired
	private TbStockMapper stockMapper;
	/**
	 * 火车站点Mapper
	 */
	@Autowired
	private TbTrainStationMapper  stationMapper;
    /**
     * 货场Mapper
     */
    @Autowired
    private TbFreightYardMapper  freightYardMapper;
    
    /**
     * 库存历史调整记录
     */
    @Autowired
    private TbStockAdjustHistoryMapper adjustHistoryMapper;
    
    /**
     * 网点分支的Mapper
     */
    @Autowired
    private TbBranchGroupMapper branchGroupMapper;
    
	/**
	* @description 获取库存盘查列表信息
	* @date 2017年12月28日
	* @author shilvfei
	* @param 
	* @return
	 */
	@Override
	public DataGridResult listProjectInventoryByPage(Integer page, Integer limit,TbProject search,Integer uid) {
		TbProjectExample example=new TbProjectExample();
		example.setOrderByClause("create_date desc");
    	com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria criteria = example.createCriteria();
    	criteria.andStatusNotEqualTo(Constants.PROJECT_STATUS_CANCLE);//不包含未取消
    	//criteria.andStatusNotEqualTo(Constants.PROJECT_STATUS_UNUSED);//不包含未使用的
    	//不包含汽运的
    	criteria.andTransportTypeNotEqualTo(Constants.TRANSPORTTYPE_TRUCK);
    	
    	List<Integer> branchIds = branchGroupMapper.selectDotBranchIdsByUid(uid);//查询该用户所属的网点分支
    	
    	criteria.andBranchGroupIdIn(branchIds);
    	
    	criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
    	criteria.andTabNameEqualTo("tb_project");
    	
    	inventoryCriteria(search, criteria);//条件查询
    	
    	PageHelper.startPage(page, limit);
    	//执行查询
		List<ProjectDetail> list = projectMapper.selectProjectDetailByExample(example);
		List<StuckHelpPojo> helpPojos = new ArrayList<StuckHelpPojo>();
		for (ProjectDetail tbProject : list) {
			StuckHelpPojo helpPojo = convertObject(tbProject);
			helpPojos.add(helpPojo);
		}
		//计算库存信息
		for (StuckHelpPojo stuckHelpPojo : helpPojos) {
			getQty(stuckHelpPojo);
		}
		//获取分页总记录数
		PageInfo<ProjectDetail> pageInfo = new PageInfo<ProjectDetail>(list);
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, helpPojos, limit);
	}

	
	/**
	 * @description 获取库存信息
	 * @date 2018年1月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private void getQty(StuckHelpPojo stuckHelpPojo) {
		//通过projectId获取它的入库和出库总库存 总的库存 总的调整库存
		Integer projectId = stuckHelpPojo.getProjectId();
		
		//判断项目类型
		if(stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE
		||  stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_TRAIN
		||  stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_TRAIN
		|| 	stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_UNION
		||	stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_TRAIN_DELIVERY
		|| 	stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY){//接取 接取+火运 联运 接取+送达
			//入库
			BigDecimal enterQty = stockMapper.selectEnterQtyByProjectId(Constants.STOCK_TYPE_BEGIN, projectId);

			stuckHelpPojo.setBeginEnterQty(enterQty);
			//出库
			BigDecimal outQty = stockMapper.selectOutQtyByProjectId(Constants.STOCK_TYPE_BEGIN, projectId);
			stuckHelpPojo.setBeginOutQty(outQty);
			//库存
			BigDecimal currentQty = stockMapper.selectCurrentQtyByProjectId(Constants.STOCK_TYPE_BEGIN, projectId);
			stuckHelpPojo.setBeginCurrentQty(currentQty);
			//调整库存
			BigDecimal adjustQty = stockMapper.selectAdjustQtyByProjectId(Constants.STOCK_TYPE_BEGIN, projectId);
			stuckHelpPojo.setBeginAdjustQty(adjustQty);
			isManyFrientYard(stuckHelpPojo, projectId,Constants.STOCK_TYPE_BEGIN);
		}
		
		if(stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_DELIVERY
				||  stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_TRAIN
				||  stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_TRAIN_DELIVERY
				||  stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_TRAIN
				|| 	stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_UNION
				|| 	stuckHelpPojo.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY){
			//入库
			BigDecimal enterQty = stockMapper.selectEnterQtyByProjectId(Constants.STOCK_TYPE_END, projectId);
			stuckHelpPojo.setEndEnterQty(enterQty);
			//出库
			BigDecimal outQty = stockMapper.selectOutQtyByProjectId(Constants.STOCK_TYPE_END, projectId);
			stuckHelpPojo.setEndOutQty(outQty);
			//库存
			BigDecimal currentQty = stockMapper.selectCurrentQtyByProjectId(Constants.STOCK_TYPE_END, projectId);
			stuckHelpPojo.setEndCurrentQty(currentQty);
			//调整库存
			BigDecimal adjustQty = stockMapper.selectAdjustQtyByProjectId(Constants.STOCK_TYPE_END, projectId);
			stuckHelpPojo.setEndAdjustQty(adjustQty);
			isManyFrientYard(stuckHelpPojo, projectId,Constants.STOCK_TYPE_END);
		}
	}

	
	/**
	 * @description 是否是多货场 多货位
	 * @date 2018年1月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private void isManyFrientYard(StuckHelpPojo stuckHelpPojo, Integer projectId,Byte type) {
		//是否是多货场 多货位
		TbStockExample stockExample = new TbStockExample();
		com.shenhesoft.logistics.business.pojo.stock.TbStockExample.Criteria stockCriteria = stockExample.createCriteria();
		stockExample.setOrderByClause("adjust_date desc");
		stockCriteria.andProjectIdEqualTo(projectId);
		if(type==Constants.STOCK_TYPE_BEGIN){
			stockCriteria.andTypeEqualTo(type);//始发站点
			List<TbStock> stockList = stockMapper.selectByExample(stockExample);
			if(stockList!=null && stockList.size()!=0){
				if(stockList.size()>1){
					stuckHelpPojo.setBeginFreightYardName("多货场");
					stuckHelpPojo.setBeginCargoLocationName("多货位");
				}else{
					stuckHelpPojo.setBeginFreightYardName(stockList.get(0).getFreightYardName());
					stuckHelpPojo.setBeginCargoLocationName(stockList.get(0).getCargoLocationName());
				}
				stuckHelpPojo.setAdjustDate(stockList.get(0).getAdjustDate());
			}
		}else{
			stockCriteria.andTypeEqualTo(type);//到达站点
			List<TbStock> stockList = stockMapper.selectByExample(stockExample);
			if(stockList!=null && stockList.size()!=0){
				if(stockList.size()>1){
					stuckHelpPojo.setEndFreightYardName("多货场");
					stuckHelpPojo.setEndCargoLocationName("多货位");
				}else{
					stuckHelpPojo.setEndFreightYardName(stockList.get(0).getFreightYardName());
					stuckHelpPojo.setEndCargoLocationName(stockList.get(0).getCargoLocationName());
				}
				stuckHelpPojo.setAdjustDate(stockList.get(0).getAdjustDate());
			}
		}
		
	}

	
	/**
	 * @description 封装库存对象
	 * @date 2018年1月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private StuckHelpPojo convertObject(TbProject tbProject ) {
		StuckHelpPojo helpPojo = new StuckHelpPojo();
		helpPojo.setProjectId(tbProject.getId());
		helpPojo.setProjectCode(tbProject.getProjectCode());
		helpPojo.setProjectType(tbProject.getProjectType());
		helpPojo.setBranchGroupName(tbProject.getBranchGroupName());
		helpPojo.setTransportType(tbProject.getTransportType());
		helpPojo.setCargoName(tbProject.getCargoName());
		helpPojo.setSendCargoCompanyName(tbProject.getSendCargoCompanyName());
		helpPojo.setReceiveCargoCompanyName(tbProject.getReceiveCargoCompanyName());
		//根据不同的联运模式获取
		//接取 送达 接取+送达
		if(tbProject.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE
				|| 	tbProject.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_DELIVERY
				|| 	tbProject.getTransportType()==Constants.TRANSPORTTYPE_DELIVERY
				){
			helpPojo.setBeginStationId(tbProject.getReceiveCargoSiteId());
			helpPojo.setEndStationId(tbProject.getForwardingSiteId());
			
			if(tbProject.getReceiveCargoSiteId()!=null){
				TbTrainStation tbTrainStation = stationMapper.selectByPrimaryKey(tbProject.getReceiveCargoSiteId());
				helpPojo.setBeginStationName(tbTrainStation.getStationName());
			}
			
			if(tbProject.getForwardingSiteId()!=null){
				TbTrainStation tbTrainStation = stationMapper.selectByPrimaryKey(tbProject.getForwardingSiteId());
				helpPojo.setEndStationName(tbTrainStation.getStationName());
			}
			
		}
		//火运 接取+火运 火运+送达 联运 
		if(tbProject.getTransportType()==Constants.TRANSPORTTYPE_RECEIVE_TRAIN
				||  tbProject.getTransportType()==Constants.TRANSPORTTYPE_TRAIN
				||  tbProject.getTransportType()==Constants.TRANSPORTTYPE_TRAIN_DELIVERY
				|| 	tbProject.getTransportType()==Constants.TRANSPORTTYPE_UNION){
			helpPojo.setBeginStationId(tbProject.getBeginSiteId());
			helpPojo.setEndStationId(tbProject.getEndCenterSiteId());
			
			if(tbProject.getBeginSiteId()!=null){
				TbTrainStation tbTrainStation = stationMapper.selectByPrimaryKey(tbProject.getBeginSiteId());
				if(tbTrainStation!=null){
					helpPojo.setBeginStationName(tbTrainStation.getStationName());
				}
			}
			
			if(tbProject.getEndCenterSiteId()!=null){
				TbTrainStation tbTrainStation = stationMapper.selectByPrimaryKey(tbProject.getEndCenterSiteId());
				if(tbTrainStation!=null){
					helpPojo.setEndStationName(tbTrainStation.getStationName());
				}
				
			}
		}
		
		return helpPojo;
	}
	
	/**
	* @description  获取仓位图
	* @date 2017年12月29日
	* @author shilvfei
	* @param 
	* @return
	 */
	@Override
	public LogisticsResult getCargoLocationImg(Integer id) {
	
		//获取项目信息
		TbProject project = projectMapper.selectByPrimaryKey(id);
		
		List<FreightSpace> resultList = new ArrayList<>();//站点信息
		
		//  获取去重的站点 
		List<Integer> stationIds =stockMapper.distinctStationId(id);
		if(stationIds==null || stationIds.size()==0){
		}
		//  根据站点获取去重的货场
		List<Integer> freightYardIds=null;
		for (Integer stationId : stationIds) {
			FreightSpace freightSpace = new FreightSpace();
			freightSpace.setStationId(stationId);
			
			//根据站点id获取站点名称 
			TbTrainStation station = stationMapper.selectByPrimaryKey(stationId);
			freightSpace.setStationName(station.getStationName());
			
			//根据站点id获取去重货场
			freightYardIds =  stockMapper.distinctFreightYard(stationId,id);
			
			//  根据去重的货场获取货位
			List<FreightSpace> freightList = new ArrayList<>();
			
			for (Integer freightYardId : freightYardIds) {
				FreightSpace freightSpace1 = new FreightSpace();
				freightSpace1.setFreightYardId(freightYardId);
				freightSpace1.setStationName(station.getStationName());
				freightSpace1.setProjectId(id);
				freightSpace1.setProjectCode(project.getProjectCode());//项目编号
				freightSpace1.setProjectType(project.getProjectType());//项目类型
				freightSpace1.setCargoName(project.getCargoName());
				 //货场图片
				//根据货场id获取货场图片
				TbFreightYard freightYard = freightYardMapper.selectByPrimaryKey(freightYardId);
				 freightSpace1.setImg(freightYard.getFreightYardImg());
				 freightSpace1.setFreightYardName(freightYard.getName());
				
				 //通过货场获取货位
				 List<FreightSpace> list = stockMapper.selectCargoLocation(freightYardId,id);
				//给货场装货位
				 freightSpace1.setStockList(list);
				
				 //添加到站点
				 freightList.add(freightSpace1);
			}
			
			freightSpace.setStockList(freightList);
			resultList.add(freightSpace);
		}
		
		return LogisticsResult.ok(resultList);
	}

	/**
	 * @description 库存盘查 查看项目详情
	 * @date 2018年1月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@Override
	public LogisticsResult getInventoryCheckDetail(Integer projectId) {
		//获取项目信息
		 TbProject tbProject = projectMapper.selectDetailProjectById(projectId);
		
		//转换
		StuckHelpPojo stuckHelpPojo = convertObject(tbProject);
		//获取总的库存信息
		getQty(stuckHelpPojo);
		
		//通过项目 id获取单个货位的库存信息
		TbStockExample example = new TbStockExample();
		com.shenhesoft.logistics.business.pojo.stock.TbStockExample.Criteria criteria = example.createCriteria();
		criteria.andProjectIdEqualTo(projectId);
		List<TbStock> stocks = stockMapper.selectByExample(example);
		
		stuckHelpPojo.setStocks(stocks);
		
		return LogisticsResult.ok(stuckHelpPojo);
	}


	/**
	 * @description 通过项目id和货场id查询该货场下的货位信息
	 * @date 2018年1月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	@Override
	public LogisticsResult getStockByFreightYardId(Integer projectId, Integer freightYardId) {
		TbStockExample example=new TbStockExample();
		com.shenhesoft.logistics.business.pojo.stock.TbStockExample.Criteria criteria = example.createCriteria();
		criteria.andProjectIdEqualTo(projectId);//and条件 项目id
		criteria.andFreightYardIdEqualTo(freightYardId);//and 条件 货场id
		List<TbStock> list = stockMapper.selectByExample(example);//获取货场下的货位信息
		return LogisticsResult.ok(list);
	}

	/**
	* @description 调整库存信息
	* @date 2018年1月3日
	* @author shilvfei
	* @param 
	* @return
	 */
	@Override
	public LogisticsResult updateStock(List<TbStock> list,TbSystemUser user) {
		Date adjustDate=new Date();
		for (TbStock tbStock : list) {
			//获取库存信息
			TbStock stock = stockMapper.selectByPrimaryKey(tbStock.getId());
			stock.setAdjustDate(adjustDate);
			if(stock.getAdjustQty()==null || stock.getAdjustQty().compareTo(BigDecimal.ZERO)==0 ){//为0true 不为0false
				stock.setAdjustQty(tbStock.getAdjustQty());
			}else{
				stock.setAdjustQty(stock.getAdjustQty().add(tbStock.getAdjustQty()));
			}
			if(stock.getCurrentQty()==null || stock.getCurrentQty().compareTo(BigDecimal.ZERO)==0 ){//为0true 不为0false
				stock.setCurrentQty(tbStock.getAdjustQty());
			}else{
				stock.setCurrentQty(stock.getCurrentQty().add(tbStock.getAdjustQty()));
			}
			
			stockMapper.updateByPrimaryKeySelective(stock);//更新库存
			
			//保存历史库存调整记录
			TbStockAdjustHistory record= new  TbStockAdjustHistory();
			record.setAdjustStock(tbStock.getAdjustQty());
			record.setProjectId(stock.getProjectId());
			record.setStockId(stock.getId());
			record.setAdjustor(user.getName());
			record.setAdjustDate(adjustDate);
			adjustHistoryMapper.insert(record);
		}
		
		return LogisticsResult.ok();
	}

	/**
	 * @description 库存盘查 :条件查询
	 * @date 2018年1月11日
	 * @author shilvfei
	 * @param search
	 * @param criteria
	 */
	private void inventoryCriteria(TbProject search,
			com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria criteria) {
		if(search==null){
			return;
		}
		if(StringUtils.isNotBlank(search.getProjectCode())){//项目编号
			criteria.andProjectCodeLike(search.getProjectCode());
		}
		if(search.getBranchGroupId()!=null){//分支机构
			criteria.andBranchGroupIdEqualTo(search.getBranchGroupId());
		}
		if(search.getProjectType()!=null){//项目类型
			criteria.andProjectTypeEqualTo(search.getProjectType());
		}
		if(search.getCargoId()!=null){//货物名称
			criteria.andCargoIdEqualTo(search.getCargoId());
		}
		if(StringUtils.isNotBlank(search.getSendCargoCompanyName())){//发货企业
			criteria.andSendCargoCompanyNameLike(search.getSendCargoCompanyName());
		}
		if(StringUtils.isNotBlank(search.getReceiveCargoCompanyName())){//收货企业
			criteria.andReceiveCargoCompanyNameLike(search.getReceiveCargoCompanyName());
		}
	}


	/**
	* @description 库存盘查:入库
	* @date 2018年1月15日
	* @author shilvfei
	* @param 
	* @return
	 */
	@Override
	public LogisticsResult storageInventory(TbStock stock) {
		Integer projectId = stock.getProjectId();
		Integer cargoLocationId = stock.getCargoLocationId();
		TbStockExample example=new TbStockExample();
		com.shenhesoft.logistics.business.pojo.stock.TbStockExample.Criteria criteria = example.createCriteria();
		criteria.andProjectIdEqualTo(projectId);
		criteria.andCargoLocationIdEqualTo(cargoLocationId);
		List<TbStock> list = stockMapper.selectByExample(example);
		stock.setCurrentQty(stock.getEnterQty());//库存
		if(list!=null && list.size()!=0){
			TbStock tbStock = list.get(0);
			//增加入库库存
			BigDecimal enterQty = tbStock.getEnterQty()==null?new BigDecimal(0):tbStock.getEnterQty();
			tbStock.setEnterQty(enterQty.add(stock.getEnterQty()==null?new BigDecimal(0):stock.getEnterQty()));
			//增加库存
			BigDecimal currentQty = tbStock.getCurrentQty()==null? new BigDecimal(0):tbStock.getCurrentQty();
			tbStock.setCurrentQty(currentQty.add(stock.getCurrentQty()==null?new BigDecimal(0):stock.getCurrentQty()));
			stockMapper.updateByPrimaryKey(tbStock);//更新库存
		}else{
			stockMapper.insertSelective(stock);
		}
		return LogisticsResult.ok();
	}
}
