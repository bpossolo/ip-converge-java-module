package net.styleguise.converge;

public class TestableConvergeServiceDelegate implements ConvergeServiceDelegate {

	public boolean handshakeStart(HandshakeStartRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean handshakeEnd(HandshakeEndRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean handshakeRemove(HandshakeRemoveRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onMemberDelete(OnMemberDeleteRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onPasswordChange(OnPasswordChangeRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onEmailChange(OnEmailChangeRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onUsernameChange(OnUsernameChangeRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onValidate(OnValidateRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	public GetMembersInfoResponse getMembersInfo(ConvergeApiRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public ImportMembersResponse importMembers(ImportMembersRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public ConvergeLoginResponse convergeLogin(ConvergeLoginRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public ConvergeLogoutResponse convergeLogout(ConvergeLogoutRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProductName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProductCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPHPUtilUrlEndpoint() {
		return "http://www.styleguise.net/utils/json-to-php-array.php";
	}

}
