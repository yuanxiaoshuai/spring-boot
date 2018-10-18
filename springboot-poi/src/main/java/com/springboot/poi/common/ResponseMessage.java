package com.springboot.poi.common;

import com.springboot.poi.common.aenum.Alexenum;

import lombok.Data;

public class ResponseMessage {
	
	private boolean success;;

	private long code;  
	
	private String desc;  // 
	
	private Object obj = null; 
	
	private String content;
	
	public ResponseMessage() {
		this.success = false;
		this.code =Alexenum.ErrorInitSys.getCode();
		this.desc=Alexenum.ErrorInitSys.getMessage();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
