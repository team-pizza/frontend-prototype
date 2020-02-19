package Objects;
import java.util.Vector;

public class Group {
	public String groupName;
	private Vector<Account> groupMembers;
	private String groupOwner;
	
	// Empty Constructor
	public Group(){
		groupName = "";
		groupMembers = new Vector<Account>();
		groupOwner = "";
	}
	
	// Override Constructor
	public Group(Account creator, String name){
		groupName = name;
		groupOwner = creator.getName();
		
		groupMembers = new Vector<Account>();
		groupMembers.addElement(creator);
	}
	
	// Getters
	public String displayGroupName() {
		return groupName;
	}
	
	public String displayOwner() {
		return groupOwner;
	}
	
	public Vector<Account> listGroupMembers() {
		return groupMembers;
	}
	
	// Setters
	public void setOwner(String newOwner) {
		groupOwner = newOwner;
	}
	
	public void setGroupName(String newName, String requestor) {
		if(requestor.contentEquals(groupOwner)) {
			groupName = newName;
		}
		else {
			System.out.println("Error: User does not have Admin permission. Request Denied ");
		}
	}
	
	// Data Manipulation
	public void addGroupMember(Account newMember) {
		groupMembers.addElement(newMember);
	}
	
}
