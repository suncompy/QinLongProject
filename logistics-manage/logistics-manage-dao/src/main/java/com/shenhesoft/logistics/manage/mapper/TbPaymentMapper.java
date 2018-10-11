package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.helpPojo.PaymentDetail;
import com.shenhesoft.logistics.manage.pojo.payment.TbPayment;
import com.shenhesoft.logistics.manage.pojo.payment.TbPaymentExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbPaymentMapper {
    int countByExample(TbPaymentExample example);

    int deleteByExample(TbPaymentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbPayment record);

    int insertSelective(TbPayment record);

    List<TbPayment> selectByExample(TbPaymentExample example);

    TbPayment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbPayment record, @Param("example") TbPaymentExample example);

    int updateByExample(@Param("record") TbPayment record, @Param("example") TbPaymentExample example);

    int updateByPrimaryKeySelective(TbPayment record);

    int updateByPrimaryKey(TbPayment record);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月11日
	 * @param 
	 * @return
	*/
	List<PaymentDetail> selectPaymentList(Map<String, Object> map);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月13日
	 * @param 
	 * @return
	*/
	/*List<Integer> selectShortBargeIdById(Integer id);*/
}