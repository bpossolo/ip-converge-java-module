package net.styleguise.converge;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import redstone.xmlrpc.XmlRpcClient;
import redstone.xmlrpc.XmlRpcFault;

import net.styleguise.converge.AuthenticationResponse.Response;

public class ConvergeClient {
	
	private static final Logger log = Logger.getLogger(ConvergeClient.class.getName());
	
	private final String authKey;
	
	private final int productId;
	
	private final XmlRpcClient rpcClient;
	
	public ConvergeClient(String convergeServerBaseUrl, String authKey, int productId) 
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
	public AuthenticationResponse authenticate(String userId, AuthType authType, String password) throws XmlRpcFault {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("auth_key", authKey);
		params.put("product_id", productId);
		params.put("md5_once_password", ConvergeUtil.md5(password));
		
		if( authType == AuthType.Username )
			params.put("username", userId);
		else
			params.put("email_address", userId);
		
		@SuppressWarnings("unchecked")
		Map<String,Object> xmlResponse = 
			(Map<String,Object>)rpcClient.invoke("convergeAuthenticate", new Object[]{params});
		
		Integer complete = (Integer)xmlResponse.get("complete");
		if( complete == null || complete != 1 )
			log.warning("Response parameter [complete] is not equal to 1");
		
		AuthenticationResponse authResponse = new AuthenticationResponse();
		
		String response = (String)xmlResponse.get("response");
		
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
			authResponse.setUsername((String)xmlResponse.get("username"));
			authResponse.setEmail((String)xmlResponse.get("email"));
			authResponse.setIpAddress((String)xmlResponse.get("ipaddress"));
			authResponse.setJoined(ConvergeUtil.unixTimestampToDate((String)xmlResponse.get("joined")));
		}
		else if( "AUTH_FAILURE".equals(response) ){
			authResponse.setResponse(Response.AuthFailure);
		}
		
		return authResponse;
	}
	
	/**
	 * Call this method when a user registers in the remote application.
	 * This method is important because it keeps the member list in 
	 * IP.Converge in-sync with the member list in the remote application.
	 * 
	 * @param username the username (must be unique)
	 * @param email the email address (must be unique)
	 * @param password the clear text password
	 * @param ipAddress the users ip address at the time of registration
	 * @param validating true if the user validated their account (for example by clicking a link in an email), false otherwise
	 * @param extraData optional user-specific information that can be used to increase the complexity of the final hashed password on IP.Converge
	 * @param flag its uncler what this is for. according to the documentation it should almost always be true
	 * @return the result of adding the member to IP.Converge
	 * @throws XmlRpcFault
	 */
	public AddMemberResponse addMember(
			String username, String email, String password, 
			String ipAddress, boolean validating, 
			String extraData, boolean flag) throws XmlRpcFault {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("auth_key", authKey);
		params.put("product_id", productId);
		params.put("email_address", email);
		params.put("username", username);
		params.put("md5_once_password", ConvergeUtil.md5(password));
		params.put("ip_address", ipAddress);
		params.put("unix_join_date", ConvergeUtil.dateToUnixTimestamp(new Date()));
		params.put("validating", validating ? 1 : 0);
		params.put("extra", extraData);
		params.put("flag", flag ? 1 : 0);
		
		log.info("Sending convergeAddMember request to IP.Converge");
		log.fine(params.toString());
		
		@SuppressWarnings("unchecked")
		Map<String,Object> xmlResponse = 
			(Map<String,Object>)rpcClient.invoke("convergeAddMember", new Object[]{params});
		
		Integer complete = (Integer)xmlResponse.get("complete");
		if( complete == null || complete != 1 )
			log.warning("Response parameter [complete] is not equal to 1");
		
		String response = (String)xmlResponse.get("response");
		
		if( "MISSING_DATA".equals(response) ){
			return AddMemberResponse.MissingData;
		}
		else if( "EMAIL_IN_USE".equals(response) ){
			return AddMemberResponse.EmailInUse;
		}
		else if( "SUCCESS".equals(response) ){
			return AddMemberResponse.Success;
		}
		else{
			log.log(Level.WARNING, "Unrecognized response code", response);
			return null;
		}
	}

}
