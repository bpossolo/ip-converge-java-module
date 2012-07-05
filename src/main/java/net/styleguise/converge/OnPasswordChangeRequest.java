package net.styleguise.converge;

public class OnPasswordChangeRequest extends ConvergeApiRequest {
	
	private String auth;
	
	private String hashedPassword;

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

}
