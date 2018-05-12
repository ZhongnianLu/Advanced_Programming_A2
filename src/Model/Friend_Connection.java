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
	
		int age_p1 = getPerson1().getAge();
		int age_p2 = getPerson2().getAge();

		
		//check dependent friend
	    if(age_p1 >= 16 && age_p2 >= 16) {
			
		}
		else if(age_p1 < 16 && age_p2 < 16
				                      && age_p1 > 2 
				                      && age_p2 > 2) {
			
			if(age_p1 > age_p2) {
				
				if(age_p1 - age_p2 > 3) {
					
					throw new NotToBeFriendsException("Can't connect two" + 
							" children with an age gap larger than 3");
				}
			}
			
			if(age_p1 < age_p2) {
				
				if(age_p2 - age_p1 > 3) {
				
					throw new NotToBeFriendsException("Can't connect two"
							+ " children with an age gap larger than 3");				
				}
			}
			
		}else if(age_p1 < 2 || age_p2 < 2){
			
			throw new NotToBeFriendsException("Can't make friend with a young child");
		
		}else {
			
			throw new TooYoungException("Adult can't make friend with a teenager");
		}
		
		
    }




}
	
	
	

