package Interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import Model.Profile;

public interface ProfileManager {
	
	public void importList(ArrayList<Profile> profiles);
	
	public boolean uniqueName(String name);
	
	public ArrayList<Profile> getAdults();
	
	public Profile selectProfile(String title) throws IOException;
	
	public ArrayList<String> listNames();
	
	public Profile askInfo() throws IOException, InputMismatchException;

}