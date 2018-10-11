/*搜索*/
function search(path){
		$.ajax({
        	type:'post',      
       		url:$.getUrl(path),  
	       	data:{page:1,search:JSON.stringify($('#searchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			htmlTable(data.data.rows);
        			$("#page").paging({
						pageNo:1, 
						totalPage: data.data.totalPage,
						totalSize: data.data.limit,
						callback: function(num) {
							searchByPage(num,path);
						}
					})	
        		}
        	}	
		})	
	}

/*分页搜索*/
function searchByPage(num,path){
		$.ajax({  
        	type:'post',      
       		url:$.getUrl(path),  
	       	data:{page:num,search:JSON.stringify($('#searchForm').serializeJSON())},
	        cache:false,
	        dataType:'json',
        	success:function(data){
        		if(data.status==200){
        			htmlTable(data.data.rows);
        		}
    		}
		})
	}