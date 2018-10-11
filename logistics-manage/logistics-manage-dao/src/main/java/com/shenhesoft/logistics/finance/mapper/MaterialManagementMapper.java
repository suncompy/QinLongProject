package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.shenhesoft.logistics.finance.Material;

/**
 * 油气卡信息表-Dao.
 * <p>
 * <a href="Material.java"><i>View Source</i></a>
 * </p>
 * @author JiangYS
 * @date 2018-02-07
 * @version 1.0.0
 * @since 1.0.0
 */
public interface MaterialManagementMapper {

	/**
	 * 新增物料信息表.
	 * @param Material 物料信息表实体
	 */
	  void addMaterial(Material materialManagement);
	
  /**
   * 获取物料信息表.
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> queryMaterial(@Param(value = "map") Map<String, Object> form);
  
  /**
   * 修改物料审核状态
   */
  void updateMaterial(Material materialManagement);
  
  /**
   * 获取公司账户
   */
  List<Map<String, Object>> queryCompanyAccount();
  
  /**
   * 通过id获取信息
   */
  Map<String, Object> queryMaterialById(String id);
}
