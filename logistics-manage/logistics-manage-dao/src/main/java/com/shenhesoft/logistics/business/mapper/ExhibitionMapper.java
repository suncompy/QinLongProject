package com.shenhesoft.logistics.business.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 短驳打包-运单中间表-Dao.
 * <p>
 * <a href="ExhibitionMapper.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ExhibitionMapper {

	/**
	 * 检索项目展示最新项目列表.
	 * 
	 * @param form
	 *            页面表单
	 * @return 结果集合
	 */
	List<Map<String, Object>> getExhibitionTops(@Param(value = "map") Map<String, Object> form);

    /**
     * 检索项目展示项目列表.
     * 
     * @param form
     *            页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getExhibitionNotTops(@Param(value = "map") Map<String, Object> form);
}