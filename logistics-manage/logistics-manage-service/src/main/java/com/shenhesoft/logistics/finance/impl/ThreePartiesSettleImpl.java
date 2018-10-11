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
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.finance.CustomerPack;
import com.shenhesoft.logistics.finance.CustomerPackService;
import com.shenhesoft.logistics.finance.FinanceAccountDetail;
import com.shenhesoft.logistics.finance.ThreePartiesReceivables;
import com.shenhesoft.logistics.finance.ThreePartiesSettle;
import com.shenhesoft.logistics.finance.ThreePartiesSettleService;
import com.shenhesoft.logistics.finance.mapper.CustomerPackMapper;
import com.shenhesoft.logistics.finance.mapper.FinanceAccountDetailMapper;
import com.shenhesoft.logistics.finance.mapper.ThreePartiesReceivablesMapper;
import com.shenhesoft.logistics.finance.mapper.ThreePartiesSettleMapper;
import com.shenhesoft.logistics.manage.mapper.TbFinanceAccountMapper;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 三方结算信息表-业务层接口.
 * <p>
 * <a href="ThreePartiesSettleImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author shilvfei
 * @date 2018-01-19
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class ThreePartiesSettleImpl implements ThreePartiesSettleService {

	@Autowired
	private ThreePartiesSettleMapper threePartiesSettleMapper;
	
	@Autowired
	private ThreePartiesReceivablesMapper threePartiesReceivablesMapper;
	
	@Autowired
	private CustomerPackMapper customerPackMapper;;
	
	@Autowired
	private TbFinanceAccountMapper financeAccountMapper;
	
	@Autowired
	private CustomerPackService customerPackService;
	
	@Autowired
	private FinanceAccountDetailMapper financeAccountDetailMapper;
	
	@OrgLinkData(tabComment="三方应收款结算")
	public ThreePartiesSettle addThreePartiesSettle(ThreePartiesSettle threePartiesSettle, TbSystemUser user) {
		// 生成id
		threePartiesSettle.setId(AppUtils.randomUUID());
		threePartiesSettle.setCreateBy(user.getId().toString());
		threePartiesSettle.setCreateDate(new Date());
		threePartiesSettle.setSettleStatus("0");//未审核
	    // 保存三方应收款表
		threePartiesSettleMapper.addThreePartiesSettle(threePartiesSettle);
	   
		/*//更新客户cust_pack 表
		CustomerPack customerPack = new CustomerPack();
		customerPack.setCustPackId(threePartiesSettle.getCustPackId());
		//设置本次结算金额
		customerPack.setSettleAuditType(1);//结算待审核
		customerPack.setSettleType(1);//银行卡
		customerPackMapper.editCustomerPackById(customerPack);*/
		ThreePartiesReceivables threePartiesReceivables = new ThreePartiesReceivables();
		threePartiesReceivables.setId(threePartiesSettle.getThreePartiesReceivablesId());
		threePartiesReceivables.setSettleMoney(threePartiesSettle.getSettleMoney());
		threePartiesReceivables.setStatus(1);
		threePartiesReceivablesMapper.updateByPrimaryKeySelective(threePartiesReceivables);
		return threePartiesSettle;
	}

	@Override
	public List<Map<String, Object>> getThreePartiesSettle(int start, int pageSize, Map<String, Object> form) {
		PageHelper.offsetPage(start, pageSize);
		return this.getThreePartiesSettle(form);
	}

	@Override
	public List<Map<String, Object>> getThreePartiesSettle(Map<String, Object> form) {
		return threePartiesSettleMapper.getThreePartiesSettle(form);
	}

	@Override
	public void financeAuditShortPackByIds(String settleIds) {
		if (StringUtil.isEmpty(settleIds))
			return;
		List<String> settleIdList = Arrays.asList(settleIds.trim().split(","));

		//获取本次结算的金额
		BigDecimal totalSettleMoney = new BigDecimal(0);
		
		List<ThreePartiesSettle> threePartiesSettles = threePartiesSettleMapper.getThreePartiesSettleByIds(settleIdList);
		
		if(threePartiesSettles== null || threePartiesSettles.size()==0){
			return;
		}
		
		Integer userId = AppSession.getCurrentUserId();
		
		String threePartiesReceivablesId = null ;
		
		for (ThreePartiesSettle threePartiesSettle : threePartiesSettles) {
			threePartiesReceivablesId = threePartiesSettle.getThreePartiesReceivablesId();
			BigDecimal settleMoney = threePartiesSettle.getSettleMoney() == null ? new BigDecimal(0) : threePartiesSettle.getSettleMoney();
			threePartiesSettle.setSettleStatus("1");//已审核
			totalSettleMoney = totalSettleMoney.add(settleMoney);
			threePartiesSettle.setAuitBy(userId.toString());
			threePartiesSettle.setAuitDate(new Date());
			threePartiesSettleMapper.editThreePartiesSettle(threePartiesSettle);
			//获取支出账户
			TbFinanceAccount payAccount = financeAccountMapper.selectByPrimaryKey(threePartiesSettle.getPayAccountId());
			if(payAccount!=null){
				BigDecimal payAccountBalance = payAccount.getAccountBalance() == null ? new BigDecimal(0) : payAccount.getAccountBalance();//获取账户余额
				payAccount.setAccountBalance(payAccountBalance.subtract(settleMoney));//减少账户余额
			}
			financeAccountMapper.updateByPrimaryKeySelective(payAccount);
			
			//获取存入账户
			TbFinanceAccount receiveAccount = financeAccountMapper.selectByPrimaryKey(threePartiesSettle.getReceiveAccountId());
			if(receiveAccount!=null){
				BigDecimal receiveAccountBalance = receiveAccount.getAccountBalance() == null ? new BigDecimal(0) : receiveAccount.getAccountBalance();//获取账户余额
				receiveAccount.setAccountBalance(receiveAccountBalance.add(settleMoney));//增加账户余额
			}
			financeAccountMapper.updateByPrimaryKeySelective(receiveAccount);
			
			FinanceAccountDetail financeAccountDetail = new FinanceAccountDetail();
			financeAccountDetail.setDepositAccountId(receiveAccount.getId());
			financeAccountDetail.setPayAccountId(payAccount.getId());
			financeAccountDetail.setMoney(threePartiesSettle.getSettleMoney());
			financeAccountDetail.setOperateId(userId);
			financeAccountDetail.setPayDate(DateUtils.date2Str(DateUtils.datetimeFormat));
			financeAccountDetail.setStatementNum(threePartiesReceivablesId);
			financeAccountDetailMapper.addFinanceAccountDetail(financeAccountDetail);
			
			ThreePartiesReceivables threePartiesReceivables = new ThreePartiesReceivables();
			threePartiesReceivables.setId(threePartiesSettle.getThreePartiesReceivablesId());
			threePartiesReceivables.setSettleMoney(threePartiesSettle.getSettleMoney());
			BigDecimal settledMoney  = threePartiesReceivables.getSettledMoney() ==null ? new BigDecimal(0):threePartiesReceivables.getSettledMoney();
			threePartiesReceivables.setSettledMoney(settledMoney.add(settleMoney));
			BigDecimal besettledMoney  = threePartiesReceivables.getBesettledMoney() ==null ? new BigDecimal(0):threePartiesReceivables.getBesettledMoney();
			threePartiesReceivables.setBesettledMoney(besettledMoney.subtract(settleMoney));
			threePartiesReceivables.setStatus(2);
			threePartiesReceivablesMapper.updateByPrimaryKeySelective(threePartiesReceivables);
		}
		
		/*//更新客户cust_pack 表
		CustomerPack customerPack = new CustomerPack();
		customerPack.setCustPackId(custPackId);
		//设置本次结算金额
		customerPack.setSettleMoney(totalSettleMoney);
		customerPackMapper.editCustomerPackById(customerPack);
		
		customerPackService.editSettleFinanceAuditCustomerPackById(custPackId, "0");*/
		
		
		
		/*//减少待结金额
		Map<String, Object> customerPack = customerPackMapper.getCustomerPackById(custPackId);
		//增加已结金额
		customerPack.get("");*/
	}

	@Override
	public void againstAuditShortPackByIds(String settleIds) {
		if (StringUtil.isEmpty(settleIds))
			return;
		List<String> settleIdList = Arrays.asList(settleIds.trim().split(","));
		//ThreePartiesSettle threePartiesSettle = new ThreePartiesSettle();
		//threePartiesSettle.setSettleStatus("1");// 对账已审核
		//threePartiesSettleMapper.editThreePartiesSettleByIds(threePartiesSettle, settleIdList);
	
		//获取本次结算的金额
		BigDecimal totalSettleMoney = new BigDecimal(0);
		
		List<ThreePartiesSettle> threePartiesSettles = threePartiesSettleMapper.getThreePartiesSettleByIds(settleIdList);
		
		if(threePartiesSettles== null || threePartiesSettles.size()==0){
			return;
		}
		
		Integer userId = AppSession.getCurrentUserId();
		
		String custPackId = null ;
		
		for (ThreePartiesSettle threePartiesSettle : threePartiesSettles) {
			custPackId = threePartiesSettle.getThreePartiesReceivablesId();
			BigDecimal settleMoney = threePartiesSettle.getSettleMoney() == null ? new BigDecimal(0) : threePartiesSettle.getSettleMoney();
			threePartiesSettle.setSettleStatus("0");
			totalSettleMoney = totalSettleMoney.add(settleMoney);
			threePartiesSettle.setAuitBy(userId.toString());
			threePartiesSettle.setAuitDate(new Date());
			threePartiesSettleMapper.editThreePartiesSettle(threePartiesSettle);
			//获取支出账户
			TbFinanceAccount payAccount = financeAccountMapper.selectByPrimaryKey(threePartiesSettle.getPayAccountId());
			if(payAccount!=null){
				BigDecimal payAccountBalance = payAccount.getAccountBalance() == null ? new BigDecimal(0) : payAccount.getAccountBalance();//获取账户余额
				payAccount.setAccountBalance(payAccountBalance.add(settleMoney));//增加账户余额
			}
			financeAccountMapper.updateByPrimaryKeySelective(payAccount);
			//获取存入账户
			TbFinanceAccount receiveAccount = financeAccountMapper.selectByPrimaryKey(threePartiesSettle.getReceiveAccountId());
			if(receiveAccount!=null){
				BigDecimal receiveAccountBalance = receiveAccount.getAccountBalance() == null ? new BigDecimal(0) : receiveAccount.getAccountBalance();//获取账户余额
				receiveAccount.setAccountBalance(receiveAccountBalance.subtract(settleMoney));//增加账户余额
			}
			financeAccountMapper.updateByPrimaryKeySelective(receiveAccount);
			
			ThreePartiesReceivables threePartiesReceivables = new ThreePartiesReceivables();
			threePartiesReceivables.setId(threePartiesSettle.getThreePartiesReceivablesId());
			threePartiesReceivables.setSettleMoney(threePartiesSettle.getSettleMoney());
			BigDecimal settledMoney  = threePartiesReceivables.getSettledMoney() ==null ? new BigDecimal(0):threePartiesReceivables.getSettledMoney();
			threePartiesReceivables.setSettledMoney(settledMoney.subtract(settleMoney));
			BigDecimal besettledMoney  = threePartiesReceivables.getBesettledMoney() ==null ? new BigDecimal(0):threePartiesReceivables.getBesettledMoney();
			threePartiesReceivables.setBesettledMoney(besettledMoney.add(settleMoney));
			threePartiesReceivables.setStatus(1);
			threePartiesReceivablesMapper.updateByPrimaryKeySelective(threePartiesReceivables);
		}
	/*	//更新客户cust_pack 表
		CustomerPack customerPack = new CustomerPack();
		customerPack.setCustPackId(custPackId);
		//设置本次结算金额
		customerPack.setSettleMoney(totalSettleMoney);
		customerPackMapper.editCustomerPackById(customerPack);
		
		customerPackService.editSettleAgainstAuditCustomerPackById(custPackId);*/
	}

}
