package com.qinlong.carmanage.common.util;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public class ResultSetUtils {

	private String state;
	private String message;
	private boolean bool;
	private HashMap<String, Object> map;
	private List<?> list;
	
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	public HashMap<String, Object> getMap() {
		return map;
	}
	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}
	
}
