package com.SRMessagingTest.constants;
/**
 * Class containing static messages.
 * In a real-world scenario, these would be in a db.
 * @author simon
 *
 */
public class MessagingResponseMessages {
	public static final String INVALID_OPERATION = "An invalid operation has been specified. Accepted operations are ADD, SUBTRACT and MULTIPLY.";
	public static final String MISSING_OPERATION = "No operation has been specified, these cannot be null";
	public static final String REPORTING_IN_PROGRESS = "Message Rejected, reporting in progress. Please try later";
	public static final String MESSAGE_ACCEPTED = "Message processed successfully.";
	public static final String ADJUSTMENT_TYPE_NOT_FOUND = "Cannot Adjust this item. No items exist.";
}
