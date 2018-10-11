$(function() {
	var slideHeight = 25; // px 定义折叠的最小高度
	var defHeight = $('#wrap').height();
	if(defHeight >= slideHeight) {
		$('#wrap').css('height', slideHeight + 'px');
		$('#lookMore').append('显示筛选<i class="fa fa-chevron-down" aria-hidden="true"></i>');
		$('#lookMore').click(function() {
			var curHeight = $('#wrap').height();
			if(curHeight == slideHeight) {
				$('#wrap').animate({
					height: defHeight
				}, "normal");
				$(this).html('收起筛选<i class="fa fa-chevron-up" aria-hidden="true"></i>');
			} else {
				$('#wrap').animate({
					height: slideHeight
				}, "normal");
				$(this).html('显示筛选<i class="fa fa-chevron-down" aria-hidden="true"></i>');
			}
			return false;
		});
	}
	// 表格的勾选框居中
	$("table tr").each(function() {
		$(this).children('td:eq(0)').css('text-align', 'center');
	});



})