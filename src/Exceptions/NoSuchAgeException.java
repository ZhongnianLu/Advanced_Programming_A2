package Exceptions;

/*
 * Represent an unexpected situation when trying to enter a person whose age is negative or over 150. (You
 * can treat age as integer)
 */

public class NoSuchAgeException extends Exception {

	
	public NoSuchAgeException(String message) {
		
		super(message);
	}
	
}
