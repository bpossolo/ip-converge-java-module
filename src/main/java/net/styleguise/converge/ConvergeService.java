package net.styleguise.converge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * 
 * @author Benjamin Possolo
 */
public class ConvergeService {
	
	private static final Logger log = Logger.getLogger(ConvergeService.class.getName());
	
	/**
	 * Jackson object mapper for serializing objects to JSON.
	 * The object mapper is thread-safe.
	 */
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private ConvergeServiceDelegate delegate;
	
	ConvergeService(ConvergeServiceDelegate delegate){
		this.delegate = delegate;
	}
	
	public String convergeInfoXml(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("<info>\n");
		sb.append("\t<productname>").append(delegate.getProductName()).append("</productname>\n");
		sb.append("\t<productcode>").append(delegate.getProductCode()).append("</productcode>\n");
		sb.append("</info>");
		return sb.toString();
	}
	
	public Map<String,Object> handshakeStart(Map<String,Object> params){
		
		log.info("Handshake Start");
		if( log.isLoggable(Level.FINE) )
			log.fine(params.toString());
		
		HandshakeStartRequest req = new HandshakeStartRequest();
		req.setRegistrationId((Integer)params.get("reg_id"));
		req.setRegistrationCode((String)params.get("reg_code"));
		req.setRegistrationDate(ConvergeUtil.unixTimestampToDate((Integer)params.get("reg_date")));
		req.setProductId((Integer)params.get("reg_product_id"));
		req.setConvergeUrl((String)params.get("converge_url"));
		req.setConvergeAdminEmail((String)params.get("acp_email"));
		req.setConvergeAdminPasswordMd5((String)params.get("acp_md5_password"));
		req.setHtaccessUsername((String)params.get("http_user"));
		req.setHtaccessPassword((String)params.get("http_pass"));
		
		boolean masterResponse = delegate.handshakeStart(req);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("reg_id", params.get("reg_id"));
		result.put("reg_code", params.get("reg_code"));
		result.put("reg_date", params.get("reg_date"));
		result.put("reg_product_id", params.get("reg_product_id"));
		result.put("converge_url", params.get("converge_url"));
		result.put("acp_email", params.get("acp_email"));
		result.put("acp_md5_password", params.get("acp_md5_password"));
		result.put("http_user", params.get("http_user"));
		result.put("http_pass", params.get("http_pass"));
		result.put("master_response", masterResponse ? 1 : 0);
		
		return result;
	}
	
	public Map<String,Object> handshakeEnd(Map<String,Object> params){
		
		log.info("Handshake end");
		if( log.isLoggable(Level.FINE) )
			log.fine(params.toString());
		
		HandshakeEndRequest req = new HandshakeEndRequest();
		req.setRegistrationId((Integer)params.get("reg_id"));
		req.setRegistrationCode((String)params.get("reg_code"));
		req.setRegistrationDate(ConvergeUtil.unixTimestampToDate((Integer)params.get("reg_date")));
		req.setProductId((Integer)params.get("reg_product_id"));
		req.setConvergeUrl((String)params.get("converge_url"));
		
		if( ((Integer)params.get("handshake_completed")) == 1 )
			req.setHandshakeCompleted(true);
		else
			req.setHandshakeCompleted(false);
		
		boolean updated = delegate.handshakeEnd(req);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("handshake_updated", updated ? 1 : 0);
		
		return result;
	}
	
	public Map<String,Object> handshakeRemove(Map<String,Object> params){
		
		log.info("Handshake remove");
		if( log.isLoggable(Level.FINE) )
			log.fine(params.toString());
		
		HandshakeRemoveRequest req = new HandshakeRemoveRequest();
		req.setRegistrationCode((String)params.get("reg_code"));
		req.setProductId((String)params.get("reg_product_id"));
		
		boolean removed = delegate.handshakeRemove(req);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("handshake_removed", removed);
		
		return result;
	}
	
