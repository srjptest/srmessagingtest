package com.SRMessagingTest.JUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.SRMessagingTest.VO.MessageType1VO;
import com.SRMessagingTest.VO.MessageType2VO;
import com.SRMessagingTest.constants.MessagingResponseMessages;
import com.SRMessagingTest.service.MessagingService;
import com.SRMessagingTest.ws.MessagingResponse;
/**
* Simple test class for message type 2;
* provides methods for group testing and a basic single test
* @author simon
*
*/
public class MessagingType2Test {
	MessagingService ms;
	MessagingResponse resp;
	@Before
	public void setUp() throws Exception {
		ms = new MessagingService();
		resp = new MessagingResponse(MessagingResponseMessages.MESSAGE_ACCEPTED, true);
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test	
	public void test() throws Exception {
		sendMessageType2("DOG", 5, 100l);
	}
	
	public void sendMessageType2(String item, long qty, double amt) throws Exception{
		MessagingResponse thisResp = ms.processMessage(new MessageType2VO(item,amt,qty));		
		assertEquals(resp.getResponse(),thisResp.getResponse());
		assertEquals(resp.isMessageAccepted(),thisResp.isMessageAccepted());
	}

}
