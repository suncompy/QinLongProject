package com.shenhesoft.logistics.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.manage.pojo.menu.TbMenu;
import com.shenhesoft.logistics.manage.pojo.menu.TbMenuExample;

public interface TbMenuMapper {
    int countByExample(TbMenuExample example);

    int deleteByExample(TbMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbMenu record);

    int insertSelective(TbMenu record);

    List<TbMenu> selectByExample(TbMenuExample example);

    TbMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbMenu record, @Param("example") TbMenuExample example);

    int updateByExample(@Param("record") TbMenu record, @Param("example") TbMenuExample example);

    int updateByPrimaryKeySelective(TbMenu record);

    int updateByPrimaryKey(TbMenu record);
    
    List<Byte> selectFirstMenu(Integer id);

    /**
	 * 获取用户所有的权限list
	 * @author dusd
	 * @date 2017年12月18日
	 * @param userId 用户id
	 * @return
	 */
	List<TbMenu> listMenuDetailByUserId(Integer userId);
}