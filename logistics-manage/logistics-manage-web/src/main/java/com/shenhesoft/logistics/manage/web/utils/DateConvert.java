package com.shenhesoft.logistics.manage.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * @description:日期转换
 * 
 * @author shilvfei
 * 
 * @date 2017年11月27日
 */
public class DateConvert implements Converter<String, Date> {

	@Override
	public Date convert(String stringDate) {
		if(StringUtils.isBlank(stringDate)){
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
}