package com.shenhesoft.logistics.finance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.shiro.util.CollectionUtils;
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
import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.collect.ImmutableMap;
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
 * <a href="OilGasCardController.java"><i>View Source</i></a>
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
public class OilGasCardController {
	@Autowired
	private OilGasCardService oilGasCardService;

	/**
	 * 添加油气卡信息表.
	 * 
	 * @param oilGasCard
	 *            {@linkplain com.shenhesoft.logistics.finance.OilGasCardPack
	 *            短驳打包信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.OilGasCardPack}&gt; -
	 *         油气卡信息表实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/addOilGasCard", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse addOilGasCard(@RequestBody OilGasCardPack oilGasCard) {
		// 验证表单内容
		AppUtils.validateModel(oilGasCard);
		TbSystemUser user = AppSession.getCurrentUser();
		// 新增短驳打包信息表
		oilGasCardService.addOilGasCard(oilGasCard,user);
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
	@RequestMapping(value = "/queryOilGasCard", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse queryOilGasCard(
			@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = oilGasCardService.queryOilGasCard(start, pageSize,
				map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	
	/*
	 * 查询油气卡表.
	 * 
	 */
	@RequestMapping(value = "/queryOilGasCardByDate", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse queryOilGasCardByDate(
			@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = oilGasCardService.queryOilGasCardByDate(start, pageSize,
				map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	
	/**
	 * @description 查询分支机构
	 */
	@RequestMapping(value="/queryBranchGroupName", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse queryBranchGroupName(){
		List<OilGasCardPack> oilGasCardList = oilGasCardService.queryBranchGroupName();
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功",oilGasCardList);
	}
	
	/**
	 * 导入油气卡号
	 * @param request 页面表单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/OilGasCard/imp/{id}", method = RequestMethod.POST)
	public ShResponse impOilGasCard(MultipartHttpServletRequest request, @PathVariable("id") String id) {
		
		Map<String, MultipartFile> fileMap=request.getFileMap();
		if (CollectionUtils.isEmpty(fileMap) || CollectionUtils.isEmpty(fileMap.keySet())) {
			return new ShResponse(HttpStatus.OK.value(), "error", "上传附件为空");
		}
		try {
		   for (String fileKey : fileMap.keySet()) {
		       // 1取得上传文件
		       if (StringUtils.isBlank(fileKey)) {
				   continue;
		       }
		       MultipartFile file = (MultipartFile) fileMap.get(fileKey);
               //2解析
               List<OilGasCardModelXls> oilGasCardModel = ExcelUtil.xlsToBean(file.getInputStream(),1,OilGasCardModelXls.class);
               AppUtils.validateModel(oilGasCardModel);
               List<OilGasCard> list = FormUtil.copyProperties(oilGasCardModel,OilGasCard.class);
               if(CollectionUtils.isEmpty(list)){
                 continue;
               }
               for(OilGasCard oilGasCard : list) {
                   TbSystemUser user = AppSession.getCurrentUser();
                   //判断卡号和金额是否为空
                   if(StringUtils.isBlank(oilGasCard.getCardNum()) || oilGasCard.getCardMoney()==null
                       || oilGasCard.getCardMoney().compareTo(BigDecimal.ZERO)==0) {
                       continue;
                   }
                   oilGasCard.setOilGasCardsId(id);
                   Map<String,Object> form = ImmutableMap.of("cardNum", oilGasCard.getCardNum());
                   List<Map<String,Object>> oCards = oilGasCardService.queryOilGasCard(form);
                   if(CollectionUtils.isEmpty(oCards) || CollectionUtils.isEmpty(oCards.get(0))){
                     oilGasCardService.insertOilGasCard(oilGasCard,user);
                   } 
               }
		   }
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("文件上传失败");
		}
		return new ShResponse(HttpStatus.OK.value(), "success", "上传成功");
	}
	
	/**
	 * 审核
	 */
	@RequestMapping(value = "/updateOilGasCardAuditStatus/{passFlag}/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse updateOilGasCardAuditStatus(@PathVariable("passFlag") String passFlag,@PathVariable("id") String id) {
		oilGasCardService.updateAuditStatus(passFlag,id);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "审核成功");
	}
}