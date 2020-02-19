package Objects;
import java.util.Vector;

public class Database {
	private Vector<Group> groupList;
	private Vector<Account> userList;
	
	public Database(Group sampleGroup){
		groupList.addElement(sampleGroup);
		userList = new Vector<Account>();
	}
	
	// Getter
	public Vector<Group> getGroup() {
		return groupList;
	}
	
	// returns vectors of groups attached to user's account
	public Vector<Account> listOfUsers() {	
		return userList;
	}
	
	
	// Setter
	public void setGroupList(Vector<Group> list) {
		groupList.clear();
		groupList.trimToSize();
		list.forEach(group -> groupList.addElement(group));
	}
	
	public void setAccountList(Vector<Account> list) {
		userList.clear();
		userList.trimToSize();
		list.forEach(user -> userList.addElement(user));
	}
	
	// Data Manipulation
	// For the Prototype Only
	public void addGroup(Group group) {
		groupList.addElement(group);
		groupList.trimToSize();
	}
	
	public void removeGroup(Group group) {
		groupList.remove(group);
		groupList.trimToSize();
	}
	
	public void addUser(Account user) {
		userList.addElement(user);
		userList.trimToSize();
	}
	
	public void removeUser(Account user) {
		userList.remove(user);
		userList.trimToSize();
	}
	
	public void updateUser(Account updatedUser) {
		int index = userList.indexOf(updatedUser);
		if (index == -1) {
			System.out.println("Error: User Does Not Exist in Database");
		}else {
			userList.set(index, updatedUser);
		}
	}
}
