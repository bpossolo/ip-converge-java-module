package net.styleguise.converge;

import java.util.Date;

public class OnRegisterRequest extends ConvergeApiRequest {

	private int timezone;
	
	private String hashedPassword;
	
	private boolean daylightSavingsTimeAutocorrect;
	
	private String ipAddress;
	
	private Date joinDate;
	
	private boolean validating;
	
	private String email;

	public int getTimezone() {
		return timezone;
	}

	void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public boolean isDaylightSavingsTimeAutocorrect() {
		return daylightSavingsTimeAutocorrect;
	}

	void setDaylightSavingsTimeAutocorrect(boolean daylightSavingsTimeAutocorrect) {
		this.daylightSavingsTimeAutocorrect = daylightSavingsTimeAutocorrect;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public boolean isValidating() {
		return validating;
	}

	void setValidating(boolean validating) {
		this.validating = validating;
	}

	public String getEmail() {
		return email;
	}
	
	void setEmail(String email) {
		this.email = email;
	}
}
