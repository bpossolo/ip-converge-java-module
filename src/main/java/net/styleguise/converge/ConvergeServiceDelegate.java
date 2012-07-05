package net.styleguise.converge;

/**
 * Represents the service methods that the IP.Converge installation will remotely
 * invoke on the user's application.
 * 
 * Application integrators are responsible for providing an implementation of this interface.
 * 
 * Implementations must be thread-safe.
 * 
 * @author Benjamin Possolo
 */
public interface ConvergeServiceDelegate {
	
	boolean handshakeStart(HandshakeStartRequest request);
	
	boolean handshakeEnd(HandshakeEndRequest request);
	
	boolean handshakeRemove(HandshakeRemoveRequest request);

	boolean onMemberDelete(OnMemberDeleteRequest request);
	
	boolean onPasswordChange(OnPasswordChangeRequest request);
	
	boolean onEmailChange(OnEmailChangeRequest request);
	
	boolean onUsernameChange(OnUsernameChangeRequest request);
	
	boolean onValidate(OnValidateRequest request);
	
	GetMembersInfoResponse getMembersInfo(ConvergeApiRequest request);
	
	ImportMembersResponse importMembers(ImportMembersRequest request);
	
	ConvergeLoginResponse convergeLogin(ConvergeLoginRequest request);
	
	ConvergeLogoutResponse convergeLogout(ConvergeLogoutRequest request);
	
	String getProductName();
	
	String getProductCode();
	
	/**
	 * The URL of the php script which converts json into base-64 encoded php arrays.
	 */
	String getPHPUtilUrlEndpoint();

}