	public Map<String,Object> onMemberDelete(Map<String,Object> params){
		
		log.info("On member delete");
		if( log.isLoggable(Level.FINE) )
			log.fine(params.toString());
		
		OnMemberDeleteRequest req = new OnMemberDeleteRequest();
		req.setAuthKey((String)params.get("auth_key"));
		req.setProductId((String)params.get("product_id"));
		req.setAuth((String)params.get("auth"));
		
		boolean success = delegate.onMemberDelete(req);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("completed", new Integer(1));
		result.put("response", success ? "SUCCESS" : "FAILED");
		
		return result;
	}
	
	public Map<String,Object> onPasswordChange(Map<String,Object> params){
		
		log.info("On password change");
		if( log.isLoggable(Level.FINE) )
			log.fine(params.toString());
		
		OnPasswordChangeRequest req = new OnPasswordChangeRequest();
		req.setAuthKey((String)params.get("auth_key"));
		req.setProductId((String)params.get("product_id"));
		req.setAuth((String)params.get("auth"));
		req.setHashedPassword((String)params.get("hashed_password"));
		
		boolean success = delegate.onPasswordChange(req);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("completed", new Integer(1));
		result.put("response", success ? "SUCCESS" : "FAILED");
		
		return result;
	}
	
	public Map<String,Object> onEmailChange(Map<String,Object> params){
		
		log.info("On email change");
		if( log.isLoggable(Level.FINE) )
			log.fine(params.toString());
		
		OnEmailChangeRequest req = new OnEmailChangeRequest();
		req.setAuthKey((String)params.get("auth_key"));
		req.setProductId((String)params.get("product_id"));
		req.setAuth((String)params.get("auth"));
		req.setOldEmail((String)params.get("old_email_address"));
		req.setNewEmail((String)params.get("new_email_address"));
		
		boolean success = delegate.onEmailChange(req);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("completed", new Integer(1));
		result.put("response", success ? "SUCCESS" : "FAILED");
		
		return result;
	}
	
	public Map<String,Object> onUsernameChange(Map<String,Object> params){
		
		log.info("On username change");
		if( log.isLoggable(Level.FINE) )
			log.fine(params.toString());
		
		OnUsernameChangeRequest req = new OnUsernameChangeRequest();
		req.setAuthKey((String)params.get("auth_key"));
		req.setProductId((String)params.get("product_id"));
		req.setAuth((String)params.get("auth"));
		req.setOldUsername((String)params.get("old_username"));
		req.setNewUsername((String)params.get("new_username"));
		
		boolean success = delegate.onUsernameChange(req);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("completed", new Integer(1));
		result.put("response", success ? "SUCCESS" : "FAILED");
		
		return result;
	}
	
	public Map<String,Object> onValidate(Map<String,Object> params){
		
		log.info("On validate");
		if( log.isLoggable(Level.FINE) )
			log.fine(params.toString());
		
		OnValidateRequest req = new OnValidateRequest();
		req.setAuthKey((String)params.get("auth_key"));
		req.setProductId((String)params.get("product_id"));
		req.setAuth((String)params.get("auth"));
		
		boolean success = delegate.onValidate(req);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("completed", new Integer(1));
		result.put("response", success ? "SUCCESS" : "FAILED");
		
		return result;
	}
	
	public Map<String,Object> getMembersInfo(Map<String,Object> params){
		
		log.info("Get members info");
		if( log.isLoggable(Level.FINE) )
			log.fine(params.toString());
		
		ConvergeApiRequest req = new ConvergeApiRequest();
		req.setAuthKey((String)params.get("auth_key"));
		req.setProductId((String)params.get("product_id"));
		
		GetMembersInfoResponse response = delegate.getMembersInfo(req);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("count", response.getCount());
		result.put("last_id", response.getLastId());
		
		return result;
	}
	
