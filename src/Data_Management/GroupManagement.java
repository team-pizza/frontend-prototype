package Data_Management;

import Objects.Group;
import java.util.Vector;
import Objects.Account;
import Objects.Database;

/**
 * Title: GroupManagement
 * Description: The class is used to manage Group data sent from the database
 * Currently this class can delete the Group data from the database
 * @author Paige Yon
 */
public class GroupManagement {
	// Holds the requested group for data manipulation
	private Group group;
	// Holds the database where the group information comes from
	private Database database;
	
	/**
	 * This is the public constructor of the Group Management class
	 * The requested group to manage is passed in along with the database data
	 * @param request | Group
	 * @param data | Database
	 */
	public GroupManagement(Group request, Database data){
		group = request;
		database = data;
	}
	
	/**
	 * This function, returnGroup, is used to return the Group object currently in GroupManagement object
	 * It is used mainly for testing purposes
	 * @return group | Group
	 */
	public Group returnGroup() {
		return group;
	}
	
	/**
	 * This function, setGroup, is used to change the Group object within the GroupManagement object using the param Group object
	 * This ensures the GroupManagement object can be re-used
	 * @param newGroup
	 */
	public void setGroup(Group newGroup) {
		group = newGroup;
	}
	
	/**
	 * This function, DeleteGroup, is used to delete a group from the database and its member's group lists
	 * The request must be from the Group's owner's account. Otherwise, the request will be denied.
	 * If the requested group is not currently in the manager, the setGroup function is called.
	 * @param requestor | Account
	 * @param requestedGroup | Group
	 */
	public void DeleteGroup(Account requestor, Group requestedGroup) {
		// Sets the GroupManagement object's group to the requested group if the param object and internal Group object are not a match
		if(!requestedGroup.returnGroupName().contentEquals(group.returnGroupName())) {
			this.setGroup(requestedGroup);
		}
		
		// If the CheckOwner function returns true, the request is permitted
		if(this.CheckOwner(requestor.returnName())==true) {
			// Calls the deleteMembers function to grab the users' information from the database and remove the group to be deleted from their lists
			this.deleteMembers(database, group);
			// Calls the deleteGroupData function to delete the group's information from the database
			this.deleteGroupData(database, group);
			// Sets the manager's internal group to null as the group data has finished being deleted
			this.setGroup(null);
		}
		// If the request is denied, this branch is used
		else {
			// When transferred to Front-End, this will be turned into a custom exception/toast message
			System.out.println("Error: User does not have Admin permission. Request Denied ");
		}
		
	}
	
	/*
	 * This private function, CheckOwner, is used to check in the passed in gmail string to the Group's current owner string
	 * Returns true if the strings are a match
	 * Returns false if the strings are not a match
	 */
	private boolean CheckOwner(String requestor){
		// If the group owner's string matches the requestor's string, this branch is used
		if(group.returnOwner().contentEquals(requestor)) {
			return true;
		}
		// If the information is not a match, this branch is used
		else {
			return false;
		}
	}
	
	/*
	 * This private function, deleteMembers, grabs the Group's members information from the database
	 * It then removes the group from each member's list and updates their entries in the database
	 */
	// Will have to change when integrated into the application to access the database
	private void deleteMembers(Database data, Group group) {
		// Holds the Group Members info
		Vector<Account> groupMembers = group.returnMemberList();
		// Removes the group from the member's list
		groupMembers.forEach(member -> member.removeGroup(group));
		// Updates the group's information in the database
		groupMembers.forEach(member->data.updateUser(member));
	}
	
	/*
	 * This private function, deletes the Group's data from the database
	 */
	// Will have to change when integrated into the application to access the database
	private void deleteGroupData(Database data, Group group) {
		data.removeGroup(group);
	}
}
