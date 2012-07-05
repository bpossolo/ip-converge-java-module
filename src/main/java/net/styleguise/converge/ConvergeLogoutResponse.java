package net.styleguise.converge;

import java.util.ArrayList;
import java.util.List;

public class ConvergeLogoutResponse {
	
	private boolean success;
	
	private List<ConvergeCookie> cookies;
	
	public void addCookie(ConvergeCookie cookie){
		if( cookies == null )
			cookies = new ArrayList<ConvergeCookie>();
		cookies.add(cookie);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<ConvergeCookie> getCookies() {
		return cookies;
	}

	public void setCookies(List<ConvergeCookie> cookies) {
		this.cookies = cookies;
	}

}
