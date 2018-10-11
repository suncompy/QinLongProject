package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 短驳打包信息表-业务层接口.
 * <p>
 * <a href="ShortPackService.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ShortPackService {

  /**
   * 添加短驳打包信息表.
   * 
   * @param shortPack 短驳打包信息表实体
   * @param user
   * @return 页面表单
   */
  public ShortPack addShortPack(ShortPack shortPack, String shOrderFinIds, TbSystemUser user);

  /**
   * 查看短驳打包信息表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  public Map<String, Object> getShortPackById(String id);

  /**
   * 修改短驳打包信息表.
   * 
   * @param shortPack 短驳打包信息表实体
   */
  public void editShortPackById(ShortPack shortPack);

  /**
   * 删除指定短驳打包信息表.
   * 
   * @param id 主键
   */
  public void delShortPackById(String id);

  /**
   * 批量删除指定短驳打包信息表.
   * 
   * @param ids 主键集合.
   */
  public void delShortPackByIds(List<String> ids);

  /**
   * 清空计量单位表.
   */
  public void delShortPacks();

  /**
   * 获取所有短驳打包信息表.
   * 
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getShortPacks(int start, int pageSize, Map<String, Object> form);

  /**
   * 获取所有短驳打包信息表.
   * 
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getShortPacks(Map<String, Object> form);

  /**
   * 获取网点交账表.
   * 
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getShortPackAbilitys(int start, int pageSize,
      Map<String, Object> form);

  /**
   * 获取网点交账表.
   * 
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getShortPackAbilitys(Map<String, Object> form);

  /**
   * 运单打包领取人列表
   * 
   * @author dusd
   * @date 2018年1月18日
   * @param shOrderFinIds
   * @return
   */
  public List<Map<String, Object>> listPackReceiveDriverByIds(String shOrderFinIds);

  /**
   * 根据领取人-返回司机开户行信息
   * 
   * @author dusd
   * @date 2018年1月18日
   * @return
   */
  Map<String, Object> getDriverBankByDriverId(String id);

  /**
   * 司机打包 对账明细 财务审核
   * 
   * @author dusd
   * @date 2018年1月18日
   * @param shPackIds
 * @param flag 
 * @param user 
   */
  public void financeAuditShortPackByIds(String shPackIds, String flag, TbSystemUser user);

  /**
   * 司机打包 对账明细 反审核
   * 
   * @author dusd
   * @date 2018年1月18日
   * @param shPackIds
   */
  public void againstAuditShortPackByIds(String shPackIds);

  /**
   * 司机打包 对账明细 解包
   * 
   * @author dusd
   * @date 2018年1月18日
   * @param shPackIds
   */
  public void dissolveShortPackByIds(String shPackIds);

  /**
   * 司机结算 受理明细 结算
   * 
   * @author dusd
   * @date 2018年1月18日
   * @param shPackIds
   */
  public void settlePassShortPackByIds(String shPackIds,ShortPack shortPack);

  /**
   * 司机结算 受理明细 财务审核
   * 
   * @author dusd
   * @date 2018年1月18日
   * @param shPackIds
   * @param passFlag 财务审核是否通过 0-通过 1-不通过
   */
  public void settleFinanceAuditShortPackByIds(String shPackIds, String passFlag);

  /**
   * 司机结算 受理明细 反审核
   * 
   * @author dusd
   * @date 2018年1月18日
   * @param shPackIds
   */
  public void settleAgainstAuditShortPackByIds(String shPackIds);

  /**
   * 网点结算
   * 
   * @author dusd
   * @date 2018年1月18日
   * @param shPackIds
   */
  public void settlePassNodeShortPackByIds(String shPackIds);

  /**
   * 获取下拉款的油气卡号
   */
  public String queryOilGasCard(String type, String text);



  /**
   * 获取网点结算油气卡表.
   * 
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> getOilGasCardUsed(Map<String, Object> form);

  /**
   * 获取网点结算油气卡表.
   * 
   * @param start 开始记录
   * @param pageSize 分页大小
   * @param form 页面表单
   * @return list
   */
  public List<Map<String, Object>> getOilGasCardUsed(int start, int pageSize,
      Map<String, Object> form);

  
  
/**
 * @description 项目部剩余油气卡金额
 * @date 2018年4月21日
 * @author shilvfei
 * @param 
 * @return
 */
public Map<String, Object> getOilGasCardMoney(String branchGroupName);
/**
 * 开始结算
 * 
 * @date 2018年1月18日
 * @param shPackIds
 */
public void settleStart(String shPackIds);
}
