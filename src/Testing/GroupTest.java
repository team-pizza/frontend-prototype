package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.Test;

import Objects.Group;

import Objects.Account;

/**
 * Title: GroupTest
 * Description: This JUnit Test Class is used to test the Group class and its functions
 * @author Paige Yon
 */
class GroupTest {

	/**
	 * This function, testGroup, is used to test the empty constructor of the GroupManagement class
	 */
	@Test
	void testGroup() {
		// Initialize Data
		Group newGroup = new Group();
		
		assertEquals("", newGroup.returnGroupName());
		assertEquals("", newGroup.returnOwner());
	}
	
	/**
	 * This function, testGroupAccountString, is used to test the override constructor of the GroupManagement class
	 */
	@Test
	void testGroupAccountString() {
		// Initialize Data
		Account testOwner = new Account("Owner");
		Group newGroup = new Group(testOwner, "TestGroup");
		
		// Check Results
		assertEquals("TestGroup", newGroup.returnGroupName());
		assertEquals("Owner", newGroup.returnOwner());
		assertNotEquals(-1, newGroup.returnMemberList().indexOf(testOwner));
	}

	/**
	 * This function, testReturnGroupName, is used to test the Group class's returnGroupName function
	 */
	@Test
	void testReturnGroupName() {
		// Initialize Data
		Account testOwner = new Account("Owner");
		
		// Begin Testing
		Group newGroup = new Group(testOwner, "TestGroup");
		
		// Check Results
		assertEquals("TestGroup", newGroup.returnGroupName());
	}

	/**
	 * This function, testReturnOwner, is used to test the Group class's returnOwner function
	 */
	@Test
	void testReturnOwner() {
		// Initialize Data
		Account testOwner = new Account("Owner");
		
		// Begin Testing
		Group newGroup = new Group(testOwner, "TestGroup");
		
		// Check Results
		assertEquals("Owner", newGroup.returnOwner());
	}

	/**
	 * This function, testReturnGroupMembers, is used to test the Group class's returnMemberList function
	 */
	@Test
	void testReturnGroupMembers() {
		// Initialize Data
		Account testOwner = new Account("Owner");
		Account testMember1 = new Account("Member1");
		Account testMember2 = new Account("Member2");
		
		// Create Account Vector array for expected outcome
		Vector<Account> expectedOutcome = new Vector<Account>();
		expectedOutcome.addElement(testOwner);
		expectedOutcome.addElement(testMember1);
		expectedOutcome.addElement(testMember2);
		
		// Begin Testing
		Group newGroup = new Group(testOwner, "TestGroup");
		newGroup.addGroupMember(testMember1);
		newGroup.addGroupMember(testMember2);
		
		// Check Results
		assertEquals(expectedOutcome, newGroup.returnMemberList());
	}

	/**
	 * This function, testSetOwner, is used to test the Group class's setOwner function
	 */
	@Test
	void testSetOwner() {
		// Initialize Data
		Group newGroup = new Group();
		
		// Begin Testing
		newGroup.setOwner("newOwner");
		
		// Check Results
		assertEquals("newOwner", newGroup.returnOwner());
	}

	/**
	 * This function, testSetGroupNameAllowed, is used to test the Group class's setGroupName function as the group's creator
	 */
	@Test
	void testSetGroupNameAllowed() {
		// Initialize Data
		Account testOwner = new Account("Owner");
		
		// Begin Testing
		Group newGroup = new Group(testOwner, "TestGroup");
		newGroup.setGroupName("Success", testOwner.returnName());
		
		// Check Results
		assertEquals("Success", newGroup.returnGroupName());
	}
	
	/**
	 * This function, testSetGroupNameAllowed, is used to test the Group class's setGroupName function as the group's member
	 */
	void testSetGroupNameDenied() {
		// Initialize Data
		Account testOwner = new Account("Owner");
		Account testMember = new Account("Member");
		
		// Begin Testing
		Group newGroup = new Group(testOwner, "TestGroup");
		newGroup.setGroupName("Success", testMember.returnName());
		
		// Check Results; Name should not be changed
		assertNotEquals("Success", newGroup.returnGroupName());
	}

	/**
	 * This function, testAddGroupMember is used to test the Group class's addGroupMember function
	 */	
	@Test
	void testAddGroupMember() {
		// Initialize Data
		Account testOwner = new Account("Owner");
		Account newMember = new Account("Member");
		
		// Begin Testing
		Group newGroup = new Group(testOwner, "TestGroup");
		newGroup.addGroupMember(newMember);

		// Check results; Ensure new member has group added to their account list as well
		assertNotEquals(-1, newGroup.returnMemberList().indexOf(newMember));
		assertNotEquals(-1, newMember.returnGroupList().indexOf(newGroup));
	}

}
