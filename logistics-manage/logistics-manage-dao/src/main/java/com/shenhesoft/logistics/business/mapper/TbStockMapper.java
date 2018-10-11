package com.shenhesoft.logistics.business.mapper;

import com.shenhesoft.logistics.business.helpPojo.FreightSpace;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
import com.shenhesoft.logistics.business.pojo.stock.TbStockExample;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbStockMapper {
    int countByExample(TbStockExample example);

    int deleteByExample(TbStockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbStock record);

    int insertSelective(TbStock record);

    List<TbStock> selectByExample(TbStockExample example);

    TbStock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbStock record, @Param("example") TbStockExample example);

    int updateByExample(@Param("record") TbStock record, @Param("example") TbStockExample example);

    int updateByPrimaryKeySelective(TbStock record);

    int updateByPrimaryKey(TbStock record);
    
	
	/**
	 * @description  入库总库存
	 * @date 2017年12月28日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	BigDecimal selectEnterQtyByProjectId(@Param("type")Byte type , @Param("id") Integer id);
	
	
	/**
	 * @description 出库总库存
	 * @date 2017年12月28日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	BigDecimal selectOutQtyByProjectId(@Param("type")Byte type , @Param("id") Integer id);
	
	
	/**
	 * @description 现有总库存
	 * @date 2017年12月28日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	BigDecimal selectCurrentQtyByProjectId(@Param("type")Byte type , @Param("id") Integer id);
	
	
	/**
	 * @description 调整总库存
	 * @date 2017年12月28日
	 * @author shilvfei
	 * @param  始发站点,到达站点
	 * @param   项目id       
	 * @return 
	 */
	BigDecimal selectAdjustQtyByProjectId(@Param("type")Byte type , @Param("id") Integer id);
	
	
	   
	/**
	 * @description 查询有库存的项目
	 * @date 2017年12月29日
	 * @author shilvfei
	 * @param  
	 * @return 项目id
	 */
	List<Integer> selectProjectId();
	
	
	
	/**
	 * @description  获取仓位平面图
	 * @date 2017年12月29日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<FreightSpace>  selectByProjectId(Integer id);
	
	
	/**
	 * @description  获取去重的站点
	 * @date 2017年12月29日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<Integer>  distinctStationId(Integer id);
	
	
	/**
	 * @description  根据站点获取去重的货场
	 * @date 2017年12月29日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<Integer>  distinctFreightYard(@Param("id")Integer id,@Param("projectId")Integer projectId);
	
	
	/**
	 * @description  根据去重的货场获取货位
	 * @date 2017年12月29日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	List<FreightSpace> selectCargoLocation(@Param("id")Integer id,@Param("projectId")Integer projectId);
	
}