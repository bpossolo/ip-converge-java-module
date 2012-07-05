package net.styleguise.converge;

import java.util.ArrayList;
import java.util.List;

public class ConvergeLoginResponse {
	
	private boolean success;
	
	private String sessionId;
	
	private String memberId;
	
	private String loginKey;
	
	private List<ConvergeCookie> cookies;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getLoginKey() {
		return loginKey;
	}

	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}

	public void addCookie(ConvergeCookie cookie){
		if( cookies == null )
			cookies = new ArrayList<ConvergeCookie>();
		cookies.add(cookie);
	}
	
	public List<ConvergeCookie> getCookies() {
		return cookies;
	}

	public void setCookies(List<ConvergeCookie> cookies) {
		this.cookies = cookies;
	}

}
