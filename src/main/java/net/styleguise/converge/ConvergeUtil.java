package net.styleguise.converge;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
	
	public static String md5(String text){
		
		try{
			MessageDigest hasher = MessageDigest.getInstance("MD5");
			byte[] md5 = hasher.digest(text.getBytes("UTF-8"));
			
			//convert bytes to hex
			StringBuilder sb = new StringBuilder();
			for( int i = 0; i < md5.length; i++ ){
				String hex = Integer.toHexString(0xFF & md5[i]);
				if( hex.length() == 1 )
					sb.append('0');
				sb.append(hex);
			}
			
			return sb.toString();
		}
		catch(NoSuchAlgorithmException e){
			throw new RuntimeException("Unable to generate md5 because MD5 algorithm does not exist", e);
		}
		catch(UnsupportedEncodingException e){
			throw new RuntimeException("Unable to generate md5 because UTF-8 encoding does not exist", e);
		}
	}
	
	/**
	 * The salt used by IP.Converge and IP.Board is exactly 5 characters long.
	 * @return a random, 5-character salt value
	 */
	public static String generateSalt(){
		
		String legalChars = "0123456789ABCDEFGHIJKLKMNOPQRSTUVWXYZ!@#$%^&*[]_<>,./{}+=-|";
		char[] salt = new char[5];
		SecureRandom random = new SecureRandom();
		
		for( int i = 0; i < 5; i++ ){
			int index = random.nextInt(legalChars.length());
			salt[i] = legalChars.charAt(index);
		}
		return new String(salt);
	}

}
