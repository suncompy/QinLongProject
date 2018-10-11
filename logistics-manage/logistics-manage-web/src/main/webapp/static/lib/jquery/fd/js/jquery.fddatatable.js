/**
 * Datatables扩展
 * LiuJiefeng
 */
;
(function($, window, document, undefined) {
    'use strict';
    $(function() {
    	
    var defaults = {
		checkbox : true,
		rownumbers : true,
		paginate : true,
		lengthChange : false,
		filter : false,
		serverSide : true,
		autoWidth: false,
		scrollCollapse : false, 
		displayLength : 30,
		dataSrc : "data",
		fixedColumns: {
	        heightMatch: 'none'
	    }
	};
    
    var FdDataTable = function(ele, options){
    	this.$element = ele;
    	this.options = options;
   	};
   	
   	FdDataTable.prototype = {
		init: function(){
			var $self = this.$element, opts =this.options, settings = {}, id = "#" + $self.attr("id"), extCols = [];
			this.settings = settings; this.id = id;
			// 配置
			$.extend(settings, defaults, opts);
			
			// 行号
			if (settings.rownumbers) {
				if(!settings.noaddRownumbers){
					$(id + " thead tr").prepend('<th style="width: 30px;"></th>');
				}
				extCols.push({
					title : "序号",
					data : function(row, type, set, meta) {
						var c = meta.settings._iDisplayStart + meta.row + 1;
						return c;
					}
				});
			}
			
			// 复选框
			if (settings.checkbox) {
				if(!settings.noaddCheckbox){
					$(id + " thead tr").prepend('<th style="width: 18px;"></th>');
				}
				extCols.splice(0,0,{
					'title' : '<label class="demo--label"> <input class="demo--checkbox" name="chkSelectAll" type="checkbox" ><span class="demo--checkboxInput"></span> </label>',
					
					'searchable' : false,
					'orderable' : false,
					'className' : 'dt-body-center',
					'render' : function(data, type, full, meta) {
						return '<label class="demo--label"> <input class="demo--checkbox"  type="checkbox" ><span class="demo--checkboxInput"></span> </label>';
					}
				});
			}
			
			// 追加添加的 行号, 复选框到列定义中
			settings.columns = extCols.concat(settings.columns);
			// 列配置的偏移
			var offset = extCols.length;
			// 列配置的targets添加偏移
			settings.columnDefs = settings.columnDefs || [];
			$.each(settings.columnDefs, function(index, data) {
				if($.isArray(data.targets)){
					$.each(data.targets, function(i, d) {
						data.targets[i] = d + offset;
					});
				}else{
					data.targets += offset;
				}
			});
			
			// 设置默认值,避免弹出警告
			settings.columnDefs.push({ sDefaultContent: '', aTargets: [ '_all' ] });
			
			var datatable = $self.DataTable(settings);
			this.datatable = datatable;
			
			// 含复选框时
			if (settings.checkbox) {
				// 翻页时，取消头部的选中框
				$(id).on( 'page.dt',
					function() {
						$(id + '_wrapper thead input[name="chkSelectAll"]').prop('checked', false);
						$(id + " tbody input[type=checkbox]").prop('checked',false);
						$(id + " tbody tr").removeClass("selected");
				});
				// 全选/取消全选
				$(id + '_wrapper thead input[name="chkSelectAll"]').click(
					function(e) {
						e.stopPropagation();
						if (this.checked) {
							$(id + " tbody input[type=checkbox]").prop('checked', true);
							$(id + " tbody tr").addClass("selected");
							settings.rowSelect && settings.rowSelect();
						} else {
							$(id + " tbody input[type=checkbox]").prop('checked', false);
							$(id + " tbody tr").removeClass("selected");
							settings.rowSelect && settings.rowSelect();
						}
				});
			}
			
			// 单击选中当前行
			$(id + " tbody").on( "click", "tr",
				function(e) {
					var $this = $(this);
					// 如果点击的是checkbox, 则将当前行追加到选中行
					if (settings.checkbox && e.target.tagName == "INPUT" && e.target.type == "checkbox") {
	
						if ($this.find('input[type="checkbox"]')[0].checked) {
							$this.addClass("selected");
							$this.find('input[type="checkbox"]').prop('checked', true);
						} else {
							$this.removeClass("selected");
							$this.find('input[type="checkbox"]').prop('checked', false);
						}
						settings.rowSelect && settings.rowSelect();
	
					// 否则,取消其他选中行,仅选中被点击行
					//} else if (e.target.tagName == "TD") {
					  } else {
						$(id + " tbody input[type=checkbox]").prop('checked', false);
						$(id + " tbody tr").removeClass("selected");
						$this.addClass("selected");
						
						$this.find('input[type="checkbox"]').prop('checked',true);
	
						settings.rowSelect && settings.rowSelect(datatable.row(".selected").data());
					}
			  });
		},
		// 取得选中的单行数据， 如果选中多行，则只返回undefined，如果没有选中行，则返回 null，否则返回记录。
		getSelected: function(key){
			var datatable = this.datatable,
			datas = datatable.rows(".selected").data();
			
			if (datas.length != 1) {
				return;
			}
			if(key){
				return datas[0][key];
			}
			return datas[0];
		},
		// 取得选中的单行数据， 如果选中多行，则只返回undefined，如果没有选中行，则返回 null，否则返回记录。
		getSelecteds: function(){
			var datas = this.datatable.rows(".selected").data();
			var dataArr = [];
			for(var i=0;i<datas.length;i++){
				dataArr.push(datas[i]);
			}
			return dataArr;
		},
		// 取得所有选中行数据，返回元素记录的数组数据
		getSelections: function(key){
			var datas  = this.datatable.rows(".selected").data();
			if(!key) {
				return datas;
			}
			
			var selectedIds = [];
			$.each(datas, function(i,d){
				selectedIds.push(d[key]);
			});
			return selectedIds;
		},
		// 根据指定的Id，选中匹配行
		autoSelect: function(target,key){
			key = key || "id";
			var id = this.id, datas = this.datatable.data();
			$.each(datas, function(i, d){
				if(target == d[key]){
					$(id + " tbody tr:nth-child("+(i+1)+") > td:first-child input:checkbox ").prop('checked', true);
					$(id + " tbody tr:nth-child("+(i+1)+")").addClass("selected");
					return false;
				}
			});
		},
		// 获取当前表中的全部数据
		getAllRowData: function(key){
			var datas = this.datatable.data();
			var data = [], datatable = this.datatable;
			$.each(datas, function(i, d){
				data.push(d[key]);
			});
			return data;
		},
		// 反选
		unSelect: function(){
			$(this.id).find('tr > td:first-child input:checkbox').each(
				function(i, d) {
					if(this.checked){
						$(this).prop('checked', false);
						$(this).parent().parent().removeClass("selected");
					}else{
						$(this).prop('checked', true);
						$(this).parent().parent().addClass("selected");
					}
			});
		},
		reload: function(){
			this.datatable.reload();
		},
		draw: function(flag){
			this.datatable.draw(flag);
		},
		row:function(target){
			return this.datatable.row(target);
		},
		cell:function(target){
			return this.datatable.cell(target);
		},
		pageInfo:function(){
			return this.datatable.pageInfo();
		},
		data:function(){
			return this.datatable.data();
		},
		destroy:function(){
			this.datatable.destroy();
		},
		clear:function(){
			return this.datatable.clear();
		},
		colHide:function(i){
		    this.datatable.setColumnVis(i,false);
		}
   	};
   	
   	$.fn.FdDataTable = function(options) {
        var dataTable = new FdDataTable(this, options);
        dataTable.init();
        return dataTable;
    };
    
 // 语言
	$.extend(true, $.fn.dataTable.defaults, {
		language: {
			"sProcessing": "处理中...",
			"sLengthMenu": "显示 _MENU_ 项结果",
			"sZeroRecords": "没有匹配结果",
			"sInfo": "第 _START_ 至 _END_ 项，共 _TOTAL_ 项",
			"sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
			"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix": "",
			"sSearch": "搜索:",
			"sUrl": "",
			"sEmptyTable": "表中数据为空",
			"sLoadingRecords": "载入中...",
			"sInfoThousands": ",",
			"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "上页",
				"sNext": "下页",
				"sLast": "末页"
			},
			"iDisplayLength": 25,
			"oAria": {
				"sSortAscending": ": 以升序排列此列",
				"sSortDescending": ": 以降序排列此列"
			}
		}
	});

	// 配置
	$.extend($.fn.dataTable.defaults, {
		searching: false,
		ordering: false
	});

  });
})(jQuery, window, document);