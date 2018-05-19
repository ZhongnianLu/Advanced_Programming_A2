package Model;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import Exceptions.NoAvailableException;
import Exceptions.NoParentException;
import Exceptions.NotToBeCoupledException;
import Exceptions.NotToBeFriendsException;
import Exceptions.ProfileNotFoundException;
import Exceptions.RepeatException;
import Exceptions.TooYoungException;
import Interfaces.ConnectionManager;
import Interfaces.ProfileManager;

/*
 * Author: Zhongnian Lu s3512993
 * 
 * This class contains a list of all connections.
 * 
 * This class contains all methods to add different types of new connection.
 * 
 * This class helps search and display functions in Menu class. 
 * 
 */

public class ConnectionManagerImpl implements ConnectionManager{
	

	//Access list of connections
	private ArrayList<Connection> c_list = new ArrayList<Connection>();
	
	
	//Provide an instance of Profile manager to access profile list
	ProfileManager Pmanager;
	
	
	// direct add a new connection into the list, only for import database
    public void addConnection(Connection target){
    	c_list.add(target);
	}
    
    //add a normal connection with checking method
    public void addConnection(String name1, String name2, String type) throws Exception {
    	
    	Profile person1 = Pmanager.searchProfile(name1);
		Profile person2 = Pmanager.searchProfile(name2);
		
		Connection addConnect = null;
		
		if(type.equals("friends")) {
				
			    //two person belong to same parents can't become friends.
			    addConnect = new Friend_Connection(person1,person2);
				checkFamily(addConnect);
		}else if(type.equals("couple")){
				addConnect = new Couple_Connection(person1,person2);
		}else if(type.equals("colleague")){
				addConnect = new Colleagues_Connection(person1,person2);
		}else if(type.equals("classmates") ) {
				addConnect = new Classmates_Connection(person1, person2);
		}else {
			throw new NoAvailableException("Type of connection is wrong.");
		}
			
		//Check valid and check repeat	
		addConnect.check(c_list);
		addConnect.repeat_check(c_list);
		c_list.add(addConnect);
    	
    }
    
 
    //Parent connection is seperate because three persons involved.
    public void addParentConnection(String name1, String nameChild) throws NoParentException, 
                                                                           ProfileNotFoundException, 
                                                                           RepeatException {
    	
    	Profile person1 = Pmanager.searchProfile(name1);
		Profile child = Pmanager.searchProfile(nameChild);
		
		//get the other parent and check it.
		if(createChildCheckParent(person1) != null) {
			Profile person2 = createChildCheckParent(person1);
			Parent_Connection addConnect = new Parent_Connection(person1,person2,child);

			addConnect.check(c_list);
			addConnect.repeat_check(c_list);
			getRelations(child.getName(), true);
			c_list.add(addConnect);
		}
	}
	
    //get the whole connection list.
    public ArrayList<Connection> get_Clist(){
	
    	return c_list;
	}
        
    
    //delete connections related to one person
    public void removeConnections(String name) throws NoParentException, ProfileNotFoundException {
    	
    	Profile targetProfile = Pmanager.searchProfile(name);
    	
    	// check whether the person has any independent
    	for(Connection connection : search_clist(targetProfile)) {

    		if(connection instanceof Parent_Connection && (
    				connection.getPerson1().getName().equals(targetProfile.getName()) || 
    				connection.getPerson2().getName().equals(targetProfile.getName()))) {
    
    			throw new NoParentException("Can't delete a person with at least one dependent");
       		}	
    	}
    	
    	//remove all connection 
    	for(java.util.Iterator<Connection> iterator = c_list.iterator(); iterator.hasNext();) {
    		
    		Connection connection = iterator.next();
    		
    		if(connection.hasProfile(targetProfile)) {
    			iterator.remove();
    		}
    	}
    }
	
    
    //For GUI output only
    public String showConnections(Profile profile) {
    	String relations = "";
    	
    	StringBuilder sb = new StringBuilder(relations);
    	
    	for(Connection connection : search_clist(profile)) {
    		sb.append(connection.getOtherPart(profile));
    	}
    	return sb.toString();
    }
    
        
    //Method to create a connection list of profiles connected to a target person
    public ArrayList<Connection> search_clist(Profile target) {  	

		ArrayList<Connection> contain = new ArrayList<Connection>();
				
		for(Connection temConnection : c_list) {
					
		    if(temConnection.hasProfile(target) == true) {
			 
		 	   contain.add(temConnection);
		    } 
	    }
		return contain;
	}
    
    
    //find the other parent
	private Profile createChildCheckParent(Profile person1) throws NoParentException {
		
		Profile otherpart = null;
		
		boolean success = false;
		
		for(Connection connection : c_list) {
			if(connection instanceof Couple_Connection) {
				if(connection.getPerson1().getName().equals(person1.getName())){
					otherpart = connection.getPerson2();
					success = true;
				}
				else if(connection.getPerson2().getName().equals(person1.getName())){
					otherpart = connection.getPerson1();
					success = true;
					
				}
			}
		}
		
		if(success == false) {
			throw new NoParentException("Parent not found.");
		}
		
		return otherpart;
	}


