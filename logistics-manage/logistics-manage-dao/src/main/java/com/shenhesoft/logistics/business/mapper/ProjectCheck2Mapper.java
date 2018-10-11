package com.shenhesoft.logistics.business.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

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
public interface ProjectCheck2Mapper {

  /**
   * 项目核查
   * @param form 页面表单
   * @return 结果集合
   */
  List<Map<String, Object>> queryProjectCheck2(@Param(value = "map") Map<String, Object> form);
  
}
