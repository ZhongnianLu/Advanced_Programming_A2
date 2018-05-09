package Model;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

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
    
    /*Connection type 1: friendConnection
    * Connection type 2: coupleConnection
    * Connection type 3: colleagueConnection
    * Connection type 4: ClassmatesConnection
    */
    public void addConnection(int ID_1, int ID_2, int connectionType) throws Exception  {
    	
    	Profile person1 = Pmanager.searchProfile(ID_1);
		Profile person2 = Pmanager.searchProfile(ID_2);
		
		Connection addConnect = null;
		
		if(connectionType == 1) {
		//create a new connection with selected profiles
			addConnect = new Friend_Connection(person1,person2);
			checkFamily(addConnect);
		}else if(connectionType == 2){
			addConnect = new Couple_Connection(person1,person2);
		}else if(connectionType == 3){
			addConnect = new Colleagues_Connection(person1,person2);
		}else if(connectionType == 4) {
			addConnect = new Classmates_Connection(person1, person2);
		}
		
		
		//check whether the couple connection is valid by calling age check method 
		addConnect.check(c_list);
		addConnect.repeat_check(c_list);
		c_list.add(addConnect);
		
	}
	
	
	

	// add new parent connection by passing three IDs including parents and child
    public void addParentConnection(int ID_1,int ID_2,int ID_child) throws NoParentException, RepeatException, ProfileNotFoundException{
	    			
		Profile person1 = Pmanager.searchProfile(ID_1);
		Profile person2 = Pmanager.searchProfile(ID_2);
		Profile child = Pmanager.searchProfile(ID_child);
		
		
		//check whether the parent connection is valid by calling parent check method passing IDs of parents 
		Parent_Connection addConnect = new Parent_Connection(person1,person2,child);
		
		addConnect.check(c_list);
		addConnect.repeat_check(c_list);
		c_list.add(addConnect);
	
    }
	
    
    

    //access connection list
    public ArrayList<Connection> get_Clist(){
	
    	return c_list;
	}
        
    
    //delete connections related to one person
    public void removeConnections(int ID) throws NoParentException, ProfileNotFoundException {
    	
    	Profile targetProfile = Pmanager.searchProfile(ID);
    	
    	// check whether the person has any independent
    	for(Connection connection : search_clist(targetProfile)) {
    		
    		System.out.println("Person1: "+connection.getPerson1().getID());
        	System.out.println("Person2: " + connection.getPerson2().getID());
       // 	System.out.print("+" +connection.getChild().getID());
       

    		if(connection instanceof Parent_Connection && (
    				connection.getPerson1().getID() == targetProfile.getID() || 
    				connection.getPerson2().getID() == targetProfile.getID())) {
    
    			throw new NoParentException("Can't delete a person with at least one dependent");
    			
    		}
    		
    	}
    	
    	//remove all connection 
    	for(java.util.Iterator<Connection> iterator = c_list.iterator(); iterator.hasNext();) {
    		
    		Connection connection = iterator.next();
    		
    		if(connection.hasProfile(targetProfile)) {
    			System.out.println("remove test");
    			iterator.remove();
    		}
    		
    	}
    	
    }
	
    
    
    //Method to create a array list of profiles connected to a target person
    public ArrayList<Profile> search(Profile target) {  	

		ArrayList<Profile> contain = new ArrayList<Profile>();
		
		for(int i=0;i<c_list.size();i++) {
			
		    Connection tem = c_list.get(i);	
			
		    //Use check tool created in connection to make sure target person is in the connection.
		    if(tem.hasProfile(target) == true) {
	
		        if((profile_repeat(tem.getPerson1(), contain) == false)
				    && tem.getPerson1().getID() != target.getID()) {
			
 		 	      contain.add(tem.getPerson1());	
		
		        }
		
		        if((profile_repeat(tem.getPerson2(), contain) == false) 
				    && tem.getPerson2().getID() != target.getID()) {
			
			       contain.add(tem.getPerson2());	
		
		        }
		
		        //rule of check parent connection is different.
		        if(tem instanceof Parent_Connection) {
		    	
				    if((profile_repeat(tem.getChild(), contain) == false)
					    && tem.getChild().getID() != target.getID()) {
				
				     contain.add(c_list.get(i).getChild());
			        }
		        }
		   }
	   }
		
		return contain;
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


    // need complete!!!
    private void checkFamily(Connection addConnect) throws NotToBeFriendsException {
	    	
    	Profile friend1 = addConnect.getPerson1();
    	Profile friend2 = addConnect.getPerson2();
    	
    	Profile person1_f1 = null;
    	Profile person2_f1 = null;
    	Profile person1_f2 = null;
    	Profile person2_f2 = null;
    	
    	if(search_clist(friend1) != null) {
    		
    		for(Connection connection : search_clist(friend1)) {
    			if(connection instanceof Parent_Connection && connection.getChild().getID() == friend1.getID()) {
    	    		person1_f1 = connection.getPerson1();
    	    		person1_f2 = connection.getPerson2();
        		}
        	}
    	}
    	
    	if(search_clist(friend2) != null) {
    		
    		for(Connection connection : search_clist(friend2)) {
        		if(connection instanceof Parent_Connection && connection.getChild().getID() == friend2.getID()) {
        			person2_f1 = connection.getPerson1();
    	    		person2_f2 = connection.getPerson2();
    	    	}
        	}
    	}
    	
    	if(person1_f1!=null && person1_f2 != null && person2_f2 != null && person2_f1 != null) {
    		if(
    			(person1_f1.getID() == person2_f1.getID() && person1_f2.getID() == person2_f2.getID()) ||
    			(person1_f1.getID() == person2_f2.getID() && person1_f2.getID() == person2_f1.getID())) {
    		
            		throw new NotToBeFriendsException("Two childs in the same family can't become friends. ");
    		
           	}
    	}
	}
    
    
    // Checking tool to help search method above: identify if a profile already included in the result of search
    public boolean profile_repeat(Profile target, 
    		                      ArrayList<Profile> check_plist) {
    
    	boolean repeat=false;
    	
    	for(int i = 0;i < check_plist.size();i++) {
    		
    		if(check_plist.get(i).getID() == target.getID()) {
    			
    			repeat = true;
    		}
    	
    	}
    	
    	return repeat;
    }
    
	/* Input profile and iterates through their connections, returning a list of parent connections */
 	public ArrayList<Connection> getRelations(int id) throws ProfileNotFoundException{
 		
 		Profile prof = Pmanager.searchProfile(id);
 		 		
 		ArrayList<Connection> friends = search_clist(prof);
 		
 		/* Create new list of connections and only add parent connections to it */
 		ArrayList<Connection> relations = new ArrayList<Connection>();		
 		for(int i = 0; i < friends.size(); i++) {
 			
 			if (friends.get(i) instanceof Parent_Connection) {
 				relations.add(friends.get(i));
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
	public void setPmanager(ProfileManager profiles) {

		Pmanager = profiles;
	}


	
	


}
    
	
    
    
    