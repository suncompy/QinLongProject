package com.shenhesoft.logistics.manage.pojo.branchgroup;

public class TbBranchGroupTrainStation {
    /** id*/
    private Integer id;

    /** 火车站点id*/
    private Integer trainStationId;

    /** 分支机构id*/
    private Integer branchGroupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainStationId() {
        return trainStationId;
    }

    public void setTrainStationId(Integer trainStationId) {
        this.trainStationId = trainStationId;
    }

    public Integer getBranchGroupId() {
        return branchGroupId;
    }

    public void setBranchGroupId(Integer branchGroupId) {
        this.branchGroupId = branchGroupId;
    }
}