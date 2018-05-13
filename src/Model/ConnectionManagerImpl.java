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
    
    
    public void addConnection(String name1, String name2, String type) throws Exception {
    	
    	Profile person1 = Pmanager.searchProfile(name1);
		Profile person2 = Pmanager.searchProfile(name2);
		
		Connection addConnect = null;
		
		if(type.equals("friends")) {
				addConnect = new Friend_Connection(person1,person2);
				checkFamily(addConnect);
		}else if(type.equals("couple")){
				addConnect = new Couple_Connection(person1,person2);
		}else if(type.equals("colleague")){
				addConnect = new Colleagues_Connection(person1,person2);
		}else if(type.equals("classmates") ) {
				addConnect = new Classmates_Connection(person1, person2);
		}
			
			
		addConnect.check(c_list);
		addConnect.repeat_check(c_list);
		c_list.add(addConnect);
    	
    }
    
    /*Connection type 1: friendConnection
    * Connection type 2: coupleConnection
    * Connection type 3: colleagueConnection
    * Connection type 4: ClassmatesConnection
    */
//    public void addConnection(int ID_1, int ID_2, int connectionType) throws Exception   {
//    	
//    	Profile person1 = Pmanager.searchProfile(ID_1);
//		Profile person2 = Pmanager.searchProfile(ID_2);
//		
//		Connection addConnect = null;
//		
//		if(connectionType == 1) {
//		//create a new connection with selected profiles
//			addConnect = new Friend_Connection(person1,person2);
//			checkFamily(addConnect);
//		}else if(connectionType == 2){
//			addConnect = new Couple_Connection(person1,person2);
//		}else if(connectionType == 3){
//			addConnect = new Colleagues_Connection(person1,person2);
//		}else if(connectionType == 4) {
//			addConnect = new Classmates_Connection(person1, person2);
//		}
//		
//		
//		addConnect.check(c_list);
//		addConnect.repeat_check(c_list);
//		c_list.add(addConnect);
//		
//	}
    
//    public void addParentConnection(String name1, String name2, String nameChild, String type) throws NoParentException, RepeatException, ProfileNotFoundException {
//
//		Profile person1 = Pmanager.searchProfile(name1);
//		Profile person2 = Pmanager.searchProfile(name2);
//		Profile child = Pmanager.searchProfile(nameChild);
//		
//		//check whether the parent connection is valid by calling parent check method passing IDs of parents 
//		Parent_Connection addConnect = new Parent_Connection(person1,person2,child);
//		addConnect.check(c_list);
//		addConnect.repeat_check(c_list);
//		getRelations(child.getID(), true);
//		c_list.add(addConnect);
//    	
//    	
//    }
    

    
    public void addParentConnection(String name1, String nameChild) throws NoParentException, ProfileNotFoundException {
    	
    	Profile person1 = Pmanager.searchProfile(name1);
		Profile child = Pmanager.searchProfile(nameChild);
		
		if(createChildCheckParent(person1) != null) {
			c_list.add(new Parent_Connection(person1, createChildCheckParent(person1), child));
		}

    }
	
    // add new parent connection by passing three IDs including parents and child
    public void addParentConnection(String name1, String name2, String name_child) throws Exception{
	    			
		Profile person1 = Pmanager.searchProfile(name1);
		Profile person2 = Pmanager.searchProfile(name2);
		Profile child = Pmanager.searchProfile(name_child);
		
		
		//check whether the parent connection is valid by calling parent check method passing IDs of parents 
		Parent_Connection addConnect = new Parent_Connection(person1,person2,child);
		
		addConnect.check(c_list);
		addConnect.repeat_check(c_list);
		getRelations(name_child, true);
		c_list.add(addConnect);
	
    }
	
    
  
    public ArrayList<Connection> get_Clist(){
	
    	return c_list;
	}
        
    
    //delete connections related to one person
    public void removeConnections(String name) throws NoParentException, ProfileNotFoundException {
    	
    	Profile targetProfile = Pmanager.searchProfile(name);
    	
    	// check whether the person has any independent
    	for(Connection connection : search_clist(targetProfile)) {
    		
//    		System.out.println("Person1: "+connection.getPerson1().getID());
//        	System.out.println("Person2: " + connection.getPerson2().getID());
       // 	System.out.print("+" +connection.getChild().getID());
       

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
				    && !(tem.getPerson1().getName().equals(target.getName()))) {
			
 		 	      contain.add(tem.getPerson1());	
		
		        }
		
		        if((profile_repeat(tem.getPerson2(), contain) == false) 
				    && !(tem.getPerson2().getName().equals( target.getName()))) {
			
			       contain.add(tem.getPerson2());	
		
		        }
		
		        //rule of check parent connection is different.
		        if(tem instanceof Parent_Connection) {
		    	
				    if((profile_repeat(tem.getChild(), contain) == false)
					    && !(tem.getChild().getName().equals (target.getName()))) {
				
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
    
    
	/* Input profile and iterates through their connections, returning a list of parent connections */
 	public ArrayList<Connection> getRelations(String name, boolean checkParent) throws ProfileNotFoundException, NoParentException{
 		
 		Profile prof = Pmanager.searchProfile(name);
 		 		
 		ArrayList<Connection> friends = search_clist(prof);
 		
 		/* Create new list of connections and only add parent connections to it */
 		ArrayList<Connection> relations = new ArrayList<Connection>();		
 		for(Connection connection : friends) {
 			
 			if (connection instanceof Parent_Connection) {
 				relations.add(connection);
 				if(checkParent == true && connection.getChild().getName().equals(name)) {
 					
 					throw new NoParentException("The child already has parents.");
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
	public void setPmanager(ProfileManager profiles) {

		Pmanager = profiles;
	}


	
	


}
    
	
    
    
    