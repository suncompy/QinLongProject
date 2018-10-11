package com.shenhesoft.logistics.finance.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.ShortPack;
import com.shenhesoft.logistics.finance.TbDotAccountPack;
import com.shenhesoft.logistics.finance.TbDotAccountPackService;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.finance.mapper.ShortPackMapper;
import com.shenhesoft.logistics.finance.mapper.TbDotAccountPackMapper;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

@Service
public class TbDotAccountPackServiceImpl implements TbDotAccountPackService {

	@Autowired
	private TbDotAccountPackMapper accountPackMapper;

	@Autowired
	private ShortPackMapper shortPackMapper;

	@Autowired
	private BranchGroupLinkMapper branchGroupLinkMapper;

	@Override
	public List<Map<String, Object>> getDotAccountPacks(Map<String, Object> form) {
		return accountPackMapper.getDotAccountPacks(form);
	}

	@Override
	public List<Map<String, Object>> getDotAccountPacks(Integer page, Integer limit, Map<String, Object> form) {
		PageHelper.startPage(page, limit);
		return this.getDotAccountPacks(form);
	}

	@Override
	public TbDotAccountPack addDotAccountPack(TbDotAccountPack dotAccountPack, TbSystemUser user) {
		String dotAccountPackId = AppUtils.randomUUID();

		String[] shPackIds = dotAccountPack.getShPackIds().split(",");

		if (shPackIds == null || shPackIds.length == 0) {
			return null;
		}
		ShortPack shortPack = new ShortPack();
		shortPack.setDotAccountPackId(dotAccountPackId);
		shortPackMapper.editShortPackByIds(shortPack, Arrays.asList(shPackIds));

		dotAccountPack.setDotAccountPackId(dotAccountPackId);
		dotAccountPack.setStatus(0);
		dotAccountPack.setCreateDate(new Date());
		dotAccountPack.setCreateUserId(user.getId());
		accountPackMapper.insertSelective(dotAccountPack);

		BranchGroupLink branchGroupLink = new BranchGroupLink();
		branchGroupLink.setId(AppUtils.randomUUID());
		branchGroupLink.setRowId(dotAccountPackId);
		branchGroupLink.setTabName("tb_dot_account_pack");
		branchGroupLink.setTabComment("网点交账打包表");
		branchGroupLink.setSysOrgCode(AppSession.getCurrentSysOrgCode());
		branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);

		return dotAccountPack;
	}

	@Override
	public void financeAuditShortPackByIds(String dotAccountPackIds) {
		List<String> dotAccountPackIdList = Arrays.asList(dotAccountPackIds.trim().split(","));
		for (String dotAccountPackId : dotAccountPackIdList) {
			TbDotAccountPack accountPack = new TbDotAccountPack();
			accountPack.setDotAccountPackId(dotAccountPackId);
			accountPack.setStatus(1);
			accountPackMapper.updateByPrimaryKeySelective(accountPack);
		}
	}
}
