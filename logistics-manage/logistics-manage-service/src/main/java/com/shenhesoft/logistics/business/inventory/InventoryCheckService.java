package com.shenhesoft.logistics.business.inventory;

import java.util.List;

import com.shenhesoft.logistics.business.helpPojo.StuckHelpPojo;
import com.shenhesoft.logistics.business.pojo.stock.TbStock;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * @description 库存盘查
 * 
 * @author shilvfei
 * 
 * @date 2017年12月28日
 */
public interface InventoryCheckService {

	
	/**
	 * @description 获取库存盘查信息
	 * @date 2017年12月28日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	DataGridResult listProjectInventoryByPage(Integer page, Integer limit,TbProject search,Integer uid);
	
	/**
	 * @description 库存盘查:获取仓位图
	 * @date 2017年12月29日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	LogisticsResult getCargoLocationImg(Integer id);

	
	/**
	 * @description 库存盘查 :查看项目详情
	 * @date 2018年1月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	LogisticsResult getInventoryCheckDetail(Integer projectId);
	
	
	/**
	 * @description 通过项目id和货场id查询该货场下的货位信息
	 * @date 2018年1月2日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	LogisticsResult getStockByFreightYardId(Integer projectId,Integer freightYardId);

	
	/**
	 * @description  调整库存信息
	 * @date 2018年1月3日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	LogisticsResult updateStock(List<TbStock> list,TbSystemUser user);

	
	/**
	 * @description  库存盘查:入库
	 * @date 2018年1月15日
	 * @author shilvfei
	 * @param 
	 * @return
	 */
	LogisticsResult storageInventory(TbStock stock);

}