    // Check whether two person in the connection belong to same family
    private void checkFamily(Connection addConnect) throws NotToBeFriendsException {
	    	
    	Profile friend1 = addConnect.getPerson1();
    	Profile friend2 = addConnect.getPerson2();
    	
    	Profile person1_f1 = null;
    	Profile person2_f1 = null;
    	Profile person1_f2 = null;
    	Profile person2_f2 = null;
    	
    	if(search_clist(friend1) != null) {
    		
    		for(Connection connection : search_clist(friend1)) {
    			if(connection instanceof Parent_Connection && connection.getChild().getName().equals(friend1.getName())) {
    	    		person1_f1 = connection.getPerson1();
    	    		person1_f2 = connection.getPerson2();
        		}
        	}
    	}
    	
    	if(search_clist(friend2) != null) {
    		
    		for(Connection connection : search_clist(friend2)) {
        		if(connection instanceof Parent_Connection && connection.getChild().getName().equals(friend2.getName())) {
        			person2_f1 = connection.getPerson1();
    	    		person2_f2 = connection.getPerson2();
    	    	}
        	}
    	}
    	
    	if(person1_f1!=null && person1_f2 != null && person2_f2 != null && person2_f1 != null) {
    		if(
    			(person1_f1.getName().equals(person2_f1.getName()) && person1_f2.getName().equals(person2_f2.getName())) ||
    			(person1_f1.getName().equals(person2_f2.getName()) && person1_f2.getName().equals(person2_f1.getName()))) {
    		
            		throw new NotToBeFriendsException("Two childs in the same family can't become friends. ");
    	    }
    	}
	}
    
    
    // Checking tool to help search method above: identify if a profile already included in the result of search
    public boolean profile_repeat(Profile target, 
    		                      ArrayList<Profile> check_plist) {
    
    	boolean repeat=false;
    	
    	for(int i = 0;i < check_plist.size();i++) {
    		
    		if(check_plist.get(i).getName().equals(target.getName())) {
    			
    			repeat = true;
    		}
    	}
    	return repeat;
    }
    
    
    //return all parent connections related to one person, it contains checking tool to make sure no repeated in parent connection
    public ArrayList<Connection> getRelations(String name, boolean checkRelation) throws ProfileNotFoundException, 
 	                                                                                     NoParentException{
 		
 		Profile prof = Pmanager.searchProfile(name);
 		 		
 		ArrayList<Connection> friends = search_clist(prof);
 		
 		/* Create new list of connections and only add parent connections to it */
 		ArrayList<Connection> relations = new ArrayList<Connection>();		
 		
 		for(Connection connection : friends) {
 			
 			if (connection instanceof Parent_Connection) {
 				relations.add(connection);
 				if(checkRelation == true) {				
 					if(connection.getChild().getName().equals(name)) {
 					
    					throw new NoParentException("The child already has parents.");
    				}
 		    	}
 			}
 			
 			if(connection instanceof Couple_Connection) {
 				if(connection.getPerson1().getName().equals(name) || 
						connection.getPerson1().getName().equals(name)) {
					throw new NoParentException("Invalid parent relation.");

     			}
 			
 	    	}
 		}
 		return relations;
 	}


    
    //Method only for importing data from database.
	public void importList(ArrayList<Connection> readConnections) {
	
		int len = readConnections.size();
		
		for (int i = 0; i < len; i++) {
			
      		 this.addConnection(readConnections.get(i));
		
		}
		
	}


	@Override
	//Method to connect a profile manager
	public void setPmanager(ProfileManager profiles) {

		Pmanager = profiles;
	}
}
    
	
    
    
    