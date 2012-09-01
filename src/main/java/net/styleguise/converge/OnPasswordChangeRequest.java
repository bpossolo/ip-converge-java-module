package net.styleguise.converge;

public class OnPasswordChangeRequest extends ConvergeApiRequest {
	
	/**
	 * Either the username or email address (as set by the applications module configuration in IPConverge).
	 */
	private String auth;
	
	/**
	 * The new password (cleartext password hashed once using md5).
	 */
	private String hashedPassword;

	/**
	 * Get the username or email address.
	 * @return username or email
	 */
	public String getAuth() {
		return auth;
	}

	void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * Get the new password (cleartext password hashed once using md5).
	 * @return the new password
	 */
	public String getHashedPassword() {
		return hashedPassword;
	}

	void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

}
