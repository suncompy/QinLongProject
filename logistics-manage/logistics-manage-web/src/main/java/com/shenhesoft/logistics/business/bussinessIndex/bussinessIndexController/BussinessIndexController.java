package com.shenhesoft.logistics.business.bussinessIndex.bussinessIndexController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Maps;
import com.shenhesoft.logistics.business.bussinessCount.BussinessCountService;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.StringUtils;

/**
 * 业务首页
 * @author liangdeng
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/bussinessCount")
public class BussinessIndexController {
	
	@Autowired
	private BussinessCountService bussinessCountService;
	
	/**
	 * @description 火运状态统计
	 * @author LiangDeng
	 * @date 2017年1月26日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/trainOrderStatusCount", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getTrainOrderStatusCount(@RequestParam Map<String, Object> form) {
	  form.put("sysOrgCode", StringUtils.isBlank(form.get("sysOrgCode"))?AppSession.getCurrentSysOrgCode():form.get("sysOrgCode").toString());
		List<Map<String, Object>> list = bussinessCountService.getTrainOrdersCount(form);
		return new ShResponse(HttpStatus.OK.value(),"获取列表成功",list);
	}
	
	/**
	 * @description 短驳状态统计
	 * @author LiangDeng
	 * @date 2017年1月30日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/bulkOrderStatusCount", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getBulkOrderStatusCount(@RequestParam Map<String, Object> form) {
	  form.put("sysOrgCode", StringUtils.isBlank(form.get("sysOrgCode"))?AppSession.getCurrentSysOrgCode():form.get("sysOrgCode").toString());
		List<Map<String, Object>> list = bussinessCountService.getBulkOrderStatusCount(form);
		return new ShResponse(HttpStatus.OK.value(),"获取列表成功",list);
	}
	   
    /**
     * @description 项目概览
     * @date 2017年1月30日
     * @param
     * @return
     */
    @RequestMapping(value = "/projectSurvey", method = RequestMethod.GET)
    @ResponseBody
    public ShResponse getProjectSurvey(@RequestParam Map<String, Object> form) {
      form.put("sysOrgCode", StringUtils.isBlank(form.get("sysOrgCode"))?AppSession.getCurrentSysOrgCode():form.get("sysOrgCode").toString());
        List<Map<String, Object>> list = bussinessCountService.getProjectSurvey(form);
        return new ShResponse(HttpStatus.OK.value(),"获取列表成功",convertMonthCol2(list));
    }
    private Map<String, Object> convertMonthCol2(List<Map<String, Object>> list){
      Map<String,Object> map = Maps.newHashMap();
      LinkedHashMap<String, Object> orderNum = Maps.newLinkedHashMap();
      LinkedHashMap<String, Object> sendNetWeight = Maps.newLinkedHashMap();
      LinkedHashMap<String, Object> reciveNetWeight = Maps.newLinkedHashMap();
      for(int i=0;i<list.size();i++){
        orderNum.put((i+1)+"", list.get(i).get("orderNum"));
        sendNetWeight.put((i+1)+"", list.get(i).get("sendNetWeight"));
        reciveNetWeight.put((i+1)+"", list.get(i).get("reciveNetWeight"));
      }
      map.put("orderNum", orderNum);
      map.put("sendNetWeight", sendNetWeight);
      map.put("reciveNetWeight", reciveNetWeight);
      return map;
    }
	/**
	 * @description 短驳运输统计
	 * @author LiangDeng
	 * @date 2017年1月26日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/bulkTrainSprotCount", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getBulkTrainSprotCount() {
	    Map<String,Object> map2 = Maps.newHashMap();Map<String,Object> map = Maps.newHashMap();
	    map2.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> listBulk = bussinessCountService.getBulkTrainSprotCount(map2);
		LinkedHashMap<String, Object> mapBulk = convertMonthCol3(listBulk);
		map.put("mapBulk", mapBulk);
		List<Map<String, Object>> listTrain = bussinessCountService.getTrainSprotCount(map2);
        LinkedHashMap<String, Object> mapTrain = convertMonthCol3(listTrain);
        map.put("mapTrain", mapTrain);
		return new ShResponse(HttpStatus.OK.value(),"获取列表成功",map);
	}
	
	/**
	 * @description 火运运输统计
	 * @author LiangDeng
	 * @date 2017年1月30日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/trainSprotCount", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getTrainSprotCount() {
	  Map<String,Object> map2 = Maps.newHashMap();
      map2.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = bussinessCountService.getTrainSprotCount(map2);
		LinkedHashMap<String, Object> map = convertMonthCol(list);
		return new ShResponse(HttpStatus.OK.value(),"获取列表成功",map);
	}
	private LinkedHashMap<String, Object> convertMonthCol3(List<Map<String, Object>> list){
      LinkedHashMap<String, Object> orderNum = Maps.newLinkedHashMap();
      for(int i=0;i<list.size();i++){
        orderNum.put((i+1)+"", list.get(i).get("orderNum"));
      }
      return orderNum;
    }
	private LinkedHashMap<String, Object> convertMonthCol(List<Map<String, Object>> list){
	  LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
      map.put("1月", list.get(0).get("1月"));
      map.put("2月", list.get(0).get("2月"));
      map.put("3月", list.get(0).get("3月"));
      map.put("4月", list.get(0).get("4月"));
      map.put("5月", list.get(0).get("5月"));
      map.put("6月", list.get(0).get("6月"));
      map.put("7月", list.get(0).get("7月"));
      map.put("8月", list.get(0).get("8月"));
      map.put("9月", list.get(0).get("9月"));
      map.put("10月", list.get(0).get("10月"));
      map.put("11月", list.get(0).get("11月"));
      map.put("12月", list.get(0).get("12月"));
      return map;
	}
	/**
     * @description 地图标注 车牌
     * @author LiangDeng
     * @date 2017年1月26日
     * @param
     * @return
     */
    @RequestMapping(value = "/map/marker", method = RequestMethod.GET)
    @ResponseBody
    public ShResponse getMapMarker(@RequestParam Map<String, Object> form) {
      form.put("sysOrgCode", StringUtils.isBlank(form.get("sysOrgCode"))?AppSession.getCurrentSysOrgCode():form.get("sysOrgCode").toString());
        List<Map<String, Object>> list = bussinessCountService.getMapPoints(form);
        return new ShResponse(HttpStatus.OK.value(),"获取列表成功",list);
    }
    /**
     * @description 地图标注 车牌
     * @author LiangDeng
     * @date 2017年1月26日
     * @param
     * @return
     */
    @RequestMapping(value = "/map/order/position", method = RequestMethod.GET)
    @ResponseBody
    public ShResponse getOrderMapPointPcs(@RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = bussinessCountService.getOrderMapPointPcs(map);
        return new ShResponse(HttpStatus.OK.value(),"获取列表成功",list);
    }
    /**
     * @description 地图标注 车牌
     * @author LiangDeng
     * @date 2017年1月26日
     * @param
     * @return
     */
    @RequestMapping(value = "/curOrg", method = RequestMethod.GET)
    @ResponseBody
    public ShResponse getCurOrg(@RequestParam(required=false) Map<String, Object> map) {
        Map<String, Object> result = bussinessCountService.getCurOrg(map);
        return new ShResponse(HttpStatus.OK.value(),"获取列表成功",result);
    }
}
