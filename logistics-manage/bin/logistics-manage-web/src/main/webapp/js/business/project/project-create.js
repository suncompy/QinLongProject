/**
 * 新增项目功能
 * */
function addProject() {
	
	//判断联运模式为 火运 接取+火运 火运+送达 联运 收发货站点不能相同
	var transportType =  $("#addUnion select[name=transportType] option:selected").val();
	//return;
	var flag = false;
	if(transportType==3 || transportType==4 || transportType==5 || transportType==6){
		//获取火运干线的收发货站点id
		var beginSiteId =  $("#addUnion select[name=beginSiteId] option:selected").val();
		var endSiteId =  $("#addUnion select[name=endSiteId] option:selected").val();
		if(beginSiteId==endSiteId){
			flag=true;
		}
		if(flag){
			$("#msgContent").html("火运干线始发站点与到达站点不能相同!");
			$("#deletnullModal").modal();
			return;
		}
	}else if(transportType == 7 ){
		var receiveCargoSiteId =  $("#addUnion select[name=receiveCargoSiteId] option:selected").val();
		var forwardingSiteId =  $("#addUnion select[name=forwardingSiteId] option:selected").val();
		if(receiveCargoSiteId==forwardingSiteId){
			flag=true;
		}
		if(flag){
			$("#msgContent").html("到达站点与取货站点不能相同!");
			$("#deletnullModal").modal();
			return;
		}
	}
	
	
	if(transportType==1 || transportType==4 || transportType==6 || transportType==7 ){
		//移除disabled属性
		$("#addUnion select[name=receiveCenterCargoSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=receiveCargoSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=receiveCargoSiteFreightYardId]").removeAttr("disabled").removeClass('selectBg');
	}
	
	if(transportType==2 || transportType==5 || transportType==6 || transportType==7){
		//移除disabled属性
		$("#addUnion select[name=forwardingCenterSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=forwardingSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=forwardingSiteFreightYardId]").removeAttr("disabled").removeClass('selectBg');
	} 
	
	
	//将隐藏域的表单填值
	$('#addUnion input[name=branchGroupName]').val($("#addUnion select[name=branchGroupId] option:selected").text());
	var formParam = "";
	//判断承运方式
	var shortBargeCarrierMode = $("#addUnion select[name=shortBargeCarrierMode] option:selected").val();
	if(shortBargeCarrierMode == 1) {
		var array1 = new Array();
		$("#forms6 form").each(function(j, item) {
			var data = JSON.stringify($(this).serializeJSON());
			array1[j] = data;
		})
		//将短驳承运方赋值到隐藏域
		$('#addUnion input[name="shortBargeCarrierName"]').val("[" + array1 + "]");
	} else {
		var transportPrice = $("#addUnion input[name=transportPrice]:eq(0)").val();
		var deductionRate = $("#addUnion input[name=deductionRate]:eq(0)").val();
		var deductionPrice = $("#addUnion input[name=deductionPrice]:eq(0)").val();
		var payment = $("#addUnion  select[name=payment]:eq(0) option:selected").val();
		var shortBargeJson = "{shortBargeName: '平台',transportPrice: '" + transportPrice + "',deductionRate: '" + deductionRate + "',deductionPrice:'" + deductionPrice + "',payment:'" + payment + "'}";
		shortBargeJson = eval("(" + shortBargeJson + ")");
		var data = JSON.stringify(shortBargeJson);
		$('#addUnion input[name="shortBargeCarrierName"]').val("[" + data + "]");
	}
	//判断承运方式
	var sendShortBargeCarrierMode = $("#addUnion select[name=sendShortBargeCarrierMode] option:selected").val();
	if(sendShortBargeCarrierMode == 1) {
		var array2 = new Array();
		$("#forms7 form").each(function(j, item) {
			var data = JSON.stringify($(this).serializeJSON());
			array2[j] = data;
		})
		$('#addUnion input[name="sendShortBargeCarrierName"]').val("[" + array2 + "]");
	} else {
		var transportPrice = $("#addUnion input[name=transportPrice]:eq(1)").val();
		var deductionRate = $("#addUnion input[name=deductionRate]:eq(1)").val();
		var deductionPrice = $("#addUnion input[name=deductionPrice]:eq(1)").val();
		var payment = $("#addUnion  select[name=payment]:eq(1) option:selected").val();
		var shortBargeJson = "{shortBargeName: '平台',transportPrice: '" + transportPrice + "',deductionRate: '" + deductionRate + "',deductionPrice:'" + deductionPrice + "',payment:'" + payment + "'}";
		shortBargeJson = eval("(" + shortBargeJson + ")");
		var data = JSON.stringify(shortBargeJson);
		$('#addUnion input[name="sendShortBargeCarrierName"]').val("[" + data + "]");
	}
	//序列化表单
	formParam = $('#addUnion').serialize(); //序列化表格内容为字符串 
	$.ajax({
		type: 'post',
		url: $.getUrl('/projectManagment/addProject.do'),
		data: formParam,
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$("#creatProjectModal").modal("hide");
				$("#successMsg").html("");
				$("#successMsg").html("新增项目成功");
				$("#successModal").modal();
			} else {
				$("#msgContent").html("");
				$("#msgContent").html(data.msg);
				$("#deletnullModal").modal();
			}
		}
	})
}

