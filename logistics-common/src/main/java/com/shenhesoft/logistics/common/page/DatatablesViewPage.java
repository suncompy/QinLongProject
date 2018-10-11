package com.shenhesoft.logistics.common.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页数据.
 * 
 * @author LiuJiefeng
 */
public class DatatablesViewPage<T> {

  private List<T> data;
  private long recordsTotal;
  private long recordsFiltered;

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public long getRecordsTotal() {
    return recordsTotal;
  }

  public void setRecordsTotal(long recordsTotal) {
    this.recordsTotal = recordsTotal;
  }

  public long getRecordsFiltered() {
    return recordsFiltered;
  }

  public void setRecordsFiltered(long recordsFiltered) {
    this.recordsFiltered = recordsFiltered;
  }

  public DatatablesViewPage() {

  }

  /**
   * 逻辑分页.
   * 
   * @param pageData 分页数据
   */
  public DatatablesViewPage(List<T> pageData) {
    super();
    if (pageData instanceof Page) {

      PageInfo<T> pageInfo = new PageInfo<>(pageData);

      this.data = pageInfo.getList();
      this.recordsTotal = pageInfo.getTotal();
      this.recordsFiltered = pageInfo.getTotal();
    }else{
    		PageInfo<T> pageInfo = new PageInfo<>(pageData);
    	 this.data = pageInfo.getList();
    }
  }

  /**
   * 逻辑分页.
   */
  public DatatablesViewPage(List<T> list, int total) {
    this.data = list;
    this.recordsFiltered = total;
    this.recordsTotal = total;

  }
}
