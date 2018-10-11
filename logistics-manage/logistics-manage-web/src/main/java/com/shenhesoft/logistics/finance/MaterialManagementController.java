package com.shenhesoft.logistics.finance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shenhesoft.logistics.common.annotation.RequestJsonParam;
import com.shenhesoft.logistics.common.exception.SystemException;
import com.shenhesoft.logistics.common.page.DatatablesViewPage;
import com.shenhesoft.logistics.common.page.MapWapper;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.office.ExcelUtil;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 油气卡信息表-控制层Action.
 * <p>
 * <a href="MaterialManagementController.java"><i>View Source</i></a>
 * </p>
 * 
 * @author Jys
 * @date 2018-01-26
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class MaterialManagementController {
	@Autowired
	private MaterialManagementService materialManagementService;

	/*
	 * 新增物料表
	 */
	@RequestMapping(value = "/addMaterial", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse addMaterial(@RequestBody Material materialManagement) {
		// 验证表单内容
		AppUtils.validateModel(materialManagement);
		// 新增短驳打包信息表
		materialManagementService.addMaterial(materialManagement);
		return new ShResponse(HttpStatus.CREATED.value(), "获取列表成功");
	}
	
	/**
	 * 获取所有油气卡表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            {@linkplain com.shenhesoft.logistics.finance.OilGasCardPack
	 *            短驳运单财务表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;List&lt;
	 *         {@link com.shenhesoft.logistics.finance.OilGasCardPack}
	 *         &gt;&gt; - 油气卡实体集的响应
	 *         </p>
	 */
	@RequestMapping(value = "/queryMaterial", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse queryMaterial(
			@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	    Map<String, Object> map = form.getInnerMap();
	    map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
	    List<Map<String, Object>> list = materialManagementService.queryMaterial(start, pageSize,
				map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	
	/**
	 * 审核
	 */
	@RequestMapping(value = "/updateMaterialAuditStatus/{passFlag}/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse updateMaterialAuditStatus(@PathVariable("passFlag") String passFlag,@PathVariable("id") String id) {
		materialManagementService.updateAuditStatus(passFlag, id);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "审核成功");
	}
	
	 /**
	   * 获取公司账户
	   */
	  @RequestMapping(value = "/queryCompanyAccount", method = RequestMethod.GET)
	  @ResponseBody
	  public ShResponse queryCompanyAccount() {
		List<Map<String, Object>> form = materialManagementService.queryCompanyAccount();
	    // 获取客户对账设置表详情
	    return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	  }
}