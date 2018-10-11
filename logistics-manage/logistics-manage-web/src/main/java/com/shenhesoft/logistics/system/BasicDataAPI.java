package com.shenhesoft.logistics.system;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Maps;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;

/**
 * 基础数据api
 * 
 * @author LiuJiefeng
 */
@Controller("BasicDataAPI")
@RequestMapping("/api/basicdata")
public class BasicDataAPI {

  @Resource
  private BasicDataService basicDataService;

  /**
   * 通用维度下拉框数据
   * 
   * @return
   */
  @RequestMapping(value = "/dims/{pid}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse<List<Map<String, String>>> getDims(@PathVariable("pid") String pid,
      @RequestParam(value = "text", required = false, defaultValue = "") String text) {
    String sysOrgCode = AppSession.getCurrentSysOrgCode();
    Map<String,Object> map = Maps.newHashMap();
    map.put("pid", pid);
    map.put("text", text);
    map.put("sysOrgCode",sysOrgCode);
    return new ShResponse<List<Map<String, String>>>(HttpStatus.OK.value(), "查询成功",
        basicDataService.getDims(map));

  }

  /**
   * 通用普通下拉框数据
   */
  @RequestMapping(value = "/types/{type}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse<List<Map<String, Object>>> getSelections(
      @RequestParam(value = "text", required = false, defaultValue = "") String text,
      @PathVariable("type") String type) {
    String sysOrgCode = AppSession.getCurrentSysOrgCode();
    Map<String,Object> map = Maps.newHashMap();
    map.put("type", type);
    map.put("text", text);
    map.put("sysOrgCode",sysOrgCode);
    return new ShResponse<List<Map<String, Object>>>(HttpStatus.OK.value(), "查询成功",
        basicDataService.getSelections(map));

  }

  /**
   * 通用下拉框树数据(即text检索)
   * 
   * @return
   */
  @RequestMapping(value = "/trees/{type}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse<List<Map<String, String>>> getTrees(@PathVariable("type") String type) {

    return new ShResponse<List<Map<String, String>>>(HttpStatus.OK.value(), "查询成功",
        basicDataService.getTree(type));

  }

  @RequestMapping(value = "/types/{type}/ids/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse<List<Map<String, Object>>> getSelection(
      @RequestParam(value = "text", required = false, defaultValue = "") String text,
      @PathVariable("type") String type,
      @PathVariable("id") String id) {
    String sysOrgCode = AppSession.getCurrentSysOrgCode();
    Map<String,Object> map = Maps.newHashMap();
    map.put("id", id);
    map.put("type", type);
    map.put("text", text);
    map.put("sysOrgCode",sysOrgCode);
    return new ShResponse<List<Map<String, Object>>>(HttpStatus.OK.value(), "查询成功",
        basicDataService.getSelections(map));
  }
  
  /**
   * 火运集装箱通用普通下拉框数据
   */
  @RequestMapping(value = "/typesByTrain/{type}/ids/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ShResponse<List<Map<String, Object>>> getSelectionsByStationId(
      @RequestParam(value = "text", required = false, defaultValue = "") String text,
      @PathVariable("id") String id,
      @PathVariable("type") String type) {

    return new ShResponse<List<Map<String, Object>>>(HttpStatus.OK.value(), "查询成功",
        basicDataService.getSelectionsByStationId(type, text, id));

  }

}
