$(function(){
//

var slideHeight =20; // px
   var defHeight = $('#wrap').height();
   if(defHeight >= slideHeight){
$('#wrap').css('height' , slideHeight + 'px');
$('#lookMore').click(function(){
   var curHeight = $('#wrap').height();
   if(curHeight == slideHeight){
    $('#wrap').animate({
     height: defHeight
    }, "normal");
    $('#lookMore').html('关&emsp;&emsp;闭');
    $('#gradient').fadeOut();
   }else{
    $('#wrap').animate({
     height: slideHeight
    }, "normal");
    $('#lookMore').html('显示筛选');
    $('#gradient').fadeIn();
   }
   return false;
});  
   }



 // 表格的勾选框居中
   $("table tr").each(function() {
        $(this).children('td:eq(0)').css('text-align','center');
     });
   





})
