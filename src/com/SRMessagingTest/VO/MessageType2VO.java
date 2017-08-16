package com.SRMessagingTest.VO;

/**
 * VO Class for type 2 messages.
 * @author simon
 *
 */
public class MessageType2VO implements IMessageVO{

	private String item;
	private double amount;
	private long quantity;
    
	/**
	 * Class constructor
	 * @param item String
	 * @param amount double
	 * @param quantity long
	 */
	public MessageType2VO(String item, double amount, long quantity) {
		super();
		this.item = item;
		this.amount = amount;
		this.quantity = quantity;
	}
	/**
	 * Getter for quantity.
	 * @return long
	 */
	public long getQuantity() {
		return quantity;
	}
	/**
	 * Getter for amount
	 * @return double
	 */
	@Override
	public double getAmount() {
		return amount;
	}
	/**
	 * Getter for item
	 * @return String
	 */
	@Override
	public String getItem() {
		return item;
	}
	
	
}
