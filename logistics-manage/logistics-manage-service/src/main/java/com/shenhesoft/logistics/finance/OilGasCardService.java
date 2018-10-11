package com.shenhesoft.logistics.finance;

import java.util.List;
import java.util.Map;

import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 油气卡信息表-业务层接口.
 * <p>
 * <a href="OilGasCardService.java"><i>View Source</i></a>
 * </p>
 * 
 * @author Jys
 * @date 2018-01-26
 * @version 1.0.0
 * @since 1.0.0
 */
public interface OilGasCardService {

	/**
	 * 添加油气卡打包信息表.
	 * 
	 * @param OilGasCardPack
	 *            油气卡信息表实体
	 * @param user 
	 * @return 页面表单
	 */
	public void addOilGasCard(OilGasCardPack oilGasCardPack, TbSystemUser user);
	
	/**
	 * 添加油气卡信息表.
	 * 
	 * @param OilGasCardPack
	 *            油气卡信息表实体
	 * @param user 
	 * @return 页面表单
	 */
	public void insertOilGasCard(OilGasCard oilGasCard, TbSystemUser user);
	
	 /**
	   * @description 删除原有油气卡号
	   * @author RuanXiang
	   * @date 2018年3月5日
	   * @param oilGasCardsId
	   */
	public void deleteOilGasCard(String oilGasCardsId);
	
	/**
	 * 批量添加油气卡信息表.
	 * 
	 * @param OilGasCardPack
	 *            油气卡信息表实体
	 * @param user 
	 * @return 页面表单
	 */
	public List<OilGasCardPack> addOilGasCards(List<OilGasCardPack> list, TbSystemUser user);

	/**
	 * 获取所有油气卡信息表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> queryOilGasCard(int start, int pageSize, Map<String, Object> form);

	/**
	 * 获取所有油气卡信息表.
	 * 
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> queryOilGasCard(Map<String, Object> form);
	  /**
	   * 获取是否已存在的油气卡号
	   */
	public List<Map<String,Object>> listOilGasCardNum(String id);
	/**
	 * 查看同一批油气卡信息表详情.
	 * 
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> queryOilGasCardByDate(int start, int pageSize,Map<String, Object> form);
	
	/**
	 * 查看同一批油气卡信息表详情.
	 * 
	 * @param form
	 *            页面表单
	 * @return list
	 */
	public List<Map<String, Object>> queryOilGasCardByDate(Map<String, Object> form);
	
	/**
	 * 查询分支机构
	 */
	//public List<Map<String, Object>> queryBranchGroupName(int start, int pageSize, Map<String, Object> form);
	
	/**
	 * 查询分支机构
	 */
	//public List<Map<String, Object>> queryBranchGroupName(Map<String, Object> form);
	List<OilGasCardPack> queryBranchGroupName();

	/**
	 * 查询金额
	 */
	public List<OilGasCard> queryMoney(String suppliesNum);
	
	/**
	 * 修改审核状态
	 */
	public void updateAuditStatus(String passFlag,String id);

  /**
   * @description 
   * @date 2018年3月13日
   * @param 
   * @return
  */
  public void updatePackId(Map<String, Object> map);

  /**
   * @description 
   * @author liangLin
   * @date 2018年3月13日
   * @param 
   * @return
  */
  public void delOilGasCardById(String id);

  /**
   * @description 
   * @author liangLin
   * @date 2018年3月13日
   * @param 
   * @return
  */
  public void delOilGasCardByIds(List<String> ids);
}