//	新建项目切换
$('#selectField').change(function(event) {
	var transportType = $('#selectField option:selected').val();
	$('.col').hide();
	$('.motor').children().remove();
	if(transportType == 0) { //汽运
		$('#addUnion .train_line').hide(); //火运干线隐藏
		$('#addUnion .train_line').next('hr').hide();
		$('#addUnion .send_truck').hide(); //送达短驳隐藏
		$('#addUnion .send_truck').next('hr').hide();
		$('#addUnion input[name=pickUpPrice]').parent('div').hide(); //单价隐藏
		$('#addUnion input[name=trainPrice]').parent('div').hide(); //单价隐藏
		$('#addUnion input[name=arrivePrice]').parent('div').hide(); //单价隐藏
		//汽运单价显示
		$('#addUnion input[name=truckPrice]').parent('div').show(); //汽运单价显示
		$('#addUnion .receve_truck').find('h5').html("汽运短驳<i class='requireds'>*</i>");
		$('#truckDiv').find('select').eq(0).attr('name', 'receivingDepartmentId'); //收货单位
		$('#truckDiv').find('input').eq(0).attr('name', 'receivingDepartmentName'); //收货单位
		$("#receiveDiv").hide();
		$("#truckDiv").show();
		$('#addUnion .receve_truck').show(); //接取显示
		$('#addUnion .receve_truck').next('hr').show();
	} else if(transportType == 1) { //接取
		$('#addUnion .train_line').hide(); //火运干线隐藏
		$('#addUnion .train_line').next('hr').hide();
		$('#addUnion .send_truck').hide(); //送达短驳隐藏
		$('#addUnion .send_truck').next('hr').hide();
		$('#addUnion input[name=truckPrice]').parent('div').hide(); //汽运单价隐藏
		$('#addUnion input[name=trainPrice]').parent('div').hide(); //火运单价隐藏
		$('#addUnion input[name=arrivePrice]').parent('div').hide(); //送达单价隐藏
		$('#addUnion input[name=pickUpPrice]').parent('div').show(); //接取单价显示
		$('#addUnion .receve_truck').find('h5').html("接取短驳<i class='requireds'>*</i>");
		$("#addUnion select[name=receiveCenterCargoSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=receiveCargoSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=receiveCargoSiteFreightYardId]").removeAttr("disabled").removeClass('selectBg');
		$("#truckDiv").hide();
		$("#receiveDiv").show();
		$('#addUnion .receve_truck').show(); //接取显示
		$('#addUnion .receve_truck').next('hr').show();
	} else if(transportType == 2) { //送达
		$('#addUnion .train_line').hide(); //火运干线隐藏
		$('#addUnion .train_line').next('hr').hide();
		$('#addUnion .receve_truck').hide(); //接取短驳隐藏
		$('#addUnion .receve_truck').next('hr').hide();
		$('#addUnion input[name=truckPrice]').parent('div').hide(); //汽运单价隐藏
		$('#addUnion input[name=trainPrice]').parent('div').hide(); //火运单价隐藏
		$('#addUnion input[name=pickUpPrice]').parent('div').hide(); //接取单价隐藏
		$('#addUnion input[name=arrivePrice]').parent('div').show(); //送达单价显示
		$("#addUnion select[name=forwardingSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=forwardingCenterSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=forwardingSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=forwardingSiteFreightYardId]").removeAttr("disabled").removeClass('selectBg');
		$('#truckDiv').find('select').eq(0).removeAttr('name'); //收货单位
		$('#truckDiv').find('input').eq(0).removeAttr('name'); //收货单位
		$('#addUnion .send_truck').show(); //送达短驳显示
		$('#addUnion .send_truck').next('hr').show();
	} else if(transportType == 3) { //火运
		$('#addUnion .send_truck').hide(); //接取短驳隐藏
		$('#addUnion .send_truck').next('hr').hide();
		$('#addUnion .receve_truck').hide(); //接取短驳隐藏
		$('#addUnion .receve_truck').next('hr').hide();
		$('#addUnion input[name=truckPrice]').parent('div').hide(); //汽运单价隐藏
		$('#addUnion input[name=arrivePrice]').parent('div').hide(); //送达单价隐藏
		$('#addUnion input[name=pickUpPrice]').parent('div').hide(); //接取单价隐藏
		$('#addUnion input[name=trainPrice]').parent('div').show(); //火运单价显示
		$('#addUnion .train_line').show(); //火运干线显示
		$('#addUnion .train_line').next('hr').show();
	} else if(transportType == 4) { //接取+火运
		$('#addUnion .send_truck').hide(); //送达短驳隐藏
		$('#addUnion .send_truck').next('hr').hide();
		$('#addUnion input[name=truckPrice]').parent('div').hide(); //汽运单价隐藏
		$('#addUnion input[name=arrivePrice]').parent('div').hide(); //送达单价隐藏
		$('#addUnion input[name=pickUpPrice]').parent('div').show(); //接取单价显示
		$('#addUnion input[name=trainPrice]').parent('div').show(); //火运单价隐藏
		$('#addUnion .receve_truck').find('h5').html("接取短驳<i class='requireds'>*</i>");
		$("#addUnion select[name=receiveCenterCargoSiteId]").prop("disabled","disabled").addClass('selectBg');
		$("#addUnion select[name=receiveCargoSiteId]").prop("disabled","disabled").addClass('selectBg');
		$("#addUnion select[name=receiveCargoSiteFreightYardId]").prop("disabled","disabled").addClass('selectBg');
		$("#truckDiv").hide();
		$("#receiveDiv").show();
		$('#addUnion .receve_truck').show(); //接取显示
		$('#addUnion .receve_truck').next('hr').show();
		$('#addUnion .train_line').show(); //火运干线显示
		$('#addUnion .train_line').next('hr').show();
	} else if(transportType == 5) { //火运+送达
		$('#addUnion .receve_truck').hide(); //接取短驳隐藏
		$('#addUnion .receve_truck').next('hr').hide();
		$('#addUnion input[name=truckPrice]').parent('div').hide(); //汽运单价隐藏
		$('#addUnion input[name=pickUpPrice]').parent('div').hide(); //接取单价隐藏
		$('#addUnion input[name=trainPrice]').parent('div').show(); //火运单价显示
		$('#addUnion input[name=arrivePrice]').parent('div').show(); //送达单价显示
		$("#addUnion select[name=forwardingCenterSiteId]").prop("disabled","disabled").addClass('selectBg');
		$("#addUnion select[name=forwardingSiteId]").prop("disabled","disabled").addClass('selectBg');
		$("#addUnion select[name=forwardingSiteFreightYardId]").prop("disabled","disabled").addClass('selectBg');
		$('#truckDiv').find('select').eq(0).removeAttr('name'); //收货单位
		$('#truckDiv').find('input').eq(0).removeAttr('name'); //收货单位
		$('#addUnion .train_line').show(); //火运干线显示
		$('#addUnion .train_line').next('hr').show();
		$('#addUnion .send_truck').show(); //送达短驳显示
		$('#addUnion .send_truck').next('hr').show();
	} else if(transportType == 6) { //联运
		$('#addUnion .receve_truck').show(); //接取短驳显示
		$('#addUnion .receve_truck').next('hr').show();
		$('#addUnion .train_line').show(); //火运干线显示
		$('#addUnion .train_line').next('hr').show(); //火运干线显示
		$('#addUnion .send_truck').show(); //送达短驳显示
		$('#addUnion .send_truck').next('hr').show();
		$('#addUnion input[name=pickUpPrice]').parent('div').show(); //接取单价显示
		$('#addUnion input[name=trainPrice]').parent('div').show(); //火运单价显示
		$('#addUnion input[name=arrivePrice]').parent('div').show(); //送达单价显示
		$('#addUnion input[name=truckPrice]').parent('div').hide(); //汽运单价隐藏
		$('#addUnion .receve_truck').find('h5').html("接取短驳<i class='requireds'>*</i>");
		$("#truckDiv").hide();
		$("#receiveDiv").show();
		$('#truckDiv').find('select').eq(0).removeAttr('name'); //收货单位
		$('#truckDiv').find('input').eq(0).removeAttr('name'); //收货单位
		$("#addUnion select[name=receiveCenterCargoSiteId]").prop("disabled","disabled").addClass('selectBg');
		$("#addUnion select[name=receiveCargoSiteId]").prop("disabled","disabled").addClass('selectBg');
		$("#addUnion select[name=receiveCargoSiteFreightYardId]").prop("disabled","disabled").addClass('selectBg');
		$("#addUnion select[name=forwardingCenterSiteId]").prop("disabled","disabled").addClass('selectBg');
		$("#addUnion select[name=forwardingSiteId]").prop("disabled","disabled").addClass('selectBg');
		$("#addUnion select[name=forwardingSiteFreightYardId]").prop("disabled","disabled").addClass('selectBg');
	} else if(transportType == 7) { //接取+送达
		$('#addUnion .train_line').hide(); //火运干线隐藏
		$('#addUnion .train_line').next('hr').hide();
		$('#addUnion input[name=trainPrice]').parent('div').hide(); //火运单价隐藏
		$('#addUnion input[name=truckPrice]').parent('div').hide(); //汽运单价隐藏
		$('#addUnion input[name=pickUpPrice]').parent('div').show(); //接取单价显示
		$('#addUnion input[name=arrivePrice]').parent('div').show(); //送达单价显示
		$('#addUnion .receve_truck').find('h5').html("接取短驳<i class='requireds'>*</i>");
		$("#addUnion select[name=receiveCenterCargoSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=receiveCargoSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=receiveCargoSiteFreightYardId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=forwardingCenterSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=forwardingSiteId]").removeAttr("disabled").removeClass('selectBg');
		$("#addUnion select[name=forwardingSiteFreightYardId]").removeAttr("disabled").removeClass('selectBg');
		$('#truckDiv').find('select').eq(0).removeAttr('name'); //收货单位
		$('#truckDiv').find('input').eq(0).removeAttr('name'); //收货单位
		$("#truckDiv").hide();
		$("#receiveDiv").show();
		$('#addUnion .receve_truck').show(); //接取显示
		$('#addUnion .receve_truck').next('hr').show();
		$('#addUnion .send_truck').show(); //送达短驳显示
		$('#addUnion .send_truck').next('hr').show();
	}
	$('.union').show();
	creatProjectModal(transportType); //触发这个方法
});

