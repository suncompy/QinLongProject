package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.pojo.tbSmsSend.TbSmsSend;
import com.shenhesoft.logistics.manage.pojo.tbSmsSend.TbSmsSendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSmsSendMapper {
    int countByExample(TbSmsSendExample example);

    int deleteByExample(TbSmsSendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSmsSend record);

    int insertSelective(TbSmsSend record);

    List<TbSmsSend> selectByExample(TbSmsSendExample example);

    TbSmsSend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSmsSend record, @Param("example") TbSmsSendExample example);

    int updateByExample(@Param("record") TbSmsSend record, @Param("example") TbSmsSendExample example);

    int updateByPrimaryKeySelective(TbSmsSend record);

    int updateByPrimaryKey(TbSmsSend record);
}