package com.SRMessagingTest.ws;


import com.SRMessagingTest.Exception.MessagingException;
import com.SRMessagingTest.VO.MessageType1VO;
import com.SRMessagingTest.VO.MessageType2VO;
import com.SRMessagingTest.VO.MessageType3VO;
import com.SRMessagingTest.service.MessagingService;

/**
 * Class that exposes the web service.
 * The glue between messaging service and the ws libraries.
 * @author simon
 *
 */
public class Messaging {
	private MessagingService ms = new MessagingService();
	
	/**
	 * Handles Type 1 Message requests
	 * @param item - this item
	 * @param cost - this cost
	 * @return MessagingResponse - this response
	 * @throws MessagingException - Passed up
	 */
	public MessagingResponse setMessage1(String item, Double cost) throws MessagingException {
		 return ms.processMessage(new MessageType1VO(item, cost));		
	} 
	/**
	 * Handles Type 2 Message Requests
	 * @param item - this item
	 * @param cost - this cost
	 * @param qty - this quantity
	 * @return MessagingResponse - this response
	 * @throws MessagingException
	 */
	public MessagingResponse setMessage2(String item, Double cost, long qty) throws MessagingException {
		return ms.processMessage(new MessageType2VO(item, cost, qty));
	}
	/**
	 * Handles Type 3 Message Requests
	 * @param item - this item
	 * @param qty - this quantity
	 * @param operation - this operation
	 * @return MessagingResponse - this response
	 * @throws MessagingException
	 */
	public MessagingResponse setMessage3(String item, Double qty, String operation) throws MessagingException  {
		return ms.processMessage(new MessageType3VO(item, qty, WSUtils.parseOperation(operation)));
	}
}
