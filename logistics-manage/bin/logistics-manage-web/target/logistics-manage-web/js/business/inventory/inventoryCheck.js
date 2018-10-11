$(function(){
	//选项切换清空复选框
	$(".tabbable .nav li").click(function(){
		$("input[type=checkbox]").prop("checked",false);
	});
	var slideHeight1 = 25; // px 定义折叠的最小高度
	var defHeight1 = $('#wrap1').height();
	if(defHeight1 >= slideHeight1) {
		$('#wrap1').css('height', slideHeight1 + 'px');
		$('#lookMore1').append('显示筛选<i class="fa fa-chevron-down" aria-hidden="true"></i>');
		$('#lookMore1').click(function() {
			var curHeight = $('#wrap1').height();
			if(curHeight == slideHeight1) {
				$('#wrap1').animate({
					height: defHeight1
				}, "normal");
				$(this).html('收起筛选<i class="fa fa-chevron-up" aria-hidden="true"></i>');
			} else {
				$('#wrap1').animate({
					height: slideHeight1
				}, "normal");
				$(this).html('显示筛选<i class="fa fa-chevron-down" aria-hidden="true"></i>');
			}
			return false;
		});
	}


         $(".position-form").hide();
        		$("#items").click(function(){
        			$(".list-form").show();
        			$(".position-form").hide();
        		});
        		$("#position-plane").click(function(){
        			$(".list-form").hide();
        			$(".position-form").show();
        		});
        		$("#historyInventory").click(function(){
        			$(".list-form").show();
        			$(".position-form").hide()
        		})

     //	导出模态框
    $(".export").click(function(){ 
   var id=$(this).parent().parent().parent().attr("id");
    	if($("#"+ id +"  input[type=checkbox]:checked").length > 0) {
			$("#exportModal").modal();
			$("#nullModal").modal("hide");
		} else {
			$("#nullModal").modal();
			return;
		}
    })
    
})