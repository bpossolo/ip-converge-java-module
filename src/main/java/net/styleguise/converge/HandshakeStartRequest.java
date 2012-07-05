package net.styleguise.converge;

import java.util.Date;

public class HandshakeStartRequest {
	
	private int registrationId;
	
	private String registrationCode;
	
	private Date registrationDate;
	
	private int productId;
	
	private String convergeUrl;
	
	private String convergeAdminEmail;
	
	private String convergeAdminPasswordMd5;

	private String htaccessUsername;
	
	private String htaccessPassword;
	
	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getConvergeUrl() {
		return convergeUrl;
	}

	public void setConvergeUrl(String convergeUrl) {
		this.convergeUrl = convergeUrl;
	}

	public String getConvergeAdminEmail() {
		return convergeAdminEmail;
	}

	public void setConvergeAdminEmail(String convergeAdminEmail) {
		this.convergeAdminEmail = convergeAdminEmail;
	}

	public String getConvergeAdminPasswordMd5() {
		return convergeAdminPasswordMd5;
	}

	public void setConvergeAdminPasswordMd5(String convergeAdminPasswordMd5) {
		this.convergeAdminPasswordMd5 = convergeAdminPasswordMd5;
	}

	public String getHtaccessUsername() {
		return htaccessUsername;
	}

	public void setHtaccessUsername(String htaccessUsername) {
		this.htaccessUsername = htaccessUsername;
	}

	public String getHtaccessPassword() {
		return htaccessPassword;
	}

	public void setHtaccessPassword(String htaccessPassword) {
		this.htaccessPassword = htaccessPassword;
	}

}