	public Map<String,Object> importMembers(Map<String,Object> params) throws ConvergeException {
		
		try {
			log.info("Import members");
			if( log.isLoggable(Level.FINE) )
				log.fine(params.toString());
			
			ImportMembersRequest req = new ImportMembersRequest();
			req.setAuthKey((String)params.get("auth_key"));
			req.setProductId((String)params.get("product_id"));
			req.setOffset((Integer)params.get("limit_a"));
			req.setLimit((Integer)params.get("limit_b"));
			
			ImportMembersResponse response = delegate.importMembers(req);
			response.setComplete(1);
			
			String json = objectMapper.writeValueAsString(response);
			String encodedPhpArray = convertJsonToEncodedPhpArray(json);
			
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("__serialized64__", encodedPhpArray);
			
			return result;
		}
		catch(Exception e) {
			throw new ConvergeException("Failed to encode members", e);
		}
	}
	
	public Map<String,Object> convergeLogIn(Map<String,Object> params) throws ConvergeException {
		
		log.info("Converge login");
		if( log.isLoggable(Level.FINE) )
			log.fine(params.toString());
		
		ConvergeLoginRequest req = new ConvergeLoginRequest();
		req.setAuthKey((String)params.get("auth_key"));
		req.setProductId((String)params.get("product_id"));
		req.setEmail((String)params.get("email_address"));
		req.setUsername((String)params.get("username"));
		req.setHashedPassword((String)params.get("hashed_password"));
		req.setIpAddress((String)params.get("ip_address"));
		req.setJoinDate((String)params.get("unix_join_date"));
		req.setTimezone((String)params.get("timezone"));
		req.setDaylightSavingsTimeAutocorrect((String)params.get("dst_autocorrect"));
		
		ConvergeLoginResponse response = delegate.convergeLogin(req);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("complete", 1);
		result.put("response", response.isSuccess() ? "SUCCESS" : "FAILED");
		result.put("session_id", response.getSessionId());
		result.put("member_id", response.getMemberId());
		result.put("log_in_key", response.getLoginKey());
		result.put("__cookie__serialized64__", encodeCookies(response.getCookies()));
		
		return result;
	}
	
	public Map<String,Object> convergeLogOut(Map<String,Object> params) throws ConvergeException {
		
		log.info("Converge logout");
		if( log.isLoggable(Level.FINE) )
			log.fine(params.toString());
		
		ConvergeLogoutRequest req = new ConvergeLogoutRequest();
		req.setAuthKey((String)params.get("auth_key"));
		req.setProductId((String)params.get("product_id"));
		req.setAuth((String)params.get("auth"));
		
		ConvergeLogoutResponse response = delegate.convergeLogout(req);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("complete", new Integer(1));
		result.put("response", response.isSuccess() ? "SUCCESS" : "FAILED");
		result.put("__cookie__serialized64__", encodeCookies(response.getCookies()));
		
		return result;
	}
	
	String encodeCookies(List<ConvergeCookie> cookies) throws ConvergeException {
		try{
			String json = objectMapper.writeValueAsString(cookies);
			return convertJsonToEncodedPhpArray(json);
		}
		catch(IOException e){
			throw new ConvergeException("Failed to encode cookies", e);
		}
	}
	
	String convertJsonToEncodedPhpArray(String json) throws IOException {
		
		log.fine("Converting json to base-64 encoded php array");
		log.fine(json);
		
		byte[] jsonBytes = json.getBytes("UTF-8");
		
		URL url = new URL(delegate.getPHPUtilUrlEndpoint());
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(8000); //8 seconds
		conn.setDoOutput(true);
		conn.setReadTimeout(4000); //4 seconds
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Content-Length", Integer.toString(jsonBytes.length));
		
		OutputStream os = conn.getOutputStream();
		os.write(jsonBytes);
		os.flush();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while( (line = reader.readLine()) != null )
			sb.append(line);
		
		os.close();
		reader.close();
		
		String encodedPhpArray = sb.toString();
		log.fine("Received encoded php array");
		log.fine(encodedPhpArray);
		return encodedPhpArray;
	}
}
