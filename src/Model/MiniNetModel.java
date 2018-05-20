package Model;

import Database.DatabaseReader;
import Database.FileManager;
import Interfaces.ConnectionManager;
import Interfaces.ProfileManager;

/*
 * For GUI output, all managers are packed into one model.
 * The model is private, final and static to make sure only one object existed.
 * 
 * 
 */
public class MiniNetModel {
	
	private final static MiniNetModel model = new MiniNetModel();
	
	private ProfileManager profiles;
	
	private ConnectionManager conns;
	
	private DatabaseReader dreader;
	
	private FileManager freader;
	
	
	
	private MiniNetModel() {
		
    	profiles = new ProfileManagerImpl();  
    	
    	conns = new ConnectionManagerImpl(); 
		conns.setPmanager(profiles);
		
		freader = new FileManager();
		dreader = new DatabaseReader();

	}	
	
	//access the only one model from this class.
	public static MiniNetModel getModel() {
		
		return model;
	}
	
	public ProfileManager getProfileManager() {
		return profiles;
	}
	
	public ConnectionManager getConnectionManager() {
		return conns;
		
	}
	
	public DatabaseReader getDataReader() {
		return dreader;

	}
	
	public FileManager getFileManager() {
		return freader;
	}
	

}
