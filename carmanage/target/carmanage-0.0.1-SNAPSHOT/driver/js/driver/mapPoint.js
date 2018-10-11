//# sourceURL=mapPoint.js
/**
 * @title 地图标记表
 * @description 地图标记表
 * @author LiuJiefeng
 * @date 2018-01-16
 */
;
(function($) {
  'use strict';	
  $(function() {
	  //初始化地图对象，加载地图
	    var map = new AMap.Map("container", {
	        resizeEnable: true,
	        center: [114.306206, 38.975468],
	 		zoom:5
	    });
	    var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
	    function markerClick(e) {
	        infoWindow.setContent(e.target.content);
	        infoWindow.open(map, e.target.getPosition());
	    }
	    function query(icon,data){
	    	$.ajax({
				  url : "map/marker.do",
				  type : "get",
				  data: data,
				  dataType : "json",
				  success : function(data) {
					  	console.log(data);
					  	for (var i = 0, marker; i < data.data.length; i++) {
					  		var lnglats = data.data;
					        var marker = new AMap.Marker({
					        	icon: icon,
					            position: [lnglats[i].lon,lnglats[i].lat],
					            map: map
					        });
					        marker.content = '车牌号是：' + lnglats[i].carNo;
					        marker.on('click', markerClick);
					        marker.emit('click', {target: marker});
					    }
					  	callback();
				  }
			});
	    }
	    var orderId = $("#hiddenOrderId").val();
	   query("../driver/img/truck_t.gif",{"orderId":orderId});
	   function callback(){
		    map.setFitView();
		    map.setZoom(5);
		    map.plugin(["AMap.ToolBar"], function() {
				map.addControl(new AMap.ToolBar());
			});
			if(location.href.indexOf('&guide=1')!==-1){
				map.setStatus({scrollWheel:false})
			}
	   }
	   queryPosition({"orderId":orderId});
	   function queryPosition(data){
	    	$.ajax({
				  url : "map/order/position.do",
				  type : "get",
				  data: data,
				  dataType : "json",
				  success : function(data) {
					  	console.log(data);
					  	$("#positionDetail").empty();
					  	var htmlbulkInfo = "";
					  	for(var i=0;data.data&&i<data.data.length;i++){
							htmlbulkInfo=htmlbulkInfo+"<ul class='localUl'><li>"+data.data[i].createDate+"</li><li>"+data.data[i].position+"</li><li>"+data.data[i].remark+"</li></ul>"
						}
					$("#positionDetail").append(htmlbulkInfo);
				  }
			});
	    }
  });
}(jQuery));
