package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.pojo.branchgroup.TbUserBranchGroup;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbUserBranchGroupExample;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserBranchGroupMapper {
    int countByExample(TbUserBranchGroupExample example);

    int deleteByExample(TbUserBranchGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbUserBranchGroup record);

    int insertSelective(TbUserBranchGroup record);

    List<TbUserBranchGroup> selectByExample(TbUserBranchGroupExample example);

    TbUserBranchGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbUserBranchGroup record, @Param("example") TbUserBranchGroupExample example);

    int updateByExample(@Param("record") TbUserBranchGroup record, @Param("example") TbUserBranchGroupExample example);

    int updateByPrimaryKeySelective(TbUserBranchGroup record);

    int updateByPrimaryKey(TbUserBranchGroup record);
    
    List<TbSystemUser> selectResponsiblerByBranchId(Integer id);
}