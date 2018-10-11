	window.onresize = function(){
		if($("#lookMore").is(":hidden")==true){
	if(document.body.clientWidth<1611){
		$('#lookMore').show();
	}
	else{
		$('#lookMore').hide();
	}	
	}
}

$(function() {
			
	//页面刷新
$("body").bind("keydown",function(event){  
           if (event.keyCode == 116) {  
                     event.preventDefault(); //阻止默认刷新  
                     location=location;  
                     $('#showMask', window.parent.document).hide();
         }  
     }) 
     //显示筛选
		if($("#lookMore").is(":hidden")==true){
	if(document.body.clientWidth<1702){
		$('#lookMore').show();
	}
	else{
		$('#lookMore').hide();
	}	
	}
	var slideHeight = 25; // px 定义折叠的最小高度
	var defHeight = $('#wrap').height();
	if(defHeight >= slideHeight) {
		$('#wrap').css('height', slideHeight + 'px');
		$('#lookMore').append("显示筛选<em class='arrows'></em>");
		$('#lookMore').click(function() {
			var curHeight = $('#wrap').height();
			if(curHeight == slideHeight) {
				$('#wrap').animate({
					height: defHeight
				}, "normal");
				$(this).html("收起筛选<em class='arrowup'></em>");
			} else {
				$('#wrap').animate({
					height: slideHeight
				}, "normal");
				$(this).html("显示筛选<em class='arrows'></em>");
			}
			return false;
		});
	}
	// 表格的勾选框居中
	$("table tr").each(function() {
		$(this).children('td:eq(0)').css('text-align', 'center');
	});
$("body").on("click",".checks",function(){
			$('#showMask', window.parent.document).hide();
		});
//取消layer
$(".cancleBtns").click(function(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
$('#showMask', window.parent.parent.document).hide();
});
//取消bootstrap

$(".sureBtn").click(function(){
	$('#showMask', window.parent.document).hide();
});
$(".exportBtn").click(function(){
	$('#showMask', window.parent.document).show();
});
//
$(".modal-header span").click(function(){
$('#showMask', window.parent.document).hide();
});
$('.modal').on('shown.bs.modal', function (e) {
	$('#showMask', window.parent.document).show();
});
$('.modal').on('hide.bs.modal', function (e) {
	$('#showMask', window.parent.document).hide();
});

});

