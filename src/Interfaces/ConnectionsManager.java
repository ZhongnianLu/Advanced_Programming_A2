package Interfaces;

import java.util.ArrayList;

import Model.Connection;
import Model.Profile;


public interface ConnectionsManager {
	
	
	public void addConnection(Connection target);
	
	public boolean addFriendConnection(int ID_1,int ID_2);
		
	boolean addParentConnection(int ID_1,int ID_2,int ID_child);
	
	public boolean addCoupleConnection(int ID_1,int ID_2);
	
	public ArrayList<Connection> get_Clist();
	
	public ArrayList<Profile> search(Profile target);
	
	public ArrayList<Connection> search_clist(Profile target);
	
	public boolean profile_repeat(Profile target, ArrayList<Profile> check_plist); 
	 
	public void importList(ArrayList<Connection> readConnections); 

}
