package com.shenhesoft.logistics.business.shortbarge.order;

import java.io.IOException;
import java.util.List;

import com.shenhesoft.logistics.business.helpPojo.ProjectDistributionDetail;
import com.shenhesoft.logistics.business.helpPojo.TbBulkSubForwardDetail;
import com.shenhesoft.logistics.business.helpPojo.TbOrderCarDetail;
import com.shenhesoft.logistics.business.helpPojo.TbReceipterDetail;
import com.shenhesoft.logistics.business.helpPojo.TbSubForwardDetail;
import com.shenhesoft.logistics.business.helpPojo.TbWaitDispatchDetail;
import com.shenhesoft.logistics.business.helpPojo.TbguideDetail;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.helpPojo.DotBranchDetail;
import com.shenhesoft.logistics.manage.helpPojo.ProjectAppHelp;
import com.shenhesoft.logistics.manage.pojo.box.TbContainer;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.search.OrderSearch;

/**
 * @description 短驳运单
 * 
 * @author shilvfei
 * 
 * @date 2018年1月24日
 */
public interface ShortBargeOrderService {

	/**
	 * @description 集装箱管理 - 页面list
	 * @author liangLin
	 * @param user 
	 * @param isBulk 
	 * @date 2017年12月19日
	 * @param 
	 * @return
	*/
	DataGridResult selectBoxManagerOrderByPages(Integer page, Integer limit,byte type,OrderSearch order, TbSystemUser user, byte isBulk);

	/**
	 * @description 集装箱管理 - 点击单个 获取详情
	 * @author liangLin
	 * @date 2017年12月19日
	 * @param 
	 * @return
	*/
	TbOrder selectBoxManagerOrderById(Integer id);

	/**
	 * @description  等待调度获取详情
	 * @author liangLin
	 * @date 2017年12月21日
	 * @param 
	 * @return
	*/
	TbOrder getDispatchMsgByProjectId(Integer id);

	/**
	 * @description 获取集装箱下拉
	 * @author liangLin
	 * @date 2017年12月21日
	 * @param 
	 * @return
	*/
	List<TbContainer> getTbContainerNumbers();

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月21日
	 * @param 
	 * @return
	*/
	boolean dispatchAdd(TbWaitDispatchDetail tbWaitDispatchDetail,TbSystemUser user)throws Exception;

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	int subForwardingAdd(TbSubForwardDetail tbSubForwardDetail, TbSystemUser user)throws Exception;

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	boolean carryAdd(TbWaitDispatchDetail tbWaitDispatchDetail, TbSystemUser user);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	boolean guideAdd(TbguideDetail tbguideDetail, TbSystemUser user);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	boolean receipterAdd(TbReceipterDetail tbReceipterDetail, TbSystemUser user);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	boolean delete(List<Integer> idList,String reason,TbSystemUser user);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	boolean orderAdd(TbOrder tbOrder, TbSystemUser user,byte type);

	/**
	 * @description 
	 * @author liangLin
	 * @param branchGroups 
	 * @param projectType 
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	List<TbProject> getProjects(byte projectType, List<DotBranchDetail> branchGroups);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	TbProject selectDetailProject(Integer id);

	/**
	 * @description 短驳管理 ： 新建运单 自选： 获取短驳承运车辆
	 * @author liangLin
	 * @date 2017年12月22日
	 * @param 
	 * @return
	*/
	List<TbOrderCarDetail>  selectCarTeams(Integer projectId,Integer driverId,Integer shortType);

	/**
	 * @description 
	 * @author liangLin
	 * @param user 
	 * @param isBulk 
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	DataGridResult selectBoxManagerOrderDeleteByPages(Integer page, Integer limit,OrderSearch searchOrder, TbSystemUser user, byte isBulk );

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	LogisticsResult revertOrder(List<Integer> idList);

	/**
	 * @description 
	 * @author liangLin
	 * @param user 
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	DataGridResult selectBulkPackingOrderByPages(Integer pAGE_NUM, Integer cUSTOMER_PAGE_LIMIT, byte b,OrderSearch orderSearch, TbSystemUser user);

	/**
	 * @description 
	 * @author liangLin
	 * @param user 
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	DataGridResult selectBulkPackingOrderDeleteByPages(Integer pAGE_NUM, Integer cUSTOMER_PAGE_LIMIT,OrderSearch orderSearch, TbSystemUser user);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月23日
	 * @param 
	 * @return
	 * @throws Exception 
	*/
	boolean dispatchbulkPackingAdd(TbWaitDispatchDetail tbWaitDispatchDetail, TbSystemUser user) throws Exception;

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	int subBulkForwardingAdd(TbBulkSubForwardDetail tbBulkSubForwardDetail, TbSystemUser user)throws Exception;

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月23日
	 * @param 
	 * @return
	*/
	boolean carryBulkAdd(TbWaitDispatchDetail tbWaitDispatchDetail, TbSystemUser user);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月26日
	 * @param 
	 * @return
	*/
	TbOrder getExceptionByOrderId(Integer id);

	/**
	 * @description 
	 * @author liangLin
	 * @date 2017年12月26日
	 * @param 
	 * @return
	*/
	boolean rejectException(Integer id);

	/**
	 * @description 短驳管理：新建运单 获取承运车辆(平台)
	 * @author liangLin
	 * @date 2017年12月26日
	 * @param 
	 * @return
	*/
	List<TbOrderCarDetail> selectCarTeamsByPlatform(Integer projectId, Integer driverId,Integer shortType);

	/**
	 * @description app获取项目列表
	 * @author liangLin
	 * @param sysOrgCode 
	 * @date 2017年12月26日
	 * @param 
	 * @return
	*/
	List<ProjectAppHelp> appGetAllProjectByBulk(Integer id, String sysOrgCode);

	
	/**
	 * @description 根据项目id获取 正在运行的车辆
	 * @date 2018年3月15日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<TbOrderCarDetail> selectRuningCarTeams(Integer projectId);

	
	/**
	 * @description 获取司机信息
	 * @date 2018年3月16日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	TbOrderCarDetail selectDriverByDriverId(Integer driverId);

	/**
	 * @description 获取运单统计
	 * @date 2018年3月30日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	Integer queryCountsOrderOfDays(TbSystemUser tbSystemUser,Integer type);

	/**
	 * @description 查询短驳到货确认和计费确认异常运单列表
	 * @date 2018年4月4日
	 * @author liangdeng
	 * @param 
	 * @return
	 */
	DataGridResult selectExceptionOrderByStatus(Integer page, Integer pageLimit, Integer status,TbSystemUser tbSystemUser);

	Integer appWaitBillentCounts(TbSystemUser tbSystemUser);

}
