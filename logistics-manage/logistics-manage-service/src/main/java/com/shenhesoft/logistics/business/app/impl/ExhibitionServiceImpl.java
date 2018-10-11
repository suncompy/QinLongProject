package com.shenhesoft.logistics.business.app.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.shenhesoft.logistics.business.app.ExhibitionService;
import com.shenhesoft.logistics.business.mapper.ExhibitionMapper;

/**
 * 短驳打包-运单中间表-业务实现.
 * <p>
 * <a href="ExhibitionServiceImpl.java"><i>View Source</i></a>
 * </p>
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class ExhibitionServiceImpl implements ExhibitionService {
  
  @Autowired
  private ExhibitionMapper exhibitionMapper;
  
  /**
   * 检索项目展示最新项目列表.
   * 
   * @param form
   *            页面表单
   * @return 结果集合
   */
  public List<Map<String, Object>> getExhibitionTops(Map<String, Object> form){
    return exhibitionMapper.getExhibitionTops(form);
  }

  /**
  * 获取所有短驳打包-运单中间表.
  * 
  * @return 短驳打包-运单中间表分页
  */
  public List<Map<String, Object>> getExhibitionNotTops(int start, int pageSize, Map<String, Object> form) {
    PageHelper.offsetPage(start, pageSize);
    return this.getExhibitionNotTops(form);
  }

  /**
  * 获取所有短驳打包-运单中间表.
  * 
  * @return 短驳打包-运单中间表
  */
  public List<Map<String, Object>> getExhibitionNotTops(Map<String, Object> form) {
    return exhibitionMapper.getExhibitionNotTops(form);
  }

}