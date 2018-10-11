//# sourceURL=baseData.js
/**
 * app
 * 
 */
;
(function($, window, document, undefined) {
    'use strict';
    $(function() {
    	$.extend({  
    		getTypeOpts:function(){
    			return typeOpts;
    		},
    		getDims:function(){
    			return dims;
    		}  
    	});  
    	//数据字典下拉框
    	var dims = {
    			//短驳状态
    			bulkStatus:{
    				code:"2"
    				,minimumResultsForSearch: 0
    				,cache : false
    			}
		    	//短驳状态
				,bulkStatus2:{
					code:"2"
				}
    	};
		//其他业务下拉框
    	var typeOpts = {
			//火运状态
    		trainStatus:{
				ajax: {
					url : "api/basicdata/typesByTrain/testType/ids/hiddenBeginSiteId"
				}
				,minimumResultsForSearch: 0
				,cache : false
			}
			//短驳状态
			,trainStatus2:{
				ajax: {
					url : "api/basicdata/types/testType"
				}
			}
			//车型
    		,trainType:{
				ajax: {
					url : "api/basicdata/types/trainType"
				}
	    		,minimumResultsForSearch: 0
				,cache : false
			}
    		//项目
    		,projectCode:{
				ajax: {
					url : "api/basicdata/types/projectCode"
				}
	    		,minimumResultsForSearch: 0
				,cache : false
			}
    		//未使用油气卡号
    		,oilGasCardUnused:{
				ajax: {
					url : "api/basicdata/types/oilGasCardUnused/ids/id"
				}
	    		,minimumResultsForSearch: 0
				,cache : false
			}
    		//油气卡领取人 
    		,reciverFinance:{
				ajax: {
					url : "api/basicdata/types/reciverFinance/ids/shOrderFinIds"
				}
	    		,minimumResultsForSearch: 0
				,cache : false
			}
    		//客户业务联系人
    		,bizContactor:{
				ajax: {
					url : "api/basicdata/types/bizContactor/ids/customId"
				}
	    		,minimumResultsForSearch: 0
				,cache : false
			}
    		//收发货企业、客户单位  
    		,customCompany:{
				ajax: {
					url : "api/basicdata/types/customCompany"
				}
	    		,minimumResultsForSearch: 0
				,cache : false
			}
    		//查询栏分支机构
    		,sysOrgSearch:{
				ajax: {
					url : "api/basicdata/types/sysOrgSearch"
				}
	    		,minimumResultsForSearch: 0
				,cache : false
			}
    		//panel分支机构  
    		,sysOrgPanel:{
				ajax: {
					url : "api/basicdata/types/sysOrgPanel"
				}
	    		,minimumResultsForSearch: 0
				,cache : false
			}
    		//支出方账号
    		,provideCompanyId:{
				ajax: {
					url : "api/basicdata/types/provideCompanyId"
				}
	    		,minimumResultsForSearch: 0
				,cache : false
			}
    	};
		
    });
})(jQuery, window, document);

