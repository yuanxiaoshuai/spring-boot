package com.springboot.poi.common.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
	
	public static int differentDaysByMillisecond(Date date1,Date date2)
	{
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
		return days;
	}
	
	//字符串转换为时间
	public  static Date getDateByString(){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		 try {
	           date=simpleDateFormat.parse("2018-10-11");
	        } catch(ParseException px) {
	            px.printStackTrace();
	        }
		 return date;
	}
}
