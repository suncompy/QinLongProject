//# sourceURL=exhibition.js
/**
 * @title 短驳打包-运单中间表
 * @description 短驳打包-运单中间表
 * @author LiuLiuJiefeng
 * @date 2018-01-16
 */
;
(function($) {
  'use strict';	
  $(function() {
  var queryCondition = {}, queryData = {}, pageSize = 15;

  queryData.start = 0;
  queryData.length = pageSize;
  queryData.condition = queryCondition;
  function getProjectListTop(){
	  var url = "api/link/exhibitions/top";
	  var type = "get";
		$.fd.ajax({
	      url : url,
	      type : type,
	      data : queryCondition,
	      success : function(d) {
	    	  if(d&&d.data){
	    		  var htmlLatestProjects = buildHtml(d.data);
	    		  $(" [name=LatestProjects]").html(htmlLatestProjects);
	    	  }
	      }
		});
  }
  function getProjectListNotTop(isInit){
	  var url = "api/link/exhibitions/notTop";
	  var type = "get";
		$.fd.ajax({
	      url : url,
	      type : type,
	      data : queryData,
	      success : function(d) {
	    	  if(d&&d.data&&d.data.data){
	    		  var htmlLatestProjects = buildHtml(d.data.data);
	    		  $(" [name=allProjects]").html(htmlLatestProjects);
	    		  initPagination(isInit, d.data);
	    	  }
	      }
		});
  }
  getProjectListTop();
  getProjectListNotTop(true);
//检索
 $(".searchIcon").click(function(e) {
   e.preventDefault();
   // 刷新检索条件
   loadqueryCondition();
   queryData.condition=queryCondition;
   // 刷新数据
   getProjectListNotTop(true);
 });
  function loadqueryCondition(){
	  queryCondition = $(" [name=frmProjectQuery]").serializeJson();
	  return queryCondition;
  }

  function buildHtml(data){
   var html = '';
	 for(var i=0;i<data.length;i++){
	  var classItem =  i%3==2?'listItem nomarg':'listItem';
	  html=html+'<li><div class="'+classItem+'" >               '    
	  +'<div class="clearfix"><em class="projectBg">'
	  +'<img src="'+data[i].logo+'" alt="" />'
	  +'</em><p>'+data[i].projectCode+'</p>                        '
	  +'</div><div>                                   '
	  +'	<label>委托方：</label>                   '
	  +'	<span>'+data[i].client+'</span>               '
	  +'</div>                                  '
	  +'<div class="goodsInformation">          '
	  +'	<label>货物信息：</label>                  '
	  +'	<span>'+data[i].cargoName+'</span>                       '
	  +'	<span>'+data[i].cargoSpecifications+'</span>                      '
	  +'	<span>'+data[i].valuationUnitName+'</span>                        '
	  +'</div>                                  '
	  +'<div>                                   '
	  +'	<label>短驳承运方：</label><dl class="shortClient">'
	  +getShortBargeCarrierName(data[i])
	  // +'	<span>'+getShortBargeCarrierName(data[i])+'</span>               '
	  +'</dl></div>                                  '
	  +'<div>                                   '
	  +'	<label>新建时间：</label>                  '
	  +'	<span>'+data[i].createDate+'</span>        '
	  +'</div>                                  '
	  +'</div></li>                                   '
	 }
   return html;
  }
  function getShortBargeCarrierName(dataObj){
	  var shortName='';
	  var len = 3;
	  if(dataObj.shortBargeName){
		  var shortBargeNameArr = (dataObj.shortBargeName).split(",");
		  len = 3<shortBargeNameArr.length?3:shortBargeNameArr.length;
		  for(var k=0;k<len;k++){
			  shortName = shortName+'<dd>'+shortBargeNameArr[k]+'</dd>'
		  }
		  return shortName;
	  }
	  var shortBargeCarrierName = '';
	  if(!dataObj || !dataObj.shortBargeCarrierName){
		  return '<dd></dd>';
	  }  
	  var shortBargeCarrierNameArr =JSON.parse(dataObj.shortBargeCarrierName);
	  if(!shortBargeCarrierNameArr[0] || !shortBargeCarrierNameArr[0].shortBargeName){
		  return '<dd></dd>';
	  }
	  if(shortBargeCarrierNameArr[0].shortBargeName=='平台'){
		  return '<dd>平台</dd>';
	  }
	  shortName='';
	  len = 3<shortBargeCarrierNameArr.length?3:shortBargeCarrierNameArr.length;
	  for(var j=0;j<len;j++){
		  shortName = shortName+'<dd>'+shortBargeCarrierNameArr[j].shortBargeName+'</dd>'
	  }
	  return shortName;
  }
  
//获取总页数
		var getTotalPage = function (totalRecord, pageSize) {
			if (!pageSize) {
				pageSize = 15;
			}
			return Math.floor((totalRecord + pageSize - 1) / pageSize);
		};
		//更多的时候需要生成分页栏
		var initPagination = function (isInit, d) {
			if ($('#listPagination').data("twbs-pagination") && isInit) {
				$('#listPagination').twbsPagination('destroy');
			}
			$("#listPagination").twbsPagination({
				totalPages: getTotalPage(d.recordsTotal),
				visiblePages: 5,
				first: "首页",
				prev: "上页",
				next: "下页",
				last: "末页",
				onPageClick: function (event, page) {
					queryData.start = (page - 1) * pageSize;
					//if(!isInit){
					getProjectListNotTop(false);
					//}
				}
			});
		};

  
  });
}(jQuery));
