package com.shenhesoft.logistics.finance.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.shenhesoft.logistics.business.helpPojo.ProjectDetail;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.business.project.manage.ProjectManagmentService;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.finance.CostOrderFinance;
import com.shenhesoft.logistics.finance.CostOrderFinanceService;
import com.shenhesoft.logistics.finance.mapper.CostOrderFinanceMapper;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 短驳运单-业务实现.
 * <p>
 * <a href="CostOrderFinanceServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author shilvfei
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CostOrderFinanceServiceImpl implements CostOrderFinanceService{

	
	@Autowired
	private CostOrderFinanceMapper costOrderFinanceMapper;
	
	@Autowired 
	private ProjectManagmentService projectManagmentService;
	
	@Override
	public List<Map<String, Object>> getCostOrderFinances(int start, int pageSize, Map<String, Object> queryMap) {
		PageHelper.offsetPage(start, pageSize);
		return this.getCostOrderFinances(queryMap);
	}
	
	/**
	 * 获取所有短驳运单财务表.
	 * 
	 * @return 短驳运单财务表
	 */
	public List<Map<String, Object>> getCostOrderFinances(Map<String, Object> form) {
		return costOrderFinanceMapper.getCostOrderFinances(form);
	}

	@Override
	public void addCostOrderFinanceByTborder(TbOrder order, Integer userId) {
		if (order == null)
			return;
		CostOrderFinance costOrderFinance = new CostOrderFinance();
		// 项目id
		costOrderFinance.setProjectId(order.getProjectId());
		// 运单id
		costOrderFinance.setOrderId(order.getId());
		// 财务状态(0-待确认、1-待计算、2-待审核、3-已审核)
		costOrderFinance.setFinanceStatus((byte)0);
		// 是否被打包(0-未打包 1-已打包)
		costOrderFinance.setPackFlag((byte)0);
		// 是否删除(0-未删除 1-删除)
		costOrderFinance.setDeleteFlag((byte)0);
		// 毛重
		costOrderFinance.setSendGross(order.getSendGross());
		// 净重
		if(order.getContainerOneSendNet() != null ){
			if(order.getContainerTwoSendNet() != null){
				costOrderFinance.setSendNet(order.getContainerOneSendNet().add(order.getContainerTwoSendNet()));
			}else{
				costOrderFinance.setSendNet(order.getContainerOneSendNet());
			}
		}
		// 皮重
		costOrderFinance.setSendTare(order.getSendTare());
		
		//获取货物单价
		LogisticsResult logisticsResult = projectManagmentService.selectProject(order.getProjectId());
		
		if(logisticsResult.getStatus()==200){
			ProjectDetail projectDetail = (ProjectDetail)logisticsResult.getData();
			if(projectDetail!=null){
				//货物单价
				costOrderFinance.setCargoUnitPrice(projectDetail.getCargoPrice());
				
				if(costOrderFinance.getCargoUnitPrice()!=null && costOrderFinance.getSendNet() != null){
					//货物价格
					costOrderFinance.setCargoPrice(costOrderFinance.getCargoUnitPrice().multiply(costOrderFinance.getSendNet()));
				}
			}
		}
		
		// 创建时间
		costOrderFinance.setCreateDate(new Date());
		// 创建人
		costOrderFinance.setCreateUserId(userId);
		this.addCostOrderFinance(costOrderFinance);
	}
	@OrgLinkData(idName="costOrderFinId",tabComment="费用对账")
	public CostOrderFinance addCostOrderFinance(CostOrderFinance costOrderFinance) {
		// 保存短驳运单财务表
		// 生成id
		costOrderFinance.setCostOrderFinId(AppUtils.randomUUID());
		costOrderFinanceMapper.addCostOrderFinance(costOrderFinance);
		return costOrderFinance;		
	}

}
