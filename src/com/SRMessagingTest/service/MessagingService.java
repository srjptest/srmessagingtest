package com.SRMessagingTest.service;

import java.util.ArrayList;

import com.SRMessagingTest.Exception.MessagingException;
import com.SRMessagingTest.VO.IMessageVO;
import com.SRMessagingTest.VO.MessageType1VO;
import com.SRMessagingTest.VO.MessageType2VO;
import com.SRMessagingTest.VO.MessageType3VO;
import com.SRMessagingTest.VO.SaleVO;
import com.SRMessagingTest.constants.MessagingResponseMessages;
import com.SRMessagingTest.constants.Operation;
import com.SRMessagingTest.persistence.MessagingDAO;
import com.SRMessagingTest.ws.MessagingResponse;
/**
 * Main service class, business logic goes here.
 * @author simon
 *
 */
public class MessagingService {
	MessagingDAO mdao = new MessagingDAO();
	private static final int SALES_REP_FREQ = 10;//frequency of sales reports
	private static final int ADJ_REP_FREQ   = 50;//frequency of adjustment reports
	
	/**
	 * Main processing class. 
	 * Takes an incoming message, processes it depending on type,
	 * Then stores it and checks the current counts of messages to determine
	 * if any reporting needs to be done.
	 * Finally responds to client.
	 * @param messageVO - MessageVO this message to be processed.
	 * @return MessageResponse - this response to the client.
	 * @throws MessagingException - Exception - passed up.
	 */
	public MessagingResponse processMessage(IMessageVO messageVO) throws MessagingException {
		
		//Reject message if reporting is in service.
		if(mdao.isReportingInProgress()) {
			return new MessagingResponse(MessagingResponseMessages.REPORTING_IN_PROGRESS, false);
		}
		
		//handle message depending on type
		if(messageVO instanceof MessageType1VO) {
			addSalesByQuantity(messageVO.getItem(),messageVO.getAmount(),1l);
		}
		else if (messageVO instanceof MessageType2VO) {
			addSalesByQuantity(messageVO.getItem(),messageVO.getAmount(), ((MessageType2VO) messageVO).getQuantity());
		}
		else if (messageVO instanceof MessageType3VO) {
			adjustSales(messageVO.getItem(),messageVO.getAmount(), ((MessageType3VO) messageVO).getOperation());
		}
		
		storeMessageAndHandleReporting(messageVO);
		
		//Success, send response to client		
		return new MessagingResponse(MessagingResponseMessages.MESSAGE_ACCEPTED, true);
		
	}

	/**
	 * Records a number of sales.
	 * 
	 * @param item - this item
	 * @param amount - this amount
	 * @param quantity - this quantity, the number of sales to be created
	 */
	private void addSalesByQuantity(String item, double amount, long quantity) {

		for(long i=0; i<quantity; i++) {
			mdao.addSale(new SaleVO(item, amount));
		}
	}
	
	/**
	 * Adjusts sales of type 'item' by amount based on operation (ADD, SUBTRACT, MULTIPLY)
	 * @param item this item to be adjusted.
	 * @param amount this amount to be adjusted by.
	 * @param operation this operation (add/subtract/divide)
	 * @throws MessagingException
	 */
	private void adjustSales(String item, double amount, Operation operation) throws MessagingException {

		ArrayList<SaleVO> sales  = mdao.getSaleByType(item);
		//Throw an error if the object to be adjusted doesn't have any sales
		if (sales==null) {
			throw new MessagingException(MessagingResponseMessages.ADJUSTMENT_TYPE_NOT_FOUND);
		}
		
		for(SaleVO sale : sales) {
			performOperation(sale, amount, operation);
		}
		
		mdao.replaceSales(item, sales);

	}
	/**
	 * Performs the appropriate operation to the Sale.
	 * @param sale - the sale to be adjusted
	 * @param adjustmentAmount - the amount to adjust by
	 * @param operation - the amount to perform
	 */
	private void performOperation(SaleVO sale, double adjustmentAmount, Operation operation) {
		double saleAmount = sale.getAmount();
		switch(operation) {
		case ADD:
			saleAmount +=adjustmentAmount;
			break;
		case SUBTRACT:
			saleAmount -=adjustmentAmount;
			break;

		case MULTIPLY:
			saleAmount *=adjustmentAmount;
			break;

		default :
			break;

		}
		sale.setAmount(saleAmount);
	}
    /**
     * Stores the message in persistence and generates a report (if neccesary)
     * @param message the message to be saved.
     */
	private void storeMessageAndHandleReporting(IMessageVO message) {
		ReportService rs  = new ReportService();
		
		mdao.addMessage(message);
		
		int msgCount = mdao.getMessagesCount();
		
		if(msgCount % SALES_REP_FREQ == 0) {
			mdao.setReportingInProgress(true);
			rs.doSalesSummary();
		}
		if(msgCount % ADJ_REP_FREQ == 0) {
			mdao.setReportingInProgress(true);
			rs.doAdjustmentSummary();
		}
		mdao.setReportingInProgress(false);
	}
}
