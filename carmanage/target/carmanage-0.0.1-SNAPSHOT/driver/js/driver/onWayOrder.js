	$(function() {
	var slideHeight = 180; // px 定义折叠的最小高度
	var defHeight = $('.wrap').height();
	if(defHeight >= slideHeight) {
		$('.wrap').css('height', slideHeight + 'px');
		$('.read-more').append('<a href="#">展开<i class="fa fa-chevron-down" aria-hidden="true"></i></a>');
		$('.read-more a').click(function() {
			var curHeight = $(this).parent().parent().next().next().height();
			if(curHeight == slideHeight) {
				$(this).parent().parent().next().next().animate({
					height: defHeight
				}, "normal");
				$(this).html('折叠<i class="fa fa-chevron-up" aria-hidden="true"></i>');
			} else {
				$(this).parent().parent().next().next().animate({
					height: slideHeight
				}, "normal");
				$(this).html('展开<i class="fa fa-chevron-down" aria-hidden="true"></i>');
			}
			return false;
		});
	}
});