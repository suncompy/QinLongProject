function listSearchParam(){
	/**
	 * 获取网点分支
	 * */
	$.ajax({
    	type:'post',      
   		url:$.getUrl('/humanOrganization/listDotBranchByUserId.do'),  
        cache:true,
        dataType:'json',
    	success:function(data){
    		if(data.status==200){
    			$('#searchForm select[name=branchGroupId]').html("<option></option>");
    			$('#searchForm select[name=branchGroupName]').html("<option></option>");
    			
    			$.each(data.data, function(index,branchGroup) {
    				$('#searchForm select[name=branchGroupId]').append(
    				"<option value='"+branchGroup.id+"'>"+branchGroup.name+"</option>"
    				);
    				$('#searchForm select[name=branchGroupName]').append(
    				"<option value='"+branchGroup.name+"'>"+branchGroup.name+"</option>"
    				);
    			});
    		}
    	}	
	})	
	
	/**
	 * 获取货物品名
	 * */
	$.ajax({
    	type:'post',      
   		url:$.getUrl('/cargo/listCargo.do'),  
        cache:true,
        dataType:'json',
    	success:function(data){
    		if(data.status==200){
    			$('#searchForm select[name=cargoId]').html("<option></option>");
    			$('#searchForm select[name=cargoName]').html("<option></option>");
    			$.each(data.data, function(index,cargo) {
    				$('#searchForm select[name=cargoId]').append(
    				"<option value='"+cargo.id+"'>"+cargo.cargoName+"</option>"
    				);
    				$('#searchForm select[name=cargoName]').append(
    				"<option value='"+cargo.cargoName+"'>"+cargo.cargoName+"</option>"
    				);
    			});
    		}
    	}	
	})	
	
}
