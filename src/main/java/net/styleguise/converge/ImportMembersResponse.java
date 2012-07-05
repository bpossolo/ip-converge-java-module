package net.styleguise.converge;

import java.util.HashMap;
import java.util.Map;

public class ImportMembersResponse {
	
	private int complete;
	
	private Map<Integer,ConvergeMember> members;
	
	public void addMember(Integer memberId, ConvergeMember member){
		if( members == null )
			members = new HashMap<Integer, ConvergeMember>();
		members.put(memberId, member);
	}

	public int getComplete() {
		return complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	public Map<Integer, ConvergeMember> getMembers() {
		return members;
	}

	public void setMembers(Map<Integer, ConvergeMember> members) {
		this.members = members;
	}

}
