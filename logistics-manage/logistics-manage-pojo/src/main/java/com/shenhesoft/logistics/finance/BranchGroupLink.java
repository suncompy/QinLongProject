// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft; All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.finance;

/**
 * @description
 *
 *
 * @date 2018年4月3日
 */
public class BranchGroupLink {
  private String id;
  private String rowId;
  private String tabName;
  private String tabComment;
  private String sysOrgCode;
  
  /**
   * 
   */
  public BranchGroupLink() {
    super();
  }
  
  /**
   * @param rowId各表主键名
   * @param tabComment表注释
   */
  public BranchGroupLink(String rowId,String tabComment) {
    super();
    this.rowId = rowId;
    this.tabComment = tabComment;
  }

  /**
   * @param id
   * @param rowId
   * @param tabName
   * @param tabComment
   * @param sysOrgCode
   */
  public BranchGroupLink(String id, String rowId, String tabName, String tabComment,
      String sysOrgCode) {
    super();
    this.id = id;
    this.rowId = rowId;
    this.tabName = tabName;
    this.tabComment = tabComment;
    this.sysOrgCode = sysOrgCode;
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getRowId() {
    return rowId;
  }
  public void setRowId(String rowId) {
    this.rowId = rowId;
  }
  public String getTabName() {
    return tabName;
  }
  public void setTabName(String tabName) {
    this.tabName = tabName;
  }
  public String getTabComment() {
    return tabComment;
  }
  public void setTabComment(String tabComment) {
    this.tabComment = tabComment;
  }
  public String getSysOrgCode() {
    return sysOrgCode;
  }
  public void setSysOrgCode(String sysOrgCode) {
    this.sysOrgCode = sysOrgCode;
  }
  
}
