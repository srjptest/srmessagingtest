package com.SRMessagingTest.JUnit;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.SRMessagingTest.VO.IMessageVO;
import com.SRMessagingTest.VO.SaleVO;
import com.SRMessagingTest.constants.Operation;
import com.SRMessagingTest.persistence.MockStorage;
/**
 * Group test class. 
 * 
 * This simulates multiple messages, enough to generate both reports.
 * Tests all 3 message types and each operation in message type 3.
 * @author simon
 *
 */
public class GroupedTests {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();//this will hold the logfiles so we can read them
	private final StringBuffer buf = new StringBuffer();

	private MessagingType1Test m1;
	private MessagingType2Test m2;
	private MessagingType3Test m3;

	//Below are the predicted reports at each assertion.
	private static final ArrayList<String> reportLines = new ArrayList<String>();

	/**
	 * Sets up the data for each test.
	 * Using an arraylist for key info in the report that we can check output for.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {		
		System.setOut(new PrintStream(outContent)); //capture print 
		m1 = new MessagingType1Test();
		m2 = new MessagingType2Test();
		m3 = new MessagingType3Test();
		m1.setUp();
		m2.setUp();
		m3.setUp();

		//we expect these to be in the report at various points in the tests
		reportLines.add("Item: CAT. Number sold: 10. Total value: 500.0.");
		reportLines.add("Item: MOUSE. Number sold: 9. Total value: 45.0.");
		reportLines.add("Item: DOG. Number sold: 5. Total value: 500.0.");
		reportLines.add("Item: HAMSTER. Number sold: 6. Total value: 24.0.");
		reportLines.add("Item: MOUSE. Number sold: 9. Total value: 90.0.");
		reportLines.add("Item: MOUSE. Type: ADD. By: 5.0");	
		reportLines.add("Item: MOUSE. Number sold: 9. Total value: 27.0.");
		reportLines.add("Item: MOUSE. Type: SUBTRACT. By: 2.0");
		reportLines.add("Item: MOUSE. Number sold: 9. Total value: 135.0.");
		reportLines.add("Item: MOUSE. Type: MULTIPLY. By: 3.0");
	}
	
	/**
	 * Clears up data and resets the mock storage
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		System.out.println(outContent.toString());	

		//Reset the maps in mock storage after each test.
		MockStorage.setMessages(new ArrayList<IMessageVO>());
		MockStorage.setSalesMap(new HashMap<String,ArrayList<SaleVO>>());
	}

	/**
	 * Simulates 49 messages, checks the report after each 10
	 * and allows us to send one last adjustment to test the 50
	 * message report
	 * @throws Exception
	 */
	public void add49Messages() throws Exception {
		//Use Test 1 to give us 10 cats
		for(int i=0;i<10;i++) {
			m1.test();
		}
		assertTrue(outContent.toString().contains(reportLines.get(0)));
		flush();


		//add 5 dogs using message type 2
		m2.test();
		//add 3 * 3 mice @ £5 = 4 messages.
		for(int i = 0; i<3; i++) {
			m2.sendMessageType2("MOUSE", 3, 5l);
		}
		//add 6 hamsters at £4
		for(int i=0; i<6;i++) {
			m1.sendMessageType1("HAMSTER", 4l);
		}

		for(int i=0;i<4;i++) {
			assertTrue(outContent.toString().contains(reportLines.get(i)));
		}
		flush();
		//currently 20 messages, add 29 gerbils at £6
		for(int i=0; i<29; i++) {
			m1.sendMessageType1("GERBIL", 6l);
		}

		//we are now up to 49 messages

	}
	
	/**
	 * Tests the add operation
	 * @throws Exception
	 */
	@Test
	public void testAdd() throws Exception {
		add49Messages();
		m3.sendMessageType3("MOUSE", 5l, Operation.ADD);
		for(int i=0;i<4;i++) {
			if(i!=1) {//skip previous mouse value, as it has been adjusted
				assertTrue(outContent.toString().contains(reportLines.get(i)));
			}
		}
		assertTrue(outContent.toString().contains(reportLines.get(5)));	
	}
	
	/**
	 * Tests the subtract operation
	 * @throws Exception
	 */
	@Test
	public void testSub() throws Exception {
		add49Messages();
		m3.sendMessageType3("MOUSE", 2l, Operation.SUBTRACT);
		for(int i=0;i<3;i++) {
			if(i!=1) {//skip previous mouse value, as it has been adjusted
				assertTrue(outContent.toString().contains(reportLines.get(i)));
			}
		}

		assertTrue(outContent.toString().contains(reportLines.get(6)));
		assertTrue(outContent.toString().contains(reportLines.get(7)));		
	}
	
	/**
	 * Tests the multiply operation
	 * @throws Exception
	 */
	@Test
	public void testMult() throws Exception {
		add49Messages();
		m3.sendMessageType3("MOUSE", 3l, Operation.MULTIPLY);
		for(int i=0;i<3;i++) {
			if(i!=1) {//skip previous mouse value, as it has been adjusted
				assertTrue(outContent.toString().contains(reportLines.get(i)));
			}
		}

		assertTrue(outContent.toString().contains(reportLines.get(8)));
		assertTrue(outContent.toString().contains(reportLines.get(9)));		
	}


	/**
	 * Helper class. Flushes the current buffer after each report and 
	 * captures it in a string buffer so we can output it during teardown.
	 */
	private void flush() {
		buf.append(outContent.toString());
		outContent.reset();
	}

}
