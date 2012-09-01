package net.styleguise.converge;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Represents the service methods that the IP.Converge application will
 * invoke on the remote application.
 * 
 * Application integrators are responsible for providing an implementation of this interface.
 * 
 * Implementations must be thread-safe.
 * 
 * @author Benjamin Possolo
 */
public interface ConvergeServiceDelegate {
	
	void init(ServletContext context) throws ServletException;
	
	boolean handshakeStart(HandshakeStartRequest request);
	
	boolean handshakeEnd(HandshakeEndRequest request);
	
	boolean handshakeRemove(HandshakeRemoveRequest request);

	boolean onMemberDelete(OnMemberDeleteRequest request);
	
	boolean onPasswordChange(OnPasswordChangeRequest request);
	
	boolean onEmailChange(OnEmailChangeRequest request);
	
	boolean onUsernameChange(OnUsernameChangeRequest request);
	
	boolean onValidate(OnValidateRequest request);
	
	boolean onRegister(OnRegisterRequest request);
	
	/**
	 * This service method is called by IPConverge before importing members
	 * and also when testing its connection to the remote app.
	 * @param request the request parameters sent by IPConverge
	 * @return info about the members contained in the remote app
	 */
	GetMembersInfoResponse getMembersInfo(ConvergeApiRequest request);
	
	ImportMembersResponse importMembers(ImportMembersRequest request);
	
	ConvergeLoginResponse convergeLogin(ConvergeLoginRequest request);
	
	ConvergeLogoutResponse convergeLogout(ConvergeLogoutRequest request);
	
	String getProductName();
	
	/**
	 * The remote applications product code.
	 * Product code length must be <= 9 characters.
	 * Must match product code on Converge server in file:
	 * CONVERGE_HOME/api/modules/<PRODUCT_CODE>/<PRODUCT_CODE>.php
	 * @return the product code
	 */
	String getProductCode();
	
	ConvergeHandshakeInfo getConvergeHandshakeInfo(String authKey, int productId);
	
	/**
	 * The URL of the php script which converts json into base-64 encoded php arrays.
	 */
	String getPHPUtilUrlEndpoint();

}
