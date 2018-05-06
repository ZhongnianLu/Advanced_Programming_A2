package Model;
import java.util.ArrayList;

import Exceptions.NotToBeCoupledException;

/*
 * Author: Zhongnian Lu s3512993
 * 
 * This class creates restrictions for adding couple connection.
 * 
 */

public class Couple_Connection extends Connection{

	//Constructor from super class	
	public Couple_Connection(Profile person1, Profile person2) {
		
		super(person1, person2);
	}

	
	//Override check method from super class. 
	//Each person in a couple can't exist in other couple connections.
	@Override
	public void check(ArrayList<Connection> c_list) throws NotToBeCoupledException {
		
		//Check legal age
		if(getPerson1().getAge() < 16 || getPerson2().getAge() <16 ) {
			
			throw new NotToBeCoupledException("At least one person in this "
					+ "couple connection is not in legal age");
		}
		
		
		// Check whether person in this couple connection is repeated in other couple connections
		for(int i = 0;i < c_list.size();i++) {
			
			Profile person_x;	
			Profile person_y;
			
			//create a boolean to help find target connection that contains two IDs we inputed 
			person_x = c_list.get(i).getPerson1();
			
			person_y = c_list.get(i).getPerson2();
			
			
			if(c_list.get(i) instanceof Couple_Connection) {
				
		    	if((person_x.getID() == getPerson1().getID() || person_y.getID() == getPerson2().getID())
		    			|| (person_x.getID() == getPerson2().getID() || person_y.getID() == getPerson1().getID())
		    			) {
				
		    		throw new NotToBeCoupledException("At least one person in this connectionn is already "
		    				+ "in a couple connection");
			    }
		    }
		}
	}
}

	
	

