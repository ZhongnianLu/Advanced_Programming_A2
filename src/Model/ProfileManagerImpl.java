package Model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Exceptions.NoAvailableException;
import Exceptions.NoSuchAgeException;
import Exceptions.ProfileNotFoundException;
import Exceptions.ProfileRepeatException;
import Interfaces.ProfileManager;

/*
 * Author: Jake Mott s3349405
 * 
 * This class contains the profile list and methods
 * to manipulate the list as well as search it and 
 * other related functions
 * 
 */

public class ProfileManagerImpl implements ProfileManager {
	
	ArrayList<Profile> profiles =new ArrayList<Profile>();
	
	public ProfileManagerImpl(ArrayList<Profile> profs) {
		this.profiles = profs;
	}
	
	public ProfileManagerImpl() {
		this.profiles = new ArrayList<Profile>();
	}
	
	public ArrayList<Profile> get_Plist(){
		return profiles;
	}
	
	public void addProfile(Profile add) throws NoSuchAgeException, NoAvailableException{
		if(add.getName() == null || uniqueName(add.getName()) == false){
			throw new NoAvailableException("Name is invalid");
		}
		if((add.getAge()) >= 150) {
			throw new NoSuchAgeException("Age can't be over 150 years. ");
		}
		profiles.add(add);
	}
	
	
	public void removeProfile(Profile del) {
		profiles.remove(del);
	}
	
	
	/* ArrayList given to fill an empty list or override an existing list */
	public void importList(ArrayList<Profile> profiles) throws NoSuchAgeException, ProfileRepeatException, NoAvailableException {
				
		/* Iterate over given list and add entries to main list */
		for (Profile profile : profiles) {
			try {
				if(uniqueName(profile.getName()) == false) {
					throw new ProfileRepeatException("Name repeated.");
					
				}
				this.addProfile(profile);
				
			} catch (NoSuchAgeException e) {
				throw new NoSuchAgeException("Age can't be over 150 years.");
			} 
		}
		
	}
	
	
	/*checks if a given name is unique among the profiles, return true means it is unique */
	public boolean uniqueName(String name) {
		boolean unique = true;
		name = name.toLowerCase();
		
		/* Iterates over all profiles to match the given name */
		for (int i = 0; i < this.get_Plist().size(); i++) { 
			String str = this.get_Plist().get(i).getName().toLowerCase();
			
			/* If names match return a false boolean value */
			if (name.equals(str)) {    
				unique = false;
			}
		}
		return unique; 
	}
	
	
	public Profile searchProfile(String name) throws ProfileNotFoundException {
		
		boolean success = false;

		for(Profile profile : profiles) {
			
			if(profile.getName().equals(name)) {
				success = true;
				return profile;
			}
		}
		
		if(success == false) {
			
			throw new ProfileNotFoundException("Can't find the profile in database.");
		}
		return null;
	}
}

