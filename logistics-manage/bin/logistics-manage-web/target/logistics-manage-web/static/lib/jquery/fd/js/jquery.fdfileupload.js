/**
 * 文件上传
 * haichao.sang
 */
;
(function($, window, document, undefined) {
    'use strict';
    $(function() {
    	
    	//默认样式，如果需特定样式 可以在ops中指定
    	var defaultMdlTmpl = '<div class="modal fade" id="mdlFileupload" role="dialog" data-keyboard="false" data-backdrop="static" aria-hidden="true">'
    		+'   <div class="modal-dialog">'
    		+'     <div class="modal-content">'
    		+'       <div class="modal-body" style="padding:0px;min-height:400px;">'
    		+'         <div class="panel panel-default" >'
    		+'           <div class="panel-heading">文件上传<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button></div>'
    		+'           <div class="panel-body">{btnTmpl}</div>'
    		+'   {tblTmpl}'
    		+'   </div></div></div></div></div>';
    	
    	var defaultBtnTmpl = '<span class="btn btn-success fileinput-button btn-xs">'
    		+'              <span>添加文件 </span>'
    		+'               <input id="fileupload_{uuid}" type="file" name="files[]" multiple>'
    		+'             </span>'
    		+'             <button class="btn btn-xs btn-primary" id="btnFileupload_{uuid}">开始上传</button>'
    		+'             <div id="acceptFileTypes_{uuid}" class="callout callout-warning" style="display:inline;margin-bottom:0px;padding:2px;float:right"><strong><i class="fa fa-warning"></i></strong>&nbsp;&nbsp;<small>类型限制:<span name="acceptFileTypes"></span>, 大小限制:<span name="maxFileSize"></span></span>, 文件数量限制:<span name="maxFileNum"></span></small></div>';
    		
    	var defaultTblTmpl =	'<table id="tblFileupload_{uuid}" class="table table-striped" style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all">'
    		+'             <thead>'
    		+'               <tr>'
    		+'                 <th>文件名</th>'
    		+'                 <th width="100px">文件大小</th>'
    		+'                 <th width="150px">进度</th>'
    		+'                 <th width="80px"></th>'
    		+'               </tr>'
    		+'             </thead>'
    		+'             <tbody>'
    		+'             </tbody>'
    		+'           </table>';
    	
//    	// 弹出框形式
//    	var mdlFileupload = mdlTmpl.replace(/{btnTmpl}/, btnTmpl).replace(/{tblTmpl}/, tblTmpl);
//    	// 渲染到容器内
//    	var tblFileupload = btnTmpl + tblTmpl;
    	
    	var FdFileupload = function(options){
        	this.options = options;
        	this.uuid = new Date().getTime();
       	};
       	
       	// 上传文件个数
       	var FILE_COUNT = 0;
       	// 系统内文件个数
       	var SYS_FILE_COUNT = 0;
        
       	FdFileupload.prototype = {
       		// 文件上传
   			submit: function(opts){
		    	FILE_COUNT = $("#tblFileupload_"+ this.uuid+" .fd-attachment-upload").length;
		    	$("#tblFileupload_"+ this.uuid +" .fd-attachment-upload").each(function(){
		    		var $this = $(this), data = $this.data();
		    		if(opts && opts.url){
		    			data.url = opts.url;
		    		}
		    		data.submit().always(function (){
		    			if(!opts.delEnable){
		    				$this.remove();
		    			}
		            });
		    	});
		    	
		    	SYS_FILE_COUNT = $("#tblFileupload_"+ this.uuid+" .fd-attachment-sysUpload").length;
		    	$("#tblFileupload_"+ this.uuid +" .fd-attachment-sysUpload").each(function(){
		    		var id=this.children[0].getAttribute("fileid");
		    		$.fd.ajax({
	                    type: "put",
	                    data:data, 
	                    url: opts.url+"/sysfile/"+id,
			    	});
		    	});
		    	
		    	//如果上传文件为空的时候且设置了完成后的回调函数，则调用回调函数继续后续处理
		    	if(FILE_COUNT == 0 && SYS_FILE_COUNT == 0 && this.options.complete){
		    		this.options.complete();
		    	}
   			},
   			reset: function(){
   				var opts = this.options;
   				if(!opts.container){
   					$("#mdlFileupload").remove;
   				}else{
   					$("#"+opts.container +" .fileupload").empty();
   				}
   			},
   			files: function(owner){
   				var tableId = "#tblFileupload_" + this.uuid;
   				var opts = this.options;
   				$.fd.ajax({
                    type: "get",
                    url: "api/owners/" + owner + "/attachments",
                    success:function(data){
                    	if(opts.successFun){
                    		opts.successFun(data,tableId);
                    	}else{
                    		var $table = $(tableId);
                        	$.each(data.data.data,function(i,d){
                        		if(opts.fileDelete!=false){
                        			$table.append(
            	                    	$('<tr/>')
            		   		    		.append($('<td/>').text(d["attachmentName"]))
            		   		    		.append($('<td/>').text(d["attachmentSize"]))
            		   		    		.append($('<td/>').html('<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>'))
            		   		    		.append($('<td/>').html('<i class="fa fa-trash-o" fileId="'+ d["id"] +'">'))
                                	);
                        		}else{
                        			$table.append(
            	                    	$('<tr/>')
            		   		    		.append($('<td/>').text(d["attachmentName"]))
            		   		    		.append($('<td/>').text(d["attachmentSize"]))
            		   		    	);
                        		}
                        		
                        	});
                        	
                        	// 附件删除
    						$(tableId+' tbody').on('click', 'i.fa-trash-o', function (e){
    							e.preventDefault();
    							if($(this).attr("fileType")){
    								return;
    							}
    							var fileId = $(this).attr("fileId");
    							var $this = this;
    							$.confirm({
    								content : "请确认删除文件",
    								confirm : function() {
    									var ajaxOpts = {
    										showMsg: true,
    										type: "delete",
    										url: "api/attachments/"+ fileId,
    										success:function(e){
    											$this.closest('tr').remove();
    											if(opts.delCallBack){
    												opts.delCallBack();
    											}
    										}
    									};					
    									$.fd.ajax(ajaxOpts);
    								}
    							}); 
    						});
                    	}
                    }
                });
   			},
   			//添加系统内附件
   			addSystemFiles:function(files){
   				var tableId = "#tblFileupload_" + this.uuid;
   				var $table = $(tableId);
            	$.each(files,function(index,obj){
            		$table.append(
	                    	$('<tr/>')
   		    		.append($('<td/>').text(obj.filename))
   		    		.append($('<td/>').text(obj.size))
   		    		.append($('<td/>').html('系统内文件'))
   		    		.append($('<span/>').addClass('fd-attachment-sysUpload').html('<i class="fa fa-trash-o" fileType="inner" fileId="'+ obj.id +'" fileName="'+ obj.filename +'">')));
            	});

            	// 附件删除
				$(tableId+' tbody').on('click', 'i.fa-trash-o', function (e){
					e.preventDefault();
					$(this).parent().parent("tr").remove();
				});
   			},
   			init: function(){
   			    // 文件上传有两种展示形式 1：弹出框 2：渲染到指定的容器内。 若指定了容器，则采用2，否则采用1.
   				var opts = this.options, uuid = this.uuid;
   				var mdlTmpl;
   				var btnTmpl;
   				var tblTmpl;
   				//如果不指定样式，则按照默认样式
   				if(opts.mdlTmpl||opts.mdlTmpl==""){
   					mdlTmpl=opts.mdlTmpl;
   				}else{
   					mdlTmpl=defaultMdlTmpl;
   				}
   				if(opts.btnTmpl||opts.btnTmpl==""){
   					btnTmpl=opts.btnTmpl;
   				}else{
   					btnTmpl=defaultBtnTmpl;
   				}
   				if(opts.tblTmpl||opts.tblTmpl==""){
   					tblTmpl=opts.tblTmpl;
   				}else{
   					tblTmpl=defaultTblTmpl;
   				}
   				
   				
   				// 文件删除按钮
	   			var btnDelFile = $('<span/>').addClass('fd-attachment-upload').html('<i class="fa fa-trash-o">').on('click', function () {
	   		    	$(this).parent().parent("tr").remove();
	   		    });
   		    
	   		    // 添加文件记录到一览
	   		    var addFileRow = function(data){
	   		    	if(opts.url){
	   		    		data.url = opts.url;
	   		    	}
	   		    	$.each(data.files, function (index, file) {
	   		    		data.context = $('<tr/>')
	   		    		.append($('<td/>').text(file.name))
	   		    		.append($('<td/>').text($.fd.file.formatFileSize(file.size)))
	   		    		.append($('<td/>').html('<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>'));
	   		    		
	   		    		if(opts.formData){
	   		    			data.formData = opts.formData;
	   		    		}
	   		    		data.context.append($('<td/>').html(btnDelFile.clone(true).data(data)));
	   		    		
	   		    		$('#tblFileupload_'+ uuid+' tbody').append(data.context);
	   		        });
	   		    };
   				
   				// 获取支持的文件类型
   				var getAcceptFileTypes = function(acceptFileTypes){
   					var $acceptFileTypes = $("#acceptFileTypes_"+uuid+" [name=acceptFileTypes]");
   			    	if(!acceptFileTypes){
   			    		$acceptFileTypes.text("无");
   			    		return undefined;
   			    	}
   			    	var types = acceptFileTypes.join("|");
   			    	$acceptFileTypes.text(types);
   			    	return new RegExp('(\.|\/)('+ types +')$','i');
   			    };
   			    
   				// 获取支持的文件大小   			    
   			    var getMaxFileSize = function(fileSize){
   			    	var $acceptFileSize = $("#acceptFileTypes_"+uuid+" [name=maxFileSize]");
   			    	if(!fileSize){
   			    		$acceptFileSize.text("无");
   			    		return undefined;
   			    	}
   			    	$acceptFileSize.text(fileSize+"M");
   			    	return fileSize * 1024 * 1024;
   			    };
   			    
   			    // 获取支持的文件数量  			    
   			    var getMaxFileNum = function(fileNum){
   			    	var $acceptFileNum = $("#acceptFileTypes_"+uuid+" [name=maxFileNum]");
   			    	if(!fileNum){
   			    		$acceptFileNum.text("无");
   			    	}
   			    	$acceptFileNum.text(fileNum);
   			    };
   			    
   			    
   			    // 未指定容器，采用弹出框的方式
   				if(!opts.container){
   					
   					var mdlFileupload = mdlTmpl.replace(/{btnTmpl}/, btnTmpl).replace(/{tblTmpl}/, tblTmpl);
   					
   					if($("#mdlFileupload").length > 0){
   						$("#mdlFileupload").remove();
   					}
   					
   					$("body").append(mdlFileupload.replace(/{uuid}/g, uuid));
   					
   				    // 文件上传按钮
   				    $("#btnFileupload_"+uuid).click(function(){
   				    	if(opts.beforeUpload){
   				    		var result = opts.beforeUpload();
   				    		if(!result){
   				    			return;
   				    		}
   				    	}
   				    	FILE_COUNT = $("#tblFileupload_"+ uuid+" .fd-attachment-upload").length;
   				    	$("#tblFileupload_"+ uuid +" .fd-attachment-upload").each(function(){
   				    		var $this = $(this);
   				    		$this.data().submit().always(function () {
   				                 $this.remove();
   				            });
   				    	});
   				    });
   					$("#mdlFileupload").modal("show");
   				// 渲染到容器内	
   				}else if(!opts.btnTitle){
   					var tblFileupload = btnTmpl + tblTmpl;
   					
   					var $container = $("#"+opts.container);
   					$container.empty();
   					$container.append(tblFileupload.replace(/{uuid}/g, uuid));
   					$("#btnFileupload_"+uuid).hide();
   				}else{
   					var btnTmpls = '<span class="btn btn-primary fileinput-button btn-xs">'
   			    		           +'<span>'+opts.btnTitle+' </span>'
   			    		               +'<input id="fileupload_{uuid}" type="file" name="files[]" multiple>'
			   			    		+'</span>'
			   			    		+'<span style="margin-left: 10px;color: red;">单个文件最大不能超过5M</span>';
   			    	var tblTmpls =	'<div id="tblFileupload_{uuid}" class="fileupload" style="padding:1px 0;"></div>';
   					var tblFileuploads = btnTmpls + tblTmpls;
   					var $container = $("#"+opts.container);
   					$container.empty();
   					$container.append(tblFileuploads.replace(/{uuid}/g, uuid));
   					$("#btnFileupload_"+uuid).hide();
   				}
   				
   				var $fileupload =$('#fileupload_' + uuid);
   				
   				// 创建文件上传控件
   				$fileupload.fileupload({
   			        dataType: 'json',
   			        autoUpload: false,
   			        acceptFileTypes: getAcceptFileTypes(opts.acceptFileTypes),
   			        maxFileSize: getMaxFileSize(opts.maxFileSize),
   			        maxFileNum: getMaxFileNum(opts.maxFileNum)
   			    // 添加上传文件
   				}).on("fileuploadadd",function (e, data){
   					if(opts.addFileRow){
   						opts["addFileRow"](data);
   					}else{
   						if(opts.maxFileNum){
   							var fileCount=$('#tblFileupload_'+ uuid+' tbody').children().length;
   							if(fileCount>=opts.maxFileNum){
   								$.fd.msg.notice("文件数量达到最大上限");
   								return false;
   							}
   						}
   						addFileRow(data);
   					}
   	   			    
   	   			// 更新各文件上传进度
   	   			}).on("fileuploadprogress", function (e, data){
   					var progress = parseInt(data.loaded / data.total * 100, 10);
   			    	if (data.context) {
   		                data.context.each(function () {
   		                    $(this).find('.progress').attr('aria-valuenow', progress).children().first().css('width',progress + '%').text(progress + '%');
   		                });
   		            }
   	   	   	   // 处理从服务器端返回的文件上传结果
   			   }).on("fileuploadalways",function (e, data) {
  					if(opts.fileuploadalways){
  						opts["fileuploadalways"](e,data);
  					}else{
  					    // 更新各文件上传进度
  	   					var respJson = data.response().jqXHR.responseJSON;
  	   					// 服务器返回处理结果异常时
  	   					if (respJson.meta.code != 200){				
  	   						$(data.context.find("td")[3]).append('<span data-toggle="tooltip" data-original-title="'+ 
  	   								respJson.meta.message +'" style="color:red;margin-left:15px"><i class="fa fa-info-circle"></span>');
  	   						$(data.context).css("color","red");
  	   					}
  	   					// 文件全部上传时
  	   					FILE_COUNT = FILE_COUNT - 1;
  	   					if(FILE_COUNT == 0){
  	   						opts.success && opts.success();
  	   					}
  	   					opts.complete && opts.complete(respJson);
  					}
  	   			    
  	   			
    	   		// 对上传的文件类型和大小进行检查
   				}).on("fileuploadprocessalways", function (e, data){
   					var file = data.files[data.index];
   					if (file.error) {
   						$(data.context).css("color","red");
   						data.context.find(".fd-attachment-upload").removeData().removeClass("fd-attachment-upload");
   						var msg ="";
   						if(file.error=='File type not allowed'){
   							msg += "类型错误  ";
   						}else if(file.error=='File is too large'){
   							msg += "大小超限";
   						} 
   						if(msg){
   							$(data.context.find("td")[3]).append('<span data-toggle="tooltip" data-original-title="'+ msg +'" style="color:red;margin-left:15px"><i class="fa fa-info-circle"></span>');
   						}
   			        }
   				});
   				if(opts.initCallback){
   					opts.initCallback();
   				}
   			}
       };
       	
       	$.fd.FdFileupload = function(options) {
            var fileupload = new FdFileupload(options);
            fileupload.init();
            return fileupload;
        };
    });

})(jQuery, window, document);