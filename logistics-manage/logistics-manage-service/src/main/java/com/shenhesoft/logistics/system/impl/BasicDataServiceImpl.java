package com.shenhesoft.logistics.system.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shenhesoft.logistics.business.mapper.BasicDataMapper;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.StringUtils;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.system.BasicDataService;

/**
 * 基础数据业务接口实现
 */
@Service("BasicDataServiceImpl")
public class BasicDataServiceImpl implements BasicDataService {

  @Autowired
  private BasicDataMapper basicDataMapper;

  @Autowired
  private TbProjectMapper projectMapper;
  /**
   * 获取维度表中，下拉框数据
   * 
   * @return
   */
  @Override
  public List<Map<String, String>> getDims(Map<String,Object> map) {
    return basicDataMapper.getDims(map);
  }

  /**
   * 获取下拉框树数据
   * 
   * @return
   */
  @Override
  public List<Map<String, String>> getTree(String type) {

    List<Map<String, String>> tree = null;

    switch (type) {
      // 案由
      case "cause":
        //tree = basicDataMapper.getCauseTree();
        break;
      case "causes":
          //tree = basicDataMapper.getCauses();
          break;   
      default:
        System.out.println("select2下拉树没有找到type:"+type);
        break;
    }

    return tree;
  }


  /**
   * 
   * 通用普通下拉框数据
   *
   * @param type 类型
   * @param text 检索用输入内容
   * @return
   */
  public List<Map<String, Object>> getSelections(Map<String,Object> map) {
    List<Map<String, Object>> selections = null;
    String type= StringUtils.isBlank(map.get("type"))?null:map.get("type").toString();
    String text = StringUtils.isBlank(map.get("text"))?null:map.get("text").toString();
    String id = StringUtils.isBlank(map.get("id"))?null:map.get("id").toString();
    switch (type) {
      // 获取test类型
      case "testType":
        selections = basicDataMapper.getTestType(text);
        break;
        //车型
      case "trainType":
    	  Map<String, Object> map2 = new HashMap<String, Object>();
	      map2.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
          selections = basicDataMapper.getTrainType(text,map2);
          break;
      //项目
      case "projectCode":
          selections = basicDataMapper.getProjectCode(map);
          break;  
      //支出账号
      case "provideCompanyId":
          selections = basicDataMapper.getProvideCompanyId(map);
          break;  
      //未使用油气卡号
      case "oilGasCardUnused":
        if(null==id){
          selections = Lists.newArrayList();
        }
        if("1".equals(id)){
          id="0";
        }else if("2".equals(id)){
          id="1";
        }else {
          id=null;
        }
        Map<String, Object> mapNew = Maps.newHashMap();
        mapNew.put("text",text);
        mapNew.put("id",id);
        selections = basicDataMapper.getOilGasCardUnused(mapNew);
        break;
      //油气卡领取人 
      case "reciverFinance":
        List<String> ids = Arrays.asList(id.split(","));
        selections = basicDataMapper.getReciverByFinId(text,ids);
        break;
      //客户业务联系人 
      case "bizContactor":
        map.put("customId", id);
        selections = basicDataMapper.getBizContactor(map);
        break;  
      //收发货企业、客户单位  
      case "customCompany":
        selections = basicDataMapper.getCustomCompany(map);
        break; 
      //查询栏分支机构  
      case "sysOrgSearch":
        //selections = basicDataMapper.getOrgTop(sysOrgCodeSearch,text);
        //selections = basicDataMapper.getOrgAll(sysOrgCodeSearch,text);
        //selections = basicDataMapper.getOrgBranchSelf(sysOrgCodeSearch,text);
        selections = basicDataMapper.getOrgBranchAll(map);
        break;
      //panel分支机构  
      case "sysOrgPanel":
        //selections = basicDataMapper.getOrgTop(sysOrgCode,text);
        selections = basicDataMapper.getOrgAll(map);
        //selections = basicDataMapper.getOrgBranchSelf(sysOrgCode,text);
        //selections = basicDataMapper.getOrgBranchAll(sysOrgCode,text);
        break;
      //顶级机构  
      case "sysOrgTop":
          selections = basicDataMapper.getOrgTop(map);
          break;  
        
      default:
        System.out.println("select2下拉框没有找到type:"+type);
        break;
    }
    return selections;
  }

