package com.SRMessagingTest.ws;
import com.SRMessagingTest.Exception.MessagingException;
import com.SRMessagingTest.constants.MessagingResponseMessages;
import com.SRMessagingTest.constants.Operation;

public class WSUtils {
	
	/**
	 * Parses the operation and returns an enum representation
	 * @param oper this operation
	 * @return Operation - enum representation.
	 * @throws MessagingException - null error message if null, invalid operation otherwise.
	 */
	public static Operation parseOperation(String oper) throws MessagingException {
		try {
			return Operation.valueOf(oper);

		} catch (IllegalArgumentException e)  {
			
			throw new MessagingException(MessagingResponseMessages.INVALID_OPERATION);
			
		} catch (NullPointerException e) {
			
			throw new MessagingException(MessagingResponseMessages.MISSING_OPERATION);
			
		}
	}
	
	

}