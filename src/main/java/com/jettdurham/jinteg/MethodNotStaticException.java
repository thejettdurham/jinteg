package com.jettdurham.jinteg;

/**
 * Thrown when a method designed to be run as a test is not static.
 * 
 * @author jettdurham
 *
 */
public class MethodNotStaticException extends Exception {

	private static final long serialVersionUID = -6889136974544927532L;

	public MethodNotStaticException(String message) {
        super(message);
    }
	
}
