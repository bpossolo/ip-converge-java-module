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
	
	public static int objToInt(Object obj){
		if( obj instanceof String ){
			return Integer.parseInt((String)obj);
		}
		else if( obj instanceof Integer ){
			return ((Integer)obj).intValue();
		}
		else
			throw new IllegalArgumentException("Object must be either a java.lang.String or java.lang.Integer");
	}

}
