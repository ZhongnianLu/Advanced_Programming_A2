package Exceptions;

/*
 * Represent an unexpected situation when trying to connect a child in a colleague relation.
 */

public class NotToBeColleaguesException extends Exception {

	
	public NotToBeColleaguesException(String message) {
		
		super(message);
	}
	
}
