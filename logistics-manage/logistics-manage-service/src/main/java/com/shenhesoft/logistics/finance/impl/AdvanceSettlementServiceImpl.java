package com.shenhesoft.logistics.finance.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderMapper;
import com.shenhesoft.logistics.business.pojo.trainOrder.TbTrainOrder;
import com.shenhesoft.logistics.common.exception.ParameterException;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.PleaseCarNumUtil;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.finance.AdvanceCharge;
import com.shenhesoft.logistics.finance.AdvanceSettlementService;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.FinanceAccountDetail;
import com.shenhesoft.logistics.finance.mapper.AdvanceSettlementMapper;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.finance.mapper.FinanceAccountDetailMapper;
import com.shenhesoft.logistics.manage.mapper.TbFinanceAccountMapper;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbAccountRecordDetail;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;

/**
 * 预付款结算-业务实现.
 * <p>
 * <a href="CostOrderFinanceServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiangDeng
 * @date 2018-2-1
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class AdvanceSettlementServiceImpl implements AdvanceSettlementService{

	@Autowired
	private AdvanceSettlementMapper advanceSettlementMapper;
	
	@Autowired
	private TbFinanceAccountMapper financeAccountMapper;
	
	@Autowired
	private TbTrainOrderMapper trainOrderMapper;
	
	@Autowired
	private FinanceAccountDetailMapper financeAccountDetailMapper;
	
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	@Override
	public List<Map<String, Object>> getProjectAdvance(int start, int pageSize, Map<String, Object> innerMap) {
		PageHelper.offsetPage(start, pageSize);
		return this.getProjectAdvance(innerMap);
	}

	public List<Map<String, Object>> getProjectAdvance(Map<String, Object> form) {
		return advanceSettlementMapper.getProjectAdvance(form);
	}

	@Override
	public List<Map<String, Object>> getProjectAdvanceByProjectId(int start, int pageSize, Map<String, Object> innerMap) {
		PageHelper.offsetPage(start, pageSize);
		return this.getProjectAdvanceByProjectId(innerMap);
	}
	
	public List<Map<String, Object>> getProjectAdvanceByProjectId(Map<String, Object> form) {
		return advanceSettlementMapper.getProjectAdvanceByProjectId(form);
	}

	@Override
	public List<Map<String, Object>> getAccountByUnitId(Map<String, Object> map) {
		return advanceSettlementMapper.getAccountByUnitId(map);
	}

	@Override
	public Map<String, Object> getAccountDetailById(Map<String, Object> map) {
		return advanceSettlementMapper.getAccountDetailById(map);
	}

	@Override
	public AdvanceCharge addAdvanceCharge(AdvanceCharge advanceCharge) {
		if(advanceCharge.getDepositAmount() != null && advanceCharge.getPurposeAmount() == null && advanceCharge.getCashAmount() == null) {
			//存入 的是中心账户  校验只能存一个中心站点下的一个账户 可多次存入
			if(advanceCharge.getReceiveType() != null && advanceCharge.getReceiveType() == 1) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("projectId", advanceCharge.getProjectId());
				AdvanceCharge checAdv = advanceSettlementMapper.selectExistAdvanceCharge(map2);
				if(checAdv != null) {
					if((!checAdv.getReceiveUnitId().equals(advanceCharge.getReceiveUnitId())) || (!checAdv.getReceiveAccountId().equals(advanceCharge.getReceiveAccountId()))) {
						throw new ParameterException("该项目已经存入，请选择"+" "+checAdv.getReceiveUnitName()+" "+"下"+" "+checAdv.getReceiveAccountName()+" "+"账户进行存入");
					}
				}
			}
			advanceCharge.setStatus((byte)1);
			advanceCharge.setType((byte)0);//存入
			advanceCharge.setOperationDate(new Date());
			advanceCharge.setOperationPerson(AppSession.getCurrentUser().getName());
			advanceCharge.setDeleteFlag((byte)0);
			advanceCharge.setSerialNumber(PleaseCarNumUtil.serialNum());
			advanceCharge.setProduceTime(new Date());
			advanceSettlementMapper.addAdvanceCharge(advanceCharge);
		}else if(advanceCharge.getDepositAmount() == null && advanceCharge.getPurposeAmount() != null && advanceCharge.getCashAmount() == null){
			advanceCharge.setStatus((byte)1);
			advanceCharge.setType((byte)1);//抵用
			advanceCharge.setOperationDate(new Date());
			advanceCharge.setOperationPerson(AppSession.getCurrentUser().getName());
			advanceSettlementMapper.updateAdvanceCharge(advanceCharge);
		}else if(advanceCharge.getDepositAmount() == null && advanceCharge.getPurposeAmount() == null && advanceCharge.getCashAmount() != null) {
			advanceCharge.setStatus((byte)1);
			advanceCharge.setType((byte)3);//提现
			advanceCharge.setOperationDate(new Date());
			advanceCharge.setOperationPerson(AppSession.getCurrentUser().getName());
			advanceCharge.setDeleteFlag((byte)0);
			advanceCharge.setSerialNumber(PleaseCarNumUtil.serialNum());
			advanceCharge.setProduceTime(new Date());
			advanceSettlementMapper.addAdvanceCharge(advanceCharge);
		}
		
		return advanceCharge;
	}

	@Override
	public void accountAuditStatusByIds(String accountChargeIds,String flag) {
		if(StringUtil.isEmpty(accountChargeIds))
			return;
		List<String> accountIdList = Arrays.asList(accountChargeIds.trim().split(","));
		if(flag.equals("0")) {
			//更新账户余额
			for (String accountId : accountIdList) {
				Map<String,Object> accountMap = advanceSettlementMapper.getAdvanceChargeDetailByid(Integer.valueOf(accountId));
				Integer idI = (Integer) accountMap.get("receiveAccountId");
				Integer idO = (Integer) accountMap.get("payAccountId");
				Integer idP = (Integer) accountMap.get("purposeChooseAccount");
				Integer type = (Integer) accountMap.get("type");
				//抵用审核 是从已经存入的账号减余额
				BigDecimal nBalance =  (BigDecimal) accountMap.get("depositAmount");
				BigDecimal nBalance2 =  (BigDecimal) accountMap.get("purposeAmount");
				BigDecimal nBalance3 =  (BigDecimal) accountMap.get("cashAmount");
				// 1 抵用
				if(type == 1) {
					Map<String, Object> map2 = new HashMap<String, Object>();
					Integer projectId = (Integer) accountMap.get("projectId");
					Integer advancetType = (Integer) accountMap.get("advanceType");
					map2.put("projectId", projectId);
					map2.put("advanceType", advancetType);
					map2.put("id", idO);
					Map<String, Object> form = advanceSettlementMapper.getDepostAccountByProjectIdDetail(map2);
					BigDecimal checkBlance =  (BigDecimal) form.get("depositAmount"); 
					int rc = checkBlance.compareTo(nBalance2);
					if(rc == -1) {
						//余额不足
						throw new ParameterException("支出账户余额不足");
					}
					
					TbFinanceAccount faP = financeAccountMapper.selectByPrimaryKey(idO);
					faP.setAccountBalance(faP.getAccountBalance().subtract(nBalance2));
					financeAccountMapper.updateByPrimaryKeySelective(faP);
					
					//插入记录tb_finance_account_detail 收支序数据 accountMap
					payAndReceiveRecore(accountMap);
				}else if(type == 0){
					//存入
					
					//支出账户 减余额
					TbFinanceAccount faO = financeAccountMapper.selectByPrimaryKey(idO);
					//compareTo方法，-1小于，0相等，1大于
					int r = faO.getAccountBalance().compareTo(nBalance);
					if(r == -1) {
						//余额不足
						throw new ParameterException("支出账户余额不足");
					}else {
						faO.setAccountBalance(faO.getAccountBalance().subtract(nBalance));
						financeAccountMapper.updateByPrimaryKeySelective(faO);
					}
					//存入账户 加余额
					TbFinanceAccount faI = financeAccountMapper.selectByPrimaryKey(idI);
					faI.setAccountBalance(faI.getAccountBalance().add(nBalance));
					financeAccountMapper.updateByPrimaryKeySelective(faI);
					
					//插入记录tb_finance_account_detail 收支序数据 accountMap
					payAndReceiveRecore(accountMap);
				}else {
					//提现  即 退款   校验余额应该是查询该项目 该账号存入的金额
					Map<String, Object> map2 = new HashMap<String, Object>();
					Integer projectId = (Integer) accountMap.get("projectId");
					Integer advanceType = (Integer) accountMap.get("advanceType");
					map2.put("projectId", projectId);
					map2.put("advanceType", advanceType);
					map2.put("id", idO);
					Map<String, Object> form = advanceSettlementMapper.getDepostAccountByProjectIdDetail(map2);
					BigDecimal checkBlance =  (BigDecimal) form.get("depositAmount"); 
					int rc = checkBlance.compareTo(nBalance3);
					if(rc == -1) {
						//余额不足
						throw new ParameterException("支出账户余额不足");
					}
					
					//取出账户 减余额
					TbFinanceAccount faO = financeAccountMapper.selectByPrimaryKey(idO);
					faO.setAccountBalance(faO.getAccountBalance().subtract(nBalance3));
					financeAccountMapper.updateByPrimaryKeySelective(faO);
					
					//提现账户 加余额
					TbFinanceAccount faI = financeAccountMapper.selectByPrimaryKey(idI);
					faI.setAccountBalance(faI.getAccountBalance().add(nBalance3));
					financeAccountMapper.updateByPrimaryKeySelective(faI);
					
					//插入记录tb_finance_account_detail 收支序数据 accountMap
					payAndReceiveRecore(accountMap);
				}
			}
		}
		
		Byte stu = null;
		if(flag.equals("0")) {
			stu = 0;
		}else {
			stu = 3;
		}
		AdvanceCharge ac = new AdvanceCharge();
		ac.setStatus(stu);//0已审核 3审核不通过
		//ac.setAssessorId(AppSession.getCurrentUser().getId());
		ac.setAssessor(AppSession.getCurrentUser().getName());
		ac.setAssessorDate(new Date());
		advanceSettlementMapper.accountAuditStatusByIds(ac,accountIdList);
	}

	//收支序数据
	public void payAndReceiveRecore(Map<String,Object> accountMap) {
		Integer projectId = (Integer) accountMap.get("projectId");
		String projectCode =  (String) accountMap.get("projectCode");
		TbAccountRecordDetail record = new TbAccountRecordDetail();
		Integer type = (Integer) accountMap.get("type");
		BigDecimal nBalance =  (BigDecimal) accountMap.get("depositAmount");
		BigDecimal nBalance2 =  (BigDecimal) accountMap.get("purposeAmount");
		BigDecimal nBalance3 =  (BigDecimal) accountMap.get("cashAmount");
		Integer receiveType = (Integer) accountMap.get("receiveType");
		Integer payType = (Integer) accountMap.get("payType");
		Integer receiveAccountId = (Integer) accountMap.get("receiveAccountId");
		Integer payAccountId = (Integer) accountMap.get("payAccountId");
		Integer purposeChooseAccountId = (Integer) accountMap.get("purposeChooseAccount");
		String statementNum =  (String) accountMap.get("serialNumber");
		FinanceAccountDetail financeAccountDetail = new FinanceAccountDetail();
		//financeAccountDetail.setDepositAccountType(receiveType);
		financeAccountDetail.setPayAccountType(payType);
		if(type == 0) {
			financeAccountDetail.setMoney(nBalance);
			financeAccountDetail.setPayAccountId(payAccountId);
			financeAccountDetail.setDepositAccountId(receiveAccountId);
		}else if(type == 1) {
			financeAccountDetail.setMoney(nBalance2);
			financeAccountDetail.setPayAccountId(payAccountId);
		}else {
			financeAccountDetail.setMoney(nBalance3);
			financeAccountDetail.setPayAccountId(payAccountId);
		}
		financeAccountDetail.setOperateId(AppSession.getCurrentUser().getId());
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		financeAccountDetail.setPayDate(datetimeFormat.format(new Date()));
		financeAccountDetail.setStatementNum(statementNum);
		financeAccountDetailMapper.addFinanceAccountDetail(financeAccountDetail);
		
		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(financeAccountDetail.getId().toString());
		branchGroupLink.setTabName("tb_finance_account_detail");
		branchGroupLink.setTabComment("账户明细-收支序");
		branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		
		//账户管理详情表 插入数据
		record.setId(AppUtils.randomUUID());
		record.setProjectId(projectId);
		record.setProjectCode(projectCode);
		record.setDeleteFlag((byte)0);
		if(type == 0) {
			record.setAccountId(receiveAccountId);
			record.setAmount(nBalance);
			record.setType(0);
		}else if(type == 1) {
			record.setAccountId(payAccountId);
			record.setAmount(nBalance2);
			record.setType(1);
		}else {
			record.setAccountId(payAccountId);
			record.setAmount(nBalance3);
			record.setType(2);
		}
		financeAccountMapper.addAccountRecordDetail(record);
	}
	
	@Override
	public void backAccountAuditStatusByIds(String accountChargeIds) {
		if(StringUtil.isEmpty(accountChargeIds))
			return;
		List<String> accountIdList = Arrays.asList(accountChargeIds.trim().split(","));
		//更新账户余额
		for (String accountId : accountIdList) {
			Map<String,Object> accountMap = advanceSettlementMapper.getAdvanceChargeDetailByid(Integer.valueOf(accountId));
			Integer type = (Integer) accountMap.get("type");
			Integer idI = (Integer) accountMap.get("receiveAccountId");
			Integer idO = null;
			BigDecimal nBalance =  (BigDecimal) accountMap.get("depositAmount");
			BigDecimal nBalance2 =  (BigDecimal) accountMap.get("purposeAmount");
			//查询账户余额 在更新
			if(type == 0) {
				idO = (Integer) accountMap.get("payAccountId");
				//存入账户 减余额
				TbFinanceAccount faI = financeAccountMapper.selectByPrimaryKey(idI);
				faI.setAccountBalance(faI.getAccountBalance().subtract(nBalance));
				financeAccountMapper.updateByPrimaryKeySelective(faI);
				//支出账户 加余额 与审核相反
				TbFinanceAccount faO = financeAccountMapper.selectByPrimaryKey(idO);
				faO.setAccountBalance(faO.getAccountBalance().add(nBalance));
				financeAccountMapper.updateByPrimaryKeySelective(faO);
			}else {
				idO = (Integer) accountMap.get("purposeChooseAccount");
				//支出账户 加余额 与审核相反
				TbFinanceAccount faO = financeAccountMapper.selectByPrimaryKey(idO);
				faO.setAccountBalance(faO.getAccountBalance().add(nBalance2));
				financeAccountMapper.updateByPrimaryKeySelective(faO);
			}
		}
				
		AdvanceCharge ac = new AdvanceCharge();
		ac.setStatus((byte)1);//待审核
		ac.setAssessor(AppSession.getCurrentUser().getName());
		ac.setAssessorDate(new Date());
		advanceSettlementMapper.accountAuditStatusByIds(ac,accountIdList);
	}

	@Override
	public List<Map<String, Object>> getDepostAccountByProjectId(Map<String, Object> map) {
		return  advanceSettlementMapper.getDepostAccountByProjectId(map);
	}

	@Override
	public List<Map<String, Object>> getTrainInfoByBillName(Map<String, Object> map) {
		return advanceSettlementMapper.getTrainInfoByBillName(map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getTrainInfoByTrainId(Map<String, Object> map) {
		String trainOrderId = (String) map.get("trainOrderId");
		Integer id = Integer.valueOf(trainOrderId);
		TbTrainOrder train = trainOrderMapper.selectByPrimaryKey(id);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("train", train);
		return map2;
	}

	@Override
	public Map<String, Object> getDetailInfoByAcId(Map<String, Object> map) {
		String id = (String) map.get("id");
		Map<String,Object> accountMap = advanceSettlementMapper.getAdvanceChargeDetailByid(Integer.valueOf(id));
		return accountMap;
	}

	@Override
	public List<Map<String, Object>> getCostByBillName(Map<String, Object> map) {
		return advanceSettlementMapper.getCostByBillName(map);
	}

	@Override
	public Map<String, Object> getCostInfoByCostId(Map<String, Object> map) {
		//String costPackId = (String) map.get("costPackId");
		Map<String, Object> map2 = advanceSettlementMapper.getCostInfoByCostId(map);
		return map2;
	}

	@Override
	public List<Map<String, Object>> getCousterAgentById(Map<String, Object> map) {
		return advanceSettlementMapper.getCousterAgentById(map);
	}

	@Override
	public List<Map<String, Object>> getCashAccountByProjectId(Map<String, Object> map) {
		return  advanceSettlementMapper.getCashAccountByProjectId(map);
	}

	@Override
	public Map<String, Object> getDepostAccountByProjectIdDetail(Map<String, Object> map) {
		return  advanceSettlementMapper.getDepostAccountByProjectIdDetail(map);
	}

	@Override
	public void deleteAdvance(String accountChargeId) {
		if(StringUtil.isEmpty(accountChargeId))
			return;
		advanceSettlementMapper.deleteAdvanceById(Integer.valueOf(accountChargeId));
	}
}
