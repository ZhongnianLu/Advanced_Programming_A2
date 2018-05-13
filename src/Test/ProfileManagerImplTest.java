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
		
		Profile p2 = new Profile("AB", 17);

		Profile p3 = new Profile("B", 15);
		
	}
	
	
	@Test
	void testAddProfile() {
		Profile p4 = new Profile("D", 180);
		assertThrows(NoSuchAgeException.class, () -> profiles.addProfile(p4));
	}


}
