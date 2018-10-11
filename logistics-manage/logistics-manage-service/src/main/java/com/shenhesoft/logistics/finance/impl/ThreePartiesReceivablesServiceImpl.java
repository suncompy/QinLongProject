package com.shenhesoft.logistics.finance.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.CustomerPack;
import com.shenhesoft.logistics.finance.CustomerPackOrder;
import com.shenhesoft.logistics.finance.ShPackOrder;
import com.shenhesoft.logistics.finance.ThreePartiesReceivables;
import com.shenhesoft.logistics.finance.ThreePartiesReceivablesService;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.finance.mapper.CustomerPackMapper;
import com.shenhesoft.logistics.finance.mapper.CustomerPackOrderMapper;
import com.shenhesoft.logistics.finance.mapper.ThreePartiesReceivablesMapper;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 三方应收款-业务实现.
 * <p>
 * <a href="ThreePartiesReceivablesServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author shilvfei
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class ThreePartiesReceivablesServiceImpl implements ThreePartiesReceivablesService {

	@Autowired
	private ThreePartiesReceivablesMapper threePartiesReceivablesMapper;


	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	
	public ThreePartiesReceivables addThreePartiesReceivables(ThreePartiesReceivables threePartiesReceivables,
			TbSystemUser user) {
		// 生成id
		String threePartiesReceivablesId= AppUtils.randomUUID();
		threePartiesReceivables.setId(threePartiesReceivablesId);
		threePartiesReceivables.setCreateBy(user.getId().toString());
		threePartiesReceivables.setCreateDate(new Date());
		threePartiesReceivables.setStatus(0);
		
		threePartiesReceivables.setBesettledMoney(threePartiesReceivables.getPayableMoney());
		// 保存三方应收款表
		threePartiesReceivablesMapper.addThreePartiesReceivables(threePartiesReceivables);

		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(threePartiesReceivablesId);
		branchGroupLink.setTabName("tb_three_parties_receivables");
		branchGroupLink.setTabComment("三方应收款管理");
		branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);

		/*
		 * //更新客户cust_pack 表 CustomerPack customerPack = new CustomerPack();
		 * customerPack.setCustPackId(threePartiesReceivables.getCustomerPackId(
		 * )); //设置待结金额 customerPack.setBesettledMoney(threePartiesReceivables.
		 * getPayableMoney()); //更新发票状态 customerPack.setInvoiceStatus(1);//已登入
		 * 
		 * customerPackMapper.editCustomerPackById(customerPack);
		 */
		return threePartiesReceivables;
	}

	/**
	 * 获取所有三方收款信息表.
	 * 
	 * @return 三方收款信息表分页
	 */
	@Override
	public List<Map<String, Object>> getThreePartiesReceivables(int start, int pageSize, Map<String, Object> form) {
		PageHelper.offsetPage(start, pageSize);
		return this.getThreePartiesReceivables(form);
	}

	/**
	 * 获取所有三方收款信息表.
	 * 
	 * @return 三方收款信息表
	 */
	@Override
	public List<Map<String, Object>> getThreePartiesReceivables(Map<String, Object> form) {
		return threePartiesReceivablesMapper.getThreePartiesReceivables(form);
	}

}
