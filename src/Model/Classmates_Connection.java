package Model;

import java.util.ArrayList;

import Exceptions.NotToBeClassmatesException;

/*
 * Represent class connection.
 * 
 */
public class Classmates_Connection extends Connection{

	public Classmates_Connection(Profile person1, Profile person2) {
		super(person1, person2);
	}

	
	@Override
	//babies can't become classmates.
	public void check(ArrayList<Connection> c_list) throws NotToBeClassmatesException {
		
		if(getPerson1().getAge() < 3 || getPerson2().getAge() < 3) {
			throw new NotToBeClassmatesException("Can't make a classmate with a young child. ");
		}
	}
}
