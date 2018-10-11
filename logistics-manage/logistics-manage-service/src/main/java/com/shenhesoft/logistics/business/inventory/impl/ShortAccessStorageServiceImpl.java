package com.shenhesoft.logistics.business.inventory.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenhesoft.logistics.business.helpPojo.TbOrderHelpPojo;
import com.shenhesoft.logistics.business.inventory.ShortAccessStorageService;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrderExample;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrderExample.Criteria;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.search.OrderSearch;

/**
 * @description 短驳出入库查询
 * 
 * @author shilvfei
 * 
 * @date 2018年1月2日
 */
@Service
public class ShortAccessStorageServiceImpl implements ShortAccessStorageService{
	
	/**
	 * 短驳运单的Mapper
	 */
	@Autowired
	private TbOrderMapper orderMapper;
	
	/**
	 * 项目的Mapper
	 */
	@Autowired
	private TbProjectMapper projectMapper;
	
	
	/**
	* @description 
	* @date 2018年1月3日
	* @author shilvfei
	* @param 
	* @return
	 */
	@Override
	public DataGridResult getShortAccessStorage(Integer page, Integer limit,OrderSearch orderSearch) {
		// 分页处理，显示第一页的20条数据
		PageHelper.startPage(page, limit);
		TbOrderExample example = new TbOrderExample();
		example.setOrderByClause("A.id desc");
		// 查询条件
		Criteria criteria = example.createCriteria();
		criteria.andStatusNotEqualTo(Constants.SMS_POINT_WAIT_DIS);//不包含等待调度的
		criteria.andStepSelectCodeNotEqualTo(Constants.SHORT_BRAGE_TYPE_CARRUN);//不包含汽运
		criteria.andDeleteFlagEqualTo((byte) 0);//未删除的
		criteria.andExceptionStatusEqualTo((byte)0);//全部（异常，非异常）
		
		criteria.andSysOrgCodeEqualTo(AppSession.getCurrentSysOrgCode());
		criteria.andTabNameEqualTo("tb_order");
		//条件x查询
		orderCriteria(orderSearch, criteria);
		
		List<TbOrder> list = orderMapper.selectByExample(example);// 查询
		// 取分页信息
		PageInfo<TbOrder> pageInfo = new PageInfo<TbOrder>(list);
		long total = pageInfo.getTotal(); // 获取总记录数
		return new DataGridResult(total, list, limit);
	}
	
