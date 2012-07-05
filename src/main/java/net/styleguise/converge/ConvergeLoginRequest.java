package net.styleguise.converge;

public class ConvergeLoginRequest extends ConvergeApiRequest {
	
	private String email;
	
	private String username;
	
	private String hashedPassword;
	
	private String ipAddress;
	
	private String joinDate;
	
	private String timezone;
	
	private String daylightSavingsTimeAutocorrect;

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

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getDaylightSavingsTimeAutocorrect() {
		return daylightSavingsTimeAutocorrect;
	}

	public void setDaylightSavingsTimeAutocorrect(
			String daylightSavingsTimeAutocorrect) {
		this.daylightSavingsTimeAutocorrect = daylightSavingsTimeAutocorrect;
	}
}
