$(document).ready(function(){
	//个人车辆切换
      	$(".personal,.fleetBtn").hide();
        $(".personBtn").click(function(event) {
          	$(".personBtn,.fleet").hide();
            $(".fleetBtn,.personal").show();
         });
        $(".fleetBtn").click(function(event) {
        		$(".personBtn,.fleet").show();
            $(".fleetBtn,.personal").hide();
         });

        $(".form_datetime").datetimepicker({format: 'yyyy-mm-dd'});
          //初始化隐藏驾驶员信息选项卡
           $(".driverInfo").hide();
          var temp = $(".driver-license");
          //驾驶员信息选项卡显示隐藏时间
          $(".radio").click(function(){
            var selectValue = $("input[name='driverRadio']:checked").val();
                if(selectValue=="yes"){
                  	$(".driverInfo").hide();
                 	$(".driver-license").remove();
                	$(".owner").append(temp);
                }else if(selectValue=="no"){
                    $(".driverInfo").show();
                	$(".driver-license").remove();
                	$(".driverInfo").append(temp);
                }
          });
 });
  
// 车队加入身份证照片
//      function previewImage(file)
//       {
//      
//        var MAXWIDTH  = 150; 
//        var MAXHEIGHT = 150;
//        var div = document.getElementById('idFront');
//        if (file.files && file.files[0])
//        {
//            div.innerHTML ='<img id=imghead>';
//            var img = document.getElementById('imghead');
//            img.onload = function(){
//              var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
//              img.width  =  rect.width;
//              img.height =  rect.height;
//              
//            }
//            var reader = new FileReader();
//            reader.onload = function(evt){img.src = evt.target.result;}
//            reader.readAsDataURL(file.files[0]);
//        }
//        else //兼容IE
//        {
//          var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
//          file.select();
//          var src = document.selection.createRange().text;
//          div.innerHTML = '<img id="imghead">';
//          var img = document.getElementById('imghead');
//          img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
//          var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
//          status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
//          div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
//        }
//      }
