package Model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Interfaces.ConnectionManager;
import Interfaces.ProfileManager;

/*
 * Author: Jake Mott s3349405
 * 
 * This class contains predefined data but can contain methods
 * for reading in data from another source. Methods return
 * ArrayLists to be called to feed the data to another class.
 * 
 */

public class DatabaseReader {
	
	/* Create new empty ArrayLists of profiles and connections */
	ArrayList<Profile> profiles = new ArrayList<Profile>();
	ArrayList<Connection> conns = new ArrayList<Connection>();	
	
	public  ArrayList<Profile> readPeople() {
		
		 ArrayList<Profile> profiles = new ArrayList<Profile>();
		
		
		  Connection con = null;
	      Statement stmt = null;
	      ResultSet result = null;
	      
	      try {
	         Class.forName("org.hsqldb.jdbc.JDBCDriver");
	         con = DriverManager.getConnection(
	            "jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
	         stmt = con.createStatement();
	         result = stmt.executeQuery(
	            "SELECT * FROM people");
	         
	         while(result.next()){
	            
	            Profile addProfile = new Profile(result.getString("name"), result.getString("image"), result.getString("status"),
	            		result.getString("gender"), result.getInt("age"), result.getString("state"));
				profiles.add(addProfile);
	         }
	         
	         
	      } catch (Exception e) {
	         e.printStackTrace(System.out);
	      }
	   
	      return profiles;
	
		
	}
	
	public  void readConnection(ProfileManager pmanager, ConnectionManager cmanager) {
		
		
		Connection con = null;
	    Statement stmt = null;
	    ResultSet result = null;
		
		try {
		
		  Class.forName("org.hsqldb.jdbc.JDBCDriver");
	      con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
	      stmt = con.createStatement();
          result = stmt.executeQuery( "SELECT * FROM relations");
	         while(result.next()){
	        	
	        	try {
	    			if(result.getString("relation").equals("friends")) {
	    				cmanager.addConnection(result.getString("people1"),result.getString("people2"), "friends");
	    			}else if(result.getString("relation").equals("couple")){
	    				cmanager.addConnection(result.getString("people1"),result.getString("people2"), "couple");
	    			}else if(result.getString("relation").equals("colleague")){	
	    				cmanager.addConnection(result.getString("people1"),result.getString("people2"), "colleague");
	    			}else if(result.getString("relation").equals("classmates") ) {
	    				cmanager.addConnection(result.getString("people1"),result.getString("people2"), "classmates");
	    			}else if(result.getString("relation").equals("parent")) {
	    				cmanager.addParentConnection(result.getString("people1"),result.getString("people2"));
	    			}
				
	        	}catch(Exception e) {
	        		
	        	}
			
			System.out.println("read");
	       }
			
		
		} catch (Exception e) {
		
		}
		
		 
	      
	}
	
	
	
}