/*
 *新建项目模态框
 * */
$('#creatProject').click(function() {
	listBranchGroup();
	listCargos();
	listSendAndReceiveCompany();
	$('#selectField').change();
	$("#creatProjectModal").modal();
});

function creatProjectModal(transportType) {
	if(transportType == 0) {
		listSendUnit();//发货单位
		listReceiveUnit();//收货单位
	} else if(transportType == 1) {
		listSendUnit();//发货单位
		listReceiveStations();//到达中心站
	} else if(transportType == 2) {
		listSendStations();
		listReceiveUnit();
	} else if(transportType == 3) {
		listCenterStations();
	} else if(transportType == 4) {
		listSendUnit();
		listReceiveUnit();
		listReceiveStations();
		listCenterStations();
	} else if(transportType == 5) {
		listReceiveUnit();
		listSendStations();
		listCenterStations();
	} else if(transportType == 6) {
		listSendUnit();
		listReceiveUnit();
		listSendStations();
		listReceiveStations();
		listCenterStations();
	} else if(transportType == 7) {
		listSendUnit();
		listReceiveUnit();
		listSendStations();
		listReceiveStations();
		listCenterStations();
	}
	for(var i = 1; i <= 9; i++) {
		$("div[name='platformShortBarge" + i + "']").html("<div class='form-inline'>" +
			"<div class='form-group'><label>&emsp;短驳承运方：</label><input type='text' readonly='readonly' name='shortBargeName' value='平台' /></div>" +
			"<div class='form-group short-client'><label>运输单价：</label><input name='transportPrice' type='text'><span>元</span></div>" +
			"<div class='form-group short-client'><label>扣损比率：</label><input name='deductionRate' type='text'><span>‰</span></div>" +
			"<div class='form-group short-client'><label>扣损单价：</label><input name='deductionPrice' type='text'><span>元</span></div>" +
			"<div class='form-group short-client'><label>付款周期：</label> <select name='payment'>" +
			"<option value='0'>日结</option>" +
			"<option value='1'>周结</option>" +
			"<option value='2'>月结</option>" +
			"</select></div>" +
			"</div >")
	}
	//$("#addUnion").validate().resetForm();
}
/**
 * 货物信息二级联动
 * */

