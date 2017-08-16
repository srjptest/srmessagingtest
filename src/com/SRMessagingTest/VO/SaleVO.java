package com.SRMessagingTest.VO;
/**
 * VO Class for sale
 * @author simon
 *
 */
public class SaleVO {
	
	private String item;
	private double amount;
	
	/**
	 * Class constructor
	 * @param item - String
	 * @param amount double
	 */
	public SaleVO(String item, double amount) {
		super();
		this.item = item;
		this.amount = amount;
	}

	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	
}
