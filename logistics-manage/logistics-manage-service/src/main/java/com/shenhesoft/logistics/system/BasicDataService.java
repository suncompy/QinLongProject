package com.shenhesoft.logistics.system;

import java.util.List;
import java.util.Map;

/**
 * 基础数据接口
 *
 */
public interface BasicDataService {

	/**
	 * 
	 * 通用普通下拉框数据
	 *
	 * @param type
	 *            类型
	 * @param text
	 *            检索用输入内容
	 * @return
	 */
	List<Map<String, Object>> getSelections(Map<String,Object> map);

	/**
	 * 获取维度表中，下拉框数据
	 * 
	 * @return
	 */
	List<Map<String, String>> getDims(Map<String,Object> map);
	
	/**
	 * 获取下拉框树
	 * @param type
	 * @return
	 */
	List<Map<String, String>> getTree(String type);

	List<Map<String, Object>> getSelectionsByStationId(String type, String text, String id);

}
