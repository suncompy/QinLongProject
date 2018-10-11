package com.shenhesoft.logistics.business.caretam;

import java.util.List;

import com.shenhesoft.logistics.manage.helpPojo.CarTeamDetail;
import com.shenhesoft.logistics.manage.pojo.carTeam.TbCarTeam;

/**
 * @description:
 * 
 * @author shilvfei
 * 
 * @date 2017年12月19日
 */
public interface CarTeamService {

	/**
	 * @description 获取公司下挂靠车队
	 * @date 2017年12月19日
	 * @author shilvfei
	 * @param companyId
	 * @return
	 */
	List<TbCarTeam> selectAnchoredCarTeam(Integer companyId);

}
