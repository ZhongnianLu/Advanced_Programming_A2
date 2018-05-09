package Model;
import java.util.ArrayList;

import Exceptions.NotToBeFriendsException;
import Exceptions.RepeatException;

/*
 * Author: Zhongnian Lu s3512993
 * 
 * An abstract class represents the concept of connection for further inheritance and polymorphism.
 * 
 * Assume all connections contains two major person (person1 and person2).
 * 
 * Include a method to avoid adding any repeated connection. 
*/

public abstract class Connection {
	
	//in this task, assume all connections based on two people
	private Profile person1;
	private Profile person2;
	
		
	//Constructor passing two profiles
	public Connection(Profile person1, Profile person2) {
	
		this.person1=person1;
		this.person2=person2;
	}
	

	
	//Accessor to get profiles of two persons
	public Profile getPerson1() {
		
		return person1;
	}
	
	
	
	public Profile getPerson2() {
	
		return person2;
	}

	
	
	//Accessor for child in parent relationship
	public Profile getChild() {
	
		return null;
	}
	
	
	
    //Abstract method to check whether this connection is valid to be added. Need to be override.
	public abstract void check(ArrayList<Connection> c_list) throws Exception ;
	
	
	
	//Check whether this connection already added in the connection list
	public void repeat_check(ArrayList<Connection> c_list) throws RepeatException {
				
		for(int i = 0;i < c_list.size();i++) {
			
			if(c_list.get(i).getPerson1().getID() == getPerson1().getID() 
					&& c_list.get(i).getPerson2().getID() == getPerson2().getID()
					&& c_list.get(i).getClass().equals(getClass())){
			 
				
				throw new RepeatException("ID Repeated");
			}
	
			if(c_list.get(i).getPerson2().getID() == getPerson1().getID() 
					&& c_list.get(i).getPerson1().getID() == getPerson2().getID()
					&& c_list.get(i).getClass().equals(getClass())){
				
				throw new RepeatException("ID Repeated");
								
			}
		}
		
	}
	
	
	
	// Get all persons' profiles within this connection
	public  ArrayList<Profile> getProfileInside(){
		
    	ArrayList<Profile> linked_person = new ArrayList<Profile>();
		
		linked_person.add(getPerson1());
		
		linked_person.add(getPerson2());

		return linked_person;
	} 
	
	
	
	//Serve the search function: check whether the target profile is in this connection
	public  boolean hasProfile (Profile target){
		
     	boolean in=false;
	
	    if(getPerson1().getID() == target.getID()
	    		||getPerson2().getID() == target.getID()) {
		   
	    	in = true;
	    }

    	return in;
	
	}


	
}
