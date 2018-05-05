package Interfaces;

import java.util.ArrayList;

import Exceptions.NoParentException;
import Exceptions.NotToBeCoupledException;
import Exceptions.NotToBeFriendsException;
import Exceptions.RepeatException;
import Exceptions.TooYoungException;
import Model.Connection;
import Model.Profile;


public interface ConnectionManager {
	
	
	public void addConnection(Connection target);
	
	public void addFriendConnection(int ID_1,int ID_2) throws Exception;
		
	void addParentConnection(int ID_1,int ID_2,int ID_child) throws NoParentException, RepeatException;
	
	public void addCoupleConnection(int ID_1,int ID_2) throws RepeatException, NotToBeCoupledException;
	
	public ArrayList<Connection> get_Clist();
	
	public ArrayList<Profile> search(Profile target);
	
	public ArrayList<Connection> search_clist(Profile target);
	
	public boolean profile_repeat(Profile target, ArrayList<Profile> check_plist); 
	 
	public void importList(ArrayList<Connection> readConnections);

	public void setPmanager(ProfileManager profiles); 

}
