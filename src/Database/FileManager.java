package Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Exceptions.NoParentException;
import Exceptions.NotToBeFriendsException;
import Exceptions.ProfileNotFoundException;
import Exceptions.RepeatException;
import Interfaces.ConnectionManager;
import Interfaces.ProfileManager;
import Model.Profile;

/*
 * Class to read text files.
 * 
 * 
 */
public class FileManager {	
	
	//Read people from people.txt
	public  ArrayList<Profile> readPeople() throws IOException {
		
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		
		FileReader fr = new FileReader("people.txt") ;
		BufferedReader br = new BufferedReader(fr);
		String line;
			
		while((line = br.readLine())!=null) {
			String[] tokens = line.split(",");
			Profile addProfile = new Profile(tokens[0], tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), tokens[5]);
			profiles.add(addProfile);
		}
			
		br.close();
		return profiles;
		
	}
	
	// read all connections from relations.txt
	public  void readConnection(ProfileManager pmanager, ConnectionManager cmanager) throws IOException {
		
		FileReader fr = new FileReader("relations.txt") ;
		BufferedReader br = new BufferedReader(fr);
		String line;
		
		while((line = br.readLine())!=null) {
			
			try {
			String[] tokens = line.split(",");
	   			if(tokens[2].equals("friends")) {
	   				cmanager.addConnection(pmanager.searchProfile(tokens[0]).getName(),pmanager.searchProfile(tokens[1]).getName(), "friends");
	   			}else if(tokens[2].equals("couple")){
	   				cmanager.addConnection(pmanager.searchProfile(tokens[0]).getName(),pmanager.searchProfile(tokens[1]).getName(), "couple");
	   			}else if(tokens[2].equals("colleague")){	
	   				cmanager.addConnection(pmanager.searchProfile(tokens[0]).getName(),pmanager.searchProfile(tokens[1]).getName(), "colleague");
	   			}else if(tokens[2].equals("classmates") ) {
	   				cmanager.addConnection(pmanager.searchProfile(tokens[0]).getName(),pmanager.searchProfile(tokens[1]).getName(), "classmates");
	   			}else if(tokens[2].equals("parent")) {
	   				cmanager.addParentConnection(tokens[0],tokens[1]);
	   			}
			}catch(Exception e) {
				
			}	
		}
			
		br.close();
	}

}
