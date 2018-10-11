package com.shenhesoft.logistics.finance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.shenhesoft.logistics.business.bussinessCount.BussinessCountService;
import com.shenhesoft.logistics.business.mapper.TbOrderMapper;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.StringUtil;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.ShortOrderFinance;
import com.shenhesoft.logistics.finance.ShortOrderFinanceService;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.finance.mapper.ShortOrderFinanceMapper;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.system.aspect.OrgLinkData;

/**
 * 短驳运单财务表-业务实现.
 * <p>
 * <a href="ShortOrderFinanceServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class ShortOrderFinanceServiceImpl implements ShortOrderFinanceService {

	@Autowired
	private ShortOrderFinanceMapper shortOrderFinanceMapper;
	@Autowired
    private BussinessCountService bussinessCountService;
	@Autowired
	private TbOrderMapper tbOrderMapper;
	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;
	/**
	 * 新增短驳运单财务表.
	 * 
	 * @param shortOrderFinance
	 *            短驳运单财务表实体
	 * @return 页面表单
	 */
	@OrgLinkData(idName="shOrderFinId",tabComment="运费支出")
	public ShortOrderFinance addShortOrderFinance(ShortOrderFinance shortOrderFinance) {
		// 生成id
		shortOrderFinance.setShOrderFinId(AppUtils.randomUUID());
		// 保存短驳运单财务表
		shortOrderFinanceMapper.addShortOrderFinance(shortOrderFinance);

		return shortOrderFinance;
	}

	/**
	 * 查看短驳运单财务表详情.
	 * 
	 * @param id
	 *            主键
	 * @return 页面表单
	 */
	public Map<String, Object> getShortOrderFinanceById(String id) {
		return shortOrderFinanceMapper.getShortOrderFinanceById(id);
	}
    /**
     * 通过projectId查询待对账信息.
     * @param checkFlag 0司机对账1费用对账2客户对账
     * @param id
     *            主键
     * @return 页面表单
     */
    public Map<String, Object> getCheckConfPickupByProjectId(Map<String, Object> form){
      Map<String, Object> checkConf = shortOrderFinanceMapper.getCheckConfPickupByProjectId(form);
      return checkConf;
    }
    /**
     * 通过projectId查询待对账信息.
     * 
     * @param id
     *            主键
     * @return 页面表单
     */
    public Map<String, Object> getCheckConfByProjectId(Map<String, Object> form){
      Map<String, Object> checkConf = shortOrderFinanceMapper.getCheckConfByProjectId(form);
      return checkConf;
    }
	/**
	 * 修改短驳运单财务表.
	 * 
	 * @param shortOrderFinance
	 *            短驳运单财务表实体
	 */
	public void editShortOrderFinanceById(ShortOrderFinance shortOrderFinance) {
		shortOrderFinanceMapper.editShortOrderFinanceById(shortOrderFinance);
	}

	/**
	 * 删除指定短驳运单财务表.
	 * 
	 * @param id
	 *            主键
	 */
	public void delShortOrderFinanceById(String id) {
		shortOrderFinanceMapper.delShortOrderFinanceById(id);
	}

	/**
	 * 批量删除指定短驳运单财务表.
	 * 
	 * @param ids
	 *            主键集合
	 */
	public void delShortOrderFinanceByIds(List<String> ids) {
		shortOrderFinanceMapper.delShortOrderFinanceByIds(ids);
	}

	/**
	 * 清空计量单位表.
	 */
	public void delShortOrderFinances() {
		shortOrderFinanceMapper.delShortOrderFinances();
	}

	/**
	 * 获取所有短驳运单财务表.
	 * 
	 * @return 短驳运单财务表分页
	 */
	public List<Map<String, Object>> getShortOrderFinances(int start, int pageSize, Map<String, Object> form) {
	    //form = CollectionUtils.isEmpty(form)?Maps.newHashMap():form;
        //form.put("branchGroupIdCount",  bussinessCountService.getBranchIds(form));
		PageHelper.offsetPage(start, pageSize);
		return this.getShortOrderFinances(form);
	}

	/**
	 * 获取所有短驳运单财务表.
	 * 
	 * @return 短驳运单财务表
	 */
	public List<Map<String, Object>> getShortOrderFinances(Map<String, Object> form) {
		return shortOrderFinanceMapper.getShortOrderFinances(form);
	}

	@Override
	public void addShortOrderFinanceByTbOrder(TbOrder order, Integer userId) {
		if (null==order || null==order.getId())
			return;
		Map<String, Object> form = ImmutableMap.of("orderId", order.getId());
		List<Map<String, Object>> existOrderFinances = shortOrderFinanceMapper.getShortOrderFinances(form);
		if(!CollectionUtils.isEmpty(existOrderFinances)){
		  return;
		}
		ShortOrderFinance shortOrderFinance = new ShortOrderFinance();
		// 项目id
		shortOrderFinance.setProjectId(order.getProjectId());
		// 运单id
		shortOrderFinance.setOrderId(order.getId());
		// 财务状态(0-待确认、1-待计算、2-待审核、3-已审核)
		shortOrderFinance.setFinanceStatus(0);
		// 是否被打包(0-未打包 1-已打包)
		shortOrderFinance.setPackFlag(0);
		// 是否删除(0-未删除 1-删除)
		shortOrderFinance.setDeleteFlag(0);
		// 运输费用
		shortOrderFinance.setShortBargeCost(order.getShortBargeCost());
		// 补加金额
		shortOrderFinance.setSubsidy(new BigDecimal(0));
		// 应付费用
		shortOrderFinance.setShouldPayFigure(order.getShortBargeCost());
		// 创建时间
		shortOrderFinance.setCreateDate(DateUtils.date2Str(DateUtils.datetimeFormat));
		// 创建人
		shortOrderFinance.setCreateUserId(userId);
		this.addShortOrderFinance(shortOrderFinance);
	}

	@Override
	public void billingVerifyShortOrderFinanceByIds(String shOrderFinIds,TbSystemUser user, String flag) {
		if(StringUtil.isEmpty(shOrderFinIds))
			return;
		List<String> shOrderFinIdList = Arrays.asList(shOrderFinIds.trim().split(","));
		ShortOrderFinance shortOrderFinance = new ShortOrderFinance();
		if(flag.equals("0")) {
			shortOrderFinance.setFinanceStatus(2);//待审核
			//确认计费时间
			shortOrderFinance.setBillingDate(DateUtils.date2Str(DateUtils.datetimeFormat));
			shortOrderFinance.setBillingUserId(null==AppSession.getCurrentUserId()?user.getId():AppSession.getCurrentUserId());
			shortOrderFinanceMapper.editShortOrderFinanceByIds(shortOrderFinance, shOrderFinIdList);
			if(CollectionUtils.isEmpty(shOrderFinIdList)) {
				return;
			}
			for(String id : shOrderFinIdList) {
				Map<String,Object> orderMap =shortOrderFinanceMapper.getShortOrderFinanceById(id);
				if(!CollectionUtils.isEmpty(orderMap) && null!=orderMap.get("orderId")){
				    user = (null==user || null==user.getId())?AppSession.getCurrentUser():user;
		            Map<String,Object> map = new HashMap<String,Object>();
	    			map.put("orderId", orderMap.get("orderId"));
	    			map.put("userDispatchId", user.getId());
	    			map.put("userDispatchName", user.getName());
	    			map.put("status", (byte)6);
	    			tbOrderMapper.updateOrderStatusOfFince(map);
				}
			}
		}else {
			shortOrderFinance.setFinanceStatus(4);//计费确认不通过
			//确认计费时间
			shortOrderFinance.setBillingDate(DateUtils.date2Str(DateUtils.datetimeFormat));
			shortOrderFinance.setBillingUserId(null==AppSession.getCurrentUserId()?user.getId():AppSession.getCurrentUserId());
			shortOrderFinanceMapper.editShortOrderFinanceByIds(shortOrderFinance, shOrderFinIdList);
			if(CollectionUtils.isEmpty(shOrderFinIdList)) {
				return;
			}
		}
	}

	@Override
	public void againstVerifyShortOrderFinanceByIds(String shOrderFinIds) {
		if(StringUtil.isEmpty(shOrderFinIds))
			return;
		List<String> shOrderFinIdList = Arrays.asList(shOrderFinIds.trim().split(","));
		ShortOrderFinance shortOrderFinance = new ShortOrderFinance();
		shortOrderFinance.setFinanceStatus(0);//待确认
		shortOrderFinanceMapper.editShortOrderFinanceByIds(shortOrderFinance, shOrderFinIdList);
	}

	@Override
	public void financeAuditShortOrderFinanceByIds(String shOrderFinIds,TbSystemUser user, String flag) {
		if(StringUtil.isEmpty(shOrderFinIds))
			return;
		List<String> shOrderFinIdList = Arrays.asList(shOrderFinIds.trim().split(","));
		ShortOrderFinance shortOrderFinance = new ShortOrderFinance();
		if(flag.equals("0")) {
			shortOrderFinance.setFinanceStatus(3);//已审核
		}else {
			shortOrderFinance.setFinanceStatus(5);//审核不通过
		}
		//财务审核时间
		shortOrderFinance.setFinanceAuditDate(DateUtils.date2Str(DateUtils.datetimeFormat));
		shortOrderFinance.setAuditUserId(null==AppSession.getCurrentUserId()?user.getId():AppSession.getCurrentUserId());
		shortOrderFinanceMapper.editShortOrderFinanceByIds(shortOrderFinance, shOrderFinIdList);
	}

	@Override
	public void againstAuditShortOrderFinanceByIds(String shOrderFinIds) {
		if(StringUtil.isEmpty(shOrderFinIds))
			return;
		List<String> shOrderFinIdList = Arrays.asList(shOrderFinIds.trim().split(","));
		ShortOrderFinance shortOrderFinance = new ShortOrderFinance();
		shortOrderFinance.setFinanceStatus(2);//待审核
		shortOrderFinanceMapper.editShortOrderFinanceByIds(shortOrderFinance, shOrderFinIdList);
	}

	@Override
	public void billingFreightShortOrderFinanceByIds(String shOrderFinIds) {
		if(StringUtil.isEmpty(shOrderFinIds))
			return;
		List<String> shOrderFinIdList = Arrays.asList(shOrderFinIds.trim().split(","));
		ShortOrderFinance shortOrderFinance = new ShortOrderFinance();
		shortOrderFinance.setFinanceStatus(0);//待确认
		shortOrderFinanceMapper.editShortOrderFinanceByIds(shortOrderFinance, shOrderFinIdList);
	}

	@Override
	public List<String> listRetroactivelyPayProject(String retroactivelyPayProject) {
		String[] arrTurndownReason = retroactivelyPayProject.split(";");
		// 遍历一下，去除空字符串
		List<String> retroactivelyPayProjectList = new ArrayList<String>();
		for (String tmpRetroactivelyPayProject : arrTurndownReason) {
			if (StringUtil.isEmpty(tmpRetroactivelyPayProject))
				continue;
			retroactivelyPayProjectList.add(tmpRetroactivelyPayProject.trim());
		}
		return retroactivelyPayProjectList;
	}

	@Override
	public void subsidyShortOrderFinance(ShortOrderFinance shortOrderFinance, TbSystemUser user) {
		if(shortOrderFinance == null)
			return;
		shortOrderFinance.setAddUserId(user.getId());//追加人id
		shortOrderFinance.setAddDate(DateUtils.date2Str(DateUtils.datetimeFormat));//追加人时间
		shortOrderFinance.setFinanceStatus(1);//待计算
		this.editShortOrderFinanceById(shortOrderFinance);
	}

}