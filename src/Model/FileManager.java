package Model;

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

public class FileManager {
	
	
	public  ArrayList<Profile> readPeople() {
		
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		
		try {
			FileReader fr = new FileReader("people.txt") ;
			BufferedReader br = new BufferedReader(fr);
			String line;
			
			while((line = br.readLine())!=null) {
				String[] tokens = line.split(",");
				Profile addProfile = new Profile(tokens[0], tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), tokens[5]);
				profiles.add(addProfile);
			}
			
			br.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return profiles;
		
	}
	
	
	public  ArrayList<Connection> readConnection(ProfileManager pmanager, ConnectionManager cmanager) {
		
		ArrayList<Connection> connections = new ArrayList<Connection>();
		
		try {
			FileReader fr = new FileReader("relations.txt") ;
			BufferedReader br = new BufferedReader(fr);
			String line;
			Connection addConnect = null;
			
			while((line = br.readLine())!=null) {
				String[] tokens = line.split(",");
				if(tokens[2].equals("friends")) {
					cmanager.addConnection( new Friend_Connection(pmanager.searchProfile(tokens[0]),pmanager.searchProfile(tokens[1])));
				}else if(tokens[2].equals("couple")){
					cmanager.addConnection( new Couple_Connection(pmanager.searchProfile(tokens[0]),pmanager.searchProfile(tokens[1])));
				}else if(tokens[2].equals("colleague")){	
					cmanager.addConnection( new Colleagues_Connection(pmanager.searchProfile(tokens[0]),pmanager.searchProfile(tokens[1])));
				}else if(tokens[2].equals("classmates") ) {
					cmanager.addConnection( new Classmates_Connection(pmanager.searchProfile(tokens[0]),pmanager.searchProfile(tokens[1])));
				}else if(tokens[2].equals("parent")) {
					cmanager.addParentConnection(tokens[0],tokens[1]);

			}
				if(addConnect != null) {
				connections.add(addConnect);
			}}
			
			br.close();
		
		} catch (IOException | ProfileNotFoundException | NoParentException | RepeatException | NotToBeFriendsException e) {
			e.printStackTrace();
		}
		
		return connections;
		
	}

}
