package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.finance.OilGasCard;
import com.shenhesoft.logistics.finance.OilGasCardPack;
import com.shenhesoft.logistics.finance.ShortPack;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;

/**
 * 油气卡信息表-Dao.
 * <p>
 * <a href="OilGasCardMapper.java"><i>View Source</i></a>
 * </p>
 * @author Jys
 * @date 2018-01-26
 * @version 1.0.0
 * @since 1.0.0
 */
public interface OilGasCardMapper {

  /**
   * 新增油气卡打包信息表.
   * 
   * @param OilGasCardPack 油气卡信息表实体
   * @return dao成功失败标志
   */
  void addOilGasCard(OilGasCardPack oilGasCardPack);
  
  /**
   * 新增油气卡信息表.
   * 
   * @param OilGasCard 油气卡信息表实体
   * @return dao成功失败标志
   */
  void insertOilGasCard(OilGasCard oilGasCard);
  
  /**
   * @description 删除原有油气卡号
   * @author RuanXiang
   * @date 2018年3月5日
   * @param oilGasCardsId
   */
  void deleteOilGasCard(String oilGasCardsId);

  /**
   * 批量新增油气卡信息表.
   * 
   * @param list 批量油气卡信息表实体
   */
  void addOilGasCards(@Param(value = "list") List<OilGasCardPack> list);

  /**
   * 根据日期获取油气卡信息表.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> queryOilGasCard(@Param(value = "map") Map<String, Object> form);
  /**
   * 获取是否已存在的油气卡号
   */
  List<Map<String, Object>> listOilGasCardNum(String id);
  /**
   * 查看同一批油气卡信息表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  List<Map<String, Object>> queryOilGasCardByDate(@Param(value = "map") Map<String, Object> form);

  /**
   * 查询分支机构
   */
  //List<Map<String, Object>> queryBranchGroupName(@Param(value = "map") Map<String, Object> form);
  List<OilGasCardPack> queryBranchGroupName();
  
  /**
   * 查询金额
   */
  List<OilGasCard> queryMoney(String suppliesNum);
  
  /**
   * 修改油气卡使用状态
   */
  void updateOilGasCardStatus(OilGasCard oilGasCard);
  
  /**
   * 修改油气卡审核状态
   */
  void updateAuditStatus(OilGasCardPack oilGasCard);
  
  /**
   * 根据id查询油气打包表信息
   */
  Map<String,Object> queryOilGasCardById(String id);

  /**
   * @description 
   * @author liangLin
   * @date 2018年3月13日
   * @param 
   * @return
  */
  void updatePackId(@Param("map") Map<String, Object> map);
  
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
  public void delOilGasCardByIds(@Param("list") List<String> ids);
}
