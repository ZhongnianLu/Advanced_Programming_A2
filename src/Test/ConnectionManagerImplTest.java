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

class ConnectionManagerImplTest {
	
	static ProfileManager profiles;
	
	static ConnectionManager conns;
	
	

	@BeforeAll
	static void beforeAllTest() {
		
		profiles = new ProfileManagerImpl();  
		
		conns = new ConnectionManagerImpl(); 
		conns.setPmanager(profiles);
			
		Profile p1 = new Profile("A", 19);
		p1.setID(1);
		
		Profile p2 = new Profile("AB", 17);
		p2.setID(2);

		Profile p3 = new Profile("B", 15);
		p3.setID(3);

		Profile p4 = new Profile("C", 11);
		p4.setID(4);
		
		Profile p5 = new Profile("D",3);
		p5.setID(5);
		
		Profile p6 = new Profile("E", 10);
		p6.setID(6);
		
		Profile p7 = new Profile("F", 20);
		p7.setID(7);
		
		Profile p8 = new Profile("G", 30);
		p8.setID(8);
		
		Profile p9 = new Profile("H", 40);
		p9.setID(9);
			
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
		} catch (NoSuchAgeException e1) {
			System.out.println(e1.getMessage());
			}
		

	}
	
	@Test
	void testAddConnectionIntIntInt1() {
		
		try {
			conns.addConnection(2,1,1);
		} catch (Exception e) {
			e.getMessage();

		}
		
		assertEquals(2, conns.get_Clist().get(0).getPerson1().getID());

		
	}
	
	@Test
	void testAddConnectionIntIntInt2() {

		
		assertThrows(TooYoungException.class, () -> conns.addConnection(2,4,1));

	}
	
	@Test
	void testAddConnectionIntIntInt3() {
		
		assertThrows(NotToBeFriendsException.class, () -> conns.addConnection(3,4,1));
	}
	
	@Test
	void testAddConnectionIntIntInt4() {
		
		try {
			conns.addConnection(1,2,2);
		} catch (Exception e) {
			e.getClass()	;	
		}
		
		assertThrows(NotToBeCoupledException.class, () -> conns.addConnection(1,9,2));
	}
	
	@Test
	void testAddConnectionIntIntInt5() {
		assertThrows(NoAvailableException.class, () -> conns.addConnection(4,7,2));


	}

	@Test
	void testAddParentConnection() {
	
		assertThrows(NoParentException.class, () -> conns.addParentConnection(1,7,5));
	}
	
	@Test
	void testAddChildConnection() {
		
		try {
		
			conns.addParentConnection(8,9,6);

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
	
		assertThrows(NotToBeFriendsException.class, () -> conns.addConnection(6,4,1));
	}

	
	@Test
	void testRemoveConnections() {
		
		try {
			conns.addConnection(8,9,2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			conns.addParentConnection(8,9,4);
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
		}
		assertThrows(NoParentException.class, () -> conns.removeConnections(9));
	}

}