function getCargo(param) {
	var cargoId = "";
	if(param == 'addUnion') {
		$("#addUnion select[name='cargoId']").next('input').val($("#addUnion select[name='cargoId'] option:selected").text());
		cargoId = $("#addUnion select[name='cargoId']").val();
	}
	$.ajax({
		type: 'post',
		url: $.getUrl('/cargo/getOneCargoSpecificte.do'),
		data: {
			cargoId: cargoId
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var specifictes = data.data;
				$("select[name='cargoSpecifications']").html("");
				$.each(specifictes, function(index, specificte) {
					$("select[name='cargoSpecifications']").append(
						"<option value='" + specificte.name + "'>" + specificte.name + "</option>"
					);
				});
			}
		}
	})
}

/**企业二级联动*/

$("select[name=sendCargoCompanyId]").change(function() {
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		$("select[name=sendCargoCompanyId]").parent('div').next('div').children('input').val("");
		$("select[name=sendCargoCompanyId]").parent('div').next('div').next('div').children('input').val("");
		return;
	}
	$(this).next('input').val(name);
	$.ajax({
		type: 'post',
		url: $.getUrl('/customerManagement/getCustomerById.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$("select[name=sendCargoCompanyId]").parent('div').next('div').children('input').val(data.data.stationPhone);
				$("select[name=sendCargoCompanyId]").parent('div').next('div').next('div').children('input').val(data.data.addressCode + data.data.detailAddress);
			}
		}
	})
})

$("select[name=receiveCargoCompanyId]").change(function() {
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		$("select[name=receiveCargoCompanyId]").parent('div').next('div').children('input').val("");
		$("select[name=receiveCargoCompanyId]").parent('div').next('div').next('div').children('input').val("");
		return;
	}
	$(this).next('input').val(name);
	$.ajax({
		type: 'post',
		url: $.getUrl('/customerManagement/getCustomerById.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$("select[name=receiveCargoCompanyId]").parent('div').next('div').children('input').val(data.data.stationPhone);
				$("select[name=receiveCargoCompanyId]").parent('div').next('div').next('div').children('input').val(data.data.addressCode + data.data.detailAddress);
			}
		}
	})
})

/*收发货单位*/
$("select[name=sendCargoUnitId]").change(function() {
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		return;
	}
	$(this).next('input').val(name);
	$.ajax({
		type: 'post',
		url: $.getUrl('/customerManagement/getCustomerById.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				if(data.data.district==undefined || data.data.district==null ||data.data.district==""){
					$("select[name=sendCargoUnitId]").parent('div').next('div').children('input').val(data.data.city);
				}else{
					$("select[name=sendCargoUnitId]").parent('div').next('div').children('input').val(data.data.district);
				}
				
				$("select[name=sendCargoUnitId]").parent('div').next('div').next('div').children('input').val(data.data.addressCode + data.data.detailAddress);
			}
		}
	})
})

$("select[name=receivingDepartmentId]").change(function() {
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		return;
	}
	$(this).next('input').val(name);
	$.ajax({
		type: 'post',
		url: $.getUrl('/customerManagement/getCustomerById.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$("select[name=receivingDepartmentId]").parent('div').next('div').children('input').val(data.data.district);
				$("select[name=receivingDepartmentId]").parent('div').next('div').next('div').children('input').val(data.data.addressCode + data.data.detailAddress);
			}
		}
	})
})

