package com.springboot.poi.common.aenum;

public enum Alexenum {
		ErrorSysTem(500,"系统错误"),
		ErrorNotFind(404,"系统404错误"),
		ErrorInitSys(1000,"系统初始化返回"),
		SuccessSys(200,"处理成功"),
		NotFind(102,"查询为空");
		
		private Integer code;
	 	private String message;
	 	
	 	Alexenum(Integer code, String message) {
	 		this.code = code;
	 		this.message = message;
	 	}
	    public static Alexenum getResponseStatus(String message) {
	        for (Alexenum ut : Alexenum.values()) {
	            if (ut.getMessage().equals(message)) {
	                return ut;
	            }
	        }
	        return null;
	    }

	    public Integer getCode() {
	        return code;
	    }

	    public String getMessage() {
	        return message;
	    }
	    
}
