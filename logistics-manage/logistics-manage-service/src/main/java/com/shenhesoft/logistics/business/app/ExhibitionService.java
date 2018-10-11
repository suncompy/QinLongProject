package com.shenhesoft.logistics.business.app;

import java.util.List;
import java.util.Map;

/**
 * 短驳打包-运单中间表-业务层接口.
 * <p>
 * <a href="ExhibitionService.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ExhibitionService {

  /**
   * 检索项目展示最新项目列表.
   * 
   * @param form
   *            页面表单
   * @return 结果集合
   */
  public List<Map<String, Object>> getExhibitionTops(Map<String, Object> form);

  /**
   * 检索项目展示项目列表.
   * 
   * @param form
   *            页面表单
   * @return 结果集合
   */
  public List<Map<String, Object>> getExhibitionNotTops(Map<String, Object> form);
  
  /**
   * 检索项目展示项目列表.
   * 
   * @param form
   *            页面表单
   * @return 结果集合
   */
  public List<Map<String, Object>> getExhibitionNotTops(int start, int pageSize, Map<String, Object> form);
}