/**三级站点二级联动*/
/*$("select[name=receiveCargoSiteId]").change(function() {
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		$("select[name=receivingDepartmentId]").parent('div').next('div').children('input').val("");
		$("select[name=receivingDepartmentId]").parent('div').next('div').next('div').children('input').val("");
		return;
	}
	$(this).next('input').val(name);
	var selectName = $(this).attr('name');
	if($(this).attr('name') == 'receivingDepartmentId') {
		$.ajax({
			type: 'post',
			url: $.getUrl('/customerManagement/getCustomerById.do'),
			data: {
				id: value
			},
			cache: false,
			dataType: 'json',
			success: function(data) {
				if(data.status == 200) {
					$("select[name=receivingDepartmentId]").parent('div').next('div').children('input').val(data.data.district);
					$("select[name=receivingDepartmentId]").parent('div').next('div').next('div').children('input').val(data.data.addressCode + data.data.detailAddress);
				}
			}
		})
	} else {
		$.ajax({
			type: 'get',
			url: $.getUrl('/trainStation/get.do'),
			data: {
				id: value
			},
			cache: false,
			dataType: 'json',
			success: function(data) {
				if(data.status == 200) {
					$("select[name=" + selectName + "]").parent('div').next('div').children('input').val(data.data.district);
					$("select[name=" + selectName + "]").parent('div').next('div').next('div').children('input').val(data.data.detailAddress);
				}
			}
		})
	}

})*/

/*$("select[name=forwardingSiteId]").change(function() {
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		$("select[name=forwardingSiteId]").parent('div').next('div').children('input').val("");
		$("select[name=forwardingSiteId]").parent('div').next('div').next('div').children('input').val("");
		return;
	}
	$(this).next('input').val(name);
	$.ajax({
		type: 'get',
		url: $.getUrl('/trainStation/get.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$("select[name=forwardingSiteId]").parent('div').next('div').children('input').val(data.data.district);
				$("select[name=forwardingSiteId]").parent('div').next('div').next('div').children('input').val(data.data.detailAddress);
			}
		}
	})
})*/

$("select[name=beginSiteId]").change(function() {
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		$("select[name=beginSiteId]").parent('div').next('div').children('input').val("");
		return;
	}
	//收货站点赋值
	$("#creatProjectModal select[name='receiveCargoSiteId'] option").removeAttr("selected");
	$("#creatProjectModal select[name='receiveCargoSiteId'] option[value='"+value+"']").attr("selected","selected");
	$("#creatProjectModal select[name='receiveCargoSiteId']").change();
	
	$(this).next('input').val(name);
	var flag = this;
	$(flag).parent('div').next('div').children('select[name=beginSiteFreightYard]').html("<option></option>");
	$.ajax({
		type: 'post',
		url: $.getUrl('/siteManager/getFreightYardByStationId.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$.each(data.data, function(index, freightYard) {
					$(flag).parent('div').next('div').children('select[name=beginSiteFreightYard]').append(
						"<option value='" + freightYard.id + "'>" + freightYard.name + "</option>"
					);
				});
				$("#creatProjectModal select[name=beginSiteFreightYard]").change();
			}
			
		}
	})
})

$("select[name=endSiteId]").change(function() {
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		$("select[name=endSiteId]").parent('div').next('div').children('input').val("");
		return;
	}
	//接取站点赋值
	$("#creatProjectModal select[name='forwardingSiteId'] option").removeAttr("selected");
	$("#creatProjectModal select[name='forwardingSiteId'] option[value='"+value+"']").attr("selected","selected");
	$("#creatProjectModal select[name='forwardingSiteId']").change();
	
	$(this).next('input').val(name);
	var flag = this;
	$(flag).parent('div').next('div').children('select[name=endSiteFreightYard]').html("<option></option>");
	$.ajax({
		type: 'post',
		url: $.getUrl('/siteManager/getFreightYardByStationId.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$.each(data.data, function(index, freightYard) {
				$(flag).parent('div').next('div').children('select[name=endSiteFreightYard]').append(
						"<option value='" + freightYard.id + "'>" + freightYard.name + "</option>"
					);
				});
				$("#creatProjectModal select[name=endSiteFreightYard]").change();
			}
		}
	})
})

/**二级联动赋值*/
function changeAndSetValue(param) {
	//给隐藏域赋值
	$(param).next('input').val($(param).find("option:selected").text());
}

/**
 * 中心站联动三级站点
 * */
$("select[name=beginCenterSiteId]").change(function() {
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		return;
	}
	$(this).next('input').val(name);
	//收货中心站点赋值
	$("#creatProjectModal select[name='receiveCenterCargoSiteId'] option").removeAttr("selected");
	$("#creatProjectModal select[name='receiveCenterCargoSiteId'] option[value='"+value+"']").attr("selected","selected");
	$("#creatProjectModal select[name='receiveCenterCargoSiteId']").change();
	
	//赋值三级站点
	//$(this).parent('div').next('div').children('select').append("<option value='123'>456</option>");
	var flag = this;

	$.ajax({
		type: 'post',
		url: $.getUrl('/trainStation/getchildrenStationById.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var stations = data.data;
				$(flag).parent('div').next('div').children('select').html("");
				$.each(stations, function(index, station) {
					$(flag).parent('div').next('div').children('select[name=beginSiteId]').append(
						"<option value='" + station.id + "'>" + station.stationName + "</option>"
					);
				});
				$(flag).parent('div').next('div').children('select[name=beginSiteId]').change();
				
			}
		}
	})
})

