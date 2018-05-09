package Interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import Exceptions.ProfileNotFoundException;
import Model.Profile;

public interface ProfileManager {
	
	public void importList(ArrayList<Profile> profiles);
	
	public boolean uniqueName(String name);
	
	public ArrayList<Profile> getAdults();
	
	public Profile selectProfile(String title) throws IOException;
	
	public ArrayList<String> listNames();
	
	public Profile askInfo() throws IOException, InputMismatchException;

	public ArrayList<Profile> get_Plist();

	public void addProfile(Profile person);

	public void removeProfile(Profile person);
	
	public Profile searchProfile(int ID) throws ProfileNotFoundException;

}
