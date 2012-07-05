package net.styleguise.converge;

import java.util.Date;

public class ConvergeUtil {
	
	private ConvergeUtil(){}
	
	public static Date unixTimestampToDate(String timestamp){
		return new Date(Long.parseLong(timestamp) * 1000L);
	}
	
	public static Date unixTimestampToDate(int timestamp){
		return new Date((long)timestamp * 1000L);
	}
	
	public static int dateToUnixTimestamp(Date date){
		return (int)(date.getTime() / 1000L);
	}

}
