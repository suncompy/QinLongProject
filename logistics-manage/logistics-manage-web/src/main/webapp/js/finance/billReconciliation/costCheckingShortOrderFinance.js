//# sourceURL=costCheckingShortOrderFinance.js
/**
 * @title 费用对账财务表
 * @description 
 * @author LiuLiuJiefeng
 * @date 2018-01-16
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
	data = $.fd.DataTable.loadPageOpts(page,data);
    $.fd.DataTable.ajax(source, data, callback, queryCondition);
  };

  // 短驳运单财务表信息一览
  var tblShortOrderFinance = $("#tblShortOrderFinance").FdDataTable({
    serverData : retrieveData,
    ajaxSource : "api/getCostOrderFinancesByProjectId",
    columns : [
    {
      data : "costOrderFinId"//复选框
    }
    ,{
      data : "projectCode"
    }
    ,{
      data : "orderCode"	//运单编号
    },{
      data : "branchGroupName"	//网点分支
    } 
    ,{
      data : "financeStatus",
      render:function(data,type,full,meta) {//状态
    	  var returnVal = '';
    	  switch (data) {
			case 0:
				returnVal = '待确认';
				break;
			case 1:
				returnVal = '待计算';
				break;
			case 2:
				returnVal = '待审核';
				break;
			case 3:
				returnVal = '已审核';
				break;
			default:
				break;
			}
    	  return returnVal;
      }
    },
    {
      data : "cargoName" //承运车辆
    },
    {
      data : "cargoPrice" //货物单价
    }
    ,{
      data : "carPlateNumber" //承运车辆
    }
    ,{
      data : "sendTare" 	//发货皮重
    }
    ,{
      data : "sendGross"	//发货毛重
    },{
      data : "sendNet"	//发货净重
    }
    ,{
    	 data : "orderOrign",
         render:function(data,type,full,meta) {//运单来源
       	  var returnVal = '';
       	  switch (data) {
   			case 1:
   				returnVal = 'PC端';
   				break;
   			case 2:
   				returnVal = 'APP端';
   				break;
   			default:
   				break;
   			}
       	  return returnVal;
         }	
    }
    ,{
      data : "createDate" //创建时间
    }
    ,{
      data : "pickupPlace" //取货地
    }
    ,{
      data : "takeDeliveryDate" //装货时间
    }
    ,{
      data : "arrivePlace"  //运抵地
    }
    ,{
      data : "receipterDate" //到货时间
    }
    ,{
      data : "userDispatchName"  //调度员
    }
    ],
    columnDefs : [ {
      targets : [ 0 ],
      visible : false
    } ]
  });
  
  // 短驳运单财务表 - 初始化开始对账页面
	$("#btnShortOrderFinanceStartChecking").click(function(e) {
		e.preventDefault();
		var type = "get";
		var url = "projectManagment/getProjectByFinance.do";
		  $.fd.ajax({
			  url : url,
			  type : type,
			  data : {checkFlag:1},
			  success : function(data) {
				  var selectStr = '';
				  $.each(data.data,function(index,project){
					  selectStr += '<option value="' + project.id + '">' + project.projectCode + '</option>';
				  })
				  // 填充下拉列表
				  $("#chooseProjectFormId [name=projectId]").html(selectStr);
					// 重置内容
					$("#chooseProjectFormId").resetText();
					//自动填充 项目信息
					autoPaddingProjectInfo();
			  }
		  });
		 /* $('#chooseProjectFormId [name=projectId]').combobox({
	            url: 'projectManagment/getProjectByFinance.do',
	            valueField: 'id',
	            textField: 'projectCode',
	            callback :function(){
	            	// 重置内容
	    			$("#chooseProjectFormId").resetText();
	    			//自动填充 项目信息
	    			autoPaddingProjectInfo();
	            }
		  });*/
		  $("#chooseProjectDivId").modal("show");
	});
	
	// 短驳运单财务表 - 填充开始对账页面
	$("#projectId").change(function(e) {
		e.preventDefault();
		autoPaddingProjectInfo();
	});
	
	//自动填充 项目信息
	function autoPaddingProjectInfo() {
		var projectId=$("#chooseProjectFormId [name=projectId]").children('option:selected').val();
		var type = "post";
		var url = "projectManagment/getProject.do";
		/*$.ajax({
			url : url,
			type : type,
			data : {id:projectId},
			dataType : "json",
			cache : false,
			success : function(data) {
				var project = data.data;
				$("#chooseProjectFormId").resetText();
				$("#chooseProjectFormId").loadJson2Text(project);
			}
		});*/
		$.fd.ajax({
			url : "api/checkConf/projectId/pickup/"+projectId,
			type: "get",
			success : function(data) {
				var project = data.data;
				$("#chooseProjectFormId").resetText();
				$("#chooseProjectFormId").loadJson2Text(project);
			}
		})
	}
	
	// 选择项目信息后 点击 开始对账按钮
	$("#btnShortOrderFinanceQueryProjectId").click(function(e) {
		var projectId=$("#chooseProjectFormId [name=projectId]").children('option:selected').val();
		//queryCondition.projectId=projectId;//赋值项目主键
		 $("#frmShortOrderFinanceQuery [name=projectId]").val(projectId);
		//tblShortOrderFinance.reload();
		 $("#chooseProjectDivId").modal("hide");
		 $("#btnShortOrderFinanceQuery").click();
	});
	
	// 初始化打包信息
	$("#btnShortOrderFinancePack").click(function(e) {
		e.preventDefault();
		var shOrderFinIds = tblShortOrderFinance.getSelections('costOrderFinId');
		if (!shOrderFinIds || shOrderFinIds.length ==0) {
			  $.fd.msg.notice("请至少选择一条信息");
			  return false;
		}
		//1table data
		var shOrderFinss = tblShortOrderFinance.getSelecteds(shOrderFinIds);
		$("#tblInitPack").bootstrapTable('load', shOrderFinss);
		
		//2支付模式
		//loadPayModeSelect();
		//3领取人
		var ids = $.arrToSplit(shOrderFinIds);
		//loadReceiverSelect(ids);
		//4表单赋值
		setValue(shOrderFinss,ids);
		$("#initPackDivId").modal("show");
	});
	//表单赋值
	function setValue(shOrderFinss,ids){
		$("#initPackFormId [name=projectId]").val(shOrderFinss[0].projectId);
		$("#initPackFormId [name=projectCode]").val(shOrderFinss[0].projectCode);
		$("#initPackFormId [name=branchGroupName]").val(shOrderFinss[0].branchGroupName);
		$("#initPackFormId [name=cargoPrice]").val(shOrderFinss[0].cargoPrice);
		$("#initPackFormId [name=costOrderFinIds]").val(ids);
		
		var carPlateNumbers = tblShortOrderFinance.getSelections('carPlateNumber');
		var carNum = $.uniqueArr(carPlateNumbers).length;
		$("#initPackFormId [name=packTruckNum]").val(carNum);
		$("#initPackFormId [name=packTruckDegree]").val(carPlateNumbers.length);
	}

	//加载支付模式下拉框
	function loadPayModeSelect(){
		//加载补交项目
		$('#initPackFormId [name=paymentId]').combobox({
            url: 'payment/listAllPayment.do',
            valueField: 'id',
            textField: 'name'
	    });
	}	
	//加载领取人下拉框
