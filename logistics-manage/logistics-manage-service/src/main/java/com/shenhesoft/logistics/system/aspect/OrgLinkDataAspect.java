package com.shenhesoft.logistics.system.aspect;

import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.shenhesoft.logistics.common.session.AppSession;
import com.shenhesoft.logistics.common.util.AppUtils;
import com.shenhesoft.logistics.common.util.FormUtil;
import com.shenhesoft.logistics.finance.BranchGroupLink;
import com.shenhesoft.logistics.finance.mapper.BranchGroupLinkMapper;
import com.shenhesoft.logistics.manage.pojo.systemuser.TbSystemUser;

@Component
@Aspect
public class OrgLinkDataAspect {
  protected static final Logger logger = LoggerFactory.getLogger(OrgLinkDataAspect.class);
  @Autowired
  private BranchGroupLinkMapper branchGroupLinkMapper;

  @Pointcut("@annotation(com.shenhesoft.logistics.system.aspect.OrgLinkData)")
  public void authDataParam() {}

  @After("authDataParam() && @annotation(data)")
  public void doBefore(JoinPoint joinPoint, OrgLinkData data) {
    insertBranchLink(joinPoint, data);
  }

  protected boolean isNotTopUserCourt(TbSystemUser user) {
    if (null != user && null != user.getCompanyId() && "".equals(user.getCompanyId())
        && "34".equals(user.getCompanyId()) && "340000".equals(user.getCompanyId())) {
      return true;
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  protected void transData(TbSystemUser user, Object o) {
    if (o instanceof Map) {
      Map<String, Object> map = (Map<String, Object>) o;
      String cityCode = getMapValue(map, "cityCode");
      String courtCode = getMapValue(map, "courtCode");
      if (null != courtCode) {
        map = loadMap(user, map, "courtCode", courtCode);
        map.put("cityCode", null);
      } else if (null != cityCode) {
        map = loadMap(user, map, "cityCode", cityCode);
      }
      new BeanWrapperImpl(map);
    }
  }

  private Map<String, Object> loadMap(TbSystemUser user, Map<String, Object> map, String key,
      String value) {
    if (user.getCompanyId() > value.length()
        || (user.getCompanyId() == value.length() && user.getCompanyId().equals(value))) {
      map.put(key, user.getCompanyId());
    }
    return map;
  }

  private String getMapValue(Map<String, Object> map, String key) {
    return (null == map.get(key) || "".equals(map.get(key).toString().trim())) ? null
        : map.get(key).toString();
  }

  private void insertBranchLink(JoinPoint joinPoint, OrgLinkData data) {
    BranchGroupLink branchGroupLink = new BranchGroupLink(data.idName(), data.tabComment());
    branchGroupLink.setId(AppUtils.randomUUID());
    Integer userId = AppSession.getCurrentUserId();
    if(null!=userId){
      Map<String, String> sysCodeMap = branchGroupLinkMapper.getSysOrgCode(userId);
      branchGroupLink.setSysOrgCode(sysCodeMap.get("sysOrgCode"));
      
      Object[] obj = joinPoint.getArgs();
      String className = obj[0].getClass().getName();
      className = className.substring(className.lastIndexOf(".")+1, className.length());
      className = className.startsWith("Tb") ? className : "tb" + className;
      branchGroupLink.setTabName(FormUtil.camelToUnderline(className));
      
      Map<String, Object> map = FormUtil.populate(obj[0]);
      branchGroupLink
          .setRowId(null == map.get(data.idName()) ? null : map.get(data.idName()).toString());
      branchGroupLinkMapper.addBranchGroupLink(branchGroupLink);
    }
  }

}
