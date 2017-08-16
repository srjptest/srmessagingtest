package com.SRMessagingTest.JUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.SRMessagingTest.Exception.MessagingException;
import com.SRMessagingTest.VO.MessageType1VO;
import com.SRMessagingTest.constants.MessagingResponseMessages;
import com.SRMessagingTest.service.MessagingService;
import com.SRMessagingTest.ws.MessagingResponse;
/**
 * Simple test class for message type 1;
 * provides methods for group testing and a basic single test
 * @author simon
 *
 */
public class MessagingType1Test {
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
		sendMessageType1("CAT", 50l);
	}
	
	public void sendMessageType1(String item, long qty) throws Exception{
		MessagingResponse thisResp = ms.processMessage(new MessageType1VO(item,qty));		
		assertEquals(resp.getResponse(),thisResp.getResponse());
		assertEquals(resp.isMessageAccepted(),thisResp.isMessageAccepted());
	}

}
