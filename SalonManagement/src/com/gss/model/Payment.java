package com.gss.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gss.service.PaymentService;
import com.gss.service.PaymentServiceImpl;

public class Payment {
	
	private int intPaymentID;			//dummy data
	private int intInvoiceID;			//real data
	private String transactionType;		//"order", "walkin", or "reservation"
	private double dblPaymentAmount;	//real data
	private String paymentType;			//FULL or DOWN or COMPLEMENTARY
	private Date datDateOfPayment;		//dummy data
	
	/*	ang pagset ng data nyan galing sa jsp
	 * 	gagamitin mong variables payment.intPaymentID, payment.intInvoiceID, payment.strPaymentType, etc.
	 */
	
	public Payment(int intPaymentID, int intInvoiceID, String transactionType, double dblPaymentAmount,
			String paymentType, Date datDateOfPayment) {
		super();
		this.intPaymentID = intPaymentID;
		this.intInvoiceID = intInvoiceID;
		this.transactionType = transactionType;
		this.dblPaymentAmount = dblPaymentAmount;
		this.paymentType = paymentType;
		this.datDateOfPayment = datDateOfPayment;
	}
	
	public static String convertPaymentStatus(int status){
		
		if(status == 1){
			return "INCOMPLETE";
		}
		else if(status == 2){
			return "COMPLETE";
		}
		else
			return "UNPAID";
		
	}

	public int getIntPaymentID() {
		return intPaymentID;
	}

	public void setIntPaymentID(int intPaymentID) {
		this.intPaymentID = intPaymentID;
	}

	public int getIntInvoiceID() {
		return intInvoiceID;
	}

	public void setIntInvoiceID(int intInvoiceID) {
		this.intInvoiceID = intInvoiceID;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getDblPaymentAmount() {
		return dblPaymentAmount;
	}

	public void setDblPaymentAmount(double dblPaymentAmount) {
		this.dblPaymentAmount = dblPaymentAmount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getDatDateOfPayment() {
		return datDateOfPayment;
	}

	public void setDatDateOfPayment(Date datDateOfPayment) {
		this.datDateOfPayment = datDateOfPayment;
	}

	public static String convertToString(int payment){
		
		String converted = "";
		
		switch(payment){
			case 1:
				converted = "DOWN PAYMENT";
				break;
			case 2:
				converted = "COMPLEMENTARY PAYMENT";
				break;
			case 3:
				converted = "FULL PAYMENT";
				break;
			default:
				converted = "Update Payment.convertToString()";
		}
		
		return converted;
	}
	
	public static boolean createPayment(String paymentType, Payment payment, String receipt) throws SQLException{
		
		PaymentService service = new PaymentServiceImpl();
		
		if(paymentType.equalsIgnoreCase("reservation")){
			return service.createReservationPayment(payment, receipt);
		}
		else if(paymentType.equalsIgnoreCase("walkin")){
			return service.createWalkInPayment(payment, receipt);
		}
		else if(paymentType.equalsIgnoreCase("order"))
			return service.createProductSalesPayment(payment, receipt);
		
		return false;
	}
	
	public static List<ProductSales> getAllUnpaidOrder() throws SQLException{
		
		PaymentService service = new PaymentServiceImpl();
		
		return service.getAllUnpaidOrder();
	}
	
	public static List<Reservation> getAllUnpaidReservation() throws SQLException{
		
		return Reservation.getAllReservationNoDetails();
	}

	public static List<WalkIn> getAllUnpaidWalkIn() {
		
		PaymentService service = new PaymentServiceImpl();
		
		try {
			return service.getAllUnpaidWalkIn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
