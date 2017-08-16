package com.SRMessagingTest.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.SRMessagingTest.VO.IMessageVO;
import com.SRMessagingTest.VO.MessageType3VO;
import com.SRMessagingTest.VO.SaleVO;
/**
 * DAO class for storing and retriving messages and sales.
 * In the real world this would connect to the DB.
 * @author simon
 *
 */
public class MessagingDAO {
	/**
	 * Adds a sale to storags
	 * @param sale - This sale
	 */
	public void addSale(SaleVO sale) {
		
		if(MockStorage.getSalesMap().get(sale.getItem())==null) {			
			ArrayList<SaleVO> sales = new ArrayList<SaleVO>();
			MockStorage.getSalesMap().put(sale.getItem(), sales);
		} 
		
		MockStorage.getSalesMap().get(sale.getItem()).add(sale);		
	}
	
	/**
	 * Replaces a collection of sales by type
	 * @param type - this type
	 * @param salesColl - this collection of sales (ArrayList)
	 */
	public void replaceSales(String type, ArrayList<SaleVO> salesColl) {
		MockStorage.getSalesMap().put(type, salesColl);
	}
	
	/**
	 * Returns an ArrayList of sales based on type
	 * @param type - this type
	 * @return ArrayList<SaleVO> these sales
	 */
	public ArrayList<SaleVO> getSaleByType(String type){
		return MockStorage.getSalesMap().get(type);
	}
	
	/**
	 * Adds the message 
	 * @param message
	 */
	public void addMessage(IMessageVO message) {
		MockStorage.getMessages().add(message);
	}
	
	/**
	 * Returns the amount of messages recieved by the application
	 * @return int - this count
	 */
	public int getMessagesCount() {
		return MockStorage.getMessages().size();
	}
	
	/**
	 * Returns all adjustment messages for the reporting service.
	 * @return ArrayList<MessageType3VO> these messages.
	 */
	public ArrayList<MessageType3VO> getAdjustments (){
		return (ArrayList<MessageType3VO>) MockStorage.getMessages().stream().filter(MessageType3VO.class::isInstance)
				.map(MessageType3VO.class::cast).collect(Collectors.toList());
	}
	
	/**
	 * Returns a hashmap of all sales for reporting.
	 * @return HashMap<String, ArrayList<SaleVO>> - these sales.
	 */
	public HashMap<String, ArrayList<SaleVO>> getSalesMap(){
		return MockStorage.getSalesMap();
	}
	
	/**
	 * Sets whether or not reporting is currently in service.
	 * @param b - boolean
	 */
	public void setReportingInProgress(boolean b) {
		MockStorage.setReportingInProgress(b);
	}
	/**
	 * Returns whether or not reporting is currently in service.
	 * @return - boolean
	 */
	public boolean isReportingInProgress() {
		return MockStorage.isReportingInProgress();
	}
}
