package Model;
import java.util.ArrayList;

import Exceptions.NotToBeColleaguesException;

/*
 * Represent colleague relationship
 * 
 */
public class Colleagues_Connection extends Connection{

	public Colleagues_Connection(Profile person1, Profile person2) {
		super(person1, person2);
	}

	
	@Override
	//children can't become colleagues
	public void check(ArrayList<Connection> c_list) throws NotToBeColleaguesException {
		
		if(getPerson1().getAge() < 16 || getPerson2().getAge() <16) {
			throw new NotToBeColleaguesException("Only adults can become coleagues.");
		}
	}
}
