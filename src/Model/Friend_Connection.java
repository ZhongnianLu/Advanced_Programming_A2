package Model;
import java.util.ArrayList;

import Exceptions.NotToBeFriendsException;
import Exceptions.TooYoungException;

/*
 * Author: Zhongnian Lu s3512993
 * 
 * This class is a subclass of abstract class Connection and represent friend relationship. 
 */

public  class Friend_Connection extends Connection{

	//Constructor inheritance from super class
	public Friend_Connection(Profile person1, Profile person2) {
	    
		super(person1, person2);
	}

	
	
	//Override check method from super class. Friend connections include dependent friend and adult friend.
	public void check(ArrayList<Connection> c_list) throws NotToBeFriendsException, TooYoungException {
	
		//check dependent friend
		if(getPerson1().getAge() < 16 && getPerson2().getAge() < 16
				                      && getPerson1().getAge() > 2 
				                      && getPerson2().getAge() > 2) {
			
			if(getPerson1().getAge() > getPerson2().getAge()) {
				
				if(getPerson1().getAge() - getPerson2().getAge() > 3) {
					
					throw new NotToBeFriendsException("Can't make an adult and a child friend or connect two\n" + 
							" * children with an age gap larger than 3");
				}
			}
			
			if(getPerson1().getAge() < getPerson2().getAge()) {
				
				if(getPerson2().getAge() - getPerson1().getAge() > 3) {
				
					throw new NotToBeFriendsException("Can't make an adult and a child friend or connect two\n" + 
							" * children with an age gap larger than 3");				}
			}
			
		}else if(getPerson1().getAge() > 2 || getPerson2().getAge() > 2){
			
			throw new TooYoungException("Can't make friend with a young child");
		
		}else {
			
			throw new TooYoungException("Can't make friend with a young child");
		}
		
		
    }

}
	
	
	

