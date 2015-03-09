///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AmazonStore.java
// File:             InsufficentCreditException.java
// Semester:         CS302 Spring 2015
//
// Author:           Adam Converse
// Email:            acconverse@wisc.edu
// CS Login:         adam
// Lecturer's Name:  Jim Skrentny
// Lab Section:      105
//
// Pair Partner:     Brandon Lapp
// Email:            blapp@wisc.edu
// CS Login:         brendon
// Lecturer's Name:  Jim Skrentny
// Lab Section:      105

//package p2;

/**
 * Exception thrown when user does not have enough credit to buy an item.
 *
 */
public class InsufficientCreditException extends Exception {
	public InsufficientCreditException(){
		
	}
	
	public InsufficientCreditException(String message){
		super(message);
	}
}
