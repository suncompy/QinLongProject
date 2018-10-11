// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.web.financialManagement;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenhesoft.logistics.common.page.ShResponse;
import com.shenhesoft.logistics.common.util.DataGridResult;
import com.shenhesoft.logistics.common.util.LogisticsResult;
import com.shenhesoft.logistics.manage.finacialManagement.interfaces.PaymentModelService;
import com.shenhesoft.logistics.manage.helpPojo.PaymentDetail;

/**
 * @description 财务管理---支付模式
 *
 * @author LiangDeng
 *
 * @date 2017年12月11日
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/payment")
public class PaymentModelController {

	@Autowired
	private PaymentModelService paymentModelService;

	@Value("${PAGE_NUM}")
	private Integer PAGE_NUM;
	@Value("${CUSTOMER_PAGE_LIMIT}")
	private Integer CUSTOMER_PAGE_LIMIT;

	/**
	 * @description 支付模式列表
	 * @author LiangDeng
	 * @date 2017年12月11日
	 * @param
	 * @return
	 */
	@RequestMapping("/paymentList")
	public String paymentModel(Model model) {
		DataGridResult result = paymentModelService.paymentList(PAGE_NUM, CUSTOMER_PAGE_LIMIT);
		model.addAttribute("paymentList", result);
		return "/html/manage/financialManagement/paymentMode";
	}

	// 分页查询
	@RequestMapping("/selectPayment")
	@ResponseBody
	public LogisticsResult selectCustomer(Integer page) {
		// 获取客户信息
		DataGridResult result = paymentModelService.paymentList(page, CUSTOMER_PAGE_LIMIT);
		return LogisticsResult.ok(result.getRows());
	}

	/**
	 * @description 删除支付模式
	 * @author LiangDeng
	 * @date 2017年12月11日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/deletePayment", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult deletePayment(@RequestParam List<Integer> ids) {
		// 删除之前先判断是否关联项目短驳承运表
		/*
		 * for (Integer id : ids) { List<Integer> shortBargeId =
		 * paymentModelService.selectShortBargeIdById(id); if(shortBargeId !=
		 * null && shortBargeId.size()>0){ return LogisticsResult.build(401,
		 * "该支付模式关联了短驳承运，不可删除"); } }
		 */
		int row = 0;
		for (Integer id : ids) {
			row = row + paymentModelService.delPaymentById(id);
		}
		if (ids.size() != row) {
			return LogisticsResult.build(400, "删除失败");
		}
		return LogisticsResult.ok();
	}

	/**
	 * @description 新增支付模式
	 * @author LiangDeng
	 * @date 2017年12月11日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/addPayment", method = RequestMethod.POST)
	@ResponseBody
	public LogisticsResult addPayment(@RequestParam String name) {
		if (StringUtils.isBlank(name)) {
			return LogisticsResult.build(401, "支付模式不能为空");
		}
		int row = paymentModelService.addPayment(name);
		if (row != 1) {
			return LogisticsResult.build(400, "新增失败");
		}
		return LogisticsResult.ok();
	}

	/**
	 * 查询所有的支付模式
	 * 
	 * @author dusd
	 * @date 2018年1月18日
	 * @return
	 */
	@RequestMapping(value = "/listAllPayment", method = RequestMethod.GET)
	@ResponseBody
	public ShResponse listAllPayment() {
		List<PaymentDetail> paymentDetailList = paymentModelService.listAllPayment();
		return new ShResponse(HttpStatus.OK.value(), "计算运费成功", paymentDetailList);
	}
}
