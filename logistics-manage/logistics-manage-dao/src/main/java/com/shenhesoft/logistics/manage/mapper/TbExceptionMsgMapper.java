package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.pojo.exception.TbExceptionMsg;
import com.shenhesoft.logistics.manage.pojo.exception.TbExceptionMsgExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbExceptionMsgMapper {
    int countByExample(TbExceptionMsgExample example);

    int deleteByExample(TbExceptionMsgExample example);

    int deleteByPrimaryKey(Integer exceptionId);

    int insert(TbExceptionMsg record);

    int insertSelective(TbExceptionMsg record);

    List<TbExceptionMsg> selectByExample(TbExceptionMsgExample example);

    TbExceptionMsg selectByPrimaryKey(Integer exceptionId);

    int updateByExampleSelective(@Param("record") TbExceptionMsg record, @Param("example") TbExceptionMsgExample example);

    int updateByExample(@Param("record") TbExceptionMsg record, @Param("example") TbExceptionMsgExample example);

    int updateByPrimaryKeySelective(TbExceptionMsg record);

    int updateByPrimaryKey(TbExceptionMsg record);

    /**
     * 通过用户id查询异常信息
     * @author dusd
     * @date 2018年1月6日
     * @param tbExceptionMsgExample
     * @return
     */
	List<Map<String, Object>> listShortExceptionInfoByUserId(Map<String, Object> paramMap);
}