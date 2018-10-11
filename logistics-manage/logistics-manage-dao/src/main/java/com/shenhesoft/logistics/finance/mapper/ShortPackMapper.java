package com.shenhesoft.logistics.finance.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.finance.ShortPack;

/**
 * 短驳打包信息表-Dao.
 * <p>
 * <a href="ShortPackMapper.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ShortPackMapper {

  /**
   * 新增短驳打包信息表.
   * 
   * @param shortPack 短驳打包信息表实体
   * @return dao成功失败标志
   */
  int addShortPack(ShortPack shortPack);

  /**
   * 批量新增短驳打包信息表.
   * 
   * @param list 批量短驳打包信息表实体
   */
  void addShortPacks(@Param(value = "list") List<ShortPack> list);

  /**
   * 查看短驳打包信息表详情.
   * 
   * @param id 主键
   * @return 页面表单
   */
  Map<String, Object> getShortPackById(String id);

  /**
   * 修改短驳打包信息表.
   * 
   * @param shortPack 短驳打包信息表实体
   * @return dao成功失败标志
   */
  int editShortPackById(ShortPack shortPack);

  /**
   * 存款.
   * 
   * @param map 实体
   * @return dao成功失败标志
   */
  int editFinanceMoneyAddById(Map<String,Object> map);
  /**
   * 扣款.
   * 
   * @param map 实体
   * @return dao成功失败标志
   */
  int editFinanceMoneySubById(Map<String,Object> map);
  /**
   * 批量修改短驳打包信息表.
   * 
   * @param shortPack 短驳打包信息表实体
   * @param ids 主键集合
   */
  void editShortPackByIds(@Param("map") ShortPack shortPack,@Param("list") List<String> ids);

  /**
   * 删除指定短驳打包信息表.
   * 
   * @param id 主键
   * @return dao成功失败标志
   */
  int delShortPackById(String id);
  
  /**
   * 批量删除指定短驳打包信息表.
   * 
   * @param ids 主键集合
   * @return dao成功失败标志
   */
  int delShortPackByIds(@Param("list") List<String> ids);

  /**
    * 清空计量单位表.
    * 
    */
  void delShortPacks();

  /**
   * 获取所有短驳打包信息表.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> getShortPacks(@Param(value = "map") Map<String, Object> form);
  
  /**
   * 获取所有短驳打包信息表.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> getShortPackAbilitys(@Param(value = "map") Map<String, Object> form);
  
  /**
   * 获取下拉款的油气卡号
   */
  String queryOilGasCard(@Param(value="text") String text);
  
  /**
   * 获取所有短驳打包信息表.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> getOilGasCardUsed(@Param("map") Map<String, Object> form);
  
  
	/**
	 * @description 项目部剩余油气卡金额
	 * @date 2018年4月21日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	BigDecimal getOilGasCardMoney(@Param("map") Map<String, Object> form);
}