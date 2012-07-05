package net.styleguise.converge;

import org.codehaus.jackson.annotate.JsonProperty;

public class ConvergeMember {
	
	@JsonProperty("email_address")
	private String email;
	
	@JsonProperty
	private String username;
	
	@JsonProperty("pass_salt")
	private String passwordSalt;
	
	@JsonProperty("password")
	private String passwordMd5Hash;
	
	@JsonProperty("ip_address")
	private String ipAddress;
	
	@JsonProperty("join_date")
	private int joinDate;
	
	@JsonProperty
	private String extra;
	
	@JsonProperty
	private int flag;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPasswordMd5Hash() {
		return passwordMd5Hash;
	}

	public void setPasswordMd5Hash(String passwordMd5Hash) {
		this.passwordMd5Hash = passwordMd5Hash;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(int joinDate) {
		this.joinDate = joinDate;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
