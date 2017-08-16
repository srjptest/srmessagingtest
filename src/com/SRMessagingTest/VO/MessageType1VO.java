package com.SRMessagingTest.VO;

/**
 * VO Class for Type 1 Messages
 * @author simon
 *
 */
public class MessageType1VO implements IMessageVO{ 
	/**
	 */
	private String item;
	private double amount;
	
	/**
	 * Class constructor
	 * @param item - String
	 * @param amount - double
	 */
	public MessageType1VO(String item, double amount) {
		super();
		this.item = item;
		this.amount = amount;
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
