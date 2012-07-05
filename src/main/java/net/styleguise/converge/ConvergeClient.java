package net.styleguise.converge;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import redstone.xmlrpc.XmlRpcClient;
import redstone.xmlrpc.XmlRpcFault;

import net.styleguise.converge.AuthenticationResponse.Response;

public class ConvergeClient {
	
	private static final Logger log = Logger.getLogger(ConvergeClient.class.getName());
	
	private final String authKey;
	
	private final String productId;
	
	private final XmlRpcClient rpcClient;
	
	public ConvergeClient(String convergeServerBaseUrl, String authKey, String productId) 
		throws MalformedURLException {
		
		this.authKey = authKey;
		this.productId = productId;
		
		String url = convergeServerBaseUrl + "/converge_master/converge_server.php";
		
		rpcClient = new XmlRpcClient(url, false);
	}
	
	/**
	 * Checks if an email address is available.
	 * @param email the email address to check.
	 * @return true if the email is available, false if it is taken
	 * @throws XmlRpcFault if an error occurs talking to converge
	 */
	public boolean checkEmailAvailable(String email) throws XmlRpcFault {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("auth_key", authKey);
		params.put("product_id", productId);
		params.put("email_address", email);
		
		@SuppressWarnings("unchecked")
		Map<String,Object> xmlResponse = 
			(Map<String,Object>)rpcClient.invoke("convergeCheckEmail", new Object[]{params});
		
		Integer complete = (Integer)xmlResponse.get("complete");
		if( complete == null || complete != 1 )
			log.warning("Response parameter [complete] is not equal to 1");
		
		String response = (String)xmlResponse.get("response");
		
		if( "EMAIL_NOT_IN_USE".equals(response) )
			return true;
		else
			return false;
	}
	
	/**
	 * Checks if a username is available.
	 * @param username the username to check.
	 * @return true if the username is available, false if it is taken
	 * @throws XmlRpcFault if an error occurs talking to converge
	 */
	public boolean checkUsernameAvailable(String username) throws XmlRpcFault {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("auth_key", authKey);
		params.put("product_id", productId);
		params.put("username", username);
		
		@SuppressWarnings("unchecked")
		Map<String,Object> xmlResponse = 
			(Map<String,Object>)rpcClient.invoke("convergeCheckUsername", new Object[]{params});
		
		Integer complete = (Integer)xmlResponse.get("complete");
		if( complete == null || complete != 1 )
			log.warning("Response parameter [complete] is not equal to 1");
		
		String response = (String)xmlResponse.get("response");
		
		if( "USERNAME_NOT_IN_USE".equals(response) )
			return true;
		else
			return false;
	}
	
	/**
	 * Authenticates a user.
	 * The password will be hashed using MD5 before being sent over the wire to Converge.
	 * @param email the email address to authenticate
	 * @param username required if the email address is not provided
	 * @param password the cleartext password
	 * @return the authentication response
	 * @throws XmlRpcFault if an error occurs talking to converge
	 */
	public AuthenticationResponse authenticate(String email, String username, String password) throws XmlRpcFault {
		
		//TODO md5 hash the password
		String passwordMd5 = null;
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("auth_key", authKey);
		params.put("product_id", productId);
		params.put("md5_once_password", passwordMd5);
		
		if( email == null )
			params.put("username", username);
		else
			params.put("email_address", email);
		
		@SuppressWarnings("unchecked")
		Map<String,Object> xmlResponse = 
			(Map<String,Object>)rpcClient.invoke("convergeCheckUsername", new Object[]{params});
		
		Integer complete = (Integer)xmlResponse.get("complete");
		if( complete == null || complete != 1 )
			log.warning("Response parameter [complete] is not equal to 1");
		
		String response = (String)xmlResponse.get("response");
		String responseUsername = (String)xmlResponse.get("username");
		String responseEmail = (String)xmlResponse.get("email");
		String ipAddress = (String)xmlResponse.get("ipaddress");
		//todo process unix joined time
		
		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setUsername(responseUsername);
		authResponse.setEmail(responseEmail);
		authResponse.setIpAddress(ipAddress);
		
		if( "NO_USER".equals(response) ){
			authResponse.setResponse(Response.NoUser);
		}
		else if( "DETAILS_INCOMPLETE".equals(response) ){
			authResponse.setResponse(Response.DetailsIncomplete);
		}
		else if( "WRONG_AUTH".equals(response) ){
			authResponse.setResponse(Response.WrongAuth);
		}
		else if( "FLAGGED_LOCAL".equals(response) ){
			authResponse.setResponse(Response.FlaggedLocal);
		}
		else if( "FLAGGED_REMOTE".equals(response) ){
			authResponse.setResponse(Response.FlaggedRemote);
		}
		else if( "SUCCESS".equals(response) ){
			authResponse.setResponse(Response.Success);
		}
		else if( "AUTH_FAILURE".equals(response) ){
			authResponse.setResponse(Response.AuthFailure);
		}
		
		return authResponse;
	}

}
