package com.shenhesoft.logistics.manage.web.utils.tags;

import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.shenhesoft.logistics.common.session.AppSession;

/**
 * @description
 * 
 * @author shilvfei
 * 
 * @date 2018年5月10日
 */
public class HasUrlPermissionTag extends BodyTagSupport {

	private String code;//  acApplication/forMain.do
	
	@Override
	public int doStartTag() throws JspException {
        //获取session中存放的权限
        List<String> permissionCodes = AppSession.getUserPermissionCodes();
        //判断是否有权限访问
        if (permissionCodes.contains(code)) {
            //允许访问标签body
            return BodyTagSupport.EVAL_BODY_INCLUDE;// 返回此则执行标签body中内容，SKIP_BODY则不执行
        } else {
            return BodyTagSupport.SKIP_BODY;
        }
	}
	
	 @Override
    public int doEndTag() throws JspException {
        return BodyTagSupport.EVAL_BODY_INCLUDE;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