/*  function loadReceiverSelect(ids){
	//加载补交项目
	  $('#initPackFormId [name=receiveUserId]').combobox({
          url: "api/shortPacks/listPackReceiveDriverByIds/"+ids,
          valueField: 'driverId',
          textField: 'driverName'
	  });
  }*/

  // 检索
  $("#btnShortOrderFinanceQuery").click(function(e) {
    e.preventDefault();
    // 刷新检索条件
    loadqueryCondition();
    // 刷新数据
    tblShortOrderFinance.reload();
  });

  function loadqueryCondition(){
    queryCondition.projectId = $("#frmShortOrderFinanceQuery [name=projectId]").val();
    queryCondition.projectCode = $("#frmShortOrderFinanceQuery [name=projectCode]").val();
    queryCondition.branchGroupName = $("#frmShortOrderFinanceQuery [name=branchGroupName]").val();
    queryCondition.beginDate = $("#frmShortOrderFinanceQuery [name=beginDate]").val();
    queryCondition.endDate = $("#frmShortOrderFinanceQuery [name=endDate]").val();
    queryCondition.packFlag = 0;
    return queryCondition;
  }

  
  $("#initPackDivId [name=btnInitPackSave]").click(function(e) {
	    e.preventDefault();
		var type = "post";
		var url = "api/costPack";
		var shortOrderFinance = $("#initPackFormId").serializeJson();
		delete shortOrderFinance.projectCode;
		delete shortOrderFinance.branchGroupName;
		if(!shortOrderFinance.taxRate){
			$.fd.msg.notice("税率不能为空!");
			  return false;
		}
		$.fd.ajax({
			url : url,
			type : type,
			data : shortOrderFinance,
			showMsg:true,
			success : function(d) {
				$("#initPackDivId").modal("hide");
				// 保存成功后，刷新数据
				tblShortOrderFinance.reload();
			}
		});
   });
   $("#btnDriverCheckDetailLink").click(function(e) {
	    e.preventDefault();
	    window.open("costCheckDetail.do.do", "contentIframe");  
   });
  });
}(jQuery));
