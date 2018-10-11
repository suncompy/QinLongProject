$(function() {
	//选项切换清空复选框
	$(".tabbable .nav li").click(function() {
		if ($(this).children().text() == "已离职员工信息") {
			$(".dataTimes").hide();
			$("#lookMore").hide();
			$("#wrap").css("height", "29px")
		} else {
			$(".dataTimes").show();
			$("#lookMore").show();
			$("#wrap").css("height", "25px");
			$('#lookMore').html("显示筛选<em class='arrows'></em>");

		}
		$("input[type=checkbox]").prop("checked", false);
	});
	
	$('#searchSpan').click();
	
	//添加员工模态框 
	$("#addemployee").click(function() {
		$('#addemployeeModal input[name="phone"]').attr('readonly', false);
		$('#addemployeeModal input[name="phone"]').removeClass('inputbg');
		$('#addemployeeModal input[name="idcard"]').attr('readonly', false);
		$('#addemployeeModal input[name="idcard"]').removeClass('inputbg');
		$("#addEmpId").val("");
		$("#cityAddress").citySelect({
			prov : "新疆",
			city : "乌鲁木齐",
			dist : "新市区",
			nodata : "none"
		});
		$('#myModalLabel').html('添加员工');
		$('#addemployeeModal input[name="name"]').attr("readonly", false);
		$("#addForm")[0].reset();
		$("#addemployeeModal").modal();
	})
	
	var tale = new $.tale();
	//增加员工弹出框的 确定按钮
	$("#addEmpBtn").click(function(){
		//判断选的是否是离职状态
		var workStatus = $("#addemployeeModal select[name=workStatus]").val();
		
		if(workStatus==1){
		   tale.alertConfirm({
	            title:"确认将该员工设置为离职状态?",
	            then: function () {
	            	addEmpFn();
	            }
	        });
		}else{
			addEmpFn();
		}
	})
	
	//增加或者修改员工
	function addEmpFn(){
		var hideEmpId = $("#addEmpId").val();
		var data = $("#addForm").serialize();//序列化表格内容为字符串 
		if(hideEmpId == undefined || hideEmpId == null || hideEmpId ==""){
			var url = $.getUrl('/humanOrganization/addEmployee.do');
	        tale.post({
	            url: url,
	            data: data,
	            success: function (result) {
	                if(result.status==200){
	        			$("#addemployeeModal").modal("hide");
	                	tale.alertOkAndReload("新增员工信息成功!");
						//window.location.reload();
	        		}else{
	        			 tale.alertError(result.msg || '登录失败');
	        		}
	            }
	        });
        	return false;
		}else{
			var url =  $.getUrl('/humanOrganization/updateEmployee.do');
			tale.post({
	            url: url,
	            data: data,
	            success: function (result) {
	                if(result.status==200){
	        			$("#addemployeeModal").modal("hide");
	                	tale.alertOkAndReload("修改员工信息成功");
	                	$('#showMask', window.parent.document).hide();
						//window.location.reload();
	        		}else{
	        			 tale.alertError(result.msg || '登录失败');
	        		}
	            }
	        });
		}
	}
	
	
	//员工 进行离职操作
	$("#delBtn").click(function(){
		var ids = new Array(); 
		$("input[name='empCheckBox']:checked").each(function(){ 
		   ids.push($(this).val());
		})
		if(ids==""){
			tale.alertWarn("请选择一条您要离职的员工信息");
			$('#showMask', window.parent.document).hide();
			return ;
		}

		var url = $.getUrl('/humanOrganization/delEmployee.do');
		tale.post({  
       		url: url,  
	        data:{ids:ids},
	        cache:false,
	        dataType:'json',
        	success:function(result){
        		if(result.status==200){
                	tale.alertOkAndReload("员工离职成功");
                	$('#showMask', window.parent.document).hide();
        		}else{
        			 tale.alertError(result.msg || '登录失败');
        			 $('#showMask', window.parent.document).hide();
        		}
        	}  
    	})
	})
})



