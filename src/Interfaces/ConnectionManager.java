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


public interface ConnectionManager {
	
	
	public void addConnection(Connection target);
	
	public void addConnection(int ID1, int ID2, int connectionType) throws  Exception; 
			
	public void addParentConnection(int ID_1,int ID_2,int ID_child) throws NoParentException, RepeatException, ProfileNotFoundException, NotToBeFriendsException;
	
	public void addParentConnection(String person1, String childname) throws NoParentException, RepeatException, ProfileNotFoundException, NotToBeFriendsException;
	
	public ArrayList<Connection> get_Clist();
	
	public ArrayList<Profile> search(Profile target);
	
	public ArrayList<Connection> search_clist(Profile target);
	
	public boolean profile_repeat(Profile target, ArrayList<Profile> check_plist); 
	 
	public void importList(ArrayList<Connection> readConnections);

	public void setPmanager(ProfileManager profiles);

	public void removeConnections(int ID) throws NoParentException, ProfileNotFoundException;
	
	public ArrayList<Connection> getRelations(int ID, boolean checkParent) throws Exception;

}
