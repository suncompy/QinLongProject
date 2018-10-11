package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.finance.CostPack;

public interface CostPackMapper {
	 /**
	   * 新增短驳打包信息表.
	   * 
	   * @param CostPack 短驳打包信息表实体
	   * @return dao成功失败标志
	   */
	  int addCostPack(CostPack shortPack);

	  /**
	   * 批量新增短驳打包信息表.
	   * 
	   * @param list 批量短驳打包信息表实体
	   */
	  void addCostPacks(@Param(value = "list") List<CostPack> list);

	  /**
	   * 查看短驳打包信息表详情.
	   * 
	   * @param id 主键
	   * @return 页面表单
	   */
	  Map<String, Object> getCostPackById(String id);

	  /**
	   * 修改短驳打包信息表.
	   * 
	   * @param CostPack 短驳打包信息表实体
	   * @return dao成功失败标志
	   */
	  int editCostPackById(CostPack shortPack);

	  /**
	   * 批量修改短驳打包信息表.
	   * 
	   * @param shortPack 短驳打包信息表实体
	   * @param ids 主键集合
	   */
	  void editCostPackByIds(@Param("map") CostPack shortPack,@Param("list") List<String> ids);

	  /**
	   * 删除指定短驳打包信息表.
	   * 
	   * @param id 主键
	   * @return dao成功失败标志
	   */
	  int delCostPackById(String id);
	  
	  /**
	   * 批量删除指定短驳打包信息表.
	   * 
	   * @param ids 主键集合
	   * @return dao成功失败标志
	   */
	  int delCostPackByIds(@Param("list") List<String> ids);

	  /**
	    * 清空计量单位表.
	    * 
	    */
	  void delCostPacks();

	  /**
	   * 获取所有短驳打包信息表.
	   * @param form 页面表单
	   * @return 结果集合
	   */
	  List<Map<String, Object>> getCostPacks(@Param(value = "map") Map<String, Object> form);
	  

	/**
	 * 通过打包id查询
	 * @author dusd
	 * @date 2018年1月18日
	 * @param shPackIdList
	 * @return
	 */
	List<Map<String, Object>> listCostPackOrderByCostPackIds(@Param(value = "list") List<String> CostPackIdList);

	  /**
	   * 查询审核金额
	   */
	Map<String, Object> queryAuditMoney(String shPackIds);

    /**
     * @description 
     * @date 2018年3月31日
     * @param 
     * @return
    */
    List<Map<String, Object>> getCostPackByIds(@Param("list") List<String> shPackIdList);

	List<Map<String, Object>> getCostOrderByCostPackId(@Param("map") Map<String, Object> map);

	TbOrder getOrderMinimeByCostPackId(@Param("map") Map<String, Object> map);

	TbOrder getOrderMaxTimeByCostPackId(@Param("map") Map<String, Object> map);

}