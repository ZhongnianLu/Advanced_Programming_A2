package Interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import Exceptions.NoAvailableException;
import Exceptions.NoSuchAgeException;
import Exceptions.ProfileNotFoundException;
import Exceptions.ProfileRepeatException;
import Model.Profile;
/*
 * the interface to store all methods related to check, add and remove profiles from the system
 * 
 */
public interface ProfileManager {
	
	//only for database import
	public void importList(ArrayList<Profile> profiles) throws NoSuchAgeException, ProfileRepeatException, NoAvailableException;
	
	//check whether the name is repeated in the profile list
	public boolean uniqueName(String name);
				
	//return all profiles 
	public ArrayList<Profile> get_Plist();

	//add a specific person to profile list.
	public void addProfile(Profile person) throws NoSuchAgeException, NoAvailableException;

	//remove a profile from the list
	public void removeProfile(Profile person);
	
	//given a name and the method will return a profile with that name
	public Profile searchProfile(String name) throws ProfileNotFoundException;

}
