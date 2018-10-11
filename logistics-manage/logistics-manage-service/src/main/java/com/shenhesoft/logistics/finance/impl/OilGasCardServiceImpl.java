package com.shenhesoft.logistics.finance.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import com.shenhesoft.logistics.common.exception.ParameterException;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.FinanceAccountDetail;
import com.shenhesoft.logistics.finance.OilGasCard;
import com.shenhesoft.logistics.finance.OilGasCardPack;
import com.shenhesoft.logistics.finance.OilGasCardService;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.finance.mapper.FinanceAccountDetailMapper;
import com.shenhesoft.logistics.finance.mapper.OilGasCardMapper;
import com.shenhesoft.logistics.finance.mapper.ShortPackMapper;
import com.shenhesoft.logistics.manage.mapper.TbFinanceAccountMapper;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 油气卡信息表-业务实现.
 * <p>
 * <a href="OilGasCardServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author JiangYS
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class OilGasCardServiceImpl implements OilGasCardService {

	@Autowired
	private OilGasCardMapper oilGasCardMapper;
	@Autowired
    private ShortPackMapper shortPackMapper;
	  @Autowired
	  private TbFinanceAccountMapper financeAccountMapper;
	@Autowired
	private FinanceAccountDetailMapper financeAccountDetailMapper;
	  @Autowired
	  private BranchGroupLinkMapper branchGroupLinkMapper;
	/**
	 * 新增油气卡打包信息表.
	 * 
	 * @param oilGasCard
	 *            油气卡信息表实体
	 * @return 页面表单
	 */
	@OrgLinkData(tabComment="油气卡打包")
	public void addOilGasCard(OilGasCardPack oilGasCardPack, TbSystemUser user) {
		
		oilGasCardPack.setId(AppUtils.randomUUID());
		// 审核状态(0待审核 1:审核通过 2:审核不通过) audit_status
		oilGasCardPack.setAuditStatus(0);
		// 采购时间 purchase_date
		oilGasCardPack.setPurchaseDate(DateUtils.date2Str(DateUtils.datetimeFormat));
		// 采购人 purchase_id
		oilGasCardPack.setPurchaseId(user.getId());
		oilGasCardMapper.addOilGasCard(oilGasCardPack);
	}
	
	/**
	 * 新增油气卡信息表.
	 * 
	 * @param oilGasCard
	 *            油气卡信息表实体
	 * @return 页面表单
	 */
	@OrgLinkData(tabComment="油气卡")
	public void insertOilGasCard(OilGasCard oilGasCard, TbSystemUser user) {
		
		oilGasCard.setId(AppUtils.randomUUID());
		
		oilGasCard.setCardStatus(0);
		
		oilGasCardMapper.insertOilGasCard(oilGasCard);
	}
	
	/**
	 * 删除原有油气卡.
	 * 
	 * @param oilGasCard
	 *            油气卡信息表实体
	 * @return 页面表单
	 */
	@Override
	public void deleteOilGasCard(String oilGasCardsId) {
		
		oilGasCardMapper.deleteOilGasCard(oilGasCardsId);
	}

	/**
	 * 获取所有油气卡信息表.
	 * 
	 * @return 油气卡信息表分页
	 */
	@Override
	public List<Map<String, Object>> queryOilGasCard(int start, int pageSize, Map<String, Object> form) {
		PageHelper.offsetPage(start, pageSize);
		return this.queryOilGasCard(form);
	}
	
	/**
	 * 获取所有油气卡信息表.
	 * 
	 * @return 油气卡信息表
	 */
	@Override
	public List<Map<String, Object>> queryOilGasCard(Map<String, Object> form) {
		return oilGasCardMapper.queryOilGasCard(form);
	}

	/**
	 * 根据日期查询油气卡信息表.
	 * 
	 * @return 油气卡信息表分页
	 */
	@Override
	public List<Map<String, Object>> queryOilGasCardByDate(int start, int pageSize, Map<String, Object> form) {
		PageHelper.offsetPage(start, pageSize);
		return this.queryOilGasCardByDate(form);
	}

	/**
	 * 根据日期查询油气卡信息表.
	 * 
	 * @return 油气卡信息表分页
	 */
	@Override
	public List<Map<String, Object>> queryOilGasCardByDate(Map<String, Object> form) {
		return oilGasCardMapper.queryOilGasCardByDate(form);
	}

	/**
	 * 批量新增油气卡信息表.
	 * 
	 * @param shortPack
	 *            油气卡信息表实体
	 * @return 页面表单
	 */
	@Override
	public List<OilGasCardPack> addOilGasCards(List<OilGasCardPack> list, TbSystemUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询分支机构.
	 * 
	 * @return 
	 */
//	@Override
//	public List<Map<String, Object>> queryBranchGroupName(int start, int pageSize, Map<String, Object> form) {
//		PageHelper.offsetPage(start, pageSize);
//		return this.queryBranchGroupName(form);
//	}
	
	/* 
	 * 查询分支机构
	 */
	/*@Override
	public List<Map<String, Object>> queryBranchGroupName(Map<String, Object> form) {
		return oilGasCardMapper.queryBranchGroupName(form);
	}*/
	@Override
	public List<OilGasCardPack> queryBranchGroupName(){
		return oilGasCardMapper.queryBranchGroupName();
	};
	
	/* 
	 * 查询金额
	 */
	public List<OilGasCard> queryMoney(String suppliesNum) {
		return oilGasCardMapper.queryMoney(suppliesNum);
	}

	/* 
	 * 修改审核状态
	 */
	@Override
	public void updateAuditStatus(String passFlag,String id) {
		if (StringUtil.isEmpty(id) || StringUtil.isEmpty(passFlag))
			return;
		TbSystemUser user = AppSession.getCurrentUser();
		OilGasCardPack oilGasCardPack = new OilGasCardPack();
		if (passFlag.equals("1")) {
			oilGasCardPack.setAuditStatus(1);// 1-审核通过
			oilGasCardPack.setAuditDate(DateUtils.date2Str(DateUtils.datetimeFormat));
			oilGasCardPack.setAuditId(user.getId());
			
			Map<String, Object> map = oilGasCardMapper.queryOilGasCardById(id);
			//插入收支序时账
			FinanceAccountDetail financeAccountDetail = new FinanceAccountDetail();
			String accountIdStr = map.get("accountId").toString();
			int accountIdInt = Integer.valueOf(accountIdStr);
			financeAccountDetail.setPayAccountId(accountIdInt);
			financeAccountDetail.setMoney((BigDecimal) map.get("totalMoney"));
			financeAccountDetail.setOperateId(user.getId());
			financeAccountDetail.setPayDate(DateUtils.date2Str(DateUtils.datetimeFormat));
			financeAccountDetail.setStatementNum(id);
			financeAccountDetailMapper.addFinanceAccountDetail(financeAccountDetail);
			//
			TbFinanceAccount tbFinanceAccount= financeAccountMapper.selectByPrimaryKey(Integer.parseInt(map.get("accountId").toString()));
		      double accountBalance = tbFinanceAccount.getAccountBalance().doubleValue();
		      double money = Double.parseDouble(map.get("totalMoney").toString());
		      if(accountBalance<money){
		        throw new ParameterException("支出账户余额不足");
		      }
	         Map<String,Object> requestMap = ImmutableMap.of("money", map.get("totalMoney"),"id", map.get("accountId"));
	            shortPackMapper.editFinanceMoneySubById(requestMap);
		      BranchGroupLink branchGroupLink = new BranchGroupLink();
		      branchGroupLink.setId(AppUtils.randomUUID());
		      branchGroupLink.setRowId(financeAccountDetail.getId().toString());
		      branchGroupLink.setTabName("tb_finance_account_detail");
		      branchGroupLink.setTabComment("收支时序账");
		      branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		      branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
		} else if (passFlag.equals("2")) {
			oilGasCardPack.setAuditStatus(2);// 2-审核不通过
			oilGasCardPack.setAuditDate(DateUtils.date2Str(DateUtils.datetimeFormat));
			oilGasCardPack.setAuditId(user.getId());
		}else if (passFlag.equals("0")) {
			oilGasCardPack.setAuditStatus(0);// 0-待审核
			oilGasCardPack.setAuditDate(null);
			oilGasCardPack.setAuditId(null);
		}
		oilGasCardPack.setId(id);
		oilGasCardMapper.updateAuditStatus(oilGasCardPack);
	}
	  /**
	   * @description 
	   * @date 2018年3月13日
	   * @param 
	   * @return
	  */
	  public void updatePackId(Map<String, Object> map){
	    oilGasCardMapper.updatePackId(map);
	  }
	  
	  /**
	   * @description 
	   * @author liangLin
	   * @date 2018年3月13日
	   * @param 
	   * @return
	  */
	  public void delOilGasCardById(String id){
	    oilGasCardMapper.delOilGasCardById(id);
	  }

	  /**
	   * @description 
	   * @author liangLin
	   * @date 2018年3月13日
	   * @param 
	   * @return
	  */
	  public void delOilGasCardByIds(List<String> ids){
	    oilGasCardMapper.delOilGasCardByIds(ids);
	  }
	  /**
	   * 获取是否已存在的油气卡号
	   */
	public List<Map<String,Object>> listOilGasCardNum(String id){
	  return oilGasCardMapper.listOilGasCardNum(id);
	}
}