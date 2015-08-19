package com.jettdurham.jinteg;

/**
 * Thrown if an annotated test method has the improper return type.
 * Not currently used in framework, but saved for future use
 * @author jettdurham
 *
 */
public class ImproperReturnTypeException extends Exception {

	private static final long serialVersionUID = 6603309270924720239L;
	
	public ImproperReturnTypeException(String message) {
        super(message);
    }
}
