package com.jettdurham.jinteg;

public class ITFail implements ITReturn {

private String message = "FAILURE";
	
	ITFail() {
	}
	
	ITFail(String msg) {
		message = message + ": " + msg;
	}
	
	public boolean getStatus() {
		return false;
	}
	
	public String getStatusMessage() {
		return message;
	}

}
