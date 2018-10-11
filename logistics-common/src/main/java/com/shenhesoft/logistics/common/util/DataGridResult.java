package com.shenhesoft.logistics.common.util;

import java.io.Serializable;
import java.util.List;

/**
 * @description 分页返回数据
 * @author shilvfei
 * @date 2017年12月5日
 * @param 
 * @return
*/
public class DataGridResult implements Serializable{
	private long totalCount;//总记录数
	private List rows;
	private long limit;//每页显示记录数
	private long totalPage;//总页数
	
	public DataGridResult(long totalCount, List rows, long limit) {
		this.totalCount = totalCount;
		this.rows = rows;
		this.limit = limit;
		if(Math.floor((double)totalCount/(double)limit)<1){
			totalPage=1;
        }else{
        	totalPage=(long) Math.ceil((double)totalCount/(double)limit);
        }
		//this.totalPage=totalPage;
	}

	public DataGridResult() {
	}
	
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public long getLimit() {
		return limit;
	}
	public void setLimit(long limit) {
		this.limit = limit;
	}
	public long getTotalPage() {
		
		if(Math.floor((double)totalCount/(double)limit)<1){
			totalPage=1;
        }else{
        	totalPage=(long) Math.ceil((double)totalCount/(double)limit);
        }
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	
}
