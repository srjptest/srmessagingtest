package com.SRMessagingTest.Exception;



/**
 * @author simon
 * Application specific exception class
 */
public class MessagingException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates an exception with a reason param.
	 * @param reason - String
	 */
	public MessagingException(String reason)  {
		super(reason);
	}
	
	/**
	 * Creates an exception with a reason and a cause
	 * @param reason - String
	 * @param cause - Throwable
	 */
	public MessagingException(String reason, Throwable cause) {
		super(reason, cause);
	}
	
}
