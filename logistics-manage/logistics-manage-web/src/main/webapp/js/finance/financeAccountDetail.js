//# sourceURL=financeAccountDetail.js
/**
 * @title 账户流水明细表
 * @description 账户流水明细表
 * @author JiangYS
 * @date 2018-02-07
 */
;
(function($) {
	'use strict';
	$(function() {
		// 检索条件
		var queryCondition = {};
		var page = {};
		page.start = 0;
		page.length = 30;

		// 获取短驳运单财务表信息
		var retrieveData = function(source, data, callback) {
			data = $.fd.DataTable.loadPageOpts(page, data);
			$.fd.DataTable.ajax(source, data, callback, queryCondition);
		};

		// 短驳运单财务表信息一览
		var tblFinanceAccountDetail = $("#tblFinanceAccountDetail").FdDataTable({
			serverData : retrieveData,
			ajaxSource : "api/getFinanceAccountDetail",
			columns : [ {
				data : "id"// 复选框
			}, {
				data : "payDate"// 时间
			}, {
				data : "payName"// 支出方
			}, {
				data : "payAccountName"// 支出账户
			}, {
				data : "depositName"// 存入方
			}, {
				data : "depositAccountName"// 存入账户
			},{
				data : "money"// 金额
			},{
				data : "operateName"// 操作人
			}, {
				data : "statementNum"// 流水号
			} ],
			columnDefs : [ {
				targets : [ 0 ],
				visible : false
			} ]
		});
		
		// 检索
		  $("#btnFinanceAccountDetailQuery").click(function(e) {
		    e.preventDefault();
		    // 刷新检索条件
		    loadqueryCondition();
		    // 刷新数据
		    tblFinanceAccountDetail.reload();
		  });

		  function loadqueryCondition(){
		    queryCondition.projectId = $("#frmFinanceAccountDetailQuery [name=projectId]").val();
		    queryCondition.projectCode = $("#frmFinanceAccountDetailQuery [name=projectCode]").val();
		    queryCondition.beginSite = $("#frmFinanceAccountDetailQuery [name=beginSite]").val();
		    queryCondition.endSite = $("#frmFinanceAccountDetailQuery [name=endSite]").val();
		    queryCondition.branchGroupName = $("#frmFinanceAccountDetailQuery [name=branchGroupName]").val();
		    queryCondition.beginDate = $("#frmFinanceAccountDetailQuery [name=beginDate]").val();
		    queryCondition.endDate = $("#frmFinanceAccountDetailQuery [name=endDate]").val();
		    return queryCondition;
		  }
		
	});
}(jQuery));
