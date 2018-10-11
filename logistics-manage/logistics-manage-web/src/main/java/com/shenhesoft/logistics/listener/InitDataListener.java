package com.shenhesoft.logistics.listener;

import java.util.StringTokenizer;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.ServletContextAware;

import com.shenhesoft.logistics.common.util.Constants;

/**
 * 项目自动时执行 用于初始化数据
 * @author dusd
 * @date 2018年1月11日
 */
public class InitDataListener implements InitializingBean, ServletContextAware {
	
	/**
	 * 默认附件上传位置
	 */
	@Value("${FILE_PATH}")
	private String FILE_PATH;
	
	/**
	 * 融云 appKey
	 */
	@Value("${APP_KEY_RONG_CLOUD}")
	private String APP_KEY_RONG_CLOUD;
	/**
	 * 融云 appSecret
	 */
	@Value("${APP_SECRET_RONG_CLOUD}")
	private String APP_SECRET_RONG_CLOUD;
	/**
	 * 项目编号
	 */
	@Value("${PROJECT_CODE}")
	private String PROJECT_CODE;
	
	/**
	 * 图片地址
	 */
	@Value("${IMG_PATH}")
	private String IMG_PATH;
	
	/**
	 * 图片地址
	 */
	@Value("${PERSSION_ALL_ID}")
	private String PERSSION_ALL_ID;
	
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		Constants.FILE_PATH = FILE_PATH;
		Constants.APP_KEY_RONG_CLOUD = APP_KEY_RONG_CLOUD;
		Constants.APP_SECRET_RONG_CLOUD = APP_SECRET_RONG_CLOUD;
		Constants.PROJECT_CODE=PROJECT_CODE;
		Constants.IMG_PATH=IMG_PATH;
		Constants.PERSSION_ALL_ID=StringtoInt(PERSSION_ALL_ID);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

	 public Integer[] StringtoInt(String str) {  
		Integer ret[] = new Integer[str.length()];   
	    StringTokenizer toKenizer = new StringTokenizer(str, ",");   
	    int i = 0;  
	    while (toKenizer.hasMoreElements()) {   
	      ret[i++] = Integer.valueOf(toKenizer.nextToken());  
	    }   
	   return ret;  
	 }
	
}