  public List<Map<String, Object>> getSelectionsByStationId(String type, String text, String id) {
	    List<Map<String, Object>> selections = null;

	    switch (type) {
	      // 获取test类型
	      case "testType":
	    	//处理拼接的id 用","分割   projectId+","+trainStationIdAppend+","+kind;
	    	//kind  0接取 1送达  3火运
	    	String[] contarinArray = id.split(",");
	    	String  projectId = contarinArray[0];
	    	String  trainStationId = contarinArray[1];
	    	String  kind = contarinArray[2];
	    	//sysOrgCode在pc端 直接从session中取，在app端，没有session这个概念，所以需要传进来
	    	//这里 appOrPc 0表示app 1表示 pc
	    	String  sysOrgCode = contarinArray[3];
	    	String  appOrPc = contarinArray[4];
	    	TbProject project = projectMapper.selectByPrimaryKey(Integer.valueOf(projectId));
	    	//项目模式 0 汽运  1 接取 2 送达 3 火运 4 接取+火运 5 火运+送达 6 联运 7 接取+送达
	    	Byte sportType = project.getTransportType();
	    	//集装箱状态 0 空闲 1 使用中 2 运输中 
	    	Byte status = null;
	    	Map<String, Object> mapC = new HashMap<String, Object>();
	    	if(sportType == 1) {
	    		status = 0;
	    		mapC.put("status", status);
	    		mapC.put("trainStationId", Integer.valueOf(trainStationId));
	    		mapC.put("projectId", null);
	    	}else if(sportType == 2) {
	    		status = 0;
	    		mapC.put("status", status);
	    		mapC.put("trainStationId", Integer.valueOf(trainStationId));
	    		mapC.put("projectId", null);
	    	}else if(sportType == 3) {
	    		status = 0;
	    		mapC.put("status", status);
	    		mapC.put("trainStationId", Integer.valueOf(trainStationId));
	    		mapC.put("projectId", null);
	    	}else if(sportType == 4) {
	    		if(kind.equals("0")) {
	    			status = 0;
	    			mapC.put("status", status);
		    		mapC.put("trainStationId", Integer.valueOf(trainStationId));
		    		mapC.put("projectId", null);
	    		}else if(kind.equals("3")){
	    			status = 1;
	    			mapC.put("status", status);
		    		mapC.put("trainStationId", Integer.valueOf(trainStationId));
		    		mapC.put("projectId", Integer.valueOf(projectId));
	    		}
	    	}else if(sportType == 5) {
	    		if(kind.equals("3")) {
	    			status = 0;
	    			mapC.put("status", status);
		    		mapC.put("trainStationId", Integer.valueOf(trainStationId));
		    		mapC.put("projectId", null);
	    		}else if(kind.equals("1")){
	    			status = 1;
	    			mapC.put("status", status);
		    		mapC.put("trainStationId", Integer.valueOf(trainStationId));
		    		mapC.put("projectId", Integer.valueOf(projectId));
	    		}
	    	}else if(sportType == 6) {
	    		if(kind.equals("0")) {
	    			status = 0;
	    			mapC.put("status", status);
		    		mapC.put("trainStationId", Integer.valueOf(trainStationId));
		    		mapC.put("projectId", null);
	    		}else {
	    			status = 1;
	    			mapC.put("status", status);
		    		mapC.put("trainStationId", Integer.valueOf(trainStationId));
		    		mapC.put("projectId", Integer.valueOf(projectId));
	    		}
	    	}else if(sportType == 7) {
	    		if(kind.equals("0")) {
	    			status = 0;
	    			mapC.put("status", status);
		    		mapC.put("trainStationId", Integer.valueOf(trainStationId));
		    		mapC.put("projectId", null);
	    		}else if(kind.equals("1")){
	    			status = 1;
	    			mapC.put("status", status);
		    		mapC.put("trainStationId", Integer.valueOf(trainStationId));
		    		mapC.put("projectId", Integer.valueOf(projectId));
	    		}
	    	}
	    	if(appOrPc.equals("0")) {
	    		mapC.put("sysOrgCode", sysOrgCode);
	    	}else {
	    		mapC.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
	    	}
	        selections = basicDataMapper.getSelectionsByStationId(text,mapC);
	        break;
	      // 获取xxx
	      case "applyTopic":
	        //selections = basicDataMapper.getApplyTopics(text);
	        break;

	      default:
	        System.out.println("select2下拉框没有找到type:"+type);
	        break;
	    }
	    return selections;
	  }
}