$("select[name=endCenterSiteId]").change(function() {
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		return;
	}
	$("#creatProjectModal input[name='endAddress']").val("");
	
	//赋值接取中心站点
	$("#creatProjectModal select[name='forwardingCenterSiteId'] option").removeAttr("selected");
	$("#creatProjectModal select[name='forwardingCenterSiteId'] option[value='"+value+"']").attr("selected","selected");
	$("#creatProjectModal select[name='forwardingCenterSiteId']").change();
	$(this).next('input').val(name);
	var flag = this;

	$.ajax({
		type: 'post',
		url: $.getUrl('/trainStation/getchildrenStationById.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var stations = data.data;
				$(flag).parent('div').next('div').children('select[name=endSiteId]').html("");
				$.each(stations, function(index, station) {
					$(flag).parent('div').next('div').children('select[name=endSiteId]').append("<option value='" + station.id + "'>" + station.stationName + "</option>");
				});
				$(flag).parent('div').next('div').children('select[name=endSiteId]').change();
			}
		}
	})
})
/**
 * 始发货场联动
 * */
$("select[name=beginSiteFreightYard]").change(function() {
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		return;
	}
	$(this).next('input').val(name);
	//赋值收货站点卸货货场
	$("#creatProjectModal select[name='receiveCargoSiteFreightYardId'] option").removeAttr("selected");
	$("#creatProjectModal select[name='receiveCargoSiteFreightYardId'] option[value='"+value+"']").attr("selected","selected");
	$("#creatProjectModal select[name='receiveCargoSiteFreightYardId']").change();

	//$(this).parent('div').next('div').children('select').append("<option value='123'>456</option>");
	var flag = this;

	$.ajax({
		type: 'get',
		url: $.getUrl('/siteManager/freight/yard/get.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var freights = data.data;
				$("select[name=beginSiteFreightYard]").parent('div').next('div').children('input').val(freights.district+freights.address);
				//$("#creatProjectModal select[name='receiveCargoSiteId']").parent('div').next('div').children('input').val();
				$("#creatProjectModal select[name='receiveCargoSiteId']").parent('div').next('div').next('div').children('input').val(freights.district+freights.address);
			}
		}
	})
})

/**
 * 到达货场联动
 * */
$("select[name=endSiteFreightYard]").change(function() {
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		return;
	}
	$(this).next('input').val(name);
	//接取站点赋值
	$("#creatProjectModal select[name='forwardingSiteFreightYardId'] option").removeAttr("selected");
	

	//$(this).parent('div').next('div').children('select').append("<option value='123'>456</option>");
	var flag = this;

	$.ajax({
		type: 'get',
		url: $.getUrl('/siteManager/freight/yard/get.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var freights = data.data;
				$("select[name=endSiteFreightYard]").parent('div').next('div').children('input').val(freights.district+freights.address);
				//$("#creatProjectModal select[name='forwardingSiteId']").parent('div').next('div').children('input').val(freights.district);
				$("#creatProjectModal select[name='forwardingSiteId']").parent('div').next('div').next('div').children('input').val(freights.district+freights.address);
				$("#creatProjectModal select[name='forwardingSiteFreightYardId'] option[value='"+value+"']").attr("selected","selected");
				$("#creatProjectModal select[name='forwardingSiteFreightYardId']").change();
			}
		}
	})
})


/**
 * 获取所有网点分支
 * */
function listBranchGroup() {
	$.ajax({
		type: 'post',
		url: $.getUrl('/humanOrganization/listDotBranchByUserId.do'),
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				//获取所有分支机构
				var branchGroups = data.data;
				$("#creatProjectModal select[name='branchGroupId']").html("<option></option>");
				$.each(branchGroups, function(index, branchGroup) {
					$("#creatProjectModal select[name='branchGroupId']").append("<option value='" + branchGroup.id + "'>" + branchGroup.name + "</option>");
				});
				$("#creatProjectModal select[name='branchGroupId1']").change();
			}
		}
	})
}

/**
 * 获取货物品名
 * */
function listCargos() {
	$.ajax({
		type: 'post',
		url: $.getUrl('/cargo/listCargo.do'),
		cache: true,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var cargos = data.data;
				$("#creatProjectModal select[name='cargoId']").html("<option></option>");
				$.each(data.data, function(index, cargo) {
					$("#creatProjectModal select[name='cargoId']").append(
						"<option value='" + cargo.id + "'>" + cargo.cargoName + "</option>"
					);
				});
				$("select[name='cargoId']").change();
			}
		}
	})
}

/**
 * 获取发货收货企业
 * */
function listSendAndReceiveCompany() {
	$.ajax({
		type: 'post',
		url: $.getUrl('/customerManagement/listCustomers.do'),
		cache: true,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var customers = data.data;
				//发货与收货企业
				$("select[name='sendCargoCompanyId']").html("<option></option>");
				$("select[name='receiveCargoCompanyId']").html("<option></option>");

				$.each(customers, function(index, customer) {
					$("select[name='sendCargoCompanyId']").append(
						"<option id='" + customer.id + "' value='" + customer.id + "'>" + customer.companyName + "</option>"
					);
					$("select[name='receiveCargoCompanyId']").append(
						"<option value='" + customer.id + "'>" + customer.companyName + "</option>"
					);
				});
				$("select[name='sendCargoCompanyId']").change();
				$("select[name='receiveCargoCompanyId']").change();
			}
		}
	})
}
/**
 * 发货单位
 * */
function listSendUnit() {
	$.ajax({
		type: 'post',
		url: $.getUrl('/customerManagement/listCustomers.do'),
		cache: true,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var customers = data.data;
				//发货单位
				$("select[name='sendCargoUnitId']").html("<option></option>");
				$.each(customers, function(index, customer) {
					$("select[name='sendCargoUnitId']").append(
						"<option value='" + customer.id + "'>" + customer.companyName + "</option>"
					);
				});
				$("select[name='sendCargoUnitId']").change();
			}
		}
	})

}

/**
 *收货单位 
 * */
