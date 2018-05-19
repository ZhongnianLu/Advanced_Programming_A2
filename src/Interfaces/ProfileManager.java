package Interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import Exceptions.NoAvailableException;
import Exceptions.NoSuchAgeException;
import Exceptions.ProfileNotFoundException;
import Exceptions.ProfileRepeatException;
import Model.Profile;

public interface ProfileManager {
	
	public void importList(ArrayList<Profile> profiles) throws NoSuchAgeException, ProfileRepeatException, NoAvailableException;
	
	public boolean uniqueName(String name);
				
	public ArrayList<Profile> get_Plist();

	public void addProfile(Profile person) throws NoSuchAgeException, NoAvailableException;

	public void removeProfile(Profile person);
	
	public Profile searchProfile(String name) throws ProfileNotFoundException;

}
