package com.shenhesoft.logistics.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BasicDataMapper {

	/**
	 * 获取test类型
	 * 
	 * @param
	 *
	 * @return
	 */
	List<Map<String, Object>> getTestType(@Param(value = "text") String text);

	/**
	 * 获取维度表中，下拉框数据
	 * 
	 * @return
	 */
	List<Map<String, String>> getDims(Map<String,Object> map);
	/**
     * 获取火车车型
	 * @param map 
     * 
     * @param
     *
     * @return
     */
	List<Map<String, Object>> getTrainType(@Param(value = "text") String text,@Param(value = "map") Map<String, Object> map);
	/**
     * 获取项目
     * 
     * @param
     *
     * @return
     */
	List<Map<String, Object>> getProjectCode(Map<String, Object> form);
    /**
     * 获取支出方账号,公司账号
     * 
     * @param
     *
     * @return
     */
    List<Map<String, Object>> getProvideCompanyId(Map<String, Object> map);
	/**
     * 未使用油气卡号
     * 
     * @param
     *
     * @return
     */
    List<Map<String, Object>> getOilGasCardUnused(Map<String, Object> form);
    
    /**
     * 客户业务联系人
     * 
     * @param
     *
     * @return
     */
    List<Map<String, Object>>getBizContactor(Map<String, Object> form);
	/**
     * 获取集装箱号
     * 
     * @param
     *
     * @return
     */
	List<Map<String, Object>> getSelectionsByStationId(@Param(value = "text") String text, @Param(value = "mapC") Map<String, Object> mapC);

	
	/**
     * 获取领取人
     * 
     * @param
     *
     * @return
     */
    List<Map<String, Object>> getReciverByFinId(@Param("text") String text,@Param("list") List<String> ids);
    
    /**
     * 发货企业和收货企业
     */
    List<Map<String, Object>> getCustomCompany(Map<String, Object> form);
    
    /**
     * 获取登录人所在企业顶级机构 
     * 
     * @param
     *
     * @return
     */
    List<Map<String, Object>> getOrgTop(Map<String, Object> form);
    /**
     * 获取登录人所在企业全部机构
     * 
     * @param
     *
     * @return
     */
    List<Map<String, Object>> getOrgAll(Map<String, Object> form);
    /**
     * 获取登录人所在机构本部
     * 
     * @param
     *
     * @return
     */
    List<Map<String, Object>> getOrgBranchSelf(Map<String, Object> form);
    /**
     * 获取登录人所在机构本部及其下级子孙机构 
     * 
     * @param
     *
     * @return
     */
    List<Map<String, Object>> getOrgBranchAll(Map<String, Object> form);

}
