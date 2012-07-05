package net.styleguise.converge;

import java.util.Date;

public class HandshakeEndRequest {
	
	private int registrationId;
	
	private String registrationCode;
	
	private Date registrationDate;
	
	private int productId;
	
	private String convergeUrl;
	
	private boolean handshakeCompleted;

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

	public boolean isHandshakeCompleted() {
		return handshakeCompleted;
	}

	public void setHandshakeCompleted(boolean handshakeCompleted) {
		this.handshakeCompleted = handshakeCompleted;
	}

}
