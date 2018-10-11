package com.shenhesoft.logistics.common.conf;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 系统配置.
 * 
 * @author LiuJiefeng
 *
 */
public class AppConfig extends PropertyPlaceholderConfigurer {

  private static Map<String, String> propertiesMap;

  @Override
  protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
      Properties props) throws BeansException {

    super.processProperties(beanFactoryToProcess, props);
    propertiesMap = new HashMap<>();

    for (Object key : props.keySet()) {
      String keyStr = key.toString();
      String value = props.getProperty(keyStr);
      propertiesMap.put(keyStr, value);
    }
  }

  public static String get(String name) {
    return propertiesMap.get(name);
  }

}
