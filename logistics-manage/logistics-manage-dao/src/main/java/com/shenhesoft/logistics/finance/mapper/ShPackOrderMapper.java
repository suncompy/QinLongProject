package com.shenhesoft.logistics.finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.finance.ShPackOrder;

/**
 * 短驳打包-运单中间表-Dao.
 * <p>
 * <a href="ShPackOrderMapper.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ShPackOrderMapper {

	/**
	 * 新增短驳打包-运单中间表.
	 * 
	 * @param shPackOrder
	 *            短驳打包-运单中间表实体
	 * @return dao成功失败标志
	 */
	int addShPackOrder(ShPackOrder shPackOrder);

	/**
	 * 批量新增短驳打包-运单中间表.
	 * 
	 * @param list
	 *            批量短驳打包-运单中间表实体
	 */
	void addShPackOrders(@Param(value = "list") List<ShPackOrder> list);

	/**
	 * 查看短驳打包-运单中间表详情.
	 * 
	 * @param id
	 *            主键
	 * @return 页面表单
	 */
	Map<String, Object> getShPackOrderById(String id);

	/**
	 * 修改短驳打包-运单中间表.
	 * 
	 * @param shPackOrder
	 *            短驳打包-运单中间表实体
	 * @return dao成功失败标志
	 */
	int editShPackOrderById(ShPackOrder shPackOrder);

	/**
	 * 批量修改短驳打包-运单中间表.
	 * 
	 * @param shPackOrder
	 *            短驳打包-运单中间表实体
	 * @param ids
	 *            主键集合
	 */
	void editShPackOrderByIds(@Param("map") ShPackOrder shPackOrder, @Param("list") List<String> ids);

	/**
	 * 删除指定短驳打包-运单中间表.
	 * 
	 * @param id
	 *            主键
	 * @return dao成功失败标志
	 */
	int delShPackOrderById(String id);

	/**
	 * 批量删除指定短驳打包-运单中间表.
	 * 
	 * @param ids
	 *            主键集合
	 * @return dao成功失败标志
	 */
	int delShPackOrderByIds(@Param("list") List<String> ids);

	/**
	 * 清空计量单位表.
	 * 
	 */
	void delShPackOrders();

	/**
	 * 获取所有短驳打包-运单中间表.
	 * 
	 * @param form
	 *            页面表单
	 * @return 结果集合
	 */
	List<Map<String, Object>> getShPackOrders(@Param(value = "map") Map<String, Object> form);

	/**
	 * 通过打包id查询打包运单中间表列表
	 * @author dusd
	 * @date 2018年1月18日
	 * @param shPackIdList
	 * @return
	 */
	List<Map<String, Object>> listShPackOrderByShPackIds(@Param(value = "list") List<String> shPackIdList);
}