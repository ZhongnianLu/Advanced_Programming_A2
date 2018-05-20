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
 * 
 * 
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
	

	//Accessors to get profiles of two persons
	public Profile getPerson1() {
		return person1;
	}
	
	
	public Profile getPerson2() {
		return person2;
	}

	
	//Accessors for child in parent relationship
	public Profile getChild() {
    	return null;
	}
	
	
	//For GUI output only: return the name of the other person and the type of connection
	public String getOtherPart(Profile profile) {
		
		if(!(this instanceof Parent_Connection))
    		if(person1.getName().equals(profile.getName())){
    			return String.format("\n\n%s   %s", person2.getName(), getType());
			
    		}else {
    			return String.format("\n\n%s   %s", person1.getName(), getType());
    		}
		else {
			if(((Parent_Connection)this).getChild().getName().equals(profile.getName())){
				return String.format("\n\n%s   %s\n\n%s   %s", 
						             person1.getName(), "[PARENT]", person2.getName(), "[PARENT]");
			}else {
    			return String.format("\n\n%s   %s", ((Parent_Connection)this).getChild().getName(), "[CHILD]");
    		}
		}
	}
	
	
    //Abstract method to check whether this connection is valid to be added. Need to be override.
	public abstract void check(ArrayList<Connection> c_list) throws Exception ;
	
	
	//Check whether this connection already added in the connection list
	public void repeat_check(ArrayList<Connection> c_list) throws RepeatException {
			
		for(int i = 0;i < c_list.size();i++) {
						
			String name1 = c_list.get(i).getPerson1().getName();
			String name2 = c_list.get(i).getPerson2().getName();
			
			if(name1.equals(getPerson1().getName())  
					&& name2.equals(getPerson2().getName()) 
					&& c_list.get(i).getClass().equals(getClass())){
			 
				
				throw new RepeatException("Profile Repeated");
			}
	
			if(name2.equals(getPerson1().getName()) 
					&& name1.equals(getPerson2().getName())
					&& c_list.get(i).getClass().equals(getClass())){
				
				throw new RepeatException("Profile Repeated");
			}
		}
	}
	
	
	//Serve the search function: check whether the target profile is in this connection
	public  boolean hasProfile (Profile target){
		
     	boolean in=false;
	
	    if(getPerson1().getName().equals(target.getName())
	    		||getPerson2().getName() == target.getName()) {
		   
	    	in = true;
	    }

    	return in;
    }
	
	
	//A helper method to return type of connection
	public String getType() {
		
		if(this instanceof Friend_Connection) {
			return "[FRIEND]";
			
		}else if(this instanceof Couple_Connection){
			return "[COUPLE]";
		
		}else if(this instanceof Parent_Connection) {
			return "[RELATIVE]";
		
		}else if(this instanceof Colleagues_Connection) {
			return "[COLLEAGUE]";
		
		}else if(this instanceof Classmates_Connection) {
			return "[CLASSMATE]";
		}else {
			return "";
		}
		
	}
}
