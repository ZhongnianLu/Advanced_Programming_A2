package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Exceptions.NoAvailableException;
import Exceptions.NoParentException;
import Exceptions.NoSuchAgeException;
import Exceptions.NotToBeCoupledException;
import Exceptions.NotToBeFriendsException;
import Exceptions.ProfileNotFoundException;
import Exceptions.RepeatException;
import Exceptions.TooYoungException;
import Interfaces.ConnectionManager;
import Interfaces.ProfileManager;
import Model.ConnectionManagerImpl;
import Model.Profile;
import Model.ProfileManagerImpl;

/*
 * A junit test class to test restrictions built in ConnectionManager
 * 
 * 
 */
class ConnectionManagerImplTest {
	
	static ProfileManager profiles;
	
	static ConnectionManager conns;
	
	

	@BeforeAll
	static void beforeAllTest() {
		
		profiles = new ProfileManagerImpl();  
		
		conns = new ConnectionManagerImpl(); 
		conns.setPmanager(profiles);
			
		Profile p1 = new Profile("A", 19);
		
		Profile p2 = new Profile("B", 17);

		Profile p3 = new Profile("C", 15);

		Profile p4 = new Profile("D", 11);
		
		Profile p5 = new Profile("E",3);
		
		Profile p6 = new Profile("F", 10);
		
		Profile p7 = new Profile("G", 20);
		
		Profile p8 = new Profile("H", 30);
		
		Profile p9 = new Profile("I", 40);
		
		Profile p10 = new Profile("J", 50);

			
		try {
			profiles.addProfile(p1);
			profiles.addProfile(p2);
			profiles.addProfile(p3);
			profiles.addProfile(p4);
			profiles.addProfile(p5);
			profiles.addProfile(p6);
			profiles.addProfile(p7);
			profiles.addProfile(p8);
			profiles.addProfile(p9);
			profiles.addProfile(p10);

		} catch (NoSuchAgeException | NoAvailableException e1) {
			System.out.println(e1.getMessage());
			}
		

	}
	
	@Test
	void testAddConnectionIntIntInt1() {
		
		try {
			conns.addConnection("B","A","friends");
		} catch (Exception e) {
			e.getMessage();

		}
		
		assertEquals("B", conns.get_Clist().get(0).getPerson1().getName());

		
	}
	
	@Test
	void testAddConnectionIntIntInt2() {

		
		assertThrows(TooYoungException.class, () -> conns.addConnection("B","D","friends"));

	}
	
	@Test
	void testAddConnectionIntIntInt3() {
		
		assertThrows(NotToBeFriendsException.class, () -> conns.addConnection("C","D","friends"));
	}
	
	@Test
	void testAddConnectionIntIntInt4() {
		
		try {
			conns.addConnection("A","B","couple");
		} catch (Exception e) {
			e.getClass()	;	
		}
		
		assertThrows(NotToBeCoupledException.class, () -> conns.addConnection("A","I","couple"));
	}
	
	@Test
	void testAddConnectionIntIntInt5() {
		assertThrows(NoAvailableException.class, () -> conns.addConnection("D","G","couple"));


	}

	@Test
	void testAddParentConnection() {
	
		assertThrows(NoParentException.class, () -> conns.addParentConnection("A","E"));
	}
	
	@Test
	void testAddChildConnection() {
		
		try {
		
			conns.addParentConnection("I","F");

		} catch (NoParentException e) {
			e.printStackTrace();
		} catch (RepeatException e) {
			e.printStackTrace();
		} catch (ProfileNotFoundException e) {
			e.printStackTrace();
		} catch (NotToBeFriendsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		assertThrows(NotToBeFriendsException.class, () -> conns.addConnection("F","D","friends"));
	}

	
	@Test
	void testRemoveConnections() {
		
		try {
			conns.addConnection("J","I","couple");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			conns.addParentConnection("I","D");
		} catch (NoParentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepeatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProfileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotToBeFriendsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThrows(NoParentException.class, () -> conns.removeConnections("I"));
	}

}
