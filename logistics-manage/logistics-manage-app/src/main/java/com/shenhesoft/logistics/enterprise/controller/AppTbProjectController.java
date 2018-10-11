package com.shenhesoft.logistics.enterprise.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shenhesoft.logistics.common.GeneralResponse;
import com.shenhesoft.logistics.common.util.Constants;
import com.shenhesoft.logistics.enterprise.service.AppTbProjectService;

/**
 * app端 项目信息 controller
 * @author dusd
 * @date 2017年12月27日
 */
@RestController
@RequestMapping("app/project/")
public class AppTbProjectController {
	
	/**
	 * 企业app列表分页数量
	 */
	@Value("${APP_ENTERPRISE_PAGE_LIMIT}")
	private Integer APP_ENTERPRISE_PAGE_LIMIT;
	
	/**
	 * app端 项目信息 service 接口
	 */
	@Autowired
	private AppTbProjectService appTbProjectService;
	
//	项目仓位平面图
	
	/**
	 * 项目查询-项目核查列表
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listProjectCheckApp", method = RequestMethod.POST)
	public GeneralResponse listProjectCheckApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbProjectService.listProjectCheckApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询项目列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 项目查询-项目运营管理列表 getProjectOperation
	 * @author dusd
	 * @date 2017年12月28日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listProjectOperationApp", method = RequestMethod.POST)
	public GeneralResponse listProjectOperationApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbProjectService.listProjectOperationApp(dataMap,APP_ENTERPRISE_PAGE_LIMIT);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询项目列表出现异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 库存调整 - 查询某项目的所属站点列表
	 * @author dusd
	 * @date 2018年1月3日
	 * @return
	 */
	@RequestMapping(value = "/listSiteProjectApp", method = RequestMethod.POST)
	public GeneralResponse listSiteProjectApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbProjectService.listSiteProjectApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询某项目的所属站点列表异常");
			e.printStackTrace();
			return generalResponse;
		}
	}
	
	/**
	 * 库存调整 - 通过站点id查询所有的货场
	 * @author dusd
	 * @date 2018年1月3日
	 * @return
	 */
	@RequestMapping(value = "/listFreightYardBySiteIdApp", method = RequestMethod.POST)
	public GeneralResponse listFreightYardBySiteIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbProjectService.listFreightYardBySiteIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("通过站点id查询所有的货场异常");
			e.printStackTrace();
			return generalResponse;
		}
	} 
	
	/**
	 * 库存调整 - 查询某货场下的某项目的货位信息
	 * projectId - 项目id
	 * freightYardId - 货场id
	 * @author dusd
	 * @date 2018年1月3日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listStockByFreightYardIdApp", method = RequestMethod.POST)
	public GeneralResponse listStockByFreightYardIdApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbProjectService.listStockByFreightYardIdApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("查询某货场下的某项目的货位信息异常");
			e.printStackTrace();
			return generalResponse;
		}
		
	}
	
	/**
	 * 库存调整 - 保存调整后的库存信息
	 * @author dusd
	 * @date 2018年1月5日
	 * @return
	 */
	@RequestMapping(value = "/changeStockApp", method = RequestMethod.POST)
	public GeneralResponse changeStockApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbProjectService.changeStockApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("保存调整后的库存信息异常");
			e.printStackTrace();
			return generalResponse;
		}
		
	}
	
	/**
	 * 通过项目id查询仓位平面图
	 * @author dusd
	 * @date 2018年1月3日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/listCargoLocationImgByProjectId", method = RequestMethod.POST)
	public GeneralResponse listCargoLocationImgByProjectId(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbProjectService.listCargoLocationImgByProjectId(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("通过项目id查询仓位平面图异常");
			e.printStackTrace();
			return generalResponse;
		}		
		
	}
	
	/**
	 * 查询可分配项目列表
	 * @author dusd
	 * @date 2018年1月5日
	 * @return
	 */
	@RequestMapping(value = "/listTbProjectForDistributionApp", method = RequestMethod.POST)
	public GeneralResponse listTbProjectForDistributionApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbProjectService.listTbProjectForDistributionApp(dataMap,APP_ENTERPRISE_PAGE_LIMIT);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("项目任务分配异常");
			e.printStackTrace();
			return generalResponse;
		}	
		
	}
	
	/**
	 * 项目任务分配 APP
	 * @author dusd
	 * @date 2018年1月4日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/saveTbProjectDistributionApp", method = RequestMethod.POST)
	public GeneralResponse saveTbProjectDistributionApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbProjectService.saveTbProjectDistributionApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("项目任务分配异常");
			e.printStackTrace();
			return generalResponse;
		}		
	}
	
	/**
	 * 暂停项目分配
	 * @author dusd
	 * @date 2018年1月5日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/stopTbProjectDistributionApp", method = RequestMethod.POST)
	public GeneralResponse stopTbProjectDistributionApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbProjectService.stopTbProjectDistributionApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("暂停项目分配异常");
			e.printStackTrace();
			return generalResponse;
		}		
	}
	
	/**
	 * 开始项目分配
	 * @author dusd
	 * @date 2018年1月5日
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/beginTbProjectDistributionApp", method = RequestMethod.POST)
	public GeneralResponse beginTbProjectDistributionApp(@RequestBody Map<String, String> dataMap) {
		try {
			return appTbProjectService.beginTbProjectDistributionApp(dataMap);
		} catch (Exception e) {
			GeneralResponse generalResponse = new GeneralResponse();
			generalResponse.setState(Constants.NO);
			generalResponse.setMsg("开始项目分配异常");
			e.printStackTrace();
			return generalResponse;
		}		
	}
	


}