//修改员工信息
function changeEmploy(){
	var tale = new $.tale();
	
	if($("input:checkbox[type='checkbox']:checked").length > 1) {
		tale.alertWarn("抱歉，不可批量操作");
		$('#showMask', window.parent.document).hide();
		return ;
	} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
		tale.alertWarn("请选择一条您要修改的员工信息");
		$('#showMask', window.parent.document).hide();
		return ;
	} else {
		$("#addForm")[0].reset();
	}

	var ids = new Array(); 
	var i=0;
	$("input[name='empCheckBox']:checked").each(function(){
		i++;
		if(i>1){
			tale.alertWarn("抱歉，不可批量操作");
			$('#showMask', window.parent.document).hide();
			return ;
		}
		ids.push($(this).val());
		$("#addEmpId").val($(this).val());
	})
	if(ids==""){
		tale.alertWarn("请选择一条您要修改的员工信息");
		$('#showMask', window.parent.document).hide();
		return ;
	}
	 $('#myModalLabel').html('修改员工');
	 
	 var url = $.getUrl('/humanOrganization/getEmployee.do');
	 
	 tale.post({  
   		url: url,  
        data:{ids:ids},
        cache:false,
        dataType:'json',
    	success:function(data){
    		if(data.status==200){
    			$('#addemployeeModal input[name="name"]').attr("readonly",true);
    			
    			var employInfo = data.data;
				$('#addemployeeModal input[name="id"]').val(employInfo.id);
				$('#addemployeeModal input[name="name"]').val(employInfo.name);
				$("#addemployeeModal select[name='sex'] option").each(function(){
					    $(this).attr("selected",false);
				})
				$("#addemployeeModal select[name='sex'] option[value='"+employInfo.sex+"']").attr("selected","selected");
				//婚姻状况
				$("#addemployeeModal select[name='isMarry'] option[value='"+employInfo.isMarry+"']").attr("selected","selected");
				//学历
				$("#addemployeeModal select[name='education'] option[value='"+employInfo.education+"']").attr("selected","selected");
				
				//对元素值xxx进行选定
				$("#addemployeeModal select[name='roleId'] option").each(function(){
					    $(this).attr("selected",false);
				})
				$("#addemployeeModal select[name='roleId'] option[value='"+employInfo.roleId+"']").attr("selected","selected");
				
				$("#addemployeeModal select[name='workStatus'] option[value='"+employInfo.workStatus+"']").attr("selected","selected");
				
				$("#addemployeeModal select[name='branchGroupId'] option").each(function(){
					    $(this).attr("selected",false);
				})
				$("#addemployeeModal select[name='branchGroupId'] option[value='"+employInfo.groupId+"']").attr("selected","selected");
    			$('#addemployeeModal input[name="phone"]').val(employInfo.phone);
    			
    			//$('#addemployeeModal input[name="phone"]').attr('readonly','value');
    			//$('#addemployeeModal input[name="phone"]').addClass('inputbg');
    			
    			$('#addemployeeModal input[name="email"]').val(employInfo.email);
    			$('#addemployeeModal input[name="age"]').val(employInfo.age);
    			$('#addemployeeModal input[name="remark"]').val(employInfo.remark);
    			//省市区
			    $("#cityAddress").citySelect({
					    	prov:employInfo.province, 
					    	city:employInfo.city,
							dist:employInfo.district,
							nodata:"none"
				}); 
    			$('#addemployeeModal input[name="address"]').val(employInfo.address);
    			//身份证号
    			$('#addemployeeModal input[name="idcard"]').val(employInfo.idcard);
    			//$('#addemployeeModal input[name="idcard"]').attr('readonly','value');
    			//$('#addemployeeModal input[name="idcard"]').addClass('inputbg');
    			$('#addemployeeModal input[name="startWorkDate"]').val(employInfo.startWorkDate);
    			$("#addemployeeModal").modal();
    		}else{
    			tale.alertWarn(data.msg);
    			$('#showMask', window.parent.document).hide();
    		}
    	}  
	})
}

/**
 * 搜索
 */
function search(param){
	var status = $('#searchForm input[name=workStatus]').val();
	var page = 'employPage';
	if(status==0){
		$("#searchForm input[name=workStatus]").val(0);;//在职
		page = 'employPage';
	}else{
		$("#searchForm input[name=workStatus]").val(1);//离职
		page = 'employLeavePage';
	}
	$.ajax({
    	type:'post',      
   		url: $.getUrl('/humanOrganization/listEmployByPage.do'),
       	data:{page:1,search:JSON.stringify($('#searchForm').serializeJSON())},
        cache:false,
        dataType:'json',
    	success:function(data){
    		if(data.status==200){
    			htmlTable(data.data.rows);
    			$("#"+page+"").paging({
					pageNo:1, 
					totalPage: data.data.totalPage,
					totalSize: data.data.limit,
					callback: function(num) {
						searchByPage(num);
					}
				})	
    		}
    	}	
	})	
}



