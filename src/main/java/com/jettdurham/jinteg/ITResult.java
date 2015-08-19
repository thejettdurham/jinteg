package com.jettdurham.jinteg;

/**
 * An object returned by an Integration Test method.
 * At a minimum, an ITReturn must have a concept of a boolean status and a String message.
 * 
 * ITResults that have a false status are interpreted as a failed test.
 * The message is a human-readable status message describing the result of the test.
 * 
 * @author jettdurham
 *
 */
public interface ITResult {

	public boolean getStatus();
	public String getStatusMessage();
}