function listReceiveUnit() {
	$.ajax({
		type: 'post',
		url: $.getUrl('/customerManagement/listCustomers.do'),
		cache: true,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var customers = data.data;
				//收货单位
				$("select[name='receivingDepartmentId']").html("<option></option>");
				$.each(customers, function(index, customer) {
					$("select[name='receivingDepartmentId']").append(
						"<option value='" + customer.id + "'>" + customer.companyName + "</option>"
					);
				});
				$("select[name='receivingDepartmentId']").change();
			}
		}
	})
}

function listSendStations() { //送达
	$.ajax({
		type: 'post',
		url: $.getUrl('/trainStation/listTrainStationByLevel.do'),
		data: {
			level: 1
		}, //最明细的站点
		cache: true,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var threeLevelStations = data.data;
				$("select[name='forwardingCenterSiteId']").html("<option></option>");
				$.each(threeLevelStations, function(index, trainStation) {
					$("select[name='forwardingCenterSiteId']").append(
						"<option value='" + trainStation.id + "'>" + trainStation.stationName + "</option>"
					);
				})
				$("select[name='forwardingCenterSiteId']").change();
			}
		}
	})

}

$("select[name='forwardingCenterSiteId']").change(function(){

	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		return;
	}
	//$(this).next('input').val(name);
	//赋值三级站点
	//$("#creatProjectModal input[name='beginAddress']").val("");
	//$(this).parent('div').next('div').children('select').append("<option value='123'>456</option>");
	var flag = this;
	$.ajax({
		type: 'post',
		url: $.getUrl('/trainStation/getchildrenStationById.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var stations = data.data;
				$(flag).parent('div').next('div').children('select').html("");
				$.each(stations, function(index, station) {
					$(flag).parent('div').next('div').children('select[name=forwardingSiteId]').append(
						"<option value='" + station.id + "'>" + station.stationName + "</option>"
					);
				});
				$('select[name=forwardingSiteId]').change();
			}
		}
	})
})

$("select[name='forwardingSiteId']").change(function(){
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		//$("select[name=beginSiteId]").parent('div').next('div').children('input').val("");
		return;
	}
	//收货站点赋值
	//$("#creatProjectModal select[name='receiveCargoSiteId'] option").removeAttr("selected");
	//$("#creatProjectModal select[name='receiveCargoSiteId'] option[value='"+value+"']").attr("selected","selected");
	//$("#creatProjectModal select[name='receiveCargoSiteId']").change();
	
	$(this).next('input').val(name);
	var flag = this;
	$('select[name=forwardingSiteFreightYardId]').html("<option></option>");
	$.ajax({
		type: 'post',
		url: $.getUrl('/siteManager/getFreightYardByStationId.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$.each(data.data, function(index, freightYard) {
					$('#creatProjectModal select[name=forwardingSiteFreightYardId]').append(
						"<option value='" + freightYard.id + "'>" + freightYard.name + "</option>"
					);
				});
				$("#creatProjectModal select[name=forwardingSiteFreightYardId]").change();
			}
		}
	})
})



$("select[name='forwardingSiteFreightYardId']").change(function(){
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		return;
	}
	$(this).next('input').val(name);
	//赋值三级站点

	//$(this).parent('div').next('div').children('select').append("<option value='123'>456</option>");
	var flag = this;

	$.ajax({
		type: 'get',
		url: $.getUrl('/siteManager/freight/yard/get.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var freights = data.data;
				$("select[name=forwardingSiteFreightYardId]").parent('div').next('div').children('input').val(freights.district+freights.address);
			}
		}
	})
})





/**
 * 获取所有到达中心站站点信息
 * */
function listReceiveStations() { //接取
	$.ajax({
		type: 'post',
		url: $.getUrl('/trainStation/listTrainStationByLevel.do'),
		data: {
			level: 1
		}, 
		cache: true,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var threeLevelStations = data.data;
				$("select[name='receiveCenterCargoSiteId']").html("<option></option>");
				$.each(threeLevelStations, function(index, trainStation) {
					$("select[name='receiveCenterCargoSiteId']").append(
						"<option value='" + trainStation.id + "'>" + trainStation.stationName + "</option>"
					);
				})
				//$("select[name='receiveCargoSiteId']").change();
			}
		}
	})
}

$("select[name='receiveCenterCargoSiteId']").change(function(){

	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		return;
	}
	//$(this).next('input').val(name);
	//赋值三级站点
	//$("#creatProjectModal input[name='beginAddress']").val("");
	//$(this).parent('div').next('div').children('select').append("<option value='123'>456</option>");
	var flag = this;
	$.ajax({
		type: 'post',
		url: $.getUrl('/trainStation/getchildrenStationById.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var stations = data.data;
				$(flag).parent('div').next('div').children('select').html("");
				$.each(stations, function(index, station) {
					$(flag).parent('div').next('div').children('select[name=receiveCargoSiteId]').append(
						"<option value='" + station.id + "'>" + station.stationName + "</option>"
					);
				});
				$('select[name=receiveCargoSiteId]').change();
			}
		}
	})
})

