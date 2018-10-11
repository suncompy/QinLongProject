package com.shenhesoft.logistics.system.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.google.common.collect.ImmutableMap;
import com.shenhesoft.logistics.business.mapper.BasicDataMapper;
import com.shenhesoft.logistics.business.mapper.BussinessHomeMapper;
import com.shenhesoft.logistics.common.util.BizIdUtil;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.StringUtils;
import com.shenhesoft.logistics.manage.mapper.TbBranchGroupMapper;
import com.shenhesoft.logistics.manage.mapper.TbProjectMapper;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.system.CodeService;

/**
 * 编码生成-业务实现.
 * <p>
 * <a href="CodeServiceImpl.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CodeServiceImpl implements CodeService {
  private static final Lock lock = new ReentrantLock();
  @Autowired
  private BasicDataMapper basicDataMapper;
  @Autowired
  private BussinessHomeMapper bussinessHomeMapper;
  @Autowired
  private TbProjectMapper tbProjectMapper;
  @Autowired
  private TbBranchGroupMapper tbBranchMapper;
  /**
   * 生成项目编号.
   * 
   * @param branchGroup登录人所在顶级机构
   * @param projectCodeMax当前最大code
   * @return String 返回类型
   */
  public String createProjectCode(String sysOrgCode) {
    String projectCode = null;
    try {
      lock.lock();
      String shortCode =
          StringUtils.isBlank(getShortCode(sysOrgCode)) ? "" : getShortCode(sysOrgCode);
      String year = DateUtils.date2Str(new Date(), DateUtils.yearFormat);
      projectCode = StringUtils.isBlank(getProjectCodeMax(sysOrgCode)) ? shortCode + year + "001"
          : BizIdUtil.sub3Add1(getProjectCodeMax(sysOrgCode));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
    return projectCode;
  }
  public String getShortCode(String sysOrgCode){
    Map<String,Object> map = ImmutableMap.of("sysOrgCode", sysOrgCode);
    List<Map<String,Object>> list = basicDataMapper.getOrgTop(map);
    if(CollectionUtils.isEmpty(list) || CollectionUtils.isEmpty(list.get(0))
        || StringUtils.isBlank(list.get(0).get("id"))){
      return null;
    }
    TbBranchGroup b =  tbBranchMapper.selectByPrimaryKey(Integer.parseInt(list.get(0).get("id").toString()));
    return b.getShortCode();
  }
  public String getProjectCodeMax(String sysOrgCode){
    Map<String,Object> projectQueryMap = ImmutableMap.of("sysOrgCode", sysOrgCode);
    Map<String, Object> projectMap = bussinessHomeMapper.getProjectCodeMax(projectQueryMap);
    if(CollectionUtils.isEmpty(projectMap) || StringUtils.isBlank(projectMap.get("projectCodeMax"))){
      return null;
    }
    return projectMap.get("projectCodeMax").toString();
  }
  public String getOrderCodeMaxByProjectId(String sysOrgCode,Integer projectId){
    Map<String,Object> orderQueryMap = ImmutableMap.of("sysOrgCode", sysOrgCode,"projectId",projectId);
    Map<String, Object> orderMap = bussinessHomeMapper.getOrderCodeMaxByProjectId(orderQueryMap);
    if(CollectionUtils.isEmpty(orderMap) || StringUtils.isBlank(orderMap.get("orderCodeMax"))){
      return null;
    }
    return orderMap.get("orderCodeMax").toString();
  }
  public String getPackCodeMaxByProjectId(String sysOrgCode,Integer projectId){
    Map<String,Object> orderQueryMap = ImmutableMap.of("sysOrgCode", sysOrgCode,"projectId",projectId);
    Map<String, Object> orderMap = bussinessHomeMapper.getPackIdMax(orderQueryMap);
    if(CollectionUtils.isEmpty(orderMap) || StringUtils.isBlank(orderMap.get("custCheckConId"))){
      return null;
    }
    return orderMap.get("custCheckConId").toString();
  }
  public String getCheckCodeMaxByProjectId(String sysOrgCode,Integer projectId){
    Map<String,Object> orderQueryMap = ImmutableMap.of("sysOrgCode", sysOrgCode);
    Map<String, Object> orderMap = bussinessHomeMapper.getCheckIdMax(orderQueryMap);
    if(CollectionUtils.isEmpty(orderMap) || StringUtils.isBlank(orderMap.get("checkId"))){
      return null;
    }
    return orderMap.get("checkId").toString();
  }
  public String getPleaseTrainNumberMaxByProjectId(String sysOrgCode,Integer projectId){
    Map<String,Object> trainQueryMap = ImmutableMap.of("sysOrgCode", sysOrgCode,"projectId",projectId);
    Map<String, Object> trainMap = bussinessHomeMapper.getPleaseTrainNumberMaxByProjectId(trainQueryMap);
    if(CollectionUtils.isEmpty(trainMap) || StringUtils.isBlank(trainMap.get("pleaseTrainNumber"))){
      return null;
    }
    return trainMap.get("pleaseTrainNumber").toString();
  }

  /**
   * 生成运单编号.
   * 
   * @param branchGroup登录人所在顶级机构
   * @param projectCodeMax当前最大code
   * @return String 返回类型
   */
  public String createOrderCode(String sysOrgCode,Integer projectId) {
    String orderCode = null;
    try {
      lock.lock();
      TbProject tbProject = tbProjectMapper.selectByPrimaryKey(projectId);
      int n=tbProject.getProjectCode().length();
      String projectSno = tbProject.getProjectCode().substring(n-3, n);
      String shortCode =
          StringUtils.isBlank(getShortCode(sysOrgCode)) ? "" : getShortCode(sysOrgCode);
      String yyyyMMdd = DateUtils.date2Str(new Date(), DateUtils.yyyyMMdd);
      String orderCodeFirst = String.format("%s%sB%s001", shortCode,projectSno,yyyyMMdd);
      String orderCodeNoFirst = BizIdUtil.sub3Add1(getOrderCodeMaxByProjectId(sysOrgCode,projectId));
      orderCode = StringUtils.isBlank(getOrderCodeMaxByProjectId(sysOrgCode,projectId)) ? orderCodeFirst: orderCodeNoFirst;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
    return orderCode;
  }
  /**
   * 生成请车单号.
   * 
   * @param branchGroup登录人所在顶级机构
   * @param projectCodeMax当前最大code
   * @return String 返回类型
   */
  public String createTrainOrderCode(String sysOrgCode,Integer projectId) {
    String orderCode = null;
    try {
      lock.lock();
      TbProject tbProject = tbProjectMapper.selectByPrimaryKey(projectId);
      int n=tbProject.getProjectCode().length();
      String projectSno = tbProject.getProjectCode().substring(n-3, n);
      String shortCode =
          StringUtils.isBlank(getShortCode(sysOrgCode)) ? "" : getShortCode(sysOrgCode);
      String yyyyMMdd = DateUtils.date2Str(new Date(), DateUtils.yyyyMMdd);
      String orderCodeFirst = String.format("%s%sP%s001", shortCode,projectSno,yyyyMMdd);
      String orderCodeNoFirst = BizIdUtil.sub3Add1(getPleaseTrainNumberMaxByProjectId(sysOrgCode,projectId));
      orderCode = StringUtils.isBlank(getPleaseTrainNumberMaxByProjectId(sysOrgCode,projectId)) ?orderCodeFirst: orderCodeNoFirst;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
    return orderCode;
  }
  
  /**
   * 生成打包单号.
   * 客户对账列表的对账单号
   * @param branchGroup登录人所在顶级机构
   * @param projectCodeMax当前最大code
   * @return String 返回类型
   */
  public String createPackOrderCode(String sysOrgCode,Integer projectId) {
    String orderCode = null;
    try {
      lock.lock();
      TbProject tbProject = tbProjectMapper.selectByPrimaryKey(projectId);
      int n=tbProject.getProjectCode().length();
      String projectSno = tbProject.getProjectCode().substring(n-3, n);
      String shortCode =
          StringUtils.isBlank(getShortCode(sysOrgCode)) ? "" : getShortCode(sysOrgCode);
      String yyyyMM = DateUtils.date2Str(new Date(), DateUtils.yearMonthFormat);
      String orderCodeFirst = String.format("%s%s%s001", shortCode,projectSno,yyyyMM);
      String orderCodeNoFirst = BizIdUtil.sub3Add1(getPackCodeMaxByProjectId(sysOrgCode,projectId));
      orderCode = StringUtils.isBlank(getPackCodeMaxByProjectId(sysOrgCode,projectId)) ? orderCodeFirst: orderCodeNoFirst;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
    return orderCode;
  }
  /**
   * 生成对账单号.
   * 客户对账汽运等明细列表的对账单号、司机对账、费用对账的对账明细
   * @param branchGroup登录人所在顶级机构
   * @param projectCodeMax当前最大code
   * @return String 返回类型
   */
  public String createCheckFinCode(String sysOrgCode,Integer projectId) {
    String orderCode = null;
    try {
      lock.lock();
      TbProject tbProject = tbProjectMapper.selectByPrimaryKey(projectId);
      int n=tbProject.getProjectCode().length();
      String projectSno = tbProject.getProjectCode().substring(n-3, n);
      
      String orderCodeFirst = String.format("D%s001", projectSno);
      String orderCodeNoFirst = BizIdUtil.sub3Add1(getCheckCodeMaxByProjectId(sysOrgCode,projectId));
      orderCode = StringUtils.isBlank(getCheckCodeMaxByProjectId(sysOrgCode,projectId)) ? orderCodeFirst: orderCodeNoFirst;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
    return orderCode;
  }
}
