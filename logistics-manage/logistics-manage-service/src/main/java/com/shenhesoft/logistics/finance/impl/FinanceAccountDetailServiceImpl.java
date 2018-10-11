package com.shenhesoft.logistics.finance.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.shenhesoft.logistics.business.bussinessCount.BussinessCountService;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.mapper.TbTrainOrderMapper;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.finance.CustomerCheckingConf;
import com.shenhesoft.logistics.finance.CustomerCheckingConfService;
import com.shenhesoft.logistics.finance.FinanceAccountDetail;
import com.shenhesoft.logistics.finance.FinanceAccountDetailService;
import com.shenhesoft.logistics.finance.mapper.CustomerCheckingConfMapper;
import com.shenhesoft.logistics.finance.mapper.FinanceAccountDetailMapper;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 客户对账设置表-业务实现.
 * <p>
 * <a href="CustomerCheckingConfServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class FinanceAccountDetailServiceImpl implements FinanceAccountDetailService {

	@Autowired
	private FinanceAccountDetailMapper financeAccountDetailMapper;
	
	/* 
	 * 添加账户流水详情表.
	 */
	@OrgLinkData(tabComment="账户明细-收支序")
	public FinanceAccountDetail addFinanceAccountDetail(FinanceAccountDetail financeAccountDetail) {
		//生成流水号
		financeAccountDetail.setStatementNum(AppUtils.randomUUID());
		TbSystemUser user = AppSession.getCurrentUser();
		financeAccountDetail.setOperateId(user.getId());// 创建人
		financeAccountDetail.setPayDate(DateUtils.date2Str(DateUtils.datetimeFormat));
		return financeAccountDetail;
	}
	/* 
	 * 获取账户流水详情表.
	 */
	@Override
	public List<Map<String, Object>> getFinanceAccountDetail(int start, int pageSize, Map<String, Object> form) {
		PageHelper.offsetPage(start, pageSize);
		return this.getFinanceAccountDetail(form);
	}
	/* 
	 * 获取账户流水详情表.
	 */
	@Override
	public List<Map<String, Object>> getFinanceAccountDetail(Map<String, Object> form) {
		return financeAccountDetailMapper.getFinanceAccountDetail(form);
	}

}