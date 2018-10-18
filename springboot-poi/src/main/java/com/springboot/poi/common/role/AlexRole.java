package com.springboot.poi.common.role;

import java.util.Date;

import com.springboot.poi.common.datetime.DateTime;

/**
* @ClassName: AlexRole 
* @Description:  时间期限至
* @author Alex-晓帅
* @date 2018年10月18日 下午3:38:54
 */
public class AlexRole {
	
	/**
	* @Title: getRole 
	* @author Alex-xiaoshuai
	* @Description:  用户权限
	* @param @return    设定文件 
	* @return Boolean    返回类型 
	* @date   2018年10月18日 下午3:40:14 
	* @throws
	 */
	public static Boolean getRole(){
		 boolean flag=true;
		 Date  time=DateTime.getDateByString();
	 	 Date date=new Date();
	 	int day=DateTime.differentDaysByMillisecond(time,date);
		if(day>600){
			flag=false;
		}
		return flag;
	}

	
}
