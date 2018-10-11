package com.shenhesoft.logistics.finance;

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
import com.google.common.collect.ImmutableMap;
import com.shenhesoft.logistics.common.annotation.RequestJsonParam;
import com.shenhesoft.logistics.common.page.DatatablesViewPage;
import com.shenhesoft.logistics.common.page.MapWapper;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

/**
 * 短驳打包信息表-控制层Action.
 * <p>
 * <a href="ShortPackController.java"><i>View Source</i></a>
 * </p>
 * 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/api")
public class ShortPackController {
	@Autowired
	private ShortPackService shortPackService;
	@Autowired
	private OilGasCardService oilGasCardService;
	/**
	 * 添加短驳打包信息表.
	 * 
	 * @param shortPack
	 *            {@linkplain com.shenhesoft.logistics.finance.ShortPack
	 *            短驳打包信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.ShortPack}&gt; -
	 *         短驳打包信息表实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/shortPack", method = RequestMethod.POST)
	@ResponseBody
	public ShResponse addShortPack(@RequestBody ShortPack shortPack) {
		// 验证表单内容
		AppUtils.validateModel(shortPack);
		TbSystemUser user = AppSession.getCurrentUser();
		String shOrderFinIds = shortPack.getShOrderFinIds();
		// 新增短驳打包信息表
		return new ShResponse(HttpStatus.CREATED.value(), "保存成功", shortPackService.addShortPack(shortPack,shOrderFinIds,user));
	}

	/**
	 * 查看短驳打包信息表详情.
	 * 
	 * @param id
	 *            主键
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.ShortPack}&gt; -
	 *         短驳打包信息表实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/shortPacks/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getShortPackById(@PathVariable("id") String id) {
		Map<String, Object> form = shortPackService.getShortPackById(id);
		// 获取短驳打包信息表详情
		return new ShResponse(HttpStatus.OK.value(), "查询成功", form);
	}

	/**
	 * 修改短驳打包信息表.
	 * 
	 * @param shortPack
	 *            {@linkplain com.shenhesoft.logistics.finance.ShortPack
	 *            短驳打包信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;
	 *         {@link com.shenhesoft.logistics.finance.ShortPack}&gt; -
	 *         短驳打包信息表实体的响应
	 *         </p>
	 */
	@RequestMapping(value = "/shortPack", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse editShortPackById(@RequestBody ShortPack shortPack) {
		// 验证表单内容
		AppUtils.validateModel(shortPack);
		// 更新短驳打包信息表信息
		shortPackService.editShortPackById(shortPack);

		return new ShResponse(HttpStatus.OK.value(), "修改成功", shortPack);
	}

	/**
	 * 删除指定短驳打包信息表.
	 * 
	 * @param id
	 *            主键
	 * @return 删除提示
	 */
	@RequestMapping(value = "/shortPacks/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ShResponse delShortPackById(@PathVariable("id") String id) {

		shortPackService.delShortPackById(id);

		return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
	}

	/**
	 * 批量删除指定短驳打包信息表.
	 * 
	 * @param ids
	 *            主键集合
	 * @return 删除提示
	 */
	@RequestMapping(value = "/shortPacks/batch", method = RequestMethod.DELETE)
	@ResponseBody
	public ShResponse delShortPackByIds(@RequestJsonParam(value = "ids", required = false) List<String> ids) {

		shortPackService.delShortPackByIds(ids);

		return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
	}

	/**
	 * 获取所有短驳打包信息表.
	 * 
	 * @param start
	 *            开始记录
	 * @param pageSize
	 *            分页大小
	 * @param form
	 *            {@linkplain com.shenhesoft.logistics.finance.ShortPack
	 *            短驳打包信息表实体}
	 * @return
	 *         <p>
	 *         {@link ShResponse}&lt;List&lt;
	 *         {@link com.shenhesoft.logistics.finance.ShortPack}&gt;&gt; -
	 *         短驳打包信息表实体集的响应
	 *         </p>
	 */
	@RequestMapping(value = "/shortPacks", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getShortPacks(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = shortPackService.getShortPacks(start, pageSize, map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	
	/*
	 * 交账打包
	 */
	@RequestMapping(value = "/getOilGasCardMoney", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse getOilGasCardMoney(String branchGroupName) {
		//获取项目部剩余
		Map<String, Object> map= shortPackService.getOilGasCardMoney(branchGroupName);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功",map);
	}
	
	/*
	 * 网点交账
	 */
	@RequestMapping(value = "/shortPackAbility", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse shortPackAbility(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
	  Map<String, Object> map = form.getInnerMap();
      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
		List<Map<String, Object>> list = shortPackService.getShortPackAbilitys(start, pageSize, map);
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}

	/**
	 * 运单打包领取人列表
	 * @author dusd
	 * @date 2018年1月18日
	 * @param shOrderFinIds
	 * @return
	 */
	@RequestMapping(value = "/shortPacks/listPackReceiveDriverByIds/{shOrderFinIds}", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse listPackReceiveDriverByIds(@PathVariable("shOrderFinIds") String shOrderFinIds) {
		List<Map<String, Object>> retroactivelyPayProjectList = shortPackService.listPackReceiveDriverByIds(shOrderFinIds);
		  return new ShResponse(HttpStatus.OK.value(), "查询运单打包领取人列表成功",retroactivelyPayProjectList);
	}
	
	/**
     * 根据领取人-返回司机开户行信息
     * @author dusd
     * @date 2018年1月18日
     * @return
     */
	@RequestMapping(value = "/shortPacks/driverBank/{driverId}", method = RequestMethod.GET)
    @ResponseBody
    public ShResponse getDriverBankByDriverId(@PathVariable("driverId") String driverId) {
        Map<String, Object> map = shortPackService.getDriverBankByDriverId(driverId);
          return new ShResponse(HttpStatus.OK.value(), "查询领取人银行成功",map);
    }

	/**
	 * 司机打包 对账明细 财务审核
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/shortPacks/financeAudit/{shPackIds}/{flag}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse financeAuditShortPackByIds(@PathVariable("shPackIds") String shPackIds,@PathVariable("flag") String flag,@RequestBody(required = false) TbSystemUser user) {
		shortPackService.financeAuditShortPackByIds(shPackIds,flag,user);
		String msg = "";
		if(flag.equals("0")) {
			msg = "财务审核成功";
		}else {
			msg = "财务审核不通过";
		}
		return new ShResponse(HttpStatus.NO_CONTENT.value(), msg);
	}
    /**
   * 司机打包 对账明细 财务审核
   * 
   * @param ids
   *            主键集合
   */
  @RequestMapping(value = "/shortPacks/settleStart/{shPackIds}", method = RequestMethod.PUT)
  @ResponseBody
  public ShResponse settleStart(@PathVariable("shPackIds") String shPackIds) {
      shortPackService.settleStart(shPackIds);
      return new ShResponse(HttpStatus.OK.value(), "开始结算成功!");
  }
  
	/**
	 * 司机打包 对账明细 反审核
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/shortPacks/againstAudit/{shPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse againstAuditShortPackByIds(@PathVariable("shPackIds") String shPackIds) {
		shortPackService.againstAuditShortPackByIds(shPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "反审核成功");
	}
	
	/**
	 * 司机打包 对账明细 解包
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/shortPacks/dissolve/{shPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse dissolveShortPackByIds(@PathVariable("shPackIds") String shPackIds) {
		shortPackService.dissolveShortPackByIds(shPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "解包成功");
	}
	
	/**
	 * 司机结算 受理明细 结算 
	 * 
	 * @param ids
	 *            主键集合
	 */
	@RequestMapping(value = "/shortPacks/settlePass/{shPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse settlePassShortPackByIds(@PathVariable("shPackIds") String shPackIds,@RequestBody ShortPack shortPack) {
		shortPackService.settlePassShortPackByIds(shPackIds,shortPack);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "结算成功");
	}
	
	/**
	 * 司机结算 受理明细 财务审核 
	 * 
	 * @param ids 主键集合
	 * @param passFlag 财务审核是否通过 0-通过 1-不通过
	 *            
	 */
	@RequestMapping(value = "/shortPacks/settleFinanceAudit/{shPackIds}/{passFlag}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse settleFinanceAuditShortPackByIds(@PathVariable("shPackIds") String shPackIds,@PathVariable("passFlag") String passFlag) {
		shortPackService.settleFinanceAuditShortPackByIds(shPackIds,passFlag);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "财务审核成功");
	}
	
	/**
	 * 司机结算 受理明细 反审核 
	 * 
	 * @param ids 主键集合
	 *            
	 */
	@RequestMapping(value = "/shortPacks/settleAgainstAudit/{shPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse settleAgainstAuditShortPackByIds(@PathVariable("shPackIds") String shPackIds) {
		shortPackService.settleAgainstAuditShortPackByIds(shPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "反审核成功");
	}
	
	/**
	 * 网点结算 受理明细 结算
	 * 
	 * @param ids 主键集合
	 *            
	 */
	@RequestMapping(value = "/shortPacks/settlePassNode/{shPackIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ShResponse settlePassNodeShortPackByIds(@PathVariable("shPackIds") String shPackIds) {
		shortPackService.settlePassNodeShortPackByIds(shPackIds);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "网点结算成功");
	}
	
	/**
	 * 网点结算 获取油气卡金额
	 * 
	 * @param suppliesNum 金额
	 *            
	 */
	@RequestMapping(value = "/shortPacks/queryMoney/{suppliesNum}", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse queryMoney(@PathVariable("suppliesNum") String suppliesNum) {
		List<OilGasCard> oilGasCardList = oilGasCardService.queryMoney(suppliesNum);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "获取金额成功" ,oilGasCardList);
	}
	
	/**
	 * 下拉框获取油气卡号
	 *//*
	@RequestMapping(value = "/shortPacks/queryOilGasCard/{text}", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse queryOilGasCard(@PathVariable("text") String text) {
		String type = "suppliesNums";
		String suppliesNum = shortPackService.queryOilGasCard(type, text);
		return new ShResponse(HttpStatus.NO_CONTENT.value(), "获取油气卡号成功" ,suppliesNum);
	}*/
	
	   /**
     * 获取网点结算油气卡表.
     * 
     * @param start
     *            开始记录
     * @param pageSize
     *            分页大小
     * @param form
     *            {@linkplain com.shenhesoft.logistics.finance.ShortPack
     *            短驳打包信息表实体}
     * @return
     *         <p>
     *         {@link ShResponse}&lt;List&lt;
     *         {@link com.shenhesoft.logistics.finance.ShortPack}&gt;&gt; -
     *         短驳打包信息表实体集的响应
     *         </p>
     */
    @RequestMapping(value = "/shortPacks/oilGasCards", method = RequestMethod.GET)
    @ResponseBody
    public ShResponse getOilGasCardUsed(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
            @RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
            @RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
      Map<String, Object> map = form.getInnerMap();
      map.put("sysOrgCode", AppSession.getCurrentSysOrgCode());
        List<Map<String, Object>> list = shortPackService.getOilGasCardUsed(start, pageSize, map);
        return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
    }
    
    
    /**
     * 网点结算 获取油气卡金额
     * 
     * @param suppliesNum 金额
     *            
     */
    @RequestMapping(value = "/shortPacks/{shPackId}/oilGasCards/{suppliesNum}", method = RequestMethod.PUT)
    @ResponseBody
    public ShResponse queryMoney(@PathVariable("shPackId") String shPackId,@PathVariable("suppliesNum") String suppliesNum) {
        Map<String,Object> map = ImmutableMap.of("shPackId", shPackId,"suppliesNum",suppliesNum);
        oilGasCardService.updatePackId(map);
        return new ShResponse(HttpStatus.OK.value(), "绑定结算油气卡成功" );
    }
    
    /**
     * 删除绑定结算油气表.
     * 
     * @param id 主键
     * @return 删除提示
     */
    @RequestMapping(value = "/shortPacks/oilGasCards/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ShResponse delOilGasCardById(@PathVariable("id") String id) {

      oilGasCardService.delOilGasCardById(id);

      return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
    }

    /**
    * 批量删除绑定结算油气表.
    * 
    * @param ids 主键集合
    * @return 删除提示
    */
    @RequestMapping(value = "/shortPacks/oilGasCards/batch", method = RequestMethod.DELETE)
    @ResponseBody
    public ShResponse delOilGasCardByIds(@RequestJsonParam(value = "ids", required = false) List<String> ids) {

      oilGasCardService.delOilGasCardByIds(ids);

      return new ShResponse(HttpStatus.NO_CONTENT.value(), "删除成功");
    }
}