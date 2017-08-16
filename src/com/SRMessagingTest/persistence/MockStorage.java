package com.SRMessagingTest.persistence;

import java.util.ArrayList;
import java.util.HashMap;

import com.SRMessagingTest.VO.IMessageVO;
import com.SRMessagingTest.VO.SaleVO;
/**
 * Class to store objects throughout application lifespan.
 * This is a mock service to mimick a db.
 * @author simon
 *
 */
public class MockStorage {
	private static HashMap<String, ArrayList<SaleVO>> salesMap = new HashMap<String, ArrayList<SaleVO>>();
	private static ArrayList<IMessageVO> messages = new ArrayList<IMessageVO>();
	private static boolean reportingInProgress = false;
	
	/**
	 * returns the reporting boolean
	 * @return boolean
	 */
	public static boolean isReportingInProgress() {
		return reportingInProgress;
	}
	
	/**
	 * Sets the reporting boolean.
	 * @param reportingInProgress boolean
	 */
	public static void setReportingInProgress(boolean reportingInProgress) {
		MockStorage.reportingInProgress = reportingInProgress;
	}
	/**
	 * Returns the sales HashMap
	 * @return HashMap<String, ArrayList<SaleVO>>
	 */
	public static HashMap<String, ArrayList<SaleVO>> getSalesMap() {
		return salesMap;
	}
	/**
	 * Sets the sales HashMap
	 * @param salesMap HashMap<String, ArrayList<SaleVO>>
	 */
	public static void setSalesMap(HashMap<String, ArrayList<SaleVO>> salesMap) {
		MockStorage.salesMap = salesMap;
	}
	/**
	 * Returns the Messages collection
	 * @return ArrayList<IMessageVO>
	 */
	public static ArrayList<IMessageVO> getMessages() {
		return messages;
	}
	/**
	 * Sets the messages collection
	 * @param messages ArrayList<IMessageVO>
	 */
	public static void setMessages(ArrayList<IMessageVO> messages) {
		MockStorage.messages = messages;
	}
	
	
		
}