package com.shenhesoft.logistics.finance;
import java.util.List;
import java.util.Map;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
/**
 * 三方结算信息表-业务层接口.
 * <p>
 * <a href="ThreePartiesSettleService.java"><i>View Source</i></a>
 * </p>
 * 
 * @author shilvfei
 * @date 2018-01-19
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ThreePartiesSettleService {

	/**
	 * @description 添加三方结算 
	 * @date 2018年2月11日
	 * @param form
	 * @return
	 */
	ThreePartiesSettle addThreePartiesSettle(ThreePartiesSettle threePartiesSettle, TbSystemUser user);
	
	/**
	 * @description  分页获取所有三方结算 
	 * @date 2018年2月11日
	 * @param form
	 * @return
	 */
	public List<Map<String, Object>> getThreePartiesSettle(int start, int pageSize, Map<String, Object> form);
	
	/**
	 * @description 获取所有三方结算
	 * @date 2018年2月11日
	 * @param form
	 * @return
	 */
	public List<Map<String, Object>> getThreePartiesSettle(Map<String, Object> form);

	/**
	 * @description 审核
	 * @date 2018年2月11日
	 * @param shPackId
	 * @return
	 */
	void financeAuditShortPackByIds(String shPackIds);

	
	/**
	 * @description 反审核
	 * @date 2018年2月11日
	 * @param shPackId
	 * @return
	 */
	void againstAuditShortPackByIds(String shPackIds);
}
