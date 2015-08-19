package com.jettdurham.jinteg;

/** 
 * The reference implementation of a passing test result.
 * getStatus will always return true.
 * Default message is blank, but can be customized with the {@link #ITPass(String)} constructor
 * 
 * @author jettdurham
 *
 */
public class ITPass implements ITResult {
	
	private String message = "";
	
	/**
	 * Intialize with the default message (blank)
	 */
	public ITPass() {
	}
	
	/**
	 * Initialize with a custom message
	 * @param msg Custom message
	 */
	public ITPass(String msg) {
		message = msg;
	}
	
	/**
	 * Always true
	 */
	public boolean getStatus() {
		return true;
	}
	
	public String getStatusMessage() {
		return message;
	}
	
}
