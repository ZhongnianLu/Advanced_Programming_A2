package Exceptions;

/*
 * Represent an unexpected situation when trying to make a couple when at least one member is not an
 * adult.
 */

public class NotToBeCoupledException extends Exception {

	
	public NotToBeCoupledException(String message) {
		
		super(message);
	}
	
}
