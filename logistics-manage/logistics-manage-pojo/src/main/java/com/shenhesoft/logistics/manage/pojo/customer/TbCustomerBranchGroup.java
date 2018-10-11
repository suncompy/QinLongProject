package com.shenhesoft.logistics.manage.pojo.customer;

public class TbCustomerBranchGroup {
    /** */
    private Integer id;

    /** 分支机构id*/
    private Integer branchGroupId;

    /** 客户id*/
    private Integer customerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBranchGroupId() {
        return branchGroupId;
    }

    public void setBranchGroupId(Integer branchGroupId) {
        this.branchGroupId = branchGroupId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}