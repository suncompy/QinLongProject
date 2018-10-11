package com.shenhesoft.logistics.business.project.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.google.common.collect.Lists;
import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrder;
import com.shenhesoft.logistics.common.annotation.RequestJsonParam;
import com.shenhesoft.logistics.common.exception.SystemException;
import com.shenhesoft.logistics.common.page.DatatablesViewPage;
import com.shenhesoft.logistics.common.page.MapWapper;
import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.DateUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.common.util.office.ExcelUtil;
import com.shenhesoft.logistics.manage.pojo.project.TbProject;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;
import com.shenhesoft.logistics.manage.search.OrderSearch;

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
public class ProjectCheck2Controller {
	@Autowired
	private ProjectOperationService projectOperationService;

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
	@RequestMapping(value = "/queryProjectCheck2", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse queryMaterial(
			@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "0") int pageSize,
			@RequestJsonParam(value = "condition", required = false) MapWapper<String, Object> form) {
		List<Map<String, Object>> list = projectOperationService.queryProjectCheck2(start, pageSize,
				form.getInnerMap());
		return new ShResponse(HttpStatus.OK.value(), "获取列表成功", new DatatablesViewPage(list));
	}
	/**
     * 导出1
     * @param request 页面表单
     * @param response 输出流
     * @return
     */
    @RequestMapping(value = "/queryProjectCheck2/export", method = RequestMethod.GET)
    @ResponseBody
    public ShResponse export1(HttpServletRequest request,HttpServletResponse response) {
        Map<String,Object> map =FormUtil.getParameterMap(request);
        //全部
        List<Map<String, Object>> datas = projectOperationService.queryProjectCheck2(map);
        String[] heads = {"项目编号","项目类型","联运模式","分支机构","发货企业","收货企业","货物品名","应付现金","应付油气","汽运到货吨位","汽运费用","汽运扣损金额","接取到货吨位","接取费用","接取扣损金额","火运发送吨位","火运费用","损耗吨位","送达到货吨位","送达费用","扣损金额","汽运已对账吨位","汽运已开票吨位","汽运已结算吨位","接取已对账吨位","接取已开票吨位","接取已结算吨位","火运已对账吨位","火运已开票吨位","火运已结算吨位","送达已对账吨位","送达已开票吨位","送达已结算吨位"};
        String[] headCodes = {"projectCode","projectTypeName","transportTypeName","branchGroupName","sendCargoCompanyName","receiveCargoCompanyName","cargoName","receiptCashAmount","receiptSuppliesAmount","ccontainerReceiptNet","cpickUpMoney","cdeductionMoney","containerReceiptNet","pickUpMoney","deductionMoney","entruckWeight","trainProduceMoney","entruckArriveWeight","sendContainerReceiptNet","sendUpMoney","sendDeductionMoney","cpackedMoney","cinvoicedWeight","csettledWeight","rpackedMoney","rinvoicedWeight","settledWeight","tpackedMoney","tinvoicedWeight","tsettledWeight","spackedMoney","sinvoicedWeight","ssettledWeight"};
        
        if(ExcelUtil.createExcel(request,response, "项目核查"+DateUtils.getCurrentTime(), "项目核查", heads, headCodes, datas)){
            return new ShResponse(HttpStatus.OK.value(), "","下载成功");
        }
        return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
    }
    
    /**
     * 导出2
     * @param request 页面表单
     * @param response 输出流
     * @return
     */
    @RequestMapping(value = "/queryProjectCheck1/export", method = RequestMethod.GET)
    @ResponseBody
    public ShResponse export(HttpServletRequest request,HttpServletResponse response) {
        Map<String,Object> map =FormUtil.getParameterMap(request);
        //全部
        List<Map<String, Object>> datas = projectOperationService.queryProjectCheck2(map);
        String[] heads = {"项目编号","项目类型","联运模式","分支机构","发货企业","收货企业","货物品名","应付现金","应付油气","汽运到货吨位","汽运费用","汽运扣损金额","接取到货吨位","接取费用","接取扣损金额","火运发送吨位","火运费用","损耗吨位","送达到货吨位","送达费用","扣损金额"};
        String[] headCodes = {"projectCode","projectTypeName","transportTypeName","branchGroupName","sendCargoCompanyName","receiveCargoCompanyName","cargoName","receiptCashAmount","receiptSuppliesAmount","ccontainerReceiptNet","cpickUpMoney","cdeductionMoney","containerReceiptNet","pickUpMoney","deductionMoney","entruckWeight","trainProduceMoney","entruckArriveWeight","sendContainerReceiptNet","sendUpMoney","sendDeductionMoney"};
        
        if(ExcelUtil.createExcel(request,response, "项目明细"+DateUtils.getCurrentTime(), "项目明细", heads, headCodes, datas)){
            return new ShResponse(HttpStatus.OK.value(), "","下载成功");
        }
        return new ShResponse(HttpStatus.OK.value(), "","下载失败"); 
    }
}