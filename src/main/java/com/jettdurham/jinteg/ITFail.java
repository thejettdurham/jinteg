package com.jettdurham.jinteg;

/** 
 * The reference implementation of a failing test result.
 * getStatus will always return false.
 * Default message is blank, but can be customized with the {@link #ITFail(String)} constructor
 *
 * @author jettdurham
 *
 */
public class ITFail implements ITResult {

	private String message = "";
	
	/**
	 * Intialize with the default message (blank)
	 */
	public ITFail() {
	}
	
	/**
	 * Initialize with a custom message
	 * @param msg Custom message
	 */
	public ITFail(String msg) {
		message = msg;
	}
	
	/**
	 * Always false
	 */
	public boolean getStatus() {
		return false;
	}
	
	public String getStatusMessage() {
		return message;
	}

}