$("select[name='receiveCargoSiteId']").change(function(){
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		//$("select[name=beginSiteId]").parent('div').next('div').children('input').val("");
		return;
	}
	//收货站点赋值
	//$("#creatProjectModal select[name='receiveCargoSiteId'] option").removeAttr("selected");
	//$("#creatProjectModal select[name='receiveCargoSiteId'] option[value='"+value+"']").attr("selected","selected");
	//$("#creatProjectModal select[name='receiveCargoSiteId']").change();
	
	$(this).next('input').val(name);
	var flag = this;
	$('select[name=receiveCargoSiteFreightYardId]').html("<option></option>");
	$.ajax({
		type: 'post',
		url: $.getUrl('/siteManager/getFreightYardByStationId.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				$.each(data.data, function(index, freightYard) {
					$(flag).parent('div').next('div').children('select[name=receiveCargoSiteFreightYardId]').append(
						"<option value='" + freightYard.id + "'>" + freightYard.name + "</option>"
					);
				});
				$("#creatProjectModal select[name=receiveCargoSiteFreightYardId]").change();
			}
		}
	})
})



$("select[name='receiveCargoSiteFreightYardId']").change(function(){
	var name = $(this).find("option:checked").text();
	var value = $(this).find("option:checked").val();
	if(value==undefined || value==null || value==""){
		return;
	}
	$(this).next('input').val(name);
	//赋值三级站点

	//$(this).parent('div').next('div').children('select').append("<option value='123'>456</option>");
	var flag = this;

	$.ajax({
		type: 'get',
		url: $.getUrl('/siteManager/freight/yard/get.do'),
		data: {
			id: value
		},
		cache: false,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var freights = data.data;
				$("select[name=receiveCargoSiteFreightYardId]").parent('div').next('div').children('input').val(freights.district+freights.address);
			}
		}
	})
})


/**
 * 获取中心站点赋值
 * */
function listCenterStations() {
	$.ajax({
		type: 'post',
		url: $.getUrl('/trainStation/listTrainStationByLevel.do'),
		data: {
			level: 1
		}, //中心站点
		cache: true,
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var twoLevelStations = data.data;
				$("select[name='beginCenterSiteId']").html("<option></option>");
				$("select[name='endCenterSiteId']").html("<option></option>");
				$.each(twoLevelStations, function(index, trainStation) {
					$("select[name='beginCenterSiteId']").append(
						"<option value='" + trainStation.id + "'>" + trainStation.stationName + "</option>"
					);
					$("select[name='endCenterSiteId']").append(
						"<option value='" + trainStation.id + "'>" + trainStation.stationName + "</option>"
					);
				})
				$("select[name='beginCenterSiteId']").change();
				$("select[name='endCenterSiteId']").change();
			}
		}
	})
}

/**
 * 添加短驳承运方
 * */
$('.addBtns').on("click", function() {
	var className = this.className.split(" ")[1];
	var type = className.substr(className.length - 1, 1);
	var carTeams = "";
	var firstTeamName = "";
	/*异步提交*/
	$.ajax({
		type: 'post',
		url: $.getUrl('/transport/listCarTeams.do'),
		dataType: 'json',
		async: false,
		success: function(data) {
			if(data.status == 200) {
				$.each(data.data, function(index, carTeam) {
					if(index == 0) {
						firstTeamName = carTeam.carItemName;
					}
					carTeams = carTeams + "<option  value='" + carTeam.id + "'>" + carTeam.carItemName + "</option>"
				})
			}
		}
	})
	$("#motor" + type).append("<form><div class='form-inline'>" +
		"<div class='form-group'><label>&emsp;短驳承运方：</label> <select name='shortBargeId' onchange='changeAndSetValue(this)' >" +
		carTeams +
		"</select>	<input type='hidden' name='shortBargeName' value='" + firstTeamName + "' /></div>" +
		"<div class='form-group short-client'><label>运输单价：</label><input name='transportPrice' type='text'><span>元</span></div>" +
		"<div class='form-group short-client'><label>扣损比率：</label><input name='deductionRate' type='text'><span>‰</span></div>" +
		"<div class='form-group short-client'><label>扣损单价：</label><input name='deductionPrice' type='text'><span>元</span></div>" +
		"<div class='form-group short-client'><label>付款周期：</label> <select name='payment'>"+
		"<option value='0'>日结</option>" +
		"<option value='1'>周结</option>" +
		"<option value='2'>月结</option>" +
		"</select></div>" +
		"</div ></form>")
})

function successSure(){
	$("#successModal").modal();
	window.location.reload();
}

//项目类型联动 计量方式
$("#creatProjectModal select[name=projectType]").change(function(){
	var projectType = $(this).val();
	if(projectType!=undefined && projectType!=null && projectType!=""){
		if(projectType==0){//集装箱
			$("#creatProjectModal select[name=valuationUnitName]").html('<option></option><option value="1">吨</option><option value="0">件</option>');
			$("#creatProjectModal select[name=transportType]").html('<option></option>'+
					'<option value="1">接取</option>'+
					'<option value="2">送达</option>'+
					'<option value="3">火运</option>'+
					'<option value="4">接取+火运</option>'+
					'<option value="5">火运+送达</option>'+
					'<option value="7">接取+送达</option>'+
					'<option value="6">联运</option>');
			
		}else if(projectType==1){//散装箱
			$("#creatProjectModal select[name=valuationUnitName]").html('<option></option><option value="1">吨</option>');
			$("#creatProjectModal select[name=transportType]").html('<option></option>'+
					'<option value="0">汽运</option>'+
					'<option value="1">接取</option>'+
					'<option value="2">送达</option>'+
					'<option value="3">火运</option>'+
					'<option value="4">接取+火运</option>'+
					'<option value="5">火运+送达</option>'+
					'<option value="7">接取+送达</option>'+
					'<option value="6">联运</option>');
		}
	}
})
	
