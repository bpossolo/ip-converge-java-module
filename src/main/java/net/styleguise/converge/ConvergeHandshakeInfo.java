package net.styleguise.converge;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ConvergeHandshakeInfo implements Serializable {
	
	private String authKey;
	
	private int productId;

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
