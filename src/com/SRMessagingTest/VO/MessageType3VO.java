package com.SRMessagingTest.VO;

import com.SRMessagingTest.constants.Operation;
/**
 * VO class for type 3 messages
 * @author simon
 *
 */
public class MessageType3VO implements IMessageVO{
	private String item;
	private double amount;
	private Operation operation;	

	
	/**
	 * Class constructor
	 * @param item String
	 * @param amount double
	 * @param operation Operation
	 */
	public MessageType3VO(String item, double amount, Operation operation) {
		super();
		this.item = item;
		this.amount = amount;
		this.operation = operation;
	}
	/**
	 * Getter for operation
	 * @return Operation
	 */
	public Operation getOperation() {
		return operation;
	}
	/**
	 * Getter for item
	 * @return String
	 */
	@Override
	public String getItem() {
		return item;
	}
	/**
	 * Getter for amount
	 * @return double
	 */
	@Override
	public double getAmount() {
		return amount;
	}
}
