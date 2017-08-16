package com.SRMessagingTest.ws;
/**
 * This class encapsulates the response from the Web Service.
 * Using this means I can easily modify (add/remove objects)
 * during implementation.
 */
public class MessagingResponse {
	private String  response;
	private boolean messageAccepted;
	
	/**
	 * Basic constructor, required for JAX compliance.
	 */
	public MessagingResponse() {}
		
	/**
	 * Class constructor
	 * @param response - this response, String message to client
	 * @param messageAccepted - boolean representing success or fail
	 */
	public MessagingResponse(String response, boolean messageAccepted) {
		super();
		this.response = response;
		this.messageAccepted = messageAccepted;
	}

	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * @return the messageAccepted
	 */
	public boolean isMessageAccepted() {
		return messageAccepted;
	}

	/**
	 * @param messageAccepted the messageAccepted to set
	 */
	public void setMessageAccepted(boolean messageAccepted) {
		this.messageAccepted = messageAccepted;
	}
	
	
	
	
}
