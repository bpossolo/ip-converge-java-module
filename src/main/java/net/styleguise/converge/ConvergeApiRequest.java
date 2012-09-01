package net.styleguise.converge;

public class ConvergeApiRequest {
	
	/**
	 * The secret key assigned to the application by IPConverge.
	 */
	private String authKey;
	
	/**
	 * The unique product id assigned to the application by IPConverge.
	 */
	private int productId;

	/**
	 * Get the secret key assigned by IPConverge.
	 * @return the authentication key
	 */
	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	/**
	 * Get the unique product id assigned by IPConverge
	 * @return the product id
	 */
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
