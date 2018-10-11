package com.shenhesoft.logistics.common.page;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * FdJaxbJacksonObjectMapper : 禁用Jackson的FAIL_ON_EMPTY_BEANS
 *
 */
public class JacksonObjectMapper extends ObjectMapper {

  private static final long serialVersionUID = -6588193192037263348L;

  public JacksonObjectMapper() {
	  super(); 
      // 空值处理为空串 
      this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() { 
          @Override 
          public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException { 
            	  jg.writeString("");
          }
      }); 
      //this.setSerializationInclusion(Include.NON_NULL);
      SimpleDateFormat fmt = new SimpleDateFormat("yyyy.MM.dd HH:mm");  
      this.setDateFormat(fmt);
	    this.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
	        .setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY)
	        .setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE)
	        .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
	        .setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
	
	    // 禁用空对象转换json校验
	    this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    /*
    this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() { 
        @Override 
        public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException { 
            jg.writeString(""); 
        } 
    }); 
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy.MM.dd HH:mm");  
    this.setDateFormat(fmt);
    */
    /*
     * SimpleModule module = new SimpleModule("HTML XSS Serializer", new Version(1, 0, 0, "FINAL",
     * "com.shenhesoft", "sh-base")); module.addSerializer(new JsonHtmlXssSerializer(String.class));
     * this.registerModule(module);
     */
  }

  class JsonHtmlXssSerializer extends JsonSerializer<String> {

    public JsonHtmlXssSerializer(Class<String> string) {
      super();
    }

    public Class<String> handledType() {
      return String.class;
    }

    public void serialize(String value, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
      if (value != null) {
        String encodedValue = HtmlUtils.htmlEscape(value);
        jsonGenerator.writeString(encodedValue);
      }
    }
  }
}
