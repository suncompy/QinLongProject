package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.pojo.customer.TbCustomerBranchGroup;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomerBranchGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCustomerBranchGroupMapper {
    int countByExample(TbCustomerBranchGroupExample example);

    int deleteByExample(TbCustomerBranchGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCustomerBranchGroup record);

    int insertSelective(TbCustomerBranchGroup record);

    List<TbCustomerBranchGroup> selectByExample(TbCustomerBranchGroupExample example);

    TbCustomerBranchGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCustomerBranchGroup record, @Param("example") TbCustomerBranchGroupExample example);

    int updateByExample(@Param("record") TbCustomerBranchGroup record, @Param("example") TbCustomerBranchGroupExample example);

    int updateByPrimaryKeySelective(TbCustomerBranchGroup record);

    int updateByPrimaryKey(TbCustomerBranchGroup record);
}