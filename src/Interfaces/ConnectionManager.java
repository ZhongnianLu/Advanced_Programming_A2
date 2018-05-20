package Interfaces;

import java.util.ArrayList;

import Exceptions.NoParentException;
import Exceptions.NotToBeCoupledException;
import Exceptions.NotToBeFriendsException;
import Exceptions.ProfileNotFoundException;
import Exceptions.RepeatException;
import Exceptions.TooYoungException;
import Model.Connection;
import Model.Profile;

/*
 * This interface is created to provide security for connection data.
 * 
 * 
 */
public interface ConnectionManager {
	
	//directly add a new connection to the connection list in running program
	//@target the connection need to be added directly
	public void addConnection(Connection target);
	
	//add a normal connection 
	//@name @name2 the names of first and second names of desired connection
	public void addConnection(String name, String name2, String type) throws Exception;
	
	//add a parent connection. Due to there are three persons involve in this connection. 
	//The system will find the other parent for the child  
	public void addParentConnection(String person1, String childname) throws NoParentException, 
	                                                                         RepeatException, 
	                                                                         ProfileNotFoundException, 
	                                                                         NotToBeFriendsException;
	//get all connections
	public ArrayList<Connection> get_Clist();
	
	//get all connections related to a specific profile
	public ArrayList<Connection> search_clist(Profile target);
	
	//Remove a profile from the system and all related connections.
    public void removeConnections(String name) throws NoParentException, ProfileNotFoundException;
	
    //Get and check relation when creating a parent connection
    //@checkParent an indicator to show whether the method calling this need to failed all invliad family relationship.
	public ArrayList<Connection> getRelations(String name, boolean checkParent) throws Exception;

	//Only for GUI, it return a string contains all connections info.
	public String showConnections(Profile profile);
	
	//Only for GUI, it return a string contains all connections info.
	//@profile1 @profile2 two profiles the users want to check connection with
	public String showConnections(Profile profile1, Profile profile2);
	
    // Check whether profile is repeated
	//@target the target profile ths user want to check wether repeated or not
	public boolean profile_repeat(Profile target, ArrayList<Profile> check_plist); 
	 
	//for import from files
	public void importList(ArrayList<Connection> readConnections);

	//Connect to a profile manager.
	//@profiles a profile manager object contains all profile information
	public void setPmanager(ProfileManager profiles);

	

}
