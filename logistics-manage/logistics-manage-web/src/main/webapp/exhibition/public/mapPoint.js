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
	    $("#btnMapPointQuery").click(function(e) {
		    e.preventDefault();
		    var data = $("#frmMapPointQuery").serializeJson();
		    query("img/truck_t.gif",data);
	   }); 
	    function query(icon,data){
	    	$.fd.ajax({
				  url : "bussinessCount/map/marker",
				  type : "get",
				  data: data,
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
	   query("img/truck_t.gif",{});
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

  });
}(jQuery));
