package com.shenhesoft.logistics.finance.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.finance.FinanceAccountDetail;
import com.shenhesoft.logistics.finance.Material;
import com.shenhesoft.logistics.finance.MaterialManagementService;
import com.shenhesoft.logistics.finance.mapper.FinanceAccountDetailMapper;
import com.shenhesoft.logistics.finance.mapper.MaterialManagementMapper;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 油气卡信息表-业务实现.
 * <p>
 * <a href="MaterialManagementServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author JiangYS
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class MaterialManagementServiceImpl implements MaterialManagementService {

	@Autowired
	private MaterialManagementMapper materialManagementMapper;
	@Autowired
	private FinanceAccountDetailMapper financeAccountDetailMapper;

	/**
	 * 获取所有油气卡信息表.
	 * 
	 * @return 油气卡信息表分页
	 */
	@Override
	public List<Map<String, Object>> queryMaterial(int start, int pageSize, Map<String, Object> form) {
		PageHelper.offsetPage(start, pageSize);
		return this.queryMaterial(form);
	}
	
	/**
	 * 获取所有油气卡信息表.
	 * 
	 * @return 油气卡信息表
	 */
	@Override
	public List<Map<String, Object>> queryMaterial(Map<String, Object> form) {
		return materialManagementMapper.queryMaterial(form);
	}

	/* 
	 * 添加物料信息表.
	 */
	@OrgLinkData(tabComment="物料管理")
	public void addMaterial(Material materialManagement) {
		TbSystemUser user = AppSession.getCurrentUser();
		materialManagement.setId(AppUtils.randomUUID());
		//materialManagement.setAuditId(user.getId());
		materialManagement.setPurchaseId(user.getId());
		//materialManagement.setAuditDate(DateUtils.date2Str(DateUtils.datetimeFormat));
		materialManagement.setPurchaseDate(DateUtils.date2Str(DateUtils.datetimeFormat));
		materialManagement.setAuditStatus(0);
		materialManagementMapper.addMaterial(materialManagement);
		
	}
	
	/* 
	 * 修改审核状态
	 */
	@Override
	public void updateAuditStatus(String passFlag,String id) {
		if (StringUtil.isEmpty(id) || StringUtil.isEmpty(passFlag))
			return;
		TbSystemUser user = AppSession.getCurrentUser();
		Material materialManagement = new Material();
		if (passFlag.equals("1")) {
			materialManagement.setAuditStatus(1);// 1-审核通过
			Map<String, Object> map = materialManagementMapper.queryMaterialById(id);
			//插入收支序时账
			FinanceAccountDetail financeAccountDetail = new FinanceAccountDetail();
			financeAccountDetail.setPayAccountId((Integer) map.get("accountId"));
			financeAccountDetail.setMoney((BigDecimal) map.get("totalMoney"));
			financeAccountDetail.setOperateId((Integer) map.get("purchaseId"));
			financeAccountDetail.setPayDate(DateUtils.date2Str(DateUtils.datetimeFormat));
			financeAccountDetail.setStatementNum(id);
			financeAccountDetailMapper.addFinanceAccountDetail(financeAccountDetail);
		} else if (passFlag.equals("2")) {
			materialManagement.setAuditStatus(2);// 2-审核不通过
		}else if (passFlag.equals("0")) {
			materialManagement.setAuditStatus(0);// 0-待审核
		}
		// 审核时间
		materialManagement.setAuditDate(DateUtils.date2Str(DateUtils.datetimeFormat));
		materialManagement.setAuditId(user.getId());
		materialManagement.setId(id);
		materialManagementMapper.updateMaterial(materialManagement);
	}

	/* 
	 * 获取公司账户
	 */
	@Override
	public List<Map<String, Object>> queryCompanyAccount() {
		return materialManagementMapper.queryCompanyAccount();
	}

	/**
	 * 通过id获取信息
	 */
	@Override
	public Map<String, Object> queryMaterialById(String id) {
		return materialManagementMapper.queryMaterialById(id);
	}
}