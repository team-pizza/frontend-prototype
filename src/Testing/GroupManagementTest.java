package Testing;

import static org.junit.jupiter.api.Assertions.*;

import Objects.Database;

import Objects.Group;

import Objects.Account;

import org.junit.jupiter.api.Test;

import Data_Management.GroupManagement;

/**
 * Title: GroupManagementTest
 * Description: This JUnit Test Class is used to test the GroupManagement class and its functions
 * @author Paige Yon
 */
class GroupManagementTest {

	/**
	 * This function, testGroupManagement, is used to test the constructor of the GroupManagement class
	 */
	@Test
	void testGroupManagement() {
		// Initializes Data
		Account testOwner = new Account("Owner");
		Group newGroup = new Group(testOwner, "Test");
		Database data = new Database();
		
		// Begins Testing
		GroupManagement manager = new GroupManagement(newGroup, data);
		
		// Checks Results
		assertEquals(newGroup, manager.returnGroup());
	}
	
	/**
	 * This function, testReturnGroup, is used to test the GroupManagement's returnGroup function
	 */
	@Test
	// Tests returning the group management's group
	void testReturnGroup() {
		// Initializes Data
		Account testOwner = new Account("Owner");
		Group group = new Group(testOwner, "Test");
		Database data = new Database();
		
		// Begins Testing
		GroupManagement manager = new GroupManagement(group, data);
		
		// Checks Results
		assertEquals(group, manager.returnGroup());
	}

	/**
	 * This function, testSetGroup, is used to test the GroupManagement's setGroup function
	 */
	@Test
	// Tests setting the group management's group
	void testSetGroup() {
		// Initializes Data
		Account testOwner = new Account("Owner");
		Group oldGroup = new Group(testOwner, "Test");
		Database data = new Database();
		Group newGroup = new Group();
		
		// Begins Testing
		GroupManagement manager = new GroupManagement(oldGroup, data);
		manager.setGroup(newGroup);
		
		// Checks Results
		assertEquals(newGroup, manager.returnGroup());
	}

	/**
	 * This function, testDeleteGroupCreator, is used to test the GroupManagement's deleteGroup function as the Group's Creator
	 */
	@Test
	// Tests deleting group as creator/owner
	void testDeleteGroupCreator() {
		// Initializes Data
		Account testOwner = new Account("Owner");
		Account testMember = new Account("Member1");
		
		Group newGroup = new Group(testOwner, "Test1");
		Group deleteGroup = new Group(testOwner, "DeleteMe");
		deleteGroup.addGroupMember(testMember);
		
		// Sets up database
		Database data = new Database();
		data.addGroup(newGroup);
		data.addGroup(deleteGroup);
		data.addUser(testOwner);
		data.addUser(testMember);
		
		// Begins Testing
		GroupManagement manager = new GroupManagement(deleteGroup, data);
		manager.deleteGroup(testOwner, deleteGroup);
		
		// Checks Results; Ensures deleteGroup's data does not exist in database
		assertEquals(null, manager.returnGroup());
		assertNotEquals(-1, data.returnGroupList().indexOf(newGroup));
		assertEquals(-1, data.returnGroupList().indexOf(deleteGroup));
		// Ensures deleteGroup's data does not exist in the group member's data
		assertNotEquals(-1, data.returnAccountList().indexOf(testOwner));
		assertNotEquals(-1, data.returnAccountList().indexOf(testMember));
		assertNotEquals(-1, data.returnUser(testOwner).returnGroupList().indexOf(newGroup));
		assertEquals(-1, data.returnUser(testOwner).returnGroupList().indexOf(deleteGroup));
		assertEquals(-1, data.returnUser(testMember).returnGroupList().indexOf(deleteGroup));
	}
	
	/**
	 * This function, testDeleteNewGroupCreator, is used to test the GroupManagement's deleteGroup function as the Group's Creator
	 * It additionally makes the GroupManagement switch group data to manage the requested info
	 */
	@Test
	void testDeleteNewGroupCreator() {
		// Initializes Data
		Account testOwner1 = new Account("Owner");
		Account testOwner2 = new Account("Member1");
		
		Group newGroup = new Group(testOwner1, "Test1");
		Group deleteGroup = new Group(testOwner2, "DeleteMe");
		deleteGroup.addGroupMember(testOwner1);
		
		// Sets up database/
		Database data = new Database();
		data.addGroup(newGroup);
		data.addGroup(deleteGroup);
		data.addUser(testOwner1);
		data.addUser(testOwner2);
		
		// Begins Testing
		GroupManagement manager = new GroupManagement(newGroup, data);
		manager.deleteGroup(testOwner2, deleteGroup);
		
		
		// Checks Results; Ensures deleteGroup's data does not exist in database
		assertEquals(null, manager.returnGroup());
		assertNotEquals(-1, data.returnGroupList().indexOf(newGroup));
		assertEquals(-1, data.returnGroupList().indexOf(deleteGroup));
		// Ensures deleteGroup's data does not exist in the group member's data
		assertNotEquals(-1, data.returnAccountList().indexOf(testOwner1));
		assertNotEquals(-1, data.returnAccountList().indexOf(testOwner2));
		assertNotEquals(-1, data.returnUser(testOwner1).returnGroupList().indexOf(newGroup));
		assertEquals(-1, data.returnUser(testOwner1).returnGroupList().indexOf(deleteGroup));
		assertEquals(-1, data.returnUser(testOwner2).returnGroupList().indexOf(deleteGroup));
	}
	
	/**
	 * This function, testDeleteGroupMember, is used to test the GroupManagement's deleteGroup function as the Group's member
	 */
	@Test
	void testDeleteGroupMember() {
		// Initializes Data
		Account testOwner = new Account("Owner");
		Account testMember = new Account("Member");
		Group newGroup = new Group(testOwner, "Test");
		Database data = new Database();
		
		//Begins testing
		GroupManagement manager = new GroupManagement(newGroup, data);
		manager.deleteGroup(testMember, newGroup);
		
		// Checks Results; newGroup should not be deleted
		assertEquals(newGroup, manager.returnGroup());
	}
	
	/**
	 * This function, testDeleteNewGroupMember, is used to test the GroupManagement's deleteGroup function as the Group's member
	 * It additionally makes the GroupManagement switch group data to manage the requested info
	 */
	@Test
	void testDeleteNewGroupMember() {
		// Initializes Data
		Account testOwner = new Account("Owner");
		Account testMember = new Account("Member");
		Group oldGroup = new Group(testOwner, "Test");
		Group newGroup = new Group();
		Database data = new Database();
		
		// Begins Testing
		GroupManagement manager = new GroupManagement(oldGroup, data);
		manager.deleteGroup(testMember, newGroup);
		
		// Checks Results; newGroup should not be deleted
		assertNotEquals(oldGroup, manager.returnGroup());
		assertEquals(newGroup, manager.returnGroup());
	}

}
