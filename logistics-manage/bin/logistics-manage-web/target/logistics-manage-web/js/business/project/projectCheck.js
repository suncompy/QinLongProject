$(function() {
	//选项切换清空复选框
	$(".tabbable .nav li").click(function() {
		$("input[type=checkbox]").prop("checked", false);
	});

	//项目明细查看详情
	$("body").on("click", "#panel1 tbody tr td:not(:nth-child(1))", function() {

			$("#detailModal").modal();
			$(this).parent().toggleClass("bgclass").siblings("#panel1 tbody tr").removeClass("bgclass");

		}

	);

	//项目核算查看详情
	$("body").on("click", "#panel2 tbody tr td:not(:nth-child(1))", function() {

			$("#accountingModal").modal();
			$(this).parent().toggleClass("bgclass").siblings("#panel2 tbody tr").removeClass("bgclass");

		}

	);

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