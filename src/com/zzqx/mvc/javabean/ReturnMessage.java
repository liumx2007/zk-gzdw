package com.zzqx.mvc.javabean;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ReturnMessage {
	
	public static final String MESSAGE_ERROR = "error";
	public static final String MESSAGE_SUCCESS = "success";
	public static final String MESSAGE_NOTICE = "notice";
	
	private String type;
	private String message;
	private Object data;
	
	public ReturnMessage(String type) {
		this.type = type;
	}
	
	public ReturnMessage(String type, String message) {
		this.type = type;
		this.message = message;
	}
	
	public ReturnMessage(String type, String message, Object data) {
		this.type = type;
		this.message = message;
		this.data = data;
	}
	
	public ReturnMessage() {}
	
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
	
	public String toString(JsonConfig jsonConfig) {
		return JSONObject.fromObject(this, jsonConfig).toString();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
