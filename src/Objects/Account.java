package Objects;
import java.util.Vector;

// Temp Account class
public class Account {
	// Holds account information
	public String username;
	private Vector<Group> groupList;
	
	// constructor
	public Account(String user){
		username = user;
		groupList = new Vector<Group>();
	}
	
	// Setters
	// sets username
	public void setUserName(String name) {
		username = name;
	}
	
	// set list of group objects
	public void setGroupList(Vector<Group> list) {
		groupList.clear();
		groupList.trimToSize();
		list.forEach(group -> groupList.addElement(group));
	}
	
	// Getters
	// returns username
	public String getName() {
		return username;
	}
	
	// returns vectors of groups attached to user's account
	public Vector<Group> listOfGroups() {	
		return groupList;
	}
	
	// Data Manipulation
	public void addGroup(Group group) {
		groupList.addElement(group);
		groupList.trimToSize();
	}
	
	public void removeGroup(Group group) {
		groupList.remove(group);
		groupList.trimToSize();
	}
	
}
