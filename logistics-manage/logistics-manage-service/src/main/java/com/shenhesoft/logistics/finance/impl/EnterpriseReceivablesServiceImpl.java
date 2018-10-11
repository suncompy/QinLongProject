package com.shenhesoft.logistics.finance.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.finance.CostPack;
import com.shenhesoft.logistics.finance.EnterpriseReceivablesService;
import com.shenhesoft.logistics.finance.FinanceAccountDetail;
import com.shenhesoft.logistics.finance.TbEnterpriseReceivables;
import com.shenhesoft.logistics.finance.mapper.CostPackMapper;
import com.shenhesoft.logistics.finance.mapper.FinanceAccountDetailMapper;
import com.shenhesoft.logistics.finance.mapper.TbEnterpriseReceivablesMapper;
import com.shenhesoft.logistics.manage.mapper.TbFinanceAccountMapper;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description
 * 
 * @author shilvfei
 * 
 * @date 2018年4月24日
 */
@Service
public class EnterpriseReceivablesServiceImpl implements EnterpriseReceivablesService {

	@Autowired
	private TbEnterpriseReceivablesMapper enterpriseReceivablesMapper;
	
	@Autowired
	private CostPackMapper costPackMapper;
	
	@Autowired
	private TbFinanceAccountMapper financeAccountMapper;
	
	@Autowired
	private FinanceAccountDetailMapper financeAccountDetailMapper;
	
	@Override
	public TbEnterpriseReceivables addEnterpriseReceivables(TbEnterpriseReceivables enterpriseReceivables,
			TbSystemUser user) {
		enterpriseReceivablesMapper.insert(enterpriseReceivables);
		return enterpriseReceivables;
	}

	@Override
	public List<Map<String, Object>> getEnterpriseReceivables(int start, int pageSize, Map<String, Object> map) {
		PageHelper.startPage(start, pageSize);
		return this.getEnterpriseReceivables(map);
	}

	@Override
	public List<Map<String, Object>> getEnterpriseReceivables(Map<String, Object> map) {
		return enterpriseReceivablesMapper.getEnterpriseReceivables(map);
	}

	@Override
	public void settleFinanceAuditCostPackByIds(String enterpriseReceivablesIds, String passFlag) {
		if (StringUtil.isEmpty(enterpriseReceivablesIds) || StringUtil.isEmpty(passFlag))
			return;
		List<String> enterpriseReceivablesIdList = Arrays.asList(enterpriseReceivablesIds.trim().split(","));
		
		
		Date date = new Date();
		String costPackId = null;
		
		BigDecimal sumMoney = new BigDecimal("0");
		
		for (String enterpriseReceivablesId : enterpriseReceivablesIdList) {
			
			TbEnterpriseReceivables enterpriseReceivables = enterpriseReceivablesMapper.selectByPrimaryKey(enterpriseReceivablesId);
			
			if(enterpriseReceivables!=null){
				if(costPackId==null){
					costPackId= enterpriseReceivables.getCostPackId();
				}
				enterpriseReceivables.setAuditStatus(1);//已审核
				enterpriseReceivables.setAuditDate(date);
				enterpriseReceivables.setAuditUserId(AppSession.getCurrentUserId().toString());
				BigDecimal settleMoney = enterpriseReceivables.getSettleMoney() == null ? new BigDecimal("0"):enterpriseReceivables.getSettleMoney() ;
				sumMoney = sumMoney.add(settleMoney);
				
				int row = enterpriseReceivablesMapper.updateByPrimaryKeySelective(enterpriseReceivables);
				
				if(row!=0){
					//收款账户
					String receiveAccountId = enterpriseReceivables.getReceiveAccount();
					//更新收款账户余额
					TbFinanceAccount financeAccount = financeAccountMapper.selectByPrimaryKey(Integer.valueOf(receiveAccountId));
					if(financeAccount!=null){
						BigDecimal accountBalance =  financeAccount.getAccountBalance() == null ? new BigDecimal(0) : financeAccount.getAccountBalance();
						financeAccount.setAccountBalance(accountBalance.add(settleMoney));//加上本次结算金额
						financeAccountMapper.updateByPrimaryKeySelective(financeAccount);
					}
				}
			}
		}
		
		CostPack costPack = new CostPack();
		TbSystemUser user = AppSession.getCurrentUser();
		//查询当前结算金额、已结算金额等
		Map<String,Object> map = costPackMapper.queryAuditMoney(costPackId);
		String settledMoneyStr = FormUtil.getMapValue(map, "settledMoney");
		String besettledMoneyStr = FormUtil.getMapValue(map, "besettledMoney");
		String settleTypeStr = FormUtil.getMapValue(map, "settleType");
		
		BigDecimal settleMoney = sumMoney;
		BigDecimal settledMoney = settledMoneyStr == null ? new BigDecimal("0") : new BigDecimal(settledMoneyStr.trim());
		BigDecimal besettledMoney = besettledMoneyStr == null ? new BigDecimal("0") : new BigDecimal(besettledMoneyStr.trim());

		int settleType = Integer.valueOf(settleTypeStr);
		besettledMoney = besettledMoney.subtract(settleMoney);
		settledMoney = settledMoney.add(settleMoney);
		costPack.setShPackId(costPackId);
		costPack.setSettleAuditUid(user.getId());
		costPack.setSettleAuditDate(date);
		if (passFlag.trim().equals("0")) {// 审核通过
			// 结算审核状态(0-待结算 1-结算财务待审核 2-结算审核通过 3-结算审核不通过)
			//costPack.setCashSettleStatus((byte)2);
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
			financeAccountDetail.setStatementNum(costPackId);
			financeAccountDetailMapper.addFinanceAccountDetail(financeAccountDetail);
		} else if (passFlag.trim().equals("1")) {//审核不通过
			costPack.setCashSettleStatus((byte)3);
		}
		costPackMapper.editCostPackById(costPack);
	}
	
}
