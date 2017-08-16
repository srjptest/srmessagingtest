package com.SRMessagingTest.JUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.SRMessagingTest.Exception.MessagingException;
import com.SRMessagingTest.VO.MessageType1VO;
import com.SRMessagingTest.VO.MessageType3VO;
import com.SRMessagingTest.constants.MessagingResponseMessages;
import com.SRMessagingTest.constants.Operation;
import com.SRMessagingTest.service.MessagingService;
import com.SRMessagingTest.ws.MessagingResponse;
/**
* Simple test class for message type 3;
* provides methods for group testing a basic single tests for 
* operations on items that do exist, and an error case for when they don't.
* @author simon
*
*/
public class MessagingType3Test {

	MessagingService ms;
	MessagingResponse resp;
	MessagingType1Test test; 
	@Before
	public void setUp() throws Exception {
		ms = new MessagingService();
		resp = new MessagingResponse(MessagingResponseMessages.MESSAGE_ACCEPTED, true);

	}

	@After
	public void tearDown() throws Exception {
	}
	/**
	 * Tests adjusting the CAT object by +50
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		//Message type 3 will throw an exception if object not found.
		//Utilise test 1 to add an object.
		test = new MessagingType1Test();
		test.setUp();
		test.test();	
		sendMessageType3("CAT", 50l, Operation.ADD);
	}


	public void sendMessageType3(String item, long qty, Operation operation) throws Exception{
		MessagingResponse thisResp = ms.processMessage(new MessageType3VO(item,qty,operation));		
		assertEquals(resp.getResponse(),thisResp.getResponse());
		assertEquals(resp.isMessageAccepted(),thisResp.isMessageAccepted());
	}
	/**
	 * Test exception is thrown when unknown object is adjusted
	 * @throws Exception
	 */
	@Test 
	public void testUnknown() throws Exception {
		try {
			sendMessageType3("XXXX", 50l, Operation.ADD);
		} catch (MessagingException e) {
			assertEquals(e.getMessage(),MessagingResponseMessages.ADJUSTMENT_TYPE_NOT_FOUND);
		}
	}


}
