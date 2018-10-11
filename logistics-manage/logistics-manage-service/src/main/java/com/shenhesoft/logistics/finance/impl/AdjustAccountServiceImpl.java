package com.shenhesoft.logistics.finance.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.finance.AdjustAccount;
import com.shenhesoft.logistics.finance.AdjustAccountService;
import com.shenhesoft.logistics.finance.FinanceAccountDetail;
import com.shenhesoft.logistics.finance.mapper.AdjustAccountMapper;
import com.shenhesoft.logistics.finance.mapper.FinanceAccountDetailMapper;
import com.shenhesoft.logistics.manage.mapper.TbFinanceAccountMapper;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 财务调整 信息表  业务实现.
 * <p>
 * <a href="AdjustAccountServiceImpl.java"><i>View Source</i></a>
 * </p>
 * @author shilvfei
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class AdjustAccountServiceImpl implements AdjustAccountService {

	@Autowired
	private AdjustAccountMapper adjustAccountMapper;
	
	@Autowired
	private TbFinanceAccountMapper financeAccountMapper;
	
	@Autowired
	private FinanceAccountDetailMapper financeAccountDetailMapper;
	
	@OrgLinkData(idName="adjustAccountId",tabComment="财务调整表")
	public AdjustAccount addAdjustAccount(AdjustAccount adjustAccount, TbSystemUser user) {
		
		//调整金额
	    BigDecimal adjustMoney = adjustAccount.getAdjustMoney() == null ? new BigDecimal(0) :  adjustAccount.getAdjustMoney() ;
		
		//支出方账户
		TbFinanceAccount expenditureAccount = financeAccountMapper.selectByPrimaryKey(adjustAccount.getExpenditureAccountId());
		if(expenditureAccount!=null){
			BigDecimal expenditureAccountBalance = expenditureAccount.getAccountBalance() == null ? new BigDecimal(0) : expenditureAccount.getAccountBalance()  ;
			expenditureAccount.setAccountBalance(expenditureAccountBalance.subtract(adjustMoney));
			financeAccountMapper.updateByPrimaryKeySelective(expenditureAccount);
		}
		
		//存入方账户
		TbFinanceAccount depositAccount = financeAccountMapper.selectByPrimaryKey(adjustAccount.getDepositAccountId());//存入方 id
		if(depositAccount!=null){
			BigDecimal depositAccountBalance = depositAccount.getAccountBalance() == null ? new BigDecimal(0) :  depositAccount.getAccountBalance() ;
			depositAccount.setAccountBalance(depositAccountBalance.add(adjustMoney));
			financeAccountMapper.updateByPrimaryKeySelective(depositAccount);
		}
		
		// 生成id
		adjustAccount.setAdjustAccountId(AppUtils.randomUUID());
		adjustAccount.setCreateBy(user.getId().toString());
		adjustAccount.setCreateDate(new Date());
		adjustAccountMapper.addAdjustAccount(adjustAccount);
		
		//插入收支序时账
		FinanceAccountDetail financeAccountDetail = new FinanceAccountDetail();
		financeAccountDetail.setDepositAccountId(adjustAccount.getDepositAccountId());//存入方 id
		financeAccountDetail.setPayAccountId(adjustAccount.getExpenditureAccountId());
		financeAccountDetail.setMoney(adjustMoney);
		financeAccountDetail.setOperateId(user.getId());
		financeAccountDetail.setPayDate(DateUtils.date2Str(DateUtils.datetimeFormat));
		financeAccountDetail.setStatementNum(adjustAccount.getAdjustAccountId());
		financeAccountDetailMapper.addFinanceAccountDetail(financeAccountDetail);
		return adjustAccount;
	}
	
	
	

	/**
	 * 获取所有财务调整信息表.
	 * 
	 * @return 财务调整信息表分页
	 */
	@Override
	public List<Map<String, Object>> getAdjustAccounts(int start, int pageSize, Map<String, Object> form) {
		form = CollectionUtils.isEmpty(form)?Maps.newHashMap():form;
		PageHelper.offsetPage(start, pageSize);
		return this.getAdjustAccounts(form);
	}

	/**
	 * 获取所有短驳运单财务表.
	 * 
	 * @return 短驳运单财务表
	 */
	public List<Map<String, Object>> getAdjustAccounts(Map<String, Object> form) {
		return adjustAccountMapper.getAdjustAccounts(form);
	}
}
