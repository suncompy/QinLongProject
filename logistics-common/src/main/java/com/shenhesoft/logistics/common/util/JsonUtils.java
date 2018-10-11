package com.shenhesoft.logistics.common.util;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

/**
 * 
 */
public class JsonUtils {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @param clazz 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> T toCollection(Class<? extends Collection> collectionClass,
        Class<?> elementClasses, String json) {
      // JavaType javaType =
      // MAPPER.getTypeFactory().constructCollectionType(collectionClass,
      // elementClasses);

      CollectionType listType =
          MAPPER.getTypeFactory().constructCollectionType(collectionClass, elementClasses);

      try {
        return (T) MAPPER.readValue(json, listType);
      } catch (JsonParseException e) {
        logger.error("JsonParseException: ", e);
      } catch (JsonMappingException e) {
        logger.error("JsonMappingException: ", e);
      } catch (IOException e) {
        logger.error("IOException: ", e);
      }
      return null;
    }

    /**
     * jackjson把json字符串转换为Java对象的实现方法
     * 
     * 
     * @param <T> 转换为的java对象
     * @param json json字符串
     * @param typeReference jackjson自定义的类型
     * @return 返回Java对象
     */
    public static <T> T toObject(String json, TypeReference<T> typeReference) {
      MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
      try {
        return MAPPER.readValue(json, typeReference);
      } catch (JsonParseException e) {
        logger.error("JsonParseException: ", e);
      } catch (JsonMappingException e) {
        logger.error("JsonMappingException: ", e);
      } catch (IOException e) {
        logger.error("IOException: ", e);
      }
      return null;
    }

    /**
     * json转换为java对象
     * 
     * @param <T> 要转换的对象
     * @param json 字符串
     * @param valueType 对象的class
     * @return 返回对象
     */
    public static <T> T toObject(String json, Class<T> valueType) {
      MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
      try {
        return MAPPER.readValue(json, valueType);
      } catch (JsonParseException e) {
        logger.error("JsonParseException: ", e);
      } catch (JsonMappingException e) {
        logger.error("JsonMappingException: ", e);
      } catch (IOException e) {
        logger.error("IOException: ", e);
      }
      return null;
    }

    /**
     * java对象转换为json字符串
     * 
     * @param object Java对象
     * @return 返回字符串
     */
    public static String toJson(Object object) {
      ObjectMapper mapper = new ObjectMapper();
      try {
        return mapper.writeValueAsString(object);
      } catch (JsonGenerationException e) {
        logger.error("JsonGenerationException: ", e);
      } catch (JsonMappingException e) {
        logger.error("JsonMappingException: ", e);
      } catch (IOException e) {
        logger.error("IOException: ", e);
      }
      return null;
    }
}
