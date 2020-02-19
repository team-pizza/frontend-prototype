package Data_Management;

import Objects.Group;

import java.util.Vector;

import Objects.Account;
import Objects.Database;

public class GroupManagement {
	public Group group;
	private Database database;
	
	public GroupManagement(Group data){
		group = data;
	}
	
	public void setGroup(Group newGroup) {
		group = newGroup;
	}
	
	// Deletes group from its member's list and database
	public void DeleteGroup(String requestor, Group requestedGroup) {
		if(!requestedGroup.displayGroupName().contentEquals(group.displayGroupName())) {
			this.setGroup(requestedGroup);
		}
		
		if(this.CheckOwner(requestor)==true) {
			deleteMembers(database, group);
			deleteGroupData(database, group);
		}
		else {
			System.out.println("Error: User does not have Admin permission. Request Denied ");
		}
		
	}
	
	// Checks to make sure the user
	private boolean CheckOwner(String requestor){
		if(group.displayOwner().contentEquals(requestor)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	// Will have to change when integrated into the application to access the database
	// Use group emails to grab their accounts from the database and delete the group from their list
	private void deleteMembers(Database data, Group group) {
		Vector<Account> groupMembers = group.listGroupMembers();
		groupMembers.forEach(member -> member.removeGroup(group));
		groupMembers.forEach(member->data.updateUser(member));
	}
	
	// Will have to change when integrated into the application to access the database
	// Use to delete group from known groups in database
	private void deleteGroupData(Database data, Group group) {
		data.removeGroup(group);
	}
}
