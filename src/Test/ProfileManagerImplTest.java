package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Exceptions.NoSuchAgeException;
import Interfaces.ProfileManager;
import Model.Profile;
import Model.ProfileManagerImpl;

class ProfileManagerImplTest {

	ProfileManager profiles = new ProfileManagerImpl();  

	@BeforeAll
	static void beforeAllTest() {

	
		Profile p1 = new Profile("A", 19);
		p1.setID(1);
		
		Profile p2 = new Profile("AB", 17);
		p2.setID(2);

		Profile p3 = new Profile("B", 15);
		p3.setID(3);
		
	}
	
	
	@Test
	void testAddProfile() {
		Profile p4 = new Profile("D", 180);
		p4.setID(4);
		assertThrows(NoSuchAgeException.class, () -> profiles.addProfile(p4));
	}


}
