package Model;
import java.util.ArrayList;

import Exceptions.NoParentException;
import Exceptions.RepeatException;

/*
 * Author: Zhongnian Lu s3512993
 * 
 * This class represents parent connection, it requires a couple connection.
 */


public class Parent_Connection extends Connection {

	// difference between parent relationship and friend relationship is child profile
	Profile child;
	
	ArrayList<Profile> linked_person = new ArrayList<Profile>();
	
	
	//Constructor should include a child in parent connection.
	public Parent_Connection(Profile person1, 
			                 Profile person2, 
			                 Profile child) {
		
		super(person1, person2);
		
		this.child = child;
	}
	
	
	//Access information about child 
	public Profile getChild() {
	
		return child;
	}
	
	
    // Check whether added parent is valid or not
	public void check(ArrayList<Connection> c_list) throws NoParentException {
		
		boolean success = false;
		
		//Go though the whole connection list to check if person1 and person2 are a couple.
		for(int i = 0;i < c_list.size();i++) {
			
			Profile person_x;
			Profile person_y;
			
			//create a boolean to help find target connection that contains two IDs we inputed 
			boolean connection_check = false;
			
			person_x = c_list.get(i).getPerson1();
			
			person_y = c_list.get(i).getPerson2();
			
			
			if(person_x.getName().equals(getPerson1().getName()) 
					&& person_y.getName().equals(getPerson2().getName())) {
				
				connection_check = true;
			
			}
			
			if(person_x.getName().equals(getPerson2().getName()) 
					&& person_y.getName().equals(getPerson1().getName())) {
				
				connection_check = true;
			
			}
			
			//check whether the connection we found is a couple connection
			if(c_list.get(i) instanceof Couple_Connection 
					&& connection_check == true && age_check() == true) {
				
				success = true;
			}
		}	
		
		if(success == false) {
			
			throw new NoParentException("Parent are not couple.");
		}
	}
	
	
	//To make sure all children is older than parents.
	private boolean age_check() throws NoParentException {
		
		boolean success = true;
		
		if(child.getAge() > getPerson1().getAge() || child.getAge() > getPerson2().getAge()) {
			success = false;
			throw new NoParentException("Child can't be older than parent.");
			
		}
		return success;
	}


	// Show difference with normal connection due to the involvement of child.
	 public void repeat_check(ArrayList<Connection> c_list) throws RepeatException {
		
		boolean child_repeat = false;
		
		for(int i = 0;i < c_list.size();i++) {
			
			if(c_list.get(i) instanceof Parent_Connection) {
			
    			if(c_list.get(i).getChild().getName().equals(getChild().getName())){
				
	    			child_repeat = true;
	    		}
			
    		 	if(c_list.get(i).getPerson1().getName().equals(getPerson1().getName()) 
	    				&& c_list.get(i).getPerson2().getName().equals(getPerson2().getName())
		    			&& child_repeat == true){
				
	    			throw new RepeatException("Repeated");
		    	}
			
    			if(c_list.get(i).getPerson2().getName().equals(getPerson1().getName()) 
	    				&& c_list.get(i).getPerson1().getName().equals(getPerson2().getName())
		    			&& child_repeat == true){
			
	    			throw new RepeatException("Repeated");
    			}
			}	
		}
	}

	
	
    /* Due to the child is extra to normal connection, 
	*  this method check three profiles in parent connection and override method in super class
	*/
	public  boolean hasProfile (Profile target){
		
		boolean in = false;
		
		if(getPerson1().getName().equals(target.getName()) 
				|| getPerson2().getName().equals(target.getName())
				|| getChild().getName().equals(target.getName())) {
			
			in = true;
		}	
		
		return in;
	}
}
