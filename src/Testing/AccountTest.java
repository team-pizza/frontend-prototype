package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.Test;

import Objects.Account;
import Objects.Group;

class AccountTest {
	@Test
	// Also tests getName()
	void testAccount() {
		Account test = new Account("Success");
		assertEquals("Success", test.getName());
	}

	@Test
	void testSetUserName() {
		Account test = new Account("Fail");
		test.setUserName("Success");
		assertEquals("Success", test.getName());
		assertEquals(0, test.listOfGroups().size());
	}

	@Test
	// Also tests listofGroups()
	void testSetGroupList() {
		Account test = new Account("Success");
		Vector<Group> testList = new Vector<Group>();
		testList.addElement(new Group(test, "Test1"));
		test.setGroupList(testList);
		assertEquals(testList, test.listOfGroups());
	}

	@Test
	void testAddGroup() {
		Account test = new Account("Success");
		Group testGroup = new Group(test, "Test1");
		Vector<Group> testList = new Vector<Group>();
		
		test.addGroup(testGroup);
		testList.addElement(testGroup);
		
		assertEquals(testList, test.listOfGroups());
	}

	@Test
	void testRemoveGroup() {
		Account test = new Account("Success");
		Vector<Group> testList = new Vector<Group>();
		Group testGroup1 = new Group(test, "Test1");
		Group testGroup2 = new Group(test, "Test2");
		Group testGroup3 = new Group(test, "Test3");
		
		testList.addElement(testGroup1);
		testList.addElement(testGroup2);
		testList.addElement(testGroup3);
		
		test.setGroupList(testList);
		test.removeGroup(testGroup2);
		
		assertNotEquals(test.listOfGroups(), testList);
		assertNotEquals(-1, test.listOfGroups().indexOf(testGroup1));
		assertNotEquals(-1, test.listOfGroups().indexOf(testGroup3));
		assertEquals(-1, test.listOfGroups().indexOf(testGroup2));
	}

}
