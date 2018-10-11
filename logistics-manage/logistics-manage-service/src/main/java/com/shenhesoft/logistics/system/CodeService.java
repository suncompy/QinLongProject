package com.shenhesoft.logistics.system;

/**
 * 编码生成-业务层接口.
 * <p>
 * <a href="CodeService.java"><i>View Source</i></a>
 * </p>
 * @author LiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CodeService {
  /**
   * 生成项目编号.
   * 
   * @param branchGroup登录人所在顶级机构
   * @param projectCodeMax当前最大code
   * @return String 返回类型
   */
  public String createProjectCode(String sysOrgCode);
  /**
   * 生成运单编号.
   * 
   * @param branchGroup登录人所在顶级机构
   * @param projectCodeMax当前最大code
   * @return String 返回类型
   */
  public String createOrderCode(String sysOrgCode,Integer projectId);
  /**
   * 生成请车单号.
   * 
   * @param branchGroup登录人所在顶级机构
   * @param projectCodeMax当前最大code
   * @return String 返回类型
   */
  public String createTrainOrderCode(String sysOrgCode,Integer projectId);
  /**
   * 生成打包单号.
   * 客户对账列表的对账单号
   * @param branchGroup登录人所在顶级机构
   * @param projectCodeMax当前最大code
   * @return String 返回类型
   */
  public String createPackOrderCode(String sysOrgCode,Integer projectId);
  /**
   * 生成对账单号.
   * 客户对账汽运等明细列表的对账单号、司机对账、费用对账的对账明细
   * @param branchGroup登录人所在顶级机构
   * @param projectCodeMax当前最大code
   * @return String 返回类型
   */
  public String createCheckFinCode(String sysOrgCode,Integer projectId);
}