	/**
	 * @description 短驳出入库条件查询
	 * @date 2018年1月12日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	private void orderCriteria(OrderSearch orderSearch, Criteria criteria) {
		if(orderSearch == null){
			return;
		}
		if(StringUtils.isNotBlank(orderSearch.getProjectCode())){//项目编号
			criteria.andProjectCodeLike(orderSearch.getProjectCode());
		}
		if(StringUtils.isNotBlank(orderSearch.getBranchGroupName())){//分支机构
			criteria.andBranchGroupNameEqualTo(orderSearch.getBranchGroupName());
		}
		if(orderSearch.getProjectType()!=null){//项目类型
			criteria.andProjectTypeEqualTo(orderSearch.getProjectType());
		}
		if(StringUtils.isNotBlank(orderSearch.getCargoName())){//货物名称
			criteria.andCargoNameLike(orderSearch.getCargoName());
		}
		if(StringUtils.isNotBlank(orderSearch.getOrderCode())){	//运单编号
			criteria.andOrderCodeLike(orderSearch.getOrderCode());
		}
		if(orderSearch.getBeginDate()!=null && orderSearch.getEndDate()!=null){//创建时间
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String strBgeinDate = DateUtils.date2Str(orderSearch.getBeginDate(),sdf);
			String strEndDate = DateUtils.date2Str(orderSearch.getEndDate(),sdf);
			criteria.andCreateDateGreaterThanOrEqualTo(strBgeinDate);
			criteria.andCreateDateLessThanOrEqualTo(strEndDate);
	    }
		if(StringUtils.isNotBlank(orderSearch.getSendCompany())){//发货单位
			criteria.andSendCompanyLike(orderSearch.getSendCompany());
		}
		if(StringUtils.isNotBlank(orderSearch.getReceiptCompany())){//收货单位
			criteria.andReceiptCompanyLike(orderSearch.getReceiptCompany());
		}
		if(StringUtils.isNotBlank(orderSearch.getCarPlateNumber())){//承运车辆
			criteria.andCarPlateNumberLike(orderSearch.getCarPlateNumber());
		}
		if(orderSearch.getArriveThingDate()!=null){//到货时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String receipterDate = DateUtils.date2Str(orderSearch.getArriveThingDate(),sdf);
			criteria.andReceipterDateEqualTo(receipterDate);
		}
	}
	/**
	* @description  短驳出入库查询 查询统计
	* @date 2018年1月3日
	* @author shilvfei
	* @param 
	* @return
	 */
	@Override
	public TbOrderHelpPojo shortAccessStorageStatistics(Integer projectId) {
		TbOrderHelpPojo orderHelpPojo = new TbOrderHelpPojo();
		TbProject project = projectMapper.selectByPrimaryKey(projectId);
		orderHelpPojo.setProjectCode(project.getProjectCode());
		orderHelpPojo.setProjectType(project.getProjectType());
		orderHelpPojo.setBranchGroupName(project.getBranchGroupName());
		orderHelpPojo.setTransportType(project.getTransportType());
		orderHelpPojo.setCargoName(project.getCargoName());
		orderHelpPojo.setCargoSpecifications(project.getCargoSpecifications());
		orderHelpPojo.setCargoPrice(project.getCargoPrice());
		orderHelpPojo.setValuationUnitName(project.getValuationUnitName());
		/**
	     * 该项目下的运单
	     * */
		TbOrderExample example = new TbOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjectIdEqualTo(projectId);
		List<TbOrder> orders = orderMapper.selectByExample(example);// 查询
		orderHelpPojo.setOrders(orders);
		//总运单
		orderHelpPojo.setTotalOrder(orders.size());
		//总计提货吨位
		BigDecimal totalPickUpWeight = new BigDecimal(0);
		//总计损耗
		BigDecimal totalWastageWeight = new BigDecimal(0);
		//总计到货吨位
		BigDecimal totalArriveWeight = new BigDecimal(0);
		for (TbOrder tbOrder : orders) {
			BigDecimal oneSendWeight = tbOrder.getContainerOneSendNet()==null?new BigDecimal(0):tbOrder.getContainerOneSendNet();
			BigDecimal twoSendWeight = tbOrder.getContainerTwoSendNet()==null?new BigDecimal(0):tbOrder.getContainerTwoSendNet();
			totalPickUpWeight = totalPickUpWeight.add(oneSendWeight).add(twoSendWeight);
			
			BigDecimal oneArriveWeight = tbOrder.getContainerOneReceiptNet()==null?new BigDecimal(0):tbOrder.getContainerOneReceiptNet();
			BigDecimal twoArriveWeight = tbOrder.getContainerTwoReceiptNet()==null?new BigDecimal(0):tbOrder.getContainerTwoReceiptNet();
			totalArriveWeight = totalArriveWeight.add(oneArriveWeight).add(twoArriveWeight);
			
			totalWastageWeight = totalWastageWeight.add(totalPickUpWeight.subtract(totalArriveWeight));
			
			/*if(tbOrder.getSendGross() != null){
				totalPickUpWeight = totalPickUpWeight.add(tbOrder.getSendGross());
			}
			if(tbOrder.getSendGross() != null && tbOrder.getReceiptGross() != null){
				totalWastageWeight = totalWastageWeight.add(tbOrder.getSendGross().subtract(tbOrder.getReceiptGross()));
			}
			if(tbOrder.getReceiptGross() != null){
				totalArriveWeight = totalArriveWeight.add(tbOrder.getReceiptGross());
			}*/
		}
		orderHelpPojo.setTotalPickUpWeight(totalPickUpWeight);
		orderHelpPojo.setTotalWastageWeight(totalWastageWeight);
		orderHelpPojo.setTotalArriveWeight(totalArriveWeight);
		return orderHelpPojo;
	}
	
	
}
