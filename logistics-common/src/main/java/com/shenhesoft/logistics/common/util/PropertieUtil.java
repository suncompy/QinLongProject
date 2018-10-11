package com.shenhesoft.logistics.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @description
 *
 * @author LiuJiefeng
 *
 * @date 2018年2月8日
 */
public class PropertieUtil {

  private static final Logger logger = LoggerFactory.getLogger(PropertieUtil.class);
  protected Properties properties;

  private String fileName;

  public PropertieUtil(String fileName) {
    properties = new Properties();
    this.fileName = fileName;
    load();
  }

  protected void load() {
    try {
      String url = this.getClass().getResource("/").getPath();
      String fullFileNameString = url + fileName;  
      properties.load(new InputStreamReader(new BufferedInputStream(new FileInputStream(fullFileNameString)),"utf-8"));
    } catch (IOException e) {
      logger.error("",e);
    }
  }

  public String get(String key) {
    return properties.getProperty(key);
  }
  public void set(String key, String value) {
    properties.setProperty(key, value);
  }
}
