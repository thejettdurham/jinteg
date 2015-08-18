package com.jettdurham.jinteg;

public class ITPass implements ITReturn {

	private String message = "Success";
	
	ITPass() {
	}
	
	ITPass(String msg) {
		message = message + ": " + msg;
	}
	
	public boolean getStatus() {
		return true;
	}
	
	public String getStatusMessage() {
		return message;
	}
	
}
