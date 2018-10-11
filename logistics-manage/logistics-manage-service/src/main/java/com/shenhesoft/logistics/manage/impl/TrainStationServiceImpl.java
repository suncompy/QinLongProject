// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.impl;

import java.math.BigDecimal;
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
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.helpPojo.TbTrainStationHelp;
import com.shenhesoft.logistics.manage.interfaces.TrainStationService;
import com.shenhesoft.logistics.manage.mapper.TbTrainStationMapper;
import com.shenhesoft.logistics.manage.pojo.site.TbFreightYardExample;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStationExample;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStationExample.Criteria;

/**
 * @description 
 *
 * @author LiangLin
 *
 * @date 2017年12月11日
 */
@Service
public class TrainStationServiceImpl implements TrainStationService{
	
	@Autowired
	private TbTrainStationMapper tsMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainStationService#selectTrainStationByPage()
	 */
	@Override
	public List<TbTrainStation> selectTrainStationByPage() {
		TbTrainStationExample example = new TbTrainStationExample();
		Criteria criteria = example.createCriteria();
		example.setOrderByClause("A.id desc");
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
        criteria.andTabNameEqualTo("tb_train_station");
		return tsMapper.selectTrainStationByPage(example);
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainStationService#insertTbTrainStation(com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation)
	 */
	@Override
	@Transactional
	public boolean insertTbTrainStation(TbTrainStationHelp tbStion) {
		if(tbStion.getCity()==null) {
			tbStion.setCity("");
		}
		if(tbStion.getDistrict()==null) {
			tbStion.setDistrict("");
		}
		tbStion.setAdressCode(tbStion.getProvince()+","+tbStion.getCity()+","+tbStion.getDistrict());
		tbStion.setBankLastAmount(new BigDecimal(0));
		int rpw = tsMapper.insertSelective(tbStion);
		if (rpw != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(tbStion.getId().toString());
		branchGroupLink.setTabName("tb_train_station");
		branchGroupLink.setTabComment("站点信息");
		branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainStationService#selectTrainStationByIs(java.lang.Integer)
	 */
	@Override
	public TbTrainStationHelp selectTrainStationByIs(Integer id) {
		TbTrainStationHelp tbTransStation = tsMapper.selectTrainStationByIs(id);
		
		String addressCode = tbTransStation.getAdressCode();
		System.out.println(addressCode);
		String[] split = addressCode.split(",");
    	if(split!=null && split.length!=0){
    		tbTransStation.setProvince(split[0]);
    		tbTransStation.setCity(split[1]);
			addressCode=split[0]+split[1];
			if(split.length==3 && !split[2].equals("null")){
				tbTransStation.setDistrict(split[2]);
				addressCode=addressCode+split[2];
			}
			tbTransStation.setAdressCode(addressCode);
		}
		
		return tbTransStation;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainStationService#updateTbTrainStationById(com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation)
	 */
	@Override
	public boolean updateTbTrainStationById(TbTrainStationHelp tbStion) {
		if(tbStion.getCity()==null) {
			tbStion.setCity("");
		}
		if(tbStion.getDistrict()==null) {
			tbStion.setDistrict("");
		}
		tbStion.setAdressCode(tbStion.getProvince()+","+tbStion.getCity()+","+tbStion.getDistrict());
		if(tbStion.getStationLevel() == 2) {
			//营业厅 税号清空
			tbStion.setDutyParagraph("");
		}
		int rpw = tsMapper.updateTbTrainStationById(tbStion);
		if (rpw != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainStationService#delete(java.util.List)
	 */
	@Override
	@Transactional
	public boolean delete(List<Integer> list) {
		int rpw = tsMapper.deleteBatch(list);
		if (rpw != list.size()) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainStationService#selectIsFreightYardDeleteById(java.util.List)
	 */
	@Override
	public boolean selectIsFreightYardDeleteById(List<Integer> list) {
		int count = tsMapper.selectIsFreightYardDeleteById(list);
		if (count != 0) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainStationService#selectIsContainerDeleteById(java.util.List)
	 */
	@Override
	public boolean selectIsContainerDeleteById(List<Integer> list) {
		int count = tsMapper.selectIsContainerDeleteById(list);
		if (count != 0) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainStationService#selectIsStationBusinessDeleteById(java.util.List)
	 */
	@Override
	public boolean selectIsStationBusinessDeleteById(List<Integer> list) {
		int count = tsMapper.selectIsStationBusinessDeleteById(list);
		if (count != 0) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainStationService#selectTrainStationByPages(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DataGridResult selectTrainStationByPages(Integer page, Integer limit,TbTrainStation trainStation) {
		TbTrainStationExample example = new TbTrainStationExample();
		example.setOrderByClause("A.id desc");
		Criteria criteria = example.createCriteria();
		  //分页处理，显示第一页的20条数据
        PageHelper.startPage(page, limit);
        trainStationCriteria(trainStation, criteria);//条件查询
        List<TbTrainStation> list = tsMapper.selectTrainStationByPage(example);
        for (TbTrainStation tbTrainStation : list) {
			if(null != tbTrainStation.getParentId()) {
				String stationName = tsMapper.selectStationNameByParentId(tbTrainStation.getParentId());
				tbTrainStation.setParentName(stationName);
			}
		}
        // 取分页信息
        PageInfo<TbTrainStation> pageInfo = new PageInfo<TbTrainStation>(list);
        long total = pageInfo.getTotal(); //获取总记录数
		return new DataGridResult(total, list, limit);
	}

	@Override
	public List<TbTrainStation> selectThreeTrainStationByLevel(byte level) {
		TbTrainStationExample example = new TbTrainStationExample();
		Criteria criteria = example.createCriteria();
		criteria.andStationLevelEqualTo(level);
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
        criteria.andTabNameEqualTo("tb_train_station");
		//return 	tsMapper.selectByExample(example);
		return 	tsMapper.selectTrainStationByPage(example);
	}

	/* (non-Javadoc)
	 * @see com.shenhesoft.logistics.manage.interfaces.TrainStationService#getParentsById(java.lang.Integer)
	 */
	@Override
	public List<TbTrainStation> getParentsById(Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deleteFlag",Constants.NO);
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		if(id == 1){
			//id = id-1;
			map.put("level",0);
		}
		if(id == 0){
			//0代表铁道总局：没上级站点
			return null;
		}
		if(id == 2){
			map.put("NoLevel",id);
		}
		return tsMapper.getParentsById(map);
	}
	
	/**
	 * 
	 * @description  -根据当前所选站点级别，查询子级站点
	 * @author liangLin
	 * @date 2017年12月25日
	 * @param 
	 * @return
	 */
	@Override
	public List<TbTrainStation> getChildrenStationById(Integer id) {
		TbTrainStationExample stationExample = new TbTrainStationExample();
		Criteria criteria = stationExample.createCriteria();
		criteria.andParentIdEqualTo(id);
		criteria.andDeleteFlagEqualTo((byte)0);
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
        criteria.andTabNameEqualTo("tb_train_station");
		List<TbTrainStation> list = tsMapper.selectTrainStationByPage(stationExample);
		return list;
	}



	private void trainStationCriteria(TbTrainStation trainStation, Criteria criteria) {
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
        criteria.andTabNameEqualTo("tb_train_station");
		if(trainStation == null) {
			return;
		}
		//站点名称
		if(StringUtils.isNotBlank(trainStation.getStationName())){
			criteria.andStationNameLike(trainStation.getStationName());
		}
		//站点级别
		if(trainStation.getStationLevel()!=null){
			criteria.andStationLevelEqualTo(trainStation.getStationLevel());
		}
		
		//负责人
		if(StringUtils.isNotBlank(trainStation.getResponsibler())){
			criteria.andResponsiblerLike(trainStation.getResponsibler());
		}
		
		//联系方式
		if(StringUtils.isNotBlank(trainStation.getStationPhone())){
			criteria.andStationPhoneLike(trainStation.getStationPhone());
		}
	}

	@Override
	public boolean selectChildrenStationById(List<Integer> idList) {
		int count = tsMapper.selectChildrenStationById(idList);
		if (count != 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean selectProjectByStationId(List<Integer> idList) {
		int count = tsMapper.selectProjectByStationId(idList);
		if (count != 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<TbTrainStation> listTrainStationByArea(List<String> areas) {
		Map<String, Object> map = new HashMap<>();
		map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		map.put("areas", areas);
		return tsMapper.listTrainStationByArea(map);
	}

}
