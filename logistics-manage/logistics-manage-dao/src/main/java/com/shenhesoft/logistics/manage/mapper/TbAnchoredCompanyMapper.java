package com.shenhesoft.logistics.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.manage.pojo.anchoredCompany.TbAnchoredCompany;
import com.shenhesoft.logistics.manage.pojo.anchoredCompany.TbAnchoredCompanyExample;

public interface TbAnchoredCompanyMapper {
    int countByExample(TbAnchoredCompanyExample example);

    int deleteByExample(TbAnchoredCompanyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbAnchoredCompany record);

    int insertSelective(TbAnchoredCompany record);

    List<TbAnchoredCompany> selectByExample(TbAnchoredCompanyExample example);

    TbAnchoredCompany selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbAnchoredCompany record, @Param("example") TbAnchoredCompanyExample example);

    int updateByExample(@Param("record") TbAnchoredCompany record, @Param("example") TbAnchoredCompanyExample example);

    int updateByPrimaryKeySelective(TbAnchoredCompany record);

    int updateByPrimaryKey(TbAnchoredCompany record);

    /**
     * 通过用户id 查询用户挂靠的公司列表
     * @author dusd
     * @date 2018年1月11日
     * @return
     */
	List<TbAnchoredCompany> listTbAnchoredCompanyByUserId(@Param("user_id")Integer user_id);
}