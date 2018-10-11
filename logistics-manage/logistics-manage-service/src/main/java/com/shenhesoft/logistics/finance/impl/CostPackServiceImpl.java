package com.shenhesoft.logistics.finance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import com.shenhesoft.logistics.business.mapper.BasicDataMapper;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.finance.AdvanceCharge;
import com.shenhesoft.logistics.finance.CostOrderFinance;
import com.shenhesoft.logistics.finance.CostPack;
import com.shenhesoft.logistics.finance.CostPackService;
import com.shenhesoft.logistics.finance.CustomerPack;
import com.shenhesoft.logistics.finance.FinanceAccountDetail;
import com.shenhesoft.logistics.finance.InvoiceService;
import com.shenhesoft.logistics.finance.ShPackOrder;
import com.shenhesoft.logistics.finance.ShortOrderFinance;
import com.shenhesoft.logistics.finance.ShortPack;
import com.shenhesoft.logistics.finance.TbEnterpriseReceivables;
import com.shenhesoft.logistics.finance.mapper.AdvanceSettlementMapper;
import com.shenhesoft.logistics.finance.mapper.CostOrderFinanceMapper;
import com.shenhesoft.logistics.finance.mapper.CostPackMapper;
import com.shenhesoft.logistics.finance.mapper.FinanceAccountDetailMapper;
import com.shenhesoft.logistics.finance.mapper.TbEnterpriseReceivablesMapper;
import com.shenhesoft.logistics.manage.mapper.TbFinanceAccountMapper;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.CodeService;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 短驳运单-业务实现.
 * <p>
 * <a href="CostPackServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author shilvfei
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CostPackServiceImpl implements CostPackService {

	@Autowired
	private CostPackMapper costPackMapper;
	@Autowired
    private InvoiceService invoiceService;
	
	@Autowired
	private CostOrderFinanceMapper costOrderFinanceMapper;
	
	
	@Autowired
	private TbFinanceAccountMapper financeAccountMapper;
	
	@Autowired
	private FinanceAccountDetailMapper financeAccountDetailMapper;
	
	@Autowired
	private AdvanceSettlementMapper advanceSettlementMapper;
	
	@Autowired
	private BasicDataMapper basicDataMapper;
	
	  @Autowired
	  private CodeService codeService;
	@Autowired
	private TbEnterpriseReceivablesMapper enterpriseReceivablesMapper;
	
	/**
	 * 获取所有短驳打包信息表.
	 * 
	 * @return 短驳打包信息表分页
	 */
	@Override
	public List<Map<String, Object>> getCostPacks(int start, int pageSize, Map<String, Object> form) {
		PageHelper.offsetPage(start, pageSize);
		return this.getCostPacks(form);
	}

	/**
	 * 获取所有短驳打包信息表.
	 * 
	 * @return 短驳打包信息表
	 */
	@Override
	public List<Map<String, Object>> getCostPacks(Map<String, Object> form) {
		return costPackMapper.getCostPacks(form);
	}

	@Override
	public void dissolveCostPacksByIds(String costPackIds) {
		if (StringUtil.isEmpty(costPackIds))
			return;
		List<String> costPackIdList = Arrays.asList(costPackIds.trim().split(","));
		CostPack costPack = new CostPack();
		costPack.setDeleteFlag((byte)1);// 已删除
		costPack.setCheckingStatus((byte)3);// 已解包
		costPackMapper.editCostPackByIds(costPack, costPackIdList); 

		List<Map<String, Object>> costPackOrderMapList = costPackMapper.listCostPackOrderByCostPackIds(costPackIdList);
		if (costPackOrderMapList == null || costPackOrderMapList.size() == 0)
			return;
		List<String> shOrderFinIdList = new ArrayList<String>();
		for (Map<String, Object> map : costPackOrderMapList) {
			if (map.get("costOrderFinId") == null)
				continue;
			shOrderFinIdList.add(map.get("costOrderFinId").toString());
		}
		// 修改所有的财务运单状态为未打包
		CostOrderFinance costOrderFinance = new CostOrderFinance();
		costOrderFinance.setPackFlag((byte)0);// 未打包
		costOrderFinanceMapper.editCostOrderFinanceByIds(costOrderFinance, shOrderFinIdList);
	}

	@Override
	public void financeAuditCostPackByIds(String costPackIds ,String flag) {
		TbSystemUser tbSystemUser = AppSession.getCurrentUser();
		if (StringUtil.isEmpty(costPackIds))
			return;
		List<String> shPackIdList = Arrays.asList(costPackIds.trim().split(","));
		CostPack costPack = new CostPack();
		Date  date = new Date();
		if(flag.equals("0")) {
			costPack.setCheckingStatus((byte)1);// 对账财务审核通过
			List<Map<String, Object>> list = costPackMapper.getCostPackByIds(shPackIdList);
			invoiceService.addInvoices(list,"2");
			
			//生成预付款货款抵用
			for (Map<String, Object> map : list) {
				List<Map<String, Object>> costOrderList = costPackMapper.getCostOrderByCostPackId(map);
				TbOrder orderInfo = costPackMapper.getOrderMinimeByCostPackId(map);
				TbOrder orderInfoMax = costPackMapper.getOrderMaxTimeByCostPackId(map);
				AdvanceCharge advance = new AdvanceCharge();
				advance.setProjectId(orderInfo.getProjectId());
				advance.setProjectCode(orderInfo.getProjectCode());
				advance.setBranchId(orderInfo.getBranchId());
				advance.setBranchName(orderInfo.getBranchGroupName());
				advance.setBillName((byte)0);//货款
				advance.setAdvanceType((byte)0);
				advance.setCostPackId(map.get("shPackId").toString());
				advance.setCostPackNum(map.get("costPackCode").toString());
				advance.setEntruckWeight(new BigDecimal(map.get("sendNet").toString()));
				advance.setSumCost(new BigDecimal(map.get("cargoUnitPrice").toString()));
				//计费明细显示账户 ，方便运费和货款用同一个显示
				advance.setPurposeAmount(new BigDecimal(map.get("sendNet").toString()).multiply(new BigDecimal(map.get("cargoUnitPrice").toString())));
				advance.setSerialNumber(map.get("costPackCode").toString());
				advance.setStatus((byte)4);//新增状态 待抵用
				advance.setType((byte)1);//抵用
				advance.setDeleteFlag((byte)0);
				advance.setProduceTime(new Date());
				//起号 止号   最小、最大创建时间 对应的运单编号
				advance.setStartNumber(orderInfo.getOrderCode());
				advance.setEndNumber(orderInfoMax.getOrderCode());
				advance.setSheetNumber(costOrderList.size());
				
				Map<String, Object> requestMap = ImmutableMap.of("sysOrgCode", AppSession.getCurrentSysOrgCode());
	            List<Map<String, Object>> selections = basicDataMapper.getOrgTop(requestMap);
				TbBranchGroup branch = FormUtil.populate(TbBranchGroup.class, selections.get(0), false);
				branch.setName(branch.getText());
				//支出单位
				advance.setPayUnitId(branch.getId());
				advance.setPayUnitName(branch.getName());
				advanceSettlementMapper.addAdvanceCharge(advance);
			}
		}else {
			costPack.setCheckingStatus((byte)2);// 对账财务审核不通过
		}
		costPack.setAuditId(tbSystemUser.getId());
		costPack.setCheckingAuditDate(date);// 财务审核时间
		costPack.setBackId(tbSystemUser.getId());
		costPack.setBackDate(date);
		
		costPackMapper.editCostPackByIds(costPack, shPackIdList);
	}

	@Override
	public void againstAuditCostPackByIds(String costPackIds) {
		if (StringUtil.isEmpty(costPackIds))
			return;
		List<String> shPackIdList = Arrays.asList(costPackIds.trim().split(","));
		CostPack costPack = new CostPack();
		costPack.setCheckingStatus((byte)0);// 对账财务反审核
		// 财务审核时间
		costPack.setCheckingAuditDate(new Date());
		costPackMapper.editCostPackByIds(costPack, shPackIdList);
	}

	@OrgLinkData(idName="shPackId",tabComment="费用对账打包")
	public CostPack addCostPack(CostPack costPack, String shOrderFinIds, TbSystemUser user) {
		if (StringUtil.isEmpty(shOrderFinIds))
			return null;
	    String sysOrgCode=AppSession.getCurrentSysOrgCode();
	    String id = codeService.createCheckFinCode(sysOrgCode, costPack.getProjectId());
	    costPack.setCostPackCode(id);
		// 生成id
		costPack.setShPackId(AppUtils.randomUUID());
		// 对账状态(0-对账待审核 1-对账财务审核通过 2-对账财务审核不通过 3-已解包 ) checking_status
		costPack.setCheckingStatus((byte)0);
		// 现金结算状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过) cash_settle_status
		costPack.setCashSettleStatus((byte)0);
		// 是否删除(0-未删除 1-删除) delete_flag
		costPack.setDeleteFlag((byte)0);
		// 创建时间 create_date
		costPack.setCreateDate(new Date());
		// 创建人 create_user_id
		costPack.setCreateUserId(user.getId());
		//设置发票状态
		costPack.setInvoiceStatus((byte)0);

		BigDecimal sendTare = new BigDecimal(0);//发货皮重
		BigDecimal sendGross = new BigDecimal(0);// 发货毛重
		BigDecimal sendNet = new BigDecimal(0);// 发货净重
		
		// 货物单价
		BigDecimal cargoUnitPrice =  costPack.getCargoPrice();
		costPack.setCargoUnitPrice(cargoUnitPrice);
		
		//货物总价格
		BigDecimal cargoPrice = new  BigDecimal(0);

		// 保存司机打包运单信息中间表
		String[] arrShOrderFinId = shOrderFinIds.trim().split(",");
		for (String shOrderFinId : arrShOrderFinId) {
			if (StringUtil.isEmpty(shOrderFinId))
				continue;
			// 查询短驳运单财务信息
			Map<String, Object> costOrderFinanceMap = costOrderFinanceMapper
					.getCostOrderFinanceById(shOrderFinId.trim());
			if(costOrderFinanceMap == null)
				continue;
			//当前运单发货皮重
			BigDecimal nowSendTare = null==costOrderFinanceMap.get("sendTare")?new BigDecimal(0):
				(BigDecimal)costOrderFinanceMap.get("sendTare");
			//当前运单发货毛重
			BigDecimal nowSendGross = null==costOrderFinanceMap.get("sendGross")?new BigDecimal(0):
				(BigDecimal)costOrderFinanceMap.get("sendGross");
			//当前运单发货净重
			BigDecimal nowSendNet = null==costOrderFinanceMap.get("sendNet")?new BigDecimal(0):
				(BigDecimal)costOrderFinanceMap.get("sendNet");
			//当前运单发货净重
			BigDecimal nowcargoPrice = null==costOrderFinanceMap.get("cargoPrice")?new BigDecimal(0):
				(BigDecimal)costOrderFinanceMap.get("cargoPrice");
			
			sendTare = sendTare.add(nowSendTare);
			sendGross = sendGross.add(nowSendGross);
			sendNet = sendNet.add(nowSendNet);
			cargoPrice = cargoPrice.add(nowcargoPrice);
			
			// 标注运单已打包
			CostOrderFinance costOrderFinance = new CostOrderFinance();
			costOrderFinance.setPackFlag((byte)1);// 已打包
			costOrderFinance.setCostOrderFinId(shOrderFinId);
			costOrderFinance.setTbCostPackId(costPack.getShPackId());
			costOrderFinanceMapper.editCostOrderFinanceById(costOrderFinance);
		}

		// 发货皮重
		costPack.setSendTare(sendTare);
		// 发货毛重
		costPack.setSendGross(sendGross);
		// 发货净重
		costPack.setSendNet(sendNet);
		//货物价格
		costPack.setCargoPrice(cargoPrice);
		costPack.setTaxMoney(costPack.getCargoPrice().multiply(costPack.getTaxRate()).divide(new BigDecimal("100")));
		
		costPack.setBesettledMoney(cargoPrice.add(costPack.getTaxMoney()));
		
		costPackMapper.addCostPack(costPack);

		return costPack;
	}

	//@OrgLinkData(idName="shPackId",tabComment="企业应收款结算")
	public void settlePassCostPackByIds(CostPack costPack) {
		if (StringUtil.isEmpty(costPack.getShPackId()))
			return;
		Date date = new Date();
		TbSystemUser user = AppSession.getCurrentUser();
		// 结算审核状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)
		costPack.setCashSettleStatus((byte)1);
		costPack.setSettleId(user.getId().toString());
		costPack.setSettleDate(date);
		costPackMapper.editCostPackById(costPack);
		
		//新建企业应收款结算的信息
		TbEnterpriseReceivables enterpriseReceivables = new TbEnterpriseReceivables();
		enterpriseReceivables.setEnterpriseReceivablesId(AppUtils.randomUUID());
		enterpriseReceivables.setCreateDate(date);
		enterpriseReceivables.setAuditStatus(0);//未审核
		enterpriseReceivables.setSettleDate(date);
		enterpriseReceivables.setSettleMoney(costPack.getSettleMoney());//本次结算金额
		enterpriseReceivables.setCostPackId(costPack.getShPackId());//打包的id
		enterpriseReceivables.setSettleUserId(AppSession.getCurrentUserId().toString());
		enterpriseReceivables.setReceiveAccount(costPack.getReceiveAccountId() ==null ? null : costPack.getReceiveAccountId().toString());//收款账户
		enterpriseReceivablesMapper.insertSelective(enterpriseReceivables);
	}

	@Override
	public void settleFinanceAuditCostPackByIds(String shPackIds, String passFlag) {
		if (StringUtil.isEmpty(shPackIds) || StringUtil.isEmpty(passFlag))
			return;
		//List<String> custPackIdList = Arrays.asList(custPackIds.trim().split(","));
		CostPack costPack = new CostPack();
		TbSystemUser user = AppSession.getCurrentUser();
		//查询当前结算金额、已结算金额等
		Map<String,Object> map = costPackMapper.queryAuditMoney(shPackIds);
		String settleMoneyStr = FormUtil.getMapValue(map, "settleMoney");
		String settledMoneyStr = FormUtil.getMapValue(map, "settledMoney");
		String besettledMoneyStr = FormUtil.getMapValue(map, "besettledMoney");
		String settleTypeStr = FormUtil.getMapValue(map, "settleType");
		
		BigDecimal settleMoney = settleMoneyStr == null ? new BigDecimal("0") : new BigDecimal(settleMoneyStr.trim());
		BigDecimal settledMoney = settledMoneyStr == null ? new BigDecimal("0") : new BigDecimal(settledMoneyStr.trim());
		BigDecimal besettledMoney = besettledMoneyStr == null ? new BigDecimal("0") : new BigDecimal(besettledMoneyStr.trim());

		int settleType = Integer.valueOf(settleTypeStr);
		besettledMoney = besettledMoney.subtract(settleMoney);
		settledMoney = settledMoney.add(settleMoney);
		costPack.setShPackId(shPackIds);
		costPack.setSettleAuditUid(user.getId());
		costPack.setSettleAuditDate(new Date());
		if (passFlag.trim().equals("0")) {// 审核通过
			// 结算审核状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)
			costPack.setCashSettleStatus((byte)2);
			costPack.setSettleType(settleType);
			costPack.setSettleMoney(settleMoney);
			costPack.setSettledMoney(settledMoney);
			costPack.setBesettledMoney(besettledMoney);
			
			//插入收支序时账
			FinanceAccountDetail financeAccountDetail = new FinanceAccountDetail();
			String receiveAccountIdStr = FormUtil.getMapValue(map, "receiveAccountId");
			if(receiveAccountIdStr!=null&&receiveAccountIdStr!=""){
				financeAccountDetail.setDepositAccountId(Integer.valueOf(receiveAccountIdStr));
			}
			financeAccountDetail.setMoney(settleMoney);
			financeAccountDetail.setOperateId(user.getId());
			financeAccountDetail.setPayDate(DateUtils.date2Str(DateUtils.datetimeFormat));
			financeAccountDetail.setStatementNum(shPackIds);
			financeAccountDetailMapper.addFinanceAccountDetail(financeAccountDetail);
		} else if (passFlag.trim().equals("1")) {//审核不通过
			costPack.setCashSettleStatus((byte)3);
		}
		int row = costPackMapper.editCostPackById(costPack);
		
		if(row!=0){
			//更新收款账户余额
			Integer receiveAccountId = Integer.valueOf( FormUtil.getMapValue(map, "receiveAccountId"));
			
			TbFinanceAccount financeAccount = financeAccountMapper.selectByPrimaryKey(receiveAccountId);
			
			if(financeAccount!=null){
				BigDecimal accountBalance =  financeAccount.getAccountBalance() == null ? new BigDecimal(0) : financeAccount.getAccountBalance();
				financeAccount.setAccountBalance(accountBalance.add(settleMoney));//加上本次结算金额
				financeAccountMapper.updateByPrimaryKeySelective(financeAccount);
			}
		}
	}

	@Override
	public void settleAgainstAuditCostPackByIds(String shPackIds) {
		if (StringUtil.isEmpty(shPackIds))
			return;
		CostPack costPack = new CostPack();
		TbSystemUser user = AppSession.getCurrentUser();
		//查询当前结算金额、已结算金额等
		Map<String,Object> map = costPackMapper.queryAuditMoney(shPackIds);
		String settleMoneyStr = FormUtil.getMapValue(map, "settleMoney");
		String settledMoneyStr = FormUtil.getMapValue(map, "settledMoney");
		String besettledMoneyStr = FormUtil.getMapValue(map, "besettledMoney");
		
		BigDecimal settleMoney = settleMoneyStr == null ? new BigDecimal("0") : new BigDecimal(settleMoneyStr.trim());
		BigDecimal settledMoney = settledMoneyStr == null ? new BigDecimal("0") : new BigDecimal(settledMoneyStr.trim());
		BigDecimal besettledMoney = besettledMoneyStr == null ? new BigDecimal("0") : new BigDecimal(besettledMoneyStr.trim());
		
		besettledMoney = besettledMoney.add(settleMoney);
		settledMoney = settledMoney.subtract(settleMoney);
		costPack.setShPackId(shPackIds);
		costPack.setSettleAuditUid(user.getId());
		costPack.setSettleAuditDate(new Date());
		
		// 结算审核状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)
		costPack.setCashSettleStatus((byte)1);
		int row = costPackMapper.editCostPackById(costPack);
		
		if(row!=0){
			//更新收款账户余额
			Integer receiveAccountId = Integer.valueOf( FormUtil.getMapValue(map, "receiveAccountId"));
			
			TbFinanceAccount financeAccount = financeAccountMapper.selectByPrimaryKey(receiveAccountId);
			
			if(financeAccount!=null){
				BigDecimal accountBalance =  financeAccount.getAccountBalance() == null ? new BigDecimal(0) : financeAccount.getAccountBalance();
				financeAccount.setAccountBalance(accountBalance.subtract(settleMoney));//减去本次结算金额
				financeAccountMapper.updateByPrimaryKeySelective(financeAccount);
			}
		}
		
	}
	
}
