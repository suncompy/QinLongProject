package com.shenhesoft.logistics.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shenhesoft.logistics.manage.helpPojo.SmsCustomerHelp;
import com.shenhesoft.logistics.manage.pojo.codeModel.TbSmsCodeModel;
import com.shenhesoft.logistics.manage.pojo.codeModel.TbSmsCodeModelExample;
import com.shenhesoft.logistics.manage.pojo.stationBusiness.TbStationBusiness;
import com.shenhesoft.logistics.manage.pojo.tbSmsSend.TbSmsSend;

public interface TbSmsCodeModelMapper {
	int countByExample(TbSmsCodeModelExample example);

	int deleteByExample(TbSmsCodeModelExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbSmsCodeModel record);

	int insertSelective(TbSmsCodeModel record);

	List<TbSmsCodeModel> selectByExample(TbSmsCodeModelExample example);

	TbSmsCodeModel selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbSmsCodeModel record,
			@Param("example") TbSmsCodeModelExample example);

	int updateByExample(@Param("record") TbSmsCodeModel record, @Param("example") TbSmsCodeModelExample example);

	int updateByPrimaryKeySelective(TbSmsCodeModel record);

	int updateByPrimaryKey(TbSmsCodeModel record);

	TbSmsCodeModel selectContentByCode(@Param(value = "code") String code);

	List<SmsCustomerHelp> listCustomerAndBusniss(Map<String, Object> map);

	List<SmsCustomerHelp> listCustomerAndBusnissByPage(@Param("map")Map<String, Object> map);

	List<TbSmsSend> queryCustomerAndBusnissMsgList(TbSmsSend smsSend);

	List<TbStationBusiness> listStationBusniss(Map<String, Object> map);

	List<TbStationBusiness> selectTbStationBusinessById(Integer id);

	List<TbStationBusiness> selectBulkBusinessByMap(Map<String, Object> map);

	List<TbStationBusiness> listBulkStationBusniss(Map<String, Object> map);

	List<Byte> selectBulkStepCodeByIds(Map<String, Object> map);
	
}