package Exceptions;

/*
 * Represent an unexpected situation when trying to make an adult and a child friend or connect two
 * children with an age gap larger than 3.
 */

public class NotToBeFriendsException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8852877254457325657L;

	public NotToBeFriendsException(String message) {
		
		super(message);
	}
	
}
