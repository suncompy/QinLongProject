package com.shenhesoft.logistics.manage.mapper;

import com.shenhesoft.logistics.manage.helpPojo.AccountManagementDetail;
import com.shenhesoft.logistics.manage.pojo.branchgroup.TbBranchGroup;
import com.shenhesoft.logistics.manage.pojo.customer.TbCustomer;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbAccountRecordDetail;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccount;
import com.shenhesoft.logistics.manage.pojo.financeAccount.TbFinanceAccountExample;
import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStation;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbFinanceAccountMapper {
    int countByExample(TbFinanceAccountExample example);

    int deleteByExample(TbFinanceAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbFinanceAccount record);

    int insertSelective(TbFinanceAccount record);

    List<TbFinanceAccount> selectByExample(TbFinanceAccountExample example);

    TbFinanceAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbFinanceAccount record, @Param("example") TbFinanceAccountExample example);

    int updateByExample(@Param("record") TbFinanceAccount record, @Param("example") TbFinanceAccountExample example);

    int updateByPrimaryKeySelective(TbFinanceAccount record);

    int updateByPrimaryKey(TbFinanceAccount record);

	/**
	 * @description 
	 * @author LiangDeng
	 * @date 2017年12月13日
	 * @param 
	 * @return
	*/
	List<AccountManagementDetail> showAccountList(TbFinanceAccountExample example);

	List<TbCustomer> selectCustomserListByType();

	List<TbTrainStation> selectTrainStationListByType();

	String selectCustomserNameById(Integer chooseAccountId);

	String selectTrainStationNameById(Integer chooseAccountId);

	TbCustomer selectCustomserByTypeAndId(Integer id);

	TbTrainStation selectTrainStationByTypeAndId(Integer id);

	TbBranchGroup selectBranchGroupByTypeAndId(Integer id);

	int deleteAccountByPrimaryKey(Integer id);

	List<TbFinanceAccount> selectAccountList(TbFinanceAccountExample example);

	List<TbAccountRecordDetail> selectAccountDetailById(Integer id);
	
	int addAccountRecordDetail(TbAccountRecordDetail accountRecordDetail);
}