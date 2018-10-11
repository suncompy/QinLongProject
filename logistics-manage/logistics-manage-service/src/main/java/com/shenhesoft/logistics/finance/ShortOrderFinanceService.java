package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 短驳运单财务表-业务层接口.
 * <p>
 * <a href="ShortOrderFinanceService.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ShortOrderFinanceService {

	/**
	 * 添加短驳运单财务表.
	 * 
	 * @param shortOrderFinance
	 *            短驳运单财务表实体
	 * @return 页面表单
	 */
	public ShortOrderFinance addShortOrderFinance(ShortOrderFinance shortOrderFinance);

	/**
	 * 查看短驳运单财务表详情.
	 * 
	 * @param id
	 *            主键
	 * @return 页面表单
	 */
	public Map<String, Object> getShortOrderFinanceById(String id);
    /**
     * 通过projectId查询待对账信息.
     * @param checkFlag 0司机对账1费用对账2客户对账
     * @param id
     *            主键
     * @return 页面表单
     */
    public Map<String, Object> getCheckConfPickupByProjectId(Map<String, Object> form);
	/**
     * 通过projectId查询待对账信息.
     * 
     * @param id
     *            主键
     * @return 页面表单
     */
    public Map<String, Object> getCheckConfByProjectId(Map<String, Object> form);
	/**
	 * 修改短驳运单财务表.
	 * 
	 * @param shortOrderFinance
	 *            短驳运单财务表实体
	 */
	public void editShortOrderFinanceById(ShortOrderFinance shortOrderFinance);

	/**
	 * 删除指定短驳运单财务表.
	 * 
	 * @param id
	 *            主键
	 */
	public void delShortOrderFinanceById(String id);

	/**
	 * 批量删除指定短驳运单财务表.
	 * 
	 * @param ids
	 *            主键集合.
	 */
	public void delShortOrderFinanceByIds(List<String> ids);

	/**
	 * 清空计量单位表.
	 */
	public void delShortOrderFinances();

	/**
	 * 获取所有短驳运单财务表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> getShortOrderFinances(int start, int pageSize, Map<String, Object> form);

	/**
	 * 获取所有短驳运单财务表.
	 * 
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> getShortOrderFinances(Map<String, Object> form);

	/**
	 * 短驳运单 等待回单后 保存一条财务信息
	 * 
	 * @author dusd
	 * @date 2018年1月16日
	 * @param order
	 *            运单信息
	 * @param userId
	 *            当前登录用户id
	 */
	void addShortOrderFinanceByTbOrder(TbOrder order, Integer userId);

	/**
	 * 批量进行计费确认
	 * @author dusd
	 * @date 2018年1月17日
	 * @param shOrderFinIds 
	 * @param flag 
	 */
	public void billingVerifyShortOrderFinanceByIds(String shOrderFinIds,TbSystemUser user, String flag);

	/**
	 * 批量进行计费反确认
	 * @author dusd
	 * @date 2018年1月17日
	 * @param shOrderFinIds
	 */
	public void againstVerifyShortOrderFinanceByIds(String shOrderFinIds);

	/**
	 * 批量进行财务审核
	 * @author dusd
	 * @date 2018年1月17日
	 * @param shOrderFinIds
	 * @param flag 
	 */
	public void financeAuditShortOrderFinanceByIds(String shOrderFinIds,TbSystemUser user, String flag);

	/**
	 * 批量进行财务反审核
	 * @author dusd
	 * @date 2018年1月17日
	 * @param shOrderFinIds
	 */
	public void againstAuditShortOrderFinanceByIds(String shOrderFinIds);

	/**
	 * 批量进行计算运费
	 * @author dusd
	 * @date 2018年1月17日
	 * @param shOrderFinIds
	 */
	public void billingFreightShortOrderFinanceByIds(String shOrderFinIds);

	/**
	 * 返回补加项目列表
	 * @author dusd
	 * @date 2018年1月17日
	 * @param retroactivelyPayProject
	 * @return
	 */
	public List<String> listRetroactivelyPayProject(String retroactivelyPayProject);

	/**
	 * 保存追加金额
	 * @author dusd
	 * @date 2018年1月17日
	 * @param shortOrderFinance
	 * @param user 
	 */
	public void subsidyShortOrderFinance(ShortOrderFinance shortOrderFinance, TbSystemUser user);

}