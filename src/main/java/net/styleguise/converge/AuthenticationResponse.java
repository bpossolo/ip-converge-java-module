package net.styleguise.converge;

import java.util.Date;

public class AuthenticationResponse {

	public enum Response {
		NoUser,
		DetailsIncomplete,
		WrongAuth,
		FlaggedLocal,
		FlaggedRemote,
		Success,
		AuthFailure
	}
	
	private Response response;
	
	/**
	 * If authentication was a success, the username.
	 */
	private String username;
	
	/**
	 * If authentication was a success, the email.
	 */
	private String email;
	
	/**
	 * If authentication was a success, the date the user joined.
	 */
	private Date joined;
	
	/**
	 * If the authentication was a success, the IP address.
	 */
	private String ipAddress;

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoined() {
		return joined;
	}

	public void setJoined(Date joined) {
		this.joined = joined;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
