package com.SRMessagingTest.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.SRMessagingTest.VO.MessageType3VO;
import com.SRMessagingTest.VO.SaleVO;
import com.SRMessagingTest.persistence.MessagingDAO;
/**
 * Class responsible for reporting functionality
 * @author simon
 *
 */
public class ReportService {
	private MessagingDAO mdao = new MessagingDAO();
	
	/**
	 * Reports the quantities of each item sold and their total value
	 */
	public void doSalesSummary() {
		HashMap<String,ArrayList<SaleVO>> map = mdao.getSalesMap();
		System.out.println("Beggining report after "+ mdao.getMessagesCount()+" messages.");
		
		for(String item : map.keySet()) {
			double itemTotal = 0d;
			ArrayList<SaleVO> itemColl = map.get(item);
			for(SaleVO sale : itemColl) {
				itemTotal += sale.getAmount();
			}
			
			System.out.println("\t Item: "+item+". Number sold: "+ itemColl.size()+". Total value: "+ itemTotal+".");
		}	
	}
	
	/**
	 * Reports all adjustments that have been made to items.
	 */
	public void doAdjustmentSummary() {
		ArrayList<MessageType3VO> adjustments = mdao.getAdjustments();
		System.out.println("Beginning adjustment report after "+mdao.getMessagesCount()+" messages.");
		
		for(MessageType3VO adjustment : adjustments) {
			System.out.println("\tItem: " + adjustment.getItem() + ". Type: "+adjustment.getOperation().name()+". By: "+adjustment.getAmount());
		}
	}
}
