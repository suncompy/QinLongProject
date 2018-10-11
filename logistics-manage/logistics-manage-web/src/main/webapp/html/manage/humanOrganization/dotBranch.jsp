<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<%@ taglib uri="/WEB-INF/permission.tld" prefix="per"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title>网点分支</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap/css/font-awesome.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage/humanOrganization/dotBranch.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/public/public.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/page.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/exhibition/css/sweetalert2.min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/config/url.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/search/search.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/humanOrganization/dotBranch.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.validate.js"></script>
			<!--shilvfei加的-->
		<script type="text/javascript"  src="${pageContext.request.contextPath}/public/js/jquery.cityselect.js"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/city.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/page/paging.js" ></script>
    	<script src="${pageContext.request.contextPath}/exhibition/js/sweetalert2.min.js"></script>
		<script src="${pageContext.request.contextPath}/exhibition/js/base.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery.serializejson.js"></script>
	</head>
	
	<body>
		<!--网点分支搜索表单 -->
		<div class="form project_form container_top">
			<form class="form-inline maxWidth" id="searchForm">
				<div id="wrap">
					<div class="form-group">
						<label>网点分支：</label>
						<input name='branchGroupName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>部门简称：</label>
						<input name='shortName' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>简称代码：</label>
						<input name='shortCode' type="text" maxlength="30"/>
					</div>
					<div class="form-group">
						<label>网点归属：</label>
						<input name='ascriptionName' type="text" maxlength="30"/>
					</div>				
				</div>
				<div class="foot">
					<a class="btn btn-success" href="javascript:;"><em class="search"></em>
						<span id="searchSpan" onclick="search('humanOrganization/listDotBranchByPage.do')">搜索</span>
					</a>
				</div>
			</form>
		</div>
		
		<!-- 网点分支列表表格 -->
		<div class="container_bottom tableDiv">
			<div class="tabbable" id="tabs-529262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel1" data-toggle="tab">网点管理</a>
					</li>
				</ul>
				<div class="tab-content">
					<!--网点管理表格开始-->
					<div class="tab-pane active" id="panel1">

						<div class="domain">
							<div class="tableBg">
							    <per:hasUrlPerm code="addBranch">
								    <a href="#" class="exportBtn addNew" id="addBranch"><span>添加分支</span></a>
								 </per:hasUrlPerm>
								<per:hasUrlPerm code="updateBranch">
								    <a href="#" class="exportBtn revise" id="modify"><span>修改</span></a>
								 </per:hasUrlPerm>
								  <per:hasUrlPerm code="delBranch">
								   <a href="#" class="exportBtn del" id="delBtn"><span>删除</span></a>
								 </per:hasUrlPerm>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="tableTop">
											<th><label class="demo--label">
											     <input class="demo--checkbox" type="checkbox" disabled="disabled"  >
                                                 <span class="demo--checkboxInput"></span>
	                                        </label> </th>
											<th>编码</th>
											<th>部门名称</th>
											<th>部门简称</th>
											<th>简称代码</th>
											<th>网点归属</th>
											<th>负责人</th>
											<th>员工人数</th>
											<th>地址信息</th>
											<th>关联火车站</th>
											<th>关联发运地</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody id='dotBranchTbody'>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column paging page_div" id="page">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 添加分支模态框 -->
		<div class="modal fade addBranchModal" id="addBranchModal" tabindex="-1" role="dialog" data-backdrop="static">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">添加分支</h4>
						<span data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
					<h5>添加分支<i class='requireds'>*</i></h5>
						<form id="addForm">
							<input type="hidden" id="addBranchId" name="id"/>
						<div class="form-inline">
							<div class="form-group">
								<label>&emsp;&emsp;&emsp;编码：</label>
								<div class="input-group col-md-2">
									<span style="display:none" class="input-group-addon" name="codePrifex"></span>
									<input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="6" name="unrealCode"/>
									<input type="hidden" maxlength="100" name="code"/>
								</div>
							</div>
							<div class="form-group">
								<label>&emsp;部门名称：</label>
								<input type="text" maxlength="30" name="name" />
							</div>
							<div class="form-group">
								<label>&emsp;部门简称：</label>
								<input type="text" onkeyup="value=value.replace(/^[\u0391-\uFFE5]+$/,'')" placeholder="只能是英文字母！"  maxlength="4" name="shortName"/>
							</div>
							<div class="form-group">
								<label>&emsp;&emsp;负责人：</label>
								<select name="responsiblerid">
									<option></option>
									<c:forEach items="${reponsiblers}" var="reponsibler">	
										<option value="${reponsibler.id}">${reponsibler.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-inline">
							<div class="form-group" id="ascriptionIdDiv">
								<label>&emsp;网点归属：</label>
								<select name="ascriptionId" onchange="changeCode()">
									<option></option>
									<c:forEach items="${brachGroups}" var="brachGroup">	
										<option value="${brachGroup.id}" code="${brachGroup.code}">${brachGroup.name}</option>
									</c:forEach> 
								</select>	
							</div>
							<div class="form-group" id="hideTaxNum" style="display: none;"> 
								<label>纳税识别号：</label>
								<input type="text" maxlength="30" name="dutyParagraph"/>
							</div>
						</div>
						<div class="form-inline">
							<div class="form-group" id="city_first">
								<label>&emsp;地址信息：</label>
								<select name="province"  class="prov">
								</select>
								&nbsp;
								<select name="city"  class="city">
								</select>
								&nbsp;
								<select name="district"  class="dist">
								</select>
							</div>
							<input type="text"  name="address"  class="addressInput" maxlength="100" />
							<div  id="searchResultPanel" style="z-index:999999999;border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
						</div>
						<%-- <div class="form-inline">
							<div class="form-group" id="relationTrainLocation">
								<label>关联火车站：&emsp;</label>
									<img src='${pageContext.request.contextPath}/img/add1.png' class='trainAddImg' />
									<input type="hidden" name='relationTrainLocationId'>
							</div>
						</div> --%>
						<input type="hidden" name="relationBeginLocation"  />
						<input type="hidden" name="level"  />
						<input type="hidden" name="ascriptionName"  />
						<div class="form-inline ">
							<div class="form-group relationClass">
								<label>关联发运地：</label>
								<ul id="relationUl">
								</ul>
							</div>
						</div>
						<div class="form-inline">
							<label>&emsp;&emsp;&emsp;&emsp;备注：</label>
							<input type="text" class="remarkInput" name="comment" maxlength="100" />
							<span class="limitSpan">0/20</span>
						</div>
						<div class="row clearfix ">
							<div class="col-md-12 column modal_btn">
								<button type="button" class="btn sureBtn" id="addBranchBtn" >确定</button>
								<button type="button" class="btn cancleBtn" data-dismiss="modal" onclick="cancle()">取消</button>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
	
<script>
	/**
	 * - 添加火车站
	 */
	 $("body").on("click", ".trainAddImg", function() { //关联号增加
			$("#relationTrainLocation").html('<label>关联火车站：</label><select name="relationTrainLocationId"><option></option>'
					+'<c:forEach items="${trainStations}" var="trainStation">'
					+'<option value="${trainStation.id}">${trainStation.stationName}</option>'
					+'</c:forEach></select>&emsp;'
					+"<img src='${pageContext.request.contextPath}/img/reduce.png' class='trainReduceImg' />"		
			)
		});
	/**
	 * - 减少火车站
	 */
	$("body").on("click", ".trainReduceImg", function() { //关联号减少
		$("#relationTrainLocation").html('<label>关联火车站：&emsp;</label>'
		+"<img src='${pageContext.request.contextPath}/img/add1.png' class='trainAddImg' />"
		+"<input type='hidden' name='relationTrainLocationId'>"
		)
	});
	
	/**
	 * -添加发运地-
	 * */
	var i = 0;
	$("body").on("click", ".addImg", function() { //关联号增加
		i++;
		$("#relationUl").append("<form><li name='cityAddress" + i + "'>" +
			"<select name='province'  class='prov'></select>	&nbsp;&nbsp;" +
			"<select name='city'  class='city'></select>	&nbsp;&nbsp;" +
			"<select name='district'  class='dist'></select>" +
			"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />" +
			"<img src='${pageContext.request.contextPath}/img/reduce.png' class='reduceImg' />" +
			"</li></form>")

		$("li[name='cityAddress" + i + "']").citySelect({
			prov: "新疆",
			city: "乌鲁木齐",
			dist: "新市区",
			nodata: "none"
		});

	});
	/**
	 * -减少发运地-
	 * */
	$("body").on("click", ".reduceImg", function() { //关联号减少
		i--;
		if(i==0){
			$("#relationUl").html("");
			$("#relationUl").append("<li>" +
				"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />" +
				//"<img src='${pageContext.request.contextPath}/img/reduce.png' />" +
				"</li>")
		}
		$(this).parent().parent('form').remove();
	});
</script>

<!--网点分支的修改-->
<script>
	$('#modify').click(function(){
		$('#myModalLabel').html("修改分支");
		//修改
		if($("input:checkbox[type='checkbox']:checked").length > 1) {
			tale.alertWarn("抱歉，不可批量操作");
			$('#showMask', window.parent.document).hide();
			return ;
		} else if($("input:checkbox[type='checkbox']:checked").length == 0) {
			tale.alertWarn("请选择一条您要修改的网点分支");
			$('#showMask', window.parent.document).hide();
			return ;
		} else {
			$("#addForm").validate().resetForm();
			$("#addForm")[0].reset();
		}
		$('#myModalLabel').html("修改分支");
		
		var id = $("input:checkbox[type='checkbox']:checked").val();
		$("#addBranchModal select[name=ascriptionParentId]").change();
		$.ajax({  
		type:'post',      
		url:'${pageContext.request.contextPath}/humanOrganization/getDotBranchById.do', 
	    data:{id:id},
	    cache:false,
	    dataType:'json',
		success:function(data){
			if(data.status==200){
					var dotBranch = data.data;
					if(dotBranch.id == 1){
						$("#hideTaxNum").show();
						$('#addBranchModal input[name="dutyParagraph"]').val(dotBranch.dutyParagraph);
						$("#addBranchModal select[name='ascriptionId']").val("");
					}else{
						$("#hideTaxNum").hide();
						$('#addBranchModal input[name="dutyParagraph"]').val("");
					}
					$('#addBranchModal input[name="id"]').val(dotBranch.id);
					$('#addBranchModal input[name="unrealCode"]').val(dotBranch.code);
					$('#addBranchModal input[name="unrealCode"]').attr("readonly",true);
					$('#addBranchModal input[name="code"]').val(dotBranch.code);
					$('#addBranchModal input[name="name"]').val(dotBranch.name);
					$('#addBranchModal input[name="name"]').attr("readonly",true);
					$('#addBranchModal input[name="shortName"]').val(dotBranch.shortName);
					$('#addBranchModal input[name="shortName"]').attr("readonly",true);
					
					if(dotBranch.level==0){
						$("#ascriptionIdDiv").hide();
					}else{
						$("#ascriptionIdDiv").show();
					}
					
					$("#addBranchModal select[name='ascriptionId'] option[value='"+dotBranch.ascriptionId+"']").attr("selected","selected");
					$("#addBranchModal select[name='responsiblerid'] option[value='"+dotBranch.responsiblerid+"']").attr("selected","selected");
					$("#addBranchModal select[name='province'] option[value='"+dotBranch.province+"']").attr("selected","selected");
					$("#addBranchModal select[name='city'] option[value='"+dotBranch.city+"']").attr("selected","selected");
					$("#addBranchModal select[name='district'] option[value='"+dotBranch.district+"']").attr("selected","selected");
					
					
					$("#city_first").citySelect({
				    	prov:dotBranch.province, 
				    	city:dotBranch.city,
						dist:dotBranch.district,
						nodata:"none"
					}); 
					
					if(dotBranch.relationTrainLocationId !="" ){
						$("#relationTrainLocation").html('<label>关联火车站：</label><select name="relationTrainLocationId"><option></option>'
								+'<c:forEach items="${trainStations}" var="trainStation">'
								+'<option value="${trainStation.id}">${trainStation.stationName}</option>'
								+'</c:forEach></select>&emsp;'
								//+"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />" 
								+"<img src='${pageContext.request.contextPath}/img/reduce.png' class='trainReduceImg' />"		
						)
						$("#addBranchModal select[name='relationTrainLocationId'] option[value='"+dotBranch.relationTrainLocationId+"']").attr("selected","selected");
					}
					
					var relationBeginAddress =dotBranch.relationBeginAddress;
					$("#relationUl").html("");
					
					if(relationBeginAddress==""){
						$("#relationUl").append("<li>" +
								"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />" +
								"<img src='${pageContext.request.contextPath}/img/reduce.png' />" +
								"</li>")
					}
					var  i = 10000;
					$.each(relationBeginAddress, function(index,city) {
						$("#relationUl").append("<form><li name='cityAddress"+i+"'>"
						+"<select name='province'  class='prov'></select>	&nbsp;&nbsp;"
						+"<select name='city'  class='city'></select>	&nbsp;&nbsp;"
						+"<select name='district'  class='dist'></select>"
						+"<img src='${pageContext.request.contextPath}/img/add1.png' class='addImg' />"
						+"<img src='${pageContext.request.contextPath}/img/reduce.png' class='reduceImg' />"
						+"</li></form>")
						$("li[name='cityAddress"+i+"']").citySelect({
					    	prov:city.province, 
					    	city:city.city,
							dist:city.district,
							nodata:"none"
						}); 
						i++;
					});
					$('input[name="address"]').val(dotBranch.address);
        			$('input[name="comment"]').val(dotBranch.comment);
	    			$("#addBranchModal").modal();
	    		}else{
	    			tale.alertError(data.msg || '登录失败');
	    			$('#showMask', window.parent.document).hide();
	    		}
	   	 	}
		})
	})
</script>


</html>