function listEmploy(status){
	var tale = new $.tale();
	var page = 'employPage';
	if(status==0){
		$("#searchForm input[name=workStatus]").val(0);;//在职
		page = 'employPage';
	}else{
		$("#searchForm input[name=workStatus]").val(1);//离职
		page = 'employLeavePage';
	}
	var url = $.getUrl('/humanOrganization/listEmployByPage.do');
	tale.post({
   		url: url,
        data:{page:1,search:JSON.stringify($('#searchForm').serializeJSON())},
        cache:false,
        dataType:'json',
    	success:function(data){
    		if(data.status==200){
    			htmlTable(data.data.rows);
        		$("#"+page+"").paging({
					pageNo:1, 
					totalPage: data.data.totalPage,
					totalSize: data.data.limit,
					callback: function(num) {
						searchByPage(num);
					}
				})	
     		}else{
     			tale.alertWarn(data.msg);
    			$('#showMask', window.parent.document).hide();
      		}
	 	}
	})
}
/**执行分页查询的方法 */
function searchByPage(num){
	var tale = new $.tale();
	tale.post({  
   		url: $.getUrl('/humanOrganization/listEmployByPage.do'),
       	data:{page:num,search:JSON.stringify($('#searchForm').serializeJSON())},
        cache:false,
        dataType:'json',
    	success:function(data){
    		if(data.status==200){
    			htmlTable(data.data.rows);
    		}else{
     			tale.alertWarn(data.msg);
    			$('#showMask', window.parent.document).hide();
      		}
		}
	})
}

function htmlTable(results){
	var employeeList = results;
	var tbody ="employTbody";
	if($('#searchForm input[name=workStatus]').val()==0){
		tbody ="employTbody";
	}else{
		tbody ="employLeaveTbody";
	}
	$("#"+tbody+"").html("");
	$.each(employeeList,function(index,employee){
		var workStatus="";
		
		if(employee.workStatus==0){
			workStatus="在职";
		}else
		if(employee.workStatus==1){
			workStatus="离职";
		}else
		if(employee.workStatus==2){
			workStatus="休假";
		}else
		if(employee.workStatus==3){
			workStatus="停职";
		}
		
		$("#"+tbody+"").append("<tr>"
			+"<td style='text-align: center;'><label class='demo--label'>"
			 +" <input class='demo--checkbox' name='empCheckBox' value='"+employee.id+"' type='checkbox'   >"
              +"<span class='demo--checkboxInput'></span>"
             +"</label></td>"
		 +"<td id=''>"+employee.name+"</td>"
		  +"<td name='sex"+index+"'></td>"
		  +"<td id=''>"+employee.age+"</td>"
		   +"<td id=''>"+workStatus+"</td>"
		   +"<td name='isMarry"+index+"'></td>"
		    +"<td name='education"+index+"'></td>"
		 +"<td id=''>"+employee.roleName+"</td>"
		 +"<td id=''>"+employee.phone+"</td>"
		  +"<td id=''>"+employee.groupName+"</td>"
		 +"<td id=''>"+employee.email+"</td>"
		  +"<td id=''>"+employee.startWorkDate+"</td>"
		  +"<td name='leaveOfficeDate'>"+employee.leaveOfficeDate+"</td>"
		  +"<td id=''>"+employee.idcard+"</td>"
		 +"<td id=''>"+employee.areaCode+employee.address+"</td>"
		 +"</tr>")
		 var sex = "";
		 
		if(employee.sex==1){
			sex="男";
		}else if(employee.sex==0){
			sex="女";
		}
		$("#"+tbody+" td[name='sex"+index+"']").html(sex); 
		
		var isMarry = "";
		if(employee.isMarry==0){
			isMarry="未婚";
		}else if(employee.isMarry==1){
			isMarry="已婚";
		}
		$("#"+tbody+" td[name='isMarry"+index+"']").html(isMarry); 
		
		var education = "";
		
		if(employee.education==1){
			education = "初中";
		}else if(employee.education==2){
			education = "高中";
		}else if(employee.education==3){
			education = "大专";
		}else if(employee.education==4){
			education = "本科";
		}else if(employee.education==5){
			education ="研究生";
		}else if(employee.education==0){
			education = "小学";
		}
		$("#"+tbody+" td[name='education"+index+"']").html(education); 
	})
	if($('#searchForm input[name=workStatus]').val()==0){
		$("#"+tbody+" td[name='leaveOfficeDate']").remove();
	}
}