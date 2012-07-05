package net.styleguise.converge;

public class OnUsernameChangeRequest extends ConvergeApiRequest {
	
	private String auth;
	
	private String oldUsername;
	
	private String newUsername;

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getOldUsername() {
		return oldUsername;
	}

	public void setOldUsername(String oldUsername) {
		this.oldUsername = oldUsername;
	}

	public String getNewUsername() {
		return newUsername;
	}

	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}

}
