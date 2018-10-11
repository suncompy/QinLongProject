package com.shenhesoft.logistics.enterprise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.shenhesoft.logistics.business.bussinessCount.BussinessCountService;
import com.shenhesoft.logistics.business.pojo.map.ExceptionMsg;
import com.shenhesoft.logistics.business.pojo.map.MapPoint;
import com.shenhesoft.logistics.common.ParamForm;
import com.shenhesoft.logistics.common.page.DatatablesViewPage;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.JsonUtils;
import com.shenhesoft.logistics.common.util.StringUtils;

/**
 * 地图标记表-控制层Action.
 * <p>
 * <a href="MapPointController.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class MapPointController {
	@Autowired
	private BussinessCountService bussinessCountService;

	/**
	 * 添加地图标记表.
	 * 
	 * @param mapPoint
	 *            {@linkplain com.shenhesoft.logistics.finance.MapPoint
	 *            地图标记表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.MapPoint}&gt; -
	 *         地图标记表实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/mapPoint", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse addMapPoint(@RequestBody MapPoint mapPoint) {
		// 验证表单内容
		AppUtils.validateModel(mapPoint);
		bussinessCountService.addMapPoint(mapPoint);
		// 新增地图标记表
		return new ShResponse(HttpStatus.CREATED.value(), "保存成功");
	}
	/**
     * 添加异常.
     * 
     * @param mapPoint
     *            {@linkplain com.shenhesoft.logistics.finance.ExceptionMsg
     *            地图标记表实体}
     * @return
     *         <p>
     *         {@link ShResponse}&lt;
     *         {@link com.shenhesoft.logistics.finance.ExceptionMsg}&gt; -
     *         地图标记表实体的响应
     *         </p>
     */
    @RequestMapping(value = "/exceptionMsg", method = RequestMethod.POST)
    @ResponseBody
    public ShResponse addMapPoint(@RequestBody ExceptionMsg exceptionMsg) {
        // 验证表单内容
        AppUtils.validateModel(exceptionMsg);
        bussinessCountService.addExceptionMsg(exceptionMsg);
        // 新增地图标记表
        return new ShResponse(HttpStatus.CREATED.value(), "保存成功");
    }
    /**
     * 获取异常表.
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param form {@linkplain com.shenhesoft.logistics.finance.ExceptionMsg 实体}
     * @return <p>{@link ShResponse}&lt;List&lt;{@link com.shenhesoft.logistics.finance.ExceptionMsg}&gt;&gt; - 实体集的响应</p>
     */
    @RequestMapping(value = "/exceptionMsgs", method = RequestMethod.POST)
    @ResponseBody
    public ShResponse getCustomerCheckingApp(@RequestBody ParamForm form) {
      int start = form.getStart();
      int length = form.getLength();
      String condition = form.getCondition();
      Map<String,Object> conditionMap = JsonUtils.jsonToPojo(condition, Map.class);
      List<Map<String, Object>> list = bussinessCountService.getExceptionMsgs(start, length, conditionMap);
      return new ShResponse(HttpStatus.OK.value(),"获取列表成功",new DatatablesViewPage(list));
    }
	/**
	 * 获取所有地图标记表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            {@linkplain com.shenhesoft.logistics.finance.MapPoint
	 *            地图标记表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;List&lt;
	 *         {@link com.shenhesoft.logistics.finance.MapPoint}&gt;&gt; -
	 *         地图标记表实体集的响应
	 *         </p>
	 */
	@RequestMapping(value = "/mapPoints", method = RequestMethod.POST)
	@ResponseBody
    public ShResponse getMapPoints(@RequestBody Map<String, Object> map) {
	    if(StringUtils.isBlank(map.get("sysOrgCode"))){
	      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
	    }
        List<Map<String, Object>> list = bussinessCountService.getMapPoints(map);
        return new ShResponse(HttpStatus.OK.value(),"获取列表成功",list);
    }
	
	/**
     * 获取短驳火运本月动态.
     * curMonthFlag curMonth
     * @param start
     *            开始记录
     * @param pageSize
     *            分页大小
     * @param form
     *            {@linkplain com.shenhesoft.logistics.finance.MapPoint
     *            地图标记表实体}
     * @return
     *         <p>
     *         {@link ShResponse}&lt;List&lt;
     *         {@link com.shenhesoft.logistics.finance.MapPoint}&gt;&gt; -
     *         地图标记表实体集的响应
     *         </p>
     */
    @RequestMapping(value = "/orderStatuss", method = RequestMethod.GET)
    @ResponseBody
    public ShResponse getOrderStatus(@RequestParam Map<String, Object> map) {
    	 Map<String,Object> mapFirst = Maps.newHashMap();
         mapFirst.putAll(map); 
        List<Map<String, Object>> listTrainOrderStatus = bussinessCountService.getTrainOrdersCount(map);
        List<Map<String, Object>> listBulkOrderStatus = bussinessCountService.getBulkOrderStatusCount(mapFirst);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("sumTrainOrderStatus", FormUtil.sumListMapValue(listTrainOrderStatus, "value"));
        result.put("listTrainOrderStatus", listTrainOrderStatus);
        result.put("sumBulkOrderStatus", FormUtil.sumListMapValue(listBulkOrderStatus, "value"));
        result.put("listBulkOrderStatus", listBulkOrderStatus);
        return new ShResponse(HttpStatus.OK.value(),"获取列表成功",result);
    }
}