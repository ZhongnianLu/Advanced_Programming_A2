package Model;

/*
 * Author: Jake Mott s3349405
 * 
 * This class contains the profile constructor and 
 * methods for changing profile info
 * 
 */

public class Profile {
	
	private String name;
	private String image;
	private String status;
	private int age;
	private int ID; //ID for identifying purposes in connection list
	private String gender;
	private String state;
	
	public Profile (String name, String image, String status, String gender,  int age, String state) {
		this.name = name;
		this.image = image;
		this.status = status;
		this.gender = gender;
		this.age = age;
		this.state = state;
	}
	
	public Profile (String name, int age) {
		this(name, null, null, null, age, null);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getState() {
		return state;
	}

	public void setName(String name) {
		this.name = name;
	//	System.out.println("Your name has been changed");
	}
	
	public void setStatus(String status) {
		this.status = status;
	//	System.out.println("Your status has been changed");

	}
	
	public void setImage(String image) {
		this.image = image;
	//	System.out.println("Your image has been changed");
	}
	
	public void setID(int ID) {
		this.ID=ID;
	}
	
	
	
	
	
}

