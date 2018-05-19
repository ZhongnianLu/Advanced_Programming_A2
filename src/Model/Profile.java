package Model;

/*
 * Author: Jake Mott s3349405
 * 
 * This class contains the profile constructor and 
 * methods for changing profile info
 * 
 */

public class Profile {

	//basic personal information
	private String name;
	private String image;
	private String status;
	private int age;
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
	
	
	public String getGender() {
		return gender;
	}
	
	public String getState() {
		return state;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String profileInfo() {
		
		return String.format("Name: %s \n\nAge: %d\n\nStatus: %s\n\nGender: %s\n\n", 
				              name, age, status, gender);
	}
	
	//For gui output.
	public String toString() {
		return String.format(name + "    " + "(" + age + ")");
	}
	

	
	
	
